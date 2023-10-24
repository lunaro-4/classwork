package Geometry2;

public class Triangle extends Figure {
   double a,b,c;

    public Triangle(int a, int b, int c) throws NonNaturalParametr, ImpossibleTriange {
        this.a = a;
        this.b = b;
        this.c = c;
        if (a<0||b<0||c<0)
            throw  new NonNaturalParametr();
        if (a+b<=c||b+c<=a||c+a<=b)
            throw new ImpossibleTriange();
    }
    public void printPerimiter(){
        System.out.println(a+b+c);
    }

    @Override
    void calcPerimeter() {
       perimetr = a+b+c;
    }
}
