package com.work.falcon;

import akka.actor.UntypedActor;

public class JobControllerActor extends UntypedActor {

	int count = 0;
	int no_of_msgs = 0;
	long startedTime = System.currentTimeMillis();
	
	@Override
	public void onReceive(Object message) throws Exception {
		// TODO Auto-generated method stub
		if (message instanceof String) {
			String msg = (String) message;
			if(msg.compareTo("Done")==0){
				count++;
				if(count==no_of_msgs){
					long now = System.currentTimeMillis();
					System.out.println(" [ All message process in ] " + (now - startedTime)/1000 + " seconds");
					System.out.println(" [Total number of message processed ]  " + count);
				}
			}
		}
		
	}
	
	
	public JobControllerActor(int no_of_msgs){
		this.no_of_msgs = no_of_msgs;
	}
}
