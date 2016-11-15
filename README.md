
# Automated end2end testing with Selenium

## Overview
The main purpose of this project is to present how to write end-2-end acceptance tests for web applications. <br />

@Test code doesn't interact with tested website directly. <br />
Instead it makes use of 'framework' which purpose is to model tested application in the test code.
This approache makes tests less fragile to changes in the UI, increase their readability and lower cost of maintenance.
Readable, self-explanatory tests serves also as a great long-lived documentation of the project. 

Framework also hides away technical details of the sleneium framework which is used to interact with the browser. It also enables to write tests in more declarative way, focuses on the application behaviour rather then current architecture.    
Locking behaviour instead of implementation in the tests make them less likely to break because of the change.

[Here](https://prezi.com/xdhjffv5adwx/automated-end2end-testing/) is presentation outlining some of the concepts the project. <br />

> the only constant is change.  -Heraclitus, a Greek philosopher



## Architecture

Framework consists of three layers which absorb impact of changes of the tested application.<br />

<img src="doc/images/arch.png">

***Layers***
* [Technical Layer](#technical-layer)
* [Page Object](#page-object)
* [Action Object](#action-object)
* [Assert Object](#assert-object)
* [Test](#test)

### Technical Layer

Contains low level details of selenium framework such as finding elements on the web page, perform actions on them etc.
WebElements are located by CSS selectors. The likelihood of change in this layer is very high. 
For instance, some functionality has been moved from one place to another in the page. Hence, selectors should be as precise as possible. 


```java
    //#### login ###
    @FindBy(css = ".chiudi")
    private WebElement loginPopUp;
    @FindBy(css = ".dx > form > input:nth-child(1)")
    private WebElement loginEmail;
    @FindBy(css = ".dx > form > input:nth-child(2)")
    private WebElement loginPassword;
    @FindBy(css = ".dx > form > input.login")
    private WebElement loginSubmitButton;
```

```java
  loginPopUp.click();
  loginPassword.sendKeys(password);
```

```java
  loginPassword.sendKeys(password);
```



### Page Object

Page object is a class, which structure reflects structure of the HTML page again which you want to write a test. 
It provides coarse-grained, application-specific API and hides technical details from the lower layer. 

```java
  HomePage open()
  HomePage gotoLoginPopUp()
  HomePage fillPassword(String password)
  RegistrationPage gotoRegistrationPage()
  CompanySearchPage submitCompanySearchForCompanySearchPage()
  ...
```

Page object methods reflects also the flow of the application.
Invocation of a given functionality can result in different flow which is reflected by the methods returned value (page).

```java
    public HomePage clickLogInOnSuccess() {
        loginSubmitButton.click();
        return new HomePage(driver);
    }

    public LoginPage clickLogInOnFailure() {
        loginSubmitButton.click();
        return new LoginPage(driver);
    }
```

Page object API enables method chaining, providing fluent Interface.

```java 
homePage
  .gotoLoginPopUp()
  .fillEmail(userCredentials.email())
  .fillPassword(userCredentials.password())
  .clickLogInOnSuccess();
```


### Action Object

Action Object is a function which encapsulates frequently performed action in the system such as user login etc. 
Algorithm of a given action there is only in one place (DRY: Don't repeat yourself).

```java
@ActionObject
public class UserLogInAction implements BaseAction<HomePage, HomePage> {

    @Override
    public HomePage execute(HomePage homePage) {

        return homePage
                .gotoLoginPopUp()
                .fillEmail(userCredentials.email())
                .fillPassword(userCredentials.password())
                .clickLogInOnSuccess();
    }
```

### Assert Object 

Assert object is a wrapper for object against which we want perform same verifications (assertions). 
They main purpose is to hide low level assertions behind domain-specific API, hence increasing readability of the tests. 
Assert object are write for page objects and data returned from back-office.

```java
//then
thenBackOfficeUser(backOfficeUser).isEqualTo(user).isRegisteredAsCompany().hasNotConfirmedStatus();
           
private BackOfficeUserAssert thenBackOfficeUser(BackOfficeUser backOfficeUser) {
  return new BackOfficeUserAssert(backOfficeUser);
}
    
public BackOfficeUserAssert isEqualTo(User userData) {
  assertThat(userData.email()).isEqualTo(backOfficeUser.email());
  assertThat(userData.firstName()).isEqualTo(backOfficeUser.firstName());
  assertThat(userData.lastName()).isEqualTo(backOfficeUser.lastName());
  return this;
}
```



### Test

Tests make use of:
- page objects 
- assert objects
- action objects

It helps to separates code of the test from low level, technical details (selenium). 
Tests are written in declarative, kind of BDD way. 
They lock behavior instead of sequence of steps to perform. 
Because of this approach they are less susceptible to change and provide more readable form.



```java 
    @Test
    public void successfulLoggedIn() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        homePage = homePage.execute(new UserLogInAction().withCredentials(user));
        //then
        thenHomePage(homePage).hasLoggedInUserAs(user);
    }
```

## Reporting

[ExtentReport](http://extentreports.relevantcodes.com/) is used in order to create report from tests execution.  
In order to turn report generation on add folowing lines to your test suite. 

```java
	@BeforeClass
	public static void setUp() {
		ReportLogging.createReport();
	}

	@AfterClass
	public static void tearDown() {
		ReportLogging.closeReport();
	}
```
By default report is created in the target folder. 
Check **reportsPath** property to find out more. 

Example of a sample report [here](http://extentreports.com/os/3/extent.html).

## RunTests
In order to run tests locally on your machine clone this repo.<br /> 
Default configuration there is in configs/config_default.yml.<br />
In order to override default values create new file configs/config.yml by copping file configs/config_default.yml with changed properties (custom file). Alternativelly you can provide new values as a system properties.<br />

Some tests (logging, user creation) requires credentials.
Path to the credential file is set via property credentialsPath.
Unfortunatelly files with credentials are not commited to the public repo.
Because of it only one type of test can be performed by the anonymous user - company search. 

In order to run the example tests run test suite ExampleTestSuite.class.

Currently three features are tested:
- company search 
- user registration via internal form 
- user logging (registered previously by internal form)

## Resources

Most of the resources is in Polish language.

##### Videos 
* [Sławomir Sobótka -- Czego mama nigdy nie mówiła Ci na temat testowania automatycznego](https://www.youtube.com/watch?v=znRByMgnFSM)
* [Testy automatyczne Selenium - sposób na obronę przed smokami](https://www.youtube.com/watch?v=xmmCIdXqAn0)
* [Confitura 2013 - Michał Rokicki - Testy funkcjonalne (Selenium) w praktyce](https://www.youtube.com/watch?v=Hm67azkKs-4)

##### Repositories
* [Wikia/selenium-tests](https://github.com/Wikia/selenium-tests)
* [padcom/fluent-interfaces](https://github.com/padcom/fluent-interfaces)

##### Articles
* [PageObject - Martin Fowler](http://martinfowler.com/bliki/PageObject.html)
* [Page Object Design Pattern](http://www.seleniumhq.org/docs/06_test_design_considerations.jsp#page-object-design-pattern)
* [Czego mama nigdy nie mówiła Ci na temat testowania automatycznego by Sławek Sobótka]( https://prezi.com/cxslyh5sqo_z/czego-mama-nigdy-nie-mowia-ci-na-temat-testowania-automatycznego/)

##### Presentation
* [Automated end2end testing](https://prezi.com/xdhjffv5adwx/automated-end2end-testing/)


## Contact
In case of any questions don't hesitate to contact me:
- [klimiec@gmail.com](klimiec@gmail.com)
- [Linkedin](https://www.linkedin.com/in/piotr-klimiec-621873b5?trk=nav_responsive_tab_profile_pic)
- [Facebook](https://www.facebook.com/klimiec)
- [Twitter](https://twitter.com/piotr_twit)

