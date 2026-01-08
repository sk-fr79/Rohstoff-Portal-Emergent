package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForInternalActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_PassiveActionExecuter;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * klasse, die listenselektoren zusammenfasst und bei jedem action-event
 * die uebergebene liste selbststaending neu aufbaut und positioniert
 * 
 */
public class E2_SelectionComponentsVector extends Vector<XX_ListSelektor> implements E2_IF_Handles_ActionAgents
{

	private E2_NavigationList 			oNavigationList = null;
	
	/*
	 * schalter, der benutzt wird, um "passive", d.h. aus dem programmcode abgeleitete event-aufrufe zu starten
	 * Dies wird hier dazu benutzt, um die meldungen abzuschalten, 
	 * sonst ist der schalter dazu da, keine timestamps zu schreiben und den message-vector NICHT zu leeren
	 */
	private boolean 					bActionEventIsPassive = false;
	

	/*
	 * hier wird eine komponente einfuert, die aus einer checkbox und einem
	 * reload-button besteht. damit kann der benutzer die selektionen 
	 * aktiv ausführen, d.h. ein relaod der ansicht kommt erst nach dem ausloesen
	 * des buttons zustande.
	 */
	private MyE2_CheckBox				oCheckPassiv = new MyE2_CheckBox(new MyE2_String("Passiv"));
	private MyE2_Button					oButtonReload = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
	
	
	/*
	 * checkbox, die bestimmt, ob eine selektion komplett invertiert werden soll
	 */
	private MyE2_CheckBox  			    oCB_InvertiereAuswahl = new MyE2_CheckBox("Invertiere Auswahl");
	
	/*
	 * damit kann einem selector das neu aufbauen in einzelnen selektionselementen erlaubt werden (beispiel Selectbox in abhaengigkeit der aktuellen auswahl) 
	 */
	private Vector<XX_ActionAgent>    	vExternalActionAgents = new Vector<XX_ActionAgent>();
	
	
	private XX_ActionAgent  			oAgent4Selectors = null;
	
	
	//wenn ein sessionhash fuer den speichervorgang gesetzt wird, dann wird jede einstellung gespeichert (jede selektion)
	private String                      SESSION_HASH_4_SAVE_SETTINGS = null;
	
	
	//2011-08-16: moeglichkeit, eine komponente im Vector abzulegen, die die auswahl selbst enthaelt
	private Component                   oSelComponent = null;


	
//	//2013-02-22: eine modulspezifische selektionsliste kann in der datenbanktabelle JT_SELECTOR gesammelt werden
//	private E2_ListSelector_DB_Defined  oListSelektorDB_defined = null;
	

	/**
	 * 2013-12-18: neue methode um meldungen zu manipulieren
	 */
	private XX_MessageManipulator      oMessageManipulator = null;
	public XX_MessageManipulator   get_MessageManipulator() {
		return this.oMessageManipulator;
	}

	public void set_MessageManipulator(XX_MessageManipulator oManipulator) {
		this.oMessageManipulator = oManipulator;
	}
	
	
	
	//20170906: unterbrechungen mit benutzerinteraktion einfuegen
	private  E2_Break4PopupController break4PopupController = null;
	
	
	@Override
	public E2_Break4PopupController  getBreak4PopupController() {
		return this.break4PopupController;
	}


	/**
	 * 2018-01-16: martin: break4popup-controller setter dazugefuegt
	 * @param controller
	 * @return
	 */
	@Override
	public void setBreak4PopupController(E2_Break4PopupController controller) {
		this.break4PopupController = controller;
	}
	


	
	public E2_SelectionComponentsVector(E2_NavigationList onavigationList) throws myException
	{
		super();
		oNavigationList = onavigationList;
		
		
		this.oCheckPassiv.setSelected(false);
		this.oCB_InvertiereAuswahl.setSelected(false);

		this.oButtonReload.add_oActionAgent(new actionReload());
		this.oCB_InvertiereAuswahl.add_oActionAgent(new actionReload());
		
		this.oAgent4Selectors = new actionForAll(false);
		
		
		this.oCB_InvertiereAuswahl.setToolTipText(new MyE2_String("Die vorherige Auswahl invertieren, d.h. alle Zeilen anzeigen, die vorher verborgen waren").CTrans());
		this.oCheckPassiv.setToolTipText(new MyE2_String("Aktiviert den passiven Modus, d.h. die Selektoren lösen nicht mehr selbst aus !").CTrans());
		this.oButtonReload.setToolTipText(new MyE2_String("Löst ein Reload der Tabelle aus").CTrans());
		
//		//2013-02-22: gegebenenfalls den datenbankgestuetzten selektor aufbauen
//		this.oListSelektorDB_defined = new E2_ListSelector_DB_Defined(onavigationList);
	}

	
	

