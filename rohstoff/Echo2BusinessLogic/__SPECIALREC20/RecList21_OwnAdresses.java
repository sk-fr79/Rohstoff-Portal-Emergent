/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 08.03.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 08.03.2019
 *
 */
public class RecList21_OwnAdresses extends RecList21 {

	/**
	 * creates RecList21 with first Adress = mainadress of mandant, then all lager-adresses
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @throws myException
	 */
	public RecList21_OwnAdresses() throws myException {
		super(_TAB.adresse);
		
		Rec21 ra = new Rec21(_TAB.adresse)._fill_id(bibALL.get_ID_ADRESS_MANDANT());
		
		this.put(ra.get_key_value(), ra);
		
		RecList21 rl2 = new RecList21(_TAB.adresse);
		
		SEL s_innen = new SEL(LIEFERADRESSE.id_adresse_liefer).FROM(_TAB.lieferadresse).WHERE(new vglParam(LIEFERADRESSE.id_adresse_basis));
		
		SEL s = new SEL("*").FROM(_TAB.adresse).WHERE(new TermSimple(ADRESSE.id_adresse.fn()+" IN ("+s_innen.s()+")"));
		
		SqlStringExtended  sql = new SqlStringExtended(s.s())._addParameters(new VEK<ParamDataObject>()._a(new Param_Long("",Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()))));
		
		rl2._fill(sql);
		
		this.putAll(rl2);
	}

}
