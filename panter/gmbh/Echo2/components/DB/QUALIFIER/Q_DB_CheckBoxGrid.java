package panter.gmbh.Echo2.components.DB.QUALIFIER;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_QUALIFIER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;



public abstract class Q_DB_CheckBoxGrid extends MyE2_Grid  implements MyE2IF__DB_Component, E2_IF_Copy
{
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	
	// in jeder implementierung dieser klasse muss eine liste moeglicher tags vorhanden seine
	// die als zuordnung erlaubt sind
	public abstract Vector<Q_DEF_Element>		build_vQ_DEF_Elements(MyE2IF__Component oComponentMother) throws myException;
	public abstract String    					get_cTABLE_NAME() throws myException;
	public abstract String  					get_cCLASS_KEY();

	
	//die copy-methode muss in jeder einzelnen abgeleiteten klasse separat angelegt werden
	public abstract Object get_Copy(Object objHelp) throws myExceptionCopy;


	//weitere methoden, um action-agenten und validierer den einzelnen checkboxen zuweisen zu koennen
	//dieser werden direkt im konstruktor ausgefuehrt
	public abstract HashMap<String, Vector<XX_ActionAgent>> get_hmZusatzActionAgents4Checkboxes(Q_DB_CheckBoxGrid oGrid) throws myException;
	public abstract HashMap<String, Vector<XX_ActionValidator>> get_hmZusatzGlobalValidators(Q_DB_CheckBoxGrid oGrid) throws myException;

	
	
	//weitere methode, die zur formatierung eingesetzt werden kann (damit einzelne Checkboxen hervorgehoben werden koennen
	public abstract void  Format_CB(Q_DEF_CheckBox  oFormatedCheckBox) throws myException;
	
	
	//hashmap mit schluessel=DATENBANKTAG
	private Q_HM_CheckBoxen						hmQ_DEF_CheckBoxen = new Q_HM_CheckBoxen();
	
	private Vector<Q_DEF_Element>				vQ_DEF_Elements = new Vector<Q_DEF_Element>();
	
	//weiterer vector mit vom selectAll ausgeschlossenen elementen
	private Vector<String>						vKeys_Not_To_Select_All = new Vector<String>();
	
	

	//checkbox-formatierer, wird benutzt vor dem aufbau der maske / des popup-fensters
	private Q__IF_FormatCheckBoxes  			oFormaterCheckBoxen = null;
	
	
	//falls der button in der liste verwendet wird, muss dieser parameter true sein,
	//dabei wird der speichervorgang separat abgewickelt
	private boolean  							bIsUsedInListMode = false;
	
	
	private GridLayoutData 					  	oGLayoutData4CheckboxesInGrid = null;
	
	
	private Font  							    oFont4CheckBoxes = null;
	
	
	
	//hilfsbutton, um alle elemente ein/aus schalten zu koennen
	private MyE2_CheckBox   					oCB_ALLE_EinAus =  new MyE2_CheckBox(); 
	
	
	private boolean  							bZeigeAllSchalterEinAus = false; 
	
	/*
	 * eine hashmap mit actionAgents, die eine jeweiligen checkbox zudefiniert werden, im moment des erzeugens
	 */
	private HashMap<String, Vector<XX_ActionAgent>>   hm_ZusatzActionAgents = null;

	/*
	 * eine hashmap mit actionAgents, die eine jeweiligen checkbox zudefiniert werden, im moment des erzeugens
	 */
	private HashMap<String, Vector<XX_ActionValidator>>   hm_ZusatzGlobalValidators = null;


