Feature: A new user account can be created if a proper unused username and password are given

Scenario: creation succesful with correct username and password
        Given command new user is selected
        When  username "satu" and password "satunen1" are entered
        Then  system will respond with "new user registered"

Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When  username "satunen" and password "Sa" are entered
        Then  system will respond with "new user not registered"

Scenario: creation fails with too short username and correct password
        Given command new user is selected
        When  username "sa" and password "Satu80" are entered
        Then  system will respond with "new user not registered"

Scenario: creation fails with correct username and password consisting of letters
        Given command new user is selected
        When  username "satub" and password "sssaaatt" are entered
        Then  system will respond with "new user not registered"

Scenario: creation fails with already taken username and valid pasword
        Given command new user is selected
        When  username "Pekka" and password "Satu12345" are entered
        Then  system will respond with "new user not registered"
    