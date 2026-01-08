package rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.exceptions.myException;

/**
 * pruefer fuer adresse, die eine UST-ID brauchen, ob sie in einem LAND
 * eine korrekte UST-ID (abhaengig vom RC-Status der sorte) haben
 * 
 * @author martin
 *
 */
public class Check_HatAdresseKorrekteUSTID {

	//wenn alles ok, dann bleibt die Fehlermeldung leer
	
	//private MyE2_String 		cErrorMessage = null;
	private MyE2_Message  		o_message = null;
	private boolean 			bOnlyWarning = 		false;

	

	/**
	 * 
	 * @param recAdresseJUR (JURISTISCHE ADRESSE)
	 * @param recLAND_GEO
	 * @param recArtikel
	 * @param p_onlyWarning
	 * @throws myException
	 */
	public Check_HatAdresseKorrekteUSTID(RECORD_ADRESSE recAdresseJUR, RECORD_LAND recLAND_GEO, RECORD_ARTIKEL recArtikel, boolean p_onlyWarning) throws myException {
		super();
		
		this.bOnlyWarning = p_onlyWarning;
		
		// ist die sorte im jeweiligen land rc ?
		boolean bSorteIstRC = new Check_RC_Status_Sorte(recLAND_GEO.get_ID_LAND_cUF(), recArtikel.get_ID_ARTIKEL_cUF()).is_RC();
		
		//ist die Adresse in HOMELAND, dann muss muss die vorpruefung alles validieren, sollte hier nicht vorkommen
		if (S.isEmpty(recAdresseJUR.get_ID_LAND_cUF_NN(""))) {
			throw new myException(this,"Adress without ID_LAND is not valid !!");
		}
		
		RECORD_FIRMENINFO  recFI = recAdresseJUR.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
		
		if (recFI.is_PRIVAT_YES()) {
			throw new myException(this,"private Adresses not allowed !!");
		}
		
		String cID_LAND_HOMELAND = 	bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("");
		
		// nur bei adressen im ausland oder auslaendischen lieferadressen oder lieferadressen ausländischer firmen gueltig, 
		// ist beides in HOMELAND, dann gelten evtl. ausnahmen
		if ( (!recAdresseJUR.get_ID_LAND_cUF().equals(cID_LAND_HOMELAND)) || (!recLAND_GEO.get_ID_LAND_cUF().equals(cID_LAND_HOMELAND)) ) {
			if (recLAND_GEO.is_INTRASTAT_JN_YES()) {
				
				// dann muss die firma im land_geo ansaessig sein und eine basis-ust-id haben oder eine zusatz-ust-id mit kennung des  land_geo haben
				// beide adressen sind im ausland und im gleichen ausland
				if (recAdresseJUR.get_ID_LAND_lValue(-1l).longValue()==recLAND_GEO.get_ID_LAND_lValue(-2l).longValue()) {
					//hier muss es eine EU-auslandsadresse sein weil nur faelle untersucht werden, die entweder basis oder lager im ausland haben
					if (S.isEmpty(recFI.get_UMSATZSTEUERLKZ_cF_NN("")) || S.isEmpty(recFI.get_UMSATZSTEUERID_cF_NN(""))) {
						this.o_message = this.createMessage(new MyE2_String("Die Firma ",true,
								recAdresseJUR.get_NAME1_cUF_NN("")+" ("+recAdresseJUR.get_ID_ADRESSE_cF_NN("")+")",false,
								" besitzt keine Basis-UST-ID!",true));
					}
				} else {
					//dann muss es eine passende ust-id in der zusatztabelle geben

					RECLIST_ADRESSE_UST_ID rlUSTID = 
						recAdresseJUR.get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse(_DB.Z_ADRESSE_UST_ID$ID_LAND+"="+recLAND_GEO.get_ID_LAND_cUF(),null,true);
					
					//es darf nur eines geben oder keins
					if (rlUSTID.get_vKeyValues().size()>1) {
						this.o_message =this.createMessage(new MyE2_String("Es existieren zur Adresse ",true,
															""+recAdresseJUR.get_NAME1_cUF_NN("")+" ("+recAdresseJUR.get_ID_ADRESSE_cF_NN("")+")",false,
															" mehrfache Zusatz-UST-IDs im gleichen Land. Bitte korrigieren Sie das!",true));
					} else if (rlUSTID.get_vKeyValues().size()==1){
						if (!bSorteIstRC && rlUSTID.get(0).is_BEGRENZT_AUF_RC_SORTEN_YES()) {
							this.o_message =this.createMessage(new MyE2_String("Es existiert zur Adresse ",true,
									""+recAdresseJUR.get_NAME1_cUF_NN("")+" ("+recAdresseJUR.get_ID_ADRESSE_cF_NN("")+")",false,
									" eine für das Land <",true,recLAND_GEO.get_LAENDERNAME_cF_NN(recLAND_GEO.get_LAENDERCODE_cUF()),false,"> erfaßte Zusatz-UST-IDs. Diese ist aber nur für RC-Sorten gültig !",true));
						}
					} else {
						this.o_message =this.createMessage(new MyE2_String("Es existiert zur Adresse ",true,
								""+recAdresseJUR.get_NAME1_cUF_NN("")+" ("+recAdresseJUR.get_ID_ADRESSE_cF_NN("")+")",false,
								" keine für das Land <",true,recLAND_GEO.get_LAENDERNAME_cF_NN(recLAND_GEO.get_LAENDERCODE_cUF()),false,"> erfaßte Zusatz-UST-ID !",true));
					}
					
					if (this.o_message!=null) {
						//die fehlermeldung der zusatz-ust-id kann mit dem schalter ERZWINGE_FEHLER_WARENBEWEGUNG_BELEGDRUCK_BEI_FALSCHER_USTID  = false auf warung heruntergestuft werden
						boolean ERZWINGE_FEHLER_WARENBEWEGUNG_BELEGDRUCK_BEI_FALSCHER_USTID = 
								bib_Settigs_Mandant.get_StoredValue(ENUM_MANDANT_ZUSATZ_FELDNAMEN.ERZWINGE_FEHLER_WARENBEWEGUNG_BELEGDRUCK_BEI_FALSCHER_USTID);
						
						if (!ERZWINGE_FEHLER_WARENBEWEGUNG_BELEGDRUCK_BEI_FALSCHER_USTID) {
							this.o_message.set_type_WARNING();
						}
					}
				}
			}
		}
		
	}


	private MyE2_Message createMessage(MyE2_String cString) {
		return new MyE2_Message(cString,this.bOnlyWarning?MyE2_Message.TYP_WARNING:MyE2_Message.TYP_ALARM, false);

	}

	
	public MyE2_Message get_cErrorMessage() {
		return o_message;
	}
	
}
