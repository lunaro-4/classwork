import java.util.List;

/*          09.10.2023      */
public class Block {

    private int[][] blocks;
    private int constructOperations;
    private int nextBlocksIndex = 0;
    private List<Integer> runningStack;

    public Block(int n) {
        this.blocks = new int[n][2];

        for (int i=0; i<this.blocks.length;i++ ){
            this.blocks[i] = new int[]{-1, -1};

        }
    }
    public void addBlocks(int[] blocks){
        if (nextBlocksIndex < this.blocks.length) {
            if (blocks[0]>=0 || blocks[1]>=0){
            this.blocks[nextBlocksIndex] = blocks;
            nextBlocksIndex++;
            }else System.out.println("Введите неотрицательные числа!");
        } else System.out.println("Block overflow!");
    }

    public void calkOperations(int left, int right){
        for (int i=0; i<this.blocks.length; i++){
            if (blocks[i] == new int[]{-1, -1}){
                System.out.println("Введите больше блоков!");
                break;
            }
        int cache = 0;
        if (left == right || right == this.blocks.length){
            constructOperations = Math.max(constructOperations, cache);
        }
        if (left<this.blocks.length){
            runningStack.add(0);


        }

        }
    }
}
