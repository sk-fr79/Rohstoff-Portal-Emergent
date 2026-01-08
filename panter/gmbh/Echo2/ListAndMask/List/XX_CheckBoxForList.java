package panter.gmbh.Echo2.ListAndMask.List;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public abstract class XX_CheckBoxForList extends MyE2_CheckBox implements MyE2IF__Component, E2_IF_Copy
{
	
	public abstract MyE2IF__Component  get_TitleComponent4Checkbox(E2_NavigationList oNaviList) throws myException; 
	public abstract Object get_Copy(Object objHelp) throws myExceptionCopy;
	
	
	public XX_CheckBoxForList()
	{
		super();
		this.EXT().set_oColExtent(new Extent(18));    // bekommt immer den gleichen extent
		
		//2012-08-30: neuer actionagent fuer die meldung: wieviele ausgewaehlt sind
		this.add_oActionAgent(new ownActionAgent());
		
	}

	
	/*
	 * diese funktion wird hier auser gefecht gesetzt 
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
	}

	
//	public Object get_Copy(Object objHelp) throws myExceptionCopy
//	{
//		XX_CheckBoxForList oCheckBoxForList = new XX_CheckBoxForList();
//		
//		oCheckBoxForList.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCheckBoxForList));
//		oCheckBoxForList.__setText(this.get_oText());
//		try
//		{
//			oCheckBoxForList.set_bEnabled_For_Edit(this.isEnabled());
//		}
//		catch (myException ex)
//		{
//			throw new myExceptionCopy(ex.getOriginalMessage());
//		}
//
//		oCheckBoxForList.setStyle(this.getStyle());
//		oCheckBoxForList.setFocusTraversalParticipant(this.isFocusTraversalParticipant());
//		
//		
//		return oCheckBoxForList;
//	}

	
	//2012-08-30: neuer actionagent fuer die meldung: wieviele ausgewaehlt sind
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_NavigationList  oNaviList = XX_CheckBoxForList.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
			
			if (oNaviList!=null)
			{
				oNaviList.ShowMessageWithInfoAboutSelectedLinesAndMarkSelectedLines();
			}
		}
	}
	
	
}
