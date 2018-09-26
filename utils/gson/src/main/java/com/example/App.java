package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Item;
import com.example.Order;
import com.example.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {
	public static void main(String[] args) {
		test02();
	}
	
	// Entity to Json 
	public static void test01() {
		Item item = new Item();
		item.setId("01");
		item.setName("seafood");

		String jsonStr = new Gson().toJson(item);
		System.out.println(jsonStr);// {"id":"01","name":"seafood"}
	}

	// Json To Entity
	public static void test02() {
		Item item = new Gson().fromJson("{\"id\":\"01\",\"name\":\"seafood\"}", Item.class);
		System.out.println(item);//Item [id=01, name=seafood]
	}
	
	// Json To Entity With MultiName
	public static void test03() {
		User user = new Gson().fromJson("{\"id\":\"02\",\"first_name\":Zhou,\"last_name\":\"Jamie\"}", User.class);
		System.out.println(user);// User [id=02, firstName=Zhou, lastName=Jamie]
	}

	// Array String To Array
	public static void test04() {
		String[] arr = new Gson().fromJson("[\"Android\",\"Java\",\"PHP\"]", String[].class);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);// Android Java PHP
		}
	}

	// Array String To List
	public static void test05() {
		List<String> list = new Gson().fromJson("[\"Android\",\"Java\",\"PHP\"]", new TypeToken<List<String>>() {}.getType());
		System.out.println(list);//[Android, Java, PHP]
	}

	// Json To Map
	public static void test06() {
		Map<String, String> map = new Gson().fromJson("{\"id\":\"01\",\"name\":\"seafood\"}", new TypeToken<HashMap<String, String>>() {}.getType());
		System.out.println(map);//{name=seafood, id=01}
	}

	// Map To Json
	public static void test07() {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("id", "01");
		userMap.put("firstname", "Jamie");
		userMap.put("lastname", "Zhou");

		String userJson = new Gson().toJson(userMap);
		System.out.println(userJson);//{"firstname":"Jamie","id":"01","lastname":"Zhou"}
	}

	// Nest List To Json
	public static void test08() {
		List<Map<String, String>> userList = new ArrayList<Map<String, String>>();

		Map<String, String> userMap1 = new HashMap<String, String>();
		userMap1.put("id", "01");
		userMap1.put("firstname", "Jamie");
		userMap1.put("lastname", "Zhou");

		Map<String, String> userMap2 = new HashMap<String, String>();
		userMap2.put("id", "02");
		userMap2.put("firstname", "John");
		userMap2.put("lastname", "Nash");

		userList.add(userMap1);
		userList.add(userMap2);

		String userJson = new Gson().toJson(userList);
		System.out.println(userJson);//[{"firstname":"Jamie","id":"01","lastname":"Zhou"},{"firstname":"John","id":"02","lastname":"Nash"}]
	}

	// Nest Entity To Json
	public static void test09() {
		Order order = new Order();

		List<Item> itemlist = new ArrayList<Item>();
		Item item1 = new Item();
		item1.setId("01");
		item1.setName("item01");
		itemlist.add(item1);
		Item item2 = new Item();
		item2.setId("02");
		item2.setName("item02");
		itemlist.add(item2);
		order.setItemlist(itemlist);

		order.setId("666");
		order.setName("newBee");
		order.setItemlist(itemlist);

		String json = new Gson().toJson(order);
		System.out.println(json);// {"id":"666","name":"newBee","itemlist":[{"id":"01","name":"item01"},{"id":"02","name":"item02"}]}
	}

	// Nest Json To Entity
	public static void test10() {
		Order order = new Gson().fromJson("{\"id\":\"666\",\"name\":\"newBee\",\"itemlist\":[{\"id\":\"01\",\"name\":\"item01\"},{\"id\":\"02\",\"name\":\"item02\"}]}", Order.class);
		System.out.println(order);//Order [id=666, name=newBee, itemlist=[Item [id=01, name=item01], Item [id=02, name=item02]]]
	}

	// Json Array To Entity
	public static void test11() {
		String nestJson = "[{\"id\":\"09\",\"name\":\"item09\"}, {\"id\":\"10\",\"name\":\"item10\"}, {\"id\":\"11\",\"name\":\"item11\"}]";
		List<Item> itemlist = new Gson().fromJson(nestJson, new TypeToken<List<Item>>() {}.getType());
		System.out.println(itemlist);//[Item [id=09, name=item09], Item [id=10, name=item10], Item [id=11, name=item11]]
	}

}
