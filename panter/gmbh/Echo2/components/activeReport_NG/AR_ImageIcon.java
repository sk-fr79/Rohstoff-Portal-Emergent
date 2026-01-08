package panter.gmbh.Echo2.components.activeReport_NG;

import java.io.File;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.ImageLoader;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;
import echopointng.ImageIcon;

public class AR_ImageIcon extends ImageIcon implements IF_AR_Component {

	private RECORD_ARCHIVMEDIEN_ext 	recArch = null;
	private GridLayoutData 				gl = null;

	
	public AR_ImageIcon(RECORD_ARCHIVMEDIEN p_recArchivMedien, GridLayoutData layoutData) throws myException {
		super();
		this.gl = layoutData;

		this._init(p_recArchivMedien);
	}




	
	private void _init(RECORD_ARCHIVMEDIEN p_recArchivMedien)  throws myException {
		if (p_recArchivMedien != null) {
			this.recArch = new RECORD_ARCHIVMEDIEN_ext(p_recArchivMedien);

			if (this.recArch.get_UP_RECORD_MEDIENTYP_id_medientyp()!= null && this.recArch.get_UP_RECORD_MEDIENTYP_id_medientyp().is_IST_PIXELIMAGE_YES()) {
				if (new File(this.recArch.get__cCompletePathAndFileName()).exists()) {
					try {
						this.setIcon(new ImageLoader(this.recArch.get__cCompletePathAndFileName()) );
					} catch (Exception e) {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Laden des Bildes ",true,this.recArch.get__cCompletePathAndFileName(), false)));
					}
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Datei fehlt: ",true,this.recArch.get__cCompletePathAndFileName(), false)));
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Datei ist kein bild: ",true,this.recArch.get__cCompletePathAndFileName(), false)));				
			}
		} else {
			throw new myException(this,"Empty RECORD_ARCHIVMEDIEN is not allowd in constructor!");
		}
		
		
	}
	
	
	public AR_ImageIcon setWidth(int iWidthMaximum) {
		this.setWidth(new Extent(iWidthMaximum));
		return this;
	}
	
	
	@Override
	public GridLayoutData get_layoutData() {
		return this.gl;
	}

	@Override
	public Component comp() {
		return this;
	}
	

	
}
