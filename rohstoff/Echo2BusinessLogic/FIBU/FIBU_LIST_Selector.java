package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.HashMap;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_CONTAINER;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_MONATJAHR_bis_MONATJAHR;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_SelectField_Zahlungsbedingungen;
import panter.gmbh.Echo2.components.E2_CalendarComponent_WithDayButtons;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;



public class FIBU_LIST_Selector extends E2_ListSelectorContainer 
{

	private static final String       AUSWAHLTYP_OFFENE = 					"OFFENE";
	private static final String       AUSWAHLTYP_OFFENE_UND_GESCHLOSSENE = 	"OFFENE_UND_GESCHLOSSENE";
	private static final String       AUSWAHLTYP_OFFENE_UND_STORNOS = 		"OFFENE_UND_STORNOS";
	private static final String       AUSWAHLTYP_ALLE = 					"ALLE";
	private static final String       AUSWAHLTYP_ALLE_UNSTORNIERT = 		"ALLE_UNSTORNIERT";
	private static final String       AUSWAHLTYP_NUR_STORNOS = 				"NUR_STORNOS";
	private static final String       AUSWAHLTYP_NUR_GESCHLOSSENE = 		"NUR_GESCHLOSSENE";
	private static final String       AUSWAHLTYP_MAHNUNGSEINTRAEGE = 		"MAHNUNGSEINTRAEGE";
	
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 			oSelVector = null;
	
	private SELECTOR_COMPONENT_FirmenAuswahl 		oSelKundenMitPositionen = null; 

	private MyE2_SelectField                        oSelBelegTyp = null;
	private MyE2_SelectField                        oSelAuswahl = null;

	private SELECTOR_MONATJAHR_bis_MONATJAHR        oSelDatumBereichErstellung = null;



	private SELECTOR_MONATJAHR_bis_MONATJAHR        oSelDatumBereichFaelligkeit = null;
	private SELECTOR_MONATJAHR_bis_MONATJAHR        oSelDatumBereich_RechDruck_Erstellung = null;



	private E2_SelektorDateFromTo_NG                oSelBereichDatumFaelligkeit  = null;
	
	private ownSelektorLeistungsdatum  				oSelLeistungsdatum = new ownSelektorLeistungsdatum();
	
	private Component_USER_DROPDOWN_NEW     		oSelectHaendler = 		new Component_USER_DROPDOWN_NEW(false, 290);

	private MyE2_CheckBox  							oCB_BelegeMitStornos = 	new MyE2_CheckBox("", new MyE2_String("Wählt nur Belege aus, die mindestens eine Stornoposition beinhalten "));  
	
	private MyE2_CheckBox  							oCB_KundenMitScheck = 	new MyE2_CheckBox("", new MyE2_String("Wählt nur Belege aus, die zu Kunden mit dem Merkmal <Scheckdruck> gehören"));
	private MyE2_CheckBox  							oCB_KundenAkonto = 		new MyE2_CheckBox("", new MyE2_String("Wählt nur Belege aus, die zu Kunden mit dem Merkmal <Akonto> gehören"));
	private MyE2_CheckBox  							oCB_KundenBar = 		new MyE2_CheckBox("", new MyE2_String("Wählt nur Belege aus, die zu Kunden mit dem Merkmal <Barkunde> gehören"));
	private MyE2_CheckBox  							oCB_KundenNichtBar = 	new MyE2_CheckBox("", new MyE2_String("Wählt nur Belege aus, die zu Kunden OHNE das Merkmal <Barkunde> gehören"));
	private MyE2_CheckBox  							oCB_Verrechnung = 		new MyE2_CheckBox("", new MyE2_String("Wählt nur Belege aus, die zur Forderungsverrechnung ZUGESTIMMT haben"));
	
	//2011-09-22: negative Gutschriften selektieren
	private MyE2_CheckBox  							oCB_NegativeGutschrift= new MyE2_CheckBox("", new MyE2_String("Wählt nur Gutschriften aus, die NEGATIVE Endbeträge haben (BELASTUNGEN) "));
	
	
	//2015-05-29: selektor ueber die varianten der zahlungsbedingungen
	private SELECTOR_SelectField_Zahlungsbedingungen  selZahlungsbedingungen = new SELECTOR_SelectField_Zahlungsbedingungen(true,200);
	
	//2012-07-25: navilist als klassenvariable
	private E2_NavigationList         				oNaviList = null;

	//2015-12-18: neuer filterselektor in der fibu
	private FIBU_LIST_Selector_Filter   			filterSelektor = null;
	
	
	public FIBU_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oNaviList = oNavigationList;
		
