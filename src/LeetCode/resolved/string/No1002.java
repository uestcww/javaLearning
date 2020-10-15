package LeetCode.resolved.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No1002 {

    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<String>();
        if(A.length < 1){
            return res;
        }
        int[] minFreq = new int[26];
        int[] freq = new int[26];
        for(char c:A[0].toCharArray()){
            minFreq[c-97]++;
        }
        for(int i=1;i<A.length;i++){
            for(char c:A[i].toCharArray()){
                freq[c-97]++;
            }
            for(int j=0;j<minFreq.length;j++){
                minFreq[j] = Math.min(minFreq[j], freq[j]);
            }
            Arrays.fill(freq, 0);
        }
        for(int c=0;c<minFreq.length;c++){
            if(minFreq[c] > 0){
                for(int k=0;k<minFreq[c];k++){
                    res.add((char)(c+97) + "");
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        No1002 obj = new No1002();
        String[] strs = {"cool","lock","cook"};
        System.out.println(obj.commonChars(strs));





    }

}
