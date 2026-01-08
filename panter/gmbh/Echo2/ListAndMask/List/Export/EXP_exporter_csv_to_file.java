package panter.gmbh.Echo2.ListAndMask.List.Export;

import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.myTempFileAutoDel;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class EXP_exporter_csv_to_file {

	private E2_NavigationList 			Navigationlist = null;
	private myTempFileAutoDel 			TempFile = null;

	private _TAB  						BaseTable = null;
	
	//hier ist fuer jede spalte eine checkbox, wenn angehakt, dann export
	private Vector<EXP_Field>     		V_cb_fields_2_export = new Vector<>();

	private Vector<IF_Field>            V_exclude_fields= new Vector<>();
	
	/**
	 * 
	 * @param navigationlist
	 * @param tempFile
	 * @param baseTable
	 * @param v_append_recs
	 * @param v_cb_fields_2_export
	 */
	public EXP_exporter_csv_to_file(	E2_NavigationList navigationlist, 
										myTempFileAutoDel tempFile, 
										_TAB baseTable,
										Vector<EXP_Field> v_cb_fields_2_export) {
		super();
		Navigationlist = navigationlist;
		TempFile = tempFile;
		BaseTable = baseTable;
		V_cb_fields_2_export = v_cb_fields_2_export;
	}


	

	public void do_export() throws myException {
		
		try	{
			
			String 			name_download = this.BaseTable.baseTableName()+"-export-csv.csv";
			StringBuffer  	complete_export = new StringBuffer();

			Vector<String> v_ids = new Vector<>();
			v_ids.addAll(this.Navigationlist.get_vectorSegmentation());
			

			boolean 		b_titel = true;
			StringBuffer 	titel_line = new StringBuffer();
			
			for (String id: v_ids) {
				
				MyRECORD rec = 		this.BaseTable.get_RECORD(id);
				
				StringBuffer c_exp_line = new StringBuffer();
				
				boolean b_first = true;
				
				for (EXP_Field field: this.V_cb_fields_2_export) {
					MyRECORD rec_use = 	null;
					if (field.addon==null) {
						rec_use = rec;
					} else {
						rec_use = field.addon.generate_instance_of_append_class(rec);
					}
					c_exp_line.append((b_first?"":";")+new EXP_genCSV_String(rec_use, field.field).csv());
					if (b_titel) {
						titel_line.append((b_first?"":";")+"\""+field.get_save_key()+"\"");
					}
					b_first = false;
				}
				
				c_exp_line.append("\n");
				if (b_titel) {
					titel_line.append("\n");
					complete_export.append(titel_line);
				}
				complete_export.append(c_exp_line);
				b_titel = false;
			}

			try {
				FileUtils.writeStringToFile(this.TempFile.getFile(),complete_export.toString());
				this.TempFile.starteDownLoad(name_download, "application/txt");
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Export erfolgreich !").CTrans()));
			} catch (IOException e) {
				e.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Export-Fehler:").CTrans()+" ... "+e.getMessage()));
			}
			
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}

	}




	/**
	 * hier felder reinschreiben, die nicht in der liste erscheinen duerfen (z.B. passwort)
	 * @return
	 */
	public Vector<IF_Field> get_v_exclude_fields() {
		return V_exclude_fields;
	}


}
