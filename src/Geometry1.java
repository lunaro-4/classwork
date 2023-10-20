import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Geometry1 {
    public static void main(String[] args) throws IOException {
        ArrayList<Triangle> tList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Triangle t = new Triangle(readFromFile("triinput.txt", i));
            tList.add(t);
        }
        System.out.println("Средний периметр: "+avgPerim(tList));
        System.out.println("Средняя площадь: "+avgArea(tList));
    }


    private static Point[] readFromFile(String filename, int skipRows) throws IOException {
        Scanner sc = new Scanner(new File(filename));
        for (int i = 0; i < skipRows; i++) {
            String line = sc.nextLine();
        }
        Point[] pArr = new Point[3];
        for (int i = 0; i < 3; i++) {
            Point p = new Point(sc.nextInt(), sc.nextInt());
            pArr[i]= p;
        }
        return pArr;
    }

    private static double avgPerim(ArrayList<Triangle> tList){
        double perSum =0;
        for (int i = 0; i < tList.size(); i++) {
            perSum+=tList.get(i).perim;
        }
       return perSum/ tList.size();
    }

    private static double avgArea(ArrayList<Triangle> tList) {
        double areaSum =0;
        for (int i = 0; i < tList.size(); i++) {
            areaSum+=tList.get(i).area;
        }
        return areaSum/ tList.size();
    }


}







class Point{
    int x,y;

    @Override
    public String toString() {
        return "{" + x +
                ", " + y +
                '}';
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Triangle{
    Point A, B, C;
    double lenAB,lenBC,lenCA;
    double perim, area;

    public Triangle(Point[] pArr) {
        this.A = pArr[0];
        this.B = pArr[1];
        this.C = pArr[2];
        lenAB = calcSide(A,B);
        lenBC = calcSide(B,C);
        lenCA = calcSide(C,A);
        perim = lenAB + lenBC + lenCA;
        calcArea();
    }


    private double calcSide(Point A, Point B){
        int xDist = A.x - B.x;
        int yDist = A.y - B.y;
        return Math.sqrt(xDist*xDist+yDist*yDist);
    }

    private void calcArea(){
        double p = this.perim/2;
        this.area = Math.sqrt(p*(p-lenAB)*(p-lenBC)*(p-lenCA));
}


    @Override
    public String toString() {
        return "Triangle{" +
                "A=" + A +
                ", B=" + B +
                ", C=" + C +
                ", AB=" + lenAB +
                ", BC=" + lenBC +
                ", CA=" + lenCA +
                ", perim=" + perim +
                '}';
    }
}

