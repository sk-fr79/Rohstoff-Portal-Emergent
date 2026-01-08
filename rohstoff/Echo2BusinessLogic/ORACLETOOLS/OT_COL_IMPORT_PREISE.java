package rohstoff.Echo2BusinessLogic.ORACLETOOLS;


import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_BasicContainerToShowInfos;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

public class OT_COL_IMPORT_PREISE extends MyE2_Column
{
	
	public 	static final String 	TYPE_NORMALARTIKEL = 	"TYPE_NORMALARTIKEL";
	public 	static final String 	TYPE_DIENSTLEISTUNG = 	"TYPE_DIENSTLEISTUNG";
	
	private 	MyE2_SelectField		oSelectMonatJahr = null;
	
	private 	MyE2_Button 			oButtonImport = new MyE2_Button(new MyE2_String("STARTE IMPORT"));
	private 	OT_BasicContainer 		oBasicContainer = null;
	private 	MyDBToolBox 			oDBORA = null; 
	private 	MyDBToolBox 			oDBSAP = null;
	
	private 	Vector<String>			vNewPrices = new Vector<String>();
	private 	Vector<String>			vUpdatePrices = new Vector<String>();
	private 	Vector<String> 			vFehler = new Vector<String>();
	
	
	private 	MyE2_Grid				gridStatus = new MyE2_Grid(3,1);
	private 	String 					cArtikelTyp = null;
	
	public OT_COL_IMPORT_PREISE(String cTypArtikel,OT_BasicContainer BasicContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		this.oBasicContainer = 	BasicContainer;
		this.oDBORA = 			this.oBasicContainer.get_oDBORA();
		this.oDBSAP = 			this.oBasicContainer.get_oDBSAP();
		
		this.cArtikelTyp = cTypArtikel;
		
		this.oButtonImport.add_oActionAgent(new actionAgentStartImport());

		this.oButtonImport.add_GlobalValidator(new E2_ButtonAUTHValidator(this.oBasicContainer.get_MODUL_IDENTIFIER(),"START_IMPORT_ARTIKEL"));

		
		if (cTypArtikel.equals(OT_COL_IMPORT_PREISE.TYPE_NORMALARTIKEL))
			this.add(
					new MyE2_Label(new MyE2_String("Übernimmt die Einkaufspreise aus Tutos ins Rohstoffprogramm !!!"),
								   MyE2_Label.STYLE_TITEL_BIG()),
									E2_INSETS.I_10_10_10_10);
		else
			this.add(
					new MyE2_Label(new MyE2_String("Übernimmt die Dienstleistungspreise aus Tutos ins Rohstoffprogramm !!!"),
								   MyE2_Label.STYLE_TITEL_BIG()),
									E2_INSETS.I_10_10_10_10);
			

		
		String cSQLInfo = "SELECT 	DISTINCT " +
								" SUBSTR(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYYMMDD'),5,2)||'-'||" +
								" SUBSTR(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYYMMDD'),1,4), " +
								" SUBSTR(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYYMMDD'),1,4)||'-'||" +
								" SUBSTR(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYYMMDD'),5,2) "+
				                    " FROM "+
									bibE2.cTO()+".JT_VKOPF_STD, " +bibE2.cTO()+".JT_VPOS_STD, "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT "+ 
									" WHERE " +
									"     JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD " +
									" AND JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD " +
									" AND JT_VKOPF_STD.VORGANG_TYP='"+myCONST.VORGANGSART_ABNAHMEANGEBOT+"'"+
									" AND JT_VPOS_STD_ANGEBOT.GUELTIG_VON IS NOT NULL " +
									" AND   NVL(JT_VKOPF_STD.DELETED,'N')='N' "+
									" AND   NVL(JT_VPOS_STD.DELETED,'N')='N' "+
									" ORDER BY  " +
									" SUBSTR(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYYMMDD'),1,4)||'-'||" +
									" SUBSTR(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYYMMDD'),5,2)";
		
		String[][] cAnswer = this.oDBSAP.EinzelAbfrageInArray(cSQLInfo,"");
		
		if (cAnswer == null)
			throw new myException("COL_IMPORT_PREISE: Error Querying Months with Prices !");
		
		if (cAnswer.length == 0)
		{
			this.add(
					new MyE2_Label(new MyE2_String("FEHLER . KEINE PREISE GEFUNDEN !!!"),
								   MyE2_Label.STYLE_ERROR_BIG()),
									E2_INSETS.I_10_10_10_10);
			return;
		}
		
		// umkopieren und leer-eintrag
		String[] ccAnswer = new String[cAnswer.length+1];
		ccAnswer[0]="-";
		for (int i=0;i<cAnswer.length;i++)
			ccAnswer[i+1]=cAnswer[i][0];

		this.oSelectMonatJahr = new MyE2_SelectField(ccAnswer,"",false);
		this.oSelectMonatJahr.add_oActionAgent(new actionAgentSelectTimeRange());

		this.add(new E2_ComponentGroupHorizontal(0,
				new MyE2_Label(new MyE2_String("Monat-Jahr für Import:   ")),this.oSelectMonatJahr,E2_INSETS.I_0_2_10_2),E2_INSETS.I_10_10_10_10);

		this.add(this.gridStatus,E2_INSETS.I_10_10_10_10);
	}

	
	
