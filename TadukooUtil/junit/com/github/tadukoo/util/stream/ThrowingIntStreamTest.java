package com.github.tadukoo.util.stream;

import com.github.tadukoo.util.ListUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingIntStreamTest{
	
	@Test
	public void testEmpty(){
		assertEquals(0, ThrowingIntStream.empty().count());
	}
	
	@Test
	public void testOf1Param(){
		assertEquals(ListUtil.createList(42), ThrowingIntStream.of(42)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testOfMultipleParams(){
		assertEquals(ListUtil.createList(42, 27, 1, 2), ThrowingIntStream.of(42, 27, 1, 2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testIterateInfinite(){
		assertEquals(ListUtil.createList(0, 2, 4, 6, 8), ThrowingIntStream.iterate(0, n -> n + 2)
				.limit(5).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testIterateFinite(){
		assertEquals(ListUtil.createList(0, 1, 2, 3, 4, 5), ThrowingIntStream.iterate(0, n -> n <= 5, n -> n + 1)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testGenerate(){
		assertEquals(ListUtil.createList(42, 42, 42), ThrowingIntStream.generate(() -> 42)
				.limit(3).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testRange(){
		assertEquals(ListUtil.createList(1, 2), ThrowingIntStream.range(1, 3)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testRangeClosed(){
		assertEquals(ListUtil.createList(1, 2, 3), ThrowingIntStream.rangeClosed(1, 3)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testConcat(){
		assertEquals(ListUtil.createList(3, 42, 1, 2), ThrowingIntStream.concat(
						ThrowingIntStream.of(3, 42), ThrowingIntStream.of(1, 2))
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testStream(){
		IntStream theStream = IntStream.of(1, 2, 3);
		ThrowingIntStream theThrowingIntStream = ThrowingIntStream.from(theStream);
		assertEquals(theStream, theThrowingIntStream.stream());
	}
	
	@Test
	public void testIterator(){
		IntStream theStream = IntStream.of(42, 7, 5);
		Iterator<Integer> it = ThrowingIntStream.from(theStream).iterator();
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
		IntStream theStream = IntStream.of(42, 7, 5, 12);
		Spliterator<Integer> split = ThrowingIntStream.from(theStream).spliterator();
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
		IntStream theStream = IntStream.of(42, 7, 5, 12);
		assertFalse(ThrowingIntStream.from(theStream).sequential().isParallel());
	}
	
	@Test
	public void testParallel(){
		IntStream theStream = IntStream.of(42, 7, 5, 12);
		assertTrue(ThrowingIntStream.from(theStream).parallel().isParallel());
	}
	
	@Test
	public void testUnordered(){
		List<Integer> theList = ListUtil.createList(42, 7, 5, 12, 39);
		List<Integer> newList = ThrowingIntStream.from(theList.stream().mapToInt(val -> val)).unordered()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertTrue(newList.containsAll(theList));
	}
	
	@Test
	public void testOnClose(){
		AtomicBoolean ran = new AtomicBoolean(false);
		Runnable closeOp = () -> ran.set(true);
		IntStream theStream = IntStream.of(42, 7, 5, 12, 39);
		ThrowingIntStream.from(theStream).onClose(closeOp).close();
		assertTrue(ran.get());
	}
	
	@Test
	public void testNotThrowingFilter(){
		List<Integer> theList = ThrowingIntStream.of(0, 1, 2, 3)
				.filter(i -> i < 2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(theList, ListUtil.createList(0, 1));
	}
	
	@Test
	public void testThrowingFilter(){
		try{
			ThrowingIntStream.of(0, 1, 2, 3)
					.filter(i -> {
						if(i == 2){
							throw new Exception("you bad");
						}else{
							return i < 2;
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("you bad", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMap(){
		List<Integer> list = ThrowingIntStream.of(1, 42)
				.map(a -> a + 1)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(2, 43), list);
	}
	
	@Test
	public void testThrowingMap(){
		try{
			ThrowingIntStream.of(1, 42)
					.map(a -> {
						if(a == 42){
							throw new Exception("derp");
						}else{
							return a + 1;
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("derp", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapToObj(){
		List<String> list = ThrowingIntStream.of(1, 42)
				.mapToObj(a -> {
					if(a == 1){
						return "A";
					}else{
						return "B";
					}
				})
				.collect(Collectors.toList());
		assertEquals(ListUtil.createList("A", "B"), list);
	}
	
	@Test
	public void testThrowingMapToObj(){
		try{
			ThrowingIntStream.of(1, 42)
					.mapToObj(a -> {
						if(a == 42){
							throw new Exception("derp");
						}else{
							return "B";
						}
					})
					.collect(Collectors.toList());
			fail();
		}catch(Exception e){
			assertEquals("derp", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapToLong(){
		List<Long> list = ThrowingIntStream.of(1, 42)
				.mapToLong(a -> (long) a)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 42L), list);
	}
	
	@Test
	public void testThrowingMapToLong(){
		try{
			ThrowingIntStream.of(1, 42)
					.mapToLong(a -> {
						if(a == 42){
							throw new Exception("derp");
						}else{
							return (long) a;
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("derp", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapToDouble(){
		List<Double> list = ThrowingIntStream.of(1, 42)
				.mapToDouble(a -> (double) a)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.0, 42.0), list);
	}
	
	@Test
	public void testThrowingMapToDouble(){
		try{
			ThrowingIntStream.of(1, 42)
					.mapToDouble(a -> {
						if(a == 42){
							throw new Exception("derp");
						}else{
							return (double) a;
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("derp", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingFlatMap(){
		List<Integer> list = ThrowingIntStream.of(1, 42)
				.flatMap(a -> {
					if(a == 1){
						return IntStream.of(3, 4);
					}else{
						return IntStream.of(1, 2);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3, 4, 1, 2), list);
	}
	
	@Test
	public void testThrowingFlatMap(){
		try{
			ThrowingIntStream.of(1, 42)
					.flatMap(a -> {
						if(a == 42){
							throw new Exception("Bad A");
						}else{
							return IntStream.of(1, 2);
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
		List<Integer> list = ThrowingIntStream.of(1, 42)
				.mapMulti((a, consumer) -> {
					if(a == 42){
						consumer.accept(1);
						consumer.accept(2);
					}else{
						consumer.accept(3);
						consumer.accept(4);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3, 4, 1, 2), list);
	}
	
	@Test
	public void testThrowingMapMulti(){
		try{
			ThrowingIntStream.of(1, 42)
					.mapMulti((a, consumer) -> {
						if(a == 42){
							throw new Exception("Bad A");
						}else{
							consumer.accept(3);
							consumer.accept(4);
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
		List<Integer> list = ThrowingIntStream.of(1, 2, 3, 2)
				.distinct()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 2, 3), list);
	}
	
	@Test
	public void testSorted(){
		List<Integer> list = ThrowingIntStream.of(3, 1, 2)
				.sorted()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 2, 3), list);
	}
	
	@Test
	public void testPeek(){
		List<Integer> list = new ArrayList<>();
		ThrowingIntStream.of(1, 42)
				.peek(list::add)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 42), list);
	}
	
	@Test
	public void testPeekThrowing(){
		try{
			ThrowingIntStream.of(1, 42)
					.peek(a -> {
						if(a == 42){
							throw new Exception("Bad A");
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad A", e.getMessage());
		}
	}
	
	@Test
	public void testLimit(){
		List<Integer> list = ThrowingIntStream.of(1, 2, 3)
				.limit(2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 2), list);
	}
	
	@Test
	public void testSkip(){
		List<Integer> list = ThrowingIntStream.of(1, 2, 3)
				.skip(2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3), list);
	}
	
	@Test
	public void testTakeWhile(){
		List<Integer> list = ThrowingIntStream.of(1, 2, 42, 43, 5)
				.takeWhile(i -> i < 43)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 2, 42), list);
	}
	
	@Test
	public void testTakeWhileThrowing(){
		try{
			ThrowingIntStream.of(1, 2, 42, 43, 5)
					.takeWhile(i -> {
						if(i == 43){
							throw new Exception("Bad I");
						}else{
							return i < 43;
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad I", e.getMessage());
		}
	}
	
	@Test
	public void testDropWhile(){
		List<Integer> list = ThrowingIntStream.of(1, 2, 42, 43, 5)
				.dropWhile(i -> i < 43)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(43, 5), list);
	}
	
	@Test
	public void testDropWhileThrowing(){
		try{
			ThrowingIntStream.of(1, 2, 42, 43, 5)
					.dropWhile(i -> {
						if(i == 43){
							throw new Exception("Bad I");
						}else{
							return i < 43;
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("Bad I", e.getMessage());
		}
	}
	
	@Test
	public void testForEach(){
		List<Integer> list = new ArrayList<>();
		ThrowingIntStream.of(1, 2, 3)
				.forEach(list::add);
		assertEquals(ListUtil.createList(1, 2, 3), list);
	}
	
	@Test
	public void testForEachThrowing(){
		List<Integer> list = new ArrayList<>();
		try{
			ThrowingIntStream.of(42, 2, 1)
					.forEach(a -> {
						if(a == 42){
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
		List<Integer> list = new ArrayList<>();
		ThrowingIntStream.of(1, 2, 3)
				.forEachOrdered(list::add);
		assertEquals(ListUtil.createList(1, 2, 3), list);
	}
	
	@Test
	public void testForEachOrderedThrowing(){
		List<Integer> list = new ArrayList<>();
		try{
			ThrowingIntStream.of(42, 2, 1)
					.forEachOrdered(a -> {
						if(a == 42){
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
		int[] array = ThrowingIntStream.of(1, 42, 3)
				.toArray();
		assertArrayEquals(new int[]{1, 42, 3}, array);
	}
	
	@Test
	public void testReduceWithIdentity(){
		int result = ThrowingIntStream.of(1, 2, 3)
				.reduce(0, Integer::sum);
		assertEquals(6, result);
	}
	
	@Test
	public void testReduceWithIdentityThrowing(){
		try{
			ThrowingIntStream.of(1, 2, 3)
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
		int result = ThrowingIntStream.of(1, 2, 3)
				.reduce(Integer::sum).orElse(42);
		assertEquals(6, result);
	}
	
	@Test
	public void testReduceThrowing(){
		try{
			ThrowingIntStream.of(1, 2, 3)
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
	public void testCollect3Params(){
		List<Integer> list = ThrowingIntStream.of(1, 2, 3)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 2, 3), list);
	}
	
	@Test
	public void testCollect3ParamsThrowingSupplier(){
		try{
			ThrowingIntStream.of(1, 2, 3)
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
			ThrowingIntStream.of(42, 2, 3)
					.collect(ArrayList::new, (list, obj) -> {
						if(obj == 42){
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
			ThrowingIntStream.of(1, 2, 3)
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
			assertEquals("Bad Stuff", e.getMessage());
		}
	}
	
	@Test
	public void testSum(){
		int sum = ThrowingIntStream.of(1, 2, 42)
				.sum();
		assertEquals(45, sum);
	}
	
	@Test
	public void testMin(){
		int min = ThrowingIntStream.of(1, 2, 42)
				.min().orElse(43);
		assertEquals(1, min);
	}
	
	@Test
	public void testMax(){
		int max = ThrowingIntStream.of(1, 2, 42)
				.max().orElse(43);
		assertEquals(42, max);
	}
	
	@Test
	public void testCount(){
		long count = ThrowingIntStream.of(1, 2, 42)
				.count();
		assertEquals(3L, count);
	}
	
	@Test
	public void testAverage(){
		double average = ThrowingIntStream.of(1, 2, 42)
				.average().orElse(42);
		assertEquals(15.0, average);
	}
	
	@Test
	public void testSummaryStatistics(){
		IntSummaryStatistics stats = ThrowingIntStream.of(1, 2, 42)
				.summaryStatistics();
		assertEquals(3, stats.getCount());
		assertEquals(15.0, stats.getAverage());
		assertEquals(1, stats.getMin());
		assertEquals(42, stats.getMax());
		assertEquals(45, stats.getSum());
	}
	
	@Test
	public void testAnyMatch(){
		boolean result = ThrowingIntStream.of(1, 3, 42)
				.anyMatch(i -> i > 1);
		assertTrue(result);
	}
	
	@Test
	public void testAnyMatchThrowing(){
		try{
			ThrowingIntStream.of(42, 3, 1)
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
		boolean result = ThrowingIntStream.of(1, 3, 42)
				.allMatch(i -> i > 0);
		assertTrue(result);
	}
	
	@Test
	public void testAllMatchThrowing(){
		try{
			ThrowingIntStream.of(42, 3, 1)
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
		boolean result = ThrowingIntStream.of(1, 3, 42)
				.noneMatch(i -> i < 0);
		assertTrue(result);
	}
	
	@Test
	public void testNoneMatchThrowing(){
		try{
			ThrowingIntStream.of(1, 3, 42)
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
		int result = ThrowingIntStream.of(42, 3, 1)
				.findFirst().orElse(5);
		assertEquals(42, result);
	}
	
	@Test
	public void testFindAny(){
		int result = ThrowingIntStream.of(42, 3, 1)
				.findAny().orElse(5);
		assertEquals(42, result);
	}
	
	@Test
	public void testAsLongStream(){
		ThrowingLongStream stream = ThrowingIntStream.of(1, 2, 42)
				.asLongStream();
		assertEquals(ListUtil.createList(1L, 2L, 42L), stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testAsDoubleStream(){
		ThrowingDoubleStream stream = ThrowingIntStream.of(1, 2, 42)
				.asDoubleStream();
		assertEquals(ListUtil.createList(1.0, 2.0, 42.0), stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testBoxed(){
		ThrowingStream<Integer> stream = ThrowingIntStream.of(1, 2, 42)
				.boxed();
		assertEquals(ListUtil.createList(1, 2, 42), stream.collect(Collectors.toList()));
	}
}
