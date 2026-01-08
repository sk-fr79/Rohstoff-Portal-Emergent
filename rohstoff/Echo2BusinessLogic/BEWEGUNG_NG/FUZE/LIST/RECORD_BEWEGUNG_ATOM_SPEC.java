package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_BEWEGUNG_ATOM_SPEC extends RECORD_BEWEGUNG_ATOM {

	private RECORD_BEWEGUNG_STATION startStation = null;
	private RECORD_BEWEGUNG_STATION zielStation = null;
	
	private RECORD_ADRESSE 			startAdresse= null;
	private RECORD_ADRESSE 			zielAdresse= null;
	
	private boolean 				bFirstSearchWasDone = 	false;

	
	
	public RECORD_BEWEGUNG_ATOM_SPEC(RECORD_BEWEGUNG_ATOM recordOrig) {
		super(recordOrig);
	}
	
	private void fill() throws myException {
		if (!bFirstSearchWasDone) {
			RECLIST_BEWEGUNG_STATION  rlStations = this.get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_bewegung_atom(null, BEWEGUNG_STATION.mengenvorzeichen.t().s()+" ASC", true);
			this.startStation = rlStations.get(0);
			this.zielStation = rlStations.get(1);
			this.startAdresse = this.startStation.get_UP_RECORD_ADRESSE_id_adresse();
			this.zielAdresse = this.zielStation.get_UP_RECORD_ADRESSE_id_adresse();

			bFirstSearchWasDone=true;
		}
	}

	
	public RECORD_BEWEGUNG_STATION  get__startStation() throws myException {
		this.fill();
		return this.startStation;
	}
	
	public RECORD_BEWEGUNG_STATION  get__zielStation() throws myException {
		this.fill();
		return this.zielStation;
	}
	
	
	public boolean is__physical_start() throws myException {
		this.fill();
		return (S.isEmpty(this.startStation.get_UP_RECORD_ADRESSE_id_adresse().get_SONDERLAGER_cUF_NN("")));
	}
	
	
	public boolean is__physical_ziel() throws myException {
		this.fill();
		return (S.isEmpty(this.zielStation.get_UP_RECORD_ADRESSE_id_adresse().get_SONDERLAGER_cUF_NN("")));
	}


	public RECORD_ADRESSE get_startAdresse() throws myException {
		this.fill();
		return startAdresse;
	}


	public RECORD_ADRESSE get_zielAdresse() throws myException {
		this.fill();
		return zielAdresse;
	}

	
	public int get_anzahl_rechnungsPos() throws myException {
		this.fill();
		int i_r = 0;
		if (( this.startStation.get_ID_ADRESSE_BESITZER_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""))) && 
			(!this.zielStation.get_ID_ADRESSE_BESITZER_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("")))) {
			i_r=1;
		}
		return i_r;
	}
	
	
	public int get_anzahl_gutschriftsPos() throws myException {
		this.fill();
		int i_g = 0;
		if ((!this.startStation.get_ID_ADRESSE_BESITZER_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""))) && 
			( this.zielStation.get_ID_ADRESSE_BESITZER_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("")))) {
			i_g=1;
		}
		return i_g;
	}

	
	
}
