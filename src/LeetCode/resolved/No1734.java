package LeetCode.resolved;

/*
* 傻逼题目，只要读懂题干，就能做对，傻逼
* */
public class No1734 {

    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int sum = 1;
        for(int i=2;i<=n;++i){
            sum ^= i;
        }
        int firstRemove = encoded[1];
        for(int i=3;i<encoded.length;i+=2){
            firstRemove ^= encoded[i];
        }
        int[] result = new int[n];
        result[0] = firstRemove ^ sum;
        for(int i=1;i<n;++i){
            result[i] = result[i-1] ^ encoded[i-1];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] res = new No1734().decode(new int[]{6, 5, 4, 6});
        for(int a : res){
            System.out.print(a + " ");
        }


    }

}
