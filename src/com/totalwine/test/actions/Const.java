package com.totalwine.test.actions;

public class Const {
	private String firstName="Me";
	private String lastName = "Too";
	
	Const (String firstName,String lastName) {
		this.firstName = firstName;
		this.lastName = lastName ;
	}
	
	public static void main (String[] args) {
		Const c = new Const ("Rajat","Sud");
		System.out.println(c.firstName);
		System.out.println(c.lastName);
	}
}
