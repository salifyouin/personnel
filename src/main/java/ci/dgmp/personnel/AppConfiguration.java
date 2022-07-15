package ci.dgmp.personnel;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("appConfig") @Data
public class AppConfiguration {
    @Value("${spring.application.name}")
    private String appName;

    @Value("${appTile}")
    private String appTitle;
}
