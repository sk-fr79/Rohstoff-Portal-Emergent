/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 13.02.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 13.02.2019
 * Feld, das einen wert aus der datenbank trägt und zurückgibt, aber auf der oberfläche nur einen
 * Label darstellt
 *
 */
public abstract class RB_FieldForVisualizeValueGrid extends E2_Grid implements IF_RB_Component_Savable  {

	private String dbValue = null;
	
	public abstract void convertDbValueToVisibleLabel(String dbValFormated);
	
	private RB_KF Key = null;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();


	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.dbValue;
	}

	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.dbValue = valueFormated;
		this.convertDbValueToVisibleLabel(this.dbValue);
	}

	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		this.dbValue="";
		if (dataObject.get_RecORD()!=null) {
			this.dbValue = ((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME());
		}
		this.convertDbValueToVisibleLabel(this.dbValue);
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}





}
