package domain.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TableRepository {
	private static final List<Table> tables = new ArrayList<>();

	static {
		tables.add(new Table(1, new ArrayList<>()));
		tables.add(new Table(2, new ArrayList<>()));
		tables.add(new Table(3, new ArrayList<>()));
		tables.add(new Table(5, new ArrayList<>()));
		tables.add(new Table(6, new ArrayList<>()));
		tables.add(new Table(8, new ArrayList<>()));
	}

	public List<Table> findAll() {
		return Collections.unmodifiableList(tables);
	}

	public void updateOrderHistoryByNumber(final int number, final OrderHistory orderHistory) {
		Optional<Table> table = findByNumber(number);
		table.ifPresent(value -> value.addOrderHistory(orderHistory));
	}

	public Optional<Table> findByNumber(int number) {
		return findAll().stream()
			.filter(table -> table.isSameNumber(number))
			.findFirst();
	}
}
