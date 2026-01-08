package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_ListSelectorMultiDropDown_STD extends E2_ListSelectorMultiDropDown
{

	public E2_ListSelectorMultiDropDown_STD(MyE2_SelectField SelFieldBasis, String WhereStringSchablone) throws myException
	{
		super(SelFieldBasis, WhereStringSchablone);
	}

	
	
	public E2_ListSelectorMultiDropDown_STD(String cSQL_Query, String WhereStringSchablone) throws myException
	{
		super(cSQL_Query, WhereStringSchablone);
	}



	@Override
	public void fill_Grid4AnzeigeStatusMulti()
	{
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(2);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		oLab.get_ownLabel().setFont(new E2_FontBold(-2));
		this.get_grid4Anzeige().add(oLab,E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);
		oLab.get_ownLabel().setToolTipText(this.get_oButtonOpenMultiSelectPopup().getToolTipText());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(2);
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis(),E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);
	}

}
