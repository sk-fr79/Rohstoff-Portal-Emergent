package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public class FUS_VectorStandardElement extends Vector<__FUS_STANDARD_Element>
{

	public FUS_VectorStandardElement()
	{
		super();
	}

	public FUS_VectorStandardElement(Collection<? extends __FUS_STANDARD_Element> c)
	{
		super(c);
	}
	

	/**
	 * 
	 * @return bei einem Vector, wo die komponenten paarweise auftreten, das zugehoerige EK-Element, wenn er eines hat
	 * @throws myException
	 */
	public __FUS_STANDARD_Element  get_EK_Element() throws myException
	{
		if (this.size()==2)
		{
			if (this.get(0).get_IS_EK())
			{
				return this.get(0);
			}
			else if (this.get(1).get_IS_EK())
			{
				return this.get(1);
			}
		}
		
		throw new myException(this,"Nothing found (EK)!!");
	}
	
	/**
	 * 
	 * @return bei einem Vector, wo die komponenten paarweise auftreten, das zugehoerige EK-Element, wenn er eines hat
	 * @throws myException
	 */
	public __FUS_STANDARD_Element  get_VK_Element() throws myException
	{
		if (this.size()==2)
		{
			if (!this.get(0).get_IS_EK())
			{
				return this.get(0);
			}
			else if (!this.get(1).get_IS_EK())
			{
				return this.get(1);
			}
		}
		
		throw new myException(this,"Nothing found (VK)!!");
	}
	
	

	

}
