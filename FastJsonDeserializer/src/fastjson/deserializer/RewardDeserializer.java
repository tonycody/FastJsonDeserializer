package fastjson.deserializer;

import java.lang.reflect.Type;
import java.util.IdentityHashMap;
import java.util.Map;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.util.DeserializeBeanInfo;

import fastjson.Reward;

/**
 * 
 * @author GinKo.Wang
 * @mail <a href="mailto:yingosen@gmail.com">yingosen@gmail.com</a>
 * @date 2015年11月4日 上午10:57:22
 *
 */
public class RewardDeserializer extends JavaBeanDeserializer {

	private final Map<String, FieldDeserializer> feildDeserializerMap = new IdentityHashMap<String, FieldDeserializer>();

	public static final RewardDeserializer INSTANCE = new RewardDeserializer(ParserConfig.getGlobalInstance(),
			Reward.class);

	public RewardDeserializer(ParserConfig config, Class<?> clazz) {
		super(config, clazz);
		DeserializeBeanInfo beanInfo = DeserializeBeanInfo.computeSetters(clazz, clazz);
		feildDeserializerMap.put("reward",
				new RewardFieldDeserializer<Double>(clazz, beanInfo.getField("reward"), new Callback<Double>() {
					@Override
					public Double defaultValue() {
						return 0d;
					}

					@Override
					public Double handler(Object object, Type type, String value) {
						double reward = 0d;
						if (value == null) {
							return null;
						}
						String[] values = value.split("\\,");
						int len = values == null ? 0 : values.length;
						if (len > 0) {
							value = values[(int) (Math.random() * len)];
						}
						if (value != null) {
							reward = Double.parseDouble(value);
						}
						return reward;
					}

					@Override
					public void onError(Object object, Type type, String value, Throwable throwable) {
						throwable.printStackTrace();
					}
				}));
		feildDeserializerMap.put("type",
				new RewardFieldDeserializer<Byte>(clazz, beanInfo.getField("type"), new Callback<Byte>() {

					@Override
					public Byte defaultValue() {
						return 0;
					}

					@Override
					public Byte handler(Object object, Type type, String value) {
						byte btype = 0;
						if (value != null) {
							btype = Byte.parseByte(value);
						}
						return btype;
					}

					@Override
					public void onError(Object object, Type type, String value, Throwable throwable) {
						throwable.printStackTrace();
					}
				}));
	}

	@Override
	public boolean parseField(DefaultJSONParser parser, String key, Object object, Type objectType,
			Map<String, Object> fieldValues) {
		JSONLexer lexer = parser.getLexer();
		FieldDeserializer fieldDeserializer = feildDeserializerMap.get(key);
		if (fieldDeserializer == null) {
			return false;
		}
		lexer.nextTokenWithColon(fieldDeserializer.getFastMatchToken());
		fieldDeserializer.parseField(parser, object, objectType, fieldValues);
		return true;
	}

	public static ParserConfig config() {
		ParserConfig config = ParserConfig.getGlobalInstance();
		config.putDeserializer(Reward.class, INSTANCE);
		return config;
	}

}
