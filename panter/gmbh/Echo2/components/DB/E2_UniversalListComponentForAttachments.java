/**
 * panter.gmbh.Echo2.basic_tools.emailv2
 * @author martin
 * @date 10.02.2021
 * 
 */
package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22Archivmedien;

/**
 * @author martin
 * @date 10.02.2021
 *
 */
public abstract class E2_UniversalListComponentForAttachments extends E2_UniversalListComponent {

	
	/**
	 * @return the showDownloadButton
	 */

	private boolean showDownloadButton = true;
	private boolean showDownFileName = true;
	private boolean showMedienkenner = true;
	

	

	public E2_UniversalListComponentForAttachments() {
		super();
	}

	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.E2_Grid#set_bEnabled_For_Edit(boolean)
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}



	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		removeAll();
		this._bo_no();
	}

	public abstract String getFieldNameOfIdArchivMedien();
	
	
	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		
		try {
			int size = 0;
			this._bo_no();
			
			if (showDownloadButton) {size++;}
			if (showDownFileName) {size++;}
			if (showMedienkenner) {size++;}
			
			if (size==0) {
				throw new myException("Error not columne !");
			}
			
			String id = resultMap.get_UnFormatedValue(this.getFieldNameOfIdArchivMedien());
			if (S.isFull(id)) {
				MyLong l_id = new MyLong(id);
				if (l_id.isOK()) {
					Rec22Archivmedien ram = new Rec22Archivmedien()._fill_id(l_id.get_lValue());
					this._s(size);

					if (showDownloadButton) {
						this._a(new BtDownload(ram), new RB_gld()._ins(5, 0, 5, 0));
						this._bo_dd();
					}
					if (showDownFileName) {
						this._a(new RB_lab()._t(ram.getUfs(ARCHIVMEDIEN.downloadname,ram.getUfs(ARCHIVMEDIEN.dateiname))), new RB_gld()._ins(5, 0, 5, 0));
						this._bo_dd();
					}
					
					if (showMedienkenner) {
						this._a(new RB_lab()._t(ram.getUfs(ARCHIVMEDIEN.medienkenner,"-")), new RB_gld()._ins(5, 0, 5, 0));
						this._bo_dd();
					}
				}
				
			}
			
		} catch (myException e) {
			this._clear()._s(1)._a("ERROR reading ID_ARCHIVMEDIEN");
			e.printStackTrace();
		}	
		
		
	}

	public boolean isShowDownloadButton() {
		return showDownloadButton;
	}


	public boolean isShowDownFileName() {
		return showDownFileName;
	}

	public boolean isShowMedienkenner() {
		return showMedienkenner;
	}
	
	public E2_UniversalListComponentForAttachments _setShowDownloadButton(boolean showDownloadButton) {
		this.showDownloadButton = showDownloadButton;
		return this;
	}

	public E2_UniversalListComponentForAttachments _setShowDownFileName(boolean showDownFileName) {
		this.showDownFileName = showDownFileName;
		return this;
	}

	public E2_UniversalListComponentForAttachments _setShowMedienkenner(boolean showMedienkenner) {
		this.showMedienkenner = showMedienkenner;
		return this;
	}

	
	private class BtDownload extends E2_Button {

		public BtDownload(Rec22Archivmedien recArchiv) {
			super();
			this._image(E2_ResourceIcon.get_RI("down.png"))._ttt("Die hinterlegte Archivdatei downloaden");
			
			this.add_oActionAgent(new ownActionAgentDownload(recArchiv));
			

		}
		
		private class ownActionAgentDownload extends XX_ActionAgent {
			private Rec22Archivmedien RecArchiv = null;
			
			public ownActionAgentDownload(Rec22Archivmedien recArchiv) {
				super();
				RecArchiv = recArchiv;
			}

			public void executeAgentCode(ExecINFO oExecInfo) {
				try {
					RecArchiv.starteDownLoad();
				} catch (myException ex) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("RGOM_LISTE_ANLAGEN:Download not possible !"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}

	}
	
	
	public abstract Object get_Copy(Object objHelp) throws myExceptionCopy;
	
	
}
