package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SelectFieldFuhreKostenTyp;

public class FU_MASK_DAUGHTER_KOSTEN extends MyE2_DBC_DaughterTable
{

	public FU_MASK_DAUGHTER_KOSTEN(	SQLFieldMAP 			fieldMAPMotherTable, 
									E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		Project_SQLFieldMAP oSQLFieldMapKOSTEN = new Project_SQLFieldMAP("JT_VPOS_TPA_FUHRE_KOSTEN","ID_VPOS_TPA_FUHRE",false);

		oSQLFieldMapKOSTEN.add_SQLField(new SQLFieldJoinOutside("JT_VPOS_TPA_FUHRE_KOSTEN","ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE",
											new MyE2_String("ID-VPOS-TPA-Fuhre"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_VPOS_TPA_FUHRE")), false);

		oSQLFieldMapKOSTEN.initFields();
		
		E2_ComponentMAP 			oMapKOSTEN = 		new E2_ComponentMAP(oSQLFieldMapKOSTEN);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField			oTF_KostenBeschreibung = 	new MyE2_DB_TextField(oSQLFieldMapKOSTEN.get_SQLField("INFO_KOSTEN"),true,200);
		MyE2_DB_TextField			oTF_KostenFremdbeleg = 		new MyE2_DB_TextField(oSQLFieldMapKOSTEN.get_SQLField("FREMDBELEG_NUMMER"),true,100);
		MyE2_DB_TextField			oTF_KostenBetrag = 			new MyE2_DB_TextField(oSQLFieldMapKOSTEN.get_SQLField("BETRAG_KOSTEN"),true,80);
		
		BS__SelectFieldFuhreKostenTyp  oSFKostenTyp = new BS__SelectFieldFuhreKostenTyp(oSQLFieldMapKOSTEN.get_SQLField(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_FUHREN_KOSTEN_TYP),120);
		oSFKostenTyp.setFont(new E2_FontPlain(-2));
		
		oTF_KostenBetrag.EXT().set_oColExtent(new Extent(80));
		oTF_KostenBeschreibung.EXT().set_oColExtent(new Extent(200));
		oTF_KostenFremdbeleg.EXT().set_oColExtent(new Extent(100));
		oSFKostenTyp.EXT().set_oColExtent(new Extent(120));
		
		oTF_KostenBetrag.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridRight_DARK_TOP(E2_INSETS.I_2_2_2_2));
		oTF_KostenBeschreibung.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK_TOP(E2_INSETS.I_2_2_2_2));
		oTF_KostenFremdbeleg.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK_TOP(E2_INSETS.I_2_2_2_2));
		oSFKostenTyp.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK_TOP(E2_INSETS.I_2_2_2_2));
		
		oMapKOSTEN.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapKOSTEN.add_Component(oTF_KostenBetrag,new MyE2_String("Betrag"));
		oMapKOSTEN.add_Component(oTF_KostenFremdbeleg,new MyE2_String("Fremdbeleg"));
		oMapKOSTEN.add_Component(oTF_KostenBeschreibung,new MyE2_String("Beschreibung"));
		oMapKOSTEN.add_Component(oSFKostenTyp,new MyE2_String("Typ."));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(130));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapKOSTEN,
							null);
		
	}

}
