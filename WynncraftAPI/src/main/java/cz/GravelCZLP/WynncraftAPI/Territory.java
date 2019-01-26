package cz.GravelCZLP.WynncraftAPI;

public class Territory {

	private String name;
	private String guild;
	private String attacker;
	private Vector2D start, end;

	public Territory(String name, String guild, String attacker, Vector2D start, Vector2D end) {
		super();
		this.name = name;
		this.guild = guild;
		this.attacker = attacker;
		this.start = start;
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public String getGuild() {
		return guild;
	}

	public String getAttacker() {
		return attacker;
	}

	public Vector2D getStart() {
		return start;
	}

	public Vector2D getEnd() {
		return end;
	}

}
