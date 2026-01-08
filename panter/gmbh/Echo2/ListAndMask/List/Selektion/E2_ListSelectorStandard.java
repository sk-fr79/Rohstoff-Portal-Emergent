package panter.gmbh.Echo2.ListAndMask.List.Selektion;



import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
/*
 *   eine klasse, die ein objekt kapselt, das für eine weitere selektion
 *   benütigt wird. 
 *   Enthalten: eine Komponente, (entweder checkbox oder dropdown),
 *  			ein Querystring (teilstring) fuer eine where-bedingung mit eingelagertem #WERT#, benutzt um eine weitere where-bedinungung im bedienpanel zu bilden 
 * 	
 *   kann benutzt werden, um eine schalter-bedingung zu realisieren, z.b. erledigte von unerledigten auftragen separiert			 
 */
public class E2_ListSelectorStandard extends XX_ListSelektor
{
	private Component 		oComponent = 		null;
	private String 			cWhereString = 		"";
	private String 			cWhereStringUnSelected = "";   // wird zusaetzlich bei der checkbox benutzt, dort wird sowohl whereselected als auch (falls noetig) whereunselected uebergeben (ohne platzhalter) 
	
	private MyString   		cStringForBeschriftung = null;
	private Integer         AbstandZurBeschriftung = null;
	
	private Extent          oExtendForTextRahmen   = null;  // wird dies gesetzt, dann wird der beschriftungstext in eine border dieser breite gesetzt
	


	/*
	 * aenderung 13.06.2007
	 */
	private int 	       iPosLeerEintrag = -1;                   // falls ein leer-eintrag, der in der abfrage IS NULL - muendet, uebergeben werden soll, merkt sich dieser wert die position in der liste
																   // gilt nur bei selectfield-selectoren
	private String         cNullQueryBlock = null;                 // null-query-block, wird uebergeben, wenn der benutzer den iPosLeereintrag anwaehlt
	
	
	
	// falls ein MyE2_Selektor mit einer hashmap statt einem wherestring geliefert wird,
	// dann ist in der hashmap als key der select-wert und als value die sql-bedingung zu uebergeben
	private HashMap<String, String>  hmValuePlusWhereblock = new HashMap<String, String>();
	
	/**
	 * @param StringForBeschriftung
	 * @param Abstand
	 * @param ocomponent: entweder select 
	 * @param cwhereString: string im wherebereich mit eingelagertem #WERT#, z.b. <SPRACHE=#WERT#>
	 * @throws myException
	 */
	public E2_ListSelectorStandard(MyE2_SelectField oselfield, String cwhereString, MyString StringForBeschriftung, Integer Abstand) throws myException
	{
		this.oComponent = oselfield;
		this.cWhereString = cwhereString;
		this.cStringForBeschriftung = StringForBeschriftung;
		this.AbstandZurBeschriftung = Abstand;
		
		if (this.cWhereString.indexOf("#WERT#")<0)
			throw new myException("E2_ListSelection:Constuctor ! please put the string <#WERT#> to the query-block");
		
		/*
		 * wenn von oben kein standard-wert definiert wurde, dann auf den ersten setzen
		 */
		if (oselfield.getSelectedIndex()<0)
			if (oselfield.get_oDataToView().size()>0)
				oselfield.setSelectedIndex(0);
		
		//2012-02-14: neutralisator
		if (oselfield.get_DataToView() !=null)
		{
			if (S.isEmpty(oselfield.get_DataToView().get_cValueAtPosition(0)))
			{
				this.set_oNeutralisator(new ownSelectFieldNeutralisator_1st_is_neutral());
			}
		}
	}


	

