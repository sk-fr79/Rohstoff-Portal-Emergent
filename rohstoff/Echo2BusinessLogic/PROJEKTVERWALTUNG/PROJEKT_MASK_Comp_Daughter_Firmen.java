package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

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
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class PROJEKT_MASK_Comp_Daughter_Firmen extends MyE2_DBC_DaughterTable 
{

	public PROJEKT_MASK_Comp_Daughter_Firmen(SQLFieldMAP fieldMAPMotherTable, E2_ComponentMAP oComponentMapMother) throws myException
	{
		super();
		
				
		SQLFieldMAP oSQLFieldMap = new SQLFieldMAP("JT_PRO_ADRESSEN",bibE2.get_CurrSession());
		oSQLFieldMap.addCompleteTable_FIELDLIST("JT_PRO_ADRESSEN",":ID_PRO_ADRESSEN:ID_PROJEKT:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMap.add_SQLField(new SQLFieldForPrimaryKey("JT_PRO_ADRESSEN","ID_PRO_ADRESSEN","ID_PRO_ADRESSEN",new MyE2_String("ID-Projekt-Adressen"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_PRO_ADRESSEN.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMap.add_SQLField(new SQLFieldJoinOutside("JT_PRO_ADRESSEN","ID_PROJEKT","ID_PROJEKT",
											new MyE2_String("ID-Projekt"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_PROJEKT")), false);

		oSQLFieldMap.get_("ID_ADRESSE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMap.initFields();
		

		
		E2_ComponentMAP 			oMapFirmen = 		new E2_ComponentMAP(oSQLFieldMap);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();

		DB_SEARCH_Adress            oSearchAdress = new DB_SEARCH_Adress(oSQLFieldMap.get_("ID_ADRESSE"));
		oSearchAdress.get_oTextForAnzeige().setWidth(new Extent(400));
		
		oMapFirmen.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapFirmen.add_Component(oSearchAdress,new MyE2_String("Beteiligte Firma"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(400));
		
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							oComponentMapMother,
										oMapFirmen,
										null);

		
		
	
	}

}
