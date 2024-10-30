package task02;

import java.util.*;

public class TicTacToe {
    
    private static int row;
    private static int col;
    private static char[][] board;

    public static void initiateBoard(int row, int col){
        board = new char[row][col];

        for(int i =0; i<board.length;i++){
            for(int j =0; j<board[0].length;j++){
                board[i][j] = '.';
            }
        }
    }


    public static void printBoard(){
        for(int i =0; i<board.length;i++){
            for(int j =0; j<board[0].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }


    public static void populateBoard(List<String> list){
        for(int i=0; i<list.size();i++){
            String s = list.get(i);
            for(int j =0; j<s.length();j++){
                board[i][j] = s.charAt(j);
            }
             
        }
    }

    public char[][] cloneBoard(char[][] newboard) {
        char[][] clonedBoard = new char[newboard.length][newboard[0].length];
        for (int i = 0; i < newboard.length; i++) {
            for (int j = 0; j < newboard[i].length; j++) {
                clonedBoard[i][j] = newboard[i][j];
            }
        }
        return clonedBoard;
    }

  


    public static Boolean hasMovesAvailable(char[][] initialBoard) {
        // check if any empty squares
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (initialBoard[i][j] == '.') {
                    return true;  

                }
            }
        }

        // no empty .
        return false;
    }

    public static String checkBoard(char[][] newBoard) {
        
        // Check rows
        for(int i=0; i<newBoard.length;i++){
            if(newBoard[i][0]!='.' && newBoard[i][0]==newBoard[i][1] 
                && newBoard[i][1] == newBoard[i][2]){
                    if(newBoard[i][0] =='X'){
                        return "Player 1 has won!";
                    } else {
                        return "Player 2 has won!";
                    }
                }
        }
        
        //Check columns 
        for(int j =0; j<newBoard[0].length;j++){
            if(newBoard[0][j]!= '.' && newBoard[0][j]==newBoard[1][j] &&
                newBoard[1][j] == newBoard[2][j]){
                    if(newBoard[0][j] == 'X'){
                        return "Player 1 has won!";
                    } else{
                        return "Player 2 has won!";
                    }
            }
        }

        //Check for diagonals
        if (newBoard[1][1] != '.') {
            if ((newBoard[0][0] == newBoard[1][1] && newBoard[1][1] == newBoard[2][2]) ||
                (newBoard[0][2] == newBoard[1][1] && newBoard[1][1] == newBoard[2][0])) {
                if (newBoard[1][1] == 'X') {
                    return "Player 1 has won!";
                } else {
                    return "Player 2 has won!";
                }
            }
        }

        //Check for draw 

        if(!hasMovesAvailable(newBoard)){
            return "draw";
        }

        return "Game is still ongoing";
    

    }


    public int miniMax(char[][] newboard, boolean isMaximize){

    
        
        //Check whether anyone win or draw
        String result = checkBoard(newboard);
        if(result.equals("Player 1 has won!")){
            return -1;
        }
        if(result.equals("Player 2 has won!")){
            return 1;
        }
        if(result.equals("draw")){
            return 0;
        }

        if(isMaximize){
            int bestScore = Integer.MIN_VALUE;
            for(int i=0; i<newboard.length;i++){
                for(int j=0; j<newboard[0].length;j++){
                    if(newboard[i][j] == '.'){
                        //clone the board 
                        char[][] cloneBoard = cloneBoard(newboard);
                        cloneBoard[i][j] = 'O';
                        int score = miniMax(cloneBoard,false);
                        if(score>bestScore){
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else{
            
            int bestScore = Integer.MAX_VALUE;
            for(int i=0; i<newboard.length;i++){
                for(int j=0; j<newboard[0].length;j++){
                    if(newboard[i][j] == '.'){
                        char[][] cloneBoard = cloneBoard(newboard);
                        cloneBoard[i][j] = 'X';
                        int score = miniMax(cloneBoard, true);   
                        if(score<bestScore){
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
       
    
    }



    public void evaluate() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {  
                    //Clone the board so wont lose the original contents of the board
                    char[][] cloneBoard = cloneBoard(board);
                    cloneBoard[i][j] = 'X';
    
                    int checkUtility= miniMax(cloneBoard, true);  
            
                    String result="";
                    //Player 2 win 
                    if (checkUtility == 1) { 
                        result = "utility = -1";
                    //if i win 
                    } else if (checkUtility == -1) {
                        result = "utility = 1";
                    //if draw
                    } else if(checkUtility ==0) {
                        result = "utility = 0";
                    }
    
                    // Print the position and its utility
                    System.out.println("y= " + i + ", " + "x= " + j + ", " + result);
                }
            }
        }
    }


}
