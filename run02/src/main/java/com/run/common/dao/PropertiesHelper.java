package com.run.common.dao;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * Properties的操作的工具类,为Properties提供一个代理增加相关工具方法如
 * getRequiredString(),getInt(),getBoolean()等方法
 * 并可以通过systemPropertiesMode属性指定是否搜索System.getProperty()及System.getenv()来查找值.
 * 默认不搜索系统属性
 * 
 * <pre>
 * 使用1:
 * public class ConnectionUtils {
 *     static Properties properties = new Properties(); 
 *     // ... do load properties 
 *     
 *     // delegate to properties
 * 	   static PropertiesHelper props = new PropertiesHelper(properties);
 *     public static Connection getConnection() {
 *     		// use getRequiredProperty() 
 *     		DriverManager.getConnection(props.getRequiredString("jdbc.url"));
 *     }
 * }
 * 指定是否搜索系统属性:
 * new PropertiesHelper(properties,PropertiesHelper.SYSTEM_PROPERTIES_MODE_OVERRIDE)
 * </pre>
 * 
 * @author 柳发勇
 */
public class PropertiesHelper {
	/** Never check system properties. */
	public static final int SYSTEM_PROPERTIES_MODE_NEVER = 0;

	/**
	 * Check system properties if not resolvable in the specified properties.
	 * This is the default.
	 */
	public static final int SYSTEM_PROPERTIES_MODE_FALLBACK = 1;

	/**
	 * Check system properties first, before trying the specified properties.
	 * This allows system properties to override any other property source.
	 */
	public static final int SYSTEM_PROPERTIES_MODE_OVERRIDE = 2;

	Properties p;
	private int systemPropertiesMode = SYSTEM_PROPERTIES_MODE_NEVER;

	public PropertiesHelper(Properties p) {
		setProperties(p);
	}

