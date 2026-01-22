package com.example.green_house.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initFirebase() throws IOException {

        if (!FirebaseApp.getApps().isEmpty()) {
            return;
        }

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(
            Objects.requireNonNull(
                classLoader.getResource("serviceAccountKey.json")
            ).getFile()
        );

        try (FileInputStream serviceAccount =
                 new FileInputStream(file.getAbsolutePath())) {

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://smart-greenhouse-system-94981-default-rtdb.firebaseio.com")
                .build();

            FirebaseApp.initializeApp(options);
        }
    }
}
