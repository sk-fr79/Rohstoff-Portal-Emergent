package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_TOOLS;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.XXX_PrintProtokoll;


//2014-09-24: umstellung auf standard-protokoll
public class BS_K_Daughter_PrintProtokoll extends XXX_PrintProtokoll
{

	public BS_K_Daughter_PrintProtokoll(	SQLFieldMAP 			fieldMAP, 
											E2_ComponentMAP			ocomponentMAP_from_Mother,
											BS__SETTING  			oSetting) throws myException {
		super();
		this.init_PrintProtokoll(	fieldMAP, 
									ocomponentMAP_from_Mother, 
									oSetting.get_oVorgangTableNames().get_cVKOPF_TAB().substring(3), 
									new Extent(98,Extent.PERCENT), new Extent(460), true);

		
	}

	@Override
	public String[][] Define_DropDownArray_4_BELEGTYP() throws myException {
		String[][] cDefArray = new String[1][2];
		cDefArray[0][0] = "-";		cDefArray[0][1] = "";    

		return cDefArray;
	}

	@Override
	public void add_SpecialListComponents(SQLFieldMAP oSQLFieldMapDruck, E2_ComponentMAP oMAP_List) throws myException {
		MyE2_DB_TextField			oTF_Positionen = 	new MyE2_DB_TextField(oSQLFieldMapDruck.get_SQLField("POSITIONEN"));
		MyE2_DB_TextField			oTF_GesamtNetto = 	new MyE2_DB_TextField(oSQLFieldMapDruck.get_SQLField("GESAMT_NETTO"));

		oMAP_List.add_Component(oTF_Positionen,new MyE2_String("Positionen"));
		oMAP_List.add_Component(oTF_GesamtNetto,new MyE2_String("Netto"));

	}

	@Override
	public E2_ComponentMAP manipulate_SpecialListComponentMAP(E2_ComponentMAP oMAP_List) throws myException {
		
		Vector<String> vSort = new Vector<String>();
		vSort.add(XXX_PrintProtokoll.HASH_BUTTON_DELETE);
		vSort.add(XXX_PrintProtokoll.HASH_BUTTON_DOWNLOAD_ARCHIV);
		vSort.add(XXX_PrintProtokoll.HASH_SPECIALFIELD_DRUCKDATZEIT);
		vSort.add("POSITIONEN");
		vSort.add("GESAMT_NETTO");
		vSort.add(XXX_PrintProtokoll.HASH_BASEFIELD_MAIL);
		vSort.add(XXX_PrintProtokoll.HASH_SPECIALFIELD_USERKUERZEL);
		vSort.add(this.get_IDName_PK_PrintProtokoll());

		oMAP_List.get__Comp(XXX_PrintProtokoll.HASH_BASEFIELD_BELEG).EXT().set_bIsVisibleInList(false);
		oMAP_List.get__Comp(XXX_PrintProtokoll.HASH_BASEFIELD_ZEITSTEMPEL).EXT().set_bIsVisibleInList(false);
		oMAP_List.get__Comp(XXX_PrintProtokoll.HASH_BASEFIELD_BELEGINFO).EXT().set_bIsVisibleInList(false);
		
	//	DEBUG.System_print(oMAP_List.get_vComponentHashKeys());
		
		E2_ComponentMAP_TOOLS.NewColumnsOrder(oMAP_List, vSort);
		
		
		return oMAP_List;
	}
	
	
	@Override
	public String[][] query_MailAdresses4Protokoll_Record(String cID_RECORD_PROTOKOLL) throws myException {
		
		return bibDB.EinzelAbfrageInArray("SELECT EMAIL_SEND, EMAIL_RECEIVE "
														+" FROM "+bibE2.cTO()+"."+this.get_cPrintProtokoll_eMailTable()+" WHERE "
														+this.get_cIDName_PK_PrintProtokoll()+"="+cID_RECORD_PROTOKOLL,"-");
	}

	
}

