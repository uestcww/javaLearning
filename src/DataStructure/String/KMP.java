package DataStructure.String;

public class KMP {

    int[] next;

    public void getNext(String str, int[] next){
        next[0] = -1;
        int i = 0, j = -1;
        while (i < str.length()){
            if (j == -1 || str.charAt(i) == str.charAt(j)){
                ++i;
                ++j;
                next[i] = j;
            }else{
                j = next[j];
            }
        }
    }

    public int func(String t, String p){
        int i = 0;
        int j = 0;
        while (i < t.length() && j < p.length()){
            if (j == -1 || t.charAt(i) == p.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        if(j == p.length()){
            return i - j;
        }else{
            return -1;
        }
    }
}
