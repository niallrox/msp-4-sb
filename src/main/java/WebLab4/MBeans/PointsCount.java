package WebLab4.MBeans;

import WebLab4.pojo.Result;
import WebLab4.services.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.management.*;

@ManagedResource
@Component
public class PointsCount extends NotificationBroadcasterSupport implements PointsCountMBean {

    @Autowired
    private ResultsService service;

    private long sequenceNumber = 1;

    @Override
    public int getPointsCount() {
        return service.getResults().size();
    }

    @Override
    public long getPointsCountInArea() {
        return service.getResults().stream()
                .filter(Result::isResult).count();
    }

    @Override
    public int showPointsCount() {
        return service.getResults().size();
    }

    @Override
    public long showPointsCountInArea() {
        return service.getResults().stream()
                .filter(Result::isResult).count();
    }

    public void pointsMultiplesFive() {
        Notification n = new Notification("MultiplesFive", this.getClass().getName(), sequenceNumber, "Количество точек кратно пяти");
        if (service.getResults().size() % 5 == 0 && service.getResults().size() != 0) {
            sequenceNumber++;
            sendNotification(n);
        }
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE};
        String name = AttributeChangeNotification.class.getName();
        String description = "Количество точек кратно пяти";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }
}
