package com.edriving.restboot.inject;


public class ApplicationContextBuilder {
    public ApplicationContext build() {
        return new ApplicationContextImpl(contextName, parentContext);
    }

    public ApplicationContextBuilder withName(String name) {
        this.contextName = name;
        return this;
    }

    public ApplicationContextBuilder withDefaultName() {
        return withName(ApplicationContext.DEFAULT_NAME);
    }

    public ApplicationContextBuilder withParentContext(Context parentContext) {
        this.parentContext = parentContext;
        return this;
    }

    private String contextName;
    private Context parentContext;
}
