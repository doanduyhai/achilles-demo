package info.archinnov.achilles.demo.entity;

import info.archinnov.achilles.annotations.*;
import info.archinnov.achilles.demo.transformer.EnumTransformer;
import info.archinnov.achilles.type.NamingStrategy;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static info.archinnov.achilles.demo.referential.SchemaName.KEYSPACE;
import static info.archinnov.achilles.demo.referential.SchemaName.USER_TRACKING;

@Entity(keyspace = KEYSPACE, table = USER_TRACKING, comment = "Table to track user action")
@Strategy(naming = NamingStrategy.SNAKE_CASE)
public class UserTracking {

    @EmbeddedId
    private CompoundPrimaryKey compoundPrimaryKey;

    @NotNull
    @Column
    @TypeTransformer(valueCodecClass = EnumTransformer.class)
    private Action action;

    @Column
    private Long itemId;



    public static enum Action {
        VIEW_ITEM,
        ADD_ITEM_TO_CART,
        REMOVE_ITEM_FROM_CART,
        PURCHASE_ITEM,
        CANCEL_ITEM_PURCHASE,
        COMPLAINT_ABOUT_ITEM;
    }

    public static class CompoundPrimaryKey {

        @Order(1)
        @Column
        private String login;

        @Order(2)
        @Column
        private Date date;



















        public CompoundPrimaryKey() {
        }
        public CompoundPrimaryKey(String login, Date date) {
            this.login = login;
            this.date = date;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }



























    public UserTracking() {
    }

    public UserTracking(String login, Date date, Action action, Long itemId) {
        this.compoundPrimaryKey = new CompoundPrimaryKey(login, date);
        this.action = action;
        this.itemId = itemId;
    }

    public CompoundPrimaryKey getCompoundPrimaryKey() {
        return compoundPrimaryKey;
    }

    public void setCompoundPrimaryKey(CompoundPrimaryKey compoundPrimaryKey) {
        this.compoundPrimaryKey = compoundPrimaryKey;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
