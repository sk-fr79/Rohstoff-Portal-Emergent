package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

//2013-03-25: neuer multiselektor Adressen-Potential
public class FS_SelectorMulti_Potential extends E2_ListSelectorMultiDropDown
{
	public FS_SelectorMulti_Potential() throws myException
	{
		super(new MyE2_SelectField(
				"SELECT "+_DB.ADRESSE_POTENTIAL$KURZBESCHREIBUNG+","+_DB.ADRESSE_POTENTIAL$ID_ADRESSE_POTENTIAL+" FROM "+bibE2.cTO()+"."+
				_DB.ADRESSE_POTENTIAL+" ORDER BY "+_DB.ADRESSE_POTENTIAL$KURZBESCHREIBUNG,
				false,true,false,false),
				" JT_ADRESSE.ID_ADRESSE_POTENTIAL=#WERT#");
		this.fill_Grid4AnzeigeStatusSingle();
		this.get_oSelFieldBasis().setWidth(new Extent(80));
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
		int[] iSpalten = {80,80,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Potential"));
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten = {80,80,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Potential:"));
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
	}
	
}