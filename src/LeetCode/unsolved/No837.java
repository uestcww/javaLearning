package LeetCode.unsolved;

public class No837 {

    public double new21Game(int N, int K, int W){
        if(K>N||K==0){
            return 1.00000;
        }
        double result=0.0, positivePosibility;
        if(W>=K){
            if(W<=N){
                if(K == 1){
                    return 1.0000;
                }
                positivePosibility = 1-((double)K-1)/(double)W;
            }else{
                if(K == 1){
                    return (double)N/(double)W;
                }
                positivePosibility = ((double)N-(double)K+1)/(double)W;
            }
            for(int i=1;i<K;i++){
                result += new21Game(N-i, K-i, W) / (double)W;
            }
            result += positivePosibility;
            return result;
        }else{
            for(int i=1;i<=W;i++){
                result += new21Game(N-i, K-i, W) / (double)W;
            }
            return result;
        }
    }

    public static void main(String[] args) {

        No837 obj = new No837();

//        System.out.println("N=6,K=1,W=10---预期：0.6---实际："+obj.new21Game(6, 1 ,10));
//        System.out.println("N=1,K=0,W=1---预期：1.0---实际："+obj.new21Game(1, 0 ,1));
//        System.out.println("N=1,K=0,W=2---预期：1.0---实际："+obj.new21Game(1, 0 ,2));
//        System.out.println("N=1,K=1,W=2---预期：0.5---实际："+obj.new21Game(1, 1 ,2));

        
    }

}
