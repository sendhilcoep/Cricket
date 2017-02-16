package com.cricket;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Team {
	int runs;
	int balls;
	int extras;
	int ballsBowled;
	int runsGiven;
	Map<String, PlayerStat> playersStatMap = new HashMap<>();

	public Team(String battingTeamName) {
		this.runs = 0;
		this.balls = 0;
		this.extras = 0;
		this.ballsBowled = 0;
		this.runsGiven = 0;
	}

	private void addPlayer(String name) {
		playersStatMap.put(name, new PlayerStat(name));
	}

	private void addBallInBattingInn(int addRuns, int addBalls, int addExtras) {
		runs += addRuns;
		balls += addBalls;
		extras += addExtras;
	}

	private void addBallInBowlingInn(int addRunsGiven, int addBowlBowled) {
		runsGiven += addRunsGiven;
		ballsBowled += addBowlBowled;
	}

	private double getTeamBattingStrikeRate() {
		return ((runs - extras) * 1.0) / balls;
	}

	private double getTeamBowlingRunRate() {
		return runsGiven * 1.0 / ballsBowled;
	}

	public void addBonusToPlayers() {
		for (PlayerStat playerStat : playersStatMap.values()) {
			double teamStrikeRate = getTeamBattingStrikeRate();
			double battingStrikeRate = playerStat.getBattingStrikeRate();
			if (battingStrikeRate < 0)
				continue; // Fragile
			if (battingStrikeRate > teamStrikeRate) {
				playerStat.addBonus();
			} else if (battingStrikeRate < teamStrikeRate) {
				playerStat.deductBonus();
			}
		}

		for (PlayerStat playerStat : playersStatMap.values()) {
			double teamRunRate = getTeamBowlingRunRate();
			double bowlingRunRate = playerStat.getBowlingRunRate();
			if (bowlingRunRate < 0)
				continue;
			if (bowlingRunRate < teamRunRate) {
				playerStat.addBonus();
			} else if (bowlingRunRate > teamRunRate) {
				playerStat.deductBonus();
			}
		}
	}

	public void printBasePointsOfAllPlayers() {
		playersStatMap.values().stream().forEach(pStat -> System.out.println(pStat.getName() + pStat.calcBasePoint()));
	}

	private void addRunsToPlayer(String batsmanName, Integer runsScored) {
		if (playersStatMap.get(batsmanName) == null)
			addPlayer(batsmanName);
		playersStatMap.get(batsmanName).addRuns(runsScored);
	}

	public Collection<PlayerStat> getPlayers() {
		return playersStatMap.values();
	}
	public PlayerStat computeBestPlayer() {
		Comparator<? super PlayerStat> comparator = (p1, p2) -> p1.getFinalPoint() - p2.getFinalPoint() > BallConstants.LOWEST_ERROR ? 1 : (p1.getFinalPoint() - p2.getFinalPoint() < 0.025) ? -1 : 0;
		return playersStatMap.values().stream()
				.peek(pstat -> System.out.print(pstat.name + " " + pstat.getFinalPoint())).max(comparator).get();
	}

	private void addBallToPlayer(String bowler, Integer runsConceeded, boolean hasWicket, Integer dismissalType) {
		if (playersStatMap.get(bowler) == null)
			addPlayer(bowler);
		playersStatMap.get(bowler).addBallBowled(runsConceeded,
				hasWicket && (dismissalType != BallConstants.NOT_BOWLER_WICKET),
				dismissalType == BallConstants.SHARED_WICKET);

	}

	private void addExtraBallToPlayer(String bowler, int runsConceeded, boolean hasWicket, Integer dismissalType) {
		if (playersStatMap.get(bowler) == null)
			addPlayer(bowler);
		playersStatMap.get(bowler).addExtraBallBowled(runsConceeded,
				hasWicket && (dismissalType != BallConstants.NOT_BOWLER_WICKET));
	}

	public void addBallToBattingInnings(Ball ball) {
		Team battingTeam = this;
		if (ball.hasExtras() && !ball.isLegalBattingDelivery()) {
			battingTeam.addBallInBattingInn(0, 0, ball.getExtraRuns());
		} else {
			battingTeam.addBallInBattingInn(ball.getRunsScored(), BallConstants.ONE_BALL, ball.getExtraRuns());
			battingTeam.addRunsToPlayer(ball.getBatsmanName(), ball.getRunsScored());
		}

	}

	public void addBallToBowlingInnings(Ball ball) {
		Team bowlingTeam = this;
		if (ball.hasExtras() && !ball.isLegalBowlingDelivery()) {
			bowlingTeam.addBallInBowlingInn(ball.getRunsScored() + ball.getExtraRuns(), 0);
			bowlingTeam.addExtraBallToPlayer(ball.getBowler(), ball.getRunsScored(), ball.hasWicket(),
					ball.getDismissalType());
		} else {
			bowlingTeam.addBallInBowlingInn(ball.getRunsScored() + ball.getExtraRuns(), BallConstants.ONE_BALL);
			bowlingTeam.addBallToPlayer(ball.getBowler(), ball.getRunsScored(), ball.hasWicket(),
					ball.getDismissalType());
		}

	}

}
