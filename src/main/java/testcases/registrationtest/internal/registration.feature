


    Feature: User registration via internal form

        As an non-registered, anonymous user.
        I want to create account in e-commerce platform.
        So that I have full access to platform capabilities.


        Scenario: Successful registration as a private person
            Given a non-logged in user enters e-commerce platform home page
            When  the user registers a new account as a private person with following data: ""
            Then  the user should be redirected back to the home page
            And   the user should be logged in as: FirstName + LastName
            And   newly created user status in the backoffice should be L (Light)

        Scenario: Successful registration as a company
            Given a non-logged in user enters e-commerce platform home page
            When  the user registers a new account as a company with following data: ""
            Then  the user should be redirected back to the home page
            And   the user should be logged in as: FirstName + LastName
            And   newly created user status in the backoffice should be L (Light)

        Scenario: Unsuccessful registration as private person with e-mail already registered in the system
            Given a non-logged in user enters e-commerce platform home page
            When  the user attempts to register a new account as a private person with email already registered
            Then  the user should stay in registration page
            And   the user should see error message "Email already taken"
            And   user password field should be blank
            And   user confirmation password should be blank

        Scenario: Unsuccessful registration as private person with password and password confirmation do not mach
            Given a non-logged in user enters e-commerce platform home page
            When  the user attempts to register a new account as a private with incorrect password confirmation
            Then  the user should stay in registration page
            And   the user should see error message "Password confirmation wrong"
            And   user password field should be blank
            And   user confirmation password should be blank