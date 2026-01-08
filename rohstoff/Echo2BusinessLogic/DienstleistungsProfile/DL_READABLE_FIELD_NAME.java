 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum DL_READABLE_FIELD_NAME {
	AKTIV(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.aktiv,										"aktiv",							40,40,		new Alignment(Alignment.CENTER, Alignment.TOP)),
    ANTEIL_MENGE(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.anteil_menge,						"Anteil an der Menge (1 = 100%)",	100,100,	new Alignment(Alignment.RIGHT, Alignment.TOP)),
    REF_MENGE_IST_LADE_MGE(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.ref_menge_ist_lade_mge,	"Referenz-Mge. ist Lademge.",		100,100,	new Alignment(Alignment.CENTER, Alignment.TOP)),
    ID_ADRESSE_START(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_adresse_start,				"Ladestation",						100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ADRESSE_ZIEL(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_adresse_ziel,					"Abladestation",		    		100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ADRESSE_FREMDWARE(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_adresse_fremdware,		"Besitzer Fremdware",		    	100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ADRESSE_BUCHUNGSLAGER(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_adresse_buchungslager,"Eigenes Buchungslager",			100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ADRESSE_RECHNUMG(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_adresse_rechnung,			"Rechnungsadresse",					100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ARTIKEL(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_artikel,							"Artikel",							100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ARTIKEL_BEZ_QUELLE(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_artikel_bez_quelle,		"Artikelbez (Ladeseite)",			100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ARTIKEL_BEZ_ZIEL(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_artikel_bez_ziel,			"Artikelbez (Abladeseite)",			100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ARTIKEL_BEZ_DL(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_artikel_bez_dl,				"Dienstleist.Art. Abrechnung",		100,300,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    TYP_MENGEN_CALC(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.typ_mengen_calc,					"Typ Kalkulation",					100,100,	new Alignment(Alignment.CENTER, Alignment.TOP)),
    UMRECH_MGE_QUELLE_ZIEL(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.umrech_mge_quelle_ziel,	"Umrechnungs-Faktor",				100,100,	new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_DIENSTLEISTUNG_PROFIL(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.id_dlp_profil,			"ID",								100,100,	new Alignment(Alignment.RIGHT, Alignment.TOP)),
    GILT_AB_DATUM(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.gilt_ab_datum,						"Ab Fuhren-Dat.",					100,100,	new Alignment(Alignment.CENTER, Alignment.TOP)),
    ANWENDEN_AUF_TYP(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.anwenden_auf_typ,				"Anwendung auf",					150,150,	new Alignment(Alignment.CENTER, Alignment.TOP)),
    BESCHREIBUNG(panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL.beschreibung,						"Beschreibung",				   		200,200,	new Alignment(Alignment.LEFT, Alignment.TOP)),
    ;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private DL_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private DL_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (DL_READABLE_FIELD_NAME  rf: DL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (DL_READABLE_FIELD_NAME  rf: DL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (DL_READABLE_FIELD_NAME  rf: DL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (DL_READABLE_FIELD_NAME  rf: DL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (DL_READABLE_FIELD_NAME  rf: DL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (DL_READABLE_FIELD_NAME  rf: DL_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (DL_READABLE_FIELD_NAME  rf: DL_READABLE_FIELD_NAME.values() ) {
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
     
 
