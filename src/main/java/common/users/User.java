package common.users;


import framework.sharedkernel.annotations.ValueObject;

@ValueObject
public final class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirmation;
    private String phone;
    private String taxCode;
    private String companyName;
    private String vatNumber;

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public String passwordConfirmation() {
        return passwordConfirmation;
    }

    public String phone() {
        return phone;
    }

    public String taxCode() {
        return taxCode;
    }

    public String companyName() {
        return companyName;
    }

    public String vatNumber() {
        return vatNumber;
    }

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.passwordConfirmation = builder.passwordConfirmation;
        this.phone = builder.phone;
        this.taxCode = builder.taxCode;
        this.companyName = builder.companyName;
        this.vatNumber = builder.vatNumber;
    }

    public static class UserBuilder {

        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String passwordConfirmation;
        private String phone;
        private String taxCode;
        private String companyName;
        private String vatNumber;

        public UserBuilder(User user) {
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.email = user.email;
            this.password = user.password;
            this.passwordConfirmation = user.passwordConfirmation;
            this.phone = user.phone;
            this.taxCode = user.taxCode;
            this.companyName = user.companyName;
            this.vatNumber = user.vatNumber;
        }

        public UserBuilder() {
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withPasswordConfirmation(String passwordConfirmation) {
            this.passwordConfirmation = passwordConfirmation;
            return this;
        }

        public UserBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder withTaxCode(String taxCode) {
            this.taxCode = taxCode;
            return this;
        }

        public UserBuilder withCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public UserBuilder withVatNumber(String vatNumber) {
            this.vatNumber = vatNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
