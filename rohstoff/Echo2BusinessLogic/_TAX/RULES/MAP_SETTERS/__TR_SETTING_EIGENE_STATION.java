package rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TAX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_Auswahl_Y_N_EGAL;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__CONST;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__MASK_CompSteuerDropDown;

public class __TR_SETTING_EIGENE_STATION extends XX_MAP_Set_And_Valid
{

	private MyE2_DB_CheckBox  			cbFirmaIstMandant = 	null;
	private MyE2_DB_SelectField  		selZuSetzen = 			null;
	private MyE2IF__DB_Component    	selFieldSteuer =	 		null;
	private MyE2IF__DB_Component    	selFieldSteuerNeg =	 		null;
	private TAX__DD_Auswahl_Y_N_EGAL   	selSteuerTeilnehmer =	 	null;
	
	private boolean bEK = true;
	
	private String[] arrWerteEK_ColorChange = {	_DB.HANDELSDEF$ID_LAND_QUELLE_JUR,
												_DB.HANDELSDEF$ID_LAND_QUELLE_GEO, 
												_DB.HANDELSDEF$QUELLE_IST_MANDANT,
												_DB.HANDELSDEF$SORTE_RC_QUELLE,
												_DB.HANDELSDEF$SORTE_PRODUKT_QUELLE,
												_DB.HANDELSDEF$SORTE_EOW_QUELLE,
												_DB.HANDELSDEF$SORTE_DIENSTLEIST_QUELLE,
												_DB.HANDELSDEF$UST_TEILNEHMER_QUELLE,
												_DB.HANDELSDEF$ID_TAX_QUELLE,
												_DB.HANDELSDEF$ID_TAX_NEGATIV_QUELLE,
												_DB.HANDELSDEF$INTRASTAT_MELD_IN,
												_DB.HANDELSDEF$TRANSIT_EK,
												TR__CONST.KEY_MASK_TITEL_EK,
												TR__CONST.KEY_MASK_SORTENTYPBLOCK_EK}; 

	private String[] arrWerteVK_ColorChange = {	_DB.HANDELSDEF$ID_LAND_ZIEL_JUR,
												_DB.HANDELSDEF$ID_LAND_ZIEL_GEO, 
												_DB.HANDELSDEF$ZIEL_IST_MANDANT,
												_DB.HANDELSDEF$SORTE_RC_ZIEL,
												_DB.HANDELSDEF$SORTE_PRODUKT_ZIEL,
												_DB.HANDELSDEF$SORTE_EOW_ZIEL,
												_DB.HANDELSDEF$SORTE_DIENSTLEIST_ZIEL,
												_DB.HANDELSDEF$UST_TEILNEHMER_ZIEL,
												_DB.HANDELSDEF$ID_TAX_ZIEL,
												_DB.HANDELSDEF$ID_TAX_NEGATIV_ZIEL,
												_DB.HANDELSDEF$INTRASTAT_MELD_OUT,
												_DB.HANDELSDEF$TRANSIT_VK,
												TR__CONST.KEY_MASK_TITEL_VK,
												TR__CONST.KEY_MASK_SORTENTYPBLOCK_VK}; 

