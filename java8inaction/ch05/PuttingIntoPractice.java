package com.example.java8inaction.ch05;

import java.util.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {
	public static void main(String[] args) {
		test07();
	}

	public static List<Transaction> transactions = Arrays.asList(
			new Transaction(new Trader("Brian", "Cambridge"), 2011, 300),
			new Transaction(new Trader("Raoul", "Cambridge"), 2012, 1000),
			new Transaction(new Trader("Raoul", "Cambridge"), 2011, 400),
			new Transaction(new Trader("Mario", "Milan"), 2012, 710),
			new Transaction(new Trader("Mario", "Milan"), 2012, 700),
			new Transaction(new Trader("Alan", "Cambridge"), 2012, 950));

	public static void test01() {
		// Query 1: Find all transactions from year 2011 and sort them by value (small
		// to high).
		List<Transaction> tr2011 = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
				.sorted(comparing(Transaction::getValue)).collect(toList());
		System.out.println(tr2011);
	}

	public static void test02() {
		// Query 2: What are all the unique cities where the traders work?
		List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct()
				.collect(toList());
		System.out.println(cities);
	}

	public static void test03() {
		// Query 3: Find all traders from Cambridge and sort them by name.
		List<Trader> traders = transactions.stream().map(Transaction::getTrader)
				.filter(trader -> trader.getCity().equals("Cambridge")).distinct().sorted(comparing(Trader::getName))
				.collect(toList());
		System.out.println(traders);
	}

	public static void test04() {
		// Query 4: Return a string of all traders’ names sorted alphabetically.
		String traderStr = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct()
				.sorted().reduce("", (n1, n2) -> n1 + n2);
		System.out.println(traderStr);
	}

	public static void test05() {
		// Query 5: Are there any trader based in Milan?
		boolean milanBased = transactions.stream()
				.anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
		System.out.println(milanBased);
	}

	public static void test06() {
		// Query 6: Update all transactions so that the traders from Milan are set to
		// Cambridge.
		transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Milan"))
				.forEach(trader -> trader.setCity("Cambridge"));
		System.out.println(transactions);
	}

	public static void test07() {
		// Query 7: What's the highest value in all the transactions?
		int highestValue = transactions.stream().map(Transaction::getValue).reduce(0, Integer::max);
		System.out.println(highestValue);
	}
}