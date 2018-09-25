package com.example.modem;

import com.example.visitor.*;

public class ModemB extends Modem {
	@Override
	public void accept(ModemVisitor modemVisitor) {
		try {
			((ModemBVisitor) modemVisitor).visit(this);
		} catch (ClassCastException e) {
			System.err.println("Unable to cast to ModemBVisitor");
		}
	}

	@Override
	public String toString() {
		return "modem b";
	}
}
