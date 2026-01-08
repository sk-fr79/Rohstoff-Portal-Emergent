package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;
import rohstoff.utils.MyVPOS_ANGEBOT;

public class BSAAL_POPUP_WINDOW_CHANGE_DATES extends E2_BasicModuleContainer
{
	
	public static int   				iWINDOW_WIDTH = 450;
	public static int   				iWINDOW_HEIGHT = 300;
	
	private Vector<String> 				vIDsToChange = null;
	
	private MyE2_Button 				oButtonSave = new MyE2_Button(new MyE2_String("Speichern"));
	private MyE2_Button 				oButtonCancel = new MyE2_Button(new MyE2_String("Abbruch"));
	
	private E2_DateBrowser  			oDateBrowserVon = new E2_DateBrowser();
	private E2_DateBrowser  			oDateBrowserBis = new E2_DateBrowser();
	
	private E2_NavigationList  			oNaviList = null;
	

	
	public BSAAL_POPUP_WINDOW_CHANGE_DATES(		Vector<String> 		IDsToChange, 
												E2_NavigationList  	NaviList)
	{
		super();
		
		
		this.set_bVisible_Row_For_Messages(true);
		
		this.vIDsToChange = IDsToChange;
		this.oNaviList = NaviList; 
		
		
		MyE2_Grid oGridBase = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER());
		
		oGridBase.setWidth(new Extent(100, Extent.PERCENT));
		
		
		oGridBase.add(new MyE2_Label("gültig von ..."),1,E2_INSETS.I_10_10_10_10);
		oGridBase.add(new E2_ComponentGroupHorizontal(0,this.oDateBrowserVon,E2_INSETS.I_0_0_0_0),1,E2_INSETS.I_10_10_10_10);
		oGridBase.add(new MyE2_Label("gültig bis ..."),1,E2_INSETS.I_10_10_10_10);
		oGridBase.add(new E2_ComponentGroupHorizontal(0,this.oDateBrowserBis,E2_INSETS.I_0_0_0_0),1,E2_INSETS.I_10_10_10_10);
		this.add(oGridBase);
		
		this.get_oSplitPane().setSeparatorPosition(new Extent(iWINDOW_HEIGHT-80));
		
		// jetzt mit dem ersten gefundenen datum fuellen
		try
		{
			MyVPOS_ANGEBOT oPosAngebot = new MyVPOS_ANGEBOT((String)this.vIDsToChange.get(0));
			
			this.oDateBrowserVon.get_oDatumsFeld().setText(oPosAngebot.get_hmVPOS_STD_ANGEBOT().get_FormatedValue("GUELTIG_VON"));
			this.oDateBrowserBis.get_oDatumsFeld().setText(oPosAngebot.get_hmVPOS_STD_ANGEBOT().get_FormatedValue("GUELTIG_BIS"));
			
			MyE2_Row oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			
			oRow.add(this.oButtonSave,E2_INSETS.I_10_10_10_10);
			oRow.add(this.oButtonCancel,E2_INSETS.I_10_10_10_10);
			
			this.set_Component_To_ButtonPane(oRow);
			
			this.oButtonCancel.add_oActionAgent(new ownActionCancel());
			this.oButtonSave.add_oActionAgent(new ownActionAgentSave());
			
			this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(	new Extent(BSAAL_POPUP_WINDOW_CHANGE_DATES.iWINDOW_WIDTH),
													new Extent(BSAAL_POPUP_WINDOW_CHANGE_DATES.iWINDOW_HEIGHT),
													new MyE2_String("Gültigkeit des Angebots ändern"));
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		
	}

	
	private class ownActionCancel extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSAAL_POPUP_WINDOW_CHANGE_DATES.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Datumsänderung abgebrochen !!"));
		}
		
	}
	

	private class ownActionAgentSave extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			BSAAL_POPUP_WINDOW_CHANGE_DATES oThis = BSAAL_POPUP_WINDOW_CHANGE_DATES.this;
			
			TestingDate oTDateVon = new TestingDate(oThis.oDateBrowserVon.get_oDatumsFeld().getText());
			TestingDate oTDateBis = new TestingDate(oThis.oDateBrowserBis.get_oDatumsFeld().getText());
			
//			oThis.oLabelInfo.set_Text("");
//			
			
			if (oTDateVon.testing() & oTDateBis.testing())
			{

				if (oTDateBis.get_Calendar().before(oTDateVon.get_Calendar()))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte beachten Sie die Datumsreihenfolge der Gültigkeit!"));
					return;
				}
					

				
				String cDateDB_Von = oTDateVon.get_ISO_DateFormat(true);
				String cDateDB_Bis = oTDateBis.get_ISO_DateFormat(true);
				
				Vector<String> vSQLStatements = new Vector<String>();
				
				for (int i=0;i<oThis.vIDsToChange.size();i++)
				{
					vSQLStatements.add("UPDATE "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT SET GUELTIG_VON="+cDateDB_Von+",GUELTIG_BIS="+cDateDB_Bis+" WHERE "+
										"ID_VPOS_STD="+(String)oThis.vIDsToChange.get(i));
				}

				try
				{
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQLStatements,true));
					
					if (bibMSG.get_bIsOK())
					{
						MyE2_String oMeldung = new MyE2_String("Geänderte Positionen: ");
						bibMSG.add_MESSAGE(new MyE2_Info_Message(oMeldung.CTrans()+" "+vSQLStatements.size(),false));
						
						// aenderung in die liste uebernehmen
						for (int i=0;i<oThis.vIDsToChange.size();i++)
						{
							oThis.oNaviList.Refresh_ComponentMAP((String)oThis.vIDsToChange.get(i),E2_ComponentMAP.STATUS_VIEW);
						}
						BSAAL_POPUP_WINDOW_CHANGE_DATES.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);

						// den ersten geaenderten satz markieren
						((E2_ComponentMAP)oThis.oNaviList.get_ComponentMAP((String)oThis.vIDsToChange.get(0))).set_Marker(true);
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim speichern !!  -> "), false);
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}

			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben Sie korrekte Datumswerte an !!"), false);
			}
		}
	}



}
