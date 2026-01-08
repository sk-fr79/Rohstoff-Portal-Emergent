 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import nextapp.echo2.app.Alignment;
import panter.gmbh.indep.dataTools.IF_Field;
  
  
/*
 * enum: hier koennen die lesbaren feldueberschriften erfasst werden, die dann in liste und maske eingeblendet werden 
 */
public enum WK_RB_READABLE_FIELD_NAME {
	
    ADRESSE_LIEFERANT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.adresse_lieferant,"Kunde",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ADRESSE_SPEDITION(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.adresse_spedition,"Spedition",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ANZ_ALLG(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.anz_allg,"Anzahl",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ANZ_BEHAELTER(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.anz_behaelter,"Anzahl Behälter",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ANZ_BIGBAGS(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.anz_bigbags,"Anzahl BigBags",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ANZ_GITTERBOXEN(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.anz_gitterboxen,"Anzahl Gitterboxen",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ANZ_PALETTEN(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.anz_paletten,"Anzahl Paletten",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    BEFUND(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.befund,"Befund",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    BEMERKUNG1(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.bemerkung1,"Bemerkung Wiegekarte",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    BEMERKUNG2(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.bemerkung2,"Bemerkung 2",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    BEMERKUNG_INTERN(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.bemerkung_intern,"Bemerkung Eingangsschein",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    BEZ_ALLG(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.bez_allg,"bez_allg",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    CONTAINER_NR(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.container_nr,"Container Nr",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    DRUCKZAEHLER(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.druckzaehler,"Druckzähler",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    DRUCKZAEHLER_EINGANGSSCHEIN(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.druckzaehler_eingangsschein,"Druckzähler Eingangsschein",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ES_NR(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.es_nr,"EingangsscheinNr",200,200,new Alignment(Alignment.LEFT, Alignment.TOP)),
    GEDRUCKT_AM(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.gedruckt_am,"Gedruckt am",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    GEWICHT_ABZUG(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.gewicht_abzug,"Abzug Gewicht",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    GEWICHT_ABZUG_FUHRE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.gewicht_abzug_fuhre,"Abzug Fuhre",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    GEWICHT_NACH_ABZUG(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.gewicht_nach_abzug,"Gewicht nach Abzug",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    GEWICHT_NACH_ABZUG_FUHRE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.gewicht_nach_abzug_fuhre,"Gewicht nach Abzug Fuhre",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    GRUND_ABZUG(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.grund_abzug,"Abzugsgrund",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    ID_ADRESSE_ABN_STRECKE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_adresse_abn_strecke,"Abnehmer Strecke",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ADRESSE_LAGER(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_adresse_lager,"ID Adresse Lager",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ADRESSE_LIEFERANT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_adresse_lieferant,"Adresse",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ADRESSE_SPEDITION(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_adresse_spedition,"Spedition",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ARTIKEL_BEZ(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_artikel_bez,"ID Artikelbez",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_ARTIKEL_SORTE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_artikel_sorte,"ID Artikel",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_VPOS_TPA_FUHRE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_vpos_tpa_fuhre,"ID Fuhre",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_VPOS_TPA_FUHRE_ORT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_vpos_tpa_fuhre_ort,"ID FuhreOrt",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WAAGE_STANDORT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_waage_standort,"ID Waage Standort",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WIEGEKARTE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_wiegekarte,"ID Wiegekarte",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    ID_WIEGEKARTE_PARENT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.id_wiegekarte_parent,"ID WK Parent",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    IST_GESAMTVERWIEGUNG(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.ist_gesamtverwiegung,"Gesamtverwiegung",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    IST_LIEFERANT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.ist_lieferant,"IstLieferant",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    KENNZEICHEN(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.kennzeichen,"Kennzeichen",200,200,new Alignment(Alignment.LEFT, Alignment.TOP)),
    LFD_NR(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.lfd_nr,"WiegekartenNr",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    NETTOGEWICHT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.nettogewicht,"Nettogewicht",100,100,new Alignment(Alignment.RIGHT, Alignment.TOP)),
    SIEGEL_NR(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.siegel_nr,"Siegel Nr.",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    SORTE_HAND(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.sorte_hand,"Sorte Hand",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    STORNO(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.storno,"Storno",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    STRAHLUNG_GEPRUEFT(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.strahlung_geprueft,"Radioaktivität geprüft",30,30,new Alignment(Alignment.CENTER, Alignment.TOP)),
    SYS_TRIGGER_TIMESTAMP(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.sys_trigger_timestamp,"sys_trigger_timestamp",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
    SYS_TRIGGER_UUID(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.sys_trigger_uuid,"sys_trigger_uuid",400,400,new Alignment(Alignment.LEFT, Alignment.TOP)),
    TYP_WIEGEKARTE(panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE.typ_wiegekarte,"Typ Wiegekarte",100,100,new Alignment(Alignment.CENTER, Alignment.TOP)),
	;
	
	private IF_Field m_field = null;
	private String   m_readAble = null;
	private int      m_widthLabel = 10;                  //breite fuer die labels
	private int      m_widthComponent = 10;              //breite fuer die komponente
	private Alignment m_align = new Alignment(Alignment.CENTER, Alignment.CENTER);   
	
	private WK_RB_READABLE_FIELD_NAME(IF_Field f, String readAble) {
		this.m_field=f;
		this.m_readAble = readAble;
	}
	
	private WK_RB_READABLE_FIELD_NAME(IF_Field f, String readAble, int widthLabel, int widthComponent, Alignment align) {
		this.m_field=f;
		this.m_readAble = readAble;
		this.m_widthLabel = widthLabel;
		this.m_widthComponent = widthComponent;
		this.m_align = align;
	}
	
	
	
	public static String getReadable(IF_Field f) {
		String ret = "";
		
		for (WK_RB_READABLE_FIELD_NAME  rf: WK_RB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_readAble;
			}
		}
		return ret;
	}
	
	
	public static int getLabelWidth(IF_Field f) {
		int ret = 10;
		
		for (WK_RB_READABLE_FIELD_NAME  rf: WK_RB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthLabel;
			}
		}
		return ret;
	}
    
	public static int getComponentWidth(IF_Field f) {
		int ret = 10;
		
		for (WK_RB_READABLE_FIELD_NAME  rf: WK_RB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_widthComponent;
			}
		}
		return ret;
	}
    
	
	public static Alignment getAlignment(IF_Field f) {
		Alignment ret = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		for (WK_RB_READABLE_FIELD_NAME  rf: WK_RB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_field == f) {
				return rf.m_align;
			}
		}
		return ret;
	}
    
	
	public static int getMaxLabelWidth() {
		int i = 10;
		for (WK_RB_READABLE_FIELD_NAME  rf: WK_RB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthLabel >i) {
				i=rf.m_widthLabel;
			}
		}
		return i;
	}
	
	public static int getMaxComponentWidth() {
		int i = 10;
		for (WK_RB_READABLE_FIELD_NAME  rf: WK_RB_READABLE_FIELD_NAME.values() ) {
			if (rf.m_widthComponent >i) {
				i=rf.m_widthComponent;
			}
		}
		return i;
	}
	
    //sucht die groessere breite zwischen label und componenten-breite aus	
    public static int getMaxComponentOrLabelSize(IF_Field f) {
	    int ret = 10;
    		
		for (WK_RB_READABLE_FIELD_NAME  rf: WK_RB_READABLE_FIELD_NAME.values() ) {
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
     
 
