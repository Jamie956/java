package com.example.configure;

import com.example.modem.Zoom;
import com.example.visitor.ModemVisitor;
import com.example.visitor.ZoomVisitor;

public class ConfigureForUnixVisitor implements ModemVisitor, ZoomVisitor {
	public void visit(Zoom zoom) {
		System.out.println(zoom + " used with Unix configurator.");
	}
}