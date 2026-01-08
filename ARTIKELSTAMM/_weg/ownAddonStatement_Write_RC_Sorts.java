package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM._weg;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP.builder_AddOnSQL_STATEMENTS;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


public class ownAddonStatement_Write_RC_Sorts implements builder_AddOnSQL_STATEMENTS {

	@Override
	public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP,MyE2_MessageVector oMV) throws myException {
		Vector<String>  vSQL = new Vector<String>();
		
		String cID_LAND4RC = bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("");
		
		if (S.isEmpty(cID_LAND4RC)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("System ist unvollstaendig! Bitte erfassen Sie im Mandantenstamm ein LAND!")));
			return vSQL;
		}
		
		String cLAND = bibALL.get_RECORD_MANDANT().get_LANDKURZ_cF_NN("<land>");
		
		long lID_ZollTarifNummerNew = oE2_ComponentMAP.get_LActualDBValue(_DB.ARTIKEL$ID_ZOLLTARIFNUMMER, -1l, -1l).longValue();
		
		if (lID_ZollTarifNummerNew>0) {
			if (check_RC_Zolltarifnummer(lID_ZollTarifNummerNew)) {
				RECORDNEW_LAND_RC_SORTEN  nL_RC = new RECORDNEW_LAND_RC_SORTEN();
				nL_RC.set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(_DB.ARTIKEL$ID_ARTIKEL, "SEQ_ARTIKEL.CURRVAL");
				nL_RC.set_NEW_VALUE_ID_LAND(cID_LAND4RC);
				vSQL.add(nL_RC.get_InsertSQLStatementWith_Id_Field(true, true));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Sorte wurde in die RC-Liste des Landes "+cLAND+" eingetragen !")));
			}
		}
		return vSQL;
	}

	
	@Override
	public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(	E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP,MyE2_MessageVector oMV) throws myException {
		Vector<String>  vSQL = new Vector<String>();
		
		String cID_LAND4RC = bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("");
		
		if (S.isEmpty(cID_LAND4RC)) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("System ist unvollstaendig! Bitte erfassen Sie im Mandantenstamm ein LAND!")));
			return vSQL;
		}
		
		String cLAND = bibALL.get_RECORD_MANDANT().get_LANDKURZ_cF_NN("<land>");
		
		String cID_ARTIKEL = ""+oE2_ComponentMAP.get_oInternalSQLResultMAP().get_LActualDBValue(_DB.ARTIKEL$ID_ARTIKEL, true).longValue();
		if (cID_ARTIKEL.trim().equals("0")) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Interner Fehler! Sorte ist nicht feststellbar !")));
			return vSQL;
		}
		
		//hier pruefen, ob die Zolltarifummer geaendert wurde, wenn ja, dann pruefen, ob rc und den wert ins mandantenland schreiben
		
		long lID_ZollTarifNummerOld = oE2_ComponentMAP.get_oInternalSQLResultMAP().get_LActualDBValue(_DB.ARTIKEL$ID_ZOLLTARIFNUMMER, true).longValue();
		long lID_ZollTarifNummerNew = oInputMAP.get_LActualValue(_DB.ARTIKEL$ID_ZOLLTARIFNUMMER, true, true, 0l).longValue();
		
		if (check_RC_Zolltarifnummer(lID_ZollTarifNummerOld) && !check_RC_Zolltarifnummer(lID_ZollTarifNummerNew)) {
			//rc-status entfernen
			vSQL.add("DELETE FROM "+bibE2.cTO()+"."+_DB.LAND_RC_SORTEN+" WHERE "+
													_DB.LAND_RC_SORTEN$ID_LAND+"="+cID_LAND4RC+" AND "+
													_DB.LAND_RC_SORTEN$ID_ARTIKEL+"="+cID_ARTIKEL);
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Sorte wurde aus der RC-Liste des Landes "+cLAND+" entfernt !")));
		} else if (!check_RC_Zolltarifnummer(lID_ZollTarifNummerOld) && check_RC_Zolltarifnummer(lID_ZollTarifNummerNew)) {
			//rc-status dazu
			
			//zuerst pruefen, ob die sorte schon erfasst ist
			RECLIST_LAND_RC_SORTEN  rlLandSorte = new RECLIST_LAND_RC_SORTEN("SELECT * FROM "+_DB.LAND_RC_SORTEN+" WHERE "+
														_DB.LAND_RC_SORTEN$ID_LAND+"="+cID_LAND4RC+" AND "+
														_DB.LAND_RC_SORTEN$ID_ARTIKEL+"="+cID_ARTIKEL);
			
			if (rlLandSorte.get_vKeyValues().size()==0) {
				RECORDNEW_LAND_RC_SORTEN  nL_RC = new RECORDNEW_LAND_RC_SORTEN();
				nL_RC.set_NEW_VALUE_ID_ARTIKEL(cID_ARTIKEL);
				nL_RC.set_NEW_VALUE_ID_LAND(cID_LAND4RC);
				vSQL.add(nL_RC.get_InsertSQLStatementWith_Id_Field(true, true));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Sorte wurde in die RC-Liste des Landes "+cLAND+" eingetragen !")));
			}
		} else if (check_RC_Zolltarifnummer(lID_ZollTarifNummerOld) && check_RC_Zolltarifnummer(lID_ZollTarifNummerNew) && lID_ZollTarifNummerNew!=lID_ZollTarifNummerOld) {
			//zuerst pruefen, ob die sorte schon erfasst ist
			RECLIST_LAND_RC_SORTEN  rlLandSorte = new RECLIST_LAND_RC_SORTEN("SELECT * FROM "+_DB.LAND_RC_SORTEN+" WHERE "+
														_DB.LAND_RC_SORTEN$ID_LAND+"="+cID_LAND4RC+" AND "+
														_DB.LAND_RC_SORTEN$ID_ARTIKEL+"="+cID_ARTIKEL);
			
			if (rlLandSorte.get_vKeyValues().size()==0) {
				RECORDNEW_LAND_RC_SORTEN  nL_RC = new RECORDNEW_LAND_RC_SORTEN();
				nL_RC.set_NEW_VALUE_ID_ARTIKEL(cID_ARTIKEL);
				nL_RC.set_NEW_VALUE_ID_LAND(cID_LAND4RC);
				vSQL.add(nL_RC.get_InsertSQLStatementWith_Id_Field(true, true));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Sorte wurde in die RC-Liste des Landes "+cLAND+" eingetragen !")));
			}

		}
		
		return vSQL;
	}


	private boolean check_RC_Zolltarifnummer(long id_Zolltarifnummer) throws myException {
		
		if (id_Zolltarifnummer<1) {
			return false;
		}
		RECORD_ZOLLTARIFNUMMER  recZT = new RECORD_ZOLLTARIFNUMMER(id_Zolltarifnummer);
		return recZT.is_REVERSE_CHARGE_YES();
	}
	
	
}
