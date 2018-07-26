package dao;

import model.Community;
import model.County;
import model.Location;
import model.Voivodeship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVDataReader {
    private Scanner fileReader;
    private String filePath;

    public CSVDataReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Voivodeship> loadVoidvodeships(){
        fileReader = getScanner();
        List<Voivodeship> voivodeships = new ArrayList<>();
        while (fileReader.hasNext()){
            String[] line = fileReader.nextLine().split("\\s+");
            if (line.length == 3){
                voivodeships.add(new Voivodeship(line[1], Integer.parseInt(line[0]), line[2]));
            }
        }
        return voivodeships;
    }

    public List<County> loadCounties(){
        fileReader = getScanner();
        List<County> counties = new ArrayList<>();
        while (fileReader.hasNext()){
            String[] line = fileReader.nextLine().split("\\t+");
            if (line.length > 3) {
                if (line[3].equals("powiat") || line[3].equals("miasto na prawach powiatu")){
                    counties.add(new County(line[2], Integer.parseInt(line[0]), Integer.parseInt(line[1]), line[3]));
                }
            }
        }
        return counties;
    }

    public List<Community> loadCommunities(){
        fileReader = getScanner();
        List<Community> communities  = new ArrayList<>();
        while (fileReader.hasNext()){
            String[] line = fileReader.nextLine().split("\\t+");
            if (line.length == 6){
                communities.add(new Community(line[4], Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), line[5]));
            }
        }
        return communities;
    }

    public List<Location> loadLocations(){
        fileReader = getScanner();
        List<Location> locations  = new ArrayList<>();
        while (fileReader.hasNext()){
            String[] line = fileReader.nextLine().split("\\t+");
            if (line.length == 6){
                locations.add(new Location(line[4], Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), line[5]));
            }
        }
        return locations;
    }

    private Scanner getScanner() {

        Scanner fileReader = null;

        try {
            fileReader = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileReader;
    }
}
