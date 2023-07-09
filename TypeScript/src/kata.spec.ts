import { beforeEach, expect, test } from '@jest/globals'
import { User, UserRepository, UserService } from './kata'
import { anyNumber, anything, capture, deepEqual, instance, mock, verify, when } from 'ts-mockito'
import { fail } from 'assert'

let userRepositoryMock: UserRepository

beforeEach(() => {
    userRepositoryMock = mock<UserRepository>()
})

// This is an integration test - the UserService being tested is using the real UserRepository, which
// depends on the database. 
test('Should be able to create a user and read it back and verify proper values', async () => {
    const userService = new UserService()

    const firstName = 'Taylor'
    const lastName = 'Swift'
    const birthdate = new Date(1989, 11, 13)
    const birthdayString = '1989-12-13'

    const created = await userService.addUser(lastName, firstName, birthdate)
    expect(created).toBeDefined()
    expect(created!.id).toBeGreaterThanOrEqual(0)

    const read = await userService.getUser(created!.id!)
    expect(read).toBeDefined()
    expect(read!.firstName).toEqual(firstName)
    expect(read!.lastName).toEqual(lastName)
    expect(read!.birthday).toEqual(birthdayString)
})

// This is a unit test - we will create a mock of the UserRepository, so that we can isolate the test to
// only testing the UserService. This will help to track down the bug in the UserService.createUser method.
test('Should create a user with the proper values', async () => {
    // To Do
    //
    // 1) Create a new userService, but "inject" the userRepositoryMock so we can verify what the service sends to the repository `create`
    // const userService = new UserService(instance(userRepositoryMock))
    //
    // 2) Setup an expectation and action on the user repository mock, so that it will return a response when the `create` method is called
    // const createdUser = new User('first', 'last', new Date(), 123)
    // when(userRepositoryMock.create(anything())).thenResolve(createdUser)
    //
    // 3) Invoke the service method to add a user
    // const userToCreate = new User('FIRST', 'LAST', new Date())
    // const user = await userService.addUser(userToCreate.lastName, userToCreate.firstName, userToCreate.birthday)
    //
    // 4) Verify that the expected response was returned from addUser - which should be whatever the repository.create returns
    // expect(user).toBeDefined()
    // expect(user!.firstName).toEqual(createdUser.firstName)
    // expect(user!.lastName).toEqual(createdUser.lastName)
    // expect(user!.birthday).toEqual(createdUser.birthday)
    //
    // 5) Verify that the repository.create was invoked with proper values. This is the important part, and not possible without mocks
    // verify(userRepositoryMock.create(anything())).once()
    // const createUserRepositoryArgument = capture(userRepositoryMock.create).first()
    // expect(createUserRepositoryArgument[0]).toEqual(userToCreate)
    fail('Please implement this test. Thanks!')
})