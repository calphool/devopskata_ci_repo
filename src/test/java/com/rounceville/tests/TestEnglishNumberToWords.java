package com.rounceville.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rounceville.EnglishNumberToWords;

public class TestEnglishNumberToWords {

	@Test
	public void testZero() {
		assertEquals("zero", EnglishNumberToWords.convert(0));
	}
	
	@Test
	public void testLargeNumber() {
		assertEquals("one hundred fifty seven thousand four hundred twenty two", EnglishNumberToWords.convert(157422));
	}
	@Test
	public void testLargeNumber2() {
		assertEquals("one hundred fifty seven million four hundred twenty two", EnglishNumberToWords.convert(157,000,422));
	}

}