	/**
	 * 
	 * komponente, die die qualifier in einem grid darstellt, direkt bearbeitbar
	 * @param oSQLField
	 * @param IsUsedInListMode
	 * @param iAnzahlCols
	 * @param oGridStyle
	 * @param hmActionAgents
	 * @param hmActionVaidators
	 * @throws myException
	 */
	public Q_DB_CheckBoxGrid(	SQLField  								oSQLField,  
								boolean 								IsUsedInListMode, 
								int 									iAnzahlCols, 
								MutableStyle 							oGridStyle,
								GridLayoutData 					        LayoutData4CheckboxesInGrid
								) throws myException
	{
		super(iAnzahlCols, oGridStyle);
		
		this.__set_Component(oSQLField, IsUsedInListMode, LayoutData4CheckboxesInGrid, new E2_FontPlain(),false);
		
	}

	
	public Q_DB_CheckBoxGrid(	SQLField  								oSQLField,  
								boolean 								IsUsedInListMode, 
								int 									iAnzahlCols, 
								MutableStyle 							oGridStyle,
								GridLayoutData 					        LayoutData4CheckboxesInGrid,
								Font   									oFont4Checkbox
								) throws myException
	{
		super(iAnzahlCols, oGridStyle);
		
		this.__set_Component(oSQLField, IsUsedInListMode, LayoutData4CheckboxesInGrid, oFont4Checkbox,false);
	}


	public Q_DB_CheckBoxGrid(	SQLField  								oSQLField,  
								boolean 								IsUsedInListMode, 
								int 									iAnzahlCols, 
								MutableStyle 							oGridStyle,
								GridLayoutData 					        LayoutData4CheckboxesInGrid,
								Font   									oFont4Checkbox,
								boolean  								ZeigeAllSchalterEinAus
								) throws myException
	{
		super(iAnzahlCols, oGridStyle);
	
		this.__set_Component(oSQLField, IsUsedInListMode, LayoutData4CheckboxesInGrid, oFont4Checkbox,ZeigeAllSchalterEinAus);
	}

	
	
	private void __set_Component(	SQLField  								oSQLField,  
									boolean 								IsUsedInListMode, 
									GridLayoutData 					        LayoutData4CheckboxesInGrid,
									Font  									oFont,
									boolean   								ZeigeAllSchalterEinAus) throws myException
	{
		
		this.EXT_DB().set_oSQLField(oSQLField);
		this.bIsUsedInListMode = IsUsedInListMode;
		this.oGLayoutData4CheckboxesInGrid = LayoutData4CheckboxesInGrid;
		
		this.bZeigeAllSchalterEinAus = ZeigeAllSchalterEinAus;
		
		this.oFont4CheckBoxes = oFont;
		
		if (this.oGLayoutData4CheckboxesInGrid == null)
		{
			this.oGLayoutData4CheckboxesInGrid=MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_0);
		}
		
		this.vQ_DEF_Elements.addAll(this.build_vQ_DEF_Elements(this));
		this.hm_ZusatzActionAgents = 		this.get_hmZusatzActionAgents4Checkboxes(this);
		this.hm_ZusatzGlobalValidators = 	this.get_hmZusatzGlobalValidators(this);
		
		this.oCB_ALLE_EinAus.setToolTipText(new MyE2_String("Alle Werte ein-/ausschalten").CTrans());
		
		this.build_hm_With_CheckBoxes();

		Vector<Q_DEF_CheckBox>  vCheckBoxen = this.hmQ_DEF_CheckBoxen.get_vSortedVectorCheckboxes();

		for (int i=0;i<vCheckBoxen.size();i++)
		{
			this.add(vCheckBoxen.get(i),this.oGLayoutData4CheckboxesInGrid);
			
			//einen dummy-actionagenten immer anfuegen, damit eine evtl. validierung funktionert
			vCheckBoxen.get(i).add_oActionAgent(new actionDummy());
			
			if (this.bIsUsedInListMode)
			{
				vCheckBoxen.get(i).add_oActionAgent(new actionDirectStoreCheckbox(vCheckBoxen.get(i).get_oDefElement().get_cDB_DATENBANKTAG()));
			}
		}
		
