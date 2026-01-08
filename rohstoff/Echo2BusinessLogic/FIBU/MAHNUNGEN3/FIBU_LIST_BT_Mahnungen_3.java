package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_VERRECH_WAEHRUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_VERRECH_WAEHRUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class FIBU_LIST_BT_Mahnungen_3 extends MyE2_Button 
{
	
	private E2_NavigationList   oNaviList = null;
	
	
	
	public FIBU_LIST_BT_Mahnungen_3(E2_NavigationList   NaviList) 
	{
		super(E2_ResourceIcon.get_RI("m1.png"), true);
		
		this.oNaviList = NaviList;
		
		this.setToolTipText(new MyE2_String("Mahnung erstellen und ausdrucken").CTrans());
		this.add_GlobalAUTHValidator_AUTO("MAHNUNG_ERSTELLEN");
		
		this.add_GlobalValidator(new FIBU_MAHNUNG_Sachbearbeiter_Validator_3());
		this.add_GlobalValidator(new ownValidator());
	
		
		this.add_oActionAgent(new ownActionAgent());
		
	}
	
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)	throws myException 
		{
			VectorSingle   		vMahnstufeVorhanden = new VectorSingle();
			VectorSingle   		vAdressen = new VectorSingle();
			RECLIST_FIBU   		recListFibuMahnbar = 		new RECLIST_FIBU();
			RECLIST_FIBU   		recListFibuNichtMahnbar = 	new RECLIST_FIBU();
			
			VectorSingle        vWaehrungFremd = new VectorSingle();
			
			int iLetzteMahnstufe = FIBU_LIST_BT_Mahnungen_3.this.get_iVorhandeneMahnStufe(recListFibuMahnbar,recListFibuNichtMahnbar,vAdressen,vMahnstufeVorhanden,vWaehrungFremd);
			
			MyE2_MessageVector 	oMV = new MyE2_MessageVector();
			
			
			if (vAdressen.size()>1)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie Belege von genau einer Firma aus!")));
				return oMV;
			}
			
			if (recListFibuNichtMahnbar.get_vKeyValues().size()>0)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Gemahnt werden können nur NICHT GESCHLOSSENE und nicht AUSGEGLICHENE Belege vom Typ RECHNUNG mit POSITIVEM Wert oder GUTSCHRIFT mit negativem Wert!")));
				return oMV;
			}
			
			if (recListFibuMahnbar.get_vKeyValues().size()==0)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens EINEN Beleg zur Mahnung auswählen!")));
				return oMV;
			}
		
			if (vMahnstufeVorhanden.size()!=1)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie nur Belege aus, die die gleiche Mahnstufe haben!")));
			}
			else if (vWaehrungFremd.size()!=1)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie nur Belege aus, die die gleiche Währung haben!")));
			}
			else
			{
				if (iLetzteMahnstufe<0)
				{
					oMV.add(new MyE2_Alarm_Message(new MyE2_String("Undefinierter Fehler ...")));
				}
				else
				{
					if (iLetzteMahnstufe==3)
					{
						oMV.add(new MyE2_Alarm_Message(new MyE2_String("Mahnung sind schon alle komplette (bis Stufe 3 einschliesslich) !")));
					}
				}
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException 
		{
			return null;
		}
		
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			new ownBasicModuleContainerWaehleMahnungAus();
		}
	}
	
	
	/**
	 * popup-Container vorgeschaltet, sucht die mahnung aus (oder erstellt neue)
	 * @author martin
	 *
	 */
	private class ownBasicModuleContainerWaehleMahnungAus extends E2_BasicModuleContainer
	{
		public ownBasicModuleContainerWaehleMahnungAus() throws myException 
		{
			super();
			RECLIST_FIBU   				recListFibuMahnbar = 	new RECLIST_FIBU();

			RECLIST_FIBU   				recListFibuNichtMahnbar = 	new RECLIST_FIBU();
			
			int iMahnStufeVorhanden =	FIBU_LIST_BT_Mahnungen_3.this.get_iVorhandeneMahnStufe(recListFibuMahnbar,recListFibuNichtMahnbar,null,null,null);

			if (iMahnStufeVorhanden>=0 && iMahnStufeVorhanden<3)
			{

				new FIBU_MAHNUNG_Container(recListFibuMahnbar,iMahnStufeVorhanden,FIBU_LIST_BT_Mahnungen_3.this.oNaviList,null);
			}
		}
	}
	
	
	
	
	
	
	
	/**
	 * sucht alle mahnstufen aller rechnungen und gutschriften zusammen, wo  
	 * ( FAKTOR_BUCHUNG_PLUS_MINUS*ENDBETRAG_FREMD_WAEHRUNG)>0 ist , d.h. auch negative gutschriften
	 * @return
	 * @throws myException 
	 */
	private int  get_iVorhandeneMahnStufe(		RECLIST_FIBU reclistFibuMahnbar, 
												RECLIST_FIBU reclistFibuNichtMahnbar, 
												VectorSingle vID_ADRESSEN, 
												VectorSingle vMahnstufen, 
												VectorSingle vWaehrungenFremd) throws myException
	{
		VectorSingle vMahnstufenSammler = 	new VectorSingle();
		
		Vector<String> vIDs = this.oNaviList.get_vSelectedIDs_Unformated();
		
		for (String cID_FIBU: vIDs)
		{
			RECORD_FIBU recFibu = new RECORD_FIBU(cID_FIBU);

			if (vID_ADRESSEN!=null)
			{
				vID_ADRESSEN.add(recFibu.get_ID_ADRESSE_cUF());
			}

			
			if (	(recFibu.get_BUCHUNGSTYP_cUF_NN("@@").equals(myCONST.BT_DRUCK_RECHNUNG)	|| recFibu.get_BUCHUNGSTYP_cUF_NN("@@").equals(myCONST.BT_DRUCK_GUTSCHRIFT)) &&
					(recFibu.is_BUCHUNG_GESCHLOSSEN_NO()) &&
					(recFibu.get_RESTBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0) &&
					(recFibu.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ZERO).multiply(recFibu.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO)).compareTo(BigDecimal.ZERO)>0)
			    )
			{
				if (vWaehrungenFremd!=null)
				{
					vWaehrungenFremd.add(recFibu.get_WAEHRUNG_FREMD_cUF_NN(""));
				}
				if (reclistFibuMahnbar!=null)
				{
					reclistFibuMahnbar.ADD(recFibu, true);
				}
				
				//2012-05-29: mahnstufen-pruefung auslagern
				vMahnstufenSammler.add(this.get_cNeuesteMahnstufe(recFibu));
			}
			else
			{
				if (reclistFibuNichtMahnbar!=null)
				{
					reclistFibuNichtMahnbar.ADD(recFibu, true);
				}
			}
		}
		

		
		// 2012-05-29: weitere moeglichkeiten mahnbarer belege
		// 01. belege, die zwar negative betraege besitzen, aber mindestens eine storno-position muessen manchmal in eine mahnung aufgenommen werden
		Vector<RECORD_FIBU> vRecNichtMahnbar = new Vector<RECORD_FIBU>();   //vector zu durchfraesen
		if (reclistFibuNichtMahnbar!=null)
		{
			vRecNichtMahnbar.addAll(reclistFibuNichtMahnbar.values());
		}

		for (RECORD_FIBU recFibuCheck: vRecNichtMahnbar)
		{
			if (	(recFibuCheck.get_BUCHUNGSTYP_cUF_NN("@@").equals(myCONST.BT_DRUCK_RECHNUNG) || 
					 recFibuCheck.get_BUCHUNGSTYP_cUF_NN("@@").equals(myCONST.BT_DRUCK_GUTSCHRIFT)) &&
					(recFibuCheck.is_BUCHUNG_GESCHLOSSEN_NO())					
			    )
			{
				if (this.get_bContainsStorno2PosMitRelationZuAnderenMahnbelegen(recFibuCheck, reclistFibuMahnbar))
				{
					this.lagere_um_NichtMahnbar_zu_Mahnbar(	recFibuCheck, reclistFibuMahnbar, reclistFibuNichtMahnbar, 
															vMahnstufenSammler, vWaehrungenFremd);
				}
			}
		}
		//ausnahme 1: ende
		
		
		
		

		if (vMahnstufen!=null)
		{
			vMahnstufen.addAll(vMahnstufenSammler);
		}
		
		
		int iMahnStufe = -1;
		
		if (vMahnstufenSammler.size()==1)
		{
			MyInteger Mahnstufe = new MyInteger(vMahnstufenSammler.get(0));
			if (Mahnstufe.get_cErrorCODE().equals(MyInteger.ALL_OK))
			{
				iMahnStufe = Mahnstufe.get_iValue();
			}
		}	
		
		
		
		//2012-05-29: die zuordenbaren waehrungen umsetzen
		if (vWaehrungenFremd!=null && vWaehrungenFremd.size()>1)
		{
			//die waerhungen fuer die mahnung immer auf die erste der beiden zuordnungswaehrungen setzen
			Vector<String>  vWaehrungenUmgesetzt = new Vector<String>();
			
			//hier noch die verrechenbaren waehrungen zusammenlegen
			RECLIST_FIBU_VERRECH_WAEHRUNG recListVerrechenbar = new RECLIST_FIBU_VERRECH_WAEHRUNG("SELECT * FROM "+bibE2.cTO()+".JT_FIBU_VERRECH_WAEHRUNG");

			for (String cWaehrung: vWaehrungenFremd)
			{
				String cWaehrungNeu = cWaehrung;
				for (RECORD_FIBU_VERRECH_WAEHRUNG recVerrech: recListVerrechenbar.values())
				{
					if (recVerrech.get_WAEHRUNG2_cF_NN("@@@@###").equals(cWaehrung))
					{
						cWaehrungNeu = recVerrech.get_WAEHRUNG1_cF_NN("");
					}
				}
				vWaehrungenUmgesetzt.add(cWaehrungNeu);
			}
			
			vWaehrungenFremd.removeAllElements();
			vWaehrungenFremd.addAll(vWaehrungenUmgesetzt);
		}
		
		
		
