package email.main.model;

public class Email {
    String[] recipientEmail;
    String taskName;
    String listName;

    public Email(){}

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Email(String[] recipientEmail, String taskName, String listName) {
        this.recipientEmail = recipientEmail;
        this.taskName = taskName;
        this.listName = listName;
    }

    public String[] getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String[] recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getMessage() {
        return taskName + " has changed status to " + listName + ".";
    }

}