		if (this.bZeigeAllSchalterEinAus)
		{
			MyE2_Grid oGridHelp = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			oGridHelp.add_raw(new MyE2_Label("("), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_0));
			oGridHelp.add_raw(this.oCB_ALLE_EinAus, MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_0));
			oGridHelp.add_raw(new MyE2_Label(")"), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_0));
			
			this.add(oGridHelp,this.oGLayoutData4CheckboxesInGrid);
			
			this.oCB_ALLE_EinAus.add_oActionAgent(new actionSetAllAnAus());
			
			if (this.bIsUsedInListMode)
			{
				this.oCB_ALLE_EinAus.add_oActionAgent(new actionDirectStoreWhenAllCB_IS_Selected());
			}
			
		}
		
		
		if (this.hm_ZusatzActionAgents!=null)
		{
			Iterator<String>  oIter = this.hmQ_DEF_CheckBoxen.keySet().iterator();
			
			while (oIter.hasNext())
			{
				String cKey = oIter.next();
				
				if (this.hm_ZusatzActionAgents.get(cKey)!=null)
				{
					this.hmQ_DEF_CheckBoxen.get(cKey).add_oActionAgent(this.hm_ZusatzActionAgents.get(cKey),false);
				}
			}
		}
		
		
		if (this.hm_ZusatzGlobalValidators!=null)
		{
			Iterator<String>  oIter = this.hmQ_DEF_CheckBoxen.keySet().iterator();
			
			while (oIter.hasNext())
			{
				String cKey = oIter.next();
				
				if (this.hm_ZusatzGlobalValidators.get(cKey)!=null)
				{
					this.hmQ_DEF_CheckBoxen.get(cKey).add_GlobalValidator(this.hm_ZusatzGlobalValidators.get(cKey));
				}
			}
		}
		
		
		
		//zu schluss noch den formatierer drueberlaufenlassen
		for (int i=0;i<vCheckBoxen.size();i++)
		{
			this.Format_CB(vCheckBoxen.get(i));
		}

		
	}


	/*
	 * in jedem konstructor muss zuerst ein vector mit leeren checkboxen definiert werden
	 */
	public void build_hm_With_CheckBoxes() throws myException
	{
		for (int i=0;i<this.vQ_DEF_Elements.size();i++)
		{
			this.hmQ_DEF_CheckBoxen.put(this.vQ_DEF_Elements.get(i).get_cDB_DATENBANKTAG(),new Q_DEF_CheckBox(this.vQ_DEF_Elements.get(i), this.oFont4CheckBoxes));
		}
	}
	
	
	/**
	 * Methode um einen bestimmtem key zu setzen
	 * @param cDB_HASH_KEY
	 * @param bSelected
	 */
	public void set_selected(String cDB_HASH_KEY, boolean bSelected) throws myException
	{
		if (this.hmQ_DEF_CheckBoxen.get(cDB_HASH_KEY) != null)
		{
			this.hmQ_DEF_CheckBoxen.get(cDB_HASH_KEY).setSelected(bSelected);
		}
		else
		{
			throw new myException(cDB_HASH_KEY+"-Checkbox was not found !");
		}
	}
	
	
	/**
	 * 2013-11-20: pruefen, ob ein bestimmter Hashkey gesetzt ist
	 */
	public boolean IS_Checked(String cDB_HASH_KEY) {
		boolean bRueck = false;
		
		if (this.hmQ_DEF_CheckBoxen.get(cDB_HASH_KEY)!=null) {
			if (this.hmQ_DEF_CheckBoxen.get(cDB_HASH_KEY).isSelected()) {
				bRueck=true;
			}
		}
		
		return bRueck;
	}
	
	
	/**
	 * 2013-11-20: alle ein/ausschaltbar machen
	 */
	public void SET_ALL_CheckBoxes(boolean bSelected) {
		for (Q_DEF_CheckBox oCB: this.hmQ_DEF_CheckBoxen.values()) {
			oCB.setSelected(bSelected);
		}
	}
	
	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		//alle checkboxen auf aus schalten
		Iterator<Q_DEF_CheckBox>  oIter = this.hmQ_DEF_CheckBoxen.values().iterator();
		
		while (oIter.hasNext()) {  oIter.next().setSelected(false);    }
	}


	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cID_from_Master, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		String cTABLE_ID_UF = this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_cStringForDataBase(cID_from_Master, true, false);

		this.setze_status_der_CheckBoxen_aus_DB(cTABLE_ID_UF);
		
	}

	
	
	public void setze_status_der_CheckBoxen_aus_DB(String cID_TABLE) throws myException
	{
		
		//erstens muessen die eintraege im vQ_DEF_CheckBoxen definiert werden
		RECLIST_QUALIFIER  recList = this.get_RECLIST_QUALIFIER(cID_TABLE);
		
		//zuerst alle checkboxen auf anfang:
		Iterator<Q_DEF_CheckBox> oIter = this.hmQ_DEF_CheckBoxen.values().iterator();
		while (oIter.hasNext())
		{
			oIter.next().set_RecQualifier(null);
		}
		
		//damit stehen die Qualifier fest, die einen eintrag haben
		for (int i=0;i<recList.get_vKeyValues().size();i++)
		{
			for (Q_DEF_Element oElement: this.vQ_DEF_Elements)
			{
				if (oElement.get_cDB_DATENBANKTAG().equals(recList.get(i).get_DATENBANKTAG_cUF_NN("")))
				{
					if (this.hmQ_DEF_CheckBoxen.get(oElement.get_cDB_DATENBANKTAG())==null)
					{
						throw new myException(this,"Error : can not find Checkbox for TAG:"+recList.get(i).get_DATENBANKTAG_cUF_NN(""));
					}
					this.hmQ_DEF_CheckBoxen.get(oElement.get_cDB_DATENBANKTAG()).set_RecQualifier(recList.get(i));
					
				}
			}
		}

	}
	
	
	private class actionDummy extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
		}
	}
	
	
	
	private class actionDirectStoreCheckbox extends XX_ActionAgent
	{
		private String cKey= null;
		
		public actionDirectStoreCheckbox(String Key)
		{
			super();
			this.cKey = Key;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Q_DB_CheckBoxGrid oThis = Q_DB_CheckBoxGrid.this;
			
			String cID_ROW = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			HashMap<String, String>  hmSQLs = oThis.get_hmSQL_Stack(cID_ROW);
			
			String cSQL = hmSQLs.get(this.cKey);
			
			if (S.isEmpty(cSQL))    //das duerfte nicht passieren, da jeder click auf eine checkbox diese veraendert
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Keine Aenderung erkannt !!!"));
			}
			else
			{
				MyE2_MessageVector oMV = bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector(cSQL), true);
				
				if (oMV.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Status gespeichert !!!"));
				}
			}
		}
	}
	

	
	private class actionSetAllAnAus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			boolean bAnAus=Q_DB_CheckBoxGrid.this.oCB_ALLE_EinAus.isSelected();
			//jetzt die hm mit den checkboxen durchsuchen und nachsehen, was sich veraendert hat
			Iterator<String>  oIter = Q_DB_CheckBoxGrid.this.hmQ_DEF_CheckBoxen.keySet().iterator();
		    while (oIter.hasNext())
		    {
		    	String cKEY = oIter.next();
		    	
		    	if (!Q_DB_CheckBoxGrid.this.vKeys_Not_To_Select_All.contains(cKEY)) {
		    		Q_DB_CheckBoxGrid.this.hmQ_DEF_CheckBoxen.get(cKEY).setSelected(bAnAus);
		    	}
		    }
		}
	}
	
	
	
	private class actionDirectStoreWhenAllCB_IS_Selected extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Q_DB_CheckBoxGrid oThis = Q_DB_CheckBoxGrid.this;
			
			String cID_ROW = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			HashMap<String, String>  hmSQLs = oThis.get_hmSQL_Stack(cID_ROW);

			Vector<String> vSQL = new Vector<String>();
			vSQL.addAll(hmSQLs.values());
			
			
			if (vSQL.size()==0)    //das duerfte nicht passieren, da jeder click auf eine checkbox diese veraendert
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Keine Aenderung erkannt !!!"));
			}
			else
			{
				MyE2_MessageVector oMV = bibDB.ExecMultiSQLVector(vSQL, true);
				
				if (oMV.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Status gespeichert !!!"));
				}
			}
		}
	}
	

	
	
	
	@Override
	public String get_cActualMaskValue() throws myException
	{
		return null;
	}

	@Override
	public String get_cActualDBValueFormated() throws myException
	{
		return null;
	}

	@Override
	public void set_cActualMaskValue(String cText) throws myException
	{
	}


	@Override
	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	@Override
	public boolean get_bIsComplexObject()
	{
		return true;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
	    String cHauptIDFormated = this.EXT_DB().get_oSQLField().get_cNewValueFormated();
	    String cNewHauptIDUnformated = this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_cStringForDataBase(cHauptIDFormated, true, false);
	    
		return bibALL.get_vBuildValueVectorFromHashmap(this.get_hmSQL_Stack(cNewHauptIDUnformated));
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return bibALL.get_vBuildValueVectorFromHashmap(this.get_hmSQL_Stack(oE2_ComponentMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()));
	}

	
	/**
	 * 
	 * @param cID_MAIN_TABLE
	 * @param cHASH   (can be null, when only one checkbox is updated)
	 * @return
	 * @throws myException
	 */
	private HashMap<String,String>  get_hmSQL_Stack(String cID_MAIN_TABLE) throws myException
	{
		HashMap<String,String> hmSQLs = new HashMap<String,String>();

	    
		//jetzt die hm mit den checkboxen durchsuchen und nachsehen, was sich veraendert hat
		Iterator<String>  oIter = this.hmQ_DEF_CheckBoxen.keySet().iterator();
	    while (oIter.hasNext())
	    {
	    	String cKey = oIter.next();
	    	
	    	Q_DEF_CheckBox oCB = this.hmQ_DEF_CheckBoxen.get(cKey);
	    	
	    	if (oCB.get_bSelected_new() && !oCB.get_bSelected_old())
	    	{
	    		RECORDNEW_QUALIFIER  recNew = new RECORDNEW_QUALIFIER();

	    		recNew.set_NEW_VALUE_CLASS_KEY(this.get_cCLASS_KEY());
	    		recNew.set_NEW_VALUE_TABLENAME(Q_DB_CONST.get_TABLE_NAME(this.get_cTABLE_NAME()));
	    		recNew.set_NEW_VALUE_ID_TABLE(cID_MAIN_TABLE);
	    		recNew.set_NEW_VALUE_DATENBANKTAG(oCB.get_oDefElement().get_cDB_DATENBANKTAG());
	    		
	    		hmSQLs.put(cKey,recNew.get_InsertSQLStatementWith_Id_Field(true, true));
	    	}
	    	else if (!oCB.get_bSelected_new() && oCB.get_bSelected_old())
	    	{
	    		hmSQLs.put(cKey,oCB.get_RecQualifier().get_DELETE_STATEMENT());
	    	}
	    }
	    
		return hmSQLs;
	}
	
	
	public MyE2EXT__DB_Component EXT_DB()							{		return this.oEXTDB;		}
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)			{		this.oEXTDB = oEXT_DB;	}


	public Q__IF_FormatCheckBoxes get_oFormaterCheckBoxen()
	{
		return oFormaterCheckBoxen;
	}


	public void set_oFormaterCheckBoxen(Q__IF_FormatCheckBoxes oFormaterCheckBoxen)
	{
		this.oFormaterCheckBoxen = oFormaterCheckBoxen;
	}


	public boolean get_bIsUsedInListMode()
	{
		return bIsUsedInListMode;
	}


	public void set_bIsUsedInListMode(boolean bIsUsedInListMode)
	{
		this.bIsUsedInListMode = bIsUsedInListMode;
	}

	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		Iterator<Q_DEF_CheckBox>  oIter = this.hmQ_DEF_CheckBoxen.values().iterator();
		while (oIter.hasNext())
		{
			Q_DEF_CheckBox  oCB = oIter.next();
			
			if (!this.bIsUsedInListMode)
			{
				oCB.set_bEnabled_For_Edit(enabled);
			}
		}
		
		if (!this.bIsUsedInListMode)
		{
			this.oCB_ALLE_EinAus.set_bEnabled_For_Edit(enabled);
		}
		
		
	}
	
	
	public Q_HM_CheckBoxen get_hmQ_DEF_CheckBoxen()
	{
		return hmQ_DEF_CheckBoxen;
	}

	
	public Vector<String>  get_vAllSelectedMailTags() {
		Vector<String>  vRueck = new Vector<String>();
		
		for (String cMAILTAG: this.hmQ_DEF_CheckBoxen.keySet()) {
			
			if (this.hmQ_DEF_CheckBoxen.get(cMAILTAG).isSelected()) {
				vRueck.add(cMAILTAG);
			}
		}
		
		return vRueck;
 	}
	
	
	
	public Q_DEF_CheckBox get_oCB(String cHash)
	{
		return this.get_hmQ_DEF_CheckBoxen().get(cHash);
	}
	
	
	public GridLayoutData get_oGLayoutData4CheckboxesInGrid()
	{
		return oGLayoutData4CheckboxesInGrid;
	}
	
	public Font get_oFont4CheckBoxes()
	{
		return oFont4CheckBoxes;
	}
	
	public void set_oFont4CheckBoxes(Font oFont4CheckBoxes)
	{
		this.oFont4CheckBoxes = oFont4CheckBoxes;
	}

	
	
	public RECLIST_QUALIFIER  get_RECLIST_QUALIFIER(String cID_TABLE) throws myException
	{
		RECLIST_QUALIFIER recListRueck = null;
		
		String cQuery = "SELECT * FROM "+bibE2.cTO()+".JT_QUALIFIER WHERE "
												+RECORD_QUALIFIER.FIELD__CLASS_KEY+"="+bibALL.MakeSql(this.get_cCLASS_KEY())
												+" AND "
												+RECORD_QUALIFIER.FIELD__TABLENAME+"="+bibALL.MakeSql(Q_DB_CONST.get_TABLE_NAME(this.get_cTABLE_NAME()))
												+" AND "
												+RECORD_QUALIFIER.FIELD__ID_TABLE+"="+cID_TABLE;
		
		if (S.isEmpty(cID_TABLE))
		{
			throw new myException(this,"ID_TABLE must NOT be empty !!!");
		}
		
		recListRueck = new RECLIST_QUALIFIER(cQuery);
		
		return recListRueck;
		
	}

	
	
	public HashMap<String,MyE2_String> get_HM_DB_TAGS___VS___USERTEXT_LONG() throws myException
	{
		HashMap<String,MyE2_String>  hmRueck = new HashMap<String, MyE2_String>();
		
		Vector<Q_DEF_Element>	vQ_defs = build_vQ_DEF_Elements(null);
		
		
		for (Q_DEF_Element oQDef: vQ_defs)
		{
			hmRueck.put(oQDef.get_cDB_DATENBANKTAG(), oQDef.get_cLONG_TEXT_4_USER());
		}
		
		return hmRueck;
	}

	public HashMap<String,MyE2_String> get_HM_DB_TAGS___VS___USERTEXT_SHORT() throws myException
	{
		HashMap<String,MyE2_String>  hmRueck = new HashMap<String, MyE2_String>();
		
		Vector<Q_DEF_Element>	vQ_defs = build_vQ_DEF_Elements(null);
		
		
		for (Q_DEF_Element oQDef: vQ_defs)
		{
			hmRueck.put(oQDef.get_cDB_DATENBANKTAG(), oQDef.get_cSHORT_TEXT_4_USER());
		}
		
		return hmRueck;
	}


	

	public Vector<String> get_vKeys_Not_To_Select_All() {
		return vKeys_Not_To_Select_All;
	}

	
	
	
}
