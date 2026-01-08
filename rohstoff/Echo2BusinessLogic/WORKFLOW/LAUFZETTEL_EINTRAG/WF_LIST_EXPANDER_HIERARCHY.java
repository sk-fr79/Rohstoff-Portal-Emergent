package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_Tree_Entry;

/**
 * Klasse bildet die Darstellungshierarchie auf, die für die Darstellung im Grid und in den Reports nötig sind
 * @author manfred
 * @date 11.12.2008
 */

public class WF_LIST_EXPANDER_HIERARCHY
{
	
	private WF_LIST_EXPANDER_HIERARCHY_Settings  m_Settings = new WF_LIST_EXPANDER_HIERARCHY_Settings();
	
	private int     m_countHiddenDeleted = 0;
	private int 	m_countHiddenUser = 0;
	private int 	m_countHiddenActive = 0;
	private int 	m_countHiddenPrivat = 0;
	private int 	m_countAll = 0;
	
	
	// muss immer übergeben werden
	RECLIST_LAUFZETTEL_EINTRAG m_oChilds = null;

	// Objekt für die Abbildung  der Hierarchie
	private WF_Tree_Entry m_oTreeRoot = null;

	// Vektor hält alle aktuell sichtbaren Elemente
	// wird auch für die Reports verwendet.
	private Vector<WF_LIST_EXPANDER_ROW_OBJECT> m_vDisplayRows = null;

	
	/***
	 * Konstruktor der die Liste der Einträge übergibt
	 * Die TreeRoot und die DisplayRows werden automatisch neu erzeugt
	 * @param oChilds
	 */
	public WF_LIST_EXPANDER_HIERARCHY(RECLIST_LAUFZETTEL_EINTRAG oChilds)
	{
		this.m_oChilds = oChilds;
		this.m_oTreeRoot = new WF_Tree_Entry("root", 0);
		this.m_vDisplayRows = new Vector<WF_LIST_EXPANDER_ROW_OBJECT>();
	}
	
	/**
	 * Default-Konstruktor, wenn die Objekte per getter und setter übergeben werden
	 */
	public WF_LIST_EXPANDER_HIERARCHY()
	{
		m_oTreeRoot = new WF_Tree_Entry("root", 0);
		m_vDisplayRows = new Vector<WF_LIST_EXPANDER_ROW_OBJECT>();
	}
	
	
	/**
	 * Konstruktor, wenn die Elemente vom Aufrufer übergeben werden zum füllen
	 * @param oChilds
	 * @param oTreeRoot
	 * @param vDisplayRows
	 */
	public WF_LIST_EXPANDER_HIERARCHY(RECLIST_LAUFZETTEL_EINTRAG oChilds, WF_Tree_Entry oTreeRoot,Vector<WF_LIST_EXPANDER_ROW_OBJECT> vDisplayRows)
	{
		this.m_oChilds = oChilds;
		this.m_oTreeRoot = oTreeRoot;
		this.m_vDisplayRows = vDisplayRows;
	}
	
	
	
