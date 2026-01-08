package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FS__Adress_Info_Zentrum;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.FS__Adress_Info_Zentrum_NG;

public class FS__LIST_BUTTON_INFO extends MyE2_DB_Button 
{

	public FS__LIST_BUTTON_INFO(SQLField osqlField) throws myException 
	{
		super(osqlField);
		this.__setImages(E2_ResourceIcon.get_RI("inforound.png"),true);
		this.set_bNoTextOnButton(true);
		
		this.add_oActionAgent(new XX_ActionAgent() 
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				if(ENUM_MANDANT_DECISION.USE_NEW_INFO_BUTTON.is_YES()){
					new ownBasicContainer_ng();
				}else{
					new ownBasicContainer();
				}
			}
		});
		
	}

	//##TODO@Sebastien: 25.07.2016 - neues Adresse info system
	
	private class ownBasicContainer_ng extends E2_BasicModuleContainer
	{
		
		public ownBasicContainer_ng() throws myException 
		{
			super();
			FS__Adress_Info_Zentrum_NG oAdressInfo = new FS__Adress_Info_Zentrum_NG(this);
			oAdressInfo.init_INFO(FS__LIST_BUTTON_INFO.this.get_cActualRowID());
			
			this.add(oAdressInfo, E2_INSETS.I_2_2_2_2);
			
			this.set_iVerticalOffsetForTabbedPane(130);
			
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(800), 
					new MyE2_String("Übersicht: ",true,oAdressInfo.get_recADRESSE().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")),false," (*)", false));
			
		}
	}
	
	private class ownBasicContainer extends E2_BasicModuleContainer
	{
		
		public ownBasicContainer() throws myException 
		{
			super();
			FS__Adress_Info_Zentrum oAdressInfo = new FS__Adress_Info_Zentrum(this);
			oAdressInfo.init_INFO(FS__LIST_BUTTON_INFO.this.get_cActualRowID());
			
			this.add(oAdressInfo, E2_INSETS.I_2_2_2_2);
			
			this.set_iVerticalOffsetForTabbedPane(130);
			
			this.set_oResizeHelper(new ownResizer());
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), 
					new MyE2_String("Übersicht: ",true,oAdressInfo.get_recADRESSE().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")),false));
			
		}
	}
	
	private class ownResizer extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
			
			Extent  oWidth = ownContainer.get_oExtWidth();
			Extent  oHeight = ownContainer.get_oExtHeight();
			
			if (oWidth.getUnits()==Extent.PX && oHeight.getUnits()==Extent.PX)
			{
				E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(ownContainer, bibALL.get_Vector(MyE2_ContainerEx.class.getName()), null);
				
				if (oSearch.get_vAllComponents().size()==1)
				{
					MyE2_ContainerEx oContainerEx = (MyE2_ContainerEx)oSearch.get_vAllComponents().get(0);
					
					oContainerEx.setHeight(new Extent(oHeight.getValue()-100));
				}
			}
			
		}
	
	}
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		try 
		{
			return new FS__LIST_BUTTON_INFO(this.EXT_DB().get_oSQLField());
		} 
		catch (myException e) 
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	
}
