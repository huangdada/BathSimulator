package com.sbs.batch;

import java.text.SimpleDateFormat;


public class Job {
	private int time;
	private String stat;
	private String id;
	private String submitTime;
	private String size;
	private static int currI=0;
	private long startrun;
	public Job(int t){
		stat="waiting";
		setTime(t);
		setId();
		size=t+"";
		SimpleDateFormat sDateFormat   =   new   SimpleDateFormat("hh:mm:ss");     
		String   date  =   sDateFormat.format(new java.util.Date());  
		startrun = 0;
		submitTime = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public long getStartrun() {
		return startrun;
	}
	public void setStartrun(long startrun) {
		this.startrun = startrun;
	}
	public void setStat(String string) {
		// TODO Auto-generated method stub
		stat=string;
		//Sbs.mf.editRow(this);
	}

	
	public String getStat(){
		return stat;
	}
	public String getId() {
		return id;
	}
	public void setId() {
		this.id = currI+++"";
	}
	public String getStartTime() {
		return submitTime;
	}
	public void setStartTime(String startTime) {
		this.submitTime = startTime;
	}
	public String getRunTime() {
		if(startrun==0)return 0+"";
		else
		return ((int)(System.currentTimeMillis()-startrun)/1000)+"";
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

}
