package ru.redhat.odata3;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.olingo2.Olingo2Component;
import org.apache.camel.component.olingo2.Olingo2Configuration;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.util.IntrospectionSupport;
import org.junit.Test;

public class OData3Test extends CamelTestSupport {
    private static final String TEST_SERVICE_BASE_URL = "http://services.odata.org/V3/OData/OData.svc";

    @Override
    protected CamelContext createCamelContext() throws Exception {
        final CamelContext context = super.createCamelContext();

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("serviceUri", TEST_SERVICE_BASE_URL);
        options.put("contentType", "application/json;charset=utf-8");

        final Olingo2Configuration configuration = new Olingo2Configuration();
        IntrospectionSupport.setProperties(configuration, options);

        // add OlingoComponent to Camel context
        final Olingo2Component component = new Olingo2Component(context);
        component.setConfiguration(configuration);
        context.addComponent("olingo2", component);

        return context;
    }

    @Test
    public void testOlingo2WithOdata3() throws Exception {
        template.setDefaultEndpointUri("direct:odata3");
        template.requestBody(null);
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:odata3").to("olingo2://read/Products");
            }
        };
    }
}
