package WebLab4.MBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.management.*;
import java.lang.management.ManagementFactory;

@Component
public class PointsCountMBeanService {
    private MBeanServer mbs;
    @Autowired
    private PointsCountMBean mbean;
    private ObjectName name;

    @PostConstruct
    public void init() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        mbs = ManagementFactory.getPlatformMBeanServer();
        name = new ObjectName("MBean:name=points");
        mbs.registerMBean(mbean, name);
    }
}
