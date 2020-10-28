package LeetCode.resolved.string;

public class No10 {

    public boolean isMatch(String s, String p) {
        return build(s, p, s.length()-1, p.length()-1);
    }

    // debug时忽然想到可以用动态规划
    public boolean build(String s, String p, int sPointer, int pPointer){
        if(sPointer < 0 || pPointer < 0){
            // 当p里面空了，但是s未空，这种情况肯定是错误的
            // p空而且s空，这种当然是正确的
            if(pPointer < 0){
                // 此时根据 s 的情况来判断是否正确
                return sPointer < 0;
            }
            // 但是如果s空但p未空，此时还有一丝希望，那就是p中只剩*匹配了，可以都匹配0个，这样就是正确的
            // 但是要注意， * 前面一定要有东西啊，不能孤零零一个 * ，不过测试用例好像都是合法的，没有这种情况
            if(pPointer != 0 && p.charAt(pPointer) == '*'){
                return build(s, p, sPointer, pPointer-2);
            }else{
                return false;
            }
        }
        // 从后往前取p的一个字符，检查该字符
        char test = p.charAt(pPointer);
        // 如果是 . 说明要跳过一个字符
        if(test == '.'){
            return build(s, p, sPointer-1, pPointer-1);
        }
        // 如果是 * 那么就要继续检查前一个字符
        if(test == '*'){
            // * 不可能出现在第一个位置，这是错误的
            if(pPointer == 0){
                return false;
            }
            char test2 = p.charAt(pPointer-1);
            // .* 这种万能匹配，就要遍历所有可能才行
            // 不然不知道该如何匹配
            // 这多种可能里，只要有一种返回了 true 那就是 true
            // 只有都返回的 false 那才是 false
            if(test2 == '.'){
                for(int i=0;i<=sPointer+1;i++){
                    if(build(s, p, sPointer-i, pPointer-2)){
                        return true;
                    }
                }
            }else{
                // 这种情况是针对某一个字符的匹配
                // 前面有多少个这个字符，就是多少种+1（0个字符）种可能
                if(build(s, p, sPointer, pPointer-2)){
                    return true;
                }
                char test3 = p.charAt(pPointer-1);
                for(int i=0;i <= sPointer && s.charAt(sPointer-i) == test3;i++){
                    if(build(s, p, sPointer-i-1, pPointer-2)){
                        return true;
                    }
                }
            }
            return false;
        }
        // 没有正则表达式的干扰了，直接判断两个字符是否一致
        if(s.charAt(sPointer) == test){
            return build(s, p, sPointer-1, pPointer-1);
        }
        return false;
    }

