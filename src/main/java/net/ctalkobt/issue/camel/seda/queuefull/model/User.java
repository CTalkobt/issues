package net.ctalkobt.issue.camel.seda.queuefull.model;

public class User {

    private final String firstName;
    private final String lastName;
    private final String id;
    
    public User(String id, String firstName, String lastName) {
        this.id = firstName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() { return id; }

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + '}';
    }
    
}
