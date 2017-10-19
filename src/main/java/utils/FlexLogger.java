/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Date;
import org.apache.http.client.utils.DateUtils;

/**
 *
 * @author zua
 */
public class FlexLogger {

    private boolean isOn = false;
    private Class<?> aClass;

    public FlexLogger(Class<?> aClass) {
        this.aClass = aClass;
    }

    public void log(String format, Object... values) {
        if (isOn) {
            sout(format, values);
        }
    }

    public void info(String format, Object... values) {
        sout(format, values);
    }

    public void error(String format, Object... values) {
        if(isOn) {
            serr(format, values);
        }
    }

    public void on() {
        isOn = true;
    }

    public void off() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    private void sout(String format, Object[] values) {
        String newFormat = "[%s] %25s: " + format;
        Object[] newValues = new Object[values.length + 2];
        newValues[0] = DateUtils.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss");
        newValues[1] = aClass.getSimpleName();
        int i = 2;
        for (Object o : values) {
            newValues[i] = o;
            i++;
        }
        String result = String.format(newFormat, newValues);
        System.out.println(result);
    }

    private void serr(String format, Object[] values) {
        String newFormat = "[%s] %25s: " + format;
        Object[] newValues = new Object[values.length + 2];
        newValues[0] = DateUtils.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss");
        newValues[1] = aClass.getSimpleName();
        int i = 2;
        for (Object o : values) {
            newValues[i] = o;
            i++;
        }
        String result = String.format(newFormat, newValues);
        System.err.println(result);
    }
}
