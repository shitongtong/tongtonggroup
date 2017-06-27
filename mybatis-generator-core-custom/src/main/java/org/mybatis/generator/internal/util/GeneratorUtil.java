package org.mybatis.generator.internal.util;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/27.
 */
public class GeneratorUtil {

    /**
     * 获取表的uuid字段名称，若没有则返回null
     * @param introspectedTable
     * @return
     */
    public static String getColumnUuidName(IntrospectedTable introspectedTable){
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        List<IntrospectedColumn> baseColumns = introspectedTable.getBaseColumns();
        for (IntrospectedColumn column : baseColumns){
            String columnName = column.getActualColumnName();
            if ("uuid".equals(columnName)||(tableName+"_uuid").equals(columnName)){
                return columnName;
            }
        }
        return null;
    }

    /**
     * 对name进行驼峰命名(user_name -> userName)
     * @param name
     * @return
     */
    public static String humpName(String name){
        if (StringUtility.stringHasValue(name) && name.contains("_")){
            String[] names = name.split("_");
            StringBuilder sb = new StringBuilder();
            sb.append(names[0]);
            for (int i = 0; i < names.length-1; i++) {
                String temp = names[i + 1];
                temp = toUpperCaseFirstOne(temp);
                sb.append(temp);
            }
            return sb.toString();
        }
        return name;
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))){
            return s;
        }
        return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
