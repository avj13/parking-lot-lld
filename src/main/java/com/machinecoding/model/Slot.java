package com.machinecoding.model;

public class Slot {
	private String id;
	private int number;
	boolean isEmpty;
	private Vehicle parkVehicle;
	
	public Slot(String id, Integer number) {
        this.setId(id);
        this.number = number;
    }
	
	public void removeVehicle() {
		parkVehicle = null;
        this.isEmpty = false;
    }

    public void placeVehicle(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
        this.isEmpty = true;
    }

	public int getSlotNumber() {
		return number;
	}

	public Vehicle getParkVehicle() {
		return parkVehicle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
