package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.exceptions.myException;

/**
 * test
 * @author martin
 *
 */
public class FS_SelectorMultiDropdown_Betreuer extends E2_ListSelectorMultiDropDown
{
	public FS_SelectorMultiDropdown_Betreuer() throws myException
	{
		super(new Component_USER_DROPDOWN_NEW(false,100), "JT_ADRESSE.ID_USER = #WERT#");
		this.fill_Grid4AnzeigeStatusSingle();
		this.get_oSelFieldBasis().setWidth(new Extent(100));
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
		int[] iSpalten = {80,102,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Betreuer(1):"));
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten = {80,102,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Betreuer(1):"));
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
	}

}