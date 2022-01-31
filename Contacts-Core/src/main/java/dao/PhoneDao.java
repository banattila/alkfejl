package dao;

import models.Contact;
import models.Phone;

import java.util.List;

public interface PhoneDao {

    List<Phone> findByContactId(Contact contact);
    List<Phone> findByContactId(int contactId);
    Phone save(Phone phone, int contactId);
    void delete(Phone phone);
}