	/**
	 * 
	 * @param oselfield
	 * @param hm_ValuePlusWhereblock  -- enthaelt eine hashmap mit einem key, der im Selectfield als value vorhanden ist, und einem kompletten where-block
	 * @param StringForBeschriftung
	 * @param Abstand
	 * @throws myException
	 */
	public E2_ListSelectorStandard(MyE2_SelectField oselfield, HashMap<String, String>  hm_ValuePlusWhereblock, MyString StringForBeschriftung, Integer Abstand) throws myException
	{
		this.oComponent = oselfield;
		this.cWhereString = null;
		this.hmValuePlusWhereblock.putAll(hm_ValuePlusWhereblock);
		this.cStringForBeschriftung = StringForBeschriftung;
		this.AbstandZurBeschriftung = Abstand;

		/*
		 * wenn von oben kein standard-wert definiert wurde, dann auf den ersten setzen
		 */
		if (oselfield.getSelectedIndex()<0)
			if (oselfield.get_oDataToView().size()>0)
				oselfield.setSelectedIndex(0);
		
		
		//2012-02-14: neutralisator
		if (oselfield.get_DataToView() !=null)
		{
			//jetzt die hashmap durchsuchen nach value=leer
			Iterator<Entry<String,String>> oIter =  hm_ValuePlusWhereblock.entrySet().iterator();
			
			while (oIter.hasNext())
			{
				Entry<String,String> oEntry = oIter.next();
				
				if (S.isEmpty(oEntry.getValue()))
				{
					this.set_oNeutralisator(new ownSelectFieldNeutralisator(oEntry.getKey()));
					break;
				}
			}
		}

		
	}


	
	/**
	 * 
	 * @param oselfield
	 * @param hm_ValuePlusWhereblock  -- enthaelt eine hashmap mit einem key, der im Selectfield als value vorhanden ist, und einem kompletten where-block
	 * @param StringForBeschriftung
	 * @param Abstand
	 * @throws myException
	 */
	public E2_ListSelectorStandard(RB_selField oselfield, HashMap<String, String>  hm_ValuePlusWhereblock, MyString StringForBeschriftung, Integer Abstand) throws myException
	{
		this.oComponent = oselfield;
		this.cWhereString = null;
		this.hmValuePlusWhereblock.putAll(hm_ValuePlusWhereblock);
		this.cStringForBeschriftung = StringForBeschriftung;
		this.AbstandZurBeschriftung = Abstand;

		/*
		 * wenn von oben kein standard-wert definiert wurde, dann auf den ersten setzen
		 */
		if (oselfield.getSelectedIndex()<0) {
			if (oselfield.getHMAPVisible().size()>0) {
				if (oselfield.getActualDbVal()==null) {
					oselfield.setSelectedIndex(0);
				}
			}
		}
		
		//2019-09-04: neutralisator
		if (oselfield.getHMAPVisible().size()>0 && oselfield.getVCompleteDBVals().contains("")) {
			this.set_oNeutralisator(new ownSelFieldNeutralisator(""));
		}

		
	}


	
	/**
	 * @param ocomponent: checkbox
	 * @param cwhereStringSelected: string im wherebereich OHNE eingelagertes #WERT#, z.b. <SPRACHE=#WERT#>
	 * @param cwhereStringUnSelected: string im wherebereich OHNE eingelagertes #WERT#, z.b. <SPRACHE=#WERT#>
	 * @throws myException
	 */
	public E2_ListSelectorStandard(MyE2_CheckBox ocheckbox, String cwhereStringSelected, String cwhereStringUnSelected) throws myException
	{
		this.oComponent = ocheckbox;
		this.cWhereString = cwhereStringSelected;
		this.cWhereStringUnSelected = bibALL.null2leer(cwhereStringUnSelected);

		if (this.cWhereString.indexOf("#WERT#")>=0 || this.cWhereStringUnSelected.indexOf("#WERT#")>=0)
			throw new myException("E2_ListSelection:Constuctor ! DONT put the string <#WERT#> to the query-block");
		
		//2012-02-14: neutralisator
		if (S.isEmpty(this.cWhereString))
		{
			this.set_oNeutralisator(new ownCheckBoxNeutralisatorSelectedIsNeutral());
		}
		if (S.isEmpty(this.cWhereStringUnSelected))
		{
			this.set_oNeutralisator(new ownCheckBoxNeutralisatorUnSelectedIsNeutral());
		}
		
	}

	
	
