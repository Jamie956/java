package com.example.ch06;

import java.util.*;

import com.example.Dish;
import static com.example.Dish.menu;

import static java.util.stream.Collectors.*;

public class Grouping {
	enum CaloricLevel { DIET, NORMAL, FAT };
	
	public static void main(String[] args) {
		test09();
	}
	
	public static void test01() {
		Map<Dish.Type, List<Dish>> rs = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println("Dishes grouped by type: " + rs);
	}
    
	public static void test02() {
	    Map<Dish.Type, List<String>> rs = menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
		System.out.println("Dish names grouped by type: " + rs);
	}
	
	public static void test03() {
		Map<CaloricLevel, List<Dish>> rs = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                } ));
		System.out.println("Dishes grouped by caloric level: " + rs);
	}
	
	public static void test04() {
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> rs = menu.stream()
				.collect(groupingBy(Dish::getType, groupingBy((Dish dish) -> {
					if (dish.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				})));
		System.out.println("Dishes grouped by type and caloric level: " + rs);
	}
	
	public static void test05() {
		Map<Dish.Type, Long> rs = menu.stream().collect(groupingBy(Dish::getType, counting()));
		System.out.println("Count dishes in groups: " + rs);
	}
	
	public static void test06() {
		Map<Dish.Type, Optional<Dish>> rs = menu.stream().collect(groupingBy(Dish::getType,
				reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
		System.out.println("Most caloric dishes by type: " + rs);
	}
	
	public static void test07() {
		 Map<Dish.Type, Dish> rs = menu.stream().collect(
	                groupingBy(Dish::getType,
	                        collectingAndThen(
	                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
	                                Optional::get)));
		 System.out.println("Most caloric dishes by type: " + rs);
	}
	
	public static void test08() {
		Map<Dish.Type, Integer> rs = menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));
		System.out.println("Sum calories by type: " + rs);
	}
	
	public static void test09() {
		Map<Dish.Type, Set<CaloricLevel>> rs = menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT; },
                        toSet() )));
		System.out.println("Caloric levels by type: " + rs);
	}
	

	// System.out.println("Dish tags grouped by type: " + groupDishTagsByType());
	//  System.out.println("Caloric dishes grouped by type: " + groupCaloricDishesByType());
    
//  private static Map<Dish.Type, Set<String>> groupDishTagsByType() {
//  return menu.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get( dish.getName() ).stream(), toSet())));
//}
//
//private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType() {
////  return menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
//  return menu.stream().collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
//}
}
