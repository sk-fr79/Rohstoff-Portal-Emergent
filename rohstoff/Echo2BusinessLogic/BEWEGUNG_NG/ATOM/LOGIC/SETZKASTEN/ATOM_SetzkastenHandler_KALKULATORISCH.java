package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_SetzkastenHandler_KALKULATORISCH extends
		ATOM_SetzkastenHandler {

	public ATOM_SetzkastenHandler_KALKULATORISCH(MyE2_Column ColLoopInfo) {
		super(ColLoopInfo);
		
	}
	

	@Override
	protected void setQuerysettingsBase() {		
		m_sqlTablenameAtomSetzkasten	= "JT_BEWEGUNG_SETZKASTEN_K";
		m_sqlPrimaryKeyColAtomSetzkasten = "ID_BEWEGUNG_SETZKASTEN_K";
		m_sqlTableNameAtomVerbucht 		= "JT_BEWEGUNG_ATOM_VERBUCHT_K";
	}
	
	
	@Override
	protected void setQuerysettingsForLagerdaten(EnumBuchungstyp bt) {
		if (bt == EnumBuchungstyp.WA){
			m_sqlVorzeichen = " -1 ";
			m_sqlOrder = " ORDER BY 4,2 ";
			m_sqlMenge = "nvl(A.MENGE,0)";
			m_sqlPreis = "ROUND( " +
					"		 ( " +
					"				( (E_PREIS_RESULT_BRUTTO_MGE * (A.MENGE/NVL(ART.MENGENDIVISOR,1)) ) - NVL(V.KOSTEN_TRANSPORT_WA,0) - NVL(V.KOSTEN_PRODUKT_WA,0) ) / NVL( A.MENGE / NVL(ART.MENGENDIVISOR,1) ,1) " +
					"			)" +
						 ",2)";
			m_sqlJoinSetzkasten = "SK.ID_ATOM_AUSGANG";
			
		} else {
			m_sqlVorzeichen = " +1 ";
			m_sqlOrder = " ORDER BY 2,4 ";
			m_sqlMenge = "nvl(A.MENGE_NETTO,0)";
			m_sqlPreis = "ROUND(" +
						 "	 ( " +
						 "			( (A.E_PREIS_RESULT_NETTO_MGE * (A.MENGE_NETTO/NVL(ART.MENGENDIVISOR,1)) ) + NVL(V.KOSTEN_TRANSPORT_WE,0) + NVL(V.KOSTEN_PRODUKT_WE,0) ) / NVL(A.MENGE_NETTO / NVL(ART.MENGENDIVISOR,1)   ,1) " +
						 "		)" +
						 "	,2) ";
			m_sqlJoinSetzkasten = "SK.ID_ATOM_EINGANG";
		}
	}

	
	@Override
	protected String SQLInsertNewSetzkastenEintrag(	String id_lager_kto_ausgang,
														String id_lager_kto_eingang, 
														BigDecimal menge,			
														BigDecimal preis_eingang, 
														BigDecimal preis_ausgang, 
														String komplett)	throws myException {
		
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();

		oSql.addSQL_Paar("ID_BEWEGUNG_SETZKASTEN_K", "SEQ_BEWEGUNG_SETZKASTEN_K.NEXTVAL", false);

	    oSql.addSQL_Paar("ID_ATOM_EINGANG", id_lager_kto_eingang, false);
	    oSql.addSQL_Paar("ID_ATOM_AUSGANG", id_lager_kto_ausgang, false);
	    oSql.addSQL_Paar("MENGE", menge == null ? null : menge.toPlainString() , false);
	    oSql.addSQL_Paar("PREIS_EINGANG", preis_eingang == null ? null : preis_eingang.toPlainString() , false);
	    oSql.addSQL_Paar("PREIS_AUSGANG",preis_ausgang == null ? null : preis_ausgang.toPlainString() , false);
	    
	    oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
	    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
	    
	    String sSql = oSql.get_CompleteInsertString("JT_BEWEGUNG_SETZKASTEN_K", bibE2.cTO());

		return sSql;
	}

	@Override
	protected String SQLInsertAtomVerbucht(String id_atom, String id_vektor, String id_bewegung) throws myException {
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
		
		oSql.addSQL_Paar("ID_BEWEGUNG_ATOM_VERBUCHT_K", "SEQ_BEWEGUNG_ATOM_VERBUCHT_K.NEXTVAL", false);
		
		oSql.addSQL_Paar("ID_BEWEGUNG_ATOM", id_atom, false);
		oSql.addSQL_Paar("ID_BEWEGUNG_VEKTOR", id_vektor, false);
		oSql.addSQL_Paar("ID_BEWEGUNG", id_bewegung , false);
		
		oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
		oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
		
		String sSql = oSql.get_CompleteInsertString("JT_BEWEGUNG_ATOM_VERBUCHT_K", bibE2.cTO());
		
		return sSql;
	}
	
	

}