	/**
	 * 2017-02-03
	 * @param ocomponent: checkbox
	 * @param cwhereStringSelected: string im wherebereich OHNE eingelagertes #WERT#, z.b. <SPRACHE=#WERT#>
	 * @param cwhereStringUnSelected: string im wherebereich OHNE eingelagertes #WERT#, z.b. <SPRACHE=#WERT#>
	 * @throws myException
	 */
	public E2_ListSelectorStandard(CheckBox ocheckbox, String cwhereStringSelected, String cwhereStringUnSelected) throws myException
	{
		this.oComponent = ocheckbox;
		this.cWhereString = cwhereStringSelected;
		this.cWhereStringUnSelected = bibALL.null2leer(cwhereStringUnSelected);

		if (this.cWhereString.indexOf("#WERT#")>=0 || this.cWhereStringUnSelected.indexOf("#WERT#")>=0)
			throw new myException("E2_ListSelection:Constuctor ! DONT put the string <#WERT#> to the query-block");
		
		//2012-02-14: neutralisator
		if (S.isEmpty(this.cWhereString))
		{
			this.set_oNeutralisator(new ownRawCheckBoxNeutralisatorSelectedIsNeutral());
		}
		if (S.isEmpty(this.cWhereStringUnSelected))
		{
			this.set_oNeutralisator(new ownRawCheckBoxNeutralisatorUnSelectedIsNeutral());
		}
		
	}


	
	/**
	 * @param ocomponent: MyE2_TextField_DatePOPUP_OWN
	 * @param cwhereStringSelected: string im wherebereich OHNE eingelagertes #WERT#, z.b. <SPRACHE=#WERT#>
	 * @param cwhereStringUnSelected: string im wherebereich OHNE eingelagertes #WERT#, z.b. <SPRACHE=#WERT#>
	 * @throws myException
	 */
	public E2_ListSelectorStandard(MyE2_TextField_DatePOPUP_OWN oDateSelect, String cwhereStringSelected, String cwhereStringUnSelected) throws myException
	{
		this.oComponent = oDateSelect;
		this.cWhereString = cwhereStringSelected;
		this.cWhereStringUnSelected = bibALL.null2leer(cwhereStringUnSelected);

		if (this.cWhereString.indexOf("#WERT#")<0)
			throw new myException("E2_ListSelection:Constuctor ! please put the string <#WERT#> to the query-block");

	}

	
	
	/**
	 * @param StringForBeschriftung 
	 * @param Abstand 
	 * @param cSQL_Query: Abfrage mit zwei spalten: anzeige-wert,datenwert, der in den where-block eingefuegt wird
	 * @param @param cwhereString: string im wherebereich mit eingelagertem #WERT#, z.b. <SPRACHE=#WERT#>
	 * @param bAddEmptySelector: true, wenn ein leer-paar eingefuegt werden soll (*)
	 * @throws myException
	 */
	public E2_ListSelectorStandard(String cSQL_Query,String cwhereString, boolean bAddEmptySelector, MyString StringForBeschriftung, Integer Abstand) throws myException
	{
		this.cWhereString = cwhereString;
		this.cStringForBeschriftung = StringForBeschriftung;
		this.AbstandZurBeschriftung = Abstand;

		if (this.cWhereString.indexOf("#WERT#")<0)
			throw new myException("E2_ListSelection:Constuctor ! please put the string <#WERT#> to the query-block");

		String[][] cRueck = bibDB.EinzelAbfrageInArray(cSQL_Query,"");
		
		String[][] cHelp;
		if (cRueck== null)
			throw new myException("E2_ListSelection:Constuctor ! Query gives null-result!");
		
		if (cRueck.length==0)
		{
			cHelp = new String[1][2];
			cHelp[0][0]="*";
			cHelp[0][1]="";
		}
		else
		{
			if (cRueck[0].length!=2)
				throw new myException("E2_ListSelection:Constuctor ! Please set query with exact 2 columns: View and Value");
			
			
			if (bAddEmptySelector)
			{
				cHelp = new String[cRueck.length+1][2];
				cHelp[0][0]="*";
				cHelp[0][1]="";
				for (int i=0;i<cRueck.length;i++)
				{
					cHelp[i+1][0]=cRueck[i][0];
					cHelp[i+1][1]=cRueck[i][1];
				}
				
				//2012-02-14: neutralisator
				this.set_oNeutralisator(new ownSelectFieldNeutralisator_1st_is_neutral());
			}
			else
			{
				cHelp = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
				{
					cHelp[i][0]=cRueck[i][0];
					cHelp[i][1]=cRueck[i][1];
				}
				
			}
		}
		this.oComponent = new MyE2_SelectField(cHelp,null,false);

		/*
		 * immer auf den ersten setzen
		 */
		((MyE2_SelectField)this.oComponent).setSelectedIndex(0);

	}
	
	
	/**
	 * @param StringForBeschriftung 
	 * @param Abstand 
	 * @param cSQL_Query: Abfrage mit zwei spalten: anzeige-wert,datenwert, der in den where-block eingefuegt wird
	 * @param @param cwhereString: string im wherebereich mit eingelagertem #WERT#, z.b. <SPRACHE=#WERT#>
	 * @param bAddEmptySelector: true, wenn ein leer-paar eingefuegt werden soll (*)
	 * @param DropDownFieldWidth: wenn die selektionskomponente ein selektor wird, dann diese breite verwenden
	 * @throws myException
	 */
	public E2_ListSelectorStandard(String cSQL_Query,String cwhereString, boolean bAddEmptySelector, MyString StringForBeschriftung, Integer Abstand, Integer DropDownFieldWidth) throws myException
	{
		this.cWhereString = cwhereString;
		this.cStringForBeschriftung = StringForBeschriftung;
		this.AbstandZurBeschriftung = Abstand;

		if (this.cWhereString.indexOf("#WERT#")<0)
			throw new myException("E2_ListSelection:Constuctor ! please put the string <#WERT#> to the query-block");

		String[][] cRueck = bibDB.EinzelAbfrageInArray(cSQL_Query,"");

		String[][] cHelp;
		if (cRueck== null)
			throw new myException("E2_ListSelection:Constuctor ! Query gives null-result!");
		
		if (cRueck.length==0)
		{
			cHelp = new String[1][2];
			cHelp[0][0]="*";
			cHelp[0][1]="";
		}
		else
		{
			if (cRueck[0].length!=2)
				throw new myException("E2_ListSelection:Constuctor ! Please set query with exact 2 columns: View and Value");
			
			
			if (bAddEmptySelector)
			{
				cHelp = new String[cRueck.length+1][2];
				cHelp[0][0]="*";
				cHelp[0][1]="";
				for (int i=0;i<cRueck.length;i++)
				{
					cHelp[i+1][0]=cRueck[i][0];
					cHelp[i+1][1]=cRueck[i][1];
				}
				
				//2012-02-14: neutralisator
				this.set_oNeutralisator(new ownSelectFieldNeutralisator_1st_is_neutral());

			}
			else
			{
				cHelp = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
				{
					cHelp[i][0]=cRueck[i][0];
					cHelp[i][1]=cRueck[i][1];
				}
				
			}
		}
		
		if (DropDownFieldWidth!=null)
		{
			this.oComponent = new MyE2_SelectField(cHelp,null,false, new Extent(DropDownFieldWidth.intValue()));
		}
		else
		{
			this.oComponent = new MyE2_SelectField(cHelp,null,false);
		}

		/*
		 * immer auf den ersten setzen
		 */
		((MyE2_SelectField)this.oComponent).setSelectedIndex(0);

		
	}

