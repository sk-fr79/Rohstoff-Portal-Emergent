/**
 * panter.gmbh.Echo2.RB.COMP.SearchDialog
 * @author manfred
 * @date 26.09.2017
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import java.util.GregorianCalendar;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Enum;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.bibDate;

/**
 * @author manfred
 * @date 26.09.2017
 *
 */
public class SearchDialog_Selector_Date extends E2_TF_4_Date
		implements IF_SearchDialog_SelectorEntryComponent {

	/**
	 * @author manfred
	 * @date 26.09.2017
	 *
	 * @param bErasor
	 * @throws myException
	 */
	public SearchDialog_Selector_Date(boolean bErasor) throws myException {
		super(bErasor);
		_init();
	}


	/**
	 * @author manfred
	 * @date 26.09.2017
	 *
	 * @param cText
	 * @param iwidthPixel
	 * @param bMiniIcon
	 * @param bErasor
	 * @param mode
	 * @param bSmallPopUp
	 */
	public SearchDialog_Selector_Date(String cText, int iwidthPixel, boolean bMiniIcon, boolean bErasor,
			E2_TF_4_Date_Enum mode, boolean bSmallPopUp) {
		super(cText, iwidthPixel, bMiniIcon, bErasor, mode, bSmallPopUp);
		_init();
	}


	/**
	 * @author manfred
	 * @date 26.09.2017
	 *
	 * @param cText
	 * @param iwidthPixel
	 * @param bMiniIcon
	 * @param bErasor
	 * @param mode
	 */
	public SearchDialog_Selector_Date(String cText, int iwidthPixel, boolean bMiniIcon, boolean bErasor,
			E2_TF_4_Date_Enum mode) {
		super(cText, iwidthPixel, bMiniIcon, bErasor, mode);
		_init();
	}


	/**
	 * @author manfred
	 * @date 26.09.2017
	 *
	 * @param cText
	 * @param iwidthPixel
	 * @param bMiniIcon
	 * @param bErasor
	 * @throws myException
	 */
	public SearchDialog_Selector_Date(String cText, int iwidthPixel, boolean bMiniIcon, boolean bErasor)
			throws myException {
		super(cText, iwidthPixel, bMiniIcon, bErasor);
		_init();
	}


	/**
	 * @author manfred
	 * @date 26.09.2017
	 *
	 * @param cText
	 * @param iwidthPixel
	 * @param bMiniIcon
	 * @throws myException
	 */
	public SearchDialog_Selector_Date(String cText, int iwidthPixel, boolean bMiniIcon) throws myException {
		super(cText, iwidthPixel, bMiniIcon);
		_init();
	}


	/**
	 * @author manfred
	 * @date 26.09.2017
	 *
	 * @param cText
	 * @param iwidthPixel
	 * @throws myException
	 */
	public SearchDialog_Selector_Date(String cText, int iwidthPixel) throws myException {
		super(cText, iwidthPixel);
		_init();
	}


	/**
	 * @author manfred
	 * @date 26.09.2017
	 *
	 * @throws myException
	 */
	public SearchDialog_Selector_Date() throws myException {
		super();
		_init();
	}

	/**
	 * wird von allen konstruktoren aufgerufen
	 * @author manfred
	 * @date 27.09.2017
	 *
	 */
	private void _init(){
		this.setSelectionMode(E2_TF_4_Date_Enum.SLIP_SELECTION_MODE);
	}
	
	
	/* value ist iso-Date "yyyy-mm-dd"
	 * (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) throws myException {
		if (!bibALL.isEmpty(value)){
			MyDate oDate = new MyDate(value, MyDate.DATE_FORMAT.YYYY_MM_DD_DASH);
			this.set_oLastDateKlicked(new myDateHelper(oDate.get_cDateStandardFormat()));
			this.get_oTextField().setText(this.get_oLastDateKlicked().get_cDateFormatForMask());
		} 
	}

	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#getValue()
	 */
	@Override
	public String getValue() throws myException {

		myDateHelper dt  = this.get_oLastDateKlicked();
		String dateValue = ""; 
		if (dt != null){
			dateValue = dt.get_cDateFormat_ISO_FORMAT(); 
		}
		return dateValue;
	}
	
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#addActionAgent(panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent)
	 */
	@Override
	public void addActionAgent(XX_ActionAgent oAgent) {
		if (oAgent != null){
			this.get_oButtonEraser().add_oActionAgent(oAgent);http://localhost:8080/rohstoff_app/servlet/panter/gmbh/basics4project/E2_Container?serviceId=Echo.StreamImage&imageuid=57c8a135_15ec3bea9cb_305
			this.get_vActionAgentsZusatz().add(oAgent);
		}
	}
	
	
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#removeActionAgent(panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent)
	 */
	@Override
	public void removeActionAgent(XX_ActionAgent oAgent)  {
		if (oAgent != null){
			this.get_oButtonEraser().remove_oActionAgent(oAgent);
			this.get_vActionAgentsZusatz().remove(oAgent);
		}
	}


	private SearchDialog_SelectorEntry _selector_entry = null;
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.IF_SearchDialog_SelectorEntryComponent#setSelectroEntry(panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_SelectorEntry)
	 */
	@Override
	public void setSelectroEntry(SearchDialog_SelectorEntry selectorEntry) {
		_selector_entry = selectorEntry;
	}
	



}
