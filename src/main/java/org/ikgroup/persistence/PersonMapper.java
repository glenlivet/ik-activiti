package org.ikgroup.persistence;

import java.util.List;

import org.ikgroup.domain.Person;

public interface PersonMapper {

	/**
	 * add new person
	 * 
	 * @param person
	 */
	public void add(Person person);
	
	/**
	 * find all persons.
	 * 
	 */
	public List<Person> findAll();
	
	/**
	 * Find by username
	 * 
	 * @param username
	 * @return
	 */
	public Person findByUsername(String username);
	
}
