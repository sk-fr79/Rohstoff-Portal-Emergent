package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT_ZU_MODUL;
import panter.gmbh.basics4project.DB_ENUMS.MEDIENTYP;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.ZIP.ZIP_NG_Creator;
import panter.gmbh.indep.ZIP.ZIP_NG_NamePair;
import panter.gmbh.indep.dataTools.MyExportDataRows;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class HELP2_LIST_btExportToSql extends E2_Button {
	
	private E2_NavigationList  naviList = null;

	public HELP2_LIST_btExportToSql() throws myException {
		super();
		this._tr("Export")._standard_text_button()._aaa(new ActionExport())._ttt("Export der markierten Handbucheinträge ");
	}

	public HELP2_LIST_btExportToSql _setNaviList(E2_NavigationList naviList) {
		this.naviList = naviList;
		return this;
	}
	
	

	private class ActionExport extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			StringBuffer cSQL_TEXT = new StringBuffer();

			//zuerst nachsehen, ob eine checkbox (nur fuer developer-user) angekreuzt ist
			VEK<String>  v_positiv_ausgewaehlt = new VEK<String>();
			v_positiv_ausgewaehlt._a(naviList.get_vSelectedIDs_Unformated());
			
			VEK<String>  v_allesInSegment = new VEK<String>();
			v_allesInSegment._a(naviList.get_vectorSegmentation());
			
			VEK<String>  v_IdsToExport = new VEK<String>();

			
			//beschreibungsvector fuer das archiv aus anlagen
			Vector<ZIP_NG_NamePair> vNames = new Vector<ZIP_NG_NamePair>();
			
			int iCountHilfeBloecke = 0;
			int iCountArchivmedien = 0;
			
			if (naviList==null) {
				throw new myException(this,"Design-Error: no naviList is set !");
			}
			
			if (v_positiv_ausgewaehlt.size()==0) {
				if (v_allesInSegment.size()==0) {
					bibMSG.MV()._addAlarm("Es ist nichts zum Export ausgewählt / vorhanden ");
					return ;
				} else {
					bibMSG.MV()._addInfo(S.ms("Es wird alles in der monmentanen Filtereinstellung exportiert: ").ut(" ("+v_allesInSegment.size()+")"));
					v_IdsToExport._a(v_allesInSegment);
				}
			} else {
				bibMSG.MV()._addInfo(S.ms("Es werden die ausgewählten Einträge exportiert: ").ut(" ("+v_positiv_ausgewaehlt.size()+")"));
				v_IdsToExport._a(v_positiv_ausgewaehlt);
			}
			
			for (String s: v_IdsToExport) {
				Rec21 r = new Rec21(_TAB.hilfetext)._fill_id(s);
				
				iCountHilfeBloecke++;
				
				HashMap<String, String>  hmErsatzHt = new HashMap<String, String>();
				hmErsatzHt.put(HILFETEXT.id_hilfetext.fn(), _DB.HILFETEXT$$SEQ_NEXT);
				// im statement muessen die zwei user ID_USER_BEARBEITER und ID_USER_URSPRUNG via subselect abgefragt werden
				Rec21 		rUrsprung = r.get_up_Rec21(HILFETEXT.id_user_ursprung,USER.id_user,true);
				Rec21 		rBearbeiter = r.get_up_Rec21(HILFETEXT.id_user_bearbeiter,USER.id_user,true);
				
				if (Rec21.isFull(rUrsprung)) {
					String subsel1 = "(SELECT MAX(ID_USER) FROM JD_USER WHERE UPPER(NVL(VORNAME,''))='"+rUrsprung.getUfs(USER.vorname,"").toUpperCase()+"'"
																	+" AND  UPPER(NVL(NAME1,''))='"+rUrsprung.getUfs(USER.name1,"").toUpperCase()+"'"
																	+" AND  ID_MANDANT="+HELP2_CONST.ersatz4id_mandant4Export+")";		
					hmErsatzHt.put(HILFETEXT.id_user_ursprung.fn(), subsel1);
				}
				if  (Rec21.isFull(rBearbeiter)) {
					String subsel2 = "(SELECT MAX(ID_USER) FROM JD_USER WHERE UPPER(NVL(VORNAME,''))='"+rBearbeiter.getUfs(USER.vorname,"").toUpperCase()+"'"
																	+" AND  UPPER(NVL(NAME1,''))='"+rBearbeiter.getUfs(USER.name1,"").toUpperCase()+"'"
																	+" AND  ID_MANDANT="+HELP2_CONST.ersatz4id_mandant4Export+")";		
					hmErsatzHt.put(HILFETEXT.id_user_bearbeiter.fn(), subsel2);
				}
		
					
				cSQL_TEXT.append(new MyExportDataRows(r.getIdLong().toString(),_TAB.hilfetext.fullTableName(), HELP2_CONST.ersatz4id_mandant4Export, hmErsatzHt).get_cSQL_INSERT_STATEMENT()+";\n");
				
				
				//toechter
				
				//1: archivmedien
				RecList21 rlArchivMedien = new RecList21(_TAB.archivmedien)
							._fill(new SqlStringExtended(
									new SEL("*").FROM(_TAB.archivmedien).WHERE(new vglParam(ARCHIVMEDIEN.tablename)).AND(new vglParam(ARCHIVMEDIEN.id_table)).s()
														)._addParameters( 
																new VEK<ParamDataObject>()	._a(new Param_String("",_TAB.hilfetext.baseTableName()))
																							._a(new Param_Long(r.getLongDbValue(HILFETEXT.id_hilfetext))
																		)));
				
				
				//RECLIST_ARCHIVMEDIEN_ext rl_arch = new RECLIST_ARCHIVMEDIEN_ext(_DB.HILFETEXT, rec.get_ID_HILFETEXT_cUF(), null,null);
				HashMap<String, String>  hmErsatzAm = new HashMap<String, String>();
				hmErsatzAm.put(ARCHIVMEDIEN.id_archivmedien.fn(),		 _TAB.archivmedien.seq_nextval());
				hmErsatzAm.put(ARCHIVMEDIEN.id_table.fn(), 				_TAB.hilfetext.seq_currval());
				
				for (Rec21 ra: rlArchivMedien) {
					//medientyp aus dem anderen mandanten identifizieren
					Rec21 rmt = ra.get_up_Rec21(MEDIENTYP.id_medientyp);
//					RECORD_MEDIENTYP  recMedientyp = ra.get_UP_RECORD_MEDIENTYP_id_medientyp();
					if (Rec21.isFull(rmt)) {
						String subsel3 = "(SELECT MAX(ID_MEDIENTYP) FROM JT_MEDIENTYP "
												+ " WHERE UPPER(NVL(DATEIENDUNG,''))='"+rmt.get_ufs_dbVal(MEDIENTYP.dateiendung, "").toUpperCase()+"'"
												+" AND  ID_MANDANT="+HELP2_CONST.ersatz4id_mandant4Export+")";
						hmErsatzAm.put(ARCHIVMEDIEN.id_medientyp.fn(), subsel3);
					}
					
					cSQL_TEXT.append(new MyExportDataRows(ra.getIdLong().toString(), _TAB.archivmedien.fullTableName(), HELP2_CONST.ersatz4id_mandant4Export, hmErsatzAm).get_cSQL_INSERT_STATEMENT()+";\n");
					
					RECORD_ARCHIVMEDIEN_ext ra_ext = new RECORD_ARCHIVMEDIEN_ext(ra.getId());
					if (ra_ext.is_existing_in_filesystem()) {
						vNames.add(new ZIP_NG_NamePair(ra_ext.get_DATEINAME_cUF_NN(""), ra_ext.get__cCompletePathAndFileName()));
						iCountArchivmedien++;
					}
				}
				
				
				//2: verteiler fuer zusatzmodule
				RecList21 rl_zusatz= r.get_down_reclist21(HILFETEXT_ZU_MODUL.id_hilfetext);
				HashMap<String, String>  hmErsatzZHt = new HashMap<String, String>();
				hmErsatzZHt.put(HILFETEXT_ZU_MODUL.id_hilfetext_zu_modul.fn(),	 _TAB.hilfetext_zu_modul.seq_nextval());
				hmErsatzZHt.put(HILFETEXT_ZU_MODUL.id_hilfetext.fn(), 			  _TAB.hilfetext.seq_currval());
				
				for (Rec21 rz: rl_zusatz) {
					cSQL_TEXT.append(new MyExportDataRows(rz.getIdLong().toString(), _TAB.hilfetext_zu_modul.fullTableName(), HELP2_CONST.ersatz4id_mandant4Export, hmErsatzZHt).get_cSQL_INSERT_STATEMENT()+";\n");
				}
			
			}
			
			myTempFile  oTempFile = new myTempFile("exp_sql", ".txt", true);
			oTempFile.WriteTextBlock(cSQL_TEXT.toString(), myTempFile.CHARSET_ISO_8859_1);
			oTempFile.close();
			
			vNames.add(new ZIP_NG_NamePair("exp_sql.txt", oTempFile.getFileName()));
			
//			E2_Download  oDownload = new E2_Download();
//			oDownload.starteFileDownload(oTempFile.getFileName(),"sql-statement.txt", "application/text");

			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Anzahl Hilfetext exportiert: ",true,""+iCountHilfeBloecke,false," / Archivmediendateien: ",true,""+iCountArchivmedien,false)));
			
			//gleich noch die archivmedien-dateien dazupacken
			ZIP_NG_Creator  createZIP = new ZIP_NG_Creator(vNames, "archivdateien_fuer_hilfe.zip");
			createZIP.startDownload();
		}
		
	}
	
	
	
}
