 
package rohstoff.businesslogic.bewegung2.mask;
  
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_PRUEFPROT;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.global.EnJoins4Querys;
import rohstoff.businesslogic.bewegung2.global.EnJoins4Querys.NamesOfFieldsToRead;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;
 
 
 
public class B2_MaskDataObjectCollector extends RB_DataobjectsCollector_V21 {
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    private boolean               newRecords = false;
    
    public B2_MaskDataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        this.m_tpHashMap = p_tpHashMap;     
        
        this._setUseRec21(true);
        
        this.newRecords=true;
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
        
        //WICHTIG! bei neu erfassten ist diese Reihenfolge essentiell, damit die verbindung der Records passt
        this.registerComponent(RecS1.key, 	new RecS1(MASK_STATUS.NEW)._setTransportHashMap(this.m_tpHashMap));
        this.registerComponent(RecS2.key, 	new RecS2(MASK_STATUS.NEW)._setTransportHashMap(this.m_tpHashMap));
        this.registerComponent(RecS3.key, 	new RecS3(MASK_STATUS.NEW)._setTransportHashMap(this.m_tpHashMap));
        this.registerComponent(RecA1.key, 	new RecA1(MASK_STATUS.NEW)._setTransportHashMap(this.m_tpHashMap));
        this.registerComponent(RecA2.key,	new RecA2(MASK_STATUS.NEW)._setTransportHashMap(this.m_tpHashMap));
        this.registerComponent(RecV.key, 	new RecV(MASK_STATUS.NEW)._setTransportHashMap(this.m_tpHashMap));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
   
    public B2_MaskDataObjectCollector(RB_TransportHashMap  p_tpHashMap, String idBgVektor, MASK_STATUS status) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
 
        this._setUseRec21(true);
 
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(status);
        
        
        MyLong lIdBgVektor = new MyLong(idBgVektor);
        
        if (lIdBgVektor.isOK()) {
        	HMAP<NamesOfFieldsToRead, Long> hmIds = new EnJoins4Querys().getIdsOfRecords(lIdBgVektor.getLong());

        	if (hmIds!=null) {
        	
        		this.registerComponent(RecS1.key, 	new RecS1(status)	._fill_id(hmIds.get(NamesOfFieldsToRead.S1))._setTransportHashMap(this.m_tpHashMap));
        		this.registerComponent(RecS2.key, 	new RecS2(status)	._fill_id(hmIds.get(NamesOfFieldsToRead.S2))._setTransportHashMap(this.m_tpHashMap));
        		this.registerComponent(RecS3.key, 	new RecS3(status)	._fill_id(hmIds.get(NamesOfFieldsToRead.S3))._setTransportHashMap(this.m_tpHashMap));
		        this.registerComponent(RecA1.key, 	new RecA1(status)	._fill_id(hmIds.get(NamesOfFieldsToRead.A1))._setTransportHashMap(this.m_tpHashMap));
		        this.registerComponent(RecA2.key,	new RecA2(status)	._fill_id(hmIds.get(NamesOfFieldsToRead.A2))._setTransportHashMap(this.m_tpHashMap));
		        this.registerComponent(RecV.key, 	new RecV(status)	._fill_id(hmIds.get(NamesOfFieldsToRead.JT_BG_VEKTOR))._setTransportHashMap(this.m_tpHashMap));

        	} else {
            	throw new myException("Error: d2d3f628-87d2-4f85-a30c-ab52736d2fcb: ID-Transport konnte nicht gelesen werden !");
            }
        	
        } else {
        	
        	throw new myException("ID-Transport konnte nicht gelesen werden !");
        	
        }
        
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
		
		//hier die connections der tabellen herstellen (bei neuerfassung ueber sequencen
		//die reihenfolge hier ist die speicherreihenfolge
		
		
		
