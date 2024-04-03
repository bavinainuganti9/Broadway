import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.HashSet;
import java.util.TreeMap;
import java.lang.Integer;
import java.text.NumberFormat;
public class TreeMapTask {
    public static void main(String[] args){
        TreeMapTask t = new TreeMapTask();
    }

    public TreeMapTask(){
        File file = new File("Broadway2022.csv");

        ArrayList<Show> shows = new ArrayList<Show>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp;
            reader.readLine();
            while((temp=reader.readLine()) != null){
                String[] pieces = temp.split(",");
                shows.add(new Show(pieces[0], pieces[1], pieces[2], pieces[3], Double.parseDouble(pieces[4]), Integer.parseInt(pieces[5]), Double.parseDouble(pieces[6])));
            }
        }
        catch(IOException e){

        }

        grossByMonth(shows);
        System.out.println();

        grossByType(shows);
        System.out.println();

        attendanceByType(shows);
        System.out.println();

        grossByShowPerWeek(shows);
        System.out.println();
        
        attendanceByShowPerWeek(shows);
        System.out.println();

        attendanceByMonth(shows);
        System.out.println();

        attendanceByTheatre(shows);
        System.out.println();

        grossByTheatre(shows);
        System.out.println();
        
    }

    public void grossByMonth(ArrayList<Show> a){
        TreeMap<Integer, Double> map = new TreeMap<Integer, Double>();
        for(int i=1;i<=12;i++){
            map.put(i, 0.0);
        }


        for(int i=0;i<a.size();i++){
            String[] pieces = a.get(i).getDate().split("/");
            int k = new Integer(pieces[0]);
            map.put(k, (map.get(k)+a.get(i).getGross()));
        }

        System.out.println("Total Gross Earnings By Month\n");

        Locale locale = new Locale("en", "US");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        for(int key:map.keySet()){
            System.out.println(key+": "+currencyFormatter.format(map.get(key))+"\n");
        }
    }

    public void grossByType(ArrayList<Show> a){
        TreeMap<String, Double> map = new TreeMap<String, Double>();
        map.put("Musical", 0.0);
        map.put("Play", 0.0);
        map.put("Special", 0.0);
        
        for(int i=0;i<a.size();i++){
            String k = a.get(i).getType();
            map.put(k, (map.get(k)+a.get(i).getGross()));
        }

        System.out.println("Total Gross Earnings By Type\n");

        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        for(String key:map.keySet()){
            System.out.println(key+": "+currencyFormatter.format(map.get(key))+"\n");
        }
    }

    public void attendanceByType(ArrayList<Show> a){
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        map.put("Musical", 0);
        map.put("Play", 0);
        map.put("Special", 0);
        
        for(int i=0;i<a.size();i++){
            String k = a.get(i).getType();
            map.put(k, (map.get(k)+a.get(i).getAttendees()));
        }

        System.out.println("Attendence By Type\n");

        for(String key:map.keySet()){
            System.out.println(key+": "+map.get(key)+"\n");
        }
    }

    public void grossByShowPerWeek(ArrayList<Show> a){
        TreeMap<String, ArrayList<Double>> map1 = new TreeMap<String, ArrayList<Double>>();
        
        for(int i=0;i<a.size();i++){
            String k = a.get(i).getName();
            if(!map1.containsKey(k)){
                map1.put(k, new ArrayList<>());
            }
            map1.get(k).add(a.get(i).getGross());
        }

        System.out.println("Gross Earnings By Show Per Week\n");

        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        for(String key:map1.keySet()){
            ArrayList<Double> list = new ArrayList<>(map1.get(key));
            System.out.println(key);
            while(!list.isEmpty()){
                System.out.println("\t"+currencyFormatter.format(list.remove(0)));
            }
        }
        
        TreeMap<String, Double> map2 = new TreeMap<String, Double>();

        for(int i=0;i<a.size();i++){
            String k = a.get(i).getName();
            if(!map2.containsKey(k)){
                map2.put(k, 0.0);
            }
            map2.put(k, map2.get(k) + a.get(i).getGross());
        }

        System.out.println("\n\nTotal Gross Earnings By Show Per Week\n");

        for(String key:map2.keySet()){
            System.out.println(key+": "+currencyFormatter.format(map2.get(key))+"\n");
        }
    }

    public void attendanceByShowPerWeek(ArrayList<Show> a){
        TreeMap<String, ArrayList<Integer>> map1 = new TreeMap<String, ArrayList<Integer>>();
        
        for(int i=0;i<a.size();i++){
            String k = a.get(i).getName();
            if(!map1.containsKey(k)){
                map1.put(k, new ArrayList<>());
            }
            map1.get(k).add(a.get(i).getAttendees());
        }

        System.out.println("Attendance By Show Per Week\n");

        for(String key:map1.keySet()){
            ArrayList<Integer> list = new ArrayList<>(map1.get(key));
            System.out.println(key);
            while(!list.isEmpty()){
                System.out.println("\t"+list.remove(0));
            }
        }
        
        TreeMap<String, Integer> map2 = new TreeMap<String, Integer>();

        for(int i=0;i<a.size();i++){
            String k = a.get(i).getName();
            if(!map2.containsKey(k)){
                map2.put(k, 0);
            }
            map2.put(k, map2.get(k) + a.get(i).getAttendees());
        }

        System.out.println("\n\nTotal Attendance By Show Per Week\n");

        for(String key:map2.keySet()){
            System.out.println(key+": "+map2.get(key)+"\n");
        }
    }

    public void attendanceByMonth(ArrayList<Show> a){
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for(int i=1;i<=12;i++){
            map.put(i, 0);
        }


        for(int i=0;i<a.size();i++){
            String[] pieces = a.get(i).getDate().split("/");
            int k = new Integer(pieces[0]);
            map.put(k, (map.get(k)+a.get(i).getAttendees()));
        }

        System.out.println("Total Attendance By Month\n");

        for(int key:map.keySet()){
            System.out.println(key+": "+map.get(key)+"\n");
        }
    }

    public void attendanceByTheatre(ArrayList<Show> a){

        TreeMap<String, Integer> map = new TreeMap<String, Integer>();

        for(int i=0;i<a.size();i++){
            String k = a.get(i).getTheatre();
            if(!map.containsKey(k)){
                map.put(k, 0);
            }
            map.put(k, map.get(k) + a.get(i).getAttendees());
        }

        System.out.println("Attendance By Theatre\n");

        for(String key:map.keySet()){
            System.out.println(key+": "+map.get(key)+"\n");
        }
    }

    public void grossByTheatre(ArrayList<Show> a){

        TreeMap<String, Double> map = new TreeMap<String, Double>();

        for(int i=0;i<a.size();i++){
            String k = a.get(i).getTheatre();
            if(!map.containsKey(k)){
                map.put(k, 0.0);
            }
            map.put(k, map.get(k) + a.get(i).getGross());
        }

        System.out.println("Gross Earnings By Theatre\n");

        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        for(String key:map.keySet()){
            System.out.println(key+": "+currencyFormatter.format(map.get(key))+"\n");
        }
    }

    public  class Show{
        private String date;
        private String name;
        private String type;
        private String theatre;
        private double gross;
        private int attendees;
        private double percentCap;

        public Show(String date, String name, String type, String theatre, double gross, int attendees, double percentCap){
            this.date = date;
            this.name = name;
            this.type = type;
            this.theatre = theatre;
            this.gross = gross;
            this.attendees = attendees;
            this.percentCap = percentCap;
        }

        public String getDate(){
            return date;
        }

        public String getName(){
            return name;
        }

        public String getType(){
            return type;
        }

        public String getTheatre(){
            return theatre;
        }

        public double getGross(){
            return gross;
        }

        public int getAttendees(){
            return attendees;
        }

        public double getPercentCap(){
            return percentCap;
        }
    }

}
