package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.staticStyles.Style_Grid_Normal;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import echopointng.Separator;

public abstract class FP_ActionAgent_UMBUCHEN extends XX_ActionAgent
{
	
	/*
	 * komponenten fuer das popup-fenster
	 */
	private MyE2_Button					oButtonSTART_BUCHUNG	= null;
	private E2_DateBrowser				oDateChooser = new E2_DateBrowser();
	private MyE2_SelectField 			oSelectLKW = null;

	private RECORD_VPOS_TPA_FUHRE  		oRecFuhre = null;
	private E2_BasicModuleContainer  	oPopUpcontainer = null;
	private E2_NavigationList        	oNaviList = null;
	private XX_ActionAgent           	oAgentNachSave = null;
	
	private RECLIST_VPOS_TPA_FUHRE   	recordListFuhre_4_Umbuchung = null;
	
	/**
	 * 
	 * @param naviList
	 * @param RecFuhre   (eines von beiden muss null sein, damit ids zur verfuegung stehen
	 * @param ZusatzActionNachSave
	 * @throws myException
	 */
	public FP_ActionAgent_UMBUCHEN(	E2_NavigationList  naviList, RECORD_VPOS_TPA_FUHRE RecFuhre, XX_ActionAgent ZusatzActionNachSave) throws myException
	{
		super();
		this.oNaviList = naviList;
		this.oRecFuhre = RecFuhre;
		this.oAgentNachSave = ZusatzActionNachSave;
		
		this.oButtonSTART_BUCHUNG = new MyE2_Button("Positionen Buchen");
		
		String cQuery = "SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
						bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
						" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
						" JT_MASCHINENTYP.IST_LKW='Y'  AND NVL(JT_MASCHINEN.AKTIV,'N')='Y' ORDER BY NVL(KFZKENNZEICHEN,'--') ";		
		try
		{
			this.oSelectLKW = new MyE2_SelectField(cQuery,false,false,false,false);
		}
		catch (myException ex)
		{
			throw new myExceptionForUser("Keine LKWs definiert !"+ex.get_ErrorMessage().get_cMessage().CTrans());
		}
		
		this.oButtonSTART_BUCHUNG.add_oActionAgent(new ownActionAgentStarte_UMBUCHUNG());
		if (this.oAgentNachSave!=null)
		{
			this.oButtonSTART_BUCHUNG.add_oActionAgent(this.oAgentNachSave);
		}
	}




