/**
 * 
 */
package panter.gmbh.indep.dataTools;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class SQLFieldMAPPrep extends SQLFieldMAP {

	
	
	/**
	 * @param cMain_Table
	 * @param oses
	 */
	public SQLFieldMAPPrep(String cMain_Table, HttpSession oses) {
		super(cMain_Table, oses);
	}


	/**
	 * @param cMain_Table
	 */
	public SQLFieldMAPPrep(String cMain_Table) {
		super(cMain_Table);
	}


	//20180309: 3 neue methoden, um prepared statements zu benutzern in den listenquerys
	/**
	 * 20180309: preparedStatements
	 * baut die metadefs aller felder auf (ueber prepared-statement)
	 */
	@Override
	public void initFields() throws myException {
		
		/*
		 * wenn der main-table keinen pk hat, dann fehler
		 */
		if (this.oSQLFieldPKMainTable==null) {
			throw new myException("SQLFieldMAP:initFields:Please add a primarykey-field to MAINTABLE"+this.cMAIN_TABLE);
		}
		/*
		 * jetzt pruefen, ob alle tabellen einen primary-key im vector haben
		 */
		for (int i=0;i<this.vTableNames.size();i++) 	{
			if (this.hmPrimaryKeys.get(this.vTableNames.get(i))==null) {
				throw new myException("SQLFieldMAP:initFields:Please add a primarykey-field to "+(String)this.vTableNames.get(i));
			}
		}
		
		
		/*
		 * jetzt plausiblitaetspruefung: bei n beteiligten tabellen muss es n-1 connectionfelder geben
		 */
		if ((this.vInnerConnectors.size()+1) != this.vTableNames.size()) {
			throw new myException("SQLFieldMAP:initFields:Number of SQLConnectorInnerTables musst be Number of Tables -1");
		}
		
		
		/*
		 * leere abfrage fuer meta-daten
		 */
		String cSQLQuery = "SELECT "+this.get_cSQL_SELECT_BLOCK(true)+" FROM "+this.get_cSQL_FROM_BLOCK();

		Vector<String> vWhere = new Vector<String>();
		if (!bibALL.isEmpty(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS())) {
			vWhere.add(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS());
		}
		
		// vWhere.add("FALSE");
		vWhere.add(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName()+"=?");
		
		cSQLQuery += " WHERE "+ bibALL.Concatenate(vWhere," AND ", null);
		
		HashMap<String,MyMetaFieldDEF> oHashMapWithFieldMetaDefs = new HashMap<String,MyMetaFieldDEF>();
		SqlStringExtended sqlExt = new SqlStringExtended(cSQLQuery);
		sqlExt.getValuesList().add(new Param_Long(-1));
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		
		MyDBResultSet oRS = sqlExt.generateResultset(oDB);
		
//		MyDBResultSet oRS = oDB.OpenResultSet(cSQLQuery);
		
		DEBUG.System_println("INIT-Query der SQLFieldMAP: "+cSQLQuery, DEBUG.DEBUG_FLAGS.SQLFIELDMAP_BASE_QUERY.name());
		
		if (oRS.RS != null) {
			try	{
				for (int i=0;i<oRS.RS.getMetaData().getColumnCount();i++){
					oHashMapWithFieldMetaDefs.put(oRS.RS.getMetaData().getColumnLabel(i+1),new MyMetaFieldDEF(oRS.RS,i,this.cMAIN_TABLE));
				}
			} catch (SQLException ex){
				oRS.Close();
				bibALL.destroy_myDBToolBox(oDB);
				throw new myException("SQLFieldMAP:initFields:SQL-Exception:"+ex.getMessage()+" -->  SQL-Query:"+cSQLQuery);
			}
			oRS.Close();
		} else {
			throw new myException("SQLFieldMAP:initFields:Error opening empty resultSet!   -->  SQL-Query:"+cSQLQuery);
		}
		
		/*
		 * jetzt die meta-informationen an die SQLFields verteilen
		 */
		Vector<SQLField> vQueryFields = this.get_vFieldsForQuery();
		
		for (int i=0;i<vQueryFields.size();i++) {
			SQLField oField = (SQLField)vQueryFields.get(i);
			MyMetaFieldDEF oFieldDef = (MyMetaFieldDEF)oHashMapWithFieldMetaDefs.get(oField.get_cFieldLabel());
			if (oFieldDef == null) {
				throw new myException("SQLFieldMAP:initFields:Error MetaInformation for Field "+oField.get_cFieldLabel()+" not found !!!");
			}
			
			oField.set_oFieldMetaDef(oFieldDef);
		}
		
		/*
		 * falls noch keine order-definition vorhanden ist, dann nach primaerschluessel sortieren 
		 */
		if (this.vOrderFields.size()==0) {
			this.vOrderFields.add(this.oSQLFieldPKMainTable.get_cTableName()+"."+this.oSQLFieldPKMainTable.get_cFieldName());
		}
	}

	
	
	/*
	 * 20180309: preparedStatements
	 * aufbau einer Query, die einen partiellen ID-Vector abfragt
	 */
	public SqlStringExtended get_CompleteSQLQueryFor_SEGMENT_Prep(Vector<String> vSegmentWithUnformatedIDs) throws myException	{

		Vector<ParamDataObject> 	valuesList = new Vector<ParamDataObject>();
		
		String cSQL_Query = "SELECT "+this.get_cSQL_SELECT_BLOCK(false)+" FROM "+this.get_cSQL_FROM_BLOCK();
		Vector<String> vSammleWHERE = new Vector<String>();
		
		if (!bibALL.isEmpty(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS())) {
			vSammleWHERE.add(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS());
		}

		String cOrder = this.get_actualOrderBlock();

		String or_block = "(";
		Vector<String> vIdsBlock = new Vector<>();
		if (vSegmentWithUnformatedIDs.size()==0)    {
			vIdsBlock.add(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName()+"=?");
			valuesList.add(new Param_Long(-1));
		} else {
			for (String s: vSegmentWithUnformatedIDs) {
				vIdsBlock.add(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName()+"=?");
				valuesList.add(new Param_Long(new Long(s)));
			}
		}
		or_block=or_block+bibALL.Concatenate(vIdsBlock, " OR ", "")+")";

		if (or_block.equals("()")) {
			throw new myException(this,"Empty id-block !!!");
		}
		vSammleWHERE.add(or_block);
		
		cSQL_Query += " WHERE "+bibALL.Concatenate(vSammleWHERE," AND ", null);
		
		if (!cOrder.equals("")) {
			cSQL_Query += " ORDER BY "+cOrder;
		}
		
		//bibALL.System_println(cSQL_Query);
		DEBUG.System_println("get_CompleteSQLQueryFor_SEGMENT:"+cSQL_Query,DEBUG_FLAGS.SQLFIELDMAP_BASE_QUERY.name());

		SqlStringExtended sqlExt = new SqlStringExtended(cSQL_Query);
		sqlExt.getValuesList().addAll(valuesList);
		
		DEBUG._print("Values list : Länge: "+sqlExt.getValuesList().size());
		
		return sqlExt;
	}

	
	
	
	/**
	 * 20180309: preparedStatements
	 * @param cBedingungVonOben
	 * @return query-string for querying list of all id's of this tableset
	 * @throws myException
	 */
	public SqlStringExtended get_CompleteSQLQueryFor_ID_VECTOR_Prep(String cBedingungVonOben,  boolean bWithDynamicWhereBlock) throws myException {
		String cSQL = "";
		String cWhere = bibALL.null2leer(this.get_cWhereBlockComplete(cBedingungVonOben,bWithDynamicWhereBlock)).trim();;
		String cOrder = "";
		
		if (this.vOrderFields.size()>0) {
			cOrder = bibALL.Concatenate(this.vOrderFields,",", null).trim();
		}
		
		cSQL = "SELECT "+this.oSQLFieldPKMainTable.get_cTableName()+"."+this.oSQLFieldPKMainTable.get_cFieldName()+" FROM "+this.get_cSQL_FROM_BLOCK();

		if (!bibALL.isEmpty(cWhere)) {
			cSQL=cSQL+" WHERE "+cWhere;
		}
			
		if (!bibALL.isEmpty(cOrder)) {
			cSQL=cSQL+" ORDER BY "+cOrder;
		}
		
		DEBUG.System_println("get_CompleteSQLQueryFor_ID_VECTOR:"+cSQL,DEBUG.DEBUG_FLAGS.SQLFIELDMAP_BASE_QUERY.name());
		
		SqlStringExtended sqlExt = new SqlStringExtended(cSQL);
		
		return sqlExt;
	}
    //20180309: 3 neue methoden, um prepared statements zu benutzern in den listenquerys    - END Block

	
	
	
	/*
	 * aufbauen einer ROW-Query
	 */
	public SqlStringExtended get_CompleteSQLQueryFor_ONE_ROW_Prep(String cID_MAIN_TABLE_UNFORMATED) throws myException	{
		String ccID_MAIN_TABLE = cID_MAIN_TABLE_UNFORMATED;
		
		String cSQL_Query = "SELECT "+this.get_cSQL_SELECT_BLOCK(true)+" FROM "+this.get_cSQL_FROM_BLOCK();
		Vector<String> vSammler = new Vector<String>();
		
		if (!bibALL.isEmpty(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS()))
			vSammler.add(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS());
		
		vSammler.add(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName()+"=?");

		SqlStringExtended sqlExt = new SqlStringExtended(cSQL_Query+" WHERE "+bibALL.Concatenate(vSammler," AND ", null));
		sqlExt.getValuesList().add(new Param_Long(ccID_MAIN_TABLE));
		
		return sqlExt;
	}

	
	
}
