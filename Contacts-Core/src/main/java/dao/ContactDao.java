package dao;

import models.Contact;

import java.util.List;

public interface ContactDao {

    List<Contact> findAll();
    Contact save(Contact contact);
    void delete(Contact contact);
    void delete(int contactId);
    void update(Contact contact);
}
