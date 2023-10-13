import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Задача про ферзей.
//13.10 Находит случайное расположение, удволетворяющее условиям.


public class EightQueens {
    public static void main(String[] args) throws Exception {
        EightQueens e = new EightQueens();
        int iter =0;
        e.fieldSize =10;
        while (e.queenArray.size() < e.fieldSize && iter<100) {
            e.field = new boolean[e.fieldSize][e.fieldSize];
            e.queenArray.clear();
            findAnySolution2(e);
            iter++;
            System.out.println(iter + " Iterations");
            for (int n = 0; n < e.queenArray.size(); n++)
                System.out.print(e.queenArray.get(n)[0] + "" + e.queenArray.get(n)[1]+"  ");
            System.out.println();
        }
        System.out.println(iter + " Iterations");
        if (iter<100) {
            for (int n = 0; n < e.queenArray.size(); n++)
                System.out.println(e.queenArray.get(n)[0] + "" + e.queenArray.get(n)[1]);
        }else System.out.println("No solution");
    }


    boolean[][] field;
    List<Object[]> queenArray = new ArrayList<>();
    int fieldSize =8;

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


    public static void findAnySolution2(EightQueens inp) throws Exception {
        List<Object[]> arr = inp.queenArray;
        if (arr.size() <= inp.fieldSize-1) {
            for (int i = 0; i < 1000; i++) {
                searchLoopRandom2(inp);
            }
        }
    }


    public static boolean searchLoopRandom2(EightQueens inp) throws Exception {

        for (int i = 0; i < 1000; i++) {
            Queen q = Queen.generateRandomQueen(inp.field.length);
            if (!inp.field[q.posX][q.posY]) {
                inp.queenArray.add(new Object[]{q.posXLiteral, q.posY});
                merge(inp.field, q.field);
                return true;
            }
        }
        return false;
    }public static boolean searchLoopRandom1(List<Object[]> arr, boolean[][] solutionField, int fieldSize) throws Exception {

        for (int i = 0; i < 1000; i++) {
            Queen q = Queen.generateRandomQueen(fieldSize);
            if (!solutionField[q.posX][q.posY]) {
                arr.add(new Object[]{q.posXLiteral, q.posY});
                merge(solutionField, q.field);
                return true;
            }
        }
        return false;
    }


}
class Queen extends EightQueens {
    int posX, posY;
    char posXLiteral;

    public Queen(int x, int y, int fieldSize) throws Exception {
        if (x < 0 || y < 0)
            throw new Exception("Exception message");
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

    public static Queen generateRandomQueen(int fieldSize) throws Exception {
        Random rd = new Random();
//            int x =  rd.nextInt(8);
//            int y =  rd.nextInt(8);
        int x = (int) (Math.random() * fieldSize);
        int y = (int) (Math.random() * fieldSize);
        Queen q = new Queen(x, y, fieldSize);
        return q;
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
