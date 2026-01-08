package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.exceptions.myException;

public class AM_BasicContainer_SelectTitelInfo {

	private String table = 		null;
	private String table_id = 	null;
	
	private String titeltext = "";
	
	public AM_BasicContainer_SelectTitelInfo(String vorschlag, String p_table, String p_table_id) {
		super();
		this.table = 	p_table;
		this.table_id = p_table_id;
		
		this.table=new Archiver_Normalized_Tablename(this.table).get_TableBaseName().toUpperCase();
		
		
		//jetzt fallunterscheidung nach diversen tabellen
		
		try {
			if (S.isFull(vorschlag)) {
				this.titeltext = new MyE2_String( S.ut(vorschlag), S.ut(" / "), S.ut("ID: "),S.ut(this.table_id+" ")).CTrans();
			}else {
				this.titeltext = new MyE2_String( S.t("Tabelle: "), S.ut(this.table),S.ut(" / "), S.ut("ID: "),S.ut(this.table_id+" ")).CTrans();
			}
			
			if (this.table.equals(_DB.ARTIKEL.substring(3))) {
				RECORD_ARTIKEL rec = new RECORD_ARTIKEL(this.table_id);
				this.titeltext = this.titeltext+" / ";
				this.titeltext=this.titeltext+rec.get___KETTE(_DB.ARTIKEL$ANR1,_DB.ARTIKEL$ARTBEZ1);

			} else if (this.table.equals(_DB.MASCHINEN.substring(3))) {
				RECORD_MASCHINEN rec = new RECORD_MASCHINEN(this.table_id);
				this.titeltext = this.titeltext+" / ";
				this.titeltext=this.titeltext+rec.get___KETTE(_DB.MASCHINEN$BESCHREIBUNG,_DB.MASCHINEN$KFZKENNZEICHEN);

			} else if (this.table.equals(_DB.ADRESSE.substring(3))) {
				RECORD_ADRESSE rec = new RECORD_ADRESSE(this.table_id);
				this.titeltext = this.titeltext+" / ";
				this.titeltext=this.titeltext+rec.get___KETTE(_DB.ADRESSE$NAME1,_DB.ADRESSE$NAME2,_DB.ADRESSE$ORT);
			
			} else if (this.table.equals(_DB.FBAM.substring(3))) {
				
				RECORD_FBAM rec = new RECORD_FBAM(this.table_id);
				if (S.isFull(rec.get_ID_VPOS_TPA_FUHRE_ORT_cUF())) {
					RECORD_VPOS_TPA_FUHRE_ORT  recFO = rec.get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort();
					if (recFO.get_DEF_QUELLE_ZIEL_cF_NN("").equals("EK")) {
						this.titeltext = this.titeltext+" / ";
						this.titeltext=this.titeltext+new MyE2_String("Fuhrenzusatzort: BEANSTANDUNG der Lieferung von ").CTrans()+ 
								recFO.get___KETTE(_DB.VPOS_TPA_FUHRE_ORT$NAME1,_DB.VPOS_TPA_FUHRE_ORT$NAME2, _DB.VPOS_TPA_FUHRE_ORT$ORT );
					} else {
						this.titeltext = this.titeltext+" / ";
						this.titeltext=this.titeltext+new MyE2_String("Fuhrenzusatzort: BEANSTANDUNG der Lieferung an ").CTrans()+ 
								recFO.get___KETTE(_DB.VPOS_TPA_FUHRE_ORT$NAME1,_DB.VPOS_TPA_FUHRE_ORT$NAME2, _DB.VPOS_TPA_FUHRE_ORT$ORT );
					}
					
				} else if (S.isFull(rec.get_ID_VPOS_TPA_FUHRE_cUF())) {
					RECORD_VPOS_TPA_FUHRE  recF = rec.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
					this.titeltext = this.titeltext+" / ";

					this.titeltext=this.titeltext+new MyE2_String("BEANSTANDUNG der Fuhre von ").CTrans()+ 
							recF.get___KETTE(_DB.VPOS_TPA_FUHRE$L_NAME1,_DB.VPOS_TPA_FUHRE$L_NAME2, _DB.VPOS_TPA_FUHRE$L_ORT );

					this.titeltext=this.titeltext+new MyE2_String(" nach ").CTrans()+ 
							recF.get___KETTE(_DB.VPOS_TPA_FUHRE$A_NAME1,_DB.VPOS_TPA_FUHRE$A_NAME2, _DB.VPOS_TPA_FUHRE$A_ORT );
				}

			} else if (this.table.equals(_DB.VPOS_TPA_FUHRE.substring(3))) {
				RECORD_VPOS_TPA_FUHRE rec = new RECORD_VPOS_TPA_FUHRE(this.table_id);
				this.titeltext = this.titeltext+" / ";
				this.titeltext=this.titeltext+new MyE2_String("Fuhre von ").CTrans()+ 
						rec.get___KETTE(_DB.VPOS_TPA_FUHRE$L_NAME1,_DB.VPOS_TPA_FUHRE$L_NAME2, _DB.VPOS_TPA_FUHRE$L_ORT );

				this.titeltext=this.titeltext+new MyE2_String(" nach ").CTrans()+ 
						rec.get___KETTE(_DB.VPOS_TPA_FUHRE$A_NAME1,_DB.VPOS_TPA_FUHRE$A_NAME2, _DB.VPOS_TPA_FUHRE$A_ORT );
				
				
			} else if (this.table.equals(_DB.VPOS_TPA_FUHRE_ORT.substring(3))) {
				RECORD_VPOS_TPA_FUHRE_ORT recFO = new RECORD_VPOS_TPA_FUHRE_ORT(this.table_id);
				if (recFO.get_DEF_QUELLE_ZIEL_cF_NN("").equals("EK")) {
					this.titeltext = this.titeltext+" / ";
					this.titeltext=this.titeltext+new MyE2_String("Fuhrenzusatzort: Lieferung von ").CTrans()+ 
							recFO.get___KETTE(_DB.VPOS_TPA_FUHRE_ORT$NAME1,_DB.VPOS_TPA_FUHRE_ORT$NAME2, _DB.VPOS_TPA_FUHRE_ORT$ORT );
				} else {
					this.titeltext = this.titeltext+" / ";
					this.titeltext=this.titeltext+new MyE2_String("Fuhrenzusatzort: Lieferung an ").CTrans()+ 
							recFO.get___KETTE(_DB.VPOS_TPA_FUHRE_ORT$NAME1,_DB.VPOS_TPA_FUHRE_ORT$NAME2, _DB.VPOS_TPA_FUHRE_ORT$ORT );
				}
			
			} else if (this.table.equals(_DB.VKOPF_RG.substring(3))) {
				
				RECORD_VKOPF_RG rec = new RECORD_VKOPF_RG(this.table_id);
				this.titeltext = this.titeltext+" / ";
				this.titeltext=this.titeltext+rec.get___KETTE(_DB.VKOPF_RG$NAME1,_DB.VKOPF_RG$NAME2,_DB.VKOPF_RG$ORT);
				
			}
		} catch (myException e) {
			e.printStackTrace();
		}
	}

	
	
	public String get_Titeltext() {
		return titeltext;
	}
	

	
	
}
