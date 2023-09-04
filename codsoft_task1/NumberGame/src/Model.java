import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Model {
    private final String fileName = "Winners.txt";
    private final File file = new File(fileName);

    public void writeToFile(String playerName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
            if (checkName(playerName)) {
                //if the playerName exists , update his score if he wins again
                updateFile(playerName);
            }
            else {
                //write the new player
                int playerScore = 1;
                String scoreBoard = """
                        ----------------------------------
                                    Scoreboard
                        ----------------------------------
                          Name          Score        Date
                                              
                         """;
                //
                String line = String.format("%-10s  %5d       %s", playerName, playerScore, showDate().trim());
                if (!checkName("Scoreboard")) {
                    writer.write(scoreBoard);
                }
                writer.write("- " + line);
            }
            writer.newLine();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void readFromFile(){
        try {
            if(file.exists()) {
                String content;
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                while ((content = reader.readLine()) != null) {
                    View.displayFileContent(content);
                }
                reader.close();
            }else{
                View.displayNoWinners();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void updateFile(String playerName){
        try{
            Path path = Paths.get(fileName);
            List<String> lines  = Files.readAllLines(path);
            List<String> updatedLines = new ArrayList<>();
            for(String line : lines) {
                if(line.contains(playerName)){
                    String[] parts = line.split("\\s{2,}",3); // Split by two or more whitespace characters limited by 3
                    int oldScore = Integer.parseInt(parts[1]);
                    oldScore++; // Increment the score
                    // The Name is stand still (%-10s)
                    // The score value is formatted with three spaces (%3d)
                    // The date gets more space too (%s)
                    line = String.format("%-10s  %6d         %s", parts[0], oldScore, parts[2].trim().trim()); //trim removes any extra whitespaces
                }
                updatedLines.add(line);
            }
            Files.write(path, updatedLines, StandardOpenOption.WRITE);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public Boolean checkName(String Name){
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null){
                if(line.contains(Name))
                    return true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    public String showDate(){
        SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        //Date start = Date.from(Instant.now());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1); //summer system time
        Date start = calendar.getTime();
        return Format.format(start);
    }
}
