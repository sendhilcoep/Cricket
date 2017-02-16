package com.cricket;

public enum DismissalType {

	BOWLED(BallConstants.BOWLER_WICKET), CAUGHT(BallConstants.SHARED_WICKET), STUMPED(
			BallConstants.SHARED_WICKET), RUN_OUT(BallConstants.NOT_BOWLER_WICKET), LBW(
					BallConstants.BOWLER_WICKET), HANDLED_THE_BALL(BallConstants.NOT_BOWLER_WICKET), HIT_WICKET(
							BallConstants.NOT_BOWLER_WICKET), DOUBLE_TOUCH(
									BallConstants.NOT_BOWLER_WICKET), OBSTRUCTED_FIELD(
											BallConstants.NOT_BOWLER_WICKET), TIMED_OUT(
													BallConstants.NOT_BOWLER_WICKET), RETIRED_OUT(
															BallConstants.NOT_BOWLER_WICKET);

	private Integer dismissalType;

	private DismissalType(Integer dismissalType) {
		this.dismissalType = dismissalType;
	}

	public Integer getDismissalType() {
		return dismissalType;
	}

}
