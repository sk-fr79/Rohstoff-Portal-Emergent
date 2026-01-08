package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST_LIST_Selector_MDD_Spedition extends E2_ListSelectorMultiDropDown_STD {

	private MyE2_CheckBox  cb_negieren = new MyE2_CheckBox(new MyE2_String("Negieren"));
	
	public TP_KST_LIST_Selector_MDD_Spedition() throws myException {
		super(new TP_KST_LIST_Selector_MDD_Spedition_DD(), "JT_VKOPF_TPA.ID_ADRESSE = #WERT#");
		this.fill_Grid4AnzeigeStatusSingle();
		this.cb_negieren.setToolTipText(new MyE2_String("Die Selektion liefert ALLE Speditionen AUSSER den gewählten").CTrans());
	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	
	private class ownBasicContainer extends E2_BasicModuleContainer {}

	
	@Override
	public void fill_Grid4AnzeigeStatusMulti()
	{
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(3);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		oLab.get_ownLabel().setFont(new E2_FontBold(-2));
		this.get_grid4Anzeige().add(oLab,E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);
		this.get_grid4Anzeige().add(this.cb_negieren,E2_INSETS.I_0_0_0_0);
		oLab.get_ownLabel().setToolTipText(this.get_oButtonOpenMultiSelectPopup().getToolTipText());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(3);
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis(),E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);
		if (this.cb_negieren!=null) {
			this.get_grid4Anzeige().add(this.cb_negieren,E2_INSETS.I(2,0,0,0));
		}
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent){
		super.add_ActionAgentToComponent(oAgent);
		this.cb_negieren.remove_AllActionAgents();
		this.cb_negieren.add_oActionAgent(oAgent);
	}
	
	
	@Override
	public String get_WhereBlock() throws myException {
		String c_where = super.get_WhereBlock();
		if (S.isFull(S.NN(c_where).trim())) {
			if (this.cb_negieren.isSelected()) {
				c_where = "( NOT ("+c_where+"))";
			}
		}
		return c_where;
	}
	

}
