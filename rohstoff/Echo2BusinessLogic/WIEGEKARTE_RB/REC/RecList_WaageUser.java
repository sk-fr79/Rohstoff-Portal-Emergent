/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author manfred
 * @date 02.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author manfred
 * @date 02.04.2019
 *
 */
public class RecList_WaageUser extends RecList22{

	
	/**
	 * RecList mit aktiven Waage Usern
	 * @author manfred
	 * @date 23.04.2020
	 *
	 * @throws myException
	 */
	public RecList_WaageUser() throws myException
	{
		this(true,null);
	}
	
	
	/**
	 * Reclist mit aktiven, inaktiven oder allen Usern. 
	 * (false,false) ist nicht erlaubt -> Exception
	 * @author manfred
	 * @date 02.04.2019
	 *
	 */
	public RecList_WaageUser(boolean bAktiv, String IDWaageStandort) throws myException {
		this(getSqlExt_Default(bAktiv, IDWaageStandort));
	}


	
	/**
	 * Erzeugt eine default-SqlStringExtended für den Konstruktor der User-List für den aktuellen Mandanten.
	 * (Wird auch implizit angewendet mit dem default-Konstruktor, dabei sind wird aktiv=true, inaktiv=false gesetzt.)
	 * @author manfred
	 * @date 02.04.2019
	 *
	 * @param bAktiv
	 * @param bInaktiv
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Default(boolean bAktiv , String IDWaageStandort) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		SEL sel = new SEL("*")
				.FROM(_TAB.waage_user)
				.INNERJOIN(_TAB.user, WAAGE_USER.id_user, USER.id_user)
				.WHERE(new TermSimple(WAAGE_USER.id_mandant.fn() + " = ? "));

		vecParam._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())));
		
		// aktiv-Schalter
		And and_aktiv = new And( new TermLMR (new Nvl(new TermSimple(WAAGE_USER.aktiv.fn()),"'Y'") ,_TermCONST.COMP.EQ.s()," ? " ));
		String sAktiv = bAktiv ? "Y" : "N";

		sel.getAnd().add(and_aktiv);
		vecParam._a(new Param_String("",sAktiv));

		
		
		// Standort
		if (S.isFull(IDWaageStandort)) {
			And and_Standort = new And(new TermSimple(WAAGE_USER.id_waage_standort .fn()  + " =  ? ")) ;
			sel.getAnd().add( and_Standort);
			
			vecParam._a(new Param_Long(Long.parseLong(IDWaageStandort)));
		}
		
		// order by
		sel.ORDERUP(WAAGE_USER.sortierung).ORDERUP(WAAGE_USER.id_user);
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
	
	
	/**
	 * Reclist mit individueller SqlStringExtended
	 * NB: Tabelle muss USER sein
	 * 
	 * Bsp:
	 * SEL s = new SEL("*").FROM(_TAB.user).WHERE(new TermSimple(USER.id_mandant.fn() + " = ? "));
	 * SqlStringExtended  sql = new SqlStringExtended(s.s());
	 * sql._addParameter(new Param_Long("", Long.parseLong( bibALL.get_ID_ADRESS_MANDANT())) ) ;
	 *
	 * 
	 * @author manfred
	 * @date 02.04.2019
	 *
	 * @param sql
	 * @throws myException
	 */
	public RecList_WaageUser( SqlStringExtended sql ) throws myException{
		super (_TAB.user);
		this._fill(sql);
	}
	
	
	
}
