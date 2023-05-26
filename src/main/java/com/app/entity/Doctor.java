package com.app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Doctor {
    private Integer id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phoneNumber;

    @JsonCreator
    public Doctor(@JsonProperty("id") Integer id,
                  @JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("phoneNumber") String phoneNumber,
                  @JsonProperty("specialization") String specialization,
                  @JsonProperty("email") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.email = email;
    }

    public static DoctorBuilder builder() {
        return new DoctorBuilder();
    }

    public static class DoctorBuilder {
        private Integer id;
        private String firstName;
        private String lastName;
        private String specialization;
        private String email;
        private String phoneNumber;

        public Doctor build() {
            return new Doctor(id, firstName, lastName, phoneNumber, specialization, email);
        }

        public DoctorBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public DoctorBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public DoctorBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public DoctorBuilder setSpecialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public DoctorBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public DoctorBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
    }
}
