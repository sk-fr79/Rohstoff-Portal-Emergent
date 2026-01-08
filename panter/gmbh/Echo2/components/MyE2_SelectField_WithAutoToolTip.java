package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_SelectField_WithAutoToolTip extends MyE2_SelectField 
{

	public MyE2_SelectField_WithAutoToolTip() 
	{
		super();
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	
	public MyE2_SelectField_WithAutoToolTip(dataToView oDataToView) 
	{
		super();
		this.set_oDataToView(oDataToView);
		
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	
	
	
	
	public MyE2_SelectField_WithAutoToolTip(String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER, boolean bEmtyValueInFront,boolean bValuesFormated, boolean btranslate, Extent Width)	throws myException 
	{
		super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront,bValuesFormated, btranslate, Width);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_SelectField_WithAutoToolTip(String cSQL_Query_For_LIST,	boolean bThirdColumnIS_STANDARD_MARKER, boolean bEmtyValueInFront,boolean bValuesFormated, boolean btranslate) throws myException 
	{
		super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront,bValuesFormated, btranslate);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_SelectField_WithAutoToolTip(String[] aDefArray,String cdefaultValue, boolean btranslate) throws myException 
	{
		super(aDefArray, cdefaultValue, btranslate);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_SelectField_WithAutoToolTip(String[][] aDefArray,String cdefaultValue, boolean btranslate) throws myException 
	{
		super(aDefArray, cdefaultValue, btranslate);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}
	
	
	private class ownActionSetLongTextToToolTip extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_SelectField_WithAutoToolTip oSelect = (MyE2_SelectField_WithAutoToolTip)oExecInfo.get_MyActionEvent().getSource();
			
			if (oSelect.get_oDataToView()!=null)
			{
				oSelect.setToolTipText(S.NN(oSelect.get_ActualView()));
			}
		}
		
	}

	
	//der agent zum refresh der tooltips ist immer da
	public void remove_AllActionAgents() 
	{
		this.get_vActionAgents().removeAllElements();
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.get_oDataToView() == null)
			throw new myExceptionCopy("MyE2_SelectField:get_Copy: Error: SelectField not initialized !");
		
		
		MyE2_SelectField_WithAutoToolTip oSelField = new MyE2_SelectField_WithAutoToolTip();
		
		oSelField.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oSelField));
		oSelField.setStyle(this.getStyle());
		oSelField.set_oDataToView(this.get_oDataToView());
		oSelField.set_odataToViewShadow(this.get_odataToViewShadow());
		
		
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oSelField.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oSelField.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oSelField.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oSelField.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));

		
		return oSelField;
	}

	
	
}
