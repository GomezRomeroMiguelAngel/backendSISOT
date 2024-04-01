package geo.dgtid.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.context.event.ApplicationReadyEvent;
// import org.springframework.context.event.EventListener;

// import geo.dgtid.backend.Services.EnvioEmailService;

@SpringBootApplication
public class BackendApplication {

	// public final EnvioEmailService emailService;
    // private static String body="Hi this is Durjoy Acharya a lazy programmer and open-source contributor";
    // public BackendApplication(EnvioEmailService emailService) {
    //     this.emailService = emailService;
    // }

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	// @EventListener(ApplicationReadyEvent.class)
    // public void sendMail(){
    //     emailService.sendEmail("19161282@itoaxaca.edu.mx",null,body);
    // }

}
