package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU__PRUEFBLOCK_MengenEingabe extends MyE2_Row_EveryTimeEnabled
{
	//felder, die kontrolliert werden mit dem status der pruefung
//	private String  			cFIELDNAME_PLAN_MENGE = null;
//	private String  			cFIELDNAME_LADE_MENGE = null;
//	private String  			cFIELDNAME_ABLADE_MENGE = null;
//	private String    			cFIELDNAME_CB_NORMALE_ABRECHNUNG = null;    //schalter fuer Buchungsmenge=Lademengen usw
	
	private SQLFieldMAP  	   	oFM = null;	
	private E2_ComponentMAP    	oMap = null;
	
	private String   		   	cFIELDNAME_GEPRUEFT = null;
	private String   		   	cFIELDNAME_GEPRUEFT_VON = null;
	private String   		   	cFIELDNAME_GEPRUEFT_AM = null;
	
//	private String              cFIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM = null;
	
	private boolean    		   	bEK = false;
	private MyE2_DB_CheckBox  	oCB_Pruefung = null;
	private MyE2_DB_TextField   oTF_geprueft_am = null;
	private MyE2_DB_TextField   oTF_geprueft_von = null;

	

	public MyE2_DB_CheckBox get_oCB_Pruefung() 
	{
		return oCB_Pruefung;
	}


	/**
	 * 
	 * @param cfieldname_geprueft
	 * @param cfieldname_geprueft_von
	 * @param cfieldname_geprueft_am
	 * @param FM
	 * @param map
	 * @param tfPLAN_MENGE
	 * @param tfLADE_MENGE
	 * @param tfABLADE_MENGE
	 * @param cbNormaleAbrechnung
	 * @param EK
	 * @throws myException
	 */
	public FU__PRUEFBLOCK_MengenEingabe(	String 				cfieldname_geprueft,
											String 				cfieldname_geprueft_von, 
											String 				cfieldname_geprueft_am,
											String    			cfieldname_cb_normale_abrechnung,
											String    			cfieldname_leistungsdatum,
											SQLFieldMAP  	   	FM, 
											E2_ComponentMAP 	map, 
											String 				tfPLAN_MENGE,
											String 				tfLADE_MENGE, 
											String 				tfABLADE_MENGE,
											boolean             EK) throws myException
	{
		super();
		this.cFIELDNAME_GEPRUEFT = cfieldname_geprueft;
		this.cFIELDNAME_GEPRUEFT_VON = cfieldname_geprueft_von;
		this.cFIELDNAME_GEPRUEFT_AM = cfieldname_geprueft_am;
//		this.cFIELDNAME_CB_NORMALE_ABRECHNUNG = cfieldname_cb_normale_abrechnung;
//		this.cFIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM = cfieldname_leistungsdatum;
		this.oFM = FM;
		this.oMap = map;
//		this.cFIELDNAME_PLAN_MENGE = tfPLAN_MENGE;
//		this.cFIELDNAME_LADE_MENGE = tfLADE_MENGE;
//		this.cFIELDNAME_ABLADE_MENGE = tfABLADE_MENGE;
		this.bEK = EK;
		
		this.oCB_Pruefung = new MyE2_DB_CheckBox(oFM.get_(cFIELDNAME_GEPRUEFT));
		this.oTF_geprueft_von=new MyE2_DB_TextField(oFM.get_(cFIELDNAME_GEPRUEFT_VON),false,30,10,true,new E2_FontPlain(-2));
		this.oTF_geprueft_am= new MyE2_DB_TextField(oFM.get_(cFIELDNAME_GEPRUEFT_AM),false,60,10,true,new E2_FontPlain(-2));
		this.oMap.add_Component(this.oCB_Pruefung, new MyE2_String("Mengen als geprüft markieren"));
		this.oMap.add_Component(this.oTF_geprueft_von, new MyE2_String("Mengen wurden von <Kürzel> geprüft"));
		this.oMap.add_Component(this.oTF_geprueft_am, new MyE2_String("Mengen wurden geprüft am"));
		
		oCB_Pruefung.add_GlobalAUTHValidator_AUTO(bEK?"MENGENFREIGABE_EK":"MENGENFREIGABE_VK");
//		oCB_Pruefung.add_GlobalValidator(new ownValidator());
//		oCB_Pruefung.add_oActionAgent(new ownActionAgent());
		
		//pro-forma-actionAgent wegen des validators
		oCB_Pruefung.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
			}
		});
		
		this.add(this.oCB_Pruefung,E2_INSETS.I_0_1_2_1);
		this.add(new MyE2_Label(new MyE2_String("von:")),E2_INSETS.I_0_1_2_1);
		this.add(this.oMap.get_Comp(this.cFIELDNAME_GEPRUEFT_VON ),E2_INSETS.I_0_1_2_1);
		this.add(new MyE2_Label(new MyE2_String("am:")),E2_INSETS.I_0_1_2_1);
		this.add(this.oMap.get_Comp(this.cFIELDNAME_GEPRUEFT_AM ),E2_INSETS.I_0_1_2_1);
		
		
	}

	
