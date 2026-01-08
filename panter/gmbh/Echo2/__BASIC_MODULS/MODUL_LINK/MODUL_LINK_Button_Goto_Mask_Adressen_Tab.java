package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.Echo2BusinessLogic._4_ALL.BT_OpenMaskByID;

public class MODUL_LINK_Button_Goto_Mask_Adressen_Tab extends MODUL_LINK_Button_Base {

//	private boolean m_is_showLabelInButton = true;
	
	
	/**
	 * Konstruktor für Link-Buttons mit genau einem Sprung-Ziel
	 * Beschreibung wird in Button geschrieben
	 * @param oRec
	 * @param ContainerToClose
	 * @throws myException
	 */
	public MODUL_LINK_Button_Goto_Mask_Adressen_Tab(
			RECORD_MODUL_CONNECT oRec,
			Vector<E2_BasicModuleContainer> ContainerToClose)	throws myException {
		this(oRec,ContainerToClose,true,true);
	}
	
	public MODUL_LINK_Button_Goto_Mask_Adressen_Tab(
			RECORD_MODUL_CONNECT oRec,
			Vector<E2_BasicModuleContainer> ContainerToClose	, 
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
		
		String sTab = "";
		String sIDAdresse = "";
		String sBeschreibung = "";
		if (v_rec.size() > 0){
			sTab 		  = v_rec.get(0).get_ZIEL_ZUSATZ_cUF();
			sIDAdresse 	  = v_rec.get(0).get_ID_ZIEL_cUF();
			sBeschreibung = v_rec.get(0).get_BESCHREIBUNG_cUF();
		}
		
		// Sprungbutton auf die Adressmaske und den Speziellen Reiter
		m_button = new cButton_OpenMask_And_Select_Tab(sIDAdresse,sTab);
		
		if (is_DisplayLabel()) {
			m_label_description = createLabel();
		}
	
		if (m_button != null || m_label_description != null) {

			Insets in = E2_INSETS.I_0_0_0_0;

			if (m_button != null) {
				// kein zusätzlicher ActionAgent im Button nötig  
				// m_button.add_oActionAgent(this.set_actionAgentButton());

				// tooltip setzen
				if(!bibALL.isEmpty(sBeschreibung)){
					m_button.setToolTipText(sBeschreibung);
				}
				
				
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
	
	
	
	private class cButton_OpenMask_And_Select_Tab extends BT_OpenMaskByID {
		
		String m_TabName 		= "";
		
		
		public cButton_OpenMask_And_Select_Tab(String sIDAdresse,String sTabName) throws myException {
			super(E2_ResourceIcon.get_RI("kompass.png"),E2_ResourceIcon.get_RI("leer.png"));
			m_TabName = sTabName;
			this.set_cID_BASICTABLE_UF(sIDAdresse);
			this.set_cSTATUS_MASKE(E2_ComponentMAP.STATUS_EDIT);
		}

		
		@Override
		public MyE2_MessageVector do_AfterCreatedAndFilledMaskObject(E2_BasicModuleContainer_MASK oMaskContainer, String cID_MainTable_UF, String cEDITSTATUS) throws myException {
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			return oMV;
		}

		@Override
		public E2_BasicModuleContainer_MASK get_BasicModuleContainer_MASK() throws myException {
			// Hier wird die Maske definiert, die aufgerufen werden soll.
			// Adressmaske öffnen
			return new FS_ModulContainer_MASK();
		}

		@Override
		public MyE2_MessageVector do_AfterPopupMask(E2_BasicModuleContainer_MASK oMaskContainer, String cID_MainTable_UF, String cEDITSTATUS) throws myException {
			MyE2_MessageVector oMV = new MyE2_MessageVector();

			// Tab öffnen, falls angegeben
			if (!bibALL.isEmpty(m_TabName)){
				((FS_ModulContainer_MASK)oMaskContainer).set_TabAsActive( m_TabName );
			}
			
			return oMV;
		}

		
		@Override
		public E2_ButtonAUTHValidator getValdiatorEdit() {
			return new E2_ButtonAUTHValidator(E2_MODULNAMES.MODUL_KENNER_PROGRAMM_WIDE,"BEARBEITE_ADRESSE_AUS_LISTE");
		}

		@Override
		public E2_ButtonAUTHValidator getValdiatorView() {
			return new E2_ButtonAUTHValidator(E2_MODULNAMES.MODUL_KENNER_PROGRAMM_WIDE,"ANZEIGE_ADRESSE_AUS_LISTE");
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
	
	
}
