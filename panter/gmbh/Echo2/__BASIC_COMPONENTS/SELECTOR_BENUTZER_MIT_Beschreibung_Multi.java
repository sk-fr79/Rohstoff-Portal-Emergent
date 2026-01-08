package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.exceptions.myException;



public class SELECTOR_BENUTZER_MIT_Beschreibung_Multi extends E2_ListSelectorMultiDropDown
{
	private MyE2_String cBeschriftung = null;
	private int 		iTextWidth = 100;
	private int 		iDropdownWidth = 100;
	
	public SELECTOR_BENUTZER_MIT_Beschreibung_Multi(int 		TextWidth, 
													int 		DropdownWidth, 
													MyE2_String Beschriftung,
													String      WhereStringSchablone ) throws myException
	{
		super(new Component_USER_DROPDOWN_NEW(false, DropdownWidth), WhereStringSchablone);
		this.cBeschriftung = 	Beschriftung;
		this.iTextWidth = 		TextWidth;
		this.iDropdownWidth = 	DropdownWidth;
		
		this.get_grid4Anzeige().setColumnWidth(0, new Extent(this.iTextWidth));
		this.get_grid4Anzeige().setColumnWidth(1, new Extent(this.iDropdownWidth));
		
		this.fill_Grid4AnzeigeStatusSingle();
	}

	
	public SELECTOR_BENUTZER_MIT_Beschreibung_Multi(	int 		TextWidth, 
														int 		DropdownWidth, 
														MyE2_String Beschriftung,
														String      WhereStringSchablone, 
														String[] 	arrTextValuePair_4_SelectWhereUsersNotSet ) throws myException
	{
		super(new Component_USER_DROPDOWN_NEW(false, DropdownWidth,arrTextValuePair_4_SelectWhereUsersNotSet), WhereStringSchablone);
		this.cBeschriftung = 	Beschriftung;
		this.iTextWidth = 		TextWidth;
		this.iDropdownWidth = 	DropdownWidth;
		
		this.get_grid4Anzeige().setColumnWidth(0, new Extent(this.iTextWidth));
		this.get_grid4Anzeige().setColumnWidth(1, new Extent(this.iDropdownWidth));
		
		this.fill_Grid4AnzeigeStatusSingle();
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
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(3);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		oLab.get_ownLabel().setFont(new E2_FontBold(-2));
		this.get_grid4Anzeige().add(new MyE2_Label(this.cBeschriftung),E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(oLab,E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);
		oLab.get_ownLabel().setToolTipText(this.get_oButtonOpenMultiSelectPopup().getToolTipText());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(3);
		this.get_grid4Anzeige().add(new MyE2_Label(this.cBeschriftung),E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis(),E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);

	}

}
