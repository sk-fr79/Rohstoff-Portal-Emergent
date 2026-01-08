package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.math.BigDecimal;
import java.util.Vector;

import echopointng.Separator;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorFortschrittsbalken;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_FortsschrittsBalken;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ABZUG_EK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ABZUG_VK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ABZUG_EK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ABZUG_VK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT_ABZUG;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_AbzugsKalkulator;


/*
 * klasse, um falsch / unvollstaendig erfasste fuhren-/fuhrenort-abzuege neu zu berechnen
 */
public class PluginCol_Container4FuhrenAbzugsPrueflauf extends Basic_PluginColumn
{

	private MyE2_Column   		oColBasic = 	new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
	private MyE2_Grid     		grid4Anzeige = 	new MyE2_Grid(10);
	private MyE2_ContainerEx  	oContainerEX = 	new MyE2_ContainerEx();
	
	private String[][]          recFuhrenIDs = null;
	private String[][]          recFuhrenOrtIDs = null;
	
	
	public PluginCol_Container4FuhrenAbzugsPrueflauf(ContainerForVerwaltungsTOOLS mothercontainer)
	{
		super(mothercontainer);
	
		this.add(this.oColBasic);
		
		MyE2_Button  oButtonStarteAuswertung = new MyE2_Button(new MyE2_String("Prüfe Abzüge"));
		
		oButtonStarteAuswertung.add_oActionAgent(new actionPruefeFuhrenAbzuege());
		

		this.oColBasic.add(new E2_ComponentGroupHorizontal(0,oButtonStarteAuswertung, new ownTestButton(),E2_INSETS.I_0_0_5_0));
		this.oColBasic.add(this.oContainerEX, E2_INSETS.I_5_5_5_5);
		
		this.oContainerEX.setWidth(new Extent(900));
		this.oContainerEX.setHeight(new Extent(500));
		this.oContainerEX.add(this.grid4Anzeige);

	}

	
	
