/**
 * panter.gmbh.Echo2.RB.HIGHLEVEL
 * @author martin
 * @date 03.04.2019
 * 
 */
package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditDisabledBackground;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_FieldContainerComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_TextFieldReadOnly;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 03.04.2019
 *
 */
public abstract class RB_HL_PruefstempelComponent extends E2_Grid implements IF_RB_Component_Savable, IF_FieldContainerComponent, IF_HasChangeListeners<RB_HL_PruefstempelComponent>{

	private RB_TextField  tfWithId = new RB_TextField();     //wird hidden mitgefuehrt
	private RB_cb         checkBox = new RB_cb()._aaa( new ActionOnChangeListeners())
												._aaa( 	new ActionSetCheckBox());
	private RB_TextFieldReadOnly  tfWithUser = (RB_TextFieldReadOnly) new RB_TextFieldReadOnly()._w(50)._fsa(-2);
	private RB_TextFieldReadOnly  tfWithTimestamp = (RB_TextFieldReadOnly) new RB_TextFieldReadOnly()._w(80)._fsa(-2);
	
	private Rec21         recWithPruefRecord = null;
	
	protected abstract Rec21  					createNewPruefRec21();
	protected abstract Rec21  					readPruefRec21(Long id);
	protected abstract MyE2_MessageVector  		fillMaskComponents(Rec21 r);
	protected abstract _TAB   					getTable();
	protected abstract Long   					getTableId();
	
	
	
	private RB_KF Key = null;

	

	private VEK<IF_ExecuterOnComponentV2<RB_HL_PruefstempelComponent>>    changeListeners = new   VEK<>();       
	
	public RB_HL_PruefstempelComponent _clearChangeListener() {
		this.changeListeners.clear();
		return this;
	}
	
	public RB_HL_PruefstempelComponent _addChangeListener(IF_ExecuterOnComponentV2<RB_HL_PruefstempelComponent> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}
	
	
	
	
	public RB_HL_PruefstempelComponent() {
		super();
		this._init();
	}
	
	
	
	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}


	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject instanceof RB_Dataobject_V21 || dataObject instanceof RB_Dataobject_V22) {
			Rec21 r = (Rec21)dataObject;
			if (r==null||r.is_newRecordSet()) {
				this.rb_set_db_value_manual("");
			} else {
				this.rb_set_db_value_manual(r.get_fs_dbVal(this.Key.get_data_field()));
				this.mark_Disabled();
			}
		} else {
			throw new myException("Error: Only Rec21-types are allowed !4031de9e-562a-11e9-8647-d663bd873d93");
		}
	}

	/**
	 * can be overwritten
	 * @author martin
	 * @date 03.04.2019
	 *
	 * @return
	 */
	protected RB_HL_PruefstempelComponent _init() {
		this	._setSize(20,50,80)
				._a(checkBox, new RB_gld()._ins(0, 0, 2, 0))
				._a(tfWithUser, new RB_gld()._ins(0, 0, 2, 0))
				._a(tfWithTimestamp, new RB_gld()._ins(0, 0, 2, 0))
				;
		
		return this;
	}
	
	
	@Override
	public void rb_set_db_value_manual(String idFormated) throws myException {
		MyLong l = new MyLong(idFormated);
		if (l.isOK()) {
			this.recWithPruefRecord = this.readPruefRec21(l.get_oLong());
			if (this.recWithPruefRecord == null) {
				clearMaskComponents();
				bibMSG.MV()._addWarn(S.ms("Fehler beim Lesen des Prüfstempels ! <fcd1f590-5628-11e9-8647-d663bd873d93>"));
			} else {
				MyE2_MessageVector mv = this.fillMaskComponents(this.recWithPruefRecord);
				if (mv.get_bHasAlarms()) {
					for (MyE2_Message m: mv) {
						bibMSG.MV()._addWarn(m.get_cMessage());
					}
				} else {
					this.tfWithTimestamp.setBackground(new E2_ColorEditDisabledBackground());
					this.tfWithUser.setBackground(new E2_ColorEditDisabledBackground());
				}
			}
		} else {
			clearMaskComponents();
		}
	}


	private void clearMaskComponents() {
		this.checkBox.setSelected(false);
		this.tfWithId.setText("");
		this.tfWithTimestamp.setText("");
		this.tfWithUser.setText("");
	}
	

	@Override
	public void mark_Neutral()  {
		try {
			this.checkBox.mark_Neutral();
		} catch (myException e) {
			e.printStackTrace();
		}
		this.tfWithTimestamp.mark_Neutral();
		this.tfWithUser.mark_Neutral();
	}
	
	public void mark_Disabled() {
		this.tfWithTimestamp.setBackground(new E2_ColorEditDisabledBackground());
		this.tfWithUser.setBackground(new E2_ColorEditDisabledBackground());
	}

	
	public void mark_FalseInput() throws myException {
		this.checkBox.mark_FalseInput();
		this.tfWithTimestamp.mark_FalseInput();
		this.tfWithUser.mark_FalseInput();
	}
	
	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.tfWithId.getText();
	}

	public RB_TextField getTfWithId() {
		return tfWithId;
	}

	public RB_cb getCheckBox() {
		return checkBox;
	}

	public RB_TextField getTfWithUser() {
		return tfWithUser;
	}

	public RB_TextField getTfWithTimestamp() {
		return tfWithTimestamp;
	}

	
	private class ActionOnChangeListeners extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			for (IF_ExecuterOnComponentV2<RB_HL_PruefstempelComponent> executer: changeListeners) {
				bibMSG.MV()._add(executer.execute(RB_HL_PruefstempelComponent.this));
			}
		}
		
	}
	
	
	
	private class ActionSetCheckBox extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (checkBox.isSelected()) {
				//dann ein neues record erzeugen
				recWithPruefRecord = createNewPruefRec21();
				if (recWithPruefRecord==null) {
					clearMaskComponents();
					bibMSG.MV()._addAlarm(S.ms("Fehler beim Setzen des Prüfstempels"));
				} else {
					fillMaskComponents(recWithPruefRecord);
					tfWithTimestamp.setBackground(new E2_ColorEditDisabledBackground());
					tfWithUser.setBackground(new E2_ColorEditDisabledBackground());
				}
				
			} else {
				//dann leermachen, der alte datensatz bleibt stehen
				clearMaskComponents();
				tfWithTimestamp.setBackground(new E2_ColorEditBackground());
				tfWithUser.setBackground(new E2_ColorEditBackground());
			}
		}
	}
	
	
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		this.checkBox.set_bEnabled_For_Edit(bEnabled);
		if (bEnabled) {
			if (this.checkBox.isSelected()) {
				this.tfWithTimestamp.setBackground(new E2_ColorEditDisabledBackground());
				this.tfWithUser.setBackground(new E2_ColorEditDisabledBackground());
			} else {
				this.tfWithTimestamp.setBackground(new E2_ColorEditBackground());
				this.tfWithUser.setBackground(new E2_ColorEditBackground());
			}
		} else {
			this.tfWithTimestamp.setBackground(new E2_ColorEditDisabledBackground());
			this.tfWithUser.setBackground(new E2_ColorEditDisabledBackground());
		}
	}

	
}
