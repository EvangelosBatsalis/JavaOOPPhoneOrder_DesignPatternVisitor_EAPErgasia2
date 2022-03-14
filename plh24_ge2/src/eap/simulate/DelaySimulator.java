package eap.simulate;

//This class simulates a small delay in seconds using a regular loop.
//In this project we use it instead of using a Thread-sleep approach. Only for simulation purposes, it consumes processing power!
public class DelaySimulator {
    public static void sleep(int timeInSeconds){
        long now = System.currentTimeMillis();
        long expectedStopTime = now+timeInSeconds* 1000L;
        while(now < expectedStopTime){
            now = System.currentTimeMillis();
        }
    }
}
