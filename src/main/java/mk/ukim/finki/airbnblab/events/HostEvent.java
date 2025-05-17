package mk.ukim.finki.airbnblab.events;

import mk.ukim.finki.airbnblab.model.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class HostEvent extends ApplicationEvent {
    private LocalDateTime when;

    public HostEvent(Host source){
        super(source);
        this.when=LocalDateTime.now();
    }
    public HostEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }

    public LocalDateTime getWhen() {
        return when;
    }
}
