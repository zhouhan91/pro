/**
 * 版权声明
 */
package com.wemeCity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * IBatis文件生成
 * 
 * 注意注意：以下信息需要配置 作者：AUTHOR 就是你自己的名字 生成代码存放位置：FILE_PATH 就是生成代码存放的目录，会生成action,appservice,dao,map,model文件夹及文件 数据库服务器地址：DATABASE_LOCATION 这个就不解释了
 * 数据库端口:PORT 这个也不解释了 数据库名称：DATABASE_NAME 这个更不想解释了 数据库用户名：USER_NAME 这个照样不解释 数据库用户密码：PASSWORD 这个还是不解释 ibatis的xml文件命名空间： nameSpace
 * 对应ibatis的xml文件里的<mapper namespace= java代码包名：PACKAGE_PREFIX 这个是从工程根目录到生成代码存放位置的目录。如com.hmzone.vip 生成的vo文件就是 package
 * com.hmzone.vip.model;同理action,dao,appservice 表名： TABLES 需要生成代码的数据库表名
 * 
 *
 * @since JDK1.8
 * @history 2015年3月26 新建
 */
public class IBatisFileBuilder {

	/** 作者 */
	private final static String AUTHOR = "向小文";

	/** 编码 */
	private final static String ENCODING = "UTF-8";

	/** 生成代码存放位置 */
	private final static String FILE_PATH = "D:/workspace/project/temp";

	/** 数据库服务器地址 192.168.0.250 */
	private final static String DATABASE_LOCATION = "123.207.86.121";

	/** 数据库端口 */
	private final static int PORT = 3306;

	/** 数据库名称 */
	private final static String DATABASE_NAME = "wemecity";

	/** 数据库用户名 */
	private final static String USER_NAME = "shelven";

	/** 数据库密码 */
	private final static String PASSWORD = "shelven";

	/** sqlmap命名空间-无用 */
	private final static String nameSpace = "";

	/** java代码包名前缀 */
	private final static String PACKAGE_PREFIX = "com.wemeCity.web.catering";

	/** 去除前缀 */
	private final static String TABLE_PREFIX = "";

	/** 表名 */
	private final static String[] TABLES = new String[] {"catering_order"};
	
	/** 主键记录 */
	private final static Map<String, String> primaryKeyMap = new HashMap<String, String>();

	/** 版权声明 */
//	private final static String COPYRIGHT_NOTICES = "/********************************************\r\n * 本软件为深圳前海黄牛在线开发研制。未经本公司正式书面同意，其他任何个人、团体\r\n * 不得使用、复制、修改或发布本软件\r\n ********************************************/\r\n";
	private final static String COPYRIGHT_NOTICES = "";

	/** action包路径 */
	private final static String ACTION_PATH = FILE_PATH + "\\controller\\";

	/** service包路径 */
	private final static String SERVICE_PATH = FILE_PATH + "\\service\\";
	private final static String SERVICE_PACKAGE = PACKAGE_PREFIX + ".service";
	private final static String SERVICE_IMPL_PATH = SERVICE_PATH + "impl\\";
	private final static String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

	/** dao包路径 */
	private final static String DAO_PATH = FILE_PATH + "\\mapper\\";
	private final static String DAO_PACKAGE = PACKAGE_PREFIX + ".mapper";
	private final static String DAO_IMPL_PATH = DAO_PATH + "impl\\";
	private final static String DAO_IMPL_PACKAGE = DAO_PACKAGE + ".impl";

	/** vo包路径 */
	private final static String VO_PATH = FILE_PATH + "\\model\\";
	private final static String VO_PACKAGE = PACKAGE_PREFIX + ".model";

	/** exception */
	private final static String EXCEPTION_PATH = FILE_PATH + "\\exception\\";
	private final static String EXCEPTION_PACKAGE = PACKAGE_PREFIX + ".exception";

	/** sqlmap配置文件路径 */
	private final static String SQLMAP_PATH = FILE_PATH + "\\mapper\\";

	/** 缩进 */
	private final static String PLACEHOLDERS = "	";

	/** 数据库类型和java类型映射 */
	private final static Map<String, String> TYPE_MAPPING = new HashMap<String, String>();

	/** 数据库类型与ibatis类型映射 */
	private final static Map<String, String> TYPE_IBATIS = new HashMap<String, String>();

