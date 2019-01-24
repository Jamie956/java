package com.example;

public class Main {
	public static void main(String[] args) {
		LetterComposite orcMessage = new Messenger().messageFromOrcs();
		orcMessage.print(); // Where there is a whip there is a way.
		LetterComposite elfMessage = new Messenger().messageFromElves();
		elfMessage.print(); // Much wind pours from your mouth.

	}
	
}
