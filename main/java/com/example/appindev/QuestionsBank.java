package com.example.appindev;

import java.io.*;
import java.util.ArrayList;

public class QuestionsBank implements Serializable{

    private ArrayList<Question> questions;


    public QuestionsBank(){
        this.questions = new ArrayList();
        this.premadeQuestions();
      //  this.saveQuestions();

    }

    public void premadeQuestions(){
        this.questions.add(new Question("Pierwszy lek w leczeniu miażdzycy", "statyna" , "fibrat", "jakieś gówno", "cebula"));
        this.questions.add(new Question("Pierwszy lek w leczeniu elegible", "prawidłowa odp" , "zgd", "fg gówno", "cebula"));
        //this.questions.add(new Question("kolejne pytanie", "prawidłowa odp" , "zla", "fg fsd", "sdf", "file:rtgmeniscus.png"));
        this.questions.add(new Question("kolejne pytanie", "prawidłowa odp" , "zla", "fg fsd", "sdf"));
    }

   /* public void saveQuestions(){

        File storage = new File("C:\\Users\\shand\\IdeaProjects\\myworkin\\questions.txt");
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try{
            fout = new FileOutputStream("C:\\Users\\shand\\IdeaProjects\\myworkin\\questions.txt");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this.questions);
            oos.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println("Error initializing stream");
            e.printStackTrace();

        }

    } */

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }

}
