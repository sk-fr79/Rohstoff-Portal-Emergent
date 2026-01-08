package panter.gmbh.Echo2.ActionEventTools.Break4Popup;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;

/**
 * klasse, die die ausfuhrung beliebiger button-actions unterbricht und ein popup anzeigt, das mit ok verlassen werden kann oder abgebrochen werden kann
 * @author martin
 *
 */
public class E2_Break4PopupController {
	
	
	private Vector<E2_Break4Popup>   			vBreaks = new Vector<>();
	
	private MyE2_MessageVector          		mv = new MyE2_MessageVector();   //sammelt die Messages innerhalb der unterbrechung
	
	private ActionEvent    						eventWhichWasBroken = null;
	
	private int 								popupCounter = 0;
	
	private int   								topPosOfFistPopup = 0;
	private int   								leftPosOfFistPopup = 0;
	
	
	//20180403: fuer jeden popup einen counter, der die information transportiert, wie oft ein popup in der schleife aufgerufen wurde
	private HashMap<E2_Break4Popup, Integer>    hmCounter = new HashMap<E2_Break4Popup, Integer>();
	
	public E2_Break4PopupController() {
		super();
	}

	
	public E2_Break4PopupController _registerBreak(E2_Break4Popup  break4popup) {
		this.vBreaks.add(break4popup);
		break4popup.setBreak4PopupController(this);
		return this;
	}

	public E2_Break4PopupController _clearBreaks() {
		this.vBreaks.clear();
		return this;
	}
	
	
	/**
	 * 2018-01-16: martin
	 * methode kann ueberschrieben werden (kann benutzt werden, um meldungen im moment der ausfuehrung zu erzeugen)
	 * @return
	 * @throws myException
	 */
	public E2_Break4PopupController  _initAtExecution(ActionEvent  p_eventWhichWasBroken) throws myException {
		this.getHmCounter().clear();
		for (E2_Break4Popup br: this.vBreaks  ) {
			this.getHmCounter().put(br, new Integer(0));
		}
		return this;
	}
	
	
	//dieser aufruf prueft alle breaks auf ihren status (notwendig oder nicht) 
	public void executeChecks(ActionEvent  p_eventWhichWasBroken) throws myException {
		this.eventWhichWasBroken = p_eventWhichWasBroken;
				
		for (E2_Break4Popup b4p: this.vBreaks) {
			b4p.executeCheck(this.mv);                        //alle checks durchlaufen
		}
	}

	//dieser aufruf prueft alle breaks auf ihren status (notwendig oder nicht) 
	public void popupsIfRelevant() throws myException {
		this.resetPopupCounter();
		for (E2_Break4Popup b4p: this.vBreaks) {
			b4p.popupIfRelevant();                        //alle checks durchlaufen
			
//			//hier den popup-counter der hashmap hochzaehlen, wenn der popup nochmals gecheckt wird (z.B. im Bestätigungsbutton), ist der zaehler >0 
//			this.hmCounter.put(b4p,this.hmCounter.get(b4p).intValue()+1);
		}
	}

	// alle anhaengigen fenster schliessen
	public void closeAllPopups() throws myException {
		for (E2_Break4Popup break4popup: this.getvBreaks()) {
			if (break4popup.getPopupContainer()!=null && break4popup.getPopupContainer().IS_Popup()) {
				break4popup.getPopupContainer().CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}
	}


	
	//fragt nach, ob mindestens einer der breaks notwendig ist
	public boolean hasRelevantBreak() throws myException {
		boolean hasRelevantBreak = false;
		for (E2_Break4Popup b4p: this.vBreaks) {
			if (b4p.isRelevant4Break()) {
				hasRelevantBreak = true;
				break;
			}
		}
		return hasRelevantBreak;
	}
	

	
	
	public MyE2_MessageVector getMv() {
		return mv;
	}
	
	

	public ActionEvent getEventWhichWasBroken() {
		return this.eventWhichWasBroken;
	}

	public void setEventWhichWasBroken(ActionEvent p_eventWhichWasBroken) {
		this.eventWhichWasBroken = p_eventWhichWasBroken;
	}
	public Vector<E2_Break4Popup> getvBreaks() {
		return vBreaks;
	}


	public int getPopupCounter() {
		return popupCounter;
	}
	
	public void increasePopupCounter() {
		this.popupCounter++;
	}
	
	public void resetPopupCounter() {
		this.popupCounter=0;
	}


	public int getTopPosOfFistPopup() {
		return topPosOfFistPopup;
	}


	public int getLeftPosOfFistPopup() {
		return leftPosOfFistPopup;
	}


	public void setTopPosOfFistPopup(int topPosOfFistPopup) {
		this.topPosOfFistPopup = topPosOfFistPopup;
	}


	public void setLeftPosOfFistPopup(int leftPosOfFistPopup) {
		this.leftPosOfFistPopup = leftPosOfFistPopup;
	}


	public HashMap<E2_Break4Popup, Integer> getHmCounter() {
		return hmCounter;
	}
	
}
