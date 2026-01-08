package panter.gmbh.Echo2.ListAndMask.List.Search;

import java.util.Vector;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SearchFields;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_KeyStrokeListener;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopMiddleMenue;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.basics4project.DB_ENUMS.SEARCHDEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;



/**
 * suchklasse fuer suche in einem datenbestand.
 * Enthaelt suchfelder, texte aufnehmen. Die einzelnen textfelder enthalten teilzeichen, die wiederum
 * enthalten die per "UND" verknuepften teilzeichenfolgen
 * Jedes einzelne Suchfeld erzeugt einen vektor von unformatierten IDs.
 * Diese werden als vereinigungsmenge ( logisch "ODER") gegen  
 * die gegen die MainTable - ID der
 * SQLFieldMAP gegengeprueft
 */
public class E2_DataSearch extends E2_ComponentGroupHorizontal
{
	
	private MyE2_Label     								oLabelSuche	=					new MyE2_Label(new MyE2_String("Daten durchsuchen:   "));

	//2018-08-03: breite des suchtextes
	private int   										m_widthSearchText = 200;


	private MyE2_Button 								oButtonADDSearchField =			new MyE2_Button(E2_ResourceIcon.get_RI("add_field.png"),true);
	private MyE2_Button									oButtonStartSearch = 			new MyE2_Button(E2_ResourceIcon.get_RI("suche.png"),true);
	private MyE2_Button									oButtonClearFieldsAndReload = 	new MyE2_Button(E2_ResourceIcon.get_RI("remove_reload.png"),true);
	private E2_DataSearchButtonToSelectSearchFields		oButtonSelectSearchFields = 	null;
	private MyE2_PopMiddleMenue                         oPopdownEinzelsuche = 			new MyE2_PopMiddleMenue(E2_ResourceIcon.get_RI("suche_popup.png"),E2_ResourceIcon.get_RI("suche_popup.png"));

	private MyE2_Column 								oColWithTextFields = 			new MyE2_Column(new Style_Column_Normal(0,new Insets(5,0,5,0)));
	
	
	/*
	 * vector enthaelt die textfelder, die fuer die such eingegeben werden
	 */
	private Vector<MyE2_TextField>				vSearchTextFields = new Vector<MyE2_TextField>();  
	
	/*
	 * vector enthaelt die suchbedingungen vom typ SearchDefinition
	 */
	private Vector<E2_SearchDefinition>			vSearchDefinitions = new Vector<E2_SearchDefinition>();
	
	/*
	 * agent, der die info enthaelt, welche aktion beim klicken auf den suchbutton ausgefuehrt wird
	 */
	private E2_DataSearchAgentAbstract			oSearchAgent = null;
	
	/*
	 * namen der basic-Tabelle, deren ID gesucht wird
	 */
	private String								cBaseTableName = null;
	private String								cKeyNameBaseTable = null;
	
	private MyE2_KeyStrokeListener				oKeyListenerForSearchF2 = new MyE2_KeyStrokeListener(KeyStrokeListener.VK_F2);
	private MyE2_KeyStrokeListener				oKeyListenerForSearchShiftF2 = new MyE2_KeyStrokeListener(KeyStrokeListener.SHIFT_MASK | KeyStrokeListener.VK_F2);
	
	private String    							cMODULE_KENNER = null;
	
	private boolean   							bFirstStart = true;           	// als trigger, wenn bei der ersten aktion (suche oder Auswahl suchfelder) die
																				// vorige suchauswahl wieder eingestellt wird
	
	private boolean   							bForceListQueryOnSearch = false;
	
	
	public boolean get_bForceListQueryOnSearch() 
	{
		return bForceListQueryOnSearch;
	}



