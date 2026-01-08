package panter.gmbh.Echo2.__BASIC_MODULS.ADMINTOOLS;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.DebugPrinter;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyAdress;

public class Tool_ChangeAdressIDs extends MyE2_Column
{
	private MyE2_TextField 				oTFAdresseQuelle = new MyE2_TextField("",100,20);
	private MyE2_TextField 				oTFAdresseZiel = new MyE2_TextField("",100,20);
	private MyE2_Button					oButtonCheckAdresses = new MyE2_Button(new MyE2_String("Prüfe Adressen"));


	private MyE2_Grid					oGridWithCheckingResults = new MyE2_Grid(3,1);
	private String 						cMODULKENNER = null;

	
	private MyDBToolBox					oDB = bibALL.get_myDBToolBox();
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(oDB);
	}

	
	
	public Tool_ChangeAdressIDs(String MODULKENNER)
	{
		super();
		this.cMODULKENNER = MODULKENNER;
		
		// keine view-ersetzung
		this.oDB.set_bErsetzungTableView(false);

		
		this.oButtonCheckAdresses.add_oActionAgent(new ownActionAgentCheckADRESSES());
		
		
		MyE2_Button oButtonTauscheAdressen = new MyE2_Button(new MyE2_String("Tausche Adressfelder"));
		oButtonTauscheAdressen.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				String cAdress1 = Tool_ChangeAdressIDs.this.oTFAdresseQuelle.getText();
				String cAdress2 = Tool_ChangeAdressIDs.this.oTFAdresseZiel.getText();
				
				Tool_ChangeAdressIDs.this.oTFAdresseQuelle.setText(cAdress2);
				Tool_ChangeAdressIDs.this.oTFAdresseZiel.setText(cAdress1);
				
				Tool_ChangeAdressIDs.this.oButtonCheckAdresses.doActionPassiv();
			}
		});

		
		
		this.add(new MyE2_Label(new MyE2_String("Adress-Zusatztabellen von einer Adresse zur anderen umbuchen !!"),MyE2_Label.STYLE_TITEL_NORMAL()), E2_INSETS.I_10_10_10_10);
		
		MyE2_Grid oGridInput = new MyE2_Grid(3,0);
		oGridInput.add(new MyE2_Label(new MyE2_String("Quell-Adresse (verliert Zusätze)")),E2_INSETS.I_2_2_10_2);
		oGridInput.add(this.oTFAdresseQuelle,E2_INSETS.I_2_2_10_2);
		oGridInput.add(oButtonTauscheAdressen,E2_INSETS.I_2_2_10_2);
		
		oGridInput.add(new MyE2_Label(new MyE2_String("Ziel-Adresse (bekommt Zusätze)")),E2_INSETS.I_2_2_10_2);
		oGridInput.add(this.oTFAdresseZiel,E2_INSETS.I_2_2_10_2);
		oGridInput.add(this.oButtonCheckAdresses,E2_INSETS.I_2_2_10_2);
		
		
		this.add(oGridInput,E2_INSETS.I_10_10_10_10);
		this.add(this.oGridWithCheckingResults,E2_INSETS.I_10_10_10_10);
		
//		
//		MyE2_EE_TabbedPane oTest = new MyE2_EE_TabbedPane(null);
//		
//		oTest.add_Tabb(new MyE2_String("Test"), new MyE2_Label("Test"));
//		
//		SplitPane oTestpane = new SplitPane(SplitPane.ORIENTATION_HORIZONTAL);
//		oTestpane.add(oTest);
//		
//		this.add(oTestpane);
		
