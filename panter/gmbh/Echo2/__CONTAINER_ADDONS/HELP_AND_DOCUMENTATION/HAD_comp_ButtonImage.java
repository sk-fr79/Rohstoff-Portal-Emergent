package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class HAD_comp_ButtonImage extends MyE2_Button  implements IF_AR_Component {

	private RECORD_ARCHIVMEDIEN_ext  recArchiv = null;
	private GridLayoutData  gl = null;

	public HAD_comp_ButtonImage(GridLayoutData  p_gl, RECORD_ARCHIVMEDIEN_ext  p_recArchiv) {
		super();
		
		this.recArchiv = p_recArchiv;
		this.gl = p_gl;
		
		try {
			this.setIcon(this.recArchiv.get_image(800));
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(	new MyE2_String("Bild nicht gefunden: ",true,
														this.recArchiv.get__cCompletePathAndFileName(""),false)));
			e.printStackTrace();
		}
		
		this.setLayoutData(p_gl);
		
		this.add_oActionAgent(new ownActionDownload());
	}
	
	public void set_ImageMaxSize(int i_max_Width){
		try {
			this.setIcon(null);
			this.setIcon(this.recArchiv.get_image(i_max_Width));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public GridLayoutData get_layoutData() {
		return this.gl;
	}

	@Override
	public Component comp() {
		return this;
	}

	private class ownActionDownload extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			HAD_comp_ButtonImage.this.recArchiv.starte__downLoad();
		}
	}
	
}
