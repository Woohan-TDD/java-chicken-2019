package domain.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		Table table = findByNumber(number);
		table.addOrderHistory(orderHistory);
	}

	public Table findByNumber(int number) {
		return findAll().stream()
			.filter(table -> table.isSameNumber(number))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당 테이블을 찾을 수 없습니다. number = " + number));
	}
}
