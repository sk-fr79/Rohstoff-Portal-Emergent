package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossing_RecordList.Fields;

/**
 * Hilfsklasse zur sammlung der relevante daten eine warenbewegung (JT_VPOS_TPA_FUHRE / JT_BEWEGUNG_VEKTOR)
 * @author martin
 *
 */
public class BorderCrossingInfo {
	public String 						f_id_table = null;
	public _TAB   						f_table = null; 		  //z.b. VPOS_TP_FUHRE oder BEWEGUNG_VECTOR
	public String 						f_id_land_start = null;
	public String 						f_id_land_ziel = null;
	public MyDate 						f_relevant_date = null;      // in der regel das startdatum der warenbewegung 
	public String 						f_reminder_key = null;

	//info-felder
	public String 						startOrt = null;
	public String 						zielOrt = null;
	public String 						startSorte = null;
	public String 						zielSorte = null;
	
	// Manfred: ID für die einfachere Prüfung auf die Sorte 
	public String						idStartSorte = null;
	public String						idZielSorte = null;
	

	//wird ein objekt zum warnen gefunden, dann wird dieses feld gefuellt
	public BorderCrossing_Record  		f_record_bordercross = null;
	
	/**
	 * 
	 * @param id_table
	 * @param table
	 * @param id_land_start
	 * @param id_land_ziel
	 * @param pDate
	 * @param p_reminder_key
	 * @param p_start_ort
	 * @param p_ziel_ort
	 * @param p_start_sorte
	 * @param p_ziel_sorte
	 */
	public BorderCrossingInfo(	String id_table
								,_TAB table
								, String id_land_start
								, String id_land_ziel
								, MyDate pDate
								, String p_reminder_key
								, String p_start_ort
								, String p_ziel_ort
								, String p_start_sorte
								, String p_ziel_sorte
								, String p_id_start_sorte
								, String p_id_ziel_sorte
								) { 
		super();
		this.f_id_table = id_table;
		this.f_table = table;
		this.f_id_land_start = id_land_start;
		this.f_id_land_ziel = id_land_ziel;
		this.f_relevant_date = pDate;			
		this.f_reminder_key = p_reminder_key;
		
		this.startOrt = p_start_ort;
		this.zielOrt = p_ziel_ort;
		this.startSorte = p_start_sorte;
		this.zielSorte = p_ziel_sorte;
		
		this.idStartSorte = p_id_start_sorte;
		this.idZielSorte = p_id_ziel_sorte;
		
	}
	
	public void set_bordercrossing_that_causes_warning(BorderCrossing_Record  record_bordercross) {
		this.f_record_bordercross = record_bordercross;
	}
	

	
	public MyE2_Label get_label_start() throws myException {
		MutableStyle styleLandInBordercrossing = new MutableStyle();
		styleLandInBordercrossing.setProperty(Label.PROPERTY_FONT, new E2_FontPlain());
		styleLandInBordercrossing.setProperty(Label.PROPERTY_FOREGROUND, Color.BLACK);

		MutableStyle styleLandEmptyInBordercrossing = new MutableStyle();
		styleLandEmptyInBordercrossing.setProperty(Label.PROPERTY_FONT, new E2_FontItalic());
		styleLandEmptyInBordercrossing.setProperty(Label.PROPERTY_FOREGROUND,  Color.DARKGRAY);
		
		
		if (S.isEmpty(this.f_id_land_start)) {
			return new MyE2_Label("-");
		} else {
			RECORD_LAND rl = new RECORD_LAND(this.f_id_land_start);
			if (S.isEmpty(this.f_record_bordercross.getValue(Fields.ID_LAND_QUELLE,"") )) {
				return new MyE2_Label(rl.get_LAENDERNAME_cUF_NN(rl.get_LAENDERCODE_cUF_NN("--")),styleLandEmptyInBordercrossing);
			} else {
				return new MyE2_Label(rl.get_LAENDERNAME_cUF_NN(rl.get_LAENDERCODE_cUF_NN("--")),styleLandInBordercrossing);
			}
		}
	}

	public MyE2_Label get_label_ziel() throws myException {
		MutableStyle styleLandInBordercrossing = new MutableStyle();
		styleLandInBordercrossing.setProperty(Label.PROPERTY_FONT, new E2_FontPlain());
		styleLandInBordercrossing.setProperty(Label.PROPERTY_FOREGROUND, Color.BLACK);

		MutableStyle styleLandEmptyInBordercrossing = new MutableStyle();
		styleLandEmptyInBordercrossing.setProperty(Label.PROPERTY_FONT, new E2_FontItalic());
		styleLandEmptyInBordercrossing.setProperty(Label.PROPERTY_FOREGROUND,  Color.DARKGRAY);

		
		
		if (S.isEmpty(this.f_id_land_ziel)) {
			return new MyE2_Label("-");
		} else {
			RECORD_LAND rl = new RECORD_LAND(this.f_id_land_ziel);
			if (S.isEmpty(this.f_record_bordercross.getValue(Fields.ID_LAND_ZIEL,"") )) {
				return new MyE2_Label(rl.get_LAENDERNAME_cUF_NN(rl.get_LAENDERCODE_cUF_NN("--")),styleLandEmptyInBordercrossing);
			} else {
				return new MyE2_Label(rl.get_LAENDERNAME_cUF_NN(rl.get_LAENDERCODE_cUF_NN("--")),styleLandInBordercrossing);
			}
		}
	}


	public BorderCrossingInfo get_Copy() {
		BorderCrossingInfo new_wb = new BorderCrossingInfo(
																this.f_id_table, 
																this.f_table, 
																this.f_id_land_start, 
																this.f_id_land_ziel, 
																this.f_relevant_date, 
																this.f_reminder_key,
																this.startOrt,
																this.zielOrt,
																this.startSorte,
																this.zielSorte,
																this.idStartSorte,
																this.idZielSorte
																);
		
		return new_wb;
	}
	
	
}
