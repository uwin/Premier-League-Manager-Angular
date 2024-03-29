package View;

import Model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GuiJavaFx extends Application {
    List<SportsClub> clublistData;
    List<Match> matchlistData;
    private boolean state=true;


    public void update(){
        Serialize load = new Serialize();
        ArrayList <Object> deserialized = load.deserialize();

        clublistData = (List<SportsClub>) deserialized.get(0);
        matchlistData= (List<Match>) deserialized.get(1);
    }

    public static void main(String[] args) {
        run();
    }
    @Override
    public void start(Stage primaryStage) {
        Stage window = new Stage();
        window.setTitle("Premier League");
        clubTableScreen(window);
    }

    public static void run() {
        launch();
    }

    public ObservableList dataToTable(List listIn){
        ObservableList table = FXCollections.observableArrayList();
        table.addAll(listIn);
        return table;
    }
    public TableView<SportsClub> clubstable(){

        TableView<SportsClub> clubTable = new TableView<>();
        TableColumn<SportsClub,String> clubName = new TableColumn<>("Club");
        clubName.setMinWidth(80);
        clubName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<SportsClub,String> clubLoc = new TableColumn<>("Location");
        clubLoc.setMinWidth(80);
        clubLoc.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<SportsClub,String> clubPoints = new TableColumn<>("Points");
        clubPoints.setMinWidth(80);
        clubPoints.setCellValueFactory(new PropertyValueFactory<>("pointCount"));

        TableColumn<SportsClub,String> clubGoals = new TableColumn<>("Goals");
        clubGoals.setMinWidth(80);
        clubGoals.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));

        TableColumn<SportsClub,String> clubWins = new TableColumn<>("Wins");
        clubWins.setMinWidth(80);
        clubWins.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<SportsClub,String> clubLoss = new TableColumn<>("Loss");
        clubLoss.setMinWidth(80);
        clubLoss.setCellValueFactory(new PropertyValueFactory<>("defeat"));

        TableColumn<SportsClub,String> clubDraw = new TableColumn<>("Draw");
        clubDraw.setMinWidth(80);
        clubDraw.setCellValueFactory(new PropertyValueFactory<>("draws"));

        TableColumn<SportsClub,String> clubMatch = new TableColumn<>("Matches");
        clubMatch.setMinWidth(80);
        clubMatch.setCellValueFactory(new PropertyValueFactory<>("matchCount"));

        clubTable.setItems(dataToTable(clublistData));
        clubTable.getColumns().add(clubName);
        clubTable.getColumns().add(clubLoc);
        clubTable.getColumns().add(clubPoints);
        clubTable.getColumns().add(clubGoals);
        clubTable.getColumns().add(clubWins);
        clubTable.getColumns().add(clubLoss);
        clubTable.getColumns().add(clubDraw);
        clubTable.getColumns().add(clubMatch);

        return clubTable;
    }
    public TableView<SportsClub> matchtable(){

        TableView<SportsClub> matchTable = new TableView<>();
        TableColumn<SportsClub,String> matchDate = new TableColumn<>("Date");
        matchDate.setMinWidth(128);
        matchDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<SportsClub,String> teamAName = new TableColumn<>("Team A");
        teamAName.setMinWidth(128);
        teamAName.setCellValueFactory(new PropertyValueFactory<>("teamAName"));

        TableColumn<SportsClub,String> teamAScore = new TableColumn<>("Score");
        teamAScore.setMinWidth(128);
        teamAScore.setCellValueFactory(new PropertyValueFactory<>("teamAScore"));

        TableColumn<SportsClub,String> teamBName = new TableColumn<>("Team B");
        teamBName.setMinWidth(128);
        teamBName.setCellValueFactory(new PropertyValueFactory<>("teamBName"));

        TableColumn<SportsClub,String> teamBScore = new TableColumn<>("Score");
        teamBScore.setMinWidth(128);
        teamBScore.setCellValueFactory(new PropertyValueFactory<>("teamBScore"));


        matchTable.setItems(dataToTable(matchlistData));
        matchTable.getColumns().add(matchDate);
        matchTable.getColumns().add(teamAName);
        matchTable.getColumns().add(teamAScore);
        matchTable.getColumns().add(teamBName);
        matchTable.getColumns().add(teamBScore);
        return matchTable;
    }

    private void clubTableScreen(Stage window){
        AnchorPane first = new AnchorPane();
        Scene addViewFirst = new Scene(first, 680, 450);
        window.setScene(addViewFirst);

        update();
        TableView<SportsClub> clubTable = clubstable();
        AnchorPane.setLeftAnchor(clubTable,20d);
        AnchorPane.setTopAnchor(clubTable,50d);
        AnchorPane.setBottomAnchor(clubTable,80d);

        Button sortGoals = new Button("Goals");
        sortGoals.setOnAction(event -> {
            clublistData.sort((SportsClub::sortGoal));
            Collections.reverse(clublistData);
            clubTable.setItems(dataToTable(clublistData));
        });
        AnchorPane.setLeftAnchor(sortGoals,20d);
        AnchorPane.setBottomAnchor(sortGoals,20d);

        Button sortWins = new Button("Wins");
        sortWins.setOnAction(event -> {
            clublistData.sort((SportsClub::sortWins));
            Collections.reverse(clublistData);
            clubTable.setItems(dataToTable(clublistData));
        });
        AnchorPane.setLeftAnchor(sortWins,90d);
        AnchorPane.setBottomAnchor(sortWins,20d);

        Button sortPoints = new Button("Reset");
        sortPoints.setOnAction(event -> {
            clublistData.sort((SportsClub::sortPointsGoal));
            Collections.reverse(clublistData);
            clubTable.setItems(dataToTable(clublistData));
        });
        AnchorPane.setLeftAnchor(sortPoints,160d);
        AnchorPane.setBottomAnchor(sortPoints,20d);


        Button genMatch = new Button("Add Match");
        AnchorPane.setRightAnchor(genMatch,20d);
        AnchorPane.setBottomAnchor(genMatch,20d);

        genMatch.setOnAction(event -> {
            genarateMatch();
            update();
            clublistData.sort((SportsClub::sortPointsGoal));
            Collections.reverse(clublistData);
            clubTable.setItems(dataToTable(clublistData));
        });

        Label title = new Label("Club Details");
        title.setStyle("-fx-font: 20 arial;");
        AnchorPane.setTopAnchor(title,20d);
        AnchorPane.setLeftAnchor(title, 20d);


        Button switchtable = new Button("switch table");
        AnchorPane.setRightAnchor(switchtable,110d);
        AnchorPane.setBottomAnchor(switchtable,20d);
        switchtable.setOnAction(event -> {
            System.out.println("state = " + state);
            if (state){
                matchTableScreen(window);
                state=false;
            }else {
                clubTableScreen(window);
                state=true;
            }
            window.show();
        });
        first.getChildren().addAll(sortGoals,sortWins,sortPoints,clubTable,title,switchtable,genMatch);
        window.show();
    }
    private void matchTableScreen(Stage window){
        AnchorPane first = new AnchorPane();
        Scene addViewFirst = new Scene(first, 680, 450);
        window.setScene(addViewFirst);


        update();
        TableView<SportsClub> clubTable = matchtable();

        AnchorPane.setLeftAnchor(clubTable,20d);
        AnchorPane.setTopAnchor(clubTable,50d);
        AnchorPane.setBottomAnchor(clubTable,80d);


        Button genMatch = new Button("Add Match");
        AnchorPane.setRightAnchor(genMatch,20d);
        AnchorPane.setBottomAnchor(genMatch,20d);
        genMatch.setOnAction(event -> {
            genarateMatch();
            update();
            clubTable.setItems(dataToTable(matchlistData));

        });

        Label title = new Label("Match Details");
        title.setStyle("-fx-font: 20 arial;");
        AnchorPane.setTopAnchor(title,20d);
        AnchorPane.setLeftAnchor(title, 20d);

        Button resetButton = new Button("R");
        AnchorPane.setTopAnchor(resetButton,20d);
        AnchorPane.setRightAnchor(resetButton, 50d);
        resetButton.setOnAction(event -> {
            clubTable.setItems(dataToTable(matchlistData));
        });

        TextField dateInput = new TextField("YYYY-MM-DD");
//        dateInput.setStyle("-fx-font: 20 arial;");
        AnchorPane.setTopAnchor(dateInput,20d);
        AnchorPane.setRightAnchor(dateInput, 80d);

        Button searchButton = new Button("Q");
        AnchorPane.setTopAnchor(searchButton,20d);
        AnchorPane.setRightAnchor(searchButton, 20d);
        searchButton.setOnAction(event -> {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("enter a valid Date");
            if (dateInput.getText().trim().isEmpty()){
                a.setContentText("Feild is empty");
                a.show();
            }else
                {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
                    try {
                        LocalDate dateIn = LocalDate.parse(dateInput.getText(), dateFormat);
                        searchByDate(dateIn);
                        clubTable.setItems(dataToTable(searchByDate(dateIn)));
                    } catch (Exception ex) {
                        a.setContentText("use the YYYY-MM-DD format");
                        a.show();
                    }
                }
        });


        Button switchtable = new Button("switch table");
        AnchorPane.setRightAnchor(switchtable,110d);
        AnchorPane.setBottomAnchor(switchtable,20d);
        switchtable.setOnAction(event -> {
            System.out.println("state = " + state);
            if (state){
                matchTableScreen(window);
                state=false;
            }else {
                clubTableScreen(window);
                state=true;
            }
            window.show();
        });

        first.getChildren().addAll(clubTable,title,switchtable,genMatch,dateInput,searchButton,resetButton);
        window.show();
    }

    public int generateScore(){ return (int) (Math.random() * ((20 - 1) + 1)) + 1; }
    public int generateTeam(){ return (int) (Math.random() * (clublistData.size())); }
    public void genarateMatch() {
        PremierLeagueManager club = PremierLeagueManager.getInstance();

        if (clublistData.size()<2){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Not Enough Clubs ");
            a.setContentText("you need more 2 or more clubs to genarate teams");
            a.showAndWait();
            return;
        }

//            date
        LocalDate start = LocalDate.of(2020, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));
//            score
        int oneClubScore = generateScore();
        int twoClubScore = generateScore();
//            team
        SportsClub oneClub = clublistData.get(generateTeam());
        SportsClub twoClub = clublistData.get(generateTeam());
        while (oneClub==twoClub){
//            System.out.println("repeat");
            twoClub = clublistData.get(generateTeam());
        }

        matchlistData.add(club.addMatch(randomDate, oneClub, oneClubScore, twoClub, twoClubScore));
        Serialize load = new Serialize();
        load.serialize(clublistData,matchlistData);

        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText("New Match ");
        a.setContentText("Team A :"+oneClub.getName()+
                        "\nTeam A Score :"+oneClubScore+
                        "\nTeam B :"+twoClub.getName()+
                        "\nTeam B Score :"+twoClubScore+
                        "\nDate : "+randomDate);
        a.showAndWait();
    }
    public List<Match> searchByDate(LocalDate dateIn){
        List<Match> searchArray = new ArrayList<>();
        for (Match match :matchlistData){
            if (match.getDate().toString().equals(dateIn.toString())){
                searchArray.add(match);
            }
        }
        return searchArray;
    }
}
