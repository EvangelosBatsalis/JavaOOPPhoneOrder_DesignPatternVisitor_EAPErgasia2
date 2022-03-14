package eap.simulate;

import eap.observer.PhoneOrderHandler;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;

import java.util.*;
import java.util.List;

public class Simulation {

    //Fixed array of Greek names
    static final String[] names = {"George", "Maria", "Dimitris", "John", "Katerina",
        "Panagiotis", "Konstantinos", "Christina", "Eleni", "Petros"};

    // Στη μέθοδο main το πρόγραμμα αρχικά ζητά από τον χρήστη να δηλώσει τον αριθμό
    // των κινητών τηλεφώνων που πρόκειται να δοθούν στη συνέχεια στη γραμμή παραγωγής,
    // καθώς και τον αριθμό των πελατών που θα δημιουργηθούν
    // και θα αναμείνουν για την ενδεχόμενη παραγωγή των τηλεφώνων που τους ενδιαφέρουν
    public static void main(String[] args) {
        //δημιουργία αντικειμένου random για την παραγωγή τυχαίων αριθμών της λίστας ονομάτων
        Random random = new Random();

        //Δημιουργία αντικειμένου scanner όπου καυ θα χρησιμοποιηθεί για είσομο του χρήστη και αντίστοιχων μεταβλητών όπου θα χρησιμοποιηθούν
        //για την αποθήκευση της εισόδου του χρήστη
        Scanner sc = new Scanner(System.in);
        int userInputPhoneOrder=0, userInputClient=0;

        //χρησιμοποίησα try catch ώστε να μπορέσω να δημιουργήσω μήνυμα προτροπής χρήσης μη αλφαριθμικών χαρακτήρων
        try {
            System.out.print("Please enter number of phones to be ordered: ");
            userInputPhoneOrder = sc.nextInt();
            //χρήση της do..while για αμυντικό προγραμματισμό της εισόδου του χρήστη με μηνύματα προτροπή λάθος χρήσης
            do {
                System.out.print("Please enter number of clients waiting for new phones (MAX:10): ");
                userInputClient = sc.nextInt();
                //χρήση επιλογής if..else of ώστε να μπορέσω να είμαι πιο συγκεκριμένος στο μήνυμα σφάλματος αμυνιτκού προγραμματισμού στον χρήστη                
                if (userInputClient < 0) {
                    System.out.println("\nYou 've entered wrong value you have enter: " + userInputClient + "clients which value is a negative number");
                } else if (userInputClient > 10) {
                    System.out.println("\nYou have entered wrong value you have enter: " + userInputClient + "client which value is more than 10");
                } else if (userInputClient == 0) {
                    System.out.println("\nYou 've entered wrong value you have enter: " + userInputClient + "clients. It needs at least 1 client");
                }
            } while (!(userInputClient >= 1 && userInputClient <= 10));  //θα βρίσκεται σε λούπα σε περίτωση ο χρήστης εισάγει κάτων του 1 και πάνω από 10 πελάτες
        } catch (InputMismatchException e) {
            System.out.println("Only arithmetic values are allowed. The program now...will exit");
            System.exit(0);  //χρήση System.exit(0) σε περίπτωση που ο χρήστης εισάγει κάτι διαφορετικό από αριθμητικούς χαρακτήρες να τερματίσει το πρόγραμμα
        }
        

        //έξοδος σττην κονσόλα του προγράμματος, είναι ζητούμενο της άσκησης ανάλογα με την είσοδο του χρήστη.
        System.out.println(userInputPhoneOrder+" phone orders have been placed by the PhoneShop!");
        System.out.println(userInputClient+" clients are waiiting to buy a new phone!");
        
        //ζητούμενο τον αριθμό clients που εισάγει ο χρήστης θα χρησιμοποιήσω την random ώστε να παράξω τυχαία τα ονόματα των πελατών. 
        //επίσης θα το προσθέσω μέσα σε μια for loop ώστε να δημιουργηθούν τόσα τηλέφωνα όσα δηλώσει ο χρήστης μέσω της μεταβλητής RandomUserCreation όπου ενημερώνεται μέσω του αντικειμένου Scanner
        //final String[] PhoneListCustomerInterestedFor = {"FeaturePhone", "SmartPhone"};

        //Δήλωση και αρχικοποίηση αντικεμένου PhoneShop
        PhoneShop ps = new PhoneShop();
        //Δήλωση και αρχικοποίηση αντικειμένου Phone
        Phone phone = null; //αρχικοποίηση για χρήση του αντικειμένου έξω από την for..loop

        //χρήση for..loop δημιουργία των τηλεφώνων ανάλογα με τη δήλωση της μεταβλητής που εισήγαγε ο χρήστης userInputPhoneOrder η οποία δημιουργήθηκε με το αντικείμενο  scanner
        for(int i =0;i<userInputPhoneOrder;i++){
            phone = ps.createPhoneSpec();
            PhoneOrderHandler.addPhone(phone);  
        }
        //δημιουργία αντικειμένου Λίστας γενίκευσης Class για να αποθηκεύσω την επιλογή των τηλεφώνων που θέλουν οι clients μέσω του αντικειμένου random
        ArrayList<Class> randomPhoneClientDesiresList = new ArrayList<Class>();
        //Δήλωση και αρχικοποίηση μεταβλητής Class
        //χρηση της random για την επιλογή τυχαίου τηλεφώνου FeaturePhone και SmartPhone στο οποίο αποθηκέυεται σε γενικευμένη μορφή .class
        //στην οποία ο σκοπός δημιουργίας της είναι να σταλθεί στο αντικείμενο client που θα δημιουργηθεί στην συνέχεια
        int randomPhoneClientDesires;
        Class phoneClass = null; //αρχικοποίηση για χρήση του αντικειμένου έξω από την δομή επιλογής if..else
        for(int i =0;i<=userInputClient;i++){
            randomPhoneClientDesires = random.nextInt(2);
            if(randomPhoneClientDesires == 0){
                phoneClass = FeaturePhone.class;
                randomPhoneClientDesiresList.add(phoneClass); //προσθήκη στην Λίστα randomPhoneClientDesiresList
            }else if(randomPhoneClientDesires == 1){
                phoneClass = SmartPhone.class;
                randomPhoneClientDesiresList.add(phoneClass);  //προσθήκη στην Λίστα randomPhoneClientDesiresList
            }
        }
        //δημιουργία και αρχικοποίηση αντικειμένου client
        Client client = null; //αρχικοποίηση για χρήση του αντικειμένου έξω από την for..loop
        //με χρήση της for..loop θα δημιουργηθούν τόσοι πελάτες όσες δηλώσει ο χρήστης κατά την είσοδο του προγράμματος με χτήση της μεταβλητής userInputClient του αντικειμένου scanner
        //χρησιμοποίησα το αντικείμενο random της κλάσης Random για την παραγωγή τυχαίων ονομάτων βάση του πίνακα String[] names.
        for(int i =0; i<userInputClient;i++){
            phoneClass = randomPhoneClientDesiresList.get(i);  //Ανάγνωση από τη λίστα randomPhoneClientDesiresList με τη σειρά που έχει δημιουργηθεί για τον κάθε client και αποστολή τους ως όρισμα μέσω του ανρικειμένου client
            int randomClientsCreationList = random.nextInt(names.length);
            client = new Client(names[randomClientsCreationList], phoneClass); //αποστολή τω πληροφοριών στο αντικείμενο Client
            PhoneOrderHandler.addListener(client); //αποστολή του αντικειμένου client στη μέθοδο addlistener της κλάσης PhoneOrderHandler
        }
        //εκκίνηση της κατασκευής των τηλεφώνων μέσω της μεθόδου buildPhones της κλάσης PhoneOrderHandler
        PhoneOrderHandler.buildPhones();

        //κάλεσμα μεθόδου report η οποία τυπώνει τους πελάτες οι οποίοι δεν έχουν λάβει τηλέφωνο.
        //Η μέθοδος αυτή δημιουργήθηκε από εμένα.
        reportGetSizeOfPhoneCreationListeners();
    }

    //μέθοδος report με το κάλεσμα της θα τυπώσει μέσω τις λίσταν πελατών της κλάσης PhoneCreationListener μιας και
    //αυτή καλείται για να ενημερωθεί η λίστα πελατών phoneCreationListeners ώστε να υπολογίσω τους εναπομείναντες πελάτες οι οποίοι δεν έλαβαν τηλέφωνο
    public static void reportGetSizeOfPhoneCreationListeners(){
        PhoneOrderHandler phoneOrderHandler = new PhoneOrderHandler(); 
        System.out.println("%%%%%----Report----%%%%%");
        if(phoneOrderHandler.getSizeOfPhoneCreationListeners()==1){
            System.out.println(phoneOrderHandler.getSizeOfPhoneCreationListeners()+" client did not get a phone...");  //πληθική συντακτική διόρθωση 1 client αντί 1 clients
        }else if(phoneOrderHandler.getSizeOfPhoneCreationListeners()>1){
            System.out.println(phoneOrderHandler.getSizeOfPhoneCreationListeners()+" clients did not get a phone...");
        }else{
            System.out.println("0 clients watting...all clients got their phones...");
        System.out.println("%%%%%--------------%%%%%");
        }
    }
}
