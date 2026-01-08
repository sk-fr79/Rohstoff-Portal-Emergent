/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class PdServiceFindAdresseNearby {
		
	/**
	 * 
	 * @param adresse
	 * @param adressTyp
	 * @param iPrescision
	 * @return
	 * @throws myException
	 */
	public VEK<Rec21> getAdressesNearby(Rec21 adresse, int adressTyp, int iPrescision) throws myException {
		VEK<Rec21> v = new VEK<>();
		
		if (O.isNoOneNull(adresse.getValue(ADRESSE.longitude),adresse.getValue(ADRESSE.latitude))) {
			if (adresse.getValue(ADRESSE.longitude) instanceof BigDecimal || adresse.getValue(ADRESSE.latitude) instanceof BigDecimal) {
				return this.getAdressesNearby((BigDecimal)adresse.getValue(ADRESSE.longitude), (BigDecimal)adresse.getValue(ADRESSE.latitude), adressTyp, iPrescision);
			} else {
				throw new myException(this,"false Datatypes ");
			}
		}
		
		return v;
	}
	

	/**
	 * 
	 * @param dbLongitude
	 * @param dbLatitude
	 * @param adressTyp
	 * @param iPrescision gps-koordinaten vergleichsgenauigkeit (nachkommastellen) 3 = ca 100m
	 * @return
	 * @throws myException
	 */
	public VEK<Rec21> getAdressesNearby(BigDecimal dbLongitude,BigDecimal dbLatitude, int adressTyp, int iPrescision) throws myException {
		VEK<Rec21> v = new VEK<>();
		
		// bei n nachkommastellen muss die untersuchte koordinate an der stelle n abgeschnitten werden und die suche muss an der stelle mit +1 und -1 eingegrenzt werden
		// bsp: 2 nachkommastellen, koordinate ist 9,67886892:  daraus wird die suchbedingung: koordinate >= 9,66 und <= 9,68
		
		BigDecimal diff = new BigDecimal(1).divide(new BigDecimal(Math.pow(10, iPrescision)), iPrescision+2, BigDecimal.ROUND_HALF_UP);
		
		if (O.isNoOneNull(dbLongitude,dbLatitude)) {
			BigDecimal oberGrenzeLongitude = dbLongitude.add(diff);
			BigDecimal unterGrenzeLongitude = dbLongitude.subtract(diff);
			
			BigDecimal oberGrenzeLatitude = dbLatitude.add(diff);
			BigDecimal unterGrenzeLatitude = dbLatitude.subtract(diff);
			
			SEL sel = new SEL(_TAB.adresse).FROM(_TAB.adresse).WHERE(new vgl(ADRESSE.longitude,COMP.GE,MyNumberFormater.formatDez(unterGrenzeLongitude, iPrescision, false, ',', null,false)))
																.AND(new vgl(ADRESSE.longitude,COMP.LE,MyNumberFormater.formatDez(oberGrenzeLongitude, iPrescision, false, ',', null,false)))
																.AND(new vgl(ADRESSE.latitude,COMP.GE,MyNumberFormater.formatDez(unterGrenzeLatitude, iPrescision, false, ',', null,false)))
																.AND(new vgl(ADRESSE.latitude,COMP.LE,MyNumberFormater.formatDez(oberGrenzeLatitude, iPrescision, false, ',', null,false)))
																.AND(new vgl(ADRESSE.adresstyp,""+adressTyp));
			
			RecList21 rl = new RecList21(_TAB.adresse)._fill(sel.s());
			
			DEBUG._print(sel.s());
			
			for (Rec21 r: rl) {
				v._a(r);
			}
		} else {
			throw new myException(this, "null-values (gps) are not allowed !");
		}
		
		return v;
	}

}