	private class ownTestButton extends MyE2_Button
	{
		public ownTestButton() 
		{
			super("Test simple Daughter Wiegeabzuege");
			
			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					new TEST_Container_WiegeAbzug();
				}
			});
		}
		
	}
	
	
	
	private class actionPruefeFuhrenAbzuege extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			PluginCol_Container4FuhrenAbzugsPrueflauf.this.recFuhrenIDs = bibDB.EinzelAbfrageInArray(
					"SELECT ID_VPOS_TPA_FUHRE  FROM ( "+
							" SELECT "+ 
							" ID_VPOS_TPA_FUHRE, "+
							" ABZUG_LADEMENGE_LIEF, "+ 
							" ABZUG_ABLADEMENGE_ABN, "+ 
							" (SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ABZUG_EK WHERE  JT_VPOS_TPA_FUHRE_ABZUG_EK.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE) AS ZAHL_ABZUG_LIEF, "+
							" (SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ABZUG_VK WHERE  JT_VPOS_TPA_FUHRE_ABZUG_VK.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE) AS ZAHL_ABZUG_ABN "+
							" FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+
							" ) "+
							" WHERE      ZAHL_ABZUG_LIEF>0 "+
							" OR         ZAHL_ABZUG_ABN>0 "+
							" OR         NVL(ABZUG_LADEMENGE_LIEF,0)<>0 "+
							" OR         NVL(ABZUG_ABLADEMENGE_ABN,0)<>0 "+
							" ORDER BY ID_VPOS_TPA_FUHRE ");

			
			PluginCol_Container4FuhrenAbzugsPrueflauf.this.recFuhrenOrtIDs = bibDB.EinzelAbfrageInArray(
					"SELECT ID_VPOS_TPA_FUHRE_ORT  FROM ( "+
							" SELECT "+ 
							" ID_VPOS_TPA_FUHRE_ORT, "+
							" ABZUG_MENGE, "+ 
							" (SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ORT_ABZUG WHERE JT_VPOS_TPA_FUHRE_ORT_ABZUG.ID_VPOS_TPA_FUHRE_ORT=JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE_ORT) AS ZAHL_ABZUG "+
							" FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT "+
							" ) "+
							" WHERE      ZAHL_ABZUG>0 "+
							" OR         NVL(ABZUG_MENGE,0)<>0 "+
							" ORDER BY ID_VPOS_TPA_FUHRE_ORT ");

			
			
			
			new E2_ServerPushMessageContainer(new Extent(400),new Extent(200),new MyE2_String("Prüfung der Fuhren läuft ..."),true,true,false,6000)
			{

				@Override
				public void Run_Loop() throws myException
				{
					PluginCol_Container4FuhrenAbzugsPrueflauf.this.grid4Anzeige.setVisible(false);
					PluginCol_Container4FuhrenAbzugsPrueflauf.this.bearbeiteBetroffeneFuhren(this);
				}
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}

			};
			
		}
	}
	

	
	
	
	
	private void bearbeiteBetroffeneFuhren(E2_ServerPushMessageContainer oFortschrittsContainer) throws myException
	{
		
		PluginCol_Container4FuhrenAbzugsPrueflauf oThis = PluginCol_Container4FuhrenAbzugsPrueflauf.this;
		
		oThis.grid4Anzeige.removeAll();
		oThis.grid4Anzeige.setSize(2);

//		String cFuhrenQuery = "SELECT * FROM JT_VPOS_TPA_FUHRE where id_vpos_tpa_fuhre<10000";
		
//		RECLIST_VPOS_TPA_FUHRE  reclistFuhre = new RECLIST_VPOS_TPA_FUHRE(cFuhrenQuery);

		E2_FortsschrittsBalken   oBalkenFU = new E2_FortsschrittsBalken(oThis.recFuhrenIDs.length,20,new E2_ColorFortschrittsbalken());
		E2_FortsschrittsBalken   oBalkenFU_ORT = new E2_FortsschrittsBalken(oThis.recFuhrenOrtIDs.length,20,new E2_ColorFortschrittsbalken());
		
		int iFUGeprueft = 			0;
		int iFUUnterschiedlichEK = 	0;
		int iFUUnterschiedlichVK =	0;
		int iFUUnterschiedlichALLE = 	0;
		int iFUFuhrenStatusUnterschiedlich = 0;
		
		int iFUOGeprueft = 			0;
		int iFUOUnterschiedlichALLE = 	0;
		
		
		int iErfolgreichUpdate = 0;
		int iFehlerUpdate = 0;
		
		Vector<String> vFehlerhafteFuhren = new Vector<String>();
		
		Vector<String> vFehlerhafteFuhrenMitOrten = new Vector<String>();

		
		//zuerst die Fuhren pruefen
		for (int i=0;i<oThis.recFuhrenIDs.length;i++)
		{
			
			if (i%10==0)
			{
				oFortschrittsContainer.get_oGridBaseForMessages().removeAll();
	            oBalkenFU.set_Wert(i);
	            oFortschrittsContainer.get_oGridBaseForMessages().add(oBalkenFU);
	            oFortschrittsContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+i+" / "+oThis.recFuhrenIDs.length));
			}
			
			//zuerst schauen, ob die fuhre betroffen ist
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(oThis.recFuhrenIDs[i][0]);
			
			
			
			if ( (!(recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)==0)) ||
				 (!(recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)==0)) || 
				 (recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre().get_vKeyValues().size()>0) ||
				 (recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre().get_vKeyValues().size()>0))
			{
				ownAbzugsCalcFuhreEK oAbzugEK = new ownAbzugsCalcFuhreEK(recFuhre);
				ownAbzugsCalcFuhreVK oAbzugVK = new ownAbzugsCalcFuhreVK(recFuhre);

				iFUGeprueft++;
				
				if (oAbzugEK.get_bdGESAMTER_MENGENABZUG().compareTo(recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO))!=0)
				{
					System.out.println(""+oAbzugEK.get_bdGESAMTER_MENGENABZUG()+"   --> "+recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO));
					recFuhre.set_NEW_VALUE_ABZUG_LADEMENGE_LIEF(MyNumberFormater.formatDez(oAbzugEK.get_bdGESAMTER_MENGENABZUG(), 3, true));
					iFUUnterschiedlichEK++;
				}
				if (oAbzugVK.get_bdGESAMTER_MENGENABZUG().compareTo(recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO))!=0)
				{
					System.out.println(""+oAbzugVK.get_bdGESAMTER_MENGENABZUG()+"   --> "+recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO));
					recFuhre.set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(MyNumberFormater.formatDez(oAbzugVK.get_bdGESAMTER_MENGENABZUG(), 3, true));
					iFUUnterschiedlichVK++;
				}
				if (recFuhre.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().size()>0)
				{
					iFUUnterschiedlichALLE++;
					MyE2_MessageVector oMV = bibDB.ExecMultiSQLVector(bibALL.get_Vector(recFuhre.get_SQL_UPDATE_STATEMENT(null, true)),true);
					
					if (oMV.get_bIsOK())
					{
						iErfolgreichUpdate++;
						
						RECORD_VPOS_TPA_FUHRE recTest = new RECORD_VPOS_TPA_FUHRE(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
						
						if (recTest.get_STATUS_BUCHUNG_bdValue(BigDecimal.ZERO).compareTo(recFuhre.get_STATUS_BUCHUNG_bdValue(BigDecimal.ZERO))!=0)
						{
							iFUFuhrenStatusUnterschiedlich++;
						}
					}
					else
					{
						iFehlerUpdate++;
						vFehlerhafteFuhren.add(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
					}
					
				}
			}
			
		}

		
		//dann die orte
		for (int i=0;i<oThis.recFuhrenOrtIDs.length;i++)
		{
			
			if (i%10==0)
			{
				oFortschrittsContainer.get_oGridBaseForMessages().removeAll();
				oBalkenFU_ORT.set_Wert(i);
	            oFortschrittsContainer.get_oGridBaseForMessages().add(oBalkenFU_ORT);
	            oFortschrittsContainer.get_oGridBaseForMessages().add(new MyE2_Label(""+i+" / "+oThis.recFuhrenOrtIDs.length));
			}
			
			//zuerst schauen, ob die fuhre betroffen ist
			RECORD_VPOS_TPA_FUHRE_ORT  recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(oThis.recFuhrenOrtIDs[i][0]);
			//Fuhre zur kontrolle des status
			RECORD_VPOS_TPA_FUHRE recFuhreKontrolle1 = new RECORD_VPOS_TPA_FUHRE(recFuhreOrt.get_ID_VPOS_TPA_FUHRE_cUF());
			
			
			if ( (recFuhreOrt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)!=0) ||
				 (recFuhreOrt.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort().get_vKeyValues().size()>0))
			{
				ownAbzugsCalcFuhreORT oAbzugORT = new ownAbzugsCalcFuhreORT(recFuhreOrt);

				iFUOGeprueft++;
				
				if (oAbzugORT.get_bdGESAMTER_MENGENABZUG().compareTo(recFuhreOrt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO))!=0)
				{
					System.out.println(""+oAbzugORT.get_bdGESAMTER_MENGENABZUG()+"   --> "+recFuhreOrt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO));
					recFuhreOrt.set_NEW_VALUE_ABZUG_MENGE(MyNumberFormater.formatDez(oAbzugORT.get_bdGESAMTER_MENGENABZUG(), 3, true));
				}
				if (recFuhreOrt.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().size()>0)
				{
					iFUOUnterschiedlichALLE++;
					MyE2_MessageVector oMV = bibDB.ExecMultiSQLVector(bibALL.get_Vector(recFuhreOrt.get_SQL_UPDATE_STATEMENT(null, true)),true);
					
					if (oMV.get_bIsOK())
					{
						iErfolgreichUpdate++;
						
						RECORD_VPOS_TPA_FUHRE recFuhreKontrolle2 = new RECORD_VPOS_TPA_FUHRE(recFuhreOrt.get_ID_VPOS_TPA_FUHRE_cUF());
						
						if (recFuhreKontrolle1.get_STATUS_BUCHUNG_bdValue(BigDecimal.ZERO).compareTo(recFuhreKontrolle2.get_STATUS_BUCHUNG_bdValue(BigDecimal.ZERO))!=0)
						{
							iFUFuhrenStatusUnterschiedlich++;
						}
					}
					else
					{
						iFehlerUpdate++;
						vFehlerhafteFuhrenMitOrten.add(recFuhreOrt.get_ID_VPOS_TPA_FUHRE_cUF());
					}
					
				}
			}
			
		}

		
		
		/*
		 * 			int iGeprueft = 			0;
			int iUnterschiedlichEK = 	0;
			int iUnterschiedlichVK =	0;
			int iUnterschiedlichALLE = 	0;
			int iFuhrenStatusUnterschiedlich = 0;
			int iErfolgreichUpdate = 0;
			int iFehlerUpdate = 0;

		 */
		
		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Anzahl geprüfte Fuhren:")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUGeprueft),E2_INSETS.I_5_5_5_5);
		
		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Anzahl Fuhren mit falschem EK-Abzug:")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUUnterschiedlichEK),E2_INSETS.I_5_5_5_5);
		
		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Anzahl Fuhren mit falschem VK-Abzug:")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUUnterschiedlichVK),E2_INSETS.I_5_5_5_5);
		
		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Anzahl Fuhren mit falschem Abzug:")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUUnterschiedlichALLE),E2_INSETS.I_5_5_5_5);
		
		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Erfolgreiches Update :")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iErfolgreichUpdate),E2_INSETS.I_5_5_5_5);

		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Fehlerhaftes Update :")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFehlerUpdate),E2_INSETS.I_5_5_5_5);

		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Geaenderter Fuhrenstatus !!!!! :")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUFuhrenStatusUnterschiedlich),E2_INSETS.I_5_5_5_5);

		this.grid4Anzeige.add(new Separator(),2,E2_INSETS.I_2_2_2_2);

		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Anzahl geprüfte Fuhren-Orte:")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUOGeprueft),E2_INSETS.I_5_5_5_5);

		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Anzahl Fuhrenorte mit falschem Abzug:")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUOUnterschiedlichALLE),E2_INSETS.I_5_5_5_5);
		
		this.grid4Anzeige.add(new Separator(),2,E2_INSETS.I_2_2_2_2);

		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Anzahl geprüfte Fuhren:")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUGeprueft),E2_INSETS.I_5_5_5_5);
		
		this.grid4Anzeige.add(new MyE2_Label(new MyE2_String("Anzahl Fuhren mit falschem EK-Abzug:")),E2_INSETS.I_5_5_5_5);
		this.grid4Anzeige.add(new MyE2_Label(""+iFUUnterschiedlichEK),E2_INSETS.I_5_5_5_5);

		
		
		this.grid4Anzeige.add(new MyE2_TextArea("Fehler beim Update (Fuhren-ID): "+bibALL.Concatenate(vFehlerhafteFuhren, " - ", "--")+
												"\n\nFehler beim Orte-Update (Fuhren-ID): "+bibALL.Concatenate(vFehlerhafteFuhrenMitOrten, " - ", "--"),400,10000,10),2,E2_INSETS.I_5_5_5_5);
		
		this.grid4Anzeige.setVisible(true);
		
	}
	
	
	
		
	
	private class ownAbzugsCalcFuhreEK extends BL_AbzugsKalkulator
	{
        
		public ownAbzugsCalcFuhreEK(RECORD_VPOS_TPA_FUHRE recRG_Test) throws myException
		{
			super(		recRG_Test.get_ANTEIL_LADEMENGE_LIEF_cF_NN("0"), 
						"0",
						"0", 
						"1000",
						"1", 
						"EUR", 
						"EUR",
						"EK", 
						"EHP");
			
			RECLIST_VPOS_TPA_FUHRE_ABZUG_EK recListAbzug = recRG_Test.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre(null,"POS_NUMMER",true);
			
			for (int i=0;i<recListAbzug.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE_ABZUG_EK recAbzug = recListAbzug.get(i);
				
				if (i==0)
				{
					this.add_AbzugsKalkulationsZeile(	recAbzug.get_ABZUGTYP_cF(),
										 				recRG_Test.get_ANTEIL_LADEMENGE_LIEF_cF_NN("0"),
										 				"0",
										 				"0",
										 				recAbzug.get_ABZUG_cF(),
										 				recAbzug.get_ABZUG2_cF(),
										 				recAbzug.get_ID_VPOS_TPA_FUHRE_ABZUG_EK_cF(),
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
										 				recAbzug.get_ID_VPOS_TPA_FUHRE_ABZUG_EK_cF(),
										 				recAbzug.get_ABZUG_BELEGTEXT_SCHABLONE_cF_NN(""));
				}
			}
		}
	}
	
	
	
	private class ownAbzugsCalcFuhreVK extends BL_AbzugsKalkulator
	{
        
		public ownAbzugsCalcFuhreVK(RECORD_VPOS_TPA_FUHRE recRG_Test) throws myException
		{
			super(		recRG_Test.get_ANTEIL_ABLADEMENGE_ABN_cF_NN("0"), 
						"0",
						"0", 
						"1000",
						"1", 
						"EUR", 
						"EUR",
						"EK", 
						"EHP");
			
			RECLIST_VPOS_TPA_FUHRE_ABZUG_VK recListAbzug = recRG_Test.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre(null,"POS_NUMMER",true);
			
			for (int i=0;i<recListAbzug.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE_ABZUG_VK recAbzug = recListAbzug.get(i);
				
				if (i==0)
				{
					this.add_AbzugsKalkulationsZeile(	recAbzug.get_ABZUGTYP_cF(),
										 				recRG_Test.get_ANTEIL_ABLADEMENGE_ABN_cF_NN("0"),
										 				"0",
										 				"0",
										 				recAbzug.get_ABZUG_cF(),
										 				recAbzug.get_ABZUG2_cF(),
										 				recAbzug.get_ID_VPOS_TPA_FUHRE_ABZUG_VK_cF(),
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
										 				recAbzug.get_ID_VPOS_TPA_FUHRE_ABZUG_VK_cF(),
										 				recAbzug.get_ABZUG_BELEGTEXT_SCHABLONE_cF_NN(""));
				}
			}
		}
	}


	
	
	private class ownAbzugsCalcFuhreORT  extends BL_AbzugsKalkulator
	{
        
		public ownAbzugsCalcFuhreORT(RECORD_VPOS_TPA_FUHRE_ORT recRG_Test) throws myException
		{
			super(		(recRG_Test.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))?recRG_Test.get_ANTEIL_LADEMENGE_cF_NN("0"):recRG_Test.get_ANTEIL_ABLADEMENGE_cF_NN("0"), 
						"0",
						"0", 
						"1000",
						"1", 
						"EUR", 
						"EUR",
						"EK", 
						"EHP");
			
			RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG recListAbzug = recRG_Test.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort(null,"POS_NUMMER",true);
			
			for (int i=0;i<recListAbzug.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE_ORT_ABZUG recAbzug = recListAbzug.get(i);
				
				if (i==0)
				{
					this.add_AbzugsKalkulationsZeile(	recAbzug.get_ABZUGTYP_cF(),
														(recRG_Test.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))?recRG_Test.get_ANTEIL_LADEMENGE_cF_NN("0"):recRG_Test.get_ANTEIL_ABLADEMENGE_cF_NN("0"),
										 				"0",
										 				"0",
										 				recAbzug.get_ABZUG_cF(),
										 				recAbzug.get_ABZUG2_cF(),
										 				recAbzug.get_ID_VPOS_TPA_FUHRE_ORT_ABZUG_cF(),
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
										 				recAbzug.get_ID_VPOS_TPA_FUHRE_ORT_ABZUG_cF(),
										 				recAbzug.get_ABZUG_BELEGTEXT_SCHABLONE_cF_NN(""));
				}
			}
		}
	}

	
	
	
}
