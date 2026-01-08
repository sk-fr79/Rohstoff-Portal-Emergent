 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS;
  
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.indep.dataTools.IF_Field;
import nextapp.echo2.app.Alignment;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum RQP_READABLE_FIELD_NAME {
	
    ID_REPORTING_QUERY(REPORTING_QUERY_PARAM.id_reporting_query,				"ID (Query)",			150,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_REPORTING_QUERY_PARAM(REPORTING_QUERY_PARAM.id_reporting_query_param,	"ID (Param)",		    150,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    PARAMDEFAULT(REPORTING_QUERY_PARAM.paramdefault,							"Vorgabe",				400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    PARAMKEY(REPORTING_QUERY_PARAM.paramkey,									"Name/Platzhalter",		400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    PARAMNAME_4_USER(REPORTING_QUERY_PARAM.paramname_4_user,					"Name für den Benutzer",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    TYP(REPORTING_QUERY_PARAM.typ,												"Typ",					200,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private RQP_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private RQP_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (RQP_READABLE_FIELD_NAME  rf: RQP_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (RQP_READABLE_FIELD_NAME  rf: RQP_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (RQP_READABLE_FIELD_NAME  rf: RQP_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (RQP_READABLE_FIELD_NAME  rf: RQP_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (RQP_READABLE_FIELD_NAME  rf: RQP_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (RQP_READABLE_FIELD_NAME  rf: RQP_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (RQP_READABLE_FIELD_NAME  rf: RQP_READABLE_FIELD_NAME.values() ) {
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
     
 
