 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME {
	
    GEBINDE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.gebinde,"Gebinde",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    GEWICHT_EINZEL(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.gewicht_einzel,"Gewicht(Kg)",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WIEGEKARTE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.id_wiegekarte,"ID Wiegekarte",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WIEGEKARTE_ABZUG_GEB(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb,"ID Abzug",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WIEGEKARTE_GEBINDE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde,"ID Gebinde",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    MENGE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.menge,"Menge",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    SYS_TRIGGER_TIMESTAMP(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.sys_trigger_timestamp,"sys_trigger_timestamp",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    SYS_TRIGGER_UUID(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.sys_trigger_uuid,"sys_trigger_uuid",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    SYS_TRIGGER_VERSION(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB.sys_trigger_version,"sys_trigger_version",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME  rf: WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME  rf: WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME  rf: WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME  rf: WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME  rf: WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME  rf: WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME  rf: WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.values() ) {
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
     
 
