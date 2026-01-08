 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum LH_P_READABLE_FIELD_NAME {
	
    CHARGENNUMMER(			LAGER_PALETTE.chargennummer,			"Chargennr.",			100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    DATUM_VERARBEITET(		LAGER_PALETTE.datum_verarbeitet,		"Verarbeitet am",		100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_ARTIKEL_BEZ(			LAGER_PALETTE.id_artikel_bez,			"Material",				300,300,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
    ID_LAGER_BOX(			LAGER_PALETTE.id_lager_box,				"Box Nr.",				100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    ID_LAGER_PALETTE(		LAGER_PALETTE.id_lager_palette,			"Palette id",			100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    ID_VPOS_TPA_FUHRE_AUS(	LAGER_PALETTE.id_vpos_tpa_fuhre_aus,	"Ausbuchungsfuhre",		100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    ID_VPOS_TPA_FUHRE_EIN(	LAGER_PALETTE.id_vpos_tpa_fuhre_ein,	"Einbuchungsfuhre",		100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    IST_PALETTE(			LAGER_PALETTE.ist_palette,				"Palette",				30, 30, new Alignment(Alignment.CENTER, Alignment.TOP)),
    GEPRESST_VON(			LAGER_PALETTE.gepresst_von,				"Gepresst von",			100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    PALETTIERT_VON(			LAGER_PALETTE.palettiert_von,			"Palettiert von",		100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ENDKONTROLLE_VON(		LAGER_PALETTE.endkontrolle_von,			"Endkontrolle von",		100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    NETTOMENGE(				LAGER_PALETTE.nettomenge,				"Menge netto",			100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    BRUTTOMENGE(			LAGER_PALETTE.bruttomenge,				"Menge brutto",			100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    TARAMENGE(				LAGER_PALETTE.taramenge,				"Taramenge",			100,100,new Alignment(Alignment.RIGHT, 	Alignment.TOP)),
    EINBUCHUNG_HAND(		LAGER_PALETTE.einbuchung_hand,			"Einb. Hand",			30,30,	new Alignment(Alignment.CENTER, Alignment.TOP)),
    AUSBUCHUNG_HAND(		LAGER_PALETTE.ausbuchung_hand,			"Ausb. Hand",			30,30,	new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_ADRESSE_EINBUCH_HAND(LAGER_PALETTE.id_adresse_einbuch_hand,  "Einb. Hand Lieferant", 100,100, new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_ADRESSE_AUSBUCH_HAND(LAGER_PALETTE.id_adresse_ausbuch_hand,  "Ausb. Hand Abnehmer", 100,100, new Alignment(Alignment.CENTER, Alignment.TOP)),
    BUCHUNGSNR_HAND(		LAGER_PALETTE.buchungsnr_hand,			"Buchungsnr.",			100,100,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
    BUCHUNGSNR(				VPOS_TPA_FUHRE.buchungsnr_fuhre,		"Buchungsnr.",			100,100,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
    ZUSATZ_INFO(			LAGER_PALETTE.material_zusatzinfo,		"Material Zusatzinfo",	100,100,new Alignment(Alignment.LEFT, 	Alignment.TOP)),
    AUSBUCH_BEMERKUNG(		LAGER_PALETTE.hand_ausbuchung_bemerkung,"Ausbuchung Bem.", 100,100,new Alignment(Alignment.LEFT, 	Alignment.TOP))
    ;
	
	private IF_Field m_field 			= null;
	private String   m_readAble 		= null;
	private int      m_widthLabel 		= 10;                  
	private int      m_widthComponent 	= 10;              
	private Alignment m_align 			= new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private LH_P_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private LH_P_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (LH_P_READABLE_FIELD_NAME  rf: LH_P_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (LH_P_READABLE_FIELD_NAME  rf: LH_P_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (LH_P_READABLE_FIELD_NAME  rf: LH_P_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (LH_P_READABLE_FIELD_NAME  rf: LH_P_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (LH_P_READABLE_FIELD_NAME  rf: LH_P_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (LH_P_READABLE_FIELD_NAME  rf: LH_P_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (LH_P_READABLE_FIELD_NAME  rf: LH_P_READABLE_FIELD_NAME.values() ) {
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
     
 
