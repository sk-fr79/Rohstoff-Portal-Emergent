 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.dataTools.IF_Field;
import nextapp.echo2.app.Alignment;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum WF_SIMPLE_READABLE_FIELD_NAME {
	
    ABGESCHLOSSEN_AM(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.abgeschlossen_am,"Abgeschlossen am",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ANGELEGT_AM(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.angelegt_am,"Angelegt am",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    AUFGABE(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.aufgabe,"Aufgabe",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    BERICHT(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.bericht,"Bericht",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    FAELLIG_AM(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.faellig_am,"Fällig am",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    GELOESCHT(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.geloescht,"Gelöscht",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_EINTRAG_PARENT(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_eintrag_parent,"ID Parent",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_LAUFZETTEL(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_laufzettel,"ID Laufzettel",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_LAUFZETTEL_EINTRAG(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_laufzettel_eintrag,"ID Eintrag",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_LAUFZETTEL_PRIO(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_laufzettel_prio,"ID Prio",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_LAUFZETTEL_STATUS(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_laufzettel_status,"ID Status",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_PARENT_KADENZ(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_parent_kadenz,"ID Parent Kadenz",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_USER_ABGESCHLOSSEN_VON(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von,"ID Abgeschlossen Von",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_USER_BEARBEITER(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_user_bearbeiter,"ID Bearbeiter",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_USER_BESITZER(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.id_user_besitzer,"ID Besitzer",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    KADENZ_NACH_ABSCHLUSS(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.kadenz_nach_abschluss,"Kadenz nach Abschluss",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    KADENZ_NACH_FAELLIGKEIT(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit,"Kadenz bei Fälligkeit",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    NACHRICHT_SENT(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.nachricht_sent,"Nachricht verschickt",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    PRIVAT(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.privat,"Privat",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    SEND_NACHRICHT(panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG.send_nachricht,"Nachricht zum Fälligkeitstermin",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    
    USER_ABGESCHLOSSEN_AM(WF_SIMPLE_CONST.colname_abgeschlossen_von_name,"Abgeschlossen von",30,30,new Alignment(Alignment.LEFT, Alignment.TOP)),
    USER_BESITZER(WF_SIMPLE_CONST.colname_besitzer_name,"Aufgabe von",30,30,new Alignment(Alignment.LEFT, Alignment.TOP)),
    USER_BEARBEITER(WF_SIMPLE_CONST.colname_bearbeiter_name,"Zu Bearbeiten von",30,30,new Alignment(Alignment.LEFT, Alignment.TOP)),
    
    STATUS_STATUS(WF_SIMPLE_CONST.colname_status,"Status",100,100,new Alignment(Alignment.LEFT, Alignment.TOP) ),
    
    LAUFZETTEL_TEXT(LAUFZETTEL.text,"Thema Laufzettel",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    
    
    
	;
	
	private String   m_fieldKey = null;
	private IF_Field m_field = null;
	
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	

	
	
	private WF_SIMPLE_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private WF_SIMPLE_READABLE_FIELD_NAME(String fieldKey, String readAble) {
		this.m_field=null;
		this.m_fieldKey = fieldKey;
		this.m_readAble = readAble;
	}
	
	private WF_SIMPLE_READABLE_FIELD_NAME(String fieldKey, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=null;
		this.m_fieldKey = fieldKey;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	private WF_SIMPLE_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_fieldKey = f.tnfn();
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}

	
	public static String getReadable(String fieldKey) {
		String ret = "";
		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_fieldKey == fieldKey) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getLabelWidth(String fieldKey) {
		int ret = 10;
		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_fieldKey == fieldKey) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
	
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(String fieldKey) {
		int ret = 10;
		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_fieldKey == fieldKey) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	public static Alignment getAlignment(String fieldKey) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_fieldKey == fieldKey) {
				return rf.m_align;
			}
		}
		return ret;
	}
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
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

    public static int getMaxComponentOrLabelSize(String fieldKey) {
	    int ret = 10;
    		
		for (WF_SIMPLE_READABLE_FIELD_NAME  rf: WF_SIMPLE_READABLE_FIELD_NAME.values() ) {
			if (rf.m_fieldKey == fieldKey) {
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
     
 