	static {
		TYPE_MAPPING.put("CHAR", "char");
		TYPE_MAPPING.put("VARCHAR", "String");
		TYPE_MAPPING.put("TEXT", "String");
		TYPE_MAPPING.put("LONGTEXT", "String");
		TYPE_MAPPING.put("INT", "int");
		TYPE_MAPPING.put("BIGINT", "long");
		TYPE_MAPPING.put("FLOAT", "float");
		TYPE_MAPPING.put("DOUBLE", "double");
		TYPE_MAPPING.put("DATE", "LocalDate");
		TYPE_MAPPING.put("DATETIME", "LocalDateTime");
		TYPE_MAPPING.put("TIMESTAMP", "LocalDateTime");
		TYPE_MAPPING.put("DECIMAL", "BigDecimal");

		TYPE_IBATIS.put("CHAR", "CHAR");
		TYPE_IBATIS.put("VARCHAR", "VARCHAR");
		TYPE_IBATIS.put("TEXT", "VARCHAR");
		TYPE_IBATIS.put("LONGTEXT", "VARCHAR");
		TYPE_IBATIS.put("INT", "INTEGER");
		TYPE_IBATIS.put("BIGINT", "BIGINT");
		TYPE_IBATIS.put("FLOAT", "FLOAT");
		TYPE_IBATIS.put("DOUBLE", "DOUBLE");
		TYPE_IBATIS.put("DATE", "DATE");
		TYPE_IBATIS.put("DATETIME", "TIMESTAMP");
		TYPE_IBATIS.put("TIMESTAMP", "TIMESTAMP");
		TYPE_IBATIS.put("DECIMAL", "DECIMAL");
	}

	public static void main(String[] args) {
		// 获取数据表信息
		Map<String, List<Column>> tableDetail = readTableInfo();
		// 创建文件夹
		createDir();
		// 创建异常类
		createException();
		// 创建VO
		createVO(tableDetail);
		// 创建ibatis配置文件
		createIbatisXML(tableDetail);
		// 创建DAO
		createDAOInterface();
		// 创建appservice
		createServiceInterface();
		createServiceImpl();
		// 创建controller
	}

