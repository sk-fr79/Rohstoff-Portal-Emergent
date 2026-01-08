package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_MASK_Comp_Daughter_Mitarbeiter extends MyE2_DBC_DaughterTable 
{

	public PROJEKT_MASK_Comp_Daughter_Mitarbeiter(SQLFieldMAP fieldMAPMotherTable, E2_ComponentMAP oComponentMapMother) throws myException
	{
		super();
		
		E2_DropDownSettings oDDsMitarbeiterTyp = new E2_DropDownSettings(
														"JT_PRO_MITARB_TYP",
														"NVL(KURZBESCHREIBUNG,'-')",
														"ID_PRO_MITARB_TYP",
														"",
														"IST_STANDARD",
														true,
														"KURZBESCHREIBUNG");


				
		SQLFieldMAP oSQLFieldMap = new SQLFieldMAP("JT_PRO_MITARB",bibE2.get_CurrSession());
		oSQLFieldMap.addCompleteTable_FIELDLIST("JT_PRO_MITARB",":ID_PRO_MITARB:ID_PROJEKT:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMap.add_SQLField(new SQLFieldForPrimaryKey("JT_PRO_MITARB","ID_PRO_MITARB","ID_PRO_MITARB",new MyE2_String("ID-Projekt-Adressen"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_PRO_MITARB.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMap.add_SQLField(new SQLFieldJoinOutside("JT_PRO_MITARB","ID_PROJEKT","ID_PROJEKT",
											new MyE2_String("ID-Projekt"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_PROJEKT")), false);

		oSQLFieldMap.get_("ID_USER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMap.get_("ID_PRO_MITARB_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMap.get_("ID_PRO_MITARB_TYP").set_cDefaultValueFormated(oDDsMitarbeiterTyp.getDefault());
		
		oSQLFieldMap.initFields();
		
		E2_ComponentMAP 			oMapMitarbeiter = 		new E2_ComponentMAP(oSQLFieldMap);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();

		oMapMitarbeiter.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapMitarbeiter.add_Component(new DB_Component_USER_DROPDOWN(oSQLFieldMap.get_("ID_USER")), new MyE2_String("Projektmitarbeiter"));
		oMapMitarbeiter.add_Component(new MyE2_DB_SelectField(oSQLFieldMap.get_("ID_PRO_MITARB_TYP"),oDDsMitarbeiterTyp.getDD(),false), new MyE2_String("Funktion"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(400));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							oComponentMapMother,
							oMapMitarbeiter,
							null);
	
	}

}
