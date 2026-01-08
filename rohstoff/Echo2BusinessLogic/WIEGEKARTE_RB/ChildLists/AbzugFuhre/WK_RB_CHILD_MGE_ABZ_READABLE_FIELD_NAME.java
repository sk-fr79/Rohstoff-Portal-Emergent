 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME {
	
    ABZUG_MENGE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.abzug_menge,"Menge",100,50,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ABZUG_PROZENT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.abzug_prozent,"Prozent",100,50,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ABZUGSGRUND(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.id_abzugsgrund,"Auswahl...",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WIEGEKARTE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.id_wiegekarte,"ID Wiegekarte",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WIEGEKARTE_MGE_ABZ(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz,"ID",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    LANGTEXT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.langtext,"Zusatztext",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    SYS_TRIGGER_TIMESTAMP(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.sys_trigger_timestamp,"sys_trigger_timestamp",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    SYS_TRIGGER_UUID(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.sys_trigger_uuid,"sys_trigger_uuid",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    SYS_TRIGGER_VERSION(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ.sys_trigger_version,"sys_trigger_version",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME  rf: WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME  rf: WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME  rf: WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME  rf: WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME  rf: WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME  rf: WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME  rf: WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.values() ) {
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
     
 
