package ru.academits.dao;

import ru.academits.model.Contact;
import ru.academits.service.ContactValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Anna on 17.06.2017.
 */
public class ContactDao {
    private List<Contact> contactList = new ArrayList<>();
    private AtomicInteger idSequence = new AtomicInteger(0);
    private Contact lastContact = new Contact();
    private ContactValidation lastContactValidation = new ContactValidation();

    public ContactDao() {
        Contact contact = new Contact();
        contact.setId(getNewId());
        contact.setFirstName("Иван");
        contact.setLastName("Иванов");
        contact.setPhone("9123456789");
        contactList.add(contact);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    public List<Contact> getAllContacts() {
        return contactList;
    }

    public void add(Contact contact) {
        contact.setId(getNewId());
        contactList.add(contact);
    }

    public void saveLastContact(Contact contact) {
        this.lastContact = contact;
    }

    public Contact getLastContact() {
        return lastContact;
    }

    public void saveLastContactValidation(ContactValidation contactValidation) {
        this.lastContactValidation = contactValidation;
    }

    public ContactValidation getLastContactValidation() {
        return lastContactValidation;
    }

    public void deleteContact(int contactId) {
        Contact contactForRemoving = contactList.stream().filter(contact -> contact.getId() == contactId).findFirst().get();
        contactList.remove(contactForRemoving);
    }

    public List<Contact> searchContact(String queryString) {
        return contactList.stream()
                .filter(contact -> contact.getFirstName().toUpperCase().contains(queryString)
                        || contact.getLastName().toUpperCase().contains(queryString)
                        || contact.getPhone().toUpperCase().contains(queryString))
                .collect(Collectors.toList());
    }
}
