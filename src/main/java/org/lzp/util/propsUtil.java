package org.lzp.util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class PropsUtil {
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
}
