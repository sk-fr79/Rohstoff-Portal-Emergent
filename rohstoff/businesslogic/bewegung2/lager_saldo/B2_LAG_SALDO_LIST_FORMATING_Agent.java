package rohstoff.businesslogic.bewegung2.lager_saldo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.saldo.B2_LAG_SALDO_Ermittlung;
import rohstoff.businesslogic.bewegung2.global.saldo.B2_LAG_SALDO_SaldoDaten;
import rohstoff.businesslogic.bewegung2.lager.vertragsmengen.B2_LAG_KontraktmengenDaten;
import rohstoff.businesslogic.bewegung2.lager.vertragsmengen.B2_LAG_KontraktmengenErmittlung;
import rohstoff.businesslogic.bewegung2.lager_saldo.B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES;

public class B2_LAG_SALDO_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
	private RB_TransportHashMap   m_tpHashMap = null;

	private B2_LAG_KontraktmengenErmittlung  		m_oVertragsMengen;
	private B2_LAG_SALDO_Ermittlung 				m_oSaldoErmittlung = null;
	private ArrayList<B2_LAG_SALDO_Ermittlung> 		m_alSaldoErmittlung = null;

	public B2_LAG_SALDO_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap, B2_LAG_KontraktmengenErmittlung oVertragsMengen, B2_LAG_SALDO_Ermittlung oSaldoErmittlung) throws myException {
		this.m_tpHashMap 		= p_tpHashMap;
		this.m_oVertragsMengen 	= oVertragsMengen;
		this.m_oSaldoErmittlung = oSaldoErmittlung;
	}

	public B2_LAG_SALDO_LIST_FORMATING_Agent _set_additionnal_saldo(ArrayList<B2_LAG_SALDO_Ermittlung> al_saldoErmittlung) throws myException{
		this.m_alSaldoErmittlung = al_saldoErmittlung;
		return this;
	}

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
		Object o = null;
		MyE2_Label lblCol = null;

		
		
		String sIDLager =  oUsedResultMAP.get_UnFormatedValue("SUBADRESSE");
		String sArtInfo = oUsedResultMAP.get_UnFormatedValue("ART_INFO");
		String sIDSorte = oUsedResultMAP.get_UnFormatedValue("SUBARTIKEL");

		// Saldodaten 
		B2_LAG_SALDO_SaldoDaten oDatenSaldo = m_oSaldoErmittlung.getData(sIDLager, sIDSorte);
		String sData = "";

		// Saldo der Inventur
		lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SALDO_MIT_INVENTUR.name() );
		sData = oDatenSaldo.get_Saldo() != null ? convertBigDecimalToString(oDatenSaldo.get_Saldo(),0) : "-";
		lblCol.setText(sData);

		BigDecimal bdSaldo = oDatenSaldo.get_Saldo();


		if(S.isFull(sIDSorte)) {
			Rec21 recArtikel = new Rec21(_TAB.artikel)._fill_id(sIDSorte);
			String oLblText  = recArtikel.get_ufs_kette(" - ", ARTIKEL.anr1, ARTIKEL.artbez1); 
			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.ART_INFO.name() );
			lblCol.setText(oLblText);
		}
		
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        
		if (oDatenSaldo.get_InventurDatum() == null){
			lblCol = (MyE2_Label)oMAP.get__Comp((B2_LAG_SALDO_NAMES.INVENTURDATUM.name()));
			lblCol.setText("-");
			lblCol = (MyE2_Label)oMAP.get__Comp((B2_LAG_SALDO_NAMES.INVENTURMENGE.name()));
			lblCol.setText("-");
		}
		else {
			lblCol = (MyE2_Label)oMAP.get__Comp((B2_LAG_SALDO_NAMES.INVENTURDATUM.name()));
			sData = df.format(oDatenSaldo.get_InventurDatum());
			lblCol.setText(sData);

			lblCol = (MyE2_Label)oMAP.get__Comp((B2_LAG_SALDO_NAMES.INVENTURMENGE.name()));
			sData = convertBigDecimalToString(oDatenSaldo.get_Inventurmenge(),0) ;
			lblCol.setText(sData);
		}

		
		
		// Mengendaten der Verträge
		B2_LAG_KontraktmengenDaten oDaten = m_oVertragsMengen.getData(sIDLager, sIDSorte);
		
		if (oDaten == null || oDaten.getIsEmpty()){
			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.RESTMGE_EK_KONTRAKT.name() );
			lblCol.setText("-");

			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.RESTMGE_VK_KONTRAKT.name() );
			lblCol.setText("-");

			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SUMME_VK_KONTRAKT.name() );
			lblCol.setText("-");

			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SUMME_EK_KONTRAKT.name());
			lblCol.setText("-");

			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SALDO_KONTRAKT.name());
			lblCol.setText("-");

		} else {

			BigDecimal bdGesamt = bdSaldo;
			if (oDaten.getEKRestmenge().compareTo(BigDecimal.ZERO) > 0){
				bdGesamt = bdGesamt.add(oDaten.getEKRestmenge());
			}
			if (oDaten.getVKRestmenge().compareTo(BigDecimal.ZERO) > 0){
				bdGesamt = bdGesamt.subtract(oDaten.getVKRestmenge());
			}
			
			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SALDO_KONTRAKT.name());
			lblCol.setText(convertBigDecimalToString(bdGesamt,3));

			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.RESTMGE_EK_KONTRAKT.name());
			lblCol.setText(convertBigDecimalToString(oDaten.getEKRestmenge(),3));

			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SUMME_EK_KONTRAKT.name());
			lblCol.setText(convertBigDecimalToString(oDaten.getEKLagermenge(),3));

			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.RESTMGE_VK_KONTRAKT.name());
			lblCol.setText(convertBigDecimalToString(oDaten.getVKRestmenge(),3));

			lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SUMME_VK_KONTRAKT.name());
			lblCol.setText(convertBigDecimalToString(oDaten.getVKLagermenge(),3));

		}


		// BigDecimal-Array für die Saldo-Differenz
		BigDecimal[] arrBDSaldo = new BigDecimal[3];

		// Wert des aktuellen Saldos
		arrBDSaldo[0] = oDatenSaldo.get_Saldo();

		// additionalSaldo-Daten 
		// Saldodaten 1
				B2_LAG_SALDO_Ermittlung m_oSaldoErmittlungAdditional1 = null;
				if (m_alSaldoErmittlung != null && m_alSaldoErmittlung.size() > 0){
					m_oSaldoErmittlungAdditional1 = m_alSaldoErmittlung.get(0);
		
					if (m_oSaldoErmittlungAdditional1 != null){
						try {
							lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_1.name());
							if (lblCol != null && lblCol.isVisible() ){
								B2_LAG_SALDO_SaldoDaten oDatenSaldoAdditional1 = m_oSaldoErmittlungAdditional1.getData(sIDLager, sIDSorte);
								sData = oDatenSaldoAdditional1.get_Saldo() != null ? convertBigDecimalToString(oDatenSaldoAdditional1.get_Saldo(),3) : "-";
								lblCol.set_Text(sData);
		
								// Wert des 1. zusätzlichen Saldos			
								arrBDSaldo[1] = oDatenSaldoAdditional1.get_Saldo();
							}
						} catch (Exception e) {
							// no Saldo Column
						}
					}
				}



		// Saldodaten 2
				B2_LAG_SALDO_Ermittlung m_oSaldoErmittlungAdditional2 = null;
				if (m_alSaldoErmittlung != null && m_alSaldoErmittlung.size() > 1){
					m_oSaldoErmittlungAdditional2 = m_alSaldoErmittlung.get(1);
		
					if (m_oSaldoErmittlungAdditional2 != null){
						try {
							lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DYN_2.name());
							if (lblCol != null && lblCol.isVisible() ){
								B2_LAG_SALDO_SaldoDaten oDatenSaldoAdditional2 = m_oSaldoErmittlungAdditional2.getData(sIDLager, sIDSorte);
								sData = oDatenSaldoAdditional2.get_Saldo() != null ? convertBigDecimalToString(oDatenSaldoAdditional2.get_Saldo(),3) : "-";
								lblCol.setText(sData);
		
								// Wert des 2. zusätzlichen Saldos
								arrBDSaldo[2] = oDatenSaldoAdditional2.get_Saldo();
							}
						} catch (Exception e) {
							// no Saldo Column
						}
					}
				}

				BigDecimal bdDelta = null;
		//
		//		// für die Saldo-Differenz herausfinden, welche Werte zur Berechnung herangezogen werden müssen
				if (m_oSaldoErmittlungAdditional1 != null && m_oSaldoErmittlungAdditional2 != null){
					// es muss ein Delta gebildet werden
					bdDelta = arrBDSaldo[1].subtract(arrBDSaldo[2]);
				} else if (m_oSaldoErmittlungAdditional1 != null  ){
					bdDelta = arrBDSaldo[0].subtract(arrBDSaldo[1]);
				} else if (m_oSaldoErmittlungAdditional2 != null){
					bdDelta = arrBDSaldo[0].subtract(arrBDSaldo[1]);
				}
		
				lblCol = (MyE2_Label)oMAP.get__Comp(B2_LAG_SALDO_NAMES.SALDO_DELTA.name());
				if (lblCol != null && lblCol.isVisible() ){
					if (bdDelta != null){
						try {
							sData = convertBigDecimalToString(bdDelta,3) ;
						} catch (Exception e) {
							// no Saldo Column
						}
					} else {
						sData = "-";
					}
					lblCol.setText(sData);
				}

	}

	private String convertBigDecimalToString(BigDecimal bd, int dezimalstellen ){

		//bd.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_UP);
		String sRet = null;
		sRet = bibALL.makeDez(bd.doubleValue(), dezimalstellen, true);
		return sRet;
	}
}