//		System.out.println("Anzahl zugelassene:"+reclistFibuMahnbar.size());
//		System.out.println("Anzahl verbotene:"+reclistFibuNichtMahnbar.size());
		
		return iMahnStufe;
	}

	
	private void lagere_um_NichtMahnbar_zu_Mahnbar(	RECORD_FIBU  recFibuCheck,
													RECLIST_FIBU reclistFibuMahnbar, 
													RECLIST_FIBU reclistFibuNichtMahnbar, 
													VectorSingle vMahnstufenSammler, 
													VectorSingle vWaehrungenFremd) throws myException
	{
		if (vWaehrungenFremd!=null)
		{
			vWaehrungenFremd.add(recFibuCheck.get_WAEHRUNG_FREMD_cUF_NN(""));
		}
			
		//raus aus dem unmahnbar rein ins mahnbar
		if (reclistFibuMahnbar!=null && reclistFibuNichtMahnbar!=null)
		{
			reclistFibuMahnbar.ADD(recFibuCheck, true);
			reclistFibuNichtMahnbar.remove(recFibuCheck.get_ID_FIBU_cUF());
			reclistFibuNichtMahnbar.get_vKeyValues().remove(recFibuCheck.get_ID_FIBU_cUF());
		}
			
		vMahnstufenSammler.add(this.get_cNeuesteMahnstufe(recFibuCheck));
	}
	
	
	private String get_cNeuesteMahnstufe(RECORD_FIBU recFibu) throws myException
	{
		String 					cMahnstufe = "0";
		RECLIST_FIBU_MAHNUNG  	recListMahnungen = recFibu.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_fibu(null,"JT_FIBU_MAHNUNG.ID_FIBU_MAHNUNG",true);
		
		if (recListMahnungen.get_vKeyValues().size()>0)
		{
			RECORD_MAHNUNG recMahnungNeueste = recListMahnungen.get(recListMahnungen.get_vKeyValues().size()-1).get_UP_RECORD_MAHNUNG_id_mahnung();
			cMahnstufe = recMahnungNeueste.get_MAHNSTUFE_cUF();
		}

		return cMahnstufe;
	}
	
	
	
	//enthaelt der beleg zum record (min) eine storno-2-position, dann darf er auch auf die mahnung
	private boolean get_bContainsStorno2PosMitRelationZuAnderenMahnbelegen(RECORD_FIBU recFibuCheck, RECLIST_FIBU reclistFibuMahnbar) throws myException
	{
		boolean bRueck = false;
		
		if (recFibuCheck.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
		{
			RECLIST_VPOS_RG rlPosCheck =  recFibuCheck.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg();
			
			for (RECORD_VPOS_RG recRG: rlPosCheck.values())
			{
				if (recRG.is_DELETED_NO() && recRG.get_ID_VPOS_RG_STORNO_VORGAENGER_lValue(-1l)>0)
				{
					String cID_STORNO_VORGAENGER = recRG.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF();
					
					//jetzt nachsehen, ob die storno-position einen bezug in die "korrekten" Mahnungs-rechnungen besizt
					for (RECORD_FIBU recKorrekteFibuPos: reclistFibuMahnbar.values())
					{
						if (recKorrekteFibuPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
						{
							RECLIST_VPOS_RG recListRGPOS = recKorrekteFibuPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg();
							
							for (RECORD_VPOS_RG recVPOS: recListRGPOS.values())
							{
								if (recVPOS.get_ID_VPOS_RG_cUF().equals(cID_STORNO_VORGAENGER))
								{
									bRueck = true;
								}
							}
						}
					}
				}
			}
		}
		
		return bRueck;
	}
	
	
	
//	//enthaelt der beleg zum record (min) eine storno-2-position, dann darf er auch auf die mahnung
//	private boolean get_bIstImGleichenBuchungsBlockZuAnderenMahnbelegen(RECORD_FIBU recFibuCheck, RECLIST_FIBU reclistFibuMahnbar) throws myException
//	{
//		boolean bRueck = false;
//
//		String cBuchungsBlockUmlagerKandidat = recFibuCheck.get_BUCHUNGSBLOCK_NR_cUF_NN("");
//		
//		if (S.isFull(cBuchungsBlockUmlagerKandidat))
//		{
//			for (RECORD_FIBU  recFibuMahnbar: reclistFibuMahnbar.values())
//			{
//				if (recFibuMahnbar.get_BUCHUNGSBLOCK_NR_cUF_NN("##").equals(cBuchungsBlockUmlagerKandidat))
//				{
//					bRueck = true;
//				}
//			}
//			
//		}
//		
//		return bRueck;
//	}

	
}
