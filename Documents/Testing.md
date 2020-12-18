# Testing

Project has unit and integration tests. The graphic user interface was not included in the testing. Tests cover the dao (database-related classes) and domain packages.

## Unit and integration testing
### Domain
**PlayerServiceTest** provides integration tests for the PlayerService class and other domain classes. FakeGameDao and FakePlayerDao classes implement the DAO interfaces and provide the tools for saving data during tests. 

**CodeTest** tests if the color code is set up properly and doesn't have greys or other wrong colors. 

### Dao
**FileGameDaoTest** and **FilePlayerDaoTest** test if the writing to and reading from a file is successfull. The tests create a temporary file using TemporaryFolder-rules from JUnit. 

## Test coverage

Test coverage is quite high. Most missed branches were in PlayerService class due to many try/catch segments where the error scenarios were not tested. 

[!Test coverage](https://github.com/TuuliTG/Ohte/blob/main/Documents/misc/jacoco.png)


## System testing

The system testing for this program has been done manually. 

### Installing and configuration

I have downloaded and installed the app on a linux machine according to the instructions in the manual. The app has successfully launched and created necessary files. 


### functionalities
There was a peer review in the week 6 and the functionalities were tested by another student. Some bugs fixes and improvements were done after that. I have gone through all the functionalities listed in the Requirements Analysis and everything seem to work as wanted. 

## What need to be improved

The gui is not tested programmatically at all and therefore a majority of the program is not tested. 

