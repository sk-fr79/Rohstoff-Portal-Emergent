package rohstoff.Echo2BusinessLogic.FIBU.SUCHE;

public class FibuSucheErgebnis {
	private String id;
	private boolean bVorgaengerResult;

	public FibuSucheErgebnis(String id, boolean bVorgaengerResult) {
		this.id = id;
		this.bVorgaengerResult = bVorgaengerResult;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isbVorgaengerResult() {
		return bVorgaengerResult;
	}
	public void setbVorgaengerResult(boolean bVorgaengerResult) {
		this.bVorgaengerResult = bVorgaengerResult;
	}
	
	
	
}