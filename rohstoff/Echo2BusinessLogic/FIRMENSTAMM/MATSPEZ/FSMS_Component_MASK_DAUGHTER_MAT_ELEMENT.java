package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT extends MyE2_DBC_DaughterTable
{

	   /*
     * kenner fuer den selektor, der die definition des abzugs macht
     */
    public static String[][]   cSelektDefAnteil = {{"  ",""},{" = ","EXACT"},{" >= ","MINIMAL"},{" <= ","MAXIMAL"},{" - ","VONBIS"}}; 
	


	public FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT(	SQLFieldMAP 			fieldMAP_MatSpez, 
														E2_ComponentMAP			ocomponentMAP_from_MatSpez) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMapMatElement = new SQLFieldMAP("JT_MAT_ELEMENT",bibE2.get_CurrSession());
		oSQLFieldMapMatElement.addCompleteTable_FIELDLIST("JT_MAT_ELEMENT",":ID_MAT_ELEMENT:ID_MAT_SPEZ:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMapMatElement.add_SQLField(new SQLFieldForPrimaryKey("JT_MAT_ELEMENT","ID_MAT_ELEMENT","ID_MAT_ELEMENT",new MyE2_String("ID-Mat-Element"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_MAT_ELEMENT.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapMatElement.add_SQLField(new SQLFieldJoinOutside("JT_MAT_ELEMENT","ID_MAT_SPEZ","ID_MAT_SPEZ",
											new MyE2_String("ID-Mat-Spez"),false,bibE2.get_CurrSession(),fieldMAP_MatSpez.get_SQLField("ID_MAT_SPEZ")), false);

		oSQLFieldMapMatElement.get_("DEFANTEIL").set_cDefaultValueFormated("EXACT");
		
		oSQLFieldMapMatElement.get_("ID_ELEMENT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapMatElement.get_("ANTEIL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapMatElement.get_("DEFANTEIL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		oSQLFieldMapMatElement.initFields();
		
		/*
		 * component-Map der tochterliste
		 */
		E2_ComponentMAP 			oMap_MatElement = new E2_ComponentMAP(oSQLFieldMapMatElement);
		
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
//		E2_DropDownSettings oDDElemente = new E2_DropDownSettings("JT_ELEMENT","KURZ || ' - ' || SUBSTR(LANG,1,10)", "ID_ELEMENT",null,true);
		E2_DropDownSettings oDDElemente = new E2_DropDownSettings("JT_ELEMENT","LANG || ' ('||KURZ||')'", "ID_ELEMENT",null,null,true,"LANG");
		MyE2_DB_SelectField			oSelectElement = new MyE2_DB_SelectField(oSQLFieldMapMatElement.get_SQLField("ID_ELEMENT"),oDDElemente.getDD(),false);
		MyE2_DB_TextField			oTF_AnteilMin = 	new MyE2_DB_TextField(oSQLFieldMapMatElement.get_SQLField("ANTEILMIN"));
		MyE2_DB_SelectField			oSelectDefAnteil = new MyE2_DB_SelectField(oSQLFieldMapMatElement.get_SQLField("DEFANTEIL"),FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT.cSelektDefAnteil,false);
		MyE2_DB_TextField			oTF_AnteilMax = 	new MyE2_DB_TextField(oSQLFieldMapMatElement.get_SQLField("ANTEIL"));
		MyE2_DB_TextField			oTF_InfoText = 		new MyE2_DB_TextField(oSQLFieldMapMatElement.get_SQLField("INFOTEXT"));

		
		oSelectElement.EXT().set_oColExtent(new Extent(120));
		oTF_AnteilMin.EXT().set_oColExtent(new Extent(120));
		oSelectDefAnteil.EXT().set_oColExtent(new Extent(80));
		oTF_AnteilMax.EXT().set_oColExtent(new Extent(120));
		oTF_InfoText.EXT().set_oColExtent(new Extent(360));
		
		oTF_AnteilMin.set_iWidthPixel(100);
		oTF_AnteilMax.set_iWidthPixel(100);
		oTF_InfoText.set_iWidthPixel(350);
		
		oMap_MatElement.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMap_MatElement.add_Component(oSelectElement,new MyE2_String("Element"));
		oMap_MatElement.add_Component(oTF_AnteilMin,new MyE2_String("Min (%)"));
		oMap_MatElement.add_Component(oSelectDefAnteil,new MyE2_String("..."));
		oMap_MatElement.add_Component(oTF_AnteilMax,new MyE2_String("Max (%)"));
		oMap_MatElement.add_Component(oTF_InfoText,new MyE2_String("Beschreibung"));
		
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(230));
		this.set_oContainerExScrollWidth(new Extent(860));
		
		this.INIT_DAUGHTER(fieldMAP_MatSpez.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_MatSpez,
							oMap_MatElement,
							null);
		
	}

}
