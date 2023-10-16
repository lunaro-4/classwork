import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//  https://acmp.ru/index.asp?main=task&id_task=469
public class ChemAlert {


    public static void main(String[] args) throws FileNotFoundException {
        String filename="ChemInput.txt";
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
        int pathCost =0;
        int y=0,x=0;
        int counter=0;
        while (counter<yLength*xLength) {
            pathCost+=costMap[y][x];
            int[] directionCost = new int[4];
            Arrays.fill(directionCost,9999);
            if (calculateDirectionCost(y, visitMap, x, directionCost,
                    costMap, xLength, yLength, pathCost, originalCost))
                ;
            else{
                int[] newCoords = findUnvisited(visitMap,y,x);
                y=newCoords[0];
                x=newCoords[1];
                calculateDirectionCost(y, visitMap, x, directionCost,
                        costMap, xLength, yLength, pathCost, originalCost);

            }
            visitMap[y][x] = true;
            counter++;
        }
        System.out.println(pathCost);
    }
private static  void makeMove(double[] directionCost, int y, int x){

    int minCost = 9999, direction = 0;
    for (int i = 0; i < directionCost.length; i++) {
        if (directionCost[i]<=minCost && directionCost[i]!=9999){
            minCost=directionCost[i];
            direction=i;
        }
    }
    switch (direction){
        case 0: y--;break;
        case 3: x++;break;
        case 2: y++;break;
        case 1: x--;break;
    }
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
    }
    private static boolean calculateDirectionCost(int y, boolean[][] visitMap, int x, int[] directionCost,
                                               int[][] costMap, int xLength, int yLength,
                                               int pathCost,int[][] originalCost) {
        int falseCounter=0;
        for (int i = 0; i < 4; i++) {
            if (i==0 && y >0 && !visitMap[y -1][x]) {
                costMap[y-1][x] = Math.min(originalCost[y-1][x]+costMap[y][x],costMap[y-1][x]);
                directionCost[i] = costMap[y - 1][x];
            }else falseCounter++;
            if (i==3 && x < xLength-1 && !visitMap[y][x +1]) {
                costMap[y][x+1] = Math.min(originalCost[y][x+1]+costMap[y][x],costMap[y][x+1]);
                directionCost[i] = costMap[y][x +1];
            }else falseCounter++;
            if (i==2 && y < yLength-1 && !visitMap[y +1][x]) {
                costMap[y+1][x] = Math.min(originalCost[y+1][x]+costMap[y][x],costMap[y+1][x]);
                directionCost[i] = costMap[y + 1][x];
            }else falseCounter++;
            if (i==1 && x >0 && !visitMap[y][x -1]) {
                costMap[y][x-1] = Math.min(originalCost[y][x-1]+costMap[y][x],costMap[y][x-1]);
                directionCost[i] = costMap[y][x -1];
            }else falseCounter++;
        }
        if (falseCounter>=4)
            return false;
        else return true;
    }

    public static int[][] readInput(String filename) throws FileNotFoundException {
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
