package advent.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Part1 {
    /*
      Advent of code day 4 - part 4
      https://adventofcode.com/2021/day/4
   */
    public static int playBingo() throws FileNotFoundException {

        URL url = advent.day4.Part1.class.getResource("input.txt");
        assert url != null;
        File myObj = new File(url.getPath());
        Scanner myReader = new Scanner(myObj);
        boolean isInitialLine = true;
        List<Integer> rowOfGuesses = new ArrayList<Integer>();
        int[][] bingoTable = new int[5][5];
        int tablesLineIndex = 0;
        int indexIncreaser = 0;
        int result = 0;
        int numberOfTries = Integer.MAX_VALUE;
        int numberOfLocalTries = 0;

        while(myReader.hasNextLine()) {

            String line = myReader.nextLine();

            if(isInitialLine){
                for (String num :
                        line.split(",")){
                            rowOfGuesses.add(
                                    Integer.parseInt(num)
                            );
                }
                isInitialLine = false;
                continue;
            }
            if(!"".equals(line)){
                String[] lineSplit = line.split(" ");
                if(tablesLineIndex < 5){
                    for(int i=0;i<5;i++){
                        if(lineSplit[i+indexIncreaser].equals("")){
                            bingoTable[tablesLineIndex][i] = Integer.parseInt(lineSplit[i+indexIncreaser+1]);
                            indexIncreaser++;
                        }else{
                            bingoTable[tablesLineIndex][i] = Integer.parseInt(lineSplit[i+indexIncreaser]);
                        }
                    }

                    if(tablesLineIndex == 4){
                        for (int calledNumberIndex=0; calledNumberIndex < rowOfGuesses.size(); calledNumberIndex++) {
                            // guessing part
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(rowOfGuesses.get(calledNumberIndex) == bingoTable[i][j]){
                                        bingoTable[i][j] = 0;
                                    }
                                }
                            }
                            if(iWon(bingoTable)){
                                if(numberOfTries > numberOfLocalTries){
                                    numberOfTries = numberOfLocalTries;
                                    numberOfLocalTries = 0;
                                    result = multipliedSumOfAllUncheckedNumbers(bingoTable, rowOfGuesses.get(calledNumberIndex));
                                    System.out.println(Arrays.deepToString(bingoTable));
                                }
                            }
                            numberOfLocalTries++;
                        }

                        indexIncreaser = tablesLineIndex = 0;
                        continue;
                    }
                    indexIncreaser = 0;
                    tablesLineIndex++;
                }
            }
        }
        myReader.close();
        return result;
    }

    private static int multipliedSumOfAllUncheckedNumbers(int[][] bingoTable, int calledNumber) {
        int sum = 0;
        for (int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                sum += bingoTable[i][j];
            }
        }
        return sum*calledNumber;
    }

    private static boolean iWon(int[][] bingoTable){
            int zeroCountRows = 0;
            int zeroCountColumns = 0;
            for (int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(bingoTable[i][j] == 0) zeroCountRows++;
                    if(bingoTable[j][i] == 0) zeroCountColumns++;
                }
                if(zeroCountColumns == 5 || zeroCountRows == 5) return true;
                zeroCountRows = zeroCountColumns = 0;
            }
            return false;
    }
}
