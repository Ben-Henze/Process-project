import java.util.ArrayList;

import java.util.*;

public class Activity {
    String name = null;
    List<Activity> prev = new ArrayList<Activity>();
    List<Activity> next = new ArrayList<Activity>();
    int time = 0;
    List<Resource> resources = new ArrayList<Resource>();

    public Activity(String name, List<Activity> prev, List<Activity> next, int time, List<Resource> resources) {
        this.name = name;
        this.prev = prev;
        this.next = next;    
        this.time = time;
        this.resources = resources;
    }

    public int getTime() {
        return this.time;
    }
}
