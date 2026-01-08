package panter.gmbh.Echo2.UserSettings;


public enum ENUM_USER_SAVEKEY {
	 SESSION_HASH_SAVE_DELEVELOPER_DEBUG_LEVEL
		
	,SESSION_HASH_USER_WINDOWSIZE
	,SESSION_HASH_USER_MULTI_ROW_PRESELECTION
	,SESSION_HASH_USER_TABPANE_ORDERS
	,SESSION_HASH_USER_OPENMODULES
	,SESSION_HASH_USER_LIST_COLUMNS_VISIBLE
	,SESSION_HASH_USER_SEARCH_KOMBINATION
	,SESSION_HASH_USER_SAVE_LIST_SEITENLEANGE   					//speicherfunktion fuer die selektion der zeilen in einer liste
	,SESSION_HASH_USER_SIZE_MULTI_IMAGE_PDF     					// speichermethode fuer die Downloadmaske der zusatzdateien
	,SESSION_HASH_USER_SAVE_SORT_LIST           					//speichern der sortierung einer liste
	,SESSION_HASH_USER_SAVE_LAGER_INVENTUR_MODUL  					//speichern der Auswahl beim anfordern der Lagerinventuren
	,SESSION_HASH_USER_VARIANTEN_KOSTENERMITTLUNG  					//setting von einstellungen zum thema kosten aus speditionskosten ermitteln
	
	,SESSION_HASH_GLOBAL_SETTINGS_DIVERSE   						//sessionhash fuer singulaere speicherobjekte

	,SESSION_HASH_USER_SETTIN_EXPORT_NAVILIST_TO_CSV   				//sessionhash fuer die sicherung der gewuenschten CSV-Export-spalten
	,SESSION_HASH_USER_SETTING_SELECTOR_IN_UPLOADFILES   			//2016-03-16: auswahleinstellung in den Upload-files (wird ergänzt durch den tabellennamen)
	
	
	,SESSION_HASH_WF_HEAD_LIST_SELECTOR_MAIN             			//speichert den zuletzt benutzern Selektor im Workflow/Laufzettel-modul

	
	
	,MODULKENNER_SAVE_USERSETTINGS_IN_ADRESSINFO
	,MODULEKENNER__SAVE_SPEDITIONSSELECTION_ADRESSMODULE   			//speichert den include- oder exclude setter im Adressenselektor speditionen
	,MODULEKENNER__SAVE_SELECTION_HELP_AND_DOCUMENTATION  	 		//2015-10-13: auswahl bei der Hilfemaske 
	,SAVEING_USERSETTINGS_IN_AW2_AUSERTUNGEN              			//2015-11-10: speichern der einstellungen in der gruppierbaren auswertung 
	,SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCHADRESS              	//2015-12-03: speichern der einstellungen in der gruppierbaren auswertung
	,SAVEING_SORTING_SEARCHFIELD_RB_HL_SELECT_LIEFER_ADRESSEN    	//2015-12-03: speichern der einstellungen in der gruppierbaren auswertung
	,SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCHARTBEZ			    	//2015-12-04: speichern der suche der artikelbezeichnungen
	,SAVEING_SORTING_SEARCHFIELD_RB_HL_ARTIKEL				    	//2019-09-17: speichern der suche nach artikel
	,SAVEING_SORTING_SEARCHFIELD_RB_SEARCH_KONTRAKT_ODER_ANGEBOT	 //2016-04-15: speichern der suche nach angeboten/kontrakten
	
	,SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCH_KONTRAKT			    //2019-01-02: speichern der suche nach kontrakten

	,SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCH_ANGEBOT  				//2019-03-19: speichern der suche nach angeboten
	
	,KEY_SAVE_SEARCHFIELD_IN_XML_MODULES                        	//2016-06-30: speichert das Suchfeld in den XML-Listen
	
	,SESSIONHASH_SAVE_COLUMN_ORDER_OF_NAVILIST						//2016-07-05: speichert die spaltenreihenfolge in einer Navilist
	,SESSIONHASH_SAVE_COLUMN_WIDTH_OF_NAVILIST						//2016-07-05: speichert die spaltenbreite in einer Navilist
	
	
	,KEY_SAVE_SEARCHFIELD_IN_PLACEHOLDERLIST						//2016-07-05: speichert den letzten suchbegriff in der liste der platzhalter (info-popup)
	,KEY_SAVE_SORT_WE_SEPARATION_POPUP								//2016-11-11: speichert die sortierspalte im WE-Separator
	,KEY_SAVE_FUHRE_LAND_SELECTION
	
	,SESSION_HASH_BEWEGUNG_INFO_EMPTY_FIELD 						//2017-09-05
	
	,SESSION_HASH_MERKMAL_ARTBEZ2_ANGEZEIGT							//2017-09-05
	,SESSION_HASH_MERKMAL_KONTRAKT_AUSKLAPP_ZEIGE_RECHPOS_MIT_MENGEN//2017-09-05
	,SESSION_HASH_MERKMAL_KONTRAKT_AUSKLAPP_ZEIGE_FUHREN			//2017-09-05
	,SESSION_HASH_MERKMAL_KONTRAKT_AUSKLAPP_SORT_ANR1_2				//2017-09-05
	,SESSION_HASH_MERKMAL_KONTRAKT_AUSKLAPP_POSNUMMER				//2017-09-05
	
	,KEY_SAVE_SORTING_SEARCHDIALOG_FUHRE							//2017-09-21
	, KEY_SAVE_SORTING_SEARCHDIALOG_WIEKGEARTE
	
	,KEY_SAVE_SQLFIELDMASK_4_EXPORT 								//speichert einstellungen im EXP_ortSqlFromList
	
	,KEY_SAVE_SETTINGS_PROGRAMMGENERATOR_RB_MODUL 
	
	//29.10.2018: zusatz schlussel fur info zentrum knopf
	,KEY_SAVE_USERSETTINGS_MELDUNGEN_IN_ADRESSINFO
	,KEY_SAVE_USERSETTINGS_ZUSATZDATEI_IN_ADRESSINFO
	,SAVING_SORTING_SEARCHFIELD_WK_RB_SEARCH_FUHRE
	,SAVING_SORTING_SEARCHFIELD_WK_RB_SEARCH_CONTAINTER
}
