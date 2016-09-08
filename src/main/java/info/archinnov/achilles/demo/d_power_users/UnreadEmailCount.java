package info.archinnov.achilles.demo.d_power_users;

import info.archinnov.achilles.annotations.*;

@Table(keyspace = "production_ks", table = "unread_email_count")
public class UnreadEmailCount {

    @PartitionKey
    private String userLogin;

    @Static
    @Counter
    @Column
    private Long totalUnreadCount;

    @ClusteringColumn
    private @Enumerated InboxType inboxType; // inboxType text

    @Counter
    @Column
    private Long unreadCount;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public InboxType getInboxType() {
        return inboxType;
    }

    public void setInboxType(InboxType inboxType) {
        this.inboxType = inboxType;
    }

    public Long getTotalUnreadCount() {
        return totalUnreadCount;
    }

    public void setTotalUnreadCount(Long totalUnreadCount) {
        this.totalUnreadCount = totalUnreadCount;
    }

    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }
}
