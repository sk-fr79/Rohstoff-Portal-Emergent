package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.HashMap;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorContainer_MIT_BESCHRIFTUNG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_ListSelektorZusatzFelder;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectMultiOptionKlasse;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMultiDropdown_Betreuer;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMultiDropdown_Betreuer2;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMultiDropdown_Betreuer_Kombi;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMultiDropdown_Sachbearbeiter;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMulti_AdressKlasse;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMulti_Ausstattung;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMulti_Branche;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMulti_FreieGruppen;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMulti_GelisteteSorten;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMulti_Land;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_SelectorMulti_Potential;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_Selector_Multi_Matspez_elemente;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_Selektor_AktivInaktiv;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_Selektor_Bar_Nicht_bar;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_Selektor_Scheck_kein_Scheck;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS_Selektor_letzte_info_im_zeitraum;


public class FS_ListSelector_sich extends E2_ListSelectorContainer
{

	public static final String NO_BRANCHE="@NO_BRANCHE@";
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public static int[] CheckBoxSelektorSpalten = {40,55};
	
	public FS_ListSelector_sich(E2_NavigationList oNavigationList,FS_LIST_ComponentMAP oListComponentMAP, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);


		
		//2013-03-26: multiselektor fuer branchen
		FS_SelectorMulti_Branche oFS_SelectorMultiBranche = new FS_SelectorMulti_Branche();
		
		
		// selector fuer selbstdefinierte gruppen
		//SELECTOR_GROUPS oSelGroups = new SELECTOR_GROUPS("JT_ADRESSE","ID_ADRESSE",cMODULE_KENNER);
		//2013-03-25: austausch der singulaeren gruppenselektion auf multiselektion
		FS_SelectorMulti_FreieGruppen oSelGroups = new FS_SelectorMulti_FreieGruppen("JT_ADRESSE","ID_ADRESSE",cMODULE_KENNER);
		
	
		//2012-10-17: neuer multiselektor fuer die gelisteten Sorten 
		FS_SelectorMulti_GelisteteSorten oSelectSorten_EK = new FS_SelectorMulti_GelisteteSorten(true);
		FS_SelectorMulti_GelisteteSorten oSelectSorten_VK = new FS_SelectorMulti_GelisteteSorten(false);

		oSelVector.add(oSelectSorten_EK);
		oSelVector.add(oSelectSorten_VK);
		
		
		//test mit mehrfachselektion
		FS_SelectorMultiDropdown_Betreuer ooSelectBetreuer=new FS_SelectorMultiDropdown_Betreuer();
		oSelVector.add(ooSelectBetreuer);

		FS_SelectorMultiDropdown_Betreuer 		oFS_SelectorMultiDropdown_Betreuer = 			new FS_SelectorMultiDropdown_Betreuer();
		FS_SelectorMultiDropdown_Betreuer2 		oFS_SelectorMultiDropdown_Betreuer2 = 			new FS_SelectorMultiDropdown_Betreuer2();
		FS_SelectorMultiDropdown_Betreuer_Kombi oFS_SelectorMultiDropdown_Betreuer_Kombi = 		new FS_SelectorMultiDropdown_Betreuer_Kombi();
		FS_SelectorMultiDropdown_Sachbearbeiter oFS_SelectorMultiDropdown_Sachbearbeiter = 		new FS_SelectorMultiDropdown_Sachbearbeiter();
		FS_SelectorMulti_Land 					oFS_SelectorMulti_Land = 						new FS_SelectorMulti_Land();
		FS_SelectorMulti_AdressKlasse 			oFS_SelectorMulti_AdressKlasse = 				new FS_SelectorMulti_AdressKlasse();
		FS_SelectorMulti_Ausstattung 			oFS_SelectorMulti_Ausstattung = 				new FS_SelectorMulti_Ausstattung();
		
		oSelVector.add(oFS_SelectorMultiDropdown_Betreuer);
		oSelVector.add(oFS_SelectorMultiDropdown_Betreuer2);
		oSelVector.add(oFS_SelectorMultiDropdown_Betreuer_Kombi);
		oSelVector.add(oFS_SelectorMultiDropdown_Sachbearbeiter);
		oSelVector.add(oFS_SelectorMulti_Land);
		oSelVector.add(oFS_SelectorMulti_AdressKlasse);
		oSelVector.add(oFS_SelectorMulti_Ausstattung);

		
		oSelVector.add(oFS_SelectorMultiBranche);
		oSelVector.add(oSelGroups);

