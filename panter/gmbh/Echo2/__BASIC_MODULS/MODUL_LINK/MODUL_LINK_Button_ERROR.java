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
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.FS_Component_MASK_DAUGHTER_ZUSATZINFOS;
import rohstoff.Echo2BusinessLogic._4_ALL.BT_OpenMaskByID;

public class MODUL_LINK_Button_ERROR extends MODUL_LINK_Button_Base {

	String m_Message = ""; 
	
	/**
	 * Konstruktor für Link-Buttons, die angezeigt werden, falls das Ziel nicht mehr existiert.
	 * Beschreibung wird in Button geschrieben
	 * @param oRec
	 * @param ContainerToClose
	 * @throws myException
	 */
//	public MODUL_LINK_Button_ERROR(RECORD_MODUL_CONNECT oRec,Vector<E2_BasicModuleContainer> ContainerToClose)	throws myException {
//		this(oRec,ContainerToClose,true,true);
//	}
	
	
	/**
	 * Konstruktor für Link-Buttons mit genau einem Sprung-Ziel
	 * 
	 * @param oRec
	 * @param ContainerToClose
	 * @throws myException
	 */
//	public MODUL_LINK_Button_ERROR(RECORD_MODUL_CONNECT oRec,Vector<E2_BasicModuleContainer> ContainerToClose,
//			boolean bShowTextInButton, boolean bShowLabel)	throws myException {
//		super(oRec,ContainerToClose);
//		this.set_sIDTyp(MODUL_LINK_CONST.ps_modul_maske);
//		
//		set_showTextInButton(bShowTextInButton);
//		set_DisplayLabel(bShowLabel);
//		
//		initButton();
//	}

	public MODUL_LINK_Button_ERROR(String message)	throws myException {
		super((RECORD_MODUL_CONNECT)null,null);
		m_Message = message;
		
		initButton();
	}
	
	/**
	 * Initialisiert den Button
	 * @throws myException
	 */
	protected void initButton() throws myException{
		
		m_button = new MyE2_Button(new MyString (m_Message));
		
		this.add(m_button, E2_INSETS.I_0_0_0_0);
			
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
