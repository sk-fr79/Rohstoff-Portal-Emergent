package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_SaldoDaten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.VERTRAGSMENGEN.ATOM_Lager_KontraktmengenDaten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.VERTRAGSMENGEN.ATOM_Lager_KontraktmengenErmittlung;

public class ATOM_SALDO_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	private ATOM_Lager_KontraktmengenErmittlung m_oVertragsMengen = null;
	private ATOM_LagerSaldoErmittlung m_oSaldoErmittlung = null;
//	private ATOM_LagerSaldoErmittlung m_oSaldoErmittlungAdditional1 = null;
//	private ATOM_LagerSaldoErmittlung m_oSaldoErmittlungAdditional2 = null;
//	private ATOM_LagerSaldoErmittlung m_oSaldoErmittlungDelta = null;
	
	private ArrayList<ATOM_LagerSaldoErmittlung> m_alSaldoErmittlung = null;
	
	/**
	 * Konstruktor übernimmt die Vertragsmengen
	 * @param oVertragsMengen
	 */
	public ATOM_SALDO_LIST_FORMATING_Agent(ATOM_Lager_KontraktmengenErmittlung oVertragsMengen, ATOM_LagerSaldoErmittlung oSaldoErmittlung) {
		super();
		m_oVertragsMengen = oVertragsMengen;
		m_oSaldoErmittlung = oSaldoErmittlung;
	}
	
	public ATOM_SALDO_LIST_FORMATING_Agent setAdditionalSaldo(ArrayList<ATOM_LagerSaldoErmittlung> oSaldoAddtional){
		m_alSaldoErmittlung = oSaldoAddtional;
		return this;
	}
	
//	public ATOM_SALDO_LIST_FORMATING_Agent setAdditionalSaldo1(ATOM_LagerSaldoErmittlung oSaldoAddtional){
//		m_oSaldoErmittlungAdditional1 = oSaldoAddtional;
//		return this;
//	}
//	
//	public ATOM_SALDO_LIST_FORMATING_Agent setAdditionalSaldo2(ATOM_LagerSaldoErmittlung oSaldoAddtional){
//		m_oSaldoErmittlungAdditional2 = oSaldoAddtional;
//		return this;
//	}
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		
		// jetzt nch eventuell der Lieferanten andrucken.
		Object o = null;
		MyE2_Label lblCol = null;
		
		
		String sIDLager =  oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE");
		String sIDSorte = oUsedResultMAP.get_UnFormatedValue("ID_ARTIKEL");

		
		
		// Saldodaten 
		ATOM_SaldoDaten oDatenSaldo = m_oSaldoErmittlung.getData(sIDLager, sIDSorte);
		String sData = "";
		
		// Saldo der Inventur
		lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoMitInventur);
		sData = oDatenSaldo.get_Saldo() != null ? convertBigDecimalToString(oDatenSaldo.get_Saldo(),3) : "-";
		lblCol.setText(sData);
		
		
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        
		if (oDatenSaldo.get_InventurDatum() == null){
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_InventurDatum);
			lblCol.setText("-");
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_InventurMenge);
			lblCol.setText("-");
		}
		else {
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_InventurDatum);
			sData = df.format(oDatenSaldo.get_InventurDatum());
			lblCol.setText(sData);

			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_InventurMenge);
			sData = convertBigDecimalToString(oDatenSaldo.get_Inventurmenge(),3) ;
			lblCol.setText(sData);
		}

		
		
		BigDecimal bdSaldo = oDatenSaldo.get_Saldo();
		
		
		// Mengendaten der Verträge
		ATOM_Lager_KontraktmengenDaten oDaten = m_oVertragsMengen.getData(sIDLager, sIDSorte);
		
		
		if (oDaten == null || oDaten.getIsEmpty()){
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_EKRestmenge);
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_VKRestmenge);
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_VKLagermenge);
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_EKLagermenge);
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_GesamtRestmenge);
			lblCol.setText("-");
			
		} else {
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_EKRestmenge);
			lblCol.setText(convertBigDecimalToString(oDaten.getEKRestmenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_VKRestmenge);
			lblCol.setText(convertBigDecimalToString(oDaten.getVKRestmenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_VKLagermenge);
			lblCol.setText(convertBigDecimalToString(oDaten.getVKLagermenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_EKLagermenge);
			lblCol.setText(convertBigDecimalToString(oDaten.getEKLagermenge(),3));
			
			
			BigDecimal bdGesamt = bdSaldo;
			if (oDaten.getEKRestmenge().compareTo(BigDecimal.ZERO) > 0){
				bdGesamt = bdGesamt.add(oDaten.getEKRestmenge());
			}
			if (oDaten.getVKRestmenge().compareTo(BigDecimal.ZERO) > 0){
				bdGesamt = bdGesamt.subtract(oDaten.getVKRestmenge());
			}
			
			lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_GesamtRestmenge);
			lblCol.setText(convertBigDecimalToString(bdGesamt,3));
		}
		


		// BigDecimal-Array für die Saldo-Differenz
		BigDecimal[] arrBDSaldo = new BigDecimal[3];
		
		// Wert des aktuellen Saldos
		arrBDSaldo[0] = oDatenSaldo.get_Saldo();
		
		// additionalSaldo-Daten 
		// Saldodaten 1
		ATOM_LagerSaldoErmittlung m_oSaldoErmittlungAdditional1  = null;
		if (m_alSaldoErmittlung.size() > 0){
			m_oSaldoErmittlungAdditional1 = m_alSaldoErmittlung.get(0);
			
			if (m_oSaldoErmittlungAdditional1 != null){
				try {
					lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1);
					if (lblCol != null && lblCol.isVisible() ){
						ATOM_SaldoDaten oDatenSaldoAdditional1 = m_oSaldoErmittlungAdditional1.getData(sIDLager, sIDSorte);
						sData = oDatenSaldoAdditional1.get_Saldo() != null ? convertBigDecimalToString(oDatenSaldoAdditional1.get_Saldo(),3) : "-";
						lblCol.setText(sData);
						
						// Wert des 1. zusätzlichen Saldos			
						arrBDSaldo[1] = oDatenSaldoAdditional1.get_Saldo();
					}
				} catch (Exception e) {
					// no Saldo Column
				}
			}
		}


			
			// Saldodaten 2
		ATOM_LagerSaldoErmittlung m_oSaldoErmittlungAdditional2 = null;
		if (m_alSaldoErmittlung.size() > 1){
			m_oSaldoErmittlungAdditional2 = m_alSaldoErmittlung.get(1);
			
			if (m_oSaldoErmittlungAdditional2 != null){
				try {
					lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2);
					if (lblCol != null && lblCol.isVisible() ){
						ATOM_SaldoDaten oDatenSaldoAdditional2 = m_oSaldoErmittlungAdditional2.getData(sIDLager, sIDSorte);
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
		
		// für die Saldo-Differenz herausfinden, welche Werte zur Berechnung herangezogen werden müssen
		if (m_oSaldoErmittlungAdditional1 != null && m_oSaldoErmittlungAdditional2 != null){
			// es muss ein Delta gebildet werden
			bdDelta = arrBDSaldo[1].subtract(arrBDSaldo[2]);
		} else if (m_oSaldoErmittlungAdditional1 != null  ){
			bdDelta = arrBDSaldo[0].subtract(arrBDSaldo[1]);
		} else if (m_oSaldoErmittlungAdditional2 != null){
			bdDelta = arrBDSaldo[0].subtract(arrBDSaldo[1]);
		}
		
		lblCol = (MyE2_Label)oMAP.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta);
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