		this.oSelVector = 	  	new E2_SelectionComponentsVector(oNavigationList);

		//2012-06-19: variante mit selector, der alle vorhandenen kunden anzeigt, solche ohne offene Belege aber grau
		oSelKundenMitPositionen = new SELECTOR_COMPONENT_FirmenAuswahl(
											FIBU_CONST.get_SelectQueryFibuFirmenBase(false,"")+") ORDER BY 1", 
											197, 
											FIBU_CONST.get_SelectQueryFibuFirmenNurAbgeschlosseneBelege(""),
											this.oSelVector);
		

		this.oSelKundenMitPositionen.REFRESH_KundenSelektor();
		
		
		String[][] arrayAuswahlTypen = {
										{"Nur offene Einträge (Rech./Gutschr. die nicht durch Zahlungen ausgeglichen sind)",	FIBU_LIST_Selector.AUSWAHLTYP_OFFENE},
										{"Offene und ausgeglichene Einträge",													FIBU_LIST_Selector.AUSWAHLTYP_OFFENE_UND_GESCHLOSSENE},
										{"Offene und stornierte Fibuzeilen",													FIBU_LIST_Selector.AUSWAHLTYP_OFFENE_UND_STORNOS},
										{"Alle Fibuzeilen",																		FIBU_LIST_Selector.AUSWAHLTYP_ALLE},
										{"Alle nicht stornierten Fibuzeilen",													FIBU_LIST_Selector.AUSWAHLTYP_ALLE_UNSTORNIERT},
										{"Nur stornierte Fibuzeilen",															FIBU_LIST_Selector.AUSWAHLTYP_NUR_STORNOS},
										{"Nur ausgeglichene Fibuzeilen",														FIBU_LIST_Selector.AUSWAHLTYP_NUR_GESCHLOSSENE},
										{"Nur offene Einträge, die einen MAHNUNGSEINTRAG besitzen",								FIBU_LIST_Selector.AUSWAHLTYP_MAHNUNGSEINTRAEGE},
										};
		
		
		HashMap<String,String> hmAuswahlTypen = new HashMap<String, String>();
		hmAuswahlTypen.put("OFFENE", 					"NVL(JT_FIBU.STORNIERT,'N')='N' AND NVL(JT_FIBU.BUCHUNG_GESCHLOSSEN,'N')='N' ");
		hmAuswahlTypen.put("OFFENE_UND_GESCHLOSSENE", 	"NVL(JT_FIBU.STORNIERT,'N')='N'");
		hmAuswahlTypen.put("OFFENE_UND_STORNOS", 		"NVL(JT_FIBU.BUCHUNG_GESCHLOSSEN,'N')='N'");
		hmAuswahlTypen.put("ALLE", 						"");
		hmAuswahlTypen.put("ALLE_UNSTORNIERT", 			"NVL(JT_FIBU.STORNIERT,'N')='N'");
		hmAuswahlTypen.put("NUR_STORNOS", 				"NVL(JT_FIBU.STORNIERT,'N')='Y'");
		hmAuswahlTypen.put("NUR_GESCHLOSSENE", 			"NVL(JT_FIBU.STORNIERT,'N')='N' AND NVL(JT_FIBU.BUCHUNG_GESCHLOSSEN,'N')='Y'");

		//2012-01-16: selektion nach gemahnten eintraegen
		hmAuswahlTypen.put("MAHNUNGSEINTRAEGE", 		"NVL(JT_FIBU.STORNIERT,'N')='N' " +
														" AND NVL(JT_FIBU.BUCHUNG_GESCHLOSSEN,'N')='N' " +
														" AND ID_FIBU IN (SELECT ID_FIBU FROM JT_FIBU_MAHNUNG) ");

