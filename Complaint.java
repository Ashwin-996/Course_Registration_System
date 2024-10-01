enum Status {
    Pending,
    Resolved
}

public class Complaint{
    private Status status;
    private final String description;
    private static int counter = 1;
    private final int complaint_id;

    public Complaint(String description) {
        status = Status.Pending;
        this.description = description;
        Admin.add_complaint(this);
        complaint_id = counter;
        counter++;
    }

    public Status view_status() {
        return status;
    }

    public void set_status(Status new_status) {
        status = new_status;
    }

    public String get_description() {
        return description;
    }

    public int get_id() {
        return complaint_id;
    }
}
