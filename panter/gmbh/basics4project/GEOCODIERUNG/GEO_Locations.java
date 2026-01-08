/**
 * 
 */
package panter.gmbh.basics4project.GEOCODIERUNG;

import java.math.BigDecimal;

import org.apache.commons.codec.StringEncoderComparator;
import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.Soundex;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoDifferenceBetweenTwoPointsBean;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class GEO_Locations extends VEK<GEO_Location> {
	
	 String _id_adresse_uf;
	 String _hnr;
	 String _strasse;
	 String _plz;
	 String _ort;
	 String _iso_laender_code;
	 int	_weight_good = 70;
	 boolean _bUseRang = false;
	 
	
	public GEO_Locations setSearchDataForWeight( String id_adresse_uf, String hnr,String strasse,String plz,String ort, String iso_laender_code, int weight_good){
		_bUseRang = true;
		_id_adresse_uf= id_adresse_uf;
		_hnr = hnr;
		_strasse = strasse;
		_plz = plz;
		_ort = ort;
		_iso_laender_code = iso_laender_code;
		_weight_good = weight_good;
		return this;
	}

	
	
	//wird hier bei der pruefung neu besetzt, die letzte pruefung jedes url-blocks wird protkolliert 
	private ENUM_GEO_Error  lastMessage = null;// ENUM_GEO_Error.UNDEFINED_ERROR;
	
	
	public GEO_Location getSingleLocation() throws myException {
		return this.getSingleLocation(GEO__LocationConst.MaxNumberFoundNotes, GEO__LocationConst.GeoToleranceDiff);
	}
	
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 15.10.2018
	 * 
	 * ergaenzen um parametriesierten aufruf
	 * 
	 * @param maxAllowedNodes
	 * @param maxAllowedDifference
	 * @return
	 * @throws myException
	 */
	public GEO_Location getSingleLocation(int maxAllowedNodes, BigDecimal maxAllowedDifference) throws myException {
		
		if (maxAllowedNodes<=0 || maxAllowedDifference == null) {
			throw new myException(this,"maxAllowedNodes==0 || maxAllowedDifference == null not allowed !");
		}
		
		if (this.size()==0) {
			this.lastMessage = ENUM_GEO_Error.NO_COORDINATE;
			return null;
		} else {
			if (this.size()>maxAllowedNodes) {
				this.lastMessage = ENUM_GEO_Error.NO_EXACT_LOCALISATION;
				return null;
			} else {

				if (this.size()==1) {
					return this.get(0);
				} else {
					
					BigDecimal diff = new BigDecimal(0);
					
					for (int i=0; i<this.size(); i++) {
						for (int j=0; j<this.size(); j++) {
						
							BigDecimal entfernung =new PdServiceGeoDifferenceBetweenTwoPointsBean().differenceInMeters(	
															  this.get(i).get_latitude().get_bdWert()
															, this.get(i).get_longitude().get_bdWert()
															, this.get(j).get_latitude().get_bdWert()
															, this.get(j).get_longitude().get_bdWert()
															);
								
							if (entfernung.compareTo(diff)>0) {
								diff=entfernung;
							}
						}
					}
					
					if (diff.compareTo(maxAllowedDifference)>0) {
						this.lastMessage = ENUM_GEO_Error.NO_EXACT_LOCALISATION;
						return null;
					} else {
						this.lastMessage = ENUM_GEO_Error.OK;
						return this.get(0);
					}
				}
			}
		}
	}
	
	
	
	
	
	public GEO_Location getSingleLocationByWeight() throws myException {
	
		if (this.size()==0) {
			this.lastMessage = ENUM_GEO_Error.NO_COORDINATE;
			return null;
		} else if (!this._bUseRang){
			// wenn nach rang gesucht werden soll, aber keine Rang-Informationen gegeben sind, dann suche nach Eefaulteinstellungen
			return this.getSingleLocation(GEO__LocationConst.MaxNumberFoundNotes, GEO__LocationConst.GeoToleranceDiff);
		}else { 
			
				int count_weight_good = 0;
				GEO_Location loc_max_weight = null;
			
				Integer weight = 0;
				
				// Gewichtung ermitteln
				calculateWeights();

				for (int i=0; i<this.size(); i++) {
					GEO_Location loc = this.get(i);
					
					if (loc_max_weight != null && loc_max_weight.get_weight() < loc.get_weight()){
						loc_max_weight = loc;
					} else if (loc_max_weight == null) {
						loc_max_weight = loc;
					}
					if (loc.get_weight() >=_weight_good){
						count_weight_good ++;
					}
				}
				// rang eigentlich nur erreicht, wenn ort und strasse mindestens gefunden wurden
				if(loc_max_weight != null && loc_max_weight.get_weight() >= _weight_good && count_weight_good == 1) {  
						this.lastMessage = ENUM_GEO_Error.OK;
						return loc_max_weight;
				} else if (loc_max_weight != null && count_weight_good > 1){
					this.lastMessage = ENUM_GEO_Error.NO_EXACT_LOCALISATION;
					return null;
				} else {
						this.lastMessage = ENUM_GEO_Error.NO_COORDINATE;
						return null;
				}
		}
	}
	
		
	/**
	 * berechnen des Rangs der Location
	 * @author manfred
	 * @date 15.11.2018
	 *
	 */
	public void calculateWeights(){
		// suche nach rang
		// rang erzeugen
		Integer weight = 0;
		
		// soundex - Vergleich, um die Vergleiche zu optimieren
		StringEncoderComparator sCompare = new StringEncoderComparator(new ColognePhonetic());
		
		for (int i=0; i<this.size(); i++) {
			GEO_Location loc = this.get(i);
			
			// Gewichtung ermitteln
			weight = 0;
			if( sCompare.compare(GEO_Utils.normalizeOrt(loc.get_ort()), GEO_Utils.normalizeOrt(_ort) ) == 0) weight += 40;
			if( sCompare.compare(GEO_Utils.normalizeStreet(loc.get_strasse()), GEO_Utils.normalizeStreet(_strasse) ) == 0 ) weight += 30;
			if( sCompare.compare(GEO_Utils.normalizeHausnr(loc.get_hausnummer()), GEO_Utils.normalizeHausnr(_hnr) ) == 0 ) weight += 10;
			if( sCompare.compare(loc.get_plz(), _plz ) == 0 ) weight += 20;
			
			loc.set_weight(weight);
		}
	}
	
	
	public ENUM_GEO_Error getLastError() {
		return lastMessage;
	}
	
	

}


