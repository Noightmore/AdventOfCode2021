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
        List<Integer> rowOfGuesses = new ArrayList<>();
        int[][] bingoTable = new int[5][5];
        int tablesLineIndex = 0;
        int indexIncrease = 0;
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
                        if(lineSplit[i+indexIncrease].equals("")){
                            bingoTable[tablesLineIndex][i] = Integer.parseInt(lineSplit[i+indexIncrease+1]);
                            indexIncrease++;
                        }else{
                            bingoTable[tablesLineIndex][i] = Integer.parseInt(lineSplit[i+indexIncrease]);
                        }
                    }

                    if(tablesLineIndex == 4){
                        for (Integer rowOfGuess : rowOfGuesses) {
                            for (int i = 0; i < 5; i++) {
                                for (int j = 0; j < 5; j++) {
                                    if (rowOfGuess == bingoTable[i][j]) {
                                        bingoTable[i][j] = 0;
                                    }
                                }
                            }
                            if (iWon(bingoTable)) {
                                if (numberOfTries > numberOfLocalTries) {
                                    numberOfTries = numberOfLocalTries;
                                    result = multipliedSumOfAllUncheckedNumbers(bingoTable, rowOfGuess);
                                    System.out.println(Arrays.deepToString(bingoTable));
                                }
                            }
                            numberOfLocalTries++;
                        }
                        indexIncrease = tablesLineIndex = 0;
                        continue;
                    }
                    indexIncrease = numberOfLocalTries = 0;
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
