package com.cricket;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

public class LeagueTest {
	@Test
	public void testBowlerMoM() {
		String testInput1 = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,P Kumar,0,1b,\"\",\"\"\r\n" + 
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
		assertEquals(13,Arrays.asList(testInput1.split("\\n")).size());
		assertEquals("Z Khan",League.findMoM(Arrays.asList(testInput1.split("\\n")).stream()));
	}
	@Test
	public void testBatsmenMom() {
		String testInput2 = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,P Kumar,0,1b,\"\",\"\"\r\n" + 
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
				"1,1.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,4,0,\"\",\"\"\r\n" ;
		assertEquals("BB McCullum",League.findMoM(Arrays.asList(testInput2.split("\\n")).stream()));
	}
	
	@Test 
	public void testBowlerSamePointButDifferentRunRate() {
		String testInput = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,P Kumar,1,0,\"\",\"\"\r\n" +
				"1,0.2,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
				"1,0.3,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,1w,\"\",\"\"\r\n" + 
				"1,0.4,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
				"1,0.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
				"1,0.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,1lb,\"\",\"\"\r\n" + 
				"1,0.7,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,lbw,BB McCullum\r\n"+
				"1,1.1,Kolkata Knight Riders,Y Pathan,SC Ganguly,Z Khan,0,0,\"\",\"\"\r\n" + 
				"1,1.2,Kolkata Knight Riders,Y Pathan,SC Ganguly,Z Khan,4,0,\"\",\"\"\r\n" + 
				"1,1.3,Kolkata Knight Riders,Y Pathan,SC Ganguly,Z Khan,4,0,\"\",\"\"\r\n" + 
				"1,1.4,Kolkata Knight Riders,Y Pathan,SC Ganguly,Z Khan,6,0,\"\",\"\"\r\n" + 
				"1,1.5,Kolkata Knight Riders,Y Pathan,SC Ganguly,Z Khan,4,0,\"\",\"\"\r\n" +
				"1,1.6,Kolkata Knight Riders,Y Pathan,SC Ganguly,Z Khan,0,0,lbw,Y Pathan\r\n";
		assertEquals("P Kumar",League.findMoM(Arrays.asList(testInput.split("\\n")).stream()));
	}
	
	@Test 
	public void testBatsmenSamePointButDifferentStrikeRate() {
		String testInput = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,P Kumar,6,0,\"\",\"\"\r\n" + 
				"1,0.2,Kolkata Knight Riders,SC Ganguly,BB McCullum,P Kumar,1,0,\"\",\"\"\r\n" + 
				"1,0.3,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,1w,\"\",\"\"\r\n" + 
				"1,0.4,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
				"1,0.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
				"1,0.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n" + 
				"1,0.7,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,1lb,\"\",\"\"\r\n" + 
				"1,1.1,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,0,0,\"\",\"\"\r\n" + 
				"1,1.2,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,0,0,\"\",\"\"\r\n" + 
				"1,1.3,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,0,0,\"\",\"\"\r\n" + 
				"1,1.4,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,6,0,\"\",\"\"\r\n" + 
				"1,1.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,Z Khan,1,0,\"\",\"\"\r\n" ;;
		assertEquals("SC Ganguly",League.findMoM(Arrays.asList(testInput.split("\\n")).stream()));
	}
	
	@Test 
	public void testAllRounderMoM() {
		String testInput = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,Shane Watson,0,1b,\"\",\"\"\r\n" + 
				"1,0.2,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,6,0,\"\",\"\"\r\n" + 
				"1,0.3,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,0,1w,\"\",\"\"\r\n" + 
				"1,0.4,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,4,0,\"\",\"\"\r\n" + 
				"1,0.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,6,0,\"\",\"\"\r\n" + 
				"1,0.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,6,0,\"\",\"\"\r\n" + 
				"1,0.7,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,0,0,lbw,BB McCullum\r\n"+ 
				"2,1.1,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,0,0,\"\",\"\"\r\n" + 
				"2,1.2,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,4,0,\"\",\"\"\r\n" + 
				"2,1.3,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,4,0,\"\",\"\"\r\n" + 
				"2,1.4,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,6,0,\"\",\"\"\r\n" + 
				"2,1.5,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,4,0,\"\",\"\"\r\n" + 
				"2,1.6,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,0,0,lbw,Shane Watson\r\n" ;;
		assertEquals("Shane Watson",League.findMoM(Arrays.asList(testInput.split("\\n")).stream()));
	}
	
	@Test 
	public void testMoMEqualPointsDiffBasePoints() {
		String testInput = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,Shane Watson,0,1b,\"\",\"\"\r\n" + 
				"1,0.2,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,6,0,\"\",\"\"\r\n" + 
				"1,0.3,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,0,1w,\"\",\"\"\r\n" + 
				"1,0.4,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,4,0,\"\",\"\"\r\n" + 
				"1,0.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,6,0,\"\",\"\"\r\n" + 
				"1,0.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,4,0,\"\",\"\"\r\n" + 
				"1,0.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,Shane Watson,0,0,\"\",\"\"\r\n"+ 
				"2,1.1,Royal Challengers Bangalore,KL Rahul,Shane Watson,I Sharma,1,0,\"\",\"\"\r\n" + 
				"2,1.2,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,4,0,\"\",\"\"\r\n" + 
				"2,1.3,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,6,0,\"\",\"\"\r\n" + 
				"2,1.4,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,6,0,\"\",\"\"\r\n" + 
				"2,1.5,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,6,0,\"\",\"\"\r\n" + 
				"2,1.6,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,0,0,caught,Shane Watson\r\n" ;;
		assertEquals("Shane Watson",League.findMoM(Arrays.asList(testInput.split("\\n")).stream()));
	}
	
	@Test 
	public void testMoMEqualPointsEqualBasePoints() {
		String testInput = "1,0.1,Kolkata Knight Riders,SC Ganguly,BB McCullum,P Kumar,0,1b,\"\",\"\"\r\n" + 
				"1,0.2,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,6,0,\"\",\"\"\r\n" + 
				"1,0.3,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,1w,\"\",\"\"\r\n" + 
				"1,0.4,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,4,0,\"\",\"\"\r\n" + 
				"1,0.5,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,6,0,\"\",\"\"\r\n" + 
				"1,0.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,4,0,\"\",\"\"\r\n" + 
				"1,0.6,Kolkata Knight Riders,BB McCullum,SC Ganguly,P Kumar,0,0,\"\",\"\"\r\n"+ 
				"2,1.1,Royal Challengers Bangalore,KL Rahul,Shane Watson,I Sharma,1,0,\"\",\"\"\r\n" + 
				"2,1.2,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,4,0,\"\",\"\"\r\n" + 
				"2,1.3,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,6,0,\"\",\"\"\r\n" + 
				"2,1.4,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,4,0,\"\",\"\"\r\n" + 
				"2,1.5,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,6,0,\"\",\"\"\r\n" + 
				"2,1.6,Royal Challengers Bangalore,Shane Watson,KL Rahul,I Sharma,0,0,caught,Shane Watson\r\n" ;;
		assertEquals("BB McCullum,Shane Watson",League.findMoM(Arrays.asList(testInput.split("\\n")).stream()));
	}

}
