package com.tiantian.utils.util;

import com.tiantian.result.CommonMap;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author qi_bingo
 */
public class Utils {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private Utils() {
    }

    public static boolean empty(Object o) {
        return "".equals(getStr(o).trim());
    }

    public static String getStr(Object o) {
        return getStr(o, "");
    }

    public static String getStr(Object o, String defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        return o.toString();
    }

    public static Float getFloat(Object o) {
        return getFloat(o, null);
    }

    public static Float getFloat(Object o, Float defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            return Float.valueOf(o.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Integer getInt(Object o) {
        return getInt(o, null);
    }

    public static Integer getInt(Object o, Integer defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(o.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Long getLong(Object o) {
        return getLong(o, null);
    }

    public static Long getLong(Object o, Long defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            return Long.valueOf(o.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(Object o) {
        return getBoolean(o, false);
    }

    public static boolean getBoolean(Object o, boolean defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(o.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换request中的parameter为Map
     *
     * @return Map{name,value}
     */
    public static CommonMap getParameters(HttpServletRequest request) {
        CommonMap map = new CommonMap();
        Enumeration<String> enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String name = enums.nextElement().toString();
            String[] values = request.getParameterValues(name);
            if (values.length == 1) {
                map.put(name, Utils.getStr(values[0]).trim());
            } else {
                StringBuffer sb = new StringBuffer();
                for (String value : values) {
                    sb.append(",").append(value.trim());
                }
                map.put(name, Utils.getStr(sb.substring(1)));
            }
        }
        return map;
    }

    /**
     * @description: 去掉 url 中的参数,返回实际的 url 地址
     * @param:
     * @return:
     */
    public static String trimUrl(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (url == null) {
            return null;
        }
        int pos = url.indexOf("?");
        if (pos != -1) {
            url = url.substring(0, pos);
        }
        pos = url.indexOf(";");
        if (pos != -1) {
            url = url.substring(0, pos);
        }
        pos = url.indexOf("#");
        if (pos != -1) {
            url = url.substring(0, pos);
        }
        String schema = request.getContextPath();
        url = url.replace(schema, "");
        return url;
    }

    /**
     * @description: 获取IP
     * @param:
     * @return:
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("HTTP_X_CONNECTING_IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * @description: 生成数字字母混合随机数
     * @param: [length]
     * @return: java.lang.String
     */
    public static String getRandom(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            // 偶数生成字母
            if (new Random().nextInt(2) % 2 == 0) {
                int base = new Random().nextInt(2) % 2 == 0 ? 65 : 97;
                stringBuffer.append((char) (new Random().nextInt(26) + base));
            } else {// 奇数生成数字
                stringBuffer.append(new Random().nextInt(10));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * @description: 带特殊字符的随机数
     * @auther: ZPF
     * @date: 2019/6/4
     * @param:
     * @return:
     */
    public static String getRandomAll(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++)
            switch (new Random().nextInt(3) % 3) {
                // 生成字母
                case 0:
                    int base = new Random().nextInt(2) % 2 == 0 ? 65 : 97;
                    stringBuffer.append((char) (new Random().nextInt(26) + base));
                    break;
                // 生成数字
                case 1:
                    stringBuffer.append(new Random().nextInt(10));
                    break;
                // 生成特殊字符
                case 2:
                    stringBuffer.append((char) (new Random().nextInt(15) + 33));
                    break;
            }
        return stringBuffer.toString();
    }

    public static String formatMoney(double money) {
        DecimalFormat moneyFormat = new DecimalFormat("#0.00");
        money = StringUtil.roundDouble(money, 2);
        return moneyFormat.format(money);
    }

    /**
     * 获取double
     * 
     * @param d
     * @return
     */
    public static String getDoubleValue(Double d) {
        return d == null ? "" : formatMoney(d);
    }

    /**
     * 获取date
     * 
     * @param date
     * @return
     */
    public static String getDateValue(Date date) {
        return date == null ? "" : Utils.sdf.format(date);
    }

    /**
     * 获取int
     * 
     * @param i
     * @return
     */
    public static String getIntegerValue(Integer i) {
        return i == null ? "0" : i.toString();
    }

    /**
     * @description: 根据指定的key将list转换成map
     * @param: [dataList, key]
     * @return: java.util.Map
     */
    public static Map<String, CommonMap> tranceList2Map(List<CommonMap> dataList, String key) {
        Map<String, CommonMap> map = new HashMap<String, CommonMap>();
        for (CommonMap rowMap : dataList) {
            map.put(rowMap.get(key), rowMap);
        }
        return map;
    }

    /**
     * @description: 根据指定的key将list转换成map
     * @param: [dataList, key1, key2]
     * @return: java.util.Map
     */
    public static Map<String, CommonMap> tranceList2Map(List<CommonMap> dataList, String key1, String key2) {
        Map<String, CommonMap> map = new HashMap<String, CommonMap>();
        for (CommonMap rowMap : dataList) {
            map.put(rowMap.get(key1) + rowMap.get(key2), rowMap);
        }
        return map;
    }

    /**
     * @description: 获取XML节点信息
     */
    public static String getNodeStringValue(Element element, String nodeName) {
        if (element.getElementsByTagName(nodeName) == null) {
            return null;
        }
        if (element.getElementsByTagName(nodeName).item(0) == null) {
            return null;
        }
        if (element.getElementsByTagName(nodeName).item(0).getFirstChild() == null) {
            return null;
        }

        return element.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
    }
}
