package mk.ukim.finki.airbnblab.listeners;


import mk.ukim.finki.airbnblab.events.HostEvent;
import mk.ukim.finki.airbnblab.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostEvent(HostEvent event){
        hostService.refreshMaterializedView();
    }
}
