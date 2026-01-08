package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


/**
 * komponente fuer das umschalten der checkboxen innerhalb einer liste
 */
public class E2_PopUpAllNoneInvert_CheckBoxes extends MyE2_PopUpMenue
{
	
	/*
	 * vector mit allen E2_ComponentMAPs innerhalb einer liste
	 */
	private Vector<MyE2_CheckBox> v_CheckBoxes = null;
	private boolean 			  bDoActionAfterSet = false;
	

	/**
	 * 
	 * @param vCheckBoxes
	 * @param doActionAfterSet
	 * @throws myException
	 */
	public E2_PopUpAllNoneInvert_CheckBoxes(Vector<MyE2_CheckBox> vCheckBoxes, boolean doActionAfterSet) throws myException
	{
		super(null, null, false);
		v_CheckBoxes = vCheckBoxes;
		
		this.bDoActionAfterSet = doActionAfterSet;
		
		
		for (int i=0;i<this.v_CheckBoxes.size();i++)
			if (!(this.v_CheckBoxes.get(i) instanceof CheckBox))
				throw new myException("E2_PopUpSelectAllNoneInvert:Constructor:Vector MUST ONLY HAVE Checkboxes stored !!");
		
		
		this.addTextButton(new MyString("Alle"),"ALLE");
		this.addTextButton(new MyString("Keine"),"KEINE");
		this.addTextButton(new MyString("Invertieren"),"INVERT");
		
		this.get_Button(0).add_oActionAgent(new ActionAgentForChangeSelection("ALLE"));
		this.get_Button(1).add_oActionAgent(new ActionAgentForChangeSelection("KEINE"));
		this.get_Button(2).add_oActionAgent(new ActionAgentForChangeSelection("INVERT"));
		
	}

	

	
	private class ActionAgentForChangeSelection extends XX_ActionAgent
	{
		private String cType = ""; 

		public ActionAgentForChangeSelection(String type)
		{
			super();
			cType = type;
		}

		
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			for (int i=0;i<E2_PopUpAllNoneInvert_CheckBoxes.this.v_CheckBoxes.size();i++)
			{
				MyE2_CheckBox oCB = E2_PopUpAllNoneInvert_CheckBoxes.this.v_CheckBoxes.get(i);
				
				if (this.cType.equals("ALLE")) oCB.setSelected(true);
				if (this.cType.equals("KEINE")) oCB.setSelected(false);
				if (this.cType.equals("INVERT")) oCB.setSelected(!oCB.isSelected());
				
				if (E2_PopUpAllNoneInvert_CheckBoxes.this.bDoActionAfterSet)
				{
					oCB.doActionPassiv();
				}
			}
		}

		
	}
	
	
	
}
