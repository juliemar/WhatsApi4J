package net.sumppen.whatsapi4j.example;

import net.sumppen.whatsapi4j.MessageProcessor;
import net.sumppen.whatsapi4j.ProtocolNode;

public class ExampleMessageProcessor implements MessageProcessor {

	public void processMessage(ProtocolNode message) {
		System.out.print("Message received ::: ");
//		System.out.println("<<<  "+message.toString()+"  >>>");
		ProtocolNode body = message.getChild("body");
		String hex = new String(body.getData());
		System.out.println(hex);
	}

}
