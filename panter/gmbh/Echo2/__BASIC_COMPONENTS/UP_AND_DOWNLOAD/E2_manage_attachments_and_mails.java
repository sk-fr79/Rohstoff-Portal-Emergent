package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public abstract class E2_manage_attachments_and_mails extends E2_Grid {

	public abstract String find_id_table_uf() throws myException;
	public abstract _TAB   find_tab() throws myException;
	public abstract String find_module_identifier() throws myException;
	public abstract VEK<XX_ActionAgentWhenCloseWindow> get_closeActionsForPopup() throws myException;
	
	private enum Fibu_anlage_medientyp{
		MAIL,
		OTHER;
	}

	
	/**
	 * 
	 */
	public E2_manage_attachments_and_mails() {
		super();
	}
	

	
	public E2_manage_attachments_and_mails  _fill() throws myException {
		this.removeAll();
		this.setSize(2);
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER());

		String id_table = this.find_id_table_uf();

		RECLIST_ARCHIVMEDIEN recList_archivmedien = new RECLIST_ARCHIVMEDIEN(
				RECORD_ARCHIVMEDIEN.FIELD__TABLENAME + "='" + 
						this.find_tab().baseTableName() + "' AND " +
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

		return this;
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

				E2_manage_attachments_and_mails oThis = E2_manage_attachments_and_mails.this;
				
				String table_base = oThis.find_tab().baseTableName();

				String modul_kenner = oThis.find_module_identifier();

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

			E2_manage_attachments_and_mails oThis = E2_manage_attachments_and_mails.this;
			VEK<XX_ActionAgentWhenCloseWindow> v = oThis.get_closeActionsForPopup();
			
			for (XX_ActionAgentWhenCloseWindow a: v) {
				this.add_CloseActions(a);
			}
		}

	}

	
}
