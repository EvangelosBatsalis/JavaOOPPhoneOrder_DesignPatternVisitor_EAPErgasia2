package eap.abstractfactory;

import java.awt.*;

public class FeaturePhone extends Phone{
    private final int batterySize;
    private final Dimension screenSize;
    private final String phoneNumber;
    private final String manufacturer;
    private final int storage;

    public FeaturePhone(int batterySize, Dimension screenSize, String phoneNumber, String manufacturer, int storage) {
        this.batterySize = batterySize;
        this.screenSize = screenSize;
        this.phoneNumber = phoneNumber;
        this.manufacturer = manufacturer;
        this.storage = storage;
    }

    @Override
    public int getBatterySize() {
        return batterySize;
    }

    @Override
    public Dimension getScreenSize() {
        return screenSize;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public int getStorage() {
        return storage;
    }
}
