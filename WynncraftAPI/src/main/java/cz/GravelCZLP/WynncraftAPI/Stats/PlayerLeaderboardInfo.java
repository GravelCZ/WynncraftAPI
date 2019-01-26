package cz.GravelCZLP.WynncraftAPI.Stats;

public class PlayerLeaderboardInfo extends LeaderboardInfo {

	private String name;
	private String uuid;
	private int kills;
	private int level;
	private int xp;
	private int minPlayed;
	private String tag;
	private String rank;
	private boolean displayTag;
	private boolean veteran;
	private int num;// number on the leaderboard
	private String guildTag;
	private String guildName;

	public PlayerLeaderboardInfo(String name, String uuid, int kills, int level, int xp, int minPlayed, String tag,
			String rank, boolean displayTag, boolean veteran, int num, String guildTag, String guildName) {
		super();
		this.name = name;
		this.uuid = uuid;
		this.kills = kills;
		this.level = level;
		this.xp = xp;
		this.minPlayed = minPlayed;
		this.tag = tag;
		this.rank = rank;
		this.displayTag = displayTag;
		this.veteran = veteran;
		this.num = num;
		this.guildTag = guildTag;
		this.guildName = guildName;
	}

	public String getName() {
		return name;
	}

	public String getUuid() {
		return uuid;
	}

	public int getKills() {
		return kills;
	}

	public int getLevel() {
		return level;
	}

	public int getXp() {
		return xp;
	}

	public int getMinPlayed() {
		return minPlayed;
	}

	public String getTag() {
		return tag;
	}

	public String getRank() {
		return rank;
	}

	public boolean isDisplayTag() {
		return displayTag;
	}

	public boolean isVeteran() {
		return veteran;
	}

	public int getNumerOnLeaderboard() {
		return num;
	}

	public String getGuildTag() {
		return guildTag;
	}

	public String getGuildName() {
		return guildName;
	}

}
