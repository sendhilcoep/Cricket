package com.cricket;

import java.util.Comparator;

public class PlayerStat implements Comparable{
	private static final int BOWLER_WICKET_POINT = 25;
	private static final double SHARED_WICKET_POINT = 12.5;
	int runsScored;
	int ballsFaced;
	int runsGiven;
	int ballsBowled;
	int wicketsPicked;
	int sharedWickets;
	double bonus;
	String name;
	Comparator<? super PlayerStat> finalPointComparator = (p1, p2) -> p1.getFinalPoint() - p2.getFinalPoint() > BallConstants.LOWEST_ERROR ? 1 : (p1.getFinalPoint() - p2.getFinalPoint() < (-1*BallConstants.LOWEST_ERROR)) ? -1 : 0;
	Comparator<? super PlayerStat> basePointComparator = (p1, p2) -> p1.calcBasePoint() - p2.calcBasePoint() > BallConstants.LOWEST_ERROR ? 1 : (p1.calcBasePoint() - p2.calcBasePoint() < (-1*BallConstants.LOWEST_ERROR)) ? -1 : 0;

	public PlayerStat(String name) {
		this.name = name;
		this.runsScored = 0;
		this.ballsFaced = 0;
		this.runsGiven = 0;
		this.ballsBowled = 0;
		this.wicketsPicked = 0;
		this.sharedWickets = 0;
		this.bonus = 0.0;
	}

	public void addExtraBallBowled(int runs, boolean isWicket) {
		runsGiven += runs;
		if (isWicket) {
			wicketsPicked += 1;
			sharedWickets += 1;
		}
	}

	public void addBallBowled(int runs, boolean isWicket, boolean isSharedWicket) {
		ballsBowled++;
		runsGiven += runs;
		if (isWicket) {
			wicketsPicked += 1;
			if (isSharedWicket)
				sharedWickets += 1;
		}
	}

	public double calcBasePoint() {
		return ((runsScored * 1) + (sharedWickets * SHARED_WICKET_POINT)
				+ ((wicketsPicked - sharedWickets) * BOWLER_WICKET_POINT));
	}

	public double getBattingStrikeRate() {
		if (ballsFaced == 0)
			return BallConstants.INVALID;
		return runsScored * 1.0 / ballsFaced;
	}

	public void addBonus() {
		bonus += 0.1;
	}

	public void deductBonus() {
		bonus -= 0.1;
	}

	public Double getFinalPoint() {
		return calcBasePoint() * (1.0 + bonus);
	}

	public double getBowlingRunRate() {
		if (ballsBowled == 0)
			return BallConstants.INVALID;
		return runsGiven * 1.0 / ballsBowled;
	}

	public String getName() {
		return name;
	}

	public void addRuns(Integer runsScored) {
		this.runsScored += runsScored;
		this.ballsFaced += 1;
	}

	@Override
	public String toString() {
		return "PlayerStat [runs=" + runsScored + ", ballsFaced=" + ballsFaced + ", runsGiven=" + runsGiven
				+ ", ballsBowled=" + ballsBowled + ", wicketsPicked=" + wicketsPicked + ", sharedWickets="
				+ sharedWickets + ", bonus=" + bonus + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Object o) {
		PlayerStat p2 = (PlayerStat) o;
		return (finalPointComparator.compare(this,p2) != 0) ? finalPointComparator.compare(this,p2) : basePointComparator.compare(this, p2);    
	}
	

}
