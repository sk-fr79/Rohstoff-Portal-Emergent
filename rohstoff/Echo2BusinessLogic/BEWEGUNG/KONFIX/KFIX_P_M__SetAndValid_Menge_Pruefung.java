package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_AlarmMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_WarningMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M__SetAndValid_Menge_Pruefung extends RB_Mask_Set_And_Valid{

	private RECORD_VPOS_KON 	rec_vpos_kon = null;
//	private MyE2_MessageVector 	omv  	= null;

	private boolean bEK 				= false;
//	private boolean bPrimary 			= false;
	
	
	public KFIX_P_M__SetAndValid_Menge_Pruefung() throws myException {
		super();

	}


	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
		
		MyE2_MessageVector omv 		= new MyE2_MessageVector();
		if(ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION){
			String id = bibALL.convertID2UnformattedID(rbMASK.getRbComponentSavable(VPOS_KON.id_vpos_kon).rb_readValue_4_dataobject());
			if(S.isFull(id)){
				this.rec_vpos_kon 	= new RECORD_VPOS_KON(id);
				this.bEK 	= rbMASK.rb_get_belongs_to().rb_get_maskcontainer_i_belong_to().rb_get_OwnKENNER().equals(MODUL.MODUL_EK_KONTRAKT_MASK.get_callKey())?true:false;
				
				this._pruefen(rbMASK, omv);
			}
		}
		return omv;
	}

	public void _pruefen(RB_ComponentMap rbMASK, MyE2_MessageVector 	omv) throws myException{
		omv.clear();
		//jetzt die menge der zuordnung ermitteln
		double dSummeMengenAusPlanung = 0;
		double dSummeMengeAusFuhren  = 0;
		
		boolean    b_ueberliefert_erlaubt=  ((RB_CheckBox)rbMASK._find_component_in_neighborhood(_TAB.vpos_kon_trakt.rb_km(), VPOS_KON_TRAKT.ueberliefer_ok.fk())).isSelected();
		boolean    b_typ_ladung=  ((RB_CheckBox)rbMASK._find_component_in_neighborhood(_TAB.vpos_kon.rb_km(), VPOS_KON.typ_ladung.fk())).isSelected();

		MyBigDecimal bd_anzahl_maske =      new MyBigDecimal(  ((IF_RB_Component_Savable)rbMASK._find_component_in_neighborhood(_TAB.vpos_kon.rb_km(), VPOS_KON.anzahl.fk())).rb_readValue_4_dataobject());   
		MyBigDecimal bd_toleranz    =       new MyBigDecimal(  ((IF_RB_Component_Savable)rbMASK._find_component_in_neighborhood(_TAB.vpos_kon.rb_km(), VPOS_KON.mengen_toleranz_prozent.fk())).rb_readValue_4_dataobject());
		
		if (bd_anzahl_maske.isOK()) {
		
			Double d_toleranz = 0d;
			if (bd_toleranz.isOK()) {
				d_toleranz = bd_toleranz.get_bdWert().doubleValue();
			}
			
			dSummeMengenAusPlanung += rec_vpos_kon.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk().get_ANZAHL_d_Summe(null);
			dSummeMengenAusPlanung += rec_vpos_kon.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek().get_ANZAHL_d_Summe(null);
			dSummeMengenAusPlanung += rec_vpos_kon.get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_vpos_kon().get_LAGERMENGE_d_Summe(null);
	
			Double d_anzahl_aktuell = bd_anzahl_maske.get_bdWert().doubleValue();
			
			boolean ohne_toleranz_ueberliefert = (dSummeMengenAusPlanung>d_anzahl_aktuell);
			
			
			
			
			
			if (dSummeMengenAusPlanung>d_anzahl_aktuell*(1+d_toleranz/100))	{
				if (b_ueberliefert_erlaubt) {
					omv.add_MESSAGE(
							new MyE2_BASIC_WarningMessageWithAddonComponent(new MyE2_String("Kontraktposition ist in der Planung überliefert, aber erlaubt !"),
									this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Planung überliefert, aber erlaubt !")),
									new Extent(95,Extent.PERCENT),
									new Extent(5,Extent.PERCENT)));
				} else {
					if (!b_typ_ladung) {
						omv.add_MESSAGE(new MyE2_BASIC_AlarmMessageWithAddonComponent(
								new MyE2_String("Kontraktposition ist in der Planung überliefert / !!"),
								this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Planung überliefert !!")),
								new Extent(95,Extent.PERCENT),
								new Extent(5,Extent.PERCENT)));
					} else {
						omv.add_MESSAGE(new MyE2_BASIC_WarningMessageWithAddonComponent(
								new MyE2_String("Kontraktposition ist in der Planung überliefert, aber Kontraktposition ist Fixierung auf Ladung!!"),
								this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Planung überliefert !!")),
								new Extent(95,Extent.PERCENT),
								new Extent(5,Extent.PERCENT)));
					}
				}
			} else if (ohne_toleranz_ueberliefert) {
				omv.add_MESSAGE(new MyE2_BASIC_WarningMessageWithAddonComponent(
						new MyE2_String("Kontraktposition ist in der Planung überliefert aber innerhalb der Toleranz !!"),
						this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Planung überliefert !!")),
						new Extent(95,Extent.PERCENT),
						new Extent(5,Extent.PERCENT)));
			}
	
	
	
			//dubletten pruefen (nur ein EK-VK-Kontrakt in einem zeitraum mit einer sortenbez
			String cPruefQuery = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON " +
					" LEFT OUTER JOIN JT_VPOS_KON_TRAKT ON (JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON )" +
					" LEFT OUTER JOIN JT_VKOPF_KON ON (JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON )" +
					" WHERE " +
					" NVL(JT_VPOS_KON.DELETED,'N')='N' AND" +
					" JT_VKOPF_KON.VORGANG_TYP="+rec_vpos_kon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_VALUE_FOR_SQLSTATEMENT()+" AND "+
					" JT_VPOS_KON_TRAKT.GUELTIG_VON="+rec_vpos_kon.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_VALUE_FOR_SQLSTATEMENT()+" AND "+
					" JT_VPOS_KON_TRAKT.GUELTIG_BIS="+rec_vpos_kon.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_VALUE_FOR_SQLSTATEMENT()+" AND "+
					" JT_VKOPF_KON.ID_ADRESSE="+rec_vpos_kon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_VALUE_FOR_SQLSTATEMENT()+" AND "+
					" JT_VPOS_KON.ID_ARTIKEL_BEZ="+rec_vpos_kon.get_ID_ARTIKEL_BEZ_VALUE_FOR_SQLSTATEMENT();
	
			String cAnzahl = bibDB.EinzelAbfrage(cPruefQuery,"","","");
			if (S.isEmpty(cAnzahl))
			{
				omv.add_MESSAGE(new MyE2_Alarm_Message("Prüfung auf doppelte Kontrakt-Position war nicht moeglich !"));
				return;
			}
	
			if (new Integer(cAnzahl.trim()).intValue()>1)
			{
				omv.add_MESSAGE(new MyE2_Warning_Message("Prüfung auf doppelte Kontrakt-Position: Es gibt bereits einen Kontrakt mit diesen Rahmendaten / Kunde / Zeitraum / Sorte"));
			}
	
	
			RECLIST_VPOS_TPA_FUHRE  		reclistFuhren = null;
			RECLIST_VPOS_TPA_FUHRE_ORT  	reclistFuhrenOrte = rec_vpos_kon.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon(" NVL(DELETED,'N')='N' ", null, true);;
	
			//jetzt die mengen aus den Fuhren und orten checken
			if (this.bEK)
			{
				reclistFuhren = rec_vpos_kon.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(IST_STORNIERT,'N')='N' AND NVL(DELETED,'N')='N'",null,true);
	
				summeFuhren oSum = new summeFuhren(this.bEK);
				reclistFuhren.DoAnyThing(oSum);
				dSummeMengeAusFuhren += oSum.get_dSumme(); 
	
				summeFuhrenOrteEK  oSumOrte = new summeFuhrenOrteEK();
				reclistFuhrenOrte.DoAnyThing(oSumOrte);
				dSummeMengeAusFuhren += oSumOrte.get_dSumme();
			}
			else
			{
				reclistFuhren = rec_vpos_kon.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(IST_STORNIERT,'N')='N' AND NVL(DELETED,'N')='N'",null,true);
	
				summeFuhren oSum = new summeFuhren(this.bEK);
				reclistFuhren.DoAnyThing(oSum);
				dSummeMengeAusFuhren += oSum.get_dSumme();
	
				summeFuhrenOrteVK  oSumOrte = new summeFuhrenOrteVK();
				reclistFuhrenOrte.DoAnyThing(oSumOrte);
				dSummeMengeAusFuhren += oSumOrte.get_dSumme();
			}
	
			if (dSummeMengeAusFuhren>d_anzahl_aktuell*(1+d_toleranz/100))
			{
				if (b_ueberliefert_erlaubt) 	{
					omv.add_MESSAGE(
					new MyE2_BASIC_WarningMessageWithAddonComponent(
									new MyE2_String("Kontraktposition ist in der Fuhrenmenge überliefert, aber erlaubt !"),
									this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Fuhrenmenge überliefert !!")),
									new Extent(95,Extent.PERCENT),
									new Extent(5,Extent.PERCENT)
									));
				} else {
					omv.add_MESSAGE(
							new MyE2_BASIC_AlarmMessageWithAddonComponent(
									new MyE2_String("Kontraktposition ist in der Fuhrenmenge überliefert !!"),
									this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Fuhrenmenge überliefert !!")),
									new Extent(95,Extent.PERCENT),
									new Extent(5,Extent.PERCENT)
									));
				}
			}
		}

	}


	private class summeFuhren extends RECLIST_VPOS_TPA_FUHRE.DoAnyThingWithAll
	{
		private boolean EK = true;

		private double dSumme = 0;

		public summeFuhren(boolean bek)
		{
			super();
			EK = bek;
		}


		@Override
		public void doAnyThingWith(String hashKey, RECORD_VPOS_TPA_FUHRE orecord) throws myException
		{
			if (orecord.is_DELETED_NO() && orecord.is_IST_STORNIERT_NO())
			{
				if (EK)
				{
					double dPlanMenge = orecord.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0));
					double dLadeMenge = orecord.get_ANTEIL_LADEMENGE_LIEF_dValue(new Double(0));
					double dAbladeMenge = orecord.get_ANTEIL_ABLADEMENGE_LIEF_dValue(new Double(0));

					if (orecord.is_LADEMENGE_GUTSCHRIFT_YES())
					{
						dSumme += (dLadeMenge>0?dLadeMenge:dPlanMenge);
					}
					else
					{
						dSumme += (dAbladeMenge>0?dAbladeMenge:dPlanMenge);
					}
				}
				else
				{
					/*
					 * da auf der abladeseite zuerst mal keine planmenge steht (bis zur vollstaendigen verteilen der Mengen)
					 * wird die planmenge der lieferseite im falle es null ist, genommen
					 */
					double dPlanMenge = orecord.get_ANTEIL_PLANMENGE_ABN_dValue(orecord.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0)));
					double dLadeMenge = orecord.get_ANTEIL_LADEMENGE_ABN_dValue(new Double(0));
					double dAbladeMenge = orecord.get_ANTEIL_ABLADEMENGE_ABN_dValue(new Double(0));

					if (orecord.is_ABLADEMENGE_RECHNUNG_YES())
					{
						dSumme += (dAbladeMenge>0?dAbladeMenge:dPlanMenge);
					}
					else
					{
						dSumme += (dLadeMenge>0?dLadeMenge:dPlanMenge);
					}
				}
			}
		}


		public double get_dSumme()	{return dSumme;	}
	}



	private class summeFuhrenOrteEK extends RECLIST_VPOS_TPA_FUHRE_ORT.DoAnyThingWithAll
	{
		private double dSumme = 0;

		@Override
		public void doAnyThingWith(String hashKey, RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
		{
			if (orecord.is_DELETED_NO())
			{
				double dPlanMenge = orecord.get_ANTEIL_PLANMENGE_dValue(new Double(0));
				double dLadeMenge = orecord.get_ANTEIL_LADEMENGE_dValue(new Double(0));
				double dAbladeMenge = orecord.get_ANTEIL_ABLADEMENGE_dValue(new Double(0));

				if (orecord.is_LADEMENGE_GUTSCHRIFT_YES())
				{
					dSumme += (dLadeMenge>0?dLadeMenge:dPlanMenge);
				}
				else
				{
					dSumme += (dAbladeMenge>0?dAbladeMenge:dPlanMenge);
				}
			}
		}
		public double get_dSumme()	{return dSumme;	}
	}


	private class summeFuhrenOrteVK extends RECLIST_VPOS_TPA_FUHRE_ORT.DoAnyThingWithAll
	{
		private double dSumme = 0;

		@Override
		public void doAnyThingWith(String hashKey, RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
		{
			if (orecord.is_DELETED_NO())
			{
				double dPlanMenge = orecord.get_ANTEIL_PLANMENGE_dValue(new Double(0));
				double dLadeMenge = orecord.get_ANTEIL_LADEMENGE_dValue(new Double(0));
				double dAbladeMenge = orecord.get_ANTEIL_ABLADEMENGE_dValue(new Double(0));

				if (orecord.is_ABLADEMENGE_RECHNUNG_YES())
				{
					dSumme += (dAbladeMenge>0?dAbladeMenge:dPlanMenge);
				}
				else
				{
					dSumme += (dLadeMenge>0?dLadeMenge:dPlanMenge);
				}
			}
		}
		public double get_dSumme()	{return dSumme;	}
	}

	
	private class ownInfoButton extends E2_InfoButton
	{

		public ownInfoButton(Vector<MyString> infos)
		{
			super(infos);
		}
		
	}
	
	
	public E2_InfoButton get_ownInfoButton(MyString cFehler)
	{
		Vector<MyString> vInfo = new Vector<MyString>();

		try
		{


			vInfo.add(new MyE2_String("Fehlerinformation :"));
			vInfo.add(new MyE2_String("Die Prüfung der Kontraktposition hat einen Fehler ergeben:"));

			String cHelp = "EK-Kontrakt";
			if (rec_vpos_kon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_VK_KONTRAKT))
			{
				cHelp = "VK-Kontrakt";
			}
			vInfo.add(new MyE2_String(cHelp,true,"(ID: "+rec_vpos_kon.get_ID_VPOS_KON_cUF()+")",false,
					rec_vpos_kon.get_ANZAHL_cF_NN("0")+" "+rec_vpos_kon.get_ANR1_cUF_NN("")+" "
					+rec_vpos_kon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_NAME1_cUF_NN("")+" "
					+rec_vpos_kon.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ORT_cUF_NN(""),false));

			vInfo.add(new MyE2_String("-----------------------------",false));
			vInfo.add(cFehler);  

		} 
		catch (myException e)
		{
			e.printStackTrace();
			vInfo.add(new MyE2_String("INTERNER FEHLER !!"));
		}
		return new ownInfoButton(vInfo);
	}


}
