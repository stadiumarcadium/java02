import java.util.HashSet;

interface PhoneBook {
    public HashSet<String> get(String name);
    public void add(String name, String phone);

}
