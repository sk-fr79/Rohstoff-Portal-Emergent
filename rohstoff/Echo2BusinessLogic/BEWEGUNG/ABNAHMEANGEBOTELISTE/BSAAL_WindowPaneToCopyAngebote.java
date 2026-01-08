package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea_WithSelektorEASY;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.myDataRecordCopySQLString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class BSAAL_WindowPaneToCopyAngebote extends E2_BasicModuleContainer
{

	public static int iHeight = 500;
	
	private BSAAL__ModulContainerLIST	oModulContainerList = null;
	private MyE2_Column					oColumnBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
	
	private E2_DateBrowser				oCalendarGueltigVon = null;
	private E2_DateBrowser				oCalendarGueltigBis = null;

	private MyE2_TextArea_WithSelektorEASY	oTextAnfang = new MyE2_TextArea_WithSelektorEASY("PREISINFOANFANG");
	private MyE2_TextArea_WithSelektorEASY	oTextEnde = new MyE2_TextArea_WithSelektorEASY("PREISINFOENDE");

	
	private MyE2_Button					oButtonSTART = new MyE2_Button(new MyE2_String("BAUE KOPIEN"));
	
	private Vector<String> 				vIDS_Positionen = null;    // positionen die kopiert werden muessen
	
	private MyDBToolBox					oDB = bibALL.get_myDBToolBox();
	private MyDBToolBox					oDB2 = bibALL.get_myDBToolBox();
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
		bibALL.destroy_myDBToolBox(this.oDB2);
	}
	
	
	public BSAAL_WindowPaneToCopyAngebote( BSAAL__ModulContainerLIST	oModulContainer, Vector<String> vSelectedPosIDs)  throws myException
	{
		super();
		this.oModulContainerList = oModulContainer;
		
		this.vIDS_Positionen = vSelectedPosIDs;
		if (this.vIDS_Positionen.size()==0)
			throw new myException("Error! No selected positions to copy !");

		this.oDB2.set_bErsetzungTableView(false);             // fuer die kopierfunktion noetig
		this.oDB2.setZusatzFelder(null);
		
		this.oButtonSTART.setFont(new E2_FontBold(4));
		this.oButtonSTART.setForeground(Color.RED);
		this.oButtonSTART.add_oActionAgent(new ownActionAgentStartCopy());


		// zeiten des ersten angebotes einlesen und anzeigen
		String cQueryDatumsBereich = "SELECT TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'DD.MM.YYYY'),TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'DD.MM.YYYY') " +
									" FROM "+
									bibE2.cTO()+".JT_VPOS_STD_ANGEBOT " +
									" WHERE  JT_VPOS_STD_ANGEBOT.ID_VPOS_STD="+(String)this.vIDS_Positionen.get(0);
		

		String[][] cDatumsbereich = this.oDB.EinzelAbfrageInArray(cQueryDatumsBereich,"");
		if (cDatumsbereich==null || cDatumsbereich.length==0)
			throw new myException("Error! Cannot query old date-range !");
		
		this.oCalendarGueltigVon = new E2_DateBrowser();
		this.oCalendarGueltigBis = new E2_DateBrowser();
		this.oCalendarGueltigVon.get_oDatumsFeld().setText(cDatumsbereich[0][0]);
		this.oCalendarGueltigBis.get_oDatumsFeld().setText(cDatumsbereich[0][1]);
		this.oCalendarGueltigVon.DO_EvaluateActualText();
		this.oCalendarGueltigBis.DO_EvaluateActualText();
		

		this.oTextAnfang.get_oTextArea().set_iWidthPixel(800);
		this.oTextEnde.get_oTextArea().set_iWidthPixel(800);
		this.oTextAnfang.get_oTextArea().set_iRows(6);
		this.oTextEnde.get_oTextArea().set_iRows(6);

		MyE2_Grid oGridForText = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oGridForText.add(new MyE2_Label(new MyE2_String("Einführung:")), E2_INSETS.I_0_2_10_2);
		oGridForText.add(this.oTextAnfang, E2_INSETS.I_0_2_10_2);
		oGridForText.add(new MyE2_Label(new MyE2_String("Abschluss:")), E2_INSETS.I_0_2_10_2);
		oGridForText.add(this.oTextEnde, E2_INSETS.I_0_2_10_2);
		
		this.oColumnBasic.add(oGridForText,E2_INSETS.I_10_2_10_2);
		this.oColumnBasic.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Gültig von-bis :")),this.oCalendarGueltigVon,this.oCalendarGueltigBis, new Insets(0,0,10,0)),E2_INSETS.I_10_2_10_2);
		
		
		MyE2_Button	oButtonCloseWindow = new MyE2_Button(new MyE2_String("Fenster schliessen"));
		oButtonCloseWindow.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Aktion wurde abgebrochen !"), false);
						BSAAL_WindowPaneToCopyAngebote.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				});

		MyE2_Column	oColumnButtons = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		oColumnButtons.add(new E2_ComponentGroupHorizontal(0,this.oButtonSTART,oButtonCloseWindow,new Insets(0,0,20,0)),new Insets(10,20,10,2));
		
		this.add(oColumnBasic);
		this.set_Component_To_ButtonPane(oColumnButtons);
		
		
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				if (this.get_oContainer().get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_SAVE))
				{
					BSAAL_WindowPaneToCopyAngebote.this.oModulContainerList.get_oSelector().do_RefreshSelectDateRange();
					BSAAL_WindowPaneToCopyAngebote.this.oModulContainerList.get_oSelector().get_oSelVector().doActionPassiv();
				}

			}
		});
		
		
		
		this.CREATE_AND_SHOW_POPUPWINDOW(	new Extent(1000), 
											new Extent(BSAAL_WindowPaneToCopyAngebote.iHeight),
											true,
											new Extent(BSAAL_WindowPaneToCopyAngebote.iHeight-100),
											new MyE2_String("Abnahmeangebote kopieren"));
	}

	

	
	private class ownActionAgentStartCopy extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			BSAAL_WindowPaneToCopyAngebote oThis = BSAAL_WindowPaneToCopyAngebote.this;

			try
			{
				// zuerst die kopf-ids besorgen
				String cWhereBlock = "JT_VPOS_STD.ID_VPOS_STD IN("+bibALL.Concatenate(oThis.vIDS_Positionen,",", null)+")";
				
				String cSQL = "SELECT DISTINCT JT_VKOPF_STD.ID_VKOPF_STD FROM "+bibE2.cTO()+".JT_VKOPF_STD,"+bibE2.cTO()+
									".JT_VPOS_STD WHERE JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD AND "+cWhereBlock;
				
				String[][] cIDsKopf = oThis.oDB.EinzelAbfrageInArray(cSQL); 
				
				if (cIDsKopf==null || cIDsKopf.length==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Kopieren !"));
				}
				else
				{
	
					TestingDate oDateVon = new TestingDate(oThis.oCalendarGueltigVon.get_oDatumsFeld().getText());
					TestingDate oDateBis = new TestingDate(oThis.oCalendarGueltigBis.get_oDatumsFeld().getText());
					
					if (!(oDateVon.testing() & oDateBis.testing()))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Datumsangaben sind fehlerhaft !"));
					}
					else if (oDateVon.get_Calendar().after(oDateBis.get_Calendar()))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Datumsreihenfolge ist nicht korrekt !"));
					}
					else if (bibALL.isEmpty(oThis.oTextAnfang.get_oTextArea().getText()) | bibALL.isEmpty(oThis.oTextEnde.get_oTextArea().getText()))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben Einleitungs- und Abschlusstext ein !"));
					}
					else
					{
						HashMap<String,String> hmErsatzFieldsKopf = new HashMap<String,String>();
						hmErsatzFieldsKopf.put("ID_VKOPF_STD","SEQ_VKOPF_STD.NEXTVAL");
						hmErsatzFieldsKopf.put("ID_USER",bibALL.get_ID_USER());
						hmErsatzFieldsKopf.put("FORMULARTEXT_ANFANG",bibALL.MakeSql(oThis.oTextAnfang.get_oTextArea().getText()));
						hmErsatzFieldsKopf.put("FORMULARTEXT_ENDE",bibALL.MakeSql(oThis.oTextEnde.get_oTextArea().getText()));
						hmErsatzFieldsKopf.put("DELETED","'N'");
						
						HashMap<String,String> hmErsatzFieldsPos = new HashMap<String,String>();
						hmErsatzFieldsPos.put("ID_VPOS_STD","SEQ_VPOS_STD.NEXTVAL");
						hmErsatzFieldsPos.put("ID_VKOPF_STD","SEQ_VKOPF_STD.CURRVAL");
						hmErsatzFieldsPos.put("EINZELPREIS","NULL");
						hmErsatzFieldsPos.put("DELETED","'N'");
		
						HashMap<String,String> hmErsatzFieldsPosAngebot = new HashMap<String,String>();
						hmErsatzFieldsPosAngebot.put("ID_VPOS_STD_ANGEBOT","SEQ_VPOS_STD_ANGEBOT.NEXTVAL");
						hmErsatzFieldsPosAngebot.put("ID_VPOS_STD","SEQ_VPOS_STD.CURRVAL");
						hmErsatzFieldsPosAngebot.put("GUELTIG_VON",oDateVon.get_ISO_DateFormat(true));
						hmErsatzFieldsPosAngebot.put("GUELTIG_BIS",oDateBis.get_ISO_DateFormat(true));
						hmErsatzFieldsPosAngebot.put("DELETED","'N'");
		
						Vector<String> vSQL_Stack = new Vector<String>();
		
						int iPosCount = 0;
						for (int i=0;i<cIDsKopf.length;i++)
						{
							myDataRecordCopySQLString oCopyHead = new myDataRecordCopySQLString("JT_VKOPF_STD","ID_VKOPF_STD",cIDsKopf[i][0],null,null,null, hmErsatzFieldsKopf, true);
							
							vSQL_Stack.add(oCopyHead.get_cINSERT_String());
							
							// jetzt die beteiligten positionen-ids raussuchen
							String cSQL_PosQuery = "SELECT JT_VPOS_STD.ID_VPOS_STD,JT_VPOS_STD_ANGEBOT.ID_VPOS_STD_ANGEBOT " +
									" FROM "+
									bibE2.cTO()+".JT_VPOS_STD," +bibE2.cTO()+".JT_VPOS_STD_ANGEBOT" +
									" WHERE " +
									" JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD AND "+
									" JT_VPOS_STD.ID_VKOPF_STD="+cIDsKopf[i][0];
							
							String[][] cPosID=oThis.oDB.EinzelAbfrageInArray(cSQL_PosQuery);
							if (cPosID == null || cPosID.length==0)
								throw new myException("Error querying positions to head: "+cIDsKopf[i][0]);
							
							
							for (int k=0;k<cPosID.length;k++)
							{
								myDataRecordCopySQLString oCopyPos = new myDataRecordCopySQLString("JT_VPOS_STD","ID_VPOS_STD",cPosID[k][0],null,null,null, hmErsatzFieldsPos, true);
								vSQL_Stack.add(oCopyPos.get_cINSERT_String());
								myDataRecordCopySQLString oCopyPosAngebot = new myDataRecordCopySQLString("JT_VPOS_STD_ANGEBOT","ID_VPOS_STD_ANGEBOT",cPosID[k][1],null,null,null, hmErsatzFieldsPosAngebot, true);
								vSQL_Stack.add(oCopyPosAngebot.get_cINSERT_String());
								
								iPosCount++;
							}
							
						}
		
						bibMSG.add_MESSAGE(oThis.oDB2.ExecMultiSQLVector(vSQL_Stack,true));
						if (bibMSG.get_bIsOK())
						{
							MyE2_String oMessage = new MyE2_String("Anzahl neue Angebote: ");
							oMessage.addUnTranslated(""+cIDsKopf.length);
							oMessage.addString(new MyE2_String(".... Gesamte neue Positionen "));
							oMessage.addUnTranslated(""+iPosCount);
							bibMSG.add_MESSAGE(new MyE2_Info_Message(oMessage), false);
							oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error SQL-Execution ..."));
							bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vSQL_Stack);
						}
					}	
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}


}