//	private class ownValidator extends XX_ActionValidator
//	{
//		@Override
//		public MyE2_MessageVector isValid(Component componentWhichIsValidated) throws myException
//		{
//			MyE2_MessageVector  oMV = new MyE2_MessageVector();
//			FU__PRUEFBLOCK_MengenEingabe oThis = FU__PRUEFBLOCK_MengenEingabe.this;
//			
//			if (oThis.oCB_Pruefung.isSelected())
//			{
//				MyE2_DB_CheckBox  cbNormaleAbrechnung = (MyE2_DB_CheckBox)oThis.oMap.get__Comp(oThis.cFIELDNAME_CB_NORMALE_ABRECHNUNG);
//				
//				if (oThis.bEK)
//				{
//					BigDecimal  bdPlan = oThis.oMap.get_bdActualDBValue(oThis.cFIELDNAME_PLAN_MENGE, null, null);
//					BigDecimal  bdLade = oThis.oMap.get_bdActualDBValue(oThis.cFIELDNAME_LADE_MENGE, null, null);
//					BigDecimal  bdAbLade = oThis.oMap.get_bdActualDBValue(oThis.cFIELDNAME_ABLADE_MENGE, null, null);
//					
//					MyDate      dateLeistungsDatum = oThis.oMap.get_ActualMyDate(oThis.cFIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM,true,true,null);
//									
//					if (bdPlan==null || bdLade == null || dateLeistungsDatum==null || dateLeistungsDatum.get_cUmwandlungsergebnis()==null || (bdAbLade == null && !cbNormaleAbrechnung.isSelected()) ) 
//					{
//						oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst Mengen und Ladedatum eintragen !!!"));
//					}
//				}
//				else
//				{
//					BigDecimal  bdPlan = oThis.oMap.get_bdActualDBValue(oThis.cFIELDNAME_PLAN_MENGE, null, null);
//					BigDecimal  bdLade = oThis.oMap.get_bdActualDBValue(oThis.cFIELDNAME_LADE_MENGE, null, null);
//					BigDecimal  bdAblade = oThis.oMap.get_bdActualDBValue(oThis.cFIELDNAME_ABLADE_MENGE, null, null);
//					
//					MyDate      dateLeistungsDatum = oThis.oMap.get_ActualMyDate(oThis.cFIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM,true,true,null);
//					
//					if (bdPlan==null || bdAblade == null || dateLeistungsDatum==null || dateLeistungsDatum.get_cUmwandlungsergebnis()==null || (bdLade == null && !cbNormaleAbrechnung.isSelected())) 
//					{
//						oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst Mengen und Abladedatum eintragen !!!"));
//					}
//				}
//			}
//			return oMV;
//		}
//
//		@Override
//		protected MyE2_MessageVector isValid(String unformated)	throws myException
//		{
//			return null;
//		}
//		
//	}
	
	
//	public void EINSTELLER_ForMapSettingAgent(boolean bAusOberflaechenAktion) throws myException
//	{
//		boolean bFelderSchliessen = this.oCB_Pruefung.isSelected();
//		
//		this.oMap.get__Comp(this.cFIELDNAME_PLAN_MENGE).EXT().set_bDisabledFromInteractive(bFelderSchliessen);
//		this.oMap.get__Comp(this.cFIELDNAME_ABLADE_MENGE).EXT().set_bDisabledFromInteractive(bFelderSchliessen);
//		this.oMap.get__Comp(this.cFIELDNAME_LADE_MENGE).EXT().set_bDisabledFromInteractive(bFelderSchliessen);
//		this.oMap.get__Comp(this.cFIELDNAME_CB_NORMALE_ABRECHNUNG).EXT().set_bDisabledFromInteractive(bFelderSchliessen);
//		this.oMap.get__Comp(this.cFIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM).EXT().set_bDisabledFromInteractive(bFelderSchliessen);
//		
//		if (bAusOberflaechenAktion)
//		{
//			this.oMap.get__Comp(this.cFIELDNAME_PLAN_MENGE).set_bEnabled_For_Edit(!bFelderSchliessen);
//			this.oMap.get__Comp(this.cFIELDNAME_ABLADE_MENGE).set_bEnabled_For_Edit(!bFelderSchliessen);
//			this.oMap.get__Comp(this.cFIELDNAME_LADE_MENGE).set_bEnabled_For_Edit(!bFelderSchliessen);
//			this.oMap.get__Comp(this.cFIELDNAME_CB_NORMALE_ABRECHNUNG).set_bEnabled_For_Edit(!bFelderSchliessen);
//			this.oMap.get__Comp(this.cFIELDNAME_ZUGEHOERENDES_LEISTUNGSDATUM).set_bEnabled_For_Edit(!bFelderSchliessen);
//		}
//
//		boolean bNormalAbrechnen = ((MyE2_DB_CheckBox)this.oMap.get__Comp(this.cFIELDNAME_CB_NORMALE_ABRECHNUNG)).isSelected();
//		
//		if (!this.oCB_Pruefung.isSelected())   //dann je nach schalter des abrechnungsknopfs das Eingabefeld der gegenmenge oeffnen
//		{
//			if (this.bEK)
//			{
//				this.oMap.get__Comp(this.cFIELDNAME_ABLADE_MENGE).EXT().set_bDisabledFromInteractive(bNormalAbrechnen);
//				if (bAusOberflaechenAktion)
//				{
//					this.oMap.get__Comp(this.cFIELDNAME_ABLADE_MENGE).set_bEnabled_For_Edit(!bNormalAbrechnen);
//				}
//				
//			}
//			else
//			{
//				this.oMap.get__Comp(this.cFIELDNAME_LADE_MENGE).EXT().set_bDisabledFromInteractive(bNormalAbrechnen);
//				if (bAusOberflaechenAktion)
//				{
//					this.oMap.get__Comp(this.cFIELDNAME_LADE_MENGE).set_bEnabled_For_Edit(!bNormalAbrechnen);
//				}
//			}
//		}
//	}
	
