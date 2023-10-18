import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//https://acm.timus.ru/problem.aspx?space=1&num=1210


public class Ivanushka {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.deepToString(readFromFile("INPUT.TXT")));
    }
    private static Node[] readFromFile(String filename)throws IOException{
        Scanner sc = new Scanner(new File(filename));
        int layers = sc.nextInt();
        Node[] nodeMap = new Node[layers];
        for (int i = 0; i < layers; i++) {
            int nodesInLayer = sc.nextInt();
            Connection[] cList = new Connection[nodesInLayer];
            int counter =0;
            for (int j = 0; j < nodesInLayer; j++) {
                int prevNodeIndex = sc.nextInt();
                while (prevNodeIndex!=0) {
                    int nodeCost = sc.nextInt();
                    Connection c = new Connection(prevNodeIndex, nodeCost);
                    cList[counter] = c;
                    prevNodeIndex = sc.nextInt();
                }
            }
            Node n = new Node(cList);
            nodeMap[i]=n;
        }


        return nodeMap;
    }
/*
    private static List[] readFromFile(String filename) throws IOException {
        Scanner sc = new Scanner(new File(filename));
        int layers = sc.nextInt();
        List<List>[] nodeMap = new List[layers];
        for (int i = 0; i < layers; i++) {
            int nodesInLayer = sc.nextInt();
            for (int j = 0; j < nodesInLayer; j++) {
                List<int[]> ffgg = new ArrayList<>();
                int prevNodeIndex = sc.nextInt();
                while (prevNodeIndex!=0){
                    int transferCost = sc.nextInt();
                    ffgg.add(new int[]{prevNodeIndex,transferCost});
                    prevNodeIndex = sc.nextInt();
                }
                nodeMap[i].add(ffgg);
            }
        }
        return nodeMap;
    }*/
}

class Node{
    Connection[] connectionList;
    public Node(Connection[] connectionList) {
        this.connectionList = connectionList;
    }




}
class Connection{
    int nodeIndex;
    int cost;

    public Connection(int nodeIndex, int cost) {
        this.nodeIndex = nodeIndex;
        this.cost = cost;
    }
}
