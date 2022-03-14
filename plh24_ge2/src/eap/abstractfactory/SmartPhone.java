package eap.abstractfactory;
//εισαγωγή βιβλιοθήκης Dimension όπως και αναγράφεται στο manual της oracle ώστε να λειτουργήσει το κατηγόρημα screenSize του τελεστή Dimension.
import java.awt.Dimension;


/*Η κλάση αυτή επεκτείνει την κλάση Phone.
Διαθέτει επιπλέον τα χαρακτηριστικά camera (της δοθείσας αντίστοιχης κλάσης) και το αλφαριθμητικό operatingSystem
Θα πρέπει εντός της υλοποίησής της να υποσκελίσετε (να κάνετε override) τη μέθοδο toString η οποία έχει κληρονομηθεί από την κλάση Phone.
Ωστόσο, στη νέα υλοποίηση της μεθόδου toString Θα πρέπει ο κώδικάς σας να χρησιμοποιεί την κληρονομηθείσα μέθοδο και να συμπληρώνει το
επιστρεφόμενο αποτέλεσμα με τα νέα πεδία που εισήχθησαν στην κλάση SmartPhone.*/

//χρησιμοποίησα extends διότι η αρχική κλάση όπου και κληρονομεί τις μεθόδους είναι η phone και είναι κλάση abstract.
public class SmartPhone extends Phone{

    //Εισαγωγή των χαρακτηριστικών της κλάσης Phone η οποία είναι abstract
    private final int batterySize;
    private final Dimension screenSize;
    private final String phoneNumber;
    private final String manufacturer;
    private final int storage;
    
    //επλιπλέον χαρακτηριστικά camera καιτο αλφαριθμητικό operatingSystem
    private final Camera camera;
    //επειδή το χαρακτηριστικό Camera αντικείμενο Θα το δημιουργήσω ώστε να δεσμευτεί ο κατάλληλος χώρος στη μνήμη.
    private final String operatingSystem;
    
    //Δημιουργία Constructor της κλάσης smartphone συμπεριλαμβάνοντας τα επιπλέον χαρακτηριστικά της κλάσης
    public SmartPhone(int batterySize, Dimension screenSize, String phoneNumber, String manufacturer, int storage, Camera camera, String operatingSystem){
        this.batterySize = batterySize;
        this.screenSize = screenSize;
        this.phoneNumber = phoneNumber;
        this.manufacturer = manufacturer;
        this.storage = storage;
        this.camera = camera;
        this.operatingSystem = operatingSystem;
    }
    
    //Δημιουργία των μεθόδων της phone abstract method
    
    
    //Δημιουργία Getters
    @Override
    public int getBatterySize(){
        return batterySize;
    }
    @Override
    public Dimension getScreenSize(){
        return screenSize;
    }    
    @Override
    public String getPhoneNumber(){
        return phoneNumber;
    }
    @Override
    public String getManufacturer(){
        return manufacturer;
    }
    @Override
    public int getStorage(){
        return storage;
    }
    public Camera getCamera(){
        return camera;
    }
    public String getOperatingSystem(){
        return operatingSystem;
    }
    
    //Δεν θα γίνει η δημιουργία των setters διότι είναι final και δεν μπορούν να παραμετροποιηθούν και οι μεταβλητές θα περάσουν από την κλάση asbstacτ
    
    
    //μέθοδος toString μέθοδος overide από της κλάσης που κληρονόμησε
    @Override
    public String toString() {
        /*λόγω του ότι η εκφώνηση αναφέρει "ο κώδικάς σας να χρησιμοποιεί την κληρονομηθείσα μέθοδο" θα χρησιμοποιήσω τον τελεστή super για να αποκτήσω
        πρόσβαση στη μέθοδο της γονικής κλάσης*/
        return super.toString()
                //προσθέτω τα επιπλέον χαρακτηριστικά camera και operating system όπως αναφέρει η εκφώνηση
                +"\n"
                +"Camera: "+getCamera()+"MP"+"\n"
                +"Operatin System: "+getOperatingSystem();
    }//end of toString method
}//end of class 
