import java.util.*;

public class Test {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("hello");
        set.add("world");
        set.add("why");
        set.add("for the king");
        set.add("for the lich king");
        long before = System.currentTimeMillis();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        long after = System.currentTimeMillis();
        System.out.println(after-before);
    }

}
