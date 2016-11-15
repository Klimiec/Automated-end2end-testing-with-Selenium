package common.users;



public class UserFactory {

    public static User createPrivatePerson() {

        return new User.UserBuilder()
                .withFirstName("John")
                .withLastName("Doe")
                .withPhone("065555555")
                .withTaxCode("MLLSNT82P65Z404U")
                .withEmail("test+" + System.currentTimeMillis() + "@gmail.com")
                .withPassword("test1234")
                .withPasswordConfirmation("test1234")
                .build();
    }


    public static User createCompany() {
        return new User.UserBuilder()
                .withFirstName("John")
                .withLastName("Doe")
                .withPhone("065555555")
                .withTaxCode("MLLSNT82P65Z404U")
                .withCompanyName("Unknown Spa")
                .withVatNumber("12345678901")
                .withEmail("test+" + System.currentTimeMillis() + "@gmail.com")
                .withPassword("test1234")
                .withPasswordConfirmation("test1234")
                .build();
    }
}


