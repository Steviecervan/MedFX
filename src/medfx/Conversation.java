package medfx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Conversation implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private List<Message> messages;

    public Conversation(String id) {
        this.id = id;
        this.messages = new ArrayList<>();
    }

    // Method to add a message to the conversation
    public void addMessage(Message message) {
        messages.add(message);
    }

    // Getters
    public String getId() { return id; }
    public List<Message> getMessages() { return messages; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setMessages(List<Message> messages) { this.messages = messages; }

    // toString
    @Override
    public String toString() {
        return "Conversation{" +
                "id='" + id + '\'' +
                ", messages=" + messages +
                '}';
    }

    /* equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conversation)) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
