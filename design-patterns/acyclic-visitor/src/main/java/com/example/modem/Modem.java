package com.example.modem;

import com.example.visitor.ModemVisitor;

public abstract class Modem {
	public abstract void accept(ModemVisitor modemVisitor);
}
