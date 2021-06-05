package WebLab4.MBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.management.*;
import java.lang.management.ManagementFactory;

@Component
public class TimeMBeanService {
    private MBeanServer mbs;
    @Autowired
    private TimeMBean mbean;
    private ObjectName name;

    @PostConstruct
    public void init() throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
        mbs = ManagementFactory.getPlatformMBeanServer();
        name = new ObjectName("MBean:name=time");
        mbs.registerMBean(mbean, name);
    }
}
