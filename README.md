# C3 - 20230719-dependency-inversion

## Overview
This month's discussion and exercise are focused on [Dependency Inversion](https://en.wikipedia.org/wiki/Dependency_inversion_principle), and [Unit Testing with Mocks](https://www.telerik.com/products/mocking/unit-testing.aspx). 

We will discuss the terminology with examples before getting started on the exercises here. We will also give a brief overview of all the [SOLID Programming Principles](http://blog.cleancoder.com/uncle-bob/2020/10/18/Solid-Relevance.html), since it is important to understand these, and how they all work together to help you write better code.

## Exercise
The exercise will show a pretty common scenario, where we have a database (in our case, a SQLite in-memory database), a Repository that handles access to the database, and a Service that uses the Repository to do it's work. This is a very typical separation of concerns, and is the [Single Responsibility Principle](https://en.wikipedia.org/wiki/Single-responsibility_principle) (the "S" in SOLID).

There are projects for both [Typescript](./TypeScript) and [Java](./Java) this month. We usually try to allow you to work in whatever language you prefer, but in order to setup an exercise to illustrate these principles, we had to choose something... So if you are not comfortable in either of these languages, use this as an opportunity to learn a new language too :)

The projects are almost identical in their setup as far as the components involved, the challenge, and the solution. In each project, you will find the following classes:
1. `User` - this is a simple object that contains some values that we want to persist to and read from a database
2. `UserRepository` - a [Repository pattern](https://medium.com/@pererikbergman/repository-design-pattern-e28c0f3e4a30) interface, defining the various operations you would need for persisting the `User` object to the database
3. `SqliteUserRepository` - an implementation of the `UserRepository`, using an in-memory SQLite database. In both projects, this creates a `user` table in the database when it is constructed, mirroring the properties on the `User` class.
4. `UserService` - a business service that depends on the `UserRepository` to manage the `User` persistence. Normally this service would have other business logic, but to keep things concise for this exercise, it simply does some mapping of inputs and calls the `UserRepository`.
5. A unit test file for the `UserService`. In the TypeScript project, it is [./TypeScript/src/kata.spec.ts], and in the Java project, it is [./Java/src/test/java/UserServiceTest.java].

Your tasks for tonight's exercise are:
1. Pick a language from the two provided
2. Follow the instructions in the README under your chosen language to get yourself setup and understand the layout
3. Run the unit tests for the project, and make sure you get one success and two failures
4. Implement the test with the `fail("Please implement this test. Thanks!")` failure. There is commented code and instructions in the test method, so you can read and understand the comments, and uncomment when you are ready.
5. Using the output from the test you just implemented, see if you can understand the reason for the test failure.
6. Once you understand the root cause for the failure, fix the `UserService.addUser()` method, and re-run the tests. You should have 3 passing tests.
7. At this point, you're done, but feel free to explore more.
8. Please ask questions as you have them - we are covering a lot tonight, but they are important concepts that will help you grow as a software developer.