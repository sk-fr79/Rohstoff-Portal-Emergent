package panter.gmbh.Echo2.AgentsAndValidators;

import java.io.File;
import java.util.Collections;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.filetransfer.UploadEvent;
import nextapp.echo2.app.filetransfer.UploadListener;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_UploadSelect;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.ZIP.MyUnzipper;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;
import panterdt.Importer;

public class ActionAgent_Upload_And_Import_ExportFile extends XX_ActionAgent implements UploadListener
{

	private String 							cDirectoryToUpload = null;
	private File   							oDirectoryToUpload = null;
	private E2_UploadSelect  				oUploadSelect = null;
	private ContainerForUploadAndImport 	oUploadContainer = null;

	private MyE2_Column  					oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	
	private MyConnection     				oConn = null;
	
	// grid fuer die anzeige der zu importierenden dateien
	private static int 						iGridBreite = 4;
	private MyE2_Grid  						oGridForSelectImports = new MyE2_Grid(iGridBreite,0);
	
	private MyE2_Button						oButtonStartImport = new MyE2_Button(new MyE2_String("Starte Importvorgang"));
	private MyE2_Button						oButtonInvertSelektions = new MyE2_Button(new MyE2_String("Auswahl invertieren"));
	
	/*
	 * protokollierer :	ein StringBuffer
	 */
	private StringBuffer    oStringBufferInfo = new StringBuffer();

	
	
