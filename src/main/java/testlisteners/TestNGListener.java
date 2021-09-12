package testlisteners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    private static final Logger LOG = Logger.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info(result.getMethod().getMethodName() + " Started");
        LOG.info(result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info(result.getMethod().getMethodName() + " Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.info("Failed because of - "+ result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOG.info("Skipped because of - "+ result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LOG.info("Test failed but it is in defined success ratio "+ result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {
        LOG.info("=========== onStart :-" + context.getName() + "===============");
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.info("=========== onFinish :-" + context.getName() + "===============");
    }
}
