package domain.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tables {

    private final List<Table> tables;

    private Tables(final List<Table> tables) {
        Objects.requireNonNull(tables, "Tables에서 Null 발생!");
        this.tables = new ArrayList<>(tables);
    }

    public static Tables from(final List<Table> tables) {
        return new Tables(tables);
    }

    public Table findTableByNumber(final int tableNumber) {
        return tables.stream()
                .filter(table -> table.isSameNumber(tableNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 테이블 번호입니다."));
    }

    public List<Table> getTables() {
        return tables;
    }
}