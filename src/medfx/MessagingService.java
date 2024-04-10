package medfx;

import java.io.*;
import java.util.*;

public class MessagingService {
	private final String storagePath; // Directory where conversations are stored

	public MessagingService(String storagePath) {
		this.storagePath = storagePath;
		// Make sure the storage directory exists
		new File(storagePath).mkdirs();
	}

	public void sendMessage(String senderId, String recipientId, String content) {
		String conversationId = createConversationId(senderId, recipientId);
		Message message = new Message(UUID.randomUUID().toString(), conversationId, senderId, recipientId, content, new Date());
		Conversation conversation = getOrCreateConversation(conversationId);
		conversation.addMessage(message);
		saveConversation(conversation);
	}

	private Conversation getOrCreateConversation(String conversationId) {
		// Attempt to load the conversation from file storage first
		Conversation conversation = loadConversation(conversationId);
		if (conversation == null) {
			conversation = new Conversation(conversationId);
		}
		return conversation;
	}

	private void saveConversation(Conversation conversation) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFilePath(conversation.getId())))) {
			oos.writeObject(conversation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Conversation loadConversation(String conversationId) {
		String filePath = getFilePath(conversationId);
		File file = new File(filePath);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				return (Conversation) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// Retrieves all conversation IDs for a specific user
	public List<String> getConversationsForUser(String userId) {
		File folder = new File(storagePath);
		File[] listOfFiles = folder.listFiles();
		List<String> userConversations = new ArrayList<>();
		if (listOfFiles != null) {
			for (File file : listOfFiles) {
				String fileName = file.getName();
				if (fileName.contains(userId)) {
					userConversations.add(fileName.replace(".ser", ""));
				}
			}
		}
		return userConversations;
	}

	// Deletes a specific conversation
	public boolean deleteConversation(String conversationId) {
		String filePath = getFilePath(conversationId);
		File file = new File(filePath);
		return file.delete();
	}

	// Retrieves all messages for a specific user across all conversations
	public List<Message> getAllMessagesForUser(String userId) {
		List<Message> allMessages = new ArrayList<>();
		List<String> conversationIds = getConversationsForUser(userId);

		for (String conversationId : conversationIds) {
			allMessages.addAll(getMessagesFromConversation(conversationId));
		}

		return allMessages;
	}

	// Deletes all conversations for a specific user
	public void deleteAllConversationsForUser(String userId) {
		List<String> conversationIds = getConversationsForUser(userId);
		for (String conversationId : conversationIds) {
			deleteConversation(conversationId);
		}
	}

	// Removes a specific message from a conversation
	public boolean removeMessageFromConversation(String conversationId, String messageId) {
		Conversation conversation = loadConversation(conversationId);
		if (conversation != null) {
			boolean removed = conversation.getMessages().removeIf(msg -> msg.getMessageId().equals(messageId));
			if (removed) {
				saveConversation(conversation); // Save the conversation after removing the message
				return true;
			}
		}
		return false;
	}

	// Utility method to construct file path from conversation ID
	private String getFilePath(String conversationId) {
		return storagePath + File.separator + conversationId + ".ser";
	}

	private String createConversationId(String userOne, String userTwo) {
		// This method should generate a unique ID based on the two users
		// Here we just concatenate the two IDs, ensuring the lower ID is first for consistency
		List<String> ids = Arrays.asList(userOne, userTwo);
		Collections.sort(ids);
		return String.join("-", ids);
	}

	public List<Message> getMessagesFromConversation(String conversationId) {
		Conversation conversation = loadConversation(conversationId);
		return conversation != null ? conversation.getMessages() : new ArrayList<>();
	}

}

/**
 * 
 * getConversationsForUser retrieves all conversation IDs that include a specific user. This could be used to populate a user's message inbox.
 * deleteConversation deletes a file representing a conversation. This could be called when a user wants to remove an entire message thread.
 * getAllMessagesForUser collects all messages from all conversations involving a specific user. This could be used for generating an archive or for an administrative view.
 * deleteAllConversationsForUser deletes all conversations involving a specific user. This might be part of a user account deletion process.
 * removeMessageFromConversation removes a single message from a conversation, which could be used for message recall or moderation purposes.
 * sendMessage creates a new message, retrieves or creates the corresponding conversation, adds the message to the conversation, and then saves the updated conversation.
 * saveConversation writes a Conversation object to a file using object serialization.
 * loadConversation reads a Conversation object from a file using object deserialization.
 * getFilePath constructs a file path for the conversation file based on the conversationId.
 * The createConversationId method ensures a consistent conversationId is generated regardless of which user is considered the sender or receiver.

**/
