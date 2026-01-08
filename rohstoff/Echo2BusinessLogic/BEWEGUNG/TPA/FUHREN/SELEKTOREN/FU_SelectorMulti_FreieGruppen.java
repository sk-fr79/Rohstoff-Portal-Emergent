package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.GROUP_COLLECTOR.SELECTOR_GROUPS_MultiSelection;

//2013-03-25: umstellung der selektion nach gruppen auf multiselektion
public class FU_SelectorMulti_FreieGruppen extends SELECTOR_GROUPS_MultiSelection
{
	public FU_SelectorMulti_FreieGruppen(String NAME_OF_TABLE ,String PRIMARY_KEY_OF_TABLE, String MODULE_KENNER) throws myException
	{
		super(NAME_OF_TABLE ,PRIMARY_KEY_OF_TABLE, MODULE_KENNER);
		this.fill_Grid4AnzeigeStatusSingle();
		this.get_oSelFieldBasis().setWidth(new Extent(100));
		this.get_oSelFieldBasis().set_ActiveValue("");
	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException
	{
		return new ownPopupContainer();
	}
	
	private class ownPopupContainer extends E2_BasicModuleContainer
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
		this.get_grid4Anzeige().add(new MyE2_Label("Freie Grp."));
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten = {80,102,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Freie Grp.:"));
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
	}
	
}