//	
//	private class ownActionAgent extends XX_ActionAgent
//	{
//		@Override
//		public void executeAgentCode(ExecINFO execInfo) throws myException
//		{
//			FU__PRUEFBLOCK_MengenEingabe oThis = FU__PRUEFBLOCK_MengenEingabe.this;
//			
//			
//			
//			MyE2_DB_CheckBox oCB = (MyE2_DB_CheckBox)execInfo.get_MyActionEvent().getSource();
//			if (oCB.isSelected())
//			{
//				((MyE2IF__DB_Component)oThis.oMap.get__Comp(oThis.cFIELDNAME_GEPRUEFT_AM)).set_cActualMaskValue(bibALL.get_cDateNOW());
//				((MyE2IF__DB_Component)oThis.oMap.get__Comp(oThis.cFIELDNAME_GEPRUEFT_VON)).set_cActualMaskValue(bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("-"));
//			}
//			else
//			{
//				((MyE2IF__DB_Component)oThis.oMap.get__Comp(oThis.cFIELDNAME_GEPRUEFT_AM)).prepare_ContentForNew(false);
//				((MyE2IF__DB_Component)oThis.oMap.get__Comp(oThis.cFIELDNAME_GEPRUEFT_VON)).prepare_ContentForNew(false);
//			}
//
//			oThis.EINSTELLER_ForMapSettingAgent(true);
//		}
//	}


	public MyE2_DB_TextField get_oTF_geprueft_am()
	{
		return oTF_geprueft_am;
	}


	public MyE2_DB_TextField get_oTF_geprueft_von()
	{
		return oTF_geprueft_von;
	}
	
	

}
