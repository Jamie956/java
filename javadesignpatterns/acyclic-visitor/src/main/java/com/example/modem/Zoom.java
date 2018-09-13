package com.example.modem;

import com.example.visitor.ModemVisitor;
import com.example.visitor.ZoomVisitor;

public class Zoom extends Modem {
	@Override
	public void accept(ModemVisitor modemVisitor) {
		try {
			((ZoomVisitor) modemVisitor).visit(this);
		} catch (ClassCastException e) {
			System.err.println("Unable to cast to ZoomVisitor");
		}
	}

	@Override
	public String toString() {
		return "Zoom modem";
	}
}
