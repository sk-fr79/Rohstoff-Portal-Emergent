package panter.gmbh.Echo2.components;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

/**
 * 
 * @author martin
 *
 * @param <T>  definiert eine reference, die den button klassifiziert, wie z.b. ein Rec20-object oder aehnliches
 */
public class E2_ButtonMarker<T> extends E2_Button   implements MyE2IF__Component, E2_IF_Copy {

	private 	boolean 			isMarked = false;   // bedeutet der marker wurde gesetzt
	
	private		E2_ResourceIcon 	oIconLabelMarked = E2_ResourceIcon.get_RI("listlabel_mark.png");
	private		E2_ResourceIcon 	oIconLabelUNMarked = E2_ResourceIcon.get_RI("listlabel_trans.png");
	
	private     VEK<E2_ButtonMarker<T>>    v_collection_i_belong_to = null;
	
	private     T 					reference = null;
	
	public E2_ButtonMarker(VEK<E2_ButtonMarker<T>>  collection_i_belong_to) throws myException {
		super();
		
		this.v_collection_i_belong_to = collection_i_belong_to;
		if (this.v_collection_i_belong_to==null) {
			throw new myException(this,"Collection must NOT be null !");
		}
		
		this.v_collection_i_belong_to._a(this);
		
		this.add_oActionAgent(new XX_ActionAgent()	{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException		{
				E2_ButtonMarker<T> oThis = E2_ButtonMarker.this;
				oThis._unMarkAll(oThis);
				
				if (oThis.isMarked) {
					oThis._unMark();
				} else {
					oThis._mark();
				}
			}
		
		});

		
		this._define();
		
		this.setFocusTraversalParticipant(false);
	}

	
	public E2_ButtonMarker<T>  _unMarkAll(E2_ButtonMarker<T> not_unmark) {
		for (E2_ButtonMarker<T> b: this.v_collection_i_belong_to) {
			if (b==null || b!=not_unmark) {
				b._unMark();
			}
		}
		return this;
	}
	
	
	public E2_ButtonMarker<T> _mark() {
		this.isMarked = true;
		this._define();
		return this;
	}
	
	public E2_ButtonMarker<T> _unMark() {
		this.isMarked = false;
		this._define();
		return this;
	}
	
	
	public E2_ButtonMarker<T> _define() {
		if (isMarked)	{
			this.__setImages(this.oIconLabelMarked,this.oIconLabelMarked);
		} else {
			this.__setImages(this.oIconLabelUNMarked,this.oIconLabelUNMarked);
		}
		return this;
	}
	
	

	/*
	 * diese funktion wird hier auser gefecht gesetzt 
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy	{
		try {
			return new E2_ButtonMarker<T>(this.v_collection_i_belong_to);
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}

	
	public boolean get_bIsMarked()												{		return isMarked;	}

	
	public E2_ResourceIcon get_oIconLabelMarked()								{		return oIconLabelMarked;	}
	public void set_oIconLabelMarked(E2_ResourceIcon iconLabelMarked)			{		oIconLabelMarked = iconLabelMarked;	}
	
	public E2_ResourceIcon get_oIconLabelUNMarked()							{		return oIconLabelUNMarked;	}
	public void set_oIconLabelUNMarked(E2_ResourceIcon iconLabelUNMarked)		{		oIconLabelUNMarked = iconLabelUNMarked;	}


	public T get_reference() {
		return reference;
	}

	public E2_ButtonMarker<T>  _set_reference(T ref) {
		this.reference=ref;
		return this;
	}
	
	
}
