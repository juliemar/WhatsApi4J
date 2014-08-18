package net.sumppen.whatsapi4j.example;

import java.net.SocketTimeoutException;

import net.sumppen.whatsapi4j.WhatsApi;

class ExampleMessagePoller extends Thread {
	private boolean running = true;
	private final WhatsApi wa;

	public ExampleMessagePoller(WhatsApi wa) {
		this.wa = wa;
	}

	@Override
	public void run() {
		while(isRunning())
		try {
			wa.pollMessages();
		} catch (SocketTimeoutException e) {
			
		} catch (Exception e) {
			running = false;
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}