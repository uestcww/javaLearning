package LeetCode.solving;

public class No69 {

    public int mySqrt(int x) {
        if(x == 1){
            return 1;
        }
        return find(0, x, x);
    }

    public int find(int left, int right, int x){
        if((left * left) <= x && ((left+1) * (left+1) > x)){
            return left;
        }
        int middle = (left + right) / 2;
        if((middle * middle) > x){
            return find(left, middle, x);
        }else{
            return find(middle, right, x);
        }
    }

    public static void main(String[] args) {
        No69 obj = new No69();
        System.out.println("2 ? " + obj.mySqrt(4));
        System.out.println("2 ? " + obj.mySqrt(8));
    }
}
