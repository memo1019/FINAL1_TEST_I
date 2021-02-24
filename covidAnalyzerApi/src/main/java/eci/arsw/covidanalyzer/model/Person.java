package eci.arsw.covidanalyzer.model;

public class Person {
        private String id;
        private String FirstName;
        private String LastName;
        private String city;
        public Person(String id, String FirstName,String LastName, String city ){
            this.id=id;
            this.FirstName=FirstName;
            this.LastName=LastName;
            this.city=city;
        }

        public String getId() {
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
}


