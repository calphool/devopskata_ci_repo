package com.rounceville.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rounceville.EnglishNumberToWords;

public class TestEnglishNumberToWords {

  	@Rule
  	public final ExpectedException exception = ExpectedException.none();

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
		assertEquals("one hundred fifty seven million four hundred twenty two", EnglishNumberToWords.convert(157000422));
	}
	@Test
	public void testNumber12() {
		assertEquals("twelve", EnglishNumberToWords.convert(12));
	}

        @Test
        public void testNegative() {
 		exception.expect(InvalidParameterException.class);

                assertNotEquals("negative one", EnglishNumberToWords.convert(-1));        
        }
}
