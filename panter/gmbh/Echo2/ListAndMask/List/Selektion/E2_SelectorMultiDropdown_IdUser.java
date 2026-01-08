package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

/**
 * test
 * @author martin
 *
 */
public class E2_SelectorMultiDropdown_IdUser extends E2_ListSelectorMultiDropDown {
	
	private String feldBenennung = "Mitarbeiter";
	private int    widthAnzeige = 100;
	private int    widthSelField = 100;
	
	public E2_SelectorMultiDropdown_IdUser(IF_Field fieldUser, String p_feldBenennung, int selectFieldWidth, int anzeigeLabelWidth ) throws myException 	{
		super(new Component_USER_DROPDOWN_NEW(false,selectFieldWidth), fieldUser.tnfn()+" = #WERT#");
		this.feldBenennung = p_feldBenennung;
		this.widthSelField = selectFieldWidth;
		this.widthAnzeige  = anzeigeLabelWidth;
		
		this.fill_Grid4AnzeigeStatusSingle();
		this.get_oSelFieldBasis().setWidth(new Extent(selectFieldWidth));
		this.get_oSelFieldBasis().set_ActiveValue("");

	}

	/**
	 * hier muss die methode public String get_WhereBlock() throws myException {} ueberschrieben werden

	 * @author martin
	 * @date 30.12.2019
	 *
	 * @param p_feldBenennung
	 * @param selectFieldWidth
	 * @param anzeigeLabelWidth
	 * @throws myException
	 */
	public E2_SelectorMultiDropdown_IdUser(String p_feldBenennung, int selectFieldWidth, int anzeigeLabelWidth ) throws myException 	{
		super(new Component_USER_DROPDOWN_NEW(false,selectFieldWidth), " DUMMYSCHABLONE = #WERT#");
		this.feldBenennung = p_feldBenennung;
		this.widthSelField = selectFieldWidth;
		this.widthAnzeige  = anzeigeLabelWidth;
		
		this.fill_Grid4AnzeigeStatusSingle();
		this.get_oSelFieldBasis().setWidth(new Extent(selectFieldWidth));
		this.get_oSelFieldBasis().set_ActiveValue("");

	}
	
	
	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownE2_BasicModuleContainer();
	}
	
	private class ownE2_BasicModuleContainer extends E2_BasicModuleContainer {}
	
	
	@Override
	public void fill_Grid4AnzeigeStatusMulti()
	{
		int[] iSpalten = {widthAnzeige,widthSelField+3,30};
		
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setWidth(new Extent(widthAnzeige+widthSelField+3+30));

		this.get_grid4Anzeige().set_Spalten(iSpalten);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		
		this.get_grid4Anzeige().add(new MyE2_Label(feldBenennung));
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setWidth(new Extent(widthAnzeige+widthSelField+3+30));

		int[] iSpalten = {widthAnzeige,widthSelField+3,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().add(new MyE2_Label(feldBenennung));
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
	}

}