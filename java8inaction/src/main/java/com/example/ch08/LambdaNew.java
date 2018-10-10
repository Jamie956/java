package com.example.ch08;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class LambdaNew {
	public static void main(String[] args) {
		test01();
	}
	
    final static private Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
    
	public static void test01() {
		createProductLambda("loan");
		createProductLambda("stock");
	}
	
    public static Product createProductLambda(String name){
        Supplier<Product> p = map.get(name);
        if(p != null) return p.get();
        throw new RuntimeException("No such product " + name);
    }
    
    static private interface Product {}
    static private class Loan implements Product {
    	Loan (){
    		System.out.println("Loan constructor");
    	}
    }
    static private class Stock implements Product {
    	Stock (){
    		System.out.println("Stock constructor");
    	}
    }
    static private class Bond implements Product {
    	Bond (){
    		System.out.println("Bond constructor");
    	}
    }
}