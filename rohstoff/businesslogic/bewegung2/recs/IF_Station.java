/**
 * rohstoff.businesslogic.bewegung2.recs
 * @author martin
 * @date 26.11.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.recs;

import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 26.11.2018
 *
 */
public interface IF_Station {

	public default boolean isOwnStation() throws myException{
		if ( this instanceof Rec21) {
			if ( ((Rec21)this).is_newRecordSet()) {
				throw new myException("isOwn() is not possible in empty records !");
			}
			Rec21 s = (Rec21)this;
			if (bibALL.get_ID_ADRESS_MANDANT().equals(s.getUfs(BG_STATION.id_adresse_basis))) {
				return true;
			}
		} else {
			throw new myException("IF_Station: class is no record !");
		}
		return false;
	}
	
	
	public default boolean isNotOwnStation() throws myException{
		return !this.isOwnStation();
	}

}
