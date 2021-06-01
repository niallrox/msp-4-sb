package WebLab4;

import WebLab4.MBean.Testing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.*;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class WebLab4Application {

	public static void main(String[] args) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
		SpringApplication.run(WebLab4Application.class, args);
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("ru.urvanov.javaexamples.mbeans:type=MBean");
		Testing mbean = new Testing();
		mbs.registerMBean(mbean, name);
	}
}