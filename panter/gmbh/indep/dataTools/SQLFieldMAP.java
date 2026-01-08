package panter.gmbh.indep.dataTools;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;



/*
 * alle vorkommenden felder in einem vector
 * die tabellen werden extrahiert, der suchschluessel ist der FeldAlias 
 * 
 * Es wird fuer Updates und inserts nur die Haupttabelle herangezogen, die weiteren Tabellen, die
 * in irgendwelchen Feldern vorkommen, sind nur fuer Querys
 * 
 */
public class SQLFieldMAP extends HashMap<String,SQLField>
{

	//2019-10-30: zusaetzliche logging-felder werden beim einfuegen geblacklistet, damit keine fehler wegen mehrfach-fields vorkommen
	public enum enumBlacklistAutomaticFields {
		SYS_TRIGGER_UUID
		,SYS_TRIGGER_TIMESTAMP
		,SYS_TRIGGER_VERSION   //2020-04-09: optimistic logging in jbql
	}
	
	
	//statische deklarationen fuer join-tables
	public static int INNER = 1;
	public static int LEFT_OUTER  = 2;
	public static int RIGHT_OUTER = 3;
	
	private Vector<joinBlock>  vJoinTables = new Vector<joinBlock>();
	//------------------------------------------
	
	
	protected Vector<String> 			vOrderFields		= 	new Vector<String>();
	private Vector<String> 			vBedingungenStatic 	= 	new Vector<String>();				// bedingungen, die sich in einem modul nicht aendern
	private Vector<String> 			vBedingungenDynamic	= 	new Vector<String>();				// bedingungen, die sich in einem modul aendern (wg bediener)
	private Vector<String>			vFieldAlias			= 	new Vector<String>();     			// alle feldaliase
	protected Vector<String> 			vTableNames			=	new Vector<String>();				// alle tabellennamen der map
	private HashMap<String,String>	hmHelpTableNames 	= 	new HashMap<String,String>();      // sorgt fuer die info, ob ein tabellename doppelt vorkommt
	
	/*
	 * sammelt felder fuer SQLFieldForPrimaryKey-objecte fuer die tabellen,
	 * key ist der Tabellenname
	 */
	protected HashMap<String,SQLField>  hmPrimaryKeys 		=   new HashMap<String,SQLField>();		
	

	private HttpSession 			oSES				= 	null;
	protected String 					cMAIN_TABLE			= null;							// fuehrende tabelle / 
	protected SQLFieldForPrimaryKey	oSQLFieldPKMainTable	= null;		
	
	
	/*
	 * sonder-agenten-klassen, die zusatz-sql-statements in die aktionen 
	 * INSERT / UPDATE / DELETE einfuehren koennen
	 */
//	private SQL_Delete_AGENT oDeleteAgent = null;
	private SQL_Insert_AGENT oInsertAgent = null;
	private SQL_Update_AGENT oUpdateAgent = null;
	
	
	
	
	/*
	 * vector, der die inneren Konnektoren sammelt.
	 * Wichtig! Bei der initialisierung muss die Zahl der inneren
	 * 			Connectoren = Anzahl beteiligte Tabellen - 1 sein
	 */
	protected Vector<SQLConnectorInnerTables> 	vInnerConnectors = new Vector<SQLConnectorInnerTables>();
	

	/*
	 * in masken mit mehreren verknuepften tabellen muss in den tochtertabellen ein
	 * join-Field zu den mothertables erzeugt werden. Dies ist fuer jede tabelle SQLFieldMap nur einmal moeglich
	 */
	private SQLFieldJoinOutside oSQLFieldJoinOutside = null;
	
	
	
	/*
	 * ein schalter, der den dynamischen where-Block invertiert selektiert
	 */
	private boolean  bInvertiereDynamischenWhereblock = false; 
	
	
	/**
	 * @param cMain_Table
	 * @param oses
	 * Konstruktor fuer manuelles anfuegen der felder
	 */
	public SQLFieldMAP(String cMain_Table,HttpSession oses)
	{
		super();
		this.oSES = oses;
		this.cMAIN_TABLE = cMain_Table.toUpperCase();
		this.vTableNames.add(this.cMAIN_TABLE);                   // die haupt-tabelle steht zuerst
		this.hmHelpTableNames.put(this.cMAIN_TABLE,this.cMAIN_TABLE);
		
	}
	
	
	
	

	/**
	 * 2012-11-09: variante
	 * @param cMain_Table
	 */
	public SQLFieldMAP(String cMain_Table)
	{
		super();
		this.oSES = bibE2.get_CurrSession();
		this.cMAIN_TABLE = cMain_Table.toUpperCase();
		this.vTableNames.add(this.cMAIN_TABLE);                   // die haupt-tabelle steht zuerst
		this.hmHelpTableNames.put(this.cMAIN_TABLE,this.cMAIN_TABLE);
		
	}

	
	
