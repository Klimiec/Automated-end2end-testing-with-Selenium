package common.users;


import framework.sharedkernel.annotations.ValueObject;

@ValueObject
public class UserCredentials {

    private String password;
    private String email;
    private String name;
    private String surname;

    public UserCredentials(String email, String password, String name, String surname) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String password() {
        return password;
    }

    public String email() {
        return email;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