		this.oSelAuswahl = new MyE2_SelectField(arrayAuswahlTypen,"OFFENE",true);
		this.oSelAuswahl.setWidth(new Extent(290));
		
		
		/**
		 * 		BUCHUNGSTYPEN[0][0] = "Rechnung"; 			BUCHUNGSTYPEN[0][1]= "DRUCK_RECHNUNG";
		BUCHUNGSTYPEN[1][0] = "Gutschrift"; 		BUCHUNGSTYPEN[1][1]= "DRUCK_GUTSCHRIFT";
		BUCHUNGSTYPEN[2][0] = "Zahlungseingang"; 	BUCHUNGSTYPEN[2][1]= "ZAHLUNGSEINGANG";
		BUCHUNGSTYPEN[3][0] = "Zahlungsausgang"; 	BUCHUNGSTYPEN[3][1]= "ZAHLUNGSAUSGANG";
		BUCHUNGSTYPEN[4][0] = "Scheckdruck"; 		BUCHUNGSTYPEN[4][1]= "SCHECKDRUCK";

		 */
		
		
		// 2010-11-11: aenderung der Belegtypen-Selektion: zusaetzliche kombinationen
		String[][] arrayBelegtypenTypen = {	{"Alle",															"ALLE"},
											{"Rechnung",														"DRUCK_RECHNUNG"},
											{"Rechnung+Zahlungseingang",										"DRUCK_RECHNUNG_ZE"},
											{"Gutschrift",														"DRUCK_GUTSCHRIFT"},
											{"Gutschrift+Zahlungsausgang+Scheck", 								"DRUCK_GUTSCHRIFT_ZA_S"},
											{"Rechnungen+Gutschriften",											"DRUCK_RECH_GUT"},              //2012-07-25
											{"Rechnungen+Gutschriften+ZE+ZA+Scheck",							"DRUCK_RECH_GUT_ZE_ZA_S"},		//2012-07-25
											{"Zahlungseingang ",												"ZAHLUNGSEINGANG"},
											{"Zahlungsausgang",													"ZAHLUNGSAUSGANG"},
											{"Scheckdruck",														"SCHECKDRUCK"},
											{"Forderungen",														"FORDERUNGEN"},
											{"Verbindlichkeiten",												"VERBINDLICHKEITEN"},
											{"Positive Rechnung+negative Gutschrift (Forderungbelege)",			"DRUCK_POS_RECHNUNG_NEG_GUTSCHRIFT"},  //2012-07-27
											{"Positive Gutschrift+negative Rechnung (Verbindlichkeitsbelege)",	"DRUCK_POS_GUTSCHRIFT_NEG_RECHNUNG"},  //2012-07-27
											};
		
		HashMap<String,String> hmBelegTypen = new HashMap<String, String>();
		hmBelegTypen.put("ALLE", 					"");
		hmBelegTypen.put("DRUCK_RECHNUNG", 			"JT_FIBU.BUCHUNGSTYP='DRUCK_RECHNUNG'");
		hmBelegTypen.put("DRUCK_RECHNUNG_ZE", 		"(JT_FIBU.BUCHUNGSTYP='DRUCK_RECHNUNG' OR JT_FIBU.BUCHUNGSTYP='ZAHLUNGSEINGANG') ");
		hmBelegTypen.put("DRUCK_GUTSCHRIFT", 		"JT_FIBU.BUCHUNGSTYP='DRUCK_GUTSCHRIFT'");
		hmBelegTypen.put("DRUCK_GUTSCHRIFT_ZA_S", 	"(JT_FIBU.BUCHUNGSTYP='DRUCK_GUTSCHRIFT'  OR JT_FIBU.BUCHUNGSTYP='ZAHLUNGSAUSGANG'  OR JT_FIBU.BUCHUNGSTYP='SCHECKDRUCK')");
		hmBelegTypen.put("ZAHLUNGSEINGANG", 		"JT_FIBU.BUCHUNGSTYP='ZAHLUNGSEINGANG'");
		hmBelegTypen.put("ZAHLUNGSAUSGANG", 		"JT_FIBU.BUCHUNGSTYP='ZAHLUNGSAUSGANG'");
		hmBelegTypen.put("SCHECKDRUCK", 			"JT_FIBU.BUCHUNGSTYP='SCHECKDRUCK'");
		
		//neue selektionen 2012-07-25
		hmBelegTypen.put("DRUCK_RECH_GUT", 			"(JT_FIBU.BUCHUNGSTYP='DRUCK_RECHNUNG' OR JT_FIBU.BUCHUNGSTYP='DRUCK_GUTSCHRIFT')");		//2012-07-25
		hmBelegTypen.put("DRUCK_RECH_GUT_ZE_ZA_S", 	"(JT_FIBU.BUCHUNGSTYP='DRUCK_RECHNUNG' OR JT_FIBU.BUCHUNGSTYP='DRUCK_GUTSCHRIFT' OR" +
													" JT_FIBU.BUCHUNGSTYP='ZAHLUNGSEINGANG' OR JT_FIBU.BUCHUNGSTYP='ZAHLUNGSAUSGANG'  OR JT_FIBU.BUCHUNGSTYP='SCHECKDRUCK')");			//2012-07-25
		
