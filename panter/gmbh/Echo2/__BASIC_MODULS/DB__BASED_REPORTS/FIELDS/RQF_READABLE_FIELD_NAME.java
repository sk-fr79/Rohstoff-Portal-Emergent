 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;
  
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_FIELD;
import panter.gmbh.indep.dataTools.IF_Field;
import nextapp.echo2.app.Alignment;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum RQF_READABLE_FIELD_NAME {
	
    AKTIV(REPORTING_QUERY_FIELD.aktiv,"Aktiv",30,30,											new Alignment(Alignment.CENTER, Alignment.TOP)),
    ALIGNMENT(REPORTING_QUERY_FIELD.alignment,"Ausrichtung (Liste)",100,100,					new Alignment(Alignment.CENTER, Alignment.TOP)),
    BREITE_LISTE_PX(REPORTING_QUERY_FIELD.breite_liste_px,"Breite (Liste)",100,100,				new Alignment(Alignment.RIGHT, Alignment.TOP)),
    FIELDNAME(REPORTING_QUERY_FIELD.fieldname,"Feldname",400,400,								new Alignment(Alignment.LEFT, Alignment.TOP)),
    FIELDNAME_4_USER(REPORTING_QUERY_FIELD.fieldname_4_user,"Feldname für Benutzer",400,400,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_REPORTING_QUERY(REPORTING_QUERY_FIELD.id_reporting_query,"ID (Query)",150,100,			new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_REPORTING_QUERY_FIELD(REPORTING_QUERY_FIELD.id_reporting_query_field,"ID (Feld)",150,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    IS_SEARCHFIELD(REPORTING_QUERY_FIELD.is_searchfield,"Suchfeld",30,30,						new Alignment(Alignment.CENTER, Alignment.TOP)),
    IS_SELECTORFIELD(REPORTING_QUERY_FIELD.is_selectorfield,"Selektor ?",30,30,					new Alignment(Alignment.CENTER, Alignment.TOP)),
    SORTIERFOLGE(REPORTING_QUERY_FIELD.sortierfolge,"Sortierreihenfolge ?",30,30,				new Alignment(Alignment.CENTER, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private RQF_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private RQF_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (RQF_READABLE_FIELD_NAME  rf: RQF_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (RQF_READABLE_FIELD_NAME  rf: RQF_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (RQF_READABLE_FIELD_NAME  rf: RQF_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (RQF_READABLE_FIELD_NAME  rf: RQF_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (RQF_READABLE_FIELD_NAME  rf: RQF_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (RQF_READABLE_FIELD_NAME  rf: RQF_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (RQF_READABLE_FIELD_NAME  rf: RQF_READABLE_FIELD_NAME.values() ) {
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
     
 
