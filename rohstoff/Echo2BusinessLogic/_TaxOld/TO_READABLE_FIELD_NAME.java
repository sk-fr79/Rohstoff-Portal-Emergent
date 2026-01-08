 
package rohstoff.Echo2BusinessLogic._TaxOld;
  
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL;
import panter.gmbh.indep.dataTools.IF_Field;
import nextapp.echo2.app.Alignment;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum TO_READABLE_FIELD_NAME {
	
    BEZEICHNUNG(			panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL.bezeichnung,			"Bezeichner",	400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_LAND(				panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL.id_land,				"Land",			100,100,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_MWSTSCHLUESSEL(		panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL.id_mwstschluessel,	"ID",			100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    IST_STANDARD(			panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL.ist_standard,		"Std.",			30,30,	new Alignment(Alignment.CENTER, Alignment.TOP)),
    KURZBEZEICHNUNG(		panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL.kurzbezeichnung,		"Kurz",			200,200,new Alignment(Alignment.LEFT, Alignment.TOP)),
    STEUERSATZ(				panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL.steuersatz,			"Steuersatz (%)",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private TO_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private TO_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (TO_READABLE_FIELD_NAME  rf: TO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (TO_READABLE_FIELD_NAME  rf: TO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (TO_READABLE_FIELD_NAME  rf: TO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (TO_READABLE_FIELD_NAME  rf: TO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (TO_READABLE_FIELD_NAME  rf: TO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (TO_READABLE_FIELD_NAME  rf: TO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (TO_READABLE_FIELD_NAME  rf: TO_READABLE_FIELD_NAME.values() ) {
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
     
 
