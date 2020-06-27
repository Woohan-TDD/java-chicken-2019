package domain.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.table.order.Order;

public class TableRepository {
	private static final List<Table> tables = new ArrayList<>();

	static {
		tables.add(new Table(1, new Order()));
		tables.add(new Table(2, new Order()));
		tables.add(new Table(3, new Order()));
		tables.add(new Table(5, new Order()));
		tables.add(new Table(6, new Order()));
		tables.add(new Table(8, new Order()));
	}

	public static List<Table> tables() {
		return Collections.unmodifiableList(tables);
	}
}
