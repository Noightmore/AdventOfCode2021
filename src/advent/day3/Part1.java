package advent.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Part1 {
    /*
      Advent of code day 3 - part 1
      https://adventofcode.com/2021/day/3
   */
    public static int getDiagnosis() throws FileNotFoundException {
        URL url = advent.day3.Part1.class.getResource("input1.txt");
        assert url != null;
        File myObj = new File(url.getPath());
        Scanner myReader = new Scanner(myObj);
        int[] zeros = new int[12];
        int[] ones = new int[12];
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            for(int i=0; i < ones.length - 1; i++){
                if(Objects.equals(line.split("")[i], "1")) ones[i]++;
                else if(Objects.equals(line.split("")[i], "0")) zeros[i]++;
            }
        }
        myReader.close();
        for(int i=0; i < ones.length; i++){
            if(ones[i] > zeros[i]){
                gamma.append("1");
                epsilon.append("0");
            }
            else if (ones[i] < zeros[i]){
                gamma.append("0");
                epsilon.append("1");
            }
        }
        return Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(),2);

    }
}
