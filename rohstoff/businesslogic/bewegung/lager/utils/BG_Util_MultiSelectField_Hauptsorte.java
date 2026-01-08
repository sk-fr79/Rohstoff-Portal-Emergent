package rohstoff.businesslogic.bewegung.lager.utils;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

//2013-03-25: neuer multiselektor Adressen-Potential
public class BG_Util_MultiSelectField_Hauptsorte extends E2_ListSelectorMultiDropDown
{
	
	int 			m_iWidthDropDown 	= 100;
	String 			m_sLabelText 		= null;
	
	
	public BG_Util_MultiSelectField_Hauptsorte( String sLabelText, int WidthDropdown, String sWhereClause ) throws myException
	{
		super(new BG_Util_SelectField_Hauptsorte(WidthDropdown) ,sWhereClause); 
		m_iWidthDropDown = WidthDropdown;
		this.fill_Grid4AnzeigeStatusSingle();
		this.get_oSelFieldBasis().setWidth(new Extent(m_iWidthDropDown));
		this.get_oSelFieldBasis().set_ActiveValue("");
		m_sLabelText = sLabelText;
		
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
		int[] iSpalten;
		if (bibALL.isEmpty(m_sLabelText) ){
			iSpalten = new int[2];
			iSpalten[0] = m_iWidthDropDown + 2;
			iSpalten[1] = 30;
		} else {
			iSpalten = new int[3];
			iSpalten[0] = 80;
			iSpalten[1] = m_iWidthDropDown + 2;
			iSpalten[2] = 30;
		}
		
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		
		String[] sDD = {"<Mehrfach>"};
		MyE2_SelectField oLab = null;
		try {
			oLab = new  MyE2_SelectField(sDD,"",false);
			oLab.setStyle( this.get_oSelFieldBasis().getStyle()) ;
			oLab.set_bEnabled_For_Edit(false);
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		this.get_grid4Anzeige().removeAll();
		if (!bibALL.isEmpty(m_sLabelText) ){
			this.get_grid4Anzeige().add(new MyE2_Label(m_sLabelText));
		}
		this.get_grid4Anzeige().add(oLab);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		
	}

	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten;
		if (bibALL.isEmpty(m_sLabelText) ){
			iSpalten = new int[2];
			iSpalten[0] = m_iWidthDropDown + 2;
			iSpalten[1] = 30;
		} else {
			iSpalten = new int[3];
			iSpalten[0] = 80;
			iSpalten[1] = m_iWidthDropDown + 2;
			iSpalten[2] = 30;
		}
		
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		if (!bibALL.isEmpty(m_sLabelText) ){
			this.get_grid4Anzeige().add(new MyE2_Label(m_sLabelText));
		}
		
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());

	}
	
	
	
}