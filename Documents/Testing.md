# Testing

Project has unit and integration tests. The graphic user interface was not included in the tests. Test cover the dao (database-related classes) and domain packages.

## Unit and integration testing
### Domain
**PlayerServiceTest** provides integration tests for the PlayerService class and other domain classes. FakeGameDao and FakePlayerDao classes implement the DAO interfaces and provide the tools for saving data during tests. 

**CodeTest** tests if the color code is set up properly and doesn't have greys or other wrong colors. 

**Option