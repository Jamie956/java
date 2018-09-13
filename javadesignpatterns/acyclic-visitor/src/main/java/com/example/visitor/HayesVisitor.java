package com.example.visitor;

import com.example.modem.Hayes;

public interface HayesVisitor extends ModemVisitor {
  void visit(Hayes hayes);
}
