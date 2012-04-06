package org.kuali.student.core.test;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;
import org.kuali.rice.core.web.jetty.JettyServer;
import org.kuali.rice.test.TransactionalLifecycle;
import org.mortbay.jetty.webapp.WebAppClassLoader;

/**
 * A base test class which adds the following capabilities to the test harness:
 * 
 * <li>
 *   <ol>Automatically starts an embedded Jetty jettyServer which loads the application.</ol>
 *   <ol>Starts a transaction before the test begins and rolls the transaction back after 
 *       the test completes so that nothing is written to the database.</ol>
 * </li>
 * 
 * @author Eric Westfall
 */
public class BaseCase extends Assert {

	private static final int DEFAULT_PORT = 8090;
	private static final String CONTEXT_NAME = "/ks-test-dev";
	private static final String WEBAPP_ROOT = "/src/main/webapp";
	
	private static JettyServer jettyServer;

	private TransactionalLifecycle transactionalLifecycle;
	
	@Before
	public void setUp() throws Exception {
		transactionalLifecycle = new TransactionalLifecycle();
		transactionalLifecycle.start();
	}
	
	@After
	public void tearDown() throws Exception {
		try {
			if (transactionalLifecycle != null) {
				transactionalLifecycle.stop();
			}
		} finally {
			transactionalLifecycle = null;
		}
	}
	
	@BeforeClass
	public static void startJettyServer() throws Exception {
	    System.setProperty("kew.bootstrap.spring.file", "SpringBeans.xml");
	    
		BaseCase.jettyServer = new JettyServer(DEFAULT_PORT, CONTEXT_NAME, WEBAPP_ROOT);
		jettyServer.setFailOnContextFailure(true);
		jettyServer.setTestMode(true);
		jettyServer.start();
		// establish the GlobalResourceLoader and ConfigContext for the classloader of the test harness
		addWebappsToContext();
	}
	
    /**
     * Adds all ResourceLoaders registered to WebAppClassLoaders to the GlobalResourceLoader.
     * Overrides the current context config with the Config registered to the (last) WebAppClassLoader
     */
    public static void addWebappsToContext() {
        // TODO: RICE-2.0 UPGRADE had to jump through hoops with M8 because getConfig was made private.
        // TODO: Uncomment the following and use that instead of iterating
	    // ClassLoader webappClassLoader = jettyServer.getContext().getClassLoader();
	    // Config webappConfig = ConfigContext.getConfig(webappClassLoader);

        for (Map.Entry<ClassLoader, Config> configEntry : ConfigContext.getConfigs()) {
            if (configEntry.getKey() instanceof WebAppClassLoader) {
                ResourceLoader rl = GlobalResourceLoader.getResourceLoader(configEntry.getKey());
                if (rl == null) {
                    Assert.fail("didn't find resource loader for workflow test harness web app");
                }
                ConfigContext.overrideConfig(Thread.currentThread().getContextClassLoader(), configEntry.getValue());
                GlobalResourceLoader.addResourceLoader(rl);
            }
        }
    }
	
	@AfterClass
	public static void stopJettyServer() throws Exception {
		try {
			if (jettyServer != null) {
				jettyServer.stop();
			}
		} finally {
			jettyServer = null;
		}
	}
	
}
