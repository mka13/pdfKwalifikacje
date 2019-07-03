import java.util.HashSet;
import java.util.Set;

public class testy8 {
    public static void main(String[] args) {
        Set<String>strings=new HashSet();
        strings.add("test1");
        strings.add("test2");
        strings.add("test3");
        strings.add("test4");
        strings.add("test5");
        strings.add("test6");
        for (String x:strings
             ) {
            System.out.println(x);
        }

        System.out.println("-------------------");

        for (String x:strings
             ) {
            System.out.println(x);
        }

    }

}