		//neue selektionen 2012-07-27
		hmBelegTypen.put("DRUCK_POS_RECHNUNG_NEG_GUTSCHRIFT", 	"((JT_FIBU.BUCHUNGSTYP='DRUCK_RECHNUNG' AND JT_FIBU."+RECORD_FIBU.FIELD__ENDBETRAG_FREMD_WAEHRUNG+">=0) " +
				                                                " OR " +
				                                                "(JT_FIBU.BUCHUNGSTYP='DRUCK_GUTSCHRIFT' AND JT_FIBU."+RECORD_FIBU.FIELD__ENDBETRAG_FREMD_WAEHRUNG+"<0))");		//2012-07-25
		
		hmBelegTypen.put("DRUCK_POS_GUTSCHRIFT_NEG_RECHNUNG", 	"((JT_FIBU.BUCHUNGSTYP='DRUCK_GUTSCHRIFT' AND JT_FIBU."+RECORD_FIBU.FIELD__ENDBETRAG_FREMD_WAEHRUNG+">=0) " +
													            " OR " +
													            "(JT_FIBU.BUCHUNGSTYP='DRUCK_RECHNUNG' AND JT_FIBU."+RECORD_FIBU.FIELD__ENDBETRAG_FREMD_WAEHRUNG+"<=0))");		//2012-07-25
		
	    String sVerbindlichkeiten = " JT_FIBU.BUCHUNGSBLOCK_NR IN  " + 
									"   (        SELECT DISTINCT F2.BUCHUNGSBLOCK_NR FROM " + bibE2.cTO() + ".JT_FIBU F2  " + 
									"            LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG R2 ON F2.ID_VKOPF_RG = R2.ID_VKOPF_RG  " +
									"            WHERE NVL(F2.VORLAEUFIG,'N')   = 'N'  " +
									"            AND NVL(F2.STORNIERT,'N')= 'N'  " +
									"            AND nvl(F2.BUCHUNG_GESCHLOSSEN,'N') = 'N'  " +
									"	         GROUP BY F2.BUCHUNGSBLOCK_NR  " + 
									"            HAVING SUM( F2.FAKTOR_BUCHUNG_PLUS_MINUS * F2.ZAHLUNGSBETRAG_FREMD_WAEHRUNG) < 0  " + 
									"       UNION " +
									" 			 SELECT DISTINCT F2.BUCHUNGSBLOCK_NR FROM  " + bibE2.cTO() + ".JT_FIBU F2 " +
									"            LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG R2 ON F2.ID_VKOPF_RG = R2.ID_VKOPF_RG " +
									"            WHERE NVL(F2.VORLAEUFIG,'N')   = 'N' " +
									"            AND NVL(F2.STORNIERT,'N')= 'N' " +
									"            AND nvl(F2.BUCHUNG_GESCHLOSSEN,'N') = 'N' " +
									"            GROUP BY F2.BUCHUNGSBLOCK_NR " +
									"                    HAVING count(*) = 1 " +
									"                    AND ( max(F2.BUCHUNGSTYP)    != 'DRUCK_RECHNUNG' AND max(F2.BUCHUNGSTYP)    != 'DRUCK_GUTSCHRIFT') " +
									"  ) " ;
		hmBelegTypen.put("VERBINDLICHKEITEN", sVerbindlichkeiten);
		
	    String sForderungen = 		" JT_FIBU.BUCHUNGSBLOCK_NR IN  " + 
									"   (        SELECT DISTINCT F2.BUCHUNGSBLOCK_NR FROM " + bibE2.cTO() + ".JT_FIBU F2  " + 
									"            LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG R2 ON F2.ID_VKOPF_RG = R2.ID_VKOPF_RG  " +
									"            WHERE NVL(F2.VORLAEUFIG,'N')   = 'N'  " +
									"            AND NVL(F2.STORNIERT,'N')= 'N'  " +
									"            AND nvl(F2.BUCHUNG_GESCHLOSSEN,'N') = 'N'  " +
									"	         GROUP BY F2.BUCHUNGSBLOCK_NR  " + 
									"            HAVING SUM( F2.FAKTOR_BUCHUNG_PLUS_MINUS * F2.ZAHLUNGSBETRAG_FREMD_WAEHRUNG) > 0  " + 
									"       UNION " +
									" 			 SELECT DISTINCT F2.BUCHUNGSBLOCK_NR FROM  " + bibE2.cTO() + ".JT_FIBU F2 " +
									"            LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_VKOPF_RG R2 ON F2.ID_VKOPF_RG = R2.ID_VKOPF_RG " +
									"            WHERE NVL(F2.VORLAEUFIG,'N')   = 'N' " +
									"            AND NVL(F2.STORNIERT,'N')= 'N' " +
									"            AND nvl(F2.BUCHUNG_GESCHLOSSEN,'N') = 'N' " +
									"            GROUP BY F2.BUCHUNGSBLOCK_NR " +
									"                    HAVING count(*) = 1 " +
									"                    AND ( max(F2.BUCHUNGSTYP)    != 'DRUCK_RECHNUNG' AND max(F2.BUCHUNGSTYP)    != 'DRUCK_GUTSCHRIFT') " +
									"   ) " ;
	    hmBelegTypen.put("FORDERUNGEN", sForderungen);
		
		
		
		
		
