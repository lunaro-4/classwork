import java.io.File;
import java.io.IOException;
import java.util.*;


//https://acm.timus.ru/problem.aspx?space=1&num=1210


public class Ivanushka {
    public static void main(String[] args) throws IOException {
//        System.out.println(Arrays.deepToString(readFromFile("INPUT.TXT")));
        Layer[] nodeMap = readFromFile("INPUT.TXT");
//        System.out.println(Arrays.toString(nodeMap));

        Solution s = new Solution();
        findSolution1(nodeMap, nodeMap.length-1, 0,s,-1);
        int [][] adjMatr = convert2(nodeMap);
        System.out.println("Ответ: " +s.minPath);

        int[] costToNodes = findSolution2(adjMatr);
        int minValue = Integer.MAX_VALUE;
        for (int i =costToNodes.length-1; i >costToNodes.length- nodeMap[nodeMap.length-1].nodeList.length; i--) {
            minValue = Math.min(minValue, costToNodes[i]);
        }
        System.out.println("Ответ: " + minValue);


    }

    private static int findSolution1(Layer[] nodeMap, int layerIndex, int prevNodesCost, Solution s, int nodeIndex) {
        if (layerIndex>=0){
            if ( nodeIndex == -1) {
                for (nodeIndex = nodeMap[layerIndex].nodeList.length - 1; nodeIndex >=0; nodeIndex--) {
                    for (int j = 0; j < nodeMap[layerIndex].nodeList[nodeIndex].connectionList.size(); j++) {
                        checkConnection(nodeMap,layerIndex,prevNodesCost,s,nodeIndex,j);
                    }
                }
            } else{
                for (int j = 0; j < nodeMap[layerIndex].nodeList[nodeIndex].connectionList.size(); j++) {
                    checkConnection(nodeMap,layerIndex,prevNodesCost,s,nodeIndex,j);
                }
            }
        }else {
//            System.out.println("\t" + s.curPath + "  " + s.minPath);
            if (s.curPath < s.minPath)
                s.minPath = s.curPath;
        }
        return 0;
    }


    private static int[] findSolution2(int[][] adjMatr){
        int[] costToNode = new int[adjMatr[0].length];
        Arrays.fill(costToNode, 999999);
        for (int i = 1; i < adjMatr.length; i++) {
            for (int j = 1; j < adjMatr[i].length; j++) {
                int minCost;
                if (i==1)
                    minCost = adjMatr[i][j];
                else
                    minCost = Math.min(costToNode[i]+adjMatr[i][j], costToNode[j]);

                costToNode[j]=minCost;
            }
        }
//        System.out.println(Arrays.toString(costToNode));
        return costToNode;
    }



    private static int[][] convert2(Layer[] nodeMap){
        //  converts nodeMap to adjacency matrix

        Layer[] newMap = new Layer[nodeMap.length+1];
        Node[] nList = new Node[1];
        Layer l0 = new Layer(nList ,0);
        newMap[0] = l0;
        for (int i = 0; i < nodeMap.length; i++) {
            newMap[i+1]=nodeMap[i];
        }

        int counter = 0;
        for (int i = 0; i < nodeMap.length; i++) {
            for (int j = 0; j < nodeMap[i].nodeList.length; j++) {
                counter++;
            }
        }
        counter+=1;
        HashMap<String,Integer> nameToMatrIndex = new HashMap<>();
        // For some reason, algorythm creates -1.1 node, that does not lead anywhere,
        // but only in y-axis. So we add 1 to x-axis to compensate.
        int[][] adjMatr = new int[counter][counter+1];
        for (int i = 0; i < adjMatr.length; i++) {
            Arrays.fill(adjMatr[i], 99999);
        }

        counter =0;
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[i].nodeList.length; j++) {
                    String s =""+(i)+'.'+(j+1); // nodes named from 1
                    counter++;
                    nameToMatrIndex.put(s, counter);
            }
        }
        //
        for (int i = 1; i < newMap.length; i++) {
            for (int j = 0; j < newMap[i].nodeList.length; j++) {
                for (int k = 0; k < newMap[i].nodeList[j].connectionList.size(); k++) {
                    String thisNodeName =""+i+'.'+(j+1); // nodes named from 1
                    String toNodeName =""+(i-1)+'.'+(newMap[i].nodeList[j].connectionList.get(k).prevNodeIndex);
                    int connectionCost = newMap[i].nodeList[j].connectionList.get(k).cost;
                    adjMatr[nameToMatrIndex.get(toNodeName)][nameToMatrIndex.get(thisNodeName)] = connectionCost;
                }
            }
        }

//        System.out.println(nameToMatrIndex);
//        System.out.println(Arrays.deepToString(adjMatr));
//        ChemAlert2.printIntMatrix(adjMatr);
        return adjMatr;
    }


    private static void convert(Layer[] nodeMap){
        // converts nodeMap, so connections  stored in nodes of previous layer
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


    private static void checkConnection(Layer[] nodeMap, int layerIndex, int prevNodesCost, Solution s, int nodeIndex, int connectionIndex){
        int snap = s.curPath;
        s.curPath +=nodeMap[layerIndex].nodeList[nodeIndex].connectionList.get(connectionIndex).cost;
//        System.out.println("\t"+s.curPath+"  "+nodeIndex);
        findSolution1(nodeMap,layerIndex-1,prevNodesCost,s,nodeMap[layerIndex].nodeList[nodeIndex].connectionList.get(connectionIndex).prevNodeIndex-1 );
        s.curPath = snap;

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
    int minPath = Integer.MAX_VALUE;
    int curPath = 0;
    ArrayList<Integer> solList =new ArrayList<>();
    ArrayList<Integer> pathList =new ArrayList<>();

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
