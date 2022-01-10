package com.github.tadukoo.util.junit;

import com.github.tadukoo.util.junit.constant.DefaultTestValues;
import com.github.tadukoo.util.StringUtil;
import com.github.tadukoo.util.map.MapUtil;
import com.github.tadukoo.util.pojo.MappedPojo;
import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.ASSERT_NOT_NULL_ERROR;
import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.ASSERT_TRUE_ERROR;
import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.buildAssertError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MappedPojoTestTest implements DefaultTestValues{
	
	/**
	 * A good {@link MappedPojo} to use for passing tests
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.1
	 */
	private static class MappedPojoA implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoA(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unused")
		public MappedPojoA(MappedPojo pojo){
			map = pojo.getMap();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class BadMappedPojoA implements MappedPojo{
		private final Map<String, Object> map;
		
		public BadMappedPojoA(MappedPojo pojo){
			map = pojo.getMap();
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class BadMappedPojoA2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public BadMappedPojoA2(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class BadMappedPojoC implements MappedPojo{
		
		@SuppressWarnings("unused")
		public BadMappedPojoC(){
			throw new IllegalArgumentException("Me a bad boy");
		}
		
		@SuppressWarnings("unused")
		public BadMappedPojoC(MappedPojo pojo){
			throw new IllegalArgumentException("Me a bad boy");
		}
		
		@Override
		public Map<String, Object> getMap(){
			return null;
		}
	}
	
	private static abstract class AbstractMappedPojoA implements MappedPojo{
		
		@SuppressWarnings("unused")
		public AbstractMappedPojoA(){ }
		
		@SuppressWarnings("unused")
		public AbstractMappedPojoA(MappedPojo pojo){ }
	}
	
	private static class MappedPojoBadIsEmpty implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadIsEmpty(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean isEmpty(){
			return false;
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoNullMap implements MappedPojo{
		
		public MappedPojoNullMap(){ }
		
		@SuppressWarnings("unused")
		public MappedPojoNullMap(MappedPojo pojo){ }
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return null;
		}
	}
	
	private static class MappedPojoNonEmptyMap implements MappedPojo{
		private final Map<String, Object> map = MapUtil.createMap(Pair.of(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING));
		
		public MappedPojoNonEmptyMap(){ }
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoNullKeys implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoNullKeys(){
			map = new HashMap<>();
		}
		
		@SuppressWarnings("unused")
		public MappedPojoNullKeys(MappedPojo pojo){
			map = pojo.getMap();
		}
		
		@Override
		public Set<String> getKeys(){
			return null;
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoNonEmptyKeys implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoNonEmptyKeys(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>();
			keys.add(DEFAULT_WRONG_KEY);
			return keys;
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadHasKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasKey(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasKey(String key){
			return false;
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadHasItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasItem(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasItem(String key){
			return false;
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetItem(){
			map = new HashMap<>();
		}
		
		@Override
		public Object getItem(String key){
			return DEFAULT_WRONG_STRING;
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetKeys implements MappedPojo{
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
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetKeysExtraKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysExtraKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			if(!keys.isEmpty()){
				keys.add(DEFAULT_WRONG_KEY);
			}
			return keys;
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetKeysWrongKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysWrongKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			if(!keys.isEmpty()){
				Set<String> newKeys = new HashSet<>();
				newKeys.add(DEFAULT_WRONG_KEY);
				return newKeys;
			}else{
				return map.keySet();
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetMapExtraItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapExtraItem(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoBadGetMapWrongKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongKey(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.remove(DEFAULT_TEST_KEY);
				map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoBadGetMapWrongValue implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongValue(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.put(DEFAULT_TEST_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoBadHasKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasKey(String key){
			Object val = map.get(key);
			if((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
					(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2)){
				return false;
			}else{
				return map.containsKey(key);
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadHasItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasItem2(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasItem(String key){
			Object val = map.get(key);
			if((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
					(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2)){
				return false;
			}else{
				return map.containsKey(key) && map.get(key) != null;
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetItem2(){
			map = new HashMap<>();
		}
		
		@Override
		public Object getItem(String key){
			Object val = map.get(key);
			if((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
					(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2)){
				return DEFAULT_WRONG_STRING;
			}else{
				return map.get(key);
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetKeys2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeys2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			Object val = map.get(DEFAULT_TEST_KEY);
			if(!keys.isEmpty() &&
					((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
					(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2))){
				return null;
			}else{
				return keys;
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetKeysExtraKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysExtraKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			Object val = map.get(DEFAULT_TEST_KEY);
			if(!keys.isEmpty() &&
					((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
					(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2))){
				keys.add(DEFAULT_WRONG_KEY);
			}
			return keys;
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetKeysWrongKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysWrongKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			Object val = map.get(DEFAULT_TEST_KEY);
			if(!keys.isEmpty() &&
					((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
					(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2))){
				Set<String> newKeys = new HashSet<>();
				newKeys.add(DEFAULT_WRONG_KEY);
				return newKeys;
			}else{
				return map.keySet();
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetMapExtraItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapExtraItem2(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(DEFAULT_TEST_KEY);
			if(!map.isEmpty() &&
					((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
					(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2))){
				map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoBadGetMapWrongKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongKey2(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(DEFAULT_TEST_KEY);
			if(!map.isEmpty() &&
					((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
							(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2))){
				map.remove(DEFAULT_TEST_KEY);
				map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoBadGetMapWrongValue2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongValue2(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(DEFAULT_TEST_KEY);
			if(!map.isEmpty() &&
					((val instanceof String && StringUtil.equals((String) val, DEFAULT_TEST_STRING_2)) ||
							(val instanceof Double && (Double) val == DEFAULT_TEST_DOUBLE_2))){
				map.put(DEFAULT_TEST_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoBadClear implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadClear(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
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
	
	private static class MappedPojoBadGetter implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetter(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return DEFAULT_WRONG_STRING;
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoHasKeyFalse implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoHasKeyFalse(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return DEFAULT_TEST_STRING;
		}
		
		public void setTest(String value){
			// just don't set it
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoHasItemFalse implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoHasItemFalse(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return DEFAULT_TEST_STRING;
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, null);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoWrongItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoWrongItem(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return DEFAULT_TEST_STRING;
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, DEFAULT_WRONG_STRING);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoCustomNullKeys implements MappedPojo{
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
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoCustomExtraKey implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomExtraKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				Set<String> keys = new HashSet<>(map.keySet());
				keys.add(DEFAULT_WRONG_KEY);
				return keys;
			}else{
				return map.keySet();
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoCustomWrongKey implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomWrongKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				Set<String> keys = new HashSet<>(map.keySet());
				keys.remove(DEFAULT_TEST_KEY);
				keys.add(DEFAULT_WRONG_KEY);
				return keys;
			}else{
				return map.keySet();
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoCustomExtraMapKey implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomExtraMapKey(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(doBad){
				map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoCustomWrongMapValue implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomWrongMapValue(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			doBad = true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(doBad){
				map.put(DEFAULT_TEST_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoBadGetter2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetter2(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			String val = (String) getItem(DEFAULT_TEST_KEY);
			if(DEFAULT_TEST_STRING_2.equals(val)){
				return DEFAULT_WRONG_STRING;
			}else{
				return val;
			}
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoHasKeyFalse2 implements MappedPojo{
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
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			if(value.equals(DEFAULT_TEST_STRING_2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoHasItemFalse2 implements MappedPojo{
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
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			if(value.equals(DEFAULT_TEST_STRING_2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoWrongItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoWrongItem2(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			if(value.equals(DEFAULT_TEST_STRING_2)){
				setItem(DEFAULT_TEST_KEY, DEFAULT_WRONG_STRING);
			}else{
				setItem(DEFAULT_TEST_KEY, value);
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoCustomNullKeys2 implements MappedPojo{
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
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			if(value.equals(DEFAULT_TEST_STRING_2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoCustomExtraKey2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomExtraKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				Set<String> keys = new HashSet<>(map.keySet());
				keys.add(DEFAULT_WRONG_KEY);
				return keys;
			}else{
				return map.keySet();
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			if(value.equals(DEFAULT_TEST_STRING_2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoCustomWrongKey2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomWrongKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			if(doBad){
				Set<String> keys = new HashSet<>(map.keySet());
				keys.remove(DEFAULT_TEST_KEY);
				keys.add(DEFAULT_WRONG_KEY);
				return keys;
			}else{
				return map.keySet();
			}
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			if(value.equals(DEFAULT_TEST_STRING_2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoCustomExtraMapKey2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomExtraMapKey2(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			if(value.equals(DEFAULT_TEST_STRING_2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(doBad){
				map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoCustomWrongMapValue2 implements MappedPojo{
		private final Map<String, Object> map;
		private boolean doBad = false;
		
		public MappedPojoCustomWrongMapValue2(){
			map = new HashMap<>();
		}
		
		public String getTest(){
			return (String) getItem(DEFAULT_TEST_KEY);
		}
		
		public void setTest(String value){
			setItem(DEFAULT_TEST_KEY, value);
			if(value.equals(DEFAULT_TEST_STRING_2)){
				doBad = true;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(doBad){
				map.put(DEFAULT_TEST_KEY, DEFAULT_WRONG_STRING);
			}
			return map;
		}
	}
	
	private static class MappedPojoBadValue1 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadValue1(MappedPojo pojo){
			map = pojo.getMap();
			map.put(DEFAULT_TEST_KEY, DEFAULT_WRONG_DOUBLE);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadValue2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadValue2(MappedPojo pojo){
			map = pojo.getMap();
			map.put(DEFAULT_TEST_KEY_2, DEFAULT_WRONG_STRING);
		}
		
		@Override
		public Map<String, Object> getMap(){
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
	
	/*
	 * Assert Empty Constructor
	 */
	
	@Test
	public void testAssertEmptyConstructorPass()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		MappedPojoTest.assertEmptyConstructor(MappedPojoA.class);
	}
	
	@Test
	public void testAssertEmptyConstructorMissingMethod()
			throws InvocationTargetException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertEmptyConstructor(BadMappedPojoA.class);
			fail();
		}catch(NoSuchMethodException e){
			assertEquals("com.github.tadukoo.util.junit.MappedPojoTestTest$BadMappedPojoA.<init>()", e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorPrivateMethod()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException{
		try{
			MappedPojoTest.assertEmptyConstructor(BadMappedPojoB.class);
			fail();
		}catch(IllegalAccessException e){
			assertEquals("class " + MappedPojoTest.class.getCanonicalName() + " cannot access a member of " +
					"class " + BadMappedPojoB.class.getCanonicalName() + " with modifiers \"private\"", e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorThrows()
			throws NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertEmptyConstructor(BadMappedPojoC.class);
			fail();
		}catch(InvocationTargetException e){
			assertNull(e.getMessage());
			Throwable t = e.getTargetException();
			assertTrue(t instanceof IllegalArgumentException);
			assertEquals("Me a bad boy", t.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorAbstract()
			throws InvocationTargetException, NoSuchMethodException, IllegalAccessException{
		try{
			MappedPojoTest.assertEmptyConstructor(AbstractMappedPojoA.class);
			fail();
		}catch(InstantiationException e){
			assertNull(e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorFalseIsEmpty()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertEmptyConstructor(MappedPojoBadIsEmpty.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNullMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertEmptyConstructor(MappedPojoNullMap.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNonEmptyMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertEmptyConstructor(MappedPojoNonEmptyMap.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNullKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertEmptyConstructor(MappedPojoNullKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNonEmptyKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertEmptyConstructor(MappedPojoNonEmptyKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	/*
	 * Assert Pojo Constructor
	 */
	
	@Test
	public void testAssertPojoConstructorPass()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		MappedPojoTest.assertPojoConstructor(MappedPojoA.class);
	}
	
	@Test
	public void testAssertPojoConstructorMissingMethod()
			throws InvocationTargetException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertPojoConstructor(BadMappedPojoA2.class);
			fail();
		}catch(NoSuchMethodException e){
			assertEquals("com.github.tadukoo.util.junit.MappedPojoTestTest$BadMappedPojoA2.<init>" +
					"(com.github.tadukoo.util.pojo.MappedPojo)", e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorPrivateMethod()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException{
		try{
			MappedPojoTest.assertPojoConstructor(BadMappedPojoB.class);
			fail();
		}catch(IllegalAccessException e){
			assertEquals("class " + MappedPojoTest.class.getCanonicalName() + " cannot access a member of " +
					"class " + BadMappedPojoB.class.getCanonicalName() + " with modifiers \"private\"", e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorThrows()
			throws NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertPojoConstructor(BadMappedPojoC.class);
			fail();
		}catch(InvocationTargetException e){
			assertNull(e.getMessage());
			Throwable t = e.getTargetException();
			assertTrue(t instanceof IllegalArgumentException);
			assertEquals("Me a bad boy", t.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorAbstract()
			throws InvocationTargetException, NoSuchMethodException, IllegalAccessException{
		try{
			MappedPojoTest.assertPojoConstructor(AbstractMappedPojoA.class);
			fail();
		}catch(InstantiationException e){
			assertNull(e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorBadValue1()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertPojoConstructor(MappedPojoBadValue1.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_DOUBLE, DEFAULT_WRONG_DOUBLE), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorBadValue2()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertPojoConstructor(MappedPojoBadValue2.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorNullKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			MappedPojoTest.assertPojoConstructor(MappedPojoNullKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	/*
	 * Assert Empty Pojo
	 */
	
	@Test
	public void testAssertEmptyPojoPass(){
		MappedPojoTest.assertEmptyPojo(pojoA);
	}
	
	@Test
	public void testAssertEmptyPojoFalseIsEmpty(){
		try{
			MappedPojoTest.assertEmptyPojo(pojoBadIsEmpty);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNullMap(){
		try{
			MappedPojoTest.assertEmptyPojo(pojoNullMap);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNonEmptyMap(){
		try{
			MappedPojoTest.assertEmptyPojo(pojoNonEmptyMap);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNullKeys(){
		try{
			MappedPojoTest.assertEmptyPojo(pojoNullKeys);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNonEmptyKeys(){
		try{
			MappedPojoTest.assertEmptyPojo(pojoNonEmptyKeys);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	/*
	 * AssertValueGetSet
	 */
	
	@Test
	public void testAssertValueGetSetPass(){
		MappedPojoTest.assertValueGetSet(pojoA, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
	}
	
	@Test
	public void testAssertValueGetSetFalseIsEmpty(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadIsEmpty, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMap(){
		try{
			MappedPojoTest.assertValueGetSet(pojoNullMap, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyMap(){
		try{
			MappedPojoTest.assertValueGetSet(pojoNonEmptyMap, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeys(){
		try{
			MappedPojoTest.assertValueGetSet(pojoNullKeys, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyKeys(){
		try{
			MappedPojoTest.assertValueGetSet(pojoNonEmptyKeys, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalse(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadHasKey, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalse(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadHasItem, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFails(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetItem, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING ,DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun1(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetKeys, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun1(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetKeysExtraKey, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun1(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetKeysWrongKey, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun1(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetMapExtraItem, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun1(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetMapWrongKey, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun1(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetMapWrongValue, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadHasKey2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadHasItem2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetItem2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetKeys2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetKeysExtraKey2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetKeysWrongKey2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetMapExtraItem2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetMapWrongKey2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun2(){
		try{
			MappedPojoTest.assertValueGetSet(pojoBadGetMapWrongValue2, DEFAULT_TEST_KEY,
					DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	/*
	 * Assert Value Get/Set Custom
	 */
	@Test
	public void testAssertValueGetSetCustomPass(){
		MappedPojoTest.assertValueGetSetCustom(pojoA, pojoA::getTest, pojoA::setTest, DEFAULT_TEST_KEY,
				DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
	}
	
	@Test
	public void testAssertValueGetSetCustomFalseIsEmpty(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadIsEmpty, pojoBadIsEmpty::getTest, pojoBadIsEmpty::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullMap(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoNullMap, pojoNullMap::getTest, pojoNullMap::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNonEmptyMap(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoNonEmptyMap, pojoNonEmptyMap::getTest, pojoNonEmptyMap::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeys(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoNullKeys, pojoNullKeys::getTest, pojoNullKeys::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNonEmptyKeys(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoNonEmptyKeys, pojoNonEmptyKeys::getTest, pojoNonEmptyKeys::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalse(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadHasKey, pojoBadHasKey::getTest, pojoBadHasKey::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalse(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadHasItem, pojoBadHasItem::getTest, pojoBadHasItem::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomGetItemFails(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetItem, pojoBadGetItem::getTest, pojoBadGetItem::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING ,DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeysRun1(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetKeys, pojoBadGetKeys::getTest, pojoBadGetKeys::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKeysRun1(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetKeysExtraKey, pojoBadGetKeysExtraKey::getTest,
					pojoBadGetKeysExtraKey::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKeyRun1(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetKeysWrongKey, pojoBadGetKeysWrongKey::getTest,
					pojoBadGetKeysWrongKey::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraItemRun1(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetMapExtraItem, pojoBadGetMapExtraItem::getTest,
					pojoBadGetMapExtraItem::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapKeyRun1(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetMapWrongKey, pojoBadGetMapWrongKey::getTest,
					pojoBadGetMapWrongKey::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValueRun1(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetMapWrongValue, pojoBadGetMapWrongValue::getTest,
					pojoBadGetMapWrongValue::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalseRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadHasKey2, pojoBadHasKey2::getTest, pojoBadHasKey2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalseRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadHasItem2, pojoBadHasItem2::getTest, pojoBadHasItem2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomGetItemFailsRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetItem2, pojoBadGetItem2::getTest, pojoBadGetItem2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeysRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetKeys2, pojoBadGetKeys2::getTest, pojoBadGetKeys2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKeysRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetKeysExtraKey2, pojoBadGetKeysExtraKey2::getTest,
					pojoBadGetKeysExtraKey2::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKeyRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetKeysWrongKey2, pojoBadGetKeysWrongKey2::getTest,
					pojoBadGetKeysWrongKey2::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraItemRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetMapExtraItem2, pojoBadGetMapExtraItem2::getTest,
					pojoBadGetMapExtraItem2::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapKeyRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetMapWrongKey2, pojoBadGetMapWrongKey2::getTest,
					pojoBadGetMapWrongKey2::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValueRun2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetMapWrongValue2, pojoBadGetMapWrongValue2::getTest,
					pojoBadGetMapWrongValue2::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClear(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadClear, pojoBadClear::getTest, pojoBadClear::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadGetter(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetter, pojoBadGetter::getTest, pojoBadGetter::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomSetterHasKeyFalse(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoHasKeyFalse, pojoHasKeyFalse::getTest, pojoHasKeyFalse::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomSetterHasItemFalse(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoHasItemFalse, pojoHasItemFalse::getTest, pojoHasItemFalse::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongValue(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoWrongItem, pojoWrongItem::getTest, pojoWrongItem::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomSetterNullKeys(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomNullKeys, pojoCustomNullKeys::getTest, pojoCustomNullKeys::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKey(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomExtraKey, pojoCustomExtraKey::getTest, pojoCustomExtraKey::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKey(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomWrongKey, pojoCustomWrongKey::getTest, pojoCustomWrongKey::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraMapKey(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomExtraMapKey, pojoCustomExtraMapKey::getTest,
					pojoCustomExtraMapKey::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValue(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomWrongMapValue, pojoCustomWrongMapValue::getTest,
					pojoCustomWrongMapValue::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadGetter2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoBadGetter2, pojoBadGetter2::getTest, pojoBadGetter2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalse2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoHasKeyFalse2, pojoHasKeyFalse2::getTest, pojoHasKeyFalse2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalse2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoHasItemFalse2, pojoHasItemFalse2::getTest, pojoHasItemFalse2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongValue2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoWrongItem2, pojoWrongItem2::getTest, pojoWrongItem2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeys2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomNullKeys2, pojoCustomNullKeys2::getTest, pojoCustomNullKeys2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKey2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomExtraKey2, pojoCustomExtraKey2::getTest, pojoCustomExtraKey2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKey2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomWrongKey2, pojoCustomWrongKey2::getTest, pojoCustomWrongKey2::setTest,
					DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraMapKey2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomExtraMapKey2, pojoCustomExtraMapKey2::getTest,
					pojoCustomExtraMapKey2::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValue2(){
		try{
			MappedPojoTest.assertValueGetSetCustom(pojoCustomWrongMapValue2, pojoCustomWrongMapValue2::getTest,
					pojoCustomWrongMapValue2::setTest, DEFAULT_TEST_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
}
