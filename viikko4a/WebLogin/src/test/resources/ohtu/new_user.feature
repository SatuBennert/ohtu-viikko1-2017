Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation succesful with correct username and password
    Given new user is selected
    When correct username "tonttu1" and password "Joulu2017" and passwordConfirmation "Joulu2017"are given
    Then user is created and user is logged in

  Scenario: creation fails with too short username and valid passord
        Given new user is selected
        When  correct username "to" and password "Joulu2017" and passwordConfirmation "Joulu2017"are given
        Then user is not created and error "username should have at least 3 characters" is reported   

    Scenario: creation fails with correct username and too short password
        Given new user is selected
        When  correct username "tonttu2" and password "Jo" and passwordConfirmation "Joulu2017"are given
        Then user is not created and error "password should have at least 8 characters" is reported   

    Scenario: creation fails with correct username and password consisting of letters
        Given new user is selected
        When  correct username "tonttu3" and password "Joulujoulu" and passwordConfirmation "Joulujoulu"are given
        Then user is not created and error "password can not contain only letters" is reported 

    Scenario: creation fails with already taken username and valid pasword
        Given new user is selected
        When correct username "tonttu1" and password "Joulu2017" and passwordConfirmation "Joulu2017"are given
        Then user is not created and error "username is already taken" is reported 

    Scenario: creation fails when password and password confirmation do not match
        Given new user is selected
        When correct username "tonttu4" and password "Joulu2017" and passwordConfirmation "Joulu2018"are given
        Then user is not created and error "password and password confirmation do not match" is reported   