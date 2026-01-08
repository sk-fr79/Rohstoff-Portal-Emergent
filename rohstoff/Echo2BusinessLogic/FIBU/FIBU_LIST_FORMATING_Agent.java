package rohstoff.Echo2BusinessLogic.FIBU;


import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN2.FIBU_LIST_BUTTONS_Mahnung_NG;
import rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3.FIBU_MAHNUNG_List_Button;

public class FIBU_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	private E2_NavigationList   oNaviList = null;
	private boolean             MacheGeschlossenListZeilenGrau = false;



	public FIBU_LIST_FORMATING_Agent(E2_NavigationList NaviList, boolean bMacheGeschlossenListZeilenGrau)
	{
		super();
		this.oNaviList = NaviList;
		this.MacheGeschlossenListZeilenGrau = bMacheGeschlossenListZeilenGrau;
	}

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		String BUCHUNGSTYP = oUsedResultMAP.get_UnFormatedValue("BUCHUNGSTYP");

		MyE2_DB_Label_INROW  oLabTyp = (MyE2_DB_Label_INROW)oMAP.get__Comp("BUCHUNGSTYP");

		String cKurz = myCONST.HM_BUCHUNGSTYP_KUERZEL.get(BUCHUNGSTYP);
		RECORD_FIBU  		recFibu = new RECORD_FIBU(oUsedResultMAP.get_cUNFormatedROW_ID());


		if (S.isEmpty(cKurz))
		{
			cKurz = "ERROR !!!";
		}
		oLabTyp.get_oErsatzButton().setText(cKurz);
		oLabTyp.get_oErsatzButton().setToolTipText(new MyE2_String(myCONST.HM_BUCHUNGSTYP_TOOLTIPS.get(oUsedResultMAP.get_UnFormatedValue("BUCHUNGSTYP"))).CTrans());


		FIBU_BT_LIST_OPEN_RECH_GUT  oButtonRechGut = (FIBU_BT_LIST_OPEN_RECH_GUT)oMAP.get__Comp("ID_VKOPF_RG");
		if (oUsedResultMAP.get_LActualDBValue("ID_VKOPF_RG", true)>0)
		{
			oButtonRechGut.setText(S.NN(oUsedResultMAP.get_FormatedValue("VKOPF_BUCHUNGSNUMMER")));
			oButtonRechGut.setToolTipText("ID:"+oUsedResultMAP.get_LActualDBValue("ID_VKOPF_RG", true));
		}
		else
		{
			oButtonRechGut.setText("-");
			oButtonRechGut.setToolTipText(new MyE2_String("Keine Buchung vom Typ: Rechnung/Gutschrift").CTrans());
		}

		if (oUsedResultMAP.get_UnFormatedValue("STORNIERT").equals("Y"))
		{
			oMAP.set_AllComponentsAsDeleted();
		}
		else if (this.MacheGeschlossenListZeilenGrau && oUsedResultMAP.get_UnFormatedValue(RECORD_FIBU.FIELD__BUCHUNG_GESCHLOSSEN).equals("Y"))
		{
			oMAP.set_AllComponentsWithForeColor(Color.DARKGRAY);
		}


		BigDecimal SummeBelege = 		oUsedResultMAP.get_bdActualValue("BLOCK_SUMME_BELEGE", true);
		BigDecimal SummeZahlungen = 	oUsedResultMAP.get_bdActualValue("BLOCK_SUMME_ZAHLUNGEN", true);
		BigDecimal SummeZahlungenVorzeichen = 	SummeZahlungen.multiply(new BigDecimal(-1));

		BigDecimal BetragOffen =        SummeBelege.subtract(SummeZahlungenVorzeichen);


		//die block-info-spalten werden nur gefuehrt, wenn die Sortierung korrekt ist und es die letzte zeile im Buchungsblock ist
		((MyE2_Label)oMAP.get__Comp(FIBU_CONST.KEY_SPALTE_BETRAGBLOCK)).setText(" - ");
		((MyE2_Label)oMAP.get__Comp(FIBU_CONST.KEY_SPALTE_SUMME_ZAHLUNGEN)).setText(" - ");
		((MyE2_Label)oMAP.get__Comp(FIBU_CONST.KEY_SPALTE_REST)).setText(" - ");

		if (FIBU_CONST.ORDER_TYPES.contains(this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_vOrderFields().get(0)))
		{
			if (oUsedResultMAP.get_LActualDBValue("GROESSTE_ID_BUCHUNGSBLOCK", true).intValue()==oUsedResultMAP.get_LActualDBValue("ID_FIBU", false).intValue())
			{
				if (oUsedResultMAP.get_LActualDBValue("BLOCK_ANZAHL", false).intValue()>1)
				{
					((MyE2_Label)oMAP.get__Comp(FIBU_CONST.KEY_SPALTE_BETRAGBLOCK)).setText(MyNumberFormater.formatDez(SummeBelege,2,true));
					((MyE2_Label)oMAP.get__Comp(FIBU_CONST.KEY_SPALTE_SUMME_ZAHLUNGEN)).setText(MyNumberFormater.formatDez(SummeZahlungenVorzeichen,2,true));
					((MyE2_Label)oMAP.get__Comp(FIBU_CONST.KEY_SPALTE_REST)).setText(MyNumberFormater.formatDez(BetragOffen,2,true));

				}
			}
		}



		FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK btBuchBlock = (FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK)oMAP.get__Comp("BUCHUNGSBLOCK_NR");
		if (oUsedResultMAP.get_LActualDBValue("BLOCK_ANZAHL", true).longValue()>1)
		{
			btBuchBlock.setText(btBuchBlock.getText()+" ("+oUsedResultMAP.get_LActualDBValue("BLOCK_ANZAHL", true).longValue()+")");
		}


		//2011-02-19: infos zu fibu in die liste einblenden
		RECORD_ADRESSE recAdresse = new RECORD_ADRESSE(oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE"));
		RECLIST_ADRESSE_INFO  recListAdressInfosFibu = recAdresse.get_DOWN_RECORD_LIST_ADRESSE_INFO_id_adresse("NVL(IST_MESSAGE,'N')='Y' AND MESSAGE_TYP="+bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_FIBU), null, true);
		if (recListAdressInfosFibu.get_vKeyValues().size()>0)
		{
			MyE2_Grid_InLIST oGrid4Infos = (MyE2_Grid_InLIST)oMAP.get(FIBU_CONST.KEY_SPALTE_FIBU_INFOS_AUS_ADRESSE);
			oGrid4Infos.removeAll();
			for (int i=0;i<recListAdressInfosFibu.get_vKeyValues().size();i++)
			{
				oGrid4Infos.add(new MyE2_Label(recListAdressInfosFibu.get(i).get_TEXT_cUF_NN("<leer ??>"),new E2_FontPlain(),true),E2_INSETS.I_1_0_1_0);
			}
		}


		//2011-03-09: zahlungsziel in der Fibu markieren, wenn es geaendert wurde
		FIBU_LIST_BT_ToEdit_ZahlungsZiel oBT = (FIBU_LIST_BT_ToEdit_ZahlungsZiel)oMAP.get__Comp("ZAHLUNGSZIEL");
		oBT.setBackground(new E2_ColorDark());
		oBT.setFont(new E2_FontPlain());

		if (S.isFull(oUsedResultMAP.get_FormatedValue("ZAHLUNGSZIEL")) && S.isFull(oUsedResultMAP.get_FormatedValue("ORIGINAL_ZAHLUNGSZIEL")))
		{
			if (!oUsedResultMAP.get_FormatedValue("ZAHLUNGSZIEL").equals(oUsedResultMAP.get_FormatedValue("ORIGINAL_ZAHLUNGSZIEL")))
			{
				oBT.setBackground(new E2_ColorDDDark());
				oBT.setFont(new E2_FontBold());
			}
		}


		//wenn der storno-betrag inaktiv gemacht wurde, dann den storno-betrag markieren
		MyE2_DB_Label_INROW oLabelStornobetrag = (MyE2_DB_Label_INROW)oMAP.get__Comp(RECORD_FIBU.FIELD__SKONTOBETRAG_FREMD_WAEHRUNG);
		//standard:
		oLabelStornobetrag.EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		oLabelStornobetrag.get_oRowContainer().setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		if (S.NN(oUsedResultMAP.get_UnFormatedValue(RECORD_FIBU.FIELD__SKONTO_DATUM_UEBERSCHRITTEN)).equals("Y"))
		{
			//ROT
			GridLayoutData  oGL = LayoutDataFactory.get_GL_Copy(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP(), new E2_ColorAlarm());
			oLabelStornobetrag.EXT().set_oLayout_ListElement(oGL);
			oLabelStornobetrag.get_oRowContainer().setLayoutData(oGL);

		}



		//2011-02-28: storno-info-spalte
		if (BUCHUNGSTYP.equals(myCONST.BT_DRUCK_RECHNUNG) || BUCHUNGSTYP.equals(myCONST.BT_DRUCK_GUTSCHRIFT))
		{
			RECORD_VKOPF_RG  	recVKOPF = recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();

			RECLIST_VPOS_RG reclistRG = recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(JT_VPOS_RG.DELETED,'N')='N' " +
					" AND " +
					"(" +
					"  JT_VPOS_RG.ID_VPOS_RG_STORNO_VORGAENGER IS NOT NULL OR " +
					"  JT_VPOS_RG.ID_VPOS_RG_STORNO_NACHFOLGER IS NOT NULL)", null, true);

			if (reclistRG.get_vKeyValues().size()>0)
			{
				boolean bIstS1 = false;               //hat eine rechnung beide positonstypen, dann wird der S1-typ angezeigt
				for (int i=0;i<reclistRG.get_vKeyValues().size();i++)
				{
					RECORD_VPOS_RG recRG = reclistRG.get(i);
					if (recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_lValue(new Long(0))>0)
					{
						bIstS1=true;
						break;
					}
				}

				MyE2_Grid_InLIST  oGrid = (MyE2_Grid_InLIST)oMAP.get__Comp_From_RealComponents(FIBU_CONST.KEY_SPALTE_ZEIGE_STORNO_INFOS);
				oGrid.removeAll();
				oGrid.add(new ownButtonStornosZusammensuchen(oUsedResultMAP.get_cUNFormatedROW_ID(), reclistRG, bIstS1));
			}
		}




		//2016-03-11: mahnungen werden aus der neuen variante gezogen (zuerst nur im debug-mode)
		//2016-09-26: Schalter.
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_ALT.is_YES()){
			//2011-03-18: Mahnungen
			MyE2_Grid_InLIST oGridMahnungen = (MyE2_Grid_InLIST)oMAP.get(FIBU_CONST.KEY_SPALTE_MAHNUNGSBUTTONS);
			oGridMahnungen.removeAll();
			oGridMahnungen.add(new FIBU_LIST_BUTTONS_Mahnung_NG(recFibu,this.oNaviList));
		}
		//2016-03-21: Neue variante für die Mahnung aktiviert
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_NEU.is_YES()){
			MyE2_Grid_InLIST oGridMahnungenNeu = (MyE2_Grid_InLIST)oMAP.get(FIBU_CONST.KEY_SPALTE_MAHNUNGSBUTTONS_NEU);
			oGridMahnungenNeu.removeAll();
			oGridMahnungenNeu.add(new FIBU_MAHNUNG_List_Button(recFibu,this.oNaviList));
		}

	}





	private class ownButtonStornosZusammensuchen extends MyE2_Button
	{
		private RECLIST_VPOS_RG  reclistRG_MIT_VORGAENGER_oder_NACHFOLGER = null;
		private String           cID_FIBU = null;


		public ownButtonStornosZusammensuchen(String ID_FIBU, RECLIST_VPOS_RG Reclist_RG_MIT_VORGAENGER_oder_Nachfolger, boolean bS1) 
		{
			super(E2_ResourceIcon.get_RI(bS1?"storno_beleg.png":"storno_gegenbeleg.png"));
			this.setToolTipText(new MyE2_String("Dieser Beleg enthält mindestens 1 Stornoposition. Klicken auf den Knopf selektiert die zugehörigen Belege!").CTrans());

			this.reclistRG_MIT_VORGAENGER_oder_NACHFOLGER= Reclist_RG_MIT_VORGAENGER_oder_Nachfolger;
			this.cID_FIBU = ID_FIBU;

			this.add_oActionAgent(new ownActionAgent());
		}

		public void set_bEnabled_For_Edit(boolean _enabled) throws myException
		{
		}

		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				RECLIST_VPOS_RG Reclist_RG_MIT_VORGAENGER_NACHFOLGER = ownButtonStornosZusammensuchen.this.reclistRG_MIT_VORGAENGER_oder_NACHFOLGER;

				Vector<String> vID_FIBU = new Vector<String>();


				try 
				{
					for (int i=0;i<Reclist_RG_MIT_VORGAENGER_NACHFOLGER.get_vKeyValues().size();i++)
					{
						RECORD_VPOS_RG recRG = Reclist_RG_MIT_VORGAENGER_NACHFOLGER.get(i);

						if (recRG.get_ID_VPOS_RG_STORNO_VORGAENGER_lValue(new Long(-1))>0)
						{
							RECORD_VPOS_RG recVorgaenger_oder_Nachfolger = new RECORD_VPOS_RG(recRG.get_ID_VPOS_RG_STORNO_VORGAENGER_lValue(new Long(-1)));

							if (recVorgaenger_oder_Nachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
							{
								if (recVorgaenger_oder_Nachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg("NVL(VORLAEUFIG,'N')='N'", null, true).size()>0)
								{
									vID_FIBU.add(recVorgaenger_oder_Nachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg().get(0).get_ID_FIBU_cUF());
								}

							}
						} 
						else if (recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_lValue(new Long(-1))>0)
						{
							RECORD_VPOS_RG recVorgaenger_oder_Nachfolger = new RECORD_VPOS_RG(recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_lValue(new Long(-1)));

							if (recVorgaenger_oder_Nachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
							{
								if (recVorgaenger_oder_Nachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg("NVL(VORLAEUFIG,'N')='N'", null, true).size()>0)
								{
									vID_FIBU.add(recVorgaenger_oder_Nachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg().get(0).get_ID_FIBU_cUF());
								}

							}
						}

					}
				} 
				catch (Exception e) 
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Storno-Vorgänger konnten nicht gefunden werden !!"));
					return;
				}

				if (vID_FIBU.size()>0)
				{
					E2_NavigationList oNaviList = FIBU_LIST_FORMATING_Agent.this.oNaviList;
					oNaviList.get_vActualID_Segment().removeAllElements();
					oNaviList.get_vActualID_Segment().add( ownButtonStornosZusammensuchen.this.cID_FIBU);   //das original bleibt in der liste
					oNaviList.get_vActualID_Segment().addAll(vID_FIBU);

					oNaviList._REBUILD_ACTUAL_SITE("");
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Alle Positionen die zum angwählten Storno-Eintrag gehören ..."));
				}
			}
		}

	}


	//	private class ownButtonStornoBeleg extends MyE2_Button
	//	{
	//		private RECLIST_VPOS_RG  reclistRG_MIT_NACHFOLGER = null;
	//		private String           cID_FIBU = null;
	//		
	//		
	//		public ownButtonStornoBeleg(String ID_FIBU, RECLIST_VPOS_RG Reclist_RG_MIT_NACHFOLGER) 
	//		{
	//			super(E2_ResourceIcon.get_RI("storno_beleg.png"));
	//			this.setToolTipText(new MyE2_String("Dieser Beleg enthält mindestens 1 Stornoposition. Klicken auf den Knopf selektiert die zugehörigen Belege!").CTrans());
	//
	//			this.reclistRG_MIT_NACHFOLGER= Reclist_RG_MIT_NACHFOLGER;
	//			this.cID_FIBU = ID_FIBU;
	//			
	//			this.add_oActionAgent(new ownActionAgent());
	//		}
	//		
	//		public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	//		{
	//		}
	//		
	//		private class ownActionAgent extends XX_ActionAgent
	//		{
	//			@Override
	//			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	//			{
	//				RECLIST_VPOS_RG Reclist_RG_MIT_NACHFOLGER = ownButtonStornoBeleg.this.reclistRG_MIT_NACHFOLGER;
	//				
	//				Vector<String> vID_FIBU = new Vector<String>();
	//				
	//				try 
	//				{
	//					for (int i=0;i<Reclist_RG_MIT_NACHFOLGER.get_vKeyValues().size();i++)
	//					{
	//						RECORD_VPOS_RG recRG = Reclist_RG_MIT_NACHFOLGER.get(i);
	//						
	//						if (recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_lValue(new Long(-1))>0)
	//						{
	//							RECORD_VPOS_RG recNachfolger = new RECORD_VPOS_RG(recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_lValue(new Long(-1)));
	//							
	//							if (recNachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
	//							{
	//								if (recNachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg("NVL(VORLAEUFIG,'N')='N'", null, true).size()>0)
	//								{
	//									vID_FIBU.add(recNachfolger.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg().get(0).get_ID_FIBU_cUF());
	//								}
	//								
	//							}
	//						}
	//					}
	//				} 
	//				catch (Exception e) 
	//				{
	//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Storno-Vorgänger konnten nicht gefunden werden !!"));
	//					return;
	//				}
	//				
	//				if (vID_FIBU.size()>0)
	//				{
	//					E2_NavigationList oNaviList = FIBU_LIST_FORMATING_Agent.this.oNaviList;
	//					oNaviList.get_vActualID_Segment().removeAllElements();
	//					oNaviList.get_vActualID_Segment().add( ownButtonStornoBeleg.this.cID_FIBU);   //das original bleibt in der liste
	//					oNaviList.get_vActualID_Segment().addAll(vID_FIBU);
	//					
	//					oNaviList._REBUILD_ACTUAL_SITE("");
	//					bibMSG.add_MESSAGE(new MyE2_Info_Message("Alle Positionen die zum angwählten Storno-Eintrag gehören ..."));
	//				}
	//			}
	//		}
	//		
	//	}


}
