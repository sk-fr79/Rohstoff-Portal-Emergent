 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum AI_READABLE_FIELD_NAME {
	
    AKTIV(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.aktiv,"Aktiv",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    DATUMEINTRAG(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.datumeintrag,				"Erfassung am",120,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    DATUMEREIGNIS(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.datumereignis,			"Ereignis am",110,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    FOLGEDATUM(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.folgedatum,					"Wiedervorlage",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_ADRESSE(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_adresse,					"ID-Adresse",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ADRESSE_INFO(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_adresse_info,		"ID(Adr.Info)",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_AKTIONSANLASS(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_aktionsanlass,		"Anlass",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BESUCHSERGEBNIS1(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_besuchsergebnis1,"Ergebnis 1",150,150,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_BESUCHSERGEBNIS2(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_besuchsergebnis2,"Ergebnis 2",100,100,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_BESUCHSERGEBNIS3(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_besuchsergebnis3,"Ergebnis 3",100,100,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_USER(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_user,						"Betreuer",150,150,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_USER_ERSATZ(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_user_ersatz,			"Betreuer (2)",100,100,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_USER_SACHBEARBEITER(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.id_user_sachbearbeiter,"Sachbearbeiter",100,100,new Alignment(Alignment.LEFT, Alignment.TOP)),
    IST_MESSAGE(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.ist_message,				"Meldung ?",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    KUERZEL(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.kuerzel,						"Kürzel (Erfasser)",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    MESSAGE_SOFORT(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.message_sofort,			"Sofort",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    MESSAGE_TYP(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.message_typ,				"Typ",150,150,new Alignment(Alignment.LEFT, Alignment.TOP)),
    TEXT(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.text,								"Text",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    WIEDERHOLUNGJAEHRLICH(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.wiederholungjaehrlich,"WV jährlich",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    WIEDERHOLUNGMONATLICH(panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO.wiederholungmonatlich,"WV monatlich",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private AI_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private AI_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (AI_READABLE_FIELD_NAME  rf: AI_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static String getReadable(IF_Field f1, IF_Field f2) {
		String ret = "";
		
		for (AI_READABLE_FIELD_NAME  rf: AI_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f1) {
				ret=rf.m_readAble;
			}
			if (rf.m_field == f2) {
				ret=ret+" / "+rf.m_readAble;
			}
		}
		return ret;
	}

	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (AI_READABLE_FIELD_NAME  rf: AI_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (AI_READABLE_FIELD_NAME  rf: AI_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (AI_READABLE_FIELD_NAME  rf: AI_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (AI_READABLE_FIELD_NAME  rf: AI_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (AI_READABLE_FIELD_NAME  rf: AI_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (AI_READABLE_FIELD_NAME  rf: AI_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				if (rf.m_widthLabel>rf.m_widthComponent) {
				   return rf.m_widthLabel;
				} else {
				   return rf.m_widthComponent;
				}
			}
		}
		return ret;
	}
	
}
     
 
