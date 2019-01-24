package com.example;

public class App {
	public static void main(String[] args) {
		OrcKing king = new OrcKing();
		king.makeRequest(new Request(RequestType.DEFEND_CASTLE, "defend castle"));
		// Orc commander handling request "defend castle"
		king.makeRequest(new Request(RequestType.TORTURE_PRISONER, "torture prisoner"));
		// Orc officer handling request "torture prisoner"
		king.makeRequest(new Request(RequestType.COLLECT_TAX, "collect tax"));
		// Orc soldier handling request "collect tax"

	}
}
