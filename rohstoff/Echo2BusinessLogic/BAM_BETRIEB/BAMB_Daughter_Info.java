package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BAMB_Daughter_Info extends MyE2_DBC_DaughterTable
{

	public BAMB_Daughter_Info(	SQLFieldMAP 			fieldMAPMotherTable, 
								E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldInfo = new SQLFieldMAP("JT_FBAM_INFOBLOCK",bibE2.get_CurrSession());
		oSQLFieldInfo.addCompleteTable_FIELDLIST("JT_FBAM_INFOBLOCK",":ID_FBAM_INFOBLOCK:ID_FBAM:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldInfo.add_SQLField(new SQLFieldForPrimaryKey("JT_FBAM_INFOBLOCK","ID_FBAM_INFOBLOCK","ID_FBAM_INFOBLOCK",new MyE2_String("ID-INFO-Block"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_FBAM_INFOBLOCK.NEXTVAL FROM DUAL",true), false);

		oSQLFieldInfo.add_SQLField(new SQLFieldJoinOutside("JT_FBAM_INFOBLOCK","ID_FBAM","ID_ADRESSE",
											new MyE2_String("ID-FBAM"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_FBAM")), false);

		oSQLFieldInfo.initFields();
		
		E2_ComponentMAP 			oMapInfosBAM = new E2_ComponentMAP(oSQLFieldInfo);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextArea			oTF_Infoblock = 	new MyE2_DB_TextArea(oSQLFieldInfo.get_SQLField("INFOBLOCK"));
		
		oTF_Infoblock.EXT_DB().set_bIsSortable(false);
		
		oTF_Infoblock.set_iWidthPixel(700);
		oTF_Infoblock.set_iRows(20);
		
		oMapInfosBAM.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapInfosBAM.add_Component(oTF_Infoblock,new MyE2_String("Infos zur Beanstandung"));

		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(460));
		
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapInfosBAM,
							null);
		
	}

	
	
	
}
