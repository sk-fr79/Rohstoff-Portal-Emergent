package panter.gmbh.basics4project.specialViews;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/**
 * aufbau eines views, der alle felder (ohne ausnahme) die mit der atomstruktur verjoined sind, flach abbildet
 */
public class build_flat_atom extends XXX_ViewBuilder {

	private static String view_name =  "V_ATOM_FLAT"; 
	
	
	private enum praefix {
		ATOM(			_TAB.bewegung_atom,			"A")
		,STATION_START(	_TAB.bewegung_station,		"S")
		,STATION_ZIEL( 	_TAB.bewegung_station,		"Z")
		,VEKTOR_POS(	_TAB.bewegung_vektor_pos,	"VP")
		,VEKTOR(		_TAB.bewegung_vektor,		"V")
		,BEWEGUNG(		_TAB.bewegung,				"B")
		;
		
		private _TAB 	table = null;
		private String 	praef = null;
		
		
		private praefix(_TAB p_table, String p_preaf) {
			this.table = p_table;
			this.praef = p_preaf;
		}
		
		/**
		 * baut einen string der A.NAME1 as A_NAME1, .... auf
		 * @param with_comma_at_end
		 * @return
		 * @throws myException 
		 */
		public String get_field_block(boolean with_comma_at_end) throws myException {
			String c = "";
			
			IF_Field[] fields = this.table.get_fields();
			
			for (IF_Field f: fields) {
				String fieldalias = this.praef+"_"+f.fieldName();
				//oracle erlaubt bezeichner mit 30 Zeichen
				if (fieldalias.length()>29) {
					fieldalias = fieldalias.substring(0, 30);
				}
				c = c + "\n"+this.praef+"."+f.fieldName()+" AS "+ fieldalias+",";
			}
			
			if (!with_comma_at_end) {
				c = c.substring(0, c.length()-1);
			}
			
			return c;
		}
		
		/**
		 * 
		 * @return  z.b. leber.JT_ADRESSE A
		 */
		public String get_tab_plus_alias() {
			return bibE2.cTO()+"."+this.table.n()+" "+this.praef;
		}
		
		
		
	}
	
	
	@Override
	public boolean build_View_forAll_Mandants() throws myException {
		
		String c_sql = "SELECT "
						+praefix.ATOM.get_field_block(true)
						+praefix.STATION_START.get_field_block(true)
						+praefix.STATION_ZIEL.get_field_block(true)
						+praefix.VEKTOR_POS.get_field_block(true)
						+praefix.VEKTOR.get_field_block(true)
						+praefix.BEWEGUNG.get_field_block(false)
						+"\n FROM  "+praefix.ATOM.get_tab_plus_alias()
						+"\n inner JOIN "+praefix.STATION_START.get_tab_plus_alias()+" ON (S.ID_BEWEGUNG_ATOM=A.ID_BEWEGUNG_ATOM AND S.MENGENVORZEICHEN=-1)"
						+"\n inner JOIN "+praefix.STATION_ZIEL.get_tab_plus_alias() +" ON (Z.ID_BEWEGUNG_ATOM=A.ID_BEWEGUNG_ATOM AND Z.MENGENVORZEICHEN=1)"
						+"\n INNER JOIN "+praefix.VEKTOR_POS.get_tab_plus_alias()   +" ON (VP.ID_BEWEGUNG_VEKTOR_POS=A.ID_BEWEGUNG_VEKTOR_POS)"
						+"\n INNER JOIN "+praefix.VEKTOR.get_tab_plus_alias()       +" ON (V.ID_BEWEGUNG_VEKTOR=VP.ID_BEWEGUNG_VEKTOR)"
						+"\n INNER JOIN "+praefix.BEWEGUNG.get_tab_plus_alias()     +" ON (B.ID_BEWEGUNG=V.ID_BEWEGUNG)"
						;
		
		DEBUG.System_println(c_sql,true);
		
		String csql_view = "CREATE OR REPLACE VIEW "+build_flat_atom.view_name+" AS "+c_sql;
		DEBUG.System_println(csql_view,true);
		
		boolean ok = bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(csql_view, true);
		
		if (ok) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(S.mt("Der View: "+build_flat_atom.view_name+" wurde erstellt !")));
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.mt("FEHLER: Der View: "+build_flat_atom.view_name+" wurde nicht erstellt !")));
		}
		
		return ok;
	}

}
