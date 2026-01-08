package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU_Set_And_Valid_Mengenfreigabe extends XX_MAP_Set_And_Valid
{

	private String DEF_QUELLE_ZIEL = null;
	
	private String  			FIELDNAME_PLAN_MENGE = null;
	private String  			FIELDNAME_LADE_MENGE = null;
	private String  			FIELDNAME_ABLADE_MENGE = null;
	private String    			FIELDNAME_CB_NORMALE_ABRECHNUNG = null;    //schalter fuer Buchungsmenge=Lademengen usw
	
	private String   		   	FIELDNAME_MENGE_ABSCHLUSS = null;
	private String   		   	FIELDNAME_MENGE_ABSCHLUSS_VON = null;
	private String   		   	FIELDNAME_MENGE_ABSCHLUSS_AM = null;
	
	private String              FIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM = null;
	
	private String              FIELD_PREISFREIGABE = null;
	
	
	public FU_Set_And_Valid_Mengenfreigabe(String dEF_QUELLE_ZIEL)
	{
		super();
		DEF_QUELLE_ZIEL = dEF_QUELLE_ZIEL;
		
		boolean bEK = (this.DEF_QUELLE_ZIEL.equals("EK"));
		
		this.FIELDNAME_MENGE_ABSCHLUSS = 				bEK?RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE:		RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE;
		this.FIELDNAME_MENGE_ABSCHLUSS_VON = 			bEK?RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE_VON:	RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE_VON;
		this.FIELDNAME_MENGE_ABSCHLUSS_AM = 			bEK?RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE_AM:		RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE_AM;

		this.FIELDNAME_PLAN_MENGE = 					bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_PLANMENGE_LIEF:		RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_PLANMENGE_ABN;
		this.FIELDNAME_LADE_MENGE = 					bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_LADEMENGE_LIEF:		RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_LADEMENGE_ABN;
		this.FIELDNAME_ABLADE_MENGE = 					bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_ABLADEMENGE_LIEF:	RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_ABLADEMENGE_ABN;
		
		this.FIELDNAME_CB_NORMALE_ABRECHNUNG = 			bEK?RECORD_VPOS_TPA_FUHRE.FIELD__LADEMENGE_GUTSCHRIFT:		RECORD_VPOS_TPA_FUHRE.FIELD__ABLADEMENGE_RECHNUNG;
		this.FIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM = 	bEK?RECORD_VPOS_TPA_FUHRE.FIELD__DATUM_AUFLADEN:			RECORD_VPOS_TPA_FUHRE.FIELD__DATUM_ABLADEN;
		
		this.FIELD_PREISFREIGABE = 						bEK?RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS:			RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS;
		
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
		
		String cWert_PlanMenge = 		oMAP.get_cActualDBValueFormated(this.FIELDNAME_PLAN_MENGE);
		String cWert_LadeMenge =        oMAP.get_cActualDBValueFormated(this.FIELDNAME_LADE_MENGE);
		String cWert_AbladeMenge =      oMAP.get_cActualDBValueFormated(this.FIELDNAME_ABLADE_MENGE);
		
		//normale abrechnung bedeutet links=lade und rechts gleich Abladegewicht
		boolean bNormaleAbrechnung =    oMAP.get_bActualDBValue(this.FIELDNAME_CB_NORMALE_ABRECHNUNG);

		String cWert_NormalGewicht = 	cWert_LadeMenge;
		String cWert_GegenGewicht = 	cWert_AbladeMenge;
		
		if (this.DEF_QUELLE_ZIEL.equals("VK"))
		{
			cWert_NormalGewicht = cWert_AbladeMenge;
			cWert_GegenGewicht =  cWert_LadeMenge;
		}
		
		String cZugehoerendesLeistungsdatum = oMAP.get_cActualDBValueFormated(this.FIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM);
		//jetzt pruefen, ob das datum in ordnung ist 
		if (!bibALL.isDatumOK_NG(S.NN(cZugehoerendesLeistungsdatum)))
		{
			cZugehoerendesLeistungsdatum="";
		}
		
		
		//2014-03-17: pruefung der sortenfelder erfolgt immer, egal ob laden oder klicken
		oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL,true,false);
		oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL_BEZ_EK,true,false);
		oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL_BEZ_VK,true,false);
	
		
		
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			if (oMAP.get_bActualDBValue(this.FIELDNAME_MENGE_ABSCHLUSS))
			{
				//zuerst die validierung, ob der benutzer ueberhaupt das recht hat, freizugeben
				if (bibALL.get_RECORD_USER().is_MENGENABSCHLUSS_FUHRE_OK_NO() && bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_NO())
				{
					oMAP.get__DBComp(this.FIELDNAME_MENGE_ABSCHLUSS).set_cActualMaskValue("N");

					oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben nicht die Berechtigung für die Mengenfreigabe !")));
					//2014-03-17: sorten werden bereits bei der mengenfreigabe gesperrt
					this.pruefe_SortenFelder_nachStatus(oMAP);
					return oMV_Rueck;
				}

				
				if (bNormaleAbrechnung)
				{
					cWert_GegenGewicht = cWert_NormalGewicht;   //damit einheitlich geprueft werden kann
				}
				
				if (this.isWertKorrekt(cWert_PlanMenge) && this.isWertKorrekt(cWert_NormalGewicht) && this.isWertKorrekt(cWert_GegenGewicht) && S.isFull(cZugehoerendesLeistungsdatum))
				{
					oMAP.get__DBComp(this.FIELDNAME_MENGE_ABSCHLUSS_VON).set_cActualMaskValue(bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("--"));
					oMAP.get__DBComp(this.FIELDNAME_MENGE_ABSCHLUSS_AM).set_cActualMaskValue(bibALL.get_cDateNOW());

					this.set_Enable_Relevant_MaskComponents(oMAP, false);
				}
				else
				{
					oMAP.get__DBComp(this.FIELDNAME_MENGE_ABSCHLUSS).set_cActualMaskValue("N");

					oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Für die Mengenfreigabe auf der "+(this.DEF_QUELLE_ZIEL.equals("EK")?" Ladeseite ":" Abladeseite ")+ 
							" müssen die Mengenfelder und  "+(this.DEF_QUELLE_ZIEL.equals("EK")?" Ladedatum ":" Abladedatum ")+" korrekt gefüllt sein !")));
					
				}
				
				//jetzt pruefen, ob der benutzer auch das recht fuer die preisfreigabe hat, wenn ja dann setzen
				//2013-03-21: und zwar nur, wenn der mandant dies auch so will 
				if ((bibALL.get_RECORD_USER().is_PREISABSCHLUSS_FUHRE_OK_YES() || bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES())
					 && oMV_Rueck.get_bIsOK() && bibALL.get_RECORD_MANDANT().is_PREISFREIGABE_AUTO_FUHRE_YES())
				{
					//den aktuellen messagevector sichern
					((MyE2_DB_CheckBox)oMAP.get__Comp(this.FIELD_PREISFREIGABE)).setSelected(true);
					((MyE2_DB_CheckBox)oMAP.get__Comp(this.FIELD_PREISFREIGABE)).doActionPassiv();  //damit die meldungen durchgereicht werden 
					
					//wenn das preisfreigeben noch geklickt ist, dann hat alles geklappt, der Anwender bekommt eine meldung, dass preisfreigabe implizit erfolgt ist
					if (((MyE2_DB_CheckBox)oMAP.get__Comp(this.FIELD_PREISFREIGABE)).isSelected())
					{
//						oMV_Rueck.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Da der Benutzer <",true,
//													bibALL.get_RECORD_USER().get_NAME_cUF_NN("??"),false,
//													"> auch die Berechtigung für die Preisfreigabe besitzt, wurde diese auch ausgeführt (" +(this.DEF_QUELLE_ZIEL.equals("EK")?"EK-Seite":"VK-Seite")+ ")",true)));
					}
					
				}
			}
			else
			{
				this.set_Enable_Relevant_MaskComponents(oMAP, true);
				oMAP.get__DBComp(this.FIELDNAME_MENGE_ABSCHLUSS_VON).set_cActualMaskValue("");
				oMAP.get__DBComp(this.FIELDNAME_MENGE_ABSCHLUSS_AM).set_cActualMaskValue("");
			}
			
			
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			if (oMAP.get_bActualDBValue(this.FIELDNAME_MENGE_ABSCHLUSS))
			{
				this.set_Enable_Relevant_MaskComponents(oMAP, false);
			}
		}
		
		//2014-03-17: sorten werden bereits bei der mengenfreigabe gesperrt
		this.pruefe_SortenFelder_nachStatus(oMAP);

		return oMV_Rueck;
	}

	
	private boolean isWertKorrekt(String cFeldWert) throws myException
	{
		return (S.isFull(cFeldWert) && bibALL.isNumber(cFeldWert, true));
	}
	
	
	
	private void set_Enable_Relevant_MaskComponents(E2_ComponentMAP oMAP, boolean bEnabled) throws myException
	{
		oMAP.set_ActiveADHOC(this.FIELDNAME_PLAN_MENGE, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELDNAME_LADE_MENGE, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELDNAME_ABLADE_MENGE, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELDNAME_CB_NORMALE_ABRECHNUNG, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM, bEnabled, false);
		
		//sicherstellen, dass da die settings der mengefelder korrekt stehen
		new FU_Set_And_Valid_BuchMge_ist_LadeMge(this.DEF_QUELLE_ZIEL).make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);
		
	}
	

	/*
	 * felder, die nur geoeffnet werden duerfen, wenn beide seiten entsprechend stehen
	 */
	private void pruefe_SortenFelder_nachStatus(E2_ComponentMAP oMAP) throws myException {
		
		/*
		 * ist eine der beiden mengenpruefungen erfolgt, dann bleibt die hautpsorte und die ladesortenbez geschlossen
		 */
		if (oMAP.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$PRUEFUNG_LADEMENGE) || oMAP.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$PRUEFUNG_ABLADEMENGE)) {
			oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL,false,false);
			oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL_BEZ_EK,false,false);
		}
		
		//die ablade-sortenbez. kann offen bleiben
		if (oMAP.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$PRUEFUNG_ABLADEMENGE)) {
			oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL_BEZ_VK,false,false);
		}
	}
	
	
}
