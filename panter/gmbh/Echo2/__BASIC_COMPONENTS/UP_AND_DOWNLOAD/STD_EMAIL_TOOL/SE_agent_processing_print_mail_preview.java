package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import java.util.Vector;

import panter.gmbh.BasicInterfaces.IF_Executer2Parts;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public abstract class SE_agent_processing_print_mail_preview extends XX_ActionAgent {

	public abstract 	boolean 					is_multi_id_allowed();
	public abstract 	Vector<String>				get_ids_to_print() throws myException;
	public abstract     SE_JasperHASH_4_ArchivPDF	generate_jasperHash(String id) throws myException;
	

	private VEK<IF_Executer2Parts<Vector<String>, Vector<myTempFile>>> afterCreateAllPdfsListener = new VEK<IF_Executer2Parts<Vector<String>, Vector<myTempFile>>>();
	
	private myTempFile 	base = null;
	
	private processtype typ = null;
	
	public SE_agent_processing_print_mail_preview(processtype p_typ) {
		super();
		this.typ=p_typ;
	}

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
	
		MyE2_MessageVector  mv = new MyE2_MessageVector();

		Vector<String>  v_ids = this.get_ids_to_print();
			
		if (v_ids.size()!=1 && !this.is_multi_id_allowed()) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Druck kann nur mit genau einem Datensatz ausgeführt werden !")));
		} else {
			
			Vector<myTempFile>  v_temp_files = new Vector<>();
			
			String download_name = null;
			String download_name_multi = null;
			
			for (String id: v_ids) {
				
				SE_JasperHASH_4_ArchivPDF hash = this.generate_jasperHash(id);
			
				//beim ersten durchlauf den spaeteren downloadnamen erzeugen
				if (S.isEmpty(download_name)) {
					download_name = hash.generate_filename_4_archiv_without_ending();
					if (S.isEmpty(download_name)) {
						download_name = hash.get_table().baseTableName()+"_"+bibALL.get_cDateNOWInverse("-");
					}
				}
				
				SE_JasperFileProcessor fp= new SE_JasperFileProcessor(hash);
			
				switch (typ) {
				case PREVIEW:
					v_temp_files.add(fp.do_processing_print_or_preview(mv, true));
					break;

				case PRINT:
					v_temp_files.add(fp.do_processing_print_or_preview(mv, false));
					break;

				case MAIL:
					fp.do_processing_mail(mv);
					break;
					
				default:
					break;
				}
				
				if (mv.get_bHasAlarms()) {
					break;
				}
			}
			
			for (IF_Executer2Parts<Vector<String>, Vector<myTempFile>> executer: afterCreateAllPdfsListener) {
				executer.execute(v_ids,v_temp_files);
			}
			
			
			download_name_multi = download_name+"_weitere";
			download_name = download_name+".pdf";
			download_name_multi = download_name_multi+".pdf";
			
			if (this.typ==processtype.PREVIEW || this.typ==processtype.PRINT) {
				if (v_temp_files.size()==0) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Dateiliste zum Download ist leer !")));
				} else if (v_temp_files.size()==1) {
					this.base = v_temp_files.get(0);
					base.starteDownLoad(download_name, JasperFileDef.MIMETYP_PDF);
				} else {
					this.base = v_temp_files.get(0);
					v_temp_files.remove(base);
					base.append_pdf(v_temp_files);
					base.starteDownLoad(download_name_multi, JasperFileDef.MIMETYP_PDF);
				}
				
				this.base.set_bDeleteAtFinalize(true);
				for (myTempFile file: v_temp_files) {
					file.set_bDeleteAtFinalize(true);
				}
			}
			
			bibMSG.add_MESSAGE(mv);
		}

	}
	
	public processtype getTyp() {
		return typ;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 15.09.2020
	 *
	 * @param executer
	 * @return
	 */
	public SE_agent_processing_print_mail_preview _addAfterCreateAllPdfsListener(IF_Executer2Parts<Vector<String>, Vector<myTempFile>> executer) {
		this.afterCreateAllPdfsListener._a(executer);
		return this;
	}
}
