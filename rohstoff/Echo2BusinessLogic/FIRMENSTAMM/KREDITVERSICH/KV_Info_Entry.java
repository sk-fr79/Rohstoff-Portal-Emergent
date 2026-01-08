package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Eintrag einer Kreditversicherungs-Vereinbarung
 * @author manfred
 * @date   26.10.2011
 */
public class KV_Info_Entry {

	private BigDecimal _Betrag = null;
	private BigDecimal _Anfragebetrag = null;
	private Date	   _Gueltig_ab = null;
	private Date	   _Gueltig_bis = null;
	private String     _Description = null;
	private String     _idKVKopf = null;

	
	public KV_Info_Entry() {
		// TODO Auto-generated constructor stub
	}
	
	

	public KV_Info_Entry(
			BigDecimal m_Betrag, 
			BigDecimal m_Anfragebetrag,
			Date m_Gueltig_ab, 
			Date m_Gueltig_bis
			,String m_Description
			,String m_idKVKopf) {
		super();
		
		this._Betrag = m_Betrag;
		this._Anfragebetrag = m_Anfragebetrag;
		this._Gueltig_ab = m_Gueltig_ab;
		this._Gueltig_bis = m_Gueltig_bis;
		this._Description = m_Description;
		this._idKVKopf = m_idKVKopf;
	}
	
	/**
	 * @param m_Betrag the m_Betrag to set
	 */
	public void set_Betrag(BigDecimal m_Betrag) {
		this._Betrag = m_Betrag;
	}
	/**
	 * @return the m_Betrag
	 */
	public BigDecimal get_Betrag() {
		return _Betrag;
	}
	/**
	 * @param m_Anfragebetrag the m_Anfragebetrag to set
	 */
	public void set_Anfragebetrag(BigDecimal m_Anfragebetrag) {
		this._Anfragebetrag = m_Anfragebetrag;
	}
	/**
	 * @return the m_Anfragebetrag
	 */
	public BigDecimal get_Anfragebetrag() {
		return _Anfragebetrag;
	}
	/**
	 * @param m_Gueltig_ab the m_Gueltig_ab to set
	 */
	public void set_Gueltig_ab(Date m_Gueltig_ab) {
		this._Gueltig_ab = m_Gueltig_ab;
	}
	/**
	 * @return the m_Gueltig_ab
	 */
	public Date get_Gueltig_ab() {
		return _Gueltig_ab;
	}
	/**
	 * @param m_Gueltig_bis the m_Gueltig_bis to set
	 */
	public void set_Gueltig_bis(Date m_Gueltig_bis) {
		this._Gueltig_bis = m_Gueltig_bis;
	}
	/**
	 * @return the m_Gueltig_bis
	 */
	public Date get_Gueltig_bis() {
		return _Gueltig_bis;
	}



	public String get_Description() {
		return _Description;
	}



	public void set_Description(String m_Description) {
		this._Description = m_Description;
	}



	public String get_idKVKopf() {
		return _idKVKopf;
	}



	public void set_idKVKopf(String m_idKVKopf) {
		this._idKVKopf = m_idKVKopf;
	}
	
}
