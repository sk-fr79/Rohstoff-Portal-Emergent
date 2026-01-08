package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;

public class AH7_Marker extends RB_lab {

	/**
	 * definiert die markierung in der liste fuer die ah7-felder
	 * @author martin
	 *
	 */
	public enum MARKER {
		id_1_verbr_veranlasser(	AH7_STEUERDATEI.id_1_verbr_veranlasser) 
		,id_1_import_empfaenger(AH7_STEUERDATEI.id_1_import_empfaenger) 
		,id_1_abfallerzeuger(	AH7_STEUERDATEI.id_1_abfallerzeuger)
		,id_1_verwertungsanlage(AH7_STEUERDATEI.id_1_verwertungsanlage) 
		
		,id_2_verbr_veranlasser(AH7_STEUERDATEI.id_1_verbr_veranlasser) 
		,id_2_import_empfaenger(AH7_STEUERDATEI.id_1_import_empfaenger) 
		,id_2_abfallerzeuger(	AH7_STEUERDATEI.id_1_abfallerzeuger)
		,id_2_verwertungsanlage(AH7_STEUERDATEI.id_1_verwertungsanlage) 
		
		,id_3_verbr_veranlasser(AH7_STEUERDATEI.id_1_verbr_veranlasser) 
		,id_3_import_empfaenger(AH7_STEUERDATEI.id_1_import_empfaenger) 
		,id_3_abfallerzeuger(	AH7_STEUERDATEI.id_1_abfallerzeuger)
		,id_3_verwertungsanlage(AH7_STEUERDATEI.id_1_verwertungsanlage) 
		;

		private IF_Field  f = null; 
		private MARKER(IF_Field fd) {
			this.f = fd;
		}
		public IF_Field getField() {
			return f;
		}
		
		
		
	}
	
	public AH7_Marker(IF_Field field) {
		super();
		
		MARKER myMarker = null; 
		for (MARKER marker: MARKER.values()) {
			if (marker.getField()==field) {
				myMarker = marker;
				break;
			}
		}
		
		if (myMarker != null) {
			switch( myMarker)  {
			case 	id_1_verbr_veranlasser:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld1.png"))._tooltxt(S.ms("1. Person, die die Verbringung veranlasst [Kontrollpapier]"));
				break;
			case id_1_import_empfaenger:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld2.png"))._tooltxt(S.ms("2. Importeur/Empfänger [Kontrollpapier]"));
				break;
			case id_1_abfallerzeuger:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld6.png"))._tooltxt(S.ms("6. Abfallerzeuger  [Kontrollpapier]"));
				break;
			case id_1_verwertungsanlage:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld7.png"))._tooltxt(S.ms("7. Verwertungsanlagge [Kontrollpapier]"));
				break;
	
			case id_2_verbr_veranlasser:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld1.png"))._tooltxt(S.ms("1. Person, die die Verbringung veranlasst [Abladepapier]"));
				break;
			case id_2_import_empfaenger:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld2.png"))._tooltxt(S.ms("2. Importeur/Empfänger [Abladepapier]"));
				break;
			case id_2_abfallerzeuger:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld6.png"))._tooltxt(S.ms("6. Abfallerzeuger  [Abladepapier]"));
				break;
			case id_2_verwertungsanlage:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld7.png"))._tooltxt(S.ms("7. Verwertungsanlagge [Abladepapier]"));
				break;
			
			case id_3_verbr_veranlasser:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld1.png"))._tooltxt(S.ms("1. Person, die die Verbringung veranlasst [Ladepapier]"));
				break;
			case id_3_import_empfaenger:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld2.png"))._tooltxt(S.ms("2. Importeur/Empfänger [Ladepapier]"));
				break;
			case id_3_abfallerzeuger:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld6.png"))._tooltxt(S.ms("6. Abfallerzeuger  [Ladepapier]"));
				break;
			case id_3_verwertungsanlage:
				this._icon(E2_ResourceIcon.get_RI("ah7_feld7.png"))._tooltxt(S.ms("7. Verwertungsanlagge [Ladepapier]"));
				break;
			default:
				break;
			}
		}
		
	}

	
	
}
