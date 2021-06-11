package CommonUtils;

public class ActualStorageOfNumber {

    // 输出的就是数字的补码
    public static void ActualStorageOfInteger(int num){
        StringBuilder result = new StringBuilder();
        int count = 0;
        while(num != 0){
            result.append(num&1);
            num = num >>> 1;
            ++count;
            if(count%4==0&&count!=32){
                result.append(" ");
            }
        }
        System.out.println(result.reverse().toString());
    }

    public static void main(String[] args) {
        ActualStorageOfNumber.ActualStorageOfInteger(-1);
    }

}
