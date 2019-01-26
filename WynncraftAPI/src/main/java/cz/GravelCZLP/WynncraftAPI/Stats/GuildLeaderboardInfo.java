package cz.GravelCZLP.WynncraftAPI.Stats;

public class GuildLeaderboardInfo extends LeaderboardInfo{

	private String name;
	private String prefix;
	private int xp;
	private int level;
	private String banner;
	private int territories;
	private int membersCount;
	private int num;// Number on the leaderboard;

	public GuildLeaderboardInfo(String name, String prefix, int xp, int level, String banner, int territories,
			int membersCount, int num) {
		super();
		this.name = name;
		this.prefix = prefix;
		this.xp = xp;
		this.level = level;
		this.banner = banner;
		this.territories = territories;
		this.membersCount = membersCount;
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getXp() {
		return xp;
	}

	public int getLevel() {
		return level;
	}

	public String getBanner() {
		return banner;
	}

	public int getTerritories() {
		return territories;
	}

	public int getMembersCount() {
		return membersCount;
	}

	public int getNumerOnLeaderboard() {
		return num;
	}

}
