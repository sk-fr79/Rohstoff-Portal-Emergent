package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SONDERDEF;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * @author martin
 * Masken-Simple-Daughter zur eingabe der zugeordneten POS-Vorlagen
 */
public class FU_SONDER_MASK_TOCHTER_POS_VL extends MyE2_DBC_DaughterTable
{
	/**
	 * 
	 * @param fieldMAPMotherTable
	 * @param ocomponentMAP_from_Mother
	 * @throws myException
	 */
	
	public FU_SONDER_MASK_TOCHTER_POS_VL(	SQLFieldMAP 		fieldMAPMotherTable, 
											E2_ComponentMAP		ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMapSonder = new SQLFieldMAP("JT_FUHRE_SONDER_VL",bibE2.get_CurrSession());
		oSQLFieldMapSonder.addCompleteTable_FIELDLIST("JT_FUHRE_SONDER_VL",":ID_FUHRE_SONDER_VL:ID_VPOS_TPA_FUHRE_SONDER:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMapSonder.add_SQLField(new SQLFieldForPrimaryKey("JT_FUHRE_SONDER_VL","ID_FUHRE_SONDER_VL","ID_FUHRE_SONDER_VL",new MyE2_String("ID-FUHRE_SONDER_VL"),bibE2.get_CurrSession(),
									"SELECT "+bibE2.cTO()+".SEQ_FUHRE_SONDER_VL.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapSonder.add_SQLField(new SQLFieldJoinOutside("JT_FUHRE_SONDER_VL","ID_VPOS_TPA_FUHRE_SONDER","ID_VPOS_TPA_FUHRE_SONDER",
											new MyE2_String("ID-Fuhre-Sonderfall"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_VPOS_TPA_FUHRE_SONDER")), false);

		oSQLFieldMapSonder.initFields();
		
		
		E2_ComponentMAP 			oMapTocherSonder_VG_POS = 	new E2_ComponentMAP(oSQLFieldMapSonder);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 			new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_Label			    oLabID = 					new MyE2_DB_Label(oSQLFieldMapSonder.get_SQLField("ID_FUHRE_SONDER_VL"));
		MyE2_DB_SelectField			oSel_RG_POS_VL = 			new MyE2_DB_SelectField(
																		oSQLFieldMapSonder.get_SQLField("ID_VPOS_RG_VL"),
																		"SELECT EINTRAG_FUER_DROPDOWN,ID_VPOS_RG_VL FROM "+bibE2.cTO()+".JT_VPOS_RG_VL ORDER BY EINTRAG_FUER_DROPDOWN",
																		false,
																		false);
		

		oLabID.EXT().set_oColExtent(new Extent(100));
		oLabID.EXT().set_oLayout_ListTitelElement(MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_1_1_1_1));
		oLabID.EXT_DB().set_bIsSortable(false);
		
		oSel_RG_POS_VL.EXT().set_oColExtent(new Extent(400));
		oSel_RG_POS_VL.EXT().set_oLayout_ListTitelElement(MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_1_1_1_1));
		oSel_RG_POS_VL.EXT_DB().set_bIsSortable(false);
		
		oMapTocherSonder_VG_POS.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapTocherSonder_VG_POS.add_Component(oLabID,new MyE2_String("ID"));
		oMapTocherSonder_VG_POS.add_Component(oSel_RG_POS_VL,new MyE2_String("Sonder-Position"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(100));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapTocherSonder_VG_POS,
							null);
		
	}

}
