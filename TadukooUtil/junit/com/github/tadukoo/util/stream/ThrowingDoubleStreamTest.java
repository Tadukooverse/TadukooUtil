package com.github.tadukoo.util.stream;

import com.github.tadukoo.util.ListUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingDoubleStreamTest{
	
	@Test
	public void testEmpty(){
		assertEquals(0, ThrowingDoubleStream.empty().count());
	}
	
	@Test
	public void testOf1Param(){
		assertEquals(ListUtil.createList(42.0), ThrowingDoubleStream.of(42)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testOfMultipleParams(){
		assertEquals(ListUtil.createList(42.0, 27.0, 1.0, 2.0), ThrowingDoubleStream.of(42, 27, 1, 2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testIterateInfinite(){
		assertEquals(ListUtil.createList(0.0, 2.0, 4.0, 6.0, 8.0), ThrowingDoubleStream.iterate(0, n -> n + 2)
				.limit(5).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testIterateFinite(){
		assertEquals(ListUtil.createList(0.0, 1.0, 2.0, 3.0, 4.0, 5.0), ThrowingDoubleStream.iterate(0, n -> n <= 5, n -> n + 1)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testGenerate(){
		assertEquals(ListUtil.createList(42.0, 42.0, 42.0), ThrowingDoubleStream.generate(() -> 42)
				.limit(3).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testConcat(){
		assertEquals(ListUtil.createList(3.0, 42.0, 1.0, 2.0), ThrowingDoubleStream.concat(
						ThrowingDoubleStream.of(3, 42), ThrowingDoubleStream.of(1, 2))
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testStream(){
		DoubleStream theStream = DoubleStream.of(1, 2, 3);
		ThrowingDoubleStream theThrowingDoubleStream = ThrowingDoubleStream.from(theStream);
		assertEquals(theStream, theThrowingDoubleStream.stream());
	}
	
	@Test
	public void testIterator(){
		DoubleStream theStream = DoubleStream.of(42, 7, 5);
		Iterator<Double> it = ThrowingDoubleStream.from(theStream).iterator();
		int count = 0;
		while(it.hasNext()){
			double value = it.next();
			if(count == 0){
				assertEquals(42.0, value);
			}else if(count == 1){
				assertEquals(7.0, value);
			}else{
				assertEquals(5.0, value);
			}
			count++;
		}
	}
	
	@Test
	public void testSpliterator(){
		DoubleStream theStream = DoubleStream.of(42, 7, 5, 12);
		Spliterator<Double> split = ThrowingDoubleStream.from(theStream).spliterator();
		Spliterator<Double> split2 = split.trySplit();
		List<Double> half1 = new ArrayList<>();
		split.forEachRemaining(half1::add);
		assertEquals(5, half1.get(0));
		assertEquals(12, half1.get(1));
		List<Double> half2 = new ArrayList<>();
		split2.forEachRemaining(half2::add);
		assertEquals(42, half2.get(0));
		assertEquals(7, half2.get(1));
	}
	
	@Test
	public void testSequential(){
		DoubleStream theStream = DoubleStream.of(42, 7, 5, 12);
		assertFalse(ThrowingDoubleStream.from(theStream).sequential().isParallel());
	}
	
	@Test
	public void testParallel(){
		DoubleStream theStream = DoubleStream.of(42, 7, 5, 12);
		assertTrue(ThrowingDoubleStream.from(theStream).parallel().isParallel());
	}
	
	@Test
	public void testUnordered(){
		List<Double> theList = ListUtil.createList(42.0, 7.0, 5.0, 12.0, 39.0);
		List<Double> newList = ThrowingDoubleStream.from(theList.stream().mapToDouble(val -> val)).unordered()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertTrue(newList.containsAll(theList));
	}
	
	@Test
	public void testOnClose(){
		AtomicBoolean ran = new AtomicBoolean(false);
		Runnable closeOp = () -> ran.set(true);
		DoubleStream theStream = DoubleStream.of(42, 7, 5, 12, 39);
		ThrowingDoubleStream.from(theStream).onClose(closeOp).close();
		assertTrue(ran.get());
	}
	
	@Test
	public void testNotThrowingFilter(){
		List<Double> theList = ThrowingDoubleStream.of(0, 1, 2, 3)
				.filter(i -> i < 2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(0.0, 1.0), theList);
	}
	
	@Test
	public void testThrowingFilter(){
		try{
			ThrowingDoubleStream.of(0, 1, 2, 3)
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
		List<Double> list = ThrowingDoubleStream.of(1, 42)
				.map(a -> a + 1)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(2.0, 43.0), list);
	}
	
	@Test
	public void testThrowingMap(){
		try{
			ThrowingDoubleStream.of(1, 42)
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
		List<String> list = ThrowingDoubleStream.of(1, 42)
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
			ThrowingDoubleStream.of(1, 42)
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
		List<Long> list = ThrowingDoubleStream.of(1, 42)
				.mapToLong(Double::longValue)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 42L), list);
	}
	
	@Test
	public void testThrowingMapToLong(){
		try{
			ThrowingDoubleStream.of(1, 42)
					.mapToLong(a -> {
						if(a == 42){
							throw new Exception("derp");
						}else{
							return a.longValue();
						}
					})
					.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			fail();
		}catch(Exception e){
			assertEquals("derp", e.getMessage());
		}
	}
	
	@Test
	public void testNotThrowingMapToInt(){
		List<Integer> list = ThrowingDoubleStream.of(1, 42)
				.mapToInt(Double::intValue)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 42), list);
	}
	
	@Test
	public void testThrowingMapToInt(){
		try{
			ThrowingDoubleStream.of(1, 42)
					.mapToInt(a -> {
						if(a == 42){
							throw new Exception("derp");
						}else{
							return a.intValue();
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
		List<Double> list = ThrowingDoubleStream.of(1, 42)
				.flatMap(a -> {
					if(a == 1){
						return DoubleStream.of(3, 4);
					}else{
						return DoubleStream.of(1, 2);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3.0, 4.0, 1.0, 2.0), list);
	}
	
	@Test
	public void testThrowingFlatMap(){
		try{
			ThrowingDoubleStream.of(1, 42)
					.flatMap(a -> {
						if(a == 42){
							throw new Exception("Bad A");
						}else{
							return DoubleStream.of(1, 2);
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
		List<Double> list = ThrowingDoubleStream.of(1, 42)
				.mapMulti((a, consumer) -> {
					if(a == 42){
						consumer.accept(1.0);
						consumer.accept(2.0);
					}else{
						consumer.accept(3.0);
						consumer.accept(4.0);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3.0, 4.0, 1.0, 2.0), list);
	}
	
	@Test
	public void testThrowingMapMulti(){
		try{
			ThrowingDoubleStream.of(1, 42)
					.mapMulti((a, consumer) -> {
						if(a == 42){
							throw new Exception("Bad A");
						}else{
							consumer.accept(3.0);
							consumer.accept(4.0);
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
		List<Double> list = ThrowingDoubleStream.of(1, 2, 3, 2)
				.distinct()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.0, 2.0, 3.0), list);
	}
	
	@Test
	public void testSorted(){
		List<Double> list = ThrowingDoubleStream.of(3, 1, 2)
				.sorted()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.0, 2.0, 3.0), list);
	}
	
	@Test
	public void testPeek(){
		List<Double> list = new ArrayList<>();
		ThrowingDoubleStream.of(1, 42)
				.peek(list::add)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.0, 42.0), list);
	}
	
	@Test
	public void testPeekThrowing(){
		try{
			ThrowingDoubleStream.of(1, 42)
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
		List<Double> list = ThrowingDoubleStream.of(1, 2, 3)
				.limit(2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.0, 2.0), list);
	}
	
	@Test
	public void testSkip(){
		List<Double> list = ThrowingDoubleStream.of(1, 2, 3)
				.skip(2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3.0), list);
	}
	
	@Test
	public void testTakeWhile(){
		List<Double> list = ThrowingDoubleStream.of(1, 2, 42, 43, 5)
				.takeWhile(i -> i < 43)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.0, 2.0, 42.0), list);
	}
	
	@Test
	public void testTakeWhileThrowing(){
		try{
			ThrowingDoubleStream.of(1, 2, 42, 43, 5)
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
		List<Double> list = ThrowingDoubleStream.of(1, 2, 42, 43, 5)
				.dropWhile(i -> i < 43)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(43.0, 5.0), list);
	}
	
	@Test
	public void testDropWhileThrowing(){
		try{
			ThrowingDoubleStream.of(1, 2, 42, 43, 5)
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
		List<Double> list = new ArrayList<>();
		ThrowingDoubleStream.of(1, 2, 3)
				.forEach(list::add);
		assertEquals(ListUtil.createList(1.0, 2.0, 3.0), list);
	}
	
	@Test
	public void testForEachThrowing(){
		List<Double> list = new ArrayList<>();
		try{
			ThrowingDoubleStream.of(42, 2, 1)
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
		List<Double> list = new ArrayList<>();
		ThrowingDoubleStream.of(1, 2, 3)
				.forEachOrdered(list::add);
		assertEquals(ListUtil.createList(1.0, 2.0, 3.0), list);
	}
	
	@Test
	public void testForEachOrderedThrowing(){
		List<Double> list = new ArrayList<>();
		try{
			ThrowingDoubleStream.of(42, 2, 1)
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
		double[] array = ThrowingDoubleStream.of(1, 42, 3)
				.toArray();
		assertArrayEquals(new double[]{1, 42, 3}, array);
	}
	
	@Test
	public void testReduceWithIdentity(){
		double result = ThrowingDoubleStream.of(1, 2, 3)
				.reduce(0, Double::sum);
		assertEquals(6, result);
	}
	
	@Test
	public void testReduceWithIdentityThrowing(){
		try{
			ThrowingDoubleStream.of(1, 2, 3)
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
		double result = ThrowingDoubleStream.of(1, 2, 3)
				.reduce(Double::sum).orElse(42);
		assertEquals(6, result);
	}
	
	@Test
	public void testReduceThrowing(){
		try{
			ThrowingDoubleStream.of(1, 2, 3)
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
		List<Double> list = ThrowingDoubleStream.of(1, 2, 3)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.0, 2.0, 3.0), list);
	}
	
	@Test
	public void testCollect3ParamsThrowingSupplier(){
		try{
			ThrowingDoubleStream.of(1, 2, 3)
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
			ThrowingDoubleStream.of(42, 2, 3)
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
			ThrowingDoubleStream.of(1, 2, 3)
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
		double sum = ThrowingDoubleStream.of(1, 2, 42)
				.sum();
		assertEquals(45, sum);
	}
	
	@Test
	public void testMin(){
		double min = ThrowingDoubleStream.of(1, 2, 42)
				.min().orElse(43);
		assertEquals(1, min);
	}
	
	@Test
	public void testMax(){
		double max = ThrowingDoubleStream.of(1, 2, 42)
				.max().orElse(43);
		assertEquals(42, max);
	}
	
	@Test
	public void testCount(){
		long count = ThrowingDoubleStream.of(1, 2, 42)
				.count();
		assertEquals(3.0, count);
	}
	
	@Test
	public void testAverage(){
		double average = ThrowingDoubleStream.of(1, 2, 42)
				.average().orElse(42);
		assertEquals(15.0, average);
	}
	
	@Test
	public void testSummaryStatistics(){
		DoubleSummaryStatistics stats = ThrowingDoubleStream.of(1, 2, 42)
				.summaryStatistics();
		assertEquals(3, stats.getCount());
		assertEquals(15.0, stats.getAverage());
		assertEquals(1, stats.getMin());
		assertEquals(42, stats.getMax());
		assertEquals(45, stats.getSum());
	}
	
	@Test
	public void testAnyMatch(){
		boolean result = ThrowingDoubleStream.of(1, 3, 42)
				.anyMatch(i -> i > 1);
		assertTrue(result);
	}
	
	@Test
	public void testAnyMatchThrowing(){
		try{
			ThrowingDoubleStream.of(42, 3, 1)
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
		boolean result = ThrowingDoubleStream.of(1, 3, 42)
				.allMatch(i -> i > 0);
		assertTrue(result);
	}
	
	@Test
	public void testAllMatchThrowing(){
		try{
			ThrowingDoubleStream.of(42, 3, 1)
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
		boolean result = ThrowingDoubleStream.of(1, 3, 42)
				.noneMatch(i -> i < 0);
		assertTrue(result);
	}
	
	@Test
	public void testNoneMatchThrowing(){
		try{
			ThrowingDoubleStream.of(1, 3, 42)
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
		double result = ThrowingDoubleStream.of(42, 3, 1)
				.findFirst().orElse(5);
		assertEquals(42, result);
	}
	
	@Test
	public void testFindAny(){
		double result = ThrowingDoubleStream.of(42, 3, 1)
				.findAny().orElse(5.0);
		assertEquals(42, result);
	}
	
	@Test
	public void testBoxed(){
		ThrowingStream<Double> stream = ThrowingDoubleStream.of(1, 2, 42)
				.boxed();
		assertEquals(ListUtil.createList(1.0, 2.0, 42.0), stream.collect(Collectors.toList()));
	}
}
