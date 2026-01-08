package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button_To_FastEdit;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForNumbers;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldWithSelectorForNumbers;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField_With_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__POPUP_Select_EINZELPREIS;
import rohstoff.utils.My_MWSTSaetze;


public class BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM extends  MyE2_DB_Button_To_FastEdit
{
	
	private UB_TextFieldForNumbers   			tfEINZELPREIS = 	new UB_TextFieldForNumbers("EINZELPREIS",2,true,"");
	private UB_TextFieldWithSelectorForNumbers  tfSteuersatz = 		new UB_TextFieldWithSelectorForNumbers("STEUERSATZ",true,"",2,null,"--?--");;
	private UB_TextField_With_DatePOPUP_OWN		tfLeistungsdatum = 	new UB_TextField_With_DatePOPUP_OWN("AUSFUEHRUNGSDATUM",true,"",80);
	
	private RECORD_VPOS_RG   		 recVPOS_RG = 			null;
	
	private VECTOR_UB_FIELDS   		vUBFields = new VECTOR_UB_FIELDS();
	
	public BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM(SQLField osqlField) throws myException
	{
		super(osqlField, new MyE2_String("Eingabe Preis und Steuersatz"), MyE2_Button.StyleTextButton(), "EINGABE_PREIS_MWST", new Extent(460), new Extent(280));
		this.vUBFields.add(this.tfEINZELPREIS);
		this.vUBFields.add(this.tfSteuersatz);
		this.vUBFields.add(this.tfLeistungsdatum);
		
		this.tfSteuersatz.get_oPopUp().get_oContainerEx().setWidth(new Extent(80));
		this.tfSteuersatz.get_oPopUp().get_oContainerEx().setHeight(new Extent(100));

		
		if 		(osqlField.get_cFieldLabel().equals("EINZELPREIS"))
		{
			this.tfEINZELPREIS.EXT().set_bHasFocusOnWindowPopup(true);
		}
		else if (osqlField.get_cFieldLabel().equals("STEUERSATZ"))
		{
			this.tfSteuersatz.EXT().set_bHasFocusOnWindowPopup(true);
		}
		else if (osqlField.get_cFieldLabel().equals("AUSFUEHRUNGSDATUM"))
		{
			this.tfLeistungsdatum.EXT().set_bHasFocusOnWindowPopup(true);
		}
		
		this.add_GlobalValidator(new ownValidator());
		
	}

	
	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM oThis = BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM.this;

			RECORD_VPOS_RG recVPOS = new RECORD_VPOS_RG(oThis.get_cActualRowID());

			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
			{
				if (recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Vorgang wurde bereits abgeschlossen !"));
				}
			}
			
			if (recVPOS.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position wurde bereits gelöscht !"));
			}
			
