package org.all.test;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;

public class MyE2_Test_Component extends MyE2_Grid {

	private String name 				= null;
	private ENUM_ENTWICKLER entwickler 	= null;
	private Component komponent 		= null;
	private boolean aktiv 				= false;
	
	public MyE2_Test_Component(String oName, boolean bActive, ENUM_ENTWICKLER eEntwickler, Component oComp){
		super(3);
		
		this.name = oName;
		this.aktiv = bActive;
		this.entwickler = eEntwickler;
		this.komponent = oComp;
		
		this.add(new MyE2_Label(this.name));
		this.add(new MyE2_Label(this.entwickler.name()));
		this.add(komponent);
	}

	public String getName() {
		return name;
	}

	public ENUM_ENTWICKLER getEntwickler() {
		return entwickler;
	}

	public Component getKomponent() {
		return komponent;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
}


