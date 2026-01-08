/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE
 * @author manfred
 * @date 09.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 09.04.2019
 *
 */
public class RB_MODUL_LINK_Connector extends MODUL_LINK_Connector implements IF_RB_Component{

	private RB_KF           rb_Key = null;

	
	/**
	 * @author manfred
	 * @date 09.04.2019
	 *
	 * @throws myException
	 */
	public RB_MODUL_LINK_Connector() throws myException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author manfred
	 * @date 09.04.2019
	 *
	 * @param bHorizontal
	 * @param bShowTextInButton
	 * @param bShowLabelInConnector
	 * @throws myException
	 */
	public RB_MODUL_LINK_Connector(boolean bHorizontal, boolean bShowTextInButton, boolean bShowLabelInConnector)
			throws myException {
		super(bHorizontal, bShowTextInButton, bShowLabelInConnector);
		
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated)	throws myException {
		
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.rb_Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.rb_Key=key;
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return null;
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
		
	}
	
}
