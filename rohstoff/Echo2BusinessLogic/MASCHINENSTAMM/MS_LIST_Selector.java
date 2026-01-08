package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Grid;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class MS_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public MS_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		MyE2_Grid  oGrid = new MyE2_Grid(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid.setOrientation(Grid.ORIENTATION_HORIZONTAL);

		MS_LIST_SelectorMultiDropdown_Bediener 			oSelectorBediener = new MS_LIST_SelectorMultiDropdown_Bediener();
		MS_LIST_SelectorMultiDropdown_MaschinenTyp		oSelectorMaschinenTyp = new MS_LIST_SelectorMultiDropdown_MaschinenTyp();
		MS_LIST_Selector_AKTIV_INAKTIV  				oSelectorAktiv = new MS_LIST_Selector_AKTIV_INAKTIV();
		MS_LIST_SelectorMultiDropdown_kostenstelle      oSelKostenstelle = new MS_LIST_SelectorMultiDropdown_kostenstelle();
		
		this.oSelVector.add(oSelectorAktiv);
		this.oSelVector.add(oSelectorBediener);
		this.oSelVector.add(oSelectorMaschinenTyp);
		this.oSelVector.add(oSelKostenstelle);

		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		
		
		this.oSelVector.set_SESSION_HASH_4_SAVE_SETTINGS("MS__LIST_SELECTOR_SAVE_SELECTIONS");
		this.oSelVector.LOAD_LAST_SETTINGS();

		
		oGrid.add(oSelectorMaschinenTyp.get_oComponentForSelection(), 	E2_INSETS.I_2_2_20_2);
		oGrid.add(oSelectorAktiv.get_oComponentForSelection(), 			E2_INSETS.I_2_2_20_2);
		oGrid.add(oSelectorBediener.get_oComponentForSelection(), 		E2_INSETS.I_2_2_20_2);
		oGrid.add(oSelKostenstelle.get_oComponentForSelection(), 		E2_INSETS.I_2_2_20_2);	
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100),E2_INSETS.I_0_0_0_0);
		
		this.add(oGrid);
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
