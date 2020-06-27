package controller;

import view.InputView;

public class PageController {
	private final InputView inputView;
	private final ChickenController chickenController;

	public PageController(InputView inputView, ChickenController chickenController) {
		this.inputView = inputView;
		this.chickenController = chickenController;
	}

	public boolean runIfNotExit() {
		try {
			Command command = Command.findByNumber(inputView.inputCommandNumber());
			return !command.execute(chickenController);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
}
