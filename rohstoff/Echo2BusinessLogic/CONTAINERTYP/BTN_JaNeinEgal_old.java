/**
 * rohstoff.Echo2BusinessLogic.CONTAINERTYP
 * @author manfred
 * @date 08.12.2017
 * 
 */
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;

import java.util.HashMap;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;

/**
 * Button der den Zustand immer von Ja->Nein->Egal->Ja... durchrotiert und den Zustand intern ablegt.
 * 
 * @author manfred
 * @date 08.12.2017
 *
 */
public class BTN_JaNeinEgal_old extends E2_Button {

	enum ENUM_BTN_JA_NEIN_EGAL {
		JA(0,"Ja",Color.GREEN),
		NEIN(1,"Nein",Color.RED),
		EGAL(2,"?",new E2_ColorBase())
		;
		

		private int _Index;
		private String _TextDefault;
		private Color  _BGColDefault;
		
		private ENUM_BTN_JA_NEIN_EGAL(int Index,String Default, Color colDefault){
			this._Index = Index;
			this._TextDefault = Default;
			this._BGColDefault = colDefault;
		}
		
		
		public int getIndex(){
			return _Index;
		}
		
		public String getDefault(){
			return _TextDefault;
		}
	}
	
	private HashMap<ENUM_BTN_JA_NEIN_EGAL,String> alCaption = new HashMap<>();
	
	
	
	private ENUM_BTN_JA_NEIN_EGAL      _current = ENUM_BTN_JA_NEIN_EGAL.JA;
	
	/**
	 * @author manfred
	 * @date 08.12.2017
	 *
	 */
	public BTN_JaNeinEgal_old() {
		super();
		
			this._bordBlack()
			._i(E2_INSETS.I_2_0_2_0)
			._width(50)
			;
			
			this._align_center();
			this.setWidth(new Extent(30));
			
			;
		
		this.setZustand(ENUM_BTN_JA_NEIN_EGAL.JA);
		this.add_oActionAgent(new actionButtonBase(),true);
	}

	
	/**
	 * setzt den Knopftext auf den des im Zustand hinterlegten
	 * @author manfred
	 * @date 08.12.2017
	 *
	 * @param zustand
	 */
	public BTN_JaNeinEgal_old setZustand(ENUM_BTN_JA_NEIN_EGAL zustand){
		this._current = zustand;
		String caption = zustand.getDefault();
		
		// falls es explizite Texte gibt...
		if (alCaption.containsKey(zustand)){
			caption =alCaption.get(zustand);
		}
		
		this._bc(zustand._BGColDefault);
		this._t(caption);
		return this;
	}
	
	
	
	
	
	private class actionButtonBase extends XX_ActionAgent{

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// switch zustand 0->1->2->0....
			switch (_current._Index) {
			case 0:
				setZustand(ENUM_BTN_JA_NEIN_EGAL.NEIN);
				break;
			case 1:
				setZustand(ENUM_BTN_JA_NEIN_EGAL.EGAL);
				break;
			case 2:
				setZustand(ENUM_BTN_JA_NEIN_EGAL.JA);
				break;
			default:
				setZustand(ENUM_BTN_JA_NEIN_EGAL.JA);
				break;
			}
			
			
		}
		
	}
	
	/**
	 * Setzen der einzelnen Button-ZUstands-Texte
	 * @author manfred
	 * @date 08.12.2017
	 *
	 * @param zustand
	 * @param Caption
	 * @return
	 */
	public BTN_JaNeinEgal_old setCaption(ENUM_BTN_JA_NEIN_EGAL zustand, String Caption){
		alCaption.put(zustand, Caption);
		return this;
	}
	
//	public BTN_JaNeinEgal setImage(ENUM_ZUSTAND zustand){
//		
//		return this;
//	}
	

	/**
	 * gibt den aktuellen Zustand des Buttons zurück
	 * @author manfred
	 * @date 08.12.2017
	 *
	 * @return
	 */
	public ENUM_BTN_JA_NEIN_EGAL getZustand(){
		return _current;
	}
	
	
	
}
