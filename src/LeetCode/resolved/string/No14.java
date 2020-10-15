package LeetCode.resolved.string;

public class No14 {

    public String longestCommonPrefix(String[] strs) {
        int length = strs.length;
        if(length <= 0 || strs == null){
            return "";
        }
        if(length == 1){
            return strs[0];
        }
        String res = "";
        int position = 0;
        char c;
        while(true){
            if(position >= strs[0].length()){
                return res;
            }
            c = strs[0].charAt(position);
            for(int i=1;i<length;i++){
                if(position >= strs[i].length() || strs[i].charAt(position) != c){
                    return res;
                }
            }
            res += c;
            position++;
        }
    }

    //二分查找法
    public String longestCommonPrefixBinarySearch(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strs = new String[3];
        strs[0] = "flower";
        strs[1] = "flow";
        strs[2] = "flight";
        No14 obj = new No14();
        System.out.println(obj.longestCommonPrefix(strs));
    }


}
