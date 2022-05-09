public class Cleaner {

    private final String cleanerTc;
    private final String cleanerName;
    private final float cleanerWage;
    private final String managerTc;

    public Cleaner(
            String cleanerTc,
            String cleanerName,
            float cleanerWage,
            String managerTc
    ){
        this.cleanerTc = cleanerTc;
        this.cleanerName = cleanerName;
        this.cleanerWage = cleanerWage;
        this.managerTc = managerTc;
    }

    //Create get methods for cleanerTc, cleanerName, cleanerWage, managerTc
    public String getCleanerTc() {
        return cleanerTc;
    }

    public String getCleanerName() {
        return cleanerName;
    }

    public float getCleanerWage() {
        return cleanerWage;
    }

    public String getManagerTc() {
        return managerTc;
    }


}
