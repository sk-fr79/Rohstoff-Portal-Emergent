package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.math.BigDecimal;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorFortschrittsbalken;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_FortsschrittsBalken;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG_ABZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG_ABZUG;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_AbzugsKalkulator;

public class PluginCol_Container4Controlling extends Basic_PluginColumn
{

	private MyE2_Column   		oColBasic = 	new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
	private MyE2_Grid     		grid4Anzeige = 	new MyE2_Grid(10);
	private MyE2_ContainerEx  	oContainerEX = 	new MyE2_ContainerEx();
	
	private MyE2_CheckBox       oCB_Verdichte_Auf_Kopf = new MyE2_CheckBox(new MyE2_String("Nur betroffene Kopfsätze anzeigen"));
	
	
	public PluginCol_Container4Controlling(ContainerForVerwaltungsTOOLS mothercontainer)
	{
		super(mothercontainer);
	
		this.add(this.oColBasic);
		
		MyE2_Button  oButtonStarteAuswertung = new MyE2_Button(new MyE2_String("Prüfe Abzüge"));
		
		oButtonStarteAuswertung.add_oActionAgent(new actionPruefeAbzuege());
		

		MyE2_Button  oButtonBaueAbzuegeNeuAuf = new MyE2_Button(new MyE2_String("Alle Positionen: Abzüge neu berechnen !!"));
		oButtonBaueAbzuegeNeuAuf.add_oActionAgent(new actionBerechneAbzuegeNeu());
		
		this.oColBasic.add(new E2_ComponentGroupHorizontal(0,oButtonStarteAuswertung,oCB_Verdichte_Auf_Kopf,oButtonBaueAbzuegeNeuAuf,E2_INSETS.I_0_0_5_0));
		this.oColBasic.add(this.oContainerEX, E2_INSETS.I_5_5_5_5);
		
		this.oContainerEX.setWidth(new Extent(900));
		this.oContainerEX.setHeight(new Extent(500));
		this.oContainerEX.add(this.grid4Anzeige);

	}

	
	
	
	private class actionPruefeAbzuege extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			
//			new E2_ServerPushMessageContainer(new Extent(400),new Extent(150),new MyE2_String("Prüfung der Belegpositionen läuft ..."),true,true,false,5000)
//			{
//
//				@Override
//				public void Run_Loop() throws myException
//				{
					PluginCol_Container4Controlling.this.grid4Anzeige.setVisible(false);
					if (PluginCol_Container4Controlling.this.oCB_Verdichte_Auf_Kopf.isSelected())
					{
						PluginCol_Container4Controlling.this.baue_reportliste_kopftabelle();
					}
					else
					{
						PluginCol_Container4Controlling.this.baue_reportliste_positionen();
					}
					PluginCol_Container4Controlling.this.grid4Anzeige.setVisible(true);
//				}
//				
//			};
			
		}
	}
	

	

	
	
	private class actionBerechneAbzuegeNeu extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			
			new E2_ServerPushMessageContainer(new Extent(400),new Extent(150),new MyE2_String("Prüfung der Belegpositionen läuft ..."),true,true,false,1000)
			{

				@Override
				public void Run_Loop() throws myException
				{
					PluginCol_Container4Controlling.this.grid4Anzeige.setVisible(false);
					
					MyE2_Label  labZaehler = new MyE2_Label("Satz-Nr: 0");
					this.get_oGridBaseForMessages().removeAll();
					this.get_oGridBaseForMessages().add_raw(labZaehler,LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_10_10_10_10));
					
					String[][] cIDs = bibDB.EinzelAbfrageInArray("SELECT ID_VPOS_RG FROM JT_VPOS_RG WHERE ID_VPOS_RG IN (SELECT DISTINCT ID_VPOS_RG FROM JT_VPOS_RG_ABZUG) ORDER BY ID_VPOS_RG");
					
					for (int i=0;i<cIDs.length;i++)
					{
						
						bibDB.ExecMultiSQLVector(bibALL.get_Vector(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE JT_VPOS_RG SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_RG="+cIDs[i][0]), true);
						
						if (i%100==0)
						{
							labZaehler.set_Text("Satz-Nr. "+i+ "  von  "+cIDs.length);
						}
					}

					
					PluginCol_Container4Controlling.this.grid4Anzeige.setVisible(true);
				}
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}

				
			};
			
		}
	}
	

	
	
	
	
	
	
	
	private void baue_reportliste_positionen() throws myException
	{
		

		
		PluginCol_Container4Controlling oThis = PluginCol_Container4Controlling.this;
		
		oThis.grid4Anzeige.removeAll();
		oThis.grid4Anzeige.setSize(10);
		
		String cQuery = "SELECT ID_VPOS_RG FROM (" +
							"  SELECT " +
										" ID_VPOS_RG," +
										" ID_VKOPF_RG," +
										" ANZAHL_ABZUG," +
										" GESAMTPREIS_ABZUG," +
							            " NVL(DELETED,'N') AS DELETED,"+
										" EINZELPREIS_ABZUG," +
										" GESAMTPREIS_ABZUG_FW," +
										" EINZELPREIS_ABZUG_FW," +
										" (SELECT COUNT(*) FROM JT_VPOS_RG_ABZUG WHERE JT_VPOS_RG_ABZUG.ID_VPOS_RG=JT_VPOS_RG.ID_VPOS_RG) AS ZAHL" +
							" FROM "+bibE2.cTO()+".JT_VPOS_RG) "+
				         " WHERE " +
				            "  ((ANZAHL_ABZUG IS NOT NULL) OR " +
				            "  (GESAMTPREIS_ABZUG IS NOT NULL) OR " +
				            "  (EINZELPREIS_ABZUG IS NOT NULL) OR " +
				            "  (GESAMTPREIS_ABZUG_FW IS NOT NULL) OR " +
				            "  (EINZELPREIS_ABZUG_FW IS NOT NULL) OR " +
				            "  (ZAHL>0)) AND DELETED='N' " +
				            " ORDER BY ID_VKOPF_RG ";
		

		
		
		String[][] cIDs = bibDB.EinzelAbfrageInArray(cQuery, "");
		
		if (cIDs == null || cIDs.length==0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler in der Abfrage: "+cQuery));
			return;
		}

		
		E2_FortsschrittsBalken   oBalken = new E2_FortsschrittsBalken(cIDs.length,20,new E2_ColorFortschrittsbalken());
			
		bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Gefundene Kopfsätze mit Abzugsangaben: "+cIDs.length));
		
		Insets iHelp = new Insets(2,0,2,1);
		GridLayoutData  oGLR = new GridLayoutData();
		oGLR.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		oGLR.setInsets(iHelp);
		
		oThis.grid4Anzeige.add(new oLT("NR"),oGLR);
		oThis.grid4Anzeige.add(new oLT("Beleg-Nr"),oGLR);
		oThis.grid4Anzeige.add(new oLT("ID_VKOPF_RG"),oGLR);
		oThis.grid4Anzeige.add(new oLT("ID_VPOS_RG"),oGLR);
		oThis.grid4Anzeige.add(new oLT("Leistungsdatum"),oGLR);
		oThis.grid4Anzeige.add(new oLT("Rechnungsdatum"),oGLR);
		oThis.grid4Anzeige.add(new oLT("Mengenabzug (DB->Calc"),oGLR);
		oThis.grid4Anzeige.add(new oLT("EPreis-Abzug (DB->Calc)"),oGLR);
		oThis.grid4Anzeige.add(new oLT("GPreis-Abzug (DB-Calc)"),oGLR);
		oThis.grid4Anzeige.add(new oLT("Zahl Abzüge"),oGLR);
		

		//Vector<RECORD_VPOS_RG> vRecKaputt = new Vector<RECORD_VPOS_RG>();
		
		int iDefekt = 1;
		
		
//		oFortschrittsContainer.get_oGridBaseForMessages().removeAll();
//        oFortschrittsContainer.get_oGridBaseForMessages().add(oBalken);

		
		for (int i=0;i<cIDs.length;i++)
		{
			
//			oFortschrittsContainer.get_oGridBaseForMessages().removeAll();
			if (i%10==0)
			{
               oBalken.set_Wert((i+10));
			}
//            oFortschrittsContainer.get_oGridBaseForMessages().add(oBalken);
			
			RECORD_VPOS_RG recRG = new RECORD_VPOS_RG(cIDs[i][0]); 
			
			ownAbzugsCalc oCalc = new ownAbzugsCalc(recRG);
			
			boolean bOK = true;
			
			bOK = bOK & (new MyBigDecimal(oCalc.get_ABZUG_VOM_GESAMTPREIS(),2).get_bdWert().compareTo(recRG.get_GESAMTPREIS_ABZUG_bdValue(BigDecimal.ZERO,2))==0);
			bOK = bOK & (new MyBigDecimal(oCalc.get_ABZUG_VOM_GESAMTPREIS_FW(),2).get_bdWert().compareTo(recRG.get_GESAMTPREIS_ABZUG_FW_bdValue(BigDecimal.ZERO,2))==0);
			bOK = bOK & (new MyBigDecimal(oCalc.get_GESAMT_EPREIS_ABZUG(),2).get_bdWert().compareTo(recRG.get_EINZELPREIS_ABZUG_bdValue(BigDecimal.ZERO,2))==0);
			bOK = bOK & (new MyBigDecimal(oCalc.get_GESAMT_EPREIS_ABZUG_FW(),2).get_bdWert().compareTo(recRG.get_EINZELPREIS_ABZUG_FW_bdValue(BigDecimal.ZERO,2))==0);
			bOK = bOK & (new MyBigDecimal(oCalc.get_GESAMTER_MENGENABZUG(),3).get_bdWert().compareTo(recRG.get_ANZAHL_ABZUG_bdValue(BigDecimal.ZERO,3))==0);

		
//			if (recRG.get_ID_VKOPF_RG_cUF_NN("").equals("1504"))
//			{
//				System.out.println("1504");
//			}
			
			
			if (!bOK)
			{
				oThis.grid4Anzeige.add(new oL(""+iDefekt++),oGLR);
				oThis.grid4Anzeige.add(new oLT(""+(S.isFull(recRG.get_ID_VKOPF_RG_cUF())?recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cUF_NN(""):"")),oGLR);
				oThis.grid4Anzeige.add(new oLT(""+recRG.get_ID_VKOPF_RG_cUF()),oGLR);
				oThis.grid4Anzeige.add(new oLT(""+recRG.get_ID_VPOS_RG_cUF()),oGLR);
				oThis.grid4Anzeige.add(new oLT(recRG.get_AUSFUEHRUNGSDATUM_cF_NN("")),oGLR);
				oThis.grid4Anzeige.add(new oLT(""+(S.isFull(recRG.get_ID_VKOPF_RG_cUF())?recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DRUCKDATUM_cF_NN(""):"")),oGLR);
				oThis.grid4Anzeige.add(new oLT(recRG.get_ANZAHL_ABZUG_cF_NN("0")+" -> "+oCalc.get_GESAMTER_MENGENABZUG()),oGLR);
				oThis.grid4Anzeige.add(new oLT(recRG.get_EINZELPREIS_ABZUG_cF_NN("0")+" -> "+oCalc.get_GESAMT_EPREIS_ABZUG()),oGLR);
				oThis.grid4Anzeige.add(new oLT(recRG.get_GESAMTPREIS_ABZUG_cF_NN("0")+" -> "+oCalc.get_ABZUG_VOM_GESAMTPREIS()),oGLR);
				oThis.grid4Anzeige.add(new oLT(""+oCalc.iAnzahlAbzuege),oGLR);
			}
			
//			System.out.println("Calc:"+oCalc.get_ABZUG_VOM_GESAMTPREIS()+"   ->   DB: "+recRG.get_GESAMTPREIS_ABZUG_bdValue(BigDecimal.ZERO));
//			System.out.println("Calc:"+oCalc.get_GESAMTER_MENGENABZUG()+"   ->   DB: "+recRG.get_ANZAHL_ABZUG_bdValue(BigDecimal.ZERO));
//			System.out.println(" --------------------------------------- ");
			
		}
		
//		bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Anzahl Fehler: "+vRecKaputt.size()));
	}
	
	
	
	
	
	private void baue_reportliste_kopftabelle() throws myException
	{
		PluginCol_Container4Controlling oThis = PluginCol_Container4Controlling.this;
		
		oThis.grid4Anzeige.removeAll();
		oThis.grid4Anzeige.setSize(4);
		
		String cQuery = "SELECT ID_VPOS_RG FROM (" +
										"  SELECT " +
													" ID_VPOS_RG," +
													" ID_VKOPF_RG," +
													" ANZAHL_ABZUG," +
													" GESAMTPREIS_ABZUG," +
										            " NVL(DELETED,'N') AS DELETED,"+
													" EINZELPREIS_ABZUG," +
													" GESAMTPREIS_ABZUG_FW," +
													" EINZELPREIS_ABZUG_FW," +
													" (SELECT COUNT(*) FROM JT_VPOS_RG_ABZUG WHERE JT_VPOS_RG_ABZUG.ID_VPOS_RG=JT_VPOS_RG.ID_VPOS_RG) AS ZAHL" +
										" FROM "+bibE2.cTO()+".JT_VPOS_RG) "+
										     " WHERE " +
										        "  ((ANZAHL_ABZUG IS NOT NULL) OR " +
										        "  (GESAMTPREIS_ABZUG IS NOT NULL) OR " +
										        "  (EINZELPREIS_ABZUG IS NOT NULL) OR " +
										        "  (GESAMTPREIS_ABZUG_FW IS NOT NULL) OR " +
										        "  (EINZELPREIS_ABZUG_FW IS NOT NULL) OR " +
										        "  (ZAHL>0)) AND DELETED='N' " +
										        " ORDER BY ID_VKOPF_RG ";
		

		
		
		String[][] cIDs = bibDB.EinzelAbfrageInArray(cQuery, "");
		
		if (cIDs == null || cIDs.length==0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler in der Abfrage: "+cQuery));
			return;
		}
		
		
		E2_FortsschrittsBalken   oBalken = new E2_FortsschrittsBalken(cIDs.length,20,new E2_ColorFortschrittsbalken());

			
		bibMSG.add_MESSAGE(new MyE2_Info_Message("Gefundene Kopfsätze mit Abzugsangaben: "+cIDs.length));
		Insets iHelp = new Insets(2,0,2,1);
		GridLayoutData  oGLR = new GridLayoutData();
		oGLR.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		oGLR.setInsets(iHelp);
		
		
		
		
		oThis.grid4Anzeige.add(new oLT("NR"),oGLR);
		oThis.grid4Anzeige.add(new oLT("Beleg-Nr"),oGLR);
		oThis.grid4Anzeige.add(new oLT("ID_VKOPF_RG"),oGLR);
		oThis.grid4Anzeige.add(new oLT("Rechnungsdatum"),oGLR);
		

		//Vector<RECORD_VPOS_RG> vRecKaputt = new Vector<RECORD_VPOS_RG>();
		
		int iDefekt = 1;
		
		VectorSingle  vIDsKopf = new VectorSingle();
		
		
		for (int i=0;i<cIDs.length;i++)
		{
			
//			oFortschrittsContainer.get_oGridBaseForMessages().removeAll();
//            oBalken.set_Wert((i+1));
//            oFortschrittsContainer.get_oGridBaseForMessages().add(oBalken);

			
            try
            {
//            	if (cIDs[i][0].equals("4024"))
//            	{
//            		System.out.println("stop");
//            	}
//            	
            	RECORD_VPOS_RG recRG = new RECORD_VPOS_RG(cIDs[i][0]);
            	ownAbzugsCalc oCalc = new ownAbzugsCalc(recRG);
            	boolean bOK = true;
            	
            	bOK = bOK & (new MyBigDecimal(oCalc.get_ABZUG_VOM_GESAMTPREIS(),2).get_bdWert().compareTo(recRG.get_GESAMTPREIS_ABZUG_bdValue(BigDecimal.ZERO,2))==0);
            	bOK = bOK & (new MyBigDecimal(oCalc.get_ABZUG_VOM_GESAMTPREIS_FW(),2).get_bdWert().compareTo(recRG.get_GESAMTPREIS_ABZUG_FW_bdValue(BigDecimal.ZERO,2))==0);
            	bOK = bOK & (new MyBigDecimal(oCalc.get_GESAMT_EPREIS_ABZUG(),2).get_bdWert().compareTo(recRG.get_EINZELPREIS_ABZUG_bdValue(BigDecimal.ZERO,2))==0);
            	bOK = bOK & (new MyBigDecimal(oCalc.get_GESAMT_EPREIS_ABZUG_FW(),2).get_bdWert().compareTo(recRG.get_EINZELPREIS_ABZUG_FW_bdValue(BigDecimal.ZERO,2))==0);
            	bOK = bOK & (new MyBigDecimal(oCalc.get_GESAMTER_MENGENABZUG(),3).get_bdWert().compareTo(recRG.get_ANZAHL_ABZUG_bdValue(BigDecimal.ZERO,3))==0);
            	
            	
            	if (!bOK)
            	{
            		if ( (!recRG.get_ID_VKOPF_RG_cUF_NN("0").equals("0")) &&  !(vIDsKopf.contains(recRG.get_ID_VKOPF_RG_cUF_NN("0"))))
            		{
            			vIDsKopf.add(recRG.get_ID_VKOPF_RG_cUF_NN("0"));
            			
            			RECORD_VKOPF_RG recVKopf = recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
            			
            			oThis.grid4Anzeige.add(new oL(""+iDefekt++),oGLR);
            			oThis.grid4Anzeige.add(new oLT(""+recVKopf.get_BUCHUNGSNUMMER_cF_NN("")),oGLR);
            			oThis.grid4Anzeige.add(new oLT(""+recVKopf.get_ID_VKOPF_RG_cF_NN("")),oGLR);
            			oThis.grid4Anzeige.add(new oLT(""+recVKopf.get_DRUCKDATUM_cF_NN("")),oGLR);
            		}
            	}
            }
            catch (Exception ex)
            {
            	ex.printStackTrace();
            }
			
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	private class ownAbzugsCalc extends BL_AbzugsKalkulator
	{
        
		public int iAnzahlAbzuege = 0;
		
		
		public ownAbzugsCalc(RECORD_VPOS_RG recRG_Test) throws myException
		{
			super(		recRG_Test.get_ANZAHL_cF_NN("0"), 
						recRG_Test.get_EINZELPREIS_cF_NN("0"),
						recRG_Test.get_EINZELPREIS_cF_NN("0"), 
						"1000",
						"1", 
						"EUR", 
						"EUR",
						"EK", 
						"EHP");
			
			RECLIST_VPOS_RG_ABZUG recListAbzug = recRG_Test.get_DOWN_RECORD_LIST_VPOS_RG_ABZUG_id_vpos_rg(null,"POS_NUMMER",true);
			
			this.iAnzahlAbzuege = recListAbzug.get_vKeyValues().size();
			
			for (int i=0;i<recListAbzug.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_RG_ABZUG recAbzug = recListAbzug.get(i);
				
				if (i==0)
				{
					this.add_AbzugsKalkulationsZeile(	recAbzug.get_ABZUGTYP_cF(),
										 				recRG_Test.get_ANZAHL_cF_NN("0"),
										 				recRG_Test.get_EINZELPREIS_cF_NN("0"),
										 				recRG_Test.get_EINZELPREIS_FW_cF_NN("0"),
										 				recAbzug.get_ABZUG_cF(),
										 				recAbzug.get_ABZUG2_cF(),
										 				recAbzug.get_ID_VPOS_RG_ABZUG_cF(),
										 				recAbzug.get_ABZUG_BELEGTEXT_SCHABLONE_cF_NN(""));
				}
				else
				{
					this.add_AbzugsKalkulationsZeile(	recAbzug.get_ABZUGTYP_cF(),
														 MyNumberFormater.formatDez(this.get_dMengeNachAbzugLetzteAbzugsZeile(),3,false), 
														 MyNumberFormater.formatDez(this.get_dEPreisNachAbzugLetzteAbzugsZeile(),2,false), 
														 MyNumberFormater.formatDez(this.get_dEPreisNachAbzugLetzteAbzugsZeile_FW(),2,false),
										 				recAbzug.get_ABZUG_cF(),
										 				recAbzug.get_ABZUG2_cF(),
										 				recAbzug.get_ID_VPOS_RG_ABZUG_cF(),
										 				recAbzug.get_ABZUG_BELEGTEXT_SCHABLONE_cF_NN(""));
				}
			}
			
			
			
			
		}


		public int get_iAnzahlAbzuege()
		{
			return iAnzahlAbzuege;
		}
		
	}
	
	
	private class oLT extends MyE2_Label
	{
		public oLT(Object text)
		{
			super(text, new E2_FontBold(-2));
		}
	}

	private class oL extends MyE2_Label
	{
		public oL(Object text)
		{
			super(text, new E2_FontPlain(-2));
		}
	}
	
	
}
