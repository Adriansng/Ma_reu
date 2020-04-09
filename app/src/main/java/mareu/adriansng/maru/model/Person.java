package mareu.adriansng.maru.model;

import java.util.Objects;

public class Person {

    /**Identifier*/
    private Integer id;

    /** Name*/
    private String name;

    /**Address mail*/
    private String addressMail;

    /**
     * @param id;
     * @param name;
     * @param addressMail;
     */

    public Person(Integer id, String name, String addressMail) {
        this.id = id;
        this.name = name;
        this.addressMail = addressMail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressMail() {
        return addressMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
