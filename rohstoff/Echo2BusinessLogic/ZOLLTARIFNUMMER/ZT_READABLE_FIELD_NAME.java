 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum ZT_READABLE_FIELD_NAME {
	
    AKTIV(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.aktiv,"Aktiv","Akt.",30,30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    BM_NUMMER(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.bm_nummer,"Einheit-Nr.","EH-Nr",100,100, new Alignment(Alignment.RIGHT, Alignment.TOP)),
    BM_TEXT(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.bm_text,"Einheit",null,80,80, new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_ZOLLTARIFNUMMER(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.id_zolltarifnummer,"ID-Zolltarifnr","ID",100,100, new Alignment(Alignment.RIGHT, Alignment.TOP)),
    LETZTER_IMPORT(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.letzter_import,"Import am",null,100,100, new Alignment(Alignment.CENTER, Alignment.TOP)),
    NUMMER(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.nummer,"Zolltarifnummer",null,120,120, new Alignment(Alignment.LEFT, Alignment.TOP)),
    REVERSE_CHARGE(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.reverse_charge,"Reverse-Charge (D)","RC",40,40, new Alignment(Alignment.CENTER, Alignment.TOP)),
    TEXT1(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.text1,"Textzeile 1",null,400,400, new Alignment(Alignment.LEFT, Alignment.TOP)),
    TEXT2(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.text2,"Textzeile 2",null,400,400, new Alignment(Alignment.LEFT, Alignment.TOP)),
    TEXT3(panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER.text3,"Textzeile 3",null,400,400, new Alignment(Alignment.LEFT, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private String   m_listHeader = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
//	private ZT_READABLE_FIELD_NAME(IF_Field f, String readAble) {
//		this.m_field=f;
//		this.m_readAble = readAble;
//	}
	
	private ZT_READABLE_FIELD_NAME(IF_Field f, String listHeader, String maskLabel, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = listHeader;
		this.m_listHeader = maskLabel;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (ZT_READABLE_FIELD_NAME  rf: ZT_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (ZT_READABLE_FIELD_NAME  rf: ZT_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (ZT_READABLE_FIELD_NAME  rf: ZT_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (ZT_READABLE_FIELD_NAME  rf: ZT_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (ZT_READABLE_FIELD_NAME  rf: ZT_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (ZT_READABLE_FIELD_NAME  rf: ZT_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (ZT_READABLE_FIELD_NAME  rf: ZT_READABLE_FIELD_NAME.values() ) {
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



	/**
	 * @return the m_maskText
	 */
	public static String getListHeader(IF_Field f) {
		for (ZT_READABLE_FIELD_NAME  rf: ZT_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return S.NN(rf.m_listHeader,rf.m_readAble);
			}
		}
		return f.fieldName();		
	}
	
}
     
 