    // 动态规划版本
    public boolean isMatchDP(String s, String p) {
        // 记录s与p的长度
        int sLength = s.length(), pLength = p.length();
        // 字符串为空的情况要单独处理一下
        if(sLength < 1 || pLength < 1){
            // p为空，那么正确与否就单独取决于s是否为空
            if(pLength < 1){
                return sLength < 1;
            }
            // s为空，p不空，只有p中全是 * 匹配的可能
            int pointer = pLength-1;
            while(pointer > 0){
                if(p.charAt(pointer) == '*'){
                    pointer-=2;
                }else{
                    break;
                }
            }
            return pointer == -1;
        }
        // * 号与它的前面的字符是一个整体，需要做变换
        // 将 .* 变为 # ，将 字符加* 变为大写字符
        p = p.replace(".*", "#");
        StringBuilder pb = new StringBuilder(p);
        int pointer = pb.length()-1;
        // 遍历p，找出所有需要替换的 字符加*
        while(pointer > 0){
            if(pb.charAt(pointer) == '*'){
                if(!Character.isLowerCase(pb.charAt(pointer-1))){
                    return false;
                }
                pb.setCharAt(pointer-1, Character.toUpperCase(pb.charAt(pointer-1)));
            }
            pointer--;
        }
        // 删除所有剩余的 *
        p = pb.toString().replace("*", "");
        // 由于前面的操作影响了p的长度，所以这里更新 pLength
        pLength = p.length();
        // 这里刻意扩大了result的容量是为了保存p空s不空和p不空s空时的数据，这会使填写其他表项时更加容易
        boolean[][] result = new boolean[sLength + 1][pLength + 1];
        // p与s都为空的时候，一定是正确的
        result[0][0] = true;
        // p为空，s不空，那肯定不对
        for(int i=1;i<=sLength;i++){
            result[i][0] = false;
        }
        int k = 1;
        // p不空，s为空，这时候就要讨论了，毕竟有 * 匹配
        while(k <= pLength){
            // 如果发现小写字符或者 . 匹配，说明肯定不行，直接跳出循环
            if(p.charAt(k-1) == '.' || Character.isLowerCase(p.charAt(k-1))){
                result[0][k] = false;
                k++;
                break;
            }
            // 不是小写字符和 . 那就是 * 匹配，那就行
            result[0][k] = true;
            k++;
        }
        // 除非都是 * 匹配，否则 j 后面的肯定也不行啊
        while(k <= pLength){
            result[0][k] = false;
            k++;
        }
        char test, c;
        // 遍历整个表格，进行填表
        for(int i=1;i<=sLength;i++){
            for(int j=1;j<=pLength;j++){
                // 获得s和p的当前字符
                test = p.charAt(j-1);
                c = s.charAt(i-1);
                // 如果为 . 或者就是小写字符，那么直接匹配
                // 相同或为 . 就同 result[i-1][j-1] 的结果
                if(test == '.' || Character.isLowerCase(test)){
                    result[i][j] = charIsMatch(s,p,i,j) ? result[i-1][j-1]:false;
                }else{
                    // 这是万能的 * 匹配，其实这里还可以再优化，但是优化以后可读性可理解性变差了
                    if(test == '#'){
                        // 由于表格具有传递性，所以可以这么写
                        // 当万能匹配不匹配任何字符时，是 result[i][j-1]
                        // 当万能匹配去匹配字符时，可以看作去掉s当前字符，对前面的字符继续进行万能匹配
                        // 毕竟有不匹配任何字符这种可能，且只要有一次正确就是正确的，所以这么传递是正确的
                        result[i][j] = result[i][j-1] || result[i-1][j];
                    }else{
                        // 特定匹配，匹配0个就和 result[i][j-1] 等同
                        test = Character.toLowerCase(test);
                        // 这里首先当成不匹配看待，就是当前字符不匹配 字母加*
                        result[i][j] = result[i][j-1];
                        // 如果可以匹配的话，继续跟上面逻辑一样的做法
                        if(test == c){
                            result[i][j] = result[i][j-1] || result[i-1][j];
                        }
                    }
                }
            }
        }
        // 我们需要的就是表格中的最后一项
        return result[sLength][pLength];
    }

    public boolean charIsMatch(String s, String p, int sPointer, int pPointer){
        if(sPointer == 0){
            return false;
        }
        if(p.charAt(pPointer-1) == '.'){
            return true;
        }
        return p.charAt(pPointer-1) == s.charAt(sPointer - 1);
    }

    //这时官方的动态规划解法，为啥我的时间空间消耗都很大
    public boolean isMatchLeetCode(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                }
                else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        No10 obj = new No10();
        String s1 = "aa";
        String p1 = "a";
        System.out.println("false ? " + obj.isMatchDP(s1, p1));
        String s2 = "aa";
        String p2 = "a*";
        System.out.println("true ? " + obj.isMatchDP(s2, p2));
        String s3 = "ab";
        String p3 = ".*";
        System.out.println("true ? " + obj.isMatchDP(s3, p3));
        String s4 = "aab";
        String p4 = "c*a*b*";
        System.out.println("true ? " + obj.isMatchDP(s4, p4));
        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        System.out.println("false ? " + obj.isMatchDP(s5, p5));
        String s6 = "aab";
        String p6 = "c*a**b";
        System.out.println("false ? " + obj.isMatchDP(s6, p6));
        String s7 = "aaabaaaababcbccbaab";
        String p7 = "c*c*.*c*a*..*c*";
        System.out.println("true ? " + obj.isMatchDP(s7, p7));
    }

}