		//2012-02-27: neuer listselektor
		FS_Selektor_letzte_info_im_zeitraum  oSelZeitraumInfoEintraege = new FS_Selektor_letzte_info_im_zeitraum();
		oSelVector.add(oSelZeitraumInfoEintraege);
		
		//2012-05-11: neuer selektor mit sonderfeldern
		FS_ListSelektorZusatzFelder oSelektorZusatzFelder = new FS_ListSelektorZusatzFelder(oListComponentMAP, cMODULE_KENNER);
		oSelVector.add(oSelektorZusatzFelder);

		//2012-01-30: neue einteilung der checkbox-selektoren
		FS_Selektor_AktivInaktiv  			oSelektorAktivInaktiv = 		new FS_Selektor_AktivInaktiv();
		FS_Selektor_Bar_Nicht_bar  			oSelektorBar_Nicht_bar = 		new FS_Selektor_Bar_Nicht_bar();
		FS_Selektor_Scheck_kein_Scheck		oSelektorScheck_kein_Scheck = 	new FS_Selektor_Scheck_kein_Scheck();
		
		oSelVector.add(oSelektorAktivInaktiv);
		oSelVector.add(oSelektorBar_Nicht_bar);
		oSelVector.add(oSelektorScheck_kein_Scheck);
		
		//2013-03-25: weitere selektoren
		FS_SelectorMulti_Potential  oSelectPotential = new FS_SelectorMulti_Potential();
		oSelVector.add(oSelectPotential);
		
		//jetzt einen Standard-Selector definieren, der in einem DropDown verschiedene Bedingungen abdeckt
		String[][] 					cSelWerte = 		new String[13][2];
 		HashMap<String, String>  	hmWerte =			new HashMap<String, String>();
 		
 		cSelWerte[0][0]= "*";																				cSelWerte[0][1]= "ALLE";
 		cSelWerte[1][0]= "<BARKUNDE> AN";								cSelWerte[1][1]= "BAR_EIN";
 		cSelWerte[2][0]= "<BARKUNDE> AUS";								cSelWerte[2][1]= "BAR_AUS";
 		cSelWerte[3][0]= "<WEIHNACHTSGESCHENK> EIN";					cSelWerte[3][1]= "GESCH_WEIH";
 		cSelWerte[4][0]= "<SOMMERGESCHENK> EIN";						cSelWerte[4][1]= "GESCH_SOMMER";
 		cSelWerte[5][0]= "Beide Geschenke";								cSelWerte[5][1]= "GESCH_BEIDE";
 		cSelWerte[6][0]= "MIT AUSWEISNUMMER";							cSelWerte[6][1]= "AUSWEIS";
 		cSelWerte[7][0]= "OHNE AUSWEISNUMMER";							cSelWerte[7][1]= "KEIN_AUSWEIS";
 		cSelWerte[8][0]= "Eintrag Kreditversicherung";					cSelWerte[8][1]= "KREDITVERSICH";
 		cSelWerte[9][0]= "Firma besitzt Muldeneinträge";				cSelWerte[9][1]= "MULDEN";
 		
 		//2012-01-18: keine mahnungen
 		cSelWerte[10][0]= "Firma hat den Vermerk <Keine Mahnungen> ";	cSelWerte[10][1]= "KEINE_MAHNUNGEN";
 		
 		//2012-01-30: das transfer-merkmal wandert in die Sonderselektion
 		cSelWerte[11][0]= "Firma besitzt Merkmal <TRANSFER>";			cSelWerte[11][1]= "TRANSFER_EIN";
 		cSelWerte[12][0]= "Firma besitzt Merkmal <KEIN TRANSFER>";		cSelWerte[12][1]= "TRANSFER_AUS";

 		
 		hmWerte.put("ALLE", 			"");
 		hmWerte.put("BAR_EIN", 			"NVL(JT_ADRESSE.BARKUNDE,'N') = 'Y'");
 		hmWerte.put("BAR_AUS", 			"NVL(JT_ADRESSE.BARKUNDE,'N') = 'N'");
 		hmWerte.put("GESCH_WEIH", 		"JT_ADRESSE.ID_ADRESSE in (select JT_MITARBEITER.ID_ADRESSE_BASIS from "+bibE2.cTO()+".JT_MITARBEITER WHERE   NVL(WEIHNACHTSGESCHENK,'N')='Y')");
 		hmWerte.put("GESCH_SOMMER", 	"JT_ADRESSE.ID_ADRESSE in (select JT_MITARBEITER.ID_ADRESSE_BASIS from "+bibE2.cTO()+".JT_MITARBEITER WHERE   NVL(SOMMERGESCHENK,'N')='Y')");
 		hmWerte.put("GESCH_BEIDE", 		"JT_ADRESSE.ID_ADRESSE in (select JT_MITARBEITER.ID_ADRESSE_BASIS from "+bibE2.cTO()+".JT_MITARBEITER WHERE   NVL(WEIHNACHTSGESCHENK,'N')='Y' OR  NVL(SOMMERGESCHENK,'N')='Y')");
 		hmWerte.put("AUSWEIS", 			"JT_ADRESSE.AUSWEIS_NUMMER IS NOT NULL");
 		hmWerte.put("KEIN_AUSWEIS", 	"JT_ADRESSE.AUSWEIS_NUMMER IS NULL");
 		
