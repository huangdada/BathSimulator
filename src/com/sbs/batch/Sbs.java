package com.sbs.batch;

import java.util.ArrayList;

import com.sbs.gui.MyFrame;


public class Sbs {
	static{
		pool = new ArrayList<Slot>();
		queue = new ArrayList<Job>();
		//delJobs = new ArrayList<Job>();
		mf = new MyFrame();
	}
	public static boolean addSlots(String num){
		int n = Integer.parseInt(num);
		for(int i=0;i<n;i++){
			String vm = "cloud1,v00"+i+",ip00"+i;
			Slot slot = new Slot(vm);
			if(!pool.add(slot)){
				return false;
			}
		}

		return true;
	}
	
	public static boolean addSlot(String vm){
		Slot slot = new Slot(vm);
		if(!pool.add(slot)){
			return false;
		}
		return true;
	}
	
	public static synchronized boolean rmSlot(String name){
		Slot slot=null;
		for(Slot s : pool){
			if(s.getName().equalsIgnoreCase(name)){
				slot=s;
				break;
			}
		}
		return pool.remove(slot);
	}
	public static void start(){
		Thread t = new Thread(new Runnable(){  
			public void run(){  
				execute();				
			}});  
			t.start();  
	}
	public static void execute(){

		while(true){		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//queue.removeAll(delJobs);
			//delJobs.clear();
			ArrayList<Job> temp = new ArrayList<Job> (queue);
			mf.flashTable();
			if(temp.isEmpty()||pool.isEmpty())continue;
				for(Job job:temp){	
					//System.out.println("here");
					try {
						if(job.getStat().equalsIgnoreCase("waiting")){
							for(Slot slot : pool){
								if(!slot.isActivity()){
									slot.runJob(job);
									break;
								}
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						continue;
					}
				}	
		
			}
	}

	public static boolean addJob(int t){
		Job job = new Job(t);
		queue.add(job);	
		return true;
	}
	
	public static boolean submit(Job job){
		return queue.add(job);
	}
	public static synchronized boolean rmJob(Job job){
		//job.setStat("completed");	
		//delJobs.add(job);
		queue.remove(job);
		
		return true;
	}
	
	public static void listPool(){
		System.out.println("Name  Address  Activity");
		System.out.println("-------------------------------------------");
		for(Slot slot:pool){
			String str = slot.getName()+"  "+slot.getAddress()+"  "+slot.isActivity();
			System.out.println(str);
		}
	}
	public static void listQ(){
		System.out.println("Total  running  waiting");
		int total = queue.size();
		int running = 0;
		int waiting;
		for(Job job:queue){
			if(job.getStat().equalsIgnoreCase("running"))running++;
		}
		waiting = total-running;
		//ArrayList<Job> temp = new ArrayList<Job>(queue); 
		System.out.println(total+"  "+running+"  "+waiting);		
	}
	public static ArrayList<Job> getQueue(){
		return queue;
	}
	public static ArrayList<Slot> getPool(){
		return pool;
	}
	//private static ArrayList<Job> delJobs;
	private static ArrayList<Slot> pool;
	private static ArrayList<Job> queue;
	public static MyFrame mf;
}
