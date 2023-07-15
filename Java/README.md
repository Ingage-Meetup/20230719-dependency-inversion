# Java Exercise
This README contains instructions and guidance specifically for the Java version of the exercise. For instructions on tonight's kata itself, please see the [main README.md](../README.md) in the repository.

## Required Software
If you do not already have the following, you will need to install them for the Java exercise.
1. JDK 11 or higher - you can use your preferred way to install, or if you don't have a preference, you can look at the [Adoptium JDK 11 page](https://adoptium.net/temurin/releases/?version=11) for some options
  * After installing, verify your java version by running `java --version`, and make sure you have 11 or higher
2. An IDE - [IntelliJ IDEA](https://www.jetbrains.com/idea/download) Community or Ultimate will work, so will Visual Studio Code with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) and [Gradle for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-gradle) extensions.

You can technically do this exercise without an IDE of any sort, but if you want to be able to debug, etc, an IDE makes that easier.

## Running the Tests
Once you have gotten your preferred environment set up, you can run the unit tests from this directory by executing the following command:
```
./gradlew test
```

That will give you some output with one successful test and two failing tests. All tests are in the [./src/test/java/UserServiceTest.java](./src/test/java/UserServiceTest.java) file, and the failing tests are failing because of a bug in the `UserService.addUser()` in [./src/main/java/UserService.java](./src/main/java/UserService.java).

### Test helloWorld
This test just makes sure your environment is up and running properly.

### Test userServiceShouldCreateUserWithProperValues
This test is a true unit test, and is not implemented yet. There are comments in this test to guide you on how to implement the test, but you will need to run this, find the bug, fix it, and see the test pass.

### Test userServiceShouldBeAbleToCreateUserAndReadItBackAndVerifyProperValues
This is really an integration test - the UserService is using a concrete SqliteUserRepository for database access, and it is writing to and reading from the database as part of the test. The test is failing, but there is really no way in this test to determine the root cause for the failure. Once you find and fix the problem (by using the above test...), this test should also start passing.

## More Reading
If you are interested in learning more about the tools we used in this Java example, check these out:
* [Mockito](https://site.mockito.org/) - a unit test mocking framework for Java/Kotlin
* [JUnit 5](https://junit.org/junit5/) - the best* Java unit testing framework (* - one developer's opinion...)
* [SQLite](https://www.sqlite.org/index.html) - a small & fast file-based SQL database engine
* [JDBC](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html) - the Java Database API, provides an abstraction on vendor-specific databases, like SQLite. Note that in most "real-world" application, you will likely have some other layer(s) on top of this, like [JPA](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html), and you will not make JDBC calls directly.