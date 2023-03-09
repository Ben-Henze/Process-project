import java.util.ArrayList;

import java.util.*;

public class Resource {
    String name;
    List<Activity> activities;
    int unitLoad;
    double capacityRate;

    public Resource(String name) {
        this.activities = new ArrayList<>();
        this.unitLoad = 0;
        this.capacityRate = 0;
        this.name = name;
    }

    public void addActivity(Activity a) {
        activities.add(a);
    }

    public void initializeCapRate() {
        this.initializeUnitLoad();
        this.capacityRate = 1.0 / ((double)this.unitLoad / 60.0);
    }

    public void initializeUnitLoad() {
        int count = 0;
        for (int i =0; i < this.activities.size(); i++){
            count = count + activities.get(i).getTime();
        }
        this.unitLoad = count;
    }

    public String getName() {
        return this.name;
    }

    public double getCapRate() {
        return capacityRate;
    }




}
