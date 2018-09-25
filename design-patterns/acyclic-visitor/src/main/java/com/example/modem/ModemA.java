package com.example.modem;

import com.example.visitor.*;

public class ModemA extends Modem {
  
  @Override
  public void accept(ModemVisitor modemVisitor) {
    try {
      ((ModemAVisitor) modemVisitor).visit(this);
    } catch (ClassCastException e) {
    	System.err.println("Unable to cast to ModemAVisitor");
    }

  }
  
  @Override
  public String toString() {
    return "modem a";
  }
}
