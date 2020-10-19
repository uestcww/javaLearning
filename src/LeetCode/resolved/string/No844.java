package LeetCode.resolved.string;

public class No844 {

    //逆序遍历字符串，删除退格符和应当删除的字符，然后比较两个字符串
    //优化：可以写一个函数，专门进行遍历和删除，这样就可以简化重复的代码
    public boolean backspaceCompare(String S, String T) {
        if(S.length() < 1 && T.length() < 1){
            return true;
        }
        StringBuilder sBuilder = new StringBuilder(S);
        StringBuilder tBuilder = new StringBuilder(T);
        int sPointer = sBuilder.length()-1, tPointer = tBuilder.length()-1;
        int sBack = 0, tBack = 0;
        //对字符串S进行删除
        while(sPointer >= 0){
            if(sBuilder.charAt(sPointer) == '#'){
                //删除退格符本身，并且记录删除字符数量加一
                sBuilder.deleteCharAt(sPointer);
                sBack++;
            }else if(sBack > 0){
                //删除当前有效字符，记录值减一
                sBuilder.deleteCharAt(sPointer);
                sBack--;
            }
            sPointer--;
        }
        //对字符串T进行删除
        while(tPointer >= 0){
            if(tBuilder.charAt(tPointer) == '#'){
                tBuilder.deleteCharAt(tPointer);
                tBack++;
            }else if(tBack > 0){
                tBuilder.deleteCharAt(tPointer);
                tBack--;
            }
            tPointer--;
        }
        return sBuilder.toString().equals(tBuilder.toString());
    }

    public static void main(String[] args) {
        No844 obj = new No844();
        String s1 = "ab#c";
        String t1 = "ad#c";
        String s2 = "ab##";
        String t2 = "c#d#";
        String s3 = "a##c";
        String t3 = "#a#c";
        String s4 = "a#c";
        String t4 = "b";
        System.out.println("true ? " + obj.backspaceCompare(s1, t1));
        System.out.println("true ? " + obj.backspaceCompare(s2, t2));
        System.out.println("true ? " + obj.backspaceCompare(s3, t3));
        System.out.println("false ? " + obj.backspaceCompare(s4, t4));
    }

}
