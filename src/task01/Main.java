package task01;

// Use this class as the entry point of your program
import java.util.*;
import java.io.*;
import java.util.Map.Entry;
import java.util.stream.*;

public class Main {

	public static void main(String[] args) {

		// System.out.printf("hello, world\n");
		String fileName = "day.csv";

		try {

			ArrayList<String> list = new ArrayList<>();

			System.out.println(">>>>" + fileName);
			Map<Integer, NewBikeEntry> bikeMap = parseCSV(fileName);
			System.out.println(bikeMap.size());

			List<Entry<Integer, NewBikeEntry>> getHighest = bikeMap.entrySet().stream()
					.filter(p -> !Double.isNaN(p.getValue().getRegistered())) // Filter out NaN values for registered
					.sorted((s1, s2) -> {
						// Calculate totals for both entries
						int sum1 = s1.getValue().getRegistered() + s1.getValue().getCasual();
						int sum2 = s2.getValue().getRegistered() + s2.getValue().getCasual();

						 
						int compareSum =0;
						int compareReg = 0;
						int compareCasual =0;
						compareSum = Double.compare(sum2,sum1);
						if(compareSum!=0){
							return compareSum;
						}

						compareReg = Double.compare(s2.getValue().getRegistered(), s1.getValue().getRegistered());
						if(compareReg!=0){
							return compareReg;
						}

						compareCasual = Double.compare(s2.getValue().getCasual(), s2.getValue().getCasual());
						return compareCasual;
							
					})
					.limit(5) // Take the top 5
					.collect(Collectors.toList());

			int position = 5;

			for (Entry<Integer, NewBikeEntry> highestRatedApp : getHighest) {

				String pos = Integer.toString(position);

				if (pos.equals("5")) {
					pos = "highest";
				} else if (pos.equals("4")) {
					pos = "second highest";
				} else if (pos.equals("3")) {
					pos = "third highest";
				} else if (pos.equals("2")) {
					pos = "fourth highest";
				} else if (pos.equals("1")) {
					pos = "last";
				}

				// Season
				String s = Integer.toString(highestRatedApp.getValue().getSeason());

				if (s.equals("4")) {
					s = "Winter";
				} else if (s.equals("3")) {
					s = "fall";
				} else if (s.equals("2")) {
					s = "spring";
				} else {
					s = "Summer";
				}

				// day
				String d = Integer.toString(highestRatedApp.getValue().getWeekday());

				if (d.equals("1")) {
					d = "Monday";
				} else if (d.equals("2")) {
					d = "Tuesday";
				} else if (d.equals("3")) {
					d = "Wednesday";
				} else if (d.equals("4")) {
					d = "Thursday";
				} else if (d.equals("5")) {
					d = "Friday";
				} else if (d.equals("6")) {
					d = "Saturday";
				} else {
					d = "Sunday";
				}

				// Month
				String m = Integer.toString(highestRatedApp.getValue().getWeekday());

				if (m.equals("1")) {
					m = "January";
				} else if (m.equals("2")) {
					m = "Feburary";
				} else if (m.equals("3")) {
					m = "March";
				} else if (m.equals("4")) {
					m = "April";
				} else if (m.equals("5")) {
					m = "May";
				} else if (m.equals("6")) {
					m = "June";
				} else if (m.equals("7")) {
					m = "July";
				} else if (m.equals("8")) {
					m = "August";
				} else if (m.equals("9")) {
					m = "Septemebr";
				} else if (m.equals("10")) {
					m = "October";
				} else if (m.equals("11")) {
					m = "November";
				} else {
					m = "December";
				}

				// Total
				int sum = highestRatedApp.getValue().getRegistered() + highestRatedApp.getValue().getCasual();

				list.add(" The " + pos + " recorded number of cyclists was in " + s + "," + "on a " + d +
						" in the month of " + m + ". " + "There were a total of " + sum + " cylists.");

				System.out.println();

				position--;

			}

			for (String out : list) {
				System.out.println(out);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Map<Integer, NewBikeEntry> parseCSV(String fileName) {

		Map<Integer, NewBikeEntry> bikeMapper = new HashMap<>();

		try {

			// Read file
			File file = new File(fileName);
			Reader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);

			String[] headers = parseLine(br.readLine()); // skip the header line
			String line;
			try {

				while (true) {
					line = br.readLine();

					if (line == null) {
						break;
					}

					String[] values = line.split(",");

					NewBikeEntry bikeEntry = new NewBikeEntry(
							Integer.parseInt(values[0]), // Season
							Integer.parseInt(values[1]), // Month
							Integer.parseInt(values[2]), // Holiday 0 or 1
							Integer.parseInt(values[3]),
							Integer.parseInt(values[4]),
							Float.parseFloat(values[5]),
							Float.parseFloat(values[6]),
							Float.parseFloat(values[7]),
							Integer.parseInt(values[8]),
							Integer.parseInt(values[9])

					);
					// BikeEntry bikeEntry = new BikeEntry();
					// bikeEntry.toBikeEntry(values);

					bikeMapper.put(bikeEntry.getRegistered(), bikeEntry);

				}

			} catch (IOException io) {
				io.printStackTrace();
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();

		} catch (IOException io2) {
			io2.printStackTrace();
		}

		return bikeMapper;

	}

	// This manually parse a csv line, handling quoted fields correctly
	public static String[] parseLine(String line) {
		List<String> result = new ArrayList<>();
		StringBuilder currentField = new StringBuilder();
		boolean insideQuotes = false;

		for (int i = 0; i < line.length(); i++) {
			char currentChar = line.charAt(i);

			if (currentChar == '\"') {
				// Toggle insideQuotes when encountering a quote character
				insideQuotes = !insideQuotes;
			} else if (currentChar == ',' && !insideQuotes) {
				// If we encounter a comma outside of quotes, it's the end of a field
				result.add(currentField.toString().trim());
				currentField.setLength(0); // Reset the field buffer
			} else {
				// Add the character to the current field
				currentField.append(currentChar);
			}
		}

		// Add the last field to the result (since the line might not end with a comma)
		result.add(currentField.toString().trim());

		return result.toArray(new String[0]);
	}

}