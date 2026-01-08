package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;



public class BSK_P_MASK_Daughter_Liefertermin extends MyE2_DBC_DaughterTable
{
	
	public BSK_P_MASK_Daughter_Liefertermin(	SQLFieldMAP 			fieldMAP, 
										E2_ComponentMAP			ocomponentMAP_from_Mother
										) throws myException
	{
		super();
		
		
		/*
		 * tochtertabelle fuer das druckprotokoll
		 */
		Project_SQLFieldMAP oFieldMapTermine = new Project_SQLFieldMAP("JT_VPOS_KON_TERM",":ID_VPOS_KON:",false);
		oFieldMapTermine.add_SQLField(
				new SQLFieldJoinOutside("JT_VPOS_KON_TERM","ID_VPOS_KON","ID_VPOS_KON",
									new MyE2_String("ID-VPOS-Kon"),false,bibE2.get_CurrSession(),
									fieldMAP.get_SQLField("ID_VPOS_KON")), false
									);
		oFieldMapTermine.initFields();
		
		E2_ComponentMAP oMapLiefertermin = new E2_ComponentMAP(oFieldMapTermine);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		MyE2_DB_TextField			oTF_Lieferdatumdatum 	= 	new MyE2_DB_TextField(oFieldMapTermine.get_SQLField("LIEFERDATUM"));
		MyE2_DB_TextField			oTF_Lieferdatumdatum_bis = 	new MyE2_DB_TextField(oFieldMapTermine.get_SQLField("LIEFERDATUM_BIS"));
		MyE2_DB_TextField			oTF_Menge = 				new MyE2_DB_TextField(oFieldMapTermine.get_SQLField("ANZAHL"),true,150);
		
		MyE2_DB_TextArea			oTF_BEMERKUNG = 	new MyE2_DB_TextArea(oFieldMapTermine.get_SQLField("BEMERKUNG"));
		MyE2_DB_Label				oLabelID = 			new MyE2_DB_Label(oFieldMapTermine.get_SQLField("ID_VPOS_KON_TERM"));

		MyE2_DB_MultiComponentColumn   oColDatumsWerte = new MyE2_DB_MultiComponentColumn();
		oColDatumsWerte.add_Component(oTF_Lieferdatumdatum, 	new MyE2_String("Datum von"), null);
		oColDatumsWerte.add_Component(oTF_Lieferdatumdatum_bis, new MyE2_String("  ...bis"), null);
		
		
		oMapLiefertermin.add_Component(BSK__CONST.HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_LIEFERTERMIN,oButtonForDel,new MyE2_String("?"));
		oMapLiefertermin.add_Component("HASH_LIEFERDATEN",oColDatumsWerte,new MyE2_String("Datum"));
		oMapLiefertermin.add_Component(oTF_Menge,		new MyE2_String("Menge"));
		oMapLiefertermin.add_Component(oTF_BEMERKUNG,	new MyE2_String("Bemerkung"));
		oMapLiefertermin.add_Component(oLabelID,		new MyE2_String("ID"));
		
		oTF_BEMERKUNG.set_iWidthPixel(500);
		oTF_BEMERKUNG.set_iRows(4);
		
		/*
		 * neueingabe-button definieren
		 */
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);
		
		this.set_oContainerExScrollHeight(new Extent(350));
		
		this.INIT_DAUGHTER(	fieldMAP.get_oSQLFieldPKMainTable(),
											ocomponentMAP_from_Mother,
											oMapLiefertermin,
											null);
	
		oTF_Menge.EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());

	}
	
}