		this.oSelBelegTyp = new MyE2_SelectField(arrayBelegtypenTypen,"ALLE",true);
		this.oSelBelegTyp.setWidth(new Extent(200));
		
		

		this.oSelDatumBereichErstellung  = new SELECTOR_MONATJAHR_bis_MONATJAHR(
							                 80,  new MyE2_String("bis"), 
							                "TO_CHAR(JT_FIBU.BUCHUNGSDATUM,'YYYY-MM')>=", 
							                "TO_CHAR(JT_FIBU.BUCHUNGSDATUM,'YYYY-MM')<=");


		this.oSelDatumBereichFaelligkeit  = new SELECTOR_MONATJAHR_bis_MONATJAHR(
							                 80,  new MyE2_String("bis"), 
							                "TO_CHAR(NVL(JT_FIBU.ZAHLUNGSZIEL,JT_FIBU.BUCHUNGSDATUM),'YYYY-MM')>=", 
							                "TO_CHAR(NVL(JT_FIBU.ZAHLUNGSZIEL,JT_FIBU.BUCHUNGSDATUM),'YYYY-MM')<=");


		this.oSelDatumBereich_RechDruck_Erstellung = new SELECTOR_MONATJAHR_bis_MONATJAHR(
								              80, new MyE2_String("bis"), 
								             "TO_CHAR((CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN JT_FIBU.BUCHUNGSDATUM ELSE JT_VKOPF_RG.DRUCKDATUM END),'YYYY-MM')>=", 
								             "TO_CHAR((CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN JT_FIBU.BUCHUNGSDATUM ELSE JT_VKOPF_RG.DRUCKDATUM END),'YYYY-MM')<=");
		
		
		this.oSelDatumBereich_RechDruck_Erstellung.get_oSelMonateJahrVon().setToolTipText(new MyE2_String("Datum bedeutet bei Rechnungs-/Gutschriftsbelegen das Rechnungsdatum, bei Buchungseinträgen das Erstellungsdatum").CTrans());
		this.oSelDatumBereich_RechDruck_Erstellung.get_oSelMonateJahrBis().setToolTipText(new MyE2_String("Datum bedeutet bei Rechnungs-/Gutschriftsbelegen das Rechnungsdatum, bei Buchungseinträgen das Erstellungsdatum").CTrans());
		
		this.oSelDatumBereichErstellung.get_oSelMonateJahrVon().setToolTipText(new MyE2_String("Datum bedeutet das Erstellungsdatum des Eintrags").CTrans());
		this.oSelDatumBereichErstellung.get_oSelMonateJahrBis().setToolTipText(new MyE2_String("Datum bedeutet das Erstellungsdatum des Eintrags").CTrans());
		
		//2011-06-08: neue selektoren
		String cQueryBelegeMitStornos = " JT_FIBU.ID_VKOPF_RG IN ( SELECT DISTINCT RGP.ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_VPOS_RG RGP WHERE " +
												" (( RGP.ID_VPOS_RG_STORNO_VORGAENGER IS NOT NULL OR " +
												   " RGP.ID_VPOS_RG_STORNO_NACHFOLGER IS NOT NULL) AND" +
												   " NVL(RGP.DELETED,'N')='N'))";
		
		oSelVector.add(new E2_ListSelectorStandard(oCB_BelegeMitStornos, cQueryBelegeMitStornos, ""));
		
		
		
		oSelVector.add(new E2_ListSelectorStandard(oCB_KundenMitScheck,
												 "JT_FIBU.ID_ADRESSE IN (SELECT DISTINCT FI.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIRMENINFO FI WHERE NVL(FI.SCHECKDRUCK_JN,'N')='Y')",
												""));

		oSelVector.add(new E2_ListSelectorStandard(oCB_KundenAkonto,
												 "JT_FIBU.ID_ADRESSE IN (SELECT DISTINCT FI.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIRMENINFO FI WHERE NVL(FI.AKONTO,'N')='Y')",
												""));
		
		oSelVector.add(new E2_ListSelectorStandard(oCB_KundenBar,
												 "JT_FIBU.ID_ADRESSE IN (SELECT DISTINCT AD1.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE AD1 WHERE NVL(AD1.BARKUNDE,'N')='Y')",
												""));

