/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author manfred
 * @date 02.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINENTYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * @author manfred
 * @date 25.05.2020
 *
 */
public class RecList21_OwnLKW extends RecList21{

	
	/**
	 * RecList mit aktiven LKW des Mandanten
	 * @author manfred
	 * @date 25.05.2020
	 *
	 * @throws myException
	 */
	public RecList21_OwnLKW() throws myException
	{
		this(true,false);
	}
	
	
	/**
	 * Reclist mit aktiven, inaktiven oder allen LKW des Mandanten. 
	 * (false,false) ist nicht erlaubt -> Exception
	 * @author manfred
	 * @date 25.05.2020
	 *
	 */
	public RecList21_OwnLKW(boolean bAktiv, boolean bInaktiv) throws myException {
		this(getSqlExt_Default(bAktiv, bInaktiv));
	}


	/**
	 * Erzeugt eine default-SqlStringExtended für den Konstruktor der User-List für den aktuellen Mandanten.
	 * (Wird auch implizit angewendet mit dem default-Konstruktor, dabei sind wird aktiv=true, inaktiv=false gesetzt.)
	 * @author manfred
	 * @date 25.05.2020
	 *
	 * @param bAktiv
	 * @param bInaktiv
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Default(boolean bAktiv, boolean bInaktiv) throws myException{
		if (bAktiv == false && bInaktiv == false){
			throw new myException("Es müssen entweder aktive, inaktive oder beides gewählt sein.");
		}
		
		// LKW - Kennzeichen oder allgemeine Kennzeichnung
//				String sqlLKWs = " SELECT UPPER( REPLACE(M.KFZKENNZEICHEN,'-',' ') ) "
//						+ " FROM  "
//						+ bibE2.cTO()
//						+ ".JT_MASCHINENTYP  T INNER JOIN "
//						+ bibE2.cTO()
//						+ ".JT_MASCHINEN M  ON   ( T.ID_MASCHINENTYP = M.ID_MASCHINENTYP    ) "
//						+ " WHERE     NVL(T.IST_LKW ,'N') = 'Y' AND NVL(M.AKTIV,'N') = 'Y' ORDER BY 1  "
//						+ " ";
		
		SEL s = new SEL(_TAB.maschinen).FROM(_TAB.maschinen)
				.INNERJOIN(_TAB.maschinentyp, MASCHINEN.id_maschinentyp, MASCHINENTYP.id_maschinentyp)
				.WHERE(new TermSimple(MASCHINEN.id_mandant.tnfn() + " = ? "))
				.AND (new TermLMR (new Nvl(new TermSimple(MASCHINENTYP.ist_lkw.tnfn()),"'N'") ,_TermCONST.COMP.EQ.s()," 'Y' " ))
				.ORDERUP(MASCHINEN.kfzkennzeichen);
		
		And and_aktiv = new And( new TermLMR (new Nvl(new TermSimple(MASCHINEN.aktiv.tnfn()),"'N'") ,_TermCONST.COMP.EQ.s()," ? " ));
		String sAktiv = "";
		
		SqlStringExtended  sql ;
		
		if (bAktiv && bInaktiv){
			sql = new SqlStringExtended(s.s());
			sql._addParameter(new Param_Long("", Long.parseLong( bibALL.get_ID_MANDANT())) ) ;
		} else {
			if (bInaktiv){
				sAktiv = "N";
			} else  {
				sAktiv = "Y";
			}
			
			// selektion erweitern
			s.getAnd().add(and_aktiv);
			sql = new SqlStringExtended(s.s());
			
			// nur aktive oder inaktive: Bedingung anhängen
			sql	._addParameter(new Param_Long("", Long.parseLong( bibALL.get_ID_MANDANT())) )
			._addParameter(new Param_String("aktiv", sAktiv));
		}
		
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
	public RecList21_OwnLKW( SqlStringExtended sql ) throws myException{
		super (_TAB.maschinen);
		this._fill(sql);
	}
	
	
	
}
