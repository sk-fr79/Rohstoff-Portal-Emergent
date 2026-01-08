package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.Simple_Component_USER_DROPDOWN;

public class USER__PopUpUserSettings_CONTAINER extends Project_BasicModuleContainer 
{
	private 	E2_NavigationList 	oNavigationListFromListModule = null;
	
	private MyString 						cError = 	null;
	private Simple_Component_USER_DROPDOWN  oDDUser = 	null;
	
	private String							cSelectedUser = null; 	
	
	private MyE2_Button						oButtonOK = new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"),true);
	private MyE2_Button						oButtonCancel = new MyE2_Button(E2_ResourceIcon.get_RI("cancel.png"),true);
	
	private MyE2_CheckBox 					oCB_Menues = new MyE2_CheckBox("Übernahme der Menüstruktur");
	private MyE2_CheckBox 					oCB_Rechte = new MyE2_CheckBox("Übernahme der Buttonrechte");
	
	private MyE2_Grid					   	oGridBase = new MyE2_Grid(2,0);
	
	private RECORD_USER 					oUser = null; 


	public USER__PopUpUserSettings_CONTAINER(E2_NavigationList NavigationListFromListModule) throws myException 
	{
		super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_USERVERWALTUNG_LISTE_POPUP_USERSETTINGS.get_callKey());
		this.oNavigationListFromListModule = NavigationListFromListModule;
		this.build_Content();
	}


	public void build_Content() throws myException 
	{
		Vector<String> vIds = this.oNavigationListFromListModule.get_vSelectedIDs_Unformated();
		
		if (vIds.size()!=1)
		{
			this.cError = new MyE2_String("Bitte genau einen Benutzer auswählen !!");
		}
		else
		{
			this.oDDUser = new Simple_Component_USER_DROPDOWN(false);
			this.oDDUser.add_oActionAgent(new ActionSelectUser());

			this.oButtonCancel.add_oActionAgent(new ActionCloseWindow());
			this.oButtonOK.add_oActionAgent(new ActionCopyUserStructure());
			
			
			this.cSelectedUser = (String) vIds.get(0);
			
			this.add(this.oGridBase, E2_INSETS.I_10_10_10_10);
			
			this.oUser = new RECORD_USER(this.cSelectedUser);
			
			this.oGridBase.add(new MyE2_Label(new MyE2_String("Benutzerrechte/Menüstruktur kopieren ..."),MyE2_Label.STYLE_TITEL_BIG()), 2,new Insets(2,2,2,20));

			this.oGridBase.add(new MyE2_Label("Ziel ist der Benutzer: "+oUser.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")),MyE2_Label.STYLE_NORMAL_ITALLIC()), 2,new Insets(2,2,2,20));
			
			this.oGridBase.add(new MyE2_Label(new MyE2_String("Welcher Benutzer soll kopiert werden")), new Insets(2,2,2,20));
			this.oGridBase.add(this.oDDUser, new Insets(2,2,2,20));

			this.oGridBase.add(this.oCB_Menues,2, new Insets(2,2,2,20));
			this.oGridBase.add(this.oCB_Rechte,2, new Insets(2,2,2,20));
			
			this.oGridBase.add(new E2_ComponentGroupHorizontal(1,this.oButtonOK,this.oButtonCancel,new Insets(0,0,10,0)),new Insets(2,2,2,20));
			
		}
	}

	public MyString get_Check_CanBeShown() 
	{
		return this.cError;
	}

	private void setAktiv(boolean bAktiv)
	{
		try
		{
			this.oButtonOK.set_bEnabled_For_Edit(bAktiv);
			this.oCB_Menues.setSelected(bAktiv);
			this.oCB_Rechte.setSelected(bAktiv);
			this.oCB_Menues.set_bEnabled_For_Edit(bAktiv);
			this.oCB_Rechte.set_bEnabled_For_Edit(bAktiv);
		}
		catch (myException ex)
		{}
	}
	
	
	private class ActionSelectUser extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			USER__PopUpUserSettings_CONTAINER oThis = USER__PopUpUserSettings_CONTAINER.this;
			
			if (oThis.oDDUser.get_ActualWert().trim().equals("") || oThis.oDDUser.get_ActualWert().trim().equals(oThis.cSelectedUser))
				oThis.setAktiv(false);
			else
				oThis.setAktiv(true);
		}
	}
	
	
	private class ActionCloseWindow extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			USER__PopUpUserSettings_CONTAINER oThis = USER__PopUpUserSettings_CONTAINER.this;
			oThis.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			
		}
	}
	
	
	private class ActionCopyUserStructure extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			USER__PopUpUserSettings_CONTAINER oThis = USER__PopUpUserSettings_CONTAINER.this;
			
			String cZielUser = oThis.cSelectedUser;
			String cQuellUser = bibALL.ReplaceTeilString(oThis.oDDUser.get_ActualWert(),".","");
			
			String [][] cServlets = new String[0][0];
			String [][] cRechte = new String[0][0];
			
			if (oThis.oCB_Menues.isSelected())
				cServlets = bibDB.EinzelAbfrageInArray("SELECT ID_SERVLETS FROM "+bibE2.cTO()+".JD_ZUGRIFF WHERE ID_USER="+cQuellUser);
			
			if (oThis.oCB_Rechte.isSelected())
				cRechte = bibDB.EinzelAbfrageInArray("SELECT ID_BUTTON FROM "+bibE2.cTO()+".JD_BUTTON_USER WHERE ID_USER="+cQuellUser);
			
			if (cServlets==null || cRechte==null)
				throw new myException(this,"Error Querying userstatus !");
			
			//jetzt die schleife
			Vector<String> vSQL = new Vector<String>();
			int iCountServlets = 0;
			int iCountRechte = 0;
			
			for (int i=0;i<cServlets.length;i++)
			{
				if (bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JD_ZUGRIFF WHERE ID_SERVLETS="+cServlets[i][0]+" AND ID_USER="+cZielUser).equals("0"))
				{
					vSQL.add("INSERT INTO "+bibE2.cTO()+".JD_ZUGRIFF (ID_ZUGRIFF,ID_SERVLETS,ID_USER) VALUES(SEQ_ZUGRIFF.NEXTVAL,"+cServlets[i][0]+","+cZielUser+")");
					iCountServlets++;
				}
			}
			
			for (int i=0;i<cRechte.length;i++)
			{
				if (bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JD_BUTTON_USER WHERE ID_BUTTON="+cRechte[i][0]+" AND ID_USER="+cZielUser).equals("0"))
				{
					vSQL.add("INSERT INTO "+bibE2.cTO()+".JD_BUTTON_USER (ID_BUTTON_USER,ID_BUTTON,ID_USER) VALUES(SEQ_BUTTON_USER.NEXTVAL,"+cRechte[i][0]+","+cZielUser+")");
					iCountRechte++;
				}
			}
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			
			if (bibMSG.get_bIsOK())
			{
				MyE2_String oMessage = new MyE2_String("Hinzugefügt für den Benutzer: ");
				oMessage.addUnTranslated(oThis.oUser.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")));
				oMessage.addString(new MyE2_String("Menüeinträge: "));
				oMessage.addUnTranslated(""+iCountServlets);
				oMessage.addString(new MyE2_String("----    Rechte : "));
				oMessage.addUnTranslated(""+iCountRechte);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(oMessage), false);
			}
		}
	}

	
	
}
