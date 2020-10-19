import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        char[][] board = new char[5][5];
        for(char[] charArr : board){
            Arrays.fill(charArr, '.');
        }
        System.out.println(board[2][2] == '.');
    }

}
