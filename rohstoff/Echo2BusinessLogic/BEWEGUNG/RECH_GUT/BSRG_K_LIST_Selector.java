package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_Selector_Monat_Jahr;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_ADRESSKLASSE_MIT_Beschreibung;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_BENUTZER_MIT_Beschreibung_Multi;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_LAENDERCODE_MIT_Beschreibung;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSRG_K_LIST_Selector extends E2_ListSelectorContainer 
{


	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	
//	private SELECTOR_BENUTZER_MIT_Beschreibung   oSelectMitarbeiterADR = null;
//	private SELECTOR_BENUTZER_MIT_Beschreibung   oSelectMitarbeiterANG = null;

	//2012-10-12: umstellung auf multiselect
	private SELECTOR_BENUTZER_MIT_Beschreibung_Multi  oSelectMitarbeiterADR = null;
	private SELECTOR_BENUTZER_MIT_Beschreibung_Multi  oSelectMitarbeiterANG = null;
	
	private SELECTOR_LAENDERCODE_MIT_Beschreibung		oSelectLand = null;
	private SELECTOR_ADRESSKLASSE_MIT_Beschreibung		oSelectAdressKlasse = null;

	private MyE2_CheckBox					oCheckBoxNURAbgeschlossen = new MyE2_CheckBox(new MyE2_String("NUR Abgeschlossen ?"), new MyE2_String("Zeige nur Belege, die abgeschlossen / gedruckt sind und (falls Mail-Belege), die komplett verschickt sind."));
	private MyE2_CheckBox					oCheckBoxNUROffen = new MyE2_CheckBox(new MyE2_String("NUR Offen ?"), new MyE2_String("Zeige nur Belege, die offen / ungedruckt sind oder deren Beleg erstellt, aber noch unverschickt ist"));
	private MyE2_CheckBox  					oCB_ShowDeletedRows = new MyE2_CheckBox(new MyE2_String("Zeige gelöschte"), new MyE2_String("Zeige auch Belege, die gelöscht worden sind"));
	
	private MyE2_CheckBox 					oCB_TeilzahlungsBelege = new MyE2_CheckBox(new MyE2_String("Teilzahlung"),new MyE2_String("Teilzahlungsbelege auswählen, die noch nicht storniert sind"));
	//2012-01-18: keine mahnung
	private MyE2_CheckBox  					oCB_ShowKeineMahnung = new MyE2_CheckBox(new MyE2_String("Keine Mahnung"), new MyE2_String("Zeige nur Belege, die den Schalter KEINE Mahnung zeigen, oder deren Firma diesen Schalter trägt"));
	
	private SELECTOR_COMPONENT_FirmenAuswahl oSelKundenMitKontrakten = null; 
	
	
	public BSRG_K_LIST_Selector(E2_NavigationList oNavigationList, String cBelegtyp, String cMODUL_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);
		
//		this.oSelectMitarbeiterADR = 	new SELECTOR_BENUTZER_MIT_Beschreibung(120, 100, new MyE2_String("Betreuer (ADR):"));
//		this.oSelectMitarbeiterANG = 	new SELECTOR_BENUTZER_MIT_Beschreibung(120,100, new MyE2_String("Ersteller "+(cBelegtyp.equals(myCONST.VORGANGSART_RECHNUNG)?"(RECH):":"(GUT):")));
		
		this.oSelectMitarbeiterADR = 	new SELECTOR_BENUTZER_MIT_Beschreibung_Multi(120, 100, new MyE2_String("Betreuer (ADR):"),"JT_ADRESSE.ID_USER = #WERT#");
		this.oSelectMitarbeiterANG = 	new SELECTOR_BENUTZER_MIT_Beschreibung_Multi(120,100, new MyE2_String("Ersteller "+(cBelegtyp.equals(myCONST.VORGANGSART_RECHNUNG)?"(RECH):":"(GUT):")),"JT_VKOPF_RG.ID_USER = #WERT#");
		
		this.oSelectLand = 				new SELECTOR_LAENDERCODE_MIT_Beschreibung(80,100, new MyE2_String("Land: "));
		this.oSelectAdressKlasse = 		new SELECTOR_ADRESSKLASSE_MIT_Beschreibung(80,100, new MyE2_String("Adr.Klasse: "));

		
		
//		String cSQLSelMonate = "ID_VKOPF_RG IN (" +
//						"SELECT JT_VPOS_RG.ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE " +
//						" TO_NUMBER(TO_CHAR(JT_VPOS_RG.AUSFUEHRUNGSDATUM,'MM')) = #WERT#)";
//
//		String cSQLSelJahre = "ID_VKOPF_RG IN (" +
//						"SELECT JT_VPOS_RG.ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE " +
//						" TO_NUMBER(TO_CHAR(JT_VPOS_RG.AUSFUEHRUNGSDATUM,'YYYY')) = #WERT#)";


		String cQueryKundenSelektor =  "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
						"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
						" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN "+
						" (SELECT DISTINCT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE NVL(DELETED,'N')='N' AND VORGANG_TYP="
						+ bibALL.MakeSql(cBelegtyp)+" ) "+" ORDER BY NAMEN";

		oSelKundenMitKontrakten = new SELECTOR_COMPONENT_FirmenAuswahl(cQueryKundenSelektor,73, 150, this.oSelVector, new MyE2_String("Firma:"));
		this.oSelKundenMitKontrakten.REFRESH_KundenSelektor();

		oCheckBoxNUROffen.setSelected(true);
		
		oSelVector.add(oSelectMitarbeiterADR);
		oSelVector.add(oSelectMitarbeiterANG);
		oSelVector.add(new E2_ListSelectorStandard(oSelectLand.get_oSelLaenderCode(),"JT_VKOPF_RG.LAENDERCODE = '#WERT#'", null, null));
		
		//2015-11-20: selektor-austausch
		OwnSelector_MonatJahr_Leistungsdatum sel_combi_mon_jahr = new OwnSelector_MonatJahr_Leistungsdatum();
		oSelVector.add(sel_combi_mon_jahr);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelectAdressKlasse.get_oSelAdressKlasse(),"JT_VKOPF_RG.ID_ADRESSE in (select JT_ADRESSKLASSE.ID_ADRESSE from "+bibE2.cTO()+".JT_ADRESSKLASSE where ID_ADRESSKLASSE_DEF=#WERT#)", null, null));
		
		//2015-01-19: neue bedingung fuer nur offen
		BRSG__TERM_FindUnsend_basic 			termUnsend_basic = 	new BRSG__TERM_FindUnsend_basic();
		String cAbgeschlossen = "(NVL(JT_VKOPF_RG.ABGESCHLOSSEN,'N')='Y'"+" AND "+termUnsend_basic.get_SQL_Bedingung_4_Selector()+")";
		
		oSelVector.add(new E2_ListSelectorStandard(this.oCheckBoxNURAbgeschlossen,cAbgeschlossen,""));
		oSelVector.add(new E2_ListSelectorStandard(this.oCheckBoxNUROffen,		  " NOT "+cAbgeschlossen,""));

		oSelVector.add(new E2_ListSelectorStandard(this.oCB_ShowDeletedRows,"","  NVL(JT_VKOPF_RG.DELETED,'N')='N'"));
		
		oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitKontrakten.get_oSelKunden()," JT_VKOPF_RG.ID_ADRESSE=#WERT#",null,null));

		
		// Alleinstellungs-Selektion: Alle Belege mit Teilzahlungsabzug, die noch nicht storniert sind
		E2_ListSelectorStandard oSelTeilzahlungen = new E2_ListSelectorStandard(oCB_TeilzahlungsBelege,
				"JT_VKOPF_RG.ID_VKOPF_RG in ("+
				" SELECT JT_VKOPF_RG.ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_VPOS_RG"+ 
				" LEFT OUTER JOIN JT_VKOPF_RG ON (JT_VPOS_RG.ID_VKOPF_RG=JT_VKOPF_RG.ID_VKOPF_RG)"+
				" INNER JOIN JT_VPOS_RG_ABZUG ON (JT_VPOS_RG_ABZUG.ID_VPOS_RG=JT_VPOS_RG.ID_VPOS_RG)"+
				" WHERE JT_VPOS_RG_ABZUG.ABZUGTYP LIKE 'EPREIS_PROZENT_VORAUSZAHLUNG%' AND " +
				" NVL(JT_VPOS_RG.ID_VPOS_RG_STORNO_NACHFOLGER,-1)=-1 AND NVL(JT_VPOS_RG.ID_VPOS_RG_STORNO_VORGAENGER,-1)=-1"+")","");
		oSelTeilzahlungen.set_IstAlleinMerkmal(true, new MyE2_String("Selektion NUR nach unbearbeiteten Teilzahlungsbelegen "));
		oSelVector.add(oSelTeilzahlungen);
		

		E2_ListSelectorStandard  oSelKeineMahnung = new E2_ListSelectorStandard(this.oCB_ShowKeineMahnung, 
				"JT_VKOPF_RG.ID_VKOPF_RG in ("+
				" SELECT R.ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_VKOPF_RG R " +
						" LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE A ON (R.ID_ADRESSE=A.ID_ADRESSE)  " +
						" LEFT OUTER JOIN "+bibE2.cTO()+".JT_FIRMENINFO F ON (F.ID_ADRESSE=A.ID_ADRESSE)  " +
				" WHERE NVL(F.KEINE_MAHNUNGEN,'N')='Y' OR  NVL(R.KEINE_MAHNUNGEN,'N')='Y' "+
				")","");
		
		oSelVector.add(oSelKeineMahnung);
		
		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODUL_KENNER);
		oSelVector.add(oDB_BasedSelektor);

		
		MyE2_Grid oGrid = new MyE2_Grid(2,0);
		oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
		
		Insets oIN2 = new Insets(0,2,20,2);
		
		oGrid.add(oSelectMitarbeiterADR.get_oComponentForSelection(),1,oIN2);		
		oGrid.add(oSelectMitarbeiterANG.get_oComponentForSelection(),1,oIN2);
		oGrid.add(oSelectLand,1,oIN2);				
		oGrid.add(oSelectAdressKlasse,1,oIN2); 
		