//		MyE2_TextField oTFKlick = new MyE2_TextField();
//		oTFKlick.addActionListener(new ActionListener()
//		{
//
//			@Override
//			public void actionPerformed(ActionEvent arg0)
//			{
//				bibALL.System_println("Text klick !!!");
//				
//			}
//			
//		});
//		
//		this.add(oTFKlick);
		
	}

	
	
	
	private class ownActionAgentCheckADRESSES extends XX_ActionAgent
	{
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Tool_ChangeAdressIDs oThis = Tool_ChangeAdressIDs.this;
			
			oThis.oGridWithCheckingResults.removeAll();
			
			String cID_Quelle = oThis.oTFAdresseQuelle.getText().trim();
			String cID_Ziel = oThis.oTFAdresseZiel.getText().trim();
			

			if (cID_Quelle.equals("") || cID_Ziel.equals(""))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte beide Adress-IDs angeben !!"));
				return;
			}
			
			cID_Quelle = 	bibALL.ReplaceTeilString(cID_Quelle,".","");
			cID_Ziel = 		bibALL.ReplaceTeilString(cID_Ziel,".","");
			
			if (!bibALL.isInteger(cID_Quelle) || !bibALL.isInteger(cID_Ziel))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Eine Adress-ID ist nicht korrekt !!"));
				return;
			}
			

			MyAdress oAdressQuelle = null;
			MyAdress oAdressZiel = null;
			try
			{
				oAdressQuelle = new MyAdress(cID_Quelle, false);
				oAdressZiel = new MyAdress(cID_Ziel,false);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Beide Adress-IDs müssen vorhanden sein !!!"));
				return;
			}

			
			if (!oAdressQuelle.get_bIS_FirmenAdresse() || !oAdressZiel.get_bIS_FirmenAdresse() )
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es müssen beides Firmenadressen sein !!!"));
				return;
			}
		

			// wenn bis hier alles klar, dann naechste stufe zuenden
			MyE2_Column oColQuelle = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
			MyE2_Column oColZiel = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
			MyE2_Row	oRowInfos = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER());
			
			oThis.oGridWithCheckingResults.add(new MyE2_Label(new MyE2_String("Quell-Adresse"),MyE2_Label.STYLE_TITEL_NORMAL()), E2_INSETS.I_10_2_10_2);
			oThis.oGridWithCheckingResults.add(new MyE2_Label(new MyE2_String("Ziel-Adresse"),MyE2_Label.STYLE_TITEL_NORMAL()), E2_INSETS.I_10_2_10_2);
			oThis.oGridWithCheckingResults.add(new MyE2_Label(new MyE2_String("Automatische Änderungen"),MyE2_Label.STYLE_TITEL_NORMAL()), E2_INSETS.I_10_2_10_2);
			
			oThis.oGridWithCheckingResults.add(oColQuelle, 	MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_10_10_10_10,1,new Alignment(Alignment.LEFT,Alignment.TOP)));
			oThis.oGridWithCheckingResults.add(oColZiel, 	MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_10_10_10_10,1,new Alignment(Alignment.LEFT,Alignment.TOP)));
			oThis.oGridWithCheckingResults.add(oRowInfos, 	MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_10_10_10_10,1,new Alignment(Alignment.LEFT,Alignment.TOP)));
			
			this.fillAdressInfo(oAdressQuelle,oColQuelle);
			this.fillAdressInfo(oAdressZiel,oColZiel);
			this.fillTableInfos(cID_Quelle,cID_Ziel,oRowInfos,oThis.oDB);
			
			
		}
		
		
		private void fillAdressInfo(MyAdress oAdress,MyE2_Column oCol) throws myException
		{
			oCol.add(new MyE2_Label(bibALL.null2leer(oAdress.get_UnFormatedValue("NAME1"))));
			oCol.add(new MyE2_Label(bibALL.null2leer(oAdress.get_UnFormatedValue("NAME2"))));
			oCol.add(new MyE2_Label(bibALL.null2leer(oAdress.get_UnFormatedValue("NAME3"))));
			oCol.add(new MyE2_Label(bibALL.null2leer(oAdress.get_UnFormatedValue("STRASSE"))));
			oCol.add(new MyE2_Label(bibALL.null2leer(oAdress.get_UnFormatedValue("ORT"))));
		}
		

		/*
		 * abfragen, wieviele referenzen es gibt und diese anzeigen, mit der anzahl betroffener datensätze,
		 * 
		 */
		private void fillTableInfos(String cID_ADRESS_QUELLE,String cID_ADRESS_ZIEL,MyE2_Row oRow, MyDBToolBox DB) throws myException
		{
			//String cQueryForeignKeys = "select tablename,columnname from foreignkeycolumns where owner='"+bibE2.cTO()+"' and  reftablename = 'JT_ADRESSE' and upper(tablename)<>'JT_FIRMENINFO' ORDER BY tablename";

			Tool_ChangeAdressIDs oThis = Tool_ChangeAdressIDs.this;

			
			String[][] cAutoTables = new String[36][2];
			int z=0;
			
			cAutoTables[z][0]="JT_FAHRPLANPOS";					cAutoTables[z++][1]="ID_ADRESSE_ZIEL";
			cAutoTables[z][0]="JT_FAHRPLANPOS";					cAutoTables[z++][1]="ID_ADRESSE_START";
			cAutoTables[z][0]="JT_FAHRTENVARIANTEN";			cAutoTables[z++][1]="ID_ADRESSE_ZIEL";
			cAutoTables[z][0]="JT_FAHRTENVARIANTEN";			cAutoTables[z++][1]="ID_ADRESSE_START";
			cAutoTables[z][0]="JT_LIEFERADRESSE";				cAutoTables[z++][1]="ID_ADRESSE_BASIS";
			cAutoTables[z][0]="JT_MITARBEITER";					cAutoTables[z++][1]="ID_ADRESSE_BASIS";
			cAutoTables[z][0]="JT_PRO_ADRESSEN";				cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z][0]="JT_VKOPF_KON";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z][0]="JT_VKOPF_STD";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z][0]="JT_VKOPF_RG";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z][0]="JT_VKOPF_TPA";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_VPOS_KON";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_VPOS_KON";					cAutoTables[z++][1]="ID_ADRESSE_LAGER";
			cAutoTables[z ][0]="JT_VPOS_KON_LAGER";				cAutoTables[z++][1]="ID_ADRESSE_LAGER";
			cAutoTables[z ][0]="JT_VPOS_STD";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_VPOS_STD";					cAutoTables[z++][1]="ID_ADRESSE_LAGER";
			cAutoTables[z ][0]="JT_VPOS_RG";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_VPOS_TPA";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_VPOS_TPA";					cAutoTables[z++][1]="ID_ADRESSE_LAGER";
			cAutoTables[z ][0]="JT_VPOS_TPA_FUHRE";				cAutoTables[z++][1]="ID_ADRESSE_LAGER_ZIEL";
			cAutoTables[z ][0]="JT_VPOS_TPA_FUHRE";				cAutoTables[z++][1]="ID_ADRESSE_ZIEL";
			cAutoTables[z ][0]="JT_VPOS_TPA_FUHRE";				cAutoTables[z++][1]="ID_ADRESSE_LAGER_START";
			cAutoTables[z ][0]="JT_VPOS_TPA_FUHRE";				cAutoTables[z++][1]="ID_ADRESSE_START";
			cAutoTables[z ][0]="JT_VPOS_TPA_FUHRE";				cAutoTables[z++][1]="ID_ADRESSE_SPEDITION";
			cAutoTables[z ][0]="JT_VPOS_TPA_FUHRE";				cAutoTables[z++][1]="ID_ADRESSE_FREMDAUFTRAG";        //2012-04-26: id_adresse_fremdauftrag muss auch umgesetzt werden
			cAutoTables[z ][0]="JT_VPOS_TPA_FUHRE_ORT";			cAutoTables[z++][1]="ID_ADRESSE_LAGER";
			cAutoTables[z ][0]="JT_VPOS_TPA_FUHRE_ORT";			cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_BEZIEHUNG";					cAutoTables[z++][1]="ID_ADRESSE_1";
			cAutoTables[z ][0]="JT_EMAIL";						cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_INTERNET";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_KONTO";						cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_ADRESSE_INFO";				cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_MAT_SPEZ";					cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_KOMMUNIKATION";				cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_ADR_CONTAINERTYP";			cAutoTables[z++][1]="ID_ADRESSE";
			cAutoTables[z ][0]="JT_ARTIKELBEZ_LIEF";			cAutoTables[z++][1]="ID_ADRESSE";

			String[][] cInfoTable = null;
			
			cInfoTable = new String[cAutoTables.length][3];
			
			for (int i=0;i<cAutoTables.length;i++)
			{
				String cAnzahl = DB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+"."+cAutoTables[i][0]+" WHERE "+cAutoTables[i][1]+"="+cID_ADRESS_QUELLE);
				
				cInfoTable[i][0]=cAutoTables[i][0];
				cInfoTable[i][1]=cAutoTables[i][1];
				cInfoTable[i][2]=cAnzahl;
			}

			/*
			 * JETZT DIE LISTE AUFBAUEN
			 */
			MyE2_Grid oGrid=new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER());
			oRow.add(oGrid,E2_INSETS.I_0_0_10_0);
			
			for (int i=0;i<cInfoTable.length;i++)
			{
				oGrid.add(new MyE2_Label(cInfoTable[i][0],MyE2_Label.STYLE_SMALL_PLAIN()),E2_INSETS.I_0_2_5_2);
				oGrid.add(new MyE2_Label(cInfoTable[i][1],MyE2_Label.STYLE_SMALL_PLAIN()),E2_INSETS.I_0_2_5_2);
				oGrid.add(new MyE2_Label(cInfoTable[i][2],MyE2_Label.STYLE_SMALL_PLAIN()),E2_INSETS.I_0_2_5_2);
			}
			
			MyE2_Button oButtonStart = new MyE2_Button(new MyE2_String("Änderung durchführen ..."));
			oButtonStart.add_oActionAgent(new actionAgentStartChange(cInfoTable,cID_ADRESS_QUELLE,cID_ADRESS_ZIEL,DB));
			oButtonStart.add_GlobalValidator(new E2_ButtonAUTHValidator(oThis.cMODULKENNER,"STARTE_ADRESS_UEBERTRAGUNG"));
			
			oGrid.add(oButtonStart,3,E2_INSETS.I_10_10_10_10);
			
			
		}
		
		
		
		private class actionAgentStartChange extends XX_ActionAgent
		{
			private String[][] 	cInfoTables = null;
			private String 		cID_QuellAdress = null;
			private String 		cID_ZielAdress = null;
			private MyDBToolBox	DB = null;
			
			
			public actionAgentStartChange(String[][] InfoTables, String quellAdress, String zielAdress, MyDBToolBox oDB)
			{
				super();
				cInfoTables = InfoTables;
				cID_QuellAdress = quellAdress;
				cID_ZielAdress = zielAdress;
				this.DB = oDB;
				
			}


			public void executeAgentCode(ExecINFO oExecInfo)
			{
				Vector<String> vSQLStatements = new Vector<String>();
				
				for (int i=0;i<this.cInfoTables.length;i++)
				{
//					if (!this.cTables[i][2].trim().equals("0"))
//						vSQLStatements.add("UPDATE "+bibE2.cTO()+"."+this.cTables[i][0]+" SET "+this.cTables[i][1]+"="+this.cID_ZielAdress+" WHERE "+this.cTables[i][1]+"="+this.cID_QuellAdress);
					
					
					if (cInfoTables[i][0].equals("JT_ARTIKELBEZ_LIEF"))   //sonderbehandlung
					{
						/*
						 * als erstes sicherstellen, dass die artikelbez_lief - eintraege der quelle
						 * nicht bereits im ziel vorhanden sind, da sonst ein index verletzt wird
						 */ 
						String[][] cSQL_ID_ARTBEZ_LIEF_EK = DB.EinzelAbfrageInArray("SELECT ID_ARTIKELBEZ_LIEF FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ADRESSE="+this.cID_QuellAdress+" AND ARTBEZ_TYP='EK' AND ID_ARTIKEL_BEZ " +
														" NOT IN (SELECT ID_ARTIKEL_BEZ FROM " +bibE2.cTO()+ ".JT_ARTIKELBEZ_LIEF WHERE ARTBEZ_TYP='EK' AND ID_ADRESSE="+this.cID_ZielAdress+")");
						
						String[][] cSQL_ID_ARTBEZ_LIEF_VK = DB.EinzelAbfrageInArray("SELECT ID_ARTIKELBEZ_LIEF FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ADRESSE="+this.cID_QuellAdress+" AND ARTBEZ_TYP='VK' AND ID_ARTIKEL_BEZ " +
															" NOT IN (SELECT ID_ARTIKEL_BEZ FROM " +bibE2.cTO()+ ".JT_ARTIKELBEZ_LIEF WHERE ARTBEZ_TYP='VK' AND ID_ADRESSE="+this.cID_ZielAdress+")");

						if (cSQL_ID_ARTBEZ_LIEF_EK==null | cSQL_ID_ARTBEZ_LIEF_VK==null)
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error checking Artbez_lief"));
							return;
						}
						
						if (cSQL_ID_ARTBEZ_LIEF_EK!=null)
						{
							for (int k=0;k<cSQL_ID_ARTBEZ_LIEF_EK.length;k++)
							{
								vSQLStatements.add("UPDATE JT_ARTIKELBEZ_LIEF SET ID_ADRESSE="+this.cID_ZielAdress+" WHERE ID_ARTIKELBEZ_LIEF="+cSQL_ID_ARTBEZ_LIEF_EK[k][0]);
							}
						}
						if (cSQL_ID_ARTBEZ_LIEF_VK != null)
						{
							for (int k=0;k<cSQL_ID_ARTBEZ_LIEF_VK.length;k++)
							{
								vSQLStatements.add("UPDATE JT_ARTIKELBEZ_LIEF SET ID_ADRESSE="+this.cID_ZielAdress+" WHERE ID_ARTIKELBEZ_LIEF="+cSQL_ID_ARTBEZ_LIEF_VK[k][0]);
							}
						}
					}
					else
					{
						if (!this.cInfoTables[i][2].trim().equals("0"))
						{
							//aenderung 2012-04-26: die aenderungen werden ohne sql-daemonen ausgefuehrt , diese werden separat angetriggert
							//vSQLStatements.add("UPDATE "+bibE2.cTO()+"."+this.cInfoTables[i][0]+" SET "+this.cInfoTables[i][1]+"="+this.cID_ZielAdress+" WHERE "+this.cInfoTables[i][1]+"="+this.cID_QuellAdress);
							vSQLStatements.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+"UPDATE "+bibE2.cTO()+"."+this.cInfoTables[i][0]+" SET "+this.cInfoTables[i][1]+"="+this.cID_ZielAdress+" WHERE "+this.cInfoTables[i][1]+"="+this.cID_QuellAdress);
						}
					}
					
				}
				
				
				
				
				
				// jetzt mwst zusammenfuehren
				String cQuery = "SELECT ID_MWSTSCHLUESSEL FROM "+bibE2.cTO()+".JT_KUNDE_MWST WHERE ID_ADRESSE IN("+cID_QuellAdress+","+cID_ZielAdress+")";
				String[][] cMWST = DB.EinzelAbfrageInArray(cQuery);
				
				if (cMWST == null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error checking MWST-Relations !"));
				}
				else
				{
					
					vSQLStatements.add("DELETE FROM "+bibE2.cTO()+".JT_KUNDE_MWST WHERE ID_ADRESSE="+this.cID_ZielAdress);
					for (int i=0;i<cMWST.length;i++)
					{
						vSQLStatements.add("INSERT INTO "+bibE2.cTO()+".JT_KUNDE_MWST (ID_KUNDE_MWST,ID_MWSTSCHLUESSEL,ID_ADRESSE) VALUES(SEQ_KUNDE_MWST.NEXTVAL,"+cMWST[i][0]+","+this.cID_ZielAdress+")");
						
					}

					
					// hier noch die lief_nr / abn_nr (von quelle) an das ziel uebergeben
					String cSQL1 = "SELECT   LIEF_NR,  ABN_NR FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE="+this.cID_QuellAdress;
					String cSQL2 = "SELECT   LIEF_NR,  ABN_NR FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE="+this.cID_ZielAdress;
					
					String[][] cNR_Quell = DB.EinzelAbfrageInArray(cSQL1, "", -1);
					String[][] cNR_Ziel  = DB.EinzelAbfrageInArray(cSQL2, "", -1);

					if (cNR_Quell == null || cNR_Ziel == null)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Prüfung der ABN_NR/LIEF_NR !"));
					}
					else if (bibMSG.get_bIsOK())
					{
					
						
						
						if (!cNR_Quell[0][0].equals(""))
						{
							if (cNR_Ziel[0][0].equals(""))
							{
								cNR_Ziel[0][0]+=cNR_Quell[0][0];
							}
							else
							{
								cNR_Ziel[0][0]+=("#"+cNR_Quell[0][0]);
							}
						}
						
						if (!cNR_Quell[0][1].equals(""))
						{
							if (cNR_Ziel[0][1].equals(""))
							{
								cNR_Ziel[0][1]+=cNR_Quell[0][1];
							}
							else
							{
								cNR_Ziel[0][1]+=("#"+cNR_Quell[0][1]);
							}
						}
	
						vSQLStatements.add("UPDATE "+bibE2.cTO()+".JT_ADRESSE SET LIEF_NR="+bibALL.MakeSql(cNR_Ziel[0][0])+" WHERE ID_ADRESSE="+this.cID_ZielAdress);
						vSQLStatements.add("UPDATE "+bibE2.cTO()+".JT_ADRESSE SET ABN_NR="+bibALL.MakeSql(cNR_Ziel[0][1])+" WHERE ID_ADRESSE="+this.cID_ZielAdress);
						// ----------------------------
	
						
						
						
						//aenderung 2012-04-26: die aenderungen werden ohne sql-daemonen ausgefuehrt , diese werden separat angetriggert
						//hier die separate triggerung ueber eigentlich "leere" aenderungen
						for (int i=0;i<this.cInfoTables.length;i++)
						{
							if (!this.cInfoTables[i][2].trim().equals("0"))
							{
								vSQLStatements.add("UPDATE "+bibE2.cTO()+"."+this.cInfoTables[i][0]+" SET ERZEUGT_VON=ERZEUGT_VON  WHERE "+this.cInfoTables[i][1]+"="+this.cID_ZielAdress);
							}
						}

						
						
//						new DebugPrinter(vSQLStatements);
						
						
						bibMSG.add_MESSAGE(DB.ExecMultiSQLVector(vSQLStatements,true));
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Änderung erfolgreich !!"));
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Ändern !!"));
							bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vSQLStatements);
						}
						
					}
				}
			}
		}
	}

	

	public MyE2_TextField get_oTFAdresseQuelle() 
	{
		return oTFAdresseQuelle;
	}



	public MyE2_TextField get_oTFAdresseZiel() 
	{
		return oTFAdresseZiel;
	}
	
	

	
}
