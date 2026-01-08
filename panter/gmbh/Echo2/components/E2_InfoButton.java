package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class E2_InfoButton extends MyE2_Button
{
	private Vector<MyString>	vInfos = new Vector<MyString>();
	
	public E2_InfoButton(Vector<String> infos, boolean bTranslate)
	{
		super(E2_ResourceIcon.get_RI("info.png"),E2_ResourceIcon.get_RI("leer.png"));
		for (int i=0;i<infos.size();i++)
		{
			this.vInfos.add(new MyE2_String(infos.get(i),bTranslate));
		}
		this.add_oActionAgent(new ownInfoAction());
	}

	public E2_InfoButton(Vector<MyString> infos)
	{
		super(E2_ResourceIcon.get_RI("info.png"),E2_ResourceIcon.get_RI("leer.png"));
		this.vInfos.addAll(infos);
		this.add_oActionAgent(new ownInfoAction());
	}

	
	private class ownInfoAction extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_InfoButton oThis = E2_InfoButton.this;
			new ownBasicContainerToShowInfos(new MyE2_String("Info"),oThis.vInfos,new Extent(600),new Extent(700));
		}
	}

	
	private class ownBasicContainerToShowInfos extends E2_BasicContainerToShowInfos
	{

		public ownBasicContainerToShowInfos(MyE2_String ctitle,	Vector<MyString> infos, Extent owidth, Extent oheight)	throws myException
		{
			super(ctitle, infos, owidth, oheight);
		}
	}
	
	
}
