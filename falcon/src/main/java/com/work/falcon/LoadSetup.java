package com.work.falcon;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class LoadSetup {
	
	private ActorSystem system;
	private ActorRef router;
	private final static int no_of_msgs = 10 * 1000000;

	
	public LoadSetup() {
		system = ActorSystem.create("loadGen");
		
		//final ActorRef appManager = system.actorOf(Props.create(JobControllerActor.class, no_of_msgs ),"jobController");
		ActorRef appManager = system.actorOf(Props.create(JobControllerActor.class, no_of_msgs), "jobController");
		router = system.actorOf(new RoundRobinPool(5).props(Props.create(WorkerActor.class, appManager)),"workerRouter");
		
		
	}
	
	
	private void generateLoad() {
		for (int i = no_of_msgs; i >= 0; i--) {
			router.tell("Job Id " + i + "# send", null);
		}
		System.out.println("All jobs sent successfully");
	}

	
	public static void main(String[] args) {
		
		 new LoadSetup().generateLoad();
	}
}
