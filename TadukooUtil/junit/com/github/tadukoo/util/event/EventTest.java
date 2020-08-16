package com.github.tadukoo.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest{
	private static final String type = "Test Event";
	private static final String message = "Doing testing stuff";
	private static final class TestEvent extends Event{
		public TestEvent(){
			super(type, message);
		}
	}
	private Event event;
	
	@BeforeEach
	public void setup(){
		event = new TestEvent();
	}
	
	@Test
	public void testGetType(){
		assertEquals(type, event.getType());
	}
	
	@Test
	public void testGetMessage(){
		assertEquals(message, event.getMessage());
	}
}
