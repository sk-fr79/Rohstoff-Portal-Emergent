package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.enumtools.IF_enum_4_fielddefinition;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;

public class FZ_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public FZ_LIST_SqlFieldMAP() throws myException 
	{
		super(_TAB.bewegung_vektor.n(), "", false);
		
		//dummy-felder zum fuellen der komplexen spalten, die eine id_bewegung_vektor brauchen
		this.add_SQLField(new SQLField(	BEWEGUNG_VEKTOR.id_bewegung_vektor.tnfn(), 
										FZ__CONST.SPALTEN_NAMEN.STARTORT.sqlFieldAlias(), 
										FZ__CONST.SPALTEN_NAMEN.STARTORT.userInfo(), bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField(	BEWEGUNG_VEKTOR.id_bewegung_vektor.tnfn(), 
										FZ__CONST.SPALTEN_NAMEN.ZIELORT.sqlFieldAlias(), 
										FZ__CONST.SPALTEN_NAMEN.ZIELORT.userInfo(), bibE2.get_CurrSession()), true);
	
		this.add_SQLField(new SQLField(	BEWEGUNG_VEKTOR.id_bewegung_vektor.tnfn(), 
										FZ__CONST.SPALTEN_NAMEN.SORTE.sqlFieldAlias(), 
										FZ__CONST.SPALTEN_NAMEN.SORTE.userInfo(), bibE2.get_CurrSession()), true);

		
		
//		
//		
//		
//		//joiner-tabelle zu den hilfsspalten
//		this.add_JOIN_Table(	BEWEGUNG_VEKTOR_JOINER.fullTabName(), 	
//								"CON", 
//								SQLFieldMAP.LEFT_OUTER, 
//								BEWEGUNG_VEKTOR_JOINER.id_bewegung_vektor_joiner.fieldName(), 
//								true, 
//								//"NVL(JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_ATOM_TRIGSTART,START_ATOM(JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_VEKTOR))=ATS.ID_BEWEGUNG_ATOM",
//								new vgl(BEWEGUNG_VEKTOR_JOINER.id_bewegung_vektor.t("CON"),BEWEGUNG_VEKTOR.id_bewegung_vektor.t()).s(),
//								"CON_", null);	
		
		
		//start-ort
		this.add_JOIN_Table(	BEWEGUNG_ATOM.fullTabName(), 	
								"ATS", 
								SQLFieldMAP.LEFT_OUTER, 
								BEWEGUNG_ATOM.id_bewegung_atom.fieldName(), 
								true, 
								//"JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_ATOM_TRIGSTART=ATS.ID_BEWEGUNG_ATOM",
								new vgl(BEWEGUNG_VEKTOR.id_bewegung_atom_trigstart.t(),BEWEGUNG_ATOM.id_bewegung_atom.t("ATS")).s(),
								"ATS_", null);		
		
		this.add_JOIN_Table(	BEWEGUNG_STATION.fullTabName(), 	
								"STS", 
								SQLFieldMAP.LEFT_OUTER, 
								BEWEGUNG_STATION.id_bewegung_station.fieldName(), 
								true, 
								"ATS.ID_BEWEGUNG_ATOM=STS.ID_BEWEGUNG_ATOM AND STS.MENGENVORZEICHEN=-1", 
								"STS_", null);	
		
		
		this.add_JOIN_Table(	ADRESSE.fullTabName(), 	
								"ADS", 
								SQLFieldMAP.LEFT_OUTER, 
								DB_STATICS.listOfFields(":", ADRESSE.id_adresse,ADRESSE.name1, ADRESSE.name2, ADRESSE.plz, ADRESSE.ort), 
								true, 
								"STS.ID_ADRESSE=ADS.ID_ADRESSE", 
								"ADS_", null);	
		
		this.add_JOIN_Table(	ADRESSE.fullTabName(), 	
								"ADBS", 
								SQLFieldMAP.LEFT_OUTER, 
								DB_STATICS.listOfFields(":", ADRESSE.id_adresse,ADRESSE.name1, ADRESSE.name2, ADRESSE.plz, ADRESSE.ort), 
								true, 
								"STS.ID_ADRESSE_BESITZER=ADBS.ID_ADRESSE", 
								"ADBS_", null);	

		
		
		
		//ziel-ort
		this.add_JOIN_Table(	BEWEGUNG_ATOM.fullTabName(), 	
								"ATZ", 
								SQLFieldMAP.LEFT_OUTER, 
								BEWEGUNG_ATOM.id_bewegung_atom.fieldName(), 
								true, 
								//"JT_BEWEGUNG_VEKTOR.ID_BEWEGUNG_ATOM_TRIGZIEL=ATZ.ID_BEWEGUNG_ATOM",
								new vgl(BEWEGUNG_VEKTOR.id_bewegung_atom_trigziel.t(),BEWEGUNG_ATOM.id_bewegung_atom.t("ATZ")).s(),

								"ATZ_", null);	
		
		this.add_JOIN_Table(	BEWEGUNG_STATION.fullTabName(), 	
								"STZ", 
								SQLFieldMAP.LEFT_OUTER, 
								BEWEGUNG_STATION.id_bewegung_station.fieldName(), 
								true, 
								"ATZ.ID_BEWEGUNG_ATOM=STZ.ID_BEWEGUNG_ATOM AND STZ.MENGENVORZEICHEN=1", 
								"STZ_", null);	
		
		this.add_JOIN_Table(	ADRESSE.fullTabName(), 	
								"ADZ", 
								SQLFieldMAP.LEFT_OUTER, 
								DB_STATICS.listOfFields(":", ADRESSE.id_adresse,ADRESSE.name1, ADRESSE.name2, ADRESSE.plz, ADRESSE.ort), 
								true, 
								"STZ.ID_ADRESSE=ADZ.ID_ADRESSE", 
								"ADZ_", null);	

		this.add_JOIN_Table(	ADRESSE.fullTabName(), 	
								"ADBZ", 
								SQLFieldMAP.LEFT_OUTER, 
								DB_STATICS.listOfFields(":", ADRESSE.id_adresse,ADRESSE.name1, ADRESSE.name2, ADRESSE.plz, ADRESSE.ort), 
								true, 
								"STZ.ID_ADRESSE_BESITZER=ADBZ.ID_ADRESSE", 
								"ADBZ_", null);	

		
		
		
		//start-sorte
		this.add_JOIN_Table(	ARTIKEL.fullTabName(), 	
								ARTIKEL.fullTabName(), 
								SQLFieldMAP.LEFT_OUTER, 
								DB_STATICS.listOfFields(":", ARTIKEL.id_artikel,ARTIKEL.anr1, ARTIKEL.artbez1), 
								true, 
								"ATZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL", 
								"", null);	
		
		
		

		this.add_JOIN_Table(	BEWEGUNG.fullTabName(), 	
								BEWEGUNG.fullTabName(), 
								SQLFieldMAP.LEFT_OUTER, 
								BEWEGUNG.id_bewegung.fieldName()+":"+BEWEGUNG.id_vpos_tpa_fuhre.fieldName(), 
								true, 
								new vgl(BEWEGUNG_VEKTOR.id_bewegung,BEWEGUNG.id_bewegung).s(), 
								"B_", null);	
		
	
		this.add_JOIN_Table(	VPOS_TPA_FUHRE.fullTabName(), 	
								VPOS_TPA_FUHRE.fullTabName(), 
								SQLFieldMAP.LEFT_OUTER, 
								VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fieldName(), 
								true, 
								new vgl(BEWEGUNG.id_vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre).s(), 
								"F_", null);	

		

		//sonderdefinitionen zufuegen
		for (IF_enum_4_fielddefinition def: FZ__CONST.FIELDS.STARTADRESSE_NAME_ORT.get_all_defs()) {
			
//			DEBUG.System_println(def.querydef());
			
			this.add_SQLField(new SQLField(
					bibALL.ReplaceTeilString(def.querydef(),"#EIGENE_ADRESS_ID#",bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF()), 
					def.alias(), 
					new MyE2_String(def.user_text_lang())), false);
		}
		
		this.initFields();
	}
	
}
