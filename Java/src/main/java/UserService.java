import java.util.Date;
import java.util.List;
import java.util.Optional;

// This is the only class you should need to modify for this exercise, but you are welcome to
// explore more once you've solved the problem.
public class UserService {
    private UserRepository userRepository;

    // This defaults the UserRepository to a concrete implementation
    public UserService() throws Exception {
        this.userRepository = new SqliteUserRepository();
    }

    // This supports a constructor-injected UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // There is a bug in this method, need to write some unit tests to find it and
    // fix it
    public Optional<User> addUser(String lastName, String firstName, Date birthday) {
        return userRepository.create(new User(lastName, firstName, birthday));
    }

    public Optional<User> getUser(int id) {
        return userRepository.read(id);
    }

    public Optional<List<User>> listAllUsers() {
        return userRepository.list();
    }

    public Optional<User> updateUser(User user) {
        return userRepository.update(user);
    }

    public Optional<User> deleteUser(int id) {
        return userRepository.delete(id);
    }
}
