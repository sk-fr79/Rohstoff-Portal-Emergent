 
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_Enum4db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum TX_READABLE_FIELD_NAME implements IF_Enum4db<TX_READABLE_FIELD_NAME> {
	
    AKTIV(panter.gmbh.basics4project.DB_ENUMS.TAX.aktiv,						"Akt.",30,30,							"Merkmale",			E2_ALIGN.LEFT_TOP(),null),
    DROPDOWN_TEXT(panter.gmbh.basics4project.DB_ENUMS.TAX.dropdown_text,		"DropDown-Eintrag",400,400,				"DropDown-Eintrag",	E2_ALIGN.LEFT_TOP(),null),
    HISTORISCH(panter.gmbh.basics4project.DB_ENUMS.TAX.historisch,				"Hist.",30,30,							"Historisch",		E2_ALIGN.CENTER_TOP(),null),
    ID_FIBU_KONTO_GS(panter.gmbh.basics4project.DB_ENUMS.TAX.id_fibu_konto_gs,	"Fibu-Konto GS",200,200,				"Fibu-Konto GS",	E2_ALIGN.LEFT_TOP(),null),
    ID_FIBU_KONTO_RE(panter.gmbh.basics4project.DB_ENUMS.TAX.id_fibu_konto_re,	"Fibu-Konto RE",200,200,				"Fibu-Konto RE",	E2_ALIGN.LEFT_TOP(),null),
    ID_LAND(panter.gmbh.basics4project.DB_ENUMS.TAX.id_land,					"Land (Gültigkeit)",100,100,			"Land (Gültigkeit)",E2_ALIGN.LEFT_TOP(),null),
    ID_TAX(panter.gmbh.basics4project.DB_ENUMS.TAX.id_tax,						"ID",100,100,							"ID",				E2_ALIGN.RIGHT_TOP(),null),
    ID_TAX_GROUP(panter.gmbh.basics4project.DB_ENUMS.TAX.id_tax_group,			"Steuergruppe",100,100,					"Gruppe/Sort",		E2_ALIGN.LEFT_TOP(),null),
    INFO_TEXT_USER(panter.gmbh.basics4project.DB_ENUMS.TAX.info_text_user,		"Benutzer-Information",400,400,			"Benutzer-Information",	E2_ALIGN.LEFT_TOP(),null),
    LEERVERMERK(panter.gmbh.basics4project.DB_ENUMS.TAX.leervermerk,			"LeerVer.",30,30,						"Leervermerk",		E2_ALIGN.CENTER_TOP(),null),
    STEUERSATZ(panter.gmbh.basics4project.DB_ENUMS.TAX.steuersatz,				"Steuersatz %",100,100,					"Steuersatz %",		E2_ALIGN.RIGHT_TOP(),null),
    STEUERVERMERK(panter.gmbh.basics4project.DB_ENUMS.TAX.steuervermerk,		"Steuervermerk für Belege",400,400,		"Steuervermerk für Belege",	E2_ALIGN.LEFT_TOP(),null),
    TAXTYP(panter.gmbh.basics4project.DB_ENUMS.TAX.taxtyp,						"Typ des Steuersachverhalts",200,200,	"Typ des Steuersachverhalts",		E2_ALIGN.LEFT_TOP(),null),
    WECHSELDATUM(panter.gmbh.basics4project.DB_ENUMS.TAX.wechseldatum,			"Wechseldatum für Steuersatz",100,100,	"Wechseldatum für Steuersatz",		E2_ALIGN.CENTER_TOP()
    																																,new VEK<String>()	._a("Ein Wechseldatum und")
    																																					._a("ein neuer Steuersatz kommen nur")	
    																																					._a("zum Tragen, wenn es zum Leistungsdatum")
    																																					._a("keinen Eintrag in der Änderungs-Tabelle")
    																																					._a("gibt!")),
    STEUERSATZ_NEU(panter.gmbh.basics4project.DB_ENUMS.TAX.steuersatz_neu,		"Steuersatz nach Wechsel",100,100,		"Steuersatz nach Wechsel",	E2_ALIGN.RIGHT_TOP(),null),
    SORT(panter.gmbh.basics4project.DB_ENUMS.TAX.sort,							"Sortierung",100,100,					"Sortierung",		E2_ALIGN.CENTER_TOP(),null),

	;
	
	private IF_Field m_field = null;
	private String   m_readAbleForList = null;
	private String   m_readAbleForMask = null;
	private int      m_widthLabelList = 10;                  //breite fuer die labels
	private int      m_widthComponentList = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER); 
	
	private VEK<String>  textsFuerHilfeButton = new VEK<String>();
	
	private TX_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAbleForList = readAble;
	}
	
	private TX_READABLE_FIELD_NAME(IF_Field f, String readAbleForList, int m_widthLabelList, int widthComponentList, String readAbleForMask, Alignment align, VEK<String> textVekHilfe) {
		this.m_field=f;
		this.m_readAbleForList = readAbleForList;
		this.m_readAbleForMask = readAbleForMask;
		this.m_widthLabelList = m_widthLabelList;
		this.m_widthComponentList = widthComponentList;
		this.m_align = align;
		
		if (textVekHilfe!=null) {
			this.textsFuerHilfeButton._a(textVekHilfe);
		}
	}
	
	
	
	public static String getReadableForList(IF_Field f) {
		String ret = "";
		
		for (TX_READABLE_FIELD_NAME  rf: TX_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAbleForList;
			}
		}
		return ret;
	}
	
	public static String getListText(IF_Field f) {
		return TX_READABLE_FIELD_NAME.getReadableForList(f);
	}
	
	public static String getReadableForMask(IF_Field f) {
		String ret = "";
		
		for (TX_READABLE_FIELD_NAME  rf: TX_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAbleForMask;
			}
		}
		return ret;
	}
	
	public static String getMaskText(IF_Field f) {
		return TX_READABLE_FIELD_NAME.getReadableForMask(f);
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (TX_READABLE_FIELD_NAME  rf: TX_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabelList;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (TX_READABLE_FIELD_NAME  rf: TX_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponentList;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (TX_READABLE_FIELD_NAME  rf: TX_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (TX_READABLE_FIELD_NAME  rf: TX_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabelList >i) {
				i=rf.m_widthLabelList;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (TX_READABLE_FIELD_NAME  rf: TX_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponentList >i) {
				i=rf.m_widthComponentList;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (TX_READABLE_FIELD_NAME  rf: TX_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				if (rf.m_widthLabelList>rf.m_widthComponentList) {
				   return rf.m_widthLabelList;
				} else {
				   return rf.m_widthComponentList;
				}
			}
		}
		return ret;
	}

    
    
    

	@Override
	public String db_val() {
		return m_field.fieldName();
	}


	@Override
	public String user_text() {
		return this.m_readAbleForMask;
	}


	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(TX_READABLE_FIELD_NAME.values(),emptyPairInFront);
	}

	@Override
	public TX_READABLE_FIELD_NAME[] get_Values() {
		return values();
	}

	@Override
	public VEK<String>  getHelpTextVek() {
		return textsFuerHilfeButton;
	}
	
}
     
 
