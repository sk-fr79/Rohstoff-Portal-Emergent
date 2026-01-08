package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MODUL_CONNECT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/**
 * Baut ein Grid auf und erzeugt 
 * für jeden Connector einen eingenen Button, 
 * default: vertikale Anordnung
 * falls Horizonatal, wird in das Grid eine Row eingebettet.
 * 
 * @author manfred
 *
 */
public class MODUL_LINK_Connector extends MyE2_Grid {
	
	protected String 		m_ID_Source = null;
	protected String 		m_Source 	= null;
	protected Vector<E2_BasicModuleContainer> m_ContainerToClose = null;
	
	protected MyE2_Row_EveryTimeEnabled m_RowForButtons = null;
	protected boolean 		m_bHorizontal = false;
	
	protected boolean 		m_bShowTextInButton = true;
	protected boolean 		m_bShowLabelInConnector = false;
	
	protected RECLIST_MODUL_CONNECT  m_list_Targets = null;
	
	protected Vector<MODUL_LINK_Object_Base> m_Link_Objects = null;
	
	
	public MODUL_LINK_Connector(  ) throws myException {
		super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.m_Link_Objects = new Vector<MODUL_LINK_Object_Base>();
	}
	
	public MODUL_LINK_Connector( boolean bHorizontal, boolean bShowTextInButton, boolean bShowLabelInConnector ) throws myException {
		super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		m_bShowTextInButton = bShowTextInButton;
		m_bShowLabelInConnector = bShowLabelInConnector;
		
		if (bHorizontal){
			m_RowForButtons = new MyE2_Row_EveryTimeEnabled(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			this.add(m_RowForButtons);
		}

		this.m_Link_Objects = new Vector<MODUL_LINK_Object_Base>();
	}

	
	
	public void initConnector(String source, String iD_source, Vector<E2_BasicModuleContainer> ContainerToClose) throws myException{
		this.m_Source = source;
		this.m_ID_Source = iD_source;
		this.m_ContainerToClose = ContainerToClose;
		
		this.getTargets();

		this.buildComponent();
		
	}
	
	
	public void initConnector(String source, String iD_source, E2_BasicModuleContainer ContainerToClose) throws myException{
		this.m_Source = source;
		this.m_ID_Source = iD_source;
		
		m_ContainerToClose = new Vector<E2_BasicModuleContainer>();
		m_ContainerToClose.add(ContainerToClose);
		
		this.getTargets();

		this.buildComponent();
		
	}
	
	
	
	/**
	 * Ermitteln aller Ziele zur quelle
	 * @throws myException 
	 */
	protected void getTargets() throws myException{
	
		if (!bibALL.isEmpty(m_Source) && !bibALL.isEmpty(m_ID_Source ))
		{
			m_list_Targets = new RECLIST_MODUL_CONNECT("QUELLE = '" + m_Source + "' AND ID_QUELLE = " + m_ID_Source , "");
		}
	}

	
	
	/**
	 *  Baut eine Column auf und wenn mehr als ein Ziel da ist, dann werden  
	 *  mehrere Buttons erzeugt
	 * @throws myException 
	*/
	protected void buildComponent() throws myException{
	
		MODUL_LINK_Object_Factory oFactory = new MODUL_LINK_Object_Factory();
		
	
		for (int i= 0; i< m_list_Targets.size(); i++){

			// link-Button generiefen
			MODUL_LINK_Button_Base o =oFactory.getLinkButton(m_list_Targets.get(i),m_ContainerToClose,m_bShowTextInButton, m_bShowLabelInConnector);
			
			if (o != null){
				// zum internen Vector dazufügen
				this.m_Link_Objects.add(o);
				
				
				// zum Grid dazufügen
				if(m_bHorizontal){
					m_RowForButtons.add(o);
				} else {
					this.add(o);
				}
			}
 		}
	}

	
	
	/**
	 * gibt einen Vector mit den Link-Objekten zurück
	 * @return
	 */
	public Vector<MODUL_LINK_Object_Base> get_Link_Objects(){
		return m_Link_Objects;
	}
	
	
	/**
	 * Enablen-Disabled des Link-Objektes
	 * @param bEnable
	 * @throws myException
	 */
	public void set_bEnabled_For_Edit(boolean bEnable) throws myException{
		super.set_bEnabled_For_Edit(bEnable);
		
		for(MODUL_LINK_Object_Base o : m_Link_Objects){
			//o.set_bEnabled_For_Edit(bEnable);
			o.set_bEnabled(bEnable);
		}
	};
	
	
	
}
