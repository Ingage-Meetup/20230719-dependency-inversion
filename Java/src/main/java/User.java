import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

// A simple entity object that we'll persist in our database
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;

    public User(String firstName, String lastName, Date birthday) {
        this(firstName, lastName, birthday, -1);
    }

    public User(String firstName, String lastName, Date birthday, int id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public User(String firstName, String lastName, String birthday, int id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = Date.from(LocalDate.parse(birthday).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(birthday);
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = Date.from(LocalDate.parse(birthday).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public String toString() {
        return new StringBuilder("{\n")
                .append("    \"id\": ").append(id).append(",\n")
                .append("    \"firstName\": ").append("\"").append(firstName).append("\",\n")
                .append("    \"lastName\": ").append("\"").append(lastName).append("\",\n")
                .append("    \"birthday\": ").append("\"").append(getBirthdayString()).append("\"\n")
                .append("}")
                .toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        return true;
    }
}
