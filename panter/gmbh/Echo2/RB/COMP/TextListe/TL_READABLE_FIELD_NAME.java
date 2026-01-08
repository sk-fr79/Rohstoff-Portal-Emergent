 
package panter.gmbh.Echo2.RB.COMP.TextListe;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum TL_READABLE_FIELD_NAME {
		
	ID_TEXT_LISTE(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.id_text_liste,							"ID",null,									80, 80, new Alignment(Alignment.RIGHT, Alignment.TOP)),
	ID_TABLE(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.id_table,									"ID-Referenztabelle",null,					80, 80, new Alignment(Alignment.RIGHT, Alignment.TOP)),
	TABLENAME(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.tablename,									"Tabelle","Zugeordnet zu ",   				80, 80, new Alignment(Alignment.LEFT, Alignment.TOP)),
	POSITION(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.position,									"Sort","Sortierung auf Formular",			50, 50, new Alignment(Alignment.CENTER, Alignment.TOP)),
	POSITION_ENUM(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.position_enum,							"Wo ?","Position im Formular",	   			50, 50, new Alignment(Alignment.LEFT, Alignment.TOP)),
    TITEL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.titel_text,								"Überschrift",null,							200, 200, new Alignment(Alignment.LEFT, Alignment.TOP)),
    FONTSIZE_TITEL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.fontsize_titel_text,				"ÜS Font","Schriftgröße Überschrift",		80, 80, new Alignment(Alignment.RIGHT, Alignment.TOP)),
    BOLD_TITEL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.bold_titel_text,						"ÜS fett","Überschrift fett",		 		30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    ITALIC_TITEL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.italic_titel_text,					"ÜS kurs","Überschrift kursiv", 	 		30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    UNDERLINE_TITEL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.underline_titel_text,			"ÜS unter.","Überschrift unterstrichen",  	30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),

    AUFZAEHL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.aufzaehl_text,							"Aufzähl.",null,							100, 100, new Alignment(Alignment.LEFT, Alignment.TOP)),
    FONTSIZE_AUFZAEHL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.fontsize_aufzaehl_text,		"AZ Font","Schriftgröße Aufzählung",		80, 80, new Alignment(Alignment.RIGHT, Alignment.TOP)),
    BOLD_AUFZAEHL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.bold_aufzaehl_text,				"AZ fett","Aufzählung fett",			 	30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    ITALIC_AUFZAEHL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.italic_aufzaehl_text,			"AZ kursiv","Aufzählung kursiv",		 	30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    UNDERLINE_AUFZAEHL_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.underline_aufzaehl_text,		"AZ unter.","Aufzählung unterstrichen",  	30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    
    LANG_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.lang_text,									"Text",null,								400, 400, new Alignment(Alignment.LEFT, Alignment.TOP)),
    FONTSIZE_LANG_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.fontsize_lang_text,				"T Font","Schriftgröße Langtext",			80, 80, new Alignment(Alignment.RIGHT, Alignment.TOP)),
    BOLD_LANG_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.bold_lang_text,						"T fett","Langtext fett",					30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    ITALIC_LANG_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.italic_lang_text,					"T kursiv","Langtext fett",					30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    UNDERLINE_LANG_TEXT(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE.underline_lang_text,				"T unter.","Langtext unterstrichen",		30,  30, new Alignment(Alignment.CENTER, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAbleForListHeader = null;
	private String   m_readAbleForMaskHeader = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private TL_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAbleForListHeader = readAble;
	}
	
	private TL_READABLE_FIELD_NAME(IF_Field f, String readAbleListHeader, String readableMaskTitel, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAbleForListHeader = readAbleListHeader;
		this.m_readAbleForMaskHeader = S.isEmpty(readableMaskTitel)?readAbleListHeader:readableMaskTitel;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadableListHeader(IF_Field f) {
		String ret = "";
		
		for (TL_READABLE_FIELD_NAME  rf: TL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAbleForListHeader;
			}
		}
		return ret;
	}
	
	public static String getMaskTitel(IF_Field f) {
		String ret = "";
		
		for (TL_READABLE_FIELD_NAME  rf: TL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAbleForMaskHeader;
			}
		}
		return ret;
	}
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (TL_READABLE_FIELD_NAME  rf: TL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (TL_READABLE_FIELD_NAME  rf: TL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (TL_READABLE_FIELD_NAME  rf: TL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (TL_READABLE_FIELD_NAME  rf: TL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (TL_READABLE_FIELD_NAME  rf: TL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (TL_READABLE_FIELD_NAME  rf: TL_READABLE_FIELD_NAME.values() ) {
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
     
 
