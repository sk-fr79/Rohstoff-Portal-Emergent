/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 23.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_FieldContainerComponent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_RadioButtonAgent;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author manfred
 * @date 23.03.2020
 *
 */
public class WK_RB_WeWa extends E2_Grid  implements IF_RB_Component_Savable, IF_FieldContainerComponent, IF_HasChangeListeners<WK_RB_WeWa>{

	private RB_RadioButtonAgent _agentWatchdogWEWA 	= new RB_RadioButtonAgent(false);
	private RB_cb 				_cbWE 				= (RB_cb) new RB_cb(new MyE2_String("Wareneingang"))._fsa(3)._b()._aaaV(new VEK<XX_ActionAgent>()._a(new ActionSetCheckBox(),new ActionOnChangeListeners()) );
	private RB_cb 				_cbWA 				= (RB_cb) new RB_cb(new MyE2_String("Warenausgang"))._fsa(3)._b()._aaaV(new VEK<XX_ActionAgent>()._a(new ActionSetCheckBox(),new ActionOnChangeListeners()) );

	private MyE2_Column			_gWE 				= new MyE2_Column();
	private MyE2_Column			_gWA 				= new MyE2_Column();
	
	private RB_KF _rb_Key ;
	
	private MyE2EXT__Component EXT = new MyE2EXT__Component(this);
	
	public WK_RB_WeWa() {
		_init_gui();
	}
	
	
	
	protected MyE2_MessageVector fillMaskComponents(Rec22 r) throws myException {
		MyE2_MessageVector mv = bibMSG.getNewMV();
			
		return mv;
	};

	
	/** 
	 * aufbau des Grids für die Waegung
	 * @author manfred
	 * @throws myException 
	 * @date 19.03.2020
	 *
	 */
	private void _init_gui()  {

		_gWE.add(_cbWE, E2_INSETS.I_5_5_5_5);
		_gWA.add(_cbWA, E2_INSETS.I_5_5_5_5);
		
		this
		._setSize(150,150)
		._a(_gWE, new RB_gld()._ins(0,0,2,0))
		._a(_gWA, new RB_gld()._ins(20,0,2,0))	;
		
		try {
			_agentWatchdogWEWA._addCb(_cbWE);
			_agentWatchdogWEWA._addCb(_cbWA);
		} catch (Exception e) {
		}
		clearMaskComponents();
	}
	
	
	private void clearMaskComponents() {
		_agentWatchdogWEWA._setAllUnselected();
		setCBColors();
	}
	
	
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		
		if (dataObject.get_RecORD() != null) {
			Rec22 r = (Rec22)dataObject;
			if (r==null||r.is_newRecordSet()) {
				clearMaskComponents();
			} else {
				String sValue = ((RB_Dataobject_V22) dataObject).getUfs(this.rb_KF().get_data_field());
				sValue = S.isEmpty(sValue) ? "N" : sValue;
				this.rb_set_db_value_manual(sValue);
				this.set_bEnabled_For_Edit(false);
				
			}
		}
	}

	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		if (S.isFull(valueFormated) ) { 
			if (valueFormated.equalsIgnoreCase("Y")) {
				_agentWatchdogWEWA.setCheckbox(_cbWE, true);
			} else {
				_agentWatchdogWEWA.setCheckbox(_cbWA, true);
			}
		} else {
			_agentWatchdogWEWA._setAllUnselected();
		}
		// Farben setzen
		setCBColors();
	}

	private void setCBColors() {
		boolean bWE = _cbWE.isSelected();
		boolean bWA = _cbWA.isSelected();

		if (!bWE && !bWA) {
			_gWE.setBackground(Color.RED);
			_gWA.setBackground(Color.RED);
		} else if (bWE) {
			_gWE.setBackground(Color.GREEN);
			_gWA.setBackground(new E2_ColorBase());
		} else if (bWA) {
			_gWE.setBackground(new E2_ColorBase());
			_gWA.setBackground(Color.GREEN);
		}
		
	}
	

	/**
	 * Element ist gültig, wenn entweder WE oder WA gesetzt ist.
	 * @author manfred
	 * @date 29.05.2020
	 *
	 * @return
	 */
	public boolean isValid() {
		return _cbWE.isSelected() || _cbWA.isSelected();
	}
	
	/**
	 * true, wenn WE, false wenn WA 
	 * @author manfred
	 * @date 17.06.2020
	 *
	 * @return
	 */
	public boolean isLieferant() {
		return _cbWE.isSelected();
	}
	
	
	@Override
	public RB_KF rb_KF() throws myException {
		return _rb_Key ;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		_rb_Key=key;
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return new Vector<RB_Validator_Component>();
	}


	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		
		if (bEnabled) {
			_agentWatchdogWEWA.set_AllEnabled();
		} else {
			_agentWatchdogWEWA.set_AllDisabled();
		}


	}

	

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		String sRet = null;
		if (isValid()) {
			sRet = _cbWE.isSelected() ? "Y" : "N";
		} 
		return sRet;
	}


	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
		_agentWatchdogWEWA.set_AllDisabled();
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	private VEK<IF_ExecuterOnComponentV2<WK_RB_WeWa>>    changeListeners = new   VEK<>();
	
	@Override
	public WK_RB_WeWa _clearChangeListener() {
		changeListeners._clear();
		return this;
	}

	@Override
	public WK_RB_WeWa _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_WeWa> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}


	private class ActionSetCheckBox extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			_agentWatchdogWEWA.executeAgentCodePassiv(oExecInfo);
			setCBColors();
		}
	}
	
	/**
	 * Weitergabe der von aussen gesetzten Listeners 
	 * @author manfred
	 * @date 29.05.2020
	 *
	 */
	private class ActionOnChangeListeners extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			for (IF_ExecuterOnComponentV2<WK_RB_WeWa> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_WeWa.this));
			}
			bibMSG.MV()._add(mv);
		}
	}
	

}
