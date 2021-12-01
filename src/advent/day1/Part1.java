package advent.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Part1 {

    public static long measurementOfTheDepth(){

        URL url = Part1.class.getResource("input1.txt");
        boolean isFirstLine = true;
        long lastLine = Long.MIN_VALUE, currentLine;
        long countOfDepthIncreases = 0;

        try {
            assert url != null;
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                currentLine = Long.parseLong(line);

                if(isFirstLine){
                    isFirstLine = false;
                    lastLine = currentLine;
                    continue;
                }

                if(currentLine > lastLine) countOfDepthIncreases++;

                lastLine = currentLine;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return  countOfDepthIncreases;
    }
}
