package panter.gmbh.Echo2.components.DB.QUALIFIER;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_QUALIFIER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



public abstract class Q_DB_Button extends MyE2_Button  implements MyE2IF__DB_Component, E2_IF_Copy
{
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	
	//in jeder klasse eine eigene modulcontainer-classe definieren wegen des speicherns der groesse
	public abstract E2_BasicModuleContainer  get_oModuleContainer() throws myException;

	
	// in jeder implementierung dieser klasse muss eine liste moeglicher tags vorhanden seine
	// die als zuordnung erlaubt sind
	public abstract Vector<Q_DEF_Element>		build_vQ_DEF_Elements(MyE2IF__Component oComponentMother) throws myException;
	public abstract String    					get_cTABLE_NAME() throws myException;
	public abstract String  					get_cCLASS_KEY();
	
	
	//die copy-methode muss in jeder einzelnen abgeleiteten klasse separat angelegt werden
	public abstract Object get_Copy(Object objHelp) throws myExceptionCopy;

	
	
	//hashmap mit schluessel=DATENBANKTAG
	private Q_HM_CheckBoxen						hmQ_DEF_CheckBoxen = new Q_HM_CheckBoxen();
	
	private Vector<Q_DEF_Element>				vQ_DEF_Elements = new Vector<Q_DEF_Element>();
	
	//checkbox-formatierer, wird benutzt vor dem aufbau der maske / des popup-fensters
	private Q__IF_FormatCheckBoxes  			oFormaterCheckBoxen = null;
	
	
	//falls der button in der liste verwendet wird, muss dieser parameter true sein,
	//dabei wird der speichervorgang separat abgewickelt
	private boolean  							bIsUsedInListMode = false;
	
	private MyE2_String  						cButtonLabel = null;
	private MyE2_String 						cLabel4Popup = null;
	
	//ein paar einstellungs-definitionen
	private int 								iNumberCheckboxRowsInPopup = 5; 	
	private MutableStyle   						oStyle4Grid = MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100();
	private Insets  							oInsets4Grid = E2_INSETS.I_2_2_2_2;
	
	
	private MyE2_String   						cButtonTextSave = 			new MyE2_String("Speichern");
	private MyE2_String   						cButtonTextUebernehmen = 	new MyE2_String("Übernehmen in Maske");
	private MyE2_String   						cButtonTextAbbruch = 		new MyE2_String("Abbruch und gespeicherten Wert wiederherstellen");
	
	
	public Q_DB_Button(MyE2_String ButtonLabel,MyE2_String Label4Popup, SQLField  oSQLField,  boolean IsUsedInListMode) throws myException
	{
		super(ButtonLabel);
		
		this.setLineWrap(true);
		
		this.cButtonLabel = ButtonLabel;
		this.cLabel4Popup = Label4Popup;
		
		this.EXT_DB().set_oSQLField(oSQLField);
		this.bIsUsedInListMode = IsUsedInListMode;
		this.vQ_DEF_Elements.addAll(this.build_vQ_DEF_Elements(this));
		this.build_hm_With_CheckBoxes();
		
		this.add_oActionAgent(new ownActionAgentOpenPopup());
		
	}



