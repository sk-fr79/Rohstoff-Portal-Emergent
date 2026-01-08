package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Combo_Transportmittel;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_FUS_Grid_InnereErfassungsMaske;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum2;

public class FUS_BUTTON_New_fast_and_furious extends MyE2_Button
{

	private E2_NavigationList   			oNaviList = null;
//	private E2_BasicModuleContainer_MASK 	oMaskContainer = null;
	

	/*
	 *  diese komponente bekommt ein kurzzeitgedaechtnis,
	 *  das zuletzt benutzte datum und das zuletzt benutzte transportmittel merkt sich das system
	 *  bis zur naechsten neuerstellung dieses objekts 
	 */
	private String                        MEM_LastUsedDate = null;

	private String                        MEM_LastUsedTransportmittel = null;
	
	
	private boolean   					  bVarianteFahrplan = false;
	

	public FUS_BUTTON_New_fast_and_furious(	E2_NavigationList   			NaviList, 
											boolean         				VarianteFuerFahrplan)
	{
		super(E2_ResourceIcon.get_RI("new_multi.png"), true);
		
		this.oNaviList =		NaviList;
//		this.oMaskContainer = 	maskContainer;
		
		this.bVarianteFahrplan = VarianteFuerFahrplan;
		
		this.add_GlobalAUTHValidator_AUTO("FUHREN_SCHNELL_ERFASSUNG");
		
		this.setToolTipText(new MyE2_String("Schnell- und Mehrfacherfassung einer Fuhre ...").CTrans());
		
		this.add_oActionAgent(new ownAction());
		
	}
	
	
	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}

//	public E2_BasicModuleContainer_MASK get_oMaskContainer()
//	{
//		return oMaskContainer;
//	}

	private class ownAction extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new FUS_Window(	FUS_BUTTON_New_fast_and_furious.this.oNaviList,
							FUS_BUTTON_New_fast_and_furious.this.bVarianteFahrplan);

			FUS_Grid_InnereErfassungsMaske oInputBlock = new _SEARCH_FUS_Grid_InnereErfassungsMaske().get_Found_FUS_Grid_InnereErfassungsMaske();
			
			//wenn mindestens 1 fuhre ausgewaehlt war, dann die repraesentanten dieser fuhre laden
			Vector<String> vID_VPOS_TPA_FUHRE = FUS_BUTTON_New_fast_and_furious.this.oNaviList.get_vSelectedIDs_Unformated();
			if (vID_VPOS_TPA_FUHRE.size()>0)
			{
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(vID_VPOS_TPA_FUHRE.get(0));
				oInputBlock.fill_Comp_LADESTELLE(recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_start());
				
				
				oInputBlock.fill_Comp_ABLADESTELLE(recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel());
				
				oInputBlock.fill_Comp_LADESORTE(recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek());
				oInputBlock.fill_Comp_ABLADESORTE(recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk());
				
				oInputBlock.fill_Comp_LADEKONTRAKT(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek());
				oInputBlock.fill_Comp_ABLADEKONTRAKT(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk());
				
				oInputBlock.fill_Comp_LADEANGEBOT(recFuhre.get_UP_RECORD_VPOS_STD_id_vpos_std_ek());
				oInputBlock.fill_Comp_ABLADEANGEBOT(recFuhre.get_UP_RECORD_VPOS_STD_id_vpos_std_vk());
				
				oInputBlock.fill_Comp_WiegeDatum(new MyDate(recFuhre.get_DATUM_AUFLADEN_cF_NN("")));
				oInputBlock.fill_Comp_TransportMittel(recFuhre.get_TRANSPORTMITTEL_cUF_NN(""));
				
				oInputBlock.fill_Comp_LKW_Kennzeichen(recFuhre.get_TRANSPORTKENNZEICHEN_cUF_NN(""));
				oInputBlock.fill_Comp_LKW_AnhaengerKennzeichen(recFuhre.get_ANHAENGERKENNZEICHEN_cUF_NN(""));
				
				
				if (FUS_BUTTON_New_fast_and_furious.this.bVarianteFahrplan)
				{
					oInputBlock.fill_Comp_AnzahlContainer(new MyBigDecimal( recFuhre.get_ANZAHL_CONTAINER_FP_bdValue(new BigDecimal(1)),0));
					
					oInputBlock.fill_Comp_WiegeDatum(new MyDate(recFuhre.get_DATUM_ABHOLUNG_cF_NN("")));
					oInputBlock.fill_Comp_Datum2(new MyDate(recFuhre.get_DATUM_ANLIEFERUNG_cF_NN("")));
				}
				
			}

			//jetzt nachsehen, ob der MEM von FUS_BUTTON_New_fast_and_furious sich ein transportmittel und ein datum gemerkt hat
			String cTransportMittel = 	FUS_BUTTON_New_fast_and_furious.this.get_MEM_LastUsedTransportmittel();
			String cDatum = 			FUS_BUTTON_New_fast_and_furious.this.get_MEM_LastUsedDate();
			
			if (S.isEmpty(new  _SEARCH_Combo_Transportmittel().getFirstComponent().get_oTextField().getText()) && S.isFull(cTransportMittel))
			{
				oInputBlock.fill_Comp_TransportMittel(cTransportMittel);
			}
			if (S.isEmpty(new  _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oTextField().getText()) && S.isFull(cDatum))
			{
				oInputBlock.fill_Comp_WiegeDatum(new MyDate(cDatum));
			}
			if (oInputBlock.get_bVarianteFahrplan())
			{
				if (S.isEmpty(new  _SEARCH_InputDatum2().get_Found_FUS_InputDatum2().get_oTextField().getText()) && S.isFull(cDatum))
				{
					oInputBlock.fill_Comp_Datum2(new MyDate(cDatum));
				}
			}
			//wenn jetzt hier das datum noch leer ist, dann das Datumsauswahlfeld hochpoppen lassen
//			if (S.isEmpty(new  _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oTextField().getText()))
//			{
//				new  _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oButtonCalendar().do_OnlyCode_from_OtherActionAgent(oExecInfo.get_MyActionEvent());
//			}
			//ab 2014-08-04: das datum immer hochpoppen lassen
			new  _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oButtonCalendar().do_OnlyCode_from_OtherActionAgent(oExecInfo.get_MyActionEvent());
			
		}
	}
	
	
	
	public String get_MEM_LastUsedDate()
	{
		return MEM_LastUsedDate;
	}


	public void set_MEM_LastUsedDate(String mEM_LastUsedDate)
	{
		MEM_LastUsedDate = mEM_LastUsedDate;
	}


	public String get_MEM_LastUsedTransportmittel()
	{
		return MEM_LastUsedTransportmittel;
	}


	public void set_MEM_LastUsedTransportmittel(String mEM_LastUsedTransportmittel)
	{
		MEM_LastUsedTransportmittel = mEM_LastUsedTransportmittel;
	}


	public boolean get_bVarianteFahrplan()
	{
		return bVarianteFahrplan;
	}


	public void set_bVarianteFahrplan(boolean bVarianteFahrplan)
	{
		this.bVarianteFahrplan = bVarianteFahrplan;
	}

	
}
