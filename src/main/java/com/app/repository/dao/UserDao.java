package com.app.repository.dao;

import com.app.entity.Person;
import com.app.repository.DatabaseAccess;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao extends DatabaseAccess {
    private static final String SELECT_ADMIN_BY_EMAIL_SQL = "select * from users where email = ? and user_role = 'admin'";
    private static final String SELECT_CLIENTS_SQL = "select * from users where user_role = 'client' ORDER BY last_name";
    private static final String SELECT_USER_BY_EMAIL_SQL = "select * from users where email = ?";
    private static final String SELECT_CLIENT_BY_EMAIL_SQL = "select * from users where email = ? and user_role = 'client'";
    private static final String SELECT_CLIENT_BY_PHONE_NUMBER_SQL = "select * from users where phone_number = ? and user_role = 'client'";
    private static final String INSERT_CLIENT_SQL = "INSERT INTO users (first_name, last_name, email, phone_number, pwd, date_of_birth, blood_group, RH, allergy, user_role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CLIENT_BY_ID_SQL = "DELETE FROM users WHERE id = ? and user_role = 'client'";
    private static final String UPDATE_CLIENT_SQL = "UPDATE users SET first_name=?, last_name=?, email=?, phone_number=?, date_of_birth=?, blood_group=?, RH=?, allergy=?, user_role=? WHERE id=?";


    public Person getAdministratorByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ADMIN_BY_EMAIL_SQL)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Person admin = Person.builder()
                            .setId(rs.getInt("id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setEmail(rs.getString("email"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setPassword(rs.getString("pwd"))
                            .setUserRole(rs.getString("user_role"))
                            .build();
                    return admin;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Person getUserByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Person person = Person.builder()
                            .setId(rs.getInt("id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setEmail(rs.getString("email"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setPassword(rs.getString("pwd"))
                            .setDateOfBirth(rs.getDate("date_of_birth"))
                            .setBloodGroup(rs.getInt("blood_group"))
                            .setRH(rs.getString("RH"))
                            .setAllergy(rs.getString("allergy"))
                            .setUserRole(rs.getString("user_role"))
                            .build();
                    return person;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Person getClientByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_CLIENT_BY_EMAIL_SQL)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Person person = Person.builder()
                            .setId(rs.getInt("id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setEmail(rs.getString("email"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setPassword(rs.getString("pwd"))
                            .setDateOfBirth(rs.getDate("date_of_birth"))
                            .setBloodGroup(rs.getInt("blood_group"))
                            .setRH(rs.getString("RH"))
                            .setAllergy(rs.getString("allergy"))
                            .setUserRole(rs.getString("user_role"))
                            .build();
                    return person;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Person getClientByPhoneNumber(String phoneNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_CLIENT_BY_PHONE_NUMBER_SQL)) {
            ps.setString(1, phoneNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Person person = Person.builder()
                            .setId(rs.getInt("id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setEmail(rs.getString("email"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setPassword(rs.getString("pwd"))
                            .setDateOfBirth(rs.getDate("date_of_birth"))
                            .setBloodGroup(rs.getInt("blood_group"))
                            .setRH(rs.getString("RH"))
                            .setAllergy(rs.getString("allergy"))
                            .setUserRole(rs.getString("user_role"))
                            .build();
                    return person;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int saveClient(Person client) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhoneNumber());
            ps.setString(5, client.getPassword());
            ps.setDate(6, client.getDateOfBirth());
            ps.setInt(7, client.getBloodGroup());
            ps.setString(8, client.getRH()); // RH value
            ps.setString(9, client.getAllergy());
            ps.setString(10, "client");

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteClientById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_BY_ID_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateClient(Person client) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_CLIENT_SQL)) {
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhoneNumber());
            ps.setDate(5, client.getDateOfBirth());
            ps.setInt(6, client.getBloodGroup());
            ps.setString(7, client.getRH()); // RH value
            ps.setString(8, client.getAllergy());
            ps.setString(9, "client");

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> getClients() {
        List<Person> clients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_CLIENTS_SQL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Person person = Person.builder()
                            .setId(rs.getInt("id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setEmail(rs.getString("email"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setDateOfBirth(rs.getDate("date_of_birth"))
                            .setBloodGroup(rs.getInt("blood_group"))
                            .setRH(rs.getString("RH"))
                            .setAllergy(rs.getString("allergy"))
                            .setUserRole(rs.getString("user_role"))
                            .build();
                    clients.add(person);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }
}
