import java.util.ArrayList;
import java.util.Comparator;

class Participant {
    private String name;
    private int id;
    private int swimmingTime;
    private int cyclingTime;
    private int runningTime;
    private int totalTime;

    public Participant(String name, int id, int swimmingTime, int cyclingTime, int runningTime) {
        if (swimmingTime < 0 || cyclingTime < 0 || runningTime < 0) {
            throw new IllegalArgumentException("Error: Times cannot be negative.");
        }
        this.name = name;
        this.id = id;
        this.swimmingTime = swimmingTime;
        this.cyclingTime = cyclingTime;
        this.runningTime = runningTime;
        calculateTotalTime();
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public int getSwimmingTime() { return swimmingTime; }
    public int getCyclingTime() { return cyclingTime; }
    public int getRunningTime() { return runningTime; }
    public int getTotalTime() { return totalTime; }

    public void setSwimmingTime(int swimmingTime) {
        if (swimmingTime < 0) throw new IllegalArgumentException("Error: Time cannot be negative.");
        this.swimmingTime = swimmingTime;
        calculateTotalTime();
    }

    public void setCyclingTime(int cyclingTime) {
        if (cyclingTime < 0) throw new IllegalArgumentException("Error: Time cannot be negative.");
        this.cyclingTime = cyclingTime;
        calculateTotalTime();
    }

    public void setRunningTime(int runningTime) {
        if (runningTime < 0) throw new IllegalArgumentException("Error: Time cannot be negative.");
        this.runningTime = runningTime;
        calculateTotalTime();
    }

    private void calculateTotalTime() {
        this.totalTime = swimmingTime + cyclingTime + runningTime;
    }

    public void displayDetails() {
        System.out.println("Participant Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Swimming Time: " + swimmingTime + " minutes");
        System.out.println("Cycling Time: " + cyclingTime + " minutes");
        System.out.println("Running Time: " + runningTime + " minutes");
        System.out.println("Total Time: " + totalTime + " minutes");
    }
}

class EliteParticipant extends Participant {
    private String sponsorName;

    public EliteParticipant(String name, int id, int swimmingTime, int cyclingTime, int runningTime, String sponsorName) {
        super(name, id, swimmingTime, cyclingTime, runningTime);
        this.sponsorName = sponsorName;
    }

    public String getSponsorName() { return sponsorName; }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Sponsor: " + sponsorName);
    }
}

class BeginnerParticipant extends Participant {
    public BeginnerParticipant(String name, int id, int swimmingTime, int cyclingTime, int runningTime) {
        super(name, id, swimmingTime, cyclingTime, runningTime);
    }
}

public class TriathlonResults {
    public static void main(String[] args) {
        try {
            ArrayList<Participant> participants = new ArrayList<>();

            participants.add(new EliteParticipant("Alice", 1, 25, 40, 20, "HealthCo"));
            participants.add(new BeginnerParticipant("Bob", 2, 20, 35, 25));
            participants.add(new BeginnerParticipant("Charlie", 3, 30, 50, 30));
            participants.add(new EliteParticipant("Diana", 4, 28, 42, 18, "FitLife"));

            participants.sort(Comparator.comparingInt(Participant::getTotalTime));

            System.out.println("Sorted Results by Total Time:");
            System.out.println("============================");
            for (Participant p : participants) {
                p.displayDetails();
                System.out.println("----------------------------");
            }

            if (participants.size() >= 2) {
                System.out.println("\nFastest Participant:");
                System.out.println("==================");
                participants.get(0).displayDetails();
                
                System.out.println("\nSecond Fastest Participant:");
                System.out.println("========================");
                participants.get(1).displayDetails();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
