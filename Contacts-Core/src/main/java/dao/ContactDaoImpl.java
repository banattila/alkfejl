package dao;

import configs.ContactConfigs;
import models.Contact;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    private final String CONNECTION_URL;
    private final String FIND_ALL = "SELECT * FROM CONTACT";
    private final String SAVE_CONTACT = "INSERT INTO CONTACT (name, email, address, dateOfBirth, company, position) VALUES (?, ?, ?, ?, ?, ?)";
    private final String DELETE_CONTACT = "DELETE FROM CONTACT WHERE id = ?";
    private final String UPDATE_CONTACT = "UPDATE CONTACT SET name = ?, email = ?, address = ?, dateOfBirth = ?, company = ?, position = ? WHERE id = ?";

    public ContactDaoImpl() {
        CONNECTION_URL = ContactConfigs.getValue("db-url");
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = connection.prepareStatement(FIND_ALL);
        ) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Contact c = new Contact();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setCompany(rs.getString("company"));
                c.setPosition(rs.getString("position"));
                Date date = Date.valueOf(rs.getString("dateOfBirth"));
                c.setDateOfBirth(date == null ? LocalDate.now() : date.toLocalDate());

                result.add(c);
            }
        } catch (SQLException e) {
            ContactConfigs.getLogger().error(e.getMessage());
        }

        return result;
    }


    private void setPSTMT(Contact contact, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, contact.getName());
        stmt.setString(2, contact.getEmail());
        stmt.setString(3, contact.getAddress());
        stmt.setString(4, contact.getDateOfBirth().toString());
        stmt.setString(5, contact.getCompany());
        stmt.setString(6, contact.getPosition());
    }

    @Override
    public Contact save(Contact contact) {

        try (Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
             PreparedStatement stmt = connection.prepareStatement(SAVE_CONTACT, Statement.RETURN_GENERATED_KEYS)
        ) {
            setPSTMT(contact, stmt);
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                int generated = rs.getInt("id");
                if (generated >= 0){
                    ContactConfigs.getLogger().info(contact.getName() + " saved into database.");
                    contact.setId(generated);
                    return contact;
                }
            }


        } catch (SQLException e) {
            ContactConfigs.getLogger().error(e.getMessage());
        }
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        try (Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
             PreparedStatement stmt = connection.prepareStatement(DELETE_CONTACT)
        ) {
            PhoneDao phoneDao = new PhoneDaoImpl();
            phoneDao.delete(contact.getId());
            stmt.setInt(1, contact.getId());
            stmt.executeQuery();
            ContactConfigs.getLogger().warn(contact.getName() + " deleted from database.");
        } catch (SQLException e) {
            ContactConfigs.getLogger().error(e.getMessage());
        }
    }

    @Override
    public void delete(int contactId) {
        try (Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
             PreparedStatement stmt = connection.prepareStatement(DELETE_CONTACT)) {
            stmt.setInt(1, contactId);
            stmt.executeQuery();
            ContactConfigs.getLogger().warn("Contact with " + contactId + "deleted from database.");
        } catch (SQLException e) {
            ContactConfigs.getLogger().error(e.getMessage());
        }
    }

    @Override
    public void update(Contact contact) {
        try (Connection connection = DriverManager.getConnection(this.CONNECTION_URL);
             PreparedStatement stmt = connection.prepareStatement(UPDATE_CONTACT)) {
            setPSTMT(contact, stmt);
            stmt.setInt(7, contact.getId());
            ContactConfigs.getLogger().info(contact.getName() + "updated successful.");
        } catch (SQLException e) {
            ContactConfigs.getLogger().error(e.getMessage());
        }
    }
}
