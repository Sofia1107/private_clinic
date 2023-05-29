package com.app.repository.dao;

import com.app.entity.Appointment;
import com.app.repository.DatabaseAccess;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentDao extends DatabaseAccess {

    private static final String INSERT_APPOINTMENT_SQL = "INSERT INTO appointments (client_id, doctor_id, date_time, summary) VALUES (?, ?, ?, ?)";
    private static final String DELETE_APPOINTMENT_BY_ID_SQL = "DELETE FROM appointments WHERE id = ?";
    private static final String DELETE_APPOINTMENT_BY_CLIENT_ID_SQL = "DELETE FROM appointments WHERE client_id = ?";
    private static final String DELETE_APPOINTMENT_BY_DOCTOR_ID_SQL = "DELETE FROM appointments WHERE doctor_id = ?";
    private static final String UPDATE_APPOINTMENT_SQL = "UPDATE appointments SET date_time = ?, summary = ? WHERE id = ?";
    private static final String SELECT_APPOINTMENTS_BY_CLIENT_ID_SQL = "SELECT ap.id, ap.client_id, ap.doctor_id, ap.date_time, ap.summary, usr.first_name, usr.last_name, docs.first_name, docs.last_name " +
            "FROM appointments ap " +
            "INNER JOIN users usr on usr.id = ap.client_id " +
            "INNER JOIN doctors docs on docs.id = ap.doctor_id " +
            "WHERE client_id = ? " +
            "ORDER BY date_time";

    private static final String SELECT_APPOINTMENTS_SQL = "SELECT ap.id, ap.client_id, ap.doctor_id, ap.date_time, ap.summary, usr.first_name, usr.last_name, docs.first_name, docs.last_name " +
            "FROM appointments ap " +
            "INNER JOIN users usr on usr.id = ap.client_id " +
            "INNER JOIN doctors docs on docs.id = ap.doctor_id " +
            "WHERE usr.user_role = 'client' " +
            "ORDER BY usr.last_name";

    public int saveAppointment(Appointment appointment) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_APPOINTMENT_SQL)) {
            ps.setInt(1, appointment.getClientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setTimestamp(3, appointment.getDateTime());
            ps.setString(4, appointment.getSummary());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteAppointmentById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_APPOINTMENT_BY_ID_SQL)) {
            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteAppointmentByClientId(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_APPOINTMENT_BY_CLIENT_ID_SQL)) {
            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteAppointmentByDoctorId(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_APPOINTMENT_BY_DOCTOR_ID_SQL)) {
            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateAppointment(Appointment appointment) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_APPOINTMENT_SQL)) {
            ps.setTimestamp(1, appointment.getDateTime());
            ps.setString(2, appointment.getSummary());
            ps.setInt(3, appointment.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Appointment> getAppointmentsByClientId(int id) {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_APPOINTMENTS_BY_CLIENT_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = Appointment.builder()
                            .setId(rs.getInt(1))
                            .setClientId(rs.getInt(2))
                            .setDoctorId(rs.getInt(3))
                            .setDateTime(rs.getTimestamp(4))
                            .setSummary(rs.getString(5))
                            .setClientFirstName(rs.getString(6))
                            .setClientLastname(rs.getString(7))
                            .setDoctorFirstName(rs.getString(8))
                            .setDoctorLastname(rs.getString(9))
                            .build();
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointments;
    }

    public List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_APPOINTMENTS_SQL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = Appointment.builder()
                            .setId(rs.getInt(1))
                            .setClientId(rs.getInt(2))
                            .setDoctorId(rs.getInt(3))
                            .setDateTime(rs.getTimestamp(4))
                            .setSummary(rs.getString(5))
                            .setClientFirstName(rs.getString(6))
                            .setClientLastname(rs.getString(7))
                            .setDoctorFirstName(rs.getString(8))
                            .setDoctorLastname(rs.getString(9))
                            .build();
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointments;
    }


}
