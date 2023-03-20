package org.hzz;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hzz.utils.ConfigConstants;
import org.hzz.utils.Utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * POJO生成
 */
@Slf4j
public class EntityGeneratorTest {
    private static final Map<String,String> jdbcToJavaType= new HashMap<>();
    private static List<String> primaryKeys;

    static{
        //初始化jdbc类型转换。
        jdbcToJavaType.put("VARCHAR", "String");
        jdbcToJavaType.put("CHAR", "String");
        jdbcToJavaType.put("VARCHAR2", "String");
        jdbcToJavaType.put("NVARCHAR", "String");
        jdbcToJavaType.put("LONGNVARCHAR", "String");
        jdbcToJavaType.put("TEXT", "String");
        jdbcToJavaType.put("CLOB", "String");
        jdbcToJavaType.put("TINYLOB", "String");
        jdbcToJavaType.put("INT", "Integer");
        jdbcToJavaType.put("INTEGER", "Integer");
        jdbcToJavaType.put("SMALLINT", "Integer");
        jdbcToJavaType.put("TINYINT", "Integer");
        jdbcToJavaType.put("BIGINT", "Long");

        jdbcToJavaType.put("NUMBERIC", "Long");
        jdbcToJavaType.put("NUMBER", "Long");
        jdbcToJavaType.put("DOUBLE", "Double");
        jdbcToJavaType.put("FLOAT", "Float");

        jdbcToJavaType.put("DATE", "Date");
        jdbcToJavaType.put("DATETIME", "Date");//java.util.Date
        jdbcToJavaType.put("TIMESTAMP", "Timestamp");//java.sql.Timestamp

    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File(ConfigConstants.ftlLocation));

        log.info("==== 开始生成 " + ConfigConstants.TABLENAME + " 表对应的POJO ====");
        Template template = configuration.getTemplate("Pojo.ftl");
        Map<String, Object> paramMap = getCommonParam(ConfigConstants.TABLENAME);


        addTableInfo(paramMap, ConfigConstants.TABLENAME);
        String pojoName = paramMap.get("pojo_name").toString();

        File pojoFile = new File(Utils.getPojoLocation(pojoName));
        Writer writer = new OutputStreamWriter(new FileOutputStream(pojoFile), "UTF-8");
        template.process(paramMap, writer);
        writer.flush();
        writer.close();
        log.info("==== " + ConfigConstants.TABLENAME + " 表对应的POJO类生成成功 生成地址：" + pojoFile.getAbsolutePath() + " ====");
    }

    private static void addTableInfo(Map<String, Object> paramMap, String tableName) throws Exception {
        //获取表结构信息 主要是表名、表注释、字段名，字段类型， 字段注释。
        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties properties = new Properties();
        properties.setProperty("user",ConfigConstants.USERNAME);
        properties.setProperty("password",ConfigConstants.PASSWORD);
        properties.setProperty("useInformationSchema","true");
        Connection connection = DriverManager.getConnection(ConfigConstants.JDBCURL, properties);

        // 获取表结构
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tableSet = metaData.getTables(ConfigConstants.SCHEMA, "%", tableName, new String[]{"TABLE"});
        if(tableSet.next()){
            paramMap.put("table_name", tableName);
            paramMap.put("table_name_small", tableName.toLowerCase());
            paramMap.put("table_remark", tableSet.getString("REMARKS"));
            log.info("获取到表信息 TABLE_NAME => " + tableSet.getString("TABLE_NAME") + ";TABLE_REMARK => " + tableSet.getString("REMARKS"));
        }else{
            log.error("数据库中没有查到表 " + tableName);
            throw new RuntimeException("数据库中没有此表");
        }

        //加载主键
        if(primaryKeys == null || primaryKeys.size() == 0){
            primaryKeys = new ArrayList<String>();//主键列名
            ResultSet primaryKeySet = metaData.getPrimaryKeys(ConfigConstants.SCHEMA, "%", tableName);
            while (primaryKeySet.next()) {
                primaryKeys.add(primaryKeySet.getString("COLUMN_NAME"));
            }
        }

        //获取字段信息
        ResultSet columnSet = metaData.getColumns(ConfigConstants.SCHEMA, "%", tableName, "%");
        List<Map<String, Object>> columns = new ArrayList<>();
        while (columnSet.next()) {
            Map<String, Object> columnInfo = new HashMap<>();
            String columnName = columnSet.getString("COLUMN_NAME");
            String columnType = columnSet.getString("TYPE_NAME");
            int datasize = columnSet.getInt("COLUMN_SIZE");
            int digits = columnSet.getInt("DECIMAL_DIGITS");
            int nullable = columnSet.getInt("NULLABLE");
            String remarks = columnSet.getString("REMARKS");
            log.info("获取到字段信息 ： columnName =>" + columnName + ";columnType => " + columnType + ";datasize=>" + datasize + "=>" + digits + ";nullable => " + nullable + ";remarks => " + remarks);
            //只对JDBC几种常见的数据类型做下匹配，其他不常用的就暂时不生成了。 健壮的类型映射还是需要看下别的框架是怎么做的。
            if (StringUtils.isNotBlank(columnType)
                    && jdbcToJavaType.containsKey(columnType.toUpperCase())) {
                columnInfo.put("columnName", columnName);
                columnInfo.put("columnType", columnType);
                columnInfo.put("javaType", jdbcToJavaType.get(columnType.toUpperCase()));
                String javaName = getCamelName(columnName);
                columnInfo.put("javaName", javaName);
                columnInfo.put("getterName", "get" + javaName.substring(0, 1).toUpperCase() + javaName.substring(1));
                columnInfo.put("setterName", "set" + javaName.substring(0, 1).toUpperCase() + javaName.substring(1));
                columnInfo.put("remarks", StringUtils.isNotBlank(remarks) ? remarks : "");
                columnInfo.put("isPK", primaryKeys.contains(columnName) ? "true" : "");
                columns.add(columnInfo);
            } else {
                log.error("字段信息 ： columnName =>" + columnName + " 类型 columnType => " + columnType + " 暂无法处理，待以后进行扩展 ;");
                throw new Exception("字段信息 ： columnName =>" + columnName + " 类型 columnType => " + columnType + " 暂无法处理，待以后进行扩展 ;");
            }
        }
        paramMap.put("COLUMNS", columns);
    }


    private static String getCamelName(String columnName) {
        //防止死循环，加个计数器。
        int count = 5;
        while(columnName.indexOf("_")>-1 && count >0) {
            int index = columnName.indexOf("_");
            columnName = columnName.substring(0,1).toLowerCase()+columnName.substring(1, index)
                    +columnName.substring(index+1,index+2).toUpperCase()+columnName.substring(index+2);
            count --;
        }
        return columnName;
    }


    private static Map<String, Object> getCommonParam(String tableName){
        Map<String, Object> paramMap = new HashMap<>();
        String pojoName = getPOJONameByTableName(tableName);
        paramMap.put("pojo_name", pojoName);
        String genTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        paramMap.put("gen_time", genTime);
        return paramMap;
    }

    /**
     * 将表名称转换为pojo名称
     * @param tableName
     * @return
     */
    private static String getPOJONameByTableName(String tableName) {
        //防止死循环，加个计数器。
        tableName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        int count = 5;
        while (tableName.indexOf("_") > -1 && count > 0) {
            int index = tableName.indexOf("_");
            tableName = tableName.substring(0, index)
                    + tableName.substring(index + 1, index + 2).toUpperCase() + tableName.substring(index + 2);
            count--;
        }
        return tableName;
    }
}