	public ActionAgent_Upload_And_Import_ExportFile(XX_ActionAgent   ActionAfterImportIsDone) 
	{
		super();
		this.oButtonInvertSelektions.add_oActionAgent(new actionInvertSelection());
		this.oButtonStartImport.add_oActionAgent(new actionMakeImport());
		
		if (ActionAfterImportIsDone!=null)
		{
			this.oButtonStartImport.add_oActionAgent(ActionAfterImportIsDone);
		}
		
	}

	
	public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	{
		this.oColBasic.removeAll();
		this.oGridForSelectImports.removeAll();
		
		//erzeugen eines temporaeren upload-UVZ
		this.cDirectoryToUpload = bibALL.get_TEMPPATH_COMPLETE_WITH_TIMESTAMP();
		this.oDirectoryToUpload = new File(this.cDirectoryToUpload);
		
		if (!this.oDirectoryToUpload .exists())
		{
			if (!this.oDirectoryToUpload.mkdir())
				throw new myException(this,"Error creating upload directory !");
		}
		else
		{
			throw new myException(this,"Error building upload directory !");
		}
		
		this.oUploadSelect = new E2_UploadSelect(this.cDirectoryToUpload,false);
		this.oUploadSelect.setUploadListener(this);
		this.oUploadContainer = new ContainerForUploadAndImport();
		this.oUploadContainer.add(this.oColBasic, E2_INSETS.I_2_2_2_2);
		this.oColBasic.add(new MyE2_Label(new MyE2_String("Bitte laden Sie das Export-File (ZIP-Format) hoch ...")));
		this.oColBasic.add(this.oUploadSelect, E2_INSETS.I_2_2_2_2);
		this.oUploadContainer.add(this.oGridForSelectImports, E2_INSETS.I_2_2_2_2);
		
		this.oUploadContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(800), new MyE2_String("Importfile einspielen"));
		
	}
	
	
	private void PackeAus(String cPath, String cFilename, MyE2_MessageVector oMV) throws myException
	{
		MyUnzipper oUZ = new MyUnzipper(cPath,cFilename);
		if (oUZ.get_bZipOK())
		{
			oMV.add_MESSAGE(new MyE2_Info_Message(""+oUZ.get_iNumberUnzipped()+" Files unzipped !"));
		}
	}
	
	
	/*
	 * container-klasse, die zuerst den upload engegennimmt und dann importiert (damit die lage fixiert werden kann)
	 */
	private class ContainerForUploadAndImport extends E2_BasicModuleContainer
	{
		public ContainerForUploadAndImport() throws myException
		{
			super();
			this.set_MODUL_IDENTIFIER(E2_CONSTANTS_AND_NAMES.NAME_MODUL_UPLOAD_AND_IMPORT_DATA);
			this.set_bVisible_Row_For_Messages(true);
		}
	}



	
	
	public void fileUpload(UploadEvent e) 
	{
		// nach dem upload wird der upload-selector aus der maske rausgeschmissen und die einzelnen files ausgepackt
		this.oColBasic.removeAll();

		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		try
		{
			
			this.oUploadSelect.doSaveFile(e, "DATADUMP", false);
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Upload OK !")));

			this.PackeAus(this.oUploadSelect.get_cUploadPfad(),this.oUploadSelect.get_cStoringFileNameWithEndung_NoPath(),oMV);
			
			this.oGridForSelectImports.removeAll();
			
			String cDirectory = this.oUploadSelect.get_cUploadPfad();
			File oDir = new File(cDirectory);
			
			// fuer jede ausgepackte datei wird nun eine checkbox erzeugt und als selektierbar angezeigt 
			String[] cFiles = oDir.list();
			Vector<String> vHelp = bibALL.get_VectorAusArray(cFiles);
			
			//vector mit dateinamen sortieren
			Collections.sort(vHelp);
			
			String   cOriginalZip = this.oUploadSelect.get_cStoringFileNameWithEndung_NoPath();
			E2_FontPlain  ofont = new E2_FontPlain(-2);
			
			for (int i=0;i<vHelp.size();i++)
			{
				String cHelp = (String)vHelp.get(i);
				if (!cHelp.equals(cOriginalZip) && !cHelp.toUpperCase().equals("EXPORT.LOG"))
				{
					MyE2_CheckBox oCB = new MyE2_CheckBox(cHelp);
					oCB.setSelected(true);
					oCB.setFont(ofont);
					oCB.EXT().set_C_MERKMAL(cHelp);
					this.oGridForSelectImports.add(oCB, E2_INSETS.I_0_0_0_0);
				}
			}
			this.oGridForSelectImports.NewLine();
			this.oGridForSelectImports.add(new E2_ComponentGroupHorizontal(0,this.oButtonStartImport,this.oButtonInvertSelektions,E2_INSETS.I_0_0_2_0), iGridBreite, E2_INSETS.I_2_2_2_2);
			
			
		}
		catch (myException ex)
		{
			oMV.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		
		bibMSG.add_MESSAGE(oMV);
		this.oUploadContainer.showActualMessages();
		
	}


	
	private class actionInvertSelection extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			ActionAgent_Upload_And_Import_ExportFile oThis = ActionAgent_Upload_And_Import_ExportFile.this;
			E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(oThis.oGridForSelectImports,
																					bibALL.get_Vector(MyE2_CheckBox.class.getName()),
																					null);

			Vector<Component> vRueck = oSearch.get_vAllComponents();
			for (int i=0;i<vRueck.size();i++)
			{
				((MyE2_CheckBox)vRueck.get(i)).setSelected(!((MyE2_CheckBox)vRueck.get(i)).isSelected());
			}
		}
	}
	
	
	private class actionMakeImport extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			ActionAgent_Upload_And_Import_ExportFile oThis = ActionAgent_Upload_And_Import_ExportFile.this;
			E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(oThis.oGridForSelectImports,
																					bibALL.get_Vector(MyE2_CheckBox.class.getName()),
																					null);

			String cPathWithImportFiles = oThis.oUploadSelect.get_cUploadPfad();
			if (!cPathWithImportFiles.endsWith(File.separator))
				cPathWithImportFiles+=File.separator;

			
			Vector<Component> 	vRueck = oSearch.get_vAllComponents();
			Vector<String> 		vListToImport = new Vector<String>(); 
			
			for (int i=0;i<vRueck.size();i++)
			{
				if (((MyE2_CheckBox)vRueck.get(i)).isSelected())
				{
					vListToImport.add(((MyE2_CheckBox)vRueck.get(i)).EXT().get_C_MERKMAL());
				}
			}

			String[] cImportList = new String[vListToImport.size()];
			for (int i=0;i<vListToImport.size();i++)
				cImportList[i]=(String)vListToImport.get(i);
			
			
			Importer oImp = new Importer(oThis.oConn,cPathWithImportFiles,cImportList);
			oImp.doImport();
			oThis.oStringBufferInfo.append(oImp.get_StringBufferProtokoll());
		}
	}
	
	
	
	
	
	public void invalidFileUpload(UploadEvent arg0) 
	{
		
	}
	
	
	public void set_oConnectionToDB_toExport(MyConnection connectionToDB_toImport) 
	{
		this.oConn = connectionToDB_toImport;
	}


	public StringBuffer get_oStringBufferInfo()
	{
		return oStringBufferInfo;
	}

	
}
