package eci.arsw.covidanalyzer.model;

import java.util.UUID;

public class Persona {
        private UUID id;
        private String FirstName;
        private String LastName;
        private String city;
        private String gender;
        private String email;
         private String birthString;
        private String testString;
        private boolean result;
        private double testSpecifity;

        public Persona(UUID id, String FirstName, String LastName, String city, String gender, String email, String birthString, String testString, boolean result, double testSpecifity){
            this.id=id;
            this.FirstName=FirstName;
            this.LastName=LastName;
            this.city=city;
            this.gender=gender;
            this.email=email;
            this.birthString=birthString;
            this.testString=testString;
            this.result=result;
            this.testSpecifity=testSpecifity;
        }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthString() {
        return birthString;
    }

    public String getTestString() {
        return testString;
    }

    public boolean isResult() {
        return result;
    }

    public double getTestSpecifity() {
        return testSpecifity;
    }



}


