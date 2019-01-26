package cz.GravelCZLP.WynncraftAPI;

public class GuildStats {

	private boolean error;
	
	private String name;
	private String prefix;
	private String banner;
	private Member[] members;

	private double xp;
	private int level;
	private int territories;

	/**
	 * 
	 * @param error - if this object is an error, if true. name is the reason
	 * @param name - name if the guild
	 * @param prefix - guild prefix
	 * @param members - the members in a array
	 * @param xp - amount of xp
	 * @param level - guild level
	 * @param territories - amount of territories
	 */
	public GuildStats(boolean error, String name, String prefix, String banner, Member[] members, double xp, int level, int territories) {
		this.error = error;
		this.banner = banner;
		this.name = name;
		this.prefix = prefix;
		this.members = members;
		this.xp = xp;
		this.level = level;
		this.territories = territories;
	}

	public String getBanner() {
		return banner;
	}
	
	public boolean isError() {
		return error;
	}
	
	public String getName() {
		return name;
	}

	public String getPrefix() {
		return prefix;
	}

	public Member[] getMembers() {
		return members;
	}

	public double getXp() {
		return xp;
	}

	public int getLevel() {
		return level;
	}

	public int getTerritories() {
		return territories;
	}

	public static class Member {

		private String name;
		private String uuid;
		private String rank;

		public Member(String name, String uuid, String rank) {
			super();
			this.name = name;
			this.uuid = uuid;
			this.rank = rank;
		}

		public String getName() {
			return name;
		}

		public String getUuid() {
			return uuid;
		}

		public String getRank() {
			return rank;
		}
	}
}
