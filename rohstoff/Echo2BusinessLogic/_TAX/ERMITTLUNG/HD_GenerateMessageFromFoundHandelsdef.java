package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class HD_GenerateMessageFromFoundHandelsdef extends MESSAGE_Handler{

	public HD_GenerateMessageFromFoundHandelsdef(RECORD_HANDELSDEF recHD) throws myException {
		super(MESSAGE_CONST.MESSAGE_TYP_SYSTEM);

		if (recHD!=null && S.isFull(recHD.get_TYP_MELDUNG_cUF_NN("")) && !recHD.get_TYP_MELDUNG_cUF_NN("").equals(TAX_CONST.MELDUNG_KEINE)) {
			String cTYP4User = TAX_CONST.hmMELDUNGEN.get(recHD.get_TYP_MELDUNG_cUF_NN(""));
			if (S.isEmpty(cTYP4User)) {
				cTYP4User="Meldung !";
			}
			
			MESSAGE_Entry oEntry  = new MESSAGE_Entry()
				.set_Titel(new MyE2_String("Nachricht vom Mehrwertsteuermodul: ",true,"(TYP: ",true,cTYP4User,false,")",false).CTrans())
				.set_Nachricht(recHD.get_MELDUNG_FUER_USER_cUF_NN("<info>"))
				.add_idEmpfaenger(bibALL.get_RECORD_USER().get_ID_USER_cUF())
				.set_Sofortanzeige(true)
				.set_DtAnzeigeAb(bibALL.get_cDateNOWInverse("-"))
				.set_Kategorie(MESSAGE_CONST.REMINDER_Kennung.MESSAGE_HANDELSDEF_INFO.toString());
			
			this.saveMessage(oEntry);
			
			
//			this.saveMessage(
//					new MyE2_String("Nachricht vom Mehrwertsteuermodul: ",true,"(TYP: ",true,cTYP4User,false,")",false).CTrans(),
//					recHD.get_MELDUNG_FUER_USER_cUF_NN("<info>"),
//					bibALL.get_RECORD_USER().get_ID_USER_cUF(),
//					true,
//					bibALL.get_cDateNOWInverse("-"),
//					"",
//					null,
//					null,
//					null,
//					null,
//					MESSAGE_CONST.REMINDER_Kennung.MESSAGE_HANDELSDEF_INFO.toString());
		}		
	}

}
