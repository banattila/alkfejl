package models;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class Contact {
    /**
     * Név (Kötelező)
     * Email (Kötelező)
     * Telefonszám (Opcionális) - lehet több is és meg lehet adni a típusát (work, home)
     * Lakcím (Opcionális)
     * Születésnap (Kötelező)
     * Szervezet (Opcionális)
     * Pozíció/Beosztás (Opcionális)
    */

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty email = new SimpleStringProperty(this, "email");
    private ObjectProperty<ObservableList<Phone>> phones = new SimpleObjectProperty<>(this, "phones");
    private StringProperty address = new SimpleStringProperty(this, "address");
    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>(this, "birthday");
    private StringProperty company = new SimpleStringProperty(this, "company");
    private StringProperty position = new SimpleStringProperty(this, "position");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public ObservableList<Phone> getPhones() {
        return phones.get();
    }

    public ObjectProperty<ObservableList<Phone>> phonesProperty() {
        return phones;
    }

    public void setPhones(ObservableList<Phone> phones) {
        this.phones.set(phones);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getCompany() {
        return company.get();
    }

    public StringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }
}
