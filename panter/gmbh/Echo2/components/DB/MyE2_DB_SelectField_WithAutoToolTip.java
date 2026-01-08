package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_SelectField_WithAutoToolTip extends MyE2_DB_SelectField
{

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, dataToView oDataToView, Extent oExt) throws myException
	{
		super(osqlField, oDataToView, oExt);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, dataToView oDataToView) throws myException
	{
		super(osqlField, oDataToView);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, Extent oExt) throws myException
	{
		super(osqlField, oExt);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER, boolean btranslate, Extent oExt) throws myException
	{
		super(osqlField, cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, btranslate, oExt);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER, boolean btranslate) throws myException
	{
		super(osqlField, cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, btranslate);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, String[] aDefArray, boolean btranslate, Extent oExt) throws myException
	{
		super(osqlField, aDefArray, btranslate, oExt);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, String[] aDefArray, boolean btranslate) throws myException
	{
		super(osqlField, aDefArray, btranslate);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, String[][] aDefArray, boolean btranslate, Extent oExt) throws myException
	{
		super(osqlField, aDefArray, btranslate, oExt);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField, String[][] aDefArray, boolean btranslate) throws myException
	{
		super(osqlField, aDefArray, btranslate);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	public MyE2_DB_SelectField_WithAutoToolTip(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.add_oActionAgent(new ownActionSetLongTextToToolTip());
	}

	
	
	private class ownActionSetLongTextToToolTip extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_SelectField oSelect = (MyE2_SelectField)oExecInfo.get_MyActionEvent().getSource();
			
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


	public int set_ActiveValue(String cWert) throws myException
    {
		int i= super.set_ActiveValue(cWert);
		new ownActionSetLongTextToToolTip().executeAgentCode(new ExecINFO(new MyActionEvent(new ActionEvent(this, "")), true));
		return i;
    }

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.get_oDataToView() == null)
			throw new myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: SelectField not initialized !");
		
		MyE2_DB_SelectField_WithAutoToolTip oSelField = null;
		
		try
		{
			oSelField = new MyE2_DB_SelectField_WithAutoToolTip(this.EXT_DB().get_oSQLField(),this.get_oDataToView());
			oSelField.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oSelField));
		}
		catch (myException ex)
		{
			throw new  myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
		oSelField.set_EXT((MyE2EXT__Component)((MyE2IF__Component)this).EXT().get_Copy(oSelField));
		oSelField.setStyle(this.getStyle());
		oSelField.set_oDataToView(this.get_oDataToView());
		oSelField.set_odataToViewShadow(this.get_odataToViewShadow());

		oSelField.setFont(this.getFont());
		
		
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

		oSelField.setFocusTraversalParticipant(this.isFocusTraversalParticipant());
		
		oSelField.setWidth(this.getWidth());

		return oSelField;
	}

	
	
}