//public class BS_K_Daughter_PrintProtokoll extends MyE2_DBC_DaughterTable
//{
//	
//	public BS_K_Daughter_PrintProtokoll(	SQLFieldMAP 			fieldMAP, 
//											E2_ComponentMAP			ocomponentMAP_from_Mother,
//											String   				cHASHKEY_FOR_COMPONENTMAP,
//											BS__SETTING  			oSetting) throws myException
//	{
//		super();
//		
//		
//		/*
//		 * tochtertabelle fuer das druckprotokoll
//		 */
//		
//		
//		/*
//		 * zuerst die namensbestandteile der tabellen beschaffen
//		 */
//		String cHeadID = oSetting.get_oVorgangTableNames().get_cVKOPF_PK();
//		String cPrintTable = oSetting.get_oVorgangTableNames().get_cVKOPF_DRUCK_TAB();
//		String cPrintTableID = oSetting.get_oVorgangTableNames().get_cVKOPF_DRUCK_PK();
//		
//		
//		//ROHSTOFF_SQLFieldMAP oFieldMapDruck = new ROHSTOFF_SQLFieldMAP(cPrintTable,":"+cHeadID+":",false);
//		
//		SQLFieldMAP          oFieldMapDruck = new SQLFieldMAP(cPrintTable,bibE2.get_CurrSession());
//		String cAusnahmen = ":"+cPrintTableID+":"+cHeadID+":ID_MANDANT:LETZTE_AENDERUNG:";
//		oFieldMapDruck.addCompleteTable_FIELDLIST(cPrintTable,cAusnahmen,false,true, "");
//		
//		oFieldMapDruck.get_("GEAENDERT_VON").set_bWriteable(false);
//		this.set_bDaughterIsPassive(true);
//		
//		String cSeq_Name = oSetting.get_oVorgangTableNames().get_cVKOPF_DRUCK_SEQ();
//		
//		oFieldMapDruck.add_SQLField(new SQLFieldForPrimaryKey(cPrintTable,cPrintTableID,cPrintTableID,new MyE2_String("ID"),bibE2.get_CurrSession(),
//				"SELECT "+bibALL.get_TABLEOWNER()+"."+cSeq_Name+".NEXTVAL FROM DUAL",true),true);
//		
//		
//		oFieldMapDruck.add_SQLField(new SQLField("TO_CHAR(DRUCKDATUM,'dd.mm.yyyy HH24:MI')","DRUCKDATZEIT",new MyE2_String("DRUCKDATZEIT"),bibE2.get_CurrSession()), false);
//
//		
//		oFieldMapDruck.add_SQLField(new SQLFieldJoinOutside(cPrintTable,cHeadID,cHeadID,
//											new MyE2_String("ID-VKopf"),false,bibE2.get_CurrSession(),fieldMAP.get_SQLField(cHeadID)), false);
//		oFieldMapDruck.initFields();
//		
//		E2_ComponentMAP oMapDruck = new E2_ComponentMAP(oFieldMapDruck);
//		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
//		MyE2_DB_TextField			oTF_Druckdatum = 	new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("DRUCKDATZEIT"));
//		MyE2_DB_TextField			oTF_Positionen = 	new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("POSITIONEN"));
//		MyE2_DB_TextField			oTF_GesamtNetto = 	new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("GESAMT_NETTO"));
//		MyE2_DB_Label				oLabelID = 			new MyE2_DB_Label(oFieldMapDruck.get_SQLField(cPrintTableID));
//		MyE2_DB_Label				oLabelWho = 		new MyE2_DB_Label(oFieldMapDruck.get_SQLField("GEAENDERT_VON"));
//
//		
//		oMapDruck.add_Component(cHASHKEY_FOR_COMPONENTMAP,oButtonForDel,new MyE2_String("?"));
//		oMapDruck.add_Component(BS__CONST.HASH_BUTTON_DOWNLOAD_ARCHIV, new BS_BUTTON_DownloadDruckbelege(), new MyE2_String("Download"));
//		oMapDruck.add_Component(oTF_Druckdatum,new MyE2_String("Druckdatum"));
//		oMapDruck.add_Component(oTF_Positionen,new MyE2_String("Positionen"));
//		oMapDruck.add_Component(oTF_GesamtNetto,new MyE2_String("Netto"));
//		oMapDruck.add_Component(oLabelID,new MyE2_String("ID"));
//		oMapDruck.add_Component(oLabelWho,new MyE2_String("?"));
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
//		this.set_oContainerExScrollWidth(new Extent(98,Extent.PERCENT));
//		this.set_oContainerExScrollHeight(new Extent(460));
//		
//		this.INIT_DAUGHTER(	fieldMAP.get_oSQLFieldPKMainTable(),
//											ocomponentMAP_from_Mother,
//											oMapDruck,
//											null);
//			
//		this.EXT().set_bDisabledFromBasic(true);
//	}
//	
//}
