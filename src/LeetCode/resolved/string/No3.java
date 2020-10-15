package LeetCode.resolved.string;

public class No3 {

    public boolean isDistinct(String s){
        for(int i=0;i<s.length()-1;i++){
            for(int j=i+1;j<s.length();j++){
                if(s.charAt(i) == s.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

    public int lengthOfLongestSubstring(String s) {

        if(s.equals("")){
            return 0;
        }
        int size = s.length();
        int[] lengthTable = new int[size];
        int[] locationTable = new int[size];
        lengthTable[0] = 1;
        locationTable[0] = 0;
        for(int i=1;i<size;i++){
            if(isDistinct(s.substring(i-lengthTable[i-1],i+1))){
                lengthTable[i] = lengthTable[i-1] + 1;
                locationTable[i] = i-lengthTable[i-1];
            }else{
                lengthTable[i] = lengthTable[i-1];
                locationTable[i] = locationTable[i-1];
            }
        }
        return lengthTable[size-1];

//        if(s.equals("")){
//            return 0;
//        }
//        int size = s.length();
//        for(int length=size;length>1;length--){
//            for(int i=0;i+length-1<size;i++){
//                String substr = s.substring(i,i+length);
//                if(isDistinct(substr)){
//                    return length;
//                }
//            }
//        }
//        return 1;
    }

    public static void main(String[] args) {

//        No3 obj = new No3();
//        String s = "pwwkew";
//        System.out.println(obj.lengthOfLongestSubstring(s));
//        System.out.println(obj.isDistinct(s));
//        System.out.println(s.charAt(s.length()-1));

        String s = "abc";
        System.out.println(s.charAt(2));

    }

}
