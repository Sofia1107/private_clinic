package com.app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Appointment {
    private Integer id;
    private Integer clientId;
    private Integer doctorId;
    private Timestamp dateTime;
    private String summary;
    private String doctorFirstName;
    private String doctorLastname;
    private String clientFirstName;
    private String clientLastname;


    @JsonCreator
    public Appointment(@JsonProperty("id") Integer id,
                       @JsonProperty("clientId") Integer clientId,
                       @JsonProperty("doctorId") Integer doctorId,
                       @JsonProperty("dateTime") Timestamp dateTime,
                       @JsonProperty("summary") String summary) {
        this.id = id;
        this.clientId = clientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.summary = summary;
    }

    public Appointment(Integer id, Integer clientId, Integer doctorId, Timestamp dateTime, String summary, String doctorFirstName, String doctorLastname, String clientFirstName, String clientLastname) {
        this.id = id;
        this.clientId = clientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.summary = summary;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastname = doctorLastname;
        this.clientFirstName = clientFirstName;
        this.clientLastname = clientLastname;
    }

    public static AppointmentBuilder builder() {
        return new AppointmentBuilder();
    }

    public static class AppointmentBuilder {
        private Integer id;
        private Integer clientId;
        private Integer doctorId;
        private Timestamp dateTime;
        private String summary;
        private String doctorFirstName;
        private String doctorLastname;
        private String clientFirstName;
        private String clientLastname;

        public Appointment build() {
            return new Appointment(id, clientId, doctorId, dateTime, summary, doctorFirstName, doctorLastname, clientFirstName, clientLastname);
        }

        public AppointmentBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public AppointmentBuilder setClientId(Integer clientId) {
            this.clientId = clientId;
            return this;
        }

        public AppointmentBuilder setDoctorId(Integer doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public AppointmentBuilder setDateTime(Timestamp dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public AppointmentBuilder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public AppointmentBuilder setDoctorFirstName(String doctorFirstName) {
            this.doctorFirstName = doctorFirstName;
            return this;
        }

        public AppointmentBuilder setDoctorLastname(String doctorLastname) {
            this.doctorLastname = doctorLastname;
            return this;
        }

        public AppointmentBuilder setClientFirstName(String clientFirstName) {
            this.clientFirstName = clientFirstName;
            return this;
        }

        public AppointmentBuilder setClientLastname(String clientLastname) {
            this.clientLastname = clientLastname;
            return this;
        }
    }
}
