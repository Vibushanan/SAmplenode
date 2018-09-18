package org.dsl.com.FairyWrapper;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import com.devskiller.jfairy.producer.person.PersonProperties;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Fairy fairy = Fairy.create();
    	
    	Person o = fairy.person(PersonProperties.female());
		System.out.println("--------------------------------");
		
		System.out.println("Got  ->"+o.getAddress().getState());
    	
    }
}
