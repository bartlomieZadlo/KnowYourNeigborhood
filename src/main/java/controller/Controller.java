package controller;

import dao.CSVDataReader;
import model.Community;
import model.County;
import model.Location;
import model.Voivodeship;
import view.View;

import java.util.*;

public class Controller {
    private List<Voivodeship> voivodeshipList;
    private List<County> counties;
    private List<Community> communities;
    private List<Location> locations;
    private View view;

    public Controller(){
        CSVDataReader reader = new CSVDataReader(Controller.class.getClassLoader().getResource("malopolska.csv").getPath());
        voivodeshipList = reader.loadVoidvodeships();
        counties = reader.loadCounties();
        communities = reader.loadCommunities();
        locations = reader.loadLocations();
        view = new View();
    }


    public void runApp(){
        int option = 1;

        while (!(option==0 )){

            view.printMenu("Exit",
                    "Print statistics",
                    "Cities with Longest Names",
                    "County with most communities",
                    "Locations with more than one category",
                    "AdvancedSearch");

            option = view.getInputInt(0,5);

            switch (option){
                case 1:
                    printStatistics();
                    break;
                case 2:
                    printCitiesWithLongestNames();
                    break;
                case 3:
                    printCountyWithMostCommunities();
                    break;
                case 4:
                    printLocWithMoreCat();
                    break;
                case 5:
                    advancedSearch();
                    break;
            }
        }
    }

    private void advancedSearch() {
        String searchTarget = view.getInputString("Searching for: ");
        CharSequence sequence = searchTarget.subSequence(0,searchTarget.length());
        int i =0;
        List<Location> list = new ArrayList<>();
        for (Location location : locations){
            if (location.getName().contains(sequence)){
                list.add(location);
                i++;
            }
        }
        view.print("found " + String.valueOf(i) + " location(s)");
        for (Location location : list){
            view.print(location.getName());
        }
        view.waitForConfirm();
    }

    private void printLocWithMoreCat() {
        Set<String> searchedLocations = prepareSet();
        for (String loc : searchedLocations){
            view.print(loc);
        }
        view.waitForConfirm();
    }

    private Set<String> prepareSet() {
        Set<String> set = new HashSet<>();
        List<String> checkedLocations = new ArrayList<>();
        for (County county: counties){
            if (county.getLocType().equals("miasto na prawach powiatu")){
                set.add(county.getName());
            }
        }
        for (Location location : locations){
            if (checkedLocations.contains(location.getName())){
                set.add(location.getName());
            }
            checkedLocations.add(location.getName());
        }
        return set;
    }

    private void printCountyWithMostCommunities() {
        Map.Entry<Integer, Integer> maxEntry = getMaxEntry();
        for (County county : counties){
            if (county.getCountIndex() == maxEntry.getKey()){
                view.print(county.getName());
            }
        }
        view.waitForConfirm();
    }

    private Map.Entry<Integer,Integer> getMaxEntry() {
        Map<Integer, Integer> countyCommunityCount = countCommunities();
        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : countyCommunityCount.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }

    private void printCitiesWithLongestNames() {
        List<Location> citiesList = getCities();
        citiesList.sort((o1, o2) -> Integer.compare(o2.getName().length(), o1.getName().length()));
        view.print(citiesList.get(0).getName());
        view.print(citiesList.get(1).getName());
        view.print(citiesList.get(2).getName());
        view.waitForConfirm();
        }



    private List<Location> getCities() {
        List<Location> citiesList = new ArrayList<>();
        for (Location location : locations) {
            if (location.getLocType().equals("miasto")) {
                citiesList.add(location);
            }
        }
        return citiesList;
    }

    private void printStatistics() {
        for (Voivodeship voivodeship : voivodeshipList){
            view.print(voivodeship.getName());
            view.print(getCounties(voivodeship, "powiat"));
            view.print(getCommunityByType(voivodeship, "gmina miejska"));
            view.print(getCommunityByType(voivodeship, "gmina wiejska"));
            view.print(getCommunityByType(voivodeship, "gmina miejsko-wiejska"));
            view.print(getCommunityByType(voivodeship, "obszar wiejski"));
            view.print(getLocation(voivodeship, "miasto"));
            view.print(getCounties(voivodeship, "miasto na prawach powiatu"));
            view.print(getLocation(voivodeship, "delegatura"));
            view.waitForConfirm();
        }
    }


    private String getLocation(Voivodeship voivodeship, String type) {
        int i = 0;
        for (Location location : locations){
            if (location.getVoidIndex() == voivodeship.getIndex() && location.getLocType().equals(type)){
                i ++;
            }
        }
        return i + type;
    }

    private String getCommunityByType(Voivodeship voivodeship, String type) {
        int i = 0;
        for(Community community : communities){
            if (community.getVoidIndex() == voivodeship.getIndex() && community.getLocType().equals(type)){
                i++;
            }
        }
        return String.valueOf(i) + type;
    }

    private String getCounties(Voivodeship voivodeship, String type) {
        int i = 0;
        for (County county : counties){
            if (county.getVoidIndex() == voivodeship.getIndex() && county.getLocType().equals(type)){
                i++;
            }
        }
        return String.valueOf(i) + type;
    }

    private Map<Integer,Integer> countCommunities() {
        Map<Integer, Integer> communityCount = new HashMap<>();
        for (County county :counties) {
            communityCount.put(county.getCountIndex(), 0);
        }
        for (Community community : communities){
            communityCount.put(community.getCountIndex(), communityCount.get(community.getCountIndex()) + 1);
        }
        return communityCount;

}
}





