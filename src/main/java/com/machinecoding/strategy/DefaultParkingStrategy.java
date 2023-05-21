package com.machinecoding.strategy;

public class DefaultParkingStrategy implements ParkingCostStrategy{

	@Override
	public int getCost(int parkHours) {
		if(parkHours < 2)
			return 20;
		return (parkHours - 2) * 10 + 20;
	}

}
