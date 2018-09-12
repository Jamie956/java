package com.example.ch08;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class FactoryMain {
	public static void main(String[] args) {
		test03();
	}
	
	// Create bean by user-defined name
	public static void test01() {
        Product p1 = ProductFactory.createProduct("loan");
        System.out.println(p1);
        
        Product p2 = ProductFactory.createProduct("stock");
        System.out.println(p2);
        
        Product p3 = ProductFactory.createProduct("bond");
        System.out.println(p3);
	}
	
	//:: Create instance
	public static void test02() {
		Supplier<Product> loanSupplier = Loan::new;
		// Execute Supplier
		Product p2 = loanSupplier.get();
		System.out.println(p2);
	}
	
	public static void test03() {
		Product p3 = ProductFactory.createProductLambda("loan");
		System.out.println(p3);
		
		Product p4 = ProductFactory.createProductLambda("");
		System.out.println(p4);
	}
	
    //Bean Factory
    static private class ProductFactory {
		// 1. Using switch
		public static Product createProduct(String name) {
			switch (name) {
			case "loan":
				return new Loan();
			case "stock":
				return new Stock();
			case "bond":
				return new Bond();
			default:
				throw new RuntimeException("No such product " + name);
			}
		}
        //2. Using Lambda
        public static Product createProductLambda(String name){
            Supplier<Product> p = map.get(name);
            if(p != null) return p.get();
            throw new RuntimeException("No such product " + name);
        }
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

    final static private Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
}