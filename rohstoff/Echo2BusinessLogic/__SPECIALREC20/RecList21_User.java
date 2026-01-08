/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author manfred
 * @date 02.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.USER;
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
 * @author manfred
 * @date 02.04.2019
 *
 */
public class RecList21_User extends RecList21{

	
	/**
	 * RecList mit aktiven Usern
	 * @author manfred
	 * @date 02.04.2019
	 *
	 * @throws myException
	 */
	public RecList21_User() throws myException
	{
		this(true,false);
	}
	
	
	/**
	 * Reclist mit aktiven, inaktiven oder allen Usern. 
	 * (false,false) ist nicht erlaubt -> Exception
	 * @author manfred
	 * @date 02.04.2019
	 *
	 */
	public RecList21_User(boolean bAktiv, boolean bInaktiv) throws myException {
		this(getSqlExt_Default(bAktiv, bInaktiv));
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
	public static SqlStringExtended getSqlExt_Default(boolean bAktiv, boolean bInaktiv) throws myException{
		if (bAktiv == false && bInaktiv == false){
			throw new myException("Es müssen entweder aktive, inaktive oder beides gewählt sein.");
		}
		
		SEL s = new SEL("*").FROM(_TAB.user).WHERE(new TermSimple(USER.id_mandant.fn() + " = ? ")).ORDERUP(USER.name1).ORDERUP(USER.vorname);
		
		And and_aktiv = new And( new TermLMR (new Nvl(new TermSimple(USER.aktiv.fn()),"'N'") ,_TermCONST.COMP.EQ.s()," ? " ));
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
	public RecList21_User( SqlStringExtended sql ) throws myException{
		super (_TAB.user);
		this._fill(sql);
	}
	
	
	
}
