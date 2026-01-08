package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.math.BigDecimal;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PreisCalculator;

public class KFIX_P___calculate_fixierungs_preis {

	private RB_ComponentMap comp_map;

	public KFIX_P___calculate_fixierungs_preis(RB_ComponentMap  p_componentMap) throws myException {
		super();		
		this.comp_map = p_componentMap;

		MyE2_MessageVector mv = new MyE2_MessageVector();

		MyBigDecimal boersenkurs_in_dollar = new MyBigDecimal(this.comp_map.getRbComponent(VPOS_KON.boersenkurs).get__actual_maskstring_in_view());

		MyBigDecimal wechselkurs_dollar_in_euro = new MyBigDecimal(this.comp_map.getRbComponent(VPOS_KON.wechselkurs).get__actual_maskstring_in_view());

		MyBigDecimal abweichung_in_proz = new MyBigDecimal(this.comp_map.getRbComponent(VPOS_KON.boerse_diff_proz).get__actual_maskstring_in_view());

		MyBigDecimal abweichung_in_abs = new MyBigDecimal(this.comp_map.getRbComponent(VPOS_KON.boerse_diff_abs).get__actual_maskstring_in_view());

		boolean preisManuell = this.comp_map.getRbComponent(VPOS_KON.preis_manuell).get__actual_maskstring_in_view().equals("Y")?true:false;

		if(!preisManuell){
			if(boersenkurs_in_dollar.isOK() && wechselkurs_dollar_in_euro.isOK()){

				MyBigDecimal erg1 = conversion_dollar_euro(boersenkurs_in_dollar, wechselkurs_dollar_in_euro);

				MyBigDecimal erg2 = null;
				if(abweichung_in_proz.isOK()){
					erg2 = abweichung_prozent(erg1, abweichung_in_proz);
				}else{
					erg2= new MyBigDecimal(erg1.get_bdWert());
				}

				MyBigDecimal erg3 = null;
				if(abweichung_in_abs.isOK()){
					erg3 = abweichung_absolut(erg2, abweichung_in_abs);
				}else{
					erg3 = new MyBigDecimal(erg2.get_bdWert());
				}

				this.comp_map.getRbComponent(VPOS_KON.einzelpreis).rb_set_db_value_manual(erg3.get_FormatedRoundedNumber(2));


			}else{
				mv._addAlarm("#--#Problem mit der Einzelpreis Rechnung. Bitte kontrollieren Sie alle Parametern");
			}	
		}
		if(mv.get_bIsOK()){
			calculate_standard_preis();

			this.comp_map.getRbComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_GRID_INFO.key()).rb_set_db_value_manual(this.comp_map.getRbComponent(VPOS_KON.id_vkopf_kon).get__actual_maskstring_in_view());
		}else{
			bibMSG.add_MESSAGE(mv);
		}
	}

	private MyBigDecimal conversion_dollar_euro(MyBigDecimal boersenkurs_in_dollar, MyBigDecimal wechselkurs_dollar_in_euro)throws myException{
		if(boersenkurs_in_dollar.isOK() && wechselkurs_dollar_in_euro.isOK()){
			return new MyBigDecimal(MyBigDecimal.multiplicate(boersenkurs_in_dollar.get_bdWert(), wechselkurs_dollar_in_euro.get_bdWert(), BigDecimal.ROUND_UNNECESSARY));
		}else{
			return new MyBigDecimal(0);
		}
	}

	private MyBigDecimal abweichung_prozent(MyBigDecimal bd_in, MyBigDecimal abweichung_in_proz) throws myException{
		if(abweichung_in_proz.isOK()){
			////			a=(bd_in*(1+(abweichung_in_proz/100)))
			//			BigDecimal b = new MyBigDecimal(1).add_to_me(MyBigDecimal.divide(abweichung_in_proz.get_bdWert(), new MyBigDecimal(100).get_bdWert(), BigDecimal.ROUND_UNNECESSARY));
			//			
			//			MyBigDecimal a = new MyBigDecimal( bd_in.add_to_me(b));

			MyBigDecimal a = new MyBigDecimal(bd_in.add_to_me(new MyBigDecimal(MyBigDecimal.divide(
					MyBigDecimal.multiplicate(bd_in.get_bdWert(), abweichung_in_proz.get_bdWert(), BigDecimal.ROUND_UNNECESSARY), 
					new MyBigDecimal(100).get_bdWert(), BigDecimal.ROUND_UNNECESSARY) 
					)));

			return a;
		}else{
			return new MyBigDecimal(0);
		}
	}

	private MyBigDecimal abweichung_absolut(MyBigDecimal bd_in, MyBigDecimal abweichung_absolut) throws myException{
		if(abweichung_absolut.isOK()){
			return new MyBigDecimal(bd_in.add_to_me(abweichung_absolut));		
		}else{
			return new MyBigDecimal(0);
		}
	}

	private void calculate_standard_preis() throws myException{
		MyBigDecimal bdAnzahl = 		new MyBigDecimal(this.comp_map.getRbComponent(VPOS_KON.anzahl).get__actual_maskstring_in_view());
		MyBigDecimal bdEpreis = 		new MyBigDecimal(this.comp_map.getRbComponent(VPOS_KON.einzelpreis).get__actual_maskstring_in_view());
		MyBigDecimal bdMengeDiv = 		new MyBigDecimal(this.comp_map.getRbComponent(VPOS_KON.mengendivisor).get__actual_maskstring_in_view());
		MyBigDecimal bdWaerungsKurs = 	new MyBigDecimal(this.comp_map.getRbComponent(VPOS_KON.waehrungskurs).get__actual_maskstring_in_view());

		//		String einzelpreis_waehrung = new Rec20(_TAB.waehrung)._fill_id(maskController.get_liveVal(tab_key, VPOS_KON.id_waehrung_fremd)).get_fs_dbVal(WAEHRUNG.kurzbezeichnung);

		if (bdAnzahl.isNotOK()) 		{
			bdAnzahl = new MyBigDecimal(0); 
		}
		if (bdEpreis.isNotOK()) 		{
			bdEpreis = new MyBigDecimal(0); 
		}
		if (bdMengeDiv.isNotOK()) 		{
			bdMengeDiv = new MyBigDecimal(1); 
		}
		if (bdWaerungsKurs.isNotOK()) 	{
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

		this.comp_map.getRbComponent(VPOS_RG.gesamtpreis)		.rb_set_db_value_manual(MyNumberFormater.formatDez(D_Gesamtpreis,2,true));
		this.comp_map.getRbComponent( VPOS_RG.einzelpreis_fw)	.rb_set_db_value_manual(MyNumberFormater.formatDez(D_EPreis_FW,2,true));
		this.comp_map.getRbComponent( VPOS_RG.gesamtpreis_fw)	.rb_set_db_value_manual(MyNumberFormater.formatDez(D_Gesamtpreis_FW,2,true));
	}

}
