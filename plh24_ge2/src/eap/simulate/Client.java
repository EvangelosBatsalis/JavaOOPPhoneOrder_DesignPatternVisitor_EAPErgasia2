package eap.simulate;

import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;
import eap.observer.PhoneCreationListener;
import eap.observer.PhoneOrderHandler;
import eap.simulate.Simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements PhoneCreationListener {

    private final String name;
    private final Class interestedFor;
    //private final String interestedFor;
    private Phone phone;
    /*Για τους παρόχους κινητής τηλεφωνίας έχουμε τις εξής παραδοχές:
    1. Είναι οι 3 που υπάρχουν στην ακόλουθη λίστα
    2. Η Cosmote έχει κινητά που ξεκινάνε 697, 698 και 699, η Vodafone 694, 695 και 696 και η Wind 691,692 και 693.
    3. Κινητά που ξεκινάνε ως 690 θα αναφέρονται ως "Διαφημιστικά".*/
    private static final String[] carriers = {"Cosmote", "Vodafone", "Wind"};

    public Phone getPhone() {
        return phone;
    }

    private void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Client(String name, Class interestedFor) {
        this.name = name;
        this.interestedFor = interestedFor;
    }

    public String getName() {
        return name;
    }

    public Class getInterestedFor() {
        return interestedFor;
    }

    /*
    (μέθοδος update)
    Εφόσον ο πελάτης βρίσκει το τηλέφωνο που τον ενδιαφέρει,
    πρέπει να αφαιρεί το τηλέφωνο από το σύνολο των διαθέσιμων τηλεφώνων (για να μην το πάρει κάποιος άλλος), 
    να απεγγράφεται από τη λίστα των ενδιαφερόμενων πελατών (ώστε να μην ενημερωθεί ξανά), 
    καθώς και να αφαιρείται από το σύνολο των πελατών που αναμένουν για κινητό
     */
    @Override
    public void update(Phone phone) {

        //βάση παραδοχή της εργασίας 9α γίνεται έλεγχος τύπου του τηλεφώνου που ενδιαφερει τον πελάτη
        String temp = null;
        if (phone instanceof FeaturePhone) {
            temp = "FeaturePhone";
        } else if (phone instanceof SmartPhone) {
            temp = "SmartPhone";
        }
        PhoneCreationListener phoneCreationListener = null;
        
        //χρησιμοποίησα to isInstance() https://www.geeksforgeeks.org/instanceof-operator-vs-isinstance-method-in-java/ για να ελέγξω τπ phone να είναι αντικείμενο της κλάσης insterestedFor
        //χρησιμοποίησα (PhoneOrderHandler.phoneExistsInList(phone) ώστε να ελέγεται το τηλέφωνο μιαφορά και να μην το ξανααίρνει ο πελάτης ο ίδιος δύο φορές
        if ((this.interestedFor.isInstance(phone)) && (PhoneOrderHandler.phoneExistsInList(phone))){ 
            String carrierName = this.getCarrierName(phone.getPhoneNumber());
            System.out.println("hi i am " + this.name + "(" + temp + ") and I got my new phone!\n"
                    + "Phone specs: \n"
                    + phone.toString() + "\n"
                    + //από το αντικείμενο phone καλώ τη μέθοδο toString ώστε να τυπώσει στην έξοδο της κοσνόλας τα χαρακτηριστικά τηλεφώνου
                    "Phone number valid: " + this.checkNumberValidity(phone.getPhoneNumber()) + "\n"
                    + //καλώ τη μέθοδο checkNumberValidity αυτή της κλάσης( με τον τελεστή this) βάζοντας ως παράμετρο το getter getPhoneNumber() του αντικειμένου phone  ώστε να στείλω σαν String τον αριθμό τηλεφώνου
                    "Phone number carrier: " + carrierName);//this.getCarrierName(phone.getPhoneNumber())); //καλώ τη μέθοδο geCcarrierName αυτή της κλάσης(με οτν τελεστή this) βάζοντας ως παράμετρο το getter getPhoneNumber() του αντικειμένου phone ώστε να στείλω σαν String τον αριθμό τηλεφώνου
            PhoneOrderHandler.removePhone(phone);
            PhoneOrderHandler.removeListener(this);
            usePhone(phone);
        }
    }

    /*Η μέθοδος χρήσης του κινητού τηλεφώνου θα κάνει τα εξής:
    Στην περίπτωση FeaturePhone θα καλεί τον αριθμό +30123456789
    Στην περίπτωση SmartPhone Θα βγάζει μια φωτογραφία με φλας και ανάλυση 12 MP*/
    private void usePhone(Phone phone) {
        //χρησιμοποίησα instanceof διότι θέλω να ελέξγω ότι το αντικείμενο να είναι στιγμιότυπο της κλάσης phone
        if (phone instanceof FeaturePhone) {
            phone.callNumber("+30123456789");
        } else if (phone instanceof SmartPhone) {
            //χρησιμοποιω downcasting ωστε να αποκτησω πρόσβαση στις μεθόδους της κλάσης SmartPhone https://www.baeldung.com/java-type-casting
            //Casting από την υπερκλάση στην υποκλάση
            ((SmartPhone) phone).getCamera().setUseFlash(true);
            ((SmartPhone) phone).getCamera().setSelectedResolution(12);
            ((SmartPhone) phone).getCamera().takePicture();
        }

    }

    //Σύμφωνα με την περιγραφή πάνω από τον πίνακα carriers, η μέθοδος επιστρέφει είτε το όνομα του carrier, είτε Διαφημιστικά
    public String getCarrierName(String phoneNumber) {
        String tempPhoneNumber = phoneNumber.substring(3); //αφαίρεσα μέσω substring() τους τρεις πρώτους χαρακτήρες του διεθνή κωδικού σε temp μεταβλητή String
//Θα χρησιμοποιήσω την μέθοδο startsWith() της κλάσης String για να ελέγξω τις παραδοχές της άσκησης
        if (tempPhoneNumber.startsWith("697") || tempPhoneNumber.startsWith("698") || tempPhoneNumber.startsWith("699")) {
            return carriers[0];
        } else if (tempPhoneNumber.startsWith("694") || tempPhoneNumber.startsWith("695") || tempPhoneNumber.startsWith("696")) {
            return carriers[1];
        } else if (tempPhoneNumber.startsWith("691") || tempPhoneNumber.startsWith("692") || tempPhoneNumber.startsWith("693")) {
            return carriers[2];
        } else{
            return "Διαφημιστικά";
        }
    }

    /*Για να είναι έγκυρο ένα κινητό τηλέφωνο πρέπει να ισχύουν ταυτόχρονα τα εξής:
    1. Να ξεκινάει από +3069 (είναι όλα από Ελλάδα και είναι όλα κινητά)
    2. Στη συνέχεια να υπάρχουν ακριβώς 10 αριθμοί
    3. Παραδοχή ότι δεν υπάρχουν κενά (white spaces μεταξύ των αριθμών)*/
    public boolean checkNumberValidity(String phoneNumber) {
        //θα αφαιρέσω από τον αριθμό τηλεφώνου το πρώτο σύμβολο ώστε να μπορέσω να επεξεργαστώ μέσω του matcher τα αρχικά πρώτα ψηφία του αριθμού τηλεφώνου
        Boolean internationalCodeNumberValidation = phoneNumber.startsWith("+30");  //Να ξεκινάει από +3069 (είναι όλα από Ελλάδα
        //System.out.println(internationalCodeNumberValidation); //έλεγχος εξόδου το σβήνω μόλις τελειώσω
        String tempPhoneNumber = phoneNumber.substring(3); //βγάζω από την αναζήτηση τον διεθνή κωδικό σε νέο String temp String
        //System.out.println(tempPhoneNumber); //έλεγχος εξόδου το σβήνω μόλις τελειώσω
        Boolean mobileNumberValidation = tempPhoneNumber.startsWith("69");  //Να ξεκινάει 69 όπου σημαίνει ότι όλα είναι κινητά
        //System.out.println(mobileNumberValidation); //έλεγχος εξόδου το σβήνω μόλις τελειώσω
        Boolean lengthNumberValidation = tempPhoneNumber.length() == 10;  //έλεγχος να υπάρχουν ακριβώς 10 αριθμοί
        //System.out.println(lengthNumberValidation); //έλεγχος εξόδου το σβήνω μόλις τελειώσω

        //Εκκίνηση regex pattern
        //Αρχικοποίηση αντικειμένου Pattern με χρήση της μεθόδου compile για να εισάγω την έκφραση regex
        //Xρήση [0-9] για τον έλεγχο εύρους χαρακτήρων και χρηση [^\s] για τον έλεγχο της Παραδοχή της άσκησης ότι δεν υπάρχουν κενά μεταξύ αριθμών
        //και τον τελεστή +. Επισυμάνω ότι για τον έλεγχο κενών χαρακτήρων μπορώ να χρησιμοποιήσω και το pattern [0-9] μιας και αν υπάρχει κενό δεν ανήκει στο range του pattern άρα θα βγάλει false

        // \\+ για να εισάγο τον έλεγχο του χαρακτήρα + έπειτα χρησιμοποιώ range μέσα σε [] από το 0 έως το 9 και τέλος χρησιμοποιώ τνο τελεστή + για να κάνει έλεγχο
        //το λιγότερο μια φορές
        Pattern patternCheckDigits = Pattern.compile("\\+[0-9]+");
        Matcher matcherCheckDigits = patternCheckDigits.matcher(phoneNumber);
        
        //[^s]+$ μεταξύ αγκύλης γίνεται έλεγχος για whitespace (index 115) ο συμβολισμός + χρησιμοποιείται για την επανάληψη του pattern [^s] το λιγότερο μιαφορά και τέλος 
        //οσυμβολισμός $ γίνεται για να ελεγθεί μέχρι το τέλος της γραμμής
        Pattern patternCheckWhiteSpace = Pattern.compile("[^s]+$");
        Matcher matcherCheckWhiteSpace = patternCheckWhiteSpace.matcher(phoneNumber);

        //Γίνεται έλεγχος για Whitespace ενα υπάρχει whiteSpace τότε τρέχω το replaceAll και βγάζω τα whitespace από τον αριθμό τηλεφώνου
        if(matcherCheckWhiteSpace.matches()!=true){
            tempPhoneNumber = tempPhoneNumber.replaceAll("\\s","");        //αν υπάρχουν whitespace τα αφαιρώ
        }
        
        if((internationalCodeNumberValidation == true) && (mobileNumberValidation == true) && (lengthNumberValidation == true) && (matcherCheckDigits.matches()==true) && (matcherCheckWhiteSpace.matches()==true) ){
            return true;
        }else{
            return false;
    
        }
    }
}
