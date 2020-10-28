package LeetCode.unsolved;

public class No925 {

    public boolean isLongPressedName(String name, String typed) {
        if(name.equals(typed)){
            return true;
        }
        char[] nameArr = name.toCharArray();
        char[] typedArr = typed.toCharArray();
        int nameLength = nameArr.length, typedLength = typedArr.length;
        int count, namePointer = 0, typedPointer = 0;
        char nameChar;
        while(namePointer < nameLength){
            nameChar = nameArr[namePointer];
            count = 1;
            while(namePointer+1 < nameLength && nameArr[namePointer+1] == nameChar){
                count++;
                namePointer++;
            }
            for(int j=0;j<count;j++){
                if(typedArr[typedPointer] != nameChar){
                    return false;
                }
                typedPointer++;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        No925 obj = new No925();
        System.out.println("true ? " + obj.isLongPressedName("alex", "aaleex"));
//        System.out.println("false ? " + obj.isLongPressedName("saeed", "ssaaedd"));
//        System.out.println("true ? " + obj.isLongPressedName("leelee", "lleeelee"));
//        System.out.println("true ? " + obj.isLongPressedName("laiden", "laiden"));


    }

}
