package com.sravani.cr.util;

public class RewardsUtil {

	
	static public int calculateRewardPoints(Float transAmount) {
		if(transAmount > 50 && transAmount <=100) {
			return transAmount.intValue()-50;
		} else if(transAmount > 100) {
			return 50 + (transAmount.intValue()-100) * 2;
		} else {
			return 0;
		}
	}
}
