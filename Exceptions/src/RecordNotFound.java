public class RecordNotFound extends RuntimeException {
    private final long id;

    public long getId() {
        return this.id;
    }

    public RecordNotFound(String message, long id) {
        super(message + id);
        this.id = id;
    }
}