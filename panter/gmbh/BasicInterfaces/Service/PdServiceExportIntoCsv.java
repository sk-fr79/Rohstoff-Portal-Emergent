/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_genCSV_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.myTempFileAutoDel;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
@Component
public class PdServiceExportIntoCsv {

	private SEL				selIdQuery = null;
	private VEK<String>		v_ids = new VEK<>();
	private StringBuffer  	complete_export =null;

	private E2_Grid         gridForAnzeige = new E2_Grid();
	private boolean 		exportWithTitleLine;
	private _TAB 			table;
	private MyE2_MessageVector mv;
	private myTempFileAutoDel tempFile;
	
	
	/*
	 * interface zum erzeugen der id-auswahl
	 */
	public static interface genIds {
		public Vector<String> ids(_TAB table, MyE2_MessageVector mv) throws myException;
	}
	
	/**
	 * 
	 * @param p_table
	 * @param generator  (if null, all records are exported)
	 * @param p_exportWithTitleLine
	 * @param p_tempFile
	 * @param p_mv
	 * @return
	 * @throws myException
	 */
	public myTempFileAutoDel export(_TAB p_table, genIds generator, boolean p_exportWithTitleLine, myTempFileAutoDel p_tempFile, MyE2_MessageVector p_mv) throws myException {

		this.table = p_table;
		this.exportWithTitleLine = p_exportWithTitleLine;
		this.tempFile = p_tempFile;
		this.mv = p_mv;
		this.v_ids.clear();
		this.complete_export = new StringBuffer();
		
		if (generator==null) {
			this.selIdQuery = new SEL(p_table.key()).FROM(p_table).ORDERUP(p_table.key());
			String[][] a_id = bibDB.EinzelAbfrageInArray(this.selIdQuery.s());
			v_ids._addVektor(  ()->{ 	Vector<String> v = new Vector<String>(); 
										for (int i=0;i<a_id.length;i++) {
											v.add(a_id[i][0]);	
										}; 
										return v;
									});
			if (a_id==null || a_id.length==0) {
				p_mv.add_MESSAGE(new MyE2_Alarm_Message("Error Quering IDs"));
				return null;
			}
		} else {
			v_ids.addAll(generator.ids(this.table,p_mv));
		}
		
		if (this.v_ids.size()<=1000) {
			this.doExport(null,null);
		} else {
			new E2_ServerPushMessageContainer_STD(new Extent(400), new Extent(250), new MyE2_String("Geocodierung der Adressen"),false,true,2000,this.gridForAnzeige,E2_INSETS.I(10,10,10,10)) {

				@Override
				public void Run_Loop() throws myException {
					PdServiceExportIntoCsv.this.doExport(this,PdServiceExportIntoCsv.this.gridForAnzeige);
				}

				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
				}

			};
		}
		
		return tempFile;
	}

	
	
	
	private void doExport(E2_ServerPushMessageContainer_STD pushPopup, E2_Grid anzeige) throws myException {
		boolean firstLine = true;
		int counter =0;
		for (String id: v_ids) {
			StringBuffer 	titel_line = 	new StringBuffer();
			
			MyRECORD 		rec = 			this.table.get_RECORD(id);
			
			if (firstLine && exportWithTitleLine) {
				boolean firstCol = true;
				for (IF_Field field: this.table.get_fields()) {
					titel_line.append((firstCol?"":";")+"\""+field.fieldName()+"\"");
					firstCol = false;
				}
				titel_line.append("\n");
				this.complete_export.append(titel_line);
			}
		
			StringBuffer 	c_exp_line = 	new StringBuffer();
			boolean firstCol = true;
			for (IF_Field field: this.table.get_fields()) {
				c_exp_line.append((firstCol?"":";")+new EXP_genCSV_String(rec, field).csv());
				firstCol = false;
			}
			c_exp_line.append("\n");
			this.complete_export.append(c_exp_line);
			firstLine = false;
			counter++;
			if (anzeige!=null && counter%100==0) {
				anzeige._clear();
				anzeige._setSize(100,50,100)._a(""+counter,new RB_gld()._ins(2)._center_mid())
											._a("von",new RB_gld()._ins(2)._center_mid())
											._a(""+v_ids.size(),new RB_gld()._ins(2)._center_mid())
											;
			}
			
			if (pushPopup!=null && pushPopup.get_bIsInterupted()) {
				break;
			}
		}
		if (anzeige!=null) {
			anzeige._clear();
			anzeige._setSize(100,50,100)._a(""+counter,new RB_gld()._ins(2)._center_mid())
										._a("von",new RB_gld()._ins(2)._center_mid())
										._a(""+v_ids.size(),new RB_gld()._ins(2)._center_mid())
										;
		}

		try {
			boolean localtemp=false;
			if (tempFile==null) {
				//dann ein tempfile anlegen
				tempFile = new myTempFileAutoDel(this.table.baseTableName()+"_EXP", ".csv", true);
				localtemp=true;
			}
			FileUtils.writeStringToFile(tempFile.getFile(),complete_export.toString());
			tempFile.starteDownLoad(this.table.baseTableName()+"_EXP.csv", "application/csv");
			mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Export erfolgreich !").CTrans()));
			
//			DEBUG._print("Start Wartezeit ...");
			if (localtemp) {
				// dann einpaar sekunden warten, sonst koennte der garbade-collector die datei vor ende
				// des downloads loeschen
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			DEBUG._print("Ende Wartezeit ...");
			
		} catch (IOException e) {
			e.printStackTrace();
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Export-Fehler:").CTrans()+" ... "+e.getMessage()));
		}
	}
	
}
