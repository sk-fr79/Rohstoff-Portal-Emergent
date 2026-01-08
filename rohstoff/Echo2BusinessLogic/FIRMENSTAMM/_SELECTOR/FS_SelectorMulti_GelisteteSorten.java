package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.exceptions.myException;

//2012-10-17: neuer multiselektor fuer gelistete sorten (EK- und VK)
public class FS_SelectorMulti_GelisteteSorten extends E2_ListSelectorMultiDropDown
{
	private boolean bEK_VK = true;
	
	public FS_SelectorMulti_GelisteteSorten(boolean bEK) throws myException
	{
		super("SELECT DISTINCT A.ANR1||' '||A.ARTBEZ1 , A.ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF ABL INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB ON (ABL.ID_ARTIKEL_BEZ=AB.ID_ARTIKEL_BEZ) "+ 
			 " INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL A ON (AB.ID_ARTIKEL=A.ID_ARTIKEL) "+
			 " WHERE ABL.ARTBEZ_TYP=" + (bEK?"'EK'":"'VK'")+
			 " ORDER BY 1" , 
			 " JT_ADRESSE.ID_ADRESSE IN ("+
			 " SELECT AD.ID_ADRESSE "+ 
			 " FROM  "+bibE2.cTO()+".JT_ADRESSE AD "+ 
			 " INNER JOIN  "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF ABL ON (AD.ID_ADRESSE=ABL.ID_ADRESSE) "+
			 " INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB ON (ABL.ID_ARTIKEL_BEZ=AB.ID_ARTIKEL_BEZ) "+
			 " INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL A ON (AB.ID_ARTIKEL=A.ID_ARTIKEL) "+
			 " WHERE ABL.ARTBEZ_TYP=" + (bEK?"'EK'":"'VK'")+
			 " AND A.ID_ARTIKEL=#WERT#)");
		
		this.bEK_VK = bEK;
		
		this.fill_Grid4AnzeigeStatusSingle();                 //damit der parameter this.bEK_VK korrekt gesetzt ist
		
		this.get_oSelFieldBasis().setWidth(new Extent(80));
		
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
		this.get_grid4Anzeige().add(new MyE2_Label(this.bEK_VK?"EK-Sort.:":"VK-Sort.:"));
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten = {80,80,30};
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().add(new MyE2_Label(this.bEK_VK?"EK-Sort:":"VK-Sort.:"));
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}
	
}