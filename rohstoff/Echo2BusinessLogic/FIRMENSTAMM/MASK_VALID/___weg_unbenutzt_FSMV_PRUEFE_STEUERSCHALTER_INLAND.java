package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MASK_VALID;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


public class ___weg_unbenutzt_FSMV_PRUEFE_STEUERSCHALTER_INLAND extends XX_MAP_Set_And_Valid
{
	private boolean bLand_FEHLT = false;
	private boolean bLand_Mandant_FEHLT = false;

	
	private boolean bVORSTEUERABZUG_RC_INLAND = false;
	private boolean bSTEUERNUMMER_STATT_UST = false;
	private boolean bINNERGEMEIN_LIEF_EU = false;
	private boolean bSteuernummerLeer = false;
	private boolean bAusland = false;
	private boolean bAusland_IN_EU = false; 
	
	private boolean bUST_CodeLeer = false;
	private boolean bUST_Falsch =  false;

	
	private MyE2_DB_CheckBox  			oCB_VORSTEUERABZUG_RC_INLAND = 				null;
	private MyE2_DB_CheckBox  			oCB_STEUERNUMMER_STATT_UST = 	null;
	private MyE2_DB_CheckBox  			oCB_INNERGEMEIN_LIEF_EU =	null;
	private MyE2_DB_SelectField     	oSelLand = 					null;       	
	private MyE2_DB_TextField       	oTF_STEUERNUMMER =  		null;
	private MyE2_DB_TextField       	oTF_UMSATZSTEUERLKZ =  		null;
	private MyE2_DB_TextField       	oTF_UMSATZSTEUERID =  		null;
	

	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.check_VORSTEUERABZUG_RC_INLAND(oMAP, ActionType,oExecInfo);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return null;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.check_VORSTEUERABZUG_RC_INLAND(oMAP, ActionType,oExecInfo);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return null;
	}
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return null;
	}
	

	private MyE2_MessageVector check_VORSTEUERABZUG_RC_INLAND(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo) throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
