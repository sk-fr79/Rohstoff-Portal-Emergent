package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS.BSFP_LIST_BT_CREATE_BELEG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG.FUB___LIST_BT_Buchung;

public class FU__MASK_Button_SaveFromMask_And_FollowAction extends MyE2_Button 
{

	private FU_MASK_ModulContainer  oFuMaskContainer = null;
	private E2_NavigationList       oNaviList = null;
	private RECORD_VPOS_TPA_FUHRE   recFuhre = null;
	
	
	private int   					iAnzahlErzeugbareBelege = 0;
	
	
	public FU__MASK_Button_SaveFromMask_And_FollowAction(FU_MASK_ModulContainer FuMaskContainer, E2_NavigationList NaviList) 
	{
		super(new MyE2_String("Speichern und mehr ..."));
		this.oFuMaskContainer 	= 	FuMaskContainer;
		this.oNaviList			=	NaviList;
	
		this.add_GlobalAUTHValidator_AUTO("SPEICHERN_UND_FOLGENDE_AUS_MASKE");
		this.add_oActionAgent(new ownActionSaveAndCheckBuchung());
		
		this.setToolTipText(new MyE2_String("Fuhre speichern und wenn möglich gleich buchen ...").CTrans());
	}
	
	
	private class ownActionSaveAndCheckBuchung extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FU__MASK_Button_SaveFromMask_And_FollowAction oThis = FU__MASK_Button_SaveFromMask_And_FollowAction.this;
			new E2_SaveMaskStandard(oThis.oFuMaskContainer,oThis.oNaviList).doSaveMask(true);
			
			FU__MASK_Button_SaveFromMask_And_FollowAction.this.iAnzahlErzeugbareBelege = 0;    //reset
			
