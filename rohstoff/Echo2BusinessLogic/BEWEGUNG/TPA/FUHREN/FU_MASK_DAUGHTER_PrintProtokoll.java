package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.XXX_PrintProtokoll;



//public class FU_MASK_DAUGHTER_PrintProtokoll extends MyE2_DBC_DaughterTable
public class FU_MASK_DAUGHTER_PrintProtokoll extends XXX_PrintProtokoll
{

	public FU_MASK_DAUGHTER_PrintProtokoll(	SQLFieldMAP 			SQL_fieldMAP_FromFuhre, 
											E2_ComponentMAP			ocomponentMAP_from_Fuhre) throws myException {
		super();
		
		this.init_PrintProtokoll(SQL_fieldMAP_FromFuhre, ocomponentMAP_from_Fuhre, "VPOS_TPA_FUHRE", new Extent(98,Extent.PERCENT), new Extent(130), true);
		
	}

	@Override
	public String[][] Define_DropDownArray_4_BELEGTYP() throws myException {
		String[][] cDefArray = new String[9][2];
		cDefArray[0][0] = "-";											cDefArray[0][1] = "";    
		cDefArray[1][0] = myCONST.FUHRE_BELEG_LIEFERSCHEIN_USER;		cDefArray[1][1] = myCONST.FUHRE_BELEG_LIEFERSCHEIN;    
		cDefArray[2][0] = myCONST.FUHRE_BELEG_LIEFERSCHEIN_MULTI_USER;	cDefArray[2][1] = myCONST.FUHRE_BELEG_LIEFERSCHEIN_MULTI;    
		cDefArray[3][0] = myCONST.FUHRE_BELEG_LADESCHEIN_USER;			cDefArray[3][1] = myCONST.FUHRE_BELEG_LADESCHEIN;    
		cDefArray[4][0] = myCONST.FUHRE_BELEG_LIEFERAVIS_USER;			cDefArray[4][1] = myCONST.FUHRE_BELEG_LIEFERAVIS;    
		cDefArray[5][0] = myCONST.FUHRE_BELEG_ABHOLAVIS_USER;			cDefArray[5][1] = myCONST.FUHRE_BELEG_ABHOLAVIS;    
		cDefArray[6][0] = myCONST.FUHRE_BELEG_CMR_USER;					cDefArray[6][1] = myCONST.FUHRE_BELEG_CMR;
		cDefArray[7][0] = myCONST.FUHRE_BELEG_ABHOLNACHRICHT;			cDefArray[7][1] = myCONST.FUHRE_BELEG_ABHOLNACHRICHT;    
		cDefArray[8][0] = myCONST.FUHRE_BELEG_EU_BLAETTER;				cDefArray[8][1] = myCONST.FUHRE_BELEG_EU_BLAETTER;

		return cDefArray;
	}

	@Override
	public void add_SpecialListComponents(SQLFieldMAP oSQLFieldMapDruck, E2_ComponentMAP oMAP_List) throws myException {
	}

