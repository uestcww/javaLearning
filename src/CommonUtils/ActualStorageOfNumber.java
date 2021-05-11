package CommonUtils;

public class ActualStorageOfNumber {

    public void ActualStorageOfInteger(int num){
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

}
