package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.util.Vector;

public class E2_JasperTempFile_and_pipePos_VECT extends 		Vector<E2_JasperTempFile_and_pipePos> 
{

	public E2_JasperTempFile_and_pipePos_VECT() {
		super();
	}

//	public E2_JasperTempFile_and_pipePos_VECT(
//			Collection<? extends E2_JasperTempFile_and_pipePos> c) {
//		super(c);
//	}
//
//	public E2_JasperTempFile_and_pipePos_VECT(int initialCapacity,
//			int capacityIncrement) {
//		super(initialCapacity, capacityIncrement);
//	}
//
//	public E2_JasperTempFile_and_pipePos_VECT(int initialCapacity) {
//		super(initialCapacity);
//	}
	
	
	public E2_JasperTempFile_VECT  get_vJasperTempFile()
	{
		E2_JasperTempFile_VECT vRueck = new E2_JasperTempFile_VECT();
		
		for (E2_JasperTempFile_and_pipePos oPart: this)
		{
			vRueck.add(oPart.get_oJasperTempFile());
		}
		
		return vRueck;
	}
	
	
	
}