	@Override
	public E2_ComponentMAP manipulate_SpecialListComponentMAP(E2_ComponentMAP oMAP_List) throws myException {
		return oMAP_List;
	}
	
	
	@Override
	public String[][] query_MailAdresses4Protokoll_Record(String cID_RECORD_PROTOKOLL) throws myException {
		
		return bibDB.EinzelAbfrageInArray("SELECT "+_DB.VPOS_TPA_FUHRE_DRUCK_EM$EMAIL_SEND+","
														+_DB.VPOS_TPA_FUHRE_DRUCK_EM$EMAIL_RECEIVE
														+" FROM "+bibE2.cTO()+"."+_DB.VPOS_TPA_FUHRE_DRUCK_EM+" WHERE "
														+_DB.VPOS_TPA_FUHRE_DRUCK_EM$ID_VPOS_TPA_FUHRE_DRUCK+"="+cID_RECORD_PROTOKOLL,"-");
	}

	
//	public FU_MASK_DAUGHTER_PrintProtokoll(	SQLFieldMAP 			SQL_fieldMAP_FromFuhre, 
//											E2_ComponentMAP			ocomponentMAP_from_Fuhre) throws myException
//	{
//		super();
//		
//		/*
//		 * tochtertabelle fuer das druckprotokoll
//		 */
//		Project_SQLFieldMAP          oFieldMapDruck = new Project_SQLFieldMAP("JT_VPOS_TPA_FUHRE_DRUCK",":ID_VPOS_TPA_FUHRE:",false);
//		this.set_bDaughterIsPassive(true);
//		
//		oFieldMapDruck.add_SQLField(new SQLFieldJoinOutside("JT_VPOS_TPA_FUHRE_DRUCK","ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE",
//									new MyE2_String("ID-VKopf"),false,bibE2.get_CurrSession(),SQL_fieldMAP_FromFuhre.get_SQLField("ID_VPOS_TPA_FUHRE")), false);
//		
//		oFieldMapDruck.add_SQLField(new SQLField("TO_CHAR(DRUCKDATUM,'dd.mm.yyyy HH24:MI')","DRUCKDATZEIT",new MyE2_String("DRUCKDATZEIT"),bibE2.get_CurrSession()), false);
//		
//		oFieldMapDruck.initFields();
//
//		
//		//fuer den (uebersetzbaren) beleg-dropdown
//		String[][] cDefArray = new String[9][2];
//		cDefArray[0][0] = "-";											cDefArray[0][1] = "";    
//		cDefArray[1][0] = myCONST.FUHRE_BELEG_LIEFERSCHEIN_USER;		cDefArray[1][1] = myCONST.FUHRE_BELEG_LIEFERSCHEIN;    
//		cDefArray[2][0] = myCONST.FUHRE_BELEG_LIEFERSCHEIN_MULTI_USER;	cDefArray[2][1] = myCONST.FUHRE_BELEG_LIEFERSCHEIN_MULTI;    
//		cDefArray[3][0] = myCONST.FUHRE_BELEG_LADESCHEIN_USER;			cDefArray[3][1] = myCONST.FUHRE_BELEG_LADESCHEIN;    
//		cDefArray[4][0] = myCONST.FUHRE_BELEG_LIEFERAVIS_USER;			cDefArray[4][1] = myCONST.FUHRE_BELEG_LIEFERAVIS;    
//		cDefArray[5][0] = myCONST.FUHRE_BELEG_ABHOLAVIS_USER;			cDefArray[5][1] = myCONST.FUHRE_BELEG_ABHOLAVIS;    
//		cDefArray[6][0] = myCONST.FUHRE_BELEG_CMR_USER;					cDefArray[6][1] = myCONST.FUHRE_BELEG_CMR;
//		cDefArray[7][0] = myCONST.FUHRE_BELEG_ABHOLNACHRICHT;			cDefArray[7][1] = myCONST.FUHRE_BELEG_ABHOLNACHRICHT;    
//		cDefArray[8][0] = myCONST.FUHRE_BELEG_EU_BLAETTER;				cDefArray[8][1] = myCONST.FUHRE_BELEG_EU_BLAETTER;
//		
//		
//		E2_ComponentMAP oMapDruck = new E2_ComponentMAP(oFieldMapDruck);
//		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
//		MyE2_DB_SelectField    		oSelBelegTyp = 		new MyE2_DB_SelectField(oFieldMapDruck.get_SQLField("BELEG"),cDefArray,true);
//		MyE2_DB_Label				oTF_Druckdatum = 	new MyE2_DB_Label(oFieldMapDruck.get_SQLField("DRUCKDATZEIT"));
//		MyE2_DB_TextField			oTF_Kuerzel = 		new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("KUERZEL"));
//		MyE2_DB_TextField			oTF_Zeitstempel = 	new MyE2_DB_TextField(oFieldMapDruck.get_SQLField("ZEITSTEMPEL"));
//		MyE2_DB_Label				oLabelID = 			new MyE2_DB_Label(oFieldMapDruck.get_SQLField("ID_VPOS_TPA_FUHRE_DRUCK"));
//		MyE2_DB_Label				oLabelInfo = 		new MyE2_DB_Label(oFieldMapDruck.get_SQLField(_DB.VPOS_TPA_FUHRE_DRUCK$BELEGINFO));
//		MyE2_DB_CheckBox 			cbMail = 			new MyE2_DB_CheckBox(oFieldMapDruck.get_SQLField("MAIL"));
//		
//		oMapDruck.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
//		
//		
//		
//		oMapDruck.add_Component(oSelBelegTyp,new MyE2_String("Beleg"));
//		oMapDruck.add_Component(FU___CONST.HASH_BUTTON_DOWNLOAD_ARCHIV, new BS_BUTTON_DownloadDruckbelege(), new MyE2_String("Lade"));
//		oMapDruck.add_Component(oTF_Druckdatum,new MyE2_String("Druckdat."));
//		oMapDruck.add_Component(oTF_Kuerzel,new MyE2_String("Wer ?"));
//		oMapDruck.add_Component(oTF_Zeitstempel,new MyE2_String("Masken-Zeitstempel"));
//		oMapDruck.add_Component(cbMail,new MyE2_String("Mail"));
//		oMapDruck.add_Component(oLabelInfo,new MyE2_String("Info"));
//		oMapDruck.add_Component(oLabelID,new MyE2_String("ID"));
//
//
//		oTF_Kuerzel.set_iWidthPixel(100);
//		oTF_Zeitstempel.set_iWidthPixel(120);
//
//		oSelBelegTyp.EXT().set_oColExtent(new Extent(200));
//		oTF_Druckdatum.EXT().set_oColExtent(new Extent(150));
//		oTF_Kuerzel.EXT().set_oColExtent(new Extent(100));
//		oTF_Zeitstempel.EXT().set_oColExtent(new Extent(120));
//		cbMail.EXT().set_oColExtent(new Extent(50));
//
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
//		this.set_oContainerExScrollHeight(new Extent(130));
//		
//		this.INIT_DAUGHTER(	SQL_fieldMAP_FromFuhre.get_oSQLFieldPKMainTable(),
//											ocomponentMAP_from_Fuhre,
//											oMapDruck,
//											null);
//			
//		this.EXT().set_bDisabledFromBasic(true);
//	}
	
}
