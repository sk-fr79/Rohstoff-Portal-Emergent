package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_Helper;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_DEFINITION;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_Treasure_Chest_searcher;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;

public class __VALIDATOR_4_ATTACHMENT_POPUP extends XX_ActionValidator {

	private IF_enum_4_db BtKey = null;
			
	
	/*
	 * allgemeiner Validierer fuer die Buttons in den jeweiligen popups. damit kann fuer die einzelnen zusatzdateien-popups 
	 * jeweils unterschiedliche validerbereiche hinterlegt werden, indem die bereiche in die VALID_ENUM_MODULES eingetragen wird
	 */
	
	public __VALIDATOR_4_ATTACHMENT_POPUP(IF_enum_4_db btKey) {
		super();
		this.BtKey = btKey;
	}

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		
		//zuerst die aktuelle Tabelle zu dem attachment-popup besorgen
		TS_Treasure_Chest_searcher  tsSearcher = new TS_Treasure_Chest_searcher(TS_DEFINITION.UPLOADFILES_TREASURE_CHEST);
		
		if (tsSearcher.get_found_treasure_chest()==null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Systemfehler: Es konnte kein Bezug zum validierten Objekt hergestellt werden: (Suchpfad:"+TS_DEFINITION.UPLOADFILES_TREASURE_CHEST.name()+")")));
		} else {
			String cTABLENAME = ((AM_BasicContainer)tsSearcher.get_found_treasure_chest().get_TREASURE_CHEST()).get_table_NAME(); 
			
			VALID_ENUM_MODULES.RANGE_KEY  module_key = new __FINDER_MODULE_KEY(cTABLENAME).get_Key();
			mv.add_MESSAGE(new E2_ButtonAUTHValidator_Helper(module_key.db_val(), this.BtKey.db_val(),true).get_oMV());
		}
		
		return mv;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException {
		return null;
	}

}
