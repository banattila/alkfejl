package dao;

import models.Contact;

import java.util.List;

public interface ContactDao {

    List<Contact> findAll();
    Contact save(Contact contact);
    void delete(Contact contact);
}