			if (S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")) || S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position ist Teil eines Stornozyklus !"));
			}

			return oMV;

		}

		@Override
		protected MyE2_MessageVector isValid(String ID_VPOS_RG)  	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			RECORD_VPOS_RG recVPOS = new RECORD_VPOS_RG(ID_VPOS_RG);
			
			if (recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
			{
				if (recVPOS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Vorgang wurde bereits abgeschlossen !"));
				}
			}
			
			if (recVPOS.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position wurde bereits gelöscht !"));
			}
			
			if (S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")) || S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position ist Teil eines Stornozyklus !"));
			}

			return oMV;
		}
		
	}



	@Override
	public E2_BasicModuleContainer create_BasicContainer4Popup()  throws myException
	{
		return new ownBasicModuleContainer();
	}

	
	
	public BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM get_Copy(Object o) throws  myExceptionCopy
	{
		BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM oRueck;
		try
		{
			oRueck = new BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM(this.get_oSQLField());
			return oRueck;
		} 
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy("Error Copy: "+e.getMessage());
		}
	}
	
	
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{

		public ownBasicModuleContainer() throws myException
		{
			super();
			BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM oThis = BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM.this;
			
			oThis.recVPOS_RG = new RECORD_VPOS_RG(oThis.get_cActualRowID());
			
			MyE2_Grid  oGridHelp = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			oGridHelp.add(new MyE2_Label(new MyE2_String("Einzelpreis:")),1, E2_INSETS.I_0_10_10_10);
			oGridHelp.add(oThis.tfEINZELPREIS,1, E2_INSETS.I_0_10_10_10);
			ownButtonStartSuchePreis oSuchePreis = new ownButtonStartSuchePreis();
			oGridHelp.add(oSuchePreis,1, E2_INSETS.I_0_10_10_10);
			
			if (bibALL.get_RECORD_MANDANT().is_ALLOW_EDIT_PRICE_IN_FUHREN_RG_NO())
			{
				oThis.tfEINZELPREIS.set_bEnabled_For_Edit(false);
				oSuchePreis.set_bEnabled_For_Edit(false);
			}

			
			oGridHelp.add(new MyE2_Label(new MyE2_String("Steuersatz:")),1, E2_INSETS.I_0_10_10_10);
			oGridHelp.add(oThis.tfSteuersatz, 2, E2_INSETS.I_0_10_10_10);
			
			oGridHelp.add(new MyE2_Label(new MyE2_String("Leistungsdatum:")),1, E2_INSETS.I_0_10_10_10);
			oGridHelp.add(oThis.tfLeistungsdatum,2, E2_INSETS.I_0_10_10_10);
			oGridHelp.add(new MyE2_Label(""),2, E2_INSETS.I_0_10_10_10);
			
			
			
			// jetzt die moeglichen mwst-saetze in das popup-fenster einbauen
			My_MWSTSaetze      oMWST = new My_MWSTSaetze(oThis.recVPOS_RG.get_ID_ADRESSE_cUF(),null);
			
			oThis.tfSteuersatz.set_Varianten(oMWST.get_MWST_DropDownArray_AdressMWST(false), "Keine Steuersätze", null);
			oThis.tfSteuersatz.set_StartValue(oThis.recVPOS_RG.get_STEUERSATZ_cF_NN(""));

			oThis.tfEINZELPREIS.set_StartValue(oThis.recVPOS_RG.get_EINZELPREIS_cF_NN(""));
			
			oThis.tfLeistungsdatum.set_StartValue(oThis.recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF_NN(""));
			
			
			oGridHelp.add(new ownSaveButton(),2, E2_INSETS.I_0_10_10_10);
			oGridHelp.add(new ownCancelButton(),2, E2_INSETS.I_0_10_10_10);
			
			this.add(oGridHelp, E2_INSETS.I_10_10_10_10);
			
		}
	}

	
	
	private class ownButtonStartSuchePreis extends MyE2_Button
	{
		public ownButtonStartSuchePreis()
		{
			super(E2_ResourceIcon.get_RI("suche_preis.png"));
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM oThis = BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM.this;
					
					RECORD_VPOS_RG recVPOS__RG = BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM.this.recVPOS_RG;
					
					
					
					// notwendige werte beschaffen
					String  cID_ARTIKEL = 				recVPOS__RG.get_ID_ARTIKEL_cUF_NN("");
					String  cLEISTUNGS_DATUM_Formated = oThis.tfLeistungsdatum.get_cText();
					String  cID_ADRESSE = 				recVPOS__RG.get_ID_ADRESSE_cUF_NN("");
					String  cEK_VK = "";
					long lLagerVorzeichen = recVPOS__RG.get_LAGER_VORZEICHEN_lValue(new Long(0));
					if (lLagerVorzeichen==-1)
					{
						cEK_VK = "VK";
					}
					else if (lLagerVorzeichen==1)
					{
						cEK_VK = "EK";
					}
					
					//optional (wenn vorhanden):
					String  cID_VPOS_KON = recVPOS__RG.get_ID_VPOS_KON_ZUGEORD_cUF_NN("");
					
					//das textfeld fuer die rueckgabe
					MyE2_TextField   oTextFuerRueckgabe = BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM.this.tfEINZELPREIS;

					new BSRG__POPUP_Select_EINZELPREIS(cID_ARTIKEL,cLEISTUNGS_DATUM_Formated,cID_ADRESSE,cEK_VK,cID_VPOS_KON,oTextFuerRueckgabe);
				}
			});
		}
	}
	
	
	
	private class ownSaveButton extends MyE2_Button
	{
		public ownSaveButton()
		{
			super(new MyE2_String("Speichern"));
			this.add_oActionAgent(new saveAction());
		}
		
		private class saveAction extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM oThis = BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM.this;
				
				bibMSG.add_MESSAGE(oThis.vUBFields.get_MV_AllFieldsAreOK_ShowErrorInput());
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(oThis.recVPOS_RG.set_NEW_VALUE_EINZELPREIS(oThis.tfEINZELPREIS.getText()));
					bibMSG.add_MESSAGE(oThis.recVPOS_RG.set_NEW_VALUE_STEUERSATZ(oThis.tfSteuersatz.get_oTextField().getText()));
					bibMSG.add_MESSAGE(oThis.recVPOS_RG.set_NEW_VALUE_AUSFUEHRUNGSDATUM(oThis.tfLeistungsdatum.get_oTextField().getText()));
					
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(oThis.recVPOS_RG.UPDATE(null, true));
						
						if (bibMSG.get_bIsOK())
						{
							oThis.get_oModuleContainer4Popup().CLOSE_AND_DESTROY_POPUPWINDOW(true);
						}
					}
				}
			}
		}
		
		
	}
	
	

	private class ownCancelButton extends MyE2_Button
	{
		public ownCancelButton()
		{
			super(new MyE2_String("Abbruch"));
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM.this.get_oModuleContainer4Popup().CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
		}
	}
	
	
	
}
