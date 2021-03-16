# jiraTestWithJenkins

## To run the tests in terminal

    mvn test -Dtest=<Classname>#<method> -Denv.USER=<jirausername> -Denv.PWD=<jirapassword>

where 'Classname' is the name of the testclass and the 'method' is the name of the test method you want to run.
'jirausername' is a your username and 'jirapassword' is your password.

## To run in IDE (IntelliJ)
In configuration -> VM options:

    -ea -Dusername=<jirausername> -Dpassword=<jirapassword>
    
where 'jirausername' is a your username and 'jirapassword' is your password.



