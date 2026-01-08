package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;


import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.specialViews.build_flat_atom;
import panter.gmbh.basics4project.specialViews.build_table_JD_DATUM;
import panter.gmbh.basics4project.specialViews.build_view_DIVERSE;
import panter.gmbh.basics4project.specialViews.build_view_FIRMENADRESSEN_FLACH;
import panter.gmbh.basics4project.specialViews.build_view_FuhrenEinfacherBewegungssatz;
import panter.gmbh.basics4project.specialViews.build_view_FuhrenKlassifizierung;
import panter.gmbh.basics4project.specialViews.build_view_FuhrenPreisInfos;
import panter.gmbh.basics4project.specialViews.build_view_Fuhren_und_Orte;
import panter.gmbh.basics4project.specialViews.build_view_Fuhren_und_Orte_REALMENGEN;
import panter.gmbh.basics4project.specialViews.build_view_MATSPEZ_4_SELECTION;
import panter.gmbh.basics4project.specialViews.build_view_Real_GUTSCHRIFT_RECHNUNG_POS;
import panter.gmbh.basics4project.specialViews.build_view_Sonderlager;
import panter.gmbh.basics4project.specialViews.build_view_Sonderlager_2;
import panter.gmbh.basics4project.specialViews.build_view_Sonderlager_Rechnungen;
import panter.gmbh.basics4project.specialViews.build_view_Sonderlager_Rechnungen_2;
import panter.gmbh.basics4project.specialViews.build_view_Transportkosten;
import panter.gmbh.basics4project.specialViews.build_view_lager_in_out_mengen_preis_vergleich;
import panter.gmbh.basics4project.specialViews.Views_4_tempTables.build_view_ListDefined_With_Globale_TempTables;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;


public class PluginCol_ViewBuilder extends Basic_PluginColumn
{

	public PluginCol_ViewBuilder(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);
		
		String 				cAbfrageTable =  DB_META.get_TablesQuerySort_A_to_Z(bibALL.get_DBKENNUNG(), bibE2.cTO(),true,true);
		MyE2_SelectField 	oSelectTables = new MyE2_SelectField(cAbfrageTable,false,true,false,false);

		oSelectTables.setSelectedIndex(0);
		oSelectTables.add_oActionAgent(new actionClearOutput());
		oSelectTables.add_oActionAgent(new actionAgentBuildTableVIEW());
	
		
		MyE2_Button oButtonBuildAll = new MyE2_Button(new MyE2_String("Baue ALLE Views aller Mandanten"));
		oButtonBuildAll.add_oActionAgent(new actionClearOutput());
		oButtonBuildAll.add_oActionAgent(new actionAgentBuildTableVIEWS());
	
		
		PopDown_AlleTabellen  oPop = new PopDown_AlleTabellen(new actionAgentBuildTableSingleVIEW()) ;


		this.add( new E2_ComponentGroupHorizontal(0,
											oButtonBuildAll,oPop, new Insets(0,0,10,0)),
											ContainerForVerwaltungsTOOLS.INSETS_LIST);
		this.add( new E2_ComponentGroupHorizontal(0,
											new MyE2_Label(new MyE2_String("Mandanten-Views einer Tabelle bauen:")),oSelectTables, new Insets(0,0,10,0)),
											ContainerForVerwaltungsTOOLS.INSETS_LIST);
		
		this.add( new Separator());
		
		this.add( new E2_ComponentGroupHorizontal(0,
				new MyE2_Label(new MyE2_String("Weiteres ...")),new btSpezielleViewsBauen(), new Insets(0,0,10,0)),
				ContainerForVerwaltungsTOOLS.INSETS_LIST);
		
