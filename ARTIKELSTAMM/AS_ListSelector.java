package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.GROUP_COLLECTOR.SELECTOR_GROUPS;

public class AS_ListSelector extends E2_ListSelectorContainer

{
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	private static int[] i2Spalten = {150,100};
	private static int[] i3Spalten = {120,55,120};



	public AS_ListSelector(AS_BasicModulContainerLIST oModulContainerLIST, E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);

		MyE2_SelectField	oSelectSortennummer = new MyE2_SelectField(
				"select distinct SUBSTR(ANR1,1,2)||'xx',SUBSTR(ANR1,1,2) from "+bibE2.cTO()+".JT_ARTIKEL ORDER BY SUBSTR(ANR1,1,2)",
				false,true,false,false);
		
		
		//selector fuer artikelbereich
		MyE2_SelectField	oSelectSortenbereich = new MyE2_SelectField(
				"select BEREICH,ID_ARTIKEL_BEREICH from "+bibE2.cTO()+".JT_ARTIKEL_BEREICH ORDER BY 1",
				false,true,false,false);
		oSelectSortenbereich.setWidth(new Extent(150));
		
		//selector fuer artikelgruppen
		MyE2_SelectField	oSelectSortengruppen = new MyE2_SelectField(
				"select GRUPPE||'  ('||BEREICH||')',ID_ARTIKEL_GRUPPE from "+bibE2.cTO()+".JT_ARTIKEL_GRUPPE " +
				" LEFT OUTER JOIN JT_ARTIKEL_BEREICH ON (JT_ARTIKEL_GRUPPE.ID_ARTIKEL_BEREICH=JT_ARTIKEL_BEREICH.ID_ARTIKEL_BEREICH) ORDER BY 1",
				false,true,false,false);
		oSelectSortengruppen.setWidth(new Extent(150));
		

		//jetzt dem bereich einen agenten mitgeben, der den gruppenselektor manipuliert
		oSelectSortenbereich.add_oActionAgent(new ownActionFillGruppeNew(oSelectSortengruppen));
		
		
		

		// selector fuer selbstdefinierte gruppen
		SELECTOR_GROUPS oSelGroups = new SELECTOR_GROUPS("JT_ARTIKEL","ID_ARTIKEL",oModulContainerLIST.get_MODUL_IDENTIFIER());

		

		
		oSelVector.add(new E2_ListSelectorStandard(oSelectSortennummer,"JT_ARTIKEL.ANR1 LIKE '#WERT#%'", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectSortenbereich,"JT_ARTIKEL.ID_ARTIKEL_GRUPPE IN" +
											" (SELECT ID_ARTIKEL_GRUPPE FROM "+bibE2.cTO()+".JT_ARTIKEL_GRUPPE  WHERE ID_ARTIKEL_BEREICH=#WERT#)", null, null));

		oSelVector.add(new E2_ListSelectorStandard(oSelectSortengruppen,"JT_ARTIKEL.ID_ARTIKEL_GRUPPE = #WERT#", null, null));
		
		oSelVector.add(oSelGroups);

		Selektor_AktivInaktiv  				oSEL_AKTIV = 	new Selektor_AktivInaktiv();
		Selektor_GefahrGutNichtGefahrgut 	oSEL_GEFAHR = 	new Selektor_GefahrGutNichtGefahrgut();
		

		Selektor_Sortentypen  oSelSortenTypen = new Selektor_Sortentypen();
		
		
		oSelVector.add(oSEL_AKTIV);
		oSelVector.add(oSEL_GEFAHR);
		oSelVector.add(oSelSortenTypen);
		
