package com.example.configure;

import com.example.modem.ModemA;
import com.example.visitor.*;

public class ConfigureForUnixVisitor implements ModemVisitor, ModemAVisitor {
	public void visit(ModemA modemA) {
		System.out.println(modemA + " used with Unix configurator.");
	}
}