	// fuellt die update und insert-vectoren, actionagent des monats-dropdown-selektors
	private class actionAgentSelectTimeRange extends XX_ActionAgent
	{
		// ein paar statistik-sammler fuer die eingaben
		private int iPreisLeer = 0;
		
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			OT_COL_IMPORT_PREISE oThis = OT_COL_IMPORT_PREISE.this;
			oThis.vNewPrices.removeAllElements();
			oThis.vUpdatePrices.removeAllElements();
			oThis.vFehler.removeAllElements();
			oThis.gridStatus.removeAll();
			
			this.iPreisLeer = 0;
			
			// start-knopf aktiv
			try
			{
				oThis.oButtonImport.set_bEnabled_For_Edit(true);
			}
			catch (myException ex) {}

			
			
			try
			{
				MyE2_SelectField 	oSelField = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
				String 				cActualValue = oSelField.get_ActualWert().trim();
			
				if (cActualValue.equals("-"))
					return;
				
				String cMonat = cActualValue.substring(0,2);
				String cJahr = cActualValue.substring(3,7);
				
				String cQueryPreise = "SELECT " +
											" JT_VKOPF_STD.ID_VKOPF_STD," +    		// 0
											" JT_VKOPF_STD.id_adresse," +      		// 1
											" JT_VPOS_STD_ANGEBOT.gueltig_von," +	// 2
											" JT_VPOS_STD_ANGEBOT.gueltig_bis," +	// 3
											" JT_VKOPF_STD.ABGESCHLOSSEN," +		// 4
											" JT_VPOS_STD.ID_VPOS_STD," +			// 5
											" JT_VPOS_STD.POSITION_TYP," +			// 6
											" JT_VPOS_STD.POSITIONSNUMMER," +		// 7
											" JT_VPOS_STD.ID_ARTIKEL_BEZ," +		// 8
											" JT_VPOS_STD.ARTBEZ1," +				// 9
											" JT_VPOS_STD.ARTBEZ2," +				// 10
											" JT_VPOS_STD.ANR1," +					// 11
											" JT_VPOS_STD.ANR2," +					// 12
											" JT_VPOS_STD.EINZELPREIS," +			// 13
											" JT_VPOS_STD.LIEFERBEDINGUNGEN," +		// 14
											" JT_ADRESSE.##WERT1## " +				// 15
										" FROM "+
												bibE2.cTO()+".JT_VKOPF_STD, "+
												bibE2.cTO()+".JT_VPOS_STD, "+
												bibE2.cTO()+".JT_VPOS_STD_ANGEBOT, "+
												bibE2.cTO()+".JT_ADRESSE, " +
												bibE2.cTO()+".JT_ARTIKEL_BEZ, " +
												bibE2.cTO()+".JT_ARTIKEL " +
										" WHERE " +
											" JT_VKOPF_STD.ID_VKOPF_STD = JT_VPOS_STD.ID_VKOPF_STD AND " +
											" JT_VPOS_STD.ID_VPOS_STD = JT_VPOS_STD_ANGEBOT.ID_VPOS_STD AND " +
											" JT_VPOS_STD.ID_ARTIKEL_BEZ = JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AND " +
											" JT_ARTIKEL_BEZ.ID_ARTIKEL = JT_ARTIKEL.ID_ARTIKEL AND " +
											" JT_VKOPF_STD.ID_ADRESSE = JT_ADRESSE.ID_ADRESSE AND " +
											" JT_VKOPF_STD.VORGANG_TYP='"+myCONST.VORGANGSART_ABNAHMEANGEBOT+"' AND "+
											"   NVL(JT_VKOPF_STD.DELETED,'N')='N' AND "+
											"   NVL(JT_VPOS_STD.DELETED,'N')='N' AND "+
											"   NVL(JT_ARTIKEL.DIENSTLEISTUNG,'N')='##WERT2##' AND "+
											" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'MM'))="+cMonat+" AND " +
											" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY'))="+cJahr+" AND POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"'";
				

				// dienstleitungsartikel unterscheidet die LIEF_NR / ABN_NR und der schalter im Artikelstamm "DIENSTLEISTUNG"
				if (oThis.cArtikelTyp.equals(OT_COL_IMPORT_PREISE.TYPE_NORMALARTIKEL))
				{
					cQueryPreise = bibALL.ReplaceTeilString(cQueryPreise,"##WERT1##","LIEF_NR"); 
					cQueryPreise = bibALL.ReplaceTeilString(cQueryPreise,"##WERT2##","N"); 
				}
				else
				{
					cQueryPreise = bibALL.ReplaceTeilString(cQueryPreise,"##WERT1##","ABN_NR"); 
					cQueryPreise = bibALL.ReplaceTeilString(cQueryPreise,"##WERT2##","Y"); 
				}
				
				
				String[][] cQuelleSAPDB = oThis.oDBSAP.EinzelAbfrageInArray(cQueryPreise,"");
				
				if (cQuelleSAPDB == null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der Preise!!"));
					return;
				}
				
				if (cQuelleSAPDB.length == 0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Keine passenden Preise gefunden !!"));
					return;
				}
		
				
				// jetzt die preise pruefen, ob update oder insert
				for (int i=0;i<cQuelleSAPDB.length;i++)
				{
					
					String cCheck = "SELECT COUNT(*) FROM "+bibALL.get_oracle_tableowner()+".LEB231P WHERE "+
										" ID_VPOS_STD="+cQuelleSAPDB[i][5];
					
					if (oThis.cArtikelTyp.equals(OT_COL_IMPORT_PREISE.TYPE_DIENSTLEISTUNG))
							cCheck = "SELECT COUNT(*) FROM "+bibALL.get_oracle_tableowner()+".LEB251P WHERE "+
									" ID_VPOS_STD="+cQuelleSAPDB[i][5];
					
					String cCount = oThis.oDBORA.EinzelAbfrage(cCheck).trim();
					
					if (cCount.equals("0"))
					{
						this.add_Statement(cQuelleSAPDB[i],cMonat,cJahr,true);
					}
					else if (cCount.equals("1"))
					{
						this.add_Statement(cQuelleSAPDB[i],cMonat,cJahr,false);
					}
					else
					{
						oThis.vFehler.add("ERROR: Abfragestatus undefiniert! ID_VPOS_STD: "+cQuelleSAPDB[i][5]);
					}
				}
				
				oThis.gridStatus.add(new MyE2_Label(new MyE2_String("Anzahl NEUE Preise: ")),E2_INSETS.I_0_0_10_10);
				E2_InfoButton oB1 = new E2_InfoButton(oThis.vNewPrices, false);
				oThis.gridStatus.add(oB1,E2_INSETS.I_0_0_10_10);
				oThis.gridStatus.add(new MyE2_Label(""+oThis.vNewPrices.size()),E2_INSETS.I_0_0_10_10);

				oThis.gridStatus.add(new MyE2_Label(new MyE2_String("Anzahl UPDATE Preise: ")),E2_INSETS.I_0_0_10_10);
				E2_InfoButton oB2 = new E2_InfoButton(oThis.vUpdatePrices, false);
				oThis.gridStatus.add(oB2,E2_INSETS.I_0_0_10_10);
				oThis.gridStatus.add(new MyE2_Label(""+oThis.vUpdatePrices.size()),E2_INSETS.I_0_0_10_10);
				
				oThis.gridStatus.add(new MyE2_Label(new MyE2_String("Anzahl Fehler: "), MyE2_Label.STYLE_ERROR_NORMAL()),E2_INSETS.I_0_0_10_10);
				E2_InfoButton oB3 = new E2_InfoButton(oThis.vFehler, false);
				oThis.gridStatus.add(oB3,E2_INSETS.I_0_0_10_10);
				oThis.gridStatus.add(new MyE2_Label(""+oThis.vFehler.size()),E2_INSETS.I_0_0_10_10);

				if (oThis.vNewPrices.size()==0) oB1.set_bEnabled_For_Edit(false);
				if (oThis.vUpdatePrices.size()==0) oB2.set_bEnabled_For_Edit(false);
				if (oThis.vFehler.size()==0) oB3.set_bEnabled_For_Edit(false);
				
				oThis.gridStatus.add(oThis.oButtonImport,3,E2_INSETS.I_0_0_10_10);
			
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Aufbau der SQL-Stapel !"));
			}
			
			
		}
		
		
		/*
		 * es werden nur inserts gezaehlt, sonst ist alle doppelt /alt: filewriter,
		 * gibt einen String zurueck, der entweder mit INSERT / UPDATE oder ERROR beginnt
		 */
		private void add_Statement(String[] cDaten, String cMonat,String cJahr,boolean bInsert) throws myException
		{
	
			OT_COL_IMPORT_PREISE oThis = OT_COL_IMPORT_PREISE.this;
		

			// vorbereitungen
			// unterscheiden normalartikel, dienstleistungsartikel
			
			String  cSequenceName = bibALL.get_oracle_tableowner()+".LEB231P_DFSEQ.NEXTVAL";
			String  cFieldRefToAdress = "LIEF_NR";
			String  cORATableName = bibALL.get_oracle_tableowner()+".LEB231P";
			String  cUserText = "Liefernummer";
			if (oThis.cArtikelTyp.equals(OT_COL_IMPORT_PREISE.TYPE_DIENSTLEISTUNG))
			{
				cSequenceName = bibALL.get_oracle_tableowner()+".LEB251P_DFSEQ.NEXTVAL";
				cFieldRefToAdress = "ABN_NR";
				cORATableName = bibALL.get_oracle_tableowner()+".LEB251P";
				cUserText	= "Abnehmernummer";
			}

			
			
			
			// zuerst die liefernummer beschaffen
			String cLIEF_ABN_NR = cDaten[15];
			if (bibALL.isEmpty(cLIEF_ABN_NR))
			{
				String cText = "ERROR: Keine "+cUserText+" bei Adress-ID :"+cDaten[1];
				if (!oThis.vFehler.contains(cText))		// sonst gleicher fehler mehrfach, wenn kunden mehrere artikel zugeordnet hat
					oThis.vFehler.add(cText);
				return;
			}
			
			Vector<String> vLief_Abn_Nrs = bibALL.TrenneZeile(cLIEF_ABN_NR,"#");
			
			if (vLief_Abn_Nrs.size()==0)
			{
				String cText = "ERROR: Fehlerhafte "+cUserText+" bei Adress-ID :"+cDaten[1];
				if (!oThis.vFehler.contains(cText))		// sonst gleicher fehler mehrfach, wenn kunden mehrere artikel zugeordnet hat
					oThis.vFehler.add(cText);
				return;
			}
				
			
			String cLiefnr = (String)vLief_Abn_Nrs.get(0);
			Integer ILiefnr = bibALL.String2Integer(cLiefnr);
			if (ILiefnr == null || ILiefnr.intValue()==0)
			{
				String cText = "ERROR: Fehlerhafte (keine Zahl) "+cUserText+" bei Adress-ID :"+cDaten[1];
				if (!oThis.vFehler.contains(cText))		// sonst gleicher fehler mehrfach, wenn kunden mehrere artikel zugeordnet hat
					oThis.vFehler.add(cText);
				return;
			}

			

			/*
			 * ab hier werden sie exportiert
			 */
			MySqlStatementBuilder oZOInsert = new MySqlStatementBuilder();
			
			String cPreis = cDaten[13];
			if (bibALL.isEmpty(cPreis))
				cPreis ="NULL";
			
			// leere werden gezaehlt, aber trotzdem mitgenommen
			if (cPreis.equals("NULL"))
				this.iPreisLeer++;

				
			
			if (bInsert) oZOInsert.addSQL_Paar("RECNUM",cSequenceName,false,true);
			oZOInsert.addSQL_Paar(cFieldRefToAdress,bibALL.MakeSql(""+ILiefnr.intValue()),false,true);
			oZOInsert.addSQL_Paar("MONAT",cMonat,false,true);
			oZOInsert.addSQL_Paar("JAHR",cJahr.substring(2,4),false,true);
			oZOInsert.addSQL_Paar("VON_DATUM",bibALL.MakeSql(bibALL.FormatDatum(cDaten[2])),false,true);
			oZOInsert.addSQL_Paar("BIS_DATUM",bibALL.MakeSql(bibALL.FormatDatum(cDaten[3])),false,true);
			oZOInsert.addSQL_Paar("ANR1",bibALL.MakeSql(cDaten[11]),false,true);
			oZOInsert.addSQL_Paar("ANR2",bibALL.MakeSql(cDaten[12]),false,true);
			oZOInsert.addSQL_Paar("PREIS_DM",cPreis,false,true);
			oZOInsert.addSQL_Paar("PREIS_FW",cPreis,false,true);
			oZOInsert.addSQL_Paar("ARTBEZ",bibALL.MakeSql(bibALL.get_LeftString(cDaten[9],30)),false,true);
			oZOInsert.addSQL_Paar("BEZEICHNUNG",bibALL.MakeSql(bibALL.get_LeftString(bibALL.CleanString(cDaten[10]),40)),false,true);
			oZOInsert.addSQL_Paar("LIEFERVORSCHR",bibALL.MakeSql(bibALL.get_LeftString(cDaten[14],40)),false,true);
			
			// kontraktnummer ist nur bei normalartikeln vorhanden
			if (oThis.cArtikelTyp.equals(OT_COL_IMPORT_PREISE.TYPE_NORMALARTIKEL))
				if (bInsert) oZOInsert.addSQL_Paar("KONTRAKT_NR","0",false,true);
			
			oZOInsert.addSQL_Paar("ID_VPOS_STD",cDaten[5],false,true);
			
			String cTagessatz = "' '";
			if (cDaten[2].equals(cDaten[3]))
				cTagessatz = "'T'";
			
			// tagessatz existiert nur in NORMAL-Artikel
			if (oThis.cArtikelTyp.equals(OT_COL_IMPORT_PREISE.TYPE_NORMALARTIKEL))
				oZOInsert.addSQL_Paar("TAGESSATZ",cTagessatz,false,true);

			
			//pruefquery, ob es bereits einen preis mit den rahmendaten gibt ohne ID_VPOS_STD
			String  cPRUEFQuery = "SELECT COUNT(*) FROM "+cORATableName+
										" WHERE " +
										" ANR1="+bibALL.MakeSql(cDaten[11])+" AND "+
										" ANR2="+bibALL.MakeSql(cDaten[12])+" AND "+
										" TAGESSATZ="+cTagessatz+" AND "+
										" LIEF_NR="+bibALL.MakeSql(""+ILiefnr.intValue())+" AND " +
										" VON_DATUM="+bibALL.MakeSql(bibALL.FormatDatum(cDaten[2]))+" AND "+
										" ID_VPOS_STD IS NULL";
			if (oThis.cArtikelTyp.equals(OT_COL_IMPORT_PREISE.TYPE_DIENSTLEISTUNG))
				cPRUEFQuery = "SELECT COUNT(*) FROM "+cORATableName+
										" WHERE " +
										" ANR1="+bibALL.MakeSql(cDaten[11])+" AND "+
										" ANR2="+bibALL.MakeSql(cDaten[12])+" AND "+
										" ABN_NR="+bibALL.MakeSql(""+ILiefnr.intValue())+" AND " +
										" VON_DATUM="+bibALL.MakeSql(bibALL.FormatDatum(cDaten[2]))+" AND "+
										" ID_VPOS_STD IS NULL";
			
			String cKontrollQuery = oThis.oDBORA.EinzelAbfrage(cPRUEFQuery).trim();
			if (cKontrollQuery.equals("1"))
			{
				oThis.vFehler.add("ERROR: Es existiert schon ein Eintrag "+
										cDaten[11]+"-"+cDaten[12]+" // Lief/Abn: "+cDaten[15]+"  ab Datum "+
										bibALL.FormatDatum(cDaten[2])+" aus Rohstoffprogramm !");
			}
			else if (cKontrollQuery.equals("0"))
			{
				if (bInsert)
				{
					oThis.vNewPrices.add("INSERT INTO "+cORATableName+" "+oZOInsert.get_cFieldsBlock(true)+" VALUES "+oZOInsert.get_cValuesBlock(true));
				}
				else
				{
					oThis.vUpdatePrices.add("UPDATE " +cORATableName+ " SET "+oZOInsert.get_cUpdateSetBlock(null)+ " WHERE ID_VPOS_STD="+cDaten[5]);
				}
			}
			else
			{
				oThis.vFehler.add("ERROR: Abfragefehler bei Konstellation "+
						cDaten[11]+"-"+cDaten[12]+" // Lief/Abn: "+cDaten[15]+"  ab Datum "+
						bibALL.FormatDatum(cDaten[2]));
				
			}
			return;

		}

		
		
	}
	
	
	
	
	
	
	private class actionAgentStartImport extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			E2_ConfirmBasicModuleContainer oConf = new E2_ConfirmBasicModuleContainer(
														new MyE2_String("Sicherheitsabfrage :"), 
														new MyE2_String(" Import starten ?"),
														new MyE2_String(""),
														new MyE2_String("JA"),
														new MyE2_String("NEIN"),
														new Extent(350),new Extent(350));
				
			oConf.set_ActionAgentForOK(new actionAgentGO());
			oConf.show_POPUP_BOX();
		}
	}
	
	/*
	 * wirklich importieren ...
	 */
	private class actionAgentGO extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			OT_COL_IMPORT_PREISE oThis = OT_COL_IMPORT_PREISE.this;

			Vector<String> vFehlerSQLNeu = new Vector<String>();
			Vector<String> vFehlerSQLUpdate = new Vector<String>();
			
			for (int i=0;i<oThis.vNewPrices.size();i++)
			{
				String cSQL = (String)oThis.vNewPrices.get(i);
				if (! oThis.oDBORA.ExecSQL(cSQL,true))
					vFehlerSQLNeu.add(cSQL);
			}

			for (int i=0;i<oThis.vUpdatePrices.size();i++)
			{
				String cSQL = (String)oThis.vUpdatePrices.get(i);
				if (! oThis.oDBORA.ExecSQL(cSQL,true))
					vFehlerSQLUpdate.add(cSQL);
			}

			Vector<String> vFehlerGesamt = new Vector<String>();
			vFehlerGesamt.addAll(vFehlerSQLNeu);
			vFehlerGesamt.addAll(vFehlerSQLUpdate);

			if (vFehlerGesamt.size()>0)
			{
				new E2_BasicContainerToShowInfos(new MyE2_String("SQL-Fehler ..."),vFehlerGesamt,new Extent(600),new Extent(700), false);
			}
			else
			{
				MyE2_String cMessage = new MyE2_String("Erfolgreiche NEUEINTRÄGE:");
				cMessage.addUnTranslated(""+(oThis.vNewPrices.size()-vFehlerSQLNeu.size()));
				cMessage.addString(new MyE2_String("     ---   Erfolgreiche Updates "));
				cMessage.addUnTranslated(""+(oThis.vUpdatePrices.size()-vFehlerSQLUpdate.size()));
				bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(cMessage.CTrans()));
			}
			
			// start-knopf verboten
			try
			{
				oThis.oButtonImport.set_bEnabled_For_Edit(false);
			}
			catch (myException ex) {}
			
		}
	}
	

	
	
	
	
}
