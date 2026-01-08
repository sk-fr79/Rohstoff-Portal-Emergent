package panter.gmbh.basics4project.SANKTION;

import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class SANKTION_MessageService{

	public SANKTION_MessageService() {
		super();
	}

	public SANKTION_MessageService generate_message(String idAdresse) throws myException{

		Rec21 recPruefung = new Rec21(_TAB.sanktion_pruefung)._fill_sql(
				new SEL()
				.FROM(SANKTION_PRUEFUNG.T())
				.WHERE(new vgl_YN(SANKTION_PRUEFUNG.aktiv, true))
				.AND(new vgl(SANKTION_PRUEFUNG.id_adresse, idAdresse))
				.AND(new vgl(SANKTION_PRUEFUNG.id_mandant, bibALL.get_ID_MANDANT()))
				.s()
				);

		String nachricht = get_nachricht_message(recPruefung);

		ENUM_MESSAGE_PROVIDER
		.MESSAGE_ADRESSE_FREIGABE
		.generateMessages(MODUL.SANKTION_PRUEFUNG_LIST, recPruefung.get_key_value(), nachricht ,"Springe zu Freigabe Tabelle");

		return this;
	}

	private String get_nachricht_message(Rec21 recPruefung) throws myException{
		String msg = "";
		
		if(recPruefung !=  null) {
			long idAddr = recPruefung.get_myLong_dbVal(SANKTION_PRUEFUNG.id_adresse,new MyLong(0)).get_oLong();
			if(idAddr>0) {
				Rec21_adresse recAddr = new Rec21_adresse()._fill_id(idAddr);
				if(recAddr != null) {
					boolean has_freigabe = recPruefung.is_yes_db_val(SANKTION_PRUEFUNG.freigabe);
					if(!has_freigabe) {
						msg = " Die Adresse "+ recAddr.__get_name1_ort() + " ("+ recAddr.getFs(ADRESSE.id_adresse,"-") +  ") hat nicht die Freigabe.";
					}
				}
			}
		}
		
		return msg;
	}

}
