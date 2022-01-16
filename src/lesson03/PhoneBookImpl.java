import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class PhoneBookImpl implements PhoneBook {
    //    private ArrayList<Abonent> abonents = new ArrayList<>(); для будущего расширения функционала
    private HashMap<String, HashSet<String>> namesPhoneBook = new HashMap<>();

    @Override
    public HashSet<String> get(String name) {
        return namesPhoneBook.get(name);
    }

    @Override
    public void add(String name, String phone) {
        Abonent abonent = new Abonent(name, phone);
//        abonents.add(abonent);
        if (namesPhoneBook.containsKey(name)) {
            HashSet<String> phoneNumbers = namesPhoneBook.get(name);
            phoneNumbers.add(phone);
        } else {
            HashSet<String> newNumber = new HashSet<>();
            newNumber.add(phone);
            namesPhoneBook.put(name, newNumber);
        }
    }
}

