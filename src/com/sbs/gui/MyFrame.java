package com.sbs.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sbs.batch.Job;
import com.sbs.batch.Sbs;
import com.sbs.batch.Slot;

public class MyFrame extends JFrame {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	
	
	
	public MyFrame(){
		ImageIcon icon = new ImageIcon("icon.jpg"); 
		this.setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocation(800, 400);
		this.setBackground(Color.white);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentX(LEFT_ALIGNMENT);
		scrollPane.setBackground(Color.white);
		getContentPane().setLayout(new GridLayout(1,2));
	    getContentPane().add(scrollPane);
	    getContentPane().setBackground(Color.white);
	    DefaultTableModel tmd=new DefaultTableModel(20,5);
        table = new JTable(tmd);
        scrollPane.setViewportView(table);        
        table.setValueAt("ID", 0, 0);
        table.setValueAt("Stat", 0, 1);
		table.setValueAt("Submitted", 0, 2);
		table.setValueAt("RunTime", 0, 3);
		table.setValueAt("Size", 0, 4);
        table.setShowGrid(false);
        table.getTableHeader().setVisible(false);
        
        scrollPane.setBorder(new EmptyBorder(8,8,8,8));
		final JScrollPane scrollPane2 = new JScrollPane();
	    getContentPane().add(scrollPane2);
	    DefaultTableModel tmd2=new DefaultTableModel(20,3);
        table2 = new JTable(tmd2);
        scrollPane2.setViewportView(table2);
        table2.setBorder(new EmptyBorder(8,8,8,8));
        table2.setValueAt("ID", 0, 0);
        table2.setValueAt("Stat", 0, 1);
		table2.setValueAt("Address", 0, 2);
        table2.setShowGrid(false);
        table2.getTableHeader().setVisible(false);    
        scrollPane2.setBorder(new EmptyBorder(8,8,8,8));
        table.setBackground(Color.white);
        table2.setBackground(Color.white);
        scrollPane2.setBackground(Color.white);
        add(scrollPane);
        add(scrollPane2);
	    setVisible(true);
	}
	
	public void flashTable(){
		
		ArrayList<Job> temp = new ArrayList<Job>(Sbs.getQueue());
		DefaultTableModel dtm =(DefaultTableModel) table.getModel();
		dtm.setRowCount(temp.size()+1);
		int row = 1;
		for(Job job:temp){	
			try {
				table.setValueAt(job.getId(), row, 0);
				table.setValueAt(job.getStat(), row, 1);
				table.setValueAt(job.getStartTime(), row, 2);
				table.setValueAt(job.getRunTime(), row, 3);
				table.setValueAt(job.getSize(), row, 4);				
				if(job.getStat().equalsIgnoreCase("running"))
				table.setRowSelectionInterval(row, row);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
			}
			row++;
		}
		
    	ArrayList<Slot> temp2 = new ArrayList<Slot>(Sbs.getPool());
		DefaultTableModel dtm2 =(DefaultTableModel) table2.getModel();
		dtm2.setRowCount(temp2.size()+1);
		int row2 = 1;
		for(Slot slot:temp2){	
			table2.setValueAt(slot.getName(), row2, 0);
			table2.setValueAt(slot.isActivity()?"Busy":"Idle", row2, 1);
			table2.setValueAt(slot.getAddress(), row2, 2);
			if(slot.isActivity())
			table2.setRowSelectionInterval(row2, row2);
			
			row2++;
		}
		
		
	}
	
	public synchronized void editRow(Job job){		
		int row = 0;
		
		for(int i=0;i<table.getRowCount();i++){
			
			if(table.getValueAt(i, 0).toString().equalsIgnoreCase(job.getId())){
				row = i;
				break;
			}
		}
		if(job.getStat().equalsIgnoreCase("completed")){
			DefaultTableModel dtm =(DefaultTableModel) table.getModel();
			dtm.removeRow(row);				
			//if(table.getRowCount()<20)
			//dtm.addRow(new String[10]);
		}else{
			table.setValueAt(job.getStat(), row, 1);
		}
	}

	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}
	

	private JTable table;
	private JTable table2;

}
