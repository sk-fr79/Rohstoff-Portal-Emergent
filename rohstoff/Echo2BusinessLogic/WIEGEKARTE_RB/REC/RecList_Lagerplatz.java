package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS.LAGERPLATZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
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

public class RecList_Lagerplatz extends RecList22 {

	public static final String FIELD_LEVEL = "tiefe";
	public static final String FIELD_PATH = "path";
	public static final String FIELD_IS_LEAF = "is_leaf";
	public static final String FIELD_ROOT = "id_root";
	public static final String FIELD_NODE_ROOT = "node_root";
	
	public static final String WHERE_AND_IST_SCHUETTGUT = " AND IST_SCHUETTGUT = 'Y' ";
	public static final String WHERE_AND_IST_LAGERPLATZ = " AND IST_LAGERPLATZ = 'Y' ";
	
	
	public static final String PATH_DIVIDER = " / ";
	
	private static final String sqlHierachical_INNER_JOIN = "("
			+ "SELECT "
			+ "  CONNECT_BY_ROOT l.ID_LAGERPLATZ AS " + FIELD_ROOT
			+ ", CONNECT_BY_ROOT l.BEZEICHNUNG  as " + FIELD_NODE_ROOT
		
			+ ", l.ID_MANDANT"
			+ ", l.BEZEICHNUNG"
			+ ", l.BESCHREIBUNG"
			+ ", Level as " + FIELD_LEVEL
			+ ", l.ID_LAGERPLATZ"
			+ ", l.ID_LAGERPLATZ_PARENT"
			+ ", l.ID_LAGERPLATZ_TYP"
			+ ", l.IST_SCHUETTGUT"
			+ ", l.IST_LAGERPLATZ "
			+ ", SYS_CONNECT_BY_PATH( l.BEZEICHNUNG, ' "+ PATH_DIVIDER + "') AS " + FIELD_PATH
			+ ", CONNECT_BY_ISLEAF AS " + FIELD_IS_LEAF
			+ " "
			+ " FROM JT_LAGERPLATZ l "
			+ " WHERE 1=1 "
			+ " #AND# "
			+ " "
			+ " CONNECT BY PRIOR l.ID_LAGERPLATZ = l.ID_LAGERPLATZ_PARENT "
			+ " "
			+ " #START_WITH# "
			+ " "
			+ " ) HIER ";
	
	
	

	/**
	 * Default Constructor, keine spezielle selektion
	 * @author manfred
	 * @date 03.12.2020
	 *
	 */
	public RecList_Lagerplatz() {
		super(_TAB.lagerplatz);
	}
	


	
	public RecList_Lagerplatz( SqlStringExtended sql ) throws myException{
		super (_TAB.lagerplatz);
		this._fill(sql);
	}
	
	
	
