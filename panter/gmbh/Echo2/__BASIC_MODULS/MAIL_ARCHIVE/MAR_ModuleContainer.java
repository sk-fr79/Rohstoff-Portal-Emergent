package panter.gmbh.Echo2.__BASIC_MODULS.MAIL_ARCHIVE;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


// liste zum bearbeiten (loeschen und downloaden von mailarchiven 
public class MAR_ModuleContainer extends Project_BasicModuleContainer 
{

	public static int 			NUMBER_OF_FILES_IN_ONE_SITE = 10;
	
	private MyE2_SelectField	oSelectFileTypes = 	null;			 		// hier kann selektiert werden, welche typen angezeigt werden
	private MyE2_SelectField  	oSelectUsers = null;
	
	private File[] 				AlleDateien = null;
	private Vector<String> 		vSelektorVarianten = new Vector<String>();
	private String[]			cSelektorVarianten = null;
	private MyE2_Column			oBasicColumn = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
	
	private String 				cActualType = "";
	private String    			cActualUser = "";
	
	private MyE2_CheckBox 		oCheckAufsteigendDatum = new MyE2_CheckBox(new MyE2_String("Datum aufsteigend sortieren"));
	
	
	
	public MAR_ModuleContainer()  throws myException
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_MAILARCHIV);

		
		// den selektor fuer die kuerzel:
		this.oSelectUsers = new MyE2_SelectField("SELECT   NVL(NAME,'-'),  NVL(KUERZEL,'-') FROM "+
												bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NAME IS NOT NULL AND KUERZEL IS NOT NULL ORDER BY NAME",false,true,false,false);		


		this.oSelectUsers.set_ActiveValue_OR_FirstValue("");
		this.oSelectUsers.add_oActionAgent(new ActionSelectUser());
		
		this.oCheckAufsteigendDatum.setSelected(true);
		this.oCheckAufsteigendDatum.add_oActionAgent(new ActionCheckSortType());
		
		
		this.leseDateienEin();
		this.leseSelektionsTypen();
		
		if (this.vSelektorVarianten.size()>0)
		{
			this.cSelektorVarianten = new String[this.vSelektorVarianten.size()+1];
			this.cSelektorVarianten[0]="-";
			for (int i=0;i<this.vSelektorVarianten.size();i++)
				this.cSelektorVarianten[i+1]=(String)this.vSelektorVarianten.get(i);
			
			this.oSelectFileTypes = new MyE2_SelectField(this.cSelektorVarianten,null,false);
			this.oSelectFileTypes.add_oActionAgent(new ActionSelectFileTyp());
		}
		
		this.BuildMask();
		
		this.add(this.oBasicColumn,E2_INSETS.I_10_2_2_2);
	}

	
	
	
	private void BuildMask() throws myException
	{
		this.oBasicColumn.removeAll();
		
		if (this.oSelectFileTypes != null)
		{
			this.oBasicColumn.add(		new E2_ComponentGroupHorizontal(0,
												new MyE2_Label(new MyE2_String("Typ:")),
												this.oSelectFileTypes,
												new MyE2_Label(new MyE2_String(" ")),
												new MyE2_Label(new MyE2_String("Benutzer:")),
												this.oSelectUsers,
												this.oCheckAufsteigendDatum,
												E2_INSETS.I_0_2_10_2),E2_INSETS.I_0_2_20_2);
		}

		
		MyE2_TabbedPane oTabbedFiles = new MyE2_TabbedPane(null);
		this.oBasicColumn.add(oTabbedFiles);
		
		
		MyE2_Grid	gridHelp = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		
		int iPages = 1;
		
		oTabbedFiles.add_Tabb(new MyE2_String(""+(iPages++)),gridHelp);
		
		int iCount = 0;
		
		for (int i=0;i<this.AlleDateien.length;i++)
		{
			// den namensfilter ueberpruefen
			if (! bibALL.isEmpty(this.cActualType))
				if(!this.AlleDateien[i].getName().toUpperCase().startsWith(this.cActualType.toUpperCase().trim()))
					continue;

			// den userfilter ueberpruefen
			if (! bibALL.isEmpty(this.cActualUser))
				if(this.AlleDateien[i].getName().toUpperCase().indexOf("_"+this.cActualUser.toUpperCase().trim()+"_")==-1)
					continue;
			
			
			gridHelp.add(new MyE2_Label(this.AlleDateien[i].getName(), MyE2_Label.STYLE_COURIER_SMALL()),E2_INSETS.I_10_2_10_2);
			
			MyE2_Button ButDown = new MyE2_Button(E2_ResourceIcon.get_RI("down.png"));
			ButDown.EXT().set_O_PLACE_FOR_EVERYTHING(this.AlleDateien[i]);
			ButDown.add_oActionAgent(new ActionDownload());
			ButDown.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO ("DOWNLOAD_ARCHIV"));
			gridHelp.add(ButDown,E2_INSETS.I_10_2_10_2);
			
			MyE2_Button ButDel = new MyE2_Button(E2_ResourceIcon.get_RI("delete.png"));
			ButDel.EXT().set_O_PLACE_FOR_EVERYTHING(this.AlleDateien[i]);
			ButDel.add_oActionAgent(new ActionDeleteSingleStep1());
			ButDel.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("DELETE_ARCHIV"));
			gridHelp.add(ButDel,E2_INSETS.I_10_2_10_2);

			iCount++;
			
			if (iCount>=MAR_ModuleContainer.NUMBER_OF_FILES_IN_ONE_SITE)
			{
				if (i<(this.AlleDateien.length-1))
				{
					gridHelp = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
					oTabbedFiles.add_Tabb(new MyE2_String(""+iPages++),gridHelp);
					iCount = 0;
				}
			}
			
		}
	}

	
	
	
	
	
	@SuppressWarnings("unchecked")
	private void leseDateienEin() throws myException
	{
		File 	oArchiv = new File(bibALL.get_CompleteProtokollPath(false));
		
		
		this.AlleDateien = oArchiv.listFiles(new FileFilter()
			{
				public boolean accept(File pathname)
				{
					boolean bRueck = false;

					if (pathname.getName().toUpperCase().endsWith("ZIP"))
					{
						bRueck = true;
					}
					return bRueck;
				}
			});
		if (this.oCheckAufsteigendDatum.isSelected())
			Arrays.sort(this.AlleDateien,new CompareTimeUp());
		else
			Arrays.sort(this.AlleDateien,new CompareTimeDown());
		
	}
	
	private class CompareTimeUp implements Comparator 
	{
	    public int compare(Object f1, Object f2) 
	    {
	    	if (f1 instanceof File && f2 instanceof File) 
	    	{
	    		Long l1 = new Long(((File) f1).lastModified());
	    		Long l2 = new Long(((File) f2).lastModified());
	    		return l1.compareTo(l2);
	    	}
	    	return 0;
	    }
	}
	

	private class CompareTimeDown implements Comparator 
	{
	    public int compare(Object f1, Object f2) 
	    {
	    	if (f1 instanceof File && f2 instanceof File) 
	    	{
	    		Long l1 = new Long(((File) f1).lastModified());
	    		Long l2 = new Long(((File) f2).lastModified());
	    		return l2.compareTo(l1);
	    	}
	    	return 0;
	    }
	}

	
	private void leseSelektionsTypen()
	{
		for (int i=0;i<this.AlleDateien.length;i++)
		{
			String cTestName = this.AlleDateien[i].getName();
			
			String cTestName2 = cTestName.substring(0,cTestName.indexOf("_"));   // name bis zum ersten "_"
			
			if (!this.vSelektorVarianten.contains(cTestName2))
				this.vSelektorVarianten.add(cTestName2);
		}
		
		
		
	}
	
	
	
	
	
	
	
	private class ActionDownload extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			MyE2_Button oBut = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			E2_Download oDownload = new E2_Download();
			String cName = ((File)oBut.EXT().get_O_PLACE_FOR_EVERYTHING()).getName();   // name ist zu lang fuer normale benutzer
			cName = bibALL.ReplaceTeilString(cName,"__","_");
			cName = bibALL.ReplaceTeilString(cName,"__","_");
			cName = bibALL.ReplaceTeilString(cName,"__","_");
			cName = bibALL.ReplaceTeilString(cName,"__","_");
			
			oDownload.starteFileDownload(((File)oBut.EXT().get_O_PLACE_FOR_EVERYTHING()).getAbsolutePath(),cName,"application/zip");
		}
		
	}
	
	
	// aufruf der sicherheitsabfrage
	private class ActionDeleteSingleStep1 extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer( new MyE2_String("Sicherheitsabfrage"),
																				new MyE2_String("Löschen ?"),
																				new MyE2_String(""),
																				new MyE2_String("JA"),
																				new MyE2_String("NEIN"),
																				new Extent(200),
																				new Extent(200));
			
			MyE2_Button oBut = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			File oDelFile =(File)oBut.EXT().get_O_PLACE_FOR_EVERYTHING();
			oConfirm.set_ActionAgentForOK(new ActionDeleteSingleStep2(oDelFile));
			
			oConfirm.show_POPUP_BOX();
			
		}
	}
	

	//ausloesen des Loeschvorgangs und neuaufbau der liste
	private class ActionDeleteSingleStep2 extends XX_ActionAgent
	{
		private File oFileToDelete = null;

		public ActionDeleteSingleStep2(File fileToDelete) 
		{
			super();
			oFileToDelete = fileToDelete;
			
		}
		
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			
			MyE2_String cMeldung = new MyE2_String(this.oFileToDelete.getName(),false);
			MyE2_String cMeldung2 = new MyE2_String(" wurde gelöscht !");
			
			cMeldung.addString(cMeldung2);
			
			this.oFileToDelete.delete();
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message(cMeldung.CTrans()));

			// neu einlesen
			try
			{
				MAR_ModuleContainer.this.leseDateienEin();
				MAR_ModuleContainer.this.BuildMask();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}

		}
		
	}

	
	
	
	private class ActionSelectFileTyp extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			MyE2_SelectField oSel = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			
			try
			{
				MAR_ModuleContainer.this.cActualType = oSel.get_ActualWert();
				if (MAR_ModuleContainer.this.cActualType.equals("-"))
					MAR_ModuleContainer.this.cActualType="";
				
				MAR_ModuleContainer.this.BuildMask();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
	}
	
	
	
	private class ActionSelectUser extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			MyE2_SelectField oSel = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			
			try
			{
				MAR_ModuleContainer.this.cActualUser = oSel.get_ActualWert();
				MAR_ModuleContainer.this.BuildMask();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}
	
	
	
	private class ActionCheckSortType extends XX_ActionAgent
	{
		@SuppressWarnings("unchecked")
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			if (MAR_ModuleContainer.this.oCheckAufsteigendDatum.isSelected())
				Arrays.sort(MAR_ModuleContainer.this.AlleDateien,new CompareTimeUp());
			else
				Arrays.sort(MAR_ModuleContainer.this.AlleDateien,new CompareTimeDown());

			
			MAR_ModuleContainer.this.BuildMask();
		}
	}

	
}
