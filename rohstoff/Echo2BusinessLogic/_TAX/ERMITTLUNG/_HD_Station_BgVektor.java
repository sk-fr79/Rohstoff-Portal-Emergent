/**
 * rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG
 * @author martin
 * @date 18.08.2020
 * 
 */
package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;

/**
 * @author martin
 * @date 18.08.2020
 *
 */
public class _HD_Station_BgVektor extends HD_Station {

	/**
	 * @author martin
	 * @date 17.08.2020
	 *
	 */
	public _HD_Station_BgVektor() {
		super();
	}
	

	@Override
	public MyE2_MessageVector applyResults(HD_WarenBewegungEinstufung oHD_Fuhreneinstufung, String cID_TAX_UF,String cIntrastat_YN, String cTransit_YN, boolean bEK_true__VK_false) throws myException {
		return bibMSG.getNewMV();
	}

	
	@Override
	public boolean isUpdatingAllowd() throws myException {
		return false;
	}


	
	public _HD_Station_BgVektor _init(	boolean 			isQuelle, 
									Rec21_adresse 		adresse, 
									Rec21_artikel_bez 	artikelBez, 
									BigDecimal 			p_bdAbrechnungsMenge,
									String 				p_cTP_Verantwortung, 
									BigDecimal 			p_bd_Preis,
									Date 				p_leistungsDatum) throws myException {
		this.init(		true, 
						isQuelle, 
						adresse._getMainAdresse().getIdLong().toString(), 
						adresse.getIdLong().toString(), 
						artikelBez.getIdLong().toString(),
						p_bdAbrechnungsMenge, 
						p_cTP_Verantwortung, 
						null, 
						null,
						adresse.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.name2),  
						adresse.getUfs(ADRESSE.ort), 
						artikelBez.__get_ANR1_ANR2_ARTBEZ1(), 
						p_bd_Preis, 
						new MyDate(p_leistungsDatum));
		
		return this;
	}

	
	

}
