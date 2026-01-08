package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


/**
 * simpleDaughter-Component zum erfassen der AVV-Codes die bei einer adresse erlaubt sind
 * @author martin
 *
 */
public class __FS_Component_MASK_DAUGHTER_EAK_CODES extends MyE2_DBC_DaughterTable
{
	
	public __FS_Component_MASK_DAUGHTER_EAK_CODES(SQLFieldMAP 		fieldMAPMotherTable, 
												E2_ComponentMAP		ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMap_EAK_CODES = new SQLFieldMAP("JT_ADRESSE_EAK_CODE",bibE2.get_CurrSession());
		oSQLFieldMap_EAK_CODES.addCompleteTable_FIELDLIST("JT_ADRESSE_EAK_CODE",":ID_ADRESSE_EAK_CODE:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMap_EAK_CODES.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE_EAK_CODE","ID_ADRESSE_EAK_CODE","ID_ADRESSE_EAK_CODE",new MyE2_String("ID_ADRESSE_EAK_CODE"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE_EAK_CODE.NEXTVAL FROM DUAL",true), false);

		String cSQL_SubQuery4Order = "(SELECT     JT_EAK_BRANCHE.KEY_BRANCHE||' '|| JT_EAK_GRUPPE.KEY_GRUPPE||' '||JT_EAK_CODE.KEY_CODE "+
								" FROM " +
								bibE2.cTO()+".JT_EAK_GRUPPE,"+
								bibE2.cTO()+".JT_EAK_BRANCHE,"+
								bibE2.cTO()+".JT_EAK_CODE "+
								" WHERE " +
								" JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE  AND"+ 
								" JT_EAK_GRUPPE.ID_EAK_GRUPPE = JT_EAK_CODE.ID_EAK_GRUPPE  AND "+
								" JT_EAK_CODE.ID_EAK_CODE=JT_ADRESSE_EAK_CODE.ID_EAK_CODE)";

		oSQLFieldMap_EAK_CODES.add_SQLField(new SQLField(cSQL_SubQuery4Order,"EAK_TEXT",new MyE2_String("ID_ADRESSE_EAK_CODE"),bibE2.get_CurrSession()),true);

		
		oSQLFieldMap_EAK_CODES.add_SQLField(new SQLFieldJoinOutside("JT_ADRESSE_EAK_CODE","ID_ADRESSE","ID_ADRESSE",
											new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")), false);

		oSQLFieldMap_EAK_CODES.initFields();
		
		
		E2_ComponentMAP 					oMapEAK_CODES = 	new E2_ComponentMAP(oSQLFieldMap_EAK_CODES);
		MyE2_ButtonMarkForDelete 			oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		__FS_MASK_COMPONENT_SEARCH_EAK_CODES		oSearchEAK_Codes = 	new __FS_MASK_COMPONENT_SEARCH_EAK_CODES(oSQLFieldMap_EAK_CODES.get_SQLField("ID_EAK_CODE"));
		oSearchEAK_Codes.EXT().set_oColExtent(new Extent(400));
		oMapEAK_CODES.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
//		MyE2_DB_TextField  oSearchEAK_Codes = new MyE2_DB_TextField(oSQLFieldMap_EAK_CODES.get_SQLField("ID_EAK_CODE"));
		oMapEAK_CODES.add_Component(oSearchEAK_Codes, new MyE2_String("AVV-Code"));	
		
		
		MyE2_Button							oButtonNEW = 		new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		//this.set_oContainerExScrollHeight(new Extent(300));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(100,Extent.PERCENT));

		//sortierung eingeben
		oSQLFieldMap_EAK_CODES.clear_ORDER_SEGMENT();
		oSQLFieldMap_EAK_CODES.add_ORDER_SEGMENT(cSQL_SubQuery4Order);
		
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapEAK_CODES,
							null);
		
	}

	
}
