package cz.GravelCZLP.WynncraftAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import cz.GravelCZLP.WynncraftAPI.GuildStats.Member;
import cz.GravelCZLP.WynncraftAPI.Stats.GuildLeaderboardInfo;
import cz.GravelCZLP.WynncraftAPI.Stats.LeaderboardInfo;
import cz.GravelCZLP.WynncraftAPI.Stats.PlayerLeaderboardInfo;

public class WynncraftAPITest {

	public static void main(String[] args) {

		System.out.println("---GUILD-List----");
		System.out.println(WynncraftAPI.getGuildList());
		System.out.println("---GUILD-List-end---");
		System.out.println("---GUILD-Stats----");
		GuildStats gs = WynncraftAPI.getGuildStats("HackForums");
		System.out.println("Name: " + gs.getName());
		System.out.println("Prefix:" + gs.getPrefix());
		System.out.println("banner: " + gs.getBanner());
		System.out.println("XP: " + gs.getXp());
		System.out.println("Level: " + gs.getLevel());
		System.out.println("Terrs: " + gs.getTerritories());
		for (Member m : gs.getMembers()) {
			System.out.println("Member name: " + m.getName() + " rank:" + m.getRank());
		}
		System.out.println("---GUILD-Stats-end---");
		System.out.println("---Player-Info---");
		PlayerStats ps = WynncraftAPI.getPlayerStats("Jumla");
		System.out.println(ps.getUsername());
		System.out.println("---Player-Info-end---");
		System.out.println("---Player-List---");
		HashMap<String, List<String>> players = WynncraftAPI.getOnlinePlayers();
		for (Entry<String, List<String>> s : players.entrySet()) {
			StringBuffer b = new StringBuffer();
			for (String st : s.getValue()) {
				b.append(st + ", ");
			}
			System.out.println(s.getKey() + ": " + b.toString());
		}
		System.out.println("---Player-List-end----");
		System.out.println("Players: " + WynncraftAPI.getOnlinePlayerSum());
		List<Territory> ters = WynncraftAPI.getTerritories();
		for (Territory t : ters) {
			System.out.println("Terr: " + t.getName() + " Guild: " + t.getGuild());
		}

		List<Item> items = WynncraftAPI.getItemsFromCategory("chestplate");
		for (Item it : items) {
			System.out.println("Item name category:" + it.getName());
		}
		List<Item> items2 = WynncraftAPI.searchItem("abandon");
		for (Item it : items2) {
			System.out.println("Item name search:" + it.getName());
		}

		HashMap<String, List<String>> s = WynncraftAPI.listGuildAndPlayers("turtles");
		for (Entry<String, List<String>> gp : s.entrySet()) {
			StringBuffer buf = new StringBuffer();
			for (String s2 : gp.getValue()) {
				buf.append(s2 + ", ");
			}
			System.out.println(gp.getKey() + ": " + buf.toString());
		}
		List<LeaderboardInfo> pvpAllTime = WynncraftAPI.queryScoreboard(LeaderboardType.PVP, TimeFrame.ALLTIME);
		List<LeaderboardInfo> pvpWeek = WynncraftAPI.queryScoreboard(LeaderboardType.PVP, TimeFrame.WEEK);
		List<LeaderboardInfo> guildAllTime = WynncraftAPI.queryScoreboard(LeaderboardType.GUILD, TimeFrame.ALLTIME);
		List<LeaderboardInfo> playerAllTime = WynncraftAPI.queryScoreboard(LeaderboardType.PLAYER, TimeFrame.ALLTIME);
		
		for (LeaderboardInfo li : pvpAllTime) {
			PlayerLeaderboardInfo pip = (PlayerLeaderboardInfo) li;
			System.out.println("Name pvpalltime: " + pip.getName());
		}
		for (LeaderboardInfo li : pvpWeek) {
			PlayerLeaderboardInfo pip = (PlayerLeaderboardInfo) li;
			System.out.println("Name pvpweek: " + pip.getName());
		}
		for (LeaderboardInfo li : guildAllTime) {
			GuildLeaderboardInfo pip = (GuildLeaderboardInfo) li;
			System.out.println("Name guildAllTime: " + pip.getName());
		}
		for (LeaderboardInfo li : playerAllTime) {
			PlayerLeaderboardInfo pip = (PlayerLeaderboardInfo) li;
			System.out.println("Name playerAllTime: " + pip.getName());
		}

	}

}
