package com.spidernet.autotest.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;

public class ConfigFile
{
    public static Properties elementProp = null;
    public static Properties valueProp = null;
    public static Properties configProp = null;

    public static String getElementProperties(String key) throws IOException
    {
        if (elementProp == null)
        {
            elementProp = new Properties();
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("Element.properties");
            BufferedReader bf = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            elementProp.load(bf);
        }

        String value = elementProp.getProperty(key);
        return value;
    }

    public static String getValueProperties(String key) throws IOException
    {
        if (valueProp == null)
        {
            valueProp = new Properties();
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("Value.properties");
            BufferedReader bf = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            valueProp.load(bf);
        }
        String value = valueProp.getProperty(key);
        return value;
    }

    public static String getConfigProperties(String key) throws IOException
    {
        if (configProp == null)
        {
            configProp = new Properties();
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("Config.properties");
            BufferedReader bf = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            configProp.load(bf);
        }
        String value = configProp.getProperty(key);
        return value;
    }

    /**
     * append content to file.
     *
     * @param file
     * @param content
     * @return
     * @throws IOException
     */
    public static boolean appendContentToLogFile(String content)
            throws IOException
    {
        boolean flag = false;
        BufferedWriter out = null;
        String filePath = getConfigProperties("logFilePath");
        File targetFile = new File(filePath);
        if (!targetFile.exists())
        {
            targetFile.getParentFile().mkdirs();
            targetFile.createNewFile();
        }
        try
        {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(targetFile, true)));
            out.write(content + "\r\n");
            flag = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println(ConfigFile.getElementProperties("element1"));
        System.out.println(ConfigFile.getValueProperties("value1"));
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
        appendContentToLogFile(sdf.format(currentDate));

    }
}
