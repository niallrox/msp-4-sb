package WebLab4.MBean;

import WebLab4.services.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Testing implements MBean {

    @Autowired
    private ResultsService service;

    @Override
    public int getPointsCount() {
        return service.getResults().size();
    }
}
