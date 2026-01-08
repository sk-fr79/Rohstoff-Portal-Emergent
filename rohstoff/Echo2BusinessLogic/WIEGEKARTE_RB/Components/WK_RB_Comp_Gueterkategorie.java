/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 23.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import java.util.Vector;

import net.sf.jasperreports.engine.export.oasis.BorderStyle;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
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
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_RadioButtonAgent;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;


/**
 * Checbox-Auswahl für die Güterkategorien Schüttgut / Stückgut
 * @author manfred
 * @date 29.04.2020
 *
 */
public class WK_RB_Comp_Gueterkategorie extends E2_Grid  implements IF_RB_Component_Savable, IF_FieldContainerComponent, IF_HasChangeListeners<WK_RB_Comp_Gueterkategorie>{

	private RB_RadioButtonAgent _agentWatchdog 	= new RB_RadioButtonAgent(false);
	private RB_cb 				_cbSchuettgut		= (RB_cb) new RB_cb(new MyE2_String("Schüttgut"))._fsa(0)._aaa(new ActionSetCheckBox(),false);
	private RB_cb 				_cbStueckgut		= (RB_cb) new RB_cb(new MyE2_String("Stückgut"))._fsa(0)._aaa(new ActionSetCheckBox(),false);;

	private MyE2_Column			_colSchuettgut 		= new MyE2_Column();
	private MyE2_Column			_colStueckgut		= new MyE2_Column();
	
	private RB_KF _rb_Key ;
	
	private RB_TransportHashMap _tpHashMap = null;
	
	public WK_RB_Comp_Gueterkategorie(RB_TransportHashMap p_tpHashMap) {
		_tpHashMap = p_tpHashMap;
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

		_colSchuettgut.add(_cbSchuettgut, E2_INSETS.I_0_0_0_0);
		_colStueckgut.add(_cbStueckgut, E2_INSETS.I_0_0_0_0);
		
		this
		._setSize(150,150)
		._a(_colSchuettgut, new RB_gld()._ins(0,0,2,0))
		._a(_colStueckgut, new RB_gld()._ins(0,0,2,0))	;
		
		try {
			_agentWatchdog._addCb(_cbSchuettgut);
			_agentWatchdog._addCb(_cbStueckgut);
		} catch (Exception e) {
		}
		
		clearMaskComponents();
	}
	
	
	private void clearMaskComponents() {
		_agentWatchdog._setAllUnselected();
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
//				sValue = S.isEmpty(sValue) ? WK_RB_CONST.ENUM_Gueterkategorie.SCHUETTGUT.db_val() : sValue;
				this.rb_set_db_value_manual(sValue);
				this.set_bEnabled_For_Edit(false);
				
			}
		}
	}

	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		if (S.isFull(valueFormated) ) { 
			if (valueFormated.equalsIgnoreCase(WK_RB_CONST.ENUM_Gueterkategorie.SCHUETTGUT.db_val())) {
				_agentWatchdog.setCheckbox(_cbSchuettgut, true);
			} else {
				_agentWatchdog.setCheckbox(_cbStueckgut, true);
			}
		} else {
			_agentWatchdog._setAllUnselected();
		}
		// Farben setzen
		setCBColors();
	}

	
	
	private void setCBColors() {
		boolean bSchuettgut = _cbSchuettgut.isSelected();
		boolean bStueckgut = _cbStueckgut.isSelected();

		if (this.isEnabled()) {
			if (!bSchuettgut && !bStueckgut) {
//				_colSchuettgut.setBackground(Color.RED);
//				_colStueckgut.setBackground(Color.RED);
				_colSchuettgut.setBorder(new Border(new Extent(2), Color.RED, Border.STYLE_SOLID));
				_colStueckgut.setBorder(new Border(new Extent(2), Color.RED, Border.STYLE_SOLID));
			} else {
//				_colSchuettgut.setBackground(new E2_ColorBase());
//				_colStueckgut.setBackground(new E2_ColorBase());
				_colSchuettgut.setBorder(new Border(new Extent(2), new E2_ColorBase(), Border.STYLE_SOLID));
				_colStueckgut.setBorder(new Border(new Extent(2), new E2_ColorBase(), Border.STYLE_SOLID));

			} 
		}
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
		super.set_bEnabled_For_Edit(bEnabled);
		
		if (bEnabled) {
			_agentWatchdog.set_AllEnabled();
			setCBColors();
		} else {
//			_colSchuettgut.setBackground(new E2_ColorBase());
//			_colStueckgut.setBackground(new E2_ColorBase());
			_colSchuettgut.setBorder(new Border(new Extent(2), new E2_ColorBase(), Border.STYLE_SOLID));
			_colStueckgut.setBorder(new Border(new Extent(2), new E2_ColorBase(), Border.STYLE_SOLID));

			_agentWatchdog.set_AllDisabled();
		}
	}


	
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		
		String sRet = "";
		
		if (_cbSchuettgut.isSelected()) {
			sRet = WK_RB_CONST.ENUM_Gueterkategorie.SCHUETTGUT.db_val();
		} else if ( _cbStueckgut.isSelected()) {
			sRet = WK_RB_CONST.ENUM_Gueterkategorie.STUECKGUT.db_val();
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
		_agentWatchdog.set_AllDisabled();
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	private VEK<IF_ExecuterOnComponentV2<WK_RB_Comp_Gueterkategorie>>    changeListeners = new   VEK<>();
	
	@Override
	public WK_RB_Comp_Gueterkategorie _clearChangeListener() {
		changeListeners._clear();
		return this;
	}

	@Override
	public WK_RB_Comp_Gueterkategorie _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_Comp_Gueterkategorie> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}


	private class ActionSetCheckBox extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			_agentWatchdog.executeAgentCodePassiv(oExecInfo);
			setCBColors();
		}
	}
	
	
	

}
