package com.example.modem;

import com.example.visitor.HayesVisitor;
import com.example.visitor.ModemVisitor;

public class Hayes extends Modem {
  
  @Override
  public void accept(ModemVisitor modemVisitor) {
    try {
      ((HayesVisitor) modemVisitor).visit(this);
    } catch (ClassCastException e) {
    	System.err.println("Unable to cast to HayesVisitor");
    }

  }
  
  @Override
  public String toString() {
    return "Hayes modem";
  }
}
