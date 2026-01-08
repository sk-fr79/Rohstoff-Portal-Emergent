package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.exceptions.myException;

/**
 * Basisklasse für alle Modul-Link-Objekte 
 * d.h. alle Buttons, Multi-Buttons usw.
 * @author manfred
 *
 */
public abstract class MODUL_LINK_Object_Base extends MyE2_Grid{
	
	// der Link-Record
	protected Vector<RECORD_MODUL_CONNECT> v_rec = new Vector<RECORD_MODUL_CONNECT>();
	
	
	// beim Link kann es sein, dass mehrere Container zu schliessen sind.
	protected Vector<E2_BasicModuleContainer>  m_ContainerToClose = new Vector<E2_BasicModuleContainer>();

	// der Typ des Objektes 
	private String m_sIDTyp = null;
	
	/**
	 * Standard-Konstruktur für Grid mit einer Spalte
	 */
	public MODUL_LINK_Object_Base() {
		this( 1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	}
	
	/**
	 * Konstruktor für x Spalten 
	 * und dem Style MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()
	 * 
	 * @param cols
	 */
	public MODUL_LINK_Object_Base(int cols) {
		this( cols, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	}
	
	/**
	 * Flexibler Konstruktor für x Spalten mit beliebigem Style
	 * @param cols
	 * @param style
	 */
	public MODUL_LINK_Object_Base(int cols, MutableStyle style) {
		super( cols, style);
	}
	
	
	
	
	/**
	 * setzen (hinzufügen) eines Link-Records 
	 * @param rec
	 */
	protected void setLinkRecord (RECORD_MODUL_CONNECT rec){
		v_rec.add(rec);
	}
	
	protected void addLinkRecords(Vector<RECORD_MODUL_CONNECT> vRec){
		v_rec.addAll(vRec);
	}
	
	
	
	/**
	 * gibt den ersten Link-Record zurück
	 * @return
	 */
	protected RECORD_MODUL_CONNECT getLinkRecord(){
		if (v_rec.size() > 0){
			return v_rec.get(0);
		}  
		else {
			return null;
		}
	}
	
	
	
	/**
	 * gibt den Vector der Linkrecords zurück
	 * @return
	 */
	protected Vector<RECORD_MODUL_CONNECT> getLinkRecords(){
		return v_rec;
	}
	


	/**
	 * @param m_sIDTyp the m_sIDTyp to set
	 */
	public void set_sIDTyp(String m_sIDTyp) {
		this.m_sIDTyp = m_sIDTyp;
	}
	
	public String get_sIDTyp() {
		return m_sIDTyp;
	}
	 

	
	/**
	 * Enablen-Disabled des Link-Objektes
	 * @param bEnable
	 * @throws myException
	 */
	public  void set_bEnabled_For_Edit(boolean bEnable) throws myException{
		// das Grid
		super.set_bEnabled_For_Edit(bEnable);
		
		// die Inhalte...
		set_bEnabled(bEnable);
	}
	
	
	public abstract void set_bEnabled(boolean bEnable) throws myException;
	

	
}
