package rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.exceptions.myException;

public class __FU_Validator_FuhreErlaubt extends XX_ActionValidator {

	private String 	id_VPOS_TPA_FUHRE_UF = 		null;
	
	/**
	 * als global validator
	 * @param idVPOS_TPA_FUHRE_UF
	 * @throws myException
	 */
	public __FU_Validator_FuhreErlaubt(String idVPOS_TPA_FUHRE_UF) throws myException {
		super();
		this.id_VPOS_TPA_FUHRE_UF = idVPOS_TPA_FUHRE_UF;
	}


	/**
	 * als id_validator
	 */
	public __FU_Validator_FuhreErlaubt() {
		super();
	}

	
	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)  throws myException {
		return this.make_valid(this.id_VPOS_TPA_FUHRE_UF);
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
		return this.make_valid(cID_Unformated);
	}

	
	
	public MyE2_MessageVector make_valid(String cID_Unformated) throws myException {
		
		//entscheiden, ob die meldungen als fehler oder als warnung generiert werden
		boolean bSteuerViaHandelsdef =bib_Settigs_Mandant.get_StoredValue(ENUM_MANDANT_ZUSATZ_FELDNAMEN.STEUERERMITTLUNG_MIT_HANDELSDEF);
		boolean bErwingeFehlerMeldungen =bib_Settigs_Mandant.get_StoredValue(ENUM_MANDANT_ZUSATZ_FELDNAMEN.ERZWINGE_FEHLER_WARENBEWEGUNG_BELEGDRUCK_BEI_FALSCHER_USTID);

		boolean bMeldungenWerdenZuFehlern = bSteuerViaHandelsdef||bErwingeFehlerMeldungen;
		
		Vector<Check__ObHandelErlaubt_Definiere_RC_Status_Sorte> vPruefPositionen = new Vector<Check__ObHandelErlaubt_Definiere_RC_Status_Sorte>();
		
		RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_Unformated);
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		
		if (!  ( recFuhre.is_OHNE_ABRECHNUNG_YES() || 
			      (		S.isFull(recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("")) && 
					    (!recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("-1").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-2"))) 
				  )
				)  
					    
			 ) {
			
			//zuerst die Hauptfuhre, zwei plaetze
			
			vPruefPositionen.add(new Check__ObHandelErlaubt_Definiere_RC_Status_Sorte(recFuhre, true, !bMeldungenWerdenZuFehlern));
			vPruefPositionen.add(new Check__ObHandelErlaubt_Definiere_RC_Status_Sorte(recFuhre, false, !bMeldungenWerdenZuFehlern));
			
			RECLIST_VPOS_TPA_FUHRE_ORT  rlOrte = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
					"nvl("+_DB.VPOS_TPA_FUHRE_ORT$DELETED+",'N')='N'", "", true);
			
			for (RECORD_VPOS_TPA_FUHRE_ORT  oFO: rlOrte.values()) {
				vPruefPositionen.add(new Check__ObHandelErlaubt_Definiere_RC_Status_Sorte(oFO, !bMeldungenWerdenZuFehlern));
			}
		}
		
		for (Check__ObHandelErlaubt_Definiere_RC_Status_Sorte oCheck: vPruefPositionen) {
			oMV.add_MESSAGE(oCheck.get_oMV());
		}
		
		
		return oMV;

	}
	
}
