package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.DownLoader;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class BS_BUTTON_DownloadDruckbelege extends MyE2_Button 
{

	public BS_BUTTON_DownloadDruckbelege() 
	{
		super(E2_ResourceIcon.get_RI("down.png"), true);
		this.add_GlobalAUTHValidator_AUTO("DOWNLOAD_PRINTARCHIV");
		this.add_oActionAgent(new ownActionAgent());
	}
	

	//spezieller button, wird immer aktiv, auch in anschau-maske
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(true);
		this.setIcon(this.get_oImgEnabled());
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		BS_BUTTON_DownloadDruckbelege oButton = new BS_BUTTON_DownloadDruckbelege();

		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		if (this.get_oText()!=null) oButton.set_Text(this.get_oText());
		oButton.__setImages(this.get_oImgEnabled(),this.get_oImgDisabled());
		try
		{
			oButton.set_bEnabled_For_Edit(this.isEnabled());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		
		oButton.setStyle(this.getStyle());
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oButton.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oButton.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oButton.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oButton.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));
		
		oButton.setFont(this.getFont());
		oButton.setAlignment(this.getAlignment());

		oButton.set_bMustSet_MILLISECONDSTAMP_TO_Session(this.get_bMustSet_MILLISECONDSTAMP_TO_Session());
		
		return oButton;
	}



	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			//zuerst nachsehen, um welche archiv-tabelle es sich handelt
			MyE2_Button 			oButt = ((MyE2_Button)oExecInfo.get_MyActionEvent().getSource());
			E2_ComponentMAP   		oMAP = oButt.EXT().get_oComponentMAP();
			
			//neueingabe abfangen (duerfte nie vorkommen)
			if (oMAP.get_oInternalSQLResultMAP()==null)
			{
				throw new myException(this,"Only allowed in existing Rows");
			}
			
			//kann eingesetzt werden fuer: JT_VKOPF_RG_DRUCK/JT_VKOPF_STD_DRUCK/JT_VKOPF_TPA_DRUCK/JT_VKOPF_KON_DRUCK/JT_VPOS_TPA_FUHRE_DRUCK
			String cTableREF_ARCH = (oMAP.get_oInternalSQLResultMAP().get_oSQLFieldMAP().get_cMAIN_TABLE()).substring(3);
			String cTableREF_ID   = oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			
//			RECLIST_ARCHIVMEDIEN  recArch = 
//					new RECLIST_ARCHIVMEDIEN("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE JT_ARCHIVMEDIEN.TABLENAME="+bibALL.MakeSql(cTableREF_ARCH)+" AND "+
//																	"JT_ARCHIVMEDIEN.ID_TABLE="+cTableREF_ID);
//			
			RECLIST_ARCHIVMEDIEN  recArch = new RECLIST_ARCHIVMEDIEN("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE "+
									" JT_ARCHIVMEDIEN.TABLENAME="+bibALL.MakeSql(cTableREF_ARCH)+" AND "+
									" JT_ARCHIVMEDIEN.ID_TABLE="+cTableREF_ID+" AND"+
									" NVL("+_DB.ARCHIVMEDIEN$IST_ORIGINAL+",'N')='N'");

			//jetzt 3 moeglichkeiten:  kein eintrag, genau ein eintrag oder mehrere
			if (recArch.get_vKeyValues().size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es existiert zu diesem Eintrag keine Archivdatei mit Dublettenmedien!"));
			}
			else if (recArch.get_vKeyValues().size()==1)       //download startet
			{
				new DownLoader(recArch.get(0));
			}
			else
			{
				Vector<MyE2_Button>  vDownloadButtons = new Vector<MyE2_Button>();
				
				Iterator<String> oIter = recArch.get_vKeyValues().iterator();
				
				while (oIter.hasNext())
				{
					vDownloadButtons.add(new ownButton(recArch.get(oIter.next())));
				}
				
				new ownPopUp(vDownloadButtons);
			}
			
			
		}
	}
	
	
	private class ownButton extends MyE2_Button
	{
		public ownButton(RECORD_ARCHIVMEDIEN  rec_Arch) throws myException 
		{
			super(E2_ResourceIcon.get_RI("down.png"), true);
			this.add_oActionAgent(new ownAction(rec_Arch));
			
			this.EXT().set_C_MERKMAL(rec_Arch.get_DOWNLOADNAME_cUF_NN(rec_Arch.get_DATEINAME_cUF()));
		}
		
		private class ownAction extends XX_ActionAgent
		{
			private RECORD_ARCHIVMEDIEN  recArch = null;
			
			public ownAction(RECORD_ARCHIVMEDIEN rec_Arch) 
			{
				super();
				this.recArch = rec_Arch;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				new DownLoader(this.recArch);
			}
		}
		
	}
	
	
	private class ownPopUp extends E2_BasicModuleContainer 
	{
		private Vector<MyE2_Button>  vDownloadButtons = null;

		public ownPopUp(Vector<MyE2_Button> v_DownloadButtons) throws myException 
		{
			super();
			this.vDownloadButtons = v_DownloadButtons;
			
			MyE2_Grid  oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			for (MyE2_Button  oButton: this.vDownloadButtons)
			{
				oGridHelp.add(new MyE2_Label(new MyE2_String(oButton.EXT().get_C_MERKMAL(),false)),E2_INSETS.I_5_2_5_2);
				oGridHelp.add(oButton,E2_INSETS.I_5_2_5_2);
			}
			
			this.add(oGridHelp, E2_INSETS.I_5_5_5_5);

			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(250), new MyE2_String("Download aus dem Archiv"));
			
		}
		
		
		
	}
	
	
	
}
