package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.Hashtable;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class WF_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 57601827713053139L;

	private String cID_LAUFZETTEL = null;
	private String cID_USER_BEARBEITER = null;
	
	
	
	/**
	 * @deprecated Use {@link #WF_MASK_BasicModuleContainer(String,String,String)} instead
	 */
	public WF_MASK_BasicModuleContainer(String ID_LAUFZETTEL, String ID_USER_BEARBEITER) throws myException
	{
		this(ID_LAUFZETTEL, ID_USER_BEARBEITER, null);
	}


	
	public WF_MASK_BasicModuleContainer(String ID_LAUFZETTEL, String ID_USER_BEARBEITER, String ID_EINTRAG_PARENT) throws myException
	{
		this(ID_LAUFZETTEL, ID_USER_BEARBEITER,ID_EINTRAG_PARENT,null);
	}


	
	public WF_MASK_BasicModuleContainer(String ID_LAUFZETTEL, String ID_USER_BEARBEITER, String ID_EINTRAG_PARENT, Hashtable<String, String> Parameters) throws myException
	{
		super(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_MASKE);
		
		
		this.cID_LAUFZETTEL=ID_LAUFZETTEL;
		this.cID_USER_BEARBEITER = ID_USER_BEARBEITER;
		this.set_bVisible_Row_For_Messages(true);
		
		WF_MASK_ComponentMAP oWF_MASK_ComponentMAP = new WF_MASK_ComponentMAP(cID_LAUFZETTEL,cID_USER_BEARBEITER, ID_EINTRAG_PARENT);
		//this.get_oRowForButtons().add(new )
		
		this.INIT(oWF_MASK_ComponentMAP, new WF_MASK(oWF_MASK_ComponentMAP), new Extent(900), new Extent(650));
		
		
		
	}
	
	
	
	
}
