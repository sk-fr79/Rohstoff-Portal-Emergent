package panter.gmbh.indep;



public class myTempFileAutoDel extends myTempFile 
{

	public myTempFileAutoDel(String pNamensKern, String pEndung, boolean bDeleteOnExit) 
	{
		super(pNamensKern, pEndung, bDeleteOnExit);
		this.set_bDeleteAtFinalize(true);
	}
	
	
    protected void finalize() throws Throwable
    {
//    	System.out.println("myTempFileAutoDel.finalize()");
    	super.finalize();
    }
	

}
