package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.Echo2.components.MyE2_SelectField_WithAutoToolTip;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * test
 * @author martin
 *
 */
public class MS_LIST_SelectorMultiDropdown_kostenstelle extends E2_ListSelectorMultiDropDown
{
	public MS_LIST_SelectorMultiDropdown_kostenstelle() throws myException
	{
		super(new ownSelector(), MS__CONST.ADDON_FIELDS.LIST_KOSTENSTELLE_GESAMT.querydef()+"='#WERT#'");
		this.fill_Grid4AnzeigeStatusSingle();
		this.get_oSelFieldBasis().set_ActiveValue("");

	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException
	{
		return new ownE2_BasicModuleContainer();
	}
	
	private class ownE2_BasicModuleContainer extends E2_BasicModuleContainer
	{
	}


	@Override
	public void fill_Grid4AnzeigeStatusMulti()
	{
		int[] iSpalten = {80,80,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Kostenstelle:"));
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten = {90,80,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Kostenstelle:"));
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
	}

	
	public static class ownSelector extends MyE2_SelectField_WithAutoToolTip {

		public ownSelector() throws myException {
			super();
			this.setWidth(new Extent(80));
			SEL sel = new SEL(	MS__CONST.ADDON_FIELDS.LIST_KOSTENSTELLE_GESAMT.querydef(),
								MS__CONST.ADDON_FIELDS.LIST_KOSTENSTELLE_GESAMT.querydef()).ADD_Distinct().FROM(_TAB.maschinen).ORDERUP(MS__CONST.ADDON_FIELDS.LIST_KOSTENSTELLE_GESAMT.querydef());
			
			String[][] kostenstelle = bibDB.EinzelAbfrageInArray(sel.s(),"");

			if (kostenstelle!=null && kostenstelle.length>0) {
				this.populateCombobox(kostenstelle, null, "", true, false);
			}
			
		}

		
	}
	
	
}