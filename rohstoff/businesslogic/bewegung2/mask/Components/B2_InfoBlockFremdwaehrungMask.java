/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 04.03.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.BasicInterfaces.Service.PdServiceCreateFremdWaehrungLabel;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;
import rohstoff.businesslogic.bewegung2.global.B2_InfoBlockFremdwaehrung;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_MaskController;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;

/**
 * @author martin
 * @date 04.03.2020
 *
 */
public class B2_InfoBlockFremdwaehrungMask extends B2_InfoBlockFremdwaehrung {


	/**
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param p_posStation
	 */
	public B2_InfoBlockFremdwaehrungMask(EnPositionStation p_posStation) {
		super(p_posStation);
		
	}

	@Override
	public Triple<Long> getIdBesitzerLeftMidRight() throws Exception {
		B2_MaskController mc = new B2_MaskController(this);
		Triple<Long> t = new Triple<>();
		try {
			t._setVal1((Long)mc.getRawLiveValThrowsEx(RecS1.key, BG_STATION.id_adresse_besitz_ldg));
		} catch (myExceptionDataValueNotFittingToField e) {
			t._setVal1(null);
		}
		try {
			t._setVal2((Long)mc.getRawLiveValThrowsEx(RecS2.key, BG_STATION.id_adresse_besitz_ldg));
		} catch (myExceptionDataValueNotFittingToField e) {
			t._setVal2(null);
		}
		try {
			t._setVal3((Long)mc.getRawLiveValThrowsEx(RecS3.key, BG_STATION.id_adresse_besitz_ldg));
		} catch (myExceptionDataValueNotFittingToField e) {
			t._setVal3(null);
		}
		
		return t;
	}

	@Override
	public Pair<Long> getIdVposKonVposStdLeft() throws Exception {
		B2_MaskController mc = new B2_MaskController(this);
		return  new Pair<Long>()
				._setVal1((Long)mc.getRawLiveVal(RecA1.key, BG_ATOM.id_vpos_kon))
				._setVal2((Long)mc.getRawLiveVal(RecA1.key, BG_ATOM.id_vpos_std))
				;
	}

	@Override
	public Pair<Long> getIdVposKonVposStdRight() throws Exception {
		B2_MaskController mc = new B2_MaskController(this);
		return  new Pair<Long>()
				._setVal1((Long)mc.getRawLiveVal(RecA2.key, BG_ATOM.id_vpos_kon))
				._setVal2((Long)mc.getRawLiveVal(RecA2.key, BG_ATOM.id_vpos_std))
				;
	}


	
	protected PdServiceCreateFremdWaehrungLabel getServiceCreateFremdWaehrungLabel() {
		return new PdServiceCreateFremdWaehrungLabel()._setEmbedded(false)._setOneLine(true);
	}
	

}
