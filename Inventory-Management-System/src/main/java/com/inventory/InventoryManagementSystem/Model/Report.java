package com.inventory.InventoryManagementSystem.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.inventory.InventoryManagementSystem.util.Util;

public class Report {
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private List<Product> added_items;
	private List<Product> deleted_items;
	
	public Report() {
		// TODO Auto-generated constructor stub
	}
	
	public Report(String fromTime,String toTime) {
		this.added_items=new ArrayList<Product>();
		this.deleted_items=new ArrayList<Product>();
		this.fromDateTime=Util.stringToDate(fromTime);
		this.toDateTime=Util.stringToDate(toTime);
	}
	
	public List<Product> getAdded_items() {
		return added_items;
	}
	public void setAdded_items(List<Product> added_items) {
		this.added_items = added_items;
	}
	public List<Product> getDeleted_items() {
		return deleted_items;
	}
	public void setDeleted_items(List<Product> deleted_items) {
		this.deleted_items = deleted_items;
	}

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}
	
	
	
	
	
}
