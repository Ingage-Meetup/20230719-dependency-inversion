import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// This is an implementation of the UserRepository interface, using a real database.
// In this case, it is just an in-memory Sqlite database, but could be Postgres, MySQL, etc.
// There should be no changes required here for this exercise, but feel free to explore
// once you've completed the exercise.
public class SqliteUserRepository implements UserRepository {
    private Connection dbConn;

    public SqliteUserRepository() throws Exception {
        dbConn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement stmt = dbConn.createStatement();
        stmt.execute(
                "CREATE TABLE user ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "first_name TEXT, " +
                        "last_name TEXT, " +
                        "birthday TEXT " +
                        ")");
        stmt.close();
    }

    @Override
    public Optional<User> create(User user) {
        try {
            PreparedStatement stmt = dbConn
                    .prepareStatement("INSERT INTO user (first_name, last_name, birthday) VALUES (?, ?, ?)");
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getBirthdayString());

            stmt.executeUpdate();

            ResultSet rs = dbConn.createStatement().executeQuery("SELECT last_insert_rowid() as id");
            rs.next();
            int id = rs.getInt("id");

            return read(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<User>> list() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public Optional<User> read(int id) {
        try {
            PreparedStatement stmt = dbConn.prepareStatement(
                    "SELECT id, first_name AS firstName, last_name AS lastName, birthday FROM user WHERE id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String birthdayString = rs.getString("birthday");

            return Optional.of(new User(firstName, lastName, birthdayString, id));
        } catch (Exception e) {
            System.err.println("Error reading user from database");
            e.printStackTrace(System.err);
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> update(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<User> delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
