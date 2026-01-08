 
package panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum TLV_READABLE_FIELD_NAME {
	
    BESCHREIBUNG_LANG(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE_VORLAGE.beschreibung_lang,"Beschreibung der Vorlage",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    BEZEICHNUNG(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE_VORLAGE.bezeichnung,"Bezeichnung der Vorlage",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_TEXT_LISTE_VORLAGE(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE_VORLAGE.id_text_liste_vorlage,"ID",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    TABLENAME_4_TEXTLISTE(panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE_VORLAGE.tablereference,"Tabellen-Name / Referenz",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private TLV_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private TLV_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (TLV_READABLE_FIELD_NAME  rf: TLV_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (TLV_READABLE_FIELD_NAME  rf: TLV_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (TLV_READABLE_FIELD_NAME  rf: TLV_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (TLV_READABLE_FIELD_NAME  rf: TLV_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (TLV_READABLE_FIELD_NAME  rf: TLV_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (TLV_READABLE_FIELD_NAME  rf: TLV_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (TLV_READABLE_FIELD_NAME  rf: TLV_READABLE_FIELD_NAME.values() ) {
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
     
 
