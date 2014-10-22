package info.archinnov.achilles.demo.entity;


import java.util.UUID;
import info.archinnov.achilles.annotations.Column;
import info.archinnov.achilles.annotations.EmbeddedId;
import info.archinnov.achilles.annotations.Entity;
import info.archinnov.achilles.annotations.Order;
import info.archinnov.achilles.annotations.PartitionKey;
import info.archinnov.achilles.annotations.TimeUUID;
import org.hibernate.validator.constraints.NotBlank;

import static info.archinnov.achilles.demo.referential.SchemaName.MAILBOX;

@Entity(table = MAILBOX, comment = "table to store user messages")
public class Message {

    @EmbeddedId
    private CompoundPrimaryKey compoundPrimaryKey;

    @NotBlank
    @Column
    private String interlocutor;

    @NotBlank
    @Column
    private String content;

    public Message(String login, UUID date,String interlocutor, String content) {
        this.compoundPrimaryKey = new CompoundPrimaryKey(login, date);
        this.interlocutor = interlocutor;
        this.content = content;
    }


    public static class CompoundPrimaryKey {

        @Order(1)
        @PartitionKey
        private String login;

        @Order(value = 2, reversed = true)
        @TimeUUID
        private UUID date;

        public CompoundPrimaryKey(String login, UUID date) {
            this.login = login;
            this.date = date;
        }



        /*
            CREATE TABLE mailbox(
                login text,
                date timeuuid,
                content text,
                interlocutor text,
                PRIMARY KEY(login, date))
            WITH comment = 'table to store user messages'
            AND CLUSTERING ORDER BY(date DESC)
         */









        public CompoundPrimaryKey() {
        }


        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public UUID getDate() {
            return date;
        }

        public void setDate(UUID date) {
            this.date = date;
        }
    }



    public Message() {
    }


    public CompoundPrimaryKey getCompoundPrimaryKey() {
        return compoundPrimaryKey;
    }

    public void setCompoundPrimaryKey(CompoundPrimaryKey compoundPrimaryKey) {
        this.compoundPrimaryKey = compoundPrimaryKey;
    }

    public String getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(String interlocutor) {
        this.interlocutor = interlocutor;
    }

    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }

}
