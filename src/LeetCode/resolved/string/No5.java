package LeetCode.resolved.string;

public class No5 {

    //简单版本，可以用动态规划，因为满足最优子结构
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int left,right;
        for(int length = s.length();length>=2;length--){
            for(int start=0;start<=s.length()-length;start++){
                left = start;
                right = start+length-1;
                while (left<right){
                    if(s.charAt(left) != s.charAt(right)){
                        break;
                    }
                    left++;
                    right--;
                }
                if (left >= right){
                    return s.substring(start,start+length);
                }
            }
        }
        return s.substring(0,1);
    }

    public static void main(String[] args) {
        No5 obj = new No5();
        String str = "bb";
        System.out.println(obj.longestPalindrome(str));
    }

}
