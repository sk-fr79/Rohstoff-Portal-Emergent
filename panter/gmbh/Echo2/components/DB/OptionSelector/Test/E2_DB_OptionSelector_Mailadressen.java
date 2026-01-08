package panter.gmbh.Echo2.components.DB.OptionSelector.Test;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.DB.OptionSelector.E2_DB_OptionSelector;
import panter.gmbh.Echo2.components.DB.OptionSelector.E2_DB_OptionSelectorPopupDetail_SingleColumnCheckbox;
import panter.gmbh.Echo2.components.DB.OptionSelector.IF_OptionSelector_PopupDetail;
import panter.gmbh.Echo2.components.DB.OptionSelector.OptionSelector_PopupContainer;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class E2_DB_OptionSelector_Mailadressen extends E2_DB_OptionSelector {

	/**
	 * MUSS ANGEPASST WERDEN
	 * @param osqlField
	 * @throws myException
	 */
	public E2_DB_OptionSelector_Mailadressen(SQLField osqlField) throws myException {
		super(osqlField);
		
		this.setTokenSeparator(";");
		this.setDisplayLength(20);
		this.set_cTextWhenEmpty(new MyString("Mailempfänger"));
		
//		this.setTokenArray(myCONST.TOKEN_EMAIL_RECEIVER,"#");
	}

	/**
	 * MUSS ANGEPASST WERDEN
	 */
	@Override
	public OptionSelector_PopupContainer setOptionSelector_PopupContainer() {
		return new OptionsSelector_PopupContainer_Mailadressen("Auswahldialog Mailadressen");
	}

	/**
	 * MUSS ANGEPASST WERDEN
	 */
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		E2_DB_OptionSelector_Mailadressen oSelMail;
		try {
			oSelMail = new E2_DB_OptionSelector_Mailadressen(super.EXT_DB().get_oSQLField());
		} catch (myException e) {
			throw new myExceptionCopy("Fehler beim erzeugen der copy");
		}
		
		oSelMail = (E2_DB_OptionSelector_Mailadressen) super.get_Copy(oSelMail);
		return oSelMail;
	}

	
	
	
	

	@Override
	public Vector<String> getSelectedValuesFromPopup() {
		return this.get_Popup().getDetailBlock().getSelectedValues();
	}


	@Override
	public IF_OptionSelector_PopupDetail setPopupDetailPanel() {
		return new E2_DB_OptionSelectorPopupDetail_SingleColumnCheckbox();
	}


	@Override
	public Vector<String> setSelectedValuesInPopup() {
		return this.getSelectedTokenList();
	}


	@Override
	public Vector<String[]> setValueListForPopup() {
		return this.getTokenArray();
	}

	
	@Override
	public XX_ActionAgent setActionAgentCancel_ForPopup() {
		return null;
	}
	
	
	@Override
	public XX_ActionAgent setActionAgentOK_ForPopup() {
		return new cActionAgentOK(this);
	}


	
	/**
	 * Der Action Agent, der auf den Click des Selektors reagiert.
	 * @author manfred
	 *
	 */
	private class cActionAgentOK extends XX_ActionAgent{
		
		private E2_DB_OptionSelector m_oThis = null;
		
		
		public cActionAgentOK(E2_DB_OptionSelector oThis) {
			super();
			m_oThis = oThis;
		}

		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			// die Werte im Button setzen (wird dann dem DB-Feld zugewiesen)
			m_oThis.set_cActualMaskValue(m_oThis.getSelectedValuesFromPopup());
			
			m_oThis.get_Popup().ClosePopup(true);
		}


		
	}




	
}