		oSelVector.add(new E2_ListSelectorStandard(oCB_KundenNichtBar,
												 "JT_FIBU.ID_ADRESSE IN (SELECT DISTINCT AD1.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE AD1 WHERE NVL(AD1.BARKUNDE,'N')='N')",
												""));
		
		oSelVector.add(new E2_ListSelectorStandard(oCB_Verrechnung,
												 "JT_FIBU.ID_ADRESSE IN (SELECT DISTINCT FI.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIRMENINFO FI " +
												 		" WHERE NVL(FORDERUNGSVERRECHNUNG,'.')='"+myCONST.FORDERUNGSVERRECHNUNG_ZUSTIMMUNG+"')",
												""));

		//2011-09-22: negative Gutschriften selektieren
		oSelVector.add(new E2_ListSelectorStandard(oCB_NegativeGutschrift,
												 "(JT_FIBU.BUCHUNGSTYP='DRUCK_GUTSCHRIFT' AND ENDBETRAG_FREMD_WAEHRUNG<0)",
												 ""));

		
		
		oSelVector.add(new E2_ListSelectorStandard(this.oSelectHaendler,
				 "JT_FIBU.ID_ADRESSE IN (SELECT DISTINCT AD.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE AD WHERE NVL(AD.ID_USER,0)=#WERT# OR NVL(AD.ID_USER_ERSATZ,0)=#WERT#)"
				, null, null));


		oSelVector.add(new E2_ListSelectorStandard(this.oSelDatumBereichErstellung.get_oSelMonateJahrVon(),this.oSelDatumBereichErstellung.get_hmStartMonat(),null,null));
		oSelVector.add(new E2_ListSelectorStandard(this.oSelDatumBereichErstellung.get_oSelMonateJahrBis(),this.oSelDatumBereichErstellung.get_hmEndMonat(),null,null));

		oSelVector.add(new E2_ListSelectorStandard(this.oSelDatumBereichFaelligkeit.get_oSelMonateJahrVon(),this.oSelDatumBereichFaelligkeit.get_hmStartMonat(),null,null));
		oSelVector.add(new E2_ListSelectorStandard(this.oSelDatumBereichFaelligkeit.get_oSelMonateJahrBis(),this.oSelDatumBereichFaelligkeit.get_hmEndMonat(),null,null));
		
		oSelVector.add(new E2_ListSelectorStandard(this.oSelDatumBereich_RechDruck_Erstellung.get_oSelMonateJahrVon(),this.oSelDatumBereich_RechDruck_Erstellung.get_hmStartMonat(),null,null));
		oSelVector.add(new E2_ListSelectorStandard(this.oSelDatumBereich_RechDruck_Erstellung.get_oSelMonateJahrBis(),this.oSelDatumBereich_RechDruck_Erstellung.get_hmEndMonat(),null,null));

		oSelVector.add(new E2_ListSelectorStandard(this.oSelAuswahl,hmAuswahlTypen,null,null));
		oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitPositionen.get_oSelKunden()," NVL(JT_FIBU.ID_ADRESSE,0)=#WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(this.oSelBelegTyp,hmBelegTypen, null, null));

		//2012-02-14: neuer selektor: leistungsdatum
		oSelVector.add(this.oSelLeistungsdatum);
		
		
		oSelVector.add(new E2_ListSelectorStandard(this.selZahlungsbedingungen,
					"("+_DB.Z_VKOPF_RG$ID_ZAHLUNGSBEDINGUNGEN+"=#WERT# OR NVL("+_DB.Z_VKOPF_RG$ID_ZAHLUNGSBEDINGUNGEN+",-1)=-1)", null,null ));
		

