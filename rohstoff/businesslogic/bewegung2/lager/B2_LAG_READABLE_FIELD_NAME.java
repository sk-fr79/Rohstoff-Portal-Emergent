 
package rohstoff.businesslogic.bewegung2.lager;
  
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.indep.dataTools.IF_Field;
import nextapp.echo2.app.Alignment;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum B2_LAG_READABLE_FIELD_NAME {
	
    ANR1(						BG_ATOM.anr1,						"anr1",							100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ANR2(						BG_ATOM.anr2,						"anr2",							100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    ARTBEZ1(					BG_ATOM.artbez1,					"artbez1",						400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ARTBEZ2(					BG_ATOM.artbez2,					"artbez2",						400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    DATUM_AUSFUEHRUNG(			BG_ATOM.datum_ausfuehrung,			"Datum",						100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    E_PREIS_BASISWAEHRUNG(		BG_ATOM.e_preis_basiswaehrung,		"Einzelpreis",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    E_PREIS_FREMDWAEHRUNG(		BG_ATOM.e_preis_fremdwaehrung,		"e_preis_fremdwaehrung",		100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    E_PREIS_RES_NETTO_MGE_BASIS(BG_ATOM.e_preis_res_netto_mge_basis,"e_preis_res_netto_mge_basis",	100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    E_PREIS_RES_NETTO_MGE_FREMD(BG_ATOM.e_preis_res_netto_mge_fremd,"e_preis_res_netto_mge_fremd",	100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ARTIKEL(					BG_ATOM.id_artikel,					"Id Artikel",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ARTIKEL_BEZ(				BG_ATOM.id_artikel_bez,				"id_artikel_bez",				100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BG_ATOM(					BG_ATOM.id_bg_atom,					"ID Atom",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BG_STATION_QUELLE(		BG_ATOM.id_bg_station_quelle,		"id_bg_station_quelle",			100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BG_STATION_ZIEL(			BG_ATOM.id_bg_station_ziel,			"id_bg_station_ziel",			100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_BG_VEKTOR(				BG_ATOM.id_bg_vektor,				"id_bg_vektor",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WAEHRUNG(				BG_ATOM.id_waehrung,				"id_waehrung",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    MENGE(						BG_ATOM.menge,						"Menge Brutto",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    MENGE_ABRECH(				BG_ATOM.menge_abrech,				"menge_abrech",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    MENGE_ABZUG(				BG_ATOM.menge_abzug,				"Mengeabzug",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    MENGE_NETTO(				BG_ATOM.menge_netto,				"Menge Netto",					100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    MENGE_VERTEILUNG(			BG_ATOM.menge_verteilung,			"menge_verteilung",				100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    WAEHRUNGSKURS(				BG_ATOM.waehrungskurs,				"Waehrungskurs",				100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private B2_LAG_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private B2_LAG_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (B2_LAG_READABLE_FIELD_NAME  rf: B2_LAG_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (B2_LAG_READABLE_FIELD_NAME  rf: B2_LAG_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (B2_LAG_READABLE_FIELD_NAME  rf: B2_LAG_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (B2_LAG_READABLE_FIELD_NAME  rf: B2_LAG_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (B2_LAG_READABLE_FIELD_NAME  rf: B2_LAG_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (B2_LAG_READABLE_FIELD_NAME  rf: B2_LAG_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (B2_LAG_READABLE_FIELD_NAME  rf: B2_LAG_READABLE_FIELD_NAME.values() ) {
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
     
 
