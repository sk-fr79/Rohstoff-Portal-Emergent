package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorFortschrittsbalken;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_FortsschrittsBalken;
import panter.gmbh.Echo2.components.E2_PopUpAllNoneInvert_CheckBoxes;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_STD_extend;
import echopointng.Separator;

public class BSAAL_ButtonBauePDFs_Container extends E2_BasicModuleContainer
{

	public static int iHeight = 700;
	
	private BSAAL__ModulContainerLIST			oModulContainerList = null;
	
	private MyE2_Column							oColumnForPapers =  new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());

	private MyE2_Button							oButtonSelekt_ALL_WhichMustBeBuild = new MyE2_Button(new MyE2_String("Wähle ungesendete"));
	
	private MyE2_Button							oButtonSTART_BauePDFs =	new MyE2_Button(new MyE2_String("Erzeuge Preisinfo-Belege/Abnahmeangebote"));
	private MyE2_Button							oButtonSENDEDIALOG = 	new MyE2_Button(new MyE2_String("ZUM VERSAND"));
	private MyE2_Button							oButtonCANCEL = 		new MyE2_Button(new MyE2_String("ABBRUCH"));
	
	private String 								cMONTH = null;
	private String 								cYEAR  = null;
	private int  								iMONTH = 0;
	private int 								iYEAR  = 0;
	
	private VectorSingle  						vVKOPF_LIST = new VectorSingle();
	/*
	 * die sende-objecte werden gesammelt
	 */
	private Vector<ownRow> 						vSelectObjects = new Vector<ownRow>();
	
	/*
	 * hilfsvector fuer die checkbox-an-aus-selektion
	 */
	private Vector<MyE2_CheckBox> 				vCheckBoxes = new Vector<MyE2_CheckBox>();
	
	
	/*
	 * selektionskomponente fuer die adress-checkboxes
	 */
	private E2_PopUpAllNoneInvert_CheckBoxes	PopUpAnAus = null;

	/*
	 * selektionsmoeglichkeit fuer die adressen / vorgaenge
	 */
	private ownListSelector						oSelector = null;


	
	public BSAAL_ButtonBauePDFs_Container(	BSAAL__ModulContainerLIST	oModulContainerLIST, 
											String 						cMonat, 
											String 						cJahr)  	throws myException
	{
		super();
		this.oModulContainerList = oModulContainerLIST;
		
		this.oSelector = new ownListSelector(cMonat,cJahr);
		
		this.PopUpAnAus = new E2_PopUpAllNoneInvert_CheckBoxes(this.vCheckBoxes, false);
		
		this.cMONTH = cMonat;
		this.cYEAR = cJahr;
		
		if (!bibALL.isInteger(this.cMONTH) || !bibALL.isInteger(this.cYEAR))
			throw new myExceptionForUser("Bitte Monat und Jahr exakt angeben !!!");
		
		this.iMONTH = new Integer(this.cMONTH);
		this.iYEAR = new Integer(this.cYEAR);
		
		
		this.oButtonSTART_BauePDFs.setFont(new E2_FontBold(4));
		this.oButtonSTART_BauePDFs.add_oActionAgent(new ownActionAgentBuildingPapers());

		this.oButtonSENDEDIALOG.setFont(new E2_FontBold(4));
		this.oButtonSENDEDIALOG.add_oActionAgent(new ownActionAgentStartSendedialog());

		// button, um die neuesten, ungesendeten angebote auszusuchen
		this.oButtonSelekt_ALL_WhichMustBeBuild.setFont(new E2_FontBold(0));
		this.oButtonSelekt_ALL_WhichMustBeBuild.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				BSAAL_ButtonBauePDFs_Container oThis = BSAAL_ButtonBauePDFs_Container.this;
				for (int i=0;i<oThis.vSelectObjects.size();i++)
				{
					oThis.vSelectObjects.get(i).get_oCB().setSelected(oThis.vSelectObjects.get(i).get_JasperVKOPF().get_REC_VKOPF_STD().is_ABGESCHLOSSEN_NO());
				}
			}
		});
		this.oButtonSelekt_ALL_WhichMustBeBuild.setToolTipText(new MyE2_String("Auswahl des jeweils neuesten Angebotes aus der Liste, das noch ungesendet ist").CTrans());
		
		
		this.add(this.oColumnForPapers);
		this.set_Component_To_ButtonPane(new E2_ComponentGroupHorizontal(0,this.oButtonSTART_BauePDFs,this.oButtonSENDEDIALOG,this.oButtonCANCEL, E2_INSETS.I_10_2_10_2));
		
		
		/*
		 * close-button verarzten
		 */
		this.oButtonCANCEL.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				BSAAL_ButtonBauePDFs_Container.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		});
		

				
		
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				//BSAAL_WindowPaneToPreparePDFs.this.deleteTempFiles();
				BSAAL_ButtonBauePDFs_Container.this.oModulContainerList.get_oSelector().do_RefreshSelectDateRange();
				BSAAL_ButtonBauePDFs_Container.this.oModulContainerList.get_oSelector().get_oSelVector().doActionPassiv();
				BSAAL_ButtonBauePDFs_Container.this.oModulContainerList.showActualMessages();
			}
		});
				
		this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT( new Extent(1000),
												new Extent(BSAAL_ButtonBauePDFs_Container.iHeight),
												new MyE2_String("Angebotsformulare zum Versand vorbereiten ..."));
		
		
		this.Query_And_Build_List("");
	}

	
	
	// alles auf start ...
	private void Query_And_Build_List(String  cWhereZusatz)	throws myException
	{

		
		
//		// abfragen, welche koepfe (komplett ausgefuellt) vorhanden sind
//		String cQUERY_Vorgaenge = "SELECT JT_VPOS_STD.ID_VKOPF_STD FROM " +       
//									bibE2.cTO()+".JT_VPOS_STD "+
//									"LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT ON (JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD) "+
//									"LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_STD        ON (JT_VPOS_STD.ID_VKOPF_STD=JT_VKOPF_STD.ID_VKOPF_STD) " +
//									"  WHERE " +
//									" JT_VKOPF_STD.VORGANG_TYP = '"+myCONST.VORGANGSART_ABNAHMEANGEBOT+"'  AND " +
//									"   NVL(JT_VPOS_STD.POSITION_TYP,'-')<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"' AND " + 
//									" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY'))="+this.cYEAR+" AND " +
//									" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'MM'))="+this.cMONTH + " AND " +
//									" JT_VPOS_STD.EINZELPREIS IS NOT NULL " +cWhereZusatz+ " AND " +
//									"   NVL(JT_VPOS_STD.DELETED,'N')='N' " +cWhereZusatz+ " AND " +
//									"   NVL(JT_VKOPF_STD.DELETED,'N')='N' " +cWhereZusatz+ 
//									" ORDER BY JT_VKOPF_STD.NAME1,JT_VKOPF_STD.ORT, JT_VPOS_STD_ANGEBOT.GUELTIG_VON, " +
//											"JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,JT_VKOPF_STD.ID_VKOPF_STD";


		//2011-01-09: cWhereZusatz wird mehrfach eingefuegt !! Unsinn
		// abfragen, welche koepfe (komplett ausgefuellt) vorhanden sind
		String cQUERY_Vorgaenge = "SELECT JT_VPOS_STD.ID_VKOPF_STD FROM " +       
									bibE2.cTO()+".JT_VPOS_STD "+
									"LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT ON (JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD) "+
									"LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_STD        ON (JT_VPOS_STD.ID_VKOPF_STD=JT_VKOPF_STD.ID_VKOPF_STD) " +
									"  WHERE " +
									" JT_VKOPF_STD.VORGANG_TYP = '"+myCONST.VORGANGSART_ABNAHMEANGEBOT+"'  AND " +
									"   NVL(JT_VPOS_STD.POSITION_TYP,'-')<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"' AND " + 
									" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY'))="+this.cYEAR+" AND " +
									" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'MM'))="+this.cMONTH + " AND " +
									" JT_VPOS_STD.EINZELPREIS IS NOT NULL  AND " +
									"   NVL(JT_VPOS_STD.DELETED,'N')='N' AND " +
									"   NVL(JT_VKOPF_STD.DELETED,'N')='N' " +cWhereZusatz+ 
									" ORDER BY JT_VKOPF_STD.NAME1,JT_VKOPF_STD.ORT, JT_VPOS_STD_ANGEBOT.GUELTIG_VON, " +
											"JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,JT_VKOPF_STD.ID_VKOPF_STD";
		
		
		
		String[][] cVKOPF_LIST= bibDB.EinzelAbfrageInArray(cQUERY_Vorgaenge);
		if (cVKOPF_LIST == null)
			throw new myException("AAL_WindowPaneToSendAngebote:Constructor:Error: Querying Head-Rows");

		//die doppelten IDs rausschmeissen
		this.vVKOPF_LIST.removeAllElements(); 
		for (int i=0;i<cVKOPF_LIST.length;i++)
		{
			vVKOPF_LIST.add(cVKOPF_LIST[i][0]);
		}
		
		this.vSelectObjects.removeAllElements();
		this.vCheckBoxes.removeAllElements();
		
		
		
		//vVKOPF_LIST.size() > 20 mit fortschrittsbalken
		if (this.vVKOPF_LIST.size()<20)
		{
			for (int i=0;i<this.vVKOPF_LIST.size();i++)
			{
				RECORD_VKOPF_STD_extend  recHelp = new RECORD_VKOPF_STD_extend(this.vVKOPF_LIST.get(i));
				
				if (recHelp.get_bAlleWarenPositionenSindAusMonatJahr(this.iMONTH, this.iYEAR) && 
					recHelp.get_bAlle_Warenpos_Preise_SindGefuellt()	)
				{
					ownRow  oRowHelp = new ownRow(new BSAAL_ButtonBauePDFs_JasperHASH(recHelp,this.cMONTH,this.cYEAR));
					
					this.vSelectObjects.add(oRowHelp);
					this.vCheckBoxes.add(oRowHelp.get_oCB());
				}
				
			}
			this.fill_List();
		}
		else
		{

			new E2_ServerPushMessageContainer(new Extent(530),new Extent(190),new MyE2_String("Überprüfung der Positionssätze ..."),true,true,false,1000)
			{
				@Override
				public void Run_Loop() throws myException
				{
					boolean bAbbruch = false;
					
					BSAAL_ButtonBauePDFs_Container oThis = BSAAL_ButtonBauePDFs_Container.this;
					
					E2_FortsschrittsBalken oBalken = new E2_FortsschrittsBalken(oThis.vVKOPF_LIST.size(),20,new E2_ColorFortschrittsbalken());
					
					/*
					 * jetzt liegt eine liste mit vorgangskopf-ids vor, die mindestens eine position im gewuenschten zeitraum MONAT/JAHR haben,
					 * jetzt alle ausselektieren, deren positionen ueber mehrere monate gehen. diese MUESSEN separat behandelt werden
					 */
					for (int i=0;i<oThis.vVKOPF_LIST.size();i++)
					{
						RECORD_VKOPF_STD_extend  recHelp = new RECORD_VKOPF_STD_extend(oThis.vVKOPF_LIST.get(i));
						
						this.get_oGridBaseForMessages().removeAll();
						oBalken.set_Wert(i);
						this.get_oGridBaseForMessages().add(oBalken);
						this.get_oGridBaseForMessages().add(
								new MyE2_Label(recHelp.get___KETTE(bibALL.get_Vector("NAME1", "ORT"), "-", "  <", ">  ", " "),MyE2_Label.STYLE_SMALL_ITALIC()));
	
						
						if (recHelp.get_bAlleWarenPositionenSindAusMonatJahr(oThis.iMONTH, oThis.iYEAR) && 
							recHelp.get_bAlle_Warenpos_Preise_SindGefuellt()	)
						{
							ownRow  oRowHelp = new ownRow(new BSAAL_ButtonBauePDFs_JasperHASH(recHelp,oThis.cMONTH,oThis.cYEAR));
							
							oThis.vSelectObjects.add(oRowHelp);
							oThis.vCheckBoxes.add(oRowHelp.get_oCB());
						}
						
						if (this.get_bIsInterupted())
						{
							bAbbruch = true;
							break;
						}
					}
					
					if (!bAbbruch)
					{
						oThis.fill_List();
						oBalken.set_Wert(oThis.vVKOPF_LIST.size());
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl selektierte Vorgänge: "+oThis.vVKOPF_LIST.size()));
					}
					else
					{
						oThis.clear_list();
					}
				}
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}

			};
		}
		
	}
	

	
	private void clear_list() throws myException
	{
		this.vSelectObjects.removeAllElements();
		this.vCheckBoxes.removeAllElements();
		this.fill_List();
	}
	
	
	
	private int fill_List() throws myException
	{
		this.oColumnForPapers.removeAll();

		
		// jetzt das tabbed-element fuellen mit 2-spaltigen grids
		MyE2_TabbedPane oTabbed = 		new MyE2_TabbedPane(null);
		
		MyE2_Row		oRow = 			new MyE2_Row(MyE2_Row.STYLE_NO_BORDER());
		MyE2_Column		oColumn1 = 		new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		MyE2_Column		oColumn2 = 		new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		oRow.add(oColumn1);
		oRow.add(oColumn2);
		
		
		// MyE2_Grid 		oGridAdresses = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_DDARK_BORDER);
		int 			iCounter = 		0;
		int 			iCountTabs = 	1;
		
		oTabbed.add_Tabb(new MyE2_String(""+iCountTabs++,false),oRow);

		this.oColumnForPapers.add(this.oSelector);
		this.oColumnForPapers.add(new Separator());

		if (this.vSelectObjects.size()==0)
		{
			this.oColumnForPapers.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String(" ----- KEINE ANGEBOTE IN DER AUSWAHL !!!!!   --------------")),E2_INSETS.I_0_2_10_2));
		}
		else
		{
			for (int i=0;i<this.vSelectObjects.size();i++)
			{
				
				if (iCounter<10)
				{
					oColumn1.add(this.vSelectObjects.get(i).getRow(),E2_INSETS.I_2_2_10_2);
				}
				else
				{
					oColumn2.add(this.vSelectObjects.get(i).getRow(),E2_INSETS.I_2_2_10_2);
				}
				iCounter ++;
				
				if (iCounter>19 && i!=(this.vSelectObjects.size()-1))
				{
					oRow = 			new MyE2_Row(MyE2_Row.STYLE_NO_BORDER());
					oColumn1 = 		new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
					oColumn2 = 		new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
					oRow.add(oColumn1); 
					oRow.add(oColumn2);
					
					oTabbed.add_Tabb(new MyE2_String(""+iCountTabs++,false),oRow);
					iCounter=0;
				}
			}
			this.oColumnForPapers.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Angebote ein/ausschalten")),this.PopUpAnAus,this.oButtonSelekt_ALL_WhichMustBeBuild,E2_INSETS.I_0_2_10_2));
			this.oColumnForPapers.add(oTabbed);
		}
		
		return this.vSelectObjects.size();
		
	}
	
	
	
	
	
	/*
	 * aufbau der ausgewaehlten angebote --- PDFs bauen
	 */
	private class ownActionAgentBuildingPapers extends XX_ActionAgent
	{
		private V_JasperHASH  vHashMaps = null;          

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSAAL_ButtonBauePDFs_Container oThis = BSAAL_ButtonBauePDFs_Container.this;
			
			// zuerst zaehlen, wieviele selektiert sind (evtl. warnung ausgeben)
			int iCountSelektiert = 0;
			for (int i=0;i<oThis.vSelectObjects.size();i++)
			{
				 if (oThis.vSelectObjects.get(i).isSelected())
					 iCountSelektiert++;
			}
			
			if (iCountSelektiert==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Sie haben keine Angebote zur Bearbeitung ausgewählt !!"));
			}
			else
			{
				
				// alle angekreuzten einsammeln
				vHashMaps = new V_JasperHASH();          
				
				for (int i=0;i<oThis.vSelectObjects.size();i++)
				{
					 if (oThis.vSelectObjects.get(i).isSelected())
					 {
						 vHashMaps.add(oThis.vSelectObjects.get(i).get_JasperVKOPF());
					 }
				}
				
				if (bibMSG.get_bIsOK())
				{
					new E2_ServerPushMessageContainer(new Extent(530),new Extent(210),new MyE2_String("Aufbau der Angebots-PDFs läuft ..."),true,true,true,3000)
					{
						@Override
						public void Run_Loop() throws myException
						{
							boolean bOK = true;
							
							// und die reports erzeugen
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vHashMaps.get_vSQL_STACK_EXECUTE_BEFORE_FROM_ALL_HASHMAPS(),true));
							if (bibMSG.get_bIsOK())
							{
								bOK = vHashMaps.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(this, true,false);     //nur neue tempfiles erzeugen
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ausführen der SQL-Statements vor dem Druck !",true)));
								bOK = false;
							}
							// liste neue aufbauen
							if (bOK)
							{
								BSAAL_ButtonBauePDFs_Container.this.fill_List();
							}
							else
							{
								BSAAL_ButtonBauePDFs_Container.this.clear_list();
							}
						}
						
						@Override
						public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
						{
						}

					};
				}
			}
		}
	}



	


	
	
	/**
	 * Füllen und starten des sendedialogs
	 * @author martin
	 *
	 */
	private class ownActionAgentStartSendedialog extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSAAL_ButtonBauePDFs_Container 	oThis = 			BSAAL_ButtonBauePDFs_Container.this;
			MailBlock_Vector  				vMailBlocksToSend = new MailBlock_Vector();                    //vector fuer den sendedialog
			
			for (int i=0; i<oThis.vSelectObjects.size();i++)
			{
				if (oThis.vSelectObjects.get(i).isSelected())
				{
					if (S.isEmpty(oThis.vSelectObjects.get(i).get_JasperVKOPF().get_HM_FILENAME_OF_TEMP_FILE()))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte nur Angebote mit bereits vorhandenem PDF-Dokument auswählen !"));
						return;
					}
					else
					{
						vMailBlocksToSend.add(oThis.vSelectObjects.get(i).get_JasperVKOPF().get_BUILD_AND_GET_MAILBLOCK());
					}
				}
			}
			
			if (vMailBlocksToSend.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Sie haben keine Angebote zum Versenden ausgewählt !"));
				return;
			}
			