	/**
	 * @param cTable
	 * @param cListOfFields
	 * @param bIncludeList !!Felder MUESSEN mit : getrennt werden
	 * @param bWriteable
	 * @param cFieldAliasPrefix 
	 * @throws myException
	 * Haengt eine ganze Tabelle an die SQLFieldMap an, mit der Bedingung:
	 * nur cListOfFields wird eingefuegt (wenn bUseList = true) 
	 * oder cListOfFields wird excluded (bUseList = false)
	 */
	public void addCompleteTable_FIELDLIST(String cTable,String ListOfFields,boolean bIncludeList, boolean Writeable, String cFieldAliasPrefix) throws myException
	{
		String cListOfFields = ListOfFields;
		boolean bWriteable = Writeable;
		
		if (!this.hmHelpTableNames.containsKey(cTable.toUpperCase()))
		{
			this.vTableNames.add(cTable.toUpperCase());                   // die haupt-tabelle steht zuerst
			
			if (this.vTableNames.size()>1 && this.vJoinTables.size()>0)
			{
				throw new myException(this,"When using join-Tables then only one Table in the Tablelist is allowed ");
			}
			
			this.hmHelpTableNames.put(cTable.toUpperCase(),cTable.toUpperCase());
		}
		
		if (!cTable.equals(this.cMAIN_TABLE)) bWriteable = false;          // zusaeztliche tabellen sind NIE schreibbar

		cListOfFields = cListOfFields.trim();
		if (!cListOfFields.endsWith(":")) cListOfFields+=":";
		if (!cListOfFields.startsWith(":")) cListOfFields= ":"+cListOfFields;
		
		try 
		{
			/*
			 * dann baut er eine standard-tabelle auf
			 */
			MyMetaFieldDEF_HashMap oFields = new MyMetaFieldDEF_HashMap(cTable.toUpperCase());
			for (int i=0;i<oFields.get_iNumberOfColumns();i++)
			{
				boolean bFieldIsInList = cListOfFields.toUpperCase().indexOf(":"+oFields.get_FieldName(i).toUpperCase()+":")>=0;
				boolean bUse = bFieldIsInList;
				if (!bIncludeList)
					bUse = !bUse;
				
				if (bUse)
				{
					String cAliasLabel=oFields.get_FieldName(i);

					if (!bibALL.isEmpty(cFieldAliasPrefix))
						cAliasLabel = cFieldAliasPrefix+cAliasLabel;
					
					SQLField oField = new SQLField(	cTable.toUpperCase(),
							oFields.get_FieldName(i),
							cAliasLabel,
							new MyString(oFields.get_FieldName(i),false),
							oFields.get_FieldNullable(i),
							this.oSES);
					oField.set_bWriteable(bWriteable);
					oField.set_bFieldCanBeWrittenInMask(bWriteable);
					this.add_SQLField(oField, false);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new myException(this,":SQLException:"+e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * 
	 * @param dbSchema          //2015-01-27: erweiterung fuer schema
	 * @param joinTableName
	 * @param joinTableAlias
	 * @param joinType
	 * @param fieldList  (Felder,getrennt durch : )
	 * @param FieldsInclude
	 * @param joinBedingung
	 * @param cFieldAliasPrefix
	 * @param hmZusatzfelder      hier koennen beliebige paerchen mit key=Aliasname, value=Queryausdruck stehen, die als lookup-field eingebaut werden
	 * @throws myException
	 * 
	 * join-table-fields werden wie zusammengesetzte felder behandelt, d.h. nur fuer lookup-tabellen,
	 * darf nur benutzt werden, wenn es exact eine vTables - eintragung gibt, sonst beissen sich die beiden
	 * join-syntaxen (a=b (+) und join ....)
	 */
	public void add_JOIN_Table(		String  dbSchema,
									String 	joinTableName, 
									String 	joinTableAlias, 
									int 	joinType, 
									String 	fieldList, 
									boolean FieldsInclude, 
									String 	joinBedingung,
									String  cFieldAliasPrefix, 
									HashMap<String, String> hmZusatzfelder) throws myException
	{
		if (this.vTableNames.size()==1)
		{
		
			if (joinType<=0 || joinType>3)
			{
				throw new myException(this,"For the parameter joinType is only allowed: SQLFieldMAP.INNER, SQLFieldMAP.LEFT_OUTER, SQLFieldMAP.RIGHT_OUTER");
			}
			
			joinBlock oJB = new joinBlock(dbSchema, joinTableName, joinTableAlias, joinType, fieldList, FieldsInclude, joinBedingung);
			this.vJoinTables.add(oJB);
		
			String cListOfFields = oJB.cFieldList;
			
			cListOfFields = cListOfFields.trim();
			if (!cListOfFields.endsWith(":")) cListOfFields+=":";
			if (!cListOfFields.startsWith(":")) cListOfFields= ":"+cListOfFields;
			
			try 
			{
				/*
				 * dann baut er eine standard-tabelle auf
				 */
				MyMetaFieldDEF_HashMap oFields = new MyMetaFieldDEF_HashMap(oJB.cJoinTableName.toUpperCase());
				for (int i=0;i<oFields.get_iNumberOfColumns();i++)
				{
					boolean bFieldIsInList = cListOfFields.toUpperCase().indexOf(":"+oFields.get_FieldName(i).toUpperCase()+":")>=0;
					boolean bUse = bFieldIsInList;
					if (!oJB.bFieldsInclude)
						bUse = !bUse;
					
					if (bUse)
					{
						String cAliasLabel=oFields.get_FieldName(i);

						if (!bibALL.isEmpty(cFieldAliasPrefix))
							cAliasLabel = cFieldAliasPrefix+cAliasLabel;
						
						//dann ein "look-up-field" erzeugen
						SQLField oField = new SQLField(	oJB.cJoinTableAlias+"."+oFields.get_FieldName(i), cAliasLabel,new MyString(oFields.get_FieldName(i)),this.oSES);
						oField.set_bWriteable(false);
						oField.set_bFieldCanBeWrittenInMask(false);
						this.add_SQLField(oField, false);
					}
				}
				
				//jetzt die zusatzfelder verarbeiten
				if (hmZusatzfelder != null)
				{
					for (Map.Entry<String, String> oEntry: hmZusatzfelder.entrySet())
					{
						//dann ein "look-up-field" erzeugen
						SQLField oField = new SQLField(	oEntry.getValue(), oEntry.getKey(),new MyString(oEntry.getKey()),this.oSES);
						oField.set_bWriteable(false);
						oField.set_bFieldCanBeWrittenInMask(false);
						this.add_SQLField(oField, false);
					}
				}
				
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new myException(this,":SQLException:"+e.getLocalizedMessage());
			}

			
			
		}
		else
		{
			throw new myException(this,"Join-Table statement only allowed when just the BASE-Table is in the tablelist !!");
		}
		
	}
	
	
	
	
	/**
	 * 
	 * @param joinEnum          //2017-10-11: strukturierung via enums
	 * @param hmZusatzfelder      hier koennen beliebige paerchen mit key=Aliasname, value=Queryausdruck stehen, die als lookup-field eingebaut werden
	 * @throws myException
	 * 
	 * join-table-fields werden wie zusammengesetzte felder behandelt, d.h. nur fuer lookup-tabellen,
	 * darf nur benutzt werden, wenn es exact eine vTables - eintragung gibt, sonst beissen sich die beiden
	 * join-syntaxen (a=b (+) und join ....)
	 */
	public void add_JOIN_Table(		IF_Enum4JoinDef  joinEnum,
									HashMap<String, String> hmZusatzfelder) throws myException
	{
		if (this.vTableNames.size()==1)	{
		
			joinBlock oJB = new joinBlock(	joinEnum.getSchema(), 
											joinEnum.getJoinField().fullTableName(), 
											joinEnum.getJoinTableAlias(), 
											joinEnum.getJoinTyp().getRefOld(), 
											joinEnum.getFieldListSeparated(":"), 
											true, 
											joinEnum.getLeftTableAlias()+"."+joinEnum.getLeftField().fn()+"="+joinEnum.getJoinTableAlias()+"."+joinEnum.getJoinField().fn());
			this.vJoinTables.add(oJB);
		
			String cListOfFields = oJB.cFieldList;
			
			cListOfFields = cListOfFields.trim();
			if (!cListOfFields.endsWith(":")) cListOfFields+=":";
			if (!cListOfFields.startsWith(":")) cListOfFields= ":"+cListOfFields;
			
			try {

				/*
				 * dann baut er eine standard-tabelle auf
				 */
				MyMetaFieldDEF_HashMap oFields = new MyMetaFieldDEF_HashMap(oJB.cJoinTableName.toUpperCase());
				for (int i=0;i<oFields.get_iNumberOfColumns();i++)	{
					boolean bFieldIsInList = cListOfFields.toUpperCase().indexOf(":"+oFields.get_FieldName(i).toUpperCase()+":")>=0;
					boolean bUse = bFieldIsInList;
					if (!oJB.bFieldsInclude) {
						bUse = !bUse;
					}
					
					if (bUse) {
						String cAliasLabel=oFields.get_FieldName(i);

						if (!S.isEmpty(joinEnum.getJoinTableFieldPrefix())) {
							cAliasLabel = joinEnum.getJoinTableFieldPrefix()+cAliasLabel;
						}
						
						//dann ein "look-up-field" erzeugen
						SQLField oField = new SQLField(	oJB.cJoinTableAlias+"."+oFields.get_FieldName(i), cAliasLabel,new MyString(oFields.get_FieldName(i)),this.oSES);
						oField.set_bWriteable(false);
						oField.set_bFieldCanBeWrittenInMask(false);
						this.add_SQLField(oField, false);
					}
				}
				
				//jetzt die zusatzfelder verarbeiten
				if (hmZusatzfelder != null)
				{
					for (Map.Entry<String, String> oEntry: hmZusatzfelder.entrySet())
					{
						//dann ein "look-up-field" erzeugen
						SQLField oField = new SQLField(	oEntry.getValue(), oEntry.getKey(),new MyString(oEntry.getKey()),this.oSES);
						oField.set_bWriteable(false);
						oField.set_bFieldCanBeWrittenInMask(false);
						this.add_SQLField(oField, false);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new myException(this,":SQLException:"+e.getLocalizedMessage());
			}
		} else {
			throw new myException(this,"Join-Table statement only allowed when just the BASE-Table is in the tablelist !!");
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param joinTableName
	 * @param joinTableAlias
	 * @param joinType
	 * @param fieldList  (Felder,getrennt durch : )
	 * @param FieldsInclude
	 * @param joinBedingung
	 * @param cFieldAliasPrefix
	 * @param hmZusatzfelder      hier koennen beliebige paerchen mit key=Aliasname, value=Queryausdruck stehen, die als lookup-field eingebaut werden
	 * @throws myException
	 * 
	 * join-table-fields werden wie zusammengesetzte felder behandelt, d.h. nur fuer lookup-tabellen,
	 * darf nur benutzt werden, wenn es exact eine vTables - eintragung gibt, sonst beissen sich die beiden
	 * join-syntaxen (a=b (+) und join ....)
	 */
	public void add_JOIN_Table(		String 	joinTableName, 
									String 	joinTableAlias, 
									int 	joinType, 
									String 	fieldList, 
									boolean FieldsInclude, 
									String 	joinBedingung,
									String  cFieldAliasPrefix, 
									HashMap<String, String> hmZusatzfelder) throws myException
	{
		this.add_JOIN_Table(null, joinTableName, joinTableAlias, joinType, fieldList, FieldsInclude, joinBedingung, cFieldAliasPrefix, hmZusatzfelder);
	}
	
	
	
	
	

	public void add_BEDINGUNG_STATIC(String cBedingungSegment)
	{
		this.vBedingungenStatic.add(cBedingungSegment);
	}
	public void add_BEDINGUNG_DYNAMIC(String cBedingungSegment)
	{
		this.vBedingungenDynamic.add(cBedingungSegment);
	}
	
	
	
	public void add_ORDER_SEGMENT(String cOrderSegment)
	{
		this.vOrderFields.add(cOrderSegment);
	}
	
	
	public Vector<String> get_vOrderFields()
	{
		return this.vOrderFields;
	}
	
	
	public void clear_BEDINGUNG_STATIC()
	{
		this.vBedingungenStatic.removeAllElements();
	}
	
	public void clear_BEDINGUNG_DYNAMIC()
	{
		this.vBedingungenDynamic.removeAllElements();
	}
		
	public void clear_ORDER_SEGMENT()
	{
		this.vOrderFields.removeAllElements();
	}

	
	/**
	 * 
	 * @author martin
	 * @date 21.02.2020
	 *
	 * @return
	 */
	public SQLFieldMAP _clearOrders() {
		this.vOrderFields.removeAllElements();
		return this;
	}
	
	public SQLFieldMAP _addOrderUp(IF_Field field) {
		this.vOrderFields.add(field.fn()+" ASC ");
		return this;
	}
	public SQLFieldMAP _addOrderDown(IF_Field field) {
		this.vOrderFields.add(field.fn()+" DESC ");
		return this;
	}
	
	
	
	public String get_actualOrderBlock() throws myException
	{
		String cOrder = "";
		if (this.vOrderFields.size()>0)
			cOrder = bibALL.Concatenate(this.vOrderFields,",", null).trim();

		return cOrder;
	}
	
	//2019-10-30: blacklisted-fields beruechsichtigen
	public void add_SQLField(SQLField oField, boolean bReplaceDouble) throws myException {
		this.add_SQLField(oField,bReplaceDouble,true); 
	}
	
	public void add_SQLField(SQLField oField, boolean bReplaceDouble, boolean considerBlacklist) throws myException {
		
		//2019-10-30: blacklisted-fields beruechsichtigen
		if (considerBlacklist) {
			for (enumBlacklistAutomaticFields blf: enumBlacklistAutomaticFields.values()) {
				if (blf.name().equals(oField.get_cFieldLabel().toUpperCase())) {
					return; 			//dann nix machen
				}
			}
		}
		
		
		if (!bReplaceDouble)
		{
			if (this.get(oField.get_cFieldLabel()) != null)
				throw new myException("SQLFieldMAPup:add_SQLField:Fieldalias "+oField.get_cFieldLabel()+" is used double!");
		}
		else
		{
			if (this.get(oField.get_cFieldLabel()) != null)
				this.vFieldAlias.remove(oField.get_cFieldLabel());
		}
		
		if (bibALL.isEmpty(oField.get_cFieldLabel()))
			throw new myException("SQLFieldMAP:add_SQLField:Every Field MUST have a label!");

		/*
		 * tablename zufuegfen (kann bei zusammengesetzten feldern null sein)
		 */
		if (oField.get_cTableName() != null)
			if (this.hmHelpTableNames.get(oField.get_cTableName().toUpperCase()) == null)
			{
				this.vTableNames.add(oField.get_cTableName().toUpperCase());
				
				if (this.vTableNames.size()>1 && this.vJoinTables.size()>0)
				{
					throw new myException(this,"When using join-Tables then only one Table in the Tablelist is allowed ");
				}

				
				this.hmHelpTableNames.put(oField.get_cTableName().toUpperCase(),oField.get_cTableName().toUpperCase());
			}

		/*
		 * es darf pro tabelle nur einen primary-key geben
		 */
		if (oField instanceof SQLFieldForPrimaryKey)
			if (this.hmPrimaryKeys.get(oField.get_cTableName().toUpperCase()) != null)
				throw new myException("SQLFieldMAP:add_SQLField:Table "+oField.get_cTableName()+" has two primary keys!");
			else
			{
				this.hmPrimaryKeys.put(oField.get_cTableName().toUpperCase(),oField);
				if (this.cMAIN_TABLE.equals(oField.get_cTableName().toUpperCase()))
					this.oSQLFieldPKMainTable = (SQLFieldForPrimaryKey)oField;
			}

		/*
		 * es darf pro SQLFieldMAP nur einen SQLFieldJoinOutside geben
		 */
		if (oField instanceof SQLFieldJoinOutside)
		{
			
			SQLFieldJoinOutside oConnectField = (SQLFieldJoinOutside) oField;
			if ( !oConnectField.get_cTableName().equals(this.cMAIN_TABLE))
				throw new myException("SQLFieldMAP:add_SQLField:SQLFieldJoinOutside: Field MUST Belong to Main-Table");

			if (this.oSQLFieldJoinOutside != null)
				throw new myException("SQLFieldMAP:add_SQLField:SQLFieldJoinOutside: ONLY ONE Connector ALLOWED");
			
			this.oSQLFieldJoinOutside = (SQLFieldJoinOutside)oField;
		}
			
		this.vFieldAlias.add(oField.get_cFieldLabel());
		this.put(oField.get_cFieldLabel(),oField);
		
		oField.set_oFieldMapThisBelongsTo(this);           // damit ist eine eindeutige zuordung zur fieldmap definiert
	}
	
	
	
	public SQLField get_SQLField(String cFieldAlias) throws myException
	{
		SQLField oFieldRueck = (SQLField)this.get(cFieldAlias);
		if (oFieldRueck == null)
			throw new myException("SQLFieldMAP:get_SQLField:Field "+cFieldAlias+" not found !!! (Basetable: "+this.cMAIN_TABLE+")");
		
		return oFieldRueck;
	}
	
	public SQLField get_SQLField(IF_Field field) throws myException	{
		return this.get_SQLField(field.fn());
	}


	/**
	 * 
	 * @param bAlleFelder (wenn false, dann werden SQLFields vom Typ SQLField_OnlyInQueryWhenVisible nur mit in der query beruecksichtigt, wenn sie auch eingeblendet sind
	 * @return
	 * @throws myException
	 */
	protected String get_cSQL_SELECT_BLOCK(boolean bAlleFelder)  throws myException
	{
		Vector<SQLField>  	vFields = this.get_vFieldsForQuery();
		Vector<String>  	vSammler = new Vector<String>();
		
		
		if (bAlleFelder)
		{
			for (int i=0;i<vFields.size();i++)
			{
	    		vSammler.add(((SQLField)vFields.get(i)).getCompleteSelectBlock());
			} 			
		}
		else
		{
			for (int i=0;i<vFields.size();i++)
			{
				//der sqlfieldtyp SQLField_OnlyInQueryWhenVisible wird nur mit abgefragt, wenn seine korrelierende spalte eingeblendet ist 
				if (vFields.get(i) instanceof SQLField_OnlyInQueryWhenVisible)
				{
					SQLField_OnlyInQueryWhenVisible oField = (SQLField_OnlyInQueryWhenVisible)vFields.get(i);
					if (oField.get_oComponentMAP_REF_this_belongs_to().get_vVisibleElementsInList().contains(oField.get_cFieldLabel()))
					{
						vSammler.add(oField.getCompleteSelectBlock());
					}
					else
					{
						vSammler.add(oField.get_DummySelectBlock());
					}
				}
				else
				{
					vSammler.add(((SQLField)vFields.get(i)).getCompleteSelectBlock());
				}
			} 			
		}
		return bibALL.Concatenate(vSammler,",", null);
	}

	
	
	/**
	 * @returns WHEREblock from connectfields
	 * @throws myException
	 */
	public String get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS() throws myException
	{
		String cRueck = "";
		Vector<String> vBedingungen = new Vector<String>();
		for (int i=0;i<this.vInnerConnectors.size();i++)
			vBedingungen.add(((SQLConnectorInnerTables)this.vInnerConnectors.get(i)).get_cConnectorString());
		
		
		if (vBedingungen.size()>0)
			cRueck = bibALL.Concatenate(vBedingungen," AND ", null);
		return cRueck;
		
	}
	
	
	
	/**
	 * @returns WHEREblock from Restrict-Fields
	 * @throws myException
	 */
	public String get_cSQL_WHERE_BLOCK_FROM_RestrictFields() throws myException
	{
		Vector<String> vSammler = new Vector<String>();
		
		for (int i=0;i<this.vFieldAlias.size();i++)
		{
			String cFieldAlias = (String)this.vFieldAlias.get(i);
			
			if (this.get(cFieldAlias) instanceof SQLFieldForRestrictTableRange)
			{
				SQLFieldForRestrictTableRange oSQLField = (SQLFieldForRestrictTableRange)this.get(cFieldAlias);
				//vSammler.add(oSQLField.get_cTableName()+"."+oSQLField.get_cFieldName()+"="+oSQLField.get_cInsertValuePart());
				//2013-06-26: die where-block-erstellung ersetzen durch die neue methode im SQLFieldForRestrictTableRange
				vSammler.add(oSQLField.get_WhereBlockForSQL_Bedingung());
			}

		
		}
		String cRueck = "";

		
		if (vSammler.size()>0)
			cRueck = bibALL.Concatenate(vSammler," AND ", null);
		
		return cRueck;
		
	}

	

	
	

	
	
	
	/**
	 * @returns WHEREblock from Restrict-Fields (only from MAIN-Table restricted fields)
	 * @throws myException
	 */
	public String get_cSQL_WHERE_BLOCK_FROM_RestrictFields_FROM_MAIN_TABLE() throws myException
	{
		Vector<String> vSammler = new Vector<String>();
		
		for (int i=0;i<this.vFieldAlias.size();i++)
		{
			String cFieldAlias = (String)this.vFieldAlias.get(i);
			
			if (this.get(cFieldAlias) instanceof SQLFieldForRestrictTableRange)
			{
				SQLFieldForRestrictTableRange oSQLField = (SQLFieldForRestrictTableRange)this.get(cFieldAlias);
				
				if (oSQLField.get_cTableName().equals(this.get_cMAIN_TABLE())) {
					//vSammler.add(oSQLField.get_cTableName()+"."+oSQLField.get_cFieldName()+"="+oSQLField.get_cInsertValuePart());
					
					//2013-06-26: die where-block-erstellung ersetzen durch die neue methode im SQLFieldForRestrictTableRange
					vSammler.add(oSQLField.get_WhereBlockForSQL_Bedingung());

					
				}
			}

		
		}
		String cRueck = "";

		
		if (vSammler.size()>0)
			cRueck = bibALL.Concatenate(vSammler," AND ", null);
		
		return cRueck;
		
	}


	
	
	
	
	
	public String get_cSQL_WHERE_BLOCK_STATIC() throws myException
	{
		String cRueck = "";
		if (this.vBedingungenStatic.size()>0)
			cRueck = bibALL.Concatenate(this.vBedingungenStatic," AND ", null);

		return cRueck;
	}
	
	
	public String get_cSQL_WHERE_BLOCK_DYNAMIC() throws myException
	{
		String cRueck = "";
		if (this.vBedingungenDynamic.size()>0)
			cRueck = bibALL.Concatenate(this.vBedingungenDynamic," AND ", null);

		return cRueck;
	}
	
	
	
	
	public String get_cSQL_FROM_BLOCK() throws myException
	{
		Vector<String> vSammler = new Vector<String>();
		
		for (int i=0;i<this.vTableNames.size();i++)
		{
			vSammler.add(bibE2.cTO()+"."+this.vTableNames.get(i));
		}
		
		String cRueck = bibALL.Concatenate(vSammler,",", null);
		
		//jetzt noch die join-tables dranhaengen
		for (int i=0;i<this.vJoinTables.size();i++)
		{
			cRueck += " "+this.vJoinTables.get(i).get_JoinBlockForSQLQuery()+" ";
		}
		
		return cRueck; 
	}
	
	

	/**
	 * @return vector with SQLField-objects
	 * @throws myException
	 */
	protected Vector<SQLField> get_vFieldsForQuery() 
	{
		Vector<SQLField> 		vSammler = new Vector<SQLField>();
		for (int i=0;i<this.vFieldAlias.size();i++)
		{
			vSammler.add(this.get(this.vFieldAlias.get(i)));
		} 		
		return vSammler;
	}

	
	

	public Vector<String> get_vTableNames()
	{
		return this.vTableNames;
	}

	
	public Vector<String> get_vFieldLabels()
	{
		return this.vFieldAlias;
	}
	

	
	/**
	 * baut die metadefs aller felder auf
	 */
	public void initFields() throws myException
	{
		
		/*
		 * wenn der main-table keinen pk hat, dann fehler
		 */
		if (this.oSQLFieldPKMainTable==null)
			throw new myException("SQLFieldMAP:initFields:Please add a primarykey-field to MAINTABLE"+this.cMAIN_TABLE);
		
		
		
		/*
		 * jetzt pruefen, ob alle tabellen einen primary-key im vector haben
		 */
		for (int i=0;i<this.vTableNames.size();i++)
		{
			if (this.hmPrimaryKeys.get(this.vTableNames.get(i))==null)
				throw new myException("SQLFieldMAP:initFields:Please add a primarykey-field to "+(String)this.vTableNames.get(i));
		}
		
		
		/*
		 * jetzt plausiblitaetspruefung: bei n beteiligten tabellen muss es n-1 connectionfelder geben
		 */
		if ((this.vInnerConnectors.size()+1) != this.vTableNames.size())
			throw new myException("SQLFieldMAP:initFields:Number of SQLConnectorInnerTables musst be Number of Tables -1");
		
		
		/*
		 * leere abfrage fuer meta-daten
		 */
		String cSQLQuery = "SELECT "+this.get_cSQL_SELECT_BLOCK(true)+" FROM "+this.get_cSQL_FROM_BLOCK();

		Vector<String> vWhere = new Vector<String>();
		if (!bibALL.isEmpty(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS()))
			vWhere.add(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS());
		
		// vWhere.add("FALSE");
		vWhere.add(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName()+" = -1");
		
		cSQLQuery += " WHERE "+ bibALL.Concatenate(vWhere," AND ", null);
		
		HashMap<String,MyMetaFieldDEF> oHashMapWithFieldMetaDefs = new HashMap<String,MyMetaFieldDEF>();
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		MyDBResultSet oRS = oDB.OpenResultSet(cSQLQuery);
		
		DEBUG.System_println("INIT-Query der SQLFieldMAP: "+cSQLQuery, DEBUG.DEBUG_FLAGS.SQLFIELDMAP_BASE_QUERY.name());
		
		if (oRS.RS != null)
		{
			try
			{
				for (int i=0;i<oRS.RS.getMetaData().getColumnCount();i++)
				{
					oHashMapWithFieldMetaDefs.put(oRS.RS.getMetaData().getColumnLabel(i+1),new MyMetaFieldDEF(oRS.RS,i,this.cMAIN_TABLE));
				}
			}
			catch (SQLException ex)
			{
				oRS.Close();
				bibALL.destroy_myDBToolBox(oDB);
				throw new myException("SQLFieldMAP:initFields:SQL-Exception:"+ex.getMessage()+" -->  SQL-Query:"+cSQLQuery);
			}
			oRS.Close();
		}
		else
		{
			throw new myException("SQLFieldMAP:initFields:Error opening empty resultSet!   -->  SQL-Query:"+cSQLQuery);
		}

		
		/*
		 * jetzt die meta-informationen an die SQLFields verteilen
		 */
		Vector<SQLField> vQueryFields = this.get_vFieldsForQuery();
		
		for (int i=0;i<vQueryFields.size();i++)
		{
			SQLField oField = (SQLField)vQueryFields.get(i);
			MyMetaFieldDEF oFieldDef = (MyMetaFieldDEF)oHashMapWithFieldMetaDefs.get(oField.get_cFieldLabel());
			if (oFieldDef == null)
				throw new myException("SQLFieldMAP:initFields:Error MetaInformation for Field "+oField.get_cFieldLabel()+" not found !!!");
			
			oField.set_oFieldMetaDef(oFieldDef);
		}

		
		/*
		 * falls noch keine order-definition vorhanden ist, dann nach primaerschluessel sortieren 
		 */
		if (this.vOrderFields.size()==0)
		{
			this.vOrderFields.add(this.oSQLFieldPKMainTable.get_cTableName()+"."+this.oSQLFieldPKMainTable.get_cFieldName());
		}
		
		
		
	}

	
	/*
	 * aufbauen einer ROW-Query
	 */
	public String get_CompleteSQLQueryFor_ONE_ROW(String cID_MAIN_TABLE_UNFORMATED) throws myException
	{
		String ccID_MAIN_TABLE = cID_MAIN_TABLE_UNFORMATED;
		
		String cSQL_Query = "SELECT "+this.get_cSQL_SELECT_BLOCK(true)+" FROM "+this.get_cSQL_FROM_BLOCK();
		Vector<String> vSammler = new Vector<String>();
		
		if (!bibALL.isEmpty(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS()))
			vSammler.add(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS());
		
		vSammler.add(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName()+" = "+ccID_MAIN_TABLE);

		return cSQL_Query+" WHERE "+bibALL.Concatenate(vSammler," AND ", null);
		
	}
	

	
	/*
	 * aufbau einer Query, die einen partiellen ID-Vector abfragt
	 */
	public String get_CompleteSQLQueryFor_SEGMENT(Vector<String> vSegmentWithUnformatedIDs) throws myException
	{

		String cSQL_Query = "SELECT "+this.get_cSQL_SELECT_BLOCK(false)+" FROM "+this.get_cSQL_FROM_BLOCK();
		Vector<String> vSammleWHERE = new Vector<String>();
		
		if (!bibALL.isEmpty(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS()))
			vSammleWHERE.add(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS());

		String cOrder = this.get_actualOrderBlock();

		// neue Version weg Abfragefehler MaxDB 7.6 in IN (..,..,..) - Statement 
		if (vSegmentWithUnformatedIDs.size()==0)      // dann war auswahl leer (wg selektoren oder aehnlichem
		{
			// vSammleWHERE.add(" FALSE ");
			vSammleWHERE.add(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName()+" =-1");
		}
		else
		{
			//String cWhereBlock = bibALL.Concatenate(vSegmentWithUnformatedIDs,",", null);
			vSammleWHERE.add(this.baue_OR_Kette(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName(),vSegmentWithUnformatedIDs));
		}

		
		cSQL_Query += " WHERE "+bibALL.Concatenate(vSammleWHERE," AND ", null);
		
		if (!cOrder.equals(""))
			cSQL_Query += " ORDER BY "+cOrder;
		
		
		//bibALL.System_println(cSQL_Query);
		DEBUG.System_println("get_CompleteSQLQueryFor_SEGMENT:"+cSQL_Query,DEBUG_FLAGS.SQLFIELDMAP_BASE_QUERY.name());

		
		return cSQL_Query;
		
	}

	
	/**
	 * 2014-08-28: eigener fieldblock fuer das ausfuehren von summationsquerys oder aehnlichem
	 * @param cOWN_FieldBlock
	 * @param vSegmentWithUnformatedIDs
	 * @return
	 * @throws myException
	 */
	public String get_SQLQuery_OwnFieldBlock(String cOWN_FieldBlock, Vector<String> vSegmentWithUnformatedIDs) throws myException
	{

		String cSQL_Query = "SELECT "+cOWN_FieldBlock+" FROM "+this.get_cSQL_FROM_BLOCK();
		Vector<String> vSammleWHERE = new Vector<String>();
		
		if (!bibALL.isEmpty(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS()))
			vSammleWHERE.add(this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS());

		if (vSegmentWithUnformatedIDs.size()==0)      // dann war auswahl leer (wg selektoren oder aehnlichem
		{
			vSammleWHERE.add(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName()+" =-1");
		}
		else
		{
			vSammleWHERE.add(this.baue_OR_Kette(this.get_oSQLFieldPKMainTable().get_cTableName()+"."+this.get_oSQLFieldPKMainTable().get_cFieldName(),vSegmentWithUnformatedIDs));
		}
		cSQL_Query += " WHERE "+bibALL.Concatenate(vSammleWHERE," AND ", null);
		
		return cSQL_Query;
	}
	
	
	
	
	private String baue_OR_Kette(String cFieldName,Vector<String> vIDs)
	{
		String cRueck = cFieldName+"= -1";
		
		if (vIDs.size()>0)
		{
			cRueck = "(";
			for (int i=0;i<vIDs.size()-1;i++)
			{
				cRueck = cRueck + cFieldName+"="+(String)vIDs.get(i)+" OR ";
			}
			// den letzten anhaengen
			cRueck = cRueck + cFieldName+"="+(String)vIDs.get(vIDs.size()-1)+")";
		}
		return cRueck;
	}

	
	
	
	/**
	 * @param cBedingungVonOben
	 * @return query-string for querying list of all id's of this tableset
	 * @throws myException
	 */
	public String get_CompleteSQLQueryFor_ID_VECTOR(String cBedingungVonOben,  boolean bWithDynamicWhereBlock) throws myException
	{
		String cSQL = "";
		String cWhere = bibALL.null2leer(this.get_cWhereBlockComplete(cBedingungVonOben,bWithDynamicWhereBlock)).trim();;
		String cOrder = "";
		
		if (this.vOrderFields.size()>0)
			cOrder = bibALL.Concatenate(this.vOrderFields,",", null).trim();
		
		cSQL = "SELECT "+this.oSQLFieldPKMainTable.get_cTableName()+"."+this.oSQLFieldPKMainTable.get_cFieldName()+" FROM "+this.get_cSQL_FROM_BLOCK();

		if (!bibALL.isEmpty(cWhere))
			cSQL=cSQL+" WHERE "+cWhere;
			
		if (!bibALL.isEmpty(cOrder))
			cSQL=cSQL+" ORDER BY "+cOrder;
		
		
		DEBUG.System_println("get_CompleteSQLQueryFor_ID_VECTOR:"+cSQL,DEBUG.DEBUG_FLAGS.SQLFIELDMAP_BASE_QUERY.name());
		
		return cSQL;
	}
	
	
	
	
	
	protected String get_cWhereBlockComplete(String cZusatzWhere, boolean bWithDynamicWhereBlock) throws myException
	{
		Vector<String> vSammler = new Vector<String>();
		
		if (bWithDynamicWhereBlock)
		{
			String w1 = this.get_cSQL_WHERE_BLOCK_DYNAMIC();
	
			if (this.bInvertiereDynamischenWhereblock)
			{
				w1 = "("+this.oSQLFieldPKMainTable.get_cTableName()+"."+this.oSQLFieldPKMainTable.get_cFieldName()+" NOT IN ("+this.get_Complete_ID_QueryFor_NegativeSelection(cZusatzWhere)+"))";
			}
			if (!w1.trim().equals("")) vSammler.add(w1);
		}
		
		String w2 = this.get_cSQL_WHERE_BLOCK_STATIC();
		String w3 = this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS();
		String w4 = this.get_cSQL_WHERE_BLOCK_FROM_RestrictFields();
		if (!w2.trim().equals("")) vSammler.add(w2);
		if (!w3.trim().equals("")) vSammler.add(w3);
		if (!w4.trim().equals("")) vSammler.add(w4);
		if (!bibALL.isEmpty(cZusatzWhere)) vSammler.add(cZusatzWhere);
		
		String cRueck = "";
		if (vSammler.size()>0)
		{
			cRueck = bibALL.Concatenate(vSammler," AND ", null);
		}
		return cRueck;
		
	}
	

	

	//ID-Selektion fuer negierte abfragen
	private String get_Complete_ID_QueryFor_NegativeSelection(String cBedingungVonOben) throws myException
	{
		String cSQL = "";
		String cWhere = bibALL.null2leer(this.get_cWhereBlockCompleteForNegativeSelection(cBedingungVonOben)).trim();;
		
		cSQL = "SELECT "+this.oSQLFieldPKMainTable.get_cTableName()+"."+this.oSQLFieldPKMainTable.get_cFieldName()+" FROM "+this.get_cSQL_FROM_BLOCK();

		if (!bibALL.isEmpty(cWhere))
			cSQL=cSQL+" WHERE "+cWhere;
		
		return cSQL;
	}

	
	
	private String get_cWhereBlockCompleteForNegativeSelection(String cZusatzWhere) throws myException
	{
		Vector<String> vSammler = new Vector<String>();
		String w1 = this.get_cSQL_WHERE_BLOCK_DYNAMIC();
		String w2 = this.get_cSQL_WHERE_BLOCK_STATIC();
		String w3 = this.get_cSQL_WHERE_BLOCK_FROM_CONNECTIONS();
		String w4 = this.get_cSQL_WHERE_BLOCK_FROM_RestrictFields();
		if (!w1.trim().equals("")) vSammler.add(w1);
		if (!w2.trim().equals("")) vSammler.add(w2);
		if (!w3.trim().equals("")) vSammler.add(w3);
		if (!w4.trim().equals("")) vSammler.add(w4);
		if (!bibALL.isEmpty(cZusatzWhere)) vSammler.add(cZusatzWhere);
		
		String cRueck = "";
		if (vSammler.size()>0)
		{
			cRueck = bibALL.Concatenate(vSammler," AND ", null);
		}
		return cRueck;
		
	}



	

	public HashMap<String,SQLField> get_hmPrimaryKeys()
	{
		return hmPrimaryKeys;
	}



	public SQLFieldForPrimaryKey get_oSQLFieldPKMainTable()
	{
		return oSQLFieldPKMainTable;
	}




	/**
	 * @param oInputMAP
	 * @return
	 * @throws myException
	 * aufbau der insert-statements aller vorhandenen tabellen, die insert-felder haben,
	 * gefuellt. zuerst werden die standard-werte genommen und dann werden die
	 * eingetragenen werte ueberschrieben.
	 * Diese Methode ist fuer neueingaben (auch fuer neue abhaengige SQLFieldMaps,
	 * bei denen die Haupt-MAP auch neu eingegeben wurde

	 */
	public Vector<String> get_SQL_INSERTSTACK(SQLMaskInputMAP oInputMAP) throws myException
	{
		return this.__SQL_INSERTSTACK(oInputMAP,null);
	}
	
	/**
	 * @param oInputMAP
	 * @param Formated_ValueOfConnectedFieldToOutside
	 * @return
	 * @throws myException
	 * Diese variante baut input-Stacks auf innerhalb abhaenginger (Tochter-) Maps,
	 * deren Haupt-Map bereits vorhanden war (z.B. bei zusaetzliche eintraegen in 
	 * Tochtertabellen)
	 * 
	 */
	public Vector<String> get_SQL_INSERTSTACK(SQLMaskInputMAP oInputMAP, String Formated_ValueOfConnectedFieldToOutside) throws myException
	{
		return this.__SQL_INSERTSTACK(oInputMAP,Formated_ValueOfConnectedFieldToOutside);
	}

	
	
	/*
	 * aufbau der insert-statements aller vorhandenen tabellen, die insert-felder haben,
	 * gefuellt. zuerst werden die standard-werte genommen und dann werden die
	 * eingetragenen werte ueberschrieben
	 */
	private Vector<String> __SQL_INSERTSTACK(SQLMaskInputMAP oInputMAP,String Formated_ValueOfConnectedFieldToOutside) throws myException
	{
		Vector<String> vSQL = new Vector<String>();
		
			
		if (!this.oSQLFieldPKMainTable.get_bWriteable())
			throw new myException("SQLFieldMAP: get_SQL_INSERTSTACK: Table cannot be written because primary-key is not writeable !!");
		
		Vector<SQLField> vFields = this.get_WriteableStandardFields(this.cMAIN_TABLE);

		/*
		 * felder fuellen
		 */
		for (int k=0;k<vFields.size();k++)
		{
			SQLField oField =(SQLField)vFields.get(k);
			
			/*
			 * zuerst das feld loeschen, damit nicht von vorigen aktionen bei labels z.b.
			 * werte in dem objekt stehen, die hier nicht ueberschrieben werden, weil die
			 * felder nicht in der input-map auftauchen
			 */
			oField.set_cNewValueFormated(null);
			
			
			/*
			 * zuerst alle felder mit dem standard-wert fuellen, damit der auch bei nicht
			 * in der maske befindlichen feldern vorhanden ist
			 */
			if (oField.get_cDefaultValueFormated() != null)
			{
				oField.set_cNewValueFormated(oField.get_cDefaultValueFormated());
			}
			
			/*
			 * dann die maskenwerte eintragen
			 */
			String cLABEL = ((SQLField)vFields.get(k)).get_cFieldLabel();
			if (oInputMAP.containsKey(cLABEL))
				oField.set_cNewValueFormated((String)oInputMAP.get(cLABEL));
		}

		/*
		 * jetzt wird das pk-feld eingehaengt
		 */
		String cNewPrimaryKeyValue = this.oSQLFieldPKMainTable.cQueryNext_KEY_ValueFormated();
		this.oSQLFieldPKMainTable.set_cNewValueFormated(cNewPrimaryKeyValue);
		vFields.add(this.oSQLFieldPKMainTable);
		
		/*
		 * der neu erzeugte PK wird in die MAP eingehaengt 
		 */
		oInputMAP.put(this.oSQLFieldPKMainTable.get_cFieldLabel(),cNewPrimaryKeyValue);
		
		/*
		 * restictions-felder reinhaengen
		 */
		vFields.addAll(this.get_RestrictRangeFields());
			

		/*
	     * wenn die SQLFieldMAP nicht die leading-Map ist, dann wird der oSQLFieldJoinOutside auch gefuellt,
	     * dies ist noetig, wenn die SQLMap eine "Untertabelle" unter der Maintable ist
	     */
	    if (!this.get_bIsSQLMapLEADINGMAP())
	    {
	    	
	    	if (bibALL.isEmpty(Formated_ValueOfConnectedFieldToOutside))
	    	{
		    	/*
		    	 * hier gibt es wieder zwei moeglichkeiten, entweder der wert der des feldes 
		    	 * der outside-join-tabelle wurde gerade neu ermittelt, d.h. die outside-tabelle ist
		    	 * auch im status NEW
		    	 */
	    		this.oSQLFieldJoinOutside.set_cNewValueFormated(oSQLFieldJoinOutside.get_oFieldFromConnectedTable().get_cNewValueFormated());
	    		/*
	    		 *  der wert des feldconnectors wird in die input-map geschrieben 
	    		 */
	    		oInputMAP.put(this.oSQLFieldJoinOutside.get_cFieldLabel(),oSQLFieldJoinOutside.get_oFieldFromConnectedTable().get_cNewValueFormated());

	    	}
	    	else
	    	{
	    		/*
	    		 * oder die outside-tabelle ist im status edit, dann muss zwingend zum aufbau eines insert-statements
	    		 * der aktuelle wert des feldes  oSQLFieldJoinOutside.get_oFieldFromConnectedTable() uebergebe werden
	    		 */
	    		this.oSQLFieldJoinOutside.set_cNewValueFormated(Formated_ValueOfConnectedFieldToOutside);
	    		/*
	    		 *  der wert des feldconnectors wird in die input-map geschrieben 
	    		 */
	    		oInputMAP.put(this.oSQLFieldJoinOutside.get_cFieldLabel(),Formated_ValueOfConnectedFieldToOutside);
	    		
	    	}
	    	
	    	vFields.add(this.oSQLFieldJoinOutside);

	    }
	    
    	vSQL.add(this.Build_SQL_Insert(this.cMAIN_TABLE, vFields));
			
		if (this.oInsertAgent != null)
			vSQL.addAll(this.oInsertAgent.get_vZusatzStatements(this,oInputMAP, cNewPrimaryKeyValue));
		
		return vSQL;
	}

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param cTablename
	 * @param vWritableFields
	 * @return
	 * @throws myException
	 * baut das insert einer einzelnen tabelle
	 */
	private String Build_SQL_Insert(String cTablename, Vector<SQLField> vWritableFields) throws myException
	{
		String cSQL = "INSERT INTO "+bibALL.get_TABLEOWNER()+"."+cTablename;
		String cSQL_Fields = "";
		String cSQL_Values = "";

		for (int i=0;i<vWritableFields.size();i++)
		{
		    cSQL_Fields += ((SQLField)vWritableFields.get(i)).get_cInsertFieldPart()+",";
		    cSQL_Values += ((SQLField)vWritableFields.get(i)).get_cInsertValuePart()+",";
		}
		
		cSQL_Fields = cSQL_Fields.substring(0,cSQL_Fields.length()-1);
		cSQL_Values = cSQL_Values.substring(0,cSQL_Values.length()-1);
		
		cSQL = cSQL +" ("+cSQL_Fields+") VALUES("+cSQL_Values+")";
		return cSQL;
	}
	
	
	
	
	/**
	 * validierung einer eingabe, komplett alle felder werden geprueft,
	 * bei aenderung werden auch die vorigen query-werte geprueft, bei
	 * neueingabe nur die werte der SQLMaskInputMAP
	 */
	public MyE2_MessageVector get_vCheckNewValues(SQLResultMAP oActualResult, SQLMaskInputMAP oMaskInputValues) throws myException
	{
		MyE2_MessageVector vError = new MyE2_MessageVector();
		
		for (int i=0;i<this.vTableNames.size();i++)
		{
			String cTablename = (String)this.vTableNames.get(i);
			Vector<SQLField> vFields = this.get_WriteableStandardFields(cTablename);

			for (int k=0;k<vFields.size();k++)
			{
				SQLField oField =(SQLField)vFields.get(k);
				
				/*
				 * dann die maskenwerte pruefen (falls vorhanden)
				 */
				String cLABEL = ((SQLField)vFields.get(k)).get_cFieldLabel();
				
//				bibALL.System_println(cLABEL);
				
				if (oMaskInputValues.containsKey(cLABEL))
					vError.add_MESSAGE(oField.get_vCheckNewValue((String)oMaskInputValues.get(cLABEL)));
				else
				{
					if (oActualResult != null)
					{
						vError.add_MESSAGE(oField.get_vCheckNewValue(oActualResult.get_FormatedValue(cLABEL)));
					}
					else
					{
						if (oField.get_cDefaultValueFormated() != null)
							vError.add_MESSAGE(oField.get_vCheckNewValue(oField.get_cDefaultValueFormated()));
					}
				}
			}
		}

		return vError;
	}
	
	

	/*
	 * aufbau der update -statements aller vorhandenen tabellen, die normale felder haben
	 */
	public Vector<String>  get_SQL_UPDATESTACK(SQLResultMAP oActualResult, SQLMaskInputMAP oMaskInputValues) throws myException
	{
		Vector<String> vSQL = new Vector<String>();
		

		if (!this.oSQLFieldPKMainTable.get_bWriteable())
			throw new myException("SQLFieldMAP: get_SQL_UPDATESTACK: Table cannot be written because primary-key is not writeable !!");

	
		Vector<SQLField> vFields = this.get_WriteableStandardFields(this.cMAIN_TABLE);

		if (vFields.size()>0)   // bekommt ein update -statement
		{
			for (int k=0;k<vFields.size();k++)
			{
				SQLField 	oField =(SQLField)vFields.get(k);
				String 		cLABEL = oField.get_cFieldLabel();
				
				/*
				 * dann die maskenwerte eintragen (falls vorhanden),
				 * sonst die alten werte eintragen aus der letzten abfrage
				 */
				if (oMaskInputValues.containsKey(cLABEL))
					oField.set_cNewValueFormated((String)oMaskInputValues.get(cLABEL));
				else
					oField.set_cNewValueFormated(oActualResult.get_FormatedValue(cLABEL));	
			}
		    
		    vSQL.addAll(this.Build_SQL_Update(this.cMAIN_TABLE, vFields, oActualResult.get_cFormatedID_FromTable(this.cMAIN_TABLE)));
		}

		if (this.oUpdateAgent != null)
			vSQL.addAll(this.oUpdateAgent.get_vZusatzStatements(this,oActualResult,oMaskInputValues));

		return vSQL;
	}

	
	
	public Vector<String> get_SQL_DELETESTACK_From_FORMATED_KEY(String cFormated_Primary_KEY_Value) throws myException
	{
		Vector<String> vSQL = new Vector<String>();

		if (!this.oSQLFieldPKMainTable.get_bWriteable())
			throw new myException("SQLFieldMAP: get_SQL_DELETESTACK: Table cannot be written because primary-key is not writeable !!");


		String cDelKeyUNFormated = this.oSQLFieldPKMainTable.get_cValueString_For_DB(cFormated_Primary_KEY_Value);

	    /*
	     * beim loeschen muessen immer erst die abhaengigen tabellen geloescht werden,
	     * deshalb wird hier zuerst alles andere, dann die haupttabelle geloescht
	     */
	    
		String cDEL = "DELETE FROM "+bibALL.get_TABLEOWNER()+"."+this.cMAIN_TABLE +" WHERE "+this.oSQLFieldPKMainTable.get_cFieldName()+"="+cDelKeyUNFormated;
		vSQL.add(cDEL);

		return vSQL;
		
	}
	

	
	public Vector<String> get_SQL_DELETESTACK_From_UNFORMATED_KEY(String cUNFormated_Primary_KEY_Value) throws myException
	{
		Vector<String> vSQL = new Vector<String>();

		if (!this.oSQLFieldPKMainTable.get_bWriteable())
			throw new myException("SQLFieldMAP: get_SQL_DELETESTACK: Table cannot be written because primary-key is not writeable !!");

	    /*
	     * beim loeschen muessen immer erst die abhaengigen tabellen geloescht werden,
	     * deshalb wird hier zuerst alles andere, dann die haupttabelle geloescht
	     */

		String cDEL = "DELETE FROM "+bibALL.get_TABLEOWNER()+"."+this.cMAIN_TABLE +" WHERE "+this.oSQLFieldPKMainTable.get_cFieldName()+"="+cUNFormated_Primary_KEY_Value;
		vSQL.add(cDEL);

		return vSQL;
		
	}

	
	

	
	/**
	 * @param cTablename
	 * @param vWritableFields
	 * @return
	 * @throws myException
	 * baut das Update einer einzelnen tabelle
	 */
	private Vector<String> Build_SQL_Update(String cTablename, Vector<SQLField> vWritableFields, String cPK_ValueFormated) throws myException
	{
		
	    SQLFieldForPrimaryKey oPKField = (SQLFieldForPrimaryKey) this.hmPrimaryKeys.get(cTablename);

	    if (oPKField == null)
	    	throw new myException("SQLFieldMAP:Build_SQL_Update:Error finding primary-key-field for table "+cTablename);

		MySQL_StatementUPDATE oUpdate = new MySQL_StatementUPDATE(cTablename,oPKField.get_cFieldName(),oPKField.get_cValueString_For_DB(cPK_ValueFormated));

		for (int i=0;i<vWritableFields.size();i++)
		{
			SQLField oField = (SQLField)vWritableFields.get(i);
			oUpdate.put( oField.get_cInsertFieldPart(),oField.get_cInsertValuePart());
		}
		
		return oUpdate.get_vSQL_StatementStrings();
	}
	

	
	
	
	
	/**
	 * @sammelt die normalen SQLFields zu einer tabelle, die 
	 * im INSERT auftauchen 
	 */
	private Vector<SQLField>  get_WriteableStandardFields(String cTableNAME)
	{
		Vector<SQLField> vRueck = new Vector<SQLField>();
		for (int i=0;i<this.vFieldAlias.size();i++)
		{
			SQLField oField = (SQLField) this.get(this.vFieldAlias.get(i));
			if (!(		oField instanceof SQLFieldJoinOutside || 
						oField  instanceof SQLFieldForPrimaryKey || 
						oField  instanceof SQLFieldForRestrictTableRange))
			{
				if (oField.get_bWriteable())
				{
					if (oField.get_cTableName().equals(cTableNAME))
						vRueck.add(oField);
				}
			}
		}
		return vRueck;
	}
	
	
	/**
	 * @sammelt die Felder, die eine beschraenkung machen 
	 */
	private Vector<SQLField>  get_RestrictRangeFields()
	{
		Vector<SQLField> vRueck = new Vector<SQLField>();
		for (int i=0;i<this.vFieldAlias.size();i++)
		{
			SQLField oField = (SQLField) this.get(this.vFieldAlias.get(i));
			if (oField  instanceof SQLFieldForRestrictTableRange)
			{
				if (oField.get_bWriteable())
					vRueck.add(oField);
			}
		}
		return vRueck;
	}




	public void add_InnerConnector(SQLConnectorInnerTables oConnector)
	{
		this.vInnerConnectors.add(oConnector);
	}
	
	
	/*
	 * wenn eine sql-fieldmap eine leading-Map ist, dann MUSS das verknuepfungsfeld nach aussen leer sein
	 */
	public boolean get_bIsSQLMapLEADINGMAP()
	{
		return (this.oSQLFieldJoinOutside == null);
	}


	public SQLFieldJoinOutside get_oSQLFieldJoinOutside()
	{
		return oSQLFieldJoinOutside;
	}
	
	public String get_cMAIN_TABLE()
	{
		return this.cMAIN_TABLE;
	}

	
	/**
	 * @param cFieldHashName
	 * @return SQLField
	 * @throws myException, if not found
	 */
	public SQLField get_(String cFieldHashName) throws myException
	{
		SQLField oField = (SQLField)this.get(cFieldHashName);
		if (oField == null)
		{
			throw new myException("SQLFieldMAP:get_:Field-Hashname not found: "+cFieldHashName);
		}
		
		return oField;
	}
	
	

	/**
	 * 2015-08-24
	 * @param field
	 * @return
	 * @throws myException
	 */
	public SQLField get_(IF_Field field) throws myException {
		return this.get_(field.fn());
	}

	
	
	/**
	 * @returns Vector mit allen vorhandenen Strings aus Tabellenname:Feldnamen (GROSS-geschrieben)
	 * @throws myException
	 */
	public Vector<String> get_vFieldKennungsVector() throws myException
	{
		Vector<String> vRueck = new Vector<String>();
		for (int i=0;i<this.vFieldAlias.size();i++)
		{
			SQLField oField = this.get_((String)this.vFieldAlias.get(i));
			vRueck.add(oField.get_cTableName().toUpperCase()+":"+oField.get_cFieldName().toUpperCase());
		}
		return vRueck;
	}




	public SQL_Insert_AGENT get_oInsertAgent()
	{
		return oInsertAgent;
	}

	public void set_oInsertAgent(SQL_Insert_AGENT insertAgent)
	{
		oInsertAgent = insertAgent;
	}

	public SQL_Update_AGENT get_oUpdateAgent()
	{
		return oUpdateAgent;
	}

	public void set_oUpdateAgent(SQL_Update_AGENT updateAgent)
	{
		oUpdateAgent = updateAgent;
	}
	
	
	/*
	 * 2012-11-12: public gemacht
	 */
	public class joinBlock
	{
		public  String cJoinTableName = null;
		public String cJoinTableAlias = null;
		public int    iJoinType = 0;
		public String cFieldList = null;
		public boolean bFieldsInclude = false;
		public String cJoinBedingung = null;
		public String cSchema = null;
		

		/**
		 * 
		 * @param schema
		 * @param joinTableName
		 * @param joinTableAlias
		 * @param joinType
		 * @param fieldList
		 * @param FieldsInclude
		 * @param joinBedingung
		 */
		public joinBlock(String schema, String joinTableName, String joinTableAlias, int joinType, String fieldList, boolean FieldsInclude, String joinBedingung)
		{
			super();
			this.cJoinTableName = joinTableName;
			this.cJoinTableAlias = S.isEmpty(joinTableAlias)?joinTableName:joinTableAlias;
			this.iJoinType = joinType;
			this.cFieldList = fieldList;
			this.bFieldsInclude = FieldsInclude;
			this.cJoinBedingung = joinBedingung;
			this.cSchema = schema;
		}
		
		
		/**
		 * 20181115: neuer reduzierter konstruktor
		 * @param tableName
		 * @param joinTableAlias
		 * @param joinType
		 * @param fieldList
		 * @param FieldsInclude
		 * @param joinBedingung
		 */
		public joinBlock(_TAB tableName, String joinTableAlias, int joinType, String fieldList, boolean FieldsInclude, String joinBedingung)
		{
			super();
			this.cJoinTableName = tableName.fullTableName();
			this.cJoinTableAlias = S.isEmpty(joinTableAlias)?cJoinTableName:joinTableAlias;
			this.iJoinType = joinType;
			this.cFieldList = fieldList;
			this.bFieldsInclude = FieldsInclude;
			this.cJoinBedingung = joinBedingung;
			this.cSchema = bibE2.cTO();
		}
		
		
		
		
		public String get_JoinBlockForSQLQuery()
		{
			String cRueck = "";
			if (this.iJoinType==SQLFieldMAP.RIGHT_OUTER)
			{
				cRueck=" RIGHT OUTER JOIN ";
			} 
			else if (this.iJoinType==SQLFieldMAP.LEFT_OUTER)
			{
				cRueck=" LEFT OUTER JOIN ";
			}
			else
			{
				cRueck=" INNER JOIN ";
			}
			
			//2015-01-27: schema beruecksichtigen
			String cTableTerm = this.cJoinTableName;
			if (S.isFull(this.cSchema)) {
				cTableTerm = this.cSchema.trim()+"."+this.cJoinTableName;
			}
			
			cRueck += cTableTerm+" "+this.cJoinTableAlias+" ON (";
			cRueck += this.cJoinBedingung+") ";
			
			
			
			
			return cRueck;
		}
		
		
	}



	public void set_bInvertiereDynamischenWhereblock(boolean invertiereDynamischenWhereblock)
	{
		this.bInvertiereDynamischenWhereblock = invertiereDynamischenWhereblock;
	}





	/**
	 * 2012-11-12: veroeffentlicht
	 * @return
	 */
	public Vector<joinBlock> get_vJoinTables()
	{
		return vJoinTables;
	}
	
	
	/**
	 * 2013-04-11: rausschreiben der restrictionfields und der werte
	 * @return s hashmap mit feldnamen und beschraenkungswerten
	 * @throws myException 
	 */
	public HashMap<String, String> get_hmRestrictionFieldValues() throws myException
	{
		HashMap<String, String>  hmRueck = new HashMap<String, String>();
		
		for (SQLField  oSQLF: this.values())
		{
			if (oSQLF instanceof SQLFieldForRestrictTableRange)
			{
				hmRueck.put(oSQLF.get_cFieldName(), ((SQLFieldForRestrictTableRange)oSQLF).get_cRestictionValue_IN_DB_FORMAT());
			}
		}
		return hmRueck;
	}
	
	
	

	/**
	 *
	 * 2015-02-19: neue methode: gibt die auszfuehrenden statements als SqlBuilder zurueck (KEINE Beruecksichtigung von join-outside-fields)
	 *
	 * @param oInputMAP
	 * @param add_on_terms
	 * @return
	 * @throws myException
	 */
	public Vector<MySqlStatementBuilder> get_SqlStatementBuilder(SQLMaskInputMAP oInputMAP, HashMap<String, String> add_on_Terms) throws myException
	{
		Vector<MySqlStatementBuilder> vStmdBd = new Vector<MySqlStatementBuilder>();
		
			
		if (!this.oSQLFieldPKMainTable.get_bWriteable())
			throw new myException("SQLFieldMAP: get_SqlStatementBuilder: Table cannot be written because primary-key is not writeable !!");
		
		Vector<SQLField> vFields = this.get_WriteableStandardFields(this.cMAIN_TABLE);

		/*
		 * felder fuellen
		 */
		for (int k=0;k<vFields.size();k++) 	{
			SQLField oField =(SQLField)vFields.get(k);
			
			/*
			 * zuerst das feld loeschen, damit nicht von vorigen aktionen bei labels z.b.
			 * werte in dem objekt stehen, die hier nicht ueberschrieben werden, weil die
			 * felder nicht in der input-map auftauchen
			 */
			oField.set_cNewValueFormated(null);
			
			
			/*
			 * zuerst alle felder mit dem standard-wert fuellen, damit der auch bei nicht
			 * in der maske befindlichen feldern vorhanden ist
			 */
			if (oField.get_cDefaultValueFormated() != null)
			{
				oField.set_cNewValueFormated(oField.get_cDefaultValueFormated());
			}
			
			/*
			 * dann die maskenwerte eintragen
			 */
			String cLABEL = ((SQLField)vFields.get(k)).get_cFieldLabel();
			if (oInputMAP.containsKey(cLABEL))
				oField.set_cNewValueFormated((String)oInputMAP.get(cLABEL));
		}

		/*
		 * jetzt wird das pk-feld eingehaengt
		 */
		String cNewPrimaryKeyValue = this.oSQLFieldPKMainTable.cQueryNext_KEY_ValueFormated();
		this.oSQLFieldPKMainTable.set_cNewValueFormated(cNewPrimaryKeyValue);
		vFields.add(this.oSQLFieldPKMainTable);
		
		/*
		 * der neu erzeugte PK wird in die MAP eingehaengt 
		 */
		oInputMAP.put(this.oSQLFieldPKMainTable.get_cFieldLabel(),cNewPrimaryKeyValue);
		
		/*
		 * restictions-felder reinhaengen
		 */
		vFields.addAll(this.get_RestrictRangeFields());
			
		MySqlStatementBuilder  stmtBd = new MySqlStatementBuilder();
		for (int i=0;i<vFields.size();i++)
		{
			stmtBd.addSQL_Paar( ((SQLField)vFields.get(i)).get_cInsertFieldPart(), ((SQLField)vFields.get(i)).get_cInsertValuePart());
		}
		if (add_on_Terms!=null) {
			stmtBd.putAll(add_on_Terms);
		}

		vStmdBd.add(stmtBd);
		
		if (this.oInsertAgent != null) {
			Vector<String> vAddOns = this.oInsertAgent.get_vZusatzStatements(this,oInputMAP, cNewPrimaryKeyValue);
			for (String sql: vAddOns) {
				vStmdBd.add(new MySqlStatementBuilder_Simple(sql));
			}
		}
		
		return vStmdBd;
	}

	
	
	
	//20180109: neue moeglichkeit einen join zu definieren ohne interne automatismen
	public void addJoinBlock(joinBlock block) throws myException {
		this.vJoinTables.add(block);
	}
	
	
	
	
	

	
	
}
