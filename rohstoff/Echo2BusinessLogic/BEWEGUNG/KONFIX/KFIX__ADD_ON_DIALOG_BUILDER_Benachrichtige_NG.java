package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer.XX_BuildAddonComponents_4_Dialog;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_CheckBoxList;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_KON_EXT;

public abstract  class KFIX__ADD_ON_DIALOG_BUILDER_Benachrichtige_NG extends  XX_BuildAddonComponents_4_Dialog
{

	private MyE2_CheckBox  cbBeteiligteErfasserNachrichtsenden = new MyE2_CheckBox();
	private MyE2_CheckBox  cbBeteiligteHaendlerNachrichtsenden = new MyE2_CheckBox();
	private MyE2_CheckBox  cbBeteiligteSachbearbeiterNachrichtsenden = new MyE2_CheckBox();
	
	private Vector<String>   vIDsToToggle = new Vector<String>();
	
	
	public Vector<String> get_vIDsToToggle()
	{
		return vIDsToToggle;
	}


	public abstract void fill_v_ID_VPOS_KON_ToToggle() throws myException;
	
	
	@Override
	public Component build_AddonComponent(E2_ConfirmBasicModuleContainer oConfirmContainer) throws myException
	{
		MyE2_Grid oGridHelp = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.fill_v_ID_VPOS_KON_ToToggle();
		
		if (vIDsToToggle.size()>1)
		{
			// bei mehreren kontraktpositionen allgemein formulieren
			
			this.cbBeteiligteErfasserNachrichtsenden = new MyE2_CheckBox("Beteiligte Erfasser benachrichtigen ");
			this.cbBeteiligteHaendlerNachrichtsenden = new MyE2_CheckBox("Beteiligte Händler benachrichtigen ");
			this.cbBeteiligteSachbearbeiterNachrichtsenden = new MyE2_CheckBox("Beteiligte Sachbearbeiter benachrichtigen ");
		}
		else
		{
			// bei einer kontraktposition die personen nennen

			RECORD_VKOPF_KON  recVKOPFKon = new RECORD_VPOS_KON(vIDsToToggle.get(0)).get_UP_RECORD_VKOPF_KON_id_vkopf_kon();
			
			if ( 	recVKOPFKon.get_UP_RECORD_USER_id_user()!=null && 
					recVKOPFKon.get_UP_RECORD_USER_id_user().get_ID_USER_lValue(new Long(-1))!=bibALL.get_RECORD_USER().get_ID_USER_lValue(new Long(-2)))
			{
				this.cbBeteiligteErfasserNachrichtsenden = new MyE2_CheckBox(new MyE2_String("Nachricht senden an:  ",true,recVKOPFKon.get_UP_RECORD_USER_id_user().get___KETTE(bibALL.get_Vector("VORNAME","NAME1")),false));
			}
			
			if ( 	recVKOPFKon.get_UP_RECORD_USER_id_user_ansprech_intern()!=null && 
					recVKOPFKon.get_UP_RECORD_USER_id_user_ansprech_intern().get_ID_USER_lValue(new Long(-1))!=bibALL.get_RECORD_USER().get_ID_USER_lValue(new Long(-2)))
			{
				this.cbBeteiligteHaendlerNachrichtsenden = new MyE2_CheckBox(new MyE2_String("Nachricht senden an:  ",true,recVKOPFKon.get_UP_RECORD_USER_id_user_ansprech_intern().get___KETTE(bibALL.get_Vector("VORNAME","NAME1")),false));
			}
			
			if ( 	recVKOPFKon.get_UP_RECORD_USER_id_user_sachbearb_intern()!=null && 
					recVKOPFKon.get_UP_RECORD_USER_id_user_sachbearb_intern().get_ID_USER_lValue(new Long(-1))!=bibALL.get_RECORD_USER().get_ID_USER_lValue(new Long(-2)))
			{
				this.cbBeteiligteSachbearbeiterNachrichtsenden = new MyE2_CheckBox(new MyE2_String("Nachricht senden an:  ",true,recVKOPFKon.get_UP_RECORD_USER_id_user_sachbearb_intern().get___KETTE(bibALL.get_Vector("VORNAME","NAME1")),false));
			}
		}

		Vector<MyE2_CheckBox>  vCBs = new Vector<MyE2_CheckBox>();
		
		vCBs.add(this.cbBeteiligteErfasserNachrichtsenden);
		vCBs.add(this.cbBeteiligteHaendlerNachrichtsenden);
		vCBs.add(this.cbBeteiligteSachbearbeiterNachrichtsenden);
		
		this.cbBeteiligteErfasserNachrichtsenden.add_oActionAgent(new ownActionAgentSaveCheckboxStatus());
		this.cbBeteiligteHaendlerNachrichtsenden.add_oActionAgent(new ownActionAgentSaveCheckboxStatus());
		this.cbBeteiligteSachbearbeiterNachrichtsenden.add_oActionAgent(new ownActionAgentSaveCheckboxStatus());
		
		E2_UserSetting_CheckBoxList  oCheckBoxSetter = new E2_UserSetting_CheckBoxList(vCBs, "BSK_P_LIST_BT_LOCK_UNLOCK_KONTRAKT@@SAVE_WER_BEKOMMTS");
		oCheckBoxSetter.restore_Status_cb();

		boolean bAnzeige = false;
		if (S.isFull(this.cbBeteiligteErfasserNachrichtsenden.getText()))
		{
			oGridHelp.add_raw(this.cbBeteiligteErfasserNachrichtsenden, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_5_5_5));
			bAnzeige = true;
		}
		if (S.isFull(this.cbBeteiligteHaendlerNachrichtsenden.getText()))
		{
			oGridHelp.add_raw(this.cbBeteiligteHaendlerNachrichtsenden, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_5_5_5));
			bAnzeige = true;
		}
		if (S.isFull(this.cbBeteiligteSachbearbeiterNachrichtsenden.getText()))
		{
			oGridHelp.add_raw(this.cbBeteiligteSachbearbeiterNachrichtsenden, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_5_5_5));
			bAnzeige = true;
		}

		
		if (bAnzeige)
		{
			return oGridHelp;
		}
		else
		{
			return null;
		}
	}

	@Override
	public Vector<XX_ActionAgent> get_vActionAgents_from_AddonComponent(E2_ConfirmBasicModuleContainer oConfirmContainer) throws myException
	{
		Vector<XX_ActionAgent> vRueck = new Vector<XX_ActionAgent>();
		
		for (int i=0;i<this.vIDsToToggle.size();i++)
		{
			RECORD_VPOS_KON  recVposKon = new RECORD_VPOS_KON(this.vIDsToToggle.get(i));
			
			if (this.cbBeteiligteErfasserNachrichtsenden.isSelected())
			{
				if (recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user()!=null &&
					!recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user().equals(bibALL.get_ID_USER()))
				{
					vRueck.add(baue_Agent(recVposKon, recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user()));
				}
			}
			if (this.cbBeteiligteHaendlerNachrichtsenden.isSelected())
			{
				if (recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user_ansprech_intern()!=null &&
					!recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user_ansprech_intern().equals(bibALL.get_ID_USER()))
				{
					vRueck.add(baue_Agent(recVposKon, recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user_ansprech_intern()));
				}
			}
			if (this.cbBeteiligteSachbearbeiterNachrichtsenden.isSelected())
			{
				if (recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user_sachbearb_intern()!=null &&
					!recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user_sachbearb_intern().equals(bibALL.get_ID_USER()))
				{
					vRueck.add(baue_Agent(recVposKon, recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_USER_id_user_sachbearb_intern()));
				}
			}
		}
		return vRueck;
	}
	
	
	private ownActionAgentSendKontraktMessage baue_Agent(RECORD_VPOS_KON recVposKon, RECORD_USER recTargetUser) throws myException
	{
		boolean bIsEK = recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT);
		
		//im moment des bauens des actionagents ist noch der alte zustand
		String cGeoeffnet_geschlossen = recVposKon.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES()?"geöffnet":"geschlossen";

		
		MyE2_String cMeldung = new MyE2_String(bIsEK?"EK-Kontrakt-Position":"VK-Kontrakt-Position",false);		cMeldung.addUnTranslated("\n");
		cMeldung.addTranslated("mit:");																			cMeldung.addUnTranslated("\n");
		cMeldung.addUnTranslated(recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")));		cMeldung.addUnTranslated("\n");
		cMeldung.addTranslated("über: ");
		String cEinheit = "<EH>";
		try 
		{
			cEinheit=recVposKon.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN("<EH>");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			cEinheit = "<EH>";
		}
		cMeldung.addUnTranslated(recVposKon.get_ANZAHL_cF_NN("0")+" "+cEinheit+" ["+recVposKon.get_ANR1_cUF_NN("<Anr1>")+" "+recVposKon.get_ANR2_cUF_NN("<Anr2>")+"] "+recVposKon.get_ARTBEZ1_cUF_NN("<Artbez1>"));
		cMeldung.addUnTranslated("\n");
		cMeldung.addTranslated("wurde "+cGeoeffnet_geschlossen+" von: ");
		cMeldung.addUnTranslated(bibALL.get_RECORD_USER().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")));
		cMeldung.addUnTranslated("\n");
		cMeldung.addTranslated("Datum: ");
		cMeldung.addUnTranslated(bibALL.get_cDateTimeNOW());
		
		//2011-05-31: weitere infos in der meldung:
		cMeldung.addUnTranslated("\n");
		cMeldung.addTranslated("Buchungsnummer Kontrakt: ");
		cMeldung.addUnTranslated(recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cF_NN("<Buchungsnr>"));
		cMeldung.addUnTranslated(" // ");
		cMeldung.addTranslated("ID-Kontraktkopf: ");
		cMeldung.addUnTranslated(recVposKon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_VKOPF_KON_cF_NN(""));
		cMeldung.addUnTranslated(" // ");
		cMeldung.addTranslated("ID-KontraktPos: ");
		cMeldung.addUnTranslated(recVposKon.get_ID_VPOS_KON_cF_NN(""));

		
		//2014-04-28: weitere infos zum kontrakt: Mengen-plan, erfüllt, preis
		cMeldung.addUnTranslated("\n=============================================================");
		
		RECORD_VPOS_KON_EXT recExt = new RECORD_VPOS_KON_EXT(recVposKon);
		cMeldung.addUnTranslated("\n");
		BigDecimal  bdPlanEcht[] = recExt.get_MengeGeliefertPlanEcht();
		cMeldung.addTranslated("Kontraktmenge: "+MyNumberFormater.formatDez(recExt.get_ANZAHL_bdValue(BigDecimal.ZERO), 0, true));
		cMeldung.addUnTranslated(" // ");
		cMeldung.addTranslated("Erfüllte Menge: "+MyNumberFormater.formatDez(bdPlanEcht[1], 0, true));
		cMeldung.addUnTranslated(" // ");
		cMeldung.addTranslated("Abzug: "+MyNumberFormater.formatDez(bdPlanEcht[2], 0, true));
		cMeldung.addUnTranslated(" // ");
		cMeldung.addTranslated("Preis: "+MyNumberFormater.formatDez(recExt.get_EINZELPREIS_bdValue(BigDecimal.ZERO), 2, true));
		
		//
		
		return new ownActionAgentSendKontraktMessage(
				new MyE2_String("Kontrakt wurde "+cGeoeffnet_geschlossen).CTrans(), 
				cMeldung.CTrans(), 
				recTargetUser.get_ID_USER_cUF(), 
				recVposKon.get_ID_VKOPF_KON_cUF(), 
				bIsEK?E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST:E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST);

	}
	
	
	
	
	private class ownActionAgentSendKontraktMessage extends  XX_ActionAgent
	{
		private String cMessageBetreff = null;
		private String cMessageMeldung = null;
		private String cID_Empfaenger = null;
		private String cID_TargetModule = null;
		private String cModuleName = null;
		
		public ownActionAgentSendKontraktMessage(	String MessageBetreff,
													String MessageMeldung, 
													String ID_Empfaenger,
													String ID_TargetModule, 
													String ModuleName)
		{
			super();
			this.cMessageBetreff =  MessageBetreff;
			this.cMessageMeldung =  MessageMeldung;
			this.cID_Empfaenger =   ID_Empfaenger;
			this.cID_TargetModule = ID_TargetModule;
			this.cModuleName =      ModuleName;
		}


		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MESSAGE_Entry msgEntry = new MESSAGE_Entry()
					.set_Titel(cMessageBetreff)
					.set_Nachricht(cMessageMeldung)
					.add_idEmpfaenger(cID_Empfaenger)
					.set_Sofortanzeige(true)
					.set_DtAnzeigeAb(bibALL.get_cDateNOWInverse("-"))
					.add_Target(new MESSAGE_Entry_Target(cID_TargetModule, cModuleName, null, new MyE2_String("Springe zum betreffenden Kontrakt").CTrans()))
					.set_Kategorie(cModuleName);
			new MESSAGE_Handler().saveMessage(msgEntry);
			

		}
	}
	
	
	private class ownActionAgentSaveCheckboxStatus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			KFIX__ADD_ON_DIALOG_BUILDER_Benachrichtige_NG oThis = KFIX__ADD_ON_DIALOG_BUILDER_Benachrichtige_NG.this;
			Vector<MyE2_CheckBox>  vCBs = new Vector<MyE2_CheckBox>();
			
			vCBs.add(oThis.cbBeteiligteErfasserNachrichtsenden);
			vCBs.add(oThis.cbBeteiligteHaendlerNachrichtsenden);
			vCBs.add(oThis.cbBeteiligteSachbearbeiterNachrichtsenden);
			
			E2_UserSetting_CheckBoxList  oCheckBoxSetter = new E2_UserSetting_CheckBoxList(vCBs, "BSK_P_LIST_BT_LOCK_UNLOCK_KONTRAKT@@SAVE_WER_BEKOMMTS");
			oCheckBoxSetter.saveSelectedStatus();
			
		}
		
	}

}
