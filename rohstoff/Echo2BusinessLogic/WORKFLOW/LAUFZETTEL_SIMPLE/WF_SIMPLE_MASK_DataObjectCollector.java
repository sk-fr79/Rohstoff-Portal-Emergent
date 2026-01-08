 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import java.util.GregorianCalendar;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Email_Handler;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS.REMINDER_Handler_Laufzettel_Eintrag;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_CB2;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_Handler;
 
 
 
public class WF_SIMPLE_MASK_DataObjectCollector extends RB_DataobjectsCollector_V21 {
 	
    private RB_TransportHashMap   	m_tpHashMap 		= null;
    
    // Blaupause und Vektor für mehrere Neueintragungen des Records
    private Rec21 					_rec_Master 		= null;
    private Vector<Rec21> 			_additionalRecs 	= new Vector<>();
    private String 					_idUserFirst 		= null;
    private boolean 				_abschluss4All		= false;
    
    
    public WF_SIMPLE_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        this.m_tpHashMap = p_tpHashMap;     
        
        this._setUseRec21(true);
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
        
        // reihenfolge wichtig fürs Speichern
        this.registerComponent(_TAB.laufzettel.rb_km(), 		new WF_SIMPLE_MASK_DataObject_LAUFZETTEL(this.m_tpHashMap));
        this.registerComponent(_TAB.laufzettel_eintrag.rb_km(), new WF_SIMPLE_MASK_DataObject(this.m_tpHashMap));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
   
