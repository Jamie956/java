package com.example.visitor;

import com.example.modem.Zoom;

public interface ZoomVisitor extends ModemVisitor {
  void visit(Zoom zoom);
}
