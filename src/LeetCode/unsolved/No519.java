package LeetCode.unsolved;

import java.util.*;

public class No519 {

    //不能这样遍历，因为效率太低
//    static class Solution {
//        private short[] matrix;
//        private final int row;
//        private final int col;
//        private int count;
//        public Solution(int n_rows, int n_cols) {
//            this.matrix = new short[n_rows];
//            this.count = n_rows * n_cols;
//            this.row = n_rows;
//            this.col = n_cols;
//        }
//        public int[] flip() {
//            if(this.count <= 0){
//                return null;
//            }
//            int num = (int) (Math.random() * this.count);
//            for(int i=0;i<this.matrix.length;++i){
//                int j=0;
//                while(j < this.col){
//                    if((this.matrix[i]>>>j & 1) != 0){
//                        ++j;
//                    }else{
//                        if(num == 0){
//                            --this.count;
//                            this.matrix[i] = (short) (this.matrix[i] | 1<<j);
//                            return new int[]{i,j};
//                        }else{
//                            --num;
//                        }
//                    }
//                }
//            }
//            return null;
//        }
//        public void reset() {
//            Arrays.fill(this.matrix, (short) 0);
//            this.count = this.row * this.col;
//        }
//    }

    static class SolutionLeetCode1 {

        Map<Integer, Integer> V = new HashMap<>();
        int nr, nc, rem;
        Random rand = new Random();

        public SolutionLeetCode1(int n_rows, int n_cols) {
            nr = n_rows;
            nc = n_cols;
            rem = nr * nc;
        }

        public int[] flip() {
            int r = rand.nextInt(rem--);
            int x = V.getOrDefault(r, r);
            V.put(r, V.getOrDefault(rem, rem));
            return new int[]{x / nc, x % nc};
        }

        public void reset() {
            V.clear();
            rem = nr * nc;
        }
    }

    static class SolutionLeetCode2 {

        int nr, nc, rem, b_size;
        List<Set<Integer>> buckets = new ArrayList<>();
        Random rand = new Random();

        public SolutionLeetCode2(int n_rows, int n_cols) {
            nr = n_rows;
            nc = n_cols;
            rem = nr * nc;
            b_size = (int) Math.sqrt(nr * nc);
            for (int i = 0; i < nr * nc; i+= b_size)
                buckets.add(new HashSet<Integer>());
        }

        public int[] flip() {
            int c = 0;
            int c0 = 0;
            int k = rand.nextInt(rem);
            for (Set<Integer> b1 : buckets) {
                if (c0 + b_size - b1.size() > k) {
                    while (true) {
                        if (!b1.contains(c)) {
                            if (c0 == k) {
                                b1.add(c);
                                rem--;
                                return new int[]{c / nc, c % nc};
                            }
                            c0++;
                        }
                        c++;
                    }
                }
                c += b_size;
                c0 += b_size - b1.size();
            }
            return null;
        }

        public void reset() {
            for (Set<Integer> b1 : buckets)
                b1.clear();
            rem = nr * nc;
        }
    }

    public static void main(String[] args) {
        SolutionLeetCode1 obj1 = new SolutionLeetCode1(2, 3);
        for(int x1 : obj1.flip()){
            System.out.print(x1 + ",");
        }
        System.out.println();
        for(int x2 : obj1.flip()){
            System.out.print(x2 + ",");
        }
        System.out.println();
        for(int x3 : obj1.flip()){
            System.out.print(x3 + ",");
        }
        System.out.println();
        for(int x4 : obj1.flip()){
            System.out.print(x4 + ",");
        }
        System.out.println();
        System.out.println("***********************");
        SolutionLeetCode1 obj2 = new SolutionLeetCode1(1, 2);
        for(int x1 : obj2.flip()){
            System.out.print(x1 + ",");
        }
        System.out.println();
        for(int x2 : obj2.flip()){
            System.out.print(x2 + ",");
        }
        System.out.println();
        obj2.reset();
        for(int x3 : obj2.flip()){
            System.out.print(x3 + ",");
        }
    }

}