	/**
	 * Startet den Aufbau der EntryListen
	 * Wenn alles gut läuft, kann man danach die Liste der anzuzeigenden IDs mit
	 * getVDisplayRows() holen
	 * @author manfred
	 * @date 11.12.2008
	 * @throws myException
	 */
	public void BuildEntryList() 
		throws myException
	{
		
		// die Displayrows löschen
		m_vDisplayRows.clear();
		
		this.generateHierarchy();
		this.addRows(m_oChilds, m_oTreeRoot);
	}
	
	
	/**
	 * Baut den Vektor für die Darstellung auf
	 * 
	 * @param oChilds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private void generateHierarchy()
			throws myException
	{

		m_oTreeRoot.removeChilds();

		
		String parent_id = null;

		// einen Clone des originalen Vektors holen
		Vector<String> oClone = (Vector<String>) m_oChilds.get_vKeyValues().clone(); 
		WF_Tree_Entry oEntry = null;
		RECORD_LAUFZETTEL_EINTRAG oRec = null;
		
		// aufbauen des Baums solange noch elemente vorhanden sind
		while (!oClone.isEmpty())
		{
			// jedesmal durchlaufen aller restlichen Elemente --- sehr
			// schlechtes vorgehen, aber machen wir erst mal so
			for (String id : oClone)
			{
				oRec = m_oChilds.get(id);
				if (oRec == null){
					continue;
				}
				
				try
				{
					parent_id = oRec.get_ID_EINTRAG_PARENT_cUF();
				} catch (myException e)
				{
					e.printStackTrace();
					throw new myException(
							"Fehler beim Aufbau der Laufzettel-Hierarchie");
				}
				if (parent_id == null)
				{
					oEntry = m_oTreeRoot;
				} else
				{
					oEntry = m_oTreeRoot.getNode(parent_id);
				}

				if (oEntry != null)
				{
					oEntry.addChild(id);
					oClone.remove(id);
					break;
				}

			}
		}
	}

	
	
	/***
	 * Geht die Hierarchie rekursiv durch und fügt die Zeilen ein Damit ist dann
	 * auch die Reihenfolge richtig
	 * @return TODO
	 */
	public int addRows(RECLIST_LAUFZETTEL_EINTRAG oChilds, WF_Tree_Entry oNode)
	{


		
		int nRows = 0;
		
		// ansonsten den Baum hinunterlaufen und immer den Childs entlang bis
		// nix mehr kommt.
		for (WF_Tree_Entry o : oNode.getChilds())
		{
			// **
			if (!o.getID().equals("root"))
			{
				try
				{
					if ( addRowToDisplayVector(oChilds, o.getID(), o.getDepth() ) )
					{
						nRows++;
					}
				} catch (myException e)
				{
					e.printStackTrace();
				}
			}
			// **

			if (o.getChilds().size() > 0)
			{
				nRows += addRows(oChilds, o);
			}
		}
		
		return nRows;
	}

	
	private  boolean addRowToDisplayVector(RECLIST_LAUFZETTEL_EINTRAG oChilds, String id,
			int depth) throws myException
	{
		// status, ob eine Zeile gezeigt werden darf oder nicht.
		RECORD_LAUFZETTEL_EINTRAG oRec = oChilds.get(id);
		// Stati aus DB 
		boolean bDeleted = S.NN(oRec.get_GELOESCHT_cF()).equals("Y");
		boolean bIsPrivate = S.NN(oRec.get_PRIVAT_cF()).equals("Y");

		String sErledigt = oRec.get_ABGESCHLOSSEN_AM_cUF();
		boolean bErledigt = S.NN(sErledigt).length() > 0;
		

		// Bearbeiter
		String sUBearbeiter = oRec.get_ID_USER_BEARBEITER_cUF();
		String sUBesitzer = oRec.get_ID_USER_BESITZER_cUF();
		
		boolean bUserIsBearbeiter = false;
		bUserIsBearbeiter = S.NN(sUBearbeiter).equals(m_Settings.get_idOwnUser());
				
		boolean bUserIsBesitzer = false;
		bUserIsBesitzer = S.NN(sUBesitzer).equals(m_Settings.get_idOwnUser());

		boolean bUserIsMe = false;
		bUserIsMe = S.NN(sUBearbeiter).equals(bibALL.get_ID_USER());
		bUserIsMe |= S.NN(sUBesitzer).equals(bibALL.get_ID_USER());


		boolean bShowRow = true;
		boolean bShowDeleted = false;
		boolean bShowActive = false;
		boolean bShowPrivat = false;
		
		boolean bShowUserBesitzer = false;
		boolean bShowUserBearbeiter = false;
		
		// Benutzer
		bShowUserBesitzer |= (m_Settings.is_showBesitzer() && bUserIsBesitzer);
		bShowUserBesitzer |= (m_Settings.is_showBesitzerNOT() && !bUserIsBesitzer);
		
		bShowUserBearbeiter |= (m_Settings.is_showBearbeiter() && bUserIsBearbeiter);
		bShowUserBearbeiter |= (m_Settings.is_showBearbeiterNOT() && !bUserIsBearbeiter);
		
		// falls kein User angegeben wurde, werden alle User gültig
		if (bibALL.isEmpty(m_Settings.get_idOwnUser())){
			bShowUserBearbeiter = true;
			bShowUserBesitzer = true;
		}
		
		// gelöschte
		bShowDeleted |= (m_Settings.is_showDeleted() == true && bDeleted);
		bShowDeleted |= (m_Settings.is_showUndeleted() == true && !bDeleted) ;
		
		// aktive
		bShowActive |= (m_Settings.is_showClosed() == true && bErledigt);
		bShowActive |= (m_Settings.is_showOpen() == true && !bErledigt);
		
		//zählen, wieviele versteckt sind, ausser die privaten
		// nur einfach zählen, deshalb hier hierarchisierun
		if (!bShowActive) {
			m_countHiddenActive++;
		} else if (!bShowUserBearbeiter && !bShowUserBesitzer) {
			m_countHiddenUser++;
		} else if (!bShowDeleted) {
			m_countHiddenDeleted++;
			
		}
		m_countAll++;
		
		// wenn die Zeile Privat ist, dann darf sie nur dem Besitzer oder Bearbeiter gezeigt werden,
		// ausser wenn der Benutzer Supervisor ist, dann bekommt er alle Zeilen angezeigt !!
		boolean bIstGF_or_DEVELOPER =  (new RECORD_USER(bibALL.get_RECORD_USER()).is_DEVELOPER_YES() || new RECORD_USER(bibALL.get_RECORD_USER()).is_GESCHAEFTSFUEHRER_YES());
		bShowPrivat = !(bIsPrivate && !bUserIsMe && !bIstGF_or_DEVELOPER) ;
		
		if (!bShowPrivat){
			m_countHiddenPrivat++;
		}

		// die Gruppen AND/OR-verknüpfen
		bShowRow = bShowDeleted && (bShowUserBearbeiter || bShowUserBesitzer) && bShowActive && bShowPrivat;
		
		if (!bShowRow) return false;
		
		WF_LIST_EXPANDER_ROW_OBJECT o = new WF_LIST_EXPANDER_ROW_OBJECT();
		o.id_Laufzettel_Eintrag = id;
		o.depth = depth;
		
		m_vDisplayRows.add(o);
		
		return true;
			
	}
	
	
	
	
	/**
	 * @return the m_oChilds
	 */
	public RECLIST_LAUFZETTEL_EINTRAG getM_oChilds()
	{
		return m_oChilds;
	}

