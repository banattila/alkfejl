package dao;

import models.Contact;
import models.Phone;

import java.util.List;

public interface PhoneDao {

    List<Phone> findByContactId(int contactId);
    Phone save(Phone phone, int contactId);
    void delete(Phone phone);
    void delete(int contactId);
    Phone update(Phone phone);
}