	/*
	 * in jedem konstructor muss zuerst ein vector mit leeren checkboxen definiert werden
	 */
	public void build_hm_With_CheckBoxes() throws myException
	{
		for (int i=0;i<this.vQ_DEF_Elements.size();i++)
		{
			this.hmQ_DEF_CheckBoxen.put(this.vQ_DEF_Elements.get(i).get_cDB_DATENBANKTAG(),new Q_DEF_CheckBox(this.vQ_DEF_Elements.get(i),new E2_FontPlain()));
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
		
		this.setText(this.generate_ButtonBeschriftung().CTrans());
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
	    
		return this.get_vSQL_Stack(cNewHauptIDUnformated);
	}

	
	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return this.get_vSQL_Stack(oE2_ComponentMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
	}

	
	public Vector<String>  get_vSQL_Stack(String cID_MAIN_TABLE) throws myException
	{
		Vector<String> vSQLs = new Vector<String>();

		//jetzt die hm mit den checkboxen durchsuchen und nachsehen, was sich veraendert hat
		Iterator<Q_DEF_CheckBox>  oIter = this.hmQ_DEF_CheckBoxen.values().iterator();
	    while (oIter.hasNext())
	    {
	    	Q_DEF_CheckBox oCB = oIter.next();
	    	
	    	if (oCB.get_bSelected_new() && !oCB.get_bSelected_old())
	    	{
	    		RECORDNEW_QUALIFIER  recNew = new RECORDNEW_QUALIFIER();

	    		recNew.set_NEW_VALUE_CLASS_KEY(this.get_cCLASS_KEY());
	    		recNew.set_NEW_VALUE_TABLENAME(Q_DB_CONST.get_TABLE_NAME(this.get_cTABLE_NAME()));
	    		recNew.set_NEW_VALUE_ID_TABLE(cID_MAIN_TABLE);
	    		recNew.set_NEW_VALUE_DATENBANKTAG(oCB.get_oDefElement().get_cDB_DATENBANKTAG());
	    		
	    		vSQLs.add(recNew.get_InsertSQLStatementWith_Id_Field(true, true));
	    	}
	    	else if (!oCB.get_bSelected_new() && oCB.get_bSelected_old())
	    	{
	    		vSQLs.add(oCB.get_RecQualifier().get_DELETE_STATEMENT());
	    	}
	    }
	    
		return vSQLs;
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

	
	
	private class ownActionAgentOpenPopup extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Q_DB_Button   oThis = Q_DB_Button.this;
			
			//vector zum sortieren erfinden
			Vector<Q_DEF_CheckBox>  vCheckBoxen = oThis.hmQ_DEF_CheckBoxen.get_vSortedVectorCheckboxes();

		    MyE2_Grid  oGridAussen = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		    
		    MyE2_Grid  oGrid = new MyE2_Grid(oThis.iNumberCheckboxRowsInPopup,oThis.oStyle4Grid);
		    
		    for (Q_DEF_CheckBox oCB: vCheckBoxen)
		    {
		    	oGrid.add(oCB, oThis.oInsets4Grid);
		    }

		    E2_BasicModuleContainer oContainer = oThis.get_oModuleContainer();
		    
		    
		    oGridAussen.add(oGrid,E2_INSETS.I_0_0_0_0);
		    
		    if (oThis.bIsUsedInListMode)    //mit direktem speichern
		    {
		    	MyE2_Button  oButtonCloseAndSave = 	new MyE2_Button(oThis.cButtonTextSave, new MyE2_String("Speichern und Maske schliessen"), new ownActionAgentSave_and_ClosePopup(oContainer));
		    	MyE2_Button  oButtonAbbruch = 		new MyE2_Button(oThis.cButtonTextAbbruch, new MyE2_String("Abbruch und Maske schliessen"), new ownActionAgentAbbruch(oContainer));
		    	oGridAussen.add(new E2_ComponentGroupHorizontal(0, oButtonCloseAndSave, oButtonAbbruch, new Insets(0,0,20,0)), E2_INSETS.I_0_10_0_0);
		    }
		    else
		    {
		    	MyE2_Button  oButtonUebernehmen = 	new MyE2_Button(oThis.cButtonTextUebernehmen, new MyE2_String("Übernehmen und Maske schliessen"), new ownActionAgentUebernehmenSchliessen(oContainer));
		    	MyE2_Button  oButtonAbbruch = 		new MyE2_Button(oThis.cButtonTextAbbruch, new MyE2_String("Abbruch und Maske schliessen, die Werte aus der Datenbank werden wiederhergestellt"), new ownActionAgentAbbruch(oContainer));
		    	oGridAussen.add(new E2_ComponentGroupHorizontal(0, oButtonUebernehmen, oButtonAbbruch, new Insets(0,0,20,0)), E2_INSETS.I_0_10_0_0);
		    }
		    
		    
		    
		    oContainer.add(oGridAussen,E2_INSETS.I_5_5_5_5);
		    oContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(600), oThis.cLabel4Popup);
		}
		
	}

	
	
	private class ownActionAgentUebernehmenSchliessen extends XX_ActionAgent
	{
		private E2_BasicModuleContainer  oContainer2Close = null;

		public ownActionAgentUebernehmenSchliessen(E2_BasicModuleContainer Container2Close)
		{
			super();
			this.oContainer2Close = Container2Close;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			this.oContainer2Close.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			
			//hier muessen die checkboxen wieder resettet werden 
			Q_DB_Button   oThis = Q_DB_Button.this;
			
			oThis.setText(oThis.generate_ButtonBeschriftung().CTrans());
			
			
		}
	}

	
	private class ownActionAgentAbbruch extends XX_ActionAgent
	{
		private E2_BasicModuleContainer  oContainer2Close = null;

		public ownActionAgentAbbruch(E2_BasicModuleContainer Container2Close)
		{
			super();
			this.oContainer2Close = Container2Close;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			this.oContainer2Close.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			
			//hier muessen die checkboxen wieder resettet werden 
			Q_DB_Button   oThis = Q_DB_Button.this;
			
			//jetzt die hm mit den checkboxen durchsuchen und nachsehen, was sich veraendert hat
			Iterator<Q_DEF_CheckBox>  oIter = oThis.hmQ_DEF_CheckBoxen.values().iterator();
		    while (oIter.hasNext())
		    {
		    	oIter.next().Reset_To_Old_State();
		    }
		    
		    oThis.setText(oThis.generate_ButtonBeschriftung().CTrans());

		}
	}


	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		if (!this.bIsUsedInListMode)
		{
			super.set_bEnabled_For_Edit(enabled);
		}
	}

	
	
	//dieser agent speichert die eintraege direkt (z.B. aus der Liste heraus) 
	private class ownActionAgentSave_and_ClosePopup extends XX_ActionAgent
	{
		private E2_BasicModuleContainer  oContainer2Close = null;

		public ownActionAgentSave_and_ClosePopup(E2_BasicModuleContainer Container2Close) throws myException
		{
			super();
			this.oContainer2Close = Container2Close;
			
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Q_DB_Button  oThis = Q_DB_Button.this;
			
			String cID_Table = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			Vector<String> vSql = oThis.get_vSQL_Stack(cID_Table);
			
			if (vSql.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurde nichts verändert !")));
			}
			else
			{
				MyE2_MessageVector  oMV = bibDB.ExecMultiSQLVector(vSql, true);
				
				if (oMV.get_bIsOK())
				{
					MyE2_String cMessage = new MyE2_String(vSql.size()==0?"Keine Änderungen":"Anzahl gespeicherte Änderungen: "+vSql.size());
					bibMSG.add_MESSAGE(new MyE2_Info_Message(cMessage));
					oThis.setze_status_der_CheckBoxen_aus_DB(cID_Table);
					oThis.setText(oThis.generate_ButtonBeschriftung().CTrans());
					oContainer2Close.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
				else
				{
					bibMSG.add_MESSAGE(oMV);
				}
			}
			
			
		}
	}

	
	public MyE2_String generate_ButtonBeschriftung()
	{
		//jetzt die hm mit den checkboxen durchsuchen und nachsehen, was sich veraendert hat
		MyE2_String cRueck = new MyE2_String(this.get_cButtonLabel().CTrans(),false);

		
		//vector zum sortieren erfinden
		Vector<Q_DEF_CheckBox>  vCheckBoxen = new Vector<Q_DEF_CheckBox>();
		
		//jetzt die hm mit den checkboxen durchsuchen und nachsehen, was sich veraendert hat
		Iterator<Q_DEF_CheckBox>  oIter = this.hmQ_DEF_CheckBoxen.values().iterator();
	    while (oIter.hasNext())
	    {
	    	vCheckBoxen.add(oIter.next());
	    }
	    
	    Collections.sort(vCheckBoxen);

		
		
		boolean bMindestens1Wert = false;

		for ( Q_DEF_CheckBox oCB: vCheckBoxen)
	    {

	    	if (oCB.isSelected())
	    	{	    	
	    		if (bMindestens1Wert)
		    	{
		    		cRueck.addUnTranslated(", ");
		    	}

	    		cRueck.addString(oCB.get_oDefElement().get_cSHORT_TEXT_4_USER());
	    		bMindestens1Wert = true;
	    	}
	    }

	    if (!bMindestens1Wert)
	    {
	    	cRueck.addUnTranslated(" ... ");
	    }
	    
	    return cRueck;
	    
	}
	

	public int get_iNumberCheckboxRowsInPopup()
	{
		return iNumberCheckboxRowsInPopup;
	}


	public void set_iNumberCheckboxRowsInPopup(int NumberCheckboxRowsInPopup)
	{
		this.iNumberCheckboxRowsInPopup = NumberCheckboxRowsInPopup;
	}




	public MutableStyle get_oStyle4Grid()
	{
		return oStyle4Grid;
	}


	public Insets get_oInsets4Grid()
	{
		return oInsets4Grid;
	}



	public void set_oStyle4Grid(MutableStyle oStyle4Grid)
	{
		this.oStyle4Grid = oStyle4Grid;
	}


	public void set_oInsets4Grid(Insets oInsets4Grid)
	{
		this.oInsets4Grid = oInsets4Grid;
	}


	public MyString get_cButtonLabel()
	{
		return cButtonLabel;
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

	
}
