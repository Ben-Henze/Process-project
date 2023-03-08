import java.util.ArrayList;

import java.util.*;

public class Resource {
    List<Activity> activities;
    int unitLoad;
    int capacityRate;

    public Resource() {
        this.activities = new ArrayList<>();
        this.unitLoad = 0;
        this.capacityRate = 0;
    }

    public void addActivity(Activity a) {
        activities.add(a);
    }

    public void initializeCapRate() {
        this.initializeUnitLoad();
        this.capacityRate = (1 / this.unitLoad);
    }

    public void initializeUnitLoad() {
        int count = 0;
        for (int i =0; i < this.activities.size(); i++){
            count =+ activities.get(i).getTime();
        }
        unitLoad = count;
    }


}
