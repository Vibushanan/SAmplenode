package com.dsl.dg.DataGeneration;


import java.util.Random;

public class RandomNumberGenerator {

	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		int randomNumber = random.nextInt(max + 1 - min) + min;
		return randomNumber;
	}

	char getRandomChar() {
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		return c;
	}

	public static void main(String[] args) {
		
		RandomNumberGenerator randNumber = new RandomNumberGenerator();
			
		System.out.println(randNumber.getRandomNumber(1, 7));

	}

}
