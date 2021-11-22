import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBook {

  Map<String, String> phoneBook = new HashMap<>();

  public boolean checkName(String name) {
    return name.matches("^[А-я][а-я]+$");
  }

  public boolean checkPhone(String phone) {
    return phone.matches("^[7][\\d]{10}$");
  }

  public void addContact(String phone, String name) {
    if (checkPhone(phone) && checkName(name)) {
      phoneBook.put(phone, name);
      System.out.println("Контакт сохранен");
    } else {
      System.out.println("Неверный формат ввода");
    }
  }

  public String getNameByPhone(String phone) {
    return phoneBook.containsKey(phone) ? phoneBook.get(phone) + " - " + phone : "";
  }

  public Set<String> getPhonesByName(String name) {
    Set<String> foundContact = new HashSet<>();

    for (Map.Entry<String, String> contact : phoneBook.entrySet()
    ) {
      if (contact.getValue().equals(name)) {
        foundContact.add(name + " - " + contact.getKey());
      }
    }
    return foundContact;
  }

  public Set<String> getAllContacts() {
    Set<String> contacts = new TreeSet<>();

    for (Map.Entry<String, String> contact : phoneBook.entrySet()
    ) {
      contacts.add(contact.getValue() + " - " + contact.getKey());
    }
    return contacts;
  }

}