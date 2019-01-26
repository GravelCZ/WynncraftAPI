package cz.GravelCZLP.WynncraftAPI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import cz.GravelCZLP.WynncraftAPI.GuildStats.Member;
import cz.GravelCZLP.WynncraftAPI.PlayerStats.ClassStats;
import cz.GravelCZLP.WynncraftAPI.PlayerStats.GlobalStats;
import cz.GravelCZLP.WynncraftAPI.PlayerStats.Guild;
import cz.GravelCZLP.WynncraftAPI.Stats.GuildLeaderboardInfo;
import cz.GravelCZLP.WynncraftAPI.Stats.LeaderboardInfo;
import cz.GravelCZLP.WynncraftAPI.Stats.PlayerLeaderboardInfo;

public class WynncraftAPI {

	private static String mainUrl = "https://api.wynncraft.com/public_api.php?action=%A%";

	private static int requests = 0;
	
	public static void init() {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				requests = 0;
			}
		};
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(r, 0, 10, TimeUnit.MINUTES);
	}
	
	/**
	 * @return - Returns all the guilds.
	 */
	public static String getGuildList() {
		if (requests > 250) {
			return null;
		}
		requests++;
		try {
			String txtResponse = Utils.makeUrlGetRequest(new URL(mainUrl.replaceFirst("%A%", "guildList")),
					new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponse);
			JSONArray guilds = obj.getJSONArray("guilds");
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < guilds.length(); i++) {
				buf.append(guilds.getString(i) + ", ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Gets guild stats.
	 * 
	 * @param name
	 *                 - the guild name
	 * @return - Info about the guild, if no guild was found, isError will be true
	 *         and name will be the reason
	 */
	public static GuildStats getGuildStats(String name) {
		if (requests > 250) {
			return null;
		}
		requests++;
		try {
			String txtResponse = Utils.makeUrlGetRequest(
					new URL(mainUrl.replaceFirst("%A%", "guildStats") + "&command=" + name), new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponse);
			if (obj.has("error")) {
				return new GuildStats(true, obj.getString("error"), null, null, null, 0, 0, 0);
			}
			String nameRes = obj.getString("name");
			String prefix = obj.getString("prefix");
			String banner = obj.getJSONObject("banner").toString();
			double xp = obj.getDouble("xp");
			int level = obj.getInt("level");
			int terr = obj.getInt("territories");
			JSONArray meb = obj.getJSONArray("members");
			Member[] members = new Member[meb.length()];
			for (int i = 0; i < meb.length(); i++) {
				JSONObject memebrObj = meb.getJSONObject(i);
				members[i] = new Member(memebrObj.getString("name"), memebrObj.getString("uuid"),
						memebrObj.getString("rank"));
			}
			return new GuildStats(false, nameRes, prefix, banner, members, xp, level, terr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets all the information about a player
	 * 
	 * @param name
	 *                 - The player name
	 * @return - The info about the player, if no player was found, isError will be
	 *         true and name will be the reason
	 */
	public static PlayerStats getPlayerStats(String name) {
		if (requests > 250) {
			return null;
		}
		requests++;
		try {
			String txtResponce = Utils.makeUrlGetRequest(
					new URL(mainUrl.replaceFirst("%A%", "playerStats") + "&command=" + name), new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponce);
			if (obj.has("error")) {
				return new PlayerStats(true, obj.getString("error"), "", "", "", false, false, -1, "", null, null, null,
						null, null);
			}
			String username = obj.getString("username");
			String uuid = obj.getString("uuid");
			String rank = obj.getString("rank");
			String tag = obj.getString("tag");
			boolean displayTag = obj.getBoolean("displayTag");
			boolean veteran = obj.getBoolean("veteran");
			int playtime = obj.getInt("playtime");
			String currentServer = obj.getString("current_server");

			JSONObject globalObj = obj.getJSONObject("global");

			GlobalStats stats = new GlobalStats(globalObj.getInt("items_identified"), globalObj.getInt("mobs_killed"),
					globalObj.getInt("pvp_kills"), globalObj.getInt("pvp_deaths"), globalObj.getInt("chests_found"),
					globalObj.getInt("blocks_walked"), globalObj.getInt("logins"), globalObj.getInt("deaths"),
					globalObj.getInt("total_level"));

			ArrayList<ClassStats> cstats = new ArrayList<>();

			JSONObject classesObj = obj.getJSONObject("classes");

			Iterator<String> iter = classesObj.keys();

			while (iter.hasNext()) {
				String key = iter.next();
				JSONObject classObj = classesObj.getJSONObject(key);

				HashMap<String, Integer> dungeons = new HashMap<>();

				JSONObject dunObj = classObj.getJSONObject("dungeons");

				Iterator<String> dunIter = dunObj.keys();

				while (dunIter.hasNext()) {
					String dunKey = dunIter.next();
					dungeons.put(dunKey, dunObj.getInt(dunKey));
				}

				ArrayList<String> quests = new ArrayList<>();
				JSONArray questsArray = classObj.getJSONArray("quests");

				for (int i = 0; i < questsArray.length(); i++) {
					quests.add(questsArray.getString(i));
				}

				HashMap<String, Integer> skills = new HashMap<>();

				JSONObject skillsObj = classObj.getJSONObject("skills");

				Iterator<String> skillsIter = skillsObj.keys();

				while (skillsIter.hasNext()) {
					String skillKey = skillsIter.next();
					skills.put(skillKey, skillsObj.getInt(skillKey));
				}

				ClassStats cs = new ClassStats(key, classObj.getInt("level"), classObj.getDouble("xp"), dungeons,
						quests, classObj.getString("dungeonsAmount"), classObj.getString("questsAmount"), skills,
						classObj.getInt("items_identified"), classObj.getInt("mobs_killed"),
						classObj.getInt("pvp_kills"), classObj.getInt("pvp_deaths"), classObj.getInt("chests_found"),
						classObj.getInt("blocks_walked"), classObj.getInt("logins"), classObj.getInt("deaths"),
						classObj.getInt("events_won"), classObj.getInt("playtime"));
				cstats.add(cs);
			}

			HashMap<String, Integer> witzFort = new HashMap<>();

			JSONObject wfObj = obj.getJSONObject("wizard_fortress");

			Iterator<String> wfiter = wfObj.keys();

			while (wfiter.hasNext()) {
				String key = wfiter.next();
				witzFort.put(key, wfObj.getInt(key));
			}

			HashMap<String, Integer> rankings = new HashMap<>();

			JSONObject ranObj = obj.getJSONObject("rankings");

			Iterator<String> ranIter = ranObj.keys();

			while (ranIter.hasNext()) {
				String key = ranIter.next();
				if (!ranObj.isNull(key)) {
					rankings.put(key, ranObj.getInt(key));
				} else {
					rankings.put(key, -1);
				}
			}

			JSONObject guildObj = obj.getJSONObject("guild");

			Guild g = new Guild(guildObj.getString("name"), guildObj.getString("rank"));

			return new PlayerStats(false, username, uuid, rank, tag, displayTag, veteran, playtime, currentServer,
					stats, cstats, witzFort, rankings, g);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns all the players on the servers.
	 * 
	 * @return - HashMap with keys as the server names and values a list of player
	 *         names on the server.
	 */
	public static HashMap<String, List<String>> getOnlinePlayers() {
		if (requests > 250) {
			return null;
		}
		requests++;
		try {
			String txtResponse = Utils.makeUrlGetRequest(new URL(mainUrl.replaceFirst("%A%", "onlinePlayers")),
					new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponse);
			Iterator<String> iter = obj.keys();
			HashMap<String, List<String>> players = new HashMap<>();
			while (iter.hasNext()) {
				String key = iter.next();
				if (key.equals("request")) {
					continue;
				}
				List<String> p = new ArrayList<>();
				JSONArray playerArray = obj.getJSONArray(key);
				for (int i = 0; i < playerArray.length(); i++) {
					p.add(playerArray.getString(i));
				}
				players.put(key, p);
			}
			return players;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the numebr of players on all server
	 * 
	 * @return - number of player
	 */
	public static int getOnlinePlayerSum() {
		if (requests > 250) {
			return 0;
		}
		requests++;
		try {
			String txtResponse = Utils.makeUrlGetRequest(new URL(mainUrl.replaceFirst("%A%", "onlinePlayersSum")),
					new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponse);
			return obj.getInt("players_online");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	/**
	 * Returns list of all Territories
	 * 
	 * @return - List of territories.
	 */
	public static List<Territory> getTerritories() {
		if (requests > 250) {
			return null;
		}
		requests++;
		try {
			String txtResponse = Utils.makeUrlGetRequest(new URL(mainUrl.replaceFirst("%A%", "territoryList")),
					new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponse);
			JSONObject territoriesObj = obj.getJSONObject("territories");
			Iterator<String> iter = territoriesObj.keys();
			List<Territory> ters = new ArrayList<>();
			while (iter.hasNext()) {
				String key = iter.next();
				JSONObject territory = territoriesObj.getJSONObject(key);
				String name = territory.getString("territory");
				String guild = territory.getString("guild");
				String attacker = null;
				if (!territory.isNull("attacker")) {
					attacker = territory.getString("attacker");
				}
				Vector2D start = null;
				Vector2D end = null;
				if (territory.has("location")) {
					JSONObject locObj = territory.getJSONObject("location");
					start = new Vector2D(locObj.getInt("startX"), locObj.getInt("startY"));
					end = new Vector2D(locObj.getInt("endX"), locObj.getInt("endY"));
				} else {
					System.out.println(key + " does not have location");
				}
				ters.add(new Territory(name, guild, attacker, start, end));
			}
			return ters;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets all the items in the category. Will be an empty list if no category was
	 * found.
	 * 
	 * @param category
	 *                     - the category to return
	 * @return - List of items
	 */
	public static List<Item> getItemsFromCategory(String category) {
		if (requests > 250) {
			return null;
		}
		requests++;
		try {
			String txtResponce = Utils.makeUrlGetRequest(
					new URL(mainUrl.replaceFirst("%A%", "itemDB") + "&category=" + category), new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponce);
			JSONArray itemsArray = obj.getJSONArray("items");
			List<Item> items = new ArrayList<>();
			for (int i = 0; i < itemsArray.length(); i++) {
				String itemObj = itemsArray.getJSONObject(i).toString();
				Gson gson = new Gson();
				Item item = gson.fromJson(itemObj, Item.class);
				items.add(item);
			}
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets the list of items that contain search param in the name.
	 * 
	 * @param search
	 *                   - the item name, can be partial, case-ignored
	 * @return - List of all the items that contain search param in the name
	 */
	public static List<Item> searchItem(String search) {
		if (requests > 250) {
			return null;
		}
		requests++;
		try {
			String txtResponce = Utils.makeUrlGetRequest(
					new URL(mainUrl.replaceFirst("%A%", "itemDB") + "&search==" + search), new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponce);
			JSONArray itemsArray = obj.getJSONArray("items");
			List<Item> items = new ArrayList<>();
			for (int i = 0; i < itemsArray.length(); i++) {
				String itemObj = itemsArray.getJSONObject(i).toString();
				Gson gson = new Gson();
				Item item = gson.fromJson(itemObj, Item.class);
				items.add(item);
			}
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param search
	 *                   - Player or guild name, case-ignored, can be partial
	 * @return - HashMap of players and guilds that match the query. keys: players,
	 *         guilds;
	 */
	public static HashMap<String, List<String>> listGuildAndPlayers(String search) {
		if (requests > 250) {
			return null;
		}
		requests++;
		try {
			String txtResponse = Utils.makeUrlGetRequest(
					new URL(mainUrl.replaceFirst("%A%", "statsSearch") + "&search=" + search), new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponse);
			JSONArray guildArray = obj.getJSONArray("guilds");
			JSONArray playersArray = obj.getJSONArray("players");
			List<String> players = new ArrayList<>();
			List<String> guilds = new ArrayList<>();
			HashMap<String, List<String>> resp = new HashMap<>();
			for (int i = 0; i < guildArray.length(); i++) {
				guilds.add(guildArray.getString(i));
			}
			for (int i = 0; i < playersArray.length(); i++) {
				players.add(playersArray.getString(i));
			}
			resp.put("players", players);
			resp.put("guilds", guilds);
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param type
	 *                      - The type you would like to query
	 * @param timeframe
	 *                      - The time frame to query (week only valid if type is
	 *                      pvp)
	 * @return - List if Leaderboard info objects, cast these to
	 *         PlayerLeaderboardInfo if you used type PVP or player, for guild use
	 *         GuildLeaderboardInfo
	 * @throws IllegalArgumentException
	 *                                      - thrown is timeframe is week and
	 *                                      leaderboardtype is other then pvp
	 */
	public static List<LeaderboardInfo> queryScoreboard(LeaderboardType type, TimeFrame timeframe)
			throws IllegalArgumentException {
		if (requests > 250) {
			return null;
		}
		requests++;
		if (type != LeaderboardType.PVP && timeframe == TimeFrame.WEEK) {
			throw new IllegalArgumentException("Leaderboard type must be pvp if TimeFrame is week");
		}
		try {
			String txtResponse = Utils
					.makeUrlGetRequest(
							new URL(mainUrl.replaceFirst("%A%", "statsLeaderboard") + "&type="
									+ type.name().toLowerCase() + "&timeframe=" + timeframe.name().toLowerCase()),
							new HashMap<>(), false);
			JSONObject obj = new JSONObject(txtResponse);
			List<LeaderboardInfo> info = new ArrayList<>();
			JSONArray data = obj.getJSONArray("data");
			if (type == LeaderboardType.GUILD) {
				for (int i = 0; i < data.length(); i++) {
					JSONObject dobj = data.getJSONObject(i);
					info.add(new GuildLeaderboardInfo(dobj.getString("name"), dobj.getString("prefix"),
							dobj.getInt("xp"), dobj.getInt("level"), dobj.has("banner") ? dobj.getJSONObject("banner").toString() : "null",
							dobj.getInt("territories"), dobj.getInt("membersCount"), dobj.getInt("num")));
				}
			} else {
				for (int i = 0; i < data.length(); i++) {
					JSONObject dobj = data.getJSONObject(i);
					String name = dobj.getString("name");
					String uuid = dobj.getString("uuid");
					int kills = dobj.getInt("kills");
					int level = dobj.getInt("level");
					int xp = dobj.getInt("level");
					int minPlayed = dobj.getInt("minPlayed");
					String tag = dobj.getString("tag");
					String rank = dobj.getString("rank");
					boolean displayTag = dobj.getBoolean("displayTag");
					boolean veteran = dobj.getBoolean("veteran");
					int num = dobj.getInt("num");
					String guild = null;
					String guildTag = null;
					if (dobj.has("guild")) {
						guild = dobj.getString("guild");
					}
					if (dobj.has("guildTag")) {
						guildTag = dobj.getString("guildTag");
					}
					info.add(new PlayerLeaderboardInfo(name, uuid, kills, level, xp, minPlayed, tag, rank, displayTag,
							veteran, num, guildTag, guild));
				}
			}
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
