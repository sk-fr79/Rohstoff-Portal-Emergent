package panter.gmbh.Echo2;

import nextapp.echo2.app.Alignment;

public class E2_ALIGN{
	
	public static Alignment  LEFT_TOP = 		new Alignment(Alignment.LEFT, 	Alignment.TOP);
	public static Alignment  LEFT_MID = 		new Alignment(Alignment.LEFT, 	Alignment.CENTER);
	public static Alignment  LEFT_BOTTOM = 		new Alignment(Alignment.LEFT, 	Alignment.BOTTOM);
	public static Alignment  CENTER_TOP =		new Alignment(Alignment.CENTER, Alignment.TOP);
	public static Alignment  CENTER_MID = 		new Alignment(Alignment.CENTER, Alignment.CENTER);
	public static Alignment  CENTER_BOTTOM = 	new Alignment(Alignment.CENTER, Alignment.BOTTOM);
	public static Alignment  RIGHT_TOP =		new Alignment(Alignment.RIGHT, 	Alignment.TOP);
	public static Alignment  RIGHT_MID = 		new Alignment(Alignment.RIGHT, 	Alignment.CENTER);
	public static Alignment  RIGHT_BOTTOM = 	new Alignment(Alignment.RIGHT, 	Alignment.BOTTOM);
	
	public static Alignment LEFT_TOP() {return new Alignment(Alignment.LEFT, 	Alignment.TOP);};
	public static Alignment LEFT_MID()     { 	return	new Alignment(Alignment.LEFT, 	Alignment.CENTER);};
	public static Alignment LEFT_BOTTOM()  {	return	new Alignment(Alignment.LEFT, 	Alignment.BOTTOM);};
	public static Alignment CENTER_TOP ()  { 	return	new Alignment(Alignment.CENTER, Alignment.TOP);};
	public static Alignment CENTER_MID()  	{	return	new Alignment(Alignment.CENTER, Alignment.CENTER);};
	public static Alignment CENTER_BOTTOM(){ 	return  new Alignment(Alignment.CENTER, Alignment.BOTTOM);};
	public static Alignment RIGHT_TOP ()  { 	return	new Alignment(Alignment.RIGHT, 	Alignment.TOP);};
	public static Alignment RIGHT_MID()  { 	return	new Alignment(Alignment.RIGHT, 	Alignment.CENTER);};
	public static Alignment RIGHT_BOTTOM()  { 	return	new Alignment(Alignment.RIGHT, 	Alignment.BOTTOM);};

}
