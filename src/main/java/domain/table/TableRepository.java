package domain.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TableRepository {
    private static final List<Table> tables = new ArrayList<>();

    static {
        tables.add(new Table(1));
        tables.add(new Table(2));
        tables.add(new Table(3));
        tables.add(new Table(5));
        tables.add(new Table(6));
        tables.add(new Table(8));
    }

    public List<Table> findAll() {
        return Collections.unmodifiableList(tables);
    }

    public Optional<Table> findTableByNumber(final int number) {
        return tables.stream()
                .filter(table -> table.isSameNumber(number))
                .findFirst();
    }
}
