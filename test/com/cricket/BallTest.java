package com.cricket;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.stream.Stream;

import org.junit.Test;

public class BallTest {
	String testInput = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,P Kumar,0,1b,\"\",\"\"\r\n" + 
			"1,0.2,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
			"1,0.3,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,1w,\"\",\"\"\r\n" + 
			"1,0.4,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
			"1,0.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
			"1,0.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
			"1,0.7,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,1lb,\"\",\"\"\r\n" + 
			"1,1.1,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,0,0,\"\",\"\"\r\n" + 
			"1,1.2,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,4,0,\"\",\"\"\r\n" + 
			"1,1.3,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,4,0,\"\",\"\"\r\n" + 
			"1,1.4,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,6,0,\"\",\"\"\r\n" + 
			"1,1.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,4,0,\"\",\"\"\r\n" + 
			"1,1.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,0,0,lbw,BB McCullum\r\n";
	
	@Test
	public void processFirstBall() {
		Ball ball = new Ball(testInput.split("\\n")[0]);
		assertTrue(ball.isLegalBattingDelivery());
		assertTrue(ball.isLegalBowlingDelivery());
		assertTrue(ball.hasExtras());
		assertEquals((Integer)1,ball.getExtraRuns());
		assertEquals("SC Ganguly",ball.getBatsmanName());
		assertEquals((Integer)0,ball.getRunsScored());
		assertEquals((Integer)1,ball.getInningsNumber());
		assertEquals("Kolkata Knight Riders", ball.getBattingTeamName());
		assertFalse(ball.hasWicket());
	}
	
	@Test
	public void processWideBall() {
		Ball ball = new Ball(testInput.split("\\n")[2]);
		assertTrue(ball.hasExtras());
		assertFalse(ball.isLegalBattingDelivery());
		assertFalse(ball.isLegalBowlingDelivery());
	}
	
	@Test
	public void processWicket() {
		Ball ball = new Ball(testInput.split("\\n")[12]);
		assertTrue(ball.hasWicket());
		assertEquals("Z Khan",ball.getBowler());
		assertEquals(BallConstants.BOWLER_WICKET,ball.getDismissalType()); // Dismissal type
		assertEquals("BB McCullum",ball.getDismissedBatsman());
	}
	
	@Test
	public void testExtraRuns() {
		assertEquals(1,Ball.extractExtraRuns("1b"));
		assertEquals(10,Ball.extractExtraRuns("10nb"));
	}
	
	@Test
	public void testExtraType() {
		assertEquals("b",Ball.extractExtraType("1b"));
		assertEquals("nb",Ball.extractExtraType("10nb"));
		assertEquals("w",Ball.extractExtraType("1w"));
	}
	/*
	 * At any point we should have the score of each team, the players and the stats
	 */
	@Test
	public void testMiniMatch() {
		String testInput = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,P Kumar,0,1b,\"\",\"\"\r\n" + 
				"1,0.2,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,1,0,\"\",\"\"\r\n";
		Stream<String> inStream = Stream.of(testInput.split("\\n"));
		Match match = new Match(inStream);
		match.process();
		assertEquals("BB McCullum",match.computeBestPlayer());
	}
	
}