	private E2_ComponentMAP 	o_MAP = null;  
	
	
	public __TR_SETTING_EIGENE_STATION(String EK_VK, E2_ComponentMAP oMAP) throws myException
	{
		super();
		
		this.o_MAP = oMAP;
		
		this.bEK = (EK_VK.equals("EK"));
		
		if (EK_VK.equals("EK"))
		{
			this.cbFirmaIstMandant = (MyE2_DB_CheckBox)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__QUELLE_IST_MANDANT);
			this.selZuSetzen = (MyE2_DB_SelectField)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ID_LAND_QUELLE_JUR);
			//20180711: aenderung wegen gruppierten steuern// this.selFieldSteuer = (MyE2_DB_SelectField)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ID_TAX_QUELLE);
			this.selFieldSteuer = (MyE2IF__DB_Component)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ID_TAX_QUELLE);
			this.selFieldSteuerNeg = (MyE2IF__DB_Component)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ID_TAX_NEGATIV_QUELLE);
			this.selSteuerTeilnehmer =(TAX__DD_Auswahl_Y_N_EGAL)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__UST_TEILNEHMER_QUELLE);
		}
		else
		{
			this.cbFirmaIstMandant = (MyE2_DB_CheckBox)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ZIEL_IST_MANDANT);
			this.selZuSetzen = (MyE2_DB_SelectField)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ID_LAND_ZIEL_JUR);
			//20180711: aenderung wegen gruppierten steuern// this.selFieldSteuer = (MyE2_DB_SelectField)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ID_TAX_ZIEL);
			this.selFieldSteuer = (MyE2IF__DB_Component)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ID_TAX_ZIEL);
			this.selFieldSteuerNeg = (MyE2IF__DB_Component)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__ID_TAX_NEGATIV_ZIEL);
			this.selSteuerTeilnehmer =(TAX__DD_Auswahl_Y_N_EGAL)oMAP.get__DBComp(RECORD_HANDELSDEF.FIELD__UST_TEILNEHMER_ZIEL);
		}
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP,  int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this._setting(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this._setting(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this._setting(oMAP, ActionType);
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
	
	private MyE2_MessageVector _setting(E2_ComponentMAP oMAP, int ActionType) throws myException
	{	
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		RECORD_MANDANT    recMandant = new RECORD_MANDANT(bibALL.get_ID_MANDANT());
		
		String cID_LandMandant_F = 		recMandant.get_ID_LAND_cF_NN("");
		String c_ID_LandImDropDown_F = 	this.selZuSetzen.get_ActualWert();
		
		
		//moegliche Fehler-Szenarien
		boolean bMandantHatKeinLand = 												false;
		boolean bEsExistiertKeinProformaLagerSteuersatz =	 						false;
		boolean bEsExistierenMehrereProformaLagerSteuersaetze = 					false;
		boolean bSchalterEigeneFirmaIstGesetzt_ABER_LandFirmaNichtMandantenLand = 	false;
		boolean bSchalterEigeneFirmaIstGesetzt_ABER_SteuerIstNichtProforma = 		false;
		boolean bSchalterEigeneFirmaIstGesetzt = 									false;

		//Szenarien fuellen
		bMandantHatKeinLand = S.isEmpty(cID_LandMandant_F);
		
		RECLIST_TAX  recListTaxWithLAGER_TAX = new RECLIST_TAX("SELECT * FROM "+bibE2.cTO()+".JT_TAX WHERE "+RECORD_TAX.FIELD__TAXTYP+"='"+TAX_CONST.TAXTYP_NULL_LAGERSEITE+"'");
		
		bEsExistiertKeinProformaLagerSteuersatz = (recListTaxWithLAGER_TAX.get_vKeyValues().size()==0);
		bEsExistierenMehrereProformaLagerSteuersaetze = (recListTaxWithLAGER_TAX.get_vKeyValues().size()>1);
	
		String cID_SteuerProforma_cF = null;
		if (recListTaxWithLAGER_TAX.get_vKeyValues().size()==1)
		{
			cID_SteuerProforma_cF = recListTaxWithLAGER_TAX.get(0).get_ID_TAX_cF();
		}
		
		if (this.cbFirmaIstMandant.isSelected() && !(S.NN(this.selFieldSteuer.get_cActualDBValueFormated()).equals(cID_SteuerProforma_cF)))
		{
			bSchalterEigeneFirmaIstGesetzt_ABER_SteuerIstNichtProforma = true;
		}
		
		if (this.cbFirmaIstMandant.isSelected())
		{
			bSchalterEigeneFirmaIstGesetzt = true;
		}
		
		if (S.isFull(cID_LandMandant_F) && this.cbFirmaIstMandant.isSelected() && !c_ID_LandImDropDown_F.equals(cID_LandMandant_F))
		{
			bSchalterEigeneFirmaIstGesetzt_ABER_LandFirmaNichtMandantenLand=true;
		}
		
		
		//jetzt die reaktion, abhaengig vom einsatz
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			if (bMandantHatKeinLand)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte stellen Sie sicher, dass im Mandantenstamm bei Ihrer Firma das korrekte Land erfasst ist !!")));
				return oMV;
			}
			
			if (bEsExistiertKeinProformaLagerSteuersatz)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte stellen Sie sicher, dass bei den Steuersätzen ein proforma-Steuersatz für die Lagerseite erfasst ist mit 0% !!")));
				return oMV;
			}

			if (bEsExistierenMehrereProformaLagerSteuersaetze)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte stellen Sie sicher, dass bei den Steuersätzen NUR EIN proforma-Steuersatz für die Lagerseite erfasst ist mit 0% !!")));
				return oMV;
			}
		
			if (bSchalterEigeneFirmaIstGesetzt_ABER_LandFirmaNichtMandantenLand)
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Wenn die eigene Firma "+(this.bEK?"Quellseite":"Zielseite")+" ist, dann muss das Land (jur.) das Mandantenland sein")));
				this.selZuSetzen.set_ActiveValue(cID_LandMandant_F);
			}
		
			if (bSchalterEigeneFirmaIstGesetzt_ABER_SteuerIstNichtProforma)
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Wenn die eigene Firma "+(this.bEK?"Quellseite":"Zielseite")+" ist, dann muss im Steuersatz ein Proforma-Steuersatz mit 0 stehen")));
				//20180711: aenderung wegen gruppierten steuern// this.selFieldSteuer.set_ActiveValue(cID_SteuerProforma_cF);
				this.selFieldSteuer.set_cActualMaskValue(cID_SteuerProforma_cF);
			}

			//bei eigener Firma wird der negativ-steuersatz leer
			if (bSchalterEigeneFirmaIstGesetzt) {
				if (S.isAllFull(this.selFieldSteuerNeg.get_cActualMaskValue())) {
					this.selFieldSteuerNeg.set_cActualMaskValue("");
					oMV._addInfo("Bei eigener Firma ist die Steuer für negative Preise leer !");
				}
			}
			
			if (this.cbFirmaIstMandant.isSelected() && !this.selSteuerTeilnehmer.get_cActualDBValueFormated().equals("1"))
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Wenn die eigene Firma "+(this.bEK?"Quellseite":"Zielseite")+" ist, dann muss der Schalter <Umsatzsteuerteilnehmer> gesetzt sein")));
				this.selSteuerTeilnehmer.set_ActiveValue("1");
			}
			
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			//hier nur warnungen
			if (bMandantHatKeinLand)
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Bitte stellen Sie sicher, dass im Mandantenstamm bei Ihrer Firma das korrekte Land erfasst ist !!")));
			}
			
			if (bEsExistiertKeinProformaLagerSteuersatz)
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Bitte stellen Sie sicher, dass bei den Steuersätzen ein proforma-Steuersatz für die Lagerseite erfasst ist mit 0% !!")));
			}

			if (bEsExistierenMehrereProformaLagerSteuersaetze)
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Bitte stellen Sie sicher, dass bei den Steuersätzen NUR EIN proforma-Steuersatz für die Lagerseite erfasst ist mit 0% !!")));
			}
		
			if (bSchalterEigeneFirmaIstGesetzt_ABER_LandFirmaNichtMandantenLand)
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Wenn die eigene Firma "+(this.bEK?"Quellseite":"Zielseite")+" ist, dann muss das Land (jur.) das Mandantenland sein")));
			}
		
			if (bSchalterEigeneFirmaIstGesetzt_ABER_SteuerIstNichtProforma)
			{
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Wenn die eigene Firma "+(this.bEK?"Quellseite":"Zielseite")+" ist, dann muss im Steuersatz ein Proforma-Steuersatz mit 0 stehen")));
			}
			//bei eigener Firma wird der negativ-steuersatz leer
			if (bSchalterEigeneFirmaIstGesetzt) {
				if (S.isAllFull(this.selFieldSteuerNeg.get_cActualMaskValue())) {
					this.selFieldSteuerNeg.set_cActualMaskValue("");
					oMV._addInfo("Bei eigener Firma ist die Steuer für negative Preise leer !");
				}
			}

			
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION)
		{
			if (bMandantHatKeinLand)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte stellen Sie sicher, dass im Mandantenstamm bei Ihrer Firma das korrekte Land erfasst ist !!")));
			}
			
			if (bEsExistiertKeinProformaLagerSteuersatz)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte stellen Sie sicher, dass bei den Steuersätzen ein proforma-Steuersatz für die Lagerseite erfasst ist mit 0% !!")));
			}

			if (bEsExistierenMehrereProformaLagerSteuersaetze)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte stellen Sie sicher, dass bei den Steuersätzen NUR EIN proforma-Steuersatz für die Lagerseite erfasst ist mit 0% !!")));
			}
		
			if (bSchalterEigeneFirmaIstGesetzt_ABER_LandFirmaNichtMandantenLand)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn die eigene Firma "+(this.bEK?"Quellseite":"Zielseite")+" ist, dann muss das Land (jur.) das Mandantenland sein")));
			}
		
			if (bSchalterEigeneFirmaIstGesetzt_ABER_SteuerIstNichtProforma)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn die eigene Firma "+(this.bEK?"Quellseite":"Zielseite")+" ist, dann muss im Steuersatz ein Proforma-Steuersatz mit 0 stehen")));
			}
			
			if (this.cbFirmaIstMandant.isSelected() && !this.selSteuerTeilnehmer.get_ActualWert().equals("1"))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn die eigene Firma "+(this.bEK?"Quellseite":"Zielseite")+" ist, dann muss der Schalter <Umsatzsteuerteilnehmer> gesetzt sein")));
			}
			
			//bei eigener Firma wird der negativ-steuersatz leer
			if (bSchalterEigeneFirmaIstGesetzt) {
				if (S.isAllFull(this.selFieldSteuerNeg.get_cActualMaskValue())) {
					this.selFieldSteuerNeg.set_cActualMaskValue("");
					oMV._addInfo("Bei eigener Firma ist die Steuer für negative Preise leer !");
				}
			}

		}

		
		//jetzt die seite mit dem mandanten heller
		if (this.bEK) {
			this.defFarbe(this.arrWerteEK_ColorChange, new E2_ColorBase());
		} else {
			this.defFarbe(this.arrWerteVK_ColorChange, new E2_ColorBase());
		}
		if (this.cbFirmaIstMandant.isSelected()) {
			this.defFarbe(this.bEK?this.arrWerteEK_ColorChange:this.arrWerteVK_ColorChange, new E2_ColorMaskHighlight());
		}
		
		
		//hier die steuerinfofelder nachziehen
		new __TR_MapSetting_Steuerinfos().show_steuerText_Konto(oMAP, ActionType);

		
		return oMV;
			
	}
	
	
	
	private void defFarbe(String[] arrayFeldNamen, Color oColNew) throws myException {
		for (int i=0;i<arrayFeldNamen.length;i++) {
			Component  ocomp = this.o_MAP.get_Comp(arrayFeldNamen[i]);
			this.set_LayoutDataMandanten(ocomp, oColNew);
		}
	}
	
	
	
	
	private void set_LayoutDataMandanten(Component o_Comp, Color oColNew) {
		Component oComp = o_Comp;
		if (o_Comp instanceof TR__MASK_CompSteuerDropDown) {
			oComp = ((TR__MASK_CompSteuerDropDown)o_Comp).get_GridContainer();
		}
		
		LayoutData oLayout = oComp.getLayoutData();
		
		if (oLayout != null && oLayout instanceof GridLayoutData) {
			GridLayoutData oGLNew = new GridLayoutData();
			
			oGLNew.setAlignment( ((GridLayoutData)oLayout).getAlignment());
			oGLNew.setColumnSpan( ((GridLayoutData)oLayout).getColumnSpan());
			oGLNew.setInsets( ((GridLayoutData)oLayout).getInsets());
			oGLNew.setRowSpan(((GridLayoutData)oLayout).getRowSpan());
			oGLNew.setBackground(oColNew);
			
			oComp.setLayoutData(oGLNew);
		}
	}
	
}
