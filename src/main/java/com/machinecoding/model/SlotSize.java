package com.machinecoding.model;

import java.util.HashMap;
import java.util.Map;

public enum SlotSize {
	
	TWO_WHEELER(1), MINI(2), HATCH(4), SEDAN(5), SUV(6), HEAVY(8);
	
	private static final Map<Integer, SlotSize> _map = new HashMap<Integer, SlotSize>();

	    static {
	        for (SlotSize slotSize : SlotSize.values())
	            _map.put(slotSize.size, slotSize);
	    }

	    int size;

	    SlotSize(int size) {
	        this.size = size;
	    }

	    public static SlotSize getSLot(int value) {
	        return _map.get(value);
	    }

}
