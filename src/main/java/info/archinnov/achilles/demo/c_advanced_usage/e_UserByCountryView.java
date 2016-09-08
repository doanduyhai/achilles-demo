package info.archinnov.achilles.demo.c_advanced_usage;

import info.archinnov.achilles.annotations.ClusteringColumn;
import info.archinnov.achilles.annotations.PartitionKey;


//@MaterializedView(baseEntity = b_User.class, view = "user_by_country_view")
public class e_UserByCountryView  {

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
