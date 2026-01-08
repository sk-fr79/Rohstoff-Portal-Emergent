package panter.gmbh.Echo2.RecursiveSearch;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.exceptions.myException;


//NEU_09

/**
 * @author martin
 * Auf masken kann nach beliebigen Maskenwerten von datenbankfeldern gesucht werden
 * Dazu wird zuerst mit einer referenzkomponente aus der maske ein E2_BasicModuleContainer_MASK gesucht,
 * von diesem dann der ComponentMap-Vector gezogen und darin nach den gesuchten hash-maps gefahndet
 */
public class E2_RecursiveSearch_MaskInfo
{
	private E2_BasicModuleContainer_MASK 	oMotherContainerMASK = null;
	
	private E2_vCombinedComponentMAPs		vVectorWithComponentMAPs = null;
	
	public E2_RecursiveSearch_MaskInfo(Component oComp) throws myException
	{
		super();
		this.__init(oComp);
	}

	
	public E2_RecursiveSearch_MaskInfo(ActionEvent oEvent) throws myException
	{
		super();
		
		if (oEvent == null || !(oEvent.getSource() instanceof Component))
			throw new myException("E2_RecursiveSearch_GetMaskValue: NULL-event is not allowed !");
		this.__init((Component)oEvent.getSource());
	}


	
	private void __init(Component oComp) throws myException
	{
		if (oComp == null)
			throw new myException("E2_RecursiveSearch_GetMaskValue: NULL-component is not allowed !");
		
		this.oMotherContainerMASK = this.get_Parent_E2_BasicModuleContainer(oComp);
		
		if (this.oMotherContainerMASK == null)
			throw new myException("E2_RecursiveSearch_GetMaskValue: no Mask-Container found !");
	
		this.vVectorWithComponentMAPs = this.oMotherContainerMASK.get_vCombinedComponentMAPs();
		
		if (this.vVectorWithComponentMAPs == null || this.vVectorWithComponentMAPs.size()==0)
			throw new myException("E2_RecursiveSearch_GetMaskValue: no ComponentMaps in Mask found !");
	
	}
		
	
	private E2_BasicModuleContainer_MASK get_Parent_E2_BasicModuleContainer(Component oComp)
	{
		E2_BasicModuleContainer_MASK oContentContainer = null;
		
		Component oCompMother = oComp.getParent();
		if (oCompMother instanceof E2_BasicModuleContainer_MASK)
		{
			if (((E2_BasicModuleContainer)oCompMother).get_oGridTopLineButtonsAndMessages().isVisible())
			{
				oContentContainer = (E2_BasicModuleContainer_MASK)oCompMother;   		
			}
			else
			{
				oContentContainer = this.get_Parent_E2_BasicModuleContainer(oCompMother);   // weitersuchen
			}
		}
		else if (oCompMother != null)
			oContentContainer = this.get_Parent_E2_BasicModuleContainer(oCompMother);
		
		return oContentContainer;
	}

	
	public Component get_Component(String HASH_KEY) throws myException
	{
		Vector<MyE2IF__Component> vFoundComponents = new Vector<MyE2IF__Component>();
		
		for (E2_ComponentMAP oMap: this.vVectorWithComponentMAPs)
		{
			HashMap<String,MyE2IF__Component> hmRealMap = oMap.get_REAL_ComponentHashMap();

			if (hmRealMap.containsKey(HASH_KEY))
				vFoundComponents.add(hmRealMap.get(HASH_KEY));
		}
		
		if (vFoundComponents.size() != 1)
			throw new myException("E2_RecursiveSearch_GetMaskValue:get_Component:No UNIQUE Component with HASHKEY "+HASH_KEY+" was found !");
		
		return (Component)vFoundComponents.get(0);
	}
	

