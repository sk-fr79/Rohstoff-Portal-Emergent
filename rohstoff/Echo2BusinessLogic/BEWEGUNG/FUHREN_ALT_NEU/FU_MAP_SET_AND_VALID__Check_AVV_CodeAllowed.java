package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EAK_CODE_ext;

public class FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed extends 	XX_MAP_Set_And_Valid {

	private String cNameOf_Field_ID_ADRESSE_LAGER = null;
	private String cNameOf_Field_ID_AVV_CODE = null;
	
	
	public FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed(	String nameOf_Field_ID_ADRESSE_LAGER, 	String nameOf_Field_ID_AVV_CODE) {
		super();
		this.cNameOf_Field_ID_ADRESSE_LAGER = nameOf_Field_ID_ADRESSE_LAGER;
		this.cNameOf_Field_ID_AVV_CODE = nameOf_Field_ID_AVV_CODE;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return pruefe_situation(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return pruefe_situation(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return pruefe_situation(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return pruefe_situation(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return new MyE2_MessageVector();
	}

	
	
	
	private MyE2_MessageVector  pruefe_situation(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
			long lID_ADRESSE_TO_CHECK = oMAP.get_LActualDBValue(this.cNameOf_Field_ID_ADRESSE_LAGER, -1l, -1l);
			long lID_AVV_CODE_TO_CHECK = oMAP.get_LActualDBValue(this.cNameOf_Field_ID_AVV_CODE, -1l, -1l);
			
			if (lID_ADRESSE_TO_CHECK>0 && lID_AVV_CODE_TO_CHECK>0) {
				String[][] cAnzahl = bibDB.EinzelAbfrageInArray(
						"SELECT COUNT(*) FROM "+bibE2.cTO()+"."+_DB.ADRESSE_EAK_CODE+" WHERE "+_DB.ADRESSE_EAK_CODE$ID_ADRESSE+"="+lID_ADRESSE_TO_CHECK);
				if (cAnzahl != null && cAnzahl.length==1 && cAnzahl[0].length==1) {
					MyLong lAnzahl= new MyLong(cAnzahl[0][0]);
					if (lAnzahl.get_oLong()==null) {
						oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String("Undefinierter Fehler (2) beim prüfen der korrekten AVV-Codes !")));
					} else {
						if (lAnzahl.get_lValue()>0) {            //pruefung erfolgt nur, wenn es eine avv-codeliste bei der adresse gibt
							String[][] arrIDs = bibDB.EinzelAbfrageInArray(
									"SELECT "+_DB.ADRESSE_EAK_CODE$ID_EAK_CODE+" FROM "+bibE2.cTO()+"."+_DB.ADRESSE_EAK_CODE+" WHERE "+_DB.ADRESSE_EAK_CODE$ID_ADRESSE+"="+lID_ADRESSE_TO_CHECK);
							
							Vector<String> vIDs = bibALL.get_VectorAusArrayColumn(arrIDs, 0);
							if (!vIDs.contains(""+lID_AVV_CODE_TO_CHECK)) {
								RECORD_EAK_CODE_ext recEAK_Code = new RECORD_EAK_CODE_ext(lID_AVV_CODE_TO_CHECK);
								RECORD_ADRESSE    	recADR = new RECORD_ADRESSE(lID_ADRESSE_TO_CHECK);
								String cEAK_Code = "<"+recEAK_Code.get_AVV_Code_Gesamt()+">";
								String cAdresse = "<"+recADR.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$ORT))+">";
								oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String("Kombination AVV-Code und Abladeadresse ist nicht erlaubt: ",true, 
																			cEAK_Code,false," zu Adresse ",true,cAdresse,false)));
							}
						}
					}
				} else {
					//abfragefehler
					oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String("Undefinierter Fehler (1) beim prüfen der korrekten AVV-Codes !")));
				}
				
			}
				
			
		}		
		return oMV_Rueck;
	
	}
	
}
