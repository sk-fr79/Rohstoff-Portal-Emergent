package org.all.test;

import panter.gmbh.Echo2.components.MyE2_Label;

public enum ENUM_TEST {
	
	
	//Add component who want to Test here
	HELLO_WORLD("HelloWorldTestClass");	

	private String className = "";
	
	private ENUM_TEST(String oClassName) {
		this.className = oClassName;	
	}
	
	public String getClassName(){
		return className;
	}
	
	
	public class HelloWorldTestClass extends MyE2_Test_Component{
		
		public HelloWorldTestClass() {
			super("Hello world !", true, ENUM_ENTWICKLER.SEBASTIEN, new MyE2_Label("Hello World !"));
		}
	}
}
