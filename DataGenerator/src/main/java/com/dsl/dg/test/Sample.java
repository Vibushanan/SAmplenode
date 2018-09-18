package com.dsl.dg.test;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;

public class Sample {
	
	
	public static void main(String args[]) {
		
		Fairy fairy = Fairy.create();
		
		
		
		Person o = fairy.person(PersonProperties.female());
		
		
		System.out.println(o.getAddress());
		
		//System.out.println(o.getFirstName());
		
		System.out.println();

		
	
	}

}
