package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_PreisEingabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Combo_Transportmittel;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_FUS_BUTTON_New_fast_and_furious;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_FUS_Grid_InnereErfassungsMaske;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Grid_ErfassteFuhren;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputAnzahlContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum2;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputLKW;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputLKW_Anhaenger;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_PreisEingabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAngebotsFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchKontraktFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchSortenFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_WiegeMengeTextField;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Wiegeschein;

public class FUS_BT_ADD_TO_LIST extends MyE2_Button
{

	public FUS_BT_ADD_TO_LIST()
	{
		super(new MyE2_String("Zur Merkliste hinzufügen"), MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
		
		this.add_oActionAgent(new actionAddToList());
		this.add_GlobalValidator(new ownValidator());
		this.add_GlobalValidator(new validVerkaufNurMitKontrakt());
	}

	
	private class actionAddToList extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FUS_Grid_ErfassteFuhren 			oFUS_Grid_ErfassteFuhren = new _SEARCH_Grid_ErfassteFuhren().get_Found_FUS_Grid_ErfassteFuhren();
			FUS_Grid_InnereErfassungsMaske 		oFUS_InnereErfassungsMaske = new _SEARCH_FUS_Grid_InnereErfassungsMaske().get_Found_FUS_Grid_InnereErfassungsMaske();
			
			oFUS_Grid_ErfassteFuhren.add_Fuhre_to_List(
					new FUS_FuhreRepraesentantInListe(
							new _SEARCH_SearchAdressFields().get_Found_EK_AdressField().get_ActualRecAdresse(), 
							new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().get_ActualRecAdresse(), 
							new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().get_ActualRecArtBez(), 
							new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().get_ActualRecArtBez(),
							new _SEARCH_SearchKontraktFields().get_Found_EK_KontraktField().get_Actual_RECORD_VPOS_KON(),
							new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField().get_Actual_RECORD_VPOS_KON(),
							new _SEARCH_SearchAngebotsFields().get_Found_EK_AngebotField().get_Actual_RECORD_VPOS_STD(),
							new _SEARCH_SearchAngebotsFields().get_Found_VK_AngebotField().get_Actual_RECORD_VPOS_STD(),
							new _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oDateFromTextField(), 
							(oFUS_InnereErfassungsMaske.get_bVarianteFahrplan()?new _SEARCH_InputDatum2().get_Found_FUS_InputDatum2().get_oDateFromTextField():null),
							new MyBigDecimal(new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().getText()),
							new _SEARCH_Combo_Transportmittel().get_found_ComboBox().get_oTextField().getText(),
							new _SEARCH_PreisEingabe().get_found_PreisFeld_EK().get_bd_Preis(),
							new _SEARCH_PreisEingabe().get_found_PreisFeld_VK().get_bd_Preis(),
							S.NN(new _SEARCH_InputLKW().get_Found_FUS_InputLKW().getText()),
							S.NN(new _SEARCH_InputLKW_Anhaenger().get_Found_FUS_InputLKW_Anhaenger().getText()),
							S.NN(new _SEARCH_Wiegeschein().get_found_Wiegeschein_EK().getText()),
							S.NN(new _SEARCH_Wiegeschein().get_found_Wiegeschein_VK().getText()),
							(oFUS_InnereErfassungsMaske.get_bVarianteFahrplan()?new MyBigDecimal(new _SEARCH_InputAnzahlContainer().get_Found_FUS_InputAnzahlContainer().getText()):null),
							oFUS_Grid_ErfassteFuhren));
			
			//jetzt das gedaechtnis des FUS_Button_New_Fast ...  fuellen
			MyDate dateTest = new _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oActualDate();
			if (dateTest !=null &&  dateTest.get_cErrorCODE().equals(MyDate.ALL_OK))
			{
				new _SEARCH_FUS_BUTTON_New_fast_and_furious().get_FoundButton().set_MEM_LastUsedDate(dateTest.get_cDateStandardFormat());
			}

			String cTrapo = new _SEARCH_Combo_Transportmittel().get_found_ComboBox().get_oTextField().getText();
			if (S.isFull(cTrapo))
			{
				new _SEARCH_FUS_BUTTON_New_fast_and_furious().get_FoundButton().set_MEM_LastUsedTransportmittel(cTrapo);
			}
			
			// oben das meiste loeschen
			new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().clean__Field();
			new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().clean__Field();
			new _SEARCH_SearchKontraktFields().get_Found_EK_KontraktField().clean__Field();
			new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField().clean__Field();
			new _SEARCH_SearchAngebotsFields().get_Found_EK_AngebotField().clean__Field();
			new _SEARCH_SearchAngebotsFields().get_Found_VK_AngebotField().clean__Field();
			new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().clean__Field();
			
			FUS_PreisEingabe PreisEK = new _SEARCH_PreisEingabe().get_found_PreisFeld_EK();
			FUS_PreisEingabe PreisVK = new _SEARCH_PreisEingabe().get_found_PreisFeld_VK();
			
			PreisEK.clean__Field();
			PreisVK.clean__Field();
			
			//die adressen bleiben stehen, d.h. jetzt pruefen, was in den preisfeldern steht und ob diese offen oder zu sind
			RECORD_ADRESSE  recLinks = new _SEARCH_SearchAdressFields().get_Found_EK_AdressField().get_ActualRecHauptAdresse();
			RECORD_ADRESSE  recRechts = new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().get_ActualRecHauptAdresse();

			if (recLinks.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1)")))
			{
				PreisEK.set_bEnabled_For_Edit(false);
				PreisEK.setText("0");
			}
			else
			{
				PreisEK.set_bEnabled_For_Edit(true);
			}
					
			if (recRechts.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1)")))
			{
				PreisVK.set_bEnabled_For_Edit(false);
				PreisVK.setText("0");
			}
			else
			{
				PreisVK.set_bEnabled_For_Edit(true);
			}
			
			
			//ebenfalls loeschen: wiegekarten und kennzeichen
			new _SEARCH_Wiegeschein().get_found_Wiegeschein(true).clean__Field();
			new _SEARCH_Wiegeschein().get_found_Wiegeschein(false).clean__Field();
			
			
			
			
		}
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (!(new _SEARCH_SearchAdressFields().get_Found_EK_AdressField().get_bIsCorrectFilled(true)))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_SearchAdressFields().get_Found_EK_AdressField().get_InfoMessage()));
			}
			if (!(new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().get_bIsCorrectFilled(true)))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().get_InfoMessage()));
			}
			
			if (!(new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().get_bIsCorrectFilled(true)))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().get_InfoMessage()));
			}
			if (!(new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().get_bIsCorrectFilled(true)))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().get_InfoMessage()));
			}
			
			if (!(new _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_bIsCorrectFilled(true)))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().get_InfoMessage()));
			}
			
			if (!(new _SEARCH_Combo_Transportmittel().get_found_ComboBox().get_bIsCorrectFilled(true)))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_Combo_Transportmittel().get_found_ComboBox().get_InfoMessage()));
			}

			if (S.isFull(new _SEARCH_PreisEingabe().get_found_PreisFeld_EK().getText()))
			{
				if (!(new _SEARCH_PreisEingabe().get_found_PreisFeld_EK().get_bIsCorrectFilled(true)))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_PreisEingabe().get_found_PreisFeld_EK().get_InfoMessage()));
				}
			}
			
			if (S.isFull(new _SEARCH_PreisEingabe().get_found_PreisFeld_VK().getText()))
			{
				if (!(new _SEARCH_PreisEingabe().get_found_PreisFeld_VK().get_bIsCorrectFilled(true)))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_PreisEingabe().get_found_PreisFeld_VK().get_InfoMessage()));
				}
			}

			
			if (new _Check_ob_fahrplan().get_bIsFahrPlan())           //variante fahrplan hat weitere felder und wiegemenge kann null sein
			{
				if (S.isFull(new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().getText()))
				{
					if (!(new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().get_bIsCorrectFilled(true)))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().get_InfoMessage()));
					}
				}
				
				if (!(new _SEARCH_InputAnzahlContainer().get_Found_FUS_InputAnzahlContainer().get_bIsCorrectFilled(true)))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_InputAnzahlContainer().get_Found_FUS_InputAnzahlContainer().get_InfoMessage()));
				}
		
				if (!(new _SEARCH_InputDatum2().get_Found_FUS_InputDatum2().get_bIsCorrectFilled(true)))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_InputDatum2().get_Found_FUS_InputDatum2().get_InfoMessage()));
				}
				
			}
			else
			{
				if (!(new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().get_bIsCorrectFilled(true)))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().get_InfoMessage()));
				}


			}
			
			
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}
	}
	
	
	private class validVerkaufNurMitKontrakt extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();

			if (!(new __FUS_Check_ob_VK_Mit_Kontrakt().get_bOK()))
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message("Rohstoffsorten dürfen nur mit einem Kontrakt verkauft werden !!"));
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{

			return null;
		}
		
	}
	
}
