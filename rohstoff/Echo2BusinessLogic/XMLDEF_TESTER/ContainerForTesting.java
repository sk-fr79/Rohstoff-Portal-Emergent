package rohstoff.Echo2BusinessLogic.XMLDEF_TESTER;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.xmlDefTools.E2_ModuleContainerLIST_XML;
import panter.gmbh.Echo2.Container.xmlDefTools.XStreamLoaderListDefs;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ColumnForFileUpload;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.Project_TableNamingAgent;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class ContainerForTesting extends E2_BasicModuleContainer
{
	private MyE2_Column 				oContent = 			new MyE2_Column();
	private MyE2_Grid	 				oGridForButtons = 	new MyE2_Grid();
	private MyE2_Column	 				oColForOutput	=	new MyE2_Column();
	private MyE2_Button 				BT_Clean	= new MyE2_Button(new MyE2_String("Ausgabebereich löschen"));
	
	private E2_ColumnForFileUpload 	oUploader = null;
	
	
	public ContainerForTesting() throws myException
	{
		super();
		
		this.oUploader = new E2_ColumnForFileUpload(	
								bibALL.get_CompleteTempPath(true),true,null,null);
		
		this.oUploader.set_ActionAfterUpload(new actionTest());
		

		this.set_MODUL_IDENTIFIER(E2_CONSTANTS_AND_NAMES.NAME_MODUL_XMLDEFTESTER);
		
		this.oContent.add(new MyE2_Label(new MyE2_String("Listen definieren ...")));
		this.oContent.add(this.oGridForButtons,new Insets(5,10,5,10));
		this.oContent.add(oUploader,new Insets(5,10,5,10));
		this.oContent.add(this.oColForOutput,new Insets(5,10,5,10));

		this.oGridForButtons.add(new BT_StartTest());
		this.oGridForButtons.add(this.BT_Clean);
		
		this.BT_Clean.add_oActionAgent(new actionClearOutput());
		
//		/*
//		 * jetzt den maskenbereich dranhaengen
//		 */
//		this.oContent.add(new Separator());
//		this.oContent.add(new MyE2_Label(new MyE2_String("Masken definieren ...")));
//		this.oContent.add(new E2_ComponentGroupHorizontal(0,
//									new MyE2_Label(new MyE2_String("Bitte wählen Sie eine Tabelle:")),
//									new DROPDOWN_SelectTableForBuildingMASK(), null));
		
		this.add(this.oContent);
		
		
		
		
	}
	
	
	private class BT_StartTest extends MyE2_Button
	{

		public BT_StartTest()
		{
			super(new MyE2_String("Starte Test"));
			this.add_oActionAgent(new actionTest());
		}
		
	}
	

	private class actionTest extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			
			
			
			ContainerForTesting.this.oColForOutput.removeAll();
			try
			{
				String cUploadFileName = ContainerForTesting.this.oUploader.get_cStoredFileNameWithEndig();
				
				if 		(cUploadFileName.endsWith(".xml"))
				{
					
					// XStreamLoader oLoader = new XStreamLoader(ContainerForTesting.this.oTxtCode.getText(),true);
					XStreamLoaderListDefs oLoaderLIST = new XStreamLoaderListDefs(ContainerForTesting.this.oUploader.get_cStoredFileNameWithEndig());
					
					E2_ModuleContainerLIST_XML oEditContainer = new E2_ModuleContainerLIST_XML(new Project_TableNamingAgent(),
																									  oLoaderLIST.get_oList());
					
					oEditContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(900),null);
				}
				
			}
			catch (myException ex)
			{
				if (ex.get_StackFromMotherException() != null)
				{
					for (int i=0;i<ex.get_StackFromMotherException().length;i++)
					{
						String cInfo = ex.get_StackFromMotherException()[i].getClassName()+" --  "+
								ex.get_StackFromMotherException()[i].getMethodName()+
								ex.get_StackFromMotherException()[i].toString();
						
						ContainerForTesting.this.oColForOutput.add(new MyE2_Label(new MyE2_String(cInfo,false)));
					}
				}
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				for (int i=0;i<ex.getStackTrace().length;i++)
				{
					String cInfo = ex.getStackTrace()[i].getClassName()+" --  "+
									ex.getStackTrace()[i].getMethodName()+
									ex.getStackTrace()[i].toString();
					
					ContainerForTesting.this.oColForOutput.add(new MyE2_Label(new MyE2_String(cInfo,false)));
				}
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ex.getLocalizedMessage(),false)));
			}
		}
		
	}
	
	
	private class actionClearOutput extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			ContainerForTesting.this.oColForOutput.removeAll();
		}
	}

	
}