	/**
	 * 
	 * @param complete_sql extended
	 * @return
	 * @throws myException
	 */
	@Override
	public RecList_Lagerplatz _fill(SqlStringExtended completeSqlExt) throws myException {
		this.execute_query(completeSqlExt);
		return this;
	}
	
	
	
	
	/**
	 * gibt das Basis-SEL-Objekt zurück
	 * @author manfred
	 * @date 15.12.2020
	 *
	 * @param sql_INNER_JOIN
	 * @param vecParam
	 * @return
	 * @throws myException
	 */
	private static SEL getSel(String sql_INNER_JOIN, VEK<ParamDataObject> vecParam) throws myException {
		SEL sel = new SEL(LAGERPLATZ._tab().fullTableName()+".*")
				.ADDFIELD("HIER.ID_LAGERPLATZ","ID_LAGERPLATZ")
				.ADDFIELD("HIER.path",FIELD_PATH)
				.ADDFIELD("HIER.tiefe",FIELD_LEVEL)
				.ADDFIELD("HIER.is_leaf",FIELD_IS_LEAF)
				.ADDFIELD("HIER.id_root",FIELD_ROOT)
				.ADDFIELD("HIER.node_root", FIELD_NODE_ROOT)
				.FROM(_TAB.lagerplatz)
			    .INNERJOIN(sql_INNER_JOIN, "HIER.ID_LAGERPLATZ", LAGERPLATZ.id_lagerplatz)
			    .WHERE(new TermSimple(LAGERPLATZ.id_mandant.tnfn() + " = ? "));
		vecParam._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())));
		
		return sel;
	}

	
	
	
	/**
	 * SqlExt für alle Lagerplätze inclusive der Spalten "path,stufe,is_leaf,id_root, node_root"
	 * @author manfred
	 * @date 02.12.2020
	 *
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Default() throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
	
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ_PARENT is null ") 
				.replaceAll("#AND#","");
				
		SEL sel = getSel(sql_INNER_JOIN,vecParam);
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
	

	
	public static SqlStringExtended getSqlExt_Default(boolean bActive, boolean bInactive) throws myException{
		return getSqlExt_Default(bActive, bInactive, false);
	}
		
	
	
	public static SqlStringExtended getSqlExt_Default(boolean bActive, boolean bInactive, boolean onlyRoot) throws myException{
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ_PARENT is null ") 
				.replaceAll("#AND#","");
				
		
		SEL sel = getSel(sql_INNER_JOIN,vecParam);
		
		addActiveInactive(sel, vecParam, bActive, bInactive); 
		
		if (onlyRoot) {
			sel.getAnd().add( new And(new TermSimple( FIELD_LEVEL +  " = ?")));
			vecParam._a(new Param_Long(1));
		}
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}

	
	
	
	
	/**
	 * Alle Containerplätze
	 * @author manfred
	 * @date 02.12.2020
	 *
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Lagerplatz(boolean bActive, boolean bInactive) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
	
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ_PARENT is null ") 
				.replaceAll("#AND#",WHERE_AND_IST_LAGERPLATZ);
				
		
		SEL sel = getSel(sql_INNER_JOIN,vecParam);

		
//		sel.getAnd().add( new And(new vgl_YN(LAGERPLATZ.ist_lagerplatz,true)));
		
		addActiveInactive(sel, vecParam, bActive, bInactive); 
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}

	
	/**
	 * gibt die Lagerplätze für Container zurück, die unterhalb eines bestimmten Lagerplatzes eingeordnet sind
	 * @author manfred
	 * @date 03.12.2020
	 *
	 * @param id_Lagerplatz_base     - alle Lagerplätze unterhalb diesen Lagerplatzes werden gesucht
	 * @param include_lagerplatz_base - true: auch der Base-Lagerplatz wird angezeigt.
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Lagerplatz_unterhalb(String id_Lagerplatz_base, boolean include_lagerplatz_base,boolean bActive, boolean bInactive) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
	
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ = ? ") 
				.replaceAll("#AND#",WHERE_AND_IST_LAGERPLATZ);
		
		vecParam._a(new Param_Long(Long.parseLong(id_Lagerplatz_base)));
		
		SEL sel = getSel(sql_INNER_JOIN,vecParam);
		

		// IST_LAGERPLATZ
		sel.getAnd().add( new And(new vgl_YN(LAGERPLATZ.ist_lagerplatz,true)));
		
		// TIEFE, d.h. mit oder ohne Wurzelknoten
		sel.getAnd().add(new And(new TermSimple("HIER.tiefe > ? ")));
		vecParam._a(new Param_Long(include_lagerplatz_base ? 0 : 1));
		
		addActiveInactive(sel, vecParam, bActive, bInactive); 

		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}

	
		
	/**
	 * Nur die Root-Knoten vom Lagerplatz
	 * @author manfred
	 * @date 14.04.2021
	 *
	 * @param bActive
	 * @param bInactive
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Lagerplatz_root(boolean bActive, boolean bInactive) throws myException{
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ_PARENT is null ") 
				.replaceAll("#AND#",WHERE_AND_IST_LAGERPLATZ);
				
		SEL sel = new SEL(LAGERPLATZ._tab().fullTableName()+".*").ADD_Distinct()
				.ADDFIELD("HIER.id_root",FIELD_ROOT)
				.ADDFIELD("HIER.node_root", FIELD_PATH)
				.ADDFIELD("0", FIELD_LEVEL)
				.FROM(_TAB.lagerplatz)
			    .INNERJOIN(sql_INNER_JOIN, "HIER.ID_ROOT", LAGERPLATZ.id_lagerplatz)
			    .WHERE(new TermSimple(LAGERPLATZ.id_mandant.tnfn() + " = ? "))
			    .ORDER("HIER.node_root");
		vecParam._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())));
		
		addActiveInactive(sel, vecParam, bActive, bInactive); 
		
		// nur ROOT, d.h. Level = 1
//		sel.getAnd().add( new And(new TermSimple( FIELD_LEVEL +  " = ?")));
//		vecParam._a(new Param_Long(1));
//	
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}


	
	
	
	
	
	/**
	 * Alle Schüttgutplätze
	 * @author manfred
	 * @date 02.12.2020
	 *
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Schuettgut(boolean bActive, boolean bInactive) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
	
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ_PARENT is null ") 
				.replaceAll("#AND#",WHERE_AND_IST_SCHUETTGUT);
				
		
		SEL sel = getSel(sql_INNER_JOIN,vecParam);
		

		// Schüttgut
//		sel.getAnd().add( new And(new vgl_YN(LAGERPLATZ.ist_schuettgut,true)));
		
		addActiveInactive(sel, vecParam, bActive, bInactive); 

		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}

	
	
	/**
	 * gibt die Lagerplätze für Schüttgut zurück, die unterhalb eines bestimmten Lagerplatzes eingeordnet sind
	 * @author manfred
	 * @date 03.12.2020
	 *
	 * @param id_Lagerplatz_base     - alle Lagerplätze unterhalb diesen Lagerplatzes werden gesucht
	 * @param include_lagerplatz_base - true: auch der Base-Lagerplatz wird angezeigt.
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Schuettgut_unterhalb(String id_Lagerplatz_base, boolean include_lagerplatz_base,boolean bActive, boolean bInactive) throws myException{
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
	
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ = ? ") 
				.replaceAll("#AND#",WHERE_AND_IST_SCHUETTGUT);
		
		vecParam._a(new Param_Long(Long.parseLong(id_Lagerplatz_base)));
		
		SEL sel = getSel(sql_INNER_JOIN,vecParam);

		// IST_LAGERPLATZ
		sel.getAnd().add( new And(new vgl_YN(LAGERPLATZ.ist_schuettgut,true)));
		
		// TIEFE, d.h. mit oder ohne Wurzelknoten
		sel.getAnd().add(new And(new TermSimple("HIER.tiefe > ? ")));
		vecParam._a(new Param_Long(include_lagerplatz_base ? 0 : 1));
		
		addActiveInactive(sel, vecParam, bActive, bInactive); 

		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
	

	/**
	 * Nur die Root-Knoten vom Schüttgut
	 * @author manfred
	 * @date 14.04.2021
	 *
	 * @param bActive
	 * @param bInactive
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSqlExt_Schuettgut_root(boolean bActive, boolean bInactive) throws myException{
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ_PARENT is null ") 
				.replaceAll("#AND#",WHERE_AND_IST_SCHUETTGUT);
				
		
		SEL sel = new SEL(LAGERPLATZ._tab().fullTableName()+".*").ADD_Distinct()
				.ADDFIELD("HIER.id_root",FIELD_ROOT)
				.ADDFIELD("HIER.node_root", FIELD_PATH)
				.ADDFIELD("0", FIELD_LEVEL)
				.FROM(_TAB.lagerplatz)
			    .INNERJOIN(sql_INNER_JOIN, "HIER.ID_ROOT", LAGERPLATZ.id_lagerplatz)
			    .WHERE(new TermSimple(LAGERPLATZ.id_mandant.tnfn() + " = ? "))
			    .ORDER("HIER.node_root");
		vecParam._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())));
		
		
		addActiveInactive(sel, vecParam, bActive, bInactive); 
		
		// nur ROOT, d.h. Level = 1
//		sel.getAnd().add( new And(new TermSimple( FIELD_LEVEL +  " = ?")));
//		vecParam._a(new Param_Long(1));
	
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}

	
	
	
	
	/**
	 * Bedingung aktive / inaktive
	 * @author manfred
	 * @date 15.12.2020
	 *
	 * @param sel
	 * @param vecParam
	 * @param bActive
	 * @param bInactive
	 */
	private static void addActiveInactive(SEL sel, VEK<ParamDataObject> vecParam, boolean bActive, boolean bInactive) {

		if (!bActive || !bInactive) {
			String sYN = "Y";
			if (bActive) {
				sYN = "Y";
				sel.getAnd().add( new And(new TermSimple("nvl(" + LAGERPLATZ.aktiv.tnfn() + ",'N') = ?")));
				vecParam._a(new Param_String("", sYN));
			} else if (bInactive) {
				sYN = "N";
				sel.getAnd().add( new And(new TermSimple("nvl(" + LAGERPLATZ.aktiv.tnfn() + ",'N') = ?")));
				vecParam._a(new Param_String("", sYN));
			} else {
				sel.getAnd().add(new And(new TermSimple("1=2")));
			}
		}
	}

	
	
	
	/**
	 * gibt eine Liste mit einem Datensatz zurück, wenn die ID vorhanden ist.
	 * @author manfred
	 * @date 15.12.2020
	 *
	 * @param id_lagerplatz
	 * @return
	 * @throws myException
	 */
	public static SqlStringExtended getSql_Lagerplatz_by_ID(String id_lagerplatz) throws myException  {
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		Long idLagerplatz = Long.parseLong(id_lagerplatz);
		
		// Hierarchical-Abfrage braucht man um den Pfad des Lagerplatzes zu ermitteln
		String sql_INNER_JOIN = sqlHierachical_INNER_JOIN
				.replaceAll("#START_WITH#", "START WITH l.ID_LAGERPLATZ_PARENT is null ") 
				.replaceAll("#AND#","");
		
		SEL sel = getSel(sql_INNER_JOIN,vecParam);
		
		
		// IST_LAGERPLATZ
		sel.getAnd().add( new And(new TermSimple(LAGERPLATZ.id_lagerplatz.tnfn() + " = ?") ));
		vecParam._a(new Param_Long(idLagerplatz));
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		return sql;
	}
	
	
	
	/**
	 * Lagerplatz mit Default == Y
	 * @author manfred
	 * @date 11.03.2021
	 *
	 * @return
	 * @throws myException 
	 */
	public static SqlStringExtended getSql_Lagerplatz_default() throws myException {
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		SEL sel = new SEL(LAGERPLATZ._tab().fullTableName()+".*")
				.FROM(_TAB.lagerplatz)
			    .WHERE(new TermSimple(LAGERPLATZ.id_mandant.tnfn() + " = ? ")  )
			    .AND( new TermSimple(LAGERPLATZ.is_default.tnfn() + " = ? " ));
		
		vecParam._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())))
				._a(new Param_String("","Y"));
		
		SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		
		return sql;
	}
	
}
