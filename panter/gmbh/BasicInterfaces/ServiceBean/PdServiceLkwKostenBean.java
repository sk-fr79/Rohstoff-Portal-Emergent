/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBean;

import java.math.BigDecimal;

import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceLkwKosten;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.MASCHINENSTAMM.ENUM_KOSTENBASIS;
import rohstoff.Echo2BusinessLogic.MASCHINENSTAMM.ENUM_KOSTENTYP;

/**
 * @author martin
 *
 */
public class PdServiceLkwKostenBean implements PdServiceLkwKosten {

	@Override
	public BigDecimal getKostenProKm(String kfzKennzeichenOrId) throws myException {
		if (S.isFull(kfzKennzeichenOrId.trim())) {
			String kennzeichenOrId = kfzKennzeichenOrId.trim().toUpperCase();
			
			SEL selFindId = null;
			
			if (S.onlyContainsDigits(kennzeichenOrId.trim())) {
				selFindId = new SEL(MASCHINEN.id_maschinen).FROM(_TAB.maschinen).WHERE(new vgl(MASCHINEN.id_maschinen,kennzeichenOrId));
			} else {
				kennzeichenOrId =S.replaceUmlaute(kennzeichenOrId.trim()).toUpperCase();
				kennzeichenOrId = S.replaceSonderZeichen(kennzeichenOrId, S.grossbuchstaben+S.ziffern, '-');
				kennzeichenOrId = kennzeichenOrId.replace("--", "-");     //alled doppelten ausmerzen
				kennzeichenOrId = kennzeichenOrId.replace("--", "-");
				kennzeichenOrId = kennzeichenOrId.replace("--", "-");
				selFindId = new SEL(MASCHINEN.id_maschinen).FROM(_TAB.maschinen).WHERE(new vgl(new TermSimple("replace(replace(replace(trim(kfzkennzeichen),' ','-'),'--','-'),'--','-')"),new TermSimple( S.hk(kennzeichenOrId))));
			}
		
			MyLong id = new MyLong(bibDB.EinzelAbfrage(selFindId.s()));
			
			if (id.isOK()) {
				Rec20 r = new Rec20(_TAB.maschinen)._fill_id(id.get_lValue());
				
				RecList20 kosten = r.get_down_reclist20(MASCHINEN_KOSTEN.id_maschinen,null,null);
				return kosten.get_sum_bigDecimal( (k)-> { 	if (k.getRawVal(MASCHINEN_KOSTEN.kosten_betrag) !=null && k.getRawVal(MASCHINEN_KOSTEN.kosten_betrag) instanceof BigDecimal) {
																if (ENUM_KOSTENTYP.FAHRTKOSTEN_PAUSCHAL.getEnum(k.getUfs(MASCHINEN_KOSTEN.ms_enum_kostentyp)).getBasis()==ENUM_KOSTENBASIS.KM) {
																	return (BigDecimal)k.getRawVal(MASCHINEN_KOSTEN.kosten_betrag, new BigDecimal(0));
																}else {
																	return new BigDecimal(0);
																}
														 	} else {
														 		return new BigDecimal(0);
														 	}
														});
			}
		}
		return null;
	}

}