 		String cSQL_KreditVS = "(SELECT FI.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIRMENINFO FI "+
							 			" WHERE FI.KREDITLIMIT IS NOT NULL "+
							 			" OR    FI.KREDITLIMIT2 IS NOT NULL "+
							 			" OR    FI.KREDITLIMIT3 IS NOT NULL "+
							 			" OR    FI.KREDITSTAND IS NOT NULL "+
							 			" OR    FI.VERSICHANFR_SUMME IS NOT NULL "+
							 			" OR    FI.VERSICH_MELDEFRIST_TAGE IS NOT NULL "+
							 			" OR    FI.KREDITLIMIT_VON IS NOT NULL "+
							 			" OR    FI.KREDITLIMIT_BIS IS NOT NULL "+
							 			" OR    FI.KREDITLIMIT2_VON IS NOT NULL "+
							 			" OR    FI.KREDITLIMIT2_BIS IS NOT NULL "+
							 			" OR    FI.KREDITLIMIT3_VON IS NOT NULL "+
							 			" OR    FI.KREDITLIMIT3_BIS IS NOT NULL "+
							 			" OR    FI.KREDITVERS_NR IS NOT NULL "+
							 			" OR    FI.VERSICHANFR_DAT IS NOT NULL " +
							 			" UNION " +
							 			" SELECT KV.ID_ADRESSE FROM "+bibE2.cTO()+".JT_KREDITVERS_ADRESSE KV)";
 		
 		hmWerte.put("KREDITVERSICH", 	"JT_ADRESSE.ID_ADRESSE in "+cSQL_KreditVS);
 		
 		
 		//2011-11-30: weiterer Selektor, der nach Mulden sucht
 		String cSQL_Mulden = "(SELECT CT.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADR_CONTAINERTYP CT)";
							
		hmWerte.put("MULDEN", 	"JT_ADRESSE.ID_ADRESSE in "+cSQL_Mulden);
 		
		
		//2012-01-18: keine mahnungen
		hmWerte.put("KEINE_MAHNUNGEN", 	"JT_ADRESSE.ID_ADRESSE in (SELECT FFI.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIRMENINFO FFI WHERE NVL(FFI.KEINE_MAHNUNGEN,'N')='Y')");
		
 		//2012-01-30: das transfer-merkmal wandert in die Sonderselektion
		hmWerte.put("TRANSFER_EIN", 	"NVL(JT_ADRESSE.TRANSFER,'N') = 'Y'");
		hmWerte.put("TRANSFER_AUS", 	"NVL(JT_ADRESSE.TRANSFER,'N') = 'N'");
 		
 		
 		MyE2_SelectField  			oSelFieldDivers = 	new MyE2_SelectField(cSelWerte,"ALLE",true);
 		oSelFieldDivers.setWidth(new Extent(200));
		
