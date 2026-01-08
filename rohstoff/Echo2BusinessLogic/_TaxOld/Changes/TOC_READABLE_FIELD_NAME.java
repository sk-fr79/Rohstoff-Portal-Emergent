 
package rohstoff.Echo2BusinessLogic._TaxOld.Changes;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum TOC_READABLE_FIELD_NAME {
	
    BESCHREIBUNG(panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN.beschreibung,					"Hintergrund der Änderung",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    GUELTIG_VON(panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von,						"Gültig von",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    GUELTIG_BIS(panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis,						"Gültig bis",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_MWSTSCHLUESSEL(panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel,			"ID_MWST",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_MWSTSCHLUESSEL_AENDERUNGEN(panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen,"ID",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    STEUERSATZ(panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN.steuersatz,						"Steuersatz",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private TOC_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private TOC_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (TOC_READABLE_FIELD_NAME  rf: TOC_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (TOC_READABLE_FIELD_NAME  rf: TOC_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (TOC_READABLE_FIELD_NAME  rf: TOC_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (TOC_READABLE_FIELD_NAME  rf: TOC_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (TOC_READABLE_FIELD_NAME  rf: TOC_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (TOC_READABLE_FIELD_NAME  rf: TOC_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (TOC_READABLE_FIELD_NAME  rf: TOC_READABLE_FIELD_NAME.values() ) {
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
     
 
