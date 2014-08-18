package net.sumppen.whatsapi4j.example;

import java.io.Console;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import net.sumppen.whatsapi4j.EventManager;
import net.sumppen.whatsapi4j.MessageProcessor;
import net.sumppen.whatsapi4j.WhatsApi;
import net.sumppen.whatsapi4j.WhatsAppException;

public class ExampleApplication {
	
	private enum WhatsAppCommand {
		send
	}

	public static void main(String[] args) {
		Logger.getRootLogger().setLevel(Level.ALL);
		Layout layout = new PatternLayout("%d [%t] %-5p %c %x - %m%n");
		Logger.getRootLogger().addAppender(new ConsoleAppender(layout));
		
		if(args.length < 4) {
			System.out.println("Usage: ExampleApplication <username> <password> <id> <nick>");
			System.exit(1);
		}
		Console cons = System.console();
		if(cons == null) {
			System.out.println("No console found. Aborting");
			System.exit(1);
		}
		
		String username = args[0];
		String password = args[1];
		String identity = args[2];
		String nickname = args[3];
		WhatsApi wa = null;
		try {
			wa = new WhatsApi(username, identity, nickname);
			
			EventManager eventManager = new ExampleEventManager();
			wa.setEventManager(eventManager );
			MessageProcessor mp = new ExampleMessageProcessor();
			wa.setNewMessageBind(mp);
			if(!wa.connect()) {
				System.out.println("Failed to connect to WhatsApp");
				System.exit(1);
			}
			wa.loginWithPassword(password);
			String cmd;
			ExampleMessagePoller poller = new ExampleMessagePoller(wa);
			poller.start();
			System.out.print("$ ");
			while((cmd=cons.readLine()) != null) {
				WhatsAppCommand wac = WhatsAppCommand.valueOf(cmd);
				switch(wac) {
				case send:
					sendMessage(cons,wa);
					break;
					default: 
						System.out.println(cmd);
				}
				System.out.print("$ ");
			}
			poller.setRunning(false);
			System.out.println("Done! Logging out");
			wa.disconnect();
		} catch (Exception e) {
			System.out.println("Caught exception: "+e.getMessage());
			e.printStackTrace();
			if(wa != null) {
				wa.disconnect();
			}
			System.exit(1);
		}
	}

	private static void sendMessage(Console cons, WhatsApi wa) throws WhatsAppException {
		System.out.print("To: ");
		String to = cons.readLine();
		if(to == null || to.length() == 0) {
			return;
		}
		System.out.print("Message: ");
		String message = cons.readLine();
		if(message == null || message.length() == 0) {
			return;
		}
		String res = wa.sendMessage(to, message);
		System.out.println(res);
	}

}
