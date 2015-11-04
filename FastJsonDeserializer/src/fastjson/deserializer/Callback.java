package fastjson.deserializer;

import java.lang.reflect.Type;

/**
 * 
 * @author GinKo.Wang
 * @mail <a href="mailto:yingosen@gmail.com">yingosen@gmail.com</a>
 * @date 2015年11月4日 下午2:21:26
 *
 */
public interface Callback<T> {

	/**
	 * 默认值
	 * 
	 * @return
	 */
	T defaultValue();

	/**
	 * 调用
	 * 
	 * @param value
	 * @return
	 */
	T handler(Object object, Type type, String value);

	/**
	 * 发生异常调用
	 */
	void onError(Object object, Type type, String value, Throwable throwable);

}
