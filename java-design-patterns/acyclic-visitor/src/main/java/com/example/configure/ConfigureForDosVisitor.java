package com.example.configure;

import com.example.modem.Hayes;
import com.example.modem.Zoom;
import com.example.visitor.AllModemVisitor;

public class ConfigureForDosVisitor implements AllModemVisitor {
	public void visit(Hayes hayes) {
		System.out.println(hayes + " used with Dos configurator.");
	}

	public void visit(Zoom zoom) {
		System.out.println(zoom + " used with Dos configurator.");
	}
}
