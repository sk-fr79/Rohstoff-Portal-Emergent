package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class FUO_Set_And_Valid_Mengenfreigabe extends XX_MAP_Set_And_Valid
{
	
	public FUO_Set_And_Valid_Mengenfreigabe()
	{
		super();
		
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
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

	
	private MyE2_MessageVector makeInterneErmittlung(E2_ComponentMAP oMAP, int ActionType) throws myException
	{
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
		
		boolean bEK = new FUO_Ermittle_EK_VK(oMAP).is_EK();

		String FIELDNAME_CB_NORMALE_ABRECHNUNG = 		bEK?RECORD_VPOS_TPA_FUHRE_ORT.FIELD__LADEMENGE_GUTSCHRIFT:		RECORD_VPOS_TPA_FUHRE.FIELD__ABLADEMENGE_RECHNUNG;
		
		String FIELDNAME_MENGE_ABSCHLUSS = 				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE;
		String FIELDNAME_MENGE_ABSCHLUSS_VON = 			RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE_VON;
		String FIELDNAME_MENGE_ABSCHLUSS_AM = 			RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE_AM;

		String FIELDNAME_PLAN_MENGE = 					RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_PLANMENGE;
		String FIELDNAME_LADE_MENGE = 					RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_LADEMENGE;
		String FIELDNAME_ABLADE_MENGE = 				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_ABLADEMENGE;
		
		String FIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM= 	RECORD_VPOS_TPA_FUHRE_ORT.FIELD__DATUM_LADE_ABLADE;
		
		String FIELD_PREISFREIGABE = 					RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS;
		
		String cWert_PlanMenge = 						oMAP.get_cActualDBValueFormated(FIELDNAME_PLAN_MENGE);
		String cWert_LadeMenge =       			 		oMAP.get_cActualDBValueFormated(FIELDNAME_LADE_MENGE);
		String cWert_AbladeMenge =      				oMAP.get_cActualDBValueFormated(FIELDNAME_ABLADE_MENGE);
		
		//normale abrechnung bedeutet links=lade und rechts gleich Abladegewicht
		boolean bNormaleAbrechnung =    				oMAP.get_bActualDBValue(FIELDNAME_CB_NORMALE_ABRECHNUNG);

		String cWert_NormalGewicht = 	cWert_LadeMenge;
		String cWert_GegenGewicht = 	cWert_AbladeMenge;
		
		if (!bEK)
		{
			cWert_NormalGewicht = cWert_AbladeMenge;
			cWert_GegenGewicht =  cWert_LadeMenge;
		}
		
		String cZugehoerendesLeistungsdatum = oMAP.get_cActualDBValueFormated(FIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM);
		if (!bibALL.isDatumOK(S.NN(cZugehoerendesLeistungsdatum)))
		{
			cZugehoerendesLeistungsdatum="";
		}
		
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			if (oMAP.get_bActualDBValue(FIELDNAME_MENGE_ABSCHLUSS))
			{
				//zuerst die validierung, ob der benutzer ueberhaupt das recht hat, freizugeben
				if (bibALL.get_RECORD_USER().is_MENGENABSCHLUSS_FUHRE_OK_NO() && bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_NO())
				{
					oMAP.get__DBComp(FIELDNAME_MENGE_ABSCHLUSS).set_cActualMaskValue("N");

					oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben nicht die Berechtigung für die Mengenfreigabe !")));
					return oMV_Rueck;
				}

				
				if (bNormaleAbrechnung)
				{
					cWert_GegenGewicht = cWert_NormalGewicht;   //damit einheitlich geprueft werden kann
				}
				
				if (this.isWertKorrekt(cWert_PlanMenge) && this.isWertKorrekt(cWert_NormalGewicht) && this.isWertKorrekt(cWert_GegenGewicht) && S.isFull(cZugehoerendesLeistungsdatum))
				{
					oMAP.get__DBComp(FIELDNAME_MENGE_ABSCHLUSS_VON).set_cActualMaskValue(bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("--"));
					oMAP.get__DBComp(FIELDNAME_MENGE_ABSCHLUSS_AM).set_cActualMaskValue(bibALL.get_cDateNOW());

					this.set_Enable_Relevant_MaskComponents(oMAP, false);
				}
				else
				{
					oMAP.get__DBComp(FIELDNAME_MENGE_ABSCHLUSS).set_cActualMaskValue("N");

					oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Für die Mengenfreigabe auf der "+(bEK?" Ladeseite ":" Abladeseite ")+ 
							" müssen die Mengenfelder und  "+(bEK?" Ladedatum ":" Abladedatum ")+" korrekt gefüllt sein !")));
					
				}

				
				//jetzt pruefen, ob der benutzer auch das recht fuer die preisfreigabe hat, wenn ja dann setzen
				if (bibALL.get_RECORD_USER().is_PREISABSCHLUSS_FUHRE_OK_YES() || bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES() && oMV_Rueck.get_bIsOK())
				{
					//den aktuellen messagevector sichern
					((MyE2_DB_CheckBox)oMAP.get__Comp(FIELD_PREISFREIGABE)).setSelected(true);
					((MyE2_DB_CheckBox)oMAP.get__Comp(FIELD_PREISFREIGABE)).doActionPassiv();  //damit die meldungen durchgereicht werden 
					
					//wenn das preisfreigeben noch geklickt ist, dann hat alles geklappt, der Anwender bekommt eine meldung, dass preisfreigabe implizit erfolgt ist
					if (((MyE2_DB_CheckBox)oMAP.get__Comp(FIELD_PREISFREIGABE)).isSelected())
					{
//						oMV_Rueck.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Da der Benutzer <",true,
//													bibALL.get_RECORD_USER().get_NAME_cUF_NN("??"),false,
//													"> auch die Berechtigung für die Preisfreigabe besitzt, wurde diese auch ausgeführt (" +(bEK?"EK-Seite":"VK-Seite")+ ")",true)));
					}
				}
			}
			else
			{
				this.set_Enable_Relevant_MaskComponents(oMAP, true);
				oMAP.get__DBComp(FIELDNAME_MENGE_ABSCHLUSS_VON).set_cActualMaskValue("");
				oMAP.get__DBComp(FIELDNAME_MENGE_ABSCHLUSS_AM).set_cActualMaskValue("");
			}
			
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			if (oMAP.get_bActualDBValue(FIELDNAME_MENGE_ABSCHLUSS))
			{
				this.set_Enable_Relevant_MaskComponents(oMAP, false);
			}
		}
		
		return oMV_Rueck;
	}

	
	private boolean isWertKorrekt(String cFeldWert) throws myException
	{
		return (S.isFull(cFeldWert) && bibALL.isNumber(cFeldWert, true));
	}
	
	
	
	private void set_Enable_Relevant_MaskComponents(E2_ComponentMAP oMAP, boolean bEnabled) throws myException
	{
		boolean bEK = new FUO_Ermittle_EK_VK(oMAP).is_EK();

		String FIELDNAME_CB_NORMALE_ABRECHNUNG = 		bEK?RECORD_VPOS_TPA_FUHRE_ORT.FIELD__LADEMENGE_GUTSCHRIFT:		RECORD_VPOS_TPA_FUHRE.FIELD__ABLADEMENGE_RECHNUNG;
		String FIELDNAME_PLAN_MENGE = 					RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_PLANMENGE;
		String FIELDNAME_LADE_MENGE = 					RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_LADEMENGE;
		String FIELDNAME_ABLADE_MENGE = 				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_ABLADEMENGE;
		String FIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM= 	RECORD_VPOS_TPA_FUHRE_ORT.FIELD__DATUM_LADE_ABLADE;
		
		oMAP.set_ActiveADHOC(FIELDNAME_PLAN_MENGE, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELDNAME_LADE_MENGE, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELDNAME_ABLADE_MENGE, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELDNAME_CB_NORMALE_ABRECHNUNG, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM, bEnabled, false);
		
		//sicherstellen, dass da die settings der mengefelder korrekt stehen
		new FUO_Set_And_Valid_BuchMge_ist_LadeMge().make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);
		
		//2014-03-17: aenderung, sortenbezeichnung wird bereits bei mengenfreigabe gesperrt
		oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE_ORT$ID_ARTIKEL_BEZ, bEnabled, false);

	}
	
	
}
