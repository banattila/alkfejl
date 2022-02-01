package dao;

import configs.ContactConfigs;
import models.Phone;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoImpl implements PhoneDao {

    private final String CONNECTION_URL;
    private final String FIND_BY_CONTACT_ID = "SELECT id, number, phone_type FROM phone WHERE contact_id = ?";
    private final String SAVE_PHONE = "INSERT INTO phone (number, phone_type, contact_id) VALUES (?, ?, ?)";
    private final String UPDATE_PHONE = "UPDATE phone SET number = ?, phone_type = ?, contact_id = ? WHERE id = ?";
    private final String DELETE_BY_PHONE_ID = "DELETE FROM phone WHERE id = ?";
    private final String DELETE_BY_CONTACT_ID = "DELETE FROM phone WHERE contact_id = ?";


    public PhoneDaoImpl(){
        this.CONNECTION_URL = ContactConfigs.getValue("db-url");
    }

    @Override
    public List<Phone> findByContactId(int contactId) {
        List<Phone> result = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
            PreparedStatement stmt = connection.prepareStatement(FIND_BY_CONTACT_ID)) {
            stmt.setInt(1, contactId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Phone phone = new Phone();
                phone.setContactId(contactId);
                phone.setId(rs.getInt("id"));
                phone.setNumber(rs.getString("number"));
                int pt = rs.getInt("phone_type");
                switch (pt){
                    case 0: phone.setPhoneType(Phone.PhoneType.HOME); break;
                    case 1: phone.setPhoneType(Phone.PhoneType.MOBILE); break;
                    case 2: phone.setPhoneType(Phone.PhoneType.WORK); break;
                    case 3: phone.setPhoneType(Phone.PhoneType.UNKNOW); break;
                }
                result.add(phone);
            }

        } catch (SQLException e){
            ContactConfigs.getLogger().error(e.getMessage());
        }

        return result;
    }

    private void setPhoneType(Phone phone, PreparedStatement stmt) throws SQLException{
        switch (phone.getPhoneType()){
            case HOME: stmt.setInt(2, 0); break;
            case MOBILE: stmt.setInt(2, 1); break;
            case WORK: stmt.setInt(2, 2); break;
            case UNKNOW: stmt.setInt(2, 3); break;
        }
    }

    @Override
    public Phone save(Phone phone, int contactId) {
        try(Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
        PreparedStatement stmt = connection.prepareStatement(SAVE_PHONE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, phone.getNumber());
            setPhoneType(phone, stmt);
            stmt.setInt(3, contactId);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                int generated = rs.getInt("id");
                if (generated <= 0){
                    phone.setId(generated);
                    ContactConfigs.getLogger().info(phone.getNumber() + "added to database with" + generated + " id's.");
                    return phone;
                }
            }

        } catch (SQLException e){
            ContactConfigs.getLogger().error(e.getMessage());
        }

        return null;
    }

    @Override
    public Phone update(Phone phone){
        try(Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
        PreparedStatement stmt = connection.prepareStatement(UPDATE_PHONE)) {
            stmt.setString(1, phone.getNumber());
            setPhoneType(phone, stmt);
            stmt.setInt(3, phone.getContactId());
        } catch (SQLException e){
            ContactConfigs.getLogger().error(e.getMessage());
        }
        return phone;
    }

    @Override
    public void delete(Phone phone) {
        try(Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
        PreparedStatement stmt = connection.prepareStatement(DELETE_BY_PHONE_ID)) {
            stmt.setInt(1, phone.getId());
            stmt.executeQuery();
            ContactConfigs.getLogger().warn("Phone deleted from database with" + phone.getContactId() + " id.");

        } catch (SQLException e){
            ContactConfigs.getLogger().error(e.getMessage());
        }
    }

    @Override
    public void delete(int contactId) {
        try(Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
        PreparedStatement stmt = connection.prepareStatement(DELETE_BY_CONTACT_ID)) {
            stmt.setInt(1, contactId);
            stmt.executeQuery();
            ContactConfigs.getLogger().warn("Phone(s) deleted from database with " + contactId + " contact id.");
        } catch (SQLException e){
            ContactConfigs.getLogger().error(e.getMessage());
        }
    }
}
