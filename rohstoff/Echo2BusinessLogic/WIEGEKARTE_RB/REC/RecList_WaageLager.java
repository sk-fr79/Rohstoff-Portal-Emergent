/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author manfred
 * @date 02.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS.WAAGE_LAGER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author manfred
 * @date 02.04.2019
 *
 */
public class RecList_WaageLager extends RecList22{

	/**
	 * Reclist für die zugeordneten Waage-Lager
	 * @author manfred
	 * @date 08.05.2020
	 *
	 * @throws myException
	 */
	public RecList_WaageLager() throws myException
	{
		this((String)null);
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date 08.05.2020
	 *
	 * @param bAktiv
	 * @param IDWaageStandort
	 * @throws myException
	 */
	public RecList_WaageLager(String IDAdresseLagerBasis) throws myException {
		this(getSqlExt_Default( IDAdresseLagerBasis	));
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
	public static SqlStringExtended getSqlExt_Default( String IDAdresseLagerBasis) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		SEL sel = new SEL("*")
				.FROM(_TAB.waage_lager)
				.WHERE(new TermSimple(" 1 = 1 "));

		// Standort
		if (S.isFull(IDAdresseLagerBasis)) {
			
			And and_Basis = new And(new TermSimple(WAAGE_LAGER.id_adresse_basis.fn()  + " =  ? ")) ;
			sel.getAnd().add( and_Basis );
			vecParam._a(new Param_Long(Long.parseLong(IDAdresseLagerBasis)));

		}
		
		// order by
		sel.ORDERUP(WAAGE_LAGER.id_adresse_lager);
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
	
	/**
	 * Individuelle SqlStringExtended.
	 * Tabelle muss WAAGE_LAGER sein
	 * @author manfred
	 * @date 08.05.2020
	 *
	 * @param sql
	 * @throws myException
	 */
	public RecList_WaageLager( SqlStringExtended sql ) throws myException{
		super (_TAB.waage_lager);
		this._fill(sql);
	}
	
	
	
}