		this.oSelBereichDatumFaelligkeit = new E2_SelektorDateFromTo_NG(
									null,"NVL(JT_FIBU.ZAHLUNGSZIEL,JT_FIBU.BUCHUNGSDATUM)","NVL(JT_FIBU.ZAHLUNGSZIEL,JT_FIBU.BUCHUNGSDATUM)",
									new Extent(80));

		
		oSelVector.add(oSelBereichDatumFaelligkeit);
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);

		
		//2015-12-18: neuer filterselektor in der fibu
		this.filterSelektor = new FIBU_LIST_Selector_Filter();
		this.oSelVector.add(this.filterSelektor);
		
		MyE2_Grid oGrid = new MyE2_Grid(4,0);
		
		oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
		
		Insets oIN2 = new Insets(0,1,20,1);
		
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Buch.Dat. von"), 		95, this.oSelDatumBereich_RechDruck_Erstellung),	1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Fällig von"), 		95, this.oSelDatumBereichFaelligkeit),				1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Dat(Erst.)"),			95,this.oSelDatumBereichErstellung),				1,oIN2);
		//oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Deb./Kreditor"),		95,this.oSelKundenMitPositionen.get_oSelKunden()),	1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Deb./Kreditor"),		80,this.oSelKundenMitPositionen),	1,oIN2);
		
		
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Auswahl:"),			90,this.oSelAuswahl),										1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Fälligkeit:"),		90,oSelBereichDatumFaelligkeit.get_oComponentWithoutText()),1,oIN2);

		//neuer selektor fuer eine eingrenzung des unteren leistungsdatums
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Leist.D.(von):"),		90,oSelLeistungsdatum.get_oComponentWithoutText()),	1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Händler:"),   		90,oSelectHaendler),								1,oIN2);
//		oGrid.add(new MyE2_Label(""));
		
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Stornopos."), 		90, oCB_BelegeMitStornos),							1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Bar-Kunde"), 			90, oCB_KundenBar),									1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Nicht-Bar"), 			90, oCB_KundenNichtBar),							1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Scheck.Lief"),		90, this.oCB_KundenMitScheck),						1,oIN2);

		
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Akonto-Kund."),		90,this.oCB_KundenAkonto),1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Forderung.Ver.:"),	90,this.oCB_Verrechnung),1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Negative GS:"),		90,this.oCB_NegativeGutschrift),1,oIN2);
		oGrid.add(new MyE2_Label(""));
		
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Belegtyp: "),			90,this.oSelBelegTyp),1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Zahlungsbed: "),		90,this.selZahlungsbedingungen),1,oIN2);
//		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Filter: "),			90,this.filterSelektor.get_oComponentForSelection()),1,oIN2);
		oGrid.add(this.filterSelektor.get_oComponentForSelection(),1,oIN2);
		oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Diverse: "),			90,oDB_BasedSelektor.get_oComponentForSelection(100)),1,oIN2);
		

		
