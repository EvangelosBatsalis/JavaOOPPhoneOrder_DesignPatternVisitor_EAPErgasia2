package eap.abstractfactory;

import eap.simulate.DelaySimulator;

public class PhoneFactory {
    public static Phone getPhone(PhoneAbstractFactory factory){
        DelaySimulator.sleep(5);
        return factory.createPhone();
    }
}
