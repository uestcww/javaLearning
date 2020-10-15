package LeetCode.unsolved;

public class No10 {

    public boolean isMatch(String s, String p) {
        if(s.equals("")){
            if(p.equals(".*") || p.equals("")){
                return true;
            }else {
                return false;
            }
        }
        if(p.equals("")){
            return false;
        }
        int sPointer=0,pPointer=0;
        int sLength = s.length();
        int pLength = p.length();
        char sChar,pChar,temp;
        boolean flag = false;
        while(sPointer<sLength && pPointer<pLength){
            sChar = s.charAt(sPointer);
            pChar = p.charAt(pPointer);
            if(sChar != pChar){
                if(pChar != '.' && pChar != '*'){
                    if(pPointer < pLength-1 && p.charAt(pPointer+1) == '*'){
                        pPointer+=2;
                        continue;
                    }
                    return false;
                }
                if(pChar == '*'){
                    if(pPointer==0||sPointer==0){
                        return false;
                    }
                    if(pPointer < pLength-1 && p.charAt(pPointer-1) == p.charAt(pPointer+1)){
                        flag = true;
                    }
                    temp = p.charAt(pPointer-1);
                    while(sPointer < sLength && s.charAt(sPointer) == temp){
                        sPointer++;
                    }
                    pPointer++;
                    if(flag){
                        pPointer++;
                    }
                    continue;
                }
                if(pPointer + 1 < pLength && p.charAt(pPointer + 1) == '*'){
                    if(pPointer+1 == pLength-1){
                        return true;
                    }
                    pChar = p.charAt(pPointer+2);
                    while(sPointer < sLength && s.charAt(sPointer) != pChar){
                        sPointer++;
                    }
                    if(sPointer >= sLength){
                        return false;
                    }
                }
            }
            sPointer++;
            pPointer++;
        }
        if(sPointer == sLength && pPointer == pLength){
            return true;
        }
        if(sPointer < sLength && pPointer == pLength){
            return false;
        }
        if(sPointer == sLength && pPointer < pLength){
            return false;
        }
        System.out.println("while循环结束");
        System.out.println("sPointer="+sPointer+" sLength="+sLength);
        System.out.println("pPointer="+pPointer+" pLength="+pLength);
        return true;
    }

    public static void main(String[] args) {

        No10 obj = new No10();
        String s = "aaa";
        String p = "ab*a*c*a";
        System.out.println("s="+s);
        System.out.println("p="+p);
        System.out.println("out="+obj.isMatch(s,p));


    }
}
