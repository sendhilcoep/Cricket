package com.cricket;

import java.util.HashMap;
import java.util.Map;

public class Ball {

	private static final int PARAM_TEAM_NUMBER = 0;
	private static final int PARAM_TEAM_NAME = 2;
	private static final int PARAM_BATSMAN_NAME = 3;
	private static final int PARAM_BOWLER_NAME = 5;
	private static final int PARAM_BATSMAN_RUNS = 6;
	private static final int PARAM_EXTRAS_STR = 7;
	private static final int PARAM_WICKET_TYPE = 8;
	private static final int PARAM_DISMISSED_BATSMAN = 9;

	private static Map<String, ExtrasType> extrasMap = new HashMap<String, ExtrasType>(5) {
		{
			put("b", ExtrasType.BYES);
			put("lb", ExtrasType.LEGBYES);
			put("nb", ExtrasType.NOBALL);
			put("w", ExtrasType.WIDE);
			put("p", ExtrasType.PENALTY);
		}
	};
	private static Map<String, Integer> dismissalMap = new HashMap<String, Integer>() {
		{
			put("bowled", BallConstants.BOWLER_WICKET);
			put("caught", BallConstants.SHARED_WICKET);
			put("stumped", BallConstants.SHARED_WICKET);
			put("run out", BallConstants.NOT_BOWLER_WICKET);
			put("lbw", BallConstants.BOWLER_WICKET);
			put("handled the ball", BallConstants.NOT_BOWLER_WICKET);
			put("hit wicket", BallConstants.NOT_BOWLER_WICKET);
			put("double touch", BallConstants.NOT_BOWLER_WICKET);
			put("obstructed field", BallConstants.NOT_BOWLER_WICKET);
			put("timed out", BallConstants.NOT_BOWLER_WICKET);
			put("retired out", BallConstants.NOT_BOWLER_WICKET);
		}
	};
	
	private int inningsNumber;
	private String teamName;
	private String batsmanName;
	private String bowlerName;
	private Integer batsmanRun;
	private Boolean hasExtras;
	private int extraRuns;
	private String extraType;
	private ExtrasType extrasType;
	private boolean hasWicket;
	private String dismissedBatsman;
	private Integer dismissalType;

	/*
	 * - Innings number: 1 or 2 - Over and ball - Batting team name - Batsman
	 * name - Non-striker name - Bowler name - Runs-off-bat - a single number -
	 * Extras - this consists of the number of runs scored, followed by a suffix
	 * to indicate type of extras. The suffix can be one of: `nb`, `w`, `b`,
	 * `lb`, and `p`. These stand for, respectively: no ball, wide, byes, leg
	 * byes, and penalty for any other reason. - Kind of wicket, if any. This
	 * can be one of: `bowled`, `caught`, `stumped`, `run out`, `lbw`, `handled
	 * the ball`, `hit wicket`, `double touch`, `obstructed field`, `timed out`,
	 * `retired out`. - Name of dismissed player, if there was a wicket
	 */
	public Ball(String commentary) {
		String[] fields = commentary.split(",");
		inningsNumber = Integer.parseInt(fields[PARAM_TEAM_NUMBER]);
		teamName = fields[PARAM_TEAM_NAME];
		batsmanName = fields[PARAM_BATSMAN_NAME];
		bowlerName = fields[PARAM_BOWLER_NAME];
		batsmanRun = Integer.parseInt(fields[PARAM_BATSMAN_RUNS]);
		String extraStr = fields[PARAM_EXTRAS_STR];
		// EXTRAS
		hasExtras = !extraStr.equals("\"\"");
		extraRuns = extraStr.equals("\"\"") ? 0 : extractExtraRuns(extraStr);
		extraType = extraStr.equals("\"\"") ? null : extractExtraType(extraStr);
		extrasType = extraStr.equals("\"\"") ? null : extrasMap.get(extraType);
		// WICKETS
		String wicketStr = fields[PARAM_WICKET_TYPE];
		hasWicket = !wicketStr.equals("\"\"");
		dismissalType = hasWicket ? dismissalMap.get(wicketStr) : null;
		dismissedBatsman = fields[PARAM_DISMISSED_BATSMAN].trim();
	}

	public boolean isLegalBattingDelivery() {
		return (extrasType == null) || extrasType.isLegalBattingDelivery();
	}

	public String getBatsmanName() {
		return batsmanName;
	}

	public Integer getRunsScored() {
		return batsmanRun;
	}

	public Integer getInningsNumber() {
		return inningsNumber;
	}

	public boolean hasExtras() {
		return hasExtras;
	}

	public Integer getExtraRuns() {
		return extraRuns;
	}

	public String getBattingTeamName() {
		return teamName;
	}

	public static String extractExtraType(String extraStr) {
		StringBuilder builder = new StringBuilder();
		for (Character character : extraStr.toCharArray()) {
			if (Character.isLetter(character)) {
				builder.append(character);
			}
		}
		return builder.toString();
	}

	public static int extractExtraRuns(String extraStr) {
		String string = extraStr.chars().filter(Character::isDigit).boxed().map(num -> num - 48).map(n -> n.toString())
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
		return Integer.parseInt(string);
	}

	public boolean isLegalBowlingDelivery() {
		if (extrasType == null)
			return true;
		return extrasType.isLegalBowlingDelivery();
	}

	public boolean hasWicket() {
		return hasWicket;
	}

	public String getBowler() {
		return bowlerName;
	}

	public Integer getDismissalType() {
		return dismissalType;
	}

	public String getDismissedBatsman() {
		return dismissedBatsman;
	}

}