		this.add(this.get_TextArea4Output(),ContainerForVerwaltungsTOOLS.INSETS_LIST);


		
	}
	
	
	
	private class btSpezielleViewsBauen extends MyE2_Button
	{

		public btSpezielleViewsBauen()
		{
			super(new MyE2_String("Spezielle Views bauen"));
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) 	throws myException
				{
					try {

						// Tabelle mit den Datumswerten neu aufbauen
						new build_table_JD_DATUM().build_View_forAll_Mandants();

						
						new build_view_Fuhren_und_Orte().build_View_forAll_Mandants();
						new build_view_Fuhren_und_Orte_REALMENGEN().build_View_forAll_Mandants();
						new build_view_Transportkosten().build_View_forAll_Mandants();
						new build_view_FuhrenKlassifizierung().build_View_forAll_Mandants();
						new build_view_FuhrenPreisInfos().build_View_forAll_Mandants();
						new build_view_FuhrenEinfacherBewegungssatz().build_View_forAll_Mandants();
						
						//2012-10-19: zwei weitere views: alle rechnungspositionen eines mandanten ohne DELETED und stornierte
						new build_view_Real_GUTSCHRIFT_RECHNUNG_POS().build_View_forAll_Mandants();
						
						//2011-08-15: neuer view lager-in-out-vergleich
						new build_view_lager_in_out_mengen_preis_vergleich().build_View_forAll_Mandants();
						
						// 2011-10-26  mandantenübergreifende Views für die Sonderlager und Rechnungen
						new build_view_Sonderlager().build_View_forAll_Mandants();
						new build_view_Sonderlager_Rechnungen().build_View_forAll_Mandants();
						
						// 2019-01-14 mandanteübergreifende Views für die Sonderlager und Rechnengen V2
						new build_view_Sonderlager_2().build_View_forAll_Mandants();
						new build_view_Sonderlager_Rechnungen_2().build_View_forAll_Mandants();
						
						//2012-03-06 mandantenübergreifende View für flache Firmenadressen 
						new build_view_FIRMENADRESSEN_FLACH().build_View_forAll_Mandants();
						
						//2015-04-16: weitere mandanten-views 
						new build_view_DIVERSE().build_View_forAll_Mandants();
						
						
						// 2012-12-12: Views für die Bewegungssätze generieren
//					new build_view_BEWEGUNG_FLACH().build_View_forAll_Mandants();
						
						//2013-04-12: materialspezifikations-View fuer die selektion
						new build_view_MATSPEZ_4_SELECTION().build_View_forAll_Mandants();
						
						//2017-05-05: spezielle flache tabelle der atom mit allen bewegungstabellen nach oben bauen
						new build_flat_atom().build_View_forAll_Mandants();

						//neue views fuer die handlichere benutzung der neuen bewegungs-struktur (Stichwort: atom)
						Vector<String> vViewFiles = bibALL.get_Vector(	"S0_WE_ATOM",
																		"S0_WE_MONATS_SUM",
																		"S0_WA_ATOM",
																		"S0_WA_MONATS_SUM",
																		"S0_WE_RECHPOS_MONAT_SUM",
																		"S0_WA_RECHPOS_MONAT_SUM");
						vViewFiles.add("S0_KOSTEN_AUS_FUHREN");
						vViewFiles.add("S0_STATISTIK_LIEFERBED");
						
						
						HashMap<String, String> hmViewFiles = new HashMap<String, String>();
						hmViewFiles.put("S0_WE_ATOM", 			"S#MANDANT#_WE_ATOM");
						hmViewFiles.put("S0_WE_MONATS_SUM", 	"S#MANDANT#_WE_MONATS_SUM");
						hmViewFiles.put("S0_WA_ATOM", 			"S#MANDANT#_WA_ATOM");
						hmViewFiles.put("S0_WA_MONATS_SUM", 	"S#MANDANT#_WA_MONATS_SUM");
						hmViewFiles.put("S0_WE_RECHPOS_MONAT_SUM", "S#MANDANT#_WE_RECHPOS_MONAT_SUM");
						hmViewFiles.put("S0_WA_RECHPOS_MONAT_SUM", "S#MANDANT#_WA_RECHPOS_MONAT_SUM");
						
						hmViewFiles.put("S0_KOSTEN_AUS_FUHREN", "S#MANDANT#_KOSTEN_AUS_FUHREN");
						hmViewFiles.put("S0_STATISTIK_LIEFERBED", "S#MANDANT#_STATISTIK_LIEFERBED");

						new build_view_ListDefined_With_Globale_TempTables(hmViewFiles,vViewFiles).build_View_forAll_Mandants();
					} catch (myException mex) {
						mex.printStackTrace();
						 
						bibMSG.add_MESSAGE(mex);
					}
					
					MyE2_MessageVector MV_Infos = bibMSG.MV().get_InfoMessages();
					MyE2_MessageVector MV_Alarm = bibMSG.MV().get_AlarmMessages();
					MyE2_MessageVector MV_Warning = bibMSG.MV().get_WarnMessages();
					
//					if (bibMSG.get_bIsOK())
//					{
					//info-Messages in das Textfeld
					PluginCol_ViewBuilder.this.get_TextArea4Output().setText(MV_Infos.get_MessagesAsText());
					bibMSG.MV().removeAllElements();
					
					bibMSG.MV().removeAllElements();
					bibMSG.add_MESSAGE(MV_Alarm);
					bibMSG.add_MESSAGE(MV_Warning);
					
//					}
				}
			});
		}
		
		
	}
	

	
	
	
	// actionagent, um alle tabellen-views aller mandanten neu aufzubauen
	// gehoert zu einem Button
	private class actionAgentBuildTableVIEWS extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			new E2_ServerPushMessageContainer(new Extent(400),new Extent(150),new MyE2_String("Aufbau der Views läuft ..."),true,true,false,4000)
			{
				@Override
				public void Run_Loop() throws myException
				{
			
					// ab hier wird neu aufgebaut ...
					PluginCol_ViewBuilder 	oThis = PluginCol_ViewBuilder.this;
					
					String 		cAbfrageMandanten = "SELECT ID_MANDANT,NVL(NAME1,TO_CHAR(ID_MANDANT)) FROM "+bibE2.cTO()+".JD_MANDANT order by ID_MANDANT";
					String[][] 	cMandanten = bibDB.EinzelAbfrageInArray(cAbfrageMandanten, false);
		
					String 		cAbfrageTable =  DB_META.get_TablesQuery(bibALL.get_DBKENNUNG(), bibE2.cTO(),true,true);
					String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable, false);
		
					if (cTabellen==null || cTabellen.length==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der Tabellen des OWNERS "+bibE2.cTO()));
						return;
					}
					if (cMandanten == null || cMandanten.length==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der vorhandenen Mandanten !"));
						return;
					}
					
					
					StringBuffer  cMessages = new StringBuffer();
					
					//Vector<String> vOutPutMessages = new Vector<String>();
					
					for (int i=0;i<cMandanten.length;i++)
					{
						for (int k=0;k<cTabellen.length;k++)
						{
							String cNamenView = "V" + cMandanten[i][0].trim() + "_" + cTabellen[k][0].substring(3);
		
							/*
							 * 2013-10-07: nur tabellen mit jt_ am anfang verarbeiten, JD_ und TT_ Tabellen auslassen
							 */
							if (!cTabellen[k][0].toUpperCase().startsWith("JT_"))
							{
								cMessages.append("Mandant: "+cMandanten[i][0].trim()+" --> Keinen View erzeugt --> " + cTabellen[k][0] + " ist eine Definitionstabelle !\n");
							}
							else
							{
								String cSqlBaueNeuView = "CREATE OR REPLACE VIEW " + cNamenView + " AS SELECT * FROM " + cTabellen[k][0] + " WHERE ID_MANDANT=" + cMandanten[i][0].trim();
		
								if (bibDB.ExecSQL(cSqlBaueNeuView,true))
									cMessages.append("OK!     Neuen View " + cNamenView + " erfolgreich erstellt !\n");
								else
									cMessages.append("ERROR!  Neuen View " + cNamenView + " nicht erstellt !\n");
							}
							
							//fortschrittsanzeige
							this.get_oGridBaseForMessages().removeAll();
							this.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String(cTabellen[k][0]+"   ("+k+"/"+cTabellen.length+")",false)));
						}
					}
					
					
					oThis.get_TextArea4Output().setText(cMessages.toString());
			
				}
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}

			};
			
		}
	}
	

	
	
	// actionagent, um alle tabellen-views aller mandanten neu aufzubauen
	// gehoert zu einem Button
	private class actionAgentBuildTableSingleVIEW extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			MyE2_Button oButton = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();

			String 		cAbfrageMandanten = "SELECT ID_MANDANT,NVL(NAME1,TO_CHAR(ID_MANDANT)) FROM "+bibE2.cTO()+".JD_MANDANT order by ID_MANDANT";
			String[][] 	cMandanten = bibDB.EinzelAbfrageInArray(cAbfrageMandanten, false);

			if (cMandanten == null || cMandanten.length==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der vorhandenen Mandanten !"));
				return;
			}
			
			
			//Vector<String> vOutPutMessages = new Vector<String>();
			String cTabelle = oButton.EXT().get_C_MERKMAL();
			
			
			for (int i=0;i<cMandanten.length;i++)
			{
					String cNamenView = "V" + cMandanten[i][0].trim() + "_" + cTabelle.substring(3);

					if (cTabelle.toUpperCase().startsWith("JD_"))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mandant: ",true,cMandanten[i][0].trim(),false," --> Keinen View erzeugt --> ",true,cTabelle,false," ist eine Definitionstabelle !",true)));
					}
					else
					{
						String cSqlBaueNeuView = "CREATE OR REPLACE VIEW " + cNamenView + " AS SELECT * FROM " + cTabelle + " WHERE ID_MANDANT=" + cMandanten[i][0].trim();

						if (bibDB.ExecSQL(cSqlBaueNeuView,true))
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("OK!     Neuen View ",true,cNamenView,false," erfolgreich erstellt !",true)));
						else
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("ERROR!  Neuen View ",true,cNamenView,false," nicht erstellt !",true)));
					}
					
			}
		}
	}

	
	
	
	// actionagent, um  tabellen-views aller mandanten  EINER TABELLE neu aufzubauen
	// gehoert zu einem Selectfield mit Tabellen in der Liste
	private class actionAgentBuildTableVIEW extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				MyE2_SelectField oSelField = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
				String cTableName = oSelField.get_ActualView().toUpperCase().trim();
				
				if (cTableName.equals(""))        // der erste eintrag ist leer
					return;
				
				// ab hier wird neu aufgebaut ...
				PluginCol_ViewBuilder 	oThis = PluginCol_ViewBuilder.this;
				
				String 		cAbfrageMandanten = "SELECT ID_MANDANT,NVL(NAME1,TO_CHAR(ID_MANDANT)) FROM "+bibE2.cTO()+".JD_MANDANT order by ID_MANDANT";
				String[][] 	cMandanten = bibDB.EinzelAbfrageInArray(cAbfrageMandanten, false);
	
	
				if (cMandanten == null || cMandanten.length==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der vorhandenen Mandanten !"));
					return;
				}
				
				//Vector<String> vOutPutMessages = new Vector<String>();
				StringBuffer  cMessages = new StringBuffer();

				
				for (int i=0;i<cMandanten.length;i++)
				{
					String cNamenView = "V" + cMandanten[i][0].trim() + "_" + cTableName.substring(3);

					if (cTableName.startsWith("JD_"))
					{
						cMessages.append("Mandant: "+cMandanten[i][0].trim()+" --> Keinen View erzeugt --> " + cTableName + " ist eine Definitionstabelle !\n");
					}
					else
					{
						String cSqlBaueNeuView = "CREATE OR REPLACE VIEW " + cNamenView + " AS SELECT * FROM " + cTableName + " WHERE ID_MANDANT=" + cMandanten[i][0].trim();

						if (bibDB.ExecSQL(cSqlBaueNeuView,true))
							cMessages.append("OK!     Neuen View " + cNamenView + " erfolgreich erstellt !\n");
						else
							cMessages.append("ERROR!  Neuen View " + cNamenView + " nicht erstellt !\n");
					}
				}
				
				oThis.get_TextArea4Output().setText(cMessages.toString());
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}

		}
	}

	
	
	private class actionClearOutput extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			PluginCol_ViewBuilder.this.get_TextArea4Output().setText("");
		}
	}

	
	
	
	
	
	
	
}
