package com.work.falcon;

import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class WorkerActor extends UntypedActor {

	private ActorRef jobController;
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		getContext()
			.system()
			.scheduler()
			.scheduleOnce(Duration.create(1000, TimeUnit.MILLISECONDS),
					jobController, "Done", getContext().dispatcher(),jobController);
	}
	
	
	public WorkerActor(ActorRef jobController){
		this.jobController = jobController;
	}
}
