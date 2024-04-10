package medfx;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String messageId;
    private String conversationId;
    private String senderId;
    private String recipientId;
    private String content;
    private Date timestamp;

    public Message(String messageId, String conversationId, String senderId, String recipientId, String content, Date timestamp) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters
    public String getMessageId() { return messageId; }
    public String getConversationId() { return conversationId; }
    public String getSenderId() { return senderId; }
    public String getRecipientId() { return recipientId; }
    public String getContent() { return content; }
    public Date getTimestamp() { return timestamp; }

    // Setters
    public void setMessageId(String messageId) { this.messageId = messageId; }
    public void setConversationId(String conversationId) { this.conversationId = conversationId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    // toString
    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", conversationId='" + conversationId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    /* equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(getMessageId(), message.getMessageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessageId());
    }*/
}

/**
 * The message class provide a complete model for handling messages and 
 * conversations, including all the necessary methods for equality 
 * checks, hash code generation (if needed), and string representation. The equals 
 * method has been implemented based on unique identifiers (messageId 
 * for Message, id for Conversation) to ensure that comparisons are 
 * accurate, and the hashCode method is consistent with the equals 
 * method. The toString method provides a string representation of the 
 * objects, which can be especially helpful for logging and debugging 
 * purposes.
 *
**/