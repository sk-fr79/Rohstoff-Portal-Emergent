package rohstoff.businesslogic.bewegung.lager.utils;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;

/**
 * Klasse zum löschen aller Atomaren Daten, die durch die Konvertierung aus den Fuhren 
 * entstanden sind, und den Setzkasten-Daten
 * @author manfred
 *
 */
public class BG_Util_Clean_ATOM_Tables {

	
	Vector<String> m_vCleanSetzkasten = new Vector<String>();
	Vector<String> m_vCleanAtoms = new Vector<String>();
	
	
	public BG_Util_Clean_ATOM_Tables() {
		// TODO Auto-generated constructor stub
		
		m_vCleanSetzkasten.add("truncate table " + bibE2.cTO() + ".jt_bewegung_setzkasten");
		m_vCleanSetzkasten.add("truncate table " + bibE2.cTO() + ".jt_bewegung_setzkasten_k");
		m_vCleanSetzkasten.add("truncate table " + bibE2.cTO() + ".jt_bewegung_atom_verbucht");
		m_vCleanSetzkasten.add("truncate table " + bibE2.cTO() + ".jt_bewegung_atom_verbucht_k");

		
		m_vCleanAtoms.add("DELETE FROM " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM WHERE ID_BEWEGUNG_ATOM IN ( "
				+ "SELECT A.ID_BEWEGUNG_ATOM FROM " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM  A "
				+ "INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR_POS P on a.ID_BEWEGUNG_VEKTOR_POS = P.ID_BEWEGUNG_VEKTOR_POS "
				+ "INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V ON p.ID_BEWEGUNG_VEKTOR = V.ID_BEWEGUNG_VEKTOR "
				+ "LEFT OUTER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG B ON V.ID_BEWEGUNG = B.ID_BEWEGUNG "
				+ "WHERE  NOT "
				+ "(       A.ID_LAGER_KONTO IS NULL "
				+ "        AND B.ID_VPOS_TPA_FUHRE IS NULL "
				+ ") "
				+ "AND A.ID_MANDANT = " + bibALL.get_ID_MANDANT()
				+ ")" );
		
		m_vCleanAtoms.add("DELETE FROM " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR WHERE ID_BEWEGUNG_VEKTOR IN "
				+ "("
				+ "        SELECT V.ID_BEWEGUNG_VEKTOR FROM " + bibE2.cTO() + ".JT_BEWEGUNG_VEKTOR V "
				+ "        LEFT OUTER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM A ON A.ID_BEWEGUNG_VEKTOR = V.ID_BEWEGUNG_VEKTOR "
				+ "        WHERE A.ID_BEWEGUNG_VEKTOR IS NULL "
				+ ") AND JT_BEWEGUNG_VEKTOR.ID_MANDANT = " + bibALL.get_ID_MANDANT() );
		
		m_vCleanAtoms.add("DELETE FROM " + bibE2.cTO() + ".JT_BEWEGUNG WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT());
		
		
		
	}

	

	
	
	/**
	 * Löscht die Setzkasten-Daten
	 * @return
	 */
	public boolean cleanSetzkasten(){
		MyDBToolBox         m_MyDBToolbox = bibALL.get_myDBToolBox();
	
		// ausführen der Statements
		boolean bOK = true;
		bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(m_vCleanSetzkasten, true);
		
		return bOK;
	}
	
	
	
	
	/**
	 * Löscht die Atom-Tabellen und die Setzkasten-Daten 
	 * @return
	 */
	public boolean cleanATOMTables(){
		MyDBToolBox         m_MyDBToolbox = bibALL.get_myDBToolBox();
	
		// ausführen der Statements
		boolean bOK = true;
		bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(m_vCleanSetzkasten, false);
		bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(m_vCleanAtoms, true);
		return bOK;
	}
	
	
	
	
	
}


