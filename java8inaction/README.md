
### lambda runnable
```java
Runnable r = () -> System.out.println("Hello!");
r.run();
```

### Stream
```java
//String to Stream
Stream.of("Java 8", "Lambdas", "In", "Action");
//Stream map
stream.map(String::toUpperCase);
//Stream limit
stream = stream.limit(2);
//Stream forEach
stream.forEach(System.out::println);
//Create empty stream
Stream.empty();

//Stream iterate
Stream.iterate(0, n -> n + 2);
//Stream generate
Stream.generate(Math::random);

//Array to IntStream
Arrays.stream(array);
//IntStream sum
intStream.sum();



//List -> Stream
Stream<Dish> stream = menu.stream();
//Stream filter
stream = stream.filter(Dish::isVegetarian);
//Stream -> List
List<Dish> list = stream.collect(toList());
//List forEach
list.forEach(System.out::println);










```