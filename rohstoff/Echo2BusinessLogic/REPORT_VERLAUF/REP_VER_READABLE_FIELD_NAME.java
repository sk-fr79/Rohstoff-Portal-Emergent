 
package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;
  
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.indep.dataTools.IF_Field;
import nextapp.echo2.app.Alignment;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum REP_VER_READABLE_FIELD_NAME {
	
	REPORT_JASPERFILE(	REPORT_LOG.report_jasperfile,	"Report",					400,400,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
    ID_REPORT_LOG(		REPORT_LOG.id_report_log,		"ID",						100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    REPORT_DATEI_NAME(	REPORT_LOG.report_datei_name,	"Dateiname",				400,400,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
    REPORT_DRUCK_AM(	REPORT_LOG.report_druck_am,		"Druck am",					100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    REPORT_DRUCK_VON(	REPORT_LOG.report_druck_von,	"Druck von",				100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    REPORT_TITEL(		REPORT_LOG.report_titel,		"Titel",					400,400,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
    REPORT_WEG(			REPORT_LOG.report_weg,			"Typ",						400,400,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
    REPORT_UUID(		REPORT_LOG.report_uuid,			"UUID (Druck Pipeline)",	400,400,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private REP_VER_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private REP_VER_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (REP_VER_READABLE_FIELD_NAME  rf: REP_VER_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (REP_VER_READABLE_FIELD_NAME  rf: REP_VER_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (REP_VER_READABLE_FIELD_NAME  rf: REP_VER_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (REP_VER_READABLE_FIELD_NAME  rf: REP_VER_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (REP_VER_READABLE_FIELD_NAME  rf: REP_VER_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (REP_VER_READABLE_FIELD_NAME  rf: REP_VER_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (REP_VER_READABLE_FIELD_NAME  rf: REP_VER_READABLE_FIELD_NAME.values() ) {
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
     
 
