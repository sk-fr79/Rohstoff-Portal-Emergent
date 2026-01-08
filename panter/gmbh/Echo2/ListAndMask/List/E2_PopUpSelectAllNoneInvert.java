package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.MyString;


/**
 * komponente fuer das umschalten der checkboxen innerhalb einer liste
 */
public class E2_PopUpSelectAllNoneInvert extends MyE2_PopUpMenue
{
	
	/*
	 * vector mit allen E2_ComponentMAPs innerhalb einer liste
	 */
	private Vector<E2_ComponentMAP> vE2_ComponentMAPS = null;
	
	public E2_PopUpSelectAllNoneInvert(Vector<E2_ComponentMAP> componentMAPS)
	{
		super(null, null, false);
		vE2_ComponentMAPS = componentMAPS;
		
		this.addTextButton(new MyString("Alle"),"ALLE");
		this.addTextButton(new MyString("Keine"),"KEINE");
		this.addTextButton(new MyString("Invertieren"),"INVERT");
		
		this.get_Button(0).add_oActionAgent(new ActionAgentForChangeSelection("ALLE",this.vE2_ComponentMAPS));
		this.get_Button(1).add_oActionAgent(new ActionAgentForChangeSelection("KEINE",this.vE2_ComponentMAPS));
		this.get_Button(2).add_oActionAgent(new ActionAgentForChangeSelection("INVERT",this.vE2_ComponentMAPS));
		
	}

	

	
	private class ActionAgentForChangeSelection extends XX_ActionAgent
	{
		private Vector<E2_ComponentMAP> vE2_Components = null;
		private String cType = ""; 

		public ActionAgentForChangeSelection(String type, Vector<E2_ComponentMAP> components)
		{
			super();
			cType = type;
			vE2_Components = components;
		}

		
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			for (int i=0;i<this.vE2_Components.size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)this.vE2_Components.get(i);
				for (int k=0;k<oMap.get_vComponentHashKeys().size();k++)
				{
					Component oComp = (Component)oMap.get(oMap.get_vComponentHashKeys().get(k));
					if (oComp instanceof E2_CheckBoxForList)
					{
						if (this.cType.equals("ALLE")) ((E2_CheckBoxForList)oComp).setSelected(true);
						if (this.cType.equals("KEINE")) ((E2_CheckBoxForList)oComp).setSelected(false);
						if (this.cType.equals("INVERT")) ((E2_CheckBoxForList)oComp).setSelected(!((E2_CheckBoxForList)oComp).isSelected());
					}
					
				}
				
			}
		}

		
	}
	
	
	
}