	public MyE2IF__Component get_MyE2IF_Component(String HASH_KEY) throws myException
	{
		Vector<MyE2IF__Component> vFoundComponents = new Vector<MyE2IF__Component>();
		
		for (E2_ComponentMAP oMap: this.vVectorWithComponentMAPs)
		{
			HashMap<String,MyE2IF__Component> hmRealMap = oMap.get_REAL_ComponentHashMap();

			if (hmRealMap.containsKey(HASH_KEY))
				vFoundComponents.add(hmRealMap.get(HASH_KEY));
		}
		
		if (vFoundComponents.size() != 1)
			throw new myException("E2_RecursiveSearch_GetMaskValue:get_Component:No UNIQUE Component with HASHKEY "+HASH_KEY+" was found !");
		
		return (MyE2IF__Component)vFoundComponents.get(0);
	}

	
	
	public String get_ActualMaskValue(String HASH_KEY) throws myException
	{

		Component oComp = this.get_Component(HASH_KEY);
		if (oComp instanceof MyE2IF__DB_Component)
		{
			return ((MyE2IF__DB_Component)oComp).get_cActualMaskValue();
		}
		else
			throw new myException("E2_RecursiveSearch_GetMaskValue:get_Component:No MyE2IF__DB_Component with HASHKEY "+HASH_KEY+" was found !");
	}
	
	
	
	
	
	
	public MyE2IF__DB_Component get_DBComponent(String HASH_KEY,String cTABLE_NAME) throws myException
	{
		Vector<MyE2IF__Component> vFoundComponents = new Vector<MyE2IF__Component>();
		
		for (E2_ComponentMAP oMap: this.vVectorWithComponentMAPs)
		{
			HashMap<String,MyE2IF__Component> hmRealMap = oMap.get_REAL_ComponentHashMap();

			if (hmRealMap.containsKey(HASH_KEY) && 
				hmRealMap.get(HASH_KEY) instanceof MyE2IF__DB_Component &&
				((MyE2IF__DB_Component)hmRealMap.get(HASH_KEY)).EXT_DB().get_oSQLField().get_cTableName().toUpperCase().equals(cTABLE_NAME.toUpperCase()))
					vFoundComponents.add(hmRealMap.get(HASH_KEY));
		}
		
		if (vFoundComponents.size() != 1)
			throw new myException("E2_RecursiveSearch_GetMaskValue:get_DBComponent:No UNIQUE Component with HASHKEY "+HASH_KEY+"AND Tablename "+cTABLE_NAME+" was found !");
		
		return (MyE2IF__DB_Component)vFoundComponents.get(0);
	}
	

	public String get_DBActualMaskValue(String HASH_KEY,String cTABLE_NAME) throws myException
	{
		MyE2IF__DB_Component oComp = this.get_DBComponent(HASH_KEY,cTABLE_NAME);
		return ((MyE2IF__DB_Component)oComp).get_cActualMaskValue();
	}
	

	
	
	
	
	
	
	
	public E2_ComponentMAP get_ComponentMAP(int iIndex) throws myException
	{
		if (iIndex < this.vVectorWithComponentMAPs.size())
		{
			return (E2_ComponentMAP)this.vVectorWithComponentMAPs.get(iIndex);
		}
		else
			throw new myException("E2_RecursiveSearch_GetMaskValue:get_Component:No ComponentMap with Index "+iIndex+" was found !");
	}
	

	
	public E2_ComponentMAP get_ComponentMAP(String cBaseTableName) throws myException
	{
		E2_ComponentMAP oMap = null;
		
		for (int i=0; i<this.vVectorWithComponentMAPs.size();i++)
		{
			if (((E2_ComponentMAP)this.vVectorWithComponentMAPs.get(i)).get_oSQLFieldMAP().get_cMAIN_TABLE().equals(cBaseTableName))
			{
				oMap=(E2_ComponentMAP)this.vVectorWithComponentMAPs.get(i);
				break;
			}
		}
			
		if (oMap == null)
			throw new myException("E2_RecursiveSearch_GetMaskValue:get_Component:No ComponentMap with BaseTableName "+cBaseTableName+" was found !");
		
		return oMap;
	}


	public E2_BasicModuleContainer_MASK get_oMotherContainerMASK() 
	{
		return oMotherContainerMASK;
	}

	

}