	@Override
	public void executeAgentCode(ExecINFO execInfo) throws myException
	{
		MyE2_Button  oButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
		
		
		this.oPopUpcontainer = new E2_BasicModuleContainer();
		this.oPopUpcontainer.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
		
		//die umbuchungsfuhren werden in einer Reclist gesammelt
		this.recordListFuhre_4_Umbuchung = new RECLIST_VPOS_TPA_FUHRE();
		if (this.oRecFuhre != null)
		{
			this.recordListFuhre_4_Umbuchung.ADD(this.oRecFuhre, true);
		}
		else
		{
			recordListFuhre_4_Umbuchung.ADD(new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE IN ("+
															bibALL.Concatenate(this.oNaviList.get_vSelectedIDs_Unformated(), ",", "-1")+")"), true);
		}
		
		
		if (recordListFuhre_4_Umbuchung.get_vKeyValues().size()==0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens eine Fahrt auswählen !"));
			return;
		}
		

		if (bibMSG.get_bIsOK())
		{
			try
			{
				/*
				 * zuerst testen
				 */
				for (String cID_VPOS_TPA_FUHRE: this.recordListFuhre_4_Umbuchung.get_vKeyValues())
				{
					bibMSG.add_MESSAGE(oButton.valid_IDValidation(bibALL.get_Vector(cID_VPOS_TPA_FUHRE)));
				}

				if (bibMSG.get_bIsOK())
				{
					this.oDateChooser.get_oDatumsFeld().setText(this.recordListFuhre_4_Umbuchung.get(0).get_DAT_FAHRPLAN_FP_cF_NN(""));
					this.oSelectLKW.set_ActiveValue_OR_FirstValue(this.recordListFuhre_4_Umbuchung.get(0).get_ID_MASCHINEN_LKW_FP_cUF_NN("0"));
					
					MyE2_Grid    	oGrid	= new MyE2_Grid(2,new Style_Grid_Normal(0,new Insets(2)));
	
					
					// Window bestuecken ...
					// oGrid nimmt die elemente auf
					MyE2_Label oLabTitel = new MyE2_Label(new MyE2_String("Fahrten in einen (anderen) Fahrplan buchen"));
					oLabTitel.setFont(new E2_FontPlain(2));
					oGrid.add(oLabTitel,2,new Insets(6,10,2,10));
					oGrid.add(new Separator(),2,new Insets(2,2,2,2));
					oGrid.add(new MyE2_Label(new MyE2_String("Datum Zielfahrplan: ")),new Insets(6,2,2,2));
					oGrid.add(this.oDateChooser,new Insets(2,2,2,2));
					oGrid.add(new MyE2_Label(new MyE2_String("LKW Zielfahrplan: ")),new Insets(6,2,2,2));
					oGrid.add(this.oSelectLKW,new Insets(2,2,2,2));
					oGrid.add(new Separator(),2,new Insets(2,2,2,2));
					
					MyE2_Button oButtonCancel = new MyE2_Button(new MyE2_String("Abbruch"));
					oButtonCancel.add_oActionAgent(new XX_ActionAgent()
					{
						public void executeAgentCode(ExecINFO oExecInfo) throws myException
						{
							FP_ActionAgent_UMBUCHEN.this.oPopUpcontainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
						}
					}
					);
					
					oPopUpcontainer.add(oGrid);
					oPopUpcontainer.set_Component_To_ButtonPane( new E2_ComponentGroupHorizontal(0,this.oButtonSTART_BUCHUNG,oButtonCancel,E2_INSETS.I_10_2_10_2));
	
					/*
					 * anzeigen des fensters
					 */
					oPopUpcontainer.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(400), new Extent(300),new MyE2_String("Fahrten umordnen ..."));
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Auswahl der Fahrten !"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}

	
	
	private class ownActionAgentStarte_UMBUCHUNG extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FP_ActionAgent_UMBUCHEN oThis = FP_ActionAgent_UMBUCHEN.this;

			try
			{
				boolean bAktionAusFahrplan = true;                //der button kann aus dem fahrplan oder aus dem pool gestartete werden
				if (S.isEmpty(oThis.recordListFuhre_4_Umbuchung.get(0).get_ID_MASCHINEN_LKW_FP_cUF()) || S.isEmpty(oThis.recordListFuhre_4_Umbuchung.get(0).get_DAT_FAHRPLAN_FP_cUF()))
				{
					bAktionAusFahrplan = false;
				}

				
				String cNewDate = FP_ActionAgent_UMBUCHEN.this.oDateChooser.get_oDatumsFeld().getText();
				String cID_LKW_NEU =  FP_ActionAgent_UMBUCHEN.this.oSelectLKW.get_ActualWert();

				new FP__UMBUCHER(oThis.recordListFuhre_4_Umbuchung,cNewDate,cID_LKW_NEU);
				
				if (bibMSG.get_bIsOK())
				{
					
					oThis.aktion_nach_umbuchen(cID_LKW_NEU);
					
					
					FP_ActionAgent_UMBUCHEN.this.oPopUpcontainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
					if (oThis.oNaviList != null)
					{
						/*
						 * wenn es einen konkreten fahrplan betrifft, dann wird die umbuchung
						 * mit einem kompletten neuaufbau beendet, da die liste sich geaendert hat
						 */
						if (bAktionAusFahrplan)
						{
							/*
							 * jetzt den ersten, nicht in der umbuchungsliste vorhandenen datensatz markieren,
							 * dann wird nach dem seitenaufbau wieder darauf plaziert
							 */
							Vector<String> vActualSiteIDs = oThis.oNaviList.get_AllVisibleIDs_Unformated();
							String cTrefferID = "";
							for (int i=0;i<vActualSiteIDs.size();i++)
							{
								if (!oThis.recordListFuhre_4_Umbuchung.get_vKeyValues().contains(vActualSiteIDs.get(i)))
								{
									cTrefferID = (String)vActualSiteIDs.get(i);
									break;
								}
							}
							if (!cTrefferID.equals(""))
							{
								oThis.oNaviList.Mark_ID_IF_IN_Page(cTrefferID);
							}
							
							oThis.oNaviList._REBUILD_COMPLETE_LIST("");
						}
						else
						{
							oThis.oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();
							oThis.oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);
						}
					}
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("FP_BUTTON_UMBUCHEN:ownActionAgentSave:doAction: Error: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	
	
	public abstract void aktion_nach_umbuchen(String cID_MASCHINEN_ZIEL_Umbuchung) throws myException;
	
}
