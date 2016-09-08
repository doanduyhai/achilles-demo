package info.archinnov.achilles.demo.a_bean_mapping;

import java.util.List;
import java.util.UUID;

import info.archinnov.achilles.annotations.*;
import info.archinnov.achilles.demo.d_power_users.Interlocutor;

@Table(keyspace = "production_ks", table = "emails")
public class EmailMessage {

    @PartitionKey(1)
    private String userLogin;

    @PartitionKey(2)
    private String inboxType;

    @TimeUUID
    @ClusteringColumn(asc = false)
    private UUID emailId; // emailid timeuuid

    @Index
    @Column
    private String subject;

    // UDT
    @Column
    private @Frozen Interlocutor sender;

    // UDT
    @EmptyCollectionIfNull
    @Column
    private List<@Frozen Interlocutor> recipients;

    @Column
    private String body;

    public EmailMessage() {
    }

    public EmailMessage(String userLogin, String inboxType, @TimeUUID UUID emailId, @Index String subject, Interlocutor sender, @EmptyCollectionIfNull List<Interlocutor> recipients, String body) {
        this.userLogin = userLogin;
        this.inboxType = inboxType;
        this.emailId = emailId;
        this.subject = subject;
        this.sender = sender;
        this.recipients = recipients;
        this.body = body;
    }

    //@Computed(function = "summaryOfBody", alias = "summary", targetColumns = "body", cqlClass = String.class)
    private String bodySummary;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getInboxType() {
        return inboxType;
    }

    public void setInboxType(String inboxType) {
        this.inboxType = inboxType;
    }

    public UUID getEmailId() {
        return emailId;
    }

    public void setEmailId(UUID emailId) {
        this.emailId = emailId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Interlocutor getSender() {
        return sender;
    }

    public void setSender(Interlocutor sender) {
        this.sender = sender;
    }

    public List<Interlocutor> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Interlocutor> recipients) {
        this.recipients = recipients;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodySummary() {
        return bodySummary;
    }

    public void setBodySummary(String bodySummary) {
        this.bodySummary = bodySummary;
    }
}
