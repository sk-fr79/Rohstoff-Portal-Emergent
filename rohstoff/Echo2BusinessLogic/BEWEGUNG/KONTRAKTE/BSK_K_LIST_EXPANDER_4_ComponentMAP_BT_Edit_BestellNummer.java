package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Button_TO_EditField_DirektEditAndSave;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class BSK_K_LIST_EXPANDER_4_ComponentMAP_BT_Edit_BestellNummer extends 	MyE2_Button_TO_EditField_DirektEditAndSave 
{
	
	private UB_TextField  				otf_TEXT_Field = null;
	private RECORD_VPOS_KON             recVPOS_KON = null;
	private E2_BasicModuleContainer     oBasicModuleContainer = null;
	
	
	public BSK_K_LIST_EXPANDER_4_ComponentMAP_BT_Edit_BestellNummer(RECORD_VPOS_KON    VPOS_KON)	throws myException 
	{
		super(VPOS_KON.get_BESTELLNUMMER_cUF_NN("<Best.Nr>"));
		
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		this.add_GlobalAUTHValidator_AUTO("AENDERN_BESTELLNUMMER_IN_LISTE");
		
		this.recVPOS_KON = VPOS_KON;
		
		this.otf_TEXT_Field = new UB_TextField("BESTELLNUMMER", true, VPOS_KON.get_BESTELLNUMMER_cUF_NN(""),200,30);
		
		this.get_vUnboundDataFields().add(this.otf_TEXT_Field);
		this.set_cTitleForPopup(new MyE2_String("Bearbeiten der Bestellnummer ..."));
		
		this.get_vInfoTexts().add(new MyE2_String("Bitte geben Sie die neue Bestellnummer für"));
		this.get_vInfoTexts().add(new MyE2_String("diese Kontrakt-Position ein ... "));
		
		this.set_PopupWidthAndHeight(350, 200);
		
		this.add_GlobalValidator(new ownValidator());
	}

	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			if (BSK_K_LIST_EXPANDER_4_ComponentMAP_BT_Edit_BestellNummer.this.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Kann bei einer geschlossenen Vertragsposition nicht mehr verändert werden !"));
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}
		
	}
	
	
	@Override
	public MyE2_Grid get_GridWithComponents() throws myException 
	{
		MyE2_Grid oGridHelp = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		oGridHelp.add(this.otf_TEXT_Field,E2_INSETS.I_0_0_0_0);
		return oGridHelp;
	}

	@Override
	public E2_BasicModuleContainer get_BasicModuleContainer() throws myException 
	{
		this.oBasicModuleContainer = new own_BasicModuleContainer();
		return this.oBasicModuleContainer ;
	}

	
	@Override
	public MyE2_Button get_ButtonToSave() throws myException 
	{
		MyE2_Button  oButtonOK = new MyE2_Button("Speichern");
		
		oButtonOK.add_oActionAgent(new ownActionAgentSave());
		
		return oButtonOK;
	}

	
	private class ownActionAgentSave extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSK_K_LIST_EXPANDER_4_ComponentMAP_BT_Edit_BestellNummer oThis = BSK_K_LIST_EXPANDER_4_ComponentMAP_BT_Edit_BestellNummer.this;
			
			if (oThis.otf_TEXT_Field.get_bFieldHasChanged())
			{
				String cNewBestNr = oThis.otf_TEXT_Field.get_cText();
				
				MyE2_MessageVector oMV=oThis.recVPOS_KON.set_NEW_VALUE_BESTELLNUMMER(cNewBestNr);
				
				int iUpdateFuhren = 0;
				int iUpdateFuhrenOrte = 0;
				int iUpdateRG_POS = 0;
				int iSkipRG_POS = 0;
				
				if (oMV.get_bIsOK())
				{
					Vector<String> vSQL = new Vector<String>();
					vSQL.add(oThis.recVPOS_KON.get_SQL_UPDATE_STATEMENT(null, true));
					
					//jetzt verbundene saetze suchen und auch aendern
					RECLIST_VPOS_TPA_FUHRE recListFuhre_EK = oThis.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(DELETED,'N')='N'", null, true);
					RECLIST_VPOS_TPA_FUHRE recListFuhre_VK = oThis.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(DELETED,'N')='N'", null, true);
					RECLIST_VPOS_TPA_FUHRE_ORT recListFuhreORT = oThis.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N'", null, true);
					
					RECLIST_VPOS_RG  reclistRG = oThis.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_kon_zugeord("NVL(DELETED,'N')='N'", null, true);
					
					Iterator<RECORD_VPOS_TPA_FUHRE> iter_F_EK = recListFuhre_EK.values().iterator();
					Iterator<RECORD_VPOS_TPA_FUHRE> iter_F_VK = recListFuhre_VK.values().iterator();
					Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iter_F_O = recListFuhreORT.values().iterator();
					
					Iterator<RECORD_VPOS_RG> iter_RG = reclistRG.values().iterator();
					
					
					while (iter_F_EK.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE recFuhre = iter_F_EK.next();
						oMV.add_MESSAGE(recFuhre.set_NEW_VALUE_BESTELLNUMMER_EK(cNewBestNr));
						if (oMV.get_bIsOK())
						{
							vSQL.add(recFuhre.get_SQL_UPDATE_STATEMENT(null, true));
							iUpdateFuhren++;
						}
					}
					while (iter_F_VK.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE recFuhre = iter_F_VK.next();
						oMV.add_MESSAGE(recFuhre.set_NEW_VALUE_BESTELLNUMMER_VK(cNewBestNr));
						if (oMV.get_bIsOK())
						{
							vSQL.add(recFuhre.get_SQL_UPDATE_STATEMENT(null, true));
							iUpdateFuhren++;
						}
					}
					while (iter_F_O.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = iter_F_O.next();
						oMV.add_MESSAGE(recFuhreOrt.set_NEW_VALUE_BESTELLNUMMER(cNewBestNr));
						if (oMV.get_bIsOK())
						{
							vSQL.add(recFuhreOrt.get_SQL_UPDATE_STATEMENT(null, true));
							iUpdateFuhrenOrte++;
						}
					}
					
					while (iter_RG.hasNext())
					{
						RECORD_VPOS_RG recRG = iter_RG.next();
						if (recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null && recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
						{
							iSkipRG_POS++;
						}
						else
						{
							oMV.add_MESSAGE(recRG.set_NEW_VALUE_BESTELLNUMMER(cNewBestNr));
							if (oMV.get_bIsOK())
							{
								vSQL.add(recRG.get_SQL_UPDATE_STATEMENT(null, true));
								iUpdateRG_POS++;
							}
						}
					}

					if (oMV.get_bIsOK())
					{
						oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
					}
					
					if (oMV.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(	"Änderung wurde gespeichert: ",true,
																					"Kontraktposition / ",true,
																					""+iUpdateFuhren+" ",false,"Fuhre(n) / ",true,
																					""+iUpdateFuhrenOrte+" ",false,"Fuhren-Zusatzorte / ",true,
																					""+iUpdateRG_POS+" ",false,"Rechnungsposition(en) / ",true,
																					" ("+iSkipRG_POS+" ",false,"Rechnungsposition(en) nicht geändert,da gedruckt !)",true
																					)));
						oThis.oBasicModuleContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						oThis.recVPOS_KON.REBUILD();
						oThis.set_Text(oThis.recVPOS_KON.get_BESTELLNUMMER_cUF_NN("<Best.Nr>"));
						oThis.otf_TEXT_Field.set_StartValue(oThis.recVPOS_KON.get_BESTELLNUMMER_cUF_NN("<Best.Nr>"));
					}
					else
					{
						bibMSG.add_MESSAGE(oMV);
					}
				}
				else
				{
					bibMSG.add_MESSAGE(oMV);
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde nichts verändert !!")));
			}
			
		}
	}
	
	
	@Override
	public MyE2_Button get_ButtonToCancel() throws myException 
	{
		MyE2_Button oButtonCancel = new MyE2_Button(new MyE2_String("Abbruch"));
		oButtonCancel.add_oActionAgent(new XX_ActionAgent() 
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				BSK_K_LIST_EXPANDER_4_ComponentMAP_BT_Edit_BestellNummer.this.oBasicModuleContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		});
		
		return oButtonCancel;
	}

	
	
	private class own_BasicModuleContainer extends E2_BasicModuleContainer
	{

		public own_BasicModuleContainer() 
		{
			super();
		}
		
	}


	@Override
	public boolean check_StatusBeforePopup(E2_BasicModuleContainer oContainer,	Vector<MyE2_String> vInfoTexts, MyE2_Button oBtSave,MyE2_Button oBtCancel) throws myException 
	{
		return true;
	}
}
