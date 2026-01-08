package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public  class E2_ListSelektorMultiselektionV2 extends XX_ListSelektor {

	/*
	 * Beispiel fuer aufruf: 
	 * E2_ListSelektorMultiselektionV2 selAktiv = new E2_ListSelektorMultiselektionV2()	._addLabel(S.ms("Zeige"), 40)
		       																			._addCheck(TAX.aktiv, true, new vgl_YN(TAX.aktiv, true).s(), S.ms("Aktive"), S.ms("Zeige aktive Steuersätze"), 70)
		       																			._addCheck(TAX.aktiv, true, new vgl_YN(TAX.aktiv, false).s(), S.ms("Inaktive"), S.ms("Zeige inaktive Steuersätze"), 70)
		       																			;
	 */
	
	
	
	private VEK<member_of_multiselect>  	partsOfSelector = new VEK<member_of_multiselect>();
	private String    						cConditionWhenNothingIsSelected = "(1=2)";
	private String    						cConditionWhenAllIsSelected = null;;
	
	private VEK<Integer>					gridColWidth = new VEK<Integer>();    //spalten des grids des selektors, wird standardmaessig von den members gefuellt
	
	public E2_ListSelektorMultiselektionV2() {
		super();
		this.set_oNeutralisator(new ownSelectFieldNeutralisator());
	}

	public E2_ListSelektorMultiselektionV2 _addCheck(IF_Field field, boolean preSelect, String orBlockPart, MyE2_String text, MyE2_String toolTip, Integer width) throws myException {
		this.partsOfSelector.add(new OwnCheckBox(preSelect,orBlockPart,text,toolTip, width));
		this.gridColWidth._a(O.NN(width,100));
		return this;
	}
	
	public E2_ListSelektorMultiselektionV2 _addLabel(MyE2_String text, Integer width) throws myException {
		this.partsOfSelector.add(new OwnLabel(text, width));
		this.gridColWidth._a(O.NN(width,100));
		return this;
	}
	

	
	@Override
	public String get_WhereBlock() throws myException {
		String cWHERE = "";
		
		boolean bAllIsSelected= true;
		
		for (member_of_multiselect oCB: this.partsOfSelector) {
			if (oCB instanceof OwnCheckBox) {
				OwnCheckBox cb = (OwnCheckBox) oCB;
				if (cb.isSelected()) {
					cWHERE += (" OR "+cb.EXT().get_C_MERKMAL());
				} else {
					bAllIsSelected = false;
				}
			}
		}
		
		if (S.isFull(cWHERE)) {
			cWHERE = cWHERE.substring(3);              							 //das erste OR wegschneiden
			cWHERE = "("+cWHERE+")";
		} else {
			cWHERE = this.cConditionWhenNothingIsSelected;                        //damit das verhalten <keine auswahl> nachvollziehbar ist
		}
		
		if (bAllIsSelected && this.cConditionWhenAllIsSelected != null)	{
			return this.cConditionWhenAllIsSelected;
		}
		
		return cWHERE;
	}

	

	@Override
	public Component get_oComponentWithoutText() throws myException {
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		for (member_of_multiselect oCB: this.partsOfSelector) {
			if (oCB instanceof OwnCheckBox) {
				OwnCheckBox cb = (OwnCheckBox) oCB;
				cb.add_oActionAgent(oAgent);
			}
		}
	}

	
	@Override
	public void doInternalCheck()
	{
	}

	
	
	public static interface member_of_multiselect {
		public Integer getColWidth();
	};
	
	private class OwnCheckBox extends RB_cb implements member_of_multiselect {
		private Integer width = 100;
		public OwnCheckBox(boolean isSelected, String OR_BLOCK, MyE2_String cText, MyE2_String cToolTip, Integer width) throws myException {
			super();
			this._setSelected(isSelected);
			if (cText!=null) {this._t(cText);}
			if (cToolTip!=null) {this._ttt(cToolTip);}
			if (width!=null) {this.width=width;}
			this.EXT().set_C_MERKMAL(OR_BLOCK);
		}
		public Integer getColWidth() {
			return width;
		}

	}

	private class OwnLabel extends RB_lab implements member_of_multiselect {
		private Integer width = 100;
		public OwnLabel(MyE2_String text, Integer width) {
			super(text);
			if (width!=null) {this.width=width;}
		}
		public Integer getColWidth() {
			return width;
		}
		
	}

	public String get_cConditionWhenNothingIsSelected() {
		return cConditionWhenNothingIsSelected;
	}

	public E2_ListSelektorMultiselektionV2  _setConditionWhenNothingIsSelected(String Condition) {
		if (S.isEmpty(Condition)) {
			Condition = "(1=2)";
		}
		if (!Condition.startsWith("(")) { Condition = "("+Condition;	 }
		if (!Condition.endsWith(")")) { Condition = Condition+")";		}
		
		this.cConditionWhenNothingIsSelected = Condition;
		return this;
	}

	
	private class ownSelectFieldNeutralisator extends XX_ListSelektor_Neutralisator {
		public ownSelectFieldNeutralisator(){
			super();
		}

		@Override
		public void set_to_Neutral() throws myException {
			for (member_of_multiselect oCB: partsOfSelector) {
				if (oCB instanceof OwnCheckBox) {
					OwnCheckBox cb = (OwnCheckBox) oCB;
					cb.setSelected(true);;
				}
			}
		}
	}

	
	public String get_cConditionWhenAllIsSelected()	{
		return this.cConditionWhenAllIsSelected;
	}

	public E2_ListSelektorMultiselektionV2 _setConditionWhenAllIsSelected(String ConditionWhenAllIsSelected)	{
		this.cConditionWhenAllIsSelected = ConditionWhenAllIsSelected;
		return this;
	}

	

	//2014-01-09: speichermoeglichkeit/wiedereinstellmoeglichkeit eines selektors einbauen
	public String get_MemStringStatusSelektor() throws myException  {
		String cRueck = "";
		for (member_of_multiselect member: this.partsOfSelector) {
			if (member instanceof OwnCheckBox) {
				OwnCheckBox cb = (OwnCheckBox) member;
				cRueck = cRueck+(cb.isSelected()?"Y":"N");
			}
		}
		return cRueck;	
	}
	
	
	public void set_MemStringStatusSelektor(String cWerteString) throws myException {
		int i=0;
		for (member_of_multiselect member: this.partsOfSelector) {
			if (member instanceof OwnCheckBox) {
				OwnCheckBox cb = (OwnCheckBox) member;
				boolean bSet=false;
				if (cWerteString.length()>i) {
					bSet = (cWerteString.substring(i, i+1).equals("Y"));
				}
				cb._setSelected(bSet);
				i++;
			}
		}
	}

	@Override
	public E2_Grid get_oComponentForSelection() throws myException {
		E2_Grid grid = new E2_Grid()._setSizeInteger(gridColWidth.getArray());
		for (member_of_multiselect member: this.partsOfSelector) {
			if (member instanceof OwnCheckBox) {
				grid._a((OwnCheckBox)member, new RB_gld()._left_mid());
			} else if (member instanceof OwnLabel) {
				grid._a((OwnLabel)member, new RB_gld()._left_mid());
			}
		}
		return grid;
	}

	/**
	 * @return the gridColWidth
	 */
	public VEK<Integer> getGridColWidth() {
		return gridColWidth;
	}

	/**
	 * @param gridColWidth the gridColWidth to set
	 */
	public E2_ListSelektorMultiselektionV2 _setGridColWidth(VEK<Integer> gridColWidth) {
		this.gridColWidth = gridColWidth;
		return this;
	}
	
	
	
	
}
