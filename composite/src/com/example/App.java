package com.example;

public class App {
	public static void main(String[] args) {
		Messenger message = new Messenger();
		LetterComposite orcMessage = message.messageFromOrcs();
		orcMessage.print();
	}
	
}
