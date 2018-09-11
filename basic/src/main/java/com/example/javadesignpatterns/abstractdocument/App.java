package com.example.javadesignpatterns.abstractdocument;

import java.util.*;

import com.example.javadesignpatterns.abstractdocument.domain.*;

public class App {
	public static void main(String[] args) {
		test02();
	}

	public static void test01() {
		Map<String, Object> partProperties = new HashMap<>();
		partProperties.put(HasType.PROPERTY, "test type");
		partProperties.put(HasModel.PROPERTY, "test model");
		partProperties.put(HasPrice.PROPERTY, 100L);
		Part part = new Part(partProperties);

		System.out.println(part.getType().get());
		System.out.println(part.getModel().get());
		System.out.println(part.getPrice().get());
	}

	public static void test02() {
		Map<String, Object> carProperties = new HashMap<>();
		carProperties.put(HasModel.PROPERTY, "test car model");
		carProperties.put(HasPrice.PROPERTY, 12L);
		carProperties.put(HasParts.PROPERTY, Arrays.asList(new HashMap<>(), new HashMap<>()));
		Car car = new Car(carProperties);

		System.out.println(car.getModel().get());
		System.out.println(car.getPrice().get());
		System.out.println(car.getParts().count());
	}
	
	

///**
// * AbstractDocument test class
// */
//public class AbstractDocumentTest {
//
//  private static final String KEY = "key";
//  private static final String VALUE = "value";
//
//  private class DocumentImplementation extends AbstractDocument {
//
//    DocumentImplementation(Map<String, Object> properties) {
//      super(properties);
//    }
//  }
//
//  private DocumentImplementation document = new DocumentImplementation(new HashMap<>());
//
//  @Test
//  public void shouldPutAndGetValue() {
//    document.put(KEY, VALUE);
//    assertEquals(VALUE, document.get(KEY));
//  }
//
//  @Test
//  public void shouldRetrieveChildren() {
//    Map<String, Object> child1 = new HashMap<>();
//    Map<String, Object> child2 = new HashMap<>();
//    List<Map<String, Object>> children = Arrays.asList(child1, child2);
//
//    document.put(KEY, children);
//
//    Stream<DocumentImplementation> childrenStream = document.children(KEY, DocumentImplementation::new);
//    assertNotNull(children);
//    assertEquals(2, childrenStream.count());
//  }
//
//  @Test
//  public void shouldRetrieveEmptyStreamForNonExistingChildren() {
//    Stream<DocumentImplementation> children = document.children(KEY, DocumentImplementation::new);
//    assertNotNull(children);
//    assertEquals(0, children.count());
//  }
//
//  @Test
//  public void shouldIncludePropsInToString() {
//    Map<String, Object> props = new HashMap<>();
//    props.put(KEY, VALUE);
//    DocumentImplementation document = new DocumentImplementation(props);
//    assertNotNull(document.toString().contains(KEY));
//    assertNotNull(document.toString().contains(VALUE));
//  }
//
//}
	
	public static void testx() {
		Map<String, Object> carProperties = new HashMap<>();
		carProperties.put(HasModel.PROPERTY, "300SL");
		carProperties.put(HasPrice.PROPERTY, 10000L);

		Map<String, Object> wheelProperties = new HashMap<>();
		wheelProperties.put(HasType.PROPERTY, "wheel");
		wheelProperties.put(HasModel.PROPERTY, "15C");
		wheelProperties.put(HasPrice.PROPERTY, 100L);

		Map<String, Object> doorProperties = new HashMap<>();
		doorProperties.put(HasType.PROPERTY, "door");
		doorProperties.put(HasModel.PROPERTY, "Lambo");
		doorProperties.put(HasPrice.PROPERTY, 300L);

		carProperties.put(HasParts.PROPERTY, Arrays.asList(wheelProperties, doorProperties));

		Car car = new Car(carProperties);
		System.out.println(car.getModel().get());
		System.out.println(car.getPrice().get());
		car.getParts().forEach(
				p -> System.out.println(p.getType().get() + " - " + p.getModel().get() + " - " + p.getPrice().get()));

	}

}
