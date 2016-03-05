package info.archinnov.achilles.demo.b_bean_mapping;

import info.archinnov.achilles.annotations.Column;

public abstract class a_BaseUser {

    @Column
    protected String firstname;

    @Column
    protected String lastname;

    @Column
    protected Integer age;

    @Column
    protected String email;



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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
