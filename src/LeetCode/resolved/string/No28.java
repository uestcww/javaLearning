package LeetCode.resolved.string;

public class No28 {

    // 我这KMP算法怎么效果这么差
    public int strStr(String haystack, String needle) {
        // 老规矩，判断
        if(needle.length() < 1){
            return 0;
        }
        // 先转为字符数组，后面好操作，还有就是长度
        char[] str = haystack.toCharArray();
        char[] target = needle.toCharArray();
        int sLength = str.length, tLength = target.length;
        // 准备构造next数组，明白KMP就知道next数组是干啥的
        int[] next = new int[tLength];
        next[0] = -1;
        int left = -1, right = 0;
        // 这个循环就是为了构造next数组的
        while(right < tLength-1){
            if(left == -1 || target[right] == target[left]){
                ++right;
                ++left;
                if(target[right] != target[left]){
                    next[right] = left;
                }else{
                    next[right] = next[left];
                }
            }else{
                left = next[left];
            }
        }
        // 构造好了，该匹配了，其实就是KMP，没啥好说的
        left = 0;
        right = 0;
        while(left < sLength && right < tLength){
            if(right == -1 || str[left] == target[right]){
                ++left;
                ++right;
            }else if(str[left] != target[right]){
                right = next[right];
            }
        }
        if(right == tLength){
            return left - right;
        }else{
            return -1;
        }
    }

    public int strStrLeetCode(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L == 0) return 0;
        int pn = 0;
        while (pn < n - L + 1) {
            while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)) ++pn;
            int currLen = 0, pL = 0;
            while (pL < L && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++currLen;
            }
            if (currLen == L) return pn - L;
            pn = pn - currLen + 1;
        }
        return -1;
    }

    public int charToInt(int idx, String s) {
        return (int)s.charAt(idx) - (int)'a';
    }

    // 这个用哈希值的方法有点秀，已经不是人能够想出来的了
    public int strStrLeetCode2(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L > n) return -1;

        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 31);

        // compute the hash of strings haystack[:L], needle[:L]
        long h = 0, ref_h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + charToInt(i, haystack)) % modulus;
            ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
        }
        if (h == ref_h) return 0;

        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - charToInt(start - 1, haystack) * aL
                    + charToInt(start + L - 1, haystack)) % modulus;
            if (h == ref_h) return start;
        }
        return -1;
    }

    public static void main(String[] args) {
        No28 obj = new No28();
        System.out.println("2 ? " + obj.strStr("hello", "ll"));
        System.out.println("-1 ? " + obj.strStr("aaaaa", "bba"));
    }

}
