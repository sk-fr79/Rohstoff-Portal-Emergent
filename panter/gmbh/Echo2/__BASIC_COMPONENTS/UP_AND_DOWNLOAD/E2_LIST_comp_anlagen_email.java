package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG.LA_Auswertung_ALLE_ATOME.ComponentMAP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;


public class E2_LIST_comp_anlagen_email extends MyE2_DB_PlaceHolder_NT {

	private E2_NavigationList  naviList = null;

	private String id_table="";

	public E2_LIST_comp_anlagen_email(E2_NavigationList  p_naviList) throws myException {
		super();

		this.naviList = p_naviList;
	}




	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {

		this.removeAll();
		this.setSize(2);
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER());

		id_table = oResultMAP.get_cUNFormatedROW_ID();

		RECLIST_ARCHIVMEDIEN recList_archivmedien = new RECLIST_ARCHIVMEDIEN(
				RECORD_ARCHIVMEDIEN.FIELD__TABLENAME + "='" + 
						new Archiver_Normalized_Tablename(naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE()).get_TableBaseName() + "' AND " +
						RECORD_ARCHIVMEDIEN.FIELD__ID_TABLE + "="+id_table
						, RECORD_ARCHIVMEDIEN.FIELD__ID_TABLE);

		int[] mailStatus = {0,0};

		for(RECORD_ARCHIVMEDIEN recArch: recList_archivmedien){

			RECORD_ARCHIVMEDIEN_ext recArchExt = new RECORD_ARCHIVMEDIEN_ext(recArch);
			mailStatus = recArchExt.getEmailStatus();
			break;

		}

		if(!recList_archivmedien.isEmpty()){
			this.add(new ownButtonAttachments(
					E2_ResourceIcon.get_RI("attach_mini_green.png"), 
					"Anlage zu diesem Datensatz",
					id_table, 
					Fibu_anlage_medientyp.OTHER));
		}else{
			
			this.add(new ownButtonAttachments(
					E2_ResourceIcon.get_RI("attach_mini.png"), 
					"Anlage zu diesem Datensatz",
					id_table, 
					Fibu_anlage_medientyp.OTHER));
			
//			MyE2_Label lbl = new MyE2_Label(E2_ResourceIcon.get_RI("attach_mini.png"));
//			lbl.setToolTipText("Keine Anlagen");
//			this.add(lbl);

			MyE2_Label lbl2 = new MyE2_Label(E2_ResourceIcon.get_RI("empty.png"));
			lbl2.setToolTipText("Keine Anlagen");
			this.add(lbl2);
		}

		String[] buttonInfos =  {"",""}; /*{ICON, TOOLTIPTEXT}*/

		if(mailStatus[0]>0){

			if(mailStatus[0] == mailStatus[1]){
				buttonInfos[0] = "email_done_.png";
				buttonInfos[1] = "Emails zu diesem Datensatz verhanden, alle versendet";
			} 
			else {
				buttonInfos[0] = "email_edit.png";
				buttonInfos[1] = "Emails zu diesem Datensatz verhanden, mindestens 1 Email nicht versendet";
			}


			this.add(new ownButtonAttachments(
					E2_ResourceIcon.get_RI(buttonInfos[0]), 
					buttonInfos[1],
					id_table, 
					Fibu_anlage_medientyp.MAIL));
		}
	}

	public enum Fibu_anlage_medientyp{
		MAIL,
		OTHER;
	}

	private class ownButtonAttachments extends MyE2_Button {

		private String id__fibu = null; 

		private Fibu_anlage_medientyp typ;

		public ownButtonAttachments(ImageReference oIcon, String sTooltip, String id_fibu_uf, Fibu_anlage_medientyp p_typ) {
			super(oIcon);
			this.id__fibu = id_fibu_uf;
			this.typ=p_typ;
			this.setToolTipText(sTooltip);
			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				E2_NavigationList nav = E2_LIST_comp_anlagen_email.this.naviList;

				String table_base = new Archiver_Normalized_Tablename(nav.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE()).get_TableBaseName();

				String modul_kenner = nav.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE();

				ownAM_BasicContainer popup = new ownAM_BasicContainer(	
						table_base, 
						ownButtonAttachments.this.id__fibu, 
						modul_kenner, 
						true);

				popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1300), new Extent(800), new MyE2_String("Anlagen und eMails"));

				if(typ==Fibu_anlage_medientyp.MAIL){
					popup.get_tabbedPane().setSelectedIndex(1);
				}

			}
		}

	}


	private class ownAM_BasicContainer extends AM_BasicContainer {

		public ownAM_BasicContainer(String ctable_name, String cid_table, String cMODULKENNER, boolean bUploadButton) throws myException {

			super(ctable_name, cid_table, cMODULKENNER, bUploadButton);

			this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this) {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					E2_LIST_comp_anlagen_email.this.naviList.Refresh_ComponentMAP(
							E2_LIST_comp_anlagen_email.this.id_table, 
							ComponentMAP.STATUS_VIEW);
				}
			});
		}



	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		E2_LIST_comp_anlagen_email anlagen_comp = null;
		try {

			anlagen_comp = new E2_LIST_comp_anlagen_email(this.naviList);
			anlagen_comp.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(anlagen_comp));
			anlagen_comp.setStyle(this.getStyle());

		} catch (myException e) {
			e.printStackTrace();
		}

		return anlagen_comp;
	}


	 public void set_vertical_orientation(){
		 this.setOrientation(ORIENTATION_VERTICAL);
	 }


	 
	 public void prepare_ContentForNew(boolean bdefault) {
	 }
	 
}
