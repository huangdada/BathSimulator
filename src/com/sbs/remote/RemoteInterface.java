package com.sbs.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 *This is the interface that the server program need to be implemented
 */
public interface RemoteInterface extends Remote{
	/**
	 *The remote client invokes this function. 
	 */	
	public boolean addSlot(String str) throws RemoteException;
	public boolean rmSlot(String str)throws RemoteException;
	public ArrayList<String> getQueue()throws RemoteException;
	public ArrayList<String> getPool()throws RemoteException;
	//public boolean 

}
