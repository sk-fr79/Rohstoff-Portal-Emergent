package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PreisCalculator;

public class KFIX_P_M_BT_Neuberechnung extends MyE2_Button implements IF_RB_Component {

	private RB_KF key;
	
	public KFIX_P_M_BT_Neuberechnung(RB_ComponentMap oCompMap) throws myException {
		super("Neuberechnung");
		this._ttt("Neuberechnung");
		this._aaa(new ActionCalculatePosition(oCompMap));
	}
	
	
	private class ActionCalculatePosition extends XX_ActionAgent
	{

		public ActionCalculatePosition(RB_ComponentMap omap) 
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			try 	{
		
				//neu: 
				/**
				 * korrektur wegen bug beim klick auf berechnung
				 */
				RB_KM tab_key = _TAB.vpos_kon.rb_km();
				KFIX_P_M__MaskController maskController = new KFIX_P_M__MaskController(KFIX_P_M_BT_Neuberechnung.this._find_componentMapCollector_i_belong_to());
				
				MyBigDecimal bdAnzahl = 		maskController.get_MyBigDecimal_liveVal(tab_key, VPOS_KON.anzahl);
				MyBigDecimal bdEpreis = 		maskController.get_MyBigDecimal_liveVal(tab_key, VPOS_KON.einzelpreis);
				MyBigDecimal bdMengeDiv = 		maskController.get_MyBigDecimal_liveVal(tab_key, VPOS_KON.mengendivisor);
				MyBigDecimal bdWaerungsKurs = 	maskController.get_MyBigDecimal_liveVal(tab_key, VPOS_KON.waehrungskurs);
	
				MyLong id_vkopf_kon	= 			maskController.get_MyLong_liveVal(tab_key, VPOS_KON.id_vkopf_kon);
				
				String einzelpreis_waehrung = new Rec20(_TAB.waehrung)._fill_id(maskController.get_liveVal(tab_key, VPOS_KON.id_waehrung_fremd)).get_fs_dbVal(WAEHRUNG.kurzbezeichnung);
				
				if (bdAnzahl==null) 		{
					bdAnzahl = new MyBigDecimal(0); 
				}
				if (bdEpreis==null) 		{
					bdEpreis = new MyBigDecimal(0); 
				}
				if (bdMengeDiv==null) 		{
					bdMengeDiv = new MyBigDecimal(1); 
				}
				if (bdWaerungsKurs==null) 	{
					bdWaerungsKurs = new MyBigDecimal(1); 
				}

				BS_PreisCalculator oPreisCalc = new BS_PreisCalculator(
									bdAnzahl.get_FormatedRoundedNumber(3),
									bdEpreis.get_FormatedRoundedNumber(2),
									bdMengeDiv.get_FormatedRoundedNumber(0),
									bdWaerungsKurs.get_FormatedRoundedNumber(4),true);
				Double D_Gesamtpreis = oPreisCalc.get_dGesamtPreis().doubleValue();
				Double D_EPreis_FW   =  oPreisCalc.get_dEinzelPreis_FW().doubleValue();
				Double D_Gesamtpreis_FW = oPreisCalc.get_dGesamtPreis_FW().doubleValue();
				
				MyE2_MessageVector mv = new MyE2_MessageVector();
				maskController.set_maskVal(tab_key, VPOS_KON.gesamtpreis,MyNumberFormater.formatDez(D_Gesamtpreis,2,true), mv);
				maskController.set_maskVal(tab_key, VPOS_KON.einzelpreis_fw,MyNumberFormater.formatDez(D_EPreis_FW,2,true), mv);
				maskController.set_maskVal(tab_key, VPOS_KON.gesamtpreis_fw,MyNumberFormater.formatDez(D_Gesamtpreis_FW,2,true), mv);
				
				maskController.set_maskVal(tab_key, KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_GRID_INFO.key(), id_vkopf_kon.get_cUF_LongString(), mv);
				
			} 
			catch (myException e) 
			{
				e.printStackTrace();
				if (!oExecInfo.get_bEventWasPassive())   // sonst werden Fehlermeldungen mehrmals angezeigt
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte füllen Sie die Felder Anzahl, Einzelpreis und Fremdwährungskurs korrekt aus !"));
				}
			}
		}
		
	}



	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void mark_MustField() throws myException {}

	@Override
	public void mark_Disabled() throws myException {}

	@Override
	public void mark_FalseInput() throws myException {}

	@Override
	public void set_Alignment(Alignment align) throws myException {}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {}

	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return new Vector<RB_Validator_Component>();
	}

}
