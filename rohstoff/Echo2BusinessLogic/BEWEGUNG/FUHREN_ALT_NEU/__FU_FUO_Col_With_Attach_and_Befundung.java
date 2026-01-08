package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.GLD;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.UP_DOWN_GenericDownloadCollector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT_INFO;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_BT_CONNECT_FUHRE_to_WK;

public class __FU_FUO_Col_With_Attach_and_Befundung extends MyE2_DB_PlaceHolder {

//	public enum typ {
//		JT_VPOS_TPA_FUHRE,
//		JT_VPOS_TPA_FUHRE_ORT
//	}
	
	private static GridLayoutData layoutInList = new GridLayoutData();
	{
		layoutInList.setInsets(E2_INSETS.I(0,0,0,0));
		layoutInList.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
	}
	
	private ENUM_BEWGUNGSATZ_TYP 	my_type = 			null; 
	private E2_NavigationList  		naviList = null;
	

	
	public __FU_FUO_Col_With_Attach_and_Befundung(SQLField  sqlField, ENUM_BEWGUNGSATZ_TYP mytype, E2_NavigationList navilist) 	throws myException {
		super(sqlField);
		this.my_type = mytype;
		this.naviList = navilist;
		this.EXT().set_oCompTitleInList(new MyE2_Label(E2_ResourceIcon.get_RI("attach_mini.png")));
		this.EXT().set_oLayout_ListElement(__FU_FUO_Col_With_Attach_and_Befundung.layoutInList);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String id_fuhre_oder_ort, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {

		this.removeAll();
		this.setSize(1);
		
		//hier nur "rechnen" wenn die spalte sichtbar ist
		if (this.naviList.get_oComponentMAP__REF().get__Comp(FU___CONST.FIELDNAME_ID_VPOS_TPA_FUHRE_3).EXT().get_bIsVisibleInList()) {
			MyLong l = new MyLong(id_fuhre_oder_ort);
			if (!l.get_bOK()) {
				throw new myException(this,"Error: no correct id!");
			}
			
			this.add(new ownAttachementButton(l.get_cUF_LongString()), E2_INSETS.I(0,0,0,0));
			this.add(new ownButtonShowBefundungsInfo(l.get_cUF_LongString()), E2_INSETS.I(0,0,0,0));
			this.add(new UP_DOWN_GenericDownloadCollector(	l.get_cUF_LongString(),
															this.my_type.toString(),
															new E2_ButtonAUTHValidator(
																	E2_MODULNAME_ENUM.MODUL.NAME_MODUL_FUHRENFUELLER.get_callKey(),
																	"ZUSATZDATEIEN_DOWNLOADEN")), E2_INSETS.I(0,0,0,0));
		}
	}

	
	
	private class ownAttachementButton extends E2_ButtonUpDown {
		public ownAttachementButton(String TableID) {
			super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_FUHRENFUELLER.get_callKey(), 
					__FU_FUO_Col_With_Attach_and_Befundung.this.my_type.name(), TableID, "Zusatzdateien und Anlagen "
					+(__FU_FUO_Col_With_Attach_and_Befundung.this.my_type==ENUM_BEWGUNGSATZ_TYP.JT_VPOS_TPA_FUHRE?" zur Fuhre ": " zum Fuhrenort ")+" hinzufügen oder scannen");
		}
	}
	
	
	private class ownButtonShowBefundungsInfo extends MyE2_Button {

		private ownRECLIST_BAM_IMPORT rlBamImport = null;

		public ownButtonShowBefundungsInfo(String id_fuhre_oder_ort) throws myException {
			super(E2_ResourceIcon.get_RI("empty10.png"),true);
			
			__FU_FUO_Col_With_Attach_and_Befundung oThis= __FU_FUO_Col_With_Attach_and_Befundung.this;

			if (oThis.my_type==ENUM_BEWGUNGSATZ_TYP.JT_VPOS_TPA_FUHRE) {
				this.rlBamImport = new ownRECLIST_BAM_IMPORT(id_fuhre_oder_ort, null);
			} else {
				this.rlBamImport = new ownRECLIST_BAM_IMPORT(null, id_fuhre_oder_ort);
			}

			this.setToolTipText(new MyE2_String("Befundungen der Fuhre anzeigen und als bearbeitet kennzeichnen !").CTrans());
			this.refresh_ButtonShowBefundungsInfo(false);
		}

