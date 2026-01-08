package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_SONDER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FU__PRUEFUNG_FREMDWARE_AUF_FREMDLAGER
{
	private MyE2_MessageVector  oMV_Rueck = new MyE2_MessageVector();


	public MyE2_MessageVector get_oMV_Rueck()
	{
		return oMV_Rueck;
	}




	/**
	 * 
	 * @param lID_ADRESSE_HAUPT
	 * @param lID_ADRESSE_LAGER
	 * @param bIstFremdwarenFuhre
	 * @param cFehlermeldungBasis
	 * @param bLadestation
	 * @throws myException
	 */
	public FU__PRUEFUNG_FREMDWARE_AUF_FREMDLAGER(		long lID_ADRESSE_HAUPT, 
														long lID_ADRESSE_LAGER, 
														boolean bIstFremdwarenFuhre, 
														String cFehlermeldungBasis, 
														boolean bLadestation,
														long lID_Sonderstatus) throws myException
	{
		super();
		
		if (bib_Settigs_Mandant.IS__Value("FREMDWARE_ZU_FREMDWARENLAGER_INAKTIV", "N", "N")) {
			return;
		}
		
		//den sonderstatus checken (ist fremdwarencheck ausgeschlossen, z.b. leergut)
		if (lID_Sonderstatus>0) {
			RECORD_VPOS_TPA_FUHRE_SONDER  recSonder = new RECORD_VPOS_TPA_FUHRE_SONDER(lID_Sonderstatus);
			if (recSonder.is_KEIN_FREMDWAREN_LAGER_CHECK_YES()) {
				return;
			}
		}

		
		
		boolean bNurWarnung = bib_Settigs_Mandant.IS__Value("FREMDWARE_ZU_FREMDWARENLAGER_WENN_AKTIV_NUR_WARNUNG", "N", "N");

		if (lID_ADRESSE_HAUPT>0 && lID_ADRESSE_LAGER>0) {   			      //nur pruefen, wenn beide lager gefuellt sind
			//falls es sich um die mandantenadressen oder fremde adressen handelt, dann kommt 0 raus
			boolean bStationIstMandantenLager = 		(""+lID_ADRESSE_HAUPT).equals(bibALL.get_ID_ADRESS_MANDANT());
			boolean bStationIstHauptAdresse = 			(lID_ADRESSE_HAUPT==lID_ADRESSE_LAGER);
			
			if (bStationIstMandantenLager) {    		//nur sinnvoll, wenn mindestens eine mandantenstation im spiel ist
				if (bStationIstHauptAdresse) {
					if (bIstFremdwarenFuhre) {
						this.oMV_Rueck.add_MESSAGE(
								this.build_Message(
										new MyE2_String(cFehlermeldungBasis+" - "+
												(bLadestation?"Fremdware kann nicht ab dem Hauptlager gefahren werden !":
													"Fremdware kann nicht zum Hauptlager gefahren werden !")),bNurWarnung));
					}
				} else {    //bei allen NICHT-HauptAdressen muss differenziert werden
					String[][] cLagerArray = this.get_LagerArray(""+lID_ADRESSE_LAGER) ;
					if (cLagerArray==null || cLagerArray.length!=1) {         //darf nicht vorkommen
						oMV_Rueck.add_MESSAGE(this.build_Message(new MyE2_String("Unbekannter Fehler bei der Fremdwaren-Lieferprufung (2)!"),bNurWarnung));
					} else {
						
						boolean bIstFremdwarenLager = !(S.NN(cLagerArray[0][2]).trim().equals("0"));
						
						if (!bIstFremdwarenFuhre && bIstFremdwarenLager) {
							RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(lID_ADRESSE_LAGER);
							String cName = recAdresse.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$ORT));
							oMV_Rueck.add_MESSAGE(this.build_Message(
									new MyE2_String(cFehlermeldungBasis+" - "+"Eigenware kann nicht VON oder ZU dem Lager mit Fremdwarenstatus: ",true,cName,false," gefahren werden !",true),bNurWarnung));
						} else if (bIstFremdwarenFuhre && !bIstFremdwarenLager) {
							RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(lID_ADRESSE_LAGER);
							String cName = recAdresse.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$ORT));
							oMV_Rueck.add_MESSAGE(this.build_Message(
									new MyE2_String(cFehlermeldungBasis+" - "+"Fremdware kann nicht VON oder ZU dem Lager mit Eigenwarenstatus: ",true,cName,false," gefahren werden !",true),bNurWarnung));
						}
					}
				}
			}
		}
	}
	
	
	
	
	private String[][] get_LagerArray(String cID_ADRESSE_LAGER) {
		return bibDB.EinzelAbfrageInArray(
				"SELECT NVL(LA.ID_ADRESSE_BASIS,0),NVL(LA.ID_ADRESSE_LIEFER,0),NVL(LA.ID_ADRESSE_FREMDWARE,0) FROM "+
						 bibE2.cTO()+".JT_LIEFERADRESSE LA WHERE LA.ID_ADRESSE_LIEFER="+cID_ADRESSE_LAGER);
	}

	private MyE2_Message build_Message(MyE2_String Message, boolean bNurWarnung) {
		return new MyE2_Message(Message, (bNurWarnung?MyE2_Message.TYP_WARNING:MyE2_Message.TYP_ALARM), false);
	}

	
}
