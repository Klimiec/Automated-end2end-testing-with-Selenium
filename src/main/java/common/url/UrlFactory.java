package common.url;


import common.configuration.Configuration;
import common.configuration.Credentials;
import common.configuration.Environment;
import org.apache.commons.lang3.StringUtils;

public class UrlFactory {

    final Environment environment = Configuration.environment();
    final Credentials credentials = Configuration.credentials();

    public String urlForEcommerceHomePage() {

        switch (environment) {
            case DEV:
                return String.format(environment.homePageUrlFormat(), Configuration.ecommerceName(), Configuration.country().toLowerCase(), platformLanguage());
            case INTEGRATION:
                return String.format(environment.homePageUrlFormat(), Configuration.country().toLowerCase(), "icrif", platformLanguage());
            case TEST:
                return String.format(environment.homePageUrlFormat(), credentials.authenticationLogin(), credentials.authenticationPassword(), Configuration.ecommerceName(), domainExtension(Configuration.country()), platformLanguage());
            case PROD:
                return String.format(environment.homePageUrlFormat(), Configuration.ecommerceName(), domainExtension(Configuration.country()), platformLanguage());
        }
        return null;
    }

    public String urlForCmsAdminPage() {

        switch (environment) {
            case DEV:
            case INTEGRATION:
            case PROD:
                return environment.openCmsAdminUrl();
            case TEST:
            return String.format(environment.openCmsAdminUrl(), credentials.authenticationLogin(), credentials.authenticationPassword());
        }
        return null;
    }

    private Object domainExtension(String country) {
        if(country.equalsIgnoreCase("it")) {
            return "com";
        } else {
            return country;
        }
    }

    private String platformLanguage() {
        if (!StringUtils.isEmpty(Configuration.language())) {
            return "/" + Configuration.language() + "/";
        } else {
            return "";
        }
    }

}
