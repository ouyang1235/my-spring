package cn.bugstack.springframework.context.support;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocations) {
        this(new String[]{configLocations});
    }


    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
