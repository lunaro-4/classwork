import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SplittableRandom;
//  https://acmp.ru/index.asp?main=task&id_task=469


public class ChemAlert2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        findLastCost("INPUT.TXT", "OUTPUT.TXT");
    }

    public static void findLastCost(String inpFilename, String outpFilename) throws IOException {
        int[][] originalCost = readInputMatrix(inpFilename);
        int yLength = originalCost.length;
        int xLength = originalCost[0].length;
        int [][] costMap = new int[yLength][xLength];
        boolean[][] visitMap = new boolean[yLength][xLength];
        for (int i = 0; i < costMap.length; i++)
            nullify(costMap[i]);
        costMap[0][0]=originalCost[0][0];
        visitMap[0][0]=true;
        LocalCoords c = new LocalCoords();
        int counter = 0;
        while (findFalse(visitMap, visitMap[yLength-1][xLength-1])){
            int[] adjacentCost= getAdjacentCost(originalCost,costMap,c);
            setAdjacentCost(adjacentCost,costMap,c);
            int[] fixedCosts = fixCosts(adjacentCost,visitMap,c);
//            System.out.println(Arrays.toString(adjacentCost));
            counter++;
           if (makeMove(fixedCosts,c)){
               visitMap[c.y][c.x]=true;

           }else{
               if (goToFalse(visitMap,c))
                   visitMap[c.y][c.x]=true;
               else break;
           }
        }
//        printBoolMatrix(visitMap);
//        printIntMatrix(costMap);
        String s = String.valueOf(costMap[yLength-1][xLength-1]);
        System.out.println(s);
//        System.out.println(counter);
        Files.write(Path.of(outpFilename), s.getBytes());
    }

    public static int[] fixCosts(int[] adjacentCost, boolean[][] visitMap, LocalCoords c){
            int x=c.x,y=c.y;
        for (int i = 0; i <4; i++) {
            if (i==0 && y >0 && visitMap[y-1][x]) {
                adjacentCost[i]=8888;
            }
            if (i==1 && x < visitMap[0].length-1 && visitMap[y][x+1]) {
                adjacentCost[i]=8888;
            }
            if (i==2 && y< visitMap.length-1 && visitMap[y+1][x]) {
                adjacentCost[i]=8888;
            }
            if (i==3 && x >0 && visitMap[y][x-1]) {
                adjacentCost[i]=8888;
            }

        }
        return adjacentCost;
    }
    public static int[] getAdjacentCost(int[][] originalCost, int[][] costMap,LocalCoords c){
        int[] adjacentCost = new int[4];
        nullify(adjacentCost);
        int x = c.x, y = c.y;
        for (int i = 0; i < 4; i++) {
            if (i==0 && y >0 ) {
                adjacentCost[i] =Math.min(costMap[y - 1][x], originalCost[y-1][x]+costMap[y][x]);
            }
            if (i==1 && x < costMap[0].length-1 ) {
                adjacentCost[i] =Math.min(costMap[y][x +1], originalCost[y][x+1]+costMap[y][x]);
            }
            if (i==2 && y < costMap.length-1 ) {
                adjacentCost[i] =Math.min(costMap[y+1][x], originalCost[y+1][x]+costMap[y][x]);
            }
            if (i==3 && x >0 ) {
                adjacentCost[i] =Math.min(costMap[y][x -1], originalCost[y][x-1]+costMap[y][x]);
            }

        }

        return adjacentCost;
    }
    public static void printBoolMatrix(boolean[][] matr){
        for (int i = 0; i < matr.length; i++) {
            System.out.println(Arrays.toString(matr[i]));
        }
    }
    public static void printIntMatrix(int[][] matr){
        for (int i = 0; i < matr.length; i++) {
            System.out.println(Arrays.toString(matr[i]));
        }
    }
    public static void setAdjacentCost(int[] adjacentCost, int[][] costMap,LocalCoords c){
        int x=c.x,y=c.y;
        for (int i = 0; i < 4; i++) {
            if (i==0 && y >0 ) {
                costMap[y - 1][x]=Math.min(costMap[y-1][x], adjacentCost[i]);
            }
            if (i==1 && x < costMap[0].length-1 ) {
                costMap[y][x +1]=Math.min(costMap[y][x+1], adjacentCost[i]);
            }
            if (i==2 && y < costMap.length-1 ) {
                costMap[y+1][x]=Math.min(costMap[y+1][x], adjacentCost[i]);
            }
            if (i==3 && x >0 ) {
                costMap[y][x -1]=Math.min(costMap[y][x-1], adjacentCost[i]);
            }

        }
    }
public static void nullify(int[] arr){
        Arrays.fill(arr, 9999);
}
public static boolean goToFalse(boolean[][] visitMap, LocalCoords c ){
    for (int i = 0; i < visitMap.length; i++) {
        for (int j = 0; j < visitMap[i].length; j++) {
            if (!visitMap[i][j]){
                c.y = i;
                c.x = j;
                return true;
            }
        }
    }

        return false;
}
    public static boolean findFalse(boolean[][] visitMap, boolean lastCell) {
        if (lastCell){
//            return false;
        }
        for (int i = 0; i <visitMap.length; i++) {
            for (int j = 0; j < visitMap[i].length; j++) {
                if (!visitMap[i][j])
                    return true;
            }
        }
        return false;
    }
    private static boolean makeMove(int[] adjacentCost, LocalCoords c){
        int minCost = 9999, direction = 0, falseCounter=0;
        for (int i = 0; i < adjacentCost.length; i++) {
            if (adjacentCost[i]<=minCost && adjacentCost[i]!=9999 && adjacentCost[i]!=8888){
                minCost=adjacentCost[i];
                direction=i;
            } else if (adjacentCost[i]==9999 || adjacentCost[i]==8888) {
                falseCounter++;
            }
        }
        if (falseCounter>=4)
            return false;
        else{
            switch (direction){
                case 0: c.y--;break;
                case 1: c.x++;break;
                case 2: c.y++;break;
                case 3: c.x--;break;
        }
            return true;
        }

    }
    public static int[][] readInputMatrix(String filename) throws FileNotFoundException {
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

class LocalCoords {
    int x=0, y=0;
}
