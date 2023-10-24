package Geometry2;

public class Circle extends Figure{

    double r;


    public Circle(double r) throws NonNaturalParametr {
        if (r<0)
            throw new NonNaturalParametr();
        this.r = r;
    }

    @Override
    void calcPerimeter() {
        perimetr = Math.PI*2*r;
    }
}
