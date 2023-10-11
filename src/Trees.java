/*      09.10.2023      */



// Решение задачи "Вырубка деревьев"
// https://acmp.ru/index.asp?main=task&id_task=24



import java.util.ArrayList;
import java.util.List;



public class Trees {
	public static void main(String[] args){
		Trees tree = new Trees(5,3);
        System.out.println(tree.chopTrees());
	}

    private int numberOfTrees, treesLeft;
    private List<Character> stack = new ArrayList<>();
    private List<Object[]> validPattern = new ArrayList<Object[]>();

    public Trees(int numberOfTrees, int treesLeft) {
        if (treesLeft<=numberOfTrees) {
            this.numberOfTrees = numberOfTrees;
            this.treesLeft = treesLeft;
		//	stack.clear();
			//buildTrees(0,0);
			//chopTrees();
		//	for (int i =0; i<validPattern.size();i++)
		//		System.out.println(Arrays.toString(validPattern.get(i)));
		//	System.out.println(validPattern.size());
        //   System.out.println(chopTrees());

        }
        else System.out.println("Неправильные числа");
    }

    public int chopTrees(){
		int counter=1;
		int empty =	numberOfTrees-treesLeft;
		if (empty>0){
			counter += empty;
			for (int i=1; i<=empty;i++){
				int add = (treesLeft*(i+1)-i);
				counter += (numberOfTrees/add);
					if (add<numberOfTrees)
						counter += numberOfTrees-add;
			}
		//	counter += empty*(treesLeft-empty) ;
		}	
		return counter;
		}
		
    


    private void buildTrees(int trees, int empty) {
        int added =0;
        if (trees + empty == numberOfTrees) {
            validPattern.add(stack.toArray().clone());
            return;
        }
        if (trees < treesLeft){
            stack.add('T');
            buildTrees(trees + 1, empty);
            stack.remove(stack.size() - 1);
        }
        if (trees==0 || trees == treesLeft) {
            if (empty<numberOfTrees-treesLeft){
                stack.add('.');
                buildTrees(trees, empty + 1);
                stack.remove(stack.size() - 1);
            }
        }
    }
    private void buildTrees2(int trees, int empty) {
        int added =0;
        if (trees + empty == numberOfTrees) {
            validPattern.add(stack.toArray().clone());
            return;
        }
        if (trees < treesLeft){
            stack.add('T');
            buildTrees(trees + 1, empty);
            stack.remove(stack.size() - 1);
        }
        if (empty < numberOfTrees - treesLeft) {
            if (empty == trees - 1 || trees == treesLeft || trees ==0) {
                stack.add('.');
                buildTrees(trees, empty + 1);
                stack.remove(stack.size() - 1);
            }
        }
    }
}
