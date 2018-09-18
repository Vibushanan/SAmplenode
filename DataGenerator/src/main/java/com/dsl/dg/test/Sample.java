package com.dsl.dg.test;

import com.github.javafaker.Faker;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;

public class Sample {

	public static void main(String args[]) {
		Faker fak = Faker.instance();

		Fairy fairy = Fairy.create();
		Person o = fairy.person(PersonProperties.female());

		System.out.println("---Personal---");
		System.out.println("firstname:\t" + o.getFirstName());
		System.out.println("lastname:\t" + o.getLastName());
		System.out.println("FullName:\t" + o.getFullName());
		System.out.println("Age:\t" + o.getAge());
		System.out.println("Gender:\t" + o.getSex());
		System.out.println("work mail:\t" + o.getCompanyEmail());

		System.out.println("---Demographics---");
		System.out.println("Street:\t" + o.getAddress().getStreet() +"\tBuldno\t"+o.getAddress().getApartmentNumber()+ "\n");
		System.out.println("Address:\t" + o.getAddress() + "\n");
		System.out.println("City:\t" + o.getAddress().getCity() + "\n");
		System.out.println("ZipCode:\t" + o.getAddress().getPostalCode() + "\n");
		// System.out.println("company nasme\t"+o.getCompany().getName());
		
<<<<<<< HEAD
		
		System.out.println(o.getAddress());
		
		//System.out.println(o.getFirstName());
		
		System.out.println();
=======
>>>>>>> branch 'master' of http://10.41.2.68:9292/vibushanan.s/DataGeneration.git

		

	}

}
