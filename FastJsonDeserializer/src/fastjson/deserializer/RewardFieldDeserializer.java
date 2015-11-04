package fastjson.deserializer;

import java.lang.reflect.Type;
import java.util.Map;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.util.FieldInfo;

/**
 * 
 * @author GinKo.Wang
 * @mail <a href="mailto:yingosen@gmail.com">yingosen@gmail.com</a>
 * @date 2015年11月4日 下午2:23:37
 *
 */
public class RewardFieldDeserializer<T> extends FieldDeserializer {

	private Callback<T> callback;

	public RewardFieldDeserializer(Class<?> clazz, FieldInfo fieldInfo, Callback<T> callback) {
		super(clazz, fieldInfo);
		this.callback = callback;
	}

	@Override
	public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
		String value;
		final JSONLexer lexer = parser.getLexer();
		if (lexer.token() == JSONToken.LITERAL_STRING) {
			value = lexer.stringVal();
			lexer.nextToken(JSONToken.COMMA);
		} else {
			Object obj = parser.parse();
			if (obj == null) {
				value = null;
			} else {
				value = obj.toString();
			}
		}
		T t = null;
		try {
			t = callback.handler(object, objectType, value);
		} catch (Exception e) {
			callback.onError(object, objectType, value, e);
		}
		if (object == null) {
			fieldValues.put(fieldInfo.getName(), t);
		} else {
			setValue(object, t);
		}
	}
}
