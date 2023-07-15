import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository userRepository;

    public UserService() throws Exception {
        this.userRepository = new SqliteUserRepository();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
