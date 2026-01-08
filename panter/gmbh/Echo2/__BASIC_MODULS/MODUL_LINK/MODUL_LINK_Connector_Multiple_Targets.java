package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MODUL_CONNECT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class MODUL_LINK_Connector_Multiple_Targets extends MODUL_LINK_Connector {

	private String m_TargetModul = null;
	
	/**
	 * Ein Sprungbutton auf eine Liste mit mehreren Ziel-IDs
	 * Dazu muss das Zielmodul explizit angegeben werden
	 * @param TargetModulName
	 * @throws myException
	 */
	public MODUL_LINK_Connector_Multiple_Targets(String TargetModulName) throws myException {
		super();
		m_TargetModul = TargetModulName;
	} 
	
	
	@Override
	protected void getTargets() throws myException {
		if (!bibALL.isEmpty(m_Source) && !bibALL.isEmpty(m_ID_Source ) && !bibALL.isEmpty(m_TargetModul))
		{
			m_list_Targets = new RECLIST_MODUL_CONNECT("QUELLE = '" + m_Source + "' AND ID_QUELLE = " + m_ID_Source + " AND ZIEL = '" + m_TargetModul + "' ", "");
		}
	} 
	
	@Override
	protected void buildComponent() throws myException {
	//		m_colBase = new MyE2_Column();
		
		MODUL_LINK_Object_Factory oFactory = new MODUL_LINK_Object_Factory();
		if (m_list_Targets.size() > 0){
			Vector<RECORD_MODUL_CONNECT> v = m_list_Targets.GET_SORTED_VECTOR(bibALL.get_Vector("ID_MODUL_CONNECT"), true);
			MODUL_LINK_Button_Base o =oFactory.getLinkButton(v,m_ContainerToClose,false,false);
			
		
			
			// zum internen Vector dazufügen
			this.m_Link_Objects.add(o);
			// zum Grid dazufügen
			this.add(o);
		}
	}
	
	
}
