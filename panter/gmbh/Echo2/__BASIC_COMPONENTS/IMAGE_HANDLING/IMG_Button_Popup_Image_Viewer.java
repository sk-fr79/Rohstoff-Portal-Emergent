package panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING;

import java.io.File;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.exceptions.myException;

public class IMG_Button_Popup_Image_Viewer extends MyE2_Button {

	E2_NavigationList 	m_navList = null;
	String    			m_sID = null;
	String    			m_sTableName = null;
	
	public IMG_Button_Popup_Image_Viewer(E2_NavigationList oNavList) {
		super(E2_ResourceIcon.get_RI("dia_mini.png") , E2_ResourceIcon.get_RI("dia_mini__.png"));
		m_navList = oNavList;
		this.add_oActionAgent(new cActionAgent());
	}
	
	
	public IMG_Button_Popup_Image_Viewer(String sTableName, String sIDTable) {
		super(E2_ResourceIcon.get_RI("dia_mini.png") , E2_ResourceIcon.get_RI("dia_mini__.png"));
		m_sID = sIDTable;
		m_sTableName = sTableName;
		this.add_oActionAgent(new cActionAgent());
	}
	
	/**
	 * Setzt die größe der Icons
	 * @param bBig
	 */
	public void setIconSize(boolean bBig){
		if(bBig){
			this.__setImages(E2_ResourceIcon.get_RI("dia.png") , E2_ResourceIcon.get_RI("dia__.png"));
		} else {
			this.__setImages(E2_ResourceIcon.get_RI("dia_mini.png") , E2_ResourceIcon.get_RI("dia_mini__.png"));
		}
	}
	
	
	
	private class cActionAgent extends XX_ActionAgent{

		IMG_Button_Popup_Image_Viewer oThis = null;
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			oThis = IMG_Button_Popup_Image_Viewer.this;
			
			// übernehmen der aktuell gewählten ID
			String sID = null;
			String sTableName = null;
			Vector<String> vSelectedIDs  = null;

			if (oThis.m_navList != null){
				vSelectedIDs = oThis.m_navList.get_vSelectedIDs_Unformated();
				if (vSelectedIDs.size() == 1) {
					sID = vSelectedIDs.get(0);
					sTableName = oThis.m_navList.get_ComponentMAP(sID).get_oSQLFieldMAP().get_cMAIN_TABLE();
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss genau ein Datensatz ausgewählt sein"));
				}
			} else {
				if (oThis.m_sID != null && oThis.m_sTableName != null){
					sID = oThis.m_sID;
					sTableName = oThis.m_sTableName;
				}
			}
			
			if (bibMSG.get_bHasAlarms()){
				return;
			}
			
			String sBasepath = Archiver.get_ARCHIV_BASE_PATH(true, true);
			Vector<String> vBilder = new Vector<String>();
			
			// Tablename ohne JT_
			if(sTableName.toUpperCase().startsWith("JT_")){
				sTableName = sTableName.substring(3);
			}
			
			
			RECLIST_ARCHIVMEDIEN oRLArchivmedien = new RECLIST_ARCHIVMEDIEN(
							"Upper(" + RECORD_ARCHIVMEDIEN.FIELD__TABLENAME + ") = upper('" + sTableName +	"') AND " + 
							RECORD_ARCHIVMEDIEN.FIELD__ID_TABLE + " = " + sID + 
							" AND ID_MEDIENTYP IN ( SELECT ID_MEDIENTYP FROM " + bibE2.cTO() + ".JT_MEDIENTYP WHERE NVL(IST_PIXELIMAGE,'N') = 'Y' ) ", 
					RECORD_ARCHIVMEDIEN.FIELD__DATEINAME);
			
			if(oRLArchivmedien.size() > 0){
				// sortierte Ausgabe über get(i)
				for (int i=0; i<oRLArchivmedien.size(); i++){
					RECORD_ARCHIVMEDIEN rec = oRLArchivmedien.get(i);
					String sFile = sBasepath + rec.get_PFAD_cUF() + File.separator + rec.get_DATEINAME_cUF();
					vBilder.add(sFile);
				}
			}
			
			IMG_PopUp_For_Display oPopUp = new IMG_PopUp_For_Display(vBilder);
			oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Bilder anzeigen ..."));
		}

		
	}

}
