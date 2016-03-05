package info.archinnov.achilles.demo.b_bean_mapping;

import info.archinnov.achilles.annotations.ClusteringColumn;
import info.archinnov.achilles.annotations.PartitionKey;
import info.archinnov.achilles.annotations.Table;

@Table(keyspace = "production_ks", table = "user_by_country")
public class c_UserByCountry extends a_BaseUser {

    @PartitionKey
    protected String country;

    @ClusteringColumn
    private String userLogin;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
