package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Auswertungen.XX_Auswertungen;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME_EINZELPOSITIONEN.AW_WarenstroemeHandler;
 

/**
 * auswertung, die wahlweise eine temporaere tabelle aufbaut oder diese benutzt
 *
 */
public class AuswertungenWarenStroemeViaTempTable extends XX_Auswertungen
{
	private static MyE2_String cErlaeuterung = new MyE2_String(
			"Sonderfunktion: Baut die (nur für Auswertungszwecke) benutzte Sondertabelle \n"+
			"<JT_FUHREN_RECHNUNGEN> auf."
			);	

	
	
	public AuswertungenWarenStroemeViaTempTable()
	{
		super(new MyE2_String("<<AUFBAU AUSWERTE-TABELLE>>"), cErlaeuterung, "AUFBAU_SONDERTABELLE_FUHREN_RECHNUNGEN", "","",null);
	}

	@Override
	public MyE2_Button get_StartButton() throws myException
	{
		MyE2_Button oBT_Start = new MyE2_Button("<<START Aufbau AUSWERTE-TABELLE>>");
		oBT_Start.add_oActionAgent(new ownActionAgent());
		oBT_Start.setStyle(MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
		oBT_Start.setLineWrap(true);
		
		return oBT_Start;
	}

	@Override
	public Vector<XX_ActionValidator> get_GlobalValidators4ListButton() throws myException
	{
		return null;
	}

	@Override
	public Component get_Zusatzkomponente() throws myException
	{
		return null;
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{

		private MyE2_TextArea   oMeldungsArea = new MyE2_TextArea("",500,5000,20);
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			// erzeugen der Tabelle aus der View
			this.baue_tabelle_und_basis();
			
			// Umsetzen und korrigieren der Daten
			AW_WarenstroemeHandler oHandler = new AW_WarenstroemeHandler();
			oHandler.korrigiereEintraege();
			
			this.oMeldungsArea.setText(bibMSG.MV().get_MessagesAsText());

			new ownContainer2ShowErgebnis();
			
		}
		
		private class ownContainer2ShowErgebnis extends E2_BasicModuleContainer
		{

			public ownContainer2ShowErgebnis() throws myException
			{
				super();
				
				this.add(ownActionAgent.this.oMeldungsArea, E2_INSETS.I_10_10_10_10);
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(500), new MyE2_String("Aufbauprotokoll der temporären Tabelle ..."));
				
			}
			
		}
		
		
		private void baue_tabelle_und_basis() throws myException
		{
			Vector<String>  vGenerateTable = new Vector<String>();
			
			vGenerateTable.add("drop table JT_FUHREN_RECHNUNGEN");
//			vGenerateTable.add("DROP SEQUENCE TEMP_SEQ_FUHREN_RECHNUNGEN");
			vGenerateTable.add("DROP SEQUENCE SEQ_FUHREN_RECHNUNGEN");
//			vGenerateTable.add("CREATE SEQUENCE TEMP_SEQ_FUHREN_RECHNUNGEN  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1000 NOCACHE  NOORDER  NOCYCLE");
			vGenerateTable.add("CREATE SEQUENCE SEQ_FUHREN_RECHNUNGEN  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1000 NOCACHE  NOORDER  NOCYCLE");
			//vGenerateTable.add("create table JT_FUHREN_RECHNUNGEN AS SELECT * FROM VIEW_FUHREN_RECHNUNGEN WHERE ID<2");
			vGenerateTable.add("create table JT_FUHREN_RECHNUNGEN AS SELECT * FROM VIEW_FUHREN_RECHNUNGEN");

			vGenerateTable.add("alter table JT_FUHREN_RECHNUNGEN drop column ID");
			vGenerateTable.add("alter table jt_fuhren_rechnungen add (ID_FUHREN_RECHNUNGEN NUMBER(10),LETZTE_AENDERUNG date, GEAENDERT_VON nvarchar2(10), ERZEUGT_VON NVARCHAR2(10), ERZEUGT_AM date)");

			// Spaltentypen anpassen
			// alte spalten umbennenen
			vGenerateTable.add(" alter table JT_FUHREN_RECHNUNGEN rename column MENGE to MENGE_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column MENGE_ABZUG to MENGE_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column MENGE_NACH_ABZUG to MENGE_NACH_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_GESAMT to R_PREIS_GESAMT_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_ABZUG to R_PREIS_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_NACH_ABZUG to R_PREIS_NACH_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_EINZEL to R_PREIS_EINZEL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_ABZUG_EINZEL to R_PREIS_ABZUG_EINZEL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_EINZEL_RESULT to R_PREIS_EINZEL_RESULT_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PCT_ABZUG to R_PCT_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PCT_METALL to R_PCT_METALL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column MENGE_LAGER to MENGE_LAGER_1 ");
			
			// neue Spalten hinzfügen
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add MENGE number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add MENGE_ABZUG number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add MENGE_NACH_ABZUG number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_GESAMT number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_ABZUG number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_NACH_ABZUG number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_EINZEL number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_ABZUG_EINZEL number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_EINZEL_RESULT number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PCT_ABZUG number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PCT_METALL number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add MENGE_LAGER number(12,3)");
			
			vGenerateTable.add(" alter table JT_FUHREN_RECHNUNGEN modify TYP NVARCHAR2(30)");
			vGenerateTable.add(" alter table JT_FUHREN_RECHNUNGEN modify IST_DIENSTLEISTUNG NVARCHAR2(1)");
			
			
			// daten updaten
			String s1 = 
				" UPDATE JT_FUHREN_RECHNUNGEN                                           " +
				" set MENGE = round(MENGE_1,3)                                          " +
				" ,MENGE_ABZUG = round(MENGE_ABZUG_1,3)                                 " +
				" ,MENGE_NACH_ABZUG = round(MENGE_NACH_ABZUG_1,3)                       " +
				" ,R_PREIS_GESAMT = round( R_PREIS_GESAMT_1 ,2)                         " +
				" ,R_PREIS_ABZUG = round( R_PREIS_ABZUG_1 ,2)                           " +
				" ,R_PREIS_NACH_ABZUG = round( R_PREIS_NACH_ABZUG_1 ,2)                 " +
				" ,R_PREIS_EINZEL = round( R_PREIS_EINZEL_1 ,2)                         " +
				" ,R_PREIS_ABZUG_EINZEL = round( R_PREIS_ABZUG_EINZEL_1 ,2)             " +
				" ,R_PREIS_EINZEL_RESULT = round( R_PREIS_EINZEL_RESULT_1 ,2)           " +
				" ,R_PCT_ABZUG = round( R_PCT_ABZUG_1 ,2)                               " +
				" ,R_PCT_METALL = round( R_PCT_METALL_1 ,2)                             " +
				" ,MENGE_LAGER = round( MENGE_LAGER_1 ,3)                               " ;
			vGenerateTable.add(s1);
			
			// alte spalten löschen
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column MENGE_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column MENGE_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column MENGE_NACH_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_GESAMT_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_NACH_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_EINZEL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_ABZUG_EINZEL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_EINZEL_RESULT_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PCT_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PCT_METALL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column MENGE_LAGER_1 ");
			
			// alte zahlenwerte zwischenspeichern
			vGenerateTable.add("alter table jt_fuhren_rechnungen add (R_PREIS_NACH_ABZUG_ORI NUMBER(10,2), R_PREIS_EINZEL_RESULT_ORI NUMBER(10,2), MENGE_NACH_ABZUG_ORI NUMBER(12,3), MENGE_LAGER_ORI NUMBER(12,3), KORREKTUR int)");
			
			vGenerateTable.add("update 		jt_fuhren_rechnungen set jt_fuhren_rechnungen.ID_FUHREN_RECHNUNGEN=SEQ_FUHREN_RECHNUNGEN.nextval");
			vGenerateTable.add("alter table jt_fuhren_rechnungen add primary key (ID_FUHREN_RECHNUNGEN)");
			
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK01_fuhren_rechnungen  FOREIGN KEY  (ID_VPOS_TPA_FUHRE) REFERENCES JT_VPOS_TPA_FUHRE (ID_VPOS_TPA_FUHRE) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK02_fuhren_rechnungen  FOREIGN KEY  (ID_VPOS_TPA_FUHRE_ORT) REFERENCES JT_VPOS_TPA_FUHRE_ORT (ID_VPOS_TPA_FUHRE_ORT) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK03_fuhren_rechnungen  FOREIGN KEY  (ID_VPOS_KON) REFERENCES JT_VPOS_KON  (ID_VPOS_KON) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK04_fuhren_rechnungen  FOREIGN KEY  (ID_ARTIKEL) REFERENCES JT_ARTIKEL  (ID_ARTIKEL) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK05_fuhren_rechnungen  FOREIGN KEY  (ID_VKOPF_RG) REFERENCES JT_VKOPF_RG  (ID_VKOPF_RG) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK06_fuhren_rechnungen  FOREIGN KEY  (ID_VPOS_RG) REFERENCES JT_VPOS_RG  (ID_VPOS_RG) ON DELETE CASCADE");
			
			
			
			vGenerateTable.add("update jt_fuhren_rechnungen set R_PREIS_NACH_ABZUG_ORI = R_PREIS_NACH_ABZUG, R_PREIS_EINZEL_RESULT_ORI = R_PREIS_EINZEL_RESULT, MENGE_NACH_ABZUG_ORI = MENGE_NACH_ABZUG, MENGE_LAGER_ORI = MENGE_LAGER , KORREKTUR = 0 ");
			
			vGenerateTable.add("create index INDEX1 on JT_FUHREN_RECHNUNGEN (LEISTUNGSDATUM)");
			vGenerateTable.add("create index INDEX2 on JT_FUHREN_RECHNUNGEN (ID_ARTIKEL)");
			vGenerateTable.add("create index INDEX3 on JT_FUHREN_RECHNUNGEN (ID_LAGER)");
			vGenerateTable.add("create index INDEX4 on JT_FUHREN_RECHNUNGEN (ID_ADRESSE_START)");
			vGenerateTable.add("create index INDEX5 on JT_FUHREN_RECHNUNGEN (ID_ADRESSE_STANDORT_START)");
			vGenerateTable.add("create index INDEX6 on JT_FUHREN_RECHNUNGEN (ID_ADRESSE_ZIEL)");
			vGenerateTable.add("create index INDEX7 on JT_FUHREN_RECHNUNGEN (ID_ADRESSE_STANDORT_ZIEL)");
			vGenerateTable.add("create index INDEX8 on JT_FUHREN_RECHNUNGEN (ID_LAGER_GEGENSEITE)");
			vGenerateTable.add("create index INDEX9 on JT_FUHREN_RECHNUNGEN (ID_MANDANT)");
			vGenerateTable.add("create index INDEX10 on JT_FUHREN_RECHNUNGEN (ID_EAK_CODE)");
			vGenerateTable.add("create index INDEX11 on JT_FUHREN_RECHNUNGEN (ID_SONDERLAGER)");
			
			
			boolean bOK = true;
			for (int i=0;i<vGenerateTable.size();i++)
			{
				if ( ! bibDB.ExecSQL(vGenerateTable.get(i), true))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("FEHLER: beim generieren der temporären Tabelle bei: " + vGenerateTable.get(i)));
					bOK = false;
				}
			}

			
			if (bOK){
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Generieren der temporären Tabelle...OK!" ));
			}
			
			
			this.BaueTrigger();
			this.baueViews();
			

		}
		
		
		
		private void BaueTrigger()
		{
			Vector<String> vTablesWithoutTrigger = new Vector<String>();
			vTablesWithoutTrigger.add("JD_DB_LOG");
			vTablesWithoutTrigger.add("JD_LOGIN");
			vTablesWithoutTrigger.add("TT_SORT_TABLE");
		
			String cTabelle = "JT_FUHREN_RECHNUNGEN";
					
			if (!vTablesWithoutTrigger.contains(cTabelle))
			{
				
				// zuerst die (falls noch nicht vorhanden) zusatzfelder fuer das mitprotokollieren des erstellers eine satzes und des erstelldatums eines satzes
				// in die tabellen reinschreiben
				String cAlterTable1 = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"ALTER TABLE "+bibE2.cTO()+"."+cTabelle+" ADD (ERZEUGT_VON NVARCHAR2(10))";
				String cAlterTable2 = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"ALTER TABLE "+bibE2.cTO()+"."+ cTabelle+" ADD (ERZEUGT_AM DATE)";
				String cFill1		= MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE "+bibE2.cTO()+"."+ cTabelle+" SET ERZEUGT_AM=LETZTE_AENDERUNG WHERE ERZEUGT_AM IS NULL";
				String cFill2		= MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE "+bibE2.cTO()+"."+ cTabelle+" SET ERZEUGT_VON=GEAENDERT_VON WHERE ERZEUGT_VON IS NULL";
		
				
				MyE2_String  cMeldung = new MyE2_String("");
				
				if (bibDB.ExecSQL(cAlterTable1, true))
				{
					cMeldung.addUnTranslated("<ERZEUGT_VON added>");
				}
				
				if (bibDB.ExecSQL(cAlterTable2, true))
				{
					cMeldung.addUnTranslated("<ERZEUGT_AM added>");
				}

				if (!bibDB.ExecSQL(cFill1, true))
				{
					cMeldung.addUnTranslated("<Error Filling: ERZEUGT_AM>");
				}
				if (!bibDB.ExecSQL(cFill2, true))
				{
					cMeldung.addUnTranslated("<Error Filling: ERZEUGT_VON>");
				}

				StringBuilder oTrigg = new StringBuilder();
				oTrigg.append("CREATE OR REPLACE TRIGGER trigg_##WERT## \n");
				oTrigg.append("BEFORE INSERT OR UPDATE OR DELETE \n");
				oTrigg.append("ON ##TABELLE## \n");
				oTrigg.append("FOR EACH ROW \n");
				oTrigg.append("DECLARE \n");
			    oTrigg.append("ora_sess        NVARCHAR2(100); \n");
				oTrigg.append("BEGIN \n");
				oTrigg.append("IF INSERTING THEN \n");
				oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:NEW.ID_##WERT##,'##TABELLE##','INSERT',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");
				oTrigg.append(":NEW.ERZEUGT_VON:=:NEW.GEAENDERT_VON; \n");
				oTrigg.append(":NEW.ERZEUGT_AM:=:NEW.LETZTE_AENDERUNG; \n");
				oTrigg.append("ELSIF UPDATING THEN \n");
				oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:OLD.ID_##WERT##,'##TABELLE##','UPDATE',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");
				oTrigg.append("ELSIF DELETING THEN \n");
				oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:OLD.ID_##WERT##,'##TABELLE##','DELETE',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");
				oTrigg.append("END IF; \n");
				oTrigg.append("END; \n");

				String cTriggerStatement = oTrigg.toString();

				
				String cTriggerHelp = bibALL.ReplaceTeilString(cTriggerStatement, "##WERT##", cTabelle.substring(3));
				cTriggerHelp = 	bibALL.ReplaceTeilString(cTriggerHelp, "##TABELLE##", cTabelle);
				cTriggerHelp = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cTriggerHelp;
				
				if (bibDB.ExecSQL(cTriggerHelp, true))
				{
					cMeldung.addUnTranslated("<Creating Trigger OK>");
					bibMSG.add_MESSAGE(new MyE2_Info_Message(cMeldung));
				}
				else
				{
					cMeldung.addUnTranslated("<Creating Trigger ERROR>");	
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cMeldung));
					
				}
			}
		}
		
		
		private void baueViews()
		{
			String 		cAbfrageMandanten = "SELECT ID_MANDANT,NVL(NAME1,TO_CHAR(ID_MANDANT)) FROM "+bibE2.cTO()+".JD_MANDANT order by ID_MANDANT";
			String[][] 	cMandanten = bibDB.EinzelAbfrageInArray(cAbfrageMandanten, false);

			if (cMandanten == null || cMandanten.length==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der vorhandenen Mandanten !"));
				return;
			}
			
			
			//Vector<String> vOutPutMessages = new Vector<String>();
			String cTabelle = "JT_FUHREN_RECHNUNGEN";
			
			
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


	@Override
	public String get_ToolTip() throws myException {
		return "";
	}

	@Override
	public MyE2_Button get_ListButton() throws myException {
		MyE2_Button oButtonList = new MyE2_Button(this.get_cAuswertungsNamen());
		oButtonList.setToolTipText(this.get_ToolTip());
		
		oButtonList.add_GlobalAUTHValidator_AUTO(this.get_cALLOW_FLAG());
		oButtonList.setLineWrap(true);

		return oButtonList;
	}
	
}
