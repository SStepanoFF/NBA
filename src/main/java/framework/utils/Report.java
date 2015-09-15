package framework.utils;

import org.testng.Reporter;

public class Report {
    public static enum State { PASS, FAIL, SKIP, DONE, BEGUN }
    public static void log(int step, String message, State state){
        Reporter.log("["+step+"] "+message+" - "+state);
        System.out.println("["+step+"] "+message+" - "+state + "\n");
    }
}