//		
//		
//		///////////STEP 1: komponenten beschaffen
//		
//		
//		String cKlickHash = null;
//		if (oExecInfo!=null)
//		{
//			cKlickHash = oExecInfo.get_HASHKEY_of_KLICK_COMPONENT();
//		}
//		
//		oCB_VORSTEUERABZUG_RC_INLAND = 	(MyE2_DB_CheckBox)  	this.get_ComponentWithKeyInMask(oMAP, RECORD_FIRMENINFO.FIELD__VORSTEUERABZUG_RC_INLAND);
//		oCB_STEUERNUMMER_STATT_UST = 	(MyE2_DB_CheckBox)      this.get_ComponentWithKeyInMask(oMAP, RECORD_FIRMENINFO.FIELD__STEUERNUMMER_STATT_UST);
//		oCB_INNERGEMEIN_LIEF_EU = 		(MyE2_DB_CheckBox)      this.get_ComponentWithKeyInMask(oMAP, RECORD_FIRMENINFO.FIELD__INNERGEMEIN_LIEF_EU);
//		oSelLand = 						(MyE2_DB_SelectField)	this.get_ComponentWithKeyInMask(oMAP, RECORD_ADRESSE.FIELD__ID_LAND);       	
//		oTF_STEUERNUMMER = 				(MyE2_DB_TextField) 	this.get_ComponentWithKeyInMask(oMAP, RECORD_FIRMENINFO.FIELD__STEUERNUMMER);
//		oTF_UMSATZSTEUERLKZ =       	(MyE2_DB_TextField)		this.get_ComponentWithKeyInMask(oMAP, RECORD_FIRMENINFO.FIELD__UMSATZSTEUERLKZ);
//		oTF_UMSATZSTEUERID =       		(MyE2_DB_TextField)		this.get_ComponentWithKeyInMask(oMAP, RECORD_FIRMENINFO.FIELD__UMSATZSTEUERID);
//		
//		
//		RECORD_MANDANT    		recMandant = new RECORD_MANDANT(bibALL.get_ID_MANDANT());
//		
//		String cID_LandMandant_F = 		recMandant.get_ID_LAND_cF_NN("");
//		String c_ID_LandImDropDown_F = 	oSelLand.get_ActualWert();
//		String c_Steuernummer =         oTF_STEUERNUMMER.get_cActualMaskValue();
//		String c_UST_LKZ = 				S.NN(oTF_UMSATZSTEUERLKZ.get_cActualMaskValue()).trim();
//		
//		
//		//fehlerzustaende benennen
//		MyLong lLandAdresse = 	new MyLong(c_ID_LandImDropDown_F);
//		MyLong lLandMandant = 	new MyLong(cID_LandMandant_F);
//
//		RECORD_LAND  recLand = null;
//		if (lLandAdresse.get_oLong()!=null)
//		{
//			recLand = new RECORD_LAND(lLandAdresse.get_oLong().longValue());
//		}
//
//		
//		
//		///////////STEP 2: Interaktionen 
//		/*
//		 * vorab: wenn der steuernummer-statt-ustid geklickt wird, dann muss auch der schalter ust-verfahren inland gesetzt werden 
//		 */
//		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION && oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(RECORD_FIRMENINFO.FIELD__STEUERNUMMER_STATT_UST))
//		{
//			oCB_VORSTEUERABZUG_RC_INLAND.setSelected(oCB_STEUERNUMMER_STATT_UST.isSelected());
//			oCB_INNERGEMEIN_LIEF_EU.setSelected(false);
//		}
//
//		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION && oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(RECORD_FIRMENINFO.FIELD__VORSTEUERABZUG_RC_INLAND))
//		{
//			oCB_INNERGEMEIN_LIEF_EU.setSelected(false);
//		}
//		
//		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION && oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(RECORD_FIRMENINFO.FIELD__INNERGEMEIN_LIEF_EU))
//		{
//			//entweder ausland oder inland
//			oCB_VORSTEUERABZUG_RC_INLAND.setSelected(false);
//			oCB_STEUERNUMMER_STATT_UST.setSelected(false);
//		}
//		
//
//		
//		///////////STEP 3: Statusvariable 
//		
//		bLand_FEHLT = 														lLandAdresse.get_oLong()==null || lLandAdresse.get_oLong()==0;
//		bLand_Mandant_FEHLT = 												lLandMandant.get_oLong()==null || lLandMandant.get_oLong()==0;
//		
//		bVORSTEUERABZUG_RC_INLAND = 										oCB_VORSTEUERABZUG_RC_INLAND.isSelected();
//		bSTEUERNUMMER_STATT_UST = 											oCB_STEUERNUMMER_STATT_UST.isSelected();
//		bSteuernummerLeer = 												S.isEmpty(c_Steuernummer);
//		bAusland = 															bLand_FEHLT||bLand_Mandant_FEHLT||(lLandAdresse.get_lValue(0)!=lLandMandant.get_lValue(1));
//		
//		bUST_CodeLeer = 													S.isEmpty(oTF_UMSATZSTEUERLKZ.get_cActualMaskValue()) || S.isEmpty(oTF_UMSATZSTEUERID.get_cActualMaskValue());
//		bUST_Falsch = 														bUST_CodeLeer||
//																			bLand_FEHLT||
//																			!(recLand.get_UST_PRAEFIX_cUF_NN("-").equals(c_UST_LKZ));
//
//
//		bINNERGEMEIN_LIEF_EU = 											oCB_INNERGEMEIN_LIEF_EU.isSelected();
//		bAusland_IN_EU = 													recLand==null?false:recLand.is_INTRASTAT_JN_YES();
//		
//		
//		
//		
//		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
//		{
//			if (bLand_FEHLT) 			{oMV.add_MESSAGE(	new MyE2_Warning_Message(new MyE2_String("Bitte zuerst ein Land für diese Adresse festlegen !!")));}
//			if (bLand_Mandant_FEHLT) 	{oMV.add_MESSAGE(	new MyE2_Warning_Message(new MyE2_String("Bitte zuerst ein Land für die MANDANTENADRESSE festlegen !!")));}
//		}
//		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
//		{
//			oMV.add_MESSAGE(this.makeInternalCheck(true,cKlickHash));
//		}
//		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION)
//		{
//			oMV.add_MESSAGE(this.makeInternalCheck(false,cKlickHash));
//		}
//		
//		return oMV;
//	}
//	
//
//	private MyE2_MessageVector  makeInternalCheck(boolean bInKlick, String cKLICK_HASH)
//	{
//		MyE2_MessageVector oMV = new MyE2_MessageVector();
//
//		
//		if (bLand_FEHLT) 			{oMV.add_MESSAGE(	new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst ein Land für diese Adresse festlegen !!")));}
//		if (bLand_Mandant_FEHLT) 	{oMV.add_MESSAGE(	new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst ein Land für die MANDANTENADRESSE festlegen !!")));}
//		
//		//falls diese bedingung falsch ist, sofort melden !!
//		if (oMV.get_bHasAlarms())
//		{
//			if (bInKlick&&(cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__VORSTEUERABZUG_RC_INLAND)||cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__STEUERNUMMER_STATT_UST)))
//			{
//				oCB_VORSTEUERABZUG_RC_INLAND.setSelected(false);
//				oCB_STEUERNUMMER_STATT_UST.setSelected(false);
//			}
//			return oMV;
//		}
//		
//		if ( bAusland && (bVORSTEUERABZUG_RC_INLAND||bSTEUERNUMMER_STATT_UST))
//		{
//			oMV.add_MESSAGE(	new MyE2_Alarm_Message(new MyE2_String("Teilnahme am Vorsteuerabzugsverfahren INLAND kann nur erfolgen, wenn Land des Kunden und das Land des Mandanten übereinstimmen!!")));
//			if (bInKlick&&(cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__VORSTEUERABZUG_RC_INLAND)||cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__STEUERNUMMER_STATT_UST)))
//			{
//				oCB_VORSTEUERABZUG_RC_INLAND.setSelected(false);
//				oCB_STEUERNUMMER_STATT_UST.setSelected(false);
//			}
//			return oMV;
//		}
//		
//		if ( bVORSTEUERABZUG_RC_INLAND && (bUST_CodeLeer||bUST_Falsch) && !bSTEUERNUMMER_STATT_UST)
//		{
//			oMV.add_MESSAGE(	new MyE2_Alarm_Message(new MyE2_String("Teilnahme am Vorsteuerabzugsverfahren INLAND kann nur erfolgen, wenn eine vollständige, dem Land zugeordnete UST-ID vorhanden ist!!")));
//			if (bInKlick&&(cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__VORSTEUERABZUG_RC_INLAND)||cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__STEUERNUMMER_STATT_UST)))
//			{
//				oCB_VORSTEUERABZUG_RC_INLAND.setSelected(false);
//			}
//		}
//
//
//		if ( bSTEUERNUMMER_STATT_UST && bSteuernummerLeer) 
//		{
//			oMV.add_MESSAGE(	new MyE2_Alarm_Message(new MyE2_String("Steuernummer MUSS vorhanden sein!!")));
//			if (bInKlick&&(cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__VORSTEUERABZUG_RC_INLAND)||cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__STEUERNUMMER_STATT_UST)))
//			{
//				oCB_VORSTEUERABZUG_RC_INLAND.setSelected(false);
//				oCB_STEUERNUMMER_STATT_UST.setSelected(false);
//			}
//		}
//		
//		
//		if (bINNERGEMEIN_LIEF_EU && !bAusland)
//		{
//			oMV.add_MESSAGE(	new MyE2_Alarm_Message(new MyE2_String("Der Schalter: EU-Ausland/Innergemeinschaftliche Lieferung geht nur bei Auslandskunden innerhalb der EU")));
//			if (bInKlick && cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__INNERGEMEIN_LIEF_EU))
//			{
//				oCB_INNERGEMEIN_LIEF_EU.setSelected(false);
//			}
//		}
//		
//		if (bINNERGEMEIN_LIEF_EU && !bAusland_IN_EU)
//		{
//			oMV.add_MESSAGE(	new MyE2_Alarm_Message(new MyE2_String("Der Schalter: EU-Ausland/Innergemeinschaftliche Lieferung geht nur bei Auslandskunden innerhalb der EU")));
//			if (bInKlick && cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__INNERGEMEIN_LIEF_EU))
//			{
//				oCB_INNERGEMEIN_LIEF_EU.setSelected(false);
//			}
//		}
//		
//		if (bINNERGEMEIN_LIEF_EU && (bUST_CodeLeer||bUST_Falsch))
//		{
//			oMV.add_MESSAGE(	new MyE2_Alarm_Message(new MyE2_String("Der Schalter: EU-Ausland/Innergemeinschaftliche Lieferung geht nur bei korrekt ausgefüllter UST-ID (muss zum Land passen)")));
//			if (bInKlick && cKLICK_HASH.equals(RECORD_FIRMENINFO.FIELD__INNERGEMEIN_LIEF_EU))
//			{
//				oCB_INNERGEMEIN_LIEF_EU.setSelected(false);
//			}
//		}
		
		
		return oMV;
	}

}
