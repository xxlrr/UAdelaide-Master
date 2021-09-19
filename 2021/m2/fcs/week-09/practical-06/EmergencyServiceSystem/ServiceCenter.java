public class ServiceCenter {
    private WaitingList awl;

    public ServiceCenter() {
        this.awl = new WaitingList();
    }

    /**
     * Record patient's data and add the patient into waiting list
     * @param name
     * @param phoneNumber
     * @param triageLevel
     * @param location
     */
    public void addPatientIntoList(String name, String phoneNumber, int triageLevel, String location) {
        Patient patient = new Patient(name, phoneNumber, triageLevel, location);
        if(this.awl.isInList(patient)){
            System.out.println(patient.getName() + " is in waiting list. ");
        }else{
            this.awl.addToList(patient);
            System.out.println("Add " + patient.getName() + " into waiting list. ");
        }
    }

    /**
     * Pop out the first patient in the waiting list and assign an Ambulance for him/her
     * @return the patient object
     */
    public Patient assignAmbulanceForPatient() {
        // TODO: The tester report that the system will crash when waiting list is empty
        Patient patient = this.awl.popPatient();
        if(patient == null) {
            System.out.println("Error! The waiting list is empty. ");
            return null;
        }
        System.out.println("Assigned an ambulance for patient: " + patient.getName());
        return patient;
    }
    
    /**
     * Pop out one patient by his/her id in the waiting list and assign an Ambulance for him/her
     * @return the patient object
     */
    public Patient assignAmbulanceForPatient(int id) {
        Patient patient = this.awl.popPatient(id);
        if(patient == null) {
            System.out.println("Error! No found the patient by the id. ");
            return null;
        }
        System.out.println("Success! An ambulance as assigned for patient " + patient.getName());
        return patient;
    }
    
    /**
     * Check how many patients are waiting before this patient given the patient's Id
     */
    public void checkPositionForPatient(int id) {
        int pos = this.awl.indexOf(id);
        if(pos == -1) {
            System.out.println("Error! No found the patient by the id. ");
            return;
        }
        System.out.println("There are " + pos + " patients before patient " + id + ".");
    }

    /**
     * Print out the waiting list
     */
    public void printWaitingList(){
        this.awl.printList();
    }
}
