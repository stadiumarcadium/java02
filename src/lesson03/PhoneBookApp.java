import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class PhoneBookApp {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBookImpl();
        phoneBook.add("samename", "123456789");
        phoneBook.add("samename", "123456788");
        phoneBook.add("somename1", "123456779");
        phoneBook.add("somename2", "123456769");
        phoneBook.add("somename3", "123456759");
        HashSet<String> numbers = phoneBook.get("somename3");
        System.out.println(numbers);
    }
}



