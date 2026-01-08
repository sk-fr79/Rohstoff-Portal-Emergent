package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;


import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MITARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MITARBEITER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class FS_BT_KOPIERE_FIRMA_UND_ANHAENGE extends MyE2_Button
{
	
	private E2_NavigationList  oNaviList = null;

	public FS_BT_KOPIERE_FIRMA_UND_ANHAENGE(E2_NavigationList oNaviList)
	{
		super(new MyE2_String("Kopiere Firma incl. Mitarbeiter/Lieferadressen/Sorten"));
		this.oNaviList = oNaviList;
		
		this.add_GlobalAUTHValidator_AUTO("KOPIERE_ADRESSE");
		
		this.add_oActionAgent(new ownActionAgent());
		
		this.setToolTipText(new MyE2_String("Kopiert eine Adresse mit allen Telefon/Mail-Einträgen, sowie allen Mitarbeitern und Lieferadressen ...").CTrans());
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_NavigationList  ooNaviList = FS_BT_KOPIERE_FIRMA_UND_ANHAENGE.this.oNaviList;
			
			Vector<String> vIDs = ooNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau EINE Adresse zum Kopieren auswählen !"));
				return;
			}
			
			
			
			RECORD_ADRESSE      	recAdresse_Orig = 		new RECORD_ADRESSE(vIDs.get(0));
			
			RECLIST_MITARBEITER     recListMitarbeiter    = recAdresse_Orig.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis();
			RECLIST_LIEFERADRESSE   recListLieferadresse  = recAdresse_Orig.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis();
			RECLIST_ARTIKELBEZ_LIEF recListArtikelBez = 	recAdresse_Orig.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse();

			Vector<MySqlStatementBuilder>  vStmtBuilders = new Vector<MySqlStatementBuilder>();
			
			String cNewID_ADRESSE_BASIS = bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_ADRESSE");
			
			this.add_StmtBuildersKopiereGanzeAdresse(recAdresse_Orig, vStmtBuilders, cNewID_ADRESSE_BASIS);
			
			for (int i=0;i<recListMitarbeiter.get_vKeyValues().size();i++)
			{
				RECORD_MITARBEITER  recMITARBEITER = 		recListMitarbeiter.get(i);
				RECORD_ADRESSE      recADRESSE_M=  			recMITARBEITER.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
				
				String cNewID_MITARBEITER = 		bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_MITARBEITER");
				String cNewID_ADRESSE_MITARBEITER = bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_ADRESSE");
				
				//jetzt die mitarbeiter-adresse kopieren
				this.add_StmtBuildersKopiereGanzeAdresse(recADRESSE_M, vStmtBuilders, cNewID_ADRESSE_MITARBEITER);
				
				//dann verbinden
				MySqlStatementBuilder  oSTMT_Mitarbeiter = 	recMITARBEITER.get_StatementBuilderFilledWithActualValues("JT_MITARBEITER");
				oSTMT_Mitarbeiter.addSQL_Paar(RECORD_MITARBEITER.FIELD__ID_MITARBEITER, 		cNewID_MITARBEITER);
				oSTMT_Mitarbeiter.addSQL_Paar(RECORD_MITARBEITER.FIELD__ID_ADRESSE_BASIS, 		cNewID_ADRESSE_BASIS);
				oSTMT_Mitarbeiter.addSQL_Paar(RECORD_MITARBEITER.FIELD__ID_ADRESSE_MITARBEITER, cNewID_ADRESSE_MITARBEITER);
				vStmtBuilders.add(oSTMT_Mitarbeiter);
			}
			
			for (int i=0;i<recListLieferadresse.get_vKeyValues().size();i++)
			{
				RECORD_LIEFERADRESSE  	recLIEFERADRESSE = 	recListLieferadresse.get(i);
				RECORD_ADRESSE      	recADRESSE_L=  		recLIEFERADRESSE.get_UP_RECORD_ADRESSE_id_adresse_liefer();
				
				String cNewID_LIEFERADRESSE = 			bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_LIEFERADRESSE");
				String cNewID_ADRESSE_L = 				bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_ADRESSE");
				
				//jetzt die mitarbeiter-adresse kopieren
				this.add_StmtBuildersKopiereGanzeAdresse(recADRESSE_L, vStmtBuilders, cNewID_ADRESSE_L);
				
				//dann verbinden
				MySqlStatementBuilder  oSTMT_Lieferadresse = 	recLIEFERADRESSE.get_StatementBuilderFilledWithActualValues("JT_LIEFERADRESSE");
				oSTMT_Lieferadresse.addSQL_Paar(RECORD_LIEFERADRESSE.FIELD__ID_LIEFERADRESSE, 		cNewID_LIEFERADRESSE);
				oSTMT_Lieferadresse.addSQL_Paar(RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_BASIS, 		cNewID_ADRESSE_BASIS);
				oSTMT_Lieferadresse.addSQL_Paar(RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_LIEFER,		cNewID_ADRESSE_L);
				vStmtBuilders.add(oSTMT_Lieferadresse);
			}
			
			//2014-07-16: kopieren von artikelbzeichungen
			for (int i=0;i<recListArtikelBez.get_vKeyValues().size();i++)
			{
				RECORD_ARTIKELBEZ_LIEF 	recARTBEZ = 				recListArtikelBez.get(i);
				
				MySqlStatementBuilder oStmt_new_Kom = recARTBEZ.get_StatementBuilderFilledWithActualValues(_DB.ARTIKELBEZ_LIEF);
				oStmt_new_Kom.addSQL_Paar(_DB.ARTIKELBEZ_LIEF$ID_ADRESSE, 			cNewID_ADRESSE_BASIS);
				oStmt_new_Kom.addSQL_Paar(_DB.ARTIKELBEZ_LIEF$ID_ARTIKELBEZ_LIEF,  bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_ARTIKELBEZ_LIEF"));
				vStmtBuilders.add(oStmt_new_Kom);
			}
			
			
			
			Vector<String> vSQL = new Vector<String>();
			for (int i=0;i<vStmtBuilders.size();i++)
			{
				vSQL.add(vStmtBuilders.get(i).get_CompleteInsertString(vStmtBuilders.get(i).get_cTableName()));
			}
			
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			
			if (bibMSG.get_bIsOK())
			{
				ooNaviList.ADD_NEW_ID_TO_ALL_VECTORS(cNewID_ADRESSE_BASIS);
				ooNaviList._REBUILD_ACTUAL_SITE(cNewID_ADRESSE_BASIS);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Bitte tragen Sie für die neue Adresse noch folgende Felder ein: KREDITOR-Nummer/DEBITOR-Nummer")));
			}
			
		}
		
		//fuellt den Vector<MySqlStatementBuilder> mit den SQLStatementBuildern einer Adresse mit angehaengten Telefonnummern und mail-adressen 
		private void add_StmtBuildersKopiereGanzeAdresse(RECORD_ADRESSE recAdress_Orig, Vector<MySqlStatementBuilder>  vStmtBuilders, String cID_ADRESSE_NEU) throws myException
		{
			//nur erlaubt fuer folgende adresstypen:
			if (recAdress_Orig.get_ADRESSTYP_lValue(-1l)==myCONST.ADRESSTYP_FIRMENINFO  || 
				recAdress_Orig.get_ADRESSTYP_lValue(-1l)==myCONST.ADRESSTYP_LIEFERADRESSE  ||
				recAdress_Orig.get_ADRESSTYP_lValue(-1l)==myCONST.ADRESSTYP_MITARBEITER )
			
			{
				MySqlStatementBuilder  oSTMT_NewBaseAdress = 	recAdress_Orig.get_StatementBuilderFilledWithActualValues("JT_ADRESSE");
				
				oSTMT_NewBaseAdress.addSQL_Paar(RECORD_ADRESSE.FIELD__ID_ADRESSE, cID_ADRESSE_NEU);
				vStmtBuilders.add(oSTMT_NewBaseAdress);
				
				if (recAdress_Orig.get_ADRESSTYP_lValue(-1l)==myCONST.ADRESSTYP_FIRMENINFO)
				{
					//2013-11-05: einige felder anders besetzen
					oSTMT_NewBaseAdress.addSQL_Paar(_DB.ADRESSE$WARENEINGANG_SPERREN, "Y",true);
					oSTMT_NewBaseAdress.addSQL_Paar(_DB.ADRESSE$WARENAUSGANG_SPERREN, "Y",true);
					
					//2014-01-24: Vertragsgrenzen der handelsvertraege veraendern 
					oSTMT_NewBaseAdress.addSQL_Paar(_DB.ADRESSE$EU_BEIBLATT_EK_VERTRAG, null);
					oSTMT_NewBaseAdress.addSQL_Paar(_DB.ADRESSE$EU_BEIBLATT_VK_VERTRAG, null);
				
					oSTMT_NewBaseAdress.addSQL_Paar(_DB.ADRESSE$EU_BEIBLATT_INFOFELD, null);
					
					MySqlStatementBuilder  oSTMT_NewBaseFirmenInfo = recAdress_Orig.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_StatementBuilderFilledWithActualValues("JT_FIRMENINFO");
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(RECORD_FIRMENINFO.FIELD__ID_ADRESSE, 		cID_ADRESSE_NEU);
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(RECORD_FIRMENINFO.FIELD__ID_FIRMENINFO, 	bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_FIRMENINFO"));
					
					//in der firmeninfo muss die kreditor und debitor-nummer leergemacht werden, damit keine index-fehler auftauchen
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(RECORD_FIRMENINFO.FIELD__KREDITOR_NUMMER, 	null);
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(RECORD_FIRMENINFO.FIELD__DEBITOR_NUMMER, 	null);
					
					//2013-11-05: einige felder anders besetzen
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(_DB.FIRMENINFO$STEUERNUMMER, 	null);
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(_DB.FIRMENINFO$UMSATZSTEUERLKZ, null);
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(_DB.FIRMENINFO$UMSATZSTEUERID, 	null);
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(_DB.FIRMENINFO$HANDELSREGISTER, null);
					oSTMT_NewBaseFirmenInfo.addSQL_Paar(_DB.FIRMENINFO$BETRIEBSNUMMER_SAA, null);
					
					vStmtBuilders.add(oSTMT_NewBaseFirmenInfo);

				}
				
				
				//die Telefonnummern der Basis-Adresse
				RECLIST_KOMMUNIKATION  recListKom =			recAdress_Orig.get_DOWN_RECORD_LIST_KOMMUNIKATION_id_adresse();
				RECLIST_EMAIL          recListEmail = 		recAdress_Orig.get_DOWN_RECORD_LIST_EMAIL_id_adresse();
				
				
				for (int i=0;i<recListKom.get_vKeyValues().size();i++)
				{
					MySqlStatementBuilder oStmt_new_Kom = recListKom.get(i).get_StatementBuilderFilledWithActualValues("JT_KOMMUNIKATION");
					oStmt_new_Kom.addSQL_Paar(RECORD_KOMMUNIKATION.FIELD__ID_ADRESSE, 		cID_ADRESSE_NEU);
					oStmt_new_Kom.addSQL_Paar(RECORD_KOMMUNIKATION.FIELD__ID_KOMMUNIKATION, bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_KOMMUNIKATION"));
					vStmtBuilders.add(oStmt_new_Kom);
				}
				
				for (int i=0;i<recListEmail.get_vKeyValues().size();i++)
				{
					MySqlStatementBuilder oStmt_new_eMail = recListEmail.get(i).get_StatementBuilderFilledWithActualValues("JT_EMAIL");
					oStmt_new_eMail.addSQL_Paar(RECORD_EMAIL.FIELD__ID_ADRESSE, 	cID_ADRESSE_NEU);
					oStmt_new_eMail.addSQL_Paar(RECORD_EMAIL.FIELD__ID_EMAIL,		 bibALL.get_SEQUENCE_NEXT_VALUE("SEQ_EMAIL"));
					vStmtBuilders.add(oStmt_new_eMail);
				}
			}
			else
			{
				throw new myException("Copy only allowed for adresses typ: ADRESSTYP_FIRMENINFO/ADRESSTYP_LIEFERADRESSE/ADRESSTYP_MITARBEITER");
			}
		}
	}
	
}
