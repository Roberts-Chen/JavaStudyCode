package HashSet;

import java.util.HashMap;
import java.util.HashSet;

public class AddNullValueTest {
    public static void main(String[] args) {
        String key = null;
        HashSet<String> set = new HashSet<>();
        set.add(key);
        if (set.contains(key)) {
            System.out.println("set is contains this key");
        }


        HashMap<String, String> map = new HashMap<>();
        map.put(key, key);
        if (map.containsKey(key)) {
            System.out.println("map is contains null key");
            String s = map.get(key);
            System.out.println(s);
        }
    }
}
