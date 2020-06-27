package controller;

public class ExitController implements RunController {
	@Override
	public void run() {
		System.exit(0);
	}
}
