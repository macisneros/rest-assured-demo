The following project is oriented to verify a mock api that contain data about people such as
name, email, gender and last location running under TestNG. Given that, in order to start up the project:

1.- Open up Intellij and import the project as Maven in Windows OS.

2.- Install scoop:

    iwr -useb get.scoop.sh | iex
    Set-ExecutionPolicy RemoteSigned -scope CurrentUser
    scoop install curl

3.- Install allure report:
    
    scoop install allure

4.- Install dependencies:
    
    mvn clean install -DskipTests

5.- To execute the tests:

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

Reporting:

Allure report is generated automatically and is stored in its own folder per each execution. 
Folder's name is determined by the time the execution gets started following format yyyy_MM_dd_SSSS_HH_mm_ss


