import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Задача про ферзей.
//13.10 Находит случайное расположение, удволетворяющее условиям.
//13.10 Находит первое значение с испольтзованимем рекурсии
//15.10 Находит все расположения
//16.10 записывает расположения в файл "solutions"


public class EightQueens {
    public static void main(String[] args) throws Exception {
        EightQueens e = new EightQueens();
        e.fieldSize = 8;            // изменить для другого размера доски
        String filename = "solutions";
        File file =new File(filename);
        if (file.exists())
            file.delete();
        Files.write(Path.of(filename), "".getBytes());
        //attempt1(e);
        e.field = new boolean[e.fieldSize][e.fieldSize];
        findFirstSolution(e, 0);
        for (int n = 0; n < e.queenArray.size(); n++)
            System.out.println(e.queenArray.get(n)[0] + "" + e.queenArray.get(n)[1]);
        System.out.println(e.solutionCounter);
    }


    boolean[][] field;
    List<Object[]> queenArray = new ArrayList<>();
    int fieldSize = 8;
    int solutionCounter = 0;


    public static int merge(boolean[][] main, boolean[][] append) {
        int counter = 0;
        for (int i = 0; i < main.length; i++) {
            for (int j = 0; j < main[i].length; j++) {
                if (append[i][j])
                    main[i][j] = true;
                if (main[i][j])
                    counter++;
            }

        }
        //System.out.println(counter);
        return counter;
    }

    public void printField() {
        System.out.println("_  0_ 1_ 2_ 3_ 4_ 5_ 6_ 7_");
        for (int i = 0; i < this.field.length; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < this.field[i].length; j++) {
                if (field[i][j])
                    System.out.print("11 ");
                else
                    System.out.print("__ ");

            }
            System.out.println("");
        }
        System.out.println("");
    }


    public static boolean findFirstSolution(EightQueens inp, int row) throws IOException {
        boolean[][] snap = new boolean[inp.fieldSize][inp.fieldSize];
        copyMatrix(inp.field, snap);
        if (row < inp.fieldSize) {
            for (int i = 0; i < inp.fieldSize; i++) {
                if (inp.field[row][i]) ;
                else {
                    Queen q = new Queen(row, i, inp.fieldSize);
                    inp.queenArray.add(new Object[]{q.posXLiteral, q.posY});
                    merge(inp.field, q.field);
                    if (findFirstSolution(inp, row + 1))
                        return true;
                    else {
                        inp.queenArray.remove(inp.queenArray.size() - 1);

                        copyMatrix(snap, inp.field);
                        //return false;
                    }
                }
                //return false;
            }
            return false;
        }
        inp.solutionCounter++;
        String s = String.valueOf(inp.solutionCounter) + "\n";
        for (int n = 0; n < inp.queenArray.size(); n++)
            s += inp.queenArray.get(n)[0] + "" + inp.queenArray.get(n)[1] + "   ";
        s += "\n";
        Files.write(Path.of("solutions"), s.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return false;
    }


    public static void copyMatrix(boolean[][] in, boolean[][] out) {
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[i].length; j++) {
                out[i][j] = in[i][j];
            }
        }
    }
}
class Queen extends EightQueens {
    int posX, posY;
    char posXLiteral;

    public Queen(int x, int y, int fieldSize) {
        if (x < 0 || y < 0);
        //    throw new Exception("Exception message");
        this.posX = x;
        this.posY = y;
        this.field = strikes(this, fieldSize);

        switch (this.posX) {
            case 0:
                this.posXLiteral = 'a';
                break;
            case 1:
                this.posXLiteral = 'b';
                break;
            case 2:
                this.posXLiteral = 'c';
                break;
            case 3:
                this.posXLiteral = 'd';
                break;
            case 4:
                this.posXLiteral = 'e';
                break;
            case 5:
                this.posXLiteral = 'f';
                break;
            case 6:
                this.posXLiteral = 'g';
                break;
            case 7:
                this.posXLiteral = 'h';
                break;
        }
    }

    public boolean[][] strikes(Queen q, int side) {
        q.field = new boolean[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                if (q.posX == i || q.posY == j
                        || q.posY - j == q.posX - i || q.posY - j == i - q.posX
                        || j - q.posY == i - q.posX || j - q.posY == q.posX - i
                )
                    q.field[i][j] = true;
            }
        }

        return q.field;
    }

}