	public void set_bForceListQueryOnSearch(boolean bForceListQueryOnSearch) 
	{
		this.bForceListQueryOnSearch = bForceListQueryOnSearch;
	}



	
	//2018-12-14: neue variante mit leerem konstruktor und init
	public E2_DataSearch() {
		super(null);
	}
	
	
	
	
	/**
	 * aenderung am 20100916: es wird auch MODULKENNER=null unterstuetzt, dann kann die suchdefinition nicht mehr gespeichert werden
	 * @param cbaseTableName
	 * @param ckeyNameTable
	 * @param MODUL_KENNER
	 */
	public E2_DataSearch(String cbaseTableName, String ckeyNameTable, String MODUL_KENNER) 	{
		super(null);
		this._init(cbaseTableName, ckeyNameTable, MODUL_KENNER);
	}
	 
	
	public E2_DataSearch _init(String cbaseTableName, String ckeyNameTable, String MODUL_KENNER) {
		
		this.cBaseTableName = cbaseTableName;
		this.cKeyNameBaseTable = ckeyNameTable;
		
		this.cMODULE_KENNER = MODUL_KENNER;
		
		if (S.isFull(this.cMODULE_KENNER))
		{
			try
			{
				new E2_UserSetting_SearchFields().restore_SearchList(this, this.cMODULE_KENNER);
			} 
			catch (myException e)
			{
				bibMSG.add_MESSAGE(e.get_ErrorMessage());
				e.printStackTrace();
			}
		}
		
		this.oButtonSelectSearchFields = new E2_DataSearchButtonToSelectSearchFields(this.vSearchDefinitions, this, MODUL_KENNER);

		this.oButtonADDSearchField.setToolTipText((new MyE2_String("Weiteres Suchfeld (Ergebnisse werden zusätzlich angezeigt)")).CTrans());
		this.oButtonStartSearch.setToolTipText((new MyE2_String("Suche starten ")).CTrans());
		this.oButtonClearFieldsAndReload.setToolTipText((new MyE2_String("Suchfelder löschen und Auswahl neu aufbauen ... ")).CTrans());
		this.oButtonSelectSearchFields.setToolTipText((new MyE2_String("Auswählen, in welchen Feldern gesucht werden soll ...")).CTrans());
		this.oPopdownEinzelsuche.setToolTipText(new MyE2_String("Suche in einzelnen Suchfeldern ...").CTrans());
		
		/*
		 * aktionen definieren
		 */
		this.oButtonADDSearchField.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo)
					{
						E2_DataSearch.this.add_SearchTextField();
						E2_DataSearch.this.rebuild_SearchTextFieldColumn();
					}
				});

		
		this.oButtonClearFieldsAndReload.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo)
					{
						E2_DataSearchAgentAbstract	osearchAgent = E2_DataSearch.this.get_oSearchAgent();
						E2_DataSearch.this.get_vSearchFields().removeAllElements();
						E2_DataSearch.this.add_SearchTextField();
						E2_DataSearch.this.rebuild_SearchTextFieldColumn();
						if (osearchAgent != null)
						{
							try
							{
								osearchAgent.ResetSearch(E2_DataSearch.this,true);
							}
							catch(myException ex)
							{
								bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
							}
						}
					}
					

				});

		this.oButtonStartSearch.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo)
					{
						E2_DataSearchAgentAbstract	osearchAgent = E2_DataSearch.this.get_oSearchAgent();
						if (osearchAgent != null)
						{
							try
							{
								E2_DataSearch.this.restore_old_settings();
								osearchAgent.StartSearch(E2_DataSearch.this, null);
							}
							catch(myException ex)
							{
								bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
							}
						}
						else
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Please define a search-agent",false)));
						
					}
				});

		
		
		/*
		 * key-action festlegen: F2 focusiert das eingabefeld 1 (falls dieses leer ist),
		 * falls nicht, wird der startknopf gedrueckt
		 */
		this.oKeyListenerForSearchF2.add_oActionAgent(new XX_ActionAgent() 
				{
					public void executeAgentCode(ExecINFO oExecInfo)
					{
						E2_DataSearch oThis = E2_DataSearch.this;
						MyE2_TextField oTF1 = (MyE2_TextField)oThis.vSearchTextFields.get(0);
						if (oTF1.getText().trim().equals(""))
						{
							ApplicationInstance.getActive().setFocusedComponent(oTF1);
						}
						else
						{
							if (oThis.oButtonStartSearch.isEnabled())
								oThis.oButtonStartSearch.doAction();
						}
						
					}
				});
		

		/*
		 * key-action festlegen: F2 focusiert das eingabefeld 1 (falls dieses leer ist),
		 * falls nicht, wird der startknopf gedrueckt
		 */
		this.oKeyListenerForSearchShiftF2.add_oActionAgent(new XX_ActionAgent() 
				{
					public void executeAgentCode(ExecINFO oExecInfo)
					{
						E2_DataSearch oThis = E2_DataSearch.this;
						if (oThis.oButtonStartSearch.isEnabled())
							oThis.oButtonClearFieldsAndReload.doAction();
						
					}
				});

		
		
		this.add(this.oKeyListenerForSearchF2);
		this.add(this.oKeyListenerForSearchShiftF2);
		this.add(this.oLabelSuche);
		this.add(this.oButtonSelectSearchFields);
		this.add(this.oColWithTextFields);
		this.add(this.oButtonADDSearchField);
		this.add(this.oButtonStartSearch);
		this.add(this.oPopdownEinzelsuche);
		this.add(this.oButtonClearFieldsAndReload);
		
		/*
		 * das erste feld bauen
		 */
		this.add_SearchTextField();
		this.rebuild_SearchTextFieldColumn();
		
		//this.setBorder(new Border(new Extent(1),new E2_ColorDDark(),Border.STYLE_OUTSET));
		//this.setBackground(new E2_ColorLight());
		this.oColWithTextFields.setBackground(new E2_ColorLight());
		
		return this;
		
	}
	
	
	
	/*
	 * suche in einem speziellen feld
	 */
	private class ownActionSpecialSearch extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_SearchDefinition oSD = (E2_SearchDefinition)((MyE2_Button)oExecInfo.get_MyActionEvent().getSource()).EXT().get_O_PLACE_FOR_EVERYTHING();
			E2_DataSearch.this.restore_old_settings();
			E2_DataSearch.this.get_oSearchAgent().StartSearch(E2_DataSearch.this, oSD);
			E2_DataSearch.this.oPopdownEinzelsuche.setExpanded(false);
		}	
	}
	
	
	public void restore_old_settings()
	{
		
		if (this.bFirstStart)
		{
			try
			{
				new E2_UserSetting_SearchFields().restore_SearchList(this, this.cMODULE_KENNER);
				this.bFirstStart = false;
			} 
			catch (myException e)
			{
				bibMSG.add_MESSAGE(e.get_ErrorMessage());
				e.printStackTrace();
			}
		}
	}
	
	

	/**
	 * suchfeld fuer suche in einem maskefeld der haupt-tabelle
	 */
	public void add_SearchElement(MyE2IF__DB_Component oDBComponent) throws myException
	{
		
		if ( (oDBComponent == null))
			throw new myException("MyE2_DataSearch:add_SearchElement:Null-Component not allowed!");

		
		MyE2EXT__DB_Component oEXT_DB = oDBComponent.EXT_DB();

		if ( !(oDBComponent instanceof MyE2IF__Component))
			throw new myException("MyE2_DataSearch:add_SearchElement:Must be a component of type MyE2IF__Component !");
		
		if (bibALL.isEmpty(oEXT_DB.get_oSQLField().get_cTableName()) || bibALL.isEmpty(oEXT_DB.get_oSQLField().get_cFieldName()))
			throw new myException("MyE2_DataSearch:add_SearchElement:Must be a basic Field, not a query-field");

		if (! oEXT_DB.get_oSQLField().get_cTableName().equals(this.cBaseTableName))
			throw new myException("MyE2_DataSearch:add_SearchElement:DataComponent must be of Base-Table: "+this.cBaseTableName);

		
        String cWhereBlock = this.makeWhereBlock(oEXT_DB);

		String cQuery =  "SELECT " +this.cBaseTableName+"."+this.cKeyNameBaseTable+
                                " FROM "+bibALL.get_TABLEOWNER()+"."+this.cBaseTableName+" WHERE "+ cWhereBlock ;

		E2_SearchDefinition oSD = new E2_SearchDefinition(true,cQuery,null,(MyE2IF__Component)oDBComponent,  oDBComponent.EXT_DB().get_oSQLFieldMAP());
		this.vSearchDefinitions.add(oSD);
		this.add_SearchdefToPopup(oSD);
	}
	

	
	/**
	 * zufuegen eines freien suchfeldes
	 */
	public void add_SearchElement(String cSQLQuery,MyString cTextForList) throws myException
	{
		E2_SearchDefinition oSD = new E2_SearchDefinition(true,cSQLQuery,cTextForList,null, null);
		this.vSearchDefinitions.add(oSD);
		this.add_SearchdefToPopup(oSD);
	}

	
	/**
	 * 2013-06-27: weitere searchdefinition: mit sqlfieldmap
	 */
	public void add_SearchElement(String cSQLQuery,MyString cTextForList, SQLFieldMAP oSQLFieldMap) throws myException
	{
		E2_SearchDefinition oSD = new E2_SearchDefinition(true,cSQLQuery,cTextForList,null, oSQLFieldMap);
		this.vSearchDefinitions.add(oSD);
		this.add_SearchdefToPopup(oSD);
	}
	
	
	
	/**
	 * zufuegen eines freien suchfeldes
	 */
	public void add_SearchElement(String cSQLQuery,MyString cTextForList, boolean bOnlySingleSearch) throws myException
	{
		E2_SearchDefinition oSD = new E2_SearchDefinition(true,cSQLQuery,cTextForList,null, null);
		this.vSearchDefinitions.add(oSD);
		this.add_SearchdefToPopup(oSD);
		oSD.set_bOnlySingleSearch(bOnlySingleSearch);
		
	}

	
	/**
	 * zufuegen eines searchdefs 
	 */
	public void add_SearchElement(E2_SearchDefinition oSD) throws myException
	{
		this.vSearchDefinitions.add(oSD);
		this.add_SearchdefToPopup(oSD);
	}
	
	
	
	/**
	 * 
	 * @param cTable
	 * @param cDatumField
	 * @param cText4List
	 * @throws myException
	 */
	public void add_DatumsSuchFeld(String cTable, String cDatumField, MyString cText4List) throws myException
	{
		String cID_Field = "ID_"+cTable.substring(3);
		
		String cSQL = "SELECT "+cTable+"."+cID_Field+ " FROM "+bibE2.cTO()+"."+cTable+" WHERE TO_CHAR("+cDatumField+",'DD.MM.YYYY') LIKE '%#WERT#%'";
		
		this.add_SearchElement(cSQL, cText4List);
		
	}
	
	
	private void add_SearchdefToPopup(E2_SearchDefinition oSD)
	{
		MyE2_Button  oButSearch = new MyE2_Button(oSD.get_cTextForList());
		oButSearch.EXT().set_O_PLACE_FOR_EVERYTHING(oSD);
		oButSearch.add_oActionAgent(new ownActionSpecialSearch());
		this.oPopdownEinzelsuche.addButton(oButSearch, true);
	}
	
	private String makeWhereBlock(MyE2EXT__DB_Component oEXT_DB) throws myException
	{
	       	String cWhereBlock = "";
	        String cFieldName = oEXT_DB.get_oSQLField().get_cTableName()+"."+oEXT_DB.get_oSQLField().get_cFieldName();
			if (oEXT_DB.get_oSQLField().get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_TEXT))
			{
			    cWhereBlock =  "UPPER(RTRIM("+cFieldName+")) LIKE UPPER('%#WERT#%')";
			}
			else if (oEXT_DB.get_oSQLField().get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
			{
			    cWhereBlock = "TO_CHAR("+cFieldName+") = '#WERT#'";
			}
			else if (oEXT_DB.get_oSQLField().get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
			{
				String cWhereBlock1 = "TO_CHAR("+cFieldName+",'DD.MM.YYYY') LIKE '%#WERT#%' ";
				String cWhereBlock2 = "TO_CHAR("+cFieldName+",'DDMMYYYY') = '#WERT#' ";
				String cWhereBlock3 = "TO_CHAR("+cFieldName+",'DDMMYY') = '#WERT#' ";
			    cWhereBlock= "("+cWhereBlock1+" OR "+cWhereBlock2+" OR "+cWhereBlock3+")";
			}
			else
			{
			    throw new myException("mySearchDefinition:makeWhereBlock:  Field-Types "+oEXT_DB.get_oSQLField().get_oFieldMetaDef().get_cBASIC_Field_TYPE()+" is not allowed !");
			}
			
			return cWhereBlock;
	}
	
	//2011-12-06: statt privat public
	public void add_SearchTextField()
	{
		this.vSearchTextFields.add(new MyE2_TextField("",this.m_widthSearchText,100,new MyE2_String("Eingaben: Einzelne Worte werden so behandelt, daß ein gefundener Datensatz in den verwendeten Suchfeldern jedes der Worte mindestens 1 mal enthalten muss. \nWerden die Worte in Anführungszeichen gesetzt, dann wird der Suchbegriff wie ein einziges Wort behandelt.")));
	}
	
	//2011-12-06: statt privat public
	public void rebuild_SearchTextFieldColumn()
	{
		this.oColWithTextFields.removeAll();
		for (int i=0;i<this.vSearchTextFields.size();i++)
		{
			this.oColWithTextFields.add((MyE2_TextField)this.vSearchTextFields.get(i));
		}
	}


	public Vector<MyE2_TextField> 				get_vSearchFields()			{		return vSearchTextFields;	}
	public Vector<E2_SearchDefinition> 			get_vSearchDefinitions()	{		return this.vSearchDefinitions;	}
	public E2_DataSearchAgentAbstract 			get_oSearchAgent()			{		return oSearchAgent;	}
	public void 								set_oSearchAgent(E2_DataSearchAgentAbstract searchAgent)	{		oSearchAgent = searchAgent;	}
	
	
	
	public void set_SearchButtonsEnabled(boolean bEnabled) throws myException
	{
		this.oButtonADDSearchField.set_bEnabled_For_Edit(bEnabled);
		this.oButtonClearFieldsAndReload.set_bEnabled_For_Edit(bEnabled);
		this.oButtonStartSearch.set_bEnabled_For_Edit(false);
		this.oButtonSelectSearchFields.set_bEnabled_For_Edit(false);
	}
	
	
	public void DO_EnableSearchFieldWithLabelText(String cText,boolean bIsSelected)
	{
		for (int i=0;i<this.vSearchDefinitions.size();i++)
		{
			E2_SearchDefinition oSD = (E2_SearchDefinition)this.vSearchDefinitions.get(i);
			if (oSD.get_cOrigTextLabel().toUpperCase().trim().equals(cText.toUpperCase().trim()))
				oSD.get_oCheckIsEnabled().setSelected(bIsSelected);
		}
	}


	public String get_cMODULE_KENNER()
	{
		return cMODULE_KENNER;
	}
	
	
	public Vector<E2_SearchDefinition> get_SelectedSearchDefs()
	{
		Vector<E2_SearchDefinition> vRueck = new Vector<E2_SearchDefinition>();
		for (E2_SearchDefinition oSD: this.vSearchDefinitions)
		{
			if (oSD.get_oCheckIsEnabled().isSelected())
			{
				vRueck.add(oSD);
			}
		}
		return vRueck;
	}
	
	
	
	public MyE2_Button get_oButtonADDSearchField()
	{
		return oButtonADDSearchField;
	}



	public MyE2_Button get_oButtonStartSearch()
	{
		return oButtonStartSearch;
	}



	public MyE2_Button get_oButtonClearFieldsAndReload()
	{
		return oButtonClearFieldsAndReload;
	}



	public E2_DataSearchButtonToSelectSearchFields get_oButtonSelectSearchFields()
	{
		return oButtonSelectSearchFields;
	}



	public MyE2_PopMiddleMenue get_oPopdownEinzelsuche()
	{
		return oPopdownEinzelsuche;
	}
	
	
	//2012-10-26: die moeglichkeit einer komplett definierte datasearch-einheit eine zusatzbedinung aufzudruecken
	//            z.B. es duerfen in der fuhrenbeanstandung keine normalen bams auftauchen
	public void add_XX_SearchAddonBedingung_to_all_SearchDefinitions(XX_SearchAddonBedingung oAddonSearchDef)
	{
		for (E2_SearchDefinition oSearchDef: this.vSearchDefinitions)
		{
			oSearchDef.set_oSearchAddon(oAddonSearchDef);
		}
	}
	
	
	
	/**
	 * 20180523: fuegt die benutzerdefinierten searchdefs aus der datentabelle JT_SEARCHDEF hinzu
	 */
	public void initAfterConstruction() {
		this.addSearchdefsFromDB();
	}
	

	
	private void addSearchdefsFromDB() {
		Vector<E2_SearchDefinition>	v_searchDefinitions_DB = new Vector<E2_SearchDefinition>();
		
		
		try {
			if (S.isFull(this.get_cMODULE_KENNER())) {
				SEL sel = new SEL("*").FROM(_TAB.searchdef).WHERE(new vgl(SEARCHDEF.modulkenner,this.cMODULE_KENNER)).AND(new vgl_YN(SEARCHDEF.aktiv, true)).ORDERUP(SEARCHDEF.user_text);
				RecList21 rl = new RecList21(_TAB.searchdef)._fill(sel.s());
				
				if (rl != null && rl.size()>0) {
					for (Rec21 r: rl) {
						v_searchDefinitions_DB.add(new E2_SearchDefinition(true,r.get_ufs_dbVal(SEARCHDEF.sqlblock), new MyE2_String("* " +r.get_ufs_dbVal(SEARCHDEF.user_text),false),null,null));
					}
				}
			}
			
		} catch (myException e) {
			e.printStackTrace();
			v_searchDefinitions_DB.clear();     //falls ein fehler auftaucht, dann passiert nix
		}
		
		//jetzt anhaengen
		for (E2_SearchDefinition sd: v_searchDefinitions_DB) {
			this.vSearchDefinitions.add(sd);
			this.add_SearchdefToPopup(sd);
		}

	}



	/**
	 * 
	 * @return left label (Daten durchsuchen)
	 */
	public MyE2_Label getLabelSuchBeschriftung() {
		return oLabelSuche;
	}



	public int getWidthSearchText() {
		return m_widthSearchText;
	}



	public E2_DataSearch _setWidthSearchText(int m_widthSearchText) {
		this.m_widthSearchText = m_widthSearchText;
		for (MyE2_TextField t: this.vSearchTextFields) {
			t.set_iWidthPixel(this.m_widthSearchText);
		}
		return this;
	}
	
	
	
}
