import java.util.Random;
public class EightQueens {
    public static void main(String[] args) throws Exception {
        Queen q = new Queen( 5, 5);
        findAnySolution();
    }
    boolean[][] field= new boolean[8][8];

    public static void merge(boolean[][] main, boolean[][] append){
        int counter =0;
        for (int i=0; i<main.length; i++){
            for (int j=0; j<main[i].length; j++){
                if (append[i][j])
                    main[i][j] = true;
                if (main[i][j])
                    counter++;
            }
        }
        //System.out.println(counter);
    }

    public void printField(){
        System.out.println("_  0_ 1_ 2_ 3_ 4_ 5_ 6_ 7_");
        for (int i = 0; i<this.field.length; i++){
            System.out.print(i+ "  ");
            for (int j=0; j<this.field[i].length;j++){
                if (field[i][j])
                    System.out.print("11 ");
                else
                    System.out.print("__ ");


            }
            System.out.println("");
        }
        System.out.println("");
    }
    public static void findAnySolution() throws Exception {
        boolean solutionFound = false;
        Object[][] arr = new Object[8][2];
        int iterCount =0;
        while (!solutionFound){
            EightQueens solution = new EightQueens();

//            if (iterCount>128){
//                System.out.println("Not Found");
//                break;
//            }

            solutionFound = true;
            arr = new Object[8][2];
//            Random rd = new Random();
//            int x =  rd.nextInt(8);
//            int y =  rd.nextInt(8);
            //int x = iterCount/8;
            //int y = iterCount%8;
            Queen queen = new Queen(5,3);
            merge(solution.field, queen.field);
            arr[0] = new Object[]{queen.posXLiteral, queen.posY};
            //searchLoop(arr, solution.field);
            boolean flagBreak = false;
            for (int n=1; n< arr.length;n++) {
                int randomCount = 0;
                while (searchLoopRandom(arr, solution.field, n))//&& randomCount<100000)
                    randomCount++;
//                if (randomCount >=100000){
//                    randomCount =0;
//                    flagBreak = true;
//                    n=1;
//                    break;
//                    }
//                else
                System.out.println(n);
            }
            if (arr[7][0] != null)//&&  flagBreak)
                solutionFound = true;
            else solutionFound = false;
            iterCount++;

        }
        for(int n=0; n<arr.length; n++)
            System.out.println(arr[n][0] + "" +arr[n][1]);
        System.out.println(iterCount);
    }

    public static boolean searchLoopRandom(Object[][] arr, boolean[][] solutionField, int n) throws Exception {
        boolean queenFound =false;
        Queen q= Queen.generateRandomQueen();
        if (!solutionField[q.posX][q.posY]){
            arr[n] = new Object[]{q.posXLiteral, q.posY};
            merge(solutionField, q.field);
            queenFound = true;
            System.out.println(q.posXLiteral+ ""+ q.posY+ " "+n);
        }
        return queenFound;
    }



    public static void searchLoop(Object[][] arr, boolean[][] solutionField) throws Exception {
        for (int n=1; n<arr.length; n++) {
            int i=0,j=0;
            boolean flagIsFree = false;
            for (; (i < arr.length) && solutionField[i][j];) {
                for (;j< arr.length;j++){
                    if (!solutionField[i][j]){
                        flagIsFree = true;
                        break;
                    }
                }
                if (flagIsFree)
                    break;
                else{
                    j=0;
                    i++;}
            }
            if (i< arr.length){
                Queen q = new Queen(i,j);
                arr[n] = new Object[]{q.posXLiteral, q.posY};
                merge(solutionField, q.field.clone());
            }
            else {
                n=1;
                break;
            }
            //solution.printField();
        }

        //return arr;
    }

}

class Queen extends EightQueens {
    int posX, posY;
    char posXLiteral;
    public Queen(int x, int y) throws Exception {
        if (x<0|| y<0)
            throw new Exception("Exception message");
        this.posX = x;
        this.posY = y;
        this.field = strikes(this);

        switch (this.posX) {
            case 0:this.posXLiteral = 'a';
                break;
            case 1:this.posXLiteral = 'b';
                break;
            case 2:this.posXLiteral = 'c';
                break;
            case 3:this.posXLiteral = 'd';
                break;
            case 4:this.posXLiteral = 'e';
                break;
            case 5:this.posXLiteral = 'f';
                break;
            case 6:this.posXLiteral = 'g';
                break;
            case 7:this.posXLiteral = 'h';
                break;
        }
    }

    public static Queen generateRandomQueen() throws Exception {
            Random rd = new Random();
//            int x =  rd.nextInt(8);
//            int y =  rd.nextInt(8);
            int x = (int) (Math.random()*8);
            int y = (int) (Math.random()*8);
            Queen q = new Queen(x,y);
            return q;
    }

    public boolean[][] strikes(Queen q){
        int side = q.field.length;
        for (int i =0; i<side; i++){
            for (int j =0; j<side; j++){
                if (q.posX == i || q.posY == j
                        || q.posY-j == q.posX-i|| q.posY-j ==i- q.posX
                        || j-q.posY == i-q.posX || j- q.posY == q.posX -i
                )
                    q.field[i][j] = true;
            }
        }

        return q.field;
    }

}
