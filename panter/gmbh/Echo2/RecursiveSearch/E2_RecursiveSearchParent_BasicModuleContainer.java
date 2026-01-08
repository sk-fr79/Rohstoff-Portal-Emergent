package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;



/**
 * @author martin
 * finds first mother-BasicModuleContainer
 */
public class E2_RecursiveSearchParent_BasicModuleContainer
{
	private Vector<E2_BasicModuleContainer> vMotherContainers = new Vector<E2_BasicModuleContainer>();
	
	
	public E2_RecursiveSearchParent_BasicModuleContainer(Component oComp)
	{
		super();
		this.find_Parent_E2_BasicModuleContainer(oComp);
	}

	
	public E2_RecursiveSearchParent_BasicModuleContainer(ActionEvent oEvent)
	{
		super();
		
		if (oEvent == null || !(oEvent.getSource() instanceof Component))
			return;
		
		Component oComp = (Component)oEvent.getSource();

		this.find_Parent_E2_BasicModuleContainer(oComp);
		
	}


	
	public E2_RecursiveSearchParent_BasicModuleContainer()
	{
		super();
		MyActionEvent oEvent = bibE2.get_LAST_ACTIONEVENT();
		
		if (!(oEvent.getSource() instanceof Component))
			return;
		
		Component oComp = (Component)oEvent.getSource();

		this.find_Parent_E2_BasicModuleContainer(oComp);
		
	}

	

	// sammelt alle E2_BasicModuleContainer-objekte in der hierarchie
	private void find_Parent_E2_BasicModuleContainer(Component oComp)
	{

		/*
		 * zuerst pruefen, ob es der sonderfall des angezeigten popup-fensters ist,
		 * wo buttons in der unteren splitpane angezeigt werden, die componentmap aber in der 
		 * oberen splitpane
		 */
		if (oComp instanceof MyE2IF__Component)
		{
			if (((MyE2IF__Component)oComp).EXT().get_oBasicModulContainerThisBelongsTo() != null)
			{
				this.vMotherContainers.add(((MyE2IF__Component)oComp).EXT().get_oBasicModulContainerThisBelongsTo());
			}	
		}
		
		Component oCompMother = oComp.getParent();
		
		if (oCompMother != null)
		{
			if (oCompMother instanceof E2_BasicModuleContainer)
			{
				 this.vMotherContainers.add((E2_BasicModuleContainer)oCompMother);   				// gefunden !!
			}
			this.find_Parent_E2_BasicModuleContainer(oCompMother); 	// weitersuchen
		}
		
	}

	
	public E2_BasicModuleContainer get_First_FoundContainer()
	{
		E2_BasicModuleContainer oContainer = null;
		if (this.vMotherContainers.size()>0)
			oContainer = (E2_BasicModuleContainer)this.vMotherContainers.get(0);
		
		return oContainer;
	}


	
	public E2_BasicModuleContainer get_First_FoundContainer_ThrowExWhenNotFound() throws myException
	{
		E2_BasicModuleContainer oContainer = null;
		if (this.vMotherContainers.size()>0)
			oContainer = (E2_BasicModuleContainer)this.vMotherContainers.get(0);

		if (oContainer == null)
			throw new myException("E2_RecursiveSearchParent_BasicModuleContainer: No Container Found !");

		return oContainer;
	}


	
	
	public E2_BasicModuleContainer get_First_FoundContainer_WithMessageBlock()
	{
		E2_BasicModuleContainer oContainer = null;
		for (int i=0; i<this.vMotherContainers.size();i++)
		{
			if (((E2_BasicModuleContainer)this.vMotherContainers.get(i)).get_bMessageBereichEin())
			{
				oContainer = (E2_BasicModuleContainer)this.vMotherContainers.get(i);
				break;
			}
		}
		return oContainer;
	}



