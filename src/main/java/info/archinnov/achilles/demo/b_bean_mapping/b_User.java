package info.archinnov.achilles.demo.b_bean_mapping;

import info.archinnov.achilles.annotations.Column;
import info.archinnov.achilles.annotations.PartitionKey;
import info.archinnov.achilles.annotations.Table;

@Table(keyspace = "production_ks", table = "user")
public class b_User extends a_BaseUser {

    @PartitionKey
    private String userLogin;

    @Column
    protected String country;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
