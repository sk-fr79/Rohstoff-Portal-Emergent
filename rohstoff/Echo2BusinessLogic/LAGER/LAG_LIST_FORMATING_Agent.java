package rohstoff.Echo2BusinessLogic.LAGER;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAG_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	private LAG_LagerMengenErmittlung m_oVertragsMengen = null;
	private LAG_LagerSaldoErmittlung  m_oSaldoErmittlung = null;
	
	/**
	 * Konstruktor übernimmt die Vertragsmengen
	 * @param oVertragsMengen
	 */
	public LAG_LIST_FORMATING_Agent(LAG_LagerMengenErmittlung oVertragsMengen, LAG_LagerSaldoErmittlung oSaldoErmittlung) {
		super();
		m_oVertragsMengen = oVertragsMengen;
		m_oSaldoErmittlung = oSaldoErmittlung;
	}
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		
		// jetzt nch eventuell der Lieferanten andrucken.
		Object o = null;
		MyE2_Label lblCol = null;
		
		String sIDLager =  oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_LAGER");
		String sIDSorte = oUsedResultMAP.get_UnFormatedValue("ID_ARTIKEL_SORTE");

		
//		DEBUG.System_println("Saldo-Ermittlung für: " + sIDLager + "/" + sIDSorte,  DEBUG.DEBUG_FLAG_DIVERS1);
		
		// Saldodaten 
		LAG_LagerSaldoDaten oDatenSaldo = m_oSaldoErmittlung.getData(sIDLager, sIDSorte);
		String sData = "";
		
		// Saldo der Inventur
//		lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_SaldoInventur);
//		sData = oDatenSaldo.get_Saldo() != null ? convertBigDecimalToString(oDatenSaldo.get_Saldo(),3) : "-";
//		lblCol.setText(sData);
		
		
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        
		if (oDatenSaldo.get_InventurDatum() == null){
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_InventurDatum);
			lblCol.setText("-");
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_InventurMenge);
			lblCol.setText("-");
		}
		else {
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_InventurDatum);
			sData = df.format(oDatenSaldo.get_InventurDatum());
			lblCol.setText(sData);

			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_InventurMenge);
			sData = convertBigDecimalToString(oDatenSaldo.get_Inventurmenge(),3) ;
			lblCol.setText(sData);
		}

		
		
		BigDecimal bdSaldo = oDatenSaldo.get_Saldo();
		
		
		// Mengendaten der Verträge
		LAG_LagerMengenDaten oDaten = m_oVertragsMengen.getData(sIDLager, sIDSorte);
		
		
		
		if (oDaten.getIsEmpty()){
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_EKRestmenge);
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_VKRestmenge);
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_VKLagermenge);
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_EKLagermenge);
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_GesamtRestmenge);
			lblCol.setText("-");
			
		} else {
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_EKRestmenge);
			lblCol.setText(convertBigDecimalToString(oDaten.getEKRestmenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_VKRestmenge);
			lblCol.setText(convertBigDecimalToString(oDaten.getVKRestmenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_VKLagermenge);
			lblCol.setText(convertBigDecimalToString(oDaten.getVKLagermenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_EKLagermenge);
			lblCol.setText(convertBigDecimalToString(oDaten.getEKLagermenge(),3));
			
			
			BigDecimal bdGesamt = bdSaldo;
			if (oDaten.getEKRestmenge().compareTo(BigDecimal.ZERO) > 0){
				bdGesamt = bdGesamt.add(oDaten.getEKRestmenge());
			}
			if (oDaten.getVKRestmenge().compareTo(BigDecimal.ZERO) > 0){
				bdGesamt = bdGesamt.subtract(oDaten.getVKRestmenge());
			}
			
			lblCol = (MyE2_Label)oMAP.get__Comp(LAG_LIST_CONST.HASH_GesamtRestmenge);
			lblCol.setText(convertBigDecimalToString(bdGesamt,3));
		}
	}
	
	
	
	
	
	
	private String convertBigDecimalToString(BigDecimal bd, int dezimalstellen ){
		
		//bd.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_UP);
		String sRet = null;
		sRet = bibALL.makeDez(bd.doubleValue(), dezimalstellen, true);
		return sRet;
	}


}
