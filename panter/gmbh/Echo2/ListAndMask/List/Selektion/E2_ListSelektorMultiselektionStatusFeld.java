package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_ListSelektorMultiselektionStatusFeld extends XX_ListSelektor
{

	private Vector<MyE2_CheckBox>  	vCheckBoxTypen = new Vector<MyE2_CheckBox>();
	
	private int            			iCountColsInGrid = 4;
	private boolean                 bZeigeBeschriftungAn = true;
	private MyE2_String             cBeschriftung = null;
	private Extent  				oExtBeschriftung = null; 					
	
	private String    				cConditionWhenNothingIsSelected = "(1=2)";
	
	
	//2012-02-28: weiteres feld fuer die definition, was passiert, wenn alle selektiert sind
	private String    				cConditionWhenAllIsSelected = null;;

	public E2_ListSelektorMultiselektionStatusFeld(int CountColumnsInGrid, boolean ZeigeBeschriftungAn, MyE2_String Beschriftung, Extent ExtBeschriftung)
	{
		super();
		this.iCountColsInGrid = CountColumnsInGrid;
		this.cBeschriftung = Beschriftung;
		this.oExtBeschriftung = ExtBeschriftung;
		this.bZeigeBeschriftungAn = ZeigeBeschriftungAn;
		
		
		this.set_oNeutralisator(new ownSelectFieldNeutralisator());
		
	}

	public void ADD_STATUS_TO_Selector(boolean bPreselected, String cBedingungsBlockFuer_OR_Statement, MyString cBeschriftung, MyString cToolTips)
	{
		this.vCheckBoxTypen.add(new ownCheckBox(bPreselected,cBedingungsBlockFuer_OR_Statement,cBeschriftung,cToolTips));
	}
	
	
	@Override
	public String get_WhereBlock() throws myException
	{
		String cWHERE = "";
		
		
		boolean bAllIsSelected= true;
		
		for (MyE2_CheckBox oCB: this.vCheckBoxTypen)
		{
			if (oCB.isSelected())
			{
				cWHERE += (" OR "+oCB.EXT().get_C_MERKMAL());
			}
			else
			{
				bAllIsSelected = false;
			}
		}
		
		if (S.isFull(cWHERE))
		{
			cWHERE = cWHERE.substring(3);              //das erste OR wegschneiden
			cWHERE = "("+cWHERE+")";
		}
		else
		{
			cWHERE = this.cConditionWhenNothingIsSelected;                        //damit das verhalten <keine auswahl> nachvollziehbar ist
		}
		
		
		if (bAllIsSelected && this.cConditionWhenAllIsSelected != null)
		{
			return this.cConditionWhenAllIsSelected;
		}
		
		return cWHERE;
	}

	

	@Override
	public Component get_oComponentWithoutText() throws myException
	{
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
	{
		for (MyE2_CheckBox oCB: this.vCheckBoxTypen)
		{
			oCB.add_oActionAgent(oAgent);
		}
	}

	
	@Override
	public void doInternalCheck()
	{
	}

	
	private class ownCheckBox extends MyE2_CheckBox
	{

		public ownCheckBox(boolean bIsSelected, String OR_BLOCK, MyString cText, MyString cToolTip)
		{
			super(bIsSelected, false);
			
			this.setText(cText.CTrans());
			this.EXT().set_C_MERKMAL(OR_BLOCK);
			
			if (cToolTip!=null) {this.setToolTipText(cToolTip.CTrans());}
		}
		
	}

	public Vector<MyE2_CheckBox> get_vCheckBoxTypen()
	{
		return vCheckBoxTypen;
	}

	public int get_iCountColsInGrid()
	{
		return iCountColsInGrid;
	}

	public Extent get_oExtBeschriftung()
	{
		return oExtBeschriftung;
	}

	public MyE2_String get_cBeschriftung()
	{
		return cBeschriftung;
	}
	
	public boolean get_bZeigeBeschriftungAn()
	{
		return bZeigeBeschriftungAn;
	}

	public String get_cConditionWhenNothingIsSelected()
	{
		return cConditionWhenNothingIsSelected;
	}

	public void set_cConditionWhenNothingIsSelected(String Condition)
	{
			
		if (S.isEmpty(Condition))
		{
			Condition = "(1=2)";
		}
	
		
		if (!Condition.startsWith("(")) { Condition = "("+Condition;	 }
		if (!Condition.endsWith(")")) { Condition = Condition+")";		}
			
		
		this.cConditionWhenNothingIsSelected = Condition;
	}

	
	//2012-02-15: ein neutralisator
	private class ownSelectFieldNeutralisator extends XX_ListSelektor_Neutralisator
	{
		public ownSelectFieldNeutralisator()
		{
			super();
		}

		@Override
		public void set_to_Neutral() throws myException
		{
			for (MyE2_CheckBox oCB: E2_ListSelektorMultiselektionStatusFeld.this.vCheckBoxTypen)
			{
				oCB.setSelected(true);
			}
		}
	}

	
	//2012-02-28: falls alles selektiert ist, kann es sein, dass mit einer leeren rueckgabe die abfrage schneller wird
	public String get_cConditionWhenAllIsSelected()
	{
		return this.cConditionWhenAllIsSelected;
	}

	public void set_cConditionWhenAllIsSelected(String ConditionWhenAllIsSelected)
	{
		this.cConditionWhenAllIsSelected = ConditionWhenAllIsSelected;
	}

	

	//2014-01-09: speichermoeglichkeit/wiedereinstellmoeglichkeit eines selektors einbauen
	public String get_MemStringStatusSelektor() throws myException  {
		String cRueck = "";
		
		for (int i=0;i<this.vCheckBoxTypen.size();i++) {
			cRueck = cRueck+(this.vCheckBoxTypen.get(i).isSelected()?"Y":"N");
		}
		return cRueck;	
	}
	
	
	public void set_MemStringStatusSelektor(String cWerteString) throws myException {
		for (int i=0;i<this.vCheckBoxTypen.size();i++) {
			boolean bSet=false;
			if (cWerteString.length()>i) {
				bSet = (cWerteString.substring(i, i+1).equals("Y"));
			}
			this.vCheckBoxTypen.get(i).setSelected(bSet);
		}
	}
	
	
	
	
}
