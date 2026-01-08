package panter.gmbh.Echo2.RB.COMP;

import javax.imageio.ImageReader;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_ArchivMedien;

public class RB_ButtonImage extends E2_Button  {

	private Rec21_ArchivMedien  		m_recArchivmedium = null;
	private int 						m_imageSize = 600;

	public RB_ButtonImage() {
		super();
		
		//platzhalter
		this._image(E2_ResourceIcon.get_RI("warnschild_16.png"))._al_center();
	}

	
	public RB_ButtonImage _render() throws myException {
		if (this.m_recArchivmedium==null) {
			throw new myException(this, "nothing to render !!");
		}

		try {
			ImageReference img = this.m_recArchivmedium.get_image(this.m_imageSize);
			if (img!=null) {
				this._image(this.m_recArchivmedium.get_image(this.m_imageSize));
				this.add_oActionAgent(new ownActionDownload());
			} else {
				this._image(E2_ResourceIcon.get_RI("warnschild_16.png"))._al_center();
				this._ttt(S.ms("Fehlendes Bild: "+this.m_recArchivmedium.getCompletePathAndFileName("")));
			}				
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(	new MyE2_String("Bild nicht gefunden: ",true,
														this.m_recArchivmedium.getCompletePathAndFileName(""),false)));
			e.printStackTrace();
			this._image(E2_ResourceIcon.get_RI("warnschild_16.png"))._al_center();
			this._ttt(S.ms("Fehlendes Bild: "+this.m_recArchivmedium.getCompletePathAndFileName("")));
		}		
		
		return this;
	}
	
	
	
	

	private class ownActionDownload extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_ButtonImage.this.m_recArchivmedium.starteDownLoad();
		}
	}



	public int getImageSize() {
		return m_imageSize;
	}

	public RB_ButtonImage _setImageSize(int imageSize) {
		this.m_imageSize = imageSize;
		return this;
	}




	public Rec21_ArchivMedien getRecArchivmedium() {
		return m_recArchivmedium;
	}




	public RB_ButtonImage _setRecArchivmedium(Rec21_ArchivMedien m_recArchivmedium) {
		this.m_recArchivmedium = m_recArchivmedium;
		return this;
	}
	
}
