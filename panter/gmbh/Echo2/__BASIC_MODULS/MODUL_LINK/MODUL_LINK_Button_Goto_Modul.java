package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.exceptions.myException;

public class MODUL_LINK_Button_Goto_Modul extends MODUL_LINK_Button_Base {

//	private boolean m_is_showLabelInButton = true;
	
	
	/**
	 * Konstruktor für Link-Buttons mit genau einem Sprung-Ziel
	 * Beschreibung wird in Button geschrieben
	 * @param oRec
	 * @param ContainerToClose
	 * @throws myException
	 */
	public MODUL_LINK_Button_Goto_Modul(RECORD_MODUL_CONNECT oRec,Vector<E2_BasicModuleContainer> ContainerToClose)	throws myException {
		this(oRec,ContainerToClose,true,true);
	}

	
	/**
	 * Konstruktor für Link-Buttons mit genau einem Sprung-Ziel
	 * Beschreibung wird in Button geschrieben
	 * @param oRec
	 * @param ContainerToClose
	 * @throws myException
	 */
	public MODUL_LINK_Button_Goto_Modul(
			RECORD_MODUL_CONNECT oRec,
			Vector<E2_BasicModuleContainer> ContainerToClose,
			boolean bShowTextInButton,
			boolean bShowLabel)	throws myException {
		super(oRec,ContainerToClose);
		this.set_sIDTyp(MODUL_LINK_CONST.ps_modul_liste);
		
		set_showTextInButton(bShowTextInButton);
		set_DisplayLabel(bShowLabel);
		
		initButton();
	}
	
	
	public MODUL_LINK_Button_Goto_Modul(Vector<RECORD_MODUL_CONNECT> vRec,Vector<E2_BasicModuleContainer> ContainerToClose)	throws myException {
		this(vRec,ContainerToClose,false,false);
	}
	
	/**
	 * Konstruktor für Link-Buttons mit mehreren Ziel-IDs 
	 * Keine Beschreibung im Button
	 * 
	 * @param vRec
	 * @param ContainerToClose
	 * @throws myException
	 */
	public MODUL_LINK_Button_Goto_Modul(
			Vector<RECORD_MODUL_CONNECT> vRec,
			Vector<E2_BasicModuleContainer> ContainerToClose,
			boolean bShowTextInButton,
			boolean bShowLabel)	throws myException {
		
		super(vRec,ContainerToClose);
		this.set_sIDTyp(MODUL_LINK_CONST.ps_modul_liste);
		set_showTextInButton(bShowTextInButton);
		set_DisplayLabel(bShowLabel);
		
		initButton();
	}
	
	
	@Override
	protected XX_ActionAgent set_actionAgentButton() throws myException {
		XX_ActionAgent o_actionAgent = null;

		if (v_rec.size() > 0){
			String sModul = v_rec.get(0).get_ZIEL_cUF();
			String sBeschreibung = v_rec.get(0).get_BESCHREIBUNG_cUF();
			
			Vector<String> vID = new Vector<String>();
			for (RECORD_MODUL_CONNECT o : v_rec){
				vID.add(o.get_ID_ZIEL_cUF());
			}
			o_actionAgent = new actionGotoModul(sModul, sBeschreibung, vID, m_ContainerToClose);
		}
		return o_actionAgent;
	}
	
	
	/**
	 * Eventhandler zum aufrufen eines Moduls und der dazugehörigen ID
	 * @author manfred
	 *
	 */
	private class actionGotoModul extends XX_ActionAgentJumpToTargetList
	{

		private Vector<String> v_id = new Vector<String>();
		public actionGotoModul(String ModuleName,String LesbarerModulName, Vector<String> vID, Vector<E2_BasicModuleContainer> ContainerToClose) throws myException 
		{
			super(ModuleName, LesbarerModulName, ContainerToClose);
			v_id = vID;
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
		{
			return v_id;
		}
	}


	@Override
	public void set_bEnabled(boolean bEnable) throws myException {
		super.set_bEnabled(bEnable);
	}
	
	
}
