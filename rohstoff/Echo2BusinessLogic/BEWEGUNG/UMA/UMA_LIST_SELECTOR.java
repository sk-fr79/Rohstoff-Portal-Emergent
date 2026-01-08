package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.LIST_SELECTOR_BENUTZER;

public class UMA_LIST_SELECTOR extends E2_ListSelectorContainer
{
	private  ownSelectionsComponentsVector 		oSelVector = null;
	private  SelectorHelperFirmen      			oSelFirmen = null;
	
	private static   int[]                      ibreiteSpaltenCheckboxen = {100,100};
	
	public UMA_LIST_SELECTOR(E2_NavigationList oNavList, String cMODULE_KENNER) throws myException
	{
		super();

		this.oSelVector  	=	new ownSelectionsComponentsVector(oNavList);
		this.oSelFirmen 	=   new SelectorHelperFirmen(this.oSelVector);   

		
		String cSQL_QueryFirmen = "JT_UMA_KONTRAKT.ID_ADRESSE=#WERT#";
		oSelVector.add(new E2_ListSelectorStandard(this.oSelFirmen.get_oSelKunden(), cSQL_QueryFirmen, new MyE2_String("Kunden"),10));

		E2_SelektorDateFromTo_NG  oSelDateVonBis = new E2_SelektorDateFromTo_NG(new MyE2_String("Datum UMA-Vertrag: "),"JT_UMA_KONTRAKT.DATUM_VERTRAG","JT_UMA_KONTRAKT.DATUM_VERTRAG",new Extent(80));
		oSelVector.add(oSelDateVonBis);
		
		LIST_SELECTOR_BENUTZER  oListSelUser = new LIST_SELECTOR_BENUTZER(new MyE2_String("Betreuer"), "JT_UMA_KONTRAKT.ID_USER_BETREUER=#WERT#");
		oSelVector.add(oListSelUser);
		
		SelectorOffenGeschlossen     oSelWasSollAngezeigtWerdenOG = new SelectorOffenGeschlossen();
		oSelVector.add(oSelWasSollAngezeigtWerdenOG);
		
		SelectorDeletedUndeleted     oSelWasSollAngezeigtWerdenDelUndel = new SelectorDeletedUndeleted();
		oSelVector.add(oSelWasSollAngezeigtWerdenDelUndel);
		
		
		MyE2_Grid  oGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
		
		oGrid.add(this.oSelFirmen, E2_INSETS.I_0_2_20_2);
		oGrid.add(oSelDateVonBis.get_oComponentForSelection(131), E2_INSETS.I_0_2_10_2);
		oGrid.add(oListSelUser.get_oComponentForSelection(80, 200), E2_INSETS.I_0_2_10_2);
		oGrid.add(new MyE2_Label(""), E2_INSETS.I_0_2_10_2);
		oGrid.add(oSelWasSollAngezeigtWerdenOG.get_oComponentForSelection(), E2_INSETS.I_0_2_10_2);
		oGrid.add(oSelWasSollAngezeigtWerdenDelUndel.get_oComponentForSelection(), E2_INSETS.I_0_2_10_2);
		
		
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100), E2_INSETS.I_0_2_10_2);

		
		this.add(oGrid, E2_INSETS.I_2_0_2_0);
		
		
	}
    
	public E2_SelectionComponentsVector  get_oSelVector()
	{
		return this.oSelVector;
	}
	
	
	
	
	private class ownSelectionsComponentsVector extends E2_SelectionComponentsVector
	{
		public ownSelectionsComponentsVector(E2_NavigationList onavigationList) throws myException
		{
			super(onavigationList);
		}
		
	}

	
	private class SelectorHelperFirmen extends  SELECTOR_COMPONENT_FirmenAuswahl
	{
		public SelectorHelperFirmen(E2_SelectionComponentsVector SelVector) throws myException
		{
			super("", 100, 250, SelVector, new MyE2_String("Kunden"));
			String cSqlSel =	"  SELECT  NVL(AD.NAME1,'-')||'-'||  NVL(AD.NAME2,'-')|| "+
											" '-'||  NVL(AD.ORT,'-') ||' ('||TO_CHAR(  NVL(AD.ID_ADRESSE,0))||')' AS NAMEN , AD.ID_ADRESSE AS  ID_ADRESSE "+
											" FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT UMA LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (UMA.ID_ADRESSE=AD.ID_ADRESSE) "+
											" ORDER BY NAMEN";
			this.set_cQuery4AdressListe(cSqlSel,true);
			this.get_oSelKunden().setWidth(new Extent(310));
		}
	}

	
	private class SelectorOffenGeschlossen extends E2_ListSelektorMultiselektionStatusFeld_STD
	{
		
		public SelectorOffenGeschlossen()
		{
			super(UMA_LIST_SELECTOR.ibreiteSpaltenCheckboxen,true,new MyE2_String("Anzeigen: "),new Extent(60));
			
			this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_UMA_KONTRAKT.ABGESCHLOSSEN,'N')='N')",		new MyE2_String("Offene"),   		new MyE2_String("Offene UMA-Kontrakte anzeigen"));
			this.ADD_STATUS_TO_Selector(false,	"(NVL(JT_UMA_KONTRAKT.ABGESCHLOSSEN,'N')='Y')",		new MyE2_String("Geschlossene"),   new MyE2_String("Abgeschlossene UMA-Kontrakte anzeigen"));

		}
	}
	
	private class SelectorDeletedUndeleted extends E2_ListSelektorMultiselektionStatusFeld_STD
	{
		public SelectorDeletedUndeleted()
		{
			super(UMA_LIST_SELECTOR.ibreiteSpaltenCheckboxen,true,new MyE2_String("Anzeigen: "),new Extent(60));
			
			this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_UMA_KONTRAKT.DELETED,'N')='N')",		    new MyE2_String("Ungelöschte"),   	new MyE2_String("Ungelöschte UMA-Kontrakte anzeigen"));
			this.ADD_STATUS_TO_Selector(false,	"(NVL(JT_UMA_KONTRAKT.DELETED,'N')='Y')",		    new MyE2_String("Gelöschte"),   	new MyE2_String("Gelöschte UMA-Kontrakte anzeigen"));

		}
	}

	
	
}