	/**
	 * @param childs the m_oChilds to set
	 */
	public void setM_oChilds(RECLIST_LAUFZETTEL_EINTRAG childs)
	{
		m_oChilds = childs;
	}

	/**
	 * @return the oTreeRoot
	 */
	public WF_Tree_Entry getOTreeRoot()
	{
		return m_oTreeRoot;
	}

	/**
	 * @param treeRoot the oTreeRoot to set
	 */
	public void setOTreeRoot(WF_Tree_Entry treeRoot)
	{
		m_oTreeRoot = treeRoot;
	}

	/**
	 * @return the vDisplayRows
	 */
	public Vector<WF_LIST_EXPANDER_ROW_OBJECT> getVDisplayRows()
	{
		return m_vDisplayRows;
	}

	/**
	 * @param displayRows the vDisplayRows to set
	 */
	public void setVDisplayRows(Vector<WF_LIST_EXPANDER_ROW_OBJECT> displayRows)
	{
		m_vDisplayRows = displayRows;
	}

	
	
	public int get_countHiddenDeleted() {
		return m_countHiddenDeleted;
	}

	public int get_countHiddenUser() {
		return m_countHiddenUser;
	}

	public int get_countHiddenActive() {
		return m_countHiddenActive;
	}
	
	public int get_countHiddenPrivat() {
		return m_countHiddenPrivat;
	}

	public int get_countAll() {
		return m_countAll;
	}

	public WF_LIST_EXPANDER_HIERARCHY_Settings get_Settings() {
		return m_Settings;
	}

	public void set_Settings(WF_LIST_EXPANDER_HIERARCHY_Settings _Settings) {
		this.m_Settings = _Settings;
	}
	

}
