import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class User {
    private String username;
    private List<String[]> inbox;

    public User(String username) {
        this.username = username;
        this.inbox = new ArrayList<>();
    }

    public void sendMessage(User recipient, String message) {
        System.out.println("Sending message from " + this.username + " to " + recipient.username + ": " + message);
        recipient.inbox.add(new String[]{this.username, message});
    }

    public void readInbox() {
        System.out.println("Inbox of " + this.username + ":");
        for (String[] msg : inbox) {
            System.out.println("From " + msg[0] + ": " + msg[1]);
        }
    }
}

class MessagingApp {
    private Map<String, User> users;

    public MessagingApp() {
        this.users = new HashMap<>();
    }

    public void registerUser(String username) {
        if (users.containsKey(username)) {
            System.out.println("Username " + username + " is already taken.");
        } else {
            users.put(username, new User(username));
            System.out.println("User " + username + " registered successfully.");
        }
    }

    public void sendMessage(String sender, String recipient, String message) {
        if (users.containsKey(sender) && users.containsKey(recipient)) {
            users.get(sender).sendMessage(users.get(recipient), message);
        } else {
            System.out.println("Either the sender or recipient does not exist.");
        }
    }

    public void showInbox(String username) {
        if (users.containsKey(username)) {
            users.get(username).readInbox();
        } else {
            System.out.println("User " + username + " does not exist.");
        }
    }

    public static void main(String[] args) {
        MessagingApp app = new MessagingApp();
        app.registerUser("Diego");
        app.registerUser("Patricia");

        app.sendMessage("Diego", "Patricia", "Hola, Patricia que tal tu día!");
        app.showInbox("Patricia");
        app.sendMessage("Patricia", "Diego", "Hola, Diego muy bien muchas gracias!");
        app.showInbox("Diego");
    }
}
