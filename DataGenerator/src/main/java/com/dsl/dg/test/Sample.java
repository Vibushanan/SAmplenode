package com.dsl.dg.test;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;

public class Sample {
	
	
	public static void main(String args[]) {
		
		Fairy fairy = Fairy.create();
		
	
		
		Person o = fairy.person(PersonProperties.female());
		System.out.println("---Personal---");
		System.out.println("firstname:\t"+o.getFirstName());
		System.out.println("lastname:\t"+o.getLastName());
		System.out.println("FullName:\t"+o.getFullName());
		System.out.println("Age:\t"+o.getAge());
		System.out.println("Gender:\t"+o.getSex());
		System.out.println("work mail:\t"+o.getCompanyEmail());
		

		System.out.println("---Demographics---");
		System.out.println("Address:\t"+o.getAddress()+"\n");
		System.out.println("City:\t"+o.getAddress().getCity()+"\n");
		System.out.println("ZipCode:\t"+o.getAddress().getPostalCode()+"\n");
		System.out.println("company name\t"+o.getCompany().getName());

	

	}

}
