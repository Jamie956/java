package com.example;

import java.util.*;

public class Word extends LetterComposite {
	public Word(List<Letter> letters) {
		for (Letter l : letters) {
			this.add(l);
		}
	}

	@Override
	protected void printThisBefore() {
		System.out.print(" ");
	}
}