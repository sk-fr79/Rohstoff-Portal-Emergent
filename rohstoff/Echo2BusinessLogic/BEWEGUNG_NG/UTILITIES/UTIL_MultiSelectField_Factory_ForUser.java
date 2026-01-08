package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES;

import bsh.util.Util;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * @author manfred
 * @date 25.01.2019
 *
 */
public class UTIL_MultiSelectField_Factory_ForUser extends E2_ListSelectorMultiDropDown
{
	
	int 			m_iWidthDropDown = 100;
	String 			m_sLabelText = null;
	
//	boolean 								m_bShowInactiveUsers = false;
//	UTIL_DBSelectField_Factory_ForUser 	m_selectfieldFactory = new UTIL_DBSelectField_Factory_ForUser();
	
	
	public UTIL_MultiSelectField_Factory_ForUser(String sLabelText, int WidthDropdown, String sWhereClause, boolean bShowInactiveUsers) throws myException
	{
		super(new UTIL_DBSelectField_Factory_ForUser().refreshSelectfield(bShowInactiveUsers).getSelectField(),sWhereClause );
		
		
		m_sLabelText = sLabelText;
		m_iWidthDropDown = WidthDropdown;

		this.fill_Grid4AnzeigeStatusSingle();
		get_oSelFieldBasis().setWidth(new Extent(WidthDropdown));
		get_oSelFieldBasis().set_ActiveValue("");
	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException
	{
		return new ownPopupContainer();
	}
	
	private class ownPopupContainer extends E2_BasicModuleContainer
	{
	}


	
//	public void refresh(boolean bShowInactiveUsers) throws myException{
//		m_selectfieldFactory.refreshSelectfield(bShowInactiveUsers);
//	}
	
	
	@Override
	public void fill_Grid4AnzeigeStatusMulti()
	{
		int[] iSpalten;
		if (bibALL.isEmpty(m_sLabelText) ){
			iSpalten = new int[3];
			iSpalten[0] = m_iWidthDropDown + 2;
			iSpalten[1] = 30;
			iSpalten[2] = 30;
			
		} else {
			iSpalten = new int[4];
			iSpalten[0] = 80;
			iSpalten[1] = m_iWidthDropDown + 2;
			iSpalten[2] = 30;
			iSpalten[3] = 30;
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
		this.get_grid4Anzeige().add(new cButtonForSelfSelection());
	}

	
	@Override
	public void fill_Grid4AnzeigeStatusSingle()
	{
		int[] iSpalten;
		if (bibALL.isEmpty(m_sLabelText) ){
			iSpalten = new int[3];
			iSpalten[0] = m_iWidthDropDown + 2;
			iSpalten[1] = 30;
			iSpalten[2] = 30;
		} else {
			iSpalten = new int[4];
			iSpalten[0] = 80;
			iSpalten[1] = m_iWidthDropDown + 2;
			iSpalten[2] = 30;
			iSpalten[3] = 30;
		}
		
		this.get_grid4Anzeige().set_Spalten(iSpalten);
		this.get_grid4Anzeige().removeAll();
		if (!bibALL.isEmpty(m_sLabelText) ){
			this.get_grid4Anzeige().add(new MyE2_Label(m_sLabelText));
		}
		
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis());
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup());
		this.get_grid4Anzeige().add(new cButtonForSelfSelection());

	}
	
	
	private class cButtonForSelfSelection extends E2_Button{
		/**
		 * @author manfred
		 * @date 31.01.2019
		 *
		 */
		public cButtonForSelfSelection() {
			this._image(E2_ResourceIcon.get_RI("person.png"), true);
			this._ttt(S.ms("Selektiert den aktuellen Anwender"));
			this.add_oActionAgent(new _actionAgentSelfSelection());
		}
		
		private class _actionAgentSelfSelection extends XX_ActionAgent{

			/* (non-Javadoc)
			 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
			 */
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				UTIL_MultiSelectField_Factory_ForUser oThis = UTIL_MultiSelectField_Factory_ForUser.this;
				if (oThis.get_SelectedValues().size() > 1){
					oThis.LEER_MACHEN();
					get_oSelFieldBasis().set_ActiveValue(bibALL.get_ID_USER());
				} else if (oThis.get_SelectedValues().size() > 0) {
					oThis.LEER_MACHEN();
				} else {
					get_oSelFieldBasis().set_ActiveValue(bibALL.get_ID_USER());
				}
				get_oSelFieldBasis().doActionPassivManual();

			}
			
			
		}
		
		
	}
	
	
}