package com.sbs.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.sbs.batch.Job;
import com.sbs.batch.Sbs;
import com.sbs.batch.Slot;

/**
 *This class involve the function that can receive the command from remote client.
 */
public class RemoteCmd extends UnicastRemoteObject implements RemoteInterface{


	private static final long serialVersionUID = 1L;

	public RemoteCmd(String cmd) throws RemoteException {
	}

	@Override
	public boolean addSlot(String str) throws RemoteException {
		// TODO Auto-generated method stub
		return Sbs.addSlot(str);
	}

	@Override
	public boolean rmSlot(String str) throws RemoteException {
		// TODO Auto-generated method stub
		
		return Sbs.rmSlot(str);
	}

	@Override
	public ArrayList<String> getQueue() throws RemoteException {
		// TODO Auto-generated method stub
		 ArrayList<String> queue = new  ArrayList<String>();
		 int total = Sbs.getQueue().size();
		 int wait = 0;
		 if(Sbs.getQueue().isEmpty()){
			  queue.add("0,0");
			  return queue;
		 }
		for(Job job:Sbs.getQueue()){
			if(job.getStat().equalsIgnoreCase("waiting"))
				wait++;
		}
		String str = total+","+wait;
		queue.add(str);
		return queue;
	}

	@Override
	public ArrayList<String> getPool() throws RemoteException {
		// TODO Auto-generated method stub
		 ArrayList<String> pool = new  ArrayList<String>();
		for(Slot slot:Sbs.getPool()){
			String str = slot.getName()+","+slot.getAddress()+","+(slot.isActivity()?"Busy":"Idle");
			pool.add(str);
		}
		return pool;
	}

	/**
	 * This function gets the command from the remote client.
	 */


}
