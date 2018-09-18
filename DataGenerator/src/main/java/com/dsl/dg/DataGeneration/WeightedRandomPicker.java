package com.dsl.dg.DataGeneration;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class WeightedRandomPicker<E> {

	private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
	private final Random random;
	private double total = 0;

	public WeightedRandomPicker() {
		this(new Random());
	}

	public WeightedRandomPicker(Random random) {
		this.random = random;
	}

	public WeightedRandomPicker<E> add(double weight, E result) {
		if (weight <= 0)
			return this;
		total += weight;
		map.put(total, result);
		return this;
	}

	public E next() {
		double value = random.nextDouble() * total;
		return map.higherEntry(value).getValue();
	}

	public static void main(String[] args) {
		WeightedRandomPicker<Object> rc = new WeightedRandomPicker<Object>().add(3, 23).add(2, 45).add(5, 77);
		for (int i = 0; i < 10; i++) {
			System.out.println(rc.next());
		}
	}

}
