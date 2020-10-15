package LeetCode.resolved.string;

public class No9 {

    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int[] arr = new int[10];
        int left=9;
        while(x>0){
            arr[left] = x%10;
            x/=10;
            left--;
        }
        int right=9;
        left++;
        while(left<right){
            if(arr[left]!=arr[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if(x<0){
            return false;
        }
        if(x<10){
            return true;
        }
        int i = 0, temp = x;
        while(temp>0){
            temp/=10;
            i++;
        }
        int j=0;
        while(++j<i-1){
            temp = (int)Math.pow(10,i-j);
            if(x%Math.pow(10,j)!=(x-x%temp)/temp){
                return false;
            }
            x%=temp;
        }
        return true;
    }

    public static void main(String[] args) {

        No9 object = new No9();

        System.out.println(object.isPalindrome2(1001));

    }

}
