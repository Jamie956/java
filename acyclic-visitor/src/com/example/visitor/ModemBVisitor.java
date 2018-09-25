package com.example.visitor;

import com.example.modem.ModemB;

public interface ModemBVisitor extends ModemVisitor {
  void visit(ModemB modemB);
}
