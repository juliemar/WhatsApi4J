package net.sumppen.whatsapi4j.example;

import java.util.Map;

import net.sumppen.whatsapi4j.AbstractEventManager;

public class ExampleEventManager extends AbstractEventManager {

	@Override
	public void fireEvent(String event, Map<String, String> eventData) {
		if(event.equals(AbstractEventManager.EVENT_UNKNOWN)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		sb.append("Event "+event+": ");
		for(String key: eventData.keySet()) {
			if(!first) {
				sb.append(",");
			} else {
				first = false;
			}
			sb.append(key);
			sb.append("=");
			sb.append(eventData.get(key));
		}
		System.out.println(sb.toString());
	}

}
