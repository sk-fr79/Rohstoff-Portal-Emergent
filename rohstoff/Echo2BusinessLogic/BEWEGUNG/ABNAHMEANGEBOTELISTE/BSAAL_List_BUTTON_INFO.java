package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FS__Adress_Info_Zentrum;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.FS__Adress_Info_Zentrum_NG;

public class BSAAL_List_BUTTON_INFO extends MyE2_DB_Button 
{
	/**
	 * schalter wird benutzt um die sprungfunktionen im popup abzuschalten, wenn der editmodus an ist
	 */
	private boolean b_allow_jumps_in_infopopup = true;

	public BSAAL_List_BUTTON_INFO(SQLField osqlField) throws myException 
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
	
//	public void setNormalMode(){
//		this.add_oActionAgent(new XX_ActionAgent() 
//		{
//
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				new ownBasicContainer();
//			}	
//		});
//	}
	
//	public void setDebugMode(){
//		this.add_oActionAgent(new XX_ActionAgent() 
//		{
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				if(ENUM_MANDANT_DECISION.USE_NEW_INFO_BUTTON.is_YES()){
//					new ownBasicContainer_ng();
//				}else{
//					new ownBasicContainer();
//				}
//			}	
//		});
//	}

	//##TODO@Sebastien: 25.07.2016 - neues Adresse info system
	private class ownBasicContainer extends E2_BasicModuleContainer
	{

		public ownBasicContainer() throws myException 
		{
			super();

			RECORD_VPOS_STD  recAngebotZeile = new RECORD_VPOS_STD(BSAAL_List_BUTTON_INFO.this.get_cActualRowID());

			if (recAngebotZeile!=null) {
				RECORD_VKOPF_STD rec_kopf = recAngebotZeile.get_UP_RECORD_VKOPF_STD_id_vkopf_std();

				if (rec_kopf!=null) {

					FS__Adress_Info_Zentrum oAdressInfo = new FS__Adress_Info_Zentrum(this);

					oAdressInfo.set_jump_is_active(BSAAL_List_BUTTON_INFO.this.b_allow_jumps_in_infopopup);

					oAdressInfo.init_INFO(rec_kopf.get_ID_ADRESSE_cUF());

					this.add(oAdressInfo, E2_INSETS.I_2_2_2_2);

					this.set_iVerticalOffsetForTabbedPane(130);

					this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), 
							new MyE2_String("NG-Übersicht: ",true,oAdressInfo.get_recADRESSE().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")),false));
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Adresse nicht identifiziert (1) !")));
				}
			}
		}
	}

	private class ownBasicContainer_ng extends E2_BasicModuleContainer
	{

		public ownBasicContainer_ng() throws myException 
		{
			super();

			RECORD_VPOS_STD  recAngebotZeile = new RECORD_VPOS_STD(BSAAL_List_BUTTON_INFO.this.get_cActualRowID());

			if (recAngebotZeile!=null) {
				RECORD_VKOPF_STD rec_kopf = recAngebotZeile.get_UP_RECORD_VKOPF_STD_id_vkopf_std();

				if (rec_kopf!=null) {

					FS__Adress_Info_Zentrum_NG oAdressInfo = new FS__Adress_Info_Zentrum_NG(this);

					oAdressInfo.set_jump_is_active(BSAAL_List_BUTTON_INFO.this.b_allow_jumps_in_infopopup);

					oAdressInfo.init_INFO(rec_kopf.get_ID_ADRESSE_cUF());

					this.add(oAdressInfo, E2_INSETS.I_2_2_2_2);

					this.set_iVerticalOffsetForTabbedPane(130);

					this.set_oResizeHelper(new ownResizer());
					
					this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(800), 
							new MyE2_String("Übersicht: ",true,oAdressInfo.get_recADRESSE().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")),false," (*)", false));
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler: Adresse nicht identifiziert (1) !")));
				}
			}
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
			return new BSAAL_List_BUTTON_INFO(this.EXT_DB().get_oSQLField());
		} 
		catch (myException e) 
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}


	/*
	 * diese funktion wird hier auser gefecht gesetzt 
	 */
	public void set_allow_jumps_in_infopopup(boolean allow) throws myException {
		this.b_allow_jumps_in_infopopup = allow;
	}



}
