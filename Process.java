import java.util.*;

public class Process {
    Activity start = null;
    Activity end = null;
    List<Activity> activities = new ArrayList<Activity>();

    public Process(List<Activity> activities) {
        this.activities = activities;
        this.start = findStart(activities);
        this.end = findEnd(activities);    
    }

    //returns activity with no prev activites or first of the list
    public Activity findStart(List<Activity> a) {
        Activity result = a.get(0);
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).prev.isEmpty()) {
                result = a.get(i);
            }
        }
        return result;
    }

    //returns activity with no next activites or last of the list
    public Activity findEnd(List<Activity> a) {
        Activity result = a.get(a.size());
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).next.isEmpty()) {
                result = a.get(i);
            }
        }
        return result;
    }
}
