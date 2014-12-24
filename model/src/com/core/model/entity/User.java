package com.core.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by i.vartanian on 16.12.2014.
 */
@NamedQueries({ @NamedQuery(name = "findPersonByLoginAndPassword", query = "SELECT o from User o where o.login = :login and o.password = :password"),
                @NamedQuery(name = "findPersonByLoginAndEmail", query = "SELECT o from User o where o.login = :login or o.email = :login")})
@Entity
@Table(name = "USERS")
public class User implements Serializable, Comparable<User>{

    @Id
    @Column(name = "LOGIN", nullable = false, insertable = true, updatable = true, length = 2000000000)
    private String login;

    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 2000000000)
    private String name;

    @Column(name = "PASSWORD", nullable = false, insertable = true, updatable = true, length = 2000000000)
    private String password;

    @Column(name = "EMAIL", nullable = true, insertable = true, updatable = true, length = 2000000000)
    private String email;


    public String getLogin() {
        return login;
    }

    public User() {
    }

    public User(String login, String name, String password, String email) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("login='").append(login).append('\'');
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", password='").append(password).append('\'');
//        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(User user) {
        if (user == null){
            return 1;
        }
        return login.compareTo(user.getLogin());
    }
}
