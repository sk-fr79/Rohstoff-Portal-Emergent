package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

/**
 * test
 * @author martin
 *
 */
public class MS_LIST_SelectorMultiDropdown_Bediener extends E2_ListSelectorMultiDropDown
{
	public MS_LIST_SelectorMultiDropdown_Bediener() throws myException
	{
		super(new Component_USER_DROPDOWN_NEW(false,180), "((NVL("+_DB.MASCHINEN+"."+_DB.MASCHINEN$ID_USER_BEDIENER1+",0)=#WERT#) OR (NVL("+_DB.MASCHINEN+"."+_DB.MASCHINEN$ID_USER_BEDIENER2+",0)=#WERT#))");
		this.fill_Grid4AnzeigeStatusSingle();
		this.get_oSelFieldBasis().setWidth(new Extent(180));
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
		int[] iSpalten = {90,150,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Bediener(1/2):"));
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten = {90,150,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Bediener(1/2):"));
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
	}

	
}