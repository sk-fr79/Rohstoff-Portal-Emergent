package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_DEFINITION;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.AM_BasicContainer;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.indep.exceptions.myException;

/**
 * eigene RB_Dataobject-klasse, transportiert im falle eine neuerfassung die 
 * gewuenschten id_archivmedien, die erlaubt sind
 * @author martin
 *
 */
public class ES_RB_DataobjectsCollector extends RB_DataobjectsCollector_V1 {

	
	private RB__CONST.MASK_STATUS 	status = null;
//	private ES__AttachementSeeker	attachMentSeeker =null;   	//2015-03-27: wird an die eMail-Maske uebergeben, damit dort zu bearbeitende eMail_Send-Masken
//																//            ermoeglicht wird, verschiedene anlagen anzuhaengen
	
	private Vector<String>    		v_ids_archivmedien_manuelle_zusammenstellung = null;

	/**
	 * constuctor fuer die manuelle zusammenstellung von eMail-Sendungen aus der archivmedien-liste
	 * @param id_EMAIL_SEND
	 * @param sta_tus
	 * @param vIDs_archivmedien_manuelle_zusammenstellung
	 * @throws myException
	 */
	public ES_RB_DataobjectsCollector(String id_EMAIL_SEND, RB__CONST.MASK_STATUS sta_tus, Vector<String> vIDs_archivmedien_manuelle_zusammenstellung) throws myException {
		super();
		this.status=sta_tus;
		this.v_ids_archivmedien_manuelle_zusammenstellung = vIDs_archivmedien_manuelle_zusammenstellung;
		this.database_to_dataobject(id_EMAIL_SEND);
	}

	/**
	 * constuctor fuer view-ansicht
	 * @param id_EMAIL_SEND
	 * @param sta_tus
	 * @param vIDs_archivmedien_manuelle_zusammenstellung
	 * @throws myException
	 */
	public ES_RB_DataobjectsCollector(String id_EMAIL_SEND, RB__CONST.MASK_STATUS sta_tus) throws myException {
		super();
		this.status=sta_tus;
		this.database_to_dataobject(id_EMAIL_SEND);
	}



//	/**
//	 * benutzung beim editieren aus der Maske
//	 * @param id_EMAIL_SEND
//	 * @param sta_tus
//	 * @param attachment_Seeker
//	 * @throws myException
//	 */
//	public ES_RB_Dataobjects_Container(String id_EMAIL_SEND, RB__CONST.MASK_STATUS sta_tus, ES__AttachementSeeker attachment_Seeker) throws myException {
//		super();
//		this.status=sta_tus;
//		this.attachMentSeeker = attachment_Seeker;	
//		
//		this.database_to_dataobject(id_EMAIL_SEND);
//		
//	}


	
	@Override
	public void database_to_dataobject(Object startPoint) throws myException {
		if (startPoint==null) {   //neueingabe
			ES_RB_DataObject  o_dat = new ES_RB_DataObject(new RECORDNEW_EMAIL_SEND());
			
			this.registerComponent(new RB_KM(_TAB.email_send),o_dat);
			if (this.v_ids_archivmedien_manuelle_zusammenstellung!=null) {
				o_dat.get_v__id_archivmedien_vorschlag().addAll(this.v_ids_archivmedien_manuelle_zusammenstellung);
			}
			
			
		} else {
			String id_EMAIL_SEND = (String) startPoint;
			ES_RB_DataObject  o_dat = new ES_RB_DataObject(new RECORD_EMAIL_SEND(id_EMAIL_SEND), this.status);
			
			//hier eine Schatzsuche nach dem basis-Container der archivmedien durchfuehren
			TS_Treasure_Chest t_chest = TS_Treasure_CONST.find_TreasureChest(TS_DEFINITION.UPLOADFILES_TREASURE_CHEST);
			
			if (t_chest!=null) {
				
				AM_BasicContainer UploadContainer = (AM_BasicContainer)t_chest.get_TREASURE_CHEST();
				ES__AttachementSeeker seeker = ES__AttachmentSeeker_Factory.get_Seeker(UploadContainer.get_table_NAME(), UploadContainer.get_id_TABLE());
				if (seeker !=null) {
					Vector<String> v_possible_ids = seeker.get_vPossible_id_Archivmedien_to_send(o_dat);
					if (v_possible_ids!=null) {
						o_dat.get_v_id_archivmedien_possible().addAll(v_possible_ids);
					}
				}
			}
			this.registerComponent(new RB_KM(_TAB.email_send), o_dat);
		}
	}

	@Override
	public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector) throws myException {
	}

	

	
}