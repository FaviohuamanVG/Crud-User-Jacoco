
package pe.edu.vallegrande.quality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VgQualityChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(VgQualityChallengeApplication.class, args);
        System.out.println("App started..."); // mala práctica: logging con System.out
    }
}
