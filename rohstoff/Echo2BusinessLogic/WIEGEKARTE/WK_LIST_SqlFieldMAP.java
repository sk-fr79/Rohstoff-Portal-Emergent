package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class WK_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public WK_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_WIEGEKARTE", "", false);
		
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
				//bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')")
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
//					"NVL(ADR_KUNDE.NAME1,'') ||  NVL2(ADR_KUNDE.NAME2, ' ' || ADR_KUNDE.NAME2,'')|| NVL2(ADR_KUNDE.PLZ, ', ' || ADR_KUNDE.PLZ,'') ||  NVL2(ADR_KUNDE.ORT,' ' || ADR_KUNDE.ORT,'')"));

		
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
					
//			"NVL(ADR_SPED.NAME1,'') ||  NVL2(ADR_SPED.NAME2, ' ' || ADR_SPED.NAME2,'')|| NVL2(ADR_SPED.PLZ, ', ' || ADR_SPED.PLZ,'') ||  NVL2(ADR_SPED.ORT,' ' || ADR_SPED.ORT,'')"));
	
		
//		this.add_SQLField(new SQLField( " (SELECT F.ID_VPOS_TPA_FUHRE as ID_FUHRE_FUHRE " +
//				" FROM JT_WIEGEKARTE W JOIN   JT_VPOS_TPA_FUHRE F ON W.ID_WIEGEKARTE = F.ID_WIEGEKARTE_LIEF " +
//				" OR W.ID_WIEGEKARTE = F.ID_WIEGEKARTE_ABN " +
//				" where W.id_wiegekarte = JT_WIEGEKARTE.ID_WIEGEKARTE " +
//				" ) " , "ID_FUHRE_FUHRE", new MyE2_String("Id Fuhre"), bibE2.get_CurrSession()), true);
//		
//		
//		this.add_SQLField(new SQLField( " (SELECT  O.ID_VPOS_TPA_FUHRE_ORT as ID_FUHRE_ORT_FUHRE " +
//				" FROM JT_WIEGEKARTE W JOIN   JT_VPOS_TPA_FUHRE_ORT O ON W.ID_WIEGEKARTE = O.ID_WIEGEKARTE " +
//				" where W.id_wiegekarte = JT_WIEGEKARTE.ID_WIEGEKARTE " +
//				") " , "ID_FUHRE_ORT_FUHRE", new MyE2_String("Id Fuhrenort"), bibE2.get_CurrSession()), true);

		
		
		this.add_SQLField(new SQLField("case when nvl(JT_WIEGEKARTE.IST_GESAMTVERWIEGUNG,'N') = 'Y' then 'Gesamtverwiegung' end ", 
					"INFO_GESAMTVERWIEGUNG", 
					new MyE2_String("Gesamtverwiegung"), 
					bibE2.get_CurrSession()),true);

		/*
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
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_WIEGEKARTE_WICHTIGKEIT","BESCHREIBUNG","ID_WK_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_WK_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_WIEGEKARTE.ID_USER="+cID_USER+" OR JT_WIEGEKARTE.ID_WK IN (SELECT ID_WK FROM "+bibE2.cTO()+".JT_WIEGEKARTE_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		
		this.add_ORDER_SEGMENT("JT_WIEGEKARTE.LFD_NR DESC");
		
		this.initFields();
	}
	
}
