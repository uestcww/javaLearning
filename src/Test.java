import java.util.*;

public class Test {

    public static void main(String[] args) {
        String str = "+";
        switch(str){
            case "+":
                System.out.println("加号");
                break;
            case "-":
                System.out.println("减号");
                break;
            case "*":
                System.out.println("乘号");
                break;
            case "/":
                System.out.println("除号");
                break;
            default:
                System.out.println("其他");
                break;
        }
    }

}
