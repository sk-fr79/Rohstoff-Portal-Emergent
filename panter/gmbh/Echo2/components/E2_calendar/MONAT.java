package panter.gmbh.Echo2.components.E2_calendar;

import panter.gmbh.Echo2.MyE2_String;

public enum MONAT {
	JANUAR(1,new MyE2_String("Januar"),new MyE2_String("Jan"))
	,FEBRUAR(2,new MyE2_String("Februar"),new MyE2_String("Feb"))
	,MAERZ(3,new MyE2_String("März"),new MyE2_String("März"))
	,APRIL(4,new MyE2_String("April"),new MyE2_String("April"))
	,MAI(5,new MyE2_String("Mai"),new MyE2_String("Mai"))
	,JUNI(6,new MyE2_String("Juni"),new MyE2_String("Juni"))
	,JULI(7,new MyE2_String("Juli"),new MyE2_String("Juli"))
	,AUGUST(8,new MyE2_String("August"),new MyE2_String("Aug"))
	,SEPTEMBER(9,new MyE2_String("September"),new MyE2_String("Sept"))
	,OKTOBER(10,new MyE2_String("Oktober"),new MyE2_String("Okt"))
	,NOVEMBER(11,new MyE2_String("November"),new MyE2_String("Nov"))
	,DEZEMBER(12,new MyE2_String("Dezember"),new MyE2_String("Dez"))
	;
	private int 		 number = 1;
	private MyE2_String  monat_lang = null;
	private MyE2_String  monat_kurz = null;
	
	private MONAT(int i_number, MyE2_String s_monat_lang, MyE2_String s_monat_kurz) {
		this.number = i_number;
		this.monat_lang = s_monat_lang;
		this.monat_kurz = s_monat_kurz;
	}

	
	public static String[][] monate_kurz_4_dropdown() {
		String[][] a_rueck = new String[12][2];
		int i=0;
		for (MONAT mon: MONAT.values()) {
			a_rueck[i][0]=mon.get_monat_kurz().CTrans();
			a_rueck[i++][1]=""+mon.get_number();
		}
		return a_rueck;
	}

	public static String[][] monate_lang_4_dropdown() {
		String[][] a_rueck = new String[12][2];
		int i=0;
		for (MONAT mon: MONAT.values()) {
			a_rueck[i][0]=mon.get_monat_lang().CTrans();
			a_rueck[i++][1]=""+mon.get_number();
		}
		return a_rueck;
	}


	public static String get_namen(int monat_1_bis_12) {
		for (MONAT mon: MONAT.values()) {
			if (mon.get_number()==monat_1_bis_12) {
				return mon.get_monat_lang().CTrans();
			}
		}
		return null;
	}
	
	
	public int get_number() {
		return number;
	}


	public MyE2_String get_monat_lang() {
		return monat_lang;
	}


	public MyE2_String get_monat_kurz() {
		return monat_kurz;
	}

}
