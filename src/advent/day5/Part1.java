package advent.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Part1 {
     /*
      Advent of code day 5 - part 1
      https://adventofcode.com/2021/day/5
   */

    public static int calculateDangerousSpots() throws FileNotFoundException {
        URL url = advent.day5.Part1.class.getResource("inputHelp.txt");
        assert url != null;
        File myObj = new File(url.getPath());
        Scanner myReader = new Scanner(myObj);
        List<Point> allPointsOfInterest = new ArrayList<>();

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] startAndEndCoords = line.split(" -> ");
            String[] startCoords = startAndEndCoords[0].split(",");
            String[] endCoords = startAndEndCoords[1].split(",");

            Point pStart = new Point(Integer.parseInt(startCoords[0]), Integer.parseInt(startCoords[1]));
            Point pEnd = new Point(Integer.parseInt(endCoords[0]), Integer.parseInt(endCoords[1]));
            // && pStart.y == pEnd.y
            if(pStart.x != pEnd.x ){
                for(int i = Math.min(pStart.x, pEnd.x); i <= Math.max(pStart.x, pEnd.x); i++){
                    allPointsOfInterest.add(new Point(i, pStart.y));
                }
            }
            // && pStart.x == pEnd.x
            if(pStart.y != pEnd.y){
                for(int i = Math.min(pStart.y, pEnd.y); i <= Math.max(pStart.y, pEnd.y); i++){
                    allPointsOfInterest.add(new Point(pStart.x, i));
                }
            }
         }
        myReader.close();
        return getCountOfMultiplePointOccurrences(allPointsOfInterest);
    }

    public static class Point{
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int getCountOfMultiplePointOccurrences(List<Point> points){
        int countOfOnePointsOccurrences = 0;
        int countOfDangerousPlaces = 0;
        List<Point> pointsCompare;
        pointsCompare = points;
        int listLen = pointsCompare.size();
        for (int j = 0; j < points.size();j++) {
           //System.out.printf("x:%d y:%d%n", p1.x, p1.y);
            for (int i = 0; i < listLen; i++) {
                if(points.get(j).x == pointsCompare.get(i).x && points.get(j).y == pointsCompare.get(i).y){
                    System.out.printf("x:%d y:%d%n",pointsCompare.get(i).x, pointsCompare.get(i).y);
                    pointsCompare.remove(i);
                    listLen--;
                    countOfOnePointsOccurrences++;
                }
            }
            if(countOfOnePointsOccurrences > 1){
                countOfDangerousPlaces++;
            }
            countOfOnePointsOccurrences = 0;
        }
        return countOfDangerousPlaces;
    }
}
