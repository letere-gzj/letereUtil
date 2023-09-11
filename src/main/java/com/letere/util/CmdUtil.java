package com.letere.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 命令工具类
 * @author gaozijie
 * @date 2023-09-11
 */
public class CmdUtil {

    /**
     * 执行命令
     * @param cmd 命令
     */
    public static void execute(String cmd) {
        execute(cmd, false);
    }

    /**
     * 执行命令
     * @param cmd 命令
     * @param isPrint 是否打印
     */
    public static void execute(String cmd, boolean isPrint) {
        if (cmd == null || cmd.trim().length() == 0) {
            return;
        }
        String osName = System.getProperties().getProperty("os.name");
        // 执行命令
        if (osName.toLowerCase().startsWith("win")) {
            cmd = "cmd /c " + cmd;
        }
        Process process = executeCmd(cmd);
        // 打印日志
        if (isPrint) {
            printCmdLog(process);
        }
    }

    /**
     * 执行windows命令
     * @param cmd 命令
     */
    private static Process executeCmd(String cmd) {
        Process process;
        try {
            process = Runtime.getRuntime().exec("cmd /c " + cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return process;
    }

    /**
     * 打印命令日志
     * @param process 进程
     */
    private static void printCmdLog(Process process) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