//			MyE2_Button 	oButtondownAll = new MyE2_Button(E2_ResourceIcon.get_RI("download_selektierte.png"));
//			oButtondownAll.add_oActionAgent(new ownActionDownloadAllInSendlist(vMailBlocksToSend,true));
//			oButtondownAll.setToolTipText(new MyE2_String("Download aller selektierten Angebote ...").CTrans());
//			MyE2_Button 	oButtondownAllNEGATIVE = new MyE2_Button(E2_ResourceIcon.get_RI("download_unselektierte.png"));
//			oButtondownAllNEGATIVE.setToolTipText(new MyE2_String("Download aller NICHT selektierten Angebote ...").CTrans());
//			oButtondownAllNEGATIVE.add_oActionAgent(new ownActionDownloadAllInSendlist(vMailBlocksToSend,false));
//			Vector<MyE2IF__Component> vZusaetze = new Vector<MyE2IF__Component>();
//			vZusaetze.add(oButtondownAll);
//			vZusaetze.add(oButtondownAllNEGATIVE);

			MyE2_Button 	oButtondownAll = new MyE2_Button(new MyE2_String("Drucke alle ausgewählten Angebote"));
			oButtondownAll.add_oActionAgent(new ownActionDownloadAllInSendlist(vMailBlocksToSend,true));
			oButtondownAll.setToolTipText(new MyE2_String("Download aller Angebote, wo mindestens eine Ziel-Mail-Adresse ausgewählt ist ...").CTrans());
			
			MyE2_Button 	oButtondownAllNEGATIVE = new MyE2_Button(new MyE2_String("Drucke alle NICHT ausgewählten Angebote"));
			oButtondownAllNEGATIVE.setToolTipText(new MyE2_String("Download aller Angebote, wo KEINE Ziel-Mail-Adresse ausgewählt ist ...").CTrans());
			oButtondownAllNEGATIVE.add_oActionAgent(new ownActionDownloadAllInSendlist(vMailBlocksToSend,false));
			
			oButtondownAll.setLineWrap(true);
			oButtondownAllNEGATIVE.setLineWrap(true);
			
			MyE2_Grid oGridHelp = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGridHelp.add(oButtondownAll);
			oGridHelp.add(oButtondownAllNEGATIVE);
			
			Vector<MyE2IF__Component> vZusaetze = new Vector<MyE2IF__Component>();
			vZusaetze.add(oGridHelp);
			
			new BSAAL_ButtonBauePDFs_MailContainer(vMailBlocksToSend,vZusaetze);
			
		}
	}
	
	
	
	
	
	
	private class ownActionDownloadAllInSendlist extends XX_ActionAgent
	{

		private MailBlock_Vector  	vMailBlocksToSend = null;
		private boolean     		bDownloadChecked = true;       

		
		public ownActionDownloadAllInSendlist(MailBlock_Vector  V_MailBlocksToSend, boolean Download_Checked)   //NEU_09 
		{
			super();
			this.vMailBlocksToSend = V_MailBlocksToSend;
			this.bDownloadChecked = Download_Checked;           //NEU_09
		}


		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			try
			{
				V_JasperHASH  vJasperTemp = new V_JasperHASH();             //jasperhash sammelt alle gewuenschten jasper-objekte auf und regelt den download
				
				for (int i=0;i<vMailBlocksToSend.size();i++)
				{
					if (bDownloadChecked && vMailBlocksToSend.get(i).get_b_EVEN_ONE_TargetAdress_isSelected())
					{
						vJasperTemp.add(vMailBlocksToSend.get(i).get_oJasperHash_this_comes_From());
					}
					if (!bDownloadChecked && (!vMailBlocksToSend.get(i).get_b_EVEN_ONE_TargetAdress_isSelected()))
					{
						vJasperTemp.add(vMailBlocksToSend.get(i).get_oJasperHash_this_comes_From());
					}
				}
				
				if (vJasperTemp.size()>0)
				{
					vJasperTemp.DOWNLOAD_FILES(null);
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die gewählte Auswahl ist leer"));
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("AAL_WindowPaneToPreparePDFs:ownActionDownloadAllInSendlist:Error: There MUST be Angebots - PDF !!!",false)));
			}
		}
	}
	
	
	
	
	
	
	
	
	
	/*
	 * eine selektorklasse fuer die auswahl der kunden, die ein angebot erhalten sollen,
	 * reduziert die anzahl der checkboxen
	 */
	private class ownListSelector extends MyE2_Row
	{
		
		/*
		 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
		 */
		private ownSelectionsComponentVector 	oSelVector = null;
		
		//private MyE2_SelectField				oSelectMitarbeiterADR = null;
		private Component_USER_DROPDOWN_NEW  		oSelectMitarbeiterADR = 	new Component_USER_DROPDOWN_NEW(false,120);

		
		private MyE2_SelectField				oSelectLand = null;
		private MyE2_SelectField				oSelectAdressKlasse = null;

		
		private SELECTOR_COMPONENT_FirmenAuswahl   	oSelKundenMitAngebot = null;

		
		public ownListSelector(String cMonth,  String cYear) throws myException
		{
			super();
			
			this.oSelVector = 				new ownSelectionsComponentVector();

//			this.oSelectMitarbeiterADR = new MyE2_SelectField(
//					"select  NVL(name,'-')||' ('|| NVL(vorname,'-')||') ',id_user from "+bibE2.cTO()+".jd_user where id_mandant="+bibALL.get_ID_MANDANT()+" order by name",
//					false,true,false,false);

			
			//2011-01-09: neuer kundenselektor fuer die auswahl
			String cIN_Query = " (SELECT JT_VKOPF_STD.ID_ADRESSE FROM " +       
											bibE2.cTO()+".JT_VPOS_STD "+
											"LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT ON (JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD) "+
											"LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_STD        ON (JT_VPOS_STD.ID_VKOPF_STD=JT_VKOPF_STD.ID_VKOPF_STD) " +
											"  WHERE " +
											" JT_VKOPF_STD.VORGANG_TYP = '"+myCONST.VORGANGSART_ABNAHMEANGEBOT+"'  AND " +
											"   NVL(JT_VPOS_STD.POSITION_TYP,'-')<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"' AND " + 
											" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY'))="+cYear+" AND " +
											" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'MM'))="+cMonth + " AND " +
											" JT_VPOS_STD.EINZELPREIS IS NOT NULL  AND " +
											"   NVL(JT_VPOS_STD.DELETED,'N')='N' AND " +
											"   NVL(JT_VKOPF_STD.DELETED,'N')='N') ";
			String cQueryKundenSelektor =  "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
												"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
												" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN "+
												cIN_Query+" ORDER BY NAMEN";
			
			this.oSelKundenMitAngebot = new SELECTOR_COMPONENT_FirmenAuswahl(cQueryKundenSelektor, 60, 120, this.oSelVector, new MyE2_String("Lieferant: "));
			this.oSelKundenMitAngebot.REFRESH_KundenSelektor();
			//ende kundenselektor
			
			
			this.oSelectLand = new MyE2_SelectField(
					"select laendername,id_land from "+bibE2.cTO()+".jd_land order by laendername",
					false,true,false,false,new Extent(120));

			this.oSelectAdressKlasse = new MyE2_SelectField(
					"select kurzbezeichnung,id_adressklasse_def  from "+bibE2.cTO()+".jt_adressklasse_def",
					false,true,false,false,new Extent(120));

			//2013-04-09: selektion der Firmen auch nach mitarbeiter 2
//			oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterADR,"JT_ADRESSE.ID_USER = #WERT#", null, null));
			oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterADR,"(NVL(JT_ADRESSE.ID_USER,-1) = #WERT#  OR NVL(JT_ADRESSE.ID_USER_ERSATZ,-1) = #WERT#)", null, null));
			oSelVector.add(new E2_ListSelectorStandard(oSelectLand,"JT_ADRESSE.ID_LAND = #WERT#", null, null));
			oSelVector.add(new E2_ListSelectorStandard(oSelectAdressKlasse,"JT_ADRESSE.ID_ADRESSE in (select JT_ADRESSKLASSE.ID_ADRESSE from JT_ADRESSKLASSE where ID_ADRESSKLASSE_DEF=#WERT#)", null, null));
			oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitAngebot.get_oSelKunden(),"JT_ADRESSE.ID_ADRESSE=#WERT# ", null, null));

			
			MyE2_Grid oGrid = new MyE2_Grid(8,0);
			
			oGrid.add(new MyE2_Label(new MyE2_String("Bearbeiter (1 od.2):")),1,E2_INSETS.I_0_2_2_2);		oGrid.add(oSelectMitarbeiterADR,1,E2_INSETS.I_2_2_10_2);	
			oGrid.add(new MyE2_Label(new MyE2_String("Land:")),1,E2_INSETS.I_0_2_2_2);					oGrid.add(oSelectLand,1,E2_INSETS.I_2_2_10_2);				
			oGrid.add(new MyE2_Label(new MyE2_String("Adr.Klasse:")),1,E2_INSETS.I_0_2_2_2);			oGrid.add(oSelectAdressKlasse,1,E2_INSETS.I_2_2_10_2);
			oGrid.add(oSelKundenMitAngebot,1,E2_INSETS.I_2_2_10_2);
			oGrid.add(oSelVector.get_AktivPassivComponent());

			this.add(oGrid);
			
		}

		public E2_SelectionComponentsVector get_oSelVector() 		{			return oSelVector;		}
		public MyE2_SelectField get_oSelectAdressKlasse()			{			return oSelectAdressKlasse;		}
		public MyE2_SelectField get_oSelectLand()					{			return oSelectLand;		}
		public MyE2_SelectField get_oSelectMitarbeiterADR()			{			return oSelectMitarbeiterADR;		}

	}
	
	
	
	/*
	 * eigene selektionsklasse mit ueberschriebenem actionPerformed()
	 */
	private class ownSelectionsComponentVector extends E2_SelectionComponentsVector
	{
		public ownSelectionsComponentVector() throws myException 
		{
			super(null, new actionReload(),new agentForSelector(false));
		}
	}
 
	
	//actionAgent fuer den reload-button im E2_SelectionComponentsVector
	private class actionReload extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			new agentForSelector(true).ExecuteAgentCode(new ExecINFO_OnlyCode());
		}
	}


	//actionAgent fuer den eigene selector
	private class agentForSelector extends XX_ActionAgent
	{

		private boolean bReloadButton = false;
		

		public agentForSelector(boolean reloadButton) {
			super();
			bReloadButton = reloadButton;
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			Vector<String> vBedingungen = new Vector<String>();
			
			BSAAL_ButtonBauePDFs_Container oThis = BSAAL_ButtonBauePDFs_Container.this;
			/*
			 * gestartet wird, wenn der reload-button gedrueckt wird oder wenn
			 * der schalter passiv nicht gesetzt ist !!
			 */
			boolean bStart = (!oThis.oSelector.get_oSelVector().get_oCheckPassiv().isSelected() || this.bReloadButton);
			
			if (bStart)
			{
					
				for (int i=0;i<oThis.oSelector.get_oSelVector().size();i++)
				{
				   XX_ListSelektor oSel = (XX_ListSelektor)oThis.oSelector.get_oSelVector().get(i);
				   if (!oSel.get_WhereBlock().trim().equals(""))
					   vBedingungen.add(oSel.get_WhereBlock());
				}
				
				vBedingungen.add("JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO);
				
				String cSQLWhereZusatz =  "AND JT_VKOPF_STD.ID_ADRESSE IN (SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ";
				cSQLWhereZusatz += bibALL.Concatenate(vBedingungen," AND ", null)+")";
				
				//falls die bedingung nur aus einem eintrag <vBedingungen.add("JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO);>
				//besteht, diese leer machen
				if (vBedingungen.size()==1)
				{
					cSQLWhereZusatz="";
				}
				
				oThis.Query_And_Build_List(cSQLWhereZusatz);
			}
		}
	}
	
	
	/*
	 * objekt fuer die darstellung der liste (mit checkbox, info, ob bereits erledigt und name+ort
	 */
	private class ownRow
	{
		private BSAAL_ButtonBauePDFs_JasperHASH  	jasperVKOPF = null;
		private MyE2_CheckBox   					oCB_Selected =  new MyE2_CheckBox();
		
		public ownRow(BSAAL_ButtonBauePDFs_JasperHASH JasperVKOPF) throws myException
		{
			this.jasperVKOPF = JasperVKOPF;
		}
		
		public MyE2_Row getRow() throws myException
		{
			MyE2_Row rowRueck = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			
			rowRueck.add(this.oCB_Selected, E2_INSETS.I_0_0_2_0);
			rowRueck.add(new MyE2_Label(this.jasperVKOPF.get_REC_VKOPF_STD().is_ABGESCHLOSSEN_YES()?E2_ResourceIcon.get_RI("locked.png"):E2_ResourceIcon.get_RI("empty20.png")), E2_INSETS.I_0_0_2_0);
			if (S.isFull(this.jasperVKOPF.get_HM_FILENAME_OF_TEMP_FILE()))
			{
				MyE2_Button oButtonDownload = new MyE2_Button(E2_ResourceIcon.get_RI("pdf2.png"));
				oButtonDownload.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo)	throws myException
					{
						ownRow.this.jasperVKOPF.get_oTempFileWithSendeName().Download_File();
					}
				});
				rowRueck.add(oButtonDownload, E2_INSETS.I_0_0_2_0);
			}
			else
			{
				rowRueck.add(new MyE2_Label(E2_ResourceIcon.get_RI("empty20.png")), E2_INSETS.I_0_0_2_0);
			}
			rowRueck.add(new MyE2_Label(this.jasperVKOPF.get_REC_VKOPF_STD().get___KETTE(bibALL.get_Vector("NAME1", "ORT","()@ID_ADRESSE"), "-", "", "", " - "),MyE2_Label.STYLE_SMALL_ITALIC()));
			
			return rowRueck;
		}
		
		public boolean isSelected()
		{
			return this.oCB_Selected.isSelected();
		}
		public MyE2_CheckBox get_oCB()
		{
			return this.oCB_Selected;
		}
		public BSAAL_ButtonBauePDFs_JasperHASH get_JasperVKOPF()
		{
			return jasperVKOPF;
		}
	}
	
	
}
