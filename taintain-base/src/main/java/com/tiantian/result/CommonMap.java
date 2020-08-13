package com.tiantian.result;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.LinkedCaseInsensitiveMap;

public class CommonMap implements Map<String, Object>, Serializable, Cloneable {

	private static final long serialVersionUID = 7867952593831688909L;

	private LinkedCaseInsensitiveMap<Object> targetMap;

	public CommonMap() {
		this.targetMap = new LinkedCaseInsensitiveMap<Object>();
	}

	/**
	 * Copy constructor.
	 */
	private CommonMap(CommonMap other) {
		this.targetMap = (LinkedCaseInsensitiveMap<Object>) other.targetMap.clone();
	}

	@Override
	public void clear() {
		this.targetMap.clear();
	}

	@Override
	public CommonMap clone() {
		return new CommonMap(this);
	}

	@Override
	public boolean containsKey(Object key) {
		return this.targetMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.targetMap.containsValue(value);
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return this.targetMap.entrySet();
	}

	@Override
	public boolean equals(Object obj) {
		return this.targetMap.equals(obj);
	}

	@Override
	public String get(Object key) {
		return getString(key.toString());
	}

	// 会导致不安全的类型转换，建议不要使用
	@Deprecated
	public Object getObject(Object key) {
		return this.targetMap.get(key);
	}

	public String get(String key, String defaultValue) {
		String value = getString(key);
		if (("".equals(value)) || (value == null)) {
			return defaultValue;
		}
		return getString(key.toString());
	}

	public double getDouble(String key) {
		String value = getString(key);
		return Double.valueOf(value).doubleValue();
	}

	public double getDouble(String key, double defaultValue) {
		String value = getString(key);
		if (("".equals(value)) || (value == null)) {
			return defaultValue;
		}
		return Double.valueOf(value).doubleValue();
	}

	public float getFloat(String key) {
		String value = getString(key);
		return Float.valueOf(value).floatValue();
	}

	public float getFloat(String key, float defaultValue) {
		String value = getString(key);
		if (("".equals(value)) || (value == null)) {
			return defaultValue;
		}
		return Float.valueOf(value).floatValue();
	}

	public int getInteger(String key) {
		String value = getString(key);
		return Integer.valueOf(value).intValue();
	}

	public int getInteger(String key, int defaultValue) {
		String value = getString(key);
		if (("".equals(value)) || (value == null)) {
			return defaultValue;
		}
		return Integer.valueOf(value).intValue();
	}

	public long getInteger(String key, long defaultValue) {
		String value = getString(key);
		if (("".equals(value)) || (value == null)) {
			return defaultValue;
		}
		return Long.valueOf(value).longValue();
	}

	public long getLong(String key) {
		String value = getString(key);
		return Long.valueOf(value).longValue();
	}

	private String getString(String key) {
		Object ret = this.targetMap.get(key);
		if (ret == null) {
			return "";
		}
		if ((ret instanceof Date)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = df.format((Date) ret);
			if (str.endsWith("00:00:00.0")) {
				str = str.substring(0, str.length() - 10);
			} else if (str.endsWith("00:00:00")) {
				str = str.substring(0, str.length() - 8);
			}
			return str = str.trim();
		}
		return ret.toString();
	}

	@Override
	public int hashCode() {
		return this.targetMap.hashCode();
	}

	@Override
	public boolean isEmpty() {
		return this.targetMap.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return this.targetMap.keySet();
	}

	@Override
	public Object put(String key, Object value) {
		return this.targetMap.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> map) {
		this.targetMap.putAll(map);
	}

	@Override
	public Object remove(Object key) {
		return this.targetMap.remove(key);
	}

	@Override
	public int size() {
		return this.targetMap.size();
	}

	@Override
	public String toString() {
		return this.targetMap.toString();
	}

	@Override
	public Collection<Object> values() {
		return this.targetMap.values();
	}
}