	public String get_WhereBlock() throws myException
	{
		String cRueck = "";
		if (this.oComponent instanceof MyE2_SelectField)
		{
			if (this.cWhereString == null && this.hmValuePlusWhereblock.size()>0)
			{
				cRueck = this.hmValuePlusWhereblock.get(((MyE2_SelectField)oComponent).get_ActualWert());
			}
			else
			{
				//klassisch via eingelagertem #WERT#
				
				if ( (!bibALL.isEmpty(this.cNullQueryBlock)) && this.iPosLeerEintrag>=0 && ((MyE2_SelectField)oComponent).getSelectedIndex()==this.iPosLeerEintrag)
				{
					cRueck = this.cNullQueryBlock;
				}
				else
				{
					if ( !((MyE2_SelectField)oComponent).get_ActualWert().trim().equals(""))
					{
						cRueck = bibALL.ReplaceTeilString(this.cWhereString,"#WERT#",((MyE2_SelectField)oComponent).get_ActualWert());
					}
				}
			}
		}
		
		if (this.oComponent instanceof CheckBox)
		{
			if (((CheckBox)this.oComponent).isSelected()){ 
				cRueck = this.cWhereString; 
			}	else { 
				cRueck = this.cWhereStringUnSelected; 
			}
		}

		if (this.oComponent instanceof MyE2_TextField_DatePOPUP_OWN)
		{
			if ( ((MyE2_TextField_DatePOPUP_OWN)oComponent).get_oLastDateKlicked() != null)
			{
				cRueck = bibALL.ReplaceTeilString(this.cWhereString,"#WERT#",
								((MyE2_TextField_DatePOPUP_OWN)oComponent).get_oLastDateKlicked().get_cDateFormat_ISO_FORMAT());
			}
			else
			{
				cRueck = "";
				if (S.isFull(this.cWhereStringUnSelected))
				{
					cRueck = bibALL.ReplaceTeilString(this.cWhereStringUnSelected,"#WERT#",
							((MyE2_TextField_DatePOPUP_OWN)oComponent).get_oTextField().getText());
				}
			}
		}
		
		
		//2019-09-04: weitere standardkomponente
		if (this.oComponent instanceof RB_selField) {
			if (this.cWhereString == null && this.hmValuePlusWhereblock.size()>0) {
				cRueck = this.hmValuePlusWhereblock.get(((RB_selField)oComponent).getActualDbVal());
			} else {
				if ( (!bibALL.isEmpty(this.cNullQueryBlock)) && this.iPosLeerEintrag>=0 && ((MyE2_SelectField)oComponent).getSelectedIndex()==this.iPosLeerEintrag)	{
					cRueck = this.cNullQueryBlock;
				} else	{
					if ( !((RB_selField)oComponent).getActualDbVal().trim().equals(""))	{
						cRueck = bibALL.ReplaceTeilString(this.cWhereString,"#WERT#",((RB_selField)oComponent).getActualDbVal());
					}
				}
			}
		}
		
		
		
		return " "+cRueck+" ";
	}
	

