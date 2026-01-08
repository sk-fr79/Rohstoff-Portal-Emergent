 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;

/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum HELP2_READABLE_FIELD_NAME {
	
	ID_HILFETEXT(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.id_hilfetext,			"ID",			50,  50,new Alignment(Alignment.RIGHT, Alignment.TOP)),
	ID_USER_URSPRUNG(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.id_user_ursprung,	"Gemeldet von",	100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
	ID_USER_BEARBEITER(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.id_user_bearbeiter,"Entwickler",	100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ABSCHLUSSDATUM(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.abschlussdatum,		"Abgeschl.am",	100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    HILFETEXT(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.hilfetext,					"Text",			400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_VERSION(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.id_version,				"Vers.",		50,50,new Alignment(Alignment.CENTER, Alignment.TOP)),
    INFO_DEVELOPER(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.info_developer,		"Interne Info",  400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    MODULKENNER(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.modulkenner,				"Modul",		100,100,new Alignment(Alignment.LEFT, Alignment.TOP)),
    STATUS(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.status,						"Status",		100,100,new Alignment(Alignment.LEFT, Alignment.TOP)),
    TICKETNUMMER(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.ticketnummer,			"Ticket",		100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    TITEL(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.titel,							"Überschrift",	400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    TYP(panter.gmbh.basics4project.DB_ENUMS.HILFETEXT.typ,								"Typ",			100,100,new Alignment(Alignment.LEFT, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private HELP2_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private HELP2_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (HELP2_READABLE_FIELD_NAME  rf: HELP2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (HELP2_READABLE_FIELD_NAME  rf: HELP2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (HELP2_READABLE_FIELD_NAME  rf: HELP2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (HELP2_READABLE_FIELD_NAME  rf: HELP2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (HELP2_READABLE_FIELD_NAME  rf: HELP2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (HELP2_READABLE_FIELD_NAME  rf: HELP2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (HELP2_READABLE_FIELD_NAME  rf: HELP2_READABLE_FIELD_NAME.values() ) {
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
     
 
