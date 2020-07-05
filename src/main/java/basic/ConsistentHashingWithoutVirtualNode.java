package basic;

import java.util.*;

/**
 * 一致性hash环，根据用户ip路由到不同服务器地址（不带虚拟节点）
 * 1. 初始化加载服务器地址，装在treemap里，key->hash, value->真实地址
 * 2. 用户根据ip得到对应hash，treemap.get(hash(userIp)) 获取服务器列表，获取顺时针最靠近的值
 * @author jamie
 *
 */
public class ConsistentHashingWithoutVirtualNode {

	private static String[] servers = { "192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111",
			"192.168.0.4:111" };

	private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

	static {
		for (int i = 0; i < servers.length; i++) {
			int hash = getHash(servers[i]);
			sortedMap.put(hash, servers[i]);
		}
	}

	private static int getHash(String str) {
		final int p = 16777619;
		int hash = (int) 2166136261L;
		for (int i = 0; i < str.length(); i++)
			hash = (hash ^ str.charAt(i)) * p;
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;

		if (hash < 0)
			hash = Math.abs(hash);
		return hash;
	}

	private static String getServer(String node) {
		int hash = getHash(node);
		SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
		return subMap.get(subMap.firstKey());
	}

	public static void main(String[] args) {
		String[] nodes = { "127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333" };
		for (int i = 0; i < nodes.length; i++) {
			System.out.println(getServer(nodes[i]));
		}
	}
}