	/**
	 * 创建异常类
	 * 
	 * @Author Shelven 2015年4月1日
	 */
	public static void createException() {
		try {
			for (String tableName : TABLES) {
				if (!"".equals(TABLE_PREFIX) && null != TABLE_PREFIX) {
					tableName = tableName.replaceFirst(TABLE_PREFIX, "");
				}
				String fileName = formatSQLName(tableName, true) + "Exception";
				File file = new File(EXCEPTION_PATH + fileName + ".java");
				if (!file.exists())
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				Writer pw = new OutputStreamWriter(fos, ENCODING);
				// 版权声明
				pw.write(COPYRIGHT_NOTICES);
				// 包
				pw.write("\r\npackage " + EXCEPTION_PACKAGE + ";\r\n\r\n");
				// import
				pw.write(getClassComment(" 模块异常类", fileName));
				pw.write("public class " + fileName + " extends Exception{\r\n\r\n");
				pw.write(PLACEHOLDERS + "private static final long serialVersionUID = 1L;\r\n\r\n");
				pw.write(PLACEHOLDERS + "public " + fileName + "(String message, Exception e){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "super(message, e);\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");
				pw.write(PLACEHOLDERS + "public " + fileName + "(String message){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "super(message);\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");
				pw.write("}");
				pw.flush();
				pw.close();
				fos.close();
				System.out.println(EXCEPTION_PATH + fileName + ".java创建完成...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建serviceimpl
	 * 
	 * @Author Shelven 2015年4月1日
	 */
	public static void createServiceImpl() {
		try {
			Calendar calendar = Calendar.getInstance();
			for (String tableName : TABLES) {
				if (!"".equals(TABLE_PREFIX) && null != TABLE_PREFIX) {
					tableName = tableName.replaceFirst(TABLE_PREFIX, "");
				}
				String voName = formatSQLName(tableName, true);
				String voProperty = formatSQLName(tableName, false);
				String daoName = formatSQLName(tableName, true) + "Mapper";
				String daoProperty = formatSQLName(tableName, false) + "Mapper";
				String exceptionName = formatSQLName(tableName, true) + "Exception";
				String fileName = formatSQLName(tableName, true) + "ServiceImpl";
				File file = new File(SERVICE_IMPL_PATH + fileName + ".java");
				if (!file.exists())
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				Writer pw = new OutputStreamWriter(fos, ENCODING);
				// 版权声明
				pw.write(COPYRIGHT_NOTICES);
				// 包
				pw.write("\r\npackage " + SERVICE_IMPL_PACKAGE + ";\r\n\r\n");
				// import
				pw.write("import java.util.List;\r\n");
				pw.write("import java.util.Map;\r\n\r\n");
				pw.write("import org.slf4j.Logger;\r\n");
				pw.write("import org.slf4j.LoggerFactory;\r\n");
				pw.write("import org.springframework.stereotype.Service;\r\n");
				pw.write("import org.springframework.beans.factory.annotation.Autowired;\r\n\r\n");
				pw.write("import " + DAO_PACKAGE + "." + daoName + ";\r\n");
				pw.write("import " + VO_PACKAGE + "." + voName + ";\r\n");
				pw.write("import " + EXCEPTION_PACKAGE + "." + exceptionName + ";\r\n");
				pw.write("import " + SERVICE_PACKAGE + "." + formatSQLName(tableName, true) + "Service;\r\n");
				pw.write(getClassComment(" AppService类", fileName));
				pw.write("@Service\r\n");
				pw.write("public class " + fileName + " implements " + formatSQLName(tableName, true) + "Service{\r\n\r\n");
				// 日志对象
				pw.write(PLACEHOLDERS + "private Logger logger=LoggerFactory.getLogger("+fileName+".class);\r\n\r\n");
				// dao对象
				pw.write(PLACEHOLDERS + "/** 数据访问接口 */\r\n");
				pw.write(PLACEHOLDERS + "@Autowired\r\n");
				pw.write(PLACEHOLDERS + "private " + daoName + " " + daoProperty + ";\r\n\r\n");

				// 增
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 新增" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param " + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " * @return 新增的对象\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS +"@Override\r\n");
				pw.write(PLACEHOLDERS + "public " + "void insert" + voName + "(" + voName + " " + voProperty + ") throws " + exceptionName
						+ "{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "try{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + daoProperty + ".insert" + voName + "(" + voProperty + ");\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}catch(Exception e){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "logger.error(\"新增" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "throw new " + exceptionName + "(\"新增" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 删
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 删除" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param id 主键\r\n");
				pw.write(PLACEHOLDERS + " * @return \r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS +"@Override\r\n");
				pw.write(PLACEHOLDERS + "public int delete" + voName + "(long id) throws " + exceptionName + "{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "try{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "return this." + daoProperty + ".delete" + voName + "(id);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}catch(Exception e){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "logger.error(\"删除" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "throw new " + exceptionName + "(\"删除" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 改
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 修改" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return \r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS +"@Override\r\n");
				pw.write(PLACEHOLDERS + "public int update" + voName + "(Map<String, Object> condition) throws " + exceptionName + "{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "try{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "return this." + daoProperty + ".update" + voName + "(condition);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}catch(Exception e){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "logger.error(\"修改" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "throw new " + exceptionName + "(\"修改" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 查
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param id 主键\r\n");
				pw.write(PLACEHOLDERS + " * @return 根据主键查找的内容\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS +"@Override\r\n");
				pw.write(PLACEHOLDERS + "public " + voName + " read" + voName + "(long id) throws " + exceptionName + "{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "try{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "return this." + daoProperty + ".read" + voName + "(id);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}catch(Exception e){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "logger.error(\"查询" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "throw new " + exceptionName + "(\"查询" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 查询集合
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "集合\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition 查询条件\r\n");
				pw.write(PLACEHOLDERS + " * @return 符合查询条件的记录\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS +"@Override\r\n");
				pw.write(PLACEHOLDERS + "public List<" + voName + "> query" + voName + "List(Map<String, Object> condition)" + "{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "try{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "return this." + daoProperty + ".query" + voName + "List(condition);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}catch(Exception e){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "logger.error(\"查询" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "return null;\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 查询列表数量
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "集合\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition 查询条件\r\n");
				pw.write(PLACEHOLDERS + " * @return 符合查询条件的记录数\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS +"@Override\r\n");
				pw.write(PLACEHOLDERS + "public long query" + voName + "Count(Map<String, Object> condition) throws " + exceptionName + "{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "try{\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "return this." + daoProperty + ".query" + voName + "Count(condition);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}catch(Exception e){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "logger.error(\"查询" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "throw new " + exceptionName + "(\"查询" + voName + "时报错\", e);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				pw.write("}");
				pw.flush();
				fos.close();
				pw.close();
				System.out.println(SERVICE_IMPL_PATH + fileName + ".java创建完成...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建service接口
	 * 
	 * @Author Shelven 2015年3月31日
	 */
	public static void createServiceInterface() {
		try {
			Calendar calendar = Calendar.getInstance();
			for (String tableName : TABLES) {
				if (!"".equals(TABLE_PREFIX) && null != TABLE_PREFIX) {
					tableName = tableName.replaceFirst(TABLE_PREFIX, "");
				}
				String voName = formatSQLName(tableName, true);
				String voProperty = formatSQLName(tableName, false);
				String fileName = formatSQLName(tableName, true) + "Service";
				String exceptionName = formatSQLName(tableName, true) + "Exception";
				File file = new File(SERVICE_PATH + fileName + ".java");
				if (!file.exists())
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				Writer pw = new OutputStreamWriter(fos, ENCODING);
				// 版权声明
				pw.write(COPYRIGHT_NOTICES);
				// 包
				pw.write("\r\npackage " + SERVICE_PACKAGE + ";\r\n\r\n");
				// import
				pw.write("import java.util.List;\r\n");
				pw.write("import java.util.Map;\r\n");
				pw.write("import " + EXCEPTION_PACKAGE + "." + exceptionName + ";\r\n");
				pw.write("import " + VO_PACKAGE + "." + voName + ";\r\n\r\n");
				pw.write(getClassComment(" Service接口", fileName));
				pw.write("public interface " + fileName + " {\r\n\r\n");
				// 增
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 新增" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param " + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " * @return 新增的对象\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "void insert" + voName + "(" + voName + " " + voProperty + ") throws " + exceptionName + ";\r\n\r\n");

				// 删
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 删除" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param id 主键\r\n");
				pw.write(PLACEHOLDERS + " * @return \r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "int delete" + voName + "(long id) throws " + exceptionName + ";\r\n\r\n");

				// 改
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 修改" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return \r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "int update" + voName + "(Map<String, Object> condition) throws " + exceptionName + ";\r\n\r\n");

				// 查
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param id 主键\r\n");
				pw.write(PLACEHOLDERS + " * @return 根据主键查找的内容\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + voName + " read" + voName + "(long id) throws " + exceptionName + ";\r\n\r\n");

				// 查询集合
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "集合\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return 符合查询条件的记录\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "List<" + voName + "> query" + voName + "List(Map<String, Object> condition) ;\r\n\r\n");

				// 查询列表数量
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "集合\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return 符合查询条件的记录数\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "long query" + voName + "Count(Map<String, Object> condition) throws " + exceptionName + ";\r\n\r\n");

				pw.write("}");
				pw.flush();
				fos.close();
				pw.close();
				System.out.println(SERVICE_PATH + fileName + ".java创建完成...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

	/**
	 * 创建ibatis配置文件
	 * 
	 * @param tableDetail
	 * @Author Shelven 2015年3月31日
	 */
	public static void createIbatisXML(Map<String, List<Column>> tableDetail) {
		try {
			for (String tableName : TABLES) {
				String fileName = "";
				String voName = "";
				String voPackage = "";
				String voProperty = "";
				if (!"".equals(TABLE_PREFIX) && null != TABLE_PREFIX) {
					String withOutPreFix = tableName.replaceFirst(TABLE_PREFIX, "");
					fileName = formatSQLName(withOutPreFix, true);
					voName = formatSQLName(withOutPreFix, true) + "VO";
					voPackage = VO_PACKAGE + "." + formatSQLName(withOutPreFix, true);
					voProperty = formatSQLName(withOutPreFix, false);
				} else {
					fileName = formatSQLName(tableName, true);
					voName = formatSQLName(tableName, true);
					voPackage = VO_PACKAGE + "." + voName;
					voProperty = formatSQLName(tableName, false);
				}
				File file = new File(SQLMAP_PATH + fileName + ".xml");
				if (!file.exists())
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				Writer pw = new OutputStreamWriter(fos, ENCODING);
				// 文件头
				pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
				pw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\r\n\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n\r\n");
				// namespace
				pw.write("<mapper namespace=\"" + DAO_PACKAGE + "." + formatSQLName(tableName, true) + "Mapper" + "\">\r\n\r\n");
				// 映射
				pw.write(PLACEHOLDERS + "<resultMap id=\"" + voProperty + "\" type=\"" + voPackage + "\">\r\n");
				// 主键
				String primaryKeyColumn = primaryKeyMap.get(tableName);
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "<id column=\"" + primaryKeyColumn + "\" property=\"" + formatSQLName(primaryKeyColumn, false)
						+ "\" />\r\n");
				for (Column c : tableDetail.get(tableName)) {
					pw.write(PLACEHOLDERS + PLACEHOLDERS + "<result column=\"" + c.getColumnName() + "\" property=\"" + c.getPropertyName()
							+ "\" jdbcType=\"" + TYPE_IBATIS.get(c.getDataType()) + "\" />\r\n");
				}
				pw.write(PLACEHOLDERS + "</resultMap>\r\n\r\n");

				// 增
				pw.write(PLACEHOLDERS + "<insert id=\"insert" + voName + "\" parameterType=\"" + voPackage + "\">\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "<![CDATA[\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "INSERT INTO " + tableName.toUpperCase() + " (\r\n");
				StringBuilder sbcolumn = new StringBuilder(512);
				StringBuilder sbProperty = new StringBuilder(512);
				for (Column c : tableDetail.get(tableName)) {
					if (c.getPrimaryKeyFlag()) {
						sbcolumn.append(PLACEHOLDERS).append(PLACEHOLDERS).append(PLACEHOLDERS).append(PLACEHOLDERS).append(c.getColumnName())
								.append(",\r\n");
						sbProperty.append(PLACEHOLDERS).append(PLACEHOLDERS).append(PLACEHOLDERS).append(PLACEHOLDERS).append("null,\r\n");
					} else {
						sbcolumn.append(PLACEHOLDERS).append(PLACEHOLDERS).append(PLACEHOLDERS).append(PLACEHOLDERS).append(c.getColumnName())
								.append(",\r\n");
						sbProperty.append(PLACEHOLDERS).append(PLACEHOLDERS).append(PLACEHOLDERS).append(PLACEHOLDERS).append("#{")
								.append(c.getPropertyName()).append("},\r\n");
					}
				}
				// 去除最后的逗号
				pw.write(sbcolumn.replace(sbcolumn.lastIndexOf(","), sbcolumn.lastIndexOf(",") + 1, "").toString());
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + ") VALUES (\r\n");
				pw.write(sbProperty.replace(sbProperty.lastIndexOf(","), sbProperty.lastIndexOf(",") + 1, "").toString());
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + ")\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "]]>\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "<selectKey resultType=\"long\" keyProperty=\"" + formatSQLName(primaryKeyColumn, false)
						+ "\">\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "<![CDATA[\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "SELECT LAST_INSERT_ID() AS ID FROM " + tableName.toUpperCase()
						+ " LIMIT 1\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "]]>\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "</selectKey>\r\n");
				pw.write(PLACEHOLDERS + "</insert>\r\n\r\n");

				// 删
				pw.write(PLACEHOLDERS + "<update id=\"delete" + voName + "\" parameterType=\"java.lang.Long\">\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "<![CDATA[\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "UPDATE " + tableName + " SET IS_DELETED='Y' WHERE " + primaryKeyColumn + " = #{"
						+ formatSQLName(primaryKeyColumn, false) + "}\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "]]>\r\n");
				pw.write(PLACEHOLDERS + "</update>\r\n\r\n");

				// 改
				pw.write(PLACEHOLDERS + "<update id=\"update" + voName + "\" parameterType=\"map\">\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "UPDATE "+tableName + " set modify_time=now()\r\n");
				StringBuilder sbCondition = new StringBuilder();
				Column primary=null;
				for (Column c : tableDetail.get(tableName)) {
					if ("modifyTime".equals(c.getPropertyName()) || c.getPrimaryKeyFlag()) {
						if (c.getPrimaryKeyFlag()) {
							primary = c;
						}
						continue;
					}
					sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + "<if test=\"" + c.getPropertyName() + " !=null\">\r\n");
					sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + ", " + c.getColumnName() + "=#{" + c.getPropertyName()
							+ "}\r\n");
					sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + "</if>\r\n");
				}
				if (primary != null) {
					sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + "WHERE "+primary.getColumnName()+"=#{"+primary.getPropertyName()+"}\r\n");
				}
				pw.write(sbCondition.toString());
				pw.write(PLACEHOLDERS + "</update>\r\n\r\n");

				// 查
				pw.write(PLACEHOLDERS + "<select id=\"read" + voName + "\" parameterType=\"java.lang.Long\" resultMap=\"" + voProperty + "\">\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "SELECT * FROM " + tableName + " WHERE " + primaryKeyColumn + "=#{"
						+ formatSQLName(primaryKeyColumn, false) + "}\r\n");
				pw.write(PLACEHOLDERS + "</select>\r\n\r\n");

				// 查集合
				pw.write(PLACEHOLDERS + "<select id=\"query" + voName + "List\" parameterType=\"map\" resultMap=\"" + voProperty
						+ "\">\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "SELECT * FROM " + tableName + " WHERE 1=1 \r\n");
				sbCondition.delete(0, sbCondition.length());
				for (Column c : tableDetail.get(tableName)) {
					sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + "<if test=\"" + c.getPropertyName() + " !=null\">\r\n");
					sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "AND " + c.getColumnName() + "=#{" + c.getPropertyName()
							+ "}\r\n");
					sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + "</if>\r\n");
				}
				// 排序
				sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + "<if test=\"sortColumn!=null\">\r\n");
				sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "order by ${sortColumn} ${sortType} \r\n");
				sbCondition.append(PLACEHOLDERS + PLACEHOLDERS + "</if>\r\n");
				
				pw.write(sbCondition.toString());
				pw.write(PLACEHOLDERS + "</select>\r\n\r\n");

				// 查符合条件的记录数
				pw.write(PLACEHOLDERS + "<select id=\"query" + voName + "Count\" parameterType=\"map\" resultType=\"long\">\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "SELECT COUNT(1) FROM " + tableName.toUpperCase() + " WHERE 1=1 \r\n");
				pw.write(sbCondition.toString());
				pw.write(PLACEHOLDERS + "</select>\r\n\r\n");
				
				// 结尾
				pw.write("</mapper>");
				pw.flush();
				pw.close();
				fos.close();
				System.out.println(SQLMAP_PATH + fileName + ".xml创建完成...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 创建dao实现类
	 * 
	 * @Author Xiang XiaoWen 2015年3月31 新建
	 */
	public static void createDAOImpl() {
		try {
			Calendar calendar = Calendar.getInstance();
			for (String tableName : TABLES) {
				if (!"".equals(TABLE_PREFIX) && null != TABLE_PREFIX) {
					tableName = tableName.replaceFirst(TABLE_PREFIX, "");
				}
				String voName = formatSQLName(tableName, true) + "VO";
				String voProperty = formatSQLName(tableName, false) + "VO";
				String fileName = formatSQLName(tableName, true) + "DAO";
				String interfaceName = "I" + fileName;
				File file = new File(DAO_IMPL_PATH + fileName + ".java");
				if (!file.exists())
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				Writer pw = new OutputStreamWriter(fos, ENCODING);
				// 版权声明
				pw.write(COPYRIGHT_NOTICES);
				// package
				pw.write("package " + DAO_IMPL_PACKAGE + ";\r\n\r\n");
				// import
				pw.write("import java.util.List;\r\n");
				pw.write("import java.util.Map;\r\n");
				pw.write("import org.springframework.stereotype.Repository;\r\n");
				pw.write("import " + DAO_PACKAGE + "." + interfaceName + ";\r\n");
				pw.write("import " + VO_PACKAGE + "." + voName + ";\r\n\r\n");
				pw.write(getClassComment("数据库访问类", fileName));
				pw.write("@Repository\r\n");
				pw.write("public class " + fileName + " implements " + interfaceName + " {\r\n\r\n");
				// 增
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 新增" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param " + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " * @return 新增的对象\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "public " + voName + " insert" + voName + "(" + voName + " " + voProperty + "){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "this.sqlSession.insert(\"" + nameSpace + ".insert" + voName + "\", " + voProperty + ");\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "return " + voProperty + ";\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 删
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 删除" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param id 主键\r\n");
				pw.write(PLACEHOLDERS + " * @return \r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "public int delete" + voName + "(long id){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "return this.sqlSession.delete(\"" + nameSpace + ".delete" + voName + "\", id);");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 改
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 修改" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return \r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "public int update" + voName + "(Map<String, Object> condition){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "return this.sqlSession.update(\"" + nameSpace + ".update" + voName + "\", condition);\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 查
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param id 主键\r\n");
				pw.write(PLACEHOLDERS + " * @return 根据主键查找的内容\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "public " + voName + " read" + voName + "(long id){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "Object obj=this.sqlSession.selectOne(\"" + nameSpace + ".read" + voName + "\", id);\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "if(obj!=null){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + PLACEHOLDERS + "return (" + voName + ")obj;\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "}\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "return null;\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 查询集合
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "集合\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return 符合查询条件的记录\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "public List<" + voName + "> query" + voName + "List(Map<String, Object> condition){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "return this.sqlSession.selectList(\"" + nameSpace + ".query" + voName
						+ "List\", condition);\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");

				// 查询列表数量
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "记录数\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return 符合查询条件的记录数\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "public long query" + voName + "Count(Map<String, Object> condition){\r\n");
				pw.write(PLACEHOLDERS + PLACEHOLDERS + "return (long)this.sqlSession.selectOne(\"query" + voName + "Count\", condition);\r\n");
				pw.write(PLACEHOLDERS + "}\r\n\r\n");
				pw.write("}");
				pw.flush();
				fos.close();
				pw.close();
				System.out.println(DAO_IMPL_PATH + fileName + ".java创建完成...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建DAO接口
	 * 
	 * @Author Shelven 2015年3月30日
	 */
	public static void createDAOInterface() {
		try {
			Calendar calendar = Calendar.getInstance();
			for (String tableName : TABLES) {
				if (!"".equals(TABLE_PREFIX) && null != TABLE_PREFIX) {
					tableName = tableName.replaceFirst(TABLE_PREFIX, "");
				}
				String voName = formatSQLName(tableName, true) + "";
				String voProperty = formatSQLName(tableName, false) + "";
				String fileName = formatSQLName(tableName, true) + "Mapper";
				File file = new File(DAO_PATH + fileName + ".java");
				if (!file.exists())
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				Writer pw = new OutputStreamWriter(fos, ENCODING);
				// 版权声明
				pw.write(COPYRIGHT_NOTICES);
				// 包
				pw.write("\r\npackage " + DAO_PACKAGE + ";\r\n\r\n");
				// import
				pw.write("import java.util.List;\r\n");
				pw.write("import java.util.Map;\r\n\r\n");
				pw.write("import org.springframework.stereotype.Repository;\r\n\r\n");
				pw.write("import " + VO_PACKAGE + "." + voName + ";\r\n\r\n");
				pw.write(getClassComment("数据库访问类", fileName));
				pw.write("@Repository\r\n");
				pw.write("public interface " + fileName + " {\r\n\r\n");
				// 增
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 新增" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param " + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " * @return 新增的对象\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "void insert" + voName + "(" + voName + " " + voProperty + ");\r\n\r\n");

				// 删
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 删除" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param id 主键\r\n");
				pw.write(PLACEHOLDERS + " * @return \r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "int delete" + voName + "(long id);\r\n\r\n");

				// 改
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 修改" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return \r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "int update" + voName + "(Map<String, Object> condition);\r\n\r\n");

				// 查
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param id 主键\r\n");
				pw.write(PLACEHOLDERS + " * @return 根据主键查找的内容\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + voName + " read" + voName + "(long id);\r\n\r\n");

				// 查询集合
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "集合\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return 符合查询条件的记录\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "List<" + voName + "> query" + voName + "List(Map<String, Object> condition);\r\n\r\n");

				// 查询列表数量
				pw.write(PLACEHOLDERS + "/**\r\n");
				pw.write(PLACEHOLDERS + " * 查询" + voProperty + "集合\r\n");
				pw.write(PLACEHOLDERS + " *\r\n");
				pw.write(PLACEHOLDERS + " * @param condition\r\n");
				pw.write(PLACEHOLDERS + " * @return 符合查询条件的记录数\r\n");
				pw.write(PLACEHOLDERS + " * @author " + AUTHOR + " " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " 新建\r\n");
				pw.write(PLACEHOLDERS + " */\r\n");
				pw.write(PLACEHOLDERS + "long query" + voName + "Count(Map<String, Object> condition);\r\n\r\n");

				pw.write("}");
				pw.flush();
				fos.close();
				pw.close();
				System.out.println(DAO_PATH + fileName + ".java创建完成...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 创建VO
	 * 
	 * @param tableDetail
	 * @Author Shelven 2015年3月26日
	 */
	public static void createVO(Map<String, List<Column>> tableDetail) {
		try {
			for (Iterator<String> i = tableDetail.keySet().iterator(); i.hasNext();) {
				StringBuilder sbContent = new StringBuilder(5120);
				String tableName = i.next();
				String fileName = "";
				if (!"".equals(TABLE_PREFIX) && null != TABLE_PREFIX) {
					fileName = formatSQLName(tableName.replaceFirst(TABLE_PREFIX, ""), true);
				} else {
					fileName = formatSQLName(tableName, true);
				}
				Set<String> importSet=new HashSet<>();
				sbContent.append("public class ").append(fileName).append(" {\r\n");
				sbContent.append(buildVOProperty(tableDetail.get(tableName), importSet));
				
				
				File file = new File(VO_PATH + fileName + ".java");
				if (!file.exists())
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				Writer pw = new OutputStreamWriter(fos, ENCODING);
				// 版权声明
				pw.write(COPYRIGHT_NOTICES);
				// 包
				pw.write("\r\npackage " + VO_PACKAGE + ";\r\n\r\n");
				importSet.forEach(str ->{
					try {
						pw.write(str);
					} catch (Exception e) {
						// TODO: handle exception
					}
				});
				// 类注释
				pw.write("\r\n"+getClassComment("实体类", fileName));
				pw.write(sbContent.toString());
				pw.write("}");
				pw.flush();
				pw.close();
				System.out.println(VO_PATH + fileName + ".java创建完成...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 类注释
	 * 
	 * @param classComment 类名
	 * @param fileName
	 * @return
	 * @Author Shelven 2015年3月30日
	 */
	public static String getClassComment(String classComment, String fileName) {
		StringBuilder sbClassComment = new StringBuilder(128);
		sbClassComment.append("/**\r\n * ").append(fileName).append(classComment).append("\r\n *\r\n");
		sbClassComment.append(" * @author ").append(AUTHOR).append("\r\n");
		sbClassComment.append(" * @since JDK1.8\r\n * @history ");
		Calendar ca = Calendar.getInstance();
		sbClassComment.append(ca.get(Calendar.YEAR)).append("-").append(ca.get(Calendar.MONTH)+1);
		sbClassComment.append("-").append(ca.get(Calendar.DATE)).append(" 新建\r\n");
		sbClassComment.append(" */\r\n");
		return sbClassComment.toString();
	}

	/**
	 * 创建VO的代码
	 * 
	 * @param columns
	 * @return
	 * @Author Shelven 2015年3月29日
	 */
	public static String buildVOProperty(List<Column> columns, Set<String> importSet) {
		// 成员变量
		StringBuilder sbProperty = new StringBuilder(512);
		// setter,getter
		StringBuilder sbMethod = new StringBuilder(2560);
		for (Column c : columns) {
			// 列名
			String columnName = c.getColumnName();
			// 属性名
			String propertyName = formatSQLName(columnName, false);
			// 列类型
			String dataType = TYPE_MAPPING.get(c.getDataType().toUpperCase());
			
			if("LocalDate".equals(dataType)){
				importSet.add("import java.time.LocalDate;\r\n");
				importSet.add("import com.fasterxml.jackson.annotation.JsonFormat;\r\n");
				importSet.add("import org.springframework.format.annotation.DateTimeFormat;\r\n");
			}else if("LocalDateTime".equals(dataType) || "".equals(dataType)){
				importSet.add("import java.time.LocalDateTime;\r\n");
				importSet.add("import com.fasterxml.jackson.annotation.JsonFormat;\r\n");
				importSet.add("import org.springframework.format.annotation.DateTimeFormat;\r\n");
			}else if("BigDecimal".equals(dataType)){
				importSet.add("import java.math.BigDecimal;\r\n");
			}
			// 拼接属性注释
			sbProperty.append(PLACEHOLDERS).append("/** (").append(columnName);
			sbProperty.append(",").append(c.getColumnType());
			if ("YES".equalsIgnoreCase(c.getIsNullable())) {
				sbProperty.append(", null");
			} else {
				sbProperty.append(",not null");
			}
			if (c.getColumnDefault() != null)
				sbProperty.append(", default:").append(c.getColumnDefault());
			sbProperty.append(")").append(c.getColumnComment()).append(" */\r\n");
			// 成员变量
			if("LocalDate".equals(dataType)){
				sbProperty.append(PLACEHOLDERS).append("@JsonFormat(pattern = \"yyyy-MM-dd\")\r\n");
				sbProperty.append(PLACEHOLDERS).append("@DateTimeFormat(pattern = \"yyyy-MM-dd\")\r\n");
			}else if("LocalDateTime".equals(dataType) || "".equals(dataType)){
				sbProperty.append(PLACEHOLDERS).append("@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\r\n");
				sbProperty.append(PLACEHOLDERS).append("@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\r\n");
			}
			sbProperty.append(PLACEHOLDERS).append("private ").append(dataType);
			sbProperty.append(" ").append(propertyName).append(";\r\n\r\n");
			// setter
			sbMethod.append(PLACEHOLDERS).append("public void set");
			sbMethod.append(formatSQLName(columnName, true)).append("(").append(dataType).append(" ");
			sbMethod.append(propertyName).append("){\r\n");
			sbMethod.append(PLACEHOLDERS).append(PLACEHOLDERS).append("this.").append(propertyName);
			sbMethod.append("=").append(propertyName).append(";\r\n").append(PLACEHOLDERS).append("}\r\n\r\n");
			// getter
			sbMethod.append(PLACEHOLDERS).append("public ").append(dataType).append(" get");
			sbMethod.append(formatSQLName(columnName, true)).append("(){\r\n").append(PLACEHOLDERS);
			sbMethod.append(PLACEHOLDERS).append("return this.").append(propertyName);
			sbMethod.append(";\r\n").append(PLACEHOLDERS).append("}\r\n\r\n");
		}
		return sbProperty.append(sbMethod).toString();
	}

	public static String builderClass(String className, String packagePath) {
		StringBuilder classBuilder = new StringBuilder(128);
		classBuilder.append("package ").append(packagePath);

		return "";
	}

	/**
	 * 创建文件夹
	 * 
	 * @Author Shelven 2015年3月26日
	 */
	public static void createDir() {
		File file = new File(ACTION_PATH);
		if (!file.exists())
			file.mkdir();
		file = new File(SERVICE_IMPL_PATH);
		if (!file.exists())
			file.mkdirs();
		file = new File(VO_PATH);
		if (!file.exists())
			file.mkdir();
		file = new File(SQLMAP_PATH);
		if (!file.exists())
			file.mkdir();
		file = new File(EXCEPTION_PATH);
		if (!file.exists()) {
			file.mkdir();
		}
		file = new File(DAO_PATH);
		if (!file.exists()) {
			file.mkdir();
		}
		System.out.println("文件夹创建完成...");
	}

	/**
	 * 读取数据表
	 *
	 * @return 表字段信息
	 * @Author Shelven 2015年3月26日
	 */
	public static Map<String, List<Column>> readTableInfo() {
		Map<String, List<Column>> tableDetail = new HashMap<String, List<Column>>(TABLES.length);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + DATABASE_LOCATION + ":" + PORT + "/" + DATABASE_NAME;
			con = DriverManager.getConnection(url, USER_NAME, PASSWORD);
			StringBuilder sb = new StringBuilder();
			sb.append("select column_name,column_default,is_nullable,column_type,data_type,column_comment, column_key ").append(
					"from information_schema.COLUMNS where TABLE_SCHEMA=? and TABLE_NAME=?");
			ps = con.prepareStatement(sb.toString());
			for (String tableName : TABLES) {
				List<Column> columns = new ArrayList<Column>();
				ps.setString(1, DATABASE_NAME);
				ps.setString(2, tableName);
				rs = ps.executeQuery();
				while (rs.next()) {
					Column c = new Column();
					c.setColumnName(rs.getString("column_name"));
					c.setColumnDefault(rs.getString("column_default"));
					c.setIsNullable(rs.getString("is_nullable"));
					c.setColumnType(rs.getString("column_type").toUpperCase());
					c.setColumnComment(rs.getString("column_comment"));
					c.setDataType(rs.getString("data_type").toUpperCase());
					c.setPropertyName(formatSQLName(c.getColumnName(), false));
					c.setPrimaryKeyFlag(false);
					// 处理主键，暂时只支持一个主键的表
					if ("PRI".equalsIgnoreCase(rs.getString("column_key"))) {
						primaryKeyMap.put(tableName, c.getColumnName());
						c.setPrimaryKeyFlag(true);
					}
					columns.add(c);
				}
				tableDetail.put(tableName, columns);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println("获取数据库信息完成...");
		return tableDetail;
	}

	/**
	 * 将下划线_转换为骆驼命名法
	 * 
	 * @param sqlName
	 * @param type 类型 Class 或 Property
	 * @return
	 * @Author Shelven 2015年3月26日
	 */
	public static String formatSQLName(String sqlName, boolean firstUpFlag) {
		sqlName = sqlName.toLowerCase();
		while (sqlName.indexOf("_") >= 0) {
			int index = sqlName.indexOf("_");
			if (index == 0 || index == sqlName.length() - 1) {
				sqlName = sqlName.replaceFirst("_", "");
			} else {
				sqlName = sqlName.replaceFirst(sqlName.substring(index, index + 2), (sqlName.charAt(index + 1) + "").toUpperCase());
			}
		}
		return firstUpFlag ? sqlName.replaceFirst(sqlName.charAt(0) + "", (sqlName.charAt(0) + "").toUpperCase()) : sqlName;
	}
}

class Column {

	private String columnName;

	private String columnDefault;

	private String isNullable;

	private String columnType;

	private String dataType;

	private String columnComment;

	private String propertyName;

	private boolean primaryKeyFlag;

	public boolean getPrimaryKeyFlag() {
		return primaryKeyFlag;
	}

	public void setPrimaryKeyFlag(boolean primaryKeyFlag) {
		this.primaryKeyFlag = primaryKeyFlag;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
