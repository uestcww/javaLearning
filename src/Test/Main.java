package Test;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private static int n;
    private static int m;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        int[][] maze = new int[n][m];
        int[][] id = new int[n][m];
        String str = in.nextLine();
        char[] charArr;
        for(int i=0;i<n;++i){
            str = in.nextLine();
            charArr = str.replaceAll("\\s*", "").toCharArray();
            for(int j=0;j<m;++j){
                id[i][j] = i*m + j;
                if(charArr[j] == '.'){
                    maze[i][j] = 1;
                }else{
                    maze[i][j] = 0;
                }
            }
        }
        int self;
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                self = maze[i][j];
                // 和右边比较
                if(j+1 < m && maze[i][j+1] == self){
                    union(i, j, i, j+1, id);
                }
                // 和下边比较
                if(i+1 < n && maze[i+1][j] == self){
                    union(i, j, i+1, j, id);
                }

            }
        }
        int start = find(0, 0, id);
        int end = find(n-1, m-1, id);
        HashSet<Integer> startSet = new HashSet<>();
        HashSet<Integer> endSet = new HashSet<>();
        int current;
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                current = find(i, j, id);
                if(current == start){
                    startSet.add(i*m+j);
                }else if(current == end){
                    endSet.add(i*m+j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int startRow, startColumn, endRow, endColumn;
        int currentValue;
        for(int startNode : startSet){
            for(int endnode : endSet){
                startRow = startNode/m;
                startColumn = startNode%m;
                endRow = endnode/m;
                endColumn = endnode%m;
                currentValue = Math.abs(startRow - endRow - 1) + Math.abs(startColumn - endColumn - 1);
                if(currentValue < min){
                    min = currentValue;
                }
            }
        }
        System.out.println(min);
    }

    public static int find(int row, int column, int[][] id){
        if((row * m + column) != id[row][column]){
            id[row][column] = find(row, column, id);
        }
        return id[row][column];
    }

    public static void union(int pRow, int pColumn, int qRow, int qColumn, int[][] id){
        int boss = find(pRow, pColumn, id);
        id[boss/m][boss%m] = find(qRow, qColumn, id);
    }

}
