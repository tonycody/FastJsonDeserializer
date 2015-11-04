package Reward;

import fastjson.Reward;
import fastjson.Rewards;
import fastjson.deserializer.RewardDeserializer;
import util.JsonUtil;

/**
 * 
 * @author GinKo.Wang
 * @mail <a href="mailto:yingosen@gmail.com">yingosen@gmail.com</a>
 * @date 2015年11月4日 上午10:39:49
 *
 */
public class RewardTest {
	public static void main(String[] args) {
		String json = "{\"rewards\":[{\"reward\":\"5\",\"type\":\"0\"},{\"reward\":\"3\",\"type\":\"0\"},{\"reward\":\"3\",\"type\":\"0\"},{\"reward\":\"1\",\"type\":\"0\"},{\"reward\":\"1\",\"type\":\"0\"},{\"reward\":\"1\",\"type\":\"0\"},{\"reward\":\"0.5\",\"type\":\"0\"},{\"reward\":\"0.5\",\"type\":\"0\"},{\"reward\":\"0.5\",\"type\":\"0\"},{\"reward\":\"2563558567,2563558541,2563558532\",\"type\":\"1\"}]}";
		String testjs = "{\"reward\":\"2563558567,2563558541,2563558532\",\"type\":\"1\"}";
		Reward reward = JsonUtil.fromJson(testjs, Reward.class, RewardDeserializer.config());
		System.err.println(reward);
		Rewards rewards = JsonUtil.fromJson(json, Rewards.class, RewardDeserializer.config());
		for (Reward item : rewards.getRewards()) {
			System.err.println(item);
		}
		/*
		 * rewards.getRewards().forEach(item -> { System.err.println(item); });
		 */
	}
}
