/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 04.04.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 04.04.2019
 *
 */
public class B2_DefaultFields extends VEK<Pair<RB_K>> {

	public B2_DefaultFields() throws myException {
		super();
		
		this
		._a(new Pair<RB_K>(RecV.key, BG_VEKTOR.en_vektor_quelle.fk()))
		._a(new Pair<RB_K>(RecV.key, BG_VEKTOR.en_vektor_status.fk()))
		._a(new Pair<RB_K>(RecV.key, BG_VEKTOR.transportverantwortung.fk()))
		._a(new Pair<RB_K>(RecA1.key, BG_ATOM.pos_in_mask.fk()))
		._a(new Pair<RB_K>(RecA2.key, BG_ATOM.pos_in_mask.fk()))
		._a(new Pair<RB_K>(RecS1.key, BG_STATION.pos_in_mask.fk()))
		._a(new Pair<RB_K>(RecS2.key, BG_STATION.pos_in_mask.fk()))
		._a(new Pair<RB_K>(RecS3.key, BG_STATION.pos_in_mask.fk()))
		;

	}

}
