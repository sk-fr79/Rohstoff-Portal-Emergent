package panter.gmbh.Echo2.ActionEventTools;

public class ExecINFO_OnlyCode extends ExecINFO 
{

	/*
	 * wird benutzt, wenn die actionEvents nicht im zusammenhang mit einem
	 * doAction-aufruf ausgefuehrt werden (z.B. als zusatz-aktionen innerhalb eines
	 * anderen kontexts
	 */
	public ExecINFO_OnlyCode() 
	{
		super(null, true);
	}

	public ExecINFO_OnlyCode(MyActionEvent oActionEventFremd) 
	{
		super(oActionEventFremd, true);
	}

}
