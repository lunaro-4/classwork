import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Classwork {
    public static void main(String[] args) throws IOException {
        //bankomat(16765, a);
        //compare(userInput());
        //findNum(new int[]{1,3,2,4,5,2,5,5,1}, 2);
        //Trees tree = new Trees(5,3);
        //System.out.println(tree.chopTrees());
        //findDuplicate(new int[]{1,2,1,4,2,5,7,8,9,10,1});
        //arrCountUnique(new int[]{1,3,4,1,1,2,3,6});
        //triangleFromFile("tri.txt");

        for (int i = 0; i < 10;i++) {
            int [][] matr = generateMatrix(10,10,0, 100);
            String s = i+" matrix.txt";
            printIntMatrixToFile(matr,s);
        }
        for (int i = 0; i < 10; i++) {
            String inp = i+" matrix.txt";
            String outp = i+" ans.txt";
            ChemAlert2.findLastCost(inp, outp);
        }
    }




    public static void printIntMatrixToFile(int[][] matr, String filename) throws IOException {
        String s =matr.length + " "+ matr[0].length+ "\n";

        for (int n = 0; n < matr.length; n++)
            s+= Arrays.toString(matr[n]).replace('[',' ').replace(']',' ').replace(',','\t') + "\n";
           // s += inp.queenArray.get(n)[0] + "" + inp.queenArray.get(n)[1] + "   ";
        s += "\n";
        Files.write(Path.of(filename), s.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.WRITE);

    }

    private static void bankomat(int value, int[] haveTickets) {
        int tickets = value;
        int k5 = 0,k1 = 0, h5 = 0,h1= 0;
        while (tickets > 0) {
            if (tickets - 5000 >= 0 && haveTickets[3] >0 ) {
                while (tickets - 5000 >= 0 && haveTickets[3] >0  ){
                tickets -= 5000;
                k5++;
                haveTickets[3]--;}
            }
            if (tickets - 1000 >= 0 && haveTickets[2] >0 || haveTickets[3] <=0) {
                while (tickets - 1000 >= 0 && haveTickets[2] >0  ){
                    tickets -= 1000;
                k1++;
                haveTickets[2]--;}
            }  if (tickets - 500 >= 0 && haveTickets[1] >0 || haveTickets[2] <=0) {
                while (tickets - 500 >= 0 && haveTickets[1] >0  ){
                tickets -= 500;
                h5++;
                haveTickets[1]--;}
            }  if (tickets - 100 >= 0 && haveTickets[0] >0 || haveTickets[1] <=0) {
                while (tickets - 100 >= 0 && haveTickets[0] >0  ){
                tickets -= 100;
                h1++;
                haveTickets[0]--;}
            }  if (tickets - 100 < 0) {
                break;
            } else break;
        }
        System.out.print("h1 = "+h1+" ");
        System.out.print("h5 = "+h5+" ");
        System.out.print("k1 = "+k1+" ");
        System.out.print("k5 = "+k5+" ");
        System.out.print(tickets+" ");
    }


    public static void circles(double[] a, double[] b){
        double xDistance = Math.pow((a[0] - b[0]),2);
        double yDistance = Math.pow((a[1] - b[1]),2);
        double distance = Math.sqrt(xDistance + yDistance);
        if (distance <= a[2]+b[2])
            System.out.println("Yes");
        else if (distance >= a[2]-b[2])
            System.out.println("Yes");
        else
            System.out.println("No");
    }

 // 06.10.2023

    /*              09.10.2023          */

    public static void findNum(int[] arr, int num){
        int n = 0;
        for (int i=0;i<arr.length;i++ ){
            if (arr[i]==num)
                n++;
        }
        System.out.println(n);
    }

    /*      12.10.2023      */

    public static void findDuplicate(int[] arr){
        int counter =0;
        boolean[] used = new boolean[arr.length];
        for (int i=0; i<arr.length;i++ ){
            for (int j = i+1;j<arr.length;j++){
                if (arr[i] == arr[j] && !used[j]){
                    used[j] = true;
                    used[i] = true;
                }
            }
        }
        for (int i=0; i<used.length; i++) {
            if (used[i])
                counter++;
        }
        System.out.println(counter);
    }

    public static void arrCountUnique(int[] arr){
        Set<Integer> set = new TreeSet<>();
        for (int i=0; i<arr.length; i++)
            set.add(arr[i]);
        System.out.println(set.size());
    }

    public static void triangleFromFile(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        int p = 0;
        for (int i=0; i<3;i++) {
            p+=sc.nextInt();
        }
        System.out.println(p);

    }

    public static int[] generateArray(int start, int end, int n){
        int [] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random()*end)-start;
            arr[i] = x;
        }
        return arr;
    }
    public static int[][] generateMatrix(int y, int x, int start, int end){
        int[][] matr = new int[y][x];
        for (int i = 0; i < y; i++) {
            int[] arr = generateArray(start,end,x);
            matr[i] = arr;
        }
       return matr;
    }

}