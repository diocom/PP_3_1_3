package ru.kata.spring.boot_security.demo.model;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name="password", nullable = false, length = 100)
    private String password;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name = "age")
    private int age;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="users_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "Id"))

    private List<Role> roles = new ArrayList<>();

    public User() { }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }
    public Long getId() {
        return id;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
