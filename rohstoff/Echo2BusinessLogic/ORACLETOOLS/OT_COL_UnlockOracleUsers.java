package rohstoff.Echo2BusinessLogic.ORACLETOOLS;


import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public class OT_COL_UnlockOracleUsers extends MyE2_Column
{
	private 	MyE2_Button 			oButtonStart = new MyE2_Button(new MyE2_String("Speichern ...."));
	private 	OT_BasicContainer 		oBasicContainer = null;
	private 	MyDBToolBox 			oDBORA = null; 
	
	private     MyE2_Grid			    oGridUserList = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
	
	private     Vector<MyE2_CheckBox>   vCB_Sammler = new Vector<MyE2_CheckBox>();
	private     Vector<String>			vSQL_Sammler = new Vector<String>();
	
	private     String    				cORA_TABLE_OWNER = bibALL.get_oracle_tableowner().toUpperCase();
	
	
	public OT_COL_UnlockOracleUsers(OT_BasicContainer BasicContainer) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		this.oBasicContainer = 	BasicContainer;
		this.oDBORA = 			this.oBasicContainer.get_oDBORA();
		
		this.add(
				new MyE2_Label(new MyE2_String("Oracle-Benutzer SPERR/ENTSPERR-Vorichtung"),
							   MyE2_Label.STYLE_TITEL_BIG()),
								E2_INSETS.I_10_10_10_10);
		
		this.oButtonStart.add_oActionAgent(new actionAgentStart());
		this.oButtonStart.add_GlobalValidator(new E2_ButtonAUTHValidator(this.oBasicContainer.get_MODUL_IDENTIFIER(),"LOCK_UNLOCK_ORACLE_USERS"));
		
		this.add(this.oGridUserList,E2_INSETS.I_10_10_10_10);
		this.add(new E2_ComponentGroupHorizontal(0,this.oButtonStart,E2_INSETS.I_0_2_5_2),E2_INSETS.I_10_10_10_10);
		
		this.RebuildList();
	}

	
	private void RebuildList()
	{
		this.oGridUserList.removeAll();
		this.vCB_Sammler.removeAllElements();
		
		String cQuery = "SELECT RECNUM,KUERZEL,NAME,AKTIV FROM \""+this.cORA_TABLE_OWNER + "\".\"USER\" ORDER BY NAME";
		String[][] cUSER = this.oDBORA.EinzelAbfrageInArray(cQuery,"");
		if (cUSER == null || cUSER.length==0)
		{
			this.oGridUserList.add(new MyE2_Label("@@@ERROR@@@  Querying Userlist !!"),4,E2_INSETS.I_10_2_2_10);
			this.oGridUserList.add(new MyE2_Label(cQuery),4,E2_INSETS.I_10_2_2_10);
		}
		else
		{
			this.oGridUserList.add(new MyE2_Label(new MyE2_String("RECNUM")),E2_INSETS.I_2_2_2_2);
			this.oGridUserList.add(new MyE2_Label(new MyE2_String("KUERZEL")),E2_INSETS.I_2_2_2_2);
			this.oGridUserList.add(new MyE2_Label(new MyE2_String("NAME")),E2_INSETS.I_2_2_2_2);
			this.oGridUserList.add(new MyE2_Label(new MyE2_String("AKTIV")),E2_INSETS.I_2_2_2_2);
			
			for (int i=0; i<cUSER.length;i++)
			{
				this.oGridUserList.add(new MyE2_Label(cUSER[i][0]),E2_INSETS.I_2_2_2_2);
				this.oGridUserList.add(new MyE2_Label(cUSER[i][1]),E2_INSETS.I_2_2_2_2);
				this.oGridUserList.add(new MyE2_Label(cUSER[i][2]),E2_INSETS.I_2_2_2_2);
				
				MyE2_CheckBox oCB = new MyE2_CheckBox();
				oCB.EXT().set_C_MERKMAL(cUSER[i][0]);
			
				if (cUSER[i][3].trim().equals(""))
				{
					oCB.EXT().set_C_MERKMAL2("N");
					oCB.setSelected(false);
				}
				else
				{
					oCB.EXT().set_C_MERKMAL2("Y");
					oCB.setSelected(true);
				}
				this.oGridUserList.add(oCB,E2_INSETS.I_2_2_2_2);
				this.vCB_Sammler.add(oCB);
			}
			
		}
		
	}
	
	
	
	private class actionAgentStart extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			OT_COL_UnlockOracleUsers oThis = OT_COL_UnlockOracleUsers.this;

			// wieviele haben sich geaendert
			oThis.vSQL_Sammler.removeAllElements();

			for (int i=0;i<oThis.vCB_Sammler.size();i++)
			{
				MyE2_CheckBox oCB = (MyE2_CheckBox)oThis.vCB_Sammler.get(i);
				if (oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("N"))
				{
					oThis.vSQL_Sammler.add("UPDATE \""+oThis.cORA_TABLE_OWNER + "\".\"USER\" SET AKTIV='*' WHERE RECNUM="+oCB.EXT().get_C_MERKMAL());
				}
				else if (!oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("Y"))
				{
					oThis.vSQL_Sammler.add("UPDATE \""+oThis.cORA_TABLE_OWNER + "\".\"USER\" SET AKTIV='' WHERE RECNUM="+oCB.EXT().get_C_MERKMAL());
				}
			}
			
			if (oThis.vSQL_Sammler.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Es ist keine Änderung gemacht worden !"));
				return;
			}
			
			E2_ConfirmBasicModuleContainer oConf = new E2_ConfirmBasicModuleContainer(
															new MyE2_String("Sicherheitsabfrage :"), 
															new MyE2_String("Benutzersperren speichern ?"),
															new MyE2_String(""),
															new MyE2_String("JA, Speichern !"),
															new MyE2_String("NEIN - Abbruch !"),
															new Extent(350),new Extent(200));
				
				
			oConf.set_ActionAgentForOK(new actionAgentOK());
			oConf.show_POPUP_BOX();
		}
	}
	
	private class actionAgentOK extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			OT_COL_UnlockOracleUsers oThis = OT_COL_UnlockOracleUsers.this;
			
			bibMSG.add_MESSAGE(oThis.oDBORA.ExecMultiSQLVector(oThis.vSQL_Sammler,true));
			if (bibMSG.get_bIsOK())
			{
				MyE2_String oInfo = new MyE2_String("Erledigt:");
				oInfo.addUnTranslated("  "+oThis.vSQL_Sammler.size()+" "  );
				oInfo.addString(new MyE2_String("geänderte Einträge gespeichert !"));
				bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(oInfo.CTrans()));
				oThis.RebuildList();
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Speichern !"));
			}
		}
	}
	

	
	
	
	
}
