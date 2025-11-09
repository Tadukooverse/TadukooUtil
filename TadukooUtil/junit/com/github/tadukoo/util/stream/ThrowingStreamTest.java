package com.github.tadukoo.util.stream;

import com.github.tadukoo.util.ListUtil;
import com.github.tadukoo.util.StringUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingStreamTest{
	
	@Test
	public void testEmpty(){
		assertEquals(0, ThrowingStream.empty().count());
	}
	
	@Test
	public void testOf1Item(){
		assertEquals(ListUtil.createList("A"), ThrowingStream.of("A")
				.collect(Collectors.toList()));
	}
	
	@Test
	public void testOfNullableNull(){
		assertEquals(0, ThrowingStream.ofNullable(null).count());
	}
	
	@Test
	public void testOfNullable(){
		assertEquals(ListUtil.createList("A"), ThrowingStream.ofNullable("A")
				.collect(Collectors.toList()));
	}
	
	@Test
	public void testOfMultipleParams(){
		assertEquals(ListUtil.createList("A", "B", "C"), ThrowingStream.of("A", "B", "C")
				.collect(Collectors.toList()));
	}
	
	@Test
	public void testIterateInfinite(){
		assertEquals(ListUtil.createList(0, 2, 4, 6, 8), ThrowingStream.iterate(0, n -> n + 2)
				.limit(5).collect(Collectors.toList()));
	}
	
	@Test
	public void testIterateFinite(){
		assertEquals(ListUtil.createList(0, 1, 2, 3, 4, 5), ThrowingStream.iterate(0, n -> n <= 5, n -> n + 1)
				.collect(Collectors.toList()));
	}
	
	@Test
	public void testGenerate(){
		assertEquals(ListUtil.createList("A", "A", "A"), ThrowingStream.generate(() -> "A")
				.limit(3).collect(Collectors.toList()));
	}
	
	@Test
	public void testConcat(){
		assertEquals(ListUtil.createList(3, 42, 1, 2), ThrowingStream.concat(
				ThrowingStream.of(3, 42), ThrowingStream.of(1, 2))
				.collect(Collectors.toList()));
	}
	
	@Test
	public void testStream(){
		Stream<Integer> theStream = Stream.of(1, 2, 3);
		ThrowingStream<Integer> theThrowingStream = ThrowingStream.from(theStream);
		assertEquals(theStream, theThrowingStream.stream());
	}
	
	@Test
	public void testIterator(){
		Stream<Integer> theStream = Stream.of(42, 7, 5);
		Iterator<Integer> it = ThrowingStream.from(theStream).iterator();
		int count = 0;
		while(it.hasNext()){
			int value = it.next();
			if(count == 0){
				assertEquals(42, value);
			}else if(count == 1){
				assertEquals(7, value);
			}else{
				assertEquals(5, value);
			}
			count++;
		}
	}
	
	@Test
	public void testSpliterator(){
		Stream<Integer> theStream = Stream.of(42, 7, 5, 12);
		Spliterator<Integer> split = ThrowingStream.from(theStream).spliterator();
		Spliterator<Integer> split2 = split.trySplit();
		List<Integer> half1 = new ArrayList<>();
		split.forEachRemaining(half1::add);
		assertEquals(5, half1.get(0));
		assertEquals(12, half1.get(1));
		List<Integer> half2 = new ArrayList<>();
		split2.forEachRemaining(half2::add);
		assertEquals(42, half2.get(0));
		assertEquals(7, half2.get(1));
	}
	
	@Test
	public void testSequential(){
		Stream<Integer> theStream = Stream.of(42, 7, 5, 12);
		assertFalse(ThrowingStream.from(theStream).sequential().isParallel());
	}
	
	@Test
	public void testParallel(){
		Stream<Integer> theStream = Stream.of(42, 7, 5, 12);
		assertTrue(ThrowingStream.from(theStream).parallel().isParallel());
	}
	
	@Test
	public void testUnordered(){
		List<Integer> theList = ListUtil.createList(42, 7, 5, 12, 39);
		List<Integer> newList = ThrowingStream.from(theList.stream()).unordered().toList();
		assertTrue(newList.containsAll(theList));
	}
	
	@Test
	public void testOnClose(){
		AtomicBoolean ran = new AtomicBoolean(false);
		Runnable closeOp = () -> ran.set(true);
		Stream<Integer> theStream = Stream.of(42, 7, 5, 12, 39);
		ThrowingStream.from(theStream).onClose(closeOp).close();
		assertTrue(ran.get());
	}
	
	@Test
	public void testNotThrowingFilter(){
		List<Integer> theList = ThrowingStream.from(Stream.of(0, 1, 2, 3))
				.filter(i -> i < 2)
				.toList();
		assertEquals(theList, ListUtil.createList(0, 1));
	}
	
	@Test
	public void testThrowingFilter(){
		try{
			List<Integer> theList = ThrowingStream.from(Stream.of(0, 1, 2, 3))
					.filter(i -> {
						if(i == 2){
							throw new Exception("you bad");
						}else{
							return i < 2;
						}
					})
					.toList();
			assertEquals(theList, ListUtil.createList(0, 1));
			fail();
		}catch(Exception e){
			assertEquals("you bad", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMap(){
		List<String> list = ThrowingStream.from(Stream.of("A", "B"))
				.map(a -> a)
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList("A", "B"), list);
	}
	
	@Test
	public void testThrowingMap(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.map(a -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("derp");
						}else{
							return a;
						}
					})
					.collect(Collectors.toList());
			fail();
		}catch(Exception e){
			assertEquals("derp", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapToInt(){
		List<Integer> list = ThrowingStream.from(Stream.of("1", "42"))
				.mapToInt(Integer::parseInt)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 42), list);
	}
	
	@Test
	public void testThrowingMapToInt(){
		try{
			ThrowingStream.from(Stream.of("1", "42"))
					.mapToInt(a -> {
						if(StringUtil.equals(a, "42")){
							throw new Exception("life");
						}else{
							return Integer.parseInt(a);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("life", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapToLong(){
		List<Long> list = ThrowingStream.from(Stream.of("1", "42"))
				.mapToLong(Long::parseLong)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 42L), list);
	}
	
	@Test
	public void testThrowingMapToLong(){
		try{
			ThrowingStream.from(Stream.of("1", "42"))
					.mapToLong(a -> {
						if(StringUtil.equals(a, "42")){
							throw new Exception("life");
						}else{
							return Long.parseLong(a);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("life", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapToDouble(){
		List<Double> list = ThrowingStream.from(Stream.of("1.5", "42.0"))
				.mapToDouble(Double::parseDouble)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.5, 42.0), list);
	}
	
	@Test
	public void testThrowingMapToDouble(){
		try{
			ThrowingStream.from(Stream.of("1.5", "42.0"))
					.mapToDouble(a -> {
						if(StringUtil.equals(a, "42.0")){
							throw new Exception("life");
						}else{
							return Double.parseDouble(a);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("life", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingFlatMap(){
		List<String> list = ThrowingStream.from(Stream.of("A", "B"))
				.flatMap(a -> {
					if(StringUtil.equals(a, "A")){
						return Stream.of("C", "D");
					}else{
						return Stream.of("A", "B");
					}
				})
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList("C", "D", "A", "B"), list);
	}
	
	@Test
	public void testThrowingFlatMap(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.flatMap(a -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							return Stream.of("A", "B");
						}
					})
					.collect(Collectors.toList());
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingFlatMapToInt(){
		List<Integer> list = ThrowingStream.from(Stream.of("A", "B"))
				.flatMapToInt(a -> {
					if(StringUtil.equals(a, "A")){
						return IntStream.of(3, 4);
					}else{
						return IntStream.of(5, 42);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3, 4, 5, 42), list);
	}
	
	@Test
	public void testThrowingFlatMapToInt(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.flatMapToInt(a -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							return IntStream.of(5, 42);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingFlatMapToLong(){
		List<Long> list = ThrowingStream.from(Stream.of("A", "B"))
				.flatMapToLong(a -> {
					if(StringUtil.equals(a, "A")){
						return LongStream.of(3, 4);
					}else{
						return LongStream.of(5, 42);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3L, 4L, 5L, 42L), list);
	}
	
	@Test
	public void testThrowingFlatMapToLong(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.flatMapToLong(a -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							return LongStream.of(5, 42);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingFlatMapToDouble(){
		List<Double> list = ThrowingStream.from(Stream.of("A", "B"))
				.flatMapToDouble(a -> {
					if(StringUtil.equals(a, "A")){
						return DoubleStream.of(3.2, 4.5);
					}else{
						return DoubleStream.of(5.1, 42.0);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3.2, 4.5, 5.1, 42.0), list);
	}
	
	@Test
	public void testThrowingFlatMapToDouble(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.flatMapToDouble(a -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							return DoubleStream.of(5.1, 42.0);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapMulti(){
		List<String> list = ThrowingStream.from(Stream.of("A", "B"))
				.mapMulti((a, consumer) -> {
					if(StringUtil.equals(a, "A")){
						consumer.accept("C");
						consumer.accept("D");
					}else{
						consumer.accept("A");
						consumer.accept("B");
					}
				})
				.map(s -> (String) s)
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList("C", "D", "A", "B"), list);
	}
	
	@Test
	public void testThrowingMapMulti(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.mapMulti((a, consumer) -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							consumer.accept("A");
							consumer.accept("B");
						}
					})
					.collect(Collectors.toList());
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapMultiToInt(){
		List<Integer> list = ThrowingStream.from(Stream.of("A", "B"))
				.mapMultiToInt((a, consumer) -> {
					if(StringUtil.equals(a, "A")){
						consumer.accept(5);
						consumer.accept(42);
					}else{
						consumer.accept(3);
						consumer.accept(4);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(5, 42, 3, 4), list);
	}
	
	@Test
	public void testThrowingMapMultiToInt(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.mapMultiToInt((a, consumer) -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							consumer.accept(3);
							consumer.accept(42);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapMultiToLong(){
		List<Long> list = ThrowingStream.from(Stream.of("A", "B"))
				.mapMultiToLong((a, consumer) -> {
					if(StringUtil.equals(a, "A")){
						consumer.accept(5L);
						consumer.accept(42L);
					}else{
						consumer.accept(3L);
						consumer.accept(4L);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(5L, 42L, 3L, 4L), list);
	}
	
	@Test
	public void testThrowingMapMultiToLong(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.mapMultiToLong((a, consumer) -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							consumer.accept(3L);
							consumer.accept(42L);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapMultiToDouble(){
		List<Double> list = ThrowingStream.from(Stream.of("A", "B"))
				.mapMultiToDouble((a, consumer) -> {
					if(StringUtil.equals(a, "A")){
						consumer.accept(5.3);
						consumer.accept(42.0);
					}else{
						consumer.accept(3.1);
						consumer.accept(4.2);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(5.3, 42.0, 3.1, 4.2), list);
	}
	
	@Test
	public void testThrowingMapMultiToDouble(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.mapMultiToDouble((a, consumer) -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							consumer.accept(3.1);
							consumer.accept(42.0);
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testDistinct(){
		List<String> list = ThrowingStream.from(Stream.of("A", "B", "C", "B"))
				.distinct()
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList("A", "B", "C"), list);
	}
	
	@Test
	public void testSorted(){
		List<Integer> list = ThrowingStream.from(Stream.of(3, 1, 2))
				.sorted()
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList(1, 2, 3), list);
	}
	
	@Test
	public void testSortedComparator(){
		List<Integer> list = ThrowingStream.from(Stream.of(3, 1, 2))
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList(3, 2, 1), list);
	}
	
	@Test
	public void testPeek(){
		StringBuilder boop = new StringBuilder();
		ThrowingStream.from(Stream.of("A", "B"))
				.peek(boop::append)
				.toList();
		assertEquals("AB", boop.toString());
	}
	
	@Test
	public void testPeekThrowing(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.peek(a -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}
					})
					.toList();
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testLimit(){
		List<String> list = ThrowingStream.from(Stream.of("A", "B", "C"))
				.limit(2)
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList("A", "B"), list);
	}
	
	@Test
	public void testSkip(){
		List<String> list = ThrowingStream.from(Stream.of("A", "B", "C"))
				.skip(2)
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList("C"), list);
	}
	
	@Test
	public void testTakeWhile(){
		List<Integer> list = ThrowingStream.from(Stream.of(1, 2, 42, 43, 5))
				.takeWhile(i -> i < 43)
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList(1, 2, 42), list);
	}
	
	@Test
	public void testTakeWhileThrowing(){
		try{
			ThrowingStream.from(Stream.of(1, 2, 42, 43, 5))
					.takeWhile(i -> {
						if(i == 43){
							throw new Exception("Bad I");
						}else{
							return i < 43;
						}
					})
					.collect(Collectors.toList());
			fail();
		}catch(Exception e){
			assertEquals("Bad I", e.getMessage());
		}
	}
	
	@Test
	public void testDropWhile(){
		List<Integer> list = ThrowingStream.from(Stream.of(1, 2, 42, 43, 5))
				.dropWhile(i -> i < 43)
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList(43, 5), list);
	}
	
	@Test
	public void testDropWhileThrowing(){
		try{
			ThrowingStream.from(Stream.of(1, 2, 42, 43, 5))
					.dropWhile(i -> {
						if(i == 43){
							throw new Exception("Bad I");
						}else{
							return i < 43;
						}
					})
					.collect(Collectors.toList());
			fail();
		}catch(Exception e){
			assertEquals("Bad I", e.getMessage());
		}
	}
	
	@Test
	public void testForEach(){
		List<String> list = new ArrayList<>();
		ThrowingStream.from(Stream.of("A", "B", "C"))
				.forEach(list::add);
		assertEquals(ListUtil.createList("A", "B", "C"), list);
	}
	
	@Test
	public void testForEachThrowing(){
		List<String> list = new ArrayList<>();
		try{
			ThrowingStream.from(Stream.of("A", "B", "C"))
					.forEach(a -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							list.add(a);
						}
					});
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
			assertTrue(list.isEmpty());
		}
	}
	
	@Test
	public void testForEachOrdered(){
		List<String> list = new ArrayList<>();
		ThrowingStream.from(Stream.of("A", "B", "C"))
				.forEachOrdered(list::add);
		assertEquals(ListUtil.createList("A", "B", "C"), list);
	}
	
	@Test
	public void testForEachOrderedThrowing(){
		List<String> list = new ArrayList<>();
		try{
			ThrowingStream.from(Stream.of("A", "B", "C"))
					.forEachOrdered(a -> {
						if(StringUtil.equals(a, "A")){
							throw new Exception("Bad A");
						}else{
							list.add(a);
						}
					});
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
			assertTrue(list.isEmpty());
		}
	}
	
	@Test
	public void testToArray(){
		Object[] array = ThrowingStream.from(Stream.of("A", "B"))
				.toArray();
		assertArrayEquals(new Object[]{"A", "B"}, array);
	}
	
	@Test
	public void testToArrayParameter(){
		String[] array = ThrowingStream.from(Stream.of("A", "B"))
				.toArray(String[]::new);
		assertArrayEquals(new String[]{"A", "B"}, array);
	}
	
	@Test
	public void testToArrayParameterThrowing(){
		try{
			ThrowingStream.from(Stream.of("A", "B"))
					.toArray(length -> {
						throw new Exception("Bad A");
					});
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testReduceWithIdentity(){
		int result = ThrowingStream.from(Stream.of(1, 2, 3))
				.reduce(0, Integer::sum);
		assertEquals(6, result);
	}
	
	@Test
	public void testReduceWithIdentityThrowing(){
		try{
			ThrowingStream.from(Stream.of(1, 2, 3))
					.reduce(0, (a, b) -> {
						if(a == 1 || b == 1){
							throw new Exception("Bad Value");
						}else{
							return a+b;
						}
					});
		}catch(Exception e){
			assertEquals("Bad Value", e.getMessage());
		}
	}
	
	@Test
	public void testReduce(){
		int result = ThrowingStream.from(Stream.of(1, 2, 3))
				.reduce(Integer::sum).orElse(42);
		assertEquals(6, result);
	}
	
	@Test
	public void testReduceThrowing(){
		try{
			ThrowingStream.from(Stream.of(1, 2, 3))
					.reduce((a, b) -> {
						if(a == 1 || b == 1){
							throw new Exception("Bad Value");
						}else{
							return a+b;
						}
					});
		}catch(Exception e){
			assertEquals("Bad Value", e.getMessage());
		}
	}
	
	@Test
	public void testReduce3Params(){
		int result = ThrowingStream.from(Stream.of("test", "derp", "blah"))
				.reduce(0, (sum, word) -> sum + word.length(), Integer::sum);
		assertEquals(12, result);
	}
	
	@Test
	public void testReduce3ParamsThrowingAccumulator(){
		try{
			ThrowingStream.from(Stream.of("test", "derp", "blah"))
					.reduce(0, (sum, word) -> {
						if(StringUtil.equals(word, "test")){
							throw new Exception("Bad Test");
						}else{
							return sum + word.length();
						}
					}, Integer::sum);
			fail();
		}catch(Exception e){
			assertEquals("Bad Test", e.getMessage());
		}
	}
	
	@Test
	public void testReduce3ParamsThrowingCombiner(){
		try{
			ThrowingStream.from(Stream.of("test", "derp", "blah"))
					.parallel()
					.reduce(0, (sum, word) -> sum + word.length(), (a, b) -> {
						if(a == 4 || b == 4){
							throw new Exception("Bad Value");
						}else{
							return a + b;
						}
					});
			fail();
		}catch(Exception e){
			assertEquals("Bad Value", e.getMessage());
		}
	}
	
	@Test
	public void testCollect3Params(){
		List<Integer> list = ThrowingStream.from(Stream.of(1, 2, 3))
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 2, 3), list);
	}
	
	@Test
	public void testCollect3ParamsThrowingSupplier(){
		try{
			ThrowingStream.from(Stream.of("A", "B", "C"))
					.collect(() -> {
						//noinspection ConstantValue
						if(true){
							throw new Exception("Bad Stuff");
						}else{
							return new ArrayList<>();
						}
					}, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad Stuff", e.getMessage());
		}
	}
	
	@Test
	public void testCollect3ParamsThrowingAccumulator(){
		try{
			ThrowingStream.from(Stream.of("A", "B", "C"))
					.collect(ArrayList::new, (list, obj) -> {
						if(StringUtil.equals(obj, "A")){
							throw new Exception("Bad Stuff");
						}else{
							list.add(obj);
						}
					}, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad Stuff", e.getMessage());
		}
	}
	
	@Test
	public void testCollect3ParamsThrowingCombiner(){
		try{
			ThrowingStream.from(Stream.of("A", "B", "C"))
					.parallel()
					.collect(ArrayList::new, ArrayList::add, (list1, list2) -> {
						//noinspection ConstantValue
						if(true){
							throw new Exception("Bad Stuff");
						}else{
							list1.addAll(list2);
						}
					});
			fail();
		}catch(Exception e){
			Throwable cause = e;
			while(cause.getCause() != null){
				cause = cause.getCause();
			}
			assertEquals("Bad Stuff", cause.getMessage());
		}
	}
	
	@Test
	public void testMin(){
		int result = ThrowingStream.from(Stream.of(1, 3, 42))
				.min(Comparator.naturalOrder())
				.orElse(4);
		assertEquals(1, result);
	}
	
	@Test
	public void testMax(){
		int result = ThrowingStream.from(Stream.of(1, 3, 42))
				.max(Comparator.naturalOrder())
				.orElse(4);
		assertEquals(42, result);
	}
	
	@Test
	public void testCount(){
		long count = ThrowingStream.from(Stream.of(1, 4, 42))
				.count();
		assertEquals(3L, count);
	}
	
	@Test
	public void testAnyMatch(){
		boolean result = ThrowingStream.from(Stream.of(1, 3, 42))
				.anyMatch(i -> i > 1);
		assertTrue(result);
	}
	
	@Test
	public void testAnyMatchThrowing(){
		try{
			ThrowingStream.from(Stream.of(42, 3, 1))
					.anyMatch(i -> {
						if(i == 42){
							throw new Exception("Bad 42");
						}else{
							return i > 5;
						}
					});
			fail();
		}catch(Exception e){
			assertEquals("Bad 42", e.getMessage());
		}
	}
	
	@Test
	public void testAllMatch(){
		boolean result = ThrowingStream.from(Stream.of(1, 3, 42))
				.allMatch(i -> i > 0);
		assertTrue(result);
	}
	
	@Test
	public void testAllMatchThrowing(){
		try{
			ThrowingStream.from(Stream.of(42, 3, 1))
					.allMatch(i -> {
						if(i == 42){
							throw new Exception("Bad 42");
						}else{
							return i > 0;
						}
					});
			fail();
		}catch(Exception e){
			assertEquals("Bad 42", e.getMessage());
		}
	}
	
	@Test
	public void testNoneMatch(){
		boolean result = ThrowingStream.from(Stream.of(1, 3, 42))
				.noneMatch(i -> i < 0);
		assertTrue(result);
	}
	
	@Test
	public void testNoneMatchThrowing(){
		try{
			ThrowingStream.from(Stream.of(1, 3, 42))
					.noneMatch(i -> {
						if(i == 42){
							throw new Exception("Bad 42");
						}else{
							return i > 40;
						}
					});
		}catch(Exception e){
			assertEquals("Bad 42", e.getMessage());
		}
	}
	
	@Test
	public void testFindFirst(){
		int result = ThrowingStream.from(Stream.of(42, 3, 1))
				.findFirst().orElse(5);
		assertEquals(42, result);
	}
	
	@Test
	public void testFindAny(){
		int result = ThrowingStream.from(Stream.of(42, 3, 1))
				.findAny().orElse(5);
		assertEquals(42, result);
	}
}
