package advent.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Part2 {
    /*
        Advent of code day 1 - part 2
        https://adventofcode.com/2021/day/1
     */
    public static long measurementOfTheDepth(){

        URL url = Part2.class.getResource("input.txt");
        long theMostLastLine = Long.MIN_VALUE, lastLine = Long.MIN_VALUE, currentLine;
        long previousSum = 0;
        long countOfDepthIncreases = -1;

        try {
            assert url != null;
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                currentLine = Long.parseLong(line);

                if(theMostLastLine != Long.MIN_VALUE){
                    if(previousSum < (theMostLastLine + lastLine + currentLine)) countOfDepthIncreases++;
                }

                previousSum = theMostLastLine + lastLine + currentLine;

                theMostLastLine = lastLine;
                lastLine = currentLine;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return countOfDepthIncreases;
    }
}
