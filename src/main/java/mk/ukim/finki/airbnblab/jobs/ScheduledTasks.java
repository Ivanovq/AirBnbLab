package mk.ukim.finki.airbnblab.jobs;


import mk.ukim.finki.airbnblab.service.domain.AccommodationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final AccommodationService accommodationService;

    public ScheduledTasks(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Skopje")
    public void refreshMaterializedView(){
        accommodationService.refreshMaterializedView();
    }
}