	public E2_SelectionComponentsVector(E2_NavigationList onavigationList, XX_ActionAgent oActionAgentForReloadButton, XX_ActionAgent ActionAgent4Selectors)  throws myException
	{
		super();
		oNavigationList = onavigationList;
		this.oCheckPassiv.setSelected(false);

		if (oActionAgentForReloadButton!=null)
			this.oButtonReload.add_oActionAgent(oActionAgentForReloadButton);
		
		this.oAgent4Selectors = ActionAgent4Selectors;	
		
		this.oCheckPassiv.setToolTipText(new MyE2_String("Aktiviert den passiven Modus, d.h. die Selektoren lösen nicht mehr selbst aus !").CTrans());
		this.oButtonReload.setToolTipText(new MyE2_String("Löst ein Reload der Tabelle aus").CTrans());
		
//		//2013-02-22: gegebenenfalls den datenbankgestuetzten selektor aufbauen
//		this.oListSelektorDB_defined = new E2_ListSelector_DB_Defined(onavigationList);
	}

	
	
	public boolean add(XX_ListSelektor oListSelector)
	{
		boolean bRueck = super.add(oListSelector);
		oListSelector.add_ActionAgentToComponent(this.oAgent4Selectors);
		return bRueck;
	}
	
	
	
	
	/**
	 * zusätzliche add-Methode mit automatischer Zuweisung des/der Valuenamens bei einem Selektor
	 * @author manfred
	 * @date 12.05.2016
	 *
	 * @param oListSelector
	 * @param sValueName
	 * @return
	 */
	public boolean add(XX_ListSelektor oListSelector,  ENUM_Selector_Report_Params... sSelectorParam){
		boolean bRueck = super.add(oListSelector);
		
		if (sSelectorParam != null ){
			for (int i=0;i<sSelectorParam.length; i++){
				oListSelector.add_SelectorParam(sSelectorParam[i]);
			}
		}
		
		oListSelector.add_ActionAgentToComponent(this.oAgent4Selectors);
		return bRueck;
	}

	/**
	 * Methode gibt die Name/Value-Paare der Selektoren zurück, die Namen haben
	 * @author manfred
	 * @date 12.05.2016
	 *
	 */
	public Hashtable<ENUM_Selector_Report_Params, Object> get_ValueTable(){
		Hashtable<ENUM_Selector_Report_Params, Object> htValues = new Hashtable<>();
		
		for (XX_ListSelektor sel : this){
			try {
				Hashtable<ENUM_Selector_Report_Params, Object> htObject = sel.get_ParamValueTable();
				if (htObject.size() > 0){
					htValues.putAll(htObject);
				}
				
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		return htValues;
	}
	
	
	
	
	
	private class actionReload extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			new actionForAll(true).ExecuteAgentCode(new ExecINFO_OnlyCode());
		}
	}
	

	private class actionForAll extends XX_ActionAgent
	{
		
		private boolean bReloadButton = false;
		

		public actionForAll(boolean reloadButton) 
		{
			super();
			bReloadButton = reloadButton;
		}


		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			
			E2_SelectionComponentsVector oThis = E2_SelectionComponentsVector.this;
			
			try
			{
				oThis.save_settings();
			}
			catch (Exception ex)
			{
				
			}
			
			/*
			 * gestartet wird, wenn der reload-button gedrueckt wird oder wenn
			 * der schalter passiv nicht gesetzt ist !!
			 */
			boolean bStart = (!oThis.oCheckPassiv.isSelected() || this.bReloadButton);
			
