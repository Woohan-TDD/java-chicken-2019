package domain.table;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(final String message) {
        super(message);
    }
}
