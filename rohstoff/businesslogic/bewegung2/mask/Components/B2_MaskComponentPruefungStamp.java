/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 03.04.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.BasicInterfaces.Service.PdServiceWritePruefProtokol;
import panter.gmbh.Echo2.AgentsAndValidators.IF_simpleValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_PruefstempelComponent;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.basics4project.EnPruefungTyp;
import panter.gmbh.basics4project.DB_ENUMS.BG_PRUEFPROT;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_MaskController;

/**
 * @author martin
 * @date 03.04.2019
 *
 */
public class B2_MaskComponentPruefungStamp extends RB_HL_PruefstempelComponent {

	private IF_Field           	m_field = null; 
	
	private RB_KM 				m_maskKey = null;
	
	private EnPruefungTyp     	m_pruefungTyp = null;
	
	
	public B2_MaskComponentPruefungStamp(RB_KM maskKey, IF_Field field, EnPruefungTyp m_pruefungTyp) {
		super();
		this.m_maskKey = maskKey;
		this.m_field = field;
		this.m_pruefungTyp = m_pruefungTyp;
		
		this.getTfWithTimestamp()._w(85);
		this.getTfWithUser()._w(20);
		
		this	._clear()._setSize(20,20,80)
				._w(120)
				._a(this.getCheckBox(), new RB_gld()._ins(0, 0, 1, 0))
				._a(this.getTfWithUser(), new RB_gld()._ins(0, 0, 1, 0))
				._a(this.getTfWithTimestamp(), new RB_gld()._ins(0, 0, 1, 0))
				;
	}


	
	
	@Override
	protected Rec21 createNewPruefRec21() {
		return new PdServiceWritePruefProtokol().getNewPruefprotRec( this.m_pruefungTyp, m_field._t(), this.getTableId());
	}

	@Override
	protected Rec21 readPruefRec21(Long id) {
		try {
			Rec21 r = new Rec21(_TAB.bg_pruefprot)._fill_id(id);
			return r;
		} catch (myException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected MyE2_MessageVector fillMaskComponents(Rec21 r) {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		try {
			this.getTfWithId().setText(r.getFs(BG_PRUEFPROT.id_bg_pruefprot));
			Date d = (Date)r.getRawVal(BG_PRUEFPROT.pruefung_am);
			String s_d = "";
			if (d!=null) {
				s_d = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(d);
			}
			this.getTfWithTimestamp().setText(s_d);
			this.getTfWithUser().setText(r.get_up_Rec21(USER.id_user)!=null?r.get_up_Rec21(USER.id_user).getUfs(USER.kuerzel):"-");
			this.getCheckBox().setSelected(true);
		} catch (myException e) {
			e.printStackTrace();
			mv._add(e.get_ErrorMessage());
		}
		return mv;
	}


	@Override
	protected _TAB getTable() {
		return _TAB.bg_atom;
	}

	
	
	@Override
	protected Long getTableId() {

		try {
			B2_MaskController controller = new B2_MaskController(this);
			return controller.getLongDBVal(m_maskKey, m_field);
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	//20180406: validierer als lambda zulassen
	public  B2_MaskComponentPruefungStamp _addValidator(IF_simpleValidator validator) {
		this.getCheckBox().add_GlobalValidator(validator.getValidator());
		return this;
	}

	//20180406: validierer als lambda zulassen
	public  B2_MaskComponentPruefungStamp _addValidator(XX_ActionValidator_NG validator) {
		this.getCheckBox().add_GlobalValidator(validator);
		return this;
	}
}
