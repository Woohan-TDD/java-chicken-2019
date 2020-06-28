package service;

import java.util.List;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.order.Amount;
import domain.table.Table;
import domain.table.TableRepository;
import service.dto.MenuOrderResponse;
import service.dto.TableOrderRequest;
import service.dto.TableOrderResponse;
import service.dto.TableResponse;

public class TableOrderService {
	private final MenuRepository menuRepository;
	private final TableRepository tableRepository;

	public TableOrderService(TableRepository tableRepository, MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
		this.tableRepository = tableRepository;
	}

	public TableOrderResponse findTableOrder(int number) {
		return new TableOrderResponse(number, MenuOrderResponse.listOf(findTableByNumber(number).getOrderIfExist()));
	}

	public List<TableResponse> findAllTables() {
		return TableResponse.listOf(tableRepository.findAll());
	}

	public void addOrder(TableOrderRequest tableOrderRequest) {
		Table table = findTableByNumber(tableOrderRequest.getTableNumber());
		table.addMenu(findMenuByNumber(tableOrderRequest.getMenuNumber()),
			Amount.of(tableOrderRequest.getMenuAmount()));
	}

	private Menu findMenuByNumber(int number) {
		return menuRepository.findByNumber(number)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 번호의 메뉴가 없습니다. number : " + number));
	}

	private Table findTableByNumber(int number) {
		return tableRepository.findByNumber(number)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 번호의 테이블이 없습니다. number :" + number));
	}
}
