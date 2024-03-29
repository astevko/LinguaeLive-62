/**
 * 
 */
package ca.jhosek.main.server.domain;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * a School
 * 
 * @author copyright (C) 2011 Andrew Stevko
 *
 */
@Entity
public class School  extends DatastoreObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2398416359335439934L;

	private String name = "";
	
	/**
	 * default used to serialize
	 */
	public School() {
		// 
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
}
