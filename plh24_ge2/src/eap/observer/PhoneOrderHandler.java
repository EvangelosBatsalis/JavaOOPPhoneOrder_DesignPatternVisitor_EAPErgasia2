package eap.observer;

import eap.abstractfactory.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneOrderHandler {
    private static final List<PhoneCreationListener> phoneCreationListeners = new ArrayList<>();
    private static final List<Phone> phoneList = new ArrayList<>();
    public static void addPhone(Phone phone){
        phoneList.add(phone);
    }
    public static void removePhone(Phone phone){
        phoneList.remove(phone);
    }
    public static boolean phoneExistsInList(Phone phone){return phoneList.contains(phone);}
    
    public static void buildPhones(){
        System.out.println("\n##########-Phone Build Process Started-##########");
        List<Phone> phones = new ArrayList<>(phoneList);//Make a copy of the phones that should be created
        for (Phone phone : phones){
            if (phone instanceof FeaturePhone){
                PhoneFactory.getPhone(new FeaturePhoneFactory(phone.getBatterySize(),phone.getScreenSize(),
                        phone.getPhoneNumber(),phone.getManufacturer(),phone.getStorage()));
                System.out.println("\n$$$$-New Phone-$$$$");
                System.out.println(phone+ "\nHas been created");
                System.out.println("$$$$-----------$$$$");
            }else if (phone instanceof SmartPhone){
                SmartPhone smartPhone = (SmartPhone)phone;
                PhoneFactory.getPhone(new SmartPhoneFactory(smartPhone.getBatterySize(),smartPhone.getScreenSize(),
                        smartPhone.getPhoneNumber(),smartPhone.getManufacturer(),smartPhone.getStorage(),
                        smartPhone.getCamera(),smartPhone.getOperatingSystem()));
                System.out.println("\n$$$$-New Phone-$$$$");
                System.out.println(phone+ "\nHas been created");
                System.out.println("$$$$-----------$$$$");
            }
            notifyListeners(phone);
        }
        printCreatedPhoneSpecsToTextFile(phones);
        System.out.println("\n########-Phone Specs saved to output.txt-########");
        System.out.println("###########-Phone Build Process Ended-###########");
    }
    //Η μέθοδος αυτή θα εκτυπώνει τα βασικά χαρακτηριστικά, μόνο από την κλάση Phone του κινητού τηλεφώνου
    //όσων κινητών παράχθηκαν
    private static void printCreatedPhoneSpecsToTextFile(List<Phone> phones){
        //Αρχικοποιώ μετρητή counter τον οποίο θα τον χρησιμοποιήσω για μετρητή στην αποθήκευση του αρχείου
        int counter = 1;
        //χρησιμοποιώ try..catch με resourses για να κλείσει αυτόματα ο πόρος μετά το πέρας την εκτέλεσης του block 
        // και για να πιάσει τυχόν σφάλματα κατα τή δημιουργία ή/και εγγραφή του αρχείου.
        //πηγή: https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        //δημιουργία και αρχικοποίηση αντικειμένου FileWriter ως όρισμα το όνομα της εξόδου του αρχείου
        try(FileWriter fileWriter = new FileWriter("output.txt")){
            //χρησιμοποιώ for..each loop για να κάνει itterate και εγγραφή όλων των στοιχείων από τη λίστα phones στο αρχείο output.txt
            for(Phone output:phones){
                fileWriter.write(counter+"."+"\n"+output.toString()+"\n"); //έξοδος της λίστας μέσω της μεθόδου write του αντικειμένου FileWriter
                counter++;  //μετρητής αύξησης κατά μία φορά μόλις ολοκληρωθεί το κάθε loop επιπλέον. το χρησιμοποιώ για την έξοδο αριθμοδότησης του αρχείου.
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public static void addListener(PhoneCreationListener phoneCreationListener){
        phoneCreationListeners.add(phoneCreationListener);
    }
    //Δημιουργία μεθόδου επιστροφής μέγεθος λίστας αναμένοντων πελατών όπου δεν έχουν πάρει τηλέφωνο
    public int getSizeOfPhoneCreationListeners(){
        return phoneCreationListeners.size();
    }
    
    public static void removeListener(PhoneCreationListener phoneCreationListener){
        phoneCreationListeners.remove(phoneCreationListener);
    }
    private static void notifyListeners(Phone phone){
        List<PhoneCreationListener> tempPhoneCreationListeners = new ArrayList<>(phoneCreationListeners);
        for(PhoneCreationListener listener : tempPhoneCreationListeners){
            listener.update(phone);
        }
    }
    
}
