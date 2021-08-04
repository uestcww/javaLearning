package Test;

public class Father {

    public String str;
    public Integer num;


    public Father(String a, Integer b){
        this.str = a;
        this.num = b;
    }

    @Override
    public String toString() {
        return "Father{" +
                "str='" + str + '\'' +
                ", num=" + num +
                '}';
    }
}
