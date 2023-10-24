package Geometry2;

public class Parallelepiped extends Figure{
    double a,b;

    public Parallelepiped(double a, double b) throws NonNaturalParametr {
        if (a<0|| b<0)
            throw new NonNaturalParametr();
        this.a = a;
        this.b = b;
    }

    @Override
    void calcPerimeter() {

    }
}
