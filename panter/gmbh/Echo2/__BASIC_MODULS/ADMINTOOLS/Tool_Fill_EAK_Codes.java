package panter.gmbh.Echo2.__BASIC_MODULS.ADMINTOOLS;

import java.util.Vector;

import nextapp.echo2.app.Extent;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.Component_SEARCH_EAK_CODES;

public class Tool_Fill_EAK_Codes extends MyE2_Column
{
	private E2_BasicModuleContainer  				oModulContainerMother = 	null;
	private MyE2_Button  							buttonRefresh = 			new MyE2_Button(new MyE2_String("Werte neu einlesen ..."));
	private MyE2_SelectField  						oSelectFieldBranchen = 		null;
	private MyE2_Grid				    			oGridForSelektion = 		new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11()); 
	private Component_SEARCH_EAK_CODES  			oSearchEAKCodes = 			null;
	private MyE2_Button 							oButtonSave = 				new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"));	
	private selectSortenGrid    					oSortenGrid = 				null;
	
	private MyE2_CheckBox                           oCB_NurLeereAVVCodes = 		new MyE2_CheckBox(new MyE2_String("Nur leere AVV-Codes berücksichtigen"));
	
	private String      							cINDEXNAME_OF_BRANCHE = 	null;
	private String      							cTABLE_OF_BRANCHE = 		null;
	private String      							cDROPDOWNFIELD_OF_BRANCHE = null;
	

	/**
	 * 
	 * @param modulContainerMother
	 * @param ctable_of_branche   		(normale Branche: JT_BRANCHE /  avv-branche: JT_EAK_BRANCHE)
	 * @param cindexname_of_branche		(normale Branche: ID_BRANCHE /  avv-branche: ID_EAK_BRANCHE)
	 * @param cdropdownfield_of_branche (normale Branche: KURZBEZEICHNUNG /  avv-branche: BRANCHE)
	 * @throws myException
	 */
	public Tool_Fill_EAK_Codes(	E2_BasicModuleContainer modulContainerMother, 	
								String 					ctable_of_branche,
								String 					cindexname_of_branche, 
								String 					cdropdownfield_of_branche) throws myException
	{
		super();
		oModulContainerMother = modulContainerMother;
		
		this.cDROPDOWNFIELD_OF_BRANCHE	=	cdropdownfield_of_branche;
		this.cINDEXNAME_OF_BRANCHE 		= 	cindexname_of_branche;
		this.cTABLE_OF_BRANCHE 			= 	ctable_of_branche;
		
		this.add(new MyE2_Label(new MyE2_String("Globale AVV-Codes ändern ..."),MyE2_Label.STYLE_NORMAL_BOLD()), E2_INSETS.I_10_2_10_2);
		this.add(new E2_ComponentGroupHorizontal(0,this.buttonRefresh,this.oCB_NurLeereAVVCodes,E2_INSETS.I_0_0_20_0), E2_INSETS.I_10_2_10_2);
		this.add(this.oGridForSelektion,E2_INSETS.I_10_2_10_2);
		
		this.oSearchEAKCodes = new Component_SEARCH_EAK_CODES();
		this.oButtonSave.add_oActionAgent(new saveActionAgent());
		this.oButtonSave.add_GlobalValidator(new E2_ButtonAUTHValidator(this.oModulContainerMother.get_MODUL_IDENTIFIER(),"SAVE_EAK_CODES"));
		
		
		this.NeuAufbau();
		
		this.buttonRefresh.add_oActionAgent
		(
		    new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					Tool_Fill_EAK_Codes.this.NeuAufbau();	
				}
			}
		);

		
	}


	private void NeuAufbau()
	{
		this.oGridForSelektion.removeAll();
		try
		{
			
//			String cQuery = "SELECT KURZBEZEICHNUNG, ID_BRANCHE FROM "+bibE2.cTO()+".JT_BRANCHE WHERE ID_BRANCHE IN " +
//					" (SELECT DISTINCT ID_BRANCHE FROM "+bibE2.cTO()+".JT_FIRMENINFO) ORDER BY KURZBEZEICHNUNG";

			String cQuery = "SELECT "+this.cDROPDOWNFIELD_OF_BRANCHE+", "+this.cINDEXNAME_OF_BRANCHE+" FROM "+bibE2.cTO()+"."+this.cTABLE_OF_BRANCHE+" WHERE "+this.cINDEXNAME_OF_BRANCHE+" IN " +
						" (SELECT DISTINCT "+this.cINDEXNAME_OF_BRANCHE+" FROM "+bibE2.cTO()+".JT_FIRMENINFO) ORDER BY "+this.cDROPDOWNFIELD_OF_BRANCHE+"";

			
			this.oSelectFieldBranchen = new MyE2_SelectField(cQuery,false,true,false,false);
			this.oSelectFieldBranchen.add_oActionAgent(new brachenSelektionsActionAgent());
			this.oSelectFieldBranchen.setWidth(new Extent(100));
			
			this.oCB_NurLeereAVVCodes.add_oActionAgent(new CheckBoxSelektionsActionAgent());

			this.oSortenGrid = new selectSortenGrid();

			this.oSearchEAKCodes.prepare_ContentForNew(true);
			
			// ueberschrift
			this.oGridForSelektion.add(new MyE2_Label(new MyE2_String("Branchen ...")),E2_INSETS.I_2_2_2_2);
			this.oGridForSelektion.add(new MyE2_Label(new MyE2_String(".. Artikelbezeichnungen")),E2_INSETS.I_2_2_2_2);
			this.oGridForSelektion.add(new MyE2_Label(new MyE2_String("AVV-Code zuordnen")),E2_INSETS.I_2_2_20_2);
			this.oGridForSelektion.add(new MyE2_Label(new MyE2_String("")),E2_INSETS.I_2_2_2_2);
			
			this.oGridForSelektion.add(this.oSelectFieldBranchen,E2_INSETS.I_2_2_2_2);
			this.oGridForSelektion.add(this.oSortenGrid,E2_INSETS.I_2_2_2_2);
			this.oGridForSelektion.add(this.oSearchEAKCodes,E2_INSETS.I_2_2_20_2);
			this.oGridForSelektion.add(this.oButtonSave,E2_INSETS.I_2_2_2_2);
			
			
		}
		catch (myException ex)
		{
			this.showError(new MyE2_Label("Error building Lists ..."),new MyE2_Label(ex.ErrorMessage),null,null);
		}
	}
	
	private void showError(MyE2_Label oLab1,MyE2_Label oLab2,MyE2_Label oLab3,MyE2_Label oLab4)
	{
		this.oGridForSelektion.removeAll();
		if (oLab1 !=null) this.oGridForSelektion.add(oLab1,4,E2_INSETS.I_2_2_2_2);
		if (oLab2 !=null) this.oGridForSelektion.add(oLab2,4,E2_INSETS.I_2_2_2_2);
		if (oLab3 !=null) this.oGridForSelektion.add(oLab3,4,E2_INSETS.I_2_2_2_2);
		if (oLab4 !=null) this.oGridForSelektion.add(oLab4,4,E2_INSETS.I_2_2_2_2);
	}
	
	
	
	/*
	 * actionagent fuer die anwahl einer branche
	 */
	private class brachenSelektionsActionAgent extends XX_ActionAgent 
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			Tool_Fill_EAK_Codes oThis = Tool_Fill_EAK_Codes.this;
			
			MyE2_SelectField oSelectBranchen = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			try
			{
				String cActualBranche = bibALL.ReplaceTeilString(oSelectBranchen.get_ActualWert(),".","");
				if (bibALL.isEmpty(cActualBranche))
				{
					oThis.oSortenGrid.removeAll();
					oThis.oSearchEAKCodes.prepare_ContentForNew(true);
				}
				else
				{
					oThis.oSearchEAKCodes.prepare_ContentForNew(true);
					oThis.oSortenGrid.build_Content(cActualBranche);
				}
			}
			catch (myException ex)
			{
				oThis.showError(new MyE2_Label(new MyE2_String("Fehler beim laden der branchenbezogenen Sorten")),null,null,null);
			}
		}
	}
	
	
	/*
	 * actionagent fuer die anwahl einer branche
	 */
	private class CheckBoxSelektionsActionAgent extends XX_ActionAgent 
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			Tool_Fill_EAK_Codes oThis = Tool_Fill_EAK_Codes.this;
			
			MyE2_SelectField oSelectBranchen = oThis.oSelectFieldBranchen;
			
			try
			{
				String cActualBranche = bibALL.ReplaceTeilString(oSelectBranchen.get_ActualWert(),".","");
				if (bibALL.isEmpty(cActualBranche))
				{
					oThis.oSortenGrid.removeAll();
					oThis.oSearchEAKCodes.prepare_ContentForNew(true);
				}
				else
				{
					oThis.oSearchEAKCodes.prepare_ContentForNew(true);
					oThis.oSortenGrid.build_Content(cActualBranche);
				}
			}
			catch (myException ex)
			{
				oThis.showError(new MyE2_Label(new MyE2_String("Fehler beim laden der branchenbezogenen Sorten")),null,null,null);
			}
		}
	}
	
	
	
	

	
	/*
	 * speichern der zuordnungen
	 */
	private class saveActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{

			Tool_Fill_EAK_Codes oThis = Tool_Fill_EAK_Codes.this;

			try
			{
				
				// jetzt nachsehen, ob es schon einen eindeutigen typ gibt, wenn ja, dann anzeigen
				String cAktuelleBranche = bibALL.ReplaceTeilString(oThis.oSelectFieldBranchen.get_ActualWert(),".","");
				Vector<String> vAktuelleID_Artikel  = oThis.oSortenGrid.get_vSelected_ID_ARTIKEL();
				
				String cID_EAK_Code = bibALL.ReplaceTeilString(oThis.oSearchEAKCodes.get_cActualMaskValue(),".","");

				
				if (vAktuelleID_Artikel.size()==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte markieren Sie mindestens eine Sorte !!"));
				}
				else
				{
					
					// zuerst prueflauf, ob alle werte ok sind
					for (int i=0;i<vAktuelleID_Artikel.size();i++)
					{
						String cAktuelleID_Artikel = (String)vAktuelleID_Artikel.get(i);
						if (bibALL.isEmpty(cAktuelleBranche) || bibALL.isEmpty(cAktuelleID_Artikel) || bibALL.isEmpty(cID_EAK_Code) || 
							!bibALL.isLong(cAktuelleBranche) || !bibALL.isLong(cAktuelleID_Artikel) || !bibALL.isLong(cID_EAK_Code))
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben Sie Branche, Artikelbezeichnung und AVV-Code korrekt vor !!!"));
						}
					}
					
					if (bibMSG.get_bIsOK())
					{
	
						boolean bNurLeereAVVs = Tool_Fill_EAK_Codes.this.oCB_NurLeereAVVCodes.isSelected();

						
						Vector<String> vSQL = new Vector<String>();
						
						// jetzt die EAK-Branche rausziehen
//						EAK_DataRecordHashMap_CODE oCode = new EAK_DataRecordHashMap_CODE(cID_EAK_Code);
//						String cID_EAK_Branche=oCode.get_hmBranche().get_UnFormatedValue("ID_EAK_BRANCHE");
						
						int iCount_Artikelbez_lief_ZuAendern = 0;
//						String cAnzahlFirmeninfo = "0";
						
//						if (!bNurLeereAVVs)    //id_eak_branche nur updaten wo alles ueberbuegelt wird
//						{
//							if (!oThis.cINDEXNAME_OF_BRANCHE.equals("ID_BRANCHE"))
//							{
//								cAnzahlFirmeninfo = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE ID_BRANCHE="+cAktuelleBranche);
//								vSQL.add("UPDATE JT_FIRMENINFO SET ID_EAK_BRANCHE="+cID_EAK_Branche+" WHERE ID_BRANCHE="+cAktuelleBranche);
//							}
//						}
						
						for (int i=0;i<vAktuelleID_Artikel.size();i++)
						{
							
							String cAktuelleID_Artikel = (String)vAktuelleID_Artikel.get(i);
							
//							String cWhereBlock1 =	"ID_ARTIKEL_BEZ IN 	(SELECT ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE ID_ARTIKEL="+cAktuelleID_Artikel+" ) AND "+
//															(bNurLeereAVVs?" JT_ARTIKELBEZ_LIEF.ID_EAK_CODE IS NULL AND ":"")+
//														"ID_ADRESSE IN 		(" +
//															"SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO " +
//																" WHERE " +
//																" JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND " +
//																"JT_FIRMENINFO.ID_BRANCHE=" +cAktuelleBranche+")";
//		

							String cWhereBlock1 =	"ID_ARTIKEL_BEZ IN 	(SELECT ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE ID_ARTIKEL="+cAktuelleID_Artikel+" ) AND "+
															(bNurLeereAVVs?" JT_ARTIKELBEZ_LIEF.ID_EAK_CODE IS NULL AND ":"")+
														"ID_ADRESSE IN 		(" +
															"SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO " +
																" WHERE " +
																" JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND " +
																" JT_FIRMENINFO."+oThis.cINDEXNAME_OF_BRANCHE+"=" +cAktuelleBranche+")";


							
							// weiterzaehlen .. 
							iCount_Artikelbez_lief_ZuAendern += new Integer(bibDB.EinzelAbfrage("SELECT COUNT(*) FROM JT_ARTIKELBEZ_LIEF WHERE " + cWhereBlock1)).intValue();					
							
							vSQL.add("UPDATE JT_ARTIKELBEZ_LIEF  SET ID_EAK_CODE="+cID_EAK_Code+" WHERE " + cWhereBlock1);
						}
						
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
						if (bibMSG.get_bIsOK())
						{
							MyE2_String oInfo = new MyE2_String("Anderungen durchgeführt (Spezifische Artikelbezeichnungen) ");
							oInfo.addUnTranslated(""+iCount_Artikelbez_lief_ZuAendern);
							bibMSG.add_MESSAGE(new MyE2_Info_Message(oInfo));
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Schreiben der Änderungen !!"));
							bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vSQL);
						}
					}
				}
			}
			catch (myException ex)
			{
				oThis.NeuAufbau();
			}
			
		}
	}
	
	
	
	
	
	

	/*
	 * grid, in das die moeglichen sorten dieser branche eingetragen werden koenne
	 */
	private class  selectSortenGrid extends MyE2_Grid
	{
		private Vector<MyE2_CheckBox> vSorten = new Vector<MyE2_CheckBox>();
		
		
		public selectSortenGrid() 
		{
			super( MyE2_Grid.STYLE_GRID_NO_BORDER());
		}
		
		
		// neuaufbau anhand einer branche
		public void build_Content(String cID__BRANCHE)
		{
			Tool_Fill_EAK_Codes  oThis = Tool_Fill_EAK_Codes.this;
			
			this.removeAll();
			this.vSorten.removeAllElements();
			
			boolean bNurLeereAVVs = Tool_Fill_EAK_Codes.this.oCB_NurLeereAVVCodes.isSelected();
			
//			String cSQLQuery = "SELECT DISTINCT JT_ARTIKEL.ANR1||' - '||JT_ARTIKEL.ARTBEZ1 AS ARTBEZ, JT_ARTIKEL.ID_ARTIKEL " +
//					    		" FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF,"+bibE2.cTO()+".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL WHERE " +
//					    		(bNurLeereAVVs?" JT_ARTIKELBEZ_LIEF.ID_EAK_CODE IS NULL AND ":"")+
//								"JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AND "+
//								"JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND "+
//								" JT_ARTIKELBEZ_LIEF.ID_ADRESSE IN (" +
//									"SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND " +
//											"JT_FIRMENINFO.ID_BRANCHE=" +cID_BRANCHE+
//							    ") ORDER BY ARTBEZ";


			String cSQLQuery = "SELECT DISTINCT JT_ARTIKEL.ANR1||' - '||JT_ARTIKEL.ARTBEZ1 AS ARTBEZ, JT_ARTIKEL.ID_ARTIKEL " +
						    		" FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF,"+bibE2.cTO()+".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL WHERE " +
						    		(bNurLeereAVVs?" JT_ARTIKELBEZ_LIEF.ID_EAK_CODE IS NULL AND ":"")+
									"JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AND "+
									"JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND "+
									" JT_ARTIKELBEZ_LIEF.ID_ADRESSE IN (" +
										"SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND " +
												"JT_FIRMENINFO."+oThis.cINDEXNAME_OF_BRANCHE+"=" +cID__BRANCHE+
								    ") ORDER BY ARTBEZ";

			
			
			
			String[][] cWerte = bibDB.EinzelAbfrageInArray(cSQLQuery,"");
			
			if (cWerte == null)
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Zuordnen der Sorten zur Branche: "+cID__BRANCHE));
			else if (cWerte.length >0 && cWerte.length<=20)
				this.setSize(1);    // einspaltig
			else if (cWerte.length >20 && cWerte.length<=40)
				this.setSize(2);    // zweispaltig
			else if (cWerte.length >40)
				this.setSize(3);    // dreispaltig
			else
				this.setSize(1);    // notfall
			
			if (cWerte != null)
			{
				for (int i=0;i<cWerte.length;i++)
				{
					checkBoxSorte oCB = new checkBoxSorte(cWerte[i][0],cWerte[i][1]);

					String cSQLQueryForCount = "SELECT COUNT(JT_ARTIKELBEZ_LIEF.ID_ARTIKELBEZ_LIEF) " +
							    		" FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF,"+bibE2.cTO()+".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL WHERE " +
							    		(bNurLeereAVVs?" JT_ARTIKELBEZ_LIEF.ID_EAK_CODE IS NULL AND ":"")+
										"JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AND "+
										"JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND "+
										" JT_ARTIKEL.ID_ARTIKEL="+cWerte[i][1]+" AND "+
										" JT_ARTIKELBEZ_LIEF.ID_ADRESSE IN (" +
											"SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND " +
													"JT_FIRMENINFO."+oThis.cINDEXNAME_OF_BRANCHE+"=" +cID__BRANCHE+")";

					
					MyE2_String oToolTip = new MyE2_String("Anzahl betroffener Artikelbezeichungen: ",true,bibDB.EinzelAbfrage(cSQLQueryForCount),false);
					oCB.setToolTipText(oToolTip.CTrans());
					
					this.vSorten.add(oCB);
					this.add(oCB,E2_INSETS.I_2_0_2_0);
				}
			}
		}
		
		
		
		public Vector<String> get_vSelected_ID_ARTIKEL()
		{
			Vector<String> vRueck = new Vector<String>();
			for (int i=0;i<this.vSorten.size();i++)
			{
				checkBoxSorte oCB = (checkBoxSorte)this.vSorten.get(i);
				if (oCB.isSelected())
					vRueck.add(oCB.get_ID_ARTIKEL());
				
			}
			return vRueck;
		}
	}
	
	
	
	private class  checkBoxSorte extends MyE2_CheckBox
	{
		public checkBoxSorte(String cSorte, String cID_ARTIKEL) 
		{
			super(cSorte);
			this.EXT().set_C_MERKMAL(cID_ARTIKEL);
			this.add_oActionAgent(new artSelektionsActionAgent());
			this.setFont(new E2_FontItalic(-2));
		}

		public String get_ID_ARTIKEL()
		{
			return this.EXT().get_C_MERKMAL();
		}
	}

	
	/*
	 * actionagent fuer die anwahl einer sorte (wird beim klick auf eine checkbox aktiv) und setzt den 
	 * eak-code-selektor jeweils so, wie die zuletzt angehakte kombination id_branche + id_artikel,
	 * WENN dieser eindeutig ist !!! 
	 * 
	 */
	private class artSelektionsActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			Tool_Fill_EAK_Codes oThis = Tool_Fill_EAK_Codes.this;

			try
			{
				oThis.oSearchEAKCodes.prepare_ContentForNew(true);
				
				checkBoxSorte oCB = (checkBoxSorte)bibE2.get_LAST_ACTIONEVENT().getSource();
				
				if (!oCB.isSelected())
					return;                           // dann wird der eak-selector nur resetet
					
				// jetzt nachsehen, ob es schon einen eindeutigen typ gibt, wenn ja, dann anzeigen
				String cAktuelleBranche = bibALL.ReplaceTeilString(oThis.oSelectFieldBranchen.get_ActualWert(),".","");
				String cAktuelleID_Artikel  = bibALL.ReplaceTeilString(oCB.get_ID_ARTIKEL(),".","");
				
				if (bibALL.isEmpty(cAktuelleID_Artikel))    // leerauswahl fuehrt zum loeschen
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error: no correct Artikel !!!",false)));
					return;
				}
				
				if (bibALL.isEmpty(cAktuelleBranche))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error: no correct Branche !!!",false)));
					return;
				}

				String cSQLQuery = "SELECT DISTINCT JT_ARTIKELBEZ_LIEF.ID_EAK_CODE " +
							    		" FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF,"+bibE2.cTO()+".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL WHERE " +
										" JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AND "+
										" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND "+
										" JT_ARTIKEL.ID_ARTIKEL="+cAktuelleID_Artikel+" AND "+
										" JT_ARTIKELBEZ_LIEF.ID_ADRESSE IN (" +
										"SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO  WHERE " +
												" JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND " +
												"JT_FIRMENINFO."+oThis.cINDEXNAME_OF_BRANCHE+"=" +cAktuelleBranche+
								    ")";
				
				String[][] cInfo = bibDB.EinzelAbfrageInArray(cSQLQuery,"");
				if (cInfo.length == 1)
				{
					oThis.oSearchEAKCodes.set_cActual_Formated_DBContent_To_Mask(cInfo[0][0],null,null);
				}

			}
			catch (myException ex)
			{
				oThis.NeuAufbau();
			}
		}
	}

	
}
