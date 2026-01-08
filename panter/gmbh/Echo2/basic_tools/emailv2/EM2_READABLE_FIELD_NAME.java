 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_Enum4db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum EM2_READABLE_FIELD_NAME implements IF_Enum4db<EM2_READABLE_FIELD_NAME>{
	
    BETREFF(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.betreff,"Betreff",400,400,"Betreff",new Alignment(Alignment.LEFT, Alignment.TOP)
    				,new VEK<String>()
    				._a("Betreff kann Platzhalter enthalten,")
    				._a("die beim Versenden aufgelöst und gefüllt werden."))
    				,
    BETREFF_2_SEND(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.betreff_2_send,"Betreff (verschickt)",400,400,"Betreff (verschickt)",new Alignment(Alignment.LEFT, Alignment.TOP)
					,new VEK<String>()
					._a("Betreff mit gefüllten Platzhaltern,")
					._a("wie er verschickt wurde."))
					,
    ID_EMAIL_SEND(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.id_email_send,"ID",100,100,"ID",new Alignment(Alignment.RIGHT, Alignment.TOP),null),
    ID_TABLE(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.id_table,"ID-Referenz",100,100,"ID-Referenz",new Alignment(Alignment.CENTER, Alignment.TOP),null),
    SENDER_ADRESS(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.sender_adress,"Absender",400,400,"Absender",new Alignment(Alignment.LEFT, Alignment.TOP),null),
    SEND_TYPE(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.send_type,"Versand bei Mehrfachziel",100,100,"Versand bei Mehrfachziel",new Alignment(Alignment.CENTER, Alignment.TOP)
					,new VEK<String>()
					._a("Definiert, wie mehrere Ziele angeschrieben werden:")
					._a("Einzelversand: Es wird für jede Zieladresse eine eigene eMail erzeugt. ")
					._a("CC:            Es wird nur eine eMail mit offenen CC-Liste erzeugt. ")
					._a("BCC:           Es wird eine Blind-Carbon-Copy-Email erzeugt, d.h. die Zusatzadressaten sind verdeckt. "))
					,
   TABLE_BASE_NAME(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.table_base_name,"Referenz-Tabelle",400,400,"Referenz-Tabelle",new Alignment(Alignment.LEFT, Alignment.TOP),null),
    TEXT(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.text,"Text-Inhalt",400,400,"Text-Inhalt",new Alignment(Alignment.LEFT, Alignment.TOP)
			,new VEK<String>()
			._a("Text kann Platzhalter enthalten,")
			._a("die beim Versenden aufgelöst und gefüllt werden."))
			,
    TEXT_2_SEND(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.text_2_send,"Text-Inhalt (verschickt)",400,400,"Text-Inhalt (verschickt)",new Alignment(Alignment.LEFT, Alignment.TOP)
			,new VEK<String>()
			._a("Text mit gefüllten Platzhaltern,")
			._a("wie er verschickt wurde."))
			,
    EMAIL_TYPE(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.email_type,"eMail-Kennung",400,400,"eMail-Kennung",new Alignment(Alignment.LEFT, Alignment.TOP),null),
    EMAIL_VERIFICATION_KEY(panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND.email_verification_key,"Prüfschluessel",400,400,"Prüfschluessel",new Alignment(Alignment.LEFT, Alignment.TOP),null),

	;
	
	private IF_Field m_field = null;
	private String   m_readAbleForList = null;
	private String   m_readAbleForMask = null;
	private int      m_widthLabelList = 10;                  //breite fuer die labels
	private int      m_widthComponentList = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
    private VEK<String>  textsFuerHilfeButton = new VEK<String>();
    
	private EM2_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAbleForList = readAble;
	}
	
	private EM2_READABLE_FIELD_NAME(IF_Field f, String readAbleForList, int m_widthLabelList, int widthComponentList, String readAbleForMask, Alignment align, VEK<String> textVekHilfe) {
		this.m_field=f;
		this.m_readAbleForList = readAbleForList;
		this.m_readAbleForMask = readAbleForMask;
		this.m_widthLabelList = m_widthLabelList;
		this.m_widthComponentList = widthComponentList;
		this.m_align = align;
 		if (textVekHilfe!=null) {
			this.textsFuerHilfeButton._a(textVekHilfe);
		}
 	}
	
	
	
	public static String getReadableForList(IF_Field f) {
		String ret = "";
		
		for (EM2_READABLE_FIELD_NAME  rf: EM2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAbleForList;
			}
		}
		return ret;
	}
	
    public static String getListText(IF_Field f) {
      return EM2_READABLE_FIELD_NAME.getReadableForList(f);
    }
	
	public static String getReadableForMask(IF_Field f) {
		String ret = "";
		
		for (EM2_READABLE_FIELD_NAME  rf: EM2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAbleForMask;
			}
		}
		return ret;
	}
	
	public static String getMaskText(IF_Field f) {
      return EM2_READABLE_FIELD_NAME.getReadableForMask(f);
    }
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (EM2_READABLE_FIELD_NAME  rf: EM2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabelList;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (EM2_READABLE_FIELD_NAME  rf: EM2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponentList;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (EM2_READABLE_FIELD_NAME  rf: EM2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (EM2_READABLE_FIELD_NAME  rf: EM2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabelList >i) {
				i=rf.m_widthLabelList;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (EM2_READABLE_FIELD_NAME  rf: EM2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponentList >i) {
				i=rf.m_widthComponentList;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (EM2_READABLE_FIELD_NAME  rf: EM2_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				if (rf.m_widthLabelList>rf.m_widthComponentList) {
				   return rf.m_widthLabelList;
				} else {
				   return rf.m_widthComponentList;
				}
			}
		}
		return ret;
	}
	
    
    
    @Override
    public String db_val() {
        return m_field.fieldName();
    }
    @Override
    public String user_text() {
        return this.m_readAbleForMask;
    }
    @Override
    public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
        return bibENUM.dd_array(EM2_READABLE_FIELD_NAME.values(),emptyPairInFront);
    }
    @Override
    public EM2_READABLE_FIELD_NAME[] get_Values() {
		return values();
    }
	@Override
	public VEK<String> getHelpTextVek() {
		return textsFuerHilfeButton;
	}
}
     
 
