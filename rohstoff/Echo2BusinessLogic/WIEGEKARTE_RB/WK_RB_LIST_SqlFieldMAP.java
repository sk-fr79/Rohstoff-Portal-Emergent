 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WK_RB_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
    	
        super(_TAB.wiegekarte.n(), "", false);
        
        this.m_tpHashMap = p_tpHashMap;        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
        
		
		// Erstwägung
		this.add_JOIN_Table("JT_WAEGUNG", 
				"W1", 
				SQLFieldMAP.LEFT_OUTER, 
				":DATUM:ZEIT:LFDNR:GEWICHT:WAEGUNG_POS:HANDEINGABE:W_IDENT_NR:W_EINHEIT:HANDEINGABE_BEM:", 
				true, 
				"JT_WIEGEKARTE.ID_WIEGEKARTE=W1.ID_WIEGEKARTE AND W1.WAEGUNG_POS = 1 AND W1.STORNO is null", 
				"W1_", 
				null);

		// USER Erstwägung
		this.add_JOIN_Table("JD_USER", 
				"USER_W1", 
				SQLFieldMAP.LEFT_OUTER, 
				":name", 
				true, 
				"W1.ID_USER_WAEGUNG=USER_W1.ID_USER", 
				"UW1_", 
				null);

		
		// Zweitwägung
		this.add_JOIN_Table("JT_WAEGUNG", 
				"W2", 
				SQLFieldMAP.LEFT_OUTER, 
				":DATUM:ZEIT:LFDNR:GEWICHT:WAEGUNG_POS:HANDEINGABE:W_IDENT_NR:W_EINHEIT:HANDEINGABE_BEM:", 
				true, 
				"JT_WIEGEKARTE.ID_WIEGEKARTE=W2.ID_WIEGEKARTE AND W2.WAEGUNG_POS = 2 AND W2.STORNO is null", 
				"W2_", 
				null);
		
		
		
		// USER Zweitwägung
		this.add_JOIN_Table("JD_USER", 
				"USER_W2", 
				SQLFieldMAP.LEFT_OUTER, 
				":name", 
				true, 
				"W2.ID_USER_WAEGUNG=USER_W2.ID_USER", 
				"UW2_", 
				null);
		
		
		// Artikel
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR1:ARTBEZ:ID_ARTIKEL:", 
				true, 
				"JT_WIEGEKARTE.ID_ARTIKEL_SORTE=JT_ARTIKEL.ID_ARTIKEL", 
				"ART_", 
				null
				);

		// Artikel-Bez
		this.add_JOIN_Table("JT_ARTIKEL_BEZ", 
				"ART_BEZ", 
				SQLFieldMAP.LEFT_OUTER, 
				"", 
				true, 
				"JT_WIEGEKARTE.ID_ARTIKEL_BEZ=ART_BEZ.ID_ARTIKEL_BEZ", 
				"ART_", 
				bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(ART_BEZ.ANR2,'') || ' ' || NVL(ART_BEZ.ARTBEZ1,'') || NVL2(ART_BEZ.ARTBEZ2,' ' || ART_BEZ.ARTBEZ2,'')"));

		
		// Kundendaten
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_KUNDE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:"
				, true, 
				"JT_WIEGEKARTE.ID_ADRESSE_LIEFERANT=ADR_KUNDE.ID_ADRESSE", 
				"ADR_KUNDE_", 
			bibALL.get_HashMap("ADDRESS_INFO_KUNDE"," CASE WHEN ADR_KUNDE.ADRESSTYP = 1   THEN   NVL(ADR_KUNDE.NAME1,'') ||  NVL2(ADR_KUNDE.NAME2, ' ' || ADR_KUNDE.NAME2,'')|| NVL2(ADR_KUNDE.PLZ, ', ' || ADR_KUNDE.PLZ,'') ||  NVL2(ADR_KUNDE.ORT,' ' || ADR_KUNDE.ORT,'') " +
								"  ELSE    (" +
								" SELECT  NVL(A2.NAME1,'')|| NVL2(A2.NAME2, ' ' || A2.NAME2,'')|| NVL2(A2.PLZ, ', ' || A2.PLZ,'') ||  NVL2(A2.ORT,' ' || A2.ORT,'')  FROM  " + 
								bibE2.cTO() + " .JT_ADRESSE A2  WHERE  A2.ID_ADRESSE = (SELECT  L2.ID_ADRESSE_BASIS  FROM  " + 
								bibE2.cTO() + " .JT_LIEFERADRESSE L2 WHERE L2.ID_ADRESSE_LIEFER = ADR_KUNDE.ID_ADRESSE AND L2.ID_MANDANT = ADR_KUNDE.ID_MANDANT ) " +
								"  )   END" )) ;

		
		// Abnehmer Strecke
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_STRECKE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:"
				, true, 
				"JT_WIEGEKARTE.ID_ADRESSE_ABN_STRECKE=ADR_STRECKE.ID_ADRESSE", 
				"ADR_STRECKE_", 
			bibALL.get_HashMap("ADDRESS_INFO_STRECKE",
					" CASE WHEN ADR_STRECKE.ADRESSTYP = 1   THEN   NVL(ADR_STRECKE.NAME1,'') ||  NVL2(ADR_STRECKE.NAME2, ' ' || ADR_STRECKE.NAME2,'')|| NVL2(ADR_STRECKE.PLZ, ', ' || ADR_STRECKE.PLZ,'') ||  NVL2(ADR_STRECKE.ORT,' ' || ADR_STRECKE.ORT,'') " +
					"  ELSE    (" +
					" SELECT  NVL(A2.NAME1,'')|| NVL2(A2.NAME2, ' ' || A2.NAME2,'') FROM  " + 
					bibE2.cTO() + " .JT_ADRESSE A2  WHERE  A2.ID_ADRESSE = (SELECT  L2.ID_ADRESSE_BASIS  FROM  " + 
					bibE2.cTO() + " .JT_LIEFERADRESSE L2 WHERE L2.ID_ADRESSE_LIEFER = ADR_STRECKE.ID_ADRESSE AND L2.ID_MANDANT = ADR_STRECKE.ID_MANDANT ) " +
					"  )   END" ));
