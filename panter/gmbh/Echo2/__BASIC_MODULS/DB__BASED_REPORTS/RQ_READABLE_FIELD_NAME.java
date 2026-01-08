 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
  
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.indep.dataTools.IF_Field;
import nextapp.echo2.app.Alignment;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum RQ_READABLE_FIELD_NAME {
	
    AKTIV(REPORTING_QUERY.aktiv,"Akt.",30,30,											new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_REPORTING_QUERY(REPORTING_QUERY.id_reporting_query,"ID",100,100,	new Alignment(Alignment.RIGHT, Alignment.TOP)),
    QUERY1(REPORTING_QUERY.query1,"SQL (1)",400,400,										new Alignment(Alignment.LEFT, Alignment.TOP)),
    QUERY2(REPORTING_QUERY.query2,"SQL (2)",400,400,										new Alignment(Alignment.LEFT, Alignment.TOP)),
    QUERY3(REPORTING_QUERY.query3,"SQL (3)",400,400,										new Alignment(Alignment.LEFT, Alignment.TOP)),
    QUERY4(REPORTING_QUERY.query4,"SQL (4)",400,400,										new Alignment(Alignment.LEFT, Alignment.TOP)),
    REALNAME_TEMPTABLE(REPORTING_QUERY.realname_temptable,"Tabellen-Name",	400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    TABLE_BASENAME(REPORTING_QUERY.table_basename,"Namens-Basis",200,200,				new Alignment(Alignment.LEFT, Alignment.TOP)),
    TITEL_4_USER(REPORTING_QUERY.titel_4_user,"Benutzer-Text",400,400,					new Alignment(Alignment.LEFT, Alignment.TOP)),
    LANGTEXTINFO(REPORTING_QUERY.langtextinfo,"Informationen",400,400,					new Alignment(Alignment.LEFT, Alignment.TOP)),
    
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private RQ_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private RQ_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (RQ_READABLE_FIELD_NAME  rf: RQ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (RQ_READABLE_FIELD_NAME  rf: RQ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (RQ_READABLE_FIELD_NAME  rf: RQ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.TOP);
		
		for (RQ_READABLE_FIELD_NAME  rf: RQ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (RQ_READABLE_FIELD_NAME  rf: RQ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (RQ_READABLE_FIELD_NAME  rf: RQ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (RQ_READABLE_FIELD_NAME  rf: RQ_READABLE_FIELD_NAME.values() ) {
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
     
 
