import java.util.List;
import java.util.Optional;

// In most languages, is typically easier to mock interfaces over classes. This also happens to be part 
// of the Dependency Inversion ("D") in SOLID. 
public interface UserRepository {
    Optional<User> create(User user);

    Optional<List<User>> list();

    Optional<User> read(int id);

    Optional<User> update(User user);

    Optional<User> delete(int id);
}