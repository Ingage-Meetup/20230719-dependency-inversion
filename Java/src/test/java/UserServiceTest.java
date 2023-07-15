import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class UserServiceTest {
    @Test
    public void helloWorld() {
        assertEquals(true, true);
    }

    // This is an integration test - the UserService being tested is using the real
    // UserRepository, which depends on the database. This test is currently
    // failing.
    @Test
    public void userServiceShouldBeAbleToCreateUserAndReadItBackAndVerifyProperValues() throws Exception {
        UserService userService = new UserService();

        // I was working on this while Miss Swift had taken over Cincinnati and
        // "Tay-cor" stadium, so...
        String firstName = "Taylor";
        String lastName = "Swift";
        Date birthday = Date.from(LocalDate.of(1989, 12, 13).atStartOfDay(ZoneId.systemDefault()).toInstant());

        Optional<User> created = userService.addUser(lastName, firstName, birthday);
        assertTrue(created.isPresent());
        assertTrue(created.get().getId() >= 0);

        Optional<User> read = userService.getUser(created.get().getId());
        assertTrue(read.isPresent());
        assertEquals(firstName, read.get().getFirstName());
        assertEquals(lastName, read.get().getLastName());
        assertEquals(birthday, read.get().getBirthday());
    }

    // This is a unit test - we will create a mock of the UserRepository, so that we
    // can isolate the test to only testing the UserService. This will help to track
    // down the bug in the UserService.createUser method.
    @Test
    public void userServiceShouldCreateUserWithProperValues() {
        fail("Please implement this test. Thanks!");

        // To Do
        // 1) Create a mock of the UserRepository
        // UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

        // 2) Create a new userService, but "inject" the userRepositoryMock so we can
        // verify what the service sends to the repository `create`
        // UserService userService = new UserService(userRepositoryMock);

        // 3) Setup an expectation and action on the user repository mock, so that it
        // will return a response when the `create` method is called
        // User createdUser = new User("first", "last", new Date());
        // when(userRepositoryMock.create(any())).thenReturn(Optional.of(createdUser));

        // 4) Invoke the service method to add a user
        // User userToCreate = new User("FIRST", "LAST", new Date());
        // Optional<User> user = userService.addUser(userToCreate.getLastName(),
        // userToCreate.getFirstName(),
        // userToCreate.getBirthday());

        // 5) Verify that the expected response was returned from addUser - which should
        // be whatever the repository.create returns
        // assertTrue(user.isPresent());
        // assertEquals(createdUser.getFirstName(), user.get().getFirstName());
        // assertEquals(createdUser.getLastName(), user.get().getLastName());
        // assertEquals(createdUser.getBirthday(), user.get().getBirthday());

        // 6) Verify that the repository.create was invoked with proper values. This is
        // the important part, and not possible without mocks
        // verify(userRepositoryMock).create(userToCreate);
        // verifyNoMoreInteractions(userRepositoryMock);
    }
}