//					"NVL(ADR_STRECKE.NAME1,'') ||  NVL2(ADR_STRECKE.NAME2, ' ' || ADR_STRECKE.NAME2,'')|| NVL2(ADR_STRECKE.PLZ, ', ' || ADR_STRECKE.PLZ,'') ||  NVL2(ADR_STRECKE.ORT,' ' || ADR_STRECKE.ORT,'')"));
		
		// Spedition
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_SPED", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:"
				, true, 
				"JT_WIEGEKARTE.ID_ADRESSE_SPEDITION=ADR_SPED.ID_ADRESSE", 
				"ADR_SPED_", 
			bibALL.get_HashMap("ADDRESS_INFO_SPED", " CASE WHEN ADR_SPED.ADRESSTYP = 1   THEN   NVL(ADR_SPED.NAME1,'') ||  NVL2(ADR_SPED.NAME2, ' ' || ADR_SPED.NAME2,'')|| NVL2(ADR_SPED.PLZ, ', ' || ADR_SPED.PLZ,'') ||  NVL2(ADR_SPED.ORT,' ' || ADR_SPED.ORT,'') " +
					"  ELSE    (" +
					" SELECT  NVL(A2.NAME1,'')|| NVL2(A2.NAME2, ' ' || A2.NAME2,'') FROM  " + 
					bibE2.cTO() + " .JT_ADRESSE A2  WHERE  A2.ID_ADRESSE = (SELECT  L2.ID_ADRESSE_BASIS  FROM  " + 
					bibE2.cTO() + " .JT_LIEFERADRESSE L2 WHERE L2.ID_ADRESSE_LIEFER = ADR_SPED.ID_ADRESSE AND L2.ID_MANDANT = ADR_SPED.ID_MANDANT ) " +
					"  )   END"));
		
		this.add_SQLField(new SQLField("case when nvl(JT_WIEGEKARTE.IST_GESAMTVERWIEGUNG,'N') = 'Y' then 'Gesamtverwiegung' end ", 
					"INFO_GESAMTVERWIEGUNG", 
					new MyE2_String("Gesamtverwiegung"), 
					bibE2.get_CurrSession()),true);

		
		
		// Befundung
		this.add_JOIN_Table("JT_WIEGEKARTE_BEFUND", 
						"WK_BEF", 
						SQLFieldMAP.LEFT_OUTER, 
						":BEMERKUNG:SORTIERUNG:ANALYSE:NAESSEPROBE:"
						, true, 
						"JT_WIEGEKARTE.ID_WIEGEKARTE=WK_BEF.ID_WIEGEKARTE", 
						"WK_BEF_",
						bibALL.get_HashMap( "WK_BE_BEMERKUNG", 	"NVL(WK_BEF.BEMERKUNG,'-')",
											"WK_BE_SORTIERUNG", 	"CASE WHEN NVL(WK_BEF.SORTIERUNG,'N') = 'N' THEN '-' ELSE to_char('Sortierung') END",
											"WK_BE_ANALYSE",		"CASE WHEN NVL(WK_BEF.ANALYSE,'N') = 'N' THEN '-' ELSE to_char('Analyse') END",
											"WK_BE_NAESSEPROBE",	"CASE WHEN NVL(WK_BEF.NAESSEPROBE,'N') = 'N' THEN '-' ELSE to_char('Nässeprobe') END")
						);
						
		
		this.add_JOIN_Table("JT_WIEGEKARTE_USER_BEFUND", 
						"WUB", 
						SQLFieldMAP.LEFT_OUTER, 
						""
						, true, 
						"WK_BEF.ID_WIEGEKARTE_USER_BEFUND = WUB.ID_WIEGEKARTE_USER_BEFUND", 
						"WUB_",
						null);

		this.add_JOIN_Table("JD_USER", 
				"USER_BEF", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:VORNAME:NAME:KUERZEL:"
				, true, 
				"USER_BEF.ID_USER = WUB.ID_USER", 
				"USER_BEF_",
				bibALL.get_HashMap("BEFUNDUNG_USER", "NVL2(USER_BEF.VORNAME, USER_BEF.VORNAME,'') || NVL2(USER_BEF.NAME1,' ' || USER_BEF.NAME1,'') ||  NVL2(USER_BEF.KUERZEL, ' (' || NVL(USER_BEF.KUERZEL,'') || ')','-')")
				);
		
		this.add_SQLField(new SQLField("TO_CHAR(JT_WIEGEKARTE.STORNIERT_AM,'DD.MM.YYYY HH24:MI') || NVL2(JT_WIEGEKARTE.STORNIERT_VON,' VON ' || JT_WIEGEKARTE.STORNIERT_VON,'')", 
				"INFO_STORNO", 
				new MyE2_String("Info Storno"), 
				bibE2.get_CurrSession()),true);

		
		// Befundung
		this.add_JOIN_Table("JT_CONTAINER", 
						"WK_CONT", 
						SQLFieldMAP.LEFT_OUTER, 
						":BEMERKUNG:SORTIERUNG:ANALYSE:NAESSEPROBE:"
						, true, 
						"JT_WIEGEKARTE.ID_CONTAINER_EIGEN=WK_CONT.ID_CONTAINER", 
						"WK_CONT_",
						bibALL.get_HashMap( "WK_CONT_ID_CONTAINER", 	"WK_CONT.ID_CONTAINER",
											"WK_CONT_CONTAINER_NR", 	"NVL(WK_CONT.CONTAINER_NR,'-')",
											"WK_CONT_UVV_DATUM",		"NVL2(WK_CONT.UVV_DATUM,to_char(WK_CONT.UVV_DATUM,'dd.MM.yyyy') ,'-')"
										   )
						);
						
		
		
		/*
		 * 
		 * 
		 * 
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BEFUND").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE_LAGER").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE_LIEFERANT").set_cDefaultValueFormated("");
		this.get_("ID_ARTIKEL_SORTE").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_cDefaultValueFormated("");
		this.get_("ID_WIEGEKARTE").set_cDefaultValueFormated("");
		this.get_("IST_LIEFERANT").set_cDefaultValueFormated("");
		this.get_("KENNZEICHEN").set_cDefaultValueFormated("");
		this.get_("LFD_NR").set_cDefaultValueFormated("");
		this.get_("NETTOGEWICHT").set_cDefaultValueFormated("");
		this.get_("ADRESSE_LIEFERANT").set_cDefaultValueFormated("");
		
			
		this.add_ORDER_SEGMENT("JT_WIEGEKARTE.LFD_NR DESC");
        
        
        
        this.initFields();
    }
    
}
 
 