    public WF_SIMPLE_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_LAUFZETTEL_EINTRAG, MASK_STATUS status) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
 
        this._setUseRec21(true);
 
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(status);
        
        Rec21 recLaufzettelEintrag 	= new Rec21(_TAB.laufzettel_eintrag)._fill_id(id_LAUFZETTEL_EINTRAG);
        this.registerComponent(_TAB.laufzettel_eintrag.rb_km(), new WF_SIMPLE_MASK_DataObject_LAUFZETTEL(recLaufzettelEintrag,status,this.m_tpHashMap));
        
        Rec21 recLaufzettel 		= new Rec21(_TAB.laufzettel)._fill_id(recLaufzettelEintrag.get_ufs_dbVal(LAUFZETTEL_EINTRAG.id_laufzettel));
        this.registerComponent(_TAB.laufzettel.rb_km(), new WF_SIMPLE_MASK_DataObject(recLaufzettel,status,this.m_tpHashMap));
        
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
    }
 
    @Override
	public void database_to_dataobject(Object startPoint) throws myException {
	}
  
	@Override
	public RB_DataobjectsCollector_V21 get_copy() throws myException {
		return null;
	}
  	
	
	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V21 do_collector, MyE2_MessageVector mv)	throws myException {
		
		RB_Dataobject doLZ = do_collector.get(WF_SIMPLE_CONST.getMaskKeyLaufzettel());
		RB_Dataobject doLZEintrag = do_collector.get(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag());
		Rec21 recLZEintrag = doLZEintrag.rec21();
		
		// alle abschliessen, wenn Schalter in der DB gesetzt 
		_abschluss4All = false;		
		Rec21 recLZ = doLZ.rec21();
		_abschluss4All = recLZ.get_ufs_dbVal(LAUFZETTEL.close_all,"N").equals("Y");
		
		
		if (recLZEintrag.is_newRecordSet()){
			// Daten des Laufzettel-Eintrags sind bei Neuanlage eine Blaupause, falls mehrere Benutzer angewählt sind...
			// d.h. der 1. Benutzer der Liste wird hier automatisch eingetragen, die nächsten sind eine Kopie des Eintrags mit 
			// geändertem Benutzer...
			recLZEintrag._setNewValueInDatabaseTerminus(LAUFZETTEL_EINTRAG.id_laufzettel, _TAB.laufzettel.seq_currval());
			
			WF_SIMPLE_MASK_ComponentMap comp_map = (WF_SIMPLE_MASK_ComponentMap)m_tpHashMap.getMaskComponentMapCollector().get(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag());
			WF_SIMPLE_UserLeftRight users = (WF_SIMPLE_UserLeftRight) comp_map.getComp(WF_SIMPLE_CONST.MASK_KEYS.USER_CROSSTABLE.key());
					
			
			if (users.get_v_cb_right().size() > 0){
				LR_CB2 cbFirst = users.get_v_cb_right().firstElement();
				WF_SIMPLE_UserLeftRightKapsel kapsel =(WF_SIMPLE_UserLeftRightKapsel)cbFirst.get_place_4_everything();
				_idUserFirst = kapsel.get_rec_user().get_key_value();
				recLZEintrag._setNewVal(LAUFZETTEL_EINTRAG.id_user_bearbeiter, Long.parseLong(_idUserFirst), bibMSG.MV());
			}
			
		} else {
			
			String abgeschlossen_am = recLZEintrag.get_fs_lastVal(LAUFZETTEL_EINTRAG.abgeschlossen_am, "");
			String abgeschlossen_am_old = recLZEintrag.get_fs_dbVal(LAUFZETTEL_EINTRAG.abgeschlossen_am, "");
			
//			_abschluss4All = ( !abgeschlossen_am.equals(abgeschlossen_am_old) && S.isEmpty(abgeschlossen_am_old)  );
		}
		
	}
		
	
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V21 do_collector,	MyE2_MessageVector mv) throws myException {

		MASK_STATUS status =  m_tpHashMap.getLastMaskLoadStatus();
		_additionalRecs.clear();

		RB_Dataobject doLZEintrag = do_collector.get(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag());
		
		if (status.equals(MASK_STATUS.NEW) || status.equals(MASK_STATUS.NEW_COPY) ) {
			Rec21 recLZEintrag = doLZEintrag.rec21().get_rec_after_save_new();
			
			if (recLZEintrag != null){
				
				WF_SIMPLE_MASK_ComponentMap comp_map = (WF_SIMPLE_MASK_ComponentMap)m_tpHashMap.getMaskComponentMapCollector().get(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag());
				WF_SIMPLE_UserLeftRight users = (WF_SIMPLE_UserLeftRight) comp_map.getComp(WF_SIMPLE_CONST.MASK_KEYS.USER_CROSSTABLE.key());
				
				// clone des eigentlichen Records generieren
				_rec_Master = new Rec21(recLZEintrag);
				
				// alle restlichen User eintragen
				if (users.get_v_cb_right().size() > 1){
					for (LR_CB2 cb : users.get_v_cb_right()){
						// jedem Clone den zugeordneten User einsetzen
						WF_SIMPLE_UserLeftRightKapsel kapsel =(WF_SIMPLE_UserLeftRightKapsel)cb.get_place_4_everything();
						String id_user = kapsel.get_rec_user().get_key_value();
						if (!id_user.equals(_idUserFirst)){
							
							Rec21 r = _rec_Master.getRecForCreateCopyStdExclude(bibMSG.MV());
							r._setNewVal(LAUFZETTEL_EINTRAG.id_user_bearbeiter, Long.parseLong(id_user), bibMSG.MV());
							_additionalRecs.addElement(r);
						}
					}
				}
				
			
			}
		} else if (status.equals(MASK_STATUS.EDIT) ){
			
			Rec21 recLZEintrag = doLZEintrag.rec21();
			
			
			if (_abschluss4All && !S.isEmpty(recLZEintrag.get_ufs_dbVal(LAUFZETTEL_EINTRAG.abgeschlossen_am, "")) ){
				
				// prüfen, ob alle Aufgaben abgeschlossen werden sollen...
				// debug..alle abschliessen
				RB_Dataobject doLZ = do_collector.get(WF_SIMPLE_CONST.getMaskKeyLaufzettel());

				RecList21 recList= doLZ.rec21().get_down_reclist21(LAUFZETTEL_EINTRAG.id_laufzettel);
				for (Rec21 rec : recList){
					if(rec.get_ufs_dbVal(LAUFZETTEL_EINTRAG.geloescht,"N").equals("N") ){
						
						if(S.isEmpty(rec.get_ufs_dbVal(LAUFZETTEL_EINTRAG.abgeschlossen_am, "")) ){
							String idStatusAbgeschlossen= (String)m_tpHashMap.getFromExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.ID_STATUS_ABGESCHLOSSEN);
							
							rec._setNewVal(LAUFZETTEL_EINTRAG.id_laufzettel_status, Long.parseLong(idStatusAbgeschlossen), bibMSG.MV());
							rec._setNewVal(LAUFZETTEL_EINTRAG.abgeschlossen_am, new GregorianCalendar().getTime() , bibMSG.MV());
							rec._setNewVal(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von,Long.parseLong( bibALL.get_ID_USER() ), bibMSG.MV());
							_additionalRecs.addElement(rec);
							
						}
					}
				}
				
			}
			
					
		}
		
		// alle zusätzlichen Records eintragen
		for (Rec21 r : _additionalRecs){
			r._SAVE(false, bibMSG.MV());
		}
		
	}
	
	/* 
	 * Nach dem Speichern muss noch geprüft werden, ob man eine nachricht schreiben muss....
	 * 
	 * (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21#executeFinalStatementsAfterCommitTransaction(panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21, panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	public void executeFinalStatementsAfterCommitTransaction(RB_DataobjectsCollector_V21 do_collector,
			MyE2_MessageVector mv) {
		
		try {		
			//
			// nach dem Speichern des Laufzetteleintrags noch prüfen, ob ein neuer erzeugt werden soll
			//
			RB_Dataobject doLZEintrag = do_collector.get(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag());
			Rec21 recLZEintrag;
			if (m_tpHashMap.getLastMaskLoadStatus().equals(MASK_STATUS.EDIT)){
				recLZEintrag = doLZEintrag.rec21();
			} else {
				recLZEintrag = doLZEintrag.rec21().get_rec_after_save_new();
			}
			handleFinalStatementsAfterCommit(recLZEintrag, mv);
			
			
			//
			// alle additional records auch noch bearbeiten
			//
			for (Rec21 rec : _additionalRecs){
				handleFinalStatementsAfterCommit(rec, mv);
			}
			
			
			// zum schluss nochmla prüfen, ob man den Laufzettel auch abschliessen muss
			RB_Dataobject recLZ = do_collector.get(WF_SIMPLE_CONST.getMaskKeyLaufzettel());
			
			this.checkCloseLaufzettel(recLZ.rec21(), mv);
		
		} catch (myException e) {
			mv._add(e.get_ErrorMessage());
		}
		
		
	}
	
	
	/**
	 * prüfen, ob neue WF-Einträge erzeugt werden müssen, und ob man den Laufzettel auch abschliessen kann ( wenn keine offfenen Einträge vorhanden sind)
	 * @author manfred
	 * @date 06.05.2019
	 *
	 * @param recLZE
	 * @param mv
	 */
	private void handleFinalStatementsAfterCommit(Rec21 recLZE, MyE2_MessageVector mv){
		try {		
			//
			// nach dem Speichern des Laufzetteleintrags noch prüfen, ob ein neuer erzeugt werden soll
			//
			
			String id_laufzettel = recLZE.getUfs(LAUFZETTEL_EINTRAG.id_laufzettel);
			String id_laufzettel_eintrag = recLZE.getUfs(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag);
			
			
			WF_Handler oHandler = new WF_Handler();
			oHandler.createNew_WFEntry_ForReminder(id_laufzettel, id_laufzettel_eintrag,true,true);
			oHandler.sendMessagesIfStatusChanged(id_laufzettel, id_laufzettel_eintrag);
		
			
			// Reminders prüfen...
			REMINDER_Handler_Laufzettel_Eintrag oReminder = new REMINDER_Handler_Laufzettel_Eintrag();
			oReminder.updateReminders();
			
			
		} catch (myException e) {
			mv._add(e.get_ErrorMessage());
		}
		
	}
	
	
	/***
	 * Zum schluss noch prüfen, ob der Laufzettel abgeschlossen werden muss
	 * @author manfred
	 * @date 06.05.2019
	 *
	 * @param recLZ
	 * @param mv
	 * @throws myException 
	 */
	private void checkCloseLaufzettel(Rec21 recLZ, MyE2_MessageVector mv) throws myException{
		// falls der Laufzettel noch nicht abgeschlossen ist, dann prüfen, ob er abgeschlossen werden muss.
		// dazu müssen alle Laufzetteleinträge abgeschlossen sein.
		RecList21 recList= recLZ.get_down_reclist21(LAUFZETTEL_EINTRAG.id_laufzettel,LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von.fn() + " IS NULL ","");
		if (recList.size() == 0){
			// wenn alle abgeschlossen sind, prüfen, ob der Laufzettel auch schon abgeschlossen war...
			if (!S.isFull(recLZ.get_ufs_dbVal(LAUFZETTEL.id_user_abgeschlossen_von, ""))){
				String idStatusAbgeschlossen= (String)m_tpHashMap.getFromExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.ID_STATUS_ABGESCHLOSSEN);
				
				recLZ._setNewVal(LAUFZETTEL.id_laufzettel_status, Long.parseLong(idStatusAbgeschlossen), bibMSG.MV());
				recLZ._setNewVal(LAUFZETTEL.abgeschlossen_am, new GregorianCalendar().getTime() , bibMSG.MV());
				recLZ._setNewVal(LAUFZETTEL.id_user_abgeschlossen_von,Long.parseLong( bibALL.get_ID_USER() ), bibMSG.MV());
				
				recLZ._SAVE(true, bibMSG.MV());
			} 
		} else {
			// wenn laufzetteleinträge bestehen, aber der Laufzettel ist abgeschlossen, dann muss der Laufzettel wieder geöffnet werden
			if (S.isFull(recLZ.get_ufs_dbVal(LAUFZETTEL.id_user_abgeschlossen_von, ""))){
				String idStatusDefault= (String)m_tpHashMap.getFromExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.DEFAULT_STATUS);
				
				recLZ._setNewVal(LAUFZETTEL.id_laufzettel_status, Long.parseLong(idStatusDefault), bibMSG.MV());
				recLZ._setNewVal(LAUFZETTEL.abgeschlossen_am, null, bibMSG.MV());
				recLZ._setNewVal(LAUFZETTEL.id_user_abgeschlossen_von,null , bibMSG.MV());
				
				recLZ._SAVE(true, bibMSG.MV());
			}
		}
	}
	
}
 
 
