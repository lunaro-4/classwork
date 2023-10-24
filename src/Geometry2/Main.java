package Geometry2;

import javax.imageio.event.IIOWriteProgressListener;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ImpossibleTriange, NonNaturalParametr {
      int counter =0;
      String filename = "file.txt";
      Scanner sc = new Scanner(new File(filename));
      while (true){
          try{
              String line = sc.nextLine();
              counter++;
          } catch (Exception e){
              break;
          }
      }

    }

    public static Figure readFromFile(String filename, int skipRows) throws IOException, NonNaturalParametr, ImpossibleTriange {
        Scanner sc = new Scanner(new File(filename));
        for (int i = 0; i < skipRows; i++) {
            String line = sc.nextLine();
            String line2 = sc.nextLine();
        }
       Figure fig = null;
        String figType = sc.next();
        try {
            switch (figType) {
                case "Cir":
                    fig = new Circle(sc.nextInt());
                    break;
                case "Par":
                    fig = new Parallelepiped(sc.nextInt(), sc.nextInt());
                    break;
                case "Tri":
                    fig = new Triangle(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    break;
                default:
                    System.out.println("Unrecognised input");
                    return null;
            }
        } catch ( NonNaturalParametr e){
            System.out.println("Обнаружено отрицательное число");
            return null;
        } catch (ImpossibleTriange e){
            System.out.println("Попытка создать невозможный треугольник");
            return null;
        }

        return fig;
    }
}
