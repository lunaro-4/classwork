import java.io.File;
import java.io.IOException;
import java.util.*;


//https://acm.timus.ru/problem.aspx?space=1&num=1210


public class Ivanushka {
    public static void main(String[] args) throws IOException {
//        System.out.println(Arrays.deepToString(readFromFile("INPUT.TXT")));
        Layer[] nodeMap = readFromFile("INPUT.TXT");
        Solution s = new Solution();
        //convert(nodeMap);
//        System.out.println(findSolution(nodeMap, 0, 1, s));
        findSolution(nodeMap, nodeMap.length-1, 0,s,-1);
        System.out.println("Ответ: " +s.minPath);

    }

    private static int findSolution(Layer[] nodeMap, int layerIndex, int prevNodesCost, Solution s, int nodeIndex) {
        if (layerIndex>=0){
            int snap = s.curPath;
            if ( nodeIndex == -1) {
                nodeIndex = nodeMap[layerIndex].nodeList.length - 1;
                for (; nodeIndex >=0; nodeIndex--) {
                    for (int j = 0; j < nodeMap[layerIndex].nodeList[nodeIndex].connectionList.size(); j++) {
                        s.curPath +=nodeMap[layerIndex].nodeList[nodeIndex].connectionList.get(j).cost;
                        System.out.println(""+layerIndex + "  "+ nodeIndex+ "  "+ s.curPath);
                        findSolution(nodeMap,layerIndex-1,prevNodesCost,s,nodeMap[layerIndex].nodeList[nodeIndex].connectionList.get(j).prevNodeIndex-1 );
                        s.curPath = snap;

                    }

                }
            } else{
                for (int j = 0; j < nodeMap[layerIndex].nodeList[nodeIndex].connectionList.size(); j++) {
                    s.curPath +=nodeMap[layerIndex].nodeList[nodeIndex].connectionList.get(j).cost;
                    System.out.println(""+layerIndex + "  "+ nodeIndex+ "  "+ s.curPath);
                    findSolution(nodeMap,layerIndex-1,prevNodesCost,s,nodeMap[layerIndex].nodeList[nodeIndex].connectionList.get(j).prevNodeIndex-1 );
                    s.curPath = snap;

            }
            }
        }else {
            System.out.println("\t" + s.curPath + "  " + s.minPath);
            if (s.curPath < s.minPath)
                s.minPath = s.curPath;
        }
        return 0;
    }
    private static void convert2(Layer[] nodeMap){
        int counter = 0;
        for (int i = 0; i < nodeMap.length; i++) {
            for (int j = 0; j < nodeMap[i].nodeList.length; j++) {
                counter++;
            }
        }
        HashMap<String,Integer> dict = HashMap.newHashMap(counter);
        counter =0;
        int[][] adjMatr = new int[counter][counter-1];
        for (int i = 1; i <= nodeMap.length; i++) {
            for (int j = 0; j < nodeMap[i].nodeList.length; j++) {
                int[] nArr = new int[counter];
                for (int k = 0; k < nodeMap[i].nodeList[j].connectionList.size(); k++) {
                    String s =""+i+'.'+j;
                    counter++;
                    dict.put(s, counter);

                }
            }
        }
    }


    private static void convert(Layer[] nodeMap){
        Node[] nArr = new Node[1];
        Layer l = new Layer(nArr, 0);
        Layer[] newMap = new Layer[nodeMap.length];
        newMap[0] = l;
        for (int i = 1; i <= nodeMap.length; i++) {
            for (int j = 0; j < nodeMap[i].nodeList.length; j++) {
                List<Connection>[] cListArr = new List[nodeMap[i-1].nodeList.length];
                for (int k = 0; k < cListArr.length; k++) {
                    List<Connection> cList = new ArrayList<>();
                    cListArr[k] = cList;
                }
                for (int k = 0; k < nodeMap[i].nodeList[j].connectionList.size(); k++) {
                   Connection c = new Connection(   nodeMap[i].nodeList[j].connectionList.get(k).prevNodeIndex,
                                                    nodeMap[i].nodeList[j].connectionList.get(k).cost );
                    for (int m = 0; m < nodeMap[i-1].nodeList.length; m++) {
                        cListArr[m].add(c);
                    }
                    for (int m = 0; m < cListArr.length; m++) {
                        Node n = new Node(cListArr[m], i);
                        newMap[i-1].nodeList[m] = n;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(nodeMap));

    }


private static void checkNode(){

}




    private static Layer[] readFromFile(String filename)throws IOException{
        Scanner sc = new Scanner(new File(filename));
        int layers = sc.nextInt();
        Layer[] nodeMap = new Layer[layers];
        for (int i = 0; i < layers; i++) {
            int nodesInLayer = sc.nextInt();
            Node[] nList = new Node[nodesInLayer];
            for (int j = 0; j < nodesInLayer; j++) {
                List<Connection> cList = new ArrayList<>();
                int prevNodeIndex = sc.nextInt();
                while (prevNodeIndex!=0) {
                    int nodeCost = sc.nextInt();
                    Connection c = new Connection(prevNodeIndex, nodeCost);
                    cList.add(c);
                    prevNodeIndex = sc.nextInt();
                }
                Node n = new Node(cList, i);
                nList[j] = n;
            }
            Layer l = new Layer(nList, i);
            nodeMap[i] = l;
            try {
                String dump = sc.next();
            } catch(Exception e) {System.out.println("End of File");}
        }
        return nodeMap;
    }





}














class Solution{
    int minPath = 0;
    int curPath = 0;

}

class Layer{
    Node[] nodeList;
    int index;

    public Layer(Node[] ndeList, int index) {
        this.nodeList = ndeList;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "index=" + index +
                "\nnodeList=" + Arrays.toString(nodeList) +
                "}\n";
    }
}
class Node{
    List<Connection> connectionList;
    int layerIndex;
    @Override
    public String toString() {
        return "\n\tNode{" +
                "connectionList=" + connectionList +
                '}';
    }

    public Node(List<Connection> connectionList, int layerIndex) {
        this.connectionList = connectionList;
        this.layerIndex =layerIndex;

    }
}
class Connection{
    int prevNodeIndex;
    int cost;

    public Connection(int prevNodeIndex, int cost) {
        this.prevNodeIndex = prevNodeIndex;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\n\t\tConnection{" +
                "prevNodeIndex=" + prevNodeIndex +
                ", cost=" + cost +
                '}';
    }
}
