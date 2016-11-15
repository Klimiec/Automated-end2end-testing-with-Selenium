package common.configuration;


import common.users.UserCredentials;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Credentials {

    private static Map<String, String> credentials;

    public Credentials() {
        String filePath = credentialsFilePath();

        Yaml yaml = new Yaml();
        try {
            credentials =  (Map) yaml.load(new FileInputStream(new File(filePath)));
        } catch (FileNotFoundException e) {
            credentials = Collections.emptyMap();
            //throw new RuntimeException("Couldn't find credentials file for a given environment" ,e);
        }
    }

    public List<UserCredentials> registeredUsers() {

        return Arrays.asList(
                new UserCredentials(
                        credentials.get("ecommerce.user.person1.email"),
                        credentials.get("ecommerce.user.person1.password"),
                        credentials.get("ecommerce.user.person1.firstname"),
                        credentials.get("ecommerce.user.person1.lastname")),
                new UserCredentials(
                        credentials.get("ecommerce.user.person2.email"),
                        credentials.get("ecommerce.user.person2.password"),
                        credentials.get("ecommerce.user.person2.firstname"),
                        credentials.get("ecommerce.user.person2.lastname")),
                new UserCredentials(
                        credentials.get("ecommerce.user.person3.email"),
                        credentials.get("ecommerce.user.person3.password"),
                        credentials.get("ecommerce.user.person3.firstname"),
                        credentials.get("ecommerce.user.person3.lastname"))
        );
    }

    public UserCredentials registeredUser() {
        return new UserCredentials(
                credentials.get("ecommerce.user.person1.email"),
                credentials.get("ecommerce.user.person1.password"),
                credentials.get("ecommerce.user.person1.firstname"),
                credentials.get("ecommerce.user.person1.lastname"));
    }

    public String registeredEmail() {
        return credentials.get("registeredEmail");
    }

    public String adminUserName() {
        return credentials.get("ecommerce.user.backoffice.login");
    }

    public String adminPassword() {
        return credentials.get("ecommerce.user.backoffice.password");
    }

    public String authenticationLogin() {
        return credentials.get("ecommerce.authentication.login");
    }

    public String authenticationPassword() {
        return credentials.get("ecommerce.authentication.password");
    }

    private String credentialsFilePath() {
        String CREDENTIALS_FILE_NAME_FORMAT = "credentials_%s_%s.yml";
        final String fileName = String.format(CREDENTIALS_FILE_NAME_FORMAT, Configuration.country(), Configuration.environment().toString().toLowerCase());
        return Configuration.credentialsDirectoryPath() + fileName;
    }
}