			if (bibMSG.get_bIsOK())
			{
				oThis.recFuhre = new RECORD_VPOS_TPA_FUHRE(oThis.oFuMaskContainer.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				
				if (recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-10)).intValue()==myCONST.STATUS_FUHRE__UNGEBUCHT)
				{
					new ownFrageContainerBUCHEN();
				}
				else
				{
					if (recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-10)).intValue()==myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS)
					{
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Fuhre kann nicht gebucht werden: Sie besitzt keine Buchungspositionen !")));
					}
					else if (	recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-10)).intValue()==myCONST.STATUS_FUHRE__TEILSGEBUCHT || 
								recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-10)).intValue()==myCONST.STATUS_FUHRE__GANZGEBUCHT)
					{
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Fuhre kann nicht gebucht werden: Sie ist bereits gebucht !")));
					}
					else if (	recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-10)).intValue()==myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG )
					{
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Fuhre kann nicht gebucht werden: Sie ist noch nicht komplett !")));
					}
					else if (	recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-10)).intValue()==myCONST.STATUS_FUHRE__IST_STORNIERT )
					{
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Fuhre kann nicht gebucht werden: Sie wurde storniert !")));
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Fuhre kann nicht gebucht werden!")));
					}
				}
			}
		}
	}
	

	
	private class ownFrageContainerBUCHEN extends E2_BasicModuleContainer
	{
		private int[] 					iBreite = {100,100};
		private MyE2_Button  			oButtonNein = new MyE2_Button(new MyE2_String("Schließe Fenster"));
		private FUB___LIST_BT_Buchung  	oButtonBuchen = null;
		private MyE2_Grid    		    oGridMitRGButtons = null;
		private MyE2_Label              oLabelInFrageWindow = null;

		
		
		public ownFrageContainerBUCHEN() throws myException
		{
			MyE2_Grid  oGrid = new MyE2_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			this.oLabelInFrageWindow = new MyE2_Label(new MyE2_String("Wollen Sie die Fuhre gleich verbuchen ?"),MyE2_Label.STYLE_TITEL_BIG());
			
			oGrid.add_raw(oLabelInFrageWindow, LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(5,10,5,40),2));
			
			this.oButtonNein.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					ownFrageContainerBUCHEN.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
			
			FU__MASK_Button_SaveFromMask_And_FollowAction		oThis = FU__MASK_Button_SaveFromMask_And_FollowAction.this;
			
			this.oButtonBuchen = new FUB___LIST_BT_Buchung(	oThis.oNaviList,
															oThis.recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
			
			this.oButtonBuchen.get_vAgents_4_BuchungsStartButton().add(new actionAgent_RELOAD_FUHRE_NachBuchung());
			this.oButtonBuchen.get_vAgents_4_BuchungsStartButton().add(new actionAgentSchalteWeiterVonBuchungZuRechnungsErstellung_OR_ClosePopup());

			//in diesem Grid werden die buttons zum oeffnen der vorgangspositionen angezeigt (wenn es jeweils genau eine RG-Position gibt) 
			this.oGridMitRGButtons = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			//this.oButtonBaueRG = new MyE2_Button(new MyE2_String("Rechnungen/Gutschriften erzeugen"));
			this.oGridMitRGButtons.setVisible(false);
			
			//ein Grid, das alle Buttons enthaelt, aber immer nur ein sichtbares
			MyE2_Grid  oGridHelp = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGridHelp.add(this.oButtonBuchen, E2_INSETS.I_0_0_0_0);
			oGridHelp.add(this.oGridMitRGButtons, E2_INSETS.I_0_0_0_0);

			
			oGrid.add_raw(oGridHelp,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(5,10,5,5)));
			oGrid.add_raw(oButtonNein,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(5,10,5,5)));
			
			this.add(oGrid, E2_INSETS.I_2_2_2_2);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(260), new MyE2_String("Fuhre buchen ?"));
			
		}
		
		
		private class actionAgent_RELOAD_FUHRE_NachBuchung extends XX_ActionAgent
		{

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				 FU__MASK_Button_SaveFromMask_And_FollowAction.this.oFuMaskContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(
						    E2_ComponentMAP.STATUS_EDIT,
						    FU__MASK_Button_SaveFromMask_And_FollowAction.this.recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
				 
				 //RECORD_VPOS_TPA_FUHRE neu aufbauen
				 FU__MASK_Button_SaveFromMask_And_FollowAction.this.recFuhre = new RECORD_VPOS_TPA_FUHRE( FU__MASK_Button_SaveFromMask_And_FollowAction.this.recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
			}
			
		}

		private class actionAgentSchalteWeiterVonBuchungZuRechnungsErstellung_OR_ClosePopup extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				if (FU__MASK_Button_SaveFromMask_And_FollowAction.this.recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__TEILSGEBUCHT ||
					FU__MASK_Button_SaveFromMask_And_FollowAction.this.recFuhre.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__GANZGEBUCHT)
				{
					ownFrageContainerBUCHEN oThis = ownFrageContainerBUCHEN.this;
					
					oThis.oButtonBuchen.setVisible(false);
					oThis.oLabelInFrageWindow.set_Text(new MyE2_String("Wollen Sie gleich Beleg(e) erzeugen ?"));
					
					//jetzt nachsehen, ob die fuhre auf der rechnungsseite genau eine buchung besitzt und auf der gutschriftseite auch
					RECORD_VPOS_TPA_FUHRE  recFuhreNeu = new RECORD_VPOS_TPA_FUHRE(FU__MASK_Button_SaveFromMask_And_FollowAction.this.recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
					
					RECLIST_VPOS_RG  reclistVPOS_RG = recFuhreNeu.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord();
					
					Vector<String> vIDs_rechpos = new Vector<String>();
					Vector<String> vIDs_gutpos = new Vector<String>();
					
					for (int i=0;i<reclistVPOS_RG.get_vKeyValues().size();i++)
					{
						RECORD_VPOS_RG  recRG = reclistVPOS_RG.get(i);

						boolean bBuchenOK = true;
						
						if (recRG.is_DELETED_YES())
						{
							bBuchenOK = false;
						}
						
						if (S.isFull(recRG.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")))
						{
							bBuchenOK = false;
						}

						if (S.isFull(recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
						{
							bBuchenOK = false;
						}
						
						if (S.isFull(recRG.get_ID_VKOPF_RG_cUF()))
						{
							bBuchenOK = false;
						}

						if (bBuchenOK)
						{
							if (recRG.get_LAGER_VORZEICHEN_lValue(new Long(0))==-1)
							{
								vIDs_rechpos.add(recRG.get_ID_VPOS_RG_cUF());
							}
							if (recRG.get_LAGER_VORZEICHEN_lValue(new Long(0))==1)
							{
								vIDs_gutpos.add(recRG.get_ID_VPOS_RG_cUF());
							}
						}
					}

					boolean bCloseOK = true;        //falls keine Belegerzeugung moeglich ist, dann das popup-fenster gleich schliessen
					
					//jetzt nachsehen, ob die fuhre genau EINE RG-position links und rechts erzeugt hat, wenn ja, dann die buttons einblenden
					if (vIDs_rechpos.size()==1)
					{
						BSFP_LIST_BT_CREATE_BELEG oButton_RE = new BSFP_LIST_BT_CREATE_BELEG(myCONST.VORGANGSART_RECHNUNG,vIDs_rechpos); 
						
						oButton_RE.add_oActionAgent(new actionCloseWindowWhenAllIsDone(), true);
						oButton_RE.add_oActionAgent(new actionSetDisabledOnClick(), true);
						
						ownFrageContainerBUCHEN.this.oGridMitRGButtons.add(oButton_RE );
						ownFrageContainerBUCHEN.this.oGridMitRGButtons.setVisible(true);
						FU__MASK_Button_SaveFromMask_And_FollowAction.this.iAnzahlErzeugbareBelege++;
						bCloseOK = false;
					}
					if (vIDs_gutpos.size()==1)
					{
						BSFP_LIST_BT_CREATE_BELEG oButton_GS =  new BSFP_LIST_BT_CREATE_BELEG(myCONST.VORGANGSART_GUTSCHRIFT,vIDs_gutpos);
						
						oButton_GS.add_oActionAgent(new actionCloseWindowWhenAllIsDone(), true);
						oButton_GS.add_oActionAgent(new actionSetDisabledOnClick(), true);
						
						ownFrageContainerBUCHEN.this.oGridMitRGButtons.add(oButton_GS);
						ownFrageContainerBUCHEN.this.oGridMitRGButtons.setVisible(true);
						FU__MASK_Button_SaveFromMask_And_FollowAction.this.iAnzahlErzeugbareBelege++;
						bCloseOK = false;
					}
					
					if (bCloseOK)
					{
						oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				}
			}
			
			//jeder buchungsbutton ist nur einmal klickbar
			private class actionSetDisabledOnClick extends XX_ActionAgent
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo)throws myException
				{
					MyE2_Button  oEigenerButton = ((MyE2_Button)oExecInfo.get_MyActionEvent().getSource());
					oEigenerButton.set_bEnabled_For_Edit(false);
				}
			}
			
			
			private class actionCloseWindowWhenAllIsDone extends XX_ActionAgent
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo)throws myException
				{
					FU__MASK_Button_SaveFromMask_And_FollowAction.this.iAnzahlErzeugbareBelege--;
					if (FU__MASK_Button_SaveFromMask_And_FollowAction.this.iAnzahlErzeugbareBelege<=0)
					{
						ownFrageContainerBUCHEN.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);   //wurden alle RGs, die moeglich sind erstellt, dann window schliessen
					}
				}
			}
			
		}
	}
}