		public void refresh_ButtonShowBefundungsInfo(boolean rebuildRecList) throws myException {
			if (rebuildRecList) {
				this.rlBamImport.REFRESH();
			}

			this.remove_AllActionAgents();
			
			this.setIcon(E2_ResourceIcon.get_RI("empty10.png"));
			
			if (rlBamImport.get_vKeyValues().size()>0) {
				if (rlBamImport.bAllDone()) {
					this.setIcon(E2_ResourceIcon.get_RI("abzuege_ok.png"));
					this.setToolTipText(new MyE2_String("Es bestehen Befundungen für diesen Vorgang, aber alle sind als bearbeitet markiert!").CTrans());
				} else {
					this.setIcon(E2_ResourceIcon.get_RI("abzuege.png"));
					this.setToolTipText(new MyE2_String("Es bestehen Befundungen für diesen Vorgang mit unbearbeiteten Positionen!").CTrans());
				}
				this.add_oActionAgent(new ownActionStartInfo());
			} else {
				this.setIcon(E2_ResourceIcon.get_RI("abzuege__.png"));
				this.setToolTipText(new MyE2_String("Es gibt für diesen Vorgang keine Befundungen!").CTrans());
			}

		}
		
		
		private class ownActionStartInfo extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
				new ownBasicModuleContainer();
			}
		}
		
		
		private class ownRECLIST_BAM_IMPORT extends RECLIST_BAM_IMPORT {
			
			public ownRECLIST_BAM_IMPORT(String id_vpos_tpa_fuhre_uf, String id_vpos_tpa_fuhre_ort_uf) throws myException 	{
				super(S.NN(id_vpos_tpa_fuhre_ort_uf,"0").trim().equals("0") ?
						"SELECT * FROM "+bibE2.cTO()+"."+_DB.BAM_IMPORT+" WHERE "+_DB.BAM_IMPORT$ID_VPOS_TPA_FUHRE+"="+id_vpos_tpa_fuhre_uf+" AND NVL("+_DB.BAM_IMPORT$ID_VPOS_TPA_FUHRE_ORT+",0)=0 "+ " ORDER BY "+_DB.BAM_IMPORT$ID_BAM_IMPORT:
						"SELECT * FROM "+bibE2.cTO()+"."+_DB.BAM_IMPORT+" WHERE "+_DB.BAM_IMPORT$ID_VPOS_TPA_FUHRE_ORT+"="+id_vpos_tpa_fuhre_ort_uf+" ORDER BY "+_DB.BAM_IMPORT$ID_BAM_IMPORT);
				
//				DEBUG.System_println(this.get_cQueryString(), DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
//				DEBUG.System_println("id_vpos_tpa_fuhre_uf: "+S.NN(id_vpos_tpa_fuhre_uf,"<FU>")+" id_vpos_tpa_fuhre_ort_uf"+S.NN(id_vpos_tpa_fuhre_ort_uf, "<FO>"), DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
				
				
			}
			
			public boolean bAllDone() throws myException {
				boolean bDone = true;
				for (RECORD_BAM_IMPORT recIMP: this) {
					bDone = bDone && recIMP.is_ABGESCHLOSSEN_YES();
				}
				return bDone;
			}
			
			
			public MyE2_Grid get_InfoGrid() throws myException {
				MyE2_Grid  gridRueck = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				
				//zuerst den titel
				gridRueck.add(new MyE2_Label(new MyE2_String("ID")), 			GLD.L(new E2_ColorDDDark(),1,Alignment.LEFT));
				gridRueck.add(new MyE2_Label(new MyE2_String("Datum/Uhrzeit")), GLD.L(new E2_ColorDDDark(),1,Alignment.LEFT));
				gridRueck.add(new MyE2_Label(new MyE2_String("Belegnummer")), 	GLD.L(new E2_ColorDDDark(),1,Alignment.LEFT));
				gridRueck.add(new MyE2_Label(""), 								GLD.L(new E2_ColorDDDark(),1,Alignment.LEFT));
				gridRueck.add(new MyE2_Label(new MyE2_String("OK")), 			GLD.L(new E2_ColorDDDark(),1,Alignment.CENTER));
				
				for (String id:  this.get_vKeyValues()) {
					RECORD_BAM_IMPORT bi = this.get(id);
					gridRueck.add(new MyE2_Label(bi.get_ID_BAM_IMPORT_cF_NN("-")), 		GLD.L(new E2_ColorDark(), 1, Alignment.LEFT));
					gridRueck.add(new MyE2_Label(bi.get_BAM_ANGELEGT_AM_cUF_NN("")), 	GLD.L(new E2_ColorDark(), 1, Alignment.LEFT));
					gridRueck.add(new MyE2_Label(bi.get_BELEGNUMMER_cUF_NN("")), 		GLD.L(new E2_ColorDark(), 1, Alignment.LEFT));
					gridRueck.add(new MyE2_Label(""), 									GLD.L(new E2_ColorDark(), 1,Alignment.LEFT));
					gridRueck.add(new button_toggle_BAM_IMPORT_STATUS(bi), 				GLD.L(new E2_ColorDark(), 1, Alignment.CENTER));
					
					for (String iid: bi.get_DOWN_RECORD_LIST_BAM_IMPORT_INFO_id_bam_import().get_vKeyValues()) {
						RECORD_BAM_IMPORT_INFO ii = bi.get_DOWN_RECORD_LIST_BAM_IMPORT_INFO_id_bam_import().get(iid);
						gridRueck.add(new MyE2_Label(new MyE2_String("")), 				GLD.L(new E2_ColorBase(), 1, Alignment.LEFT));   //einrueckung
						gridRueck.add(new MyE2_Label(ii.get_GEWICHT_cF_NN("0")+" kg"), 	GLD.L(new E2_ColorBase(), 1, Alignment.LEFT));
						gridRueck.add(new MyE2_Label(ii.get_TEXT_cUF_NN("")), 			GLD.L(new E2_ColorBase(), 1, Alignment.LEFT));
						gridRueck.add(new MyE2_Label(ii.get_ZUSATZINFOS_cUF_NN("")), 	GLD.L(new E2_ColorBase(), 1, Alignment.LEFT));	
						gridRueck.add(new MyE2_Label(""), 								GLD.L(new E2_ColorBase(), 1,Alignment.LEFT));
					}
				}
				return gridRueck;
			}
		}
		
		private class button_toggle_BAM_IMPORT_STATUS extends MyE2_Button {
			private RECORD_BAM_IMPORT recBI = null;

			public button_toggle_BAM_IMPORT_STATUS(RECORD_BAM_IMPORT rec_bi) throws myException {
				super();
				this.recBI = rec_bi;
				this.add_oActionAgent(new ownSwitcher());
				this.refresh(false);
				this.setStyle(MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
			}
			
			private void refresh(boolean dbRebuild) throws myException {
				if (dbRebuild) {
					this.recBI.set_cSQL_4_Build("SELECT * FROM "+bibE2.cTO()+"."+_DB.BAM_IMPORT+" WHERE "+_DB.BAM_IMPORT$ID_BAM_IMPORT+"="+this.recBI.get_ID_BAM_IMPORT_cUF());
					this.recBI.REBUILD();
				}
				this.setIcon(E2_ResourceIcon.get_RI(this.recBI.is_ABGESCHLOSSEN_YES()?"ok.png":"ok_red.png"));
				this.setToolTipText(new MyE2_String(this.recBI.is_ABGESCHLOSSEN_YES()?"Status der Befundung auf <OFFEN> setzen":"Status der Befundung auf <GESCHLOSSEN> setzen").CTrans());
			}

			private class ownSwitcher extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					button_toggle_BAM_IMPORT_STATUS.this.recBI.set_NEW_VALUE_ABGESCHLOSSEN(button_toggle_BAM_IMPORT_STATUS.this.recBI.is_ABGESCHLOSSEN_YES()?"N":"Y");
					bibMSG.add_MESSAGE(button_toggle_BAM_IMPORT_STATUS.this.recBI.UPDATE(true));
					button_toggle_BAM_IMPORT_STATUS.this.refresh(true);
					if (button_toggle_BAM_IMPORT_STATUS.this.recBI.is_ABGESCHLOSSEN_YES()) {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Status wurde von <Offen> auf <Geschlossen> gesetzt! ")));
					} else {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Status wurde von <Geschlossen> auf <Offen> gesetzt! ")));
					}
				}
			}
		}

		
		
		private class ownBasicModuleContainer extends E2_BasicModuleContainer {

			public ownBasicModuleContainer() throws myException {
				super();
				
				MyE2_Grid  grid = ownButtonShowBefundungsInfo.this.rlBamImport.get_InfoGrid();
				
				MyE2_Button bOK = new MyE2_Button(new MyE2_String("OK"),null,new ownActionClose());
				bOK.setWidth(new Extent(80));
				grid.add(bOK,MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(1,10,1,1), new E2_ColorBase(), grid.getSize()));
				
				this.add(grid, E2_INSETS.I(2,2,2,2));
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(300), new MyE2_String("Befundungen"));
				
				this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(ownBasicModuleContainer.this){
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						ownButtonShowBefundungsInfo.this.refresh_ButtonShowBefundungsInfo(true);
					}
				});
				
			}
			
			private class ownActionClose extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
					ownBasicModuleContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			}
			
		}
		
		
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try {
			return new __FU_FUO_Col_With_Attach_and_Befundung(this.EXT_DB().get_oSQLField(),this.my_type, this.naviList);
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	
	
//	//2015-10-09: neuer button zum download aller upload-files
//	private class ownButtonDownload extends MyE2_Button {
//
//		private String id_fuhre_oder_fuhrenort = null;
//		
//		public ownButtonDownload(String p_id_fuhre_oder_fuhrenort) {
//			super(E2_ResourceIcon.get_RI("down_mini.png"));
//			
//			this.id_fuhre_oder_fuhrenort = p_id_fuhre_oder_fuhrenort;
//			
//			this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_FUHRENFUELLER.get_callKey(),"ZUSATZDATEIEN_DOWNLOADEN"));
//			
//			this.setToolTipText(new MyE2_String("Downloaden von allen Anlagen vom Typ pdf oder Bild (pixelbasierte)").CTrans());
//			
//			this.add_oActionAgent(new ownActionAgentDownload());
//		}
//		
//		
//		private class ownActionAgentDownload extends XX_ActionAgent {
//
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				String table = __FU_FUO_Col_With_Attach_and_Befundung.this.my_type.name();
//				String id    = ownButtonDownload.this.id_fuhre_oder_fuhrenort;
//				
//				//alle archivmedien, die an dem jeweiligen datensatz haengen
//				RECLIST_ARCHIVMEDIEN_ext  ra_ext = new RECLIST_ARCHIVMEDIEN_ext(table, id, null,null);
//				
//				
//				//zuerst zaehlen was da ist
//				int i=0;
//				for (RECORD_ARCHIVMEDIEN ra: ra_ext) {
//					RECORD_ARCHIVMEDIEN_ext rae = new RECORD_ARCHIVMEDIEN_ext(ra);
//					
//					if (rae.is_pixel_image() || rae.is_PDF()) {
//						i++;
//					}
//				}
//				
//				if (i==0) {
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt keine PDF oder Pixel-Anlagen !")));
//				} else {
//					ArchiverConcatenated_PDF_and_PIXELFiles concatenater = new ArchiverConcatenated_PDF_and_PIXELFiles();
//					ArrayList<RECORD_ARCHIVMEDIEN> v_ra = new ArrayList<RECORD_ARCHIVMEDIEN>();
//					v_ra.addAll(ra_ext.values());
//					
//					String baseName = table.substring(3)+"_"+id;
//					
//					MyE2_MessageVector  mv = new MyE2_MessageVector();
//					
//					myTempFile tf_alles_zusammen = concatenater.generate_ConcatenatedFile(v_ra,  mv, baseName);
//					
//					if (mv.get_bIsOK()) {
//						tf_alles_zusammen.starteDownLoad(baseName, JasperFileDef.MIMETYP_PDF);
//					}
//				}
//				
//				
//				
//			}
//			
//		}
//		
//		
//	}
	
	
	
}
