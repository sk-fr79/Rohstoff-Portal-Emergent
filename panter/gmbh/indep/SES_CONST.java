package panter.gmbh.indep;

public class SES_CONST {

	/**
	 * sollen an bestimmten bereichen (beispiel: komplexe popups) informationen transportiert werden,
	 * dann kann ein kenner hinterlegt werden, der in einer Session-Hashmap benutzt wird. 
	 * Beispiel: 	im zusammenhang mit Upload-dateien muss immer die kombination ID_TABLE und TABLE_BASENAME vorhanden sein
	 * 				Diese muss von object zu object kommuniziert werden. Wird beim Start des uploads diese in einem speziell dazu 
	 * 				zu erzeugenden objekt hinterlegt, dann kann innerhalb es bereichs darauf zurueckgegegriffen werden 
	 * @author martin
	 *
	 */
	public enum GLOBAL_OBJECTS {
		INFOBLOCK_ARCHIVMEDIEN,  		//wird im archivmedienbereich benutzt
	}
	
	
}
