package lbp.toolsfordev.blckchn.common.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * ShutdownManager
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@Service
public class ShutdownManager implements IShutdownManager {

	private final ApplicationContext appContext;

	@Autowired
	public ShutdownManager(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	public void initiateShutdown(int returnCode){
		SpringApplication.exit(appContext, () -> returnCode);
	}
}
