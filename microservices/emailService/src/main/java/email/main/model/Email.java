package email.main.model;

public class Email {
    String recipientName;
    String taskName;

    public Email(){}

    public Email(String recipientName, String taskName) {
        this.recipientName = recipientName;
        this.taskName = taskName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getMessage() {
        return taskName + " has already successful.";
    }

}
