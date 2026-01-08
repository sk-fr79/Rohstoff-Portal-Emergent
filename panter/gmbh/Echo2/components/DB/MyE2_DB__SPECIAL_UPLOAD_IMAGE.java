package panter.gmbh.Echo2.components.DB;

import java.io.File;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.filetransfer.UploadEvent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_PopUpWindow_for_Upload_to_Archiv;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MEDIENTYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.LoadImage;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



/**
 * besonderes Datenbankfeld, bekommt ein nich beschreibbares ID_<table> - feld uebergeben (zweites alias in der sqlfielmap=
 * und haengt ein bild mit einem speziellen programmkenner versehen in die archivmedien
 * @author martin
 *
 */
public class MyE2_DB__SPECIAL_UPLOAD_IMAGE extends MyE2_Grid implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	
    
    private String   				cMOTHER_TABLE = null;			  				// mothertable-name incl. JD_ oder JT_
    private String 				   	cPROGRAMM_KENNER = null;                     	// falls unterschiedliche maskenbilder auf eine maske hochgeladen werden sollen
    																				// muessen sie durch einen programmkenner unterschieden werden
    private String   				cID_MOTHER_TABLE = null;
	
	private ownButtonUploadImage    ownButtonUpload = 			new ownButtonUploadImage();
	private ownButtonDeleteImage    ownButtonDelete = 		 	new ownButtonDeleteImage();
	private MyE2_ContainerEx        oImageContainer = 			new MyE2_ContainerEx();

    
	//spezifische meldungen und beschriftungen
	private MyE2_String             cButtonTextUpload = new MyE2_String("Bild hochladen");
	private MyE2_String             cButtonTextDelete = new MyE2_String("Bild Löschen");
	private MyE2_String             cLabelText4NewMask = new MyE2_String("Ein Bild kann erst erfasst werden, wenn der Datensatz gespeichert wurde");
	
	private MyE2_String             cFehlermeldungBildIstDoppelt = new MyE2_String("Es existiert bereits ein Bild zu diesem Datensatz");
	
	private String                  cUeberschrift_4_Upload_Dialog = "Bild hochladen";
	
	private MyE2_String        		cWarnTextVorLoeschen = 				new MyE2_String("Sicher ? Wollen Sie dieses Bild löschen ?");
	private MyE2_String        		cButtonTextJa_Loeschen   = 			new MyE2_String("JA");
	private MyE2_String        		cButtonTextNein_NichtLoeschen   = 	new MyE2_String("Nein");
	
	private String     			    BUTTON_VALIDATOR_UPLOAD_IMAGE = "BILD_EINFUEGEN";
	private String     			    BUTTON_VALIDATOR_DELETE_IMAGE = "BILD_LOESCHEN";
	
	
	public MyE2_DB__SPECIAL_UPLOAD_IMAGE(SQLField osqlField, MutableStyle oStyle, String MOTHER_TABLE, String PROGRAMM_KENNER ) throws myException
	{
		super(1,oStyle);
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.cPROGRAMM_KENNER = 	PROGRAMM_KENNER;
		this.cMOTHER_TABLE = 		MOTHER_TABLE;
		
		if (this.cMOTHER_TABLE.toUpperCase().startsWith("JT_") || this.cMOTHER_TABLE.toUpperCase().startsWith("JD_"))
		{
			this.cMOTHER_TABLE = this.cMOTHER_TABLE.toUpperCase().substring(3);
		}
		
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);

		this.ownButtonUpload.set_Text(this.cButtonTextUpload);
		this.ownButtonDelete.set_Text(this.cButtonTextDelete);
		
		
	}

	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		this.removeAll();
		this.add(new MyE2_Label(this.cLabelText4NewMask));
		this.cID_MOTHER_TABLE = null;
	}

	@Override
	public String get_cActualMaskValue() throws myException
	{
		return null;
	}

	@Override
	public String get_cActualDBValueFormated() throws myException
	{
		return null;
	}

	@Override
	public void set_cActualMaskValue(String ID_MotherTable) throws myException
	{
		MyLong  oLong = new MyLong(ID_MotherTable);
		
		this.removeAll();
		
		if (oLong.get_cErrorCODE().equals(MyLong.ALL_OK))
		{
			this.cID_MOTHER_TABLE = oLong.get_cUF_LongString();
			this.baue_komponente_auf(this.ownButtonUpload, this.ownButtonDelete, this.oImageContainer);
			this.Fill_Image_Label();
		}
		
	}

	
	//methode, die ueberschrieben werden lann
	protected void baue_komponente_auf(MyE2_Button oButtonADDImage, MyE2_Button oButtonDeleteImage, MyE2_ContainerEx oImageContainer) throws myException
	{
		this.removeAll();
		this.add(new E2_ComponentGroupHorizontal(1, oButtonADDImage, oButtonDeleteImage, E2_INSETS.I_0_0_10_0), E2_INSETS.I_0_0_0_10);
		this.add(oImageContainer,E2_INSETS.I_0_0_0_0);
	}
	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String ID_of_Table, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_Button:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");
								
		this.set_cActualMaskValue(ID_of_Table);
		this.EXT_DB().set_cLASTActualDBValueFormated(ID_of_Table);

		
	}

	@Override
	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	@Override
	public boolean get_bIsComplexObject()
	{
		return false;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return new Vector<String>();
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return new Vector<String>();
	}

	@Override
	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}

	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component EXT_DB)
	{
		this.oEXTDB = EXT_DB;
	}
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		throw new myExceptionCopy("MyE2_DB__SPECIAL_UPLOAD_IMAGE: get_Copy() not implemented yet ...");
	}
	
	
	
	private RECLIST_ARCHIVMEDIEN  get_ActualRecList() throws myException
	{
		RECLIST_ARCHIVMEDIEN  recArchivmedien = new RECLIST_ARCHIVMEDIEN("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE "+
				RECORD_ARCHIVMEDIEN.FIELD__TABLENAME+"="+bibALL.MakeSql(this.cMOTHER_TABLE)+" AND "+
				RECORD_ARCHIVMEDIEN.FIELD__PROGRAMM_KENNER+(S.isEmpty(this.cPROGRAMM_KENNER)?" IS NULL":"="+bibALL.MakeSql(this.cPROGRAMM_KENNER))+" AND "+
				RECORD_ARCHIVMEDIEN.FIELD__ID_TABLE+"="+this.cID_MOTHER_TABLE);
		
		return recArchivmedien;
	}
	
	
	
	private class ownButtonUploadImage extends MyE2_Button
	{

		public ownButtonUploadImage()
		{
			super(new MyE2_String("Bild hochladen"));
			this.add_oActionAgent(new ownActionUpload());
			
			if (S.isFull(MyE2_DB__SPECIAL_UPLOAD_IMAGE.this.BUTTON_VALIDATOR_UPLOAD_IMAGE))
			{
				this.add_GlobalAUTHValidator_AUTO(MyE2_DB__SPECIAL_UPLOAD_IMAGE.this.BUTTON_VALIDATOR_UPLOAD_IMAGE);
			}
					
		}
		
		
		private class ownActionUpload extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_DB__SPECIAL_UPLOAD_IMAGE oThis = MyE2_DB__SPECIAL_UPLOAD_IMAGE.this;	
				
				
				//jetzt nachsehen, ob es bereits ein bild gibt, wenn ja, zuerst loeschen
				RECLIST_ARCHIVMEDIEN  recArchivmedien = oThis.get_ActualRecList();  
						
				if (recArchivmedien.get_vKeyValues().size()>0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oThis.cFehlermeldungBildIstDoppelt));
				}
				else
				{
					RECLIST_MEDIENTYP  recListTypen = new RECLIST_MEDIENTYP("NVL(IST_PIXELIMAGE,'N')='Y'","DATEIENDUNG");
					if (recListTypen.get_vKeyValues().size()==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Im System wurden noch keine passenden Medientypen definiert (als Pixelbild)!")));
					}
					else
					{
						ownPopupWindow_for_Upload oUploadWindow = 
							new ownPopupWindow_for_Upload(		oThis.cMOTHER_TABLE, 
																oThis.cID_MOTHER_TABLE, 
																true, 
																oThis.cUeberschrift_4_Upload_Dialog, 
																"",
																null, 
																bibALL.get_vBuildValueVectorFromHashmap(recListTypen.get_DATEIENDUNG_hmString_Formated("")),
																oThis.cPROGRAMM_KENNER);
						
						bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(oUploadWindow);
					}
				}
			}	
		}
	}
	
	
	
	
	private class ownPopupWindow_for_Upload extends E2_PopUpWindow_for_Upload_to_Archiv
	{

		public ownPopupWindow_for_Upload(	String 				tabelle, 
											String 				cid_tabelle,
											boolean 			overWrite, 
											String 				ueberschrift, 
											String 				infoText,
											E2_NavigationList 	oNavigation_FileList,
											Vector<String> 		v_erlaubte_endungen,
											String              Programm_kenner) throws myException
		{
			super(	tabelle, cid_tabelle, overWrite, ueberschrift, infoText,oNavigation_FileList, v_erlaubte_endungen,Programm_kenner);
		}

		
		public void do_actionWhenUpload_was_ok(UploadEvent arg0) throws myException
		{
			MyE2_DB__SPECIAL_UPLOAD_IMAGE.this.Fill_Image_Label();
		}

	}


	private void Fill_Image_Label() throws myException
	{
		
		this.oImageContainer.removeAll();
		
		//bei neueingabe kann kein bild vorhanden sein
		if (this.cID_MOTHER_TABLE !=null)
		{
			//jetzt nachsehen, ob es bereits ein bild gibt, wenn ja, zuerst loeschen
			RECLIST_ARCHIVMEDIEN  recArchivmedien = this.get_ActualRecList();  
			
			if (recArchivmedien.get_vKeyValues().size()>0)
			{
				File oFileToShow = Archiver.get_File(recArchivmedien.get(0));
				
				MyE2_Label oLabelHelp;
				try 
				{
					if (new File(oFileToShow.getAbsolutePath()).exists())
					{
						LoadImage  oLoad = new LoadImage(oFileToShow.getAbsolutePath());
						
						oLabelHelp = new MyE2_Label();
						oLabelHelp.setIcon(oLoad.get_ImageRef());
						this.oImageContainer.add(oLabelHelp);
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("Bild wurde nicht gefunden ..."));
					}
				} 
				catch (myException e) 
				{
					e.printStackTrace();
					bibMSG.add_MESSAGE(e);
				}
				
				
			}
		}
	}
	
	
	
	
	private class ownButtonDeleteImage extends MyE2_Button
	{

		public ownButtonDeleteImage()
		{
			super(new MyE2_String("Bild löschen"));
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					new ownMessageBox_4_delete();
				}
			});
			
			if (S.isFull(MyE2_DB__SPECIAL_UPLOAD_IMAGE.this.BUTTON_VALIDATOR_DELETE_IMAGE))
			{
				this.add_GlobalAUTHValidator_AUTO(MyE2_DB__SPECIAL_UPLOAD_IMAGE.this.BUTTON_VALIDATOR_DELETE_IMAGE);
			}
		}
		
		
		private class ownMessageBox_4_delete extends E2_MessageBoxYesNo
		{

			public ownMessageBox_4_delete() throws myException
			{
				super(	new MyE2_String("Sicherheitsabfrage:"), 
						MyE2_DB__SPECIAL_UPLOAD_IMAGE.this.cButtonTextJa_Loeschen, 
						MyE2_DB__SPECIAL_UPLOAD_IMAGE.this.cButtonTextNein_NichtLoeschen, true, true, 
						new MyE2_Label(MyE2_DB__SPECIAL_UPLOAD_IMAGE.this.cWarnTextVorLoeschen),
						new ownActionDelete(), new Extent(270), new Extent(160));
			}
			
		}
		
		private class ownActionDelete extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_DB__SPECIAL_UPLOAD_IMAGE oThis = MyE2_DB__SPECIAL_UPLOAD_IMAGE.this;	
				

				if (S.isEmpty(oThis.cID_MOTHER_TABLE))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung ! Bei Neuerfassung ist das nicht möglich !"));
				}
				else
				{
					//jetzt nachsehen, ob es bereits ein bild gibt, wenn ja, zuerst loeschen
					RECLIST_ARCHIVMEDIEN  recArchivmedien = oThis.get_ActualRecList();  
					
					if (recArchivmedien.get_vKeyValues().size()>0)
					{
						File oFileToDelete = Archiver.get_File(recArchivmedien.get(0));
						
						if (oFileToDelete.exists() && oFileToDelete.isFile())
						{
							if (oFileToDelete.delete())
							{
								//dann den record loeschen
								MyE2_MessageVector oMV= recArchivmedien.get(0).DELETE();
								
								if (oMV.get_bIsOK())
								{
									bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Sowohl der Datenbankeintrag als auch die zugehörige Datei wurden auf dem Server gelöscht !")));
									oThis.Fill_Image_Label();
								}
								else
								{
									bibMSG.add_MESSAGE(oMV);
								}
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Datei zu dem Eintrag konnte nicht gelöscht werden !")));
							}
						}
						else
						{
							//dann den record loeschen
							MyE2_MessageVector oMV= recArchivmedien.get(0).DELETE();
							bibMSG.add_MESSAGE(oMV);
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es konnte keine Datei unter dem Pfad: ",true,oFileToDelete.getAbsolutePath(),false," gefunden werden !",true)));
						}
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurd nichts zum Löschen gefunden!")));
					}
				}
			}
		}
	}


	public MyE2_String get_cButtonTextUpload()
	{
		return cButtonTextUpload;
	}



	public MyE2_String get_cButtonTextDelete()
	{
		return cButtonTextDelete;
	}



	public MyE2_String get_cLabelText4NewMask()
	{
		return cLabelText4NewMask;
	}



	public MyE2_String get_cFehlermeldungBildIstDoppelt()
	{
		return cFehlermeldungBildIstDoppelt;
	}



	public String get_cUeberschrift_4_Upload_Dialog()
	{
		return cUeberschrift_4_Upload_Dialog;
	}



	public MyE2_String get_cWarnTextVorLoeschen()
	{
		return cWarnTextVorLoeschen;
	}



	public MyE2_String get_cButtonTextJa_Loeschen()
	{
		return cButtonTextJa_Loeschen;
	}



	public MyE2_String get_cButtonTextNein_NichtLoeschen()
	{
		return cButtonTextNein_NichtLoeschen;
	}



	public String get_BUTTON_VALIDATOR_UPLOAD_IMAGE()
	{
		return BUTTON_VALIDATOR_UPLOAD_IMAGE;
	}



	public String get_BUTTON_VALIDATOR_DELETE_IMAGE()
	{
		return BUTTON_VALIDATOR_DELETE_IMAGE;
	}



	public void set__cButtonTextUpload(MyE2_String cButtonTextUpload)
	{
		this.cButtonTextUpload = cButtonTextUpload;
		this.ownButtonUpload.set_Text(this.cButtonTextUpload);

	}



	public void set__cButtonTextDelete(MyE2_String cButtonTextDelete)
	{
		this.cButtonTextDelete = cButtonTextDelete;
		this.ownButtonDelete.set_Text(this.cButtonTextDelete);
	}



	public void set__cLabelText4NewMask(MyE2_String cLabelText4NewMask)
	{
		this.cLabelText4NewMask = cLabelText4NewMask;
	}



	public void set__cFehlermeldungBildIstDoppelt(MyE2_String cFehlermeldungBildIstDoppelt)
	{
		this.cFehlermeldungBildIstDoppelt = cFehlermeldungBildIstDoppelt;
	}



	public void set__cUeberschrift_4_Upload_Dialog(String cUeberschrift_4_Upload_Dialog)
	{
		this.cUeberschrift_4_Upload_Dialog = cUeberschrift_4_Upload_Dialog;
	}



	public void set__cWarnTextVorLoeschen(MyE2_String cWarnTextVorLoeschen)
	{
		this.cWarnTextVorLoeschen = cWarnTextVorLoeschen;
	}



	public void set__cButtonTextJa_Loeschen(MyE2_String cButtonTextJa_Loeschen)
	{
		this.cButtonTextJa_Loeschen = cButtonTextJa_Loeschen;
	}



	public void set__cButtonTextNein_NichtLoeschen(MyE2_String cButtonTextNein_NichtLoeschen)
	{
		this.cButtonTextNein_NichtLoeschen = cButtonTextNein_NichtLoeschen;
	}



	public void set__BUTTON_VALIDATOR_UPLOAD_IMAGE(String bUTTON_VALIDATOR_UPLOAD_IMAGE)
	{
		BUTTON_VALIDATOR_UPLOAD_IMAGE = bUTTON_VALIDATOR_UPLOAD_IMAGE;
	}



	public void set__BUTTON_VALIDATOR_DELETE_IMAGE(String bUTTON_VALIDATOR_DELETE_IMAGE)
	{
		BUTTON_VALIDATOR_DELETE_IMAGE = bUTTON_VALIDATOR_DELETE_IMAGE;
	}

	
	
	
	
	
}