//		if (oDB_BasedSelektor.get_bIsFilled())
//		{
//			oGrid.add(new SELECTOR_CONTAINER(new MyE2_String("Diverse: "),			90,oDB_BasedSelektor.get_oComponentForSelection(100)),1,oIN2);
//		}
//		else
//		{
//			oGrid.add(new SELECTOR_CONTAINER(new MyE2_String(" "),					90,new MyE2_Label("")),1,oIN2);
//		}
//		oGrid.add(new bt_FaelligeRechnungen(),1,oIN2);
//		oGrid.add(new SELECTOR_CONTAINER(oSelVector.get_AktivPassivComponent(),		90,   new bt_FaelligeRechnungen(new Extent(190))),1,oIN2);
		oGrid.add(new bt_FaelligeRechnungen(new Extent(190)),1,oIN2);
		oGrid.add(oSelVector.get_AktivPassivComponent(),1,oIN2);

		
		
		this.add(oGrid);
	}


	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	
	
	public MyE2_SelectField get_oSelKundenMitPositionen() 
	{
		return oSelKundenMitPositionen.get_oSelKunden();
	}



	
	
	private class bt_FaelligeRechnungen extends MyE2_Button
	{
		public bt_FaelligeRechnungen(Extent width) 
		{
			super(new MyE2_String("Suche fällige Rechnungen"));
			this.setToolTipText(new MyE2_String("Selektiert die fälligen Rechnungen innerhalb eines Bereichs (Vorbereitung zur Mahnung)").CTrans());
			this.setWidth(width);
			this.add_oActionAgent(new ownActionFaelligeRechnungen());
		}
	}
	
	
	private class ownActionFaelligeRechnungen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			new ownContainerIntern();
		}
	}

	private class ownContainerIntern extends E2_BasicModuleContainer
	{
		private E2_CalendarComponent_WithDayButtons     oCalendarSelectVon = null;
		private E2_CalendarComponent_WithDayButtons     oCalendarSelectBis = null;
		
		public ownContainerIntern() throws myException 
		{
			super();
			
			this.oCalendarSelectVon = new E2_CalendarComponent_WithDayButtons(bibALL.get_cDateNOW())
			{
				@Override
				public void doDayButtonAction(ExecINFO execInfo) throws myException 
				{
					MyE2_Button oDateButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
					String cDateSelected = ((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask();
					ownContainerIntern.this.oCalendarSelectVon.SPRINGE_AUF_DATUM(cDateSelected);
					ownContainerIntern.this.oCalendarSelectBis.SPRINGE_AUF_DATUM(cDateSelected);
				}
			};
		
			this.oCalendarSelectBis = new E2_CalendarComponent_WithDayButtons(bibALL.get_cDateNOW()) 
			{
				@Override
				public void doDayButtonAction(ExecINFO execInfo) throws myException 
				{
					MyE2_Button oDateButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
					String cDateSelected = ((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask();
	
					
					//das gewahlte datum als neues datum markieren und auch auf die Zielseite kopieren
					ownContainerIntern.this.oCalendarSelectBis.set_cSelectedDayFormated(cDateSelected);
					ownContainerIntern.this.oCalendarSelectBis.baue_calender();
					
					
					//den selektor einstellen
					FIBU_LIST_Selector oThis = FIBU_LIST_Selector.this;
					oThis.oSelAuswahl.set_ActiveValue("OFFENE");
					
					oThis.oSelBelegTyp.set_ActiveValue("DRUCK_RECHNUNG");
					
					oThis.oSelBereichDatumFaelligkeit.get_oTFDatumVon().get_oTextField().setText(ownContainerIntern.this.oCalendarSelectVon.get_cSelectedDayFormated());
					oThis.oSelBereichDatumFaelligkeit.get_oTFDatumBis().get_oTextField().setText(cDateSelected);
					
					ownContainerIntern.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
					// oThis.oSelVector.get_oButtonReload().doAction();
					oThis.oSelVector.makeSelektion();

				}
			};

			
			MyE2_Grid oGridHelp = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_BORDER(new E2_ColorDDDark()));
			oGridHelp.add(new MyE2_Label(new MyE2_String("Start Fälligkeitszeitraum"),MyE2_Label.STYLE_NORMAL_BOLD()),LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(10,10,30,10)));
			oGridHelp.add(new MyE2_Label(new MyE2_String("Ende Fälligkeitszeitraum"),MyE2_Label.STYLE_NORMAL_BOLD()),LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(10,10,30,10)));
			oGridHelp.add(this.oCalendarSelectVon,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(10,10,30,10)));
			oGridHelp.add(this.oCalendarSelectBis,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(10,10,30,10)));
			
			
			this.add(oGridHelp, new Insets(10,10,30,10));
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(370), new MyE2_String("Bitte wählen Sie den gewünschten Fälligkeitszeitraum"));
		}
		
	}

	public E2_SelektorDateFromTo_NG get_oSelBereichDatumFaelligkeit()
	{
		return oSelBereichDatumFaelligkeit;
	}
	
	
	
	private class ownSelektorLeistungsdatum extends E2_SelektorDateFromTo_NG
	{

		public ownSelektorLeistungsdatum() throws myException
		{
			super();

			//die selektion bezieht sich nur auf das startdatum 
			String cSQLVon = " (CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN JT_FIBU.BUCHUNGSDATUM ELSE (SELECT MIN(VP1.AUSFUEHRUNGSDATUM) " +
									" FROM "+bibE2.cTO()+".JT_VPOS_RG VP1 WHERE NVL(VP1.DELETED,'N')='N' AND VP1.ID_VKOPF_RG=JT_FIBU.ID_VKOPF_RG) END)";
			
			this.INIT_Selector(null, cSQLVon, cSQLVon, new Extent(80));
		}
	}

	
	
	public Component_USER_DROPDOWN_NEW get_oSelectHaendler()
	{
		return oSelectHaendler;
	}



	public E2_SelectionComponentsVector get_oSelectVector()
	{
		return oSelVector;
	}

	
	
	public SELECTOR_MONATJAHR_bis_MONATJAHR get_oSelDatumBereichErstellung()
	{
		return oSelDatumBereichErstellung;
	}

	public SELECTOR_MONATJAHR_bis_MONATJAHR get_oSelDatumBereich_RechDruck_Erstellung()
	{
		return oSelDatumBereich_RechDruck_Erstellung;
	}



	public void set_SelAuswahl_OFFENE() throws myException
	{
		this.oSelAuswahl.set_ActiveValue(FIBU_LIST_Selector.AUSWAHLTYP_OFFENE);
	}

	
	
	public void set_SelAuswahl_OFFENE_UND_GESCHLOSSENE() throws myException
	{
		this.oSelAuswahl.set_ActiveValue(FIBU_LIST_Selector.AUSWAHLTYP_OFFENE_UND_GESCHLOSSENE);
	}



	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}


	public MyE2_SelectField get_oSelBelegTyp()
	{
		return oSelBelegTyp;
	}



	public E2_SelektorDateFromTo_NG get_oSelLeistungsdatum()
	{
		return oSelLeistungsdatum;
	}



	public FIBU_LIST_Selector_Filter get_filterSelektor() {
		return filterSelektor;
	}

	
}
