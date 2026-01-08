package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.POPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE;

public class MODUL_LINK_Button_Goto_Mask_FUHRE extends MODUL_LINK_Button_Base {

	
	public MODUL_LINK_Button_Goto_Mask_FUHRE(
			RECORD_MODUL_CONNECT oRec,
			Vector<E2_BasicModuleContainer> ContainerToClose
			)	throws myException {
		this(oRec,ContainerToClose,true,true);
	}

	
	/**
	 * Konstruktor für Link-Buttons mit genau einem Sprung-Ziel
	 * Beschreibung wird in Button geschrieben
	 * @param oRec
	 * @param ContainerToClose
	 * @throws myException
	 */
	public MODUL_LINK_Button_Goto_Mask_FUHRE(
			RECORD_MODUL_CONNECT oRec,
			Vector<E2_BasicModuleContainer> ContainerToClose,
			boolean bShowTextInButton, 
			boolean bShowLabel)	throws myException {
		super(oRec,ContainerToClose);
		this.set_sIDTyp(MODUL_LINK_CONST.ps_modul_maske);
		
		set_showTextInButton(bShowTextInButton);
		set_DisplayLabel(bShowLabel);
		
		initButton();
	}
	
	
	
	/**
	 * Initialisiert den Button
	 * @throws myException
	 */
	protected void initButton() throws myException{
		
		String sBeschreibung = "";
		String sTab = "";
		String sIDZiel = "";
		if (v_rec.size() > 0){
			sBeschreibung = v_rec.get(0).get_BESCHREIBUNG_cUF();
			sTab 		  = v_rec.get(0).get_ZIEL_ZUSATZ_cUF();
			sIDZiel 	  = v_rec.get(0).get_ID_ZIEL_cUF();
			
		}
		
		// Sprungbutton auf die Adressmaske und den Speziellen Reiter
//		m_button = new POPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE(sIDZiel,E2_ResourceIcon.get_RI("kompass.png"),
//				new MyString("Springe zu Fuhre"),null,true,false);
		
		m_button = new ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE(
				sIDZiel, 
				E2_ResourceIcon.get_RI("kompass.png"), 
				new MyString("Springe zu Fuhre"), 
				(XX_ActionAgent)null,
				true,
				true
				);
		
		if (is_DisplayLabel()) {
			m_label_description = createLabel();
		}
	
		if (m_button != null || m_label_description != null) {

			Insets in = E2_INSETS.I_0_0_0_0;

			if (m_button != null) {
				// kein zusätzlicher ActionAgent im Button nötig  
				// m_button.add_oActionAgent(this.set_actionAgentButton());

				this.add(m_button, in);
				in = E2_INSETS.I_10_0_0_0;
				
				RECORD_MODUL_CONNECT m_rec = this.getLinkRecord();
				if (is_showTextInButton() && m_rec!=null && m_rec.get_BESCHREIBUNG_cUF()!=null)
				{
					m_button.setText( m_rec.get_BESCHREIBUNG_cUF());
					m_button.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
				}
				
			}
			if (m_label_description != null && !is_showTextInButton()) {
				this.add(m_label_description, in);
			}

		}
	}
	
	
	

	@Override
	public void set_bEnabled(boolean bEnable) throws myException {
		super.set_bEnabled(bEnable);
	}
		


	@Override
	protected XX_ActionAgent set_actionAgentButton() throws myException {
		return null;
	}
	
	
	private class ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE extends POPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE
	{

		/**
		 * @param cid_vpos_tpa_fuhre_unformated
		 * @param iconAmStart
		 * @param beschriftung
		 * @param actionAgentAfterSaveMask
		 * @param ShowInViewModeWhenEditForbidden
		 * @param autoRefreshContainer
		 * @throws myException
		 */
		public ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE(
				String cid_vpos_tpa_fuhre_unformated,
				E2_ResourceIcon iconAmStart, MyString beschriftung,
				XX_ActionAgent actionAgentAfterSaveMask,
				boolean ShowInViewModeWhenEditForbidden,
				boolean autoRefreshContainer) throws myException
		{
			super(cid_vpos_tpa_fuhre_unformated, iconAmStart, beschriftung,
					actionAgentAfterSaveMask, ShowInViewModeWhenEditForbidden,
					autoRefreshContainer);
			this.setToolTipText("Springe zur Fuhre");
		}

		
		public void set_bEnabled_For_Edit(boolean enabled) throws myException
		{
		}
	}

	
}
