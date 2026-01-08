package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import java.util.HashSet;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;


/**
 * Factory-Klasse zum generieren von einzelnen Auswahlfeldern bei der Verbindung von Archiv-Anhängen
 * die Bottom-Up angebunden werden, d.h. z.B. von der Fuhre zu einem Stammdatensatz ARTIKEL oder ADRESSE 
 * @author manfred
 * @date   08.04.2013
 */
public class SingleLineSelector_Factory {

	private RECORD_ARCHIVMEDIEN_ext 	m_oRecArchiv = null;
	
	private String 						m_sTableName = null;
	private String						m_sID 		 = null;
	
	// hält die IDs nur einfach, damit keine doppelten IDs verwendet werden
	private HashSet<String> 			m_hsIDs 	 = new HashSet<String>();
	
	
	public SingleLineSelector_Factory(RECORD_ARCHIVMEDIEN_ext oRecArchiv) throws myException {
		m_oRecArchiv = oRecArchiv;
		
		if (m_oRecArchiv != null){
			m_sTableName 	= m_oRecArchiv.get_TABLENAME_cUF();
			m_sID			= m_oRecArchiv.get_ID_TABLE_cUF();
		}
		
	}
	
	
	
	/**
	 * gibt die Vordefinierten Auswahlfelder für die Adresse zurück
	 * Hier muss erweitert werden falls man nach vordefinierten verknüpften Adressen suchen möchte
	 * @author manfred
	 * @date   04.04.2013
	 * @return
	 * @throws myException 
	 */
	public Vector<SingleLineSelektor_Base> getSelectorForAddress() throws myException{
		Vector<SingleLineSelektor_Base> vSel = new Vector<SingleLineSelektor_Base>();
		
		
		if ( !bibALL.isEmpty(m_sTableName ) ){
			
			//
			// AUSGANGSPUNKT FUHRE:
			//
			if (m_sTableName.equalsIgnoreCase("VPOS_TPA_FUHRE")){
				RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(m_sID);
				
				
				String sID = null;
				
				SingleLineSelektor_Base sel = null;
				RECORD_ADRESSE recADR = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_start();
				addSingleLineSelectorForAddress(recADR, vSel, "Startadresse:");
				
				recADR = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_start();
				addSingleLineSelectorForAddress(recADR, vSel, "Lager Startadresse:");
				
				recADR = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel();
				addSingleLineSelectorForAddress(recADR, vSel, "Zieladresse:");
				
				recADR = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel();
				addSingleLineSelectorForAddress(recADR, vSel, "Lager Zieladresse:");
				
			} 
//			// weitere tabellen...
//			else if (m_sTableName.equalsIgnoreCase("VPOS_TPA_FUHRE")){
//			
//			}
		}
		return vSel;
	}
	
	
	/**
	 * Fügt einen SingleLineSelektor für die Adresse zum Dialog zu, wenn die Adresse nicht schon mal vorkam 
	 * @author manfred
	 * @date   04.04.2013
	 * @param rec
	 * @param vSel
	 * @throws myException
	 */
	private void addSingleLineSelectorForAddress(RECORD_ADRESSE rec,Vector<SingleLineSelektor_Base> vSel,String sLabelText) throws myException{
		if (rec == null) return;
		
		String sID = rec.get_ID_ADRESSE_cUF();
		if(!m_hsIDs.contains(sID)){
			SingleLineSelektor_Base sel = new SingleLineSelektor_Base("ADRESSE", rec.get_ID_ADRESSE_cUF(), getAdressDescription(rec),sLabelText);
			sel.get_tbInfo().set_iWidthPixel(500);
			vSel.add(sel);
		}
		m_hsIDs.add(sID);
	}
	
	private String getAdressDescription(RECORD_ADRESSE rec) throws myException{
		String sDesc = "";
		sDesc += rec.get_NAME1_cUF_NN("-");
		sDesc += " " + rec.get_NAME2_cUF_NN("-");
		sDesc += ", " + rec.get_STRASSE_cUF_NN("-");
		sDesc += ", " + rec.get_PLZ_cUF_NN("-");
		sDesc += " " +rec.get_ORT_cUF_NN("-");
		sDesc += " (" + rec.get_ID_ADRESSE_cUF_NN("-") + ")";
		return sDesc;
	}
	
	
	
	
	
	/**
	 * gibt die Vordefinierten Auswahlfelder für die Sorten zurück
	 * @author manfred
	 * @date   04.04.2013
	 * @return
	 * @throws myException 
	 */
	public Vector<SingleLineSelektor_Base> getSelectorForSorte() throws myException{
		Vector<SingleLineSelektor_Base> vSel = new Vector<SingleLineSelektor_Base>();
		
		if ( !bibALL.isEmpty(m_sTableName ) ){
			
			//
			// AUSGANGSPUNKT FUHRE:
			//
			if (m_sTableName.equalsIgnoreCase("VPOS_TPA_FUHRE")){
				RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(m_sID);
				
				
				String sID = null;
				
				SingleLineSelektor_Base sel = null;

				RECORD_ARTIKEL_BEZ rec = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek();
				addSingleLineSelectorForArtikel(rec, vSel, "Liefersorte:");
				
				rec = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk();
				addSingleLineSelectorForArtikel(rec, vSel, "Liefersorte:");
				
			} 
//			// weitere tabellen...
//			else if (m_sTableName.equalsIgnoreCase("KONTRAKT")){
//			
//			}
		}
		return vSel;
	}
	

	/**
	 * Fügt einen SingleLineSelektor für die Adresse zum Dialog zu, wenn die Adresse nicht schon mal vorkam 
	 * @author manfred
	 * @date   04.04.2013
	 * @param rec
	 * @param vSel
	 * @throws myException
	 */
	private void addSingleLineSelectorForArtikel(RECORD_ARTIKEL_BEZ rec,Vector<SingleLineSelektor_Base> vSel,String sLabelText) throws myException{
		if (rec == null) return;
		
		RECORD_ARTIKEL recArt = rec.get_UP_RECORD_ARTIKEL_id_artikel();
		String sID = recArt.get_ID_ARTIKEL_cUF();
		if(!m_hsIDs.contains(sID)){
			SingleLineSelektor_Base sel = new SingleLineSelektor_Base("ARTIKEL", sID, getArtikelDescription(recArt),sLabelText);
			sel.get_tbInfo().set_iWidthPixel(500);
			vSel.add(sel);
		}
		m_hsIDs.add(sID);
	}
	
	
	private String getArtikelDescription(RECORD_ARTIKEL rec) throws myException{
		String sDesc = "";
		sDesc += rec.get_ANR1_cUF_NN("-");
		sDesc += " " + rec.get_ARTBEZ1_cUF_NN("-");
		sDesc += " (" + rec.get_ID_ARTIKEL_cUF_NN("-") + ")";
		return sDesc;
	}
	


	
	
	
	public Vector<SingleLineSelektor_Base> getSelectorForKontrakt(){
		return null;
	}

	


	
	
}
