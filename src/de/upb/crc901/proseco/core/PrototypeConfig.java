package de.upb.crc901.proseco.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Mutable;

/**
 * Unified class for configuration values
 * 
 * @author kadirayk, fmohr
 *
 */
public interface PrototypeConfig extends Mutable {

	/* Interview */
	public static final String INTERVIEW = "pbc.interview_path";
	public static final String INTERVIEW_RESOURCES = "pbc.interview_resources_path";

	/* search */
	public static final String STRATEGIES = "pbc.strategies_path";
	public static final String STRATEGY_RUNNABLE = "pbc.strategy.runnable";
	
	/* pre-grounding filter */
	public static final String PRE_GROUNDING_HOOK = "pbc.hook.preground";
	
	/* grounding */
	public static final String GROUNDING_FOLDER = "proseco.grounding.folder";
	public static final String GROUNDING_EXEC = "proseco.grounding.executable";
	
	/* deployment */
	public static final String DEPLOYMENT_EXEC = "proseco.deployment.executable";
	public static final String DEPLOYMENT_HOST = "proseco.deployment.host";
	public static final String DEPLOYMENT_PORT_MIN = "proseco.deployment.minport";
	public static final String DEPLOYMENT_PORT_MAX = "proseco.deployment.maxport";
	public static final String DEPLOYMENT_ENTRYPOINT = "proseco.deployment.entrypoint";
	
	/* benchmarking */
	public static final String BENCHMARK_SERVICE = "benchmarkService.bat";
	public static final String BENCHMARK_PATH = "pbc.benchmarks_path";
	public static final String INTERNAL_BENCHMARK_FOLDER = "benchmarks/";
	
	public static final String EXEC_FINAL_TEST = "src/test.bat";

	@Key(BENCHMARK_PATH)
	@DefaultValue("5")
	public String getBenchmarkPath();

	@Key(STRATEGIES)
	@DefaultValue("strategies")
	public String getNameOfStrategyFolder();

	@Key(GROUNDING_FOLDER)
	@DefaultValue(".")
	public String getNameOfGroundingFolder();

	@Key(GROUNDING_EXEC)
	@DefaultValue("grounding.sh")
	public String getGroundingCommand();
	
	@Key(DEPLOYMENT_EXEC)
	@DefaultValue("deployment.sh")
	public String getDeploymentCommand();
	
	@Key(DEPLOYMENT_HOST)
	@DefaultValue("localhost")
	public String getDeploymentHost();
	
	@Key(DEPLOYMENT_PORT_MIN)
	@DefaultValue("8100")
	public int getDeploymentMinPort();
	
	@Key(DEPLOYMENT_PORT_MAX)
	@DefaultValue("8200")
	public int getDeploymentMaxPort();
	
	@Key(DEPLOYMENT_ENTRYPOINT)
	@DefaultValue("")
	public String getDeploymentEntryPoint();

	@Key(INTERVIEW)
	@DefaultValue("interview")
	public String getNameOfInterviewFolder();

	@Key(INTERVIEW_RESOURCES)
	@DefaultValue("res")
	public String getNameOfInterviewResourceFolder();

	@Key(STRATEGY_RUNNABLE)
	@DefaultValue("run.sh")
	public String getSearchRunnable();

	public static PrototypeConfig get(PROSECOConfig prosecoConfig, String prototypeName) {
		return get(new File(prosecoConfig.getPathToPrototypes() + File.separator + prototypeName + File.separator + "prototype.conf"));
	}
	
	public static PrototypeConfig get(String file) {
		return get(new File(file));
	}

	public static PrototypeConfig get(File file) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.err.println("Could not find config file " + file + ". Assuming default configuration");
		} catch (IOException e) {
			System.err.println("Encountered problem with config file " + file + ". Assuming default configuration. Problem:" + e.getMessage());
		}

		return ConfigFactory.create(PrototypeConfig.class, props);
	}
	
	@Key(PRE_GROUNDING_HOOK)
	@DefaultValue("analysis.sh")
	public File getHookForPreGrounding();
}