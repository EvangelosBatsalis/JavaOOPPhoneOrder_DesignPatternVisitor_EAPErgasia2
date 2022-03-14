package eap.abstractfactory;

import eap.simulate.DelaySimulator;

import java.awt.*;

public abstract class Phone {
    public abstract int getBatterySize(); //Measured in mAh. Between 1000 and 5000
    public abstract Dimension getScreenSize(); //Width x Height. between (500x500) and (3000x3000)
    public abstract String getPhoneNumber(); //e.g. +306912345678. Accept only mobile numbers in Greece
    public abstract String getManufacturer();//e.g. Samsung
    public abstract int getStorage();//Measured in GB. e.g. 50GB. Between 2 and 200

    public void callNumber(String number){
        System.out.println("------------------------");
        System.out.println("Phone call from "+getPhoneNumber()+" to "+number);
        System.out.println("Call in progress...");
        DelaySimulator.sleep(3);
        System.out.println("Call terminated.");
        System.out.println("------------------------");
    }

    @Override
    public String toString() {
        return "Battery(mAh): " +
                getBatterySize()+"\n"
                +"Screen: "+
                getScreenSize()+"\n"
                +"Number: "+
                getPhoneNumber()+"\n"
                +"Manufacturer: "+
                getManufacturer()+"\n"
                +"Storage(GB): "+
                getStorage()+"GB";
    }
}
