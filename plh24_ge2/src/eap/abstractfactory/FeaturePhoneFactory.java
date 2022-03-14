package eap.abstractfactory;

import java.awt.*;

public class FeaturePhoneFactory implements PhoneAbstractFactory{
    private final int batterySize;
    private final Dimension screenSize;
    private final String phoneNumber;
    private final String manufacturer;
    private final int storage;

    public FeaturePhoneFactory(int batterySize, Dimension screenSize, String phoneNumber, String manufacturer, int storage) {
        this.batterySize = batterySize;
        this.screenSize = screenSize;
        this.phoneNumber = phoneNumber;
        this.manufacturer = manufacturer;
        this.storage = storage;
    }

    @Override
    public Phone createPhone() {
        return new FeaturePhone(batterySize,screenSize,phoneNumber,manufacturer,storage);
    }
}
