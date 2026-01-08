package rohstoff.businesslogic.bewegung2.lager_saldo;
  
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.indep.dataTools.IF_Field;
import nextapp.echo2.app.Alignment;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum B2_LAG_SALDO_READABLE_FIELD_NAME {
	
    FAX(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.fax,"fax",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    HAUSNUMMER(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.hausnummer,"hausnummer",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_ADRESSE(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_adresse,"id_adresse",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ADRESSE_BASIS(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_adresse_basis,"id_adresse_basis",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ADRESSE_BESITZ_LDG(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_adresse_besitz_ldg,"id_adresse_besitz_ldg",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BG_DEL_INFO(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_bg_del_info,"id_bg_del_info",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BG_STATION(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_bg_station,"id_bg_station",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BG_STORNO_INFO(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_bg_storno_info,"id_bg_storno_info",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BG_VEKTOR(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_bg_vektor,"id_bg_vektor",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_LAND(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_land,"id_land",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WIEGEKARTE(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.id_wiegekarte,"id_wiegekarte",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    KONTROLLMENGE(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.kontrollmenge,"kontrollmenge",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    NAME1(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.name1,"name1",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    NAME2(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.name2,"name2",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    NAME3(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.name3,"name3",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    OEFFNUNGSZEITEN(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.oeffnungszeiten,"oeffnungszeiten",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ORT(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.ort,"ort",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ORTZUSATZ(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.ortzusatz,"ortzusatz",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    PLZ(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.plz,"plz",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    POS_IN_MASK(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.pos_in_mask,"pos_in_mask",200,200,new Alignment(Alignment.LEFT, Alignment.TOP)),
    STRASSE(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.strasse,"strasse",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    TELEFON(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.telefon,"telefon",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    TIMESTAMP_IN_MASK(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.timestamp_in_mask,"timestamp_in_mask",200,200,new Alignment(Alignment.LEFT, Alignment.TOP)),
    UMSATZSTEUERID(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.umsatzsteuerid,"umsatzsteuerid",200,200,new Alignment(Alignment.LEFT, Alignment.TOP)),
    UMSATZSTEUERLKZ(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.umsatzsteuerlkz,"umsatzsteuerlkz",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    WIEGEKARTENKENNER(panter.gmbh.basics4project.DB_ENUMS.BG_STATION.wiegekartenkenner,"wiegekartenkenner",200,200,new Alignment(Alignment.LEFT, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private B2_LAG_SALDO_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private B2_LAG_SALDO_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (B2_LAG_SALDO_READABLE_FIELD_NAME  rf: B2_LAG_SALDO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (B2_LAG_SALDO_READABLE_FIELD_NAME  rf: B2_LAG_SALDO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (B2_LAG_SALDO_READABLE_FIELD_NAME  rf: B2_LAG_SALDO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (B2_LAG_SALDO_READABLE_FIELD_NAME  rf: B2_LAG_SALDO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (B2_LAG_SALDO_READABLE_FIELD_NAME  rf: B2_LAG_SALDO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (B2_LAG_SALDO_READABLE_FIELD_NAME  rf: B2_LAG_SALDO_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (B2_LAG_SALDO_READABLE_FIELD_NAME  rf: B2_LAG_SALDO_READABLE_FIELD_NAME.values() ) {
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
     
 