		MyE2_Grid  oGridSelektor = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL_W100());
		
		this.add(oGridSelektor,E2_INSETS.I(2,2,2,2));

		//Spaltenweise, 2 Zeilen
		oGridSelektor.add(new MyE2_Label(new MyE2_String("Sorten-Hauptnummer:")));
		oGridSelektor.add(new MyE2_Label(new MyE2_String("Artikel-Bereich:")),E2_INSETS.I(4,2,4,2));
		oGridSelektor.add(oSelectSortennummer, new Insets(4,2,20,2));
		oGridSelektor.add(oSelectSortenbereich, new Insets(4,2,20,2));
		
		oGridSelektor.add(new MyE2_Label(new MyE2_String("Manuelle Gruppen:")));
		oGridSelektor.add(new MyE2_Label(new MyE2_String("Artikel-Gruppen:")),E2_INSETS.I(4,2,4,2));
		oGridSelektor.add(oSelGroups.get_oComponentForSelection(), E2_INSETS.I(4,2,20,2));
		oGridSelektor.add(oSelectSortengruppen, E2_INSETS.I(4,2,20,2));
		
		oGridSelektor.add(oSEL_AKTIV.get_oComponentForSelection(), E2_INSETS.I(4,2,20,2));
		oGridSelektor.add(oSEL_GEFAHR.get_oComponentForSelection(), E2_INSETS.I(4,2,20,2));
		
		oGridSelektor.add(oSelSortenTypen.get_oComponentForSelection(),1,2,E2_INSETS.I(2,2,2,2),new Alignment(Alignment.LEFT, Alignment.TOP));
		
		
		//2015-12-16: neuer filter-selektor
		AS_ListSelector_Filter filter = new AS_ListSelector_Filter();
		oSelVector.add(filter);
		oGridSelektor.add(filter.get_oComponentForSelection(),1,1,E2_INSETS.I(2,2,2,2),new Alignment(Alignment.LEFT, Alignment.TOP));
		
		//2016-08-08: neuer selektor fuer korrekte zolltarifnummern
		Selektor_Korrekte_ZOLLTARIFNR selZT = new Selektor_Korrekte_ZOLLTARIFNR();
		oSelVector.add(selZT);
		oGridSelektor.add(selZT.get_oComponentForSelection(),1,1,E2_INSETS.I(2,2,2,2),new Alignment(Alignment.LEFT, Alignment.TOP));
		
		
		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(oModulContainerLIST.get_MODUL_IDENTIFIER());
		oSelVector.add(oDB_BasedSelektor);
		oGridSelektor.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse:"),100), new Insets(4,2,10,2));
		
	}


	
	public E2_SelectionComponentsVector get_oSelVector() {
		return oSelVector;
	}


	
	
	
	private class ownActionFillGruppeNew extends XX_ActionAgent
	{
		private MyE2_SelectField  oSelFieldGruppe = null;
		
		public ownActionFillGruppeNew(MyE2_SelectField oSelFieldGruppe) 
		{
			super();
			this.oSelFieldGruppe = oSelFieldGruppe;
		}



		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_SelectField  oSelfieldBereich = ((MyE2_SelectField) oExecInfo.get_MyActionEvent().getSource());
			String cBEREICH = oSelfieldBereich.get_ActualWert();
			if (S.isEmpty(cBEREICH))
			{
				this.oSelFieldGruppe.set_ListenInhalt("select GRUPPE||'  ('||BEREICH||')',ID_ARTIKEL_GRUPPE from "+bibE2.cTO()+".JT_ARTIKEL_GRUPPE " +
						" LEFT OUTER JOIN JT_ARTIKEL_BEREICH ON (JT_ARTIKEL_GRUPPE.ID_ARTIKEL_BEREICH=JT_ARTIKEL_BEREICH.ID_ARTIKEL_BEREICH) ORDER BY 1"
						, false,true,false,false);
			}
			else
			{
				this.oSelFieldGruppe.set_ListenInhalt(
						"select GRUPPE||'  ('||BEREICH||')',ID_ARTIKEL_GRUPPE from "+bibE2.cTO()+".JT_ARTIKEL_GRUPPE " +
						" LEFT OUTER JOIN JT_ARTIKEL_BEREICH ON (JT_ARTIKEL_GRUPPE.ID_ARTIKEL_BEREICH=JT_ARTIKEL_BEREICH.ID_ARTIKEL_BEREICH) " +
						" WHERE JT_ARTIKEL_GRUPPE.ID_ARTIKEL_BEREICH="+cBEREICH+
						" ORDER BY 1"
						, false,true,false,false);
			}
			this.oSelFieldGruppe.set_ActiveInhalt_or_FirstInhalt(null);
		}
		
	}
	
	
	
	
	private class Selektor_AktivInaktiv extends E2_ListSelektorMultiselektionStatusFeld_STD
	{
		public Selektor_AktivInaktiv()
		{
			super(AS_ListSelector.i2Spalten,false,null,new Extent(80));
			this.set_cConditionWhenAllIsSelected("");
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$AKTIV+",'N')='Y')",	new MyE2_String("Aktiv"),  	new MyE2_String("Blende aktive Sorten ein"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$AKTIV+",'N')='N')",	new MyE2_String("Inaktiv"),	new MyE2_String("Blende inaktive Sorten ein"));		
		}
	}


	private class Selektor_GefahrGutNichtGefahrgut extends E2_ListSelektorMultiselektionStatusFeld_STD
	{
		
		public Selektor_GefahrGutNichtGefahrgut()
		{
			super(AS_ListSelector.i2Spalten,false,new MyE2_String(""),new Extent(80));
			this.set_cConditionWhenAllIsSelected("");
			
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$GEFAHRGUT+",'N')='N')",	new MyE2_String("Ungefährl. Güter"),new MyE2_String("Zeige ungefährliche Sorten"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$GEFAHRGUT+",'N')='Y')",	new MyE2_String("Gefahrgut"),   	new MyE2_String("Zeige Gefahrgut-Sorten an"));		
		}
	}

	
	
	
	private class Selektor_Sortentypen extends E2_ListSelektorMultiselektionStatusFeld_STD
	{
		public Selektor_Sortentypen()
		{
			super(AS_ListSelector.i3Spalten,true,new MyE2_String("Sortenvarianten:"),new Extent(100));
			this.set_cConditionWhenAllIsSelected("");
			
			this.ADD_STATUS_TO_Selector(true,	"((NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$IST_PRODUKT+",'N')='N') AND " +
					"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$DIENSTLEISTUNG+",'N')='N') AND " +
					"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$END_OF_WASTE+",'N')='N'))",		new MyE2_String("Rohstoffsorten"), 	new MyE2_String("Blende Standard-Rohstoffsorten ein"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$IST_PRODUKT+",'N')='Y')",		new MyE2_String("Produkt"),   		new MyE2_String("Blende Sorten vom Typ Produkt ein"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$DIENSTLEISTUNG+",'N')='Y')",	new MyE2_String("Dienstleistung"), 	new MyE2_String("Blende Sorten vom Typ Dienstleistung ein"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$END_OF_WASTE+",'N')='Y')",		new MyE2_String("End of Waste"),   	new MyE2_String("Blende Sorten vom Typ \"End of Waste\" ein"));
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.ARTIKEL+"."+_DB.ARTIKEL$IST_LEERGUT+",'N')='Y')",		new MyE2_String("Leergut"), 	  	new MyE2_String("Blende Sorten vom Typ Leergut ein"));
		}
	}
	
	
	
	
	private class Selektor_Korrekte_ZOLLTARIFNR extends E2_ListSelektorMultiselektionStatusFeld_STD {
		public Selektor_Korrekte_ZOLLTARIFNR() {
			super(bibARR.ia(150,150),false,null,new Extent(1));
			this.set_cConditionWhenAllIsSelected("");
			
			String sql = "(SELECT ART.ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKEL ART "
											+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ZOLLTARIFNUMMER ZT1 ON (ART.ID_ZOLLTARIFNUMMER=ZT1.ID_ZOLLTARIFNUMMER) "
											+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ZOLLTARIFNUMMER ZT2 ON (ART.ZOLLTARIFNR=ZT2.NUMMER) "
											+ " WHERE "
											+ "( NVL(ZT1.NUMMER,'0')=NVL(ZT2.NUMMER,'0') "
											+ "  AND (NVL(ZT1.AKTIV,'N')='Y' "
											+ "  AND ART.ID_ZOLLTARIFNUMMER IS NOT NULL "
											+ "  AND ART.ZOLLTARIFNR IS NOT NULL"
											+ ") OR (ART.ID_ZOLLTARIFNUMMER IS NULL AND ART.ZOLLTARIFNR IS NULL))"
						+ ")";
			
			this.ADD_STATUS_TO_Selector(true,	"JT_ARTIKEL.ID_ARTIKEL IN "+sql, new MyE2_String("Zolltar.Nr. OK"),  				new MyE2_String("Zeige Sorten mit korrekter Zolltarifnummer"));		
			this.ADD_STATUS_TO_Selector(true,	"JT_ARTIKEL.ID_ARTIKEL NOT IN "+sql, new MyE2_String("Zolltar.Nr. falsch"),  	new MyE2_String("Zeige Sorten mit fehlerhafter Zolltarifnummer"));		
		}
	}


	
}
