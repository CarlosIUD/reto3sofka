package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.example.model.Answer;
import org.example.model.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class Database {
    private static DatabaseReference database;

    public static void init() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("C:\\Users\\Usuario\\Desktop\\juegodepreguntas\\juegodepreguntas\\src\\main\\resources\\juegodepreguntas-f0dc7-firebase-adminsdk-espjg-018e4b3319.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://juegodepreguntas-f0dc7-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);


        } catch (IOException e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // Shared Database reference
        database = FirebaseDatabase.getInstance().getReference();
    }

    public static void save(Object value, String key) {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(key);
            final CountDownLatch latch = new CountDownLatch(1);
            ref.push().setValue(value, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                    latch.countDown();
                } else {
                    System.out.println("Data saved successfully.");
                    latch.countDown();
                }
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<Question> getQuestions() {
        try {
            final Object[] response = new Object[1];
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("questions");
            final CountDownLatch latch = new CountDownLatch(1);
            ref.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot snapshot) {
                                                                    response[0] = snapshot.getValue();
                                                                    latch.countDown();
                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError error) {
                                                                    System.out.println("Error " + error.getMessage());
                                                                    latch.countDown();
                                                                }
                                                            }
            );
            latch.await();

            Map<String, HashMap> hashMap = new HashMap<>((Map) response[0]);
            List<Question> questions = hashMap.entrySet().stream().map(entry -> {
                HashMap rawQuestion = entry.getValue();
                String enunciado = (String) rawQuestion.get("enunciado");
                Long score = (Long) rawQuestion.get("score");
                Long category = (Long) rawQuestion.get("category");

                ArrayList rawAnswers = (ArrayList) rawQuestion.get("answers");

                Object collect = rawAnswers.stream().map(rawAnswer -> {
                    HashMap map = (HashMap) rawAnswer;
                    String enunciadoAnswer = (String) map.get("enunciado");
                    boolean isRightAnswer = (boolean) map.get("right");
                    return new Answer(enunciadoAnswer, isRightAnswer);
                }).collect(Collectors.toList());
                collect.toString();

                return new Question(enunciado, score.intValue(), category.intValue(), (ArrayList)collect );
            }).collect(Collectors.toList());

            return questions;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}