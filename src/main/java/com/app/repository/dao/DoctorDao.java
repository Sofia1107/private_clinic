package com.app.repository.dao;

import com.app.entity.Doctor;
import com.app.repository.DatabaseAccess;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDao extends DatabaseAccess {
    private static final String INSERT_DOCTOR_SQL = "INSERT INTO doctors (first_name, last_name, email, phone_number, specialization) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_DOCTOR_BY_ID_SQL = "DELETE FROM doctors WHERE id = ?";
    private static final String UPDATE_DOCTOR_SQL = "UPDATE doctors SET first_name = ?, last_name = ?, email = ?, phone_number = ?, specialization = ? WHERE id = ?";
    private static final String SELECT_DOCTORS_SQL = "SELECT * FROM doctors ORDER BY last_name";
    private static final String SELECT_DOCTOR_BY_EMAIL_SQL = "SELECT * FROM doctors WHERE email = ?";
    private static final String SELECT_DOCTOR_BY_PHONE_NUMBER_SQL = "SELECT * FROM doctors WHERE phone_number = ?";

    public int saveDoctor(Doctor doctor) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_DOCTOR_SQL)) {
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getEmail());
            ps.setString(4, doctor.getPhoneNumber());
            ps.setString(5, doctor.getSpecialization());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteDoctorById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_DOCTOR_BY_ID_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateDoctor(Doctor doctor) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_DOCTOR_SQL)) {
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getEmail());
            ps.setString(4, doctor.getPhoneNumber());
            ps.setString(5, doctor.getSpecialization());
            ps.setInt(6, doctor.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_DOCTORS_SQL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Doctor doctor = Doctor.builder()
                            .setId(rs.getInt("id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setEmail(rs.getString("email"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setSpecialization(rs.getString("specialization"))
                            .build();
                    doctors.add(doctor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctors;
    }

    public Doctor getDoctorByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_BY_EMAIL_SQL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Doctor doctor = Doctor.builder()
                            .setId(rs.getInt("id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setEmail(rs.getString("email"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setSpecialization(rs.getString("specialization"))
                            .build();
                    return doctor;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Doctor getDoctorByPhoneNumber(String phoneNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_BY_PHONE_NUMBER_SQL)) {
            ps.setString(1, phoneNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Doctor doctor = Doctor.builder()
                            .setId(rs.getInt("id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setEmail(rs.getString("email"))
                            .setPhoneNumber(rs.getString("phone_number"))
                            .setSpecialization(rs.getString("specialization"))
                            .build();
                    return doctor;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
