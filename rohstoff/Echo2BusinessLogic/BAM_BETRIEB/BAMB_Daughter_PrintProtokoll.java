package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_TOOLS;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.XXX_PrintProtokoll;


public class BAMB_Daughter_PrintProtokoll extends XXX_PrintProtokoll {

	public BAMB_Daughter_PrintProtokoll(	SQLFieldMAP 			fieldMAP, 
											E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException {
		super();
		this.init_PrintProtokoll(fieldMAP, ocomponentMAP_from_Mother, "FBAM", new Extent(95, Extent.PERCENT), new Extent(460),true);
	}

	@Override
	public String[][] Define_DropDownArray_4_BELEGTYP() throws myException {
		String[][] cDefArray = new String[1][2];
		cDefArray[0][0] = "-";		cDefArray[0][1] = "";    

		return cDefArray;
	}

	@Override
	public void add_SpecialListComponents(SQLFieldMAP oSQLFieldMapDruck, E2_ComponentMAP oComponentMAPDruck) throws myException {
		MyE2_DB_Label			lbl_Uhrzeit = new MyE2_DB_Label(oSQLFieldMapDruck.get_SQLField("UHRZEIT"));
		MyE2_DB_Label			lbl_DruckFormular = new MyE2_DB_Label(oSQLFieldMapDruck.get_SQLField("FORMULAR"));
		
		oComponentMAPDruck.add_Component(lbl_Uhrzeit, new MyE2_String("Uhrzeit"));
		oComponentMAPDruck.add_Component(lbl_DruckFormular, new MyE2_String("Formular"));
	}

	@Override
	public E2_ComponentMAP manipulate_SpecialListComponentMAP(E2_ComponentMAP oMAP_List) throws myException {
		
		Vector<String>  vSort = new Vector<String>();
		vSort.add(XXX_PrintProtokoll.HASH_BUTTON_DELETE);
		vSort.add(XXX_PrintProtokoll.HASH_SPECIALFIELD_DRUCKDATZEIT);
		//vSort.add("UHRZEIT");
		vSort.add("FORMULAR");
		vSort.add(XXX_PrintProtokoll.HASH_SPECIALFIELD_USERKUERZEL);
		vSort.add(XXX_PrintProtokoll.HASH_BASEFIELD_MAIL);
		vSort.add(XXX_PrintProtokoll.HASH_EMAIL_ANZEIGE_GRID);
 		
		E2_ComponentMAP_TOOLS.NewColumnsOrder(oMAP_List, vSort);
		E2_ComponentMAP_TOOLS.HideAllColums_WhichAreNotInVector(oMAP_List, vSort);
		
		
		oMAP_List.get__Comp(XXX_PrintProtokoll.HASH_SPECIALFIELD_DRUCKDATZEIT).EXT().set_oColExtent(new Extent(150));
		oMAP_List.get__Comp("UHRZEIT").EXT().set_oColExtent(new Extent(150));
		oMAP_List.get__Comp("FORMULAR").EXT().set_oColExtent(new Extent(200));
		oMAP_List.get__Comp(XXX_PrintProtokoll.HASH_SPECIALFIELD_USERKUERZEL).EXT().set_oColExtent(new Extent(80));
		
		
		return oMAP_List;
	}

	@Override
	public String[][] query_MailAdresses4Protokoll_Record(String cID_RECORD_PROTOKOLL) throws myException {
		
		return bibDB.EinzelAbfrageInArray("SELECT "+_DB.FBAM_DRUCK_EM$EMAIL_SEND+","
														+_DB.FBAM_DRUCK_EM$EMAIL_RECEIVE
														+" FROM "+bibE2.cTO()+"."+_DB.FBAM_DRUCK_EM+" WHERE "
														+_DB.FBAM_DRUCK_EM$ID_FBAM_DRUCK+"="+cID_RECORD_PROTOKOLL,"-");
	}
	
}


//public class BAMB_Daughter_PrintProtokoll extends MyE2_DBC_DaughterTable
//{
//	
//	public BAMB_Daughter_PrintProtokoll(SQLFieldMAP 			fieldMAP, 
//										E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
//	{
//		super();
//		
//		
//		/*
//		 * tochtertabelle fuer das druckprotokoll
//		 */
//		SQLFieldMAP oFieldMapDruck = new SQLFieldMAP("JT_FBAM_DRUCK",bibE2.get_CurrSession());
//		oFieldMapDruck.addCompleteTable_FIELDLIST("JT_FBAM_DRUCK",":DRUCKDATUM:UHRZEIT:AUSGEFUEHRT_VON:FORMULAR:",true,true,"");
//		oFieldMapDruck.add_SQLField(new SQLFieldForPrimaryKey("JT_FBAM_DRUCK","ID_FBAM_DRUCK","ID_FBAM_DRUCK",new MyE2_String("ID"),bibE2.get_CurrSession(),"SELECT SEQ_FBAM_DRUCK.NEXTVAL FROM DUAL",true), false);
//		oFieldMapDruck.add_SQLField(new SQLFieldJoinOutside("JT_FBAM_DRUCK","ID_FBAM","ID_FBAM",
//											new MyE2_String("ID-Bam"),false,bibE2.get_CurrSession(),fieldMAP.get_SQLField("ID_FBAM")), false);
//		oFieldMapDruck.initFields();
//		
//		E2_ComponentMAP oMapDruck = new E2_ComponentMAP(oFieldMapDruck);
//		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
//		MyE2_DB_TextField			oTF_Druckdatum = new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("DRUCKDATUM"));
//		MyE2_DB_TextField			oTF_Uhrzeit = new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("UHRZEIT"));
//		MyE2_DB_TextField			oTF_DruckFormular = new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("FORMULAR"));
//		MyE2_DB_TextField			oTF_Ausfuehrender = new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("AUSGEFUEHRT_VON"));
//		
//		oTF_Druckdatum.EXT_DB().set_bIsSortable(false);
//		oTF_Uhrzeit.EXT_DB().set_bIsSortable(false);
//		oTF_Ausfuehrender.EXT_DB().set_bIsSortable(false);
//		oTF_DruckFormular.EXT_DB().set_bIsSortable(false);
//		
//		oMapDruck.add_Component(BAMB_MASK_ModulContainer.FIELDNAME_DEL_BUTTON_DAUGHTERTABLE_PRINTPROTOKOLL,oButtonForDel,new MyE2_String("?"));
//		oMapDruck.add_Component(oTF_Druckdatum,new MyE2_String("Druckdatum"));
//		oMapDruck.add_Component(oTF_Uhrzeit,new MyE2_String("Uhrzeit"));
//		oMapDruck.add_Component(oTF_DruckFormular,new MyE2_String("Formular"));
//		oMapDruck.add_Component(oTF_Ausfuehrender,new MyE2_String("Ausgeführt von"));
//		
//		
//		/*
//		 * neueingabe-button definieren
//		 */
//		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
//		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
//		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
//		this.get_vComponentForDifferentTasks().add(oButtonNEW);
//		
//		this.set_oContainerExScrollHeight(new Extent(460));
//		
//		this.INIT_DAUGHTER(	fieldMAP.get_oSQLFieldPKMainTable(),
//											ocomponentMAP_from_Mother,
//											oMapDruck,
//											null);
//			
//		this.EXT().set_bDisabledFromBasic(true);
//		
//	}
//	
//	
//	
//}
