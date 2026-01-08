package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

//2013-03-25: neuer multiselektor Adressen-Potential
public class FS_SelectorMulti_Branche extends E2_ListSelectorMultiDropDown
{
	public FS_SelectorMulti_Branche() throws myException
	{
		super(new FS__SelectFieldBranche(), 
				"(JT_ADRESSE.ID_ADRESSE in (select JT_FIRMENINFO.ID_ADRESSE from "+bibE2.cTO()+".JT_FIRMENINFO where ID_BRANCHE=#WERT#)"+
				" OR "+
				"JT_ADRESSE.ID_ADRESSE in (select ID_ADRESSE from "+bibE2.cTO()+".JT_ADRESSE_ZUSATZBRANCHE where ID_BRANCHE=#WERT#))");
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
		this.get_grid4Anzeige().add(new MyE2_Label("Branche:"));
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten = {80,102,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label("Branche:"));
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
	}
	
	
	//hier muss die methode ueberschrieben werden, um den schalter <keine branche> abzubilden
	@Override
	public String get_WhereBlock() throws myException
	{
		String cWhere = "";
		
		if (S.NN(this.get_oSelFieldBasis().get_ActualWert()).equals(FS_ListSelector.NO_BRANCHE))
		{
			cWhere = "JT_ADRESSE.ID_ADRESSE in (select JT_FIRMENINFO.ID_ADRESSE from "+bibE2.cTO()+".JT_FIRMENINFO where ID_BRANCHE IS NULL)";
		}
		else
		{
			cWhere = super.get_WhereBlock();
		}
		return cWhere;
	}

	
}