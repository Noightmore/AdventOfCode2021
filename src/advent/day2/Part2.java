package advent.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Part2 {
    /*
       Advent of code day 2 - part 2
       https://adventofcode.com/2021/day/2
    */
    public static long getFinalPosition() {

        URL url = Part1.class.getResource("input2.txt");
        String[] formattedLine;
        long horizontalPos = 0;
        long depth = 0;
        long aim = 0;


        try {
            assert url != null;
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                formattedLine = line.split("\\s+");
                switch (formattedLine[0]){
                    case "forward":
                        horizontalPos += Long.parseLong(formattedLine[1]);
                        depth += aim * Long.parseLong(formattedLine[1]);
                        break;
                    case "down":
                        aim += Long.parseLong(formattedLine[1]);
                        break;
                    case "up":
                        aim -= Long.parseLong(formattedLine[1]);
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return horizontalPos*depth;
    }
}
