package library.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable, CsvConvertible {
    private String firstName;
    private String lastName;
    private String nin;

    User(String firstName, String lastName, String nin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nin = nin;
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

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(nin, user.nin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, nin);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + nin;
    }


}
