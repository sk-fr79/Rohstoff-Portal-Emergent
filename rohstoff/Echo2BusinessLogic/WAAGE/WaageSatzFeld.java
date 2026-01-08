package rohstoff.Echo2BusinessLogic.WAAGE;

/**
 * Klasse bildet ein Feld des Waagesatzes ab, z.b. ("Ident-Nummer",18,4)
 * @author manfred
 *
 */
public class WaageSatzFeld {

	private String 	name;
	private int 	offset;
	private int     length;
	
	
	public WaageSatzFeld(String name, int offset, int length) {
		super();
		this.name = name;
		this.offset = offset;
		this.length = length;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int lenght) {
		this.length = lenght;
	}
	
	
	
}