	public Component get_oComponentForSelection() 
	{
		if (this.cStringForBeschriftung == null)
		{
			return this.oComponent;
		}
		else
		{
			
			if (this.oExtendForTextRahmen!=null)
			{
				MyE2_Row rowHelp = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				rowHelp.add(new MyE2_Label(this.cStringForBeschriftung).get_InBorderGrid(new Border(0, Color.BLACK, Border.STYLE_NONE), this.oExtendForTextRahmen, E2_INSETS.I_0_0_0_0));
				rowHelp.add(this.oComponent,E2_INSETS.I_0_0_0_0);
				return rowHelp;
			}
			else
			{
			
				Insets  oIN = E2_INSETS.I_0_0_5_0;
				
				if (this.AbstandZurBeschriftung != null)
				{
					oIN = new Insets(0,0,this.AbstandZurBeschriftung.intValue(),0);
				}
				
				MyE2_Row rowHelp = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				rowHelp.add(new MyE2_Label(this.cStringForBeschriftung),oIN);
				rowHelp.add(this.oComponent,E2_INSETS.I_0_0_0_0);
				return rowHelp;
			}
		}
	}


	
	public Component get_oComponentWithoutText() 
	{
		return this.oComponent;
	}

	
	public void doInternalCheck()
	{
	}



	/**
	 * @param posLeerEintrag
	 * @param NullQueryBlock
	 * Definiert die position in der liste, wo unabhaengig vom wert 
	 */
	public void set_iPosLeerEintrag(int posLeerEintrag, String NullQueryBlock) 
	{
		this.iPosLeerEintrag = posLeerEintrag;
		this.cNullQueryBlock = NullQueryBlock;
	}



	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) 
	{
		if (this.oComponent instanceof MyE2_TextField_DatePOPUP_OWN)
		{
			((MyE2_TextField_DatePOPUP_OWN)this.oComponent).get_vActionAgentsZusatz().removeAllElements();
			((MyE2_TextField_DatePOPUP_OWN)this.oComponent).get_vActionAgentsZusatz().add(oAgent);
			if (((MyE2_TextField_DatePOPUP_OWN)this.oComponent).get_oButtonEraser()!=null)
			{
				((MyE2_TextField_DatePOPUP_OWN)this.oComponent).get_oButtonEraser().add_oActionAgent(oAgent);
			}
			
		}
		else
		{
			((E2_IF_Handles_ActionAgents)this.oComponent).add_oActionAgent(oAgent);
		}
	}



	//2011-08-18: weitere variante
	public Extent get_oExtendForTextRahmen()
	{
		return oExtendForTextRahmen;
	}


	public void set_oExtendForTextRahmen(Extent oExtendForTextRahmen)
	{
		this.oExtendForTextRahmen = oExtendForTextRahmen;
	}

	
	
	//2012-02-14: neutralisatoren
	private class ownCheckBoxNeutralisatorSelectedIsNeutral extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			((MyE2_CheckBox)E2_ListSelectorStandard.this.oComponent).setSelected(true);
		}
	}
	
	//2012-02-14: neutralisatoren
	private class ownCheckBoxNeutralisatorUnSelectedIsNeutral extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			((MyE2_CheckBox)E2_ListSelectorStandard.this.oComponent).setSelected(false);
		}
	}
	
	
	//2012-02-14: neutralisatoren
	private class ownRawCheckBoxNeutralisatorSelectedIsNeutral extends XX_ListSelektor_Neutralisator	{
		@Override
		public void set_to_Neutral() throws myException		{
			((CheckBox)E2_ListSelectorStandard.this.oComponent).setSelected(true);
		}
	}
	
	//2012-02-14: neutralisatoren
	private class ownRawCheckBoxNeutralisatorUnSelectedIsNeutral extends XX_ListSelektor_Neutralisator 	{
		@Override
		public void set_to_Neutral() throws myException {
			((CheckBox)E2_ListSelectorStandard.this.oComponent).setSelected(false);
		}
	}

	
	
	//2012-02-14: neutralisatoren
	private class ownSelectFieldNeutralisator_1st_is_neutral extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			((MyE2_SelectField)E2_ListSelectorStandard.this.oComponent).setSelectedIndex(0);
		}
	}
	

	//2012-02-14: neutralisatoren
	private class ownSelectFieldNeutralisator extends XX_ListSelektor_Neutralisator
	{
		private String cNeutralKey = null;
		
		public ownSelectFieldNeutralisator(String NeutralKey)
		{
			super();
			this.cNeutralKey = NeutralKey;
		}

		@Override
		public void set_to_Neutral() throws myException
		{
			((MyE2_SelectField)E2_ListSelectorStandard.this.oComponent).set_ActiveValue(this.cNeutralKey);
		}
	}

	//2012-02-14: neutralisatoren
	private class ownSelFieldNeutralisator extends XX_ListSelektor_Neutralisator
	{
		private String neutralDbVal = null;
		
		public ownSelFieldNeutralisator(String neutral_db_val)
		{
			super();
			this.neutralDbVal = neutral_db_val;
		}

		@Override
		public void set_to_Neutral() throws myException
		{
			((RB_selField)E2_ListSelectorStandard.this.oComponent)._setActiveDBVal(this.neutralDbVal);
		}
	}


	public HashMap<String, String> get_hmValuePlusWhereblock()
	{
		return hmValuePlusWhereblock;
	}
	
	public void set_hmValuePlusWhereblock(HashMap<String, String>  hm_ValuePlusWhereblock)
	{
		this.hmValuePlusWhereblock.putAll(hm_ValuePlusWhereblock);

	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#get_ValueTable()
	 * 
	 * Der Standard-Listselektor gibt immer nur einen Wert zurück. Entweder aus einer Checkbox oder einer Dropdown-Box(Select-Field) oder einem Textfeld (meistens Date)
	 * 
	 */
	@Override
	public Hashtable<ENUM_Selector_Report_Params,Object> get_ParamValueTable() throws myException {
		Hashtable<ENUM_Selector_Report_Params,Object> valuePairs = new Hashtable<>();
		String sValue = "";
		
		if (m_vSelectorParams.size()== 1){
			if (this.oComponent instanceof MyE2_SelectField)
			{
				
				//klassisch via eingelagertem #WERT#
				if ( (!bibALL.isEmpty(this.cNullQueryBlock)) && this.iPosLeerEintrag>=0 && ((MyE2_SelectField)oComponent).getSelectedIndex()==this.iPosLeerEintrag)
				{
					sValue = this.cNullQueryBlock;
				}
				else
				{
					if ( !((MyE2_SelectField)oComponent).get_ActualWert().trim().equals(""))
					{
						sValue = ((MyE2_SelectField)oComponent).get_ActualWert();
					}
				}
			}
			
			if (this.oComponent instanceof MyE2_CheckBox)
			{
				sValue = ( ((MyE2_CheckBox)this.oComponent).isSelected() ? "Y" : "N");
			}

			if (this.oComponent instanceof MyE2_TextField_DatePOPUP_OWN)
			{
				if ( ((MyE2_TextField_DatePOPUP_OWN)oComponent).get_oLastDateKlicked() != null)
				{
					sValue = ((MyE2_TextField_DatePOPUP_OWN)oComponent).get_oLastDateKlicked().get_cDateFormatForMask();
					
				}
				else
				{
					
					sValue = ((MyE2_TextField_DatePOPUP_OWN)oComponent).get_oTextField().getText();
				}
			}
		
			// den gefundenen Wert in die Hastable schreiben
			valuePairs.put(m_vSelectorParams.elementAt(0),sValue);
		}
		
		return valuePairs;
		
	}
	
	
}