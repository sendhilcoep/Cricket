package com.cricket;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Match {
	static final int NO_EXTRAS = 0;
	public static final int ONE_BALL = 1;
	Integer inningsNum = 0;

	List<Team> teams = Arrays.asList(new Team("Team1"), new Team("Team2"));
	Stream<Ball> ballStream;

	public Match(Stream<String> ballByBallStat) {
		ballStream = ballByBallStat.map(Ball::new);
	}

	public void process() {
		for (Iterator<Ball> ballIterator = ballStream.iterator(); ballIterator.hasNext();) {
			Ball ball = ballIterator.next();
			int battingInningsNumber = ball.getInningsNumber() - 1;
			int bowlingInningsNumber = (battingInningsNumber + 1) % 2;
			Team battingTeam = teams.get(battingInningsNumber);
			battingTeam.addBallToBattingInnings(ball);
			Team bowlingTeam = teams.get(bowlingInningsNumber);
			bowlingTeam.addBallToBowlingInnings(ball);
		}
		teams.stream().forEach(Team::addBonusToPlayers);
	}

	public String computeBestPlayer() {
		
		List<PlayerStat> sortedPlayers = teams.stream().flatMap(t->t.getPlayers().stream()).sorted().collect(Collectors.toList());
		Collections.reverse(sortedPlayers);
		
		PlayerStat mom = sortedPlayers.get(0);
		return sortedPlayers.stream().filter(p->(p.compareTo(mom)==0)).map(PlayerStat::getName).sorted().collect(Collectors.joining(","));
	}
	
	private String getDebugPrintPlayerStat(PlayerStat p) {
		return p.getName() + " " + p.calcBasePoint()+" "+ p.getFinalPoint();
	}

}
