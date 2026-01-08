package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class RB__Mask_Description {

	private RB_KF   Key = null;
	private MyE2_String Description = null;
	private MyE2_String Position = null;
	
	/**
	 * 
	 * @param key 
	 * @param description
	 * @param position (may be null)
	 * @throws myException 
	 */
	public RB__Mask_Description(RB_KF key, MyE2_String description, MyE2_String position) throws myException
	{
		super();
		if (key==null) {
			throw new myException("RB__MASK_DESCRIPTION: key MUST be set!");
		}
		if (description==null) {
			throw new myException("RB__MASK_DESCRIPTION: description without description has no sense!");
		}
		
		
		this.Key = 		key;
		this.Description = 	description;
		this.Position = 		position;
	}

	public MyE2_String get_description() {
		return Description;
	}

	public void set_description(MyE2_String description) {
		Description = description;
	}

	public MyE2_String get_position() {
		return Position;
	}

	public void set_position(MyE2_String position) {
		Position = position;
	}

	public RB_KF rb_KEY()
	{
		return this.Key;
	}
	
	public MyE2_String get_DescriptionAndPosition() {
		MyE2_String c_rueck = new MyE2_String(this.Description.CTrans(),false);
		if (this.Position != null && S.isFull(this.Position.CTrans())) {
			c_rueck.addString(new MyE2_String(" (",false));
			c_rueck.addString(this.Position);
			c_rueck.addString(new MyE2_String(")",false));
		}
		c_rueck.addUnTranslatedInFront("<");
		c_rueck.addUnTranslated(">");
		
		return c_rueck;
	}
	
}