//		oGrid.add(oSelMonatJahr,1,oIN2);	
		oGrid.add(sel_combi_mon_jahr.get_oComponentForSelection(),1,oIN2);	
		oGrid.add(oSelKundenMitKontrakten,1,oIN2);
		
		oGrid.add(this.oCheckBoxNUROffen,1,oIN2);
		oGrid.add(this.oCheckBoxNURAbgeschlossen,1,oIN2);
		
		oGrid.add(this.oCB_ShowDeletedRows,1,oIN2);
		oGrid.add(oCB_TeilzahlungsBelege,1,oIN2);
		
		oGrid.add(this.oCB_ShowKeineMahnung,1,oIN2);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Weitere:"), 100), new Insets(4,2,10,2));

		
		this.add(oGrid);
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}



	public MyE2_CheckBox get_oCB_ShowDeletedRows()
	{
		return oCB_ShowDeletedRows;
	}
	
	
	
	//2015-11-20: neuer selektor wegen queryproblemen
	private class OwnSelector_MonatJahr_Leistungsdatum extends E2_Selector_Monat_Jahr {

		public OwnSelector_MonatJahr_Leistungsdatum() throws myException {
			super();
		}


		@Override
		public Component get_oComponentWithoutText() throws myException {
			int[] i_breit= {100,60,95};
			//MyE2_Grid grid_rueck = new MyE2_Grid(i_breit,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			MyE2_Grid grid_rueck = new MyE2_Grid(i_breit,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			grid_rueck.add(new MyE2_Label("Leist.dat.(Pos.):"), E2_INSETS.I(0,0,10,0));
			grid_rueck.add(this.get_oSelMonate(), E2_INSETS.I(0,0,2,0));
			grid_rueck.add(this.get_oSelJahre(), E2_INSETS.I(0,0,5,0));
			
			return grid_rueck;
		}

		@Override
		public String get_WhereBlock() throws myException {
			String c_rueck = "";
			Vector<String> v_rueck = new Vector<String>();
			String mon = this.get_oSelMonate().get_ActualWert();
			String year = this.get_oSelJahre().get_ActualWert();	
			
			if (S.isFull(mon)) {
				v_rueck.add("TO_NUMBER(TO_CHAR(JT_VPOS_RG.AUSFUEHRUNGSDATUM,'MM')) = "+mon);
			}
			if (S.isFull(year)) {
				v_rueck.add("TO_NUMBER(TO_CHAR(JT_VPOS_RG.AUSFUEHRUNGSDATUM,'YYYY')) = "+year);
			}
			
			
			if (v_rueck.size()>0) {
				String bed = bibALL.Concatenate(v_rueck, " AND ", "");
				if (S.isFull(bed)) {
					c_rueck = "(ID_VKOPF_RG IN (" +
						"SELECT JT_VPOS_RG.ID_VKOPF_RG FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE "+bed+
											")"+
								")";
				}
				
			}
			return c_rueck;
		}
		
	}
	

}
