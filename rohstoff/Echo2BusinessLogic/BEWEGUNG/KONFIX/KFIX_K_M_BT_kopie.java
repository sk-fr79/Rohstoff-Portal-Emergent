package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_Date_von_bis_POPUP_OWN;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.valid_KopiereNurBelegeMitAktiveAdressen;
import rohstoff.utils.VorgangTableNames;

public class KFIX_K_M_BT_kopie extends MyE2_Button
{
	private MyE2_CheckBox  		oCB_MengenKopieren 	= new MyE2_CheckBox(new MyE2_String("Mengen kopieren")); 
	
	private btSetDateRange 		btSetzeMonat 		= new btSetDateRange(true);
	private btSetDateRange 		btSetzeMonat_plus_1 = new btSetDateRange(false);

	private E2_NavigationList   oNaviList 			= null;
	private String				oMaskContainer 		= "";

	private ownDateVonBis  		f_ownDateVonBis 	= new ownDateVonBis();

	private String 				oBelegTyp 			= "";

	public KFIX_K_M_BT_kopie(E2_NavigationList onavigationList,String omaskContainerIdentifier) throws myException
	{
		super(E2_ResourceIcon.get_RI("copy.png"), E2_ResourceIcon.get_RI("empty.png"));

		this.oNaviList = onavigationList;
		this.oMaskContainer = omaskContainerIdentifier;
		
		if(this.oMaskContainer.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_EK_KONTRAKT_LIST_NG.get_callKey())){
			oBelegTyp= myCONST.VORGANGSART_EK_KONTRAKT;
		}else if(this.oMaskContainer.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_VK_KONTRAKT_LIST_NG.get_callKey())){
			oBelegTyp= myCONST.VORGANGSART_VK_KONTRAKT;
		}

		this.add_GlobalValidator(new KFIX_K_M_Selection_Validator(oNaviList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oMaskContainer,"KOPIEREN_KONTRAKT"));
		this.setToolTipText(new MyE2_String("Kopieren eines (oder mehrerer) Kontrakte").CTrans());

		this.add_IDValidator(new ownValidator());

		this.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException 	{
				if (KFIX_K_M_BT_kopie.this.oNaviList.get_vSelectedIDs_Unformated().size()==0)	{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte wählen Sie mindestens einen Kontrakt aus !"));
					return;
				}

				bibMSG.add_MESSAGE(KFIX_K_M_BT_kopie.this.valid_IDValidation(KFIX_K_M_BT_kopie.this.oNaviList.get_vSelectedIDs_Unformated()));

				if (bibMSG.get_bIsOK())	{
					new ownPopup();
				}
			}
		});
	}


	//aenderung 2010-12-22: keine kopie von inaktiven adressen
	private class ownValidator extends valid_KopiereNurBelegeMitAktiveAdressen {

		@Override
		public VectorSingle SammleAdressIDs(String cID_BelegToCopy)		throws myException	{
			VectorSingle vRueck = new VectorSingle();

			RECORD_VKOPF_KON  recVkopf = new RECORD_VKOPF_KON(cID_BelegToCopy);

			vRueck.add(recVkopf.get_ID_ADRESSE_cUF());

			return vRueck;
		}

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)		throws myException	{
			return null;
		}
	}




	private class ownPopup extends E2_BasicModuleContainer {

		private MyE2_Button   		            	btStart = new MyE2_Button(new MyE2_String("START"));
		private MyE2_Button   		            	btAbbruch = new MyE2_Button(new MyE2_String("Abbruch"));


		public ownPopup() throws myException {
			super();

			this.add(buildGeneralGrid(), E2_INSETS.I_10_10_10_10);
			this.add(new RB_lab(E2_ResourceIcon.get_RI("empty.png")));

			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(400), new MyE2_String("Kontrakte kopieren ..."));

			this.btAbbruch.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});

			this.btStart.add_oActionAgent(new actionCopySTART());

		}

		private E2_Grid buildGeneralGrid() {
			KFIX_K_M_BT_kopie oThis = KFIX_K_M_BT_kopie.this;

			E2_Grid generalGrid = new E2_Grid()._s(2);

			oThis.oCB_MengenKopieren.setStyle(MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());

			generalGrid._gld(new RB_gld()._span(2)._ins(10, 5, 10, 5)._col(new E2_ColorDark()))
			._a_lm(new RB_lab("Kontrakte kopieren ")._b()._i()._fsa(1))//._set_style(MyE2_Label.STYLE_TITEL_BIG()))
			._gld(new RB_gld()._col(new E2_ColorBase())._span(2)._ins(10, 5, 10, 5))
			._a_lm(oThis.f_ownDateVonBis)
			._gld(new RB_gld()._span(1))
			._a_lm(oThis.oCB_MengenKopieren)
			._a_lm(new E2_ComponentGroupHorizontal(0,oThis.btSetzeMonat,oThis.btSetzeMonat_plus_1,E2_INSETS.I_0_0_20_0))
			._gld(new RB_gld()._span(2))
			._a_lm(new E2_ComponentGroupHorizontal(0,this.btStart,this.btAbbruch,E2_INSETS.I_0_0_20_0));

			return generalGrid;
		}

		private class actionCopySTART extends XX_ActionAgent
		{

			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				KFIX_K_M_BT_kopie oThis = KFIX_K_M_BT_kopie.this;

				//neue variante
				oThis.f_ownDateVonBis.show_InputStatus(true);  //voreinstellung
				bibMSG.add_MESSAGE(oThis.f_ownDateVonBis.checkInput());

				if (bibMSG.get_bHasAlarms())
					return;

				boolean bCopyMengen = oThis.oCB_MengenKopieren.isSelected();

				HashMap<String, String> hmErsatzkopf = new HashMap<String, String>();

				HashMap<String, String> hmErsatzPos_Zusatz = new HashMap<String, String>();

				//2015-08-20: von bis ueber ein kombifeld
				hmErsatzPos_Zusatz.put("GUELTIG_VON", "'"+oThis.f_ownDateVonBis.get_oDBFormatedDateFromTextFieldVon()+"'");
				hmErsatzPos_Zusatz.put("GUELTIG_BIS", "'"+oThis.f_ownDateVonBis.get_oDBFormatedDateFromTextFieldBis()+"'");

				HashMap<String, String> hmErsatzPos = new HashMap<String, String>();
				if (!bCopyMengen) hmErsatzPos.put("ANZAHL", "NULL");
				hmErsatzPos.put("BESTELLNUMMER","NULL");
				hmErsatzPos.put("EINZELPREIS","NULL");

				Vector<String> vIDsSelected = oThis.oNaviList.get_vSelectedIDs_Unformated();

				
				//2016-12-20: meldungen fuer kontrakte anzeigen
				for (String id: vIDsSelected) {
					RECORD_VKOPF_KON recKon = new RECORD_VKOPF_KON(id);
			    	new __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN(recKon.ufs(VKOPF_KON.id_adresse), recKon.ufs(VKOPF_KON.vorgang_typ)).ACTIVATE_MESSAGES();
				}
				//---------------------------------------------
				
				

				
				
				Vector<String> vAllSql = new Vector<String>();

				for (int i=0;i<vIDsSelected.size();i++)
				{
					KFIX_K__CopyVorgang oCopy = new KFIX_K__CopyVorgang(	
							(String)vIDsSelected.get(i),
							new VorgangTableNames(oThis.oBelegTyp),
							null,
							hmErsatzkopf ,
							hmErsatzPos , 
							hmErsatzPos_Zusatz);
					vAllSql.addAll(oCopy.get_vSQL_STACK_ForCopy());

				}

				String cQueryMaxID = "SELECT NVL(MAX("+VKOPF_KON.id_vkopf_kon.fieldName()+"),0) FROM "+
						bibE2.cTO()+"."+_TAB.vkopf_kon.fullTableName();


				String cLASTID = bibDB.EinzelAbfrage(cQueryMaxID);

				//zuerst pruefen, welcher der letzte id wert im kopfsatz war,
				//dann alle danach geschriebenen in die liste haengen, dann kann der stack am stueck verarbeitet werden
								bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vAllSql,true));
								if (bibMSG.get_bIsOK())
								{
									String cLASTID2 = bibDB.EinzelAbfrage(cQueryMaxID);
									try
									{
										long iLastID = new Long(cLASTID).longValue();
										long iLastID2 = new Long(cLASTID2).longValue();
										
										Vector<String> vNeue = new Vector<String>();
										Vector<String> vSQL_String_Buchungsnummern = new Vector<String>();   //direkt auch buchungsnummern vergeben
										for (long i=iLastID+1;i<=iLastID2;i++)
										{
											vNeue.add(""+i);
											
											vSQL_String_Buchungsnummern.add("UPDATE JT_VKOPF_KON SET "+get_SQL_UPDATE_Block_Fuer_Buchungsnummer()+" WHERE BUCHUNGSNUMMER IS NULL AND ID_VKOPF_KON="+i);
										}
										
										bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_String_Buchungsnummern, true));
										
										oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(vNeue);

										oNaviList._REBUILD_ACTUAL_SITE(true, true,vNeue);
										
										ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
										
										bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Kopie war erfolgreich!")));
										
									}
									catch (Exception ex) {}
								}
								else
								{
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Kopieren ..."));
				
								}

			}

		}

		private String get_SQL_UPDATE_Block_Fuer_Buchungsnummer() throws myException{
			KFIX_K_M_BT_kopie oThis = KFIX_K_M_BT_kopie.this;

			String buchungNrVorsatz = "";

			String belegTyp = oThis.oBelegTyp;

			if(oThis.oBelegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)){
				buchungNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_EKK_cUF_NN("");
			}else if(oThis.oBelegTyp.equals(myCONST.VORGANGSART_VK_KONTRAKT)){
				buchungNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_VKK_cUF_NN("");
			}

			if (S.isFull(buchungNrVorsatz))
			{
				buchungNrVorsatz = bibALL.MakeSql(buchungNrVorsatz);
			}

			String cSQL_Block = " BUCHUNGSNUMMER="+(S.isFull(buchungNrVorsatz)?buchungNrVorsatz+"||":"")+"TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_"+belegTyp+".NEXTVAL) ";

			return cSQL_Block;


		}



	}








	private class btSetDateRange extends MyE2_Button
	{

		private boolean bAktuellerMonat = true;

		/*
		 * liefert entweder den aktuellen oder den naechsten monate von anfang bis ende in die maskenfelder
		 */
		public btSetDateRange(boolean AktuellerMonat)
		{
			super("",MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
			this.bAktuellerMonat = AktuellerMonat;

			GregorianCalendar oCal = new GregorianCalendar();

			if (!this.bAktuellerMonat)
				oCal = myDateHelper.Find_First_Day_NextMonth(oCal);

			try
			{
				myDateHelper oDateHelper = new myDateHelper(oCal);

				String cButtonText = oDateHelper.get_cDateFormatForMask().substring(3);
				this.set_Text(cButtonText);
				this.EXT().set_O_PLACE_FOR_EVERYTHING(oCal);

				this.add_oActionAgent(new ownActionAgent());

			}
			catch (myException ex)
			{
				this.set_Text("@@@ERROR@@@");
			}

		}



		private class ownActionAgent extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				MyE2_Button oButton = (MyE2_Button) bibE2.get_LAST_ACTIONEVENT().getSource();

				GregorianCalendar oCal = (GregorianCalendar)oButton.EXT().get_O_PLACE_FOR_EVERYTHING();

				try
				{

					GregorianCalendar oDateStart = myDateHelper.Find_First_Day_OfMonth(oCal);;
					GregorianCalendar oDateEnd = myDateHelper.Find_Last_Day_OfMonth(oCal);;

					myDateHelper oHelpStart = new myDateHelper(oDateStart);
					myDateHelper oHelpEnd = new myDateHelper(oDateEnd);

					KFIX_K_M_BT_kopie.this.f_ownDateVonBis.get_oTextFieldVon().setText(oHelpStart.get_cDateFormatForMask());
					KFIX_K_M_BT_kopie.this.f_ownDateVonBis.get_oTextFieldBis().setText(oHelpEnd.get_cDateFormatForMask());

				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error BSK_P_MaskButton_SetDateRange:ownactionagent: "));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}



	}



	private class ownDateVonBis extends MyE2_TextField_Date_von_bis_POPUP_OWN {

		public ownDateVonBis() throws myException {
			super();
		}

		@Override
		public void Ordne_Komponenten_An(	MyE2_TextField 	oTextFieldVon, 	
				MyE2_TextField 	oTextFieldBis, 
				MyE2_Button 	oButtonCalendar,
				MyE2_Button 	oButtonEraserVon, 
				MyE2_Button 	oButtonEraserBis) throws myException {

			this.setSize(7);
			this.setColumnWidth(0, new Extent(50));
			this.add(new MyE2_Label(new MyE2_String("Gültigkeit: "),new E2_FontPlain(-2)),E2_INSETS.I(0, 0, 2, 0));
			this.add(oTextFieldVon,E2_INSETS.I(0, 0, 1, 0));
			this.add(oButtonEraserVon,E2_INSETS.I(0, 0, 5, 0));
			this.add(new MyE2_Label(new MyE2_String("bis"),new E2_FontPlain(-2)),E2_INSETS.I(0, 0, 5, 0));
			this.add(oTextFieldBis,E2_INSETS.I(0, 0, 1, 0));
			this.add(oButtonEraserBis,E2_INSETS.I(0,0,5,0));
			this.add(oButtonCalendar,E2_INSETS.I(0,0,1,0));

			this.set_bAutoCloseOnBisCalendar(true);

		}



		public MyE2_MessageVector checkInput() throws myException {
			MyE2_MessageVector  mv = new MyE2_MessageVector();

			this.show_InputStatus(true);

			MyDate start = this.get_oDateFromTextFieldVon();
			MyDate ende = this.get_oDateFromTextFieldBis();

			if (start != null && ende != null) {
				if (myDateHelper.get_Date1_Greater_Date2(start.get_cDateStandardFormat(), ende.get_cDateStandardFormat())) {
					mv.add(new MyE2_Alarm_Message(new MyE2_String("Bitte sorgen Sie dafür, daß das Startdatum vor dem Enddatum liegt !")));
					this.show_InputStatus(false);
				}
			} else {
				if (start==null) {
					mv.add(new MyE2_Alarm_Message(new MyE2_String("Bitte beim Startdatum einen korrekten Datumswert erfassen !")));
					this.show_InputStatusStart(false);
				} 

				if (ende==null) {
					mv.add(new MyE2_Alarm_Message(new MyE2_String("Bitte beim Enddatum einen korrekten Datumswert erfassen !")));
					this.show_InputStatusEnd(false);
				} 
			}

			return mv;
		}


	}


}
