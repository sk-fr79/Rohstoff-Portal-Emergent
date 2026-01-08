/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE.MASK_Controller
 * @author manfred
 * @date 05.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE.MASK_Controller;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE.RB_BT_AttachmentToWFEntry;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE.WF_SIMPLE_CONST;

/**
 * @author manfred
 * @date 05.04.2019
 *
 */
public class WF_SIMPLE_WK_MaskController_Abgeschlossen extends RB_MaskController {

	private MASK_STATUS _status ;

	/**
	 * @author manfred
	 * @date 08.04.2019
	 *
	 * @param p_componentMapCollector
	 * @throws myException
	 */
	public WF_SIMPLE_WK_MaskController_Abgeschlossen(RB_ComponentMapCollector p_componentMapCollector)
			throws myException {
		super(p_componentMapCollector);
		_status = this.getMaskStatus();
		
		
		String geloescht_LZE 	= this.get_liveVal(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),LAUFZETTEL_EINTRAG.geloescht);
		String geloescht_LZ 	= this.get_liveVal(WF_SIMPLE_CONST.getMaskKeyLaufzettel(),LAUFZETTEL.geloescht);
		boolean b_geloescht = 	S.isFull(geloescht_LZE) && geloescht_LZE.equalsIgnoreCase("Y");
		b_geloescht |= 			S.isFull(geloescht_LZ)  && geloescht_LZ.equalsIgnoreCase("Y");
		
		
		// immer disabled
		_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettel(), LAUFZETTEL.abgeschlossen_am);
		_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettel(), LAUFZETTEL.id_user_abgeschlossen_von);
		
		
		
		if (! b_geloescht && _status.equals(MASK_STATUS.EDIT)){
//			RB_ComponentMap compMap_LZEintrag = p_componentMapCollector.get(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag());

			MyLong idLaufzettelEintrag = this.get_MyLong_dbVal(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag);
			
			RB_BT_AttachmentToWFEntry btAttachment = 
					(RB_BT_AttachmentToWFEntry) p_componentMapCollector.get(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag())
					.getComp(WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_EINTRAG_ATTACHMENT.key());
			btAttachment.initButton(idLaufzettelEintrag.get_cUF_LongString());
			
			
			String idUserAbgeschlossen = this.get_liveVal(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von);
			
			if (S.isEmpty(idUserAbgeschlossen)){
				// enablen der Felder
				_registerEnabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
						LAUFZETTEL_EINTRAG.aufgabe,
						LAUFZETTEL_EINTRAG.bericht,
						LAUFZETTEL_EINTRAG.faellig_am,
						LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit,
						LAUFZETTEL_EINTRAG.kadenz_nach_abschluss,
						LAUFZETTEL_EINTRAG.id_user_bearbeiter,
						LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von
						
						);
				
				_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag() 
						, LAUFZETTEL_EINTRAG.id_user_besitzer
						, LAUFZETTEL_EINTRAG.angelegt_am
						);
				
				_registerEnabler(WF_SIMPLE_CONST.getMaskKeyLaufzettel()
						, LAUFZETTEL.text
						);
				
				
			} else {
				// disablen der Felder
				_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
						LAUFZETTEL_EINTRAG.aufgabe,
						LAUFZETTEL_EINTRAG.bericht,
						LAUFZETTEL_EINTRAG.faellig_am,
						LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit,
						LAUFZETTEL_EINTRAG.kadenz_nach_abschluss,
						LAUFZETTEL_EINTRAG.id_user_bearbeiter,
						LAUFZETTEL_EINTRAG.id_user_besitzer,
						LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von,
						LAUFZETTEL_EINTRAG.angelegt_am);

				_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettel()
						, LAUFZETTEL.text);
				
				_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
						WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_EINTRAG_ATTACHMENT.key());
			}

		} else if (b_geloescht){
			// disablen der Felder
			_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
					LAUFZETTEL_EINTRAG.aufgabe,
					LAUFZETTEL_EINTRAG.bericht,
					LAUFZETTEL_EINTRAG.faellig_am,
					LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit,
					LAUFZETTEL_EINTRAG.kadenz_nach_abschluss,
					LAUFZETTEL_EINTRAG.id_user_bearbeiter,
					LAUFZETTEL_EINTRAG.id_user_besitzer,
					LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von,
					LAUFZETTEL_EINTRAG.angelegt_am,
					LAUFZETTEL_EINTRAG.id_laufzettel_status);

			_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettel()
					, LAUFZETTEL.text);
			
			_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
					WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_EINTRAG_ATTACHMENT.key());
			
			
			
		}
		
	}

	/**
	 * @author manfred
	 * @date 05.04.2019
	 *
	 * @param p_componentMap
	 * @throws myException
	 */
	public WF_SIMPLE_WK_MaskController_Abgeschlossen(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
		_status = this.getMaskStatus();
		
		String geloescht_LZE 	= this.get_liveVal(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),LAUFZETTEL_EINTRAG.geloescht);
		String geloescht_LZ 	= this.get_liveVal(WF_SIMPLE_CONST.getMaskKeyLaufzettel(),LAUFZETTEL.geloescht);
		boolean b_geloescht = 	S.isFull(geloescht_LZE) && geloescht_LZE.equalsIgnoreCase("Y");
		b_geloescht |= 			S.isFull(geloescht_LZ)  && geloescht_LZ.equalsIgnoreCase("Y");
		
		
		if (! b_geloescht && _status.equals(MASK_STATUS.EDIT)){
			String idUserAbgeschlossen = this.get_liveVal(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von);
			if (S.isEmpty(idUserAbgeschlossen)){
				// enablen der Felder
				_registerEnabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
						LAUFZETTEL_EINTRAG.aufgabe,
						LAUFZETTEL_EINTRAG.bericht,
						LAUFZETTEL_EINTRAG.faellig_am,
						LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit,
						LAUFZETTEL_EINTRAG.kadenz_nach_abschluss,
						LAUFZETTEL_EINTRAG.id_user_bearbeiter
						);
				_registerEnabler(WF_SIMPLE_CONST.getMaskKeyLaufzettel(),
						LAUFZETTEL.text
						);
				
				_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag() 
						, LAUFZETTEL_EINTRAG.id_user_besitzer
						, LAUFZETTEL_EINTRAG.angelegt_am
						);

				
			} else {
				// disablen der Felder
				_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
						LAUFZETTEL_EINTRAG.aufgabe,
						LAUFZETTEL_EINTRAG.bericht,
						LAUFZETTEL_EINTRAG.faellig_am,
						LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit,
						LAUFZETTEL_EINTRAG.kadenz_nach_abschluss,
						LAUFZETTEL_EINTRAG.id_user_bearbeiter,
						LAUFZETTEL_EINTRAG.id_user_besitzer,
						LAUFZETTEL_EINTRAG.angelegt_am);

				_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettel()
						, LAUFZETTEL.text);
				
				_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
						WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_EINTRAG_ATTACHMENT.key());
			}
		} else if (b_geloescht){
			// disablen der Felder
			_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
					LAUFZETTEL_EINTRAG.aufgabe,
					LAUFZETTEL_EINTRAG.bericht,
					LAUFZETTEL_EINTRAG.faellig_am,
					LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit,
					LAUFZETTEL_EINTRAG.kadenz_nach_abschluss,
					LAUFZETTEL_EINTRAG.id_user_bearbeiter,
					LAUFZETTEL_EINTRAG.id_user_besitzer,
					LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von,
					LAUFZETTEL_EINTRAG.angelegt_am,
					LAUFZETTEL_EINTRAG.id_laufzettel_status);

			_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettel()
					, LAUFZETTEL.text);
			
			_registerDisabler(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag(),
					WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_EINTRAG_ATTACHMENT.key());
				
		}
		

		
		
	}
	
	
	


}
