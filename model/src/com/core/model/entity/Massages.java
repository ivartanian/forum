package com.core.model.entity;

import javax.persistence.*;

/**
 * Created by i.vartanian on 24.12.2014.
 */
@Entity
@Table(name = "massages")
@NamedQueries(value = {@NamedQuery(name = "topMessages", query = "SELECT o from Massages o order by o.date desc")})
@SequenceGenerator(name = "newid", sequenceName = "NEW_ID", allocationSize = 1)
public class Massages {

    private String login;
    private String massage;
    private String date;
    private int id;

    @Column(name = "login", nullable = true, insertable = true, updatable = true, length = 2000000000)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "massage", nullable = true, insertable = true, updatable = true, length = 2000000000)
    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    @Column(name = "date", nullable = true, insertable = true, updatable = true, length = 2000000000)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Id
    @GeneratedValue(generator = "newid", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Massages massages = (Massages) o;

        if (id != massages.id) return false;
        if (date != null ? !date.equals(massages.date) : massages.date != null) return false;
        if (login != null ? !login.equals(massages.login) : massages.login != null) return false;
        if (massage != null ? !massage.equals(massages.massage) : massages.massage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (massage != null ? massage.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

}
