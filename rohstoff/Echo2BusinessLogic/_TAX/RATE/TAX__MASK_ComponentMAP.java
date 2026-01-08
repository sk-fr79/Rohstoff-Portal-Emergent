package rohstoff.Echo2BusinessLogic._TAX.RATE;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_LAND_DROPDOWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldV2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;


public class TAX__MASK_ComponentMAP extends E2_ComponentMAP 
{

	public TAX__MASK_ComponentMAP() throws myException
	{
		super(new TAX__MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_(_DB.TAX$ID_TAX)), 											new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.TAX$DROPDOWN_TEXT),true,400,100,false),		 		new MyE2_String("Beschriftung des DropDown-Eintrags"));
		this.add_Component(new DB_Component_LAND_DROPDOWN(oFM.get_(_DB.TAX$ID_LAND),200), 							new MyE2_String("Land (Gültigkeit)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.TAX$STEUERSATZ),true,80,6,false), 					new MyE2_String("Steuersatz in Prozent"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_(_DB.TAX$STEUERVERMERK),400,8),		 						new MyE2_String("Steuervermerk für Belege"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_(_DB.TAX$INFO_TEXT_USER),400,8), 							new MyE2_String("Info-Text für Benutzer"));
		
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_(_DB.TAX$WECHSELDATUM),"",80), 				new MyE2_String("Wechseldatum für Steuersatz"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.TAX$STEUERSATZ_NEU),true,80,10,false), 				new MyE2_String("Steuersatz nach dem Wechsel"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_(_DB.TAX$TAXTYP), TAX_CONST.TAXTYP_DD_ARRAY, true),		new MyE2_String("Typ des Steuersachverhaltes"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.TAX$LEERVERMERK)),										new MyE2_String("Leervermerk, dieser wird in den RE/GS-Positionen ignoriert"));
		
		TAX_DD_FibuKonto fk = new TAX_DD_FibuKonto();
		this.add_Component(new MyE2_DB_SelectField(oFM.get_(TAX.id_fibu_konto_gs),fk.getDD(),false),				new MyE2_String("Buchungskonto für diesen Steuersatz auf Gutschriften"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_(TAX.id_fibu_konto_re),fk.getDD(),false),				new MyE2_String("Buchungskonto für diesen Steuersatz auf Rechnungen"));
		
		//2013-10-01: neues feld: aktiv mit validierung
		MyE2_DB_CheckBox  o_CB_Aktiv = new MyE2_DB_CheckBox(oFM.get_(_DB.TAX$AKTIV),new MyE2_String("Aktiv"),new MyE2_String("Ist ein Steuersatz passiv, dann kann er in einer Definition oder der Fuhre nicht mehr ausgewählt werden!"));
		
		o_CB_Aktiv.add_GlobalAUTHValidator_AUTO(TAX_CONST.VALID_KEY_ERLAUBE_AKTIV_PASSIV_STEUER_IN_MASKE);
		
		o_CB_Aktiv.add_oActionAgent(new XX_ActionAgent()    //actionagent, damit das validieren klappt
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
			}
		});
		this.add_Component(o_CB_Aktiv, new MyE2_String("Aktiv"));
		
		//2014-11-11: Neues Feld nach Rücksprache: Historisch (nilsandre)
		MyE2_DB_CheckBox  o_CB_Historisch = new MyE2_DB_CheckBox(oFM.get_("HISTORISCH"),new MyE2_String("Historisch"),new MyE2_String("Falls ein Steuersatz durch einen neuen ersetzt wird, kann er als passiv und historisch in der Datenbank bleiben!"));
		this.add_Component(o_CB_Historisch, new MyE2_String("Historisch"));
		
		
		//2018-06-13: weitere felder: steuersatzgruppe und Sortierung
		this.add_Component(new OwnSelectSteuersatzGruppe(oFM.get_(TAX.id_tax_group)),								new MyE2_String("Steuersatzgruppe"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(TAX.sort)),												new MyE2_String("Sortierung"));

		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new TAX__MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new TAX__MASK_FORMATING_Agent());
		
		
		/*
		 * sorgt fuer die richtige verarbeitung eines steuer-leer-texteintrags
		 */
		this.get_hmInteractiv_settings_validation().put_(_DB.TAX$LEERVERMERK, new ownMapSetAndValidator());
		
		this.add_oMAPValidator(new ownMaskValidator());
		
		((MyE2_DB_CheckBox)this.get__Comp(_DB.TAX$LEERVERMERK)).add_oActionAgent(new ownActionMaskEdit());
		
	}

	
	
	//einen map-set-and-validator, der den text nach aktivieren des Leer-Vermerk-Schalters richtig setzt
	private class ownMapSetAndValidator extends XX_MAP_Set_And_Valid
	{

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return this._inner_settings(oMAP, ActionType, oExecInfo, oInputMap);
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return this._inner_settings(oMAP, ActionType, oExecInfo, oInputMap);
		}
		

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return this._inner_settings(oMAP, ActionType, oExecInfo, oInputMap);
		}

		
		private MyE2_MessageVector _inner_settings(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
			{
				oMAP.set_ActiveADHOC(_DB.TAX$STEUERVERMERK, true, false);
				if (oMAP.get_bActualDBValue(_DB.TAX$LEERVERMERK))
				{
					//dann wird der vermerk-text mit dem leervermerk gefuellt und inaktiv
					oMAP.set_cActualDatabaseValueToMask(_DB.TAX$STEUERVERMERK, FU___CONST.EU_STEUERVERMERK_LEER);
					//oMAP.set_ActiveADHOC(_DB.TAX$STEUERVERMERK, false, false);
				}
			}
			else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
			{
				oMAP.set_ActiveADHOC(_DB.TAX$STEUERVERMERK, true, false);
				if (oMAP.get_bActualDBValue(_DB.TAX$LEERVERMERK))
				{
					//dann wird der vermerk-text inaktiv
					//oMAP.set_ActiveADHOC(_DB.TAX$STEUERVERMERK, false, false);
				}
			}
			
			return oMV;

		}
		
		
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return new MyE2_MessageVector();			
		}
		
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return new MyE2_MessageVector();
		}

	}
	
	
	private class ownActionMaskEdit extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			TAX__MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(TAX__MASK_ComponentMAP.this,oExecInfo);
		}

	}
	
	
	private class ownMaskValidator extends XX_MAP_ValidBeforeSAVE
	{
		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
		{
			return TAX__MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_ValidBeforeSave_settings(TAX__MASK_ComponentMAP.this,oInputMap );
		}
		
	}

	
	private class OwnSelectSteuersatzGruppe extends MyE2_DB_SelectFieldV2 {
		/**
		 * @param osqlField
		 * @throws myException
		 */
		public OwnSelectSteuersatzGruppe(SQLField osqlField) throws myException {
			super(osqlField);
			SEL  selActive = new SEL(TAX_GROUP.kurztext,TAX_GROUP.id_tax_group).FROM(_TAB.tax_group).ORDERUP(TAX_GROUP.kurztext).WHERE(new vgl_YN(TAX_GROUP.aktiv, true));
			SEL  selInActive = new SEL(TAX_GROUP.kurztext,TAX_GROUP.id_tax_group).FROM(_TAB.tax_group).ORDERUP(TAX_GROUP.kurztext).WHERE(new vgl_YN(TAX_GROUP.aktiv, false));
			this.populateCombobox(selActive.s(), selInActive.s(), false, true, true, false);
		}
		
	}

}
