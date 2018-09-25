package com.example.configure;

import com.example.modem.*;
import com.example.visitor.*;

public class ConfigureForDosVisitor implements ModemVisitor, ModemAVisitor, ModemBVisitor {
	public void visit(ModemA modemA) {
		System.out.println(modemA + " used with Dos configurator.");
	}

	public void visit(ModemB modemB) {
		System.out.println(modemB + " used with Dos configurator.");
	}
}
