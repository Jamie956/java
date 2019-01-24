package com.example.v2;

public class RefinedAbstraction implements Abstraction {
	private Implementor implementor;

	public RefinedAbstraction(Implementor implementor) {
		this.implementor = implementor;
	}

	@Override
	public void Operation() {
		// TODO Auto-generated method stub
		implementor.operation();
	}

	@Override
	public Implementor getImplementor() {
		// TODO Auto-generated method stub
		return implementor;
	}

}
