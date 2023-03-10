public class Main {

public static void main(String[] args) {
    Process main = new Process();
    main.loadFromCSV();
    for (int i = 0; i < main.activities.size(); i++) {
        System.out.println(main.activities.get(i).getName());
    }
    for (int i = 0; i < main.resources.size(); i++) {
        System.out.println(main.resources.get(i).getName());
        System.out.println(main.resources.get(i).getCapRate());
    }
    
    for (int i = 0; i < main.findLongestPath(main.start).size(); i++) {
        System.out.println(main.findLongestPath(main.start).get(i).getName());
    }
}

}