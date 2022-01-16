import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Arr {

    public static void main(String[] args) {
        ArrayList<String> arraylist = new ArrayList<>();
        arraylist.add("стол");
        arraylist.add("стол");
        arraylist.add("стул");
        arraylist.add("стул");
        arraylist.add("стул");
        arraylist.add("шкаф");
        arraylist.add("кресло");
        arraylist.add("кресло");
        arraylist.add("тумба");
        arraylist.add("диван");


        System.out.println("Список:");
        arraylist.forEach(System.out::println);

        System.out.println("\nБез повторов: ");
        HashSet<String> unique = new HashSet<>(arraylist);
        unique.forEach(System.out::println);

        System.out.println("\nКолличество:");
        HashMap<String, Integer> amount = new HashMap<>();
        for (String s : arraylist) {
            amount.put(s, amount.getOrDefault(s, 0) + 1);
        }
        amount.entrySet().forEach(System.out::println);
    }


}
