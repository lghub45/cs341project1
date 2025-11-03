package TaskPack;

public class Task {

    private String desc;
    private int id; // helps for removing and editing
    private boolean status; // completion flag

    public Task(String desc, int id) {
        this.desc = desc;
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void update(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status ? "Complete" : "Incomplete";
    }

    public boolean statusReport() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // equality based on id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task other = (Task) o;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return getId() + (statusReport() ? "[✓] " : "[ ] ") + getDesc();
    }
}
