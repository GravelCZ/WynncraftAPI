package cz.GravelCZLP.WynncraftAPI;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerStats {

	private boolean isError;

	private String username;
	private String uuid;
	private String rank;
	private String tag;
	private boolean displayTag;
	private boolean veteran;
	private int playtime;
	private String currentServer;

	private GlobalStats globalPlayerStats;

	private ArrayList<ClassStats> playerClassStats;

	private HashMap<String, Integer> wizardFortress;

	private HashMap<String, Integer> rankings;
	private Guild guild;

	public PlayerStats(boolean isError, String username, String uuid, String rank, String tag, boolean displayTag,
			boolean veteran, int playtime, String currentServer, GlobalStats globalPlayerStats,
			ArrayList<ClassStats> playerClassStats, HashMap<String, Integer> wizardFortress,
			HashMap<String, Integer> rankings, Guild guild) {
		this.isError = isError;
		this.username = username;
		this.uuid = uuid;
		this.rank = rank;
		this.tag = tag;
		this.displayTag = displayTag;
		this.veteran = veteran;
		this.playtime = playtime;
		this.currentServer = currentServer;
		this.globalPlayerStats = globalPlayerStats;
		this.playerClassStats = playerClassStats;
		this.wizardFortress = wizardFortress;
		this.rankings = rankings;
		this.guild = guild;
	}

	public boolean isError() {
		return isError;
	}

	public String getUsername() {
		return username;
	}

	public String getUuid() {
		return uuid;
	}

	public String getRank() {
		return rank;
	}

	public String getTag() {
		return tag;
	}

	public boolean isDisplayTag() {
		return displayTag;
	}

	public boolean isVeteran() {
		return veteran;
	}

	public int getPlaytime() {
		return playtime;
	}

	public String getCurrentServer() {
		return currentServer;
	}

	public GlobalStats getGlobalPlayerStats() {
		return globalPlayerStats;
	}

	public ArrayList<ClassStats> getPlayerClassStats() {
		return playerClassStats;
	}

	public HashMap<String, Integer> getWizardFortress() {
		return wizardFortress;
	}

	public HashMap<String, Integer> getRankings() {
		return rankings;
	}

	public Guild getGuild() {
		return guild;
	}

	public static class Guild {
		private String name;
		private String rank;

		public Guild(String name, String rank) {
			super();
			this.name = name;
			this.rank = rank;
		}

		public String getName() {
			return name;
		}

		public String getRank() {
			return rank;
		}

	}

	public static class ClassStats {

		private String name;
		private double xp;
		private HashMap<String, Integer> dungeons;
		private ArrayList<String> quests;
		private String dungeonsAmount; // in json as String "x / y"
		private String questsAmount; // in json as String "x / y"
		private HashMap<String, Integer> skills;
		private int itemsIdentified;
		private int mobsKilled;
		private int pvpKills;
		private int pvpDeaths;
		private int chestsFound;
		private int blocksWaled;
		private int logins;
		private int deaths;
		private int eventsWon;
		private int playtime;
		private int level;

		public ClassStats(String name, int level, double xp, HashMap<String, Integer> dungeons,
				ArrayList<String> quests, String dungeonsAmount, String questsAmount, HashMap<String, Integer> skills,
				int itemsIdentified, int mobsKilled, int pvpKills, int pvpDeaths, int chestsFound, int blocksWaled,
				int logins, int deaths, int eventsWon, int playtime) {
			this.level = level;
			this.name = name;
			this.xp = xp;
			this.dungeons = dungeons;
			this.quests = quests;
			this.dungeonsAmount = dungeonsAmount;
			this.questsAmount = questsAmount;
			this.skills = skills;
			this.itemsIdentified = itemsIdentified;
			this.mobsKilled = mobsKilled;
			this.pvpKills = pvpKills;
			this.pvpDeaths = pvpDeaths;
			this.chestsFound = chestsFound;
			this.blocksWaled = blocksWaled;
			this.logins = logins;
			this.deaths = deaths;
			this.eventsWon = eventsWon;
			this.playtime = playtime;
		}

		public int getLevel() {
			return level;
		}

		public String getName() {
			return name;
		}

		public double getXp() {
			return xp;
		}

		public HashMap<String, Integer> getDungeons() {
			return dungeons;
		}

		public ArrayList<String> getQuests() {
			return quests;
		}

		public String getDungeonsAmount() {
			return dungeonsAmount;
		}

		public String getQuestsAmount() {
			return questsAmount;
		}

		public HashMap<String, Integer> getSkills() {
			return skills;
		}

		public int getItemsIdentified() {
			return itemsIdentified;
		}

		public int getMobsKilled() {
			return mobsKilled;
		}

		public int getPvpKills() {
			return pvpKills;
		}

		public int getPvpDeaths() {
			return pvpDeaths;
		}

		public int getChestsFound() {
			return chestsFound;
		}

		public int getBlocksWaled() {
			return blocksWaled;
		}

		public int getLogins() {
			return logins;
		}

		public int getDeaths() {
			return deaths;
		}

		public int getEventsWon() {
			return eventsWon;
		}

		public int getPlaytime() {
			return playtime;
		}

	}

	public static class GlobalStats {

		private int itemsIdentified;
		private int mobsKilled;
		private int pvpKills;
		private int pvpDeaths;
		private int chestsFound;
		private int blocksWalked;
		private int logins;
		private int deaths;
		private int totalLevel;

		public GlobalStats(int itemsIdentified, int mobsKilled, int pvpKills, int pvpDeaths, int chestsFound,
				int blocksWalked, int logins, int deaths, int totalLevel) {
			super();
			this.itemsIdentified = itemsIdentified;
			this.mobsKilled = mobsKilled;
			this.pvpKills = pvpKills;
			this.pvpDeaths = pvpDeaths;
			this.chestsFound = chestsFound;
			this.blocksWalked = blocksWalked;
			this.logins = logins;
			this.deaths = deaths;
			this.totalLevel = totalLevel;
		}

		public int getItemsIdentified() {
			return itemsIdentified;
		}

		public int getMobsKilled() {
			return mobsKilled;
		}

		public int getPvpKills() {
			return pvpKills;
		}

		public int getPvpDeaths() {
			return pvpDeaths;
		}

		public int getChestsFound() {
			return chestsFound;
		}

		public int getBlocksWalked() {
			return blocksWalked;
		}

		public int getLogins() {
			return logins;
		}

		public int getDeaths() {
			return deaths;
		}

		public int getTotalLevel() {
			return totalLevel;
		}

	}
}
