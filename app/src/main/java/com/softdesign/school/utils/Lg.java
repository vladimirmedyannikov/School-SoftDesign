package com.softdesign.school.utils;

import android.util.Log;

/**
 * Обертка для стандаоного класса android.utils.Log
 */
public class Lg {
    /**
     * Префикс для сообщений лога
     */
    private static final String PREFIX = "School ";
    /**
     * Максимальная длина одного сообщения лога
     * */
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    /**
     * Функция проверки включения логирования
     * @return true Логирование включено;
     *         false отключено
     */
    private static boolean shouldLog() {
        //TODO реализовать конфигурацию
        return true;
    }

    /**
     * Обрабатывает сообщение лога, разбивая сообщения длиной {@link #LOGCAT_BUFFER_SIZE}
     * @param priority Уровень приоритета сообщения класса android.util.Log
     * @param tag Задает идентификатор для сообщения лога
     * @param message Сообщение которое выводится в лог
     */
    public static void prepareLog(int priority, String tag, String message){
        if (shouldLog()){
            String strMessage = message;
            while(message.length() > LOGCAT_BUFFER_SIZE){
                String subMessage = strMessage.substring(0, LOGCAT_BUFFER_SIZE);
                strMessage = subMessage.substring(LOGCAT_BUFFER_SIZE);
                log(priority, PREFIX + tag, subMessage);
            }
            log(priority, PREFIX + tag, strMessage);
        }
    }

    /**
     * Регистрация логов в системе
     * @param priority Приоритет/тип сообщения лога
     * @param tag Идентификатор сообщения лога
     * @param message Сообщение которое выводится в лог
     */
    private static void log(int priority, String tag, String message) {
        switch (priority){
            case Log.ERROR:
                Log.e(tag, message);
                break;
            case Log.DEBUG:
                Log.d(tag, message);
                break;
            case Log.INFO:
                Log.i(tag, message);
                break;
            case Log.WARN:
                Log.w(tag, message);
                break;
            case Log.ASSERT:
                Log.println(Log.ASSERT, tag, message);
                break;
            case Log.VERBOSE:
                Log.v(tag, message);
                break;
        }
    }

    /**
     * Отправляет сообщение уровня {@link android.util.Log#ERROR}
     * @param tag Идентификатор сообщения лога
     * @param message Сообщение которое выводится в лог
     */
    public static void e(String tag, String message){
        log(Log.ERROR, tag, message);
    }

    /**
     * Отправляет сообщение уровня {@link android.util.Log#DEBUG}
     * @param tag Идентификатор сообщения лога
     * @param message Сообщение которое выводится в лог
     */
    public static void d(String tag, String message){
        log(Log.DEBUG, tag, message);
    }

    /**
     * Отправляет сообщение уровня {@link android.util.Log#INFO}
     * @param tag Идентификатор сообщения лога
     * @param message Сообщение которое выводится в лог
     */
    public static void i(String tag, String message){
        log(Log.INFO, tag, message);
    }

    /**
     * Отправляет сообщение уровня {@link android.util.Log#WARN}
     * @param tag Идентификатор сообщения лога
     * @param message Сообщение которое выводится в лог
     */
    public static void w(String tag, String message){
        log(Log.WARN, tag, message);
    }

    /**
     * Отправляет сообщение уровня {@link android.util.Log#ASSERT}
     * @param tag Идентификатор сообщения лога
     * @param message Сообщение которое выводится в лог
     */
    public static void a(String tag, String message){
        log(Log.ASSERT, tag, message);
    }

    /**
     * Отправляет сообщение уровня {@link android.util.Log#VERBOSE}
     * @param tag Идентификатор сообщения лога
     * @param message Сообщение которое выводится в лог
     */
    public static void v(String tag, String message){
        log(Log.VERBOSE, tag, message);
    }
}
