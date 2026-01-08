package rohstoff.Echo2BusinessLogic.INTRASTAT;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

public class INSTAT_Record_Einfuhr extends INSTAT_Record {

	public INSTAT_Record_Einfuhr() {
		super();
		this.set_Meldeart("1");
		this.set_Anmeldeform("1");
		this.set_Waehrungskennziffer("2");
		this.set_Unterscheidungsnr("   ");
	}
	

	@Override
	public String GetSqlForInsert() throws myException {
		String sSql = "";
		
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
		
		BigDecimal dSaldo = BigDecimal.ZERO;
		
		oSql.addSQL_Paar("ID_INTRASTAT_MELDUNG", "SEQ_INTRASTAT_MELDUNG.NEXTVAL", false);
		oSql.addSQL_Paar("ID_VPOS_TPA_FUHRE", m_IdVposTpaFuhre , false);
		oSql.addSQL_Paar("ID_VPOS_TPA_FUHRE_ORT", m_IdVposTpaFuhreOrt, false);
		oSql.addSQL_Paar("MELDEART", m_Meldeart, true);
		oSql.addSQL_Paar("ANMELDEFORM",m_Anmeldeform , true);
		oSql.addSQL_Paar("ANMELDEJAHR",m_Jahr , true);
		oSql.addSQL_Paar("ANMELDEMONAT", m_Monat, true);
		oSql.addSQL_Paar("PAGINIERNUMMER",m_Paginiernummer , true);
		oSql.addSQL_Paar("BUNDESLAND_FA", m_Bundesland_FA, true);
		oSql.addSQL_Paar("STEUERNR", m_Steuernr, true);
		oSql.addSQL_Paar("UNTERSCHEIDUNGSNR", m_Unterscheidungsnr, true);
		oSql.addSQL_Paar("BESTIMM_LAND",m_Bestimmungsland , true);
		oSql.addSQL_Paar("BESTIMM_REGION", m_Bestimmungsregion, true);
		oSql.addSQL_Paar("GESCHAEFTSART",m_Geschaeftsart , true);
		oSql.addSQL_Paar("VERKEHRSZWEIG",m_Verkehrszweig , true);
		oSql.addSQL_Paar("WARENNR",m_Warennr , true);
		oSql.addSQL_Paar("LAND_URSPRUNG",m_Land_Ursprung , true);
		oSql.addSQL_Paar("EIGENMASSE", m_Eigenmasse, true);
		oSql.addSQL_Paar("MASSEINHEIT", m_Masseinheit, true);
		oSql.addSQL_Paar("RECHBETRAG", m_Rechbetrag , true);
		oSql.addSQL_Paar("STATISTISCHER_WERT",m_StatistischerBetrag , true);
		oSql.addSQL_Paar("BEZUGSMONAT",m_Bezugsmonat , true);
		oSql.addSQL_Paar("BEZUGSJAHR", m_Bezugsjahr, true);
		oSql.addSQL_Paar("WAEHRUNGSKENNZIFFER", m_Waehrungskennziffer , true);
		
	    oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
	    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
	    oSql.addSQL_Paar("PREISTYP", m_Preistyp, true);
	    oSql.addSQL_Paar("ID_ARTIKEL",m_IdArtikel,false);
	    oSql.addSQL_Paar("FRACHTKOSTEN", m_Kostenpauschale, true);
	    oSql.addSQL_Paar("NICHT_MELDEN", m_NichtMelden, true);
	    
	    oSql.addSQL_Paar("UMSATZSTEUERID", m_UmsatzsteuerID, true);
	    oSql.addSQL_Paar("UMSATZSTEUER_LKZ", m_UmsatzsteuerLKZ, true);
	    
	    sSql = oSql.get_CompleteInsertString("JT_INTRASTAT_MELDUNG", bibE2.cTO());

		return sSql;

	}


	/**
	 * Implementierung des Datensatzes für den Export der ASCII-Datei
	 */
	@Override
	public String GetRowForExportfile() throws myException {
		StringBuilder sbRow = new StringBuilder();
		
		sbRow.append(getStringForColumnRight(m_Meldeart,1," "));
		sbRow.append(getStringForColumnRight(m_Anmeldeform,1," "));
		sbRow.append(getStringForColumnRight(m_Monat,2," "));
		sbRow.append(getStringForColumnRight("00",2,"0"));
		sbRow.append(getStringForColumnRight( m_Paginiernummer,6,"0"));
		sbRow.append(getStringForColumnRight("",2," "));
		sbRow.append(getStringForColumnRight(m_Bundesland_FA,2," "));
		sbRow.append(getStringForColumnLeft(m_Steuernr,11,"0"));
		sbRow.append(getStringForColumnRight(m_Unterscheidungsnr,3,"0"));
		sbRow.append(getStringForColumnRight("",1," "));
		sbRow.append(getStringForColumnRight(m_Bestimmungsland,3," "));
		sbRow.append(getStringForColumnRight(m_Bestimmungsregion,2," "));
		sbRow.append(getStringForColumnRight("",2," "));
		sbRow.append(getStringForColumnRight(m_Geschaeftsart,2," "));
		sbRow.append(getStringForColumnRight(m_Verkehrszweig,1," "));
		sbRow.append(getStringForColumnRight("",1," "));
		sbRow.append(getStringForColumnRight("",6," "));
		sbRow.append(getStringForColumnRight("",6," "));
		sbRow.append(getStringForColumnRight(m_Warennr,8," "));
		sbRow.append(getStringForColumnRight(m_Land_Ursprung,3," "));
		sbRow.append(getStringForColumnRight("",5," "));
		sbRow.append(getStringForColumnRight(m_Eigenmasse, 11, " "));
		sbRow.append(getStringForColumnRight(m_Masseinheit, 11, " "));
		sbRow.append(getStringForColumnRight("",2," "));
		sbRow.append(getStringForColumnRight(m_Rechbetrag,11," "));
		sbRow.append(getStringForColumnRight(m_StatistischerBetrag == "" ? m_Rechbetrag : m_StatistischerBetrag,11," "));
		sbRow.append(getStringForColumnRight(m_Bezugsmonat,2," "));
		sbRow.append(getStringForColumnRight(m_Bezugsjahr,2," "));
		sbRow.append(getStringForColumnRight(m_Waehrungskennziffer,1," "));
		sbRow.append(getStringForColumnRight("",7," "));

		return sbRow.toString();
		
	}



}
