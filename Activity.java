import java.util.ArrayList;

import java.util.*;

public class Activity {
    String name = null;
    List<Activity> prev = new ArrayList<Activity>();
    List<Activity> next = new ArrayList<Activity>();
    int time = 0;
    List<Resource> resources = new ArrayList<Resource>();


    public Activity(String name, int time) {
        this.name = name;  
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    public String getName() {
        return this.name;
    }

    public void addResource(Resource r) {
        resources.add(r);
    }

    public void addPrev(Activity a) {
        this.prev.add(a);
    }

    public void addNext(Activity a) {
        this.next.add(a);
    }

    public List<Activity> getNext() {
        return this.next;
    }
}
