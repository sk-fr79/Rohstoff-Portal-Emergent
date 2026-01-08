/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class _SqlStringExtended_AH7Steuerdatei extends SqlStringExtended {

	/**
	 * @param sql
	 */
	public _SqlStringExtended_AH7Steuerdatei(String idAdresseStartGeo,String idAdresseZielGeo, String idAdresseStartJur , String idAdresseZielJur) throws myException {
		super(new SEL(_TAB.ah7_steuerdatei)
				.FROM(_TAB.ah7_steuerdatei)
				.WHERE(new vglParam(AH7_STEUERDATEI.id_adresse_geo_start))
				.AND(new vglParam(AH7_STEUERDATEI.id_adresse_geo_ziel))
				.AND(new vglParam(AH7_STEUERDATEI.id_adresse_jur_start))
				.AND(new vglParam(AH7_STEUERDATEI.id_adresse_jur_ziel)).s());
		
		if (S.isAllFull(idAdresseStartGeo, idAdresseZielGeo, idAdresseStartJur ,idAdresseZielJur )) {
			MyLong lAdresseStartGeo = new MyLong(idAdresseStartGeo);
			MyLong lAdresseZielGeo = new MyLong(idAdresseZielGeo);
			MyLong lAdresseStartJur = new MyLong(idAdresseStartJur);
			MyLong lAdresseZielJur = new MyLong(idAdresseZielJur);
			
			if (lAdresseStartGeo.isOK() && lAdresseZielGeo.isOK() && lAdresseStartJur.isOK() && lAdresseZielJur.isOK()) {
				this._addParameters(	new VEK<ParamDataObject>()
						._a(	new Param_Long(lAdresseStartGeo.get_oLong())
								,new Param_Long(lAdresseZielGeo.get_oLong())
								,new Param_Long(lAdresseStartJur.get_oLong())
								,new Param_Long(lAdresseZielJur.get_oLong()))
						);
			}
		} else {
			throw new myException(this, "Error: Only not empty values are allowed !");
		}
			
		
		
		
	}

}
