import java.util.*;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  

public class Process {
    Activity start = null;
    Activity end = null;
    List<Activity> activities = null;
    Activity temp;
    List<Resource> resources = null;

    //create
    public Process() {
        this.activities = new ArrayList<Activity>();
        this.resources = new ArrayList<Resource>();
    }

    // load from csv file
    public void loadFromCSV() {
        String line = "";
        String splitBy = ",";
        //MAKE ALL ACTIVITIES
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("Process.CSV"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] activity = line.split(splitBy); // use comma as separator
                // activity[0] //name
                // activity[1] //predecessor
                // activity[2] //sucessor
                // activity[3] //time
                // activity[4] //resouce(s)
                Activity temp = new Activity(activity[0], Integer.valueOf(activity[3]));
                activities.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ADD RESOURCES
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("Process.CSV"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] activity = line.split(splitBy); // use comma as separator
                // activity[0] //name
                // activity[1] //predecessor
                // activity[2] //sucessor
                // activity[3] //time
                // activity[4] //resouce
                if (!this.resources.contains(findResouce(activity[4]))) {
                Resource temp = new Resource(activity[4]);
                resources.add(temp);
                }
                if (findResouce(activity[4]) != null) {
                findActivity(activity[0]).addResource((findResouce(activity[4])));
                (findResouce(activity[4])).addActivity(findActivity(activity[0]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < resources.size(); i++) {
            resources.get(i).initializeCapRate();
        }

        //ADD PREV AND NEXT
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("Process.CSV"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] activity = line.split(splitBy); // use comma as separator
                // activity[0] //name
                // activity[1] //predecessor
                // activity[2] //sucessor
                // activity[3] //time
                // activity[4] //resouce(s)
                for (int i = 0; i < activity[1].length(); i++) {
                    findActivity(activity[0]).addPrev(findActivity(String.valueOf(activity[1].charAt(i))));
                }
                for (int i = 0; i < activity[2].length(); i++) {
                    findActivity(activity[0]).addNext(findActivity(String.valueOf(activity[2].charAt(i))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeProcess();
    

    }

    public int lengthOfPath(List<Activity> path) {
        int length = 0;
        for (Activity a : path) {
            length = length + a.getTime();
        }
        return length;
    }

    // public List<Activity> findCriticalPath() {
    //     List<Activity> cp = new ArrayList<Activity>();
    //     findCPHelper(start, cp);
    //     return cp;
    // }

    // public List<Activity> findCPHelper(Activity a, List<Activity> p) {
    //     if (a.getNext().size() > 1) {
    //         int maxLength = 0;
    //         Activity next = null;
    //         for (Activity i : a.getNext()) {
    //             if(lengthOfPath(findCPHelper(i, p)) > maxLength) {
    //                 next = i;
    //             }
    //             p.add(a);
    //             findCPHelper(next, p);
    //         }
    //     }
    //     if (a != end) {
    //         p.add(a);
    //         findCPHelper(a.getNext().get(0), p);
    //     }
    //     p.add(a);
    //     return p;
    // }

    public List<Activity> findLongestPath(Activity start) {
        List<Activity> path = new ArrayList<Activity>();
        List<Activity> longestPath = new ArrayList<Activity>();
        int maxLength = 0;
        findLongestPathHelper(start, path, longestPath, maxLength);
        return longestPath;
    }
    
    public void findLongestPathHelper(Activity current, List<Activity> path, List<Activity> longestPath, int maxLength) {
        path.add(current);
        if (current.getNext().size() > 0) {
            for (Activity next : current.getNext()) {
                if (!path.contains(next)) {
                    findLongestPathHelper(next, path, longestPath, maxLength);
                }
            }
        }
    
        if (lengthOfPath(path)  > maxLength) {
            maxLength = lengthOfPath(path);
            longestPath.clear();
            longestPath.addAll(path);
        }
    
        path.remove(current);
       
    }

    public double completableInT(double t) {
        double capRate = resources.get(0).getCapRate();
        for (int i =0;i < resources.size(); i++) {
            if (resources.get(i).getCapRate() < capRate) {  //finds the bottleneck resource
                capRate = resources.get(i).getCapRate();
            }
        }



        return 0;
    }

    //REQUIRES that n exists
    public Activity findActivity(String n) {
        Activity result = null;
        for (int i = 0; i < activities.size(); i++) {
            if(n.equals(activities.get(i).getName())) {
                result = activities.get(i);
            }
        }
        return result;
    }

    // REQUIRES that n exists
    public Resource findResouce(String r) {
        Resource result = null;
        for (int i = 0; i < resources.size(); i++) {
            if (r.equals(resources.get(i).getName()) ) {
                result = resources.get(i);
            }
        }
        return result;
    }
    

    //finds start and end
    public void initializeProcess() {
        this.start = findStart(activities);
        this.end = findEnd(activities);
    }

    //returns activity with no prev activites or first of the list
    public Activity findStart(List<Activity> a) {
        return activities.get(0);
    }

    //returns activity with no next activites or last of the list
    public Activity findEnd(List<Activity> a) {
        return activities.get(activities.size() - 1);
    }
}
