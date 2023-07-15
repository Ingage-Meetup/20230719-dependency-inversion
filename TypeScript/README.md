# TypeScript Exercise
This README contains instructions and guidance specifically for the TypeScript version of the exercise. For instructions on tonight's kata itself, please see the [main README.md](../README.md) in the repository.

## Required Software
If you do not already have the following, you will need to install them for the TypeScript exercise.
1. Node version 12 or higher - you can use your preferred way to install, or if you don't have a preference, you can look at the [Nodejs Download page](https://nodejs.org/en/download/current) for options
  * After installing, try `node --version` to verify that you have v12 or higher
2. An IDE - [IntelliJ IDEA](https://www.jetbrains.com/idea/download) Community or Ultimate will work, and Visual Studio Code supporte TypeScript "out of the box" with no extensions required

You can technically do this exercise without an IDE of any sort, but if you want to be able to debug, etc, an IDE makes that easier.

## Running the Tests
Once you have gotten your preferred environment set up, you can run the unit tests from this directory by executing the following command:
```
npm run test
```

That will give you some output with one successful test and two failing tests. All tests are in the [./src/kata.spec.ts](./src/kata.spec.ts) file, and the failing tests are failing because of a bug in the `UserService.addUser()` in [./src/kata.ts](./src/kata.ts).

### Test "Hello World"
This test just makes sure your environment is up and running properly.

### Test "Should create a user with the proper values"
This test is a true unit test, and is not implemented yet. There are comments in this test to guide you on how to implement the test, but you will need to run this, find the bug, fix it, and see the test pass.

### Test "Should be able to create a user and read it back and verify proper values"
This is really an integration test - the UserService is using a concrete SqliteUserRepository for database access, and it is writing to and reading from the database as part of the test. The test is failing, but there is really no way in this test to determine the root cause for the failure. Once you find and fix the problem (by using the above test...), this test should also start passing.

## More Reading
If you are interested in learning more about the tools we used in this TypeScriupt example, check these out:
* [ts-mockito](https://github.com/NagRock/ts-mockito) - a unit test mocking framework for TypeScript
* [Jest](https://jestjs.io/) - a Javascript/TypeScript unit testing framework
* [SQLite](https://www.sqlite.org/index.html) - a small & fast file-based SQL database engine
* [sqlite npm package](https://www.npmjs.com/package/sqlite) - a library for working with a SQLite database (or in-memory databae, in our case). Unlike Java, there are no community-accepted standards (like [JPA](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html) in Java) for database access. There are ORM tools like Prisma, Loopback, Sequelize, and others, but there is not one accepted standard. So the database access code you see in this project, while it is very crude, could very well show up in a similar fashion in other projects you work on.