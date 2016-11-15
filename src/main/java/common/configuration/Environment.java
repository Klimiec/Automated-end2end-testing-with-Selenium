package common.configuration;


public enum Environment {
    DEV("https://devvm.%s.%s%s", "https://devvm.admin.icrif.ch/system/login/index.html"),
    INTEGRATION("https://%s.%s.dev.sys%s", "https://admin.icrif.dev.sys/system/login/index.html"),
    TEST("https://%s:%s@test.%s.%s%s", "https://%s:%s@test.admin.icrif.ch/system/login/index.html"),
    PROD("https://www.%s.%s%s", "https://admin.icrif.ch/system/login/index.html");

    private final String homePageUrlFormat;
    private final String openCmsAdminUrl;

    Environment(String homePageUrlFormat, String openCmsAdminUrl) {
        this.homePageUrlFormat = homePageUrlFormat;
        this.openCmsAdminUrl = openCmsAdminUrl;
    }

    public String homePageUrlFormat() {
        return homePageUrlFormat;
    }

    public String openCmsAdminUrl() {
        return openCmsAdminUrl;
    }

    public static Environment lookUp(String environment) {
        for (Environment env : Environment.values()) {
            if (env.toString().equalsIgnoreCase(environment)){
               return env;
            }
        }
        throw new RuntimeException("Environment: " + environment + " doesn't exist, check config file");
    }
}