		oSelVector.add(new E2_ListSelectorStandard(oSelFieldDivers,hmWerte,null,null));
		// ende diverse selektionen
		
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);


		
		//2013-04-09: neue multiselektor fuer die adressklassen
		FS_SelectMultiOptionKlasse  oSelectorAdressklasseAusfuehrlich = new FS_SelectMultiOptionKlasse();
		this.oSelVector.add(oSelectorAdressklasseAusfuehrlich);
		
		//2013-04-11: neuer multiselektor fuer die Materialspezifikation
		FS_Selector_Multi_Matspez_elemente  oSelectMatSpez = new FS_Selector_Multi_Matspez_elemente();
		this.oSelVector.add(oSelectMatSpez);

		
		
		//2011-10-13: neue darstellung selektor
		MyE2_Grid oGridInnen = new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridInnen.setOrientation(MyE2_Grid.ORIENTATION_VERTICAL);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);

		Insets   iListe = new Insets(0,1,8,1);
		
		//spalte 1
		oGridInnen.add(oFS_SelectorMultiDropdown_Betreuer.get_oComponentForSelection(), iListe);
		oGridInnen.add(oFS_SelectorMultiDropdown_Betreuer2.get_oComponentForSelection(), iListe);
		oGridInnen.add(oFS_SelectorMultiDropdown_Betreuer_Kombi.get_oComponentForSelection(), iListe);
		oGridInnen.add(oFS_SelectorMultiDropdown_Sachbearbeiter.get_oComponentForSelection(), iListe);

		//spalte 2
		oGridInnen.add(oFS_SelectorMultiBranche.get_oComponentForSelection(), iListe);
		oGridInnen.add(oFS_SelectorMulti_Ausstattung.get_oComponentForSelection(), iListe);
		oGridInnen.add(oFS_SelectorMulti_AdressKlasse.get_oComponentForSelection(), iListe);
		//2013-04-09: neuer multioptions-selektor fuer die adressklasse
		oGridInnen.add(oSelectorAdressklasseAusfuehrlich.get_oComponentForSelection());


		//spalte 3
		oGridInnen.add(oFS_SelectorMulti_Land.get_oComponentForSelection(), iListe);
		oGridInnen.add(oSelGroups.get_oComponentForSelection(), iListe);
		oGridInnen.add(oSelectSorten_EK.get_oComponentForSelection(), iListe);
		oGridInnen.add(oSelectSorten_VK.get_oComponentForSelection(), iListe);
		
		//spalte 4
		oGridInnen.add(oSelectPotential.get_oComponentForSelection(), iListe);
		oGridInnen.add(oSelektorAktivInaktiv.get_oComponentForSelection(),iListe);
		oGridInnen.add(oSelektorBar_Nicht_bar.get_oComponentForSelection(),iListe);
		oGridInnen.add(oSelektorScheck_kein_Scheck.get_oComponentForSelection(),iListe);

		//spalte 5
		//int[] iB4a = {70,100};
		int[] iB3 = {80,102,100};
		int[] iB2 = {80,102};
		int[] iB5a = {170};
		oGridInnen.add(new E2_SelektorContainer_MIT_BESCHRIFTUNG(iB3, bibVECTOR.get_vCompVector(
				new MyE2_Label(new MyE2_String("Diverse:")), 		
				oSelFieldDivers,
				oSelectMatSpez.get_oComponentForSelection() ),E2_INSETS.I_0_0_10_0), iListe);
		if (oDB_BasedSelektor.get_bIsFilled())
		{
			oGridInnen.add(new E2_SelektorContainer_MIT_BESCHRIFTUNG(iB2, bibVECTOR.get_vCompVector(new MyE2_Label(new MyE2_String("Frei Defin.:")), 	oDB_BasedSelektor.get_oComponentForSelection(200)),E2_INSETS.I_0_0_10_0), iListe);
		}
		else
		{
			oGridInnen.add(new E2_SelektorContainer_MIT_BESCHRIFTUNG(iB2, bibVECTOR.get_vCompVector(new MyE2_Label(new MyE2_String("")), 	new MyE2_Label("")),E2_INSETS.I_0_0_10_0), iListe);
		}
		
		oGridInnen.add(new E2_SelektorContainer_MIT_BESCHRIFTUNG(iB2, bibVECTOR.get_vCompVector(new MyE2_Label(new MyE2_String("Infoeintrag:")), 	oSelZeitraumInfoEintraege.get_oComponentWithoutText()),E2_INSETS.I_0_0_10_0), iListe);
		
		oSelVector.get_oCB_InvertiereAuswahl().setBackground(new E2_ColorDark());
		if (oSelektorZusatzFelder.get_oComponentForSelection() instanceof MyE2_Label)
		{
			oGridInnen.add(oSelVector.get_oCB_InvertiereAuswahl(),iListe);
		}
		else
		{
			oGridInnen.add(new E2_ComponentGroupHorizontal(0, 
					oSelektorZusatzFelder.get_oComponentForSelection(),
					new E2_SelektorContainer_MIT_BESCHRIFTUNG(iB5a, bibVECTOR.get_vCompVector(oSelVector.get_oCB_InvertiereAuswahl()),null),
					E2_INSETS.I_0_0_20_0), 	iListe);
		}
		
		
		//experimentell
//		oGridInnen.add(oSelectMatSpez.get_oComponentForSelection());
		
		
	}

	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	

 
	

	
	
}