	public E2_BasicModuleContainer get_First_MaskFoundContainer()
	{
		E2_BasicModuleContainer_MASK oContainer = null;
		for (int i=0; i<this.vMotherContainers.size();i++)
		{
			if (this.vMotherContainers.get(i) instanceof E2_BasicModuleContainer_MASK)
			{
				oContainer = (E2_BasicModuleContainer_MASK)this.vMotherContainers.get(i);
				break;
			}
		}
		return oContainer;
	}

	
	public E2_BasicModuleContainer get_First_MaskFoundContainer__ThrowExWhenNotFound() throws myException
	{
		E2_BasicModuleContainer_MASK oContainer = null;
		for (int i=0; i<this.vMotherContainers.size();i++)
		{
			if (this.vMotherContainers.get(i) instanceof E2_BasicModuleContainer_MASK)
			{
				oContainer = (E2_BasicModuleContainer_MASK)this.vMotherContainers.get(i);
				break;
			}
		}
		
		if (oContainer == null)
			throw new myException("get_First_MaskFoundContainer__ThrowExWhenNotFound: No Container Found !");

		
		return oContainer;
	}



	
	
	
	public E2_BasicModuleContainer get_First_FoundContainer__WithMessageBlock_ThrowExWhenNotFound() throws myException
	{
		E2_BasicModuleContainer oContainer = null;
		for (int i=0;i<this.vMotherContainers.size();i++)
		{
			if (((E2_BasicModuleContainer)this.vMotherContainers.get(i)).get_bMessageBereichEin())
			{
				oContainer = (E2_BasicModuleContainer)this.vMotherContainers.get(i);
				break;
			}
		}

		if (oContainer == null)
			throw new myException("E2_RecursiveSearchParent_BasicModuleContainer: No Container Found !");

		return oContainer;
	}


	
	
	public E2_BasicModuleContainer get_First_FoundContainer_With_MODUL_IDENTIFIER()
	{
		E2_BasicModuleContainer oContainer = null;
		for (int i=0; i<this.vMotherContainers.size();i++)
		{
			if (S.isFull(this.vMotherContainers.get(i).get_MODUL_IDENTIFIER()))
			{
				oContainer = (E2_BasicModuleContainer)this.vMotherContainers.get(i);
				break;
			}
		}
		return oContainer;
	}


	public E2_BasicModuleContainer get_First_FoundContainer_With_MODUL_IDENTIFIER_FROM_CALLING_MODULE()
	{
		E2_BasicModuleContainer oContainer = null;
		for (int i=0; i<this.vMotherContainers.size();i++)
		{
			if (S.isFull(this.vMotherContainers.get(i).get_MODUL_IDENTIFIER_OF_CALLING_BasicModuleContainer()))
			{
				oContainer = (E2_BasicModuleContainer)this.vMotherContainers.get(i);
				break;
			}
		}
		return oContainer;
	}

	
	
	
	public E2_BasicModuleContainer get_First_FoundContainer_With_MODUL_IDENTIFIER_ThrowExWhenNotFound() throws myException
	{
		E2_BasicModuleContainer oContainer = null;
		for (int i=0;i<this.vMotherContainers.size();i++)
		{
			if (S.isFull(this.vMotherContainers.get(i).get_MODUL_IDENTIFIER()))
			{
				oContainer = (E2_BasicModuleContainer)this.vMotherContainers.get(i);
				break;
			}
		}

		if (oContainer == null)
			throw new myException("E2_RecursiveSearchParent_BasicModuleContainer: No Container Found !");

		return oContainer;
	}

	
	
	
	
	public Vector<E2_BasicModuleContainer> get_vMotherContainers() 
	{
		return vMotherContainers;
	}

	public Vector<E2_BasicModuleContainer> get_vMotherContainers_ThrowExWhenNotFound()  throws myException
	{
		if (this.vMotherContainers.isEmpty())
			throw new myException("E2_RecursiveSearchParent_BasicModuleContainer: No Container Found !");
			
		return vMotherContainers;
	}

	
}
