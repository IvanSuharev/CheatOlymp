import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var t = Graph.readListGraph();
        System.out.println(Arrays.deepToString(t));
        var tmp = Graph.translateToTableOri(t);
//        Graph.dijkstra(0, tmp);
        System.out.println(Arrays.deepToString(tmp));
        System.out.println(Arrays.toString(Graph.dijkstra(3, tmp)));
    }
}
//        5 5
//        1 5
//        1 4
//        4 3
//        5 4
//        3 2
//5 5
//        1 5 1
//        1 4 3
//        5 4 1
//        4 3 4
//        3 2 7