import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;

//  https://acmp.ru/index.asp?main=task&id_task=469


public class ChemAlert {
    private static void main(String[] args) throws FileNotFoundException, IOException {
        String filename="INPUT.TXT";
        Scanner sc = new Scanner(new File(filename));
        int yLength = sc.nextInt();
        int xLength = sc.nextInt();
        int[][] costMap = new int[yLength][xLength], originalCost = readInput(filename);
        for (int i = 0; i < yLength; i++) {
            Arrays.fill(costMap[i],9999);
        }
        costMap[0][0]=originalCost[0][0];
        boolean[][] visitMap = new boolean[yLength][xLength];
        visitMap[0][0] = true;
        int y=0,x=0;
        int counter=0;
        while (counter<yLength*xLength-0) {
            int[] directionCost = new int[4];
            Arrays.fill(directionCost,9999);
            int[] newCoords;
            if (calculateDirectionCost(y, visitMap, x, directionCost,
                    costMap, xLength, yLength, originalCost, false)){
                newCoords =  makeMove(directionCost,y,x);
                y=newCoords[0];
                x=newCoords[1];
            }
            else{   // Если алгоритм забрел в тупик, найти первую неисследованую точку
                    // и сделать от неё шаг в ближайшую незагрезненную точку
                newCoords = findUnvisited(visitMap,y,x);
                y=newCoords[0];
                x=newCoords[1];
                boolean stuckFlag = true;
                while(stuckFlag)
                    stuckFlag =calculateThisCost(y,x,costMap,xLength,yLength,originalCost) ;
                calculateDirectionCost(y, visitMap, x, directionCost,
                        costMap, xLength, yLength, originalCost, true);
                if(checkMovement(directionCost))
                    break;
                newCoords = makeMove(directionCost,y,x);
                y=newCoords[0];
                x=newCoords[1];
            }
            visitMap[y][x] = true;
            counter++;
        }
        printMap(costMap);
        for (int i = 0; i < visitMap.length; i++) {
            System.out.println(Arrays.toString(visitMap[i]));
        }
        System.out.println("\n"+"Ответ: "+costMap[yLength-1][xLength-1]);
		String s = String.valueOf(costMap[yLength-1][xLength-1]);
		Files.write(Path.of("OUTPUT.TXT"), s.getBytes());
    }

    private static void printMap(int[][] costMap) {
        for (int i = 0; i < costMap.length; i++) {
            System.out.println(Arrays.toString(costMap[i]));
        }
    }

    private static boolean checkMovement(int[] directionCost){
        int[] test = new int[4];
        Arrays.fill(test, 9999);
        int counterTest =0;
        for (int i = 0; i < test.length; i++) {
            if(test[i]==directionCost[i])
                counterTest++;
        }
        if (counterTest>=4)
            return true;
        else
            return false;
    }

    private static  int[] makeMove(int[] directionCost, int y, int x){
    int minCost = 9999, direction = 0;
    for (int i = 0; i < directionCost.length; i++) {
        if (directionCost[i]<=minCost && directionCost[i]!=9999){
            minCost=directionCost[i];
            direction=i;
        }
    }
    switch (direction){
        case 0: y--;break;
        case 1: x++;break;
        case 2: y++;break;
        case 3: x--;break;
    }
    return new int[]{y,x};
}
    private static int[] findUnvisited(boolean[][] visitMap, int y, int x){
        for (int i = 0; i < visitMap.length; i++) {
            for (int j = 0; j < visitMap[i].length; j++) {
                if (!visitMap[i][j]){
                    y=i;
                    x=j;
                    return new int[]{y,x};
                }
            }
        }
        return new int[]{y,x};
    }
    private static boolean calculateDirectionCost(int y, boolean[][] visitMap, int x, int[] directionCost,
                                               int[][] costMap, int xLength, int yLength,
                                               int[][] originalCost, boolean ignoreVisited) {
        int falseCounter=0;
        for (int i = 0; i < 4; i++) {
            if ((i==0 && y >0 && !visitMap[y -1][x])) {
                costMap[y-1][x] = Math.min(originalCost[y-1][x]+costMap[y][x],costMap[y-1][x]);
                directionCost[i] = costMap[y - 1][x];
            }else if (i==0){falseCounter++;}
            if (i==1 && x < xLength-1 && !visitMap[y][x +1]) {
                costMap[y][x+1] = Math.min(originalCost[y][x+1]+costMap[y][x],costMap[y][x+1]);
                directionCost[i] = costMap[y][x +1];
            }else if (i==1) {falseCounter++;}
            if (i==2 && y < yLength-1 && !visitMap[y +1][x]) {
                costMap[y+1][x] = Math.min(originalCost[y+1][x]+costMap[y][x],costMap[y+1][x]);
                directionCost[i] = costMap[y + 1][x];
            }else if (i==2) {falseCounter++;}
            if (i==3 && x >0 && !visitMap[y][x -1]) {
                costMap[y][x-1] = Math.min(originalCost[y][x-1]+costMap[y][x],costMap[y][x-1]);
                directionCost[i] = costMap[y][x -1];
            }else if (i==3) {falseCounter++;}
        }
        if (falseCounter>=4)
            return false;
        else return true;
    }

    private static boolean calculateThisCost(int y, int x,
                                                  int[][] costMap, int xLength, int yLength,
                                                  int[][] originalCost) {
        int[] directionCost = new int[4];
        Arrays.fill(directionCost,9999);
        int falseCounter=0;
        for (int i = 0; i < 4; i++) {
            if ((i==0 && y >0 )) {
                directionCost[i] = costMap[y - 1][x];
            }else if (i==0){falseCounter++;}
            if (i==1 && x < xLength-1) {
                directionCost[i] = costMap[y][x +1];
            }else if (i==1) {falseCounter++;}
            if (i==2 && y < yLength-1) {
                directionCost[i] = costMap[y + 1][x];
            }else if (i==2) {falseCounter++;}
            if (i==3 && x >0 ) {
                directionCost[i] = costMap[y][x -1];
            }else if (i==3) {falseCounter++;}
        }
        int minCost=costMap[y][x];
        for (int i = 0; i < directionCost.length; i++) {
            minCost=Math.min(minCost, directionCost[i]+originalCost[y][x]);
        }
        costMap[y][x] = minCost;
        boolean stuckFlag = false;
        if (falseCounter>=4)
            stuckFlag = true;
        return stuckFlag;
    }
    private static int[][] readInput(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        int y = sc.nextInt();
        int x = sc.nextInt();
        int[][] costMap = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                costMap[i][j] = sc.nextInt();
            }
        }
        return costMap;
    }

}
