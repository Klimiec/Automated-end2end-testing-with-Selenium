


    Feature: Account Login

        As a registered user.
        I want to login into e-commerce platform.
        So that I have access to my account.


        Scenario: Successful logged in
            Given a non-logged in user, having account in e-commerce platform, enters home page
            When  the user logs in to his account
            Then  the user should be redirected back to the home page
            And   the user should be logged in as: Name + Surname


        Scenario: Unsuccessful logged in with incorrect password
            Given a non-logged in user, having account in e-commerce platform, enters home page
            When  the user attempts to log in to his account with incorrect password
            Then  the user should be redirected to login page
            And   the user should see error message


        Scenario: Unsuccessful logged in with incorrect email
            Given a non-logged in user, having account in e-commerce platform, enters home page
            When  the user attempts to log in to his account with incorrect email
            Then  the user should be redirected to login page
            And   the user should see error message