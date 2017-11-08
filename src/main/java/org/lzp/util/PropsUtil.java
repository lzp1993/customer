package org.lzp.util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.FactoryConfigurationError;

public final class PropsUtil {
    private static final Logger LOGGER=LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String filename)
    {
        Properties props=null;
        InputStream is=null;
        try {
            is=Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            if(is==null)
                throw new FileNotFoundException(filename+"file is not found");
        props=new Properties();
        props.load(is);
    }catch(IOException e)
    {
        LOGGER.error("load properties file failure",e);
    }finally {
            if(is!=null){
                try{
                    is.close();
                }catch (IOException e)
                {
                    LOGGER.error("close inpurt stream falid",e);
                }
            }
        }
        return props;
        }
    public static String getString(Properties props,String key){
        return getString(props,key,"");
    }
    public static String getString(Properties props,String key,String defaultValue){
        String value=defaultValue;
        if(props.containsKey(key)){
            value=props.getProperty(key);
        }
        return value;
    }
    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }
    public static int getInt(Properties props,String key,int defaultValue){
        int value=defaultValue;
        if(props.containsKey(key)){
            value=CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }
    public static double getDouble(Properties props,String key)
    {
        return getDouble(props,key,0);
    }
    public static double getDouble(Properties props,String key,double defaultValue)
    {
        double value=defaultValue;
        if(props.containsKey(key))
        {
            value=CastUtil.castDouble(props.getProperty(key));
        }
        return value;
    }
    public static boolean getBoolean(Properties props,String key)
    {
        return getBoolean(props,key,false);
    }
    public static boolean getBoolean(Properties props,String key,boolean defaultValue)
    {
        boolean value=defaultValue;
        if(props.containsKey(key))
        {
            value=CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }


}
