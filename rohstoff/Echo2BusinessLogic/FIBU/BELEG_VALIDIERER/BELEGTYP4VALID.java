package rohstoff.Echo2BusinessLogic.FIBU.BELEG_VALIDIERER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.DB_ENUMS.FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_FORMULAR;
import panter.gmbh.indep.exceptions.myException;

public enum BELEGTYP4VALID {
	
	RECH_POSITIV("Rechnung mit positivem Endbetrag")
	,RECH_NEGATIV("Rechnung mit negativem Endbetrag")
	,RECH_POSITIV_STORNO("Rechnung mit positivem Endbetrag, enthält Stornoposition")
	,RECH_NEGATIV_STORNO("Rechnung mit negativem Endbetrag, enthält Stornoposition")
	,GUT_POSITIV("Gutschrift mit positivem Endbetrag")
	,GUT_NEGATIV("Gutschrift mit negativem Endbetrag")
	,GUT_POSITIV_STORNO("Gutschrift mit positivem Endbetrag, enthält Stornoposition")
	,GUT_NEGATIV_STORNO("Gutschrift mit negativem Endbetrag, enthält Stornoposition")
	,ZAHLUNGSEINGANG("Zahlungseingang")
	,ZAHLUNGSAUSGANG("Zahlungsausgang")
	,SCHECK("Zahlungsausgang via Scheck")
	;

	private String beschreibung = null;
	
	private BELEGTYP4VALID(String p_beschreibung) {
		this.beschreibung =p_beschreibung;
	}

	public String get_beschreibung() {
		return beschreibung;
	}
	
	
	 public static MyE2_MessageVector  valid_4_Beleg(RECORD_FIBU_FORMULAR recForm, RECORD_FIBU recFibu) throws myException {
		 MyE2_MessageVector  mv = new MyE2_MessageVector();
		 
		 RECORD_FIBU_val rf 			=  new RECORD_FIBU_val(recFibu);
		 RECORD_FIBU_FORMULAR_val	form = new RECORD_FIBU_FORMULAR_val(recForm);

		 if (!form.is_allowd(rf.belegTyp())) {
			 mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Unerlaubter Beleg enthalten: ",true,rf.belegTyp().beschreibung,true,":",true,"ID-Fibu:",true,""+recFibu.ufs(FIBU.id_fibu),false)));
		 }
		 
		 return mv;
		 
	 }


	
	
	
	
	
	
	
}
