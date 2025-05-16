import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Graph {
    public static byte[][] readTableGraph() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            byte[][] table = new byte[n][n];
            for (int i = 0; i < n; i++) {
                String[] line = bufferedReader.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    table[i][j] = Byte.parseByte(line[j]);
                }
            }
            return table;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static int[][] readListGraph() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int[][] edges = new int[m + 1][3];
            edges[0] = new int[]{n, m};
            for (int i = 0; i < m; i++) {
                line = bufferedReader.readLine().split(" ");
//                System.out.println(Arrays.toString(line));
                if (line.length == 2) {
                    edges[i + 1] = new int[]{Integer.parseInt(line[0]) - 1, Integer.parseInt(line[1]) - 1};
                } else {
                    edges[i + 1] = new int[]{Integer.parseInt(line[0]) - 1, Integer.parseInt(line[1]) - 1, Integer.parseInt(line[2])};
                }
            }
            return edges;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static byte[][] translateToTableOri(int[][] lines) {
        byte[][] table = new byte[lines[0][0]][lines[0][0]];
        for (int i = 1; i < lines.length; i++) {
            table[lines[i][0]][lines[i][1]] = (byte) (lines[1].length == 2 ? 1 : lines[i][2]);
        }
        return table;
    }
    public static byte[][] translateToTablUnori(int[][] lines) {
        byte[][] table = new byte[lines[0][0]][lines[0][0]];
        if (lines[1].length == 2) {
            for (int i = 1; i < lines.length; i++) {
                table[lines[i][0]][lines[i][1]] = 1;
                table[lines[i][1]][lines[i][0]] = 1;
            }
        } else {
            for (int i = 1; i < lines.length; i++) {
                table[lines[i][0]][lines[i][1]] = (byte) lines[i][2];
                table[lines[i][1]][lines[i][0]] = (byte) lines[i][2];
            }
        }
        return table;
    }
    public static void bfs(byte[][] table, int n) {
        boolean[] visited = new boolean[table.length];
        Queue<Integer> queue = new LinkedList<>();
        visited[n] = true;
        queue.add(n);
        while (!queue.isEmpty()) {
            int s = queue.poll();
            System.out.print(s + " ");
            for (int i = 0; i < table.length; i++) {
                if (table[s][i] > 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
    public static void dfs(byte[][] table, int n) {
        boolean[] visited = new boolean[table.length];
        Stack<Integer> stack = new Stack<>();
        stack.add(n);
        while (!stack.isEmpty()) {
            int s = stack.pop();
            if (!visited[s]) {
                System.out.print(s + " ");
                visited[s] = true;
            }
            for (int i = table.length - 1; i >= 0; i--) {
                if (table[s][i] > 0 && !visited[i]) {
                    stack.add(i);
                }
            }
        }
    }
    public static int[] dijkstra(int n, byte[][] table) {
        int[] dist = new int[table.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                Comparator.comparingInt(node -> node[1])
        );
        queue.add(new int[]{n, 0});
        while (!queue.isEmpty()) {
            int[] currentNode = queue.poll();
            for (int i = 0; i < table.length; i++) {
                if (table[currentNode[0]][i] > 0) {
                    if (dist[i] > table[currentNode[0]][i] + dist[currentNode[0]]) {
                        queue.add(new int[]{i, table[currentNode[0]][i]});
                        dist[i] = table[currentNode[0]][i] + dist[currentNode[0]];
                    }
                }
            }
        }
        return dist;
    }

}
