package com.example.visitor;

import com.example.modem.ModemA;

public interface ModemAVisitor extends ModemVisitor {
  void visit(ModemA modemA);
}
