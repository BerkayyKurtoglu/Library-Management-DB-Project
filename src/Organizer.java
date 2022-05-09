public class Organizer {

    private final String organizerId;
    private final String organizerName;
    private final float organizerSalary;
    private final String managerId;

    public Organizer(
            String organizerId,
            String organizerName,
            float organizerSalary,
            String managerId
    ){
        this.organizerId = organizerId;
        this.organizerName = organizerName;
        this.organizerSalary = organizerSalary;
        this.managerId = managerId;
    }

    //Create get methods for organizerId, organizerName, organizerSalary, managerId
    public String getOrganizerId() {
        return organizerId;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public float getOrganizerSalary() {
        return organizerSalary;
    }

    public String getManagerId() {
        return managerId;
    }


}
