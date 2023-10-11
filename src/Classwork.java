import java.util.*;

public class Classwork {
    public static void main(String[] args) {
        //bankomat(16765, a);
        //compare(userInput());
        //findNum(new int[]{1,3,2,4,5,2,5,5,1}, 2);
        Trees tree = new Trees(5,3);
        System.out.println(tree.chopTrees());
    }


    //  todo: К пятнице 13.10.23 быстрая сортировка Хоара, реализация
    //  todo: задача о восьми ферзях



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


    private static void compare(int userI){
        if (userI > 0)
            doSquare(userI);
        else if (userI < 0)
            doHalfAbs(userI);
        else
            System.out.println("Введен ноль");
    }
    private static void doHalfAbs(int a) {
        a = Math.abs(a);
        a /=2;
        System.out.println("Половина модуля числа: " + a);
    }
    private static void doSquare(int a) {
        a *=a;
        System.out.println("Квадрат числа: " + a);
    }
    private static int userInput() {
        System.out.print("Введите число: ");
        Scanner scanner = new Scanner(System.in);
        int userI = scanner.nextInt();
        return userI;
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

}