package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class CG__Column_RECORDS_and_NAMESPACE extends MyE2_Column
{
	
	private MyE2_Button  but_StartGeneratingRecords =	new MyE2_Button("Baue RECORD-Objekte");
	private MyE2_Button  but_StartGeneratingNameSpace = new MyE2_Button("Baue Statisches NAME-Objekt");
	//private MyE2_Button  but_StartGeneratingDBSchema =  new MyE2_Button("Baue neues statisches DB-Objekt");
	private MyE2_Button  but_StartGeneratingEnumNames =  new MyE2_Button("Baue Statisches ENUM-Objekt");
	
	private MyE2_Column  oColOutPut  = new MyE2_Column();
	
	private String[][]   cTables = null;
	
	
	public CG__Column_RECORDS_and_NAMESPACE() throws myException 
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		this.add(this.but_StartGeneratingRecords, 	E2_INSETS.I_10_10_10_10);
		this.add(this.but_StartGeneratingNameSpace, 	E2_INSETS.I_10_10_10_10);
		this.add(this.but_StartGeneratingEnumNames, 	E2_INSETS.I_10_10_10_10);
		this.add(this.oColOutPut,E2_INSETS.I_10_10_10_10);
		
		this.cTables = bibDB.EinzelAbfrageInArray(DB_META.get_TablesQuery_JT_and_JD(DB_META.DB_ORA, bibE2.cTO(), true, false));
		
		if (this.cTables==null || this.cTables.length==0)
			throw new myException(this,"Error: Could not read Tables ...");
		
		this.but_StartGeneratingRecords.add_oActionAgent(new CG_ActionAgent_Baue_RECORDS_AND_RECORDLISTS(this.cTables,this.oColOutPut));
		this.but_StartGeneratingNameSpace.add_oActionAgent(new CG_ActionAgent_Baue_Static_FIELDNAMES_LIST(this.cTables,this.oColOutPut));
		this.but_StartGeneratingEnumNames.add_oActionAgent(new CG_ActionAgent_Baue_ENUM_NAMES_V2(this.cTables,this.oColOutPut));
		
		//this.but_StartGeneratingDBSchema.add_oActionAgent(new CG_ActionAgent_Baue_TableSchemaNew(this.cTables,this.oColOutPut));
		
	}
	
}
