package de.phylixit.aiohub.skywars.countdowns;

public abstract class Countdown {

	int taskID;
	
	public abstract void run();
	public abstract void cancel();
}
