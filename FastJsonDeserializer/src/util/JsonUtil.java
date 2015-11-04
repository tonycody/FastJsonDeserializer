package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 
 * @author GinKo.Wang
 * @mail <a href="mailto:yingosen@gmail.com">yingosen@gmail.com</a>
 * @date 2015年11月4日 上午10:55:31
 *
 */
public class JsonUtil {

	/**
	 * 转换为object
	 * 
	 * @param input
	 * @param clazz
	 * @param config
	 * @param features
	 * @return
	 */
	public static <T> T fromJson(String input, Class<T> clazz, ParserConfig config,
			Feature... features) {
		return JSON.parseObject(input, clazz, config, JSON.DEFAULT_PARSER_FEATURE, features);
	}

	
	
}
