package web.module;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Binder;
import com.google.inject.Provides;
import com.hubspot.dropwizard.guicier.DropwizardAwareModule;
import web.configuration.GCConfiguration;
import web.resource.DataController;
import web.resource.LookupController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class GCServiceModule extends DropwizardAwareModule<GCConfiguration> {


    @Override
    public void configure(Binder binder) {
        //REST
        binder.bind(LookupController.class);
        binder.bind(DataController.class);
    }

    @Provides
    protected ObjectMapper getObjectMapper() {
        return getEnvironment().getObjectMapper();
    }

    @Provides
    protected MetricRegistry provideMetricRegistry() {
        return getMetricRegistry();
    }

    //for unit tests
    protected MetricRegistry getMetricRegistry() {
        return getEnvironment().metrics();
    }

    @Provides
    Client provideClient() {
        return ClientBuilder.newClient();
    }

}
