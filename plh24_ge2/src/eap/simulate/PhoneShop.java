package eap.simulate;


import eap.abstractfactory.Camera;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

 
public class PhoneShop {
    //Ενδεικτική λίστα με εταιρείες κατασκευής κινητών τηλεφώνων. Θεωρούμε ότι κατασκευάζουν κινητά όλων των κατηγοριών
    private final String[] manufacturers = new String[]{"Samsung","LG","Apple","Motorola"};
    //Ενδεικτική λίστα με ονόματα λειτουργικών συστημάτων κινητών
    private final String[] operatingSystems = new String[]{"Android","iOS","Tizen"};
    private final Random random = new Random();
    //Λίστα για την αποθήκευση των χαρακτηριστικών για κάθε τηλέφωνο που πρόκειται να παρχθεί
    private final List<Phone> phoneList = new ArrayList<>();

    public int numberOfPhones(){
        return phoneList.size();
    }

    //Όλα τα χαρακτηριστικά των τηλεφώνων πρέπει να παραχθούν με τυχαίο τρόπο
    public Phone createPhoneSpec(){
        //χρησιμοποίηση αντικειμένου random για την παραγωγή των τυχαίων χαρακτηριστικών
        //οι τιμές θα κυμαίνονται μεταξύ της περιγραφής αντικειμένου όπως αναγράφεται στην κλάση Phone και Camera
        
        //Το λογικό συμπέρασμα για την random είναι: το κατηγόρημα nextInt της random περιέχει το max μείον min όπου και δημιουργεί τη μέγιστη διαφορά
        //στην οποία προστίθεται το min ώστε να δημιουργείται το ελάχιστο ζητούμενο και τέλος προστίθεται το +1 διότι η random μετράει από 0 εως το πλήθος -1
        //παράδειγμα: min+(random.nextInt(max-min)+1)
        int batterySize = 3000 + random.nextInt((5000-3000)+1);
        //δημιουργία αντικειμένου screenSize. χρησιμοποίησα δύο μεταβλητές width και height όπου δημιουργήθηκαν με την random
        Dimension screenSize = new Dimension();
        int randomDimensionHeight = 500 + random.nextInt((3000-500)+1);
        int randomDimensionWidth = 500 + random.nextInt((3000-500)+1);
        screenSize.height = randomDimensionHeight;
        screenSize.width = randomDimensionWidth;
        //χρησιμοποιώ τη μέθοδο random ως όρισμα τη μέθοδο length του πίνακα και θα κάνω την έξοδο με array[randomManufacturers]
        int randomManufacturers = random.nextInt(manufacturers.length);
        //χρησιμοποιώ τη μέθοδο random ως όρισμα τη μέθοδο length του πίνακα και θα κάνω την έξοδο με array[randomOperatingSystem]
        int randomOperatingSystem = random.nextInt(operatingSystems.length);
        
        //Δημιουργία τυχαίου αριθού κινητού τηλεφώνου
        String RandomMobileNumber = "+3069" + String.valueOf(random.nextInt(100000000));
        
        //δημιουργία randoma storage
        //String randomStorage = String.valueOf(2+random.nextInt((200-2)+1))+"GB";
        int randomStorage = 2+random.nextInt((200-2)+1);
        
        //δημιουργία random χαρακτηριστικά camera και δέσμευση χώρου και δημιουργία αντικειμένου camera
        int randomCameraStats = 10 + random.nextInt((100-10)+1);
        Camera camera = new Camera(randomCameraStats);
        
        //Θα χρησιμοποιήσω πίνακα string έτσι ώστε να χρησιμοποιήσω την μέθοδο random και να παράγω δύο τυχαία τηλέφωνα featurephone ή smartphone
        //δέσμευση στη μνήμη της κλάσης abstract και με μια if θα ελέγξω ποιο τηλέφωνο θα δημιουργηθεί
        Phone phone = null;
        String[] phoneRandomSelectionList = {"FeaturePhone","SmartPhone"};
        
        int randomPhoneSelection = random.nextInt(phoneRandomSelectionList.length);
        if(phoneRandomSelectionList[randomPhoneSelection].equals("FeaturePhone")){            
            //FeaturePhone featurePhone;
            phone = new FeaturePhone(batterySize,screenSize,RandomMobileNumber,manufacturers[randomManufacturers],randomStorage);
            phoneList.add(phone);
            return phone;
        }else {//if (phoneRandomSelectionList[randomPhoneSelection].equals("SmartPhone")){
            phone = new SmartPhone(batterySize,screenSize,RandomMobileNumber,manufacturers[randomManufacturers],randomStorage,camera,operatingSystems[randomOperatingSystem]);
            phoneList.add(phone);
            return phone;
        }
    }//end of createPhoneSpec method
}//end of PhoneShop class
