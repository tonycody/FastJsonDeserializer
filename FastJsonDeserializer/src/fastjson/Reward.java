package fastjson;

public class Reward {
	private byte type;
	private double reward;

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	@Override
	public String toString() {
		return "Reward [type=" + type + ", reward=" + reward + "]";
	}

}
