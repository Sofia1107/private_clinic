package com.app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String userRole;
    private Date dateOfBirth;
    private Integer bloodGroup;
    private String RH;
    private String allergy;


    @JsonCreator
    public Person(@JsonProperty("id") Integer id,
                  @JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("phoneNumber") String phoneNumber,
                  @JsonProperty("password") String password,
                  @JsonProperty("email") String email,
                  @JsonProperty("dateOfBirth") Date dateOfBirth,
                  @JsonProperty("bloodGroup") Integer bloodGroup,
                  @JsonProperty("RH") String RH,
                  @JsonProperty("allergy") String allergy,
                  @JsonProperty("userRole") String userRole) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.RH = RH;
        this.allergy = allergy;
        this.userRole = userRole;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {
        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String password;
        private String userRole;
        private Date dateOfBirth;
        private Integer bloodGroup;
        private String RH;
        private String allergy;


        public Person build() {
            return new Person(id, firstName, lastName, phoneNumber, password, email, dateOfBirth, bloodGroup, RH, allergy, userRole);
        }

        public PersonBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public PersonBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public PersonBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PersonBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public PersonBuilder setUserRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        public PersonBuilder setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonBuilder setBloodGroup(Integer bloodGroup) {
            this.bloodGroup = bloodGroup;
            return this;
        }

        public PersonBuilder setRH(String RH) {
            this.RH = RH;
            return this;
        }

        public PersonBuilder setAllergy(String allergy) {
            this.allergy = allergy;
            return this;
        }
    }
}
