package WebLab4.MBeans;

import WebLab4.services.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;


@ManagedResource
@Component
public class Time implements TimeMBean {

    @Autowired
    private ResultsService service;

    public long startTime = 0;
    public long endTime = 0;

    @Override
    public String getTime() {
        return (System.currentTimeMillis() - startTime) / (service.getResults().size()) + " ms";
    }

    @Override
    public String showTime() {
        endTime = System.currentTimeMillis();
        return (endTime - startTime) / (service.getResults().size()) + " ms";
    }
}
