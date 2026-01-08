 
package rohstoff.businesslogic.bewegung.lager.list_saldo;
  
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.BG_Lager_Saldo_NAMES;
import rohstoff.businesslogic.bewegung.lager.saldo.BG_LagerSaldoErmittlung;
import rohstoff.businesslogic.bewegung.lager.saldo.BG_SaldoDaten;
import rohstoff.businesslogic.bewegung.lager.vertragsmengen.BG_Lager_KontraktmengenDaten;
import rohstoff.businesslogic.bewegung.lager.vertragsmengen.BG_Lager_KontraktmengenErmittlung;

@Deprecated
public class BG_Lager_Saldo_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	
	private BG_Lager_KontraktmengenErmittlung 		m_oVertragsMengen = null;
	private BG_LagerSaldoErmittlung 				m_oSaldoErmittlung = null;
	private ArrayList<BG_LagerSaldoErmittlung> 		m_alSaldoErmittlung = null;
	
	
	public BG_Lager_Saldo_LIST_FORMATING_Agent(BG_Lager_KontraktmengenErmittlung oVertragsMengen, BG_LagerSaldoErmittlung oSaldoErmittlung) {
		super();
		m_oVertragsMengen = oVertragsMengen;
		m_oSaldoErmittlung = oSaldoErmittlung;
	}
	
	public BG_Lager_Saldo_LIST_FORMATING_Agent setAdditionalSaldo(ArrayList<BG_LagerSaldoErmittlung> oSaldoAddtional){
		m_alSaldoErmittlung = oSaldoAddtional;
		return this;
	}
	
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException 
    {
    }
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
    {
    	
		
		// jetzt nch eventuell der Lieferanten andrucken.
		Object o = null;
		MyE2_Label lblCol = null;
		
		
		String sIDLager =  oUsedResultMAP.get_UnFormatedValue("S1_ID_ADRESSE");
		String sIDSorte = oUsedResultMAP.get_UnFormatedValue("ID_ARTIKEL");
		
		// Saldodaten 
		BG_SaldoDaten oDatenSaldo = m_oSaldoErmittlung.getData(sIDLager, sIDSorte);
		String sData = "";
		
		// Saldo der Inventur
		lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.SaldoMitInventur.name() );
		sData = oDatenSaldo.get_Saldo() != null ? convertBigDecimalToString(oDatenSaldo.get_Saldo(),3) : "-";
		lblCol.setText(sData);
		
		
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        
		if (oDatenSaldo.get_InventurDatum() == null){
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.InventurDatum.name());
			lblCol.setText("-");
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.InventurMenge.name() );
			lblCol.setText("-");
		}
		else {
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.InventurDatum.name());
			sData = df.format(oDatenSaldo.get_InventurDatum());
			lblCol.setText(sData);

			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.InventurMenge.name());
			sData = convertBigDecimalToString(oDatenSaldo.get_Inventurmenge(),3) ;
			lblCol.setText(sData);
		}

		
		
		BigDecimal bdSaldo = oDatenSaldo.get_Saldo();
		
		
		// Mengendaten der Verträge
		BG_Lager_KontraktmengenDaten oDaten = m_oVertragsMengen.getData(sIDLager, sIDSorte);
		
		
		if (oDaten == null || oDaten.getIsEmpty()){
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.EKRestmenge.name() );
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.VKRestmenge.name() );
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.VKLagermenge.name() );
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.EKLagermenge.name());
			lblCol.setText("-");
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.GesamtRestmenge.name());
			lblCol.setText("-");
			
		} else {
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.EKRestmenge.name());
			
			lblCol.setText(convertBigDecimalToString(oDaten.getEKRestmenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.VKRestmenge.name());
			lblCol.setText(convertBigDecimalToString(oDaten.getVKRestmenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.VKLagermenge.name());
			lblCol.setText(convertBigDecimalToString(oDaten.getVKLagermenge(),3));
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.EKLagermenge.name());
			lblCol.setText(convertBigDecimalToString(oDaten.getEKLagermenge(),3));
			
			
			BigDecimal bdGesamt = bdSaldo;
			if (oDaten.getEKRestmenge().compareTo(BigDecimal.ZERO) > 0){
				bdGesamt = bdGesamt.add(oDaten.getEKRestmenge());
			}
			if (oDaten.getVKRestmenge().compareTo(BigDecimal.ZERO) > 0){
				bdGesamt = bdGesamt.subtract(oDaten.getVKRestmenge());
			}
			
			lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.GesamtRestmenge.name());
			lblCol.setText(convertBigDecimalToString(bdGesamt,3));
		}
		


		// BigDecimal-Array für die Saldo-Differenz
		BigDecimal[] arrBDSaldo = new BigDecimal[3];
		
		// Wert des aktuellen Saldos
		arrBDSaldo[0] = oDatenSaldo.get_Saldo();
		
		// additionalSaldo-Daten 
		// Saldodaten 1
		BG_LagerSaldoErmittlung m_oSaldoErmittlungAdditional1  = null;
		if (m_alSaldoErmittlung != null && m_alSaldoErmittlung.size() > 0){
			m_oSaldoErmittlungAdditional1 = m_alSaldoErmittlung.get(0);
			
			if (m_oSaldoErmittlungAdditional1 != null){
				try {
					lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch1.name());
					if (lblCol != null && lblCol.isVisible() ){
						BG_SaldoDaten oDatenSaldoAdditional1 = m_oSaldoErmittlungAdditional1.getData(sIDLager, sIDSorte);
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
		BG_LagerSaldoErmittlung m_oSaldoErmittlungAdditional2 = null;
		if (m_alSaldoErmittlung != null && m_alSaldoErmittlung.size() > 1){
			m_oSaldoErmittlungAdditional2 = m_alSaldoErmittlung.get(1);
			
			if (m_oSaldoErmittlungAdditional2 != null){
				try {
					lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.SaldoDynamisch2.name());
					if (lblCol != null && lblCol.isVisible() ){
						BG_SaldoDaten oDatenSaldoAdditional2 = m_oSaldoErmittlungAdditional2.getData(sIDLager, sIDSorte);
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
		
		lblCol = (MyE2_Label)oMAP.get__Comp(BG_Lager_Saldo_NAMES.SaldoDelta.name());
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
 
