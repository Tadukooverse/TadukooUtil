package com.github.tadukoo.util.stream;

import com.github.tadukoo.util.ListUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingLongStreamTest{
	
	@Test
	public void testEmpty(){
		assertEquals(0, ThrowingLongStream.empty().count());
	}
	
	@Test
	public void testOf1Param(){
		assertEquals(ListUtil.createList(42L), ThrowingLongStream.of(42)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testOfMultipleParams(){
		assertEquals(ListUtil.createList(42L, 27L, 1L, 2L), ThrowingLongStream.of(42, 27, 1, 2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testIterateInfinite(){
		assertEquals(ListUtil.createList(0L, 2L, 4L, 6L, 8L), ThrowingLongStream.iterate(0, n -> n + 2)
				.limit(5).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testIterateFinite(){
		assertEquals(ListUtil.createList(0L, 1L, 2L, 3L, 4L, 5L), ThrowingLongStream.iterate(0, n -> n <= 5, n -> n + 1)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testGenerate(){
		assertEquals(ListUtil.createList(42L, 42L, 42L), ThrowingLongStream.generate(() -> 42)
				.limit(3).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testRange(){
		assertEquals(ListUtil.createList(1L, 2L), ThrowingLongStream.range(1, 3)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testRangeClosed(){
		assertEquals(ListUtil.createList(1L, 2L, 3L), ThrowingLongStream.rangeClosed(1, 3)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testConcat(){
		assertEquals(ListUtil.createList(3L, 42L, 1L, 2L), ThrowingLongStream.concat(
						ThrowingLongStream.of(3, 42), ThrowingLongStream.of(1, 2))
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testStream(){
		LongStream theStream = LongStream.of(1, 2, 3);
		ThrowingLongStream theThrowingLongStream = ThrowingLongStream.from(theStream);
		assertEquals(theStream, theThrowingLongStream.stream());
	}
	
	@Test
	public void testIterator(){
		LongStream theStream = LongStream.of(42, 7, 5);
		Iterator<Long> it = ThrowingLongStream.from(theStream).iterator();
		int count = 0;
		while(it.hasNext()){
			long value = it.next();
			if(count == 0){
				assertEquals(42L, value);
			}else if(count == 1){
				assertEquals(7L, value);
			}else{
				assertEquals(5L, value);
			}
			count++;
		}
	}
	
	@Test
	public void testSpliterator(){
		LongStream theStream = LongStream.of(42, 7, 5, 12);
		Spliterator<Long> split = ThrowingLongStream.from(theStream).spliterator();
		Spliterator<Long> split2 = split.trySplit();
		List<Long> half1 = new ArrayList<>();
		split.forEachRemaining(half1::add);
		assertEquals(5, half1.get(0));
		assertEquals(12, half1.get(1));
		List<Long> half2 = new ArrayList<>();
		split2.forEachRemaining(half2::add);
		assertEquals(42, half2.get(0));
		assertEquals(7, half2.get(1));
	}
	
	@Test
	public void testSequential(){
		LongStream theStream = LongStream.of(42, 7, 5, 12);
		assertFalse(ThrowingLongStream.from(theStream).sequential().isParallel());
	}
	
	@Test
	public void testParallel(){
		LongStream theStream = LongStream.of(42, 7, 5, 12);
		assertTrue(ThrowingLongStream.from(theStream).parallel().isParallel());
	}
	
	@Test
	public void testUnordered(){
		List<Long> theList = ListUtil.createList(42L, 7L, 5L, 12L, 39L);
		List<Long> newList = ThrowingLongStream.from(theList.stream().mapToLong(val -> val)).unordered()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertTrue(newList.containsAll(theList));
	}
	
	@Test
	public void testOnClose(){
		AtomicBoolean ran = new AtomicBoolean(false);
		Runnable closeOp = () -> ran.set(true);
		LongStream theStream = LongStream.of(42, 7, 5, 12, 39);
		ThrowingLongStream.from(theStream).onClose(closeOp).close();
		assertTrue(ran.get());
	}
	
	@Test
	public void testNotThrowingFilter(){
		List<Long> theList = ThrowingLongStream.of(0, 1, 2, 3)
				.filter(i -> i < 2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(0L, 1L), theList);
	}
	
	@Test
	public void testThrowingFilter(){
		try{
			ThrowingLongStream.of(0, 1, 2, 3)
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
		List<Long> list = ThrowingLongStream.of(1, 42)
				.map(a -> a + 1)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(2L, 43L), list);
	}
	
	@Test
	public void testThrowingMap(){
		try{
			ThrowingLongStream.of(1, 42)
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
		List<String> list = ThrowingLongStream.of(1, 42)
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
			ThrowingLongStream.of(1, 42)
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
	public void testNotThrowingMapToInt(){
		List<Integer> list = ThrowingLongStream.of(1, 42)
				.mapToInt(Long::intValue)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1, 42), list);
	}
	
	@Test
	public void testThrowingMapToInt(){
		try{
			ThrowingLongStream.of(1, 42)
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
	public void testNotThrowingMapToDouble(){
		List<Double> list = ThrowingLongStream.of(1, 42)
				.mapToDouble(a -> (double) a)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1.0, 42.0), list);
	}
	
	@Test
	public void testThrowingMapToDouble(){
		try{
			ThrowingLongStream.of(1, 42)
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
		List<Long> list = ThrowingLongStream.of(1, 42)
				.flatMap(a -> {
					if(a == 1){
						return LongStream.of(3, 4);
					}else{
						return LongStream.of(1, 2);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3L, 4L, 1L, 2L), list);
	}
	
	@Test
	public void testThrowingFlatMap(){
		try{
			ThrowingLongStream.of(1, 42)
					.flatMap(a -> {
						if(a == 42){
							throw new Exception("Bad A");
						}else{
							return LongStream.of(1, 2);
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
		List<Long> list = ThrowingLongStream.of(1, 42)
				.mapMulti((a, consumer) -> {
					if(a == 42){
						consumer.accept(1L);
						consumer.accept(2L);
					}else{
						consumer.accept(3L);
						consumer.accept(4L);
					}
				})
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3L, 4L, 1L, 2L), list);
	}
	
	@Test
	public void testThrowingMapMulti(){
		try{
			ThrowingLongStream.of(1, 42)
					.mapMulti((a, consumer) -> {
						if(a == 42){
							throw new Exception("Bad A");
						}else{
							consumer.accept(3L);
							consumer.accept(4L);
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
		List<Long> list = ThrowingLongStream.of(1, 2, 3, 2)
				.distinct()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 2L, 3L), list);
	}
	
	@Test
	public void testSorted(){
		List<Long> list = ThrowingLongStream.of(3, 1, 2)
				.sorted()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 2L, 3L), list);
	}
	
	@Test
	public void testPeek(){
		List<Long> list = new ArrayList<>();
		ThrowingLongStream.of(1, 42)
				.peek(list::add)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 42L), list);
	}
	
	@Test
	public void testPeekThrowing(){
		try{
			ThrowingLongStream.of(1, 42)
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
		List<Long> list = ThrowingLongStream.of(1, 2, 3)
				.limit(2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 2L), list);
	}
	
	@Test
	public void testSkip(){
		List<Long> list = ThrowingLongStream.of(1, 2, 3)
				.skip(2)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(3L), list);
	}
	
	@Test
	public void testTakeWhile(){
		List<Long> list = ThrowingLongStream.of(1, 2, 42, 43, 5)
				.takeWhile(i -> i < 43)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 2L, 42L), list);
	}
	
	@Test
	public void testTakeWhileThrowing(){
		try{
			ThrowingLongStream.of(1, 2, 42, 43, 5)
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
		List<Long> list = ThrowingLongStream.of(1, 2, 42, 43, 5)
				.dropWhile(i -> i < 43)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(43L, 5L), list);
	}
	
	@Test
	public void testDropWhileThrowing(){
		try{
			ThrowingLongStream.of(1, 2, 42, 43, 5)
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
		List<Long> list = new ArrayList<>();
		ThrowingLongStream.of(1, 2, 3)
				.forEach(list::add);
		assertEquals(ListUtil.createList(1L, 2L, 3L), list);
	}
	
	@Test
	public void testForEachThrowing(){
		List<Long> list = new ArrayList<>();
		try{
			ThrowingLongStream.of(42, 2, 1)
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
		List<Long> list = new ArrayList<>();
		ThrowingLongStream.of(1, 2, 3)
				.forEachOrdered(list::add);
		assertEquals(ListUtil.createList(1L, 2L, 3L), list);
	}
	
	@Test
	public void testForEachOrderedThrowing(){
		List<Long> list = new ArrayList<>();
		try{
			ThrowingLongStream.of(42, 2, 1)
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
		long[] array = ThrowingLongStream.of(1, 42, 3)
				.toArray();
		assertArrayEquals(new long[]{1, 42, 3}, array);
	}
	
	@Test
	public void testReduceWithIdentity(){
		long result = ThrowingLongStream.of(1, 2, 3)
				.reduce(0, Long::sum);
		assertEquals(6, result);
	}
	
	@Test
	public void testReduceWithIdentityThrowing(){
		try{
			ThrowingLongStream.of(1, 2, 3)
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
		long result = ThrowingLongStream.of(1, 2, 3)
				.reduce(Long::sum).orElse(42);
		assertEquals(6, result);
	}
	
	@Test
	public void testReduceThrowing(){
		try{
			ThrowingLongStream.of(1, 2, 3)
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
		List<Long> list = ThrowingLongStream.of(1, 2, 3)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		assertEquals(ListUtil.createList(1L, 2L, 3L), list);
	}
	
	@Test
	public void testCollect3ParamsThrowingSupplier(){
		try{
			ThrowingLongStream.of(1, 2, 3)
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
			ThrowingLongStream.of(42, 2, 3)
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
			ThrowingLongStream.of(1, 2, 3)
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
		long sum = ThrowingLongStream.of(1, 2, 42)
				.sum();
		assertEquals(45, sum);
	}
	
	@Test
	public void testMin(){
		long min = ThrowingLongStream.of(1, 2, 42)
				.min().orElse(43);
		assertEquals(1, min);
	}
	
	@Test
	public void testMax(){
		long max = ThrowingLongStream.of(1, 2, 42)
				.max().orElse(43);
		assertEquals(42, max);
	}
	
	@Test
	public void testCount(){
		long count = ThrowingLongStream.of(1, 2, 42)
				.count();
		assertEquals(3L, count);
	}
	
	@Test
	public void testAverage(){
		double average = ThrowingLongStream.of(1, 2, 42)
				.average().orElse(42);
		assertEquals(15.0, average);
	}
	
	@Test
	public void testSummaryStatistics(){
		LongSummaryStatistics stats = ThrowingLongStream.of(1, 2, 42)
				.summaryStatistics();
		assertEquals(3, stats.getCount());
		assertEquals(15.0, stats.getAverage());
		assertEquals(1, stats.getMin());
		assertEquals(42, stats.getMax());
		assertEquals(45, stats.getSum());
	}
	
	@Test
	public void testAnyMatch(){
		boolean result = ThrowingLongStream.of(1, 3, 42)
				.anyMatch(i -> i > 1);
		assertTrue(result);
	}
	
	@Test
	public void testAnyMatchThrowing(){
		try{
			ThrowingLongStream.of(42, 3, 1)
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
		boolean result = ThrowingLongStream.of(1, 3, 42)
				.allMatch(i -> i > 0);
		assertTrue(result);
	}
	
	@Test
	public void testAllMatchThrowing(){
		try{
			ThrowingLongStream.of(42, 3, 1)
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
		boolean result = ThrowingLongStream.of(1, 3, 42)
				.noneMatch(i -> i < 0);
		assertTrue(result);
	}
	
	@Test
	public void testNoneMatchThrowing(){
		try{
			ThrowingLongStream.of(1, 3, 42)
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
		long result = ThrowingLongStream.of(42, 3, 1)
				.findFirst().orElse(5);
		assertEquals(42, result);
	}
	
	@Test
	public void testFindAny(){
		long result = ThrowingLongStream.of(42, 3, 1)
				.findAny().orElse(5L);
		assertEquals(42, result);
	}
	
	@Test
	public void testAsDoubleStream(){
		ThrowingDoubleStream stream = ThrowingLongStream.of(1, 2, 42)
				.asDoubleStream();
		assertEquals(ListUtil.createList(1.0, 2.0, 42.0), stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
	}
	
	@Test
	public void testBoxed(){
		ThrowingStream<Long> stream = ThrowingLongStream.of(1, 2, 42)
				.boxed();
		assertEquals(ListUtil.createList(1L, 2L, 42L), stream.collect(Collectors.toList()));
	}
}