			if (bStart)
			{
				/*
				 * zuerst als doInternalCheck() - methoden ausfuehren
				 */
				for (int i=0;i<oThis.size();i++)
				{
				   XX_ListSelektor oSel = (XX_ListSelektor)oThis.get(i);
				   oSel.doInternalCheck();
				}


				boolean bAlleinstellungsSelektion = false;
				MyString  cAlleinstellungsInfo = null;
				
				// alle bedingungen loeschen
				oThis.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_DYNAMIC();
				for (int i=0;i<oThis.size();i++)
				{
				   XX_ListSelektor oSel = (XX_ListSelektor)oThis.get(i);
				   if (!oSel.get_WhereBlock().trim().equals(""))
				   {
					   if (oSel.get_bIstAlleinMerkmal())   // wird ein selektor mit alleinstellungsmerkmal gefunden, dann wird dieses alleine angewendet
					   {
							oThis.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_DYNAMIC();
							oThis.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_BEDINGUNG_DYNAMIC(oSel.get_WhereBlock());
							bAlleinstellungsSelektion = true;
							cAlleinstellungsInfo = oSel.get_AlleinstellungsInfo();
							break;
					   }
					   else
					   {
						   oThis.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_BEDINGUNG_DYNAMIC(oSel.get_WhereBlock());
					   }
				   }
				   
				   //den invertier-schalter nach unten weitergeben
				   oThis.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().set_bInvertiereDynamischenWhereblock(oThis.oCB_InvertiereAuswahl.isSelected());
				}
				
				oThis.oNavigationList._REBUILD_COMPLETE_LIST("");
				
				if (oThis.vExternalActionAgents.size()>0)
				{
					for (int i=0;i<oThis.vExternalActionAgents.size();i++)
					{
						((XX_ActionAgent)oThis.vExternalActionAgents.get(i)).ExecuteAgentCode(new ExecINFO_OnlyCode());
					}
				}
				
				if (!oThis.get_bIsPassivAction())
				{
					if (bAlleinstellungsSelektion)
					{
						if (cAlleinstellungsInfo==null)
						{
							cAlleinstellungsInfo = new MyE2_String("Alleinstellungs-Selektion");
						}
						MyE2_String oHelp = new MyE2_String(cAlleinstellungsInfo.CTrans()+":",false);
						oHelp.addUnTranslated(""+oThis.oNavigationList.get_vectorSegmentation().get_iZahlSegmente()+"   "+new MyE2_String("Seiten mit ").CTrans());
						oHelp.addUnTranslated(""+oThis.oNavigationList.get_vectorSegmentation().size()+"   "+new MyE2_String("  Zeilen insgesamt").CTrans());
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(oHelp));
					}
					else
					{
						MyE2_String oHelp = new MyE2_String("Selektion wurde durchgeführt: ");
						oHelp.addUnTranslated(""+oThis.oNavigationList.get_vectorSegmentation().get_iZahlSegmente()+"   "+new MyE2_String("Seiten mit ").CTrans());
						oHelp.addUnTranslated(""+oThis.oNavigationList.get_vectorSegmentation().size()+"   "+new MyE2_String("  Zeilen insgesamt").CTrans());
						bibMSG.add_MESSAGE(new MyE2_Info_Message(oHelp));
					}
				}
				
			}	
			//2011-09-06: Selektion ueber den passiv-button anmahnen
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Der Selektor steht auf PASSIV ! Bitte die Selektion manuell auslösen !")));
			}
		}
	}
	

	
	
	public String get_Actual_Where_Block() throws myException
	{ 
		
		String cWhereSQL = "";
		
		Vector<String> vSQLs = new Vector<String>();
		
		for (int i=0;i<this.size();i++)
		{
		   XX_ListSelektor oSel = (XX_ListSelektor)this.get(i);
		   if (!oSel.get_WhereBlock().trim().equals(""))
		   {
			   if (oSel.get_bIstAlleinMerkmal())   // wird ein selektor mit alleinstellungsmerkmal gefunden, dann wird dieses alleine angewendet
			   {
				   vSQLs.removeAllElements();
				   vSQLs.add(oSel.get_WhereBlock());
				   break;
			   }
			   else
			   {
				   vSQLs.add(oSel.get_WhereBlock());
			   }
		   }

		}
		//den invertier-schalter nach unten weitergeben
		cWhereSQL = bibALL.Concatenate(vSQLs, " AND ", " 1=1 ");
		
		if (this.oCB_InvertiereAuswahl.isSelected())
		{
			cWhereSQL="( NOT ("+cWhereSQL+"))";
		}

		return cWhereSQL;
		
	}
	
	
	
	public MyE2_Row get_AktivPassivComponent()
	{
		MyE2_Row oRow = new MyE2_Row(new Style_Row_Normal(0, new Insets(0,0)));
		
		RowLayoutData oRowLayL = new RowLayoutData();
		oRowLayL.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		oRowLayL.setInsets(new Insets(0,0,5,0));
		oRow.add(this.oCheckPassiv,oRowLayL);
		this.oCheckPassiv.setBorder(new Border(0,new E2_ColorHelpBackground(),Border.STYLE_SOLID));
		
		RowLayoutData oRowLayR = new RowLayoutData();
		oRowLayR.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		oRowLayR.setInsets(new Insets(2,0,2,0));

		oRow.add(this.oButtonReload,oRowLayR);
		
		oRow.setBackground(new E2_ColorHelpBackground());
		
		return oRow;
	}


	public MyE2_Button get_oButtonReload() 
	{
		return oButtonReload;
	}


	public MyE2_CheckBox get_oCheckPassiv() 
	{
		return oCheckPassiv;
	}


	public Vector<XX_ActionAgent> get_vExternalActionAgents() 
	{
		return vExternalActionAgents;
	}


	private boolean 				Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;

	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session() 
	{
		return this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER;
	}

	
	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP) 
	{
		this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = bMustSet_MILLISECONDSTAMP;
	}

	/**
	 * prueft alle vorhandenen globalen validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	public MyE2_MessageVector valid_GlobalValidation() throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vGlobalValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vGlobalValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(this.oButtonReload));
		}
		return vRueck;
	}

	
	/**
	 * prueft alle vorhandenen ID-basierten validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vIDValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vIDValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(vID_Unformated,false));
		}
		return vRueck;
	}


	/*
	 * vector fuer validatoren (je einer fuer globale und fuer ID bezogene) 
	 */
	private Vector<XX_ActionValidator>	vGlobalValidators = 	new Vector<XX_ActionValidator>();
	private Vector<XX_ActionValidator>	vIDValidators = 		new Vector<XX_ActionValidator>();

	
	public void add_oActionAgent(XX_ActionAgent actionAgent)
	{
	}

	public void add_oActionAgent(XX_ActionAgent actionAgent, boolean bInFront)
	{
	}

	
	/*
	 * 2012-02-07: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront)
	{
	}

	

	
	public void remove_oActionAgent(XX_ActionAgent actionAgent)
	{
	}

	
	public void remove_AllActionAgents() 
	{
	}

	
	public Vector<XX_ActionAgent> get_vActionAgents()
	{
		return null;
	}



	public void doActionPassiv() throws myException
	{
		bActionEventIsPassive =true;

		this.oButtonReload.doActionPassiv();
		
		bActionEventIsPassive =false;
	}


	public boolean get_bIsPassivAction() 
	{
		return this.bActionEventIsPassive;
	}


	public void set_bPassivAction(boolean bPassiv) 
	{
		this.bActionEventIsPassive = bPassiv;
	}


	@Override
	public void add_GlobalValidator(XX_ActionValidator valid)
	{
	}


	@Override
	public void add_IDValidator(XX_ActionValidator valid)
	{
	}


	public MyE2_CheckBox get_oCB_InvertiereAuswahl()
	{
		return oCB_InvertiereAuswahl;
	}


	@Override
	public void add_GlobalValidator(Vector<XX_ActionValidator> valid)
	{
		
	}


	@Override
	public void add_IDValidator(Vector<XX_ActionValidator> valid)
	{
	}




	public void makeSelektion() throws myException
	{
		new actionForAll(false).ExecuteAgentCode(new ExecINFO_OnlyCode());
	}
	

	public String get_SESSION_HASH_4_SAVE_SETTINGS()
	{
		return SESSION_HASH_4_SAVE_SETTINGS;
	}


	public void set_SESSION_HASH_4_SAVE_SETTINGS(String sESSION_HASH_4_SAVE_SETTINGS)
	{
		SESSION_HASH_4_SAVE_SETTINGS = sESSION_HASH_4_SAVE_SETTINGS;
	}


	private void save_settings() throws myException
	{
		if (S.isFull(this.SESSION_HASH_4_SAVE_SETTINGS))   //nur dann ist speichern moeglich
		{
			HashMap<String, String>  	hm4SaveSettings = new HashMap<String, String>();
			Vector<String>   			vKeys = new Vector<String>();
			
			for (int i=0;i<this.size();i++)
			{
				String cHASH_String = "SELEKTOR"+i;
				String cWert = "";
				
				if (this.get(i).get_bSaveSettings())
				{
					if (this.get(i) instanceof  E2_ListSelektorMultiselektionStatusFeld) {
						cWert = ((E2_ListSelektorMultiselektionStatusFeld)this.get(i)).get_MemStringStatusSelektor();
					} else if (this.get(i) instanceof E2_ListSelectorMultiDropDown) {
						cWert = ((E2_ListSelectorMultiDropDown)this.get(i)).get_MemStringStatusSelektor();
					} 
					else if (this.get(i).get_oComponentWithoutText() instanceof CheckBox)
					{
						cWert = ((CheckBox)this.get(i).get_oComponentWithoutText()).isSelected()?"Y":"N";
					}
					else if (this.get(i).get_oComponentWithoutText() instanceof MyE2_SelectField)
					{
						cWert = ((MyE2_SelectField)this.get(i).get_oComponentWithoutText()).get_ActualWert();
					}
					else if (this.get(i).get_oComponentWithoutText() instanceof MyE2_TextField_DatePOPUP_OWN)
					{
						cWert = ((MyE2_TextField_DatePOPUP_OWN)this.get(i).get_oComponentWithoutText()).get_oTextField().getText();
					}
				}
				hm4SaveSettings.put(cHASH_String, cWert);
				vKeys.add(cHASH_String);
			}
			
			if (vKeys.size()>0)
			{
				new E2_UserSetting_HashMap(this.SESSION_HASH_4_SAVE_SETTINGS, vKeys).STORE(this.SESSION_HASH_4_SAVE_SETTINGS, hm4SaveSettings);
			}
		}
	}
	
	
	
	public void LOAD_LAST_SETTINGS() throws myException
	{
		if (S.isFull(this.SESSION_HASH_4_SAVE_SETTINGS))   //nur dann ist speichern moeglich
		{
			
			Vector<String>   			vKeys = 			new Vector<String>();
			
			//keys beschaffen
			for (int i=0;i<this.size();i++)
			{
				String cHASH_String = "SELEKTOR"+i;
				vKeys.add(cHASH_String);
			}

			@SuppressWarnings("unchecked")
			HashMap<String, String> hmWerte = (HashMap<String, String> )new E2_UserSetting_HashMap(this.SESSION_HASH_4_SAVE_SETTINGS, vKeys).get_Settings(this.SESSION_HASH_4_SAVE_SETTINGS);
			
		    if (hmWerte != null)
		    {
				for (int i=0;i<this.size();i++)
				{
					String cHASH_String = "SELEKTOR"+i;
					
					if (this.get(i).get_bSaveSettings())
					{
						if (this.get(i) instanceof  E2_ListSelektorMultiselektionStatusFeld) {
							((E2_ListSelektorMultiselektionStatusFeld)this.get(i)).set_MemStringStatusSelektor(S.NN(hmWerte.get(cHASH_String)));
						} else if (this.get(i) instanceof E2_ListSelectorMultiDropDown) {
							((E2_ListSelectorMultiDropDown)this.get(i)).set_MemStringStatusSelektor(S.NN(hmWerte.get(cHASH_String)));
						} 
						else if (this.get(i).get_oComponentWithoutText() instanceof CheckBox)
						{
							if (hmWerte.containsKey(cHASH_String))
							{
								if (S.NN(hmWerte.get(cHASH_String)).equals("Y"))
								{
									((CheckBox)this.get(i).get_oComponentWithoutText()).setSelected(true);
								}
								else
								{
									((CheckBox)this.get(i).get_oComponentWithoutText()).setSelected(false);
								}
							}
						}
						else if (this.get(i).get_oComponentWithoutText() instanceof MyE2_SelectField)
						{
							if (hmWerte.containsKey(cHASH_String))
							{
								((MyE2_SelectField)this.get(i).get_oComponentWithoutText()).set_ActiveValue_OR_FirstValue(S.NN(hmWerte.get(cHASH_String)));
							}							
						}
						else if (this.get(i).get_oComponentWithoutText() instanceof MyE2_TextField_DatePOPUP_OWN)
						{
							if (hmWerte.containsKey(cHASH_String))
							{
								if (S.isEmpty(S.NN(hmWerte.get(cHASH_String))) ||  bibALL.isDatumOK(S.NN(hmWerte.get(cHASH_String))))
								{
									((MyE2_TextField_DatePOPUP_OWN)this.get(i).get_oComponentWithoutText()).get_oTextField().setText(S.NN(hmWerte.get(cHASH_String)));
								}
							}
						}
					}
				}
		    }
		}
	}
	
	
	
	
	
	public Component get_oSelComponent()
	{
		return oSelComponent;
	}


	public void set_oSelComponent(Component oSelComponent)
	{
		this.oSelComponent = oSelComponent;
	}


	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException 
	{
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
	}
	

	
	

	/**
	 * 
	 * @return s nur dann true und wird auch nur dann ausgefuehrt, wenn jeder selector einen gueltigen neutralisator hat
	 * @throws myException
	 */
	public boolean set_NeutralWhenEverySelectorHasNeutron() throws myException
	{
		boolean bRueck = true;
		
		//zuerst pruefen, ob alle member einen neutralisator haben
		
		for (XX_ListSelektor oSelector: this)
		{
			if (oSelector.get_oNeutralisator()==null)
			{
				bRueck = false;
				break;
			}
		}
		
		if (bRueck)
		{
			for (XX_ListSelektor oSelector: this)
			{
				oSelector.get_oNeutralisator().set_to_Neutral();
			}
		
		}
		
		return bRueck;
		
		
	}

	
	
	/**
	 * 
	 * @return s nur dann true und wird auch nur dann ausgefuehrt, wenn jeder selector einen gueltigen neutralisator hat
	 * @throws myException
	 */
	public void set_Neutral_Where_Possible() throws myException
	{
		//zuerst pruefen, ob alle member einen neutralisator haben
		for (XX_ListSelektor oSelector: this)
		{
			if (oSelector.get_oNeutralisator()!=null)
			{
				oSelector.get_oNeutralisator().set_to_Neutral();
			}
		}
	}


	
	
	
	//2013-01-04: codeblock fuer interalActionAgents
	private E2_InnerActionListenerForInternalActionAgents  oInnerActionListenerInternalAction = new E2_InnerActionListenerForInternalActionAgents();
	private Vector<XX_ActionAgent>  vInternalActionAgents = new Vector<XX_ActionAgent>();
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents()
	{
		return this.vInternalActionAgents;
	}

	public void add_oInternalActionAgent(XX_ActionAgent actionAgent)
	{
	}
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront)
	{
	}
	public void remove_oInternalActionAgent(XX_ActionAgent actionAgent)
	{
	}
	public void remove_AllInternalActionAgents() 
	{
	}



//
//	/**
//	 * 2013-02-28: list-selektor aus der datenbank
//	 * @return
//	 */
//	public E2_ListSelector_DB_Defined get_oListSelektorDB_defined()
//	{
//		return oListSelektorDB_defined;
//	}


	
}
