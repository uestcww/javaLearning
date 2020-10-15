package LeetCode.resolved;

public class No13 {

    public int romanToInt(String s){
        int result = 0, point = s.length()-1;
        char el;
        while(point>=0){
            el = s.charAt(point);
            switch (el) {
                case 'I':
                    result++;
                    break;
                case 'V':
                    result += 5;
                    if((point>0) && (s.charAt(point-1) == 'I')){
                        result--;
                        point--;
                    }
                    break;
                case 'X':
                    result += 10;
                    if((point>0) && (s.charAt(point-1) == 'I')){
                        result--;
                        point--;
                    }
                    break;
                case 'L':
                    result += 50;
                    if((point>0) && (s.charAt(point-1) == 'X')){
                        result-=10;
                        point--;
                    }
                    break;
                case 'C':
                    result += 100;
                    if((point>0) && (s.charAt(point-1) == 'X')){
                        result-=10;
                        point--;
                    }
                    break;
                case 'D':
                    result += 500;
                    if((point>0) && (s.charAt(point-1) == 'C')){
                        result-=100;
                        point--;
                    }
                    break;
                case 'M':
                    result += 1000;
                    if((point>0) && (s.charAt(point-1) == 'C')){
                        result-=100;
                        point--;
                    }
                    break;
            }
            point--;
        }
        return result;
    }

    public static void main(String[] args) {

        No13 obj = new No13();

        String test = "helloworld";

        System.out.println(test.charAt(test.length()-1));

        System.out.println(obj.romanToInt("MCMXCIV"));

    }

}
