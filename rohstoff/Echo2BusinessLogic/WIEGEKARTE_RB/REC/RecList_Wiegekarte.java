/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author manfred
 * @date 02.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author manfred
 * @date 02.04.2019
 *
 */
public class RecList_Wiegekarte extends RecList22{

	/**
	 * 
	 * @author manfred
	 * @date 08.05.2020
	 *
	 * @param bAktiv
	 * @param IDWaageStandort
	 * @throws myException
	 */
	public RecList_Wiegekarte() throws myException {
		this(getSqlExt_Default( ));
	}


	/**
	 * 
	 * @author manfred
	 * @date 31.07.2020
	 *
	 * @param sql
	 * @throws myException
	 */
	public RecList_Wiegekarte( SqlStringExtended sql ) throws myException{
		super (_TAB.wiegekarte);
		this._fill(sql);
	}
	
	

	/**
	 * Default-Wiegekarte-Liste alle Wiegekarten...
	 * @author manfred
	 * @date 31.07.2020
	 *
	 * @param IDAdresseLagerBasis
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Default( ) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		SEL sel = new SEL("*")
				.FROM(_TAB.wiegekarte);
		
		// order by
		sel.ORDERUP(WIEGEKARTE.id_wiegekarte);
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
	

	/**
	 * Default-Wiegekarte-Liste für wiegekarten die mit einer Fuhre verknüpft sind...
	 * 
	 * @author manfred
	 * @date 31.07.2020
	 *
	 * @param idWiegekarteCurrent  die Wiegekarte der die Fuhre zugeordnet werden soll, hat ja die Fuhre schon eingetragen
	 * @param idFuhre
	 * @param idFuhreOrt
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_WK_Fuhre(String idWiegekarteCurrent, String idFuhre, String idFuhreOrt ) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		SEL sel = new SEL("*")
				.FROM(_TAB.wiegekarte)
				.WHERE(new TermSimple(" 1 = 1 "));

//		" nvl(ID_VPOS_TPA_FUHRE,'0') || '-' || nvl(ID_VPOS_TPA_FUHRE_ORT,'')= '" + m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE() + "-" + m_BuchungWiegekarte.getID_VPOS_TPA_FUHRE_ORT() + "' " +
//		" AND nvl(STORNO,'N') = 'N' " +  sWhereID , "");

				// ID Wiegekarte
				if (S.isFull(idWiegekarteCurrent)) {
					And and_Basis = new And(new TermSimple(WIEGEKARTE.id_wiegekarte.fn()  + " !=  ? ")) ;
					sel.getAnd().add( and_Basis );
					vecParam._a(new Param_Long(Long.parseLong(idWiegekarteCurrent)));
				}

				String idFuhreKomplett = S.Concatenate("-", "0", idFuhre,idFuhreOrt);
				

				// FUHRE
				And and_Basis = new And(new TermSimple("nvl(" +  WIEGEKARTE.id_vpos_tpa_fuhre.fn() + ",0) || '-' || nvl(" + WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn() + ",0)  =  ? ")) ;
				sel.getAnd().add( and_Basis );
				sel.getAnd().add( new And(new vgl_YN(WIEGEKARTE.storno,false)));

				
				vecParam._a(new Param_String("",idFuhreKomplett));
				
		
		// order by
		sel.ORDERUP(WIEGEKARTE.id_wiegekarte);
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
		
	}
	
		

	/**
	 * Liste zum prüfen, ob eine Mehrfachverwiegung nötig ist.
	 * 
	 * @author manfred
	 * @date 31.07.2020
	 *
	 * @param idWiegekarteCurrent  null, wenn neue WK, sonst die aktuelle ID
	 * 							
	 * @param Kennzeichen
	 * @param idArtikelBez
	 * @param strDateIso
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_WK_Mehrfachverwiegung(String idWiegekarteCurrent, 
																	String Kennzeichen, 
																	String idArtikelBez, 
																	String sDate ) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();

		if ( S.isEmpty(Kennzeichen) ||S.isEmpty(idArtikelBez)) {
			throw new myException("Fehler bei der Erzeugung der Mehrfachverwiegung-Abfrage.");
		}
		
		SEL sel = new SEL("*")
				.FROM(_TAB.wiegekarte)
				.WHERE(new TermSimple(" 1 = 1 "));

		if (!S.isEmpty(idWiegekarteCurrent)) {
			// ID Wiegekarte
			And and_Basis = new And(new TermSimple(WIEGEKARTE.id_wiegekarte.fn()  + " !=  ? ")) ;
			sel.getAnd().add( and_Basis );
			vecParam._a(new Param_Long(Long.parseLong(idWiegekarteCurrent)));
		}
		
		// KENNZEICHEN
		And and_Kennz = new And(new TermSimple(WIEGEKARTE.kennzeichen.fn()  + " =  ? ")) ;
		sel.getAnd().add( and_Kennz );
		vecParam._a(new Param_String("",Kennzeichen));
		
		// IDArtikelBez
		And and_ArtikelBez = new And(new TermSimple(WIEGEKARTE.id_artikel_bez.fn()  + " =  ? ")) ;
		sel.getAnd().add( and_ArtikelBez);
		vecParam._a(new Param_Long(Long.parseLong(idArtikelBez)));

		// IDArtikelBez
		String sDateParam = "TRUNC(" + (S.isEmpty(sDate) ? "SYSDATE" : "" + sDate + "") + ",'DD')";
		
		And and_Datum = new And(new TermSimple(" TRUNC(ERZEUGT_AM,'DD') = " + sDateParam)) ;
		sel.getAnd().add( and_Datum);
//		vecParam._a(new Param_String("",sDateParam));
				
		// STORNO-WK ausblenden
		sel.getAnd().add( new And(new vgl_YN(WIEGEKARTE.storno,false)));
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
		
	}
	
	
	
}
