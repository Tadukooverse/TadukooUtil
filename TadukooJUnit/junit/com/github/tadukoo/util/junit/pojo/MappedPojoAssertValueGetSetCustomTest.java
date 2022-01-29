package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer4;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;
import com.github.tadukoo.util.junit.AssertionFailedErrors;
import com.github.tadukoo.util.map.MapUtil;
import com.github.tadukoo.util.pojo.MappedPojo;
import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class is used to test out assertion functions that are extending
 * {@link MappedPojoTest#assertValueGetSet(MappedPojo, String, Object, Object)} to verify that they function properly.
 *
 * @param <V> The type of value being tested
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public abstract class MappedPojoAssertValueGetSetCustomTest<V>{
	private final ThrowingConsumer4<MappedPojo, ThrowingSupplier<V, NoException>,
			ThrowingConsumer<V, NoException>, String, NoException> assertValueGetSetFunc;
	private final String defaultTestKey;
	private final String defaultWrongKey;
	private final V defaultTestValue;
	private final V defaultTestValue2;
	private final V defaultWrongValue;
	
	protected MappedPojoAssertValueGetSetCustomTest(
			ThrowingConsumer4<MappedPojo, ThrowingSupplier<V, NoException>,
					ThrowingConsumer<V, NoException>, String, NoException> assertValueGetSetFunc,
			String defaultTestKey, String defaultWrongKey,
			V defaultTestValue, V defaultTestValue2, V defaultWrongValue){
		this.assertValueGetSetFunc = assertValueGetSetFunc;
		this.defaultTestKey = defaultTestKey;
		this.defaultWrongKey = defaultWrongKey;
		this.defaultTestValue = defaultTestValue;
		this.defaultTestValue2 = defaultTestValue2;
		this.defaultWrongValue = defaultWrongValue;
	}
	
	/**
	 * A good {@link MappedPojo} to use for passing tests
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.1
	 */
	private class MappedPojoA implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoA(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadIsEmpty implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadIsEmpty(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean isEmpty(){
			return false;
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoNullMap implements MappedPojo{
		
		public MappedPojoNullMap(){ }
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return null;
		}
	}
	
	private class MappedPojoNonEmptyMap implements MappedPojo{
		private final Map<String, Object> map = MapUtil.createMap(Pair.of(defaultWrongKey, defaultWrongValue));
		
		public MappedPojoNonEmptyMap(){ }
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public boolean isEmpty(){
			return true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoNullKeys implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoNullKeys(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			return null;
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoNonEmptyKeys implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoNonEmptyKeys(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>();
			keys.add(defaultWrongKey);
			return keys;
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadHasKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasKey(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasKey(String key){
			return false;
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadHasItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasItem(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasItem(String key){
			return false;
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetItem(){
			map = new HashMap<>();
		}
		
		@Override
		public Object getItem(String key){
			return defaultWrongValue;
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeys implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeys(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			if(!keys.isEmpty()){
				return null;
			}else{
				return keys;
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeysExtraKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysExtraKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			if(!keys.isEmpty()){
				keys.add(defaultWrongKey);
			}
			return keys;
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeysWrongKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysWrongKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			if(!keys.isEmpty()){
				Set<String> newKeys = new HashSet<>();
				newKeys.add(defaultWrongKey);
				return newKeys;
			}else{
				return map.keySet();
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetMapExtraItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapExtraItem(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetMapWrongKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongKey(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.remove(defaultTestKey);
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetMapWrongValue implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongValue(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.put(defaultTestKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadHasKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasKey(String key){
			Object val = map.get(key);
			if(val.equals(defaultTestValue2)){
				return false;
			}else{
				return map.containsKey(key);
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadHasItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasItem2(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasItem(String key){
			Object val = map.get(key);
			if(val.equals(defaultTestValue2)){
				return false;
			}else{
				return map.containsKey(key) && map.get(key) != null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetItem2(){
			map = new HashMap<>();
		}
		
		@Override
		public Object getItem(String key){
			Object val = map.get(key);
			if(val.equals(defaultTestValue2)){
				return defaultWrongValue;
			}else{
				return map.get(key);
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeys2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeys2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			Object val = map.get(defaultTestKey);
			if(!keys.isEmpty() && val.equals(defaultTestValue2)){
				return null;
			}else{
				return keys;
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeysExtraKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysExtraKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			Object val = map.get(defaultTestKey);
			if(!keys.isEmpty() && val.equals(defaultTestValue2)){
				keys.add(defaultWrongKey);
			}
			return keys;
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeysWrongKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysWrongKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			Object val = map.get(defaultTestKey);
			if(!keys.isEmpty() && val.equals(defaultTestValue2)){
				Set<String> newKeys = new HashSet<>();
				newKeys.add(defaultWrongKey);
				return newKeys;
			}else{
				return map.keySet();
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetMapExtraItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapExtraItem2(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(defaultTestKey);
			if(!map.isEmpty() && val.equals(defaultTestValue2)){
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetMapWrongKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongKey2(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(defaultTestKey);
			if(!map.isEmpty() && val.equals(defaultTestValue2)){
				map.remove(defaultTestKey);
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetMapWrongValue2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongValue2(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(defaultTestKey);
			if(!map.isEmpty() && val.equals(defaultTestValue2)){
				map.put(defaultTestKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadClear implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadClear(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public void clear(){
			// do nothing
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetter implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetter(){
			map = new HashMap<>();
		}
		
		public V getTest(){
			return defaultWrongValue;
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoHasKeyFalse implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoHasKeyFalse(){
			map = new HashMap<>();
		}
		
		public V getTest(){
			return defaultTestValue;
		}
		
		public void setTest(V value){
			// just don't set it
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoHasItemFalse implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoHasItemFalse(){
			map = new HashMap<>();
		}
		
		public V getTest(){
			return defaultTestValue;
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, null);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoWrongItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoWrongItem(){
			map = new HashMap<>();
		}
		
		public V getTest(){
			return defaultTestValue;
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, defaultWrongValue);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoCustomNullKeys implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomNullKeys(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				return null;
			}else{
				return map.keySet();
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoCustomExtraKey implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomExtraKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				Set<String> keys = new HashSet<>(map.keySet());
				keys.add(defaultWrongKey);
				return keys;
			}else{
				return map.keySet();
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoCustomWrongKey implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomWrongKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				Set<String> keys = new HashSet<>(map.keySet());
				keys.remove(defaultTestKey);
				keys.add(defaultWrongKey);
				return keys;
			}else{
				return map.keySet();
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoCustomExtraMapKey implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomExtraMapKey(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(doBad){
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoCustomWrongMapValue implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomWrongMapValue(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(doBad){
				map.put(defaultTestKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetter2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetter2(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			V val = (V) getItem(defaultTestKey);
			if(defaultTestValue2.equals(val)){
				return defaultWrongValue;
			}else{
				return val;
			}
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoHasKeyFalse2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoHasKeyFalse2(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasKey(String key){
			if(doBad){
				return false;
			}else{
				return map.containsKey(key);
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			if(value.equals(defaultTestValue2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoHasItemFalse2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad;
		
		public MappedPojoHasItemFalse2(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasItem(String key){
			if(doBad){
				return false;
			}else{
				return map.get(key) != null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			if(value.equals(defaultTestValue2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoWrongItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoWrongItem2(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			if(value.equals(defaultTestValue2)){
				setItem(defaultTestKey, defaultWrongValue);
			}else{
				setItem(defaultTestKey, value);
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoCustomNullKeys2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomNullKeys2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				return null;
			}else{
				return map.keySet();
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			if(value.equals(defaultTestValue2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoCustomExtraKey2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomExtraKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				Set<String> keys = new HashSet<>(map.keySet());
				keys.add(defaultWrongKey);
				return keys;
			}else{
				return map.keySet();
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			if(value.equals(defaultTestValue2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoCustomWrongKey2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomWrongKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				Set<String> keys = new HashSet<>(map.keySet());
				keys.remove(defaultTestKey);
				keys.add(defaultWrongKey);
				return keys;
			}else{
				return map.keySet();
			}
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			if(value.equals(defaultTestValue2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoCustomExtraMapKey2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomExtraMapKey2(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			if(value.equals(defaultTestValue2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(doBad){
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoCustomWrongMapValue2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomWrongMapValue2(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unchecked")
		public V getTest(){
			return (V) getItem(defaultTestKey);
		}
		
		public void setTest(V value){
			setItem(defaultTestKey, value);
			if(value.equals(defaultTestValue2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(doBad){
				map.put(defaultTestKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private final MappedPojoA pojoA = new MappedPojoA();
	private final MappedPojoBadIsEmpty pojoBadIsEmpty = new MappedPojoBadIsEmpty();
	private final MappedPojoNullMap pojoNullMap = new MappedPojoNullMap();
	private final MappedPojoNonEmptyMap pojoNonEmptyMap = new MappedPojoNonEmptyMap();
	private final MappedPojoNullKeys pojoNullKeys = new MappedPojoNullKeys();
	private final MappedPojoNonEmptyKeys pojoNonEmptyKeys = new MappedPojoNonEmptyKeys();
	private final MappedPojoBadHasKey pojoBadHasKey = new MappedPojoBadHasKey();
	private final MappedPojoBadHasItem pojoBadHasItem = new MappedPojoBadHasItem();
	private final MappedPojoBadGetItem pojoBadGetItem = new MappedPojoBadGetItem();
	private final MappedPojoBadGetKeys pojoBadGetKeys = new MappedPojoBadGetKeys();
	private final MappedPojoBadGetKeysExtraKey pojoBadGetKeysExtraKey = new MappedPojoBadGetKeysExtraKey();
	private final MappedPojoBadGetKeysWrongKey pojoBadGetKeysWrongKey = new MappedPojoBadGetKeysWrongKey();
	private final MappedPojoBadGetMapExtraItem pojoBadGetMapExtraItem = new MappedPojoBadGetMapExtraItem();
	private final MappedPojoBadGetMapWrongKey pojoBadGetMapWrongKey = new MappedPojoBadGetMapWrongKey();
	private final MappedPojoBadGetMapWrongValue pojoBadGetMapWrongValue = new MappedPojoBadGetMapWrongValue();
	private final MappedPojoBadHasKey2 pojoBadHasKey2 = new MappedPojoBadHasKey2();
	private final MappedPojoBadHasItem2 pojoBadHasItem2 = new MappedPojoBadHasItem2();
	private final MappedPojoBadGetItem2 pojoBadGetItem2 = new MappedPojoBadGetItem2();
	private final MappedPojoBadGetKeys2 pojoBadGetKeys2 = new MappedPojoBadGetKeys2();
	private final MappedPojoBadGetKeysExtraKey2 pojoBadGetKeysExtraKey2 = new MappedPojoBadGetKeysExtraKey2();
	private final MappedPojoBadGetKeysWrongKey2 pojoBadGetKeysWrongKey2 = new MappedPojoBadGetKeysWrongKey2();
	private final MappedPojoBadGetMapExtraItem2 pojoBadGetMapExtraItem2 = new MappedPojoBadGetMapExtraItem2();
	private final MappedPojoBadGetMapWrongKey2 pojoBadGetMapWrongKey2 = new MappedPojoBadGetMapWrongKey2();
	private final MappedPojoBadGetMapWrongValue2 pojoBadGetMapWrongValue2 = new MappedPojoBadGetMapWrongValue2();
	private final MappedPojoBadClear pojoBadClear = new MappedPojoBadClear();
	private final MappedPojoBadGetter pojoBadGetter = new MappedPojoBadGetter();
	private final MappedPojoHasKeyFalse pojoHasKeyFalse = new MappedPojoHasKeyFalse();
	private final MappedPojoHasItemFalse pojoHasItemFalse = new MappedPojoHasItemFalse();
	private final MappedPojoWrongItem pojoWrongItem = new MappedPojoWrongItem();
	private final MappedPojoCustomNullKeys pojoCustomNullKeys = new MappedPojoCustomNullKeys();
	private final MappedPojoCustomExtraKey pojoCustomExtraKey = new MappedPojoCustomExtraKey();
	private final MappedPojoCustomWrongKey pojoCustomWrongKey = new MappedPojoCustomWrongKey();
	private final MappedPojoCustomExtraMapKey pojoCustomExtraMapKey = new MappedPojoCustomExtraMapKey();
	private final MappedPojoCustomWrongMapValue pojoCustomWrongMapValue = new MappedPojoCustomWrongMapValue();
	private final MappedPojoBadGetter2 pojoBadGetter2 = new MappedPojoBadGetter2();
	private final MappedPojoHasKeyFalse2 pojoHasKeyFalse2 = new MappedPojoHasKeyFalse2();
	private final MappedPojoHasItemFalse2 pojoHasItemFalse2 = new MappedPojoHasItemFalse2();
	private final MappedPojoWrongItem2 pojoWrongItem2 = new MappedPojoWrongItem2();
	private final MappedPojoCustomNullKeys2 pojoCustomNullKeys2 = new MappedPojoCustomNullKeys2();
	private final MappedPojoCustomExtraKey2 pojoCustomExtraKey2 = new MappedPojoCustomExtraKey2();
	private final MappedPojoCustomWrongKey2 pojoCustomWrongKey2 = new MappedPojoCustomWrongKey2();
	private final MappedPojoCustomExtraMapKey2 pojoCustomExtraMapKey2 = new MappedPojoCustomExtraMapKey2();
	private final MappedPojoCustomWrongMapValue2 pojoCustomWrongMapValue2 = new MappedPojoCustomWrongMapValue2();
	
	@Test
	public void testAssertValueGetSetPass(){
		assertValueGetSetFunc.accept(pojoA, pojoA::getTest, pojoA::setTest, defaultTestKey);
	}
	
	@Test
	public void testAssertValueGetSetFalseIsEmpty(){
		try{
			assertValueGetSetFunc.accept(pojoBadIsEmpty, pojoBadIsEmpty::getTest, pojoBadIsEmpty::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("pojo was non-empty in empty pojo!",
					AssertionFailedErrors.ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMap(){
		try{
			assertValueGetSetFunc.accept(pojoNullMap, pojoNullMap::getTest, pojoNullMap::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("Map was null in empty pojo!",
					AssertionFailedErrors.ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyMap(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyMap, pojoNonEmptyMap::getTest, pojoNonEmptyMap::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("Map was non-empty in empty pojo!",
					AssertionFailedErrors.ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNullKeys, pojoNullKeys::getTest, pojoNullKeys::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("getKeys() returned null in empty pojo!",
					AssertionFailedErrors.ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyKeys, pojoNonEmptyKeys::getTest, pojoNonEmptyKeys::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("getKeys() was not empty in empty pojo!",
					AssertionFailedErrors.ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey, pojoBadHasKey::getTest, pojoBadHasKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem, pojoBadHasItem::getTest, pojoBadHasItem::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFails(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem, pojoBadGetItem::getTest, pojoBadGetItem::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue ,defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys, pojoBadGetKeys::getTest, pojoBadGetKeys::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey, pojoBadGetKeysExtraKey::getTest,
					pojoBadGetKeysExtraKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey, pojoBadGetKeysWrongKey::getTest,
					pojoBadGetKeysWrongKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestKey, defaultWrongKey), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem, pojoBadGetMapExtraItem::getTest,
					pojoBadGetMapExtraItem::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey, pojoBadGetMapWrongKey::getTest,
					pojoBadGetMapWrongKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue, pojoBadGetMapWrongValue::getTest,
					pojoBadGetMapWrongValue::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey2, pojoBadHasKey2::getTest, pojoBadHasKey2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem2, pojoBadHasItem2::getTest, pojoBadHasItem2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem2, pojoBadGetItem2::getTest, pojoBadGetItem2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys2, pojoBadGetKeys2::getTest, pojoBadGetKeys2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey2, pojoBadGetKeysExtraKey2::getTest,
					pojoBadGetKeysExtraKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey2, pojoBadGetKeysWrongKey2::getTest,
					pojoBadGetKeysWrongKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestKey, defaultWrongKey), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem2, pojoBadGetMapExtraItem2::getTest,
					pojoBadGetMapExtraItem2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey2, pojoBadGetMapWrongKey2::getTest,
					pojoBadGetMapWrongKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue2, pojoBadGetMapWrongValue2::getTest,
					pojoBadGetMapWrongValue2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClear(){
		try{
			assertValueGetSetFunc.accept(pojoBadClear, pojoBadClear::getTest, pojoBadClear::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("pojo was non-empty in empty pojo!",
					AssertionFailedErrors.ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadGetter(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetter, pojoBadGetter::getTest, pojoBadGetter::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalse(){
		try{
			assertValueGetSetFunc.accept(pojoHasKeyFalse, pojoHasKeyFalse::getTest, pojoHasKeyFalse::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalse(){
		try{
			assertValueGetSetFunc.accept(pojoHasItemFalse, pojoHasItemFalse::getTest, pojoHasItemFalse::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongValue(){
		try{
			assertValueGetSetFunc.accept(pojoWrongItem, pojoWrongItem::getTest, pojoWrongItem::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeys(){
		try{
			assertValueGetSetFunc.accept(pojoCustomNullKeys, pojoCustomNullKeys::getTest, pojoCustomNullKeys::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKey(){
		try{
			assertValueGetSetFunc.accept(pojoCustomExtraKey, pojoCustomExtraKey::getTest, pojoCustomExtraKey::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKey(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongKey, pojoCustomWrongKey::getTest, pojoCustomWrongKey::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestKey, defaultWrongKey), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraMapKey(){
		try{
			assertValueGetSetFunc.accept(pojoCustomExtraMapKey, pojoCustomExtraMapKey::getTest,
					pojoCustomExtraMapKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValue(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongMapValue, pojoCustomWrongMapValue::getTest,
					pojoCustomWrongMapValue::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadGetter2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetter2, pojoBadGetter2::getTest, pojoBadGetter2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalse2(){
		try{
			assertValueGetSetFunc.accept(pojoHasKeyFalse2, pojoHasKeyFalse2::getTest, pojoHasKeyFalse2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalse2(){
		try{
			assertValueGetSetFunc.accept(pojoHasItemFalse2, pojoHasItemFalse2::getTest, pojoHasItemFalse2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongValue2(){
		try{
			assertValueGetSetFunc.accept(pojoWrongItem2, pojoWrongItem2::getTest, pojoWrongItem2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeys2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomNullKeys2, pojoCustomNullKeys2::getTest, pojoCustomNullKeys2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKey2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomExtraKey2, pojoCustomExtraKey2::getTest, pojoCustomExtraKey2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKey2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongKey2, pojoCustomWrongKey2::getTest, pojoCustomWrongKey2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestKey, defaultWrongKey), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraMapKey2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomExtraMapKey2, pojoCustomExtraMapKey2::getTest,
					pojoCustomExtraMapKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValue2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongMapValue2, pojoCustomWrongMapValue2::getTest,
					pojoCustomWrongMapValue2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
}
