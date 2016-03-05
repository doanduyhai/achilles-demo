package info.archinnov.achilles.demo.c_data_types_and_codecs;

import info.archinnov.achilles.annotations.Column;
import info.archinnov.achilles.annotations.UDT;

@UDT(keyspace = "production_ks", name = "interlocutor")
public class Interlocutor {

    @Column
    private String login;

    @Column
    private String email;

    @Column
    private String firstname;

    @Column
    private String lastname;

    public Interlocutor() {
    }

    public Interlocutor(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
