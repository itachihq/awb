package com.awb.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommUtil {
	private  static  final Logger logger = LoggerFactory.getLogger(CommUtil.class);
	private static final String CITYCODE_FILE= "citycode.json";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat cnDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
	private static final SimpleDateFormat cnTimeFormat = new SimpleDateFormat("hh点mm分ss秒");
	private static final SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
	private static final SimpleDateFormat longDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static final SimpleDateFormat cnLongDateFormat = new SimpleDateFormat("yyyy年MM月dd日 H点m分s秒");
	private static final DecimalFormat numberFormat = new DecimalFormat("#,###,###,##0.00");

	private static final CommUtil util = new CommUtil();

	private static final String IP_INFO_SINA = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=";

	//()字符串过滤
	public static String filterStr(String sourcestr)
	{
        int idx = sourcestr.indexOf('(');
        sourcestr=(idx==-1)?sourcestr:sourcestr.substring(0, idx);
        return sourcestr;
	}
	//正则表达式
	public static String regex(String regStr,String sourcestr)
	{
		Pattern pattern = Pattern.compile(regStr,Pattern.DOTALL);
		Matcher matcher = pattern.matcher(sourcestr); 
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()){              
		    buffer.append(matcher.group());        
		}
		return buffer.toString();
	}

	/**
	 * 判别字符串是否为空
	 * @param source
	 * @return
	 */
	public static boolean StringIsNull(String source)
	{
		return (source == null)||("".equals(source)||("null".equals(source)));
	}


	public static String readFile2String(final String file)
	{
		InputStream inputstream =CommUtil.class.getClassLoader().getResourceAsStream(file);// 读取txt内容为字符串
		StringBuffer txtContent = new StringBuffer();// 每次读取的byte数
		byte[] b = new byte[8 * 1024];
		try
		{   // 文件输入流
			while (inputstream.read(b) != -1)
			{// 字符串拼接
				txtContent.append(new String(b));
			}// 关闭流
			inputstream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (inputstream != null)
			{
				try
				{
					inputstream.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return txtContent.toString();
	}
	public static boolean beforeDays(Date date, Integer days) {
		if (date == null || days == null)
			return false;
		Long time = System.currentTimeMillis() - date.getTime();
		return time < (days * 86400000);
	}

	public static String cleanHtmlTag(String htmlText) {
		String reg = "</?[a-z][a-z0-9]*[^<>]*>?";
		return htmlText.replaceAll(reg, "");
	}

	public static String currentTime()
	{
		return longDateFormat2.format(new Date());
	}

	/**
	 * 将"yyyy年MM月dd日"格式日期字符串转换为日期对象
	 * 
	 * @param s
	 * @return
	 */
	public static Date cnFormatDate(String s) {
		Date d = null;
		try {
			d = cnDateFormat.parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	/**
	 * 将日期对象转换为"yyyy年MM月dd日 H点m分s秒"格式的日期字符串
	 * 
	 * @param v
	 * @return
	 */
	public static String cnLongDate(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = cnLongDateFormat.format(v);
		} catch (IllegalArgumentException e) {

		}
		return ret;
	}

	/**
	 * 将日期对象转换为"hh点mm分ss秒"格式的日期字符串
	 * 
	 * @param v
	 * @return
	 */
	public static String cnSortTime(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = cnTimeFormat.format(v);
		} catch (IllegalArgumentException e) {

		}
		return ret;
	}

	/**
	 * 将日期对象转换为"hh:mm:ss"格式的时间字符串
	 * 
	 * @param v
	 * @return
	 */
	public static String sortTime(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = timeFormat.format(v);
		} catch (IllegalArgumentException e) {

		}
		return ret;
	}

	/**
	 * 将日期对象转换为"yyyy-MM-dd"格式的日期字符串
	 * 
	 * @param v
	 * @return
	 */
	public static String format(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			if (v instanceof Number)
				ret = numberFormat.format(v);
			else if (v instanceof Date)
				ret = dateFormat.format(v);
			else
				return v.toString();
		} catch (IllegalArgumentException e) {

		}
		return ret;
	}
	
	public static String formatNumber(String format,Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			if (v instanceof Number){
				DecimalFormat numberFormat = new DecimalFormat(format);
				ret = numberFormat.format(v);
			}
			else
				return v.toString();
		} catch (IllegalArgumentException e) {

		}
		return ret;
	}

	/**
	 * 将"yyyy-MM-dd"格式的日期字符串转换为日期对象
	 * 
	 * @param s
	 * @return
	 */
	public static Date formatDate(String s) {
		Date d = null;
		try {
			d = dateFormat.parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	/**
	 * 将日期对象转换为format参数指定的日期格式字符串
	 * 
	 * @param format
	 * @param v
	 * @return
	 */
	public static String formatDate(String format, Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	/**
	 * 将Long型数据转换成时间格式的字符串，例如：formatLongToTimeStr(100000)=0小时1分钟
	 * 
	 * 40秒
	 */
	public static String formatLongToTimeStr(Long l) {
		int hour = 0;
		int minute = 0;
		int second = 0;

		second = l.intValue() / 1000;

		if (second > 60) {
			minute = second / 60;
			second = second % 60;
		}
		if (minute > 60) {
			hour = minute / 60;
			minute = minute % 60;
		}
		return (null2String(hour) + "小时" + null2String(minute) + "分钟"
				+ null2String(second) + "秒");
	}

	/**
	 * 将日期对象转换为"yyyy-MM-dd H:m:s"格式的日期时间字符串
	 * 
	 * @param v
	 * @return
	 */
	public static String longDate(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = longDateFormat.format(v);
		} catch (IllegalArgumentException e) {

		}
		return ret;
	}

	/**
	 * 将一个可迭代对象中的每一个元素拷贝到一个classType指定类型的对象中，然后将这个对象添加到一个list中，并最后返回这个list的迭代器。
	 * 
	 * @deprecated 使用copyIterator代替
	 * @param classType
	 * @param src
	 * @return
	 */
	public static Iterator CopyIterator(Class classType, Iterator src) {
		return copyIterator(classType, src);
	}
	public static Iterator copyIterator(Class classType, Iterator src) {
		return CopyList(classType, src).iterator();
	}
	/**
	 * 将一个可迭代对象中的每一个元素拷贝到一个classType指定类型的对象中，然后将这个对象添加到一个list中，并最后返回这个list。
	 * 
	 * @deprecated 使用copyList代替
	 * @param classType
	 * @param src
	 * @return
	 */
	public static <T> List<T> CopyList(Class<T> classType, Iterator<T> src) {
		return copyList(classType,src);
	}
	public static <T> List<T> copyList(Class<T> classType, Iterator<T> src) {
		List<T> tag = new ArrayList<T>();
		while (src.hasNext()) {
			T obj = null, ormObj = src.next();
			try {
				obj = classType.newInstance();
				BeanWrapper wrapper = new BeanWrapperImpl(obj);
				BeanWrapper wrapper1 = new BeanWrapperImpl(ormObj);
				PropertyDescriptor descriptors[] = wrapper
						.getPropertyDescriptors();
				for (PropertyDescriptor element : descriptors) {
					String name = element.getName();
					wrapper.setPropertyValue(name, wrapper1
							.getPropertyValue(name));
				}

			} catch (Exception e) {
			}
			if (obj != null)
				tag.add(obj);
		}
		return tag;
	}
	/**
	 * 格式化内容，只保留前n个字符，并进一步确认是否要在后台加上"..."
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param num
	 *            保留的字数
	 * @param hasDot
	 *            是否显示...
	 * @return
	 */
	public static String format(String str, int num, boolean hasDot) {
		if (str == null) {
			return "";
		} else {
			if (str.getBytes().length < num * 2) {
				return str;
			} else {
				byte[] ss = str.getBytes();
				byte[] bs = new byte[num * 2];
				for (int i = 0; i < bs.length; i++) {
					bs[i] = ss[i];
				}
				String subStr = CommUtil.substring(str, num * 2);
				if (hasDot) {
					subStr = subStr + "...";
				}
				return subStr;
			}
		}
	}

	/**
	 * 格式化内容，只保留前n个字符
	 * 
	 * @param str
	 * @param num
	 * @return
	 */
	public static String format(String str, int num) {
		return format(str, num, false);
	}

	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public static CommUtil getInstance() {
		return util;
	}

	public static String getOnlyID() {
		String strRnd;
		double dblTmp;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMDDhhmmss");
		dblTmp = Math.random() * 100000;
		while (dblTmp < 10000)
			dblTmp = Math.random() * 100000;
		strRnd = String.valueOf(dblTmp).substring(0, 4);
		String s = df.format(new Date()) + strRnd;
		return s;
	}

	/**
	 * 随机生成指定位数且不重复的字符串.去除了部分容易混淆的字符，如1和l，o和0等，
	 * 
	 * 随机范围1-9 a-z A-Z
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 返回指定位数且不重复的字符串
	 */
	public static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(57)];
			if (bu.indexOf(temp) == -1)
				bu.append(temp);
		}
		return bu.toString();
	}

	/**
	 * 生成指定位数的随机字符串
	 * 
	 * @param length
	 *            位数
	 * @return
	 */
	public static String getRandString(int length) {
		StringBuffer s = new StringBuffer("" + (new Date().getTime()));
		Random r = new Random(10);
		s.append(r.nextInt());
		if (s.toString().length() > length)
			return s.substring(0, length);
		return s.toString();
	}

	/**
	 * @deprecated 使用TagUtil中的options方法代替
	 * @param min
	 * @param max
	 * @param value
	 * @return 得到列表框值的结果集
	 */
	public static String getSelectOptions(int min, int max, int value) {
		String s = "";
		for (int i = min; i <= max; i++)
			s += "<option value='" + i + "' " + (i == value ? "selected" : "")
					+ ">" + i + "</option>";
		return s;
	}

	/**
	 * @deprecated 使用TagUtil中的options方法代替
	 * @param items
	 *            列表名称
	 * @param value
	 *            选定值
	 * @return 得到列表框值的结果集
	 */
	public static String getSelectOptions(String[][] items, String value) {
		String s = "";
		for (String[] element : items)
			s += "<option value='" + element[0] + "' "
					+ (element[0].equals(value) ? "selected" : "") +

					">" + element[1] + "</option>";
		return s;
	}

	/**
	 * 把一个字符串或字符串数组转换成一个字符串数组
	 * 
	 * @param obj
	 *            字符串或字符串数组对象
	 * @return 新的字符串数组
	 */
	public static String[] getStringArray(Object obj) {
		if (obj == null)
			return null;
		if (obj.getClass().isArray())
			return (String[]) obj;
		else
			return new String[] { obj.toString() };
	}

	/**
	 * 判断是否是中文字符
	 * 
	 * @param c
	 *            字符
	 * @return
	 */
	public static boolean isChinese(char c) {
		if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
				|| (c >= 'A' && c <= 'Z'))
			// 字母, 数字
			return false;
		else if (Character.isLetter(c))
			return true;
		else
			return false;
	}

	/**
	 * 将一个map中的值根据拷贝到一个对象中，map的key与obj的字段名对应。例如：map.get("a")的值与obj.a对应。
	 * @deprecated 使用map2obj代替
	 * @param map
	 * @param obj
	 */
	public static void Map2Obj(Map map, Object obj) {
		map2obj(map,obj);
	}
	public static void map2obj(Map map, Object obj) {
/*		BeanWrapper wrapper = new BeanWrapper(obj);
		PropertyDescriptor descriptors[] = wrapper.getPropertyDescriptors();
		for (PropertyDescriptor element : descriptors) {
			String name = element.getName();
			Object v = map.get(name);
			if (v != null && element.getWriteMethod() != null)
				wrapper.setPropertyValue(name, v);
		}*/
	}

	/**
	 * 将一个对象转换为int型，如果对象为空则返回0。注意：对象s必须为可以转换为int的对象，如数字字符串、Integer类型对象等。
	 * 
	 * @param s
	 * @return
	 */
	public static int null2Int(Object s) {
		int v = 0;
		if (s != null)
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	/**
	 * 将对象转换为字符串，如果对象为空则返回""。
	 * 
	 * @param s
	 * @return
	 */
	public static String null2String(Object s) {
		return s == null ? "" : s.toString();
	}

	public static boolean String2Bool(String s)
	{
		boolean boolresult = null2String(s).equalsIgnoreCase("true") ? true : false;
		return boolresult;
	}
	/**
	 * 将一个对象中的属性值拷贝到一个map中，字段名为key。是Map2Obj方法的反向操作。
	 * @deprecated 尽量改用obj2map方法
	 * @param obj
	 * @param map
	 */
	public static void Obj2Map(Object obj, Map map) {
/*		if (map == null)
			map = new java.util.HashMap();
		BeanWrapper wrapper = new BeanWrapper(obj);
		PropertyDescriptor descriptors[] = wrapper.getPropertyDescriptors();
		for (PropertyDescriptor element : descriptors) {
			String name = element.getName();
			try {
				if (element.getReadMethod() != null)
					map.put(name, wrapper.getPropertyValue(name));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
	}

	/**
	 * 将一个double类型的数值保留param位小数，最后返回这个数值的字符串形式。
	 * 
	 * @param inNumber
	 * @param param
	 * @return
	 */
	public static String round(double inNumber, int param) {
		String format = "#.";
		for (int i = 0; i < param; i++)
			format = format.concat("#");
		// 去掉多余小数点
		if (param == 0)
			format = format.substring(0, format.length() - 1);
		DecimalFormat df = new DecimalFormat(format);
		return df.format(inNumber);
	}



	/**
	 * @deprecated 使用TagUtil的showPageHtml方法代替 显示页码
	 * @param currentPage
	 * @param pages
	 * @return 分布htmp字符串
	 */
	public static String showPageHtml(int currentPage, int pages) {
		String s = "";
		if (currentPage > 1) {
			s += "<a href=# onclick='return gotoPage(1)'>首页</a> ";
			s += "<a href=# onclick='return gotoPage(" + (currentPage - 1)
					+ ")'>上一页</a> ";
		}

		int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
		if (beginPage < pages) {
			s += "第　";
			for (int i = beginPage, j = 0; i <= pages && j < 6; i++, j++)
				if (i == currentPage)
					s += "<font color=red>" + i + "</font> ";
				else
					s += "<a href=# onclick='return gotoPage(" + i +

					")'>" + i + "</a> ";
			s += "页　";
		}
		if (currentPage < pages) {
			s += "<a href=# onclick='return gotoPage(" + (currentPage + 1)
					+ ")'>下一页</a> ";
			s += "<a href=# onclick='return gotoPage(" + pages + ")'>末页</a> ";
		}
		// s+=" 转到<input type=text size=2>页";
		return s;
	}

	/**
	 * @deprecated 使用TagUtil中同名的showPublishPageHtml方法代替
	 * @param path
	 * @param currentPage
	 * @param pages
	 * @return 以静态方式发布的页面分页连接
	 */
	public static String showPublishPageHtml(String path, int currentPage,
			int pages) {
		String s = "";
		boolean isDir = false;
		if (path.endsWith("/"))
			isDir = true;
		if (currentPage > 1) {
			s += "<a href=" + path + (isDir ? "1" : "") + ".html>首页</a> ";
			s += "<a href=" + path
					+ (currentPage - 1 > 1 ? (currentPage - 1) + "" :

					"") + ".html>上一页</a> ";
		}
		int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
		if (beginPage < pages) {
			s += "第　";
			for (int i = beginPage, j = 0; i <= pages && j < 6; i++, j++)
				if (i == currentPage)
					s += "<font color=red>" + i + "</font> ";
				else
					s += "<a href=" + path + (i > 1 ? i + "" : (isDir ? i + ""

					: "")) + ".html>" + i + "</a> ";
			s += "页　";
		}
		if (currentPage < pages) {
			s += "<a href=" + path + (currentPage + 1) + ".html>下一页</a> ";
			s += "<a href=" + path + pages + ".html>末页</a> ";
		}
		// s+=" 转到<input type=text size=2>页";
		return s;
	}

	/**
	 * 截取一个字符串的前maxLength位，并在字符串尾加上"..."
	 * 
	 * @param s
	 * @param maxLength
	 * @return
	 */
	public static String substring(String s, int maxLength) {
		if (s.isEmpty())
			return s;
		if (s.getBytes().length <= maxLength)
			return s;
		int i = 0;
		for (int k = 0; k < maxLength && i < s.length(); i++, k++)
			if (s.charAt(i) > '一')
				k++;
		if (i < s.length())
			s = s.substring(0, i) + "...";
		return s;
	}

	/**
	 * 把字符串strvalue转换为"GBK"编码
	 * 
	 * @param strvalue
	 * @return
	 */
	public static String toChinese(String strvalue) {
		try {
			if (strvalue == null)
				return null;
			else {
				strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
				return strvalue;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 把字符串strvalue转换为charset指定的字符编码格式，如果charset为空则转换为"GBK"编码。
	 * 
	 * @param strvalue
	 * @param charset
	 * @return
	 */
	public static String toChinese(String strvalue, String charset) {
		try {
			if (charset == null || charset.equals(""))
				return toChinese(strvalue);
			else {
				strvalue = new String(strvalue.getBytes("ISO8859_1"), charset);
				return strvalue;
			}
		} catch (Exception e) {
			return null;
		}
	}


	public static String subStrUtf8(String str, int start, int end)
			throws UnsupportedEncodingException{

		if (str == null)  return null;
		String chinese = "[\u0391-\uFFE5]";
		byte[] b = str.getBytes("UTF-8");

		String temp = new String(b, start, end);
		String last = temp.substring(temp.length()-1,1);
		while(!last.matches(chinese)){
			temp = new String(b, start, ++end);
			last=temp.substring(temp.length()-1,1);
		}

		return new String(b, start, end);
	}


	public static String getByteStr(String str, int start, int end) throws UnsupportedEncodingException {
		byte[] b = str.getBytes("UTF-8");

		return new String(b, start, end);
	}
	/**
	 * 把字符串s转换为unicode编码
	 * 
	 * @param s
	 * @param flag
	 * @return
	 */
	public static String convert2unicode(String s, boolean flag) {
		int i = s.length();
		int j = i * 2;
		if (j < 0)
			j = 2147483647;
		StringBuffer stringbuffer = new StringBuffer(j);
		for (int k = 0; k < i; k++) {
			char c = s.charAt(k);
			if (c > '=' && c < '\177') {
				if (c == '\\') {
					stringbuffer.append('\\');
					stringbuffer.append('\\');
				} else {
					stringbuffer.append(c);
				}
				continue;
			}
			switch (c) {
			case 32: // ' '
				if (k == 0 || flag)
					stringbuffer.append('\\');
				stringbuffer.append(' ');
				break;

			case 9: // '\t'
				stringbuffer.append('\\');
				stringbuffer.append('t');
				break;

			case 10: // '\n'
				stringbuffer.append('\\');
				stringbuffer.append('n');
				break;

			case 13: // '\r'
				stringbuffer.append('\\');
				stringbuffer.append('r');
				break;

			case 12: // '\f'
				stringbuffer.append('\\');
				stringbuffer.append('f');
				break;

			case 33: // '!'
			case 35: // '#'
			case 58: // ':'
			case 61: // '='
				stringbuffer.append('\\');
				stringbuffer.append(c);
				break;

			default:
				if (c < ' ' || c > '~') {
					stringbuffer.append('\\');
					stringbuffer.append('u');
					stringbuffer.append(toHex(c >> 12 & 15));
					stringbuffer.append(toHex(c >> 8 & 15));
					stringbuffer.append(toHex(c >> 4 & 15));
					stringbuffer.append(toHex(c & 15));
				} else {
					stringbuffer.append(c);
				}
				break;
			}
		}

		return stringbuffer.toString();
	}

	/**
	 * unicode 转换成中文
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException(
										"Malformed   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}
	public static String convert2json(String s) {
		boolean flag=true;
		int i = s.length();
		int j = i * 2;
		if (j < 0)
			j = 2147483647;
		StringBuffer stringbuffer = new StringBuffer(j);
		for (int k = 0; k < i; k++) {
			char c = s.charAt(k);
			if (c > '=' && c < '\177') {
				if (c == '\\') {
					stringbuffer.append('\\');
					stringbuffer.append('\\');
				} else {
					stringbuffer.append(c);
				}
				continue;
			}
			switch (c) {
			case 32: // ' '
				if (k == 0 || flag)
					stringbuffer.append('\\');
				stringbuffer.append(' ');
				break;

			case 9: // '\t'
				stringbuffer.append('\\');
				stringbuffer.append('t');
				break;

			case 10: // '\n'
				stringbuffer.append('\\');
				stringbuffer.append('n');
				break;

			case 13: // '\r'
				stringbuffer.append('\\');
				stringbuffer.append('r');
				break;

			case 12: // '\f'
				stringbuffer.append('\\');
				stringbuffer.append('f');
				break;

			case 33: // '!'
			case 35: // '#'
			case 58: // ':'
			case 61: // '='
				stringbuffer.append('\\');
				stringbuffer.append(c);
				break;
			default:
					stringbuffer.append(c);
				break;
			}
		}
		return stringbuffer.toString();
	}

	/**
	 * 将一个list分为preNum个子List，并将这些子List作为元素添加到另一个List中，最终返回这个List。
	 * 
	 * @param list
	 * @param perNum
	 * @return
	 */
	public static List toRowChildList(List list, int perNum) {
		List l = new ArrayList();
		if (list == null)
			return l;
		for (int i = 0; i < list.size(); i += perNum) {
			List cList = new ArrayList();
			for (int j = 0; j < perNum; j++)
				if (i + j < list.size())
					cList.add(list.get(i + j));
			l.add(cList);
		}
		return l;
	}

	/**
	 * 将list中的元素拆分为若干个List，每个子List包含perNum个元素，然后将这些子List作为元素添加到另一个List中并返回这个List
	 * 。
	 * 
	 * @param list
	 * @param perNum
	 * @return
	 */
	public static List toRowDivList(List list, int perNum) {
		List l = new ArrayList();
		if (list == null)
			return l;
		for (int i = 0; i < list.size();) {
			List cList = new ArrayList();
			for (int j = 0; j < perNum; j++, i++)
				if (i < list.size())
					cList.add(list.get(i));
			l.add(cList);
		}
		return l;
	}

	/**
	 * 把数字i转换为16进制
	 * 
	 * @param i
	 * @return
	 */
	private static char toHex(int i) {
		return hexDigit[i & 15];
	}

	private static final char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	
	/**
	 * 某一个日期是否属于最近几天
	 * @param date
	 * @param days
	 * @return
	 */
	public boolean lastDay(Date date,Integer days){
		if(date==null) return false;
		Calendar ca= Calendar.getInstance();
		//int m=ca.get(Calendar.MONTH);
		ca.roll(Calendar.DAY_OF_YEAR, -days);
		//if(ca.get(Calendar.MONTH)!=m)ca.roll(Calendar.YEAR, -1);
		//System.out.println(ca.getTime());
		return date.after(ca.getTime());
	}
    public static String genTableClassName(String className){
        return "Tb"+fisrtCharToUpCase(className);
    }

    public static ArrayList rsToArrayList(ResultSet rs) {
        ArrayList rows = new ArrayList();
        try {
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                HashMap row = new HashMap();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {

                    String cname = rsmd.getColumnName(i);
                    String cvalue = rs.getString(i);
                    row.put(cname, cvalue);
                    //System.out.println(cname+"  "+cvalue);
                }
                rows.add(row);
            }

        } catch (Exception e) {

        }

        return rows;
    }

    public static String fisrtlineDelToUp(String s) {
        if (s != null && s.indexOf("_") >= 0) {
            s = s.replaceAll("_", "");
        }

        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static String fisrtCharToLowerCaseAll(String s) {
        s = s.toLowerCase();
        StringTokenizer st = new StringTokenizer(s, "_");
        StringBuffer sb = new StringBuffer();
        while (st.hasMoreTokens()) {
            String a = st.nextToken();
            a = a.substring(0, 1).toUpperCase() + a.substring(1).toLowerCase();
            sb.append(a);
        }
        st = null;
        String t = sb.toString();
        t = t.substring(0, 1).toLowerCase() + t.substring(1);
        return t;
    }
    public static String fisrtCharToLowerCase(String s) {
        s = s.substring(0, 1).toLowerCase() + s.substring(1);
        return s;
    }
    public static String fisrtCharToUpCase(String s) {
        s = s.substring(0, 1).toUpperCase() + s.substring(1);
        return s;
    }

    public static String fisrtCharToUpCaseAll(String s) {
        //s = s.toLowerCase();
        StringTokenizer st = new StringTokenizer(s, "_");
        StringBuffer sb = new StringBuffer();
        while (st.hasMoreTokens()) {
            String a = st.nextToken();
            a = a.substring(0, 1).toUpperCase() + a.substring(1);
            sb.append(a);
        }
        st = null;
        return sb.toString();
    }

    public static String delAcross(String s) {
        if (s != null && s.indexOf("_") >= 0) {
            s = s.replaceAll("_", "");
        }
        return s;
    }
	public static String replace(String inString, String oldPattern,
			String newPattern) {
		if (inString == null)
			return null;
		if (oldPattern == null || newPattern == null)
			return inString;

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));
		return sbuf.toString();
	}

    public static String CompressType(String javaType) {
        return javaType.substring(javaType.lastIndexOf(".") + 1);
    }

    public static void printContent(List list) {

        Map row = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            row = (HashMap) list.get(i);
            Iterator it = row.keySet().iterator();
            while (it.hasNext()) {
                Object obj = it.next();
                System.out.print(obj + "(" + row.get(obj) + "),");
            }
            System.out.println("");
        }

    }
    /**
    ****************************************************
    * @Title: printContent
    * @Description: 打印HashMap
    * @param hashMap
    ****************************************************
     */
    public static void printContent(HashMap hashMap) {

        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            System.out.print(obj + "(" + hashMap.get(obj) + "),");
        }
        System.out.println("");

    }
    /**
     * 
    ****************************************************
    * @Title: webPrint
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param contentString
    ****************************************************
     */
	public static void webPrint(HttpServletResponse response,
			String contentString) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		out.print(contentString);
		out.flush();
		out.close();
	}

	/**
	 * 获取IP地址的方法
	 * @param request http请求来源
	 * @return ip
	 */
	public static String getUserIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info("Proxy-Client-IP:{}",ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info("WL-Proxy-Client-IP:{}",ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			logger.info("getRemoteAddr:{}",ip);
		}
		if (ip!=null&&ip.length()>10)
		{
			String[] ips=ip.split(",");
			return ips[0];
		}
		logger.info("get real ip:{}",ip);
		return ip;
	}


	/**
	 * 调用百度接口，根据ip查询城市
	 * @param strIP
	 * @return
	 */
	public static String getAddressByBD2(String strIP) {
		try {
			URL url = new URL("http://opendata.baidu.com/api.php?query=" + strIP+"&co=&resource_id=6006&t=1433920989928&ie=utf8&oe=utf-8&format=json");;
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line = null;
			StringBuffer result = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			String location = searchValue(result.toString(), "location");
			if (StringUtils.isNotBlank(location)) {
				location = location.substring(0,location.indexOf(" ") == -1 ? location.length():location.indexOf(" "));
			}
			return location;
		} catch (IOException e) {
			return "杭州";
		}
	}

	private static String searchValue(String remoteIpInfo,String key){
		String _value="";
		if(StringUtils.isNotBlank(remoteIpInfo)){
			_value=StringUtils.substringBetween(remoteIpInfo,"\""+key+"\":", ",");
			if(StringUtils.isNotBlank(_value)){
				_value=decodeUnicode(_value);
			}
		}
		return StringUtils.isBlank(_value) ? _value : _value.replaceAll("\"","");
	}

	public static Object[] getInfoByIDCard(String idCard){
		Object[] obj = null;
		String sex=null;
		try {
			if(idCard != null){
				if(idCard.length()==18){//二代身份证
					obj=new Object[3];
					String str=idCard.substring(16,17);
					if(Integer.parseInt(str)%2==0){//偶数
						sex="女";
					}
					if(Integer.parseInt(str)%2!=0){//奇数
						sex="男";
					}
					obj[0]=sex;//性别
					String birthday=idCard.substring(6,14);//身份证的第7位到第14位是生日

					Integer age=new Date().getYear()-new SimpleDateFormat("yyyyMMdd").parse(birthday).getYear();
					obj[1]=String.valueOf(age);//年龄
					String birthdaystr=new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyyMMdd").parse(birthday));
					obj[2]=birthdaystr;//生日
				}
				if(idCard.length()==15){//一代身份证
					obj=new Object[3];
					String str=idCard.substring(14,15);
					if(Integer.parseInt(str)%2==0){//偶数
						sex="女";
					}
					if(Integer.parseInt(str)%2!=0){//奇数
						sex="男";
					}
					obj[0]=sex;
					String birthday=("19"+idCard.substring(6,12)).toString();//身份证的第7位到第12位是生日
					Integer age=new Date().getYear()-new SimpleDateFormat("yyyyMMdd").parse(birthday).getYear();
					obj[1]=String.valueOf(age);//年龄
					String birthdaystr=new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyyMMdd").parse(birthday));
					obj[2]=birthdaystr;//生日
				}
			}
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

    /**
     * 获取集合V中字段fieldName的集合
     * @param sourceList
     * @param fieldName
     * @param <K>
     * @param <V>
     * @return
     * @auth Zhouyang Sun
     */
    public static <K,V> List<K> listProperty(Collection<? extends V> sourceList ,String fieldName, Class<? extends K>... keyClass){
        List<K> targetSet = new ArrayList<K>();
        if(CollectionUtils.isEmpty(sourceList)){
            return targetSet;
        }

        for (V v : sourceList) {
            try {
                K k = (K) BeanUtil.getPrivateNestedProperty(v, fieldName);
                targetSet.add(k);
            } catch (Exception e) {

            }
        }
        targetSet.remove(null);
        return targetSet;
    }

    /**
     * 将用,隔开的字符串转换成id列表(抛出异常)
     * @param idString
     * @return
     * @throws Exception
     */
    public static List<Integer> getIdListFromStringException(String idString) throws Exception {
        if (StringUtils.isBlank(idString))
            throw new Exception("参数为空");
        String[] idArray = StringUtils.split(idString,",");
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <=idArray.length; i++) {
            list.add(Integer.parseInt(idArray[i]));
        }
        return list;
    }

    /**
     * 将用,隔开的字符串转换成id列表
     * @param idString
     * @return
     */
    public static List<Integer> getIdListFromString(String idString)  {
        if (StringUtils.isBlank(idString))
            return null;
        String[] idArray = StringUtils.split(idString,",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <idArray.length; i++) {
            list.add(Integer.parseInt(idArray[i]));
        }
        return list;
    }

	/**
	 * 将集合按特定字段分组
	 * @param beanList
	 * @param groupByFieldName
	 * @param keyClass
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K, V> TreeMap<K, List<V>> groupBy(List<V> beanList,
													 String groupByFieldName, Class<? extends K>... keyClass) {
		TreeMap<K, List<V>> map = new TreeMap<K, List<V>>();
		if (CollectionUtils.isEmpty(beanList)) {
			return map;
		}
		for (V v : beanList) {
			try {
				K k = (K) BeanUtil.getPrivateNestedProperty(v, groupByFieldName);
				List<V> tempList = map.get(k);
				if (tempList == null) {
					tempList = new ArrayList<V>();
					map.put(k, tempList);
				}
				tempList.add(v);
			} catch (Exception e) {
			}
		}
		return map;
	}

	/**
	 * 获取全模糊匹配字符
	 * @param source
	 * @return
	 */
	public static String getFullFuzzyString(String source) {
		if (StringUtils.isBlank(source))
			return null;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i<source.length();i++) {
			buffer.append("%").append(source.charAt(i));
		}
		buffer.append("%");
		return buffer.toString();
	}

	/**
	 * 对两个数进行四则运算; <strong>对于除法的运算,保留4位小数;</strong><br>
	 * result = num1+num2;<br>
	 * result = num1-num2;<br>
	 * result = num1*num2;<br>
	 * result = num1/num2;<br>
	 *
	 * @param num1
	 *            式子中的第一个数;
	 * @param num2
	 *            式子中的第二个数;
	 * @param calcSymbol
	 *            运算符号 "+" "-" "*" "/"
	 * @return BigDecimal
	 * @throws Exception
	 *             计算异常;
	 */
	public static BigDecimal calcNumber(Object num1, Object num2, String calcSymbol) throws Exception {
		return calcNumber(num1, num2, calcSymbol, 4);

	}

	/**
	 * 对两个数进行四则运算; 自行选择保留位数;不四舍五入,直接截取; result = num1+num2;<br>
	 * result = num1-num2;<br>
	 * result = num1*num2;<br>
	 * result = num1/num2;<br>
	 *
	 * @param num1
	 *            式子中的第一个数;
	 * @param num2
	 *            式子中的第二个数;
	 * @param calcSymbol
	 *            运算符号 "+" "-" "*" "/"
	 * @param remainNum
	 *            保留多少位小数; 如果小于0,则为0;
	 * @return BigDecimal
	 * @throws Exception
	 *             计算异常;
	 */
	public static BigDecimal calcNumber(Object num1, Object num2, String calcSymbol, int remainNum) throws Exception {
		remainNum = remainNum < 0 ? 0 : remainNum;
		if (!isNullAndEmpty(num1) && !isNullAndEmpty(num2)) {
			BigDecimal decimal = new BigDecimal(num1.toString());
			BigDecimal decima2 = new BigDecimal(num2.toString());
			if ("+".equals(calcSymbol)) {
				return decimal.add(decima2);
			} else if ("-".equals(calcSymbol)) {
				return decimal.subtract(decima2);
			} else if ("*".equals(calcSymbol)) {
				return decimal.multiply(decima2);
			} else if ("/".equals(calcSymbol)) {
				if (!num2.equals("0")) {
					return decimal.divide(decima2, remainNum, BigDecimal.ROUND_DOWN);
				} else {
					throw new Exception();
				}
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 判断object是否为null或者为"" true:没有数据;<br>
	 * true:没有数据;<br>
	 * false:有数据;
	 *
	 * @param obj
	 *            Object
	 * @return boolean
	 */
	public static boolean isNullAndEmpty(Object obj) {
		if (obj != null && !"".equals(obj.toString()) && !("null".equalsIgnoreCase(obj.toString()))) {
			return false;
		} else {
			return true;
		}
	}

    public static String getUniqueNumberByRandom(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"_"+((int)(Math.random() * 1000000));
	}
	/**
	 * 向一个老日期追加天数,返回一个新日期;
	 *
	 * @param oldDate
	 *            被追加天数的日期;
	 * @param days
	 *            追加的天数;
	 * @return 返回一个日历对象,追加天数后的日期;通过Calendar.getTime()可获得;
	 */
	public static Calendar addDaysFromOldDate(Date oldDate, int days) {
		// 添加结束时间;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.DATE, days);
		return calendar;
	}


}
