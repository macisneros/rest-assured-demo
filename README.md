The following project is oriented to verify a mock api that contain data about people such as
name, email, gender and last location running under TestNG. Given that, in order to start up the project:

Install dependencies:
    
    mvn clean install -DskipTests

To execute the tests:

    mvn test 

Whether you want to execute an specific suite go to suites folder, right click on a suite and select Run.

Project structure:

    ├───.idea
    ├───src
    │   ├───main
    │   │   ├───java                     # Objects and services
    │   │   │   ├───pojo                 # Java objects
    │   │   │   │   ├───externalbuilder  # Java objects implementing external builders
    │   │   │   │   └───lombok           # Java objects simplified by lombok for better maintenance
    │   │   │   └───service              # Contain services which automation scripts are going to interact
    │   │   └───resources
    │   └───test
    │       └───java                    # Automated tests
    ├───suites                          # Suites to organize execution
    └───target



