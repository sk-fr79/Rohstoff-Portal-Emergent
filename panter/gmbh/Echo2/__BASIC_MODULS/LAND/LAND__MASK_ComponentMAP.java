package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_MASK_ComponentMAP;


public class LAND__MASK_ComponentMAP extends E2_ComponentMAP 
{

	public LAND__MASK_ComponentMAP() throws myException
	{
		super(new LAND__MASK_SQLFieldMAP());
		
		LAND__MASK_SQLFieldMAP oFM = (LAND__MASK_SQLFieldMAP)this.get_oSQLFieldMAP();
		

		MyE2_DB_CheckBox  oCB_EU_Land = new MyE2_DB_CheckBox(oFM.get_(_DB.LAND$INTRASTAT_JN));
		MyE2_DB_CheckBox  oCB_Gelangensbestaetigung_sonderfall = new MyE2_DB_CheckBox(oFM.get_(LAND.sonderfall_gelangensbestaet.fn()));

		//hier kommen die Felder	
		//this.add_Component(new MyE2_DB_TextField(oFM.get_("ANZEIGEREIHENFOLGE"),true,200,20,false), new MyE2_String("ANZEIGEREIHENFOLGE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.LAND$ANZEIGEREIHENFOLGE)), new MyE2_String("Reihenfolge"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(_DB.LAND$ID_LAND)), new MyE2_String("ID-Land"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_(_DB.LAND$BESCHREIBUNG),500,5), new MyE2_String("Beschreibungstext"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.LAND$GENERELLE_EINFUHR_NOTI)), new MyE2_String("Generelle Einfuhr-Notifizierung"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.LAND$HAT_POSTCODE)), new MyE2_String("Besitzt Postcode"));
		this.add_Component(oCB_EU_Land, new MyE2_String("EU-Land"));
		this.add_Component(oCB_Gelangensbestaetigung_sonderfall, new MyE2_String("Sonderf.Gelangensbest."));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.LAND$ISTSTANDARD)), new MyE2_String("Standard"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.LAND$LAENDERCODE),true,100,3,false), new MyE2_String("Ländercode"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(LAND.iso_country_code.fn()),true,100,3,false), new MyE2_String("ISO-Code"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.LAND$LAENDERNAME),true,500,60,false), new MyE2_String("Ländername"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.LAND$LAENDERVORWAHL),true,100,20,false), new MyE2_String("Ländervorwahl"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.LAND$POST_CODE),true,100,20,false), new MyE2_String("Postcode"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.LAND$UST_PRAEFIX),true,100,3,false), new MyE2_String("UST-Präfix"));
		

		this.add_Component(LAND__CONST.LAND_MASK_COMP__RC_SORTEN_MATRIX, new LAND__MASK_RC_Sorten(oFM), new MyE2_String("RC-Sorten"));
		
		this.set_oMAPSettingAgent(new LAND__MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new LAND__MASK_FORMATING_Agent());
		
		this.get_hmInteractiv_settings_validation().put_(_DB.LAND$INTRASTAT_JN,new LAND__MAP_SET_AND_Valid_EU_RC_Sorten());
		
		oCB_EU_Land.add_oActionAgent(new ownAction4MaskCheck());
		
		this.add_oMAPValidator(new XX_MAP_ValidBeforeSAVE()
		{
			@Override
			public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
			{
				return oMap.get_hmInteractiv_settings_validation().execute_ComponentMAP_ValidBeforeSave_settings(oMap,oInputMap);
			}
		});

	}
	
	
	private class ownAction4MaskCheck extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			//die komponentengebundene aktion fuer beide maps ausfuehren
			LAND__MASK_ComponentMAP.this.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(LAND__MASK_ComponentMAP.this,oExecInfo);
		}
	}

}
