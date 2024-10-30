package task02;

import java.io.*;
import java.net.*;
import java.util.*;


public class Main {

	
	public static void main(String[] args) throws Exception {

		List<String> list = new ArrayList<>();
	 
		//System.out.printf("hello, world\n");
		 //Read the file
		//String fileName = "figure1.txt";
		String fileName ="";
		if(args.length>0){
			fileName = args[0];
		} else{
			System.out.println("Error");
		}

		try{
			File file = new File(fileName);
			Reader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);


			while(true){
				String read = br.readLine();
				if(read==null){
					break;
				}
	
				list.add(read);
	
				
			}
	
	
			TicTacToe ttt = new TicTacToe();
			ttt.initiateBoard(3, 3);
			ttt.printBoard();
			System.out.println();
			ttt.populateBoard(list);
			ttt.printBoard();
	
			
	
			ttt.evaluate();

			br.close();
			reader.close();
			

		} catch (FileNotFoundException f){
			System.out.println("Error loading the file");
		}
		 
	


	}
}
