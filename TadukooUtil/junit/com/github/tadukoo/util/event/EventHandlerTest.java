package com.github.tadukoo.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EventHandlerTest{
	private static class TestEvent extends Event{
		public TestEvent(String message){
			super("Testing", message);
		}
	}
	private static class TestEventHandler extends EventHandler<TestEvent, EventListener<TestEvent>>{
		public void createTestEvent(String message){
			sendEventToListeners(new TestEvent(message));
		}
	}
	private Event event;
	private EventListener<TestEvent> listener;
	private TestEventHandler handler;
	
	@BeforeEach
	public void setup(){
		event = null;
		
		listener = e -> event = e;
		
		handler = new TestEventHandler();
	}
	
	@Test
	public void testEventHandler(){
		assertNull(event);
		
		// Test register listener and it picks up the event
		handler.registerListener(listener);
		handler.createTestEvent("A test");
		assertNotNull(event);
		assertEquals("Testing", event.getType());
		assertEquals("A test", event.getMessage());
		
		// Unregister listener and make sure the event doesn't change
		handler.unregisterListener(listener);
		handler.createTestEvent("Another new test");
		assertNotNull(event);
		assertEquals("Testing", event.getType());
		assertEquals("A test", event.getMessage());
	}
}