        RecS1 recS1 = 	(RecS1)this.get(RecS1.key);
        RecS2 recS2 = 	(RecS2)this.get(RecS2.key);
        RecS3 recS3 = 	(RecS3)this.get(RecS3.key);
        RecA1 recA1 = 	(RecA1)this.get(RecA1.key);
        RecA2 recA2 = 	(RecA2)this.get(RecA2.key);
        RecV  recV = 	(RecV)this.get(RecV.key);
        
        
        if (recV.is_newRecordSet()) {
        	//die id des neuen vektors
        	String idVektor = _TAB.bg_vektor.getNextVal();

           	String idBgAtomQuelle = _TAB.bg_atom.getNextVal();
        	String idBgAtomZiel   = _TAB.bg_atom.getNextVal();

        	//dann muessen die seq-werte der 3 stationen ermittelt werden
        	String idStation1 = _TAB.bg_station.getNextVal();
        	String idStation2 = _TAB.bg_station.getNextVal();
        	String idStation3 = _TAB.bg_station.getNextVal();
        	
        	recS1._setNewVal(BG_STATION.id_bg_station, 		Long.parseLong(idStation1), mv);
        	recS2._setNewVal(BG_STATION.id_bg_station, 		Long.parseLong(idStation2), mv);
        	recS3._setNewVal(BG_STATION.id_bg_station, 		Long.parseLong(idStation3), mv);
        	
        	recA1._setNewVal(BG_ATOM.id_bg_atom, 			Long.parseLong(idBgAtomQuelle), mv);
        	recA1._setNewVal(BG_ATOM.id_bg_station_quelle, 	Long.parseLong(idStation1), mv);
        	recA1._setNewVal(BG_ATOM.id_bg_station_ziel,   	Long.parseLong(idStation2), mv);
        	
        	recA2._setNewVal(BG_ATOM.id_bg_atom, 			Long.parseLong(idBgAtomZiel), mv);
        	recA2._setNewVal(BG_ATOM.id_bg_station_quelle, 	Long.parseLong(idStation2), mv);
        	recA2._setNewVal(BG_ATOM.id_bg_station_ziel,   	Long.parseLong(idStation3), mv);
        	
        	recV._setNewVal(BG_VEKTOR.id_bg_vektor, 		Long.parseLong(idVektor), mv);
        	recV._setNewVal(BG_VEKTOR.id_bg_atom_quelle, 	Long.parseLong(idBgAtomQuelle), mv);
        	recV._setNewVal(BG_VEKTOR.id_bg_atom_ziel,   	Long.parseLong(idBgAtomZiel), mv);
        	
//        	//jetzt in alle abhaengigen vom RB_TRANSPORT die seq-nexval rein
//        	for (RB_Dataobject o: this) {
//        		Rec21 r = (Rec21)o;
//        		_TAB t = r.get_tab();
//        		
//        		if (r.get_tab() != _TAB.bg_vektor) {
//        			for (IF_Field f: t.get_fields()) {
//        				if (f.fieldName().toUpperCase().equals(BG_VEKTOR.id_bg_vektor.fn().toUpperCase())) {
//        					r._setNewVal(f,Long.parseLong(idVektor), mv );
//        				}
//        			}
//        		}
//        	}
        }
 
        
	}
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V21 do_collector,	MyE2_MessageVector mv) throws myException {
		
		if (this.newRecords) {
			//hier pruefen, ob die abschluss-ids gefuellt sind
	        RecV recV = 	(RecV)this.get(RecV.key);
	        RecS1 recS1 = 	(RecS1)this.get(RecS1.key);
	        RecS2 recS2 = 	(RecS2)this.get(RecS2.key);
	        RecS3 recS3 = 	(RecS3)this.get(RecS3.key);
	        RecA1 recA1 = 	(RecA1)this.get(RecA1.key);
	        RecA2 recA2 = 	(RecA2)this.get(RecA2.key);

	        if (recA1.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_abschluss)!=null) {
	        	Rec21 pruefprot = new Rec21(_TAB.bg_pruefprot)._fill_id(recA1.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_abschluss));
	        	pruefprot._setNewVal(BG_PRUEFPROT.id_base_table, recA1.get_rec_after_save_new().getId(), mv);
	        	pruefprot._SAVE(false, mv);
	        }
	        if (recA1.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_menge)!=null) {
	        	Rec21 pruefprot = new Rec21(_TAB.bg_pruefprot)._fill_id(recA1.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_menge));
	        	pruefprot._setNewVal(BG_PRUEFPROT.id_base_table, recA1.get_rec_after_save_new().getId(), mv);
	        	pruefprot._SAVE(false, mv);
	        }
	        if (recA1.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_preis)!=null) {
	        	Rec21 pruefprot = new Rec21(_TAB.bg_pruefprot)._fill_id(recA1.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_preis));
	        	pruefprot._setNewVal(BG_PRUEFPROT.id_base_table, recA1.get_rec_after_save_new().getId(), mv);
	        	pruefprot._SAVE(false, mv);
	        }

	        if (recA2.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_abschluss)!=null) {
	        	Rec21 pruefprot = new Rec21(_TAB.bg_pruefprot)._fill_id(recA2.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_abschluss));
	        	pruefprot._setNewVal(BG_PRUEFPROT.id_base_table, recA2.get_rec_after_save_new().getId(), mv);
	        	pruefprot._SAVE(false, mv);
	        }
	        if (recA2.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_menge)!=null) {
	        	Rec21 pruefprot = new Rec21(_TAB.bg_pruefprot)._fill_id(recA2.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_menge));
	        	pruefprot._setNewVal(BG_PRUEFPROT.id_base_table, recA2.get_rec_after_save_new().getId(), mv);
	        	pruefprot._SAVE(false, mv);
	        }
	        if (recA2.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_preis)!=null) {
	        	Rec21 pruefprot = new Rec21(_TAB.bg_pruefprot)._fill_id(recA2.get_rec_after_save_new().getLongDbValue(BG_ATOM.id_bg_pruefprot_preis));
	        	pruefprot._setNewVal(BG_PRUEFPROT.id_base_table, recA2.get_rec_after_save_new().getId(), mv);
	        	pruefprot._SAVE(false, mv);
	        }
	
		}
		
	}
	
	
//	
//	/*test*/
//	private void showActualId() {
//		try {
//			/*test*/        RB_KM keyleader = m_tpHashMap.getLeadingMaskKey();
//			RB_Dataobject   doB = 			m_tpHashMap.getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector().get(keyleader);
//			RecT test = (RecT)doB;
//			Long l__id = ((RecT)doB).getIdLong();
//
//			String id_edit = ""+doB.get_RecORD().get_PRIMARY_KEY_VALUE();
//			
//			DEBUG._print("ID lsdkfsalkdfkla: > "+id_edit);
//		} catch (Exception e) {
//			e.printStackTrace();
//			DEBUG._print("ID lsdkfsalkdfkla: Fehler ");
//		}
//	}
//	
	
}
 
 
