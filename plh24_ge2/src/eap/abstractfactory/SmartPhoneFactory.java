package eap.abstractfactory;

//εισαγωγή βιβλιοθήκης java.awt.*; όπως και αναγράφεται στο manual της oracle ώστε να λειτουργήσει το κατηγόρημα screenSize του τελεστή Dimension.
import java.awt.*;

//Η κλάση αυτή υλοποιεί τη διεπαφή PhoneAbstractFactory

//δημιουργία αντικειμένων όπου και θα χρειαστεί να στείλουμε στη μέθοδο createPhone() της κλάσης που κληρονομεί από το interface PhoneAbstractFactory
public class SmartPhoneFactory implements PhoneAbstractFactory{
    private final int batterySize;
    private final Dimension screenSize;
    private final String phoneNumber;
    private final String manufacturer;
    private final int storage;
    private final Camera camera;
    private final String operatingSystem;
    
    //Δημιουργία constructor με τα χαρακτηριστικά της κλάσης συμπεριλαμβανόμενου και τα επιπλέον αντικείμενα camera και operating System
    public SmartPhoneFactory(int batterySize, Dimension screenSize, String phoneNumber, String manufacturer, int storage, Camera camera, String operatingSystem){
        this.batterySize = batterySize;
        this.screenSize = screenSize;
        this.phoneNumber = phoneNumber;
        this.manufacturer = manufacturer;
        this.storage = storage;
        this.camera = camera;
        this.operatingSystem = operatingSystem;   
    }
    
    /*γίνεται override και κληρορνομεί από την κλάση PhoneAbstractFactory μια μέθοδο createPhone() ενός αντικειμένου Phone με τα χαρακτηριστικά ενός SmartPhone
    συμπεριλαμβανόμενου και τα επιπλέον αντικείμενα camera και operating system
    όπου και τα στέλνουμε στον constructor της συγκεκριμένης κλάσης Phone*/
    @Override
    public Phone createPhone(){
        return new SmartPhone(batterySize,screenSize,phoneNumber,manufacturer,storage,camera,operatingSystem);
    }
}//end of Class
