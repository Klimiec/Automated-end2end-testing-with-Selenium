package common.users;


import framework.sharedkernel.annotations.ValueObject;

@ValueObject
public class BackOfficeUser {

    private String firstName;
    private String lastName;
    private String email;
    private String registrationStatus;
    private String registrationType;
    private String userType;

    public BackOfficeUser(BackOfficeUserBuilder backOfficeUserBuilder) {
        this.firstName = backOfficeUserBuilder.firstName;
        this.lastName = backOfficeUserBuilder.lastName;
        this.email = backOfficeUserBuilder.email;
        this.registrationStatus = backOfficeUserBuilder.registrationStatus;
        this.registrationType = backOfficeUserBuilder.registrationType;
        this.userType = backOfficeUserBuilder.userType;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String email() {
        return email;
    }

    public String registrationType() {
        return registrationType;
    }

    public String registrationStatus() {
        return registrationStatus;
    }

    public String userType() {
        return userType;
    }

    public static class BackOfficeUserBuilder {

        private String firstName;
        private String lastName;
        private String email;
        private String registrationStatus;
        private String registrationType;
        private String userType;

        public BackOfficeUserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public BackOfficeUserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public BackOfficeUserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public BackOfficeUserBuilder withRegistrationStatus(String registrationStatus) {
            this.registrationStatus = registrationStatus;
            return this;
        }

        public BackOfficeUserBuilder withRegistrationType(String registrationType) {
            this.registrationType = registrationType;
            return this;
        }

        public BackOfficeUserBuilder withUserType(String userType) {
            this.userType = userType;
            return this;
        }

        public BackOfficeUser build() {
            return new BackOfficeUser(this);
        }
    }
}



