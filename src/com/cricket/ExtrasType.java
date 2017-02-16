package com.cricket;

public enum ExtrasType {
	
	NOBALL(Boolean.TRUE,Boolean.FALSE),
	WIDE(Boolean.FALSE,Boolean.FALSE),
	BYES(Boolean.TRUE,Boolean.TRUE),
	LEGBYES(Boolean.TRUE,Boolean.TRUE),
	PENALTY(Boolean.TRUE,Boolean.TRUE);
	
	private boolean isLegalBattingDelivery;
	private boolean isLegalBowlingDelivery;
	public boolean isLegalBattingDelivery() {
		return isLegalBattingDelivery;
	}
	public boolean isLegalBowlingDelivery() {
		return isLegalBowlingDelivery;
	}
	
	private ExtrasType(boolean isLegalBattingDelivery, boolean isLegalBowlingDelivery) {
		this.isLegalBattingDelivery = isLegalBattingDelivery;
		this.isLegalBowlingDelivery = isLegalBowlingDelivery;
	}
}