	/**
	 * <p>
	 * 验证systemPropertiesMode参数是否正确，此方法是默认不检查系统文件
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public PropertiesHelper(Properties p, int systemPropertiesMode) {
		setProperties(p);
		if (systemPropertiesMode != SYSTEM_PROPERTIES_MODE_NEVER
				&& systemPropertiesMode != SYSTEM_PROPERTIES_MODE_FALLBACK
				&& systemPropertiesMode != SYSTEM_PROPERTIES_MODE_OVERRIDE) {
			throw new IllegalArgumentException(
					"error systemPropertiesMode mode:" + systemPropertiesMode);
		}
		this.systemPropertiesMode = systemPropertiesMode;
	}

	/**
	 * <p>
	 * 获取properties
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Properties getProperties() {
		return p;
	}

	private void setProperties(Properties props) {
		if (props == null) {
			throw new IllegalArgumentException("properties must be not null");
		}
		this.p = props;
	}

	/**
	 * <p>
	 * 验证通过key获取value，并进行非空验证
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public String getRequiredString(String key) {
		String value = getProperty(key);
		if (isBlankString(value)) {
			throw new IllegalStateException(
					"required property is blank by key=" + key);
		}
		return value;
	}

	/**
	 * <p>
	 * 如果key为空，通过此方法获取对应的value为空
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public String getNullIfBlank(String key) {
		String value = getProperty(key);
		if (isBlankString(value)) {
			return null;
		}
		return value;
	}

	/**
	 * <p>
	 * 如果value为空，返回空
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public String getNullIfEmpty(String key) {
		String value = getProperty(key);
		if (value == null || "".equals(value)) {
			return null;
		}
		return value;
	}

	/**
	 * <p>
	 * 如果value为空，返回系统中默认的value
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public String getAndTryFromSystem(String key) {
		String value = getProperty(key);
		if (isBlankString(value)) {
			value = getSystemProperty(key);
		}
		return value;
	}

	/**
	 * <p>
	 * 如果value为空，返回系统中默认的value
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	private String getSystemProperty(String key) {
		String value;
		value = System.getProperty(key);
		if (isBlankString(value)) {
			value = System.getenv(key);
		}
		return value;
	}

	/**
	 * <p>
	 * 通过此方法返回一个Integer型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Integer getInteger(String key) {
		String v = getProperty(key);
		if (v == null) {
			return null;
		}
		return Integer.parseInt(v);
	}

	/**
	 * <p>
	 * 通过此方法返回一个int型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public int getInt(String key, int defaultValue) {
		if (getProperty(key) == null) {
			return defaultValue;
		}
		return Integer.parseInt(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个int型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public int getRequiredInt(String key) {
		return Integer.parseInt(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个Long型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Long getLong(String key) {
		if (getProperty(key) == null) {
			return null;
		}
		return Long.parseLong(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个long型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public long getLong(String key, long defaultValue) {
		if (getProperty(key) == null) {
			return defaultValue;
		}
		return Long.parseLong(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个Long型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Long getRequiredLong(String key) {
		return Long.parseLong(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个Boolean型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public boolean getBoolean(String key) {
		if (getProperty(key) == null) {
			return false;
		}
		return Boolean.parseBoolean(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个boolean型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public boolean getBoolean(String key, boolean defaultValue) {
		if (getProperty(key) == null) {
			return defaultValue;
		}
		return Boolean.parseBoolean(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个boolean型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public boolean getRequiredBoolean(String key) {
		return Boolean.parseBoolean(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个Float型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Float getFloat(String key) {
		if (getProperty(key) == null) {
			return null;
		}
		return Float.parseFloat(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个float型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public float getFloat(String key, float defaultValue) {
		if (getProperty(key) == null) {
			return defaultValue;
		}
		return Float.parseFloat(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个Float型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Float getRequiredFloat(String key) {
		return Float.parseFloat(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个Double型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Double getDouble(String key) {
		if (getProperty(key) == null) {
			return null;
		}
		return Double.parseDouble(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个Double型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public double getDouble(String key, double defaultValue) {
		if (getProperty(key) == null) {
			return defaultValue;
		}
		return Double.parseDouble(getRequiredString(key));
	}

	/**
	 * <p>
	 * 通过此方法返回一个Double型
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Double getRequiredDouble(String key) {
		return Double.parseDouble(getRequiredString(key));
	}

	/**
	 * <p>
	 * 给properties赋一int类型值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object setProperty(String key, int value) {
		return setProperty(key, String.valueOf(value));
	}

	/**
	 * <p>
	 * 给properties赋一long类型值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object setProperty(String key, long value) {
		return setProperty(key, String.valueOf(value));
	}

	/**
	 * <p>
	 * 给properties赋一float类型值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object setProperty(String key, float value) {
		return setProperty(key, String.valueOf(value));
	}

	/**
	 * <p>
	 * 给properties赋一double类型值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object setProperty(String key, double value) {
		return setProperty(key, String.valueOf(value));
	}

	/**
	 * <p>
	 * 给properties赋一boolean类型值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object setProperty(String key, boolean value) {
		return setProperty(key, String.valueOf(value));
	}

	/**
	 * <p>
	 * 通过key获取value将value按 "," 拆分返回String数组
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public String[] getStringArray(String key) {
		String v = p.getProperty(key);
		if (v == null) {
			return new String[0];
		} else {
			return split(v, ",");
		}
	}
	
	/**
	 * 将str将多个分隔符进行切分，
	 * 
	 * 示例：StringTokenizerUtils.split("1,2;3 4"," ,;"); 返回: ["1","2","3","4"]
	 * 
	 * @param str
	 * @param seperators
	 * @return
	 */
	@SuppressWarnings("all")
	public static String[] split(String str, String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
		List result = new ArrayList();

		while (tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return (String[]) result.toArray(new String[result.size()]);
	}


	/**
	 * <p>
	 * 通过key获取value将value转换成String数组，再将String转化成int数组
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public int[] getIntArray(String key) {
		String[] array = getStringArray(key);
		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = Integer.parseInt(array[i]);
		}
		return result;
	}

	/**
	 * <p>
	 * 通过key获取value，并设置默认值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public String getProperty(String key, String defaultValue) {
		return p.getProperty(key, defaultValue);
	}

	/**
	 * <p>
	 * 通过key获取value
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public String getProperty(String key) {
		String propVal = null;
		// 如果systemPropertiesMode = 2 首先检查系统属性
		if (systemPropertiesMode == SYSTEM_PROPERTIES_MODE_OVERRIDE) {
			propVal = getSystemProperty(key);
		}
		if (propVal == null) {
			propVal = p.getProperty(key);
		}
		// systemPropertiesMode = 1 如果执行属性没有，获取系统属性
		if (propVal == null
				&& systemPropertiesMode == SYSTEM_PROPERTIES_MODE_FALLBACK) {
			propVal = getSystemProperty(key);
		}
		return propVal;
	}

	/**
	 * <p>
	 * 设置properties值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object setProperty(String key, String value) {
		return p.setProperty(key, value);
	}

	/**
	 * <p>
	 * 清空properties
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void clear() {
		p.clear();
	}

	/**
	 * <p>
	 * 返回Hashtable 中所包含的键的 Set 视图
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Set<Entry<Object, Object>> entrySet() {
		return p.entrySet();
	}

	/**
	 * <p>
	 * 返回属性列表中所有键的枚举，如果在主属性列表中未找到同名的键，则包括默认属性列表中不同的键。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Enumeration<?> propertyNames() {
		return p.propertyNames();
	}

	/**
	 * <p>
	 * 验证properties时候包含指定的value
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public boolean contains(Object value) {
		return p.contains(value);
	}

	/**
	 * <p>
	 * 验证properties时候包含指定的key
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public boolean containsKey(Object key) {
		return p.containsKey(key);
	}

	/**
	 * <p>
	 * 验证properties时候包含指定的value
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public boolean containsValue(Object value) {
		return p.containsValue(value);
	}

	/**
	 * <p>
	 * 返回此哈希表中的值的枚举。对返回的对象使用 Enumeration方法，以便按顺序获取这些元素。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Enumeration<Object> elements() {
		return p.elements();
	}

	/**
	 * <p>
	 * 通过key 获取到一个object
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object get(Object key) {
		return p.get(key);
	}

	/**
	 * <p>
	 * 判断properties是否为空
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public boolean isEmpty() {
		return p.isEmpty();
	}

	/**
	 * <p>
	 * 返回此哈希表中的键的枚举。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Enumeration<Object> keys() {
		return p.keys();
	}

	/**
	 * <p>
	 * 返回此 Hashtable 中所包含的键的 Set 视图。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Set<Object> keySet() {
		return p.keySet();
	}

	/**
	 * <p>
	 * 将属性列表输出到指定的输出流。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void list(PrintStream out) {
		p.list(out);
	}

	/**
	 * <p>
	 * 将属性列表输出到指定的输出流。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void list(PrintWriter out) {
		p.list(out);
	}

	/**
	 * <p>
	 * 从输入流中读取属性列表（键和元素对）。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void load(InputStream inStream) throws IOException {
		p.load(inStream);
	}

	/**
	 * <p>
	 *  将指定输入流中由 XML 文档所表示的所有属性加载到此属性表中。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void loadFromXML(InputStream in) throws IOException {
		p.loadFromXML(in);
	}

	/**
	 * <p>
	 * 给 properties 赋值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object put(Object key, Object value) {
		return p.put(key, value);
	}

	/**
	 * <p>
	 * 给 properties 赋值
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void putAll(Map<? extends Object, ? extends Object> t) {
		p.putAll(t);
	}

	/**
	 * <p>
	 * 删除指定key的value
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Object remove(Object key) {
		return p.remove(key);
	}

	/**
	 * <p>
	 * 果在保存属性列表时发生 I/O 错误，则此方法不抛出 IOException。
	 * 保存属性列表的首选方法是通过 store(OutputStream out, String comments) 
	 * 方法或 storeToXML(OutputStream os, String comment) 方法来进行。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	@Deprecated
	public void save(OutputStream out, String comments) {
		p.save(out, comments);
	}

	/**
	 * <p>
	 * 获取properties的大小
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public int size() {
		return p.size();
	}

	/**
	 * <p>
	 * 适合使用 load 方法加载到 Properties 表中的格式，
	 * 将此 Properties 表中的属性列表（键和元素对）写入输出流。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void store(OutputStream out, String comments) throws IOException {
		p.store(out, comments);
	}

	/**
	 * <p>
	 *  发出一个表示此表中包含的所有属性的 XML 文档。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void storeToXML(OutputStream os, String comment, String encoding)
			throws IOException {
		p.storeToXML(os, comment, encoding);
	}

	/**
	 * <p>
	 *   使用指定的编码发出一个表示此表中包含的所有属性的 XML 文档。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public void storeToXML(OutputStream os, String comment) throws IOException {
		p.storeToXML(os, comment);
	}

	/**
	 * <p>
	 *   返回此 Hashtable 中所包含值的 Collection 视图。
	 *   Collection 受 Hashtable 的支持，
	 *   所以对 Hashtable 的更改反映在 Collection 中，
	 *   反之亦然。Collection 支持元素的删除（它从 Hashtable 中移除相应的条目)，
	 *   但不支持元素的添加。 
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public Collection<Object> values() {
		return p.values();
	}

	/**
	 * <p>
	 * 返回此 Hashtable 对象的字符串表示形式，其形式为 ASCII 字符 ", " 
	 * （逗号加空格）分隔开的、括在括号中的一组条目。
	 * 每个条目都按以下方式呈现：键，一个等号 = 和相关元素，
	 * 其中 toString 方法用于将键和元素转换为字符串。
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	public String toString() {
		return p.toString();
	}

	/**
	 * <p>
	 *  String字符串的非空验证
	 * </p>
	 * 
	 * @author 柳发勇
	 * @date 2014-03-28 上午9:03:20 #see
	 */
	private static boolean isBlankString(String value) {
		return value == null || "".equals(value.trim());
	}
}