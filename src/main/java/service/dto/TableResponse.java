package service.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.table.Table;

public class TableResponse {
	private int number;
	private boolean isOrdering;

	private TableResponse(int number, boolean isOrdering) {
		this.number = number;
		this.isOrdering = isOrdering;
	}

	public static TableResponse of(Table table) {
		return new TableResponse(table.getNumber(), !table.getOrder().isEmpty());
	}

	public static List<TableResponse> listOf(List<Table> tables) {
		return tables.stream()
			.map(TableResponse::of)
			.collect(Collectors.toList());
	}

	public int getNumber() {
		return number;
	}

	public boolean isOrdering() {
		return isOrdering;
	}
}
