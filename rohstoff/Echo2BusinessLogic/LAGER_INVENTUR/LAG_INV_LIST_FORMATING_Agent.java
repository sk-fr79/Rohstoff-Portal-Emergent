package rohstoff.Echo2BusinessLogic.LAGER_INVENTUR;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerBuchung_Erfassung;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_Reorganize_Sorte;

public class LAG_INV_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	
	E2_NavigationList m_navList = null;


	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		
		m_navList = oMAP.get_oNavigationList_This_Belongs_to();
		
		// jetzt nch eventuell der Lieferanten andrucken.
		Object o = null;
		MyE2_Label lblCol = null;
		
		String sIDLager =  oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_LAGER");
		String sIDSorte = oUsedResultMAP.get_UnFormatedValue("ID_ARTIKEL_SORTE");
		String sDatum   = oUsedResultMAP.get_UnFormatedValue("BUCHUNGSDATUM");
		String sZeit = "23:58";
		String sData 	= "";
		
		
		// Saldodaten
//		LAG_LagerSaldoErmittlung oSaldoErmittlung 	= new LAG_LagerSaldoErmittlung();
//		LAG_LagerSaldoDaten  	 oSaldoDaten 		= null;
//		
//		oSaldoErmittlung.readLagerSaldoDaten(sIDLager, sIDSorte, sDatum,sZeit);
//		oSaldoDaten = oSaldoErmittlung.getData(sIDLager, sIDSorte);
//		
		
//		BigDecimal bdSaldo = oSaldoDaten.get_Saldo();
		BigDecimal bdSaldo = new BigDecimal(oUsedResultMAP.get_UnFormatedValue("LAGERBESTAND"));
		
		BigDecimal bdInventur = new BigDecimal( oUsedResultMAP.get_UnFormatedValue("MENGE"));
		
		BigDecimal bdDiff = bdSaldo.subtract(bdInventur);
		boolean bSaldoOK = (bdDiff.compareTo(BigDecimal.ZERO) == 0);
		
		
		// Saldo OK
		MyE2_ButtonInLIST btnOK = null;
		btnOK = (MyE2_ButtonInLIST)oMAP.get__Comp(LAG_INV_CONST.HASH_COL_LAGERSALDO_OK);
		if (bSaldoOK){
			btnOK.setIcon(E2_ResourceIcon.get_RI("ok.png"));
			btnOK.setText("");
		} else {
			btnOK.setIcon(E2_ResourceIcon.get_RI("warnschild_22.png"));
			btnOK.setText(new MyE2_String("Korrigieren...").CTrans());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = null;
			try {
				dt = df.parse(sDatum);
			} catch (ParseException e) {
				dt = null;
			}
			// eine Lagerumbuchung initiieren!
			btnOK.add_oActionAgent(new ownActionPopup(sIDLager,sIDSorte,bdDiff.negate(),dt,false));
		}
		//((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_LADEMENGE)).setIcon(E2_ResourceIcon.get_RI("listlabel_trans.png"));
		
		MyE2_ButtonInLIST btnLagerwerte = null;
		btnLagerwerte = (MyE2_ButtonInLIST)oMAP.get__Comp(LAG_INV_CONST.HASH_COL_SETZKASTEN_NEU_AUFBAUEN);
		btnLagerwerte.add_oActionAgent(new ownActionLagerwerteNeuaufbau(sIDLager, sIDSorte));
		
		
//		// Saldo Menge
//		lblCol = (MyE2_Label)oMAP.get__Comp(LAG_INV_CONST.HASH_COL_LAGERSALDO_MENGE);
//		sData = bibALL.makeDez(oSaldoDaten.get_Saldo().doubleValue(), 3, true);
//		lblCol.setText(sData);
		
		
		// Saldo Differenz
		lblCol = (MyE2_Label)oMAP.get__Comp(LAG_INV_CONST.HASH_COL_LAGERSALDO_DIFFERENZ);
		sData = bibALL.makeDez(bdDiff.doubleValue(), 3, true);
		lblCol.setText(sData);

		
	}
	
	
	private class ownActionLagerwerteNeuaufbau extends XX_ActionAgent{
		private String m_IDLager = null;
		private String m_IDSorte = null;
		
		public ownActionLagerwerteNeuaufbau(String IDLager,String IDSorte ) {
			super();
			m_IDLager = IDLager;
			m_IDSorte = IDSorte;
		}
		

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
//			MyE2_Button oButt = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
			
			LAG_Reorganize_Sorte oReorg = new LAG_Reorganize_Sorte(m_IDLager,m_IDSorte, "2000-01-01");
			oReorg.Reorganize_Lager_WithProcessBar();
		}
	}

	
	
	private class ownActionPopup extends XX_ActionAgent
	{

		private String m_IDLager = null;
		private String m_IDSorte = null;
		private BigDecimal m_Menge = null;
		private Date m_DateBuchung = null;
		private boolean m_bKorrektur = false;
		
		
		public ownActionPopup(String IDLager,String IDSorte, BigDecimal Menge, Date DatumBuchung, boolean bKorrekturbuchung) {
			super();
			m_IDLager = IDLager;
			m_IDSorte = IDSorte;
			m_Menge = Menge;
			m_DateBuchung = DatumBuchung;
			m_bKorrektur = bKorrekturbuchung;
		
		}
		


		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_Button oButt = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
			
			//E2_ComponentMAP oMap = oButt.EXT().get_oComponentMAP();
			//String cID_Zeile = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			new LAG_LagerBuchung_Erfassung(m_IDLager, m_IDSorte, m_Menge, m_DateBuchung, LAG_INV_LIST_FORMATING_Agent.this.m_navList,m_bKorrektur);
		}
		
	

	}
}