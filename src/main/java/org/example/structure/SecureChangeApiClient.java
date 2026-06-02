package org.example.structure;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SecureChangeApiClient {
    private String baseUrl , username , password ;
    public SecureChangeApiClient(String baseUrl , String username , String password)
    {
        this.baseUrl = baseUrl ;
        this.password = password;
        this.username = username ;
    }

    private String  buildPayload(AccessRequest request)
    {

        JsonObject ticket = new JsonObject();

        ticket.addProperty("subject", request.getSubject());
        ticket.addProperty("priority", request.getPriority());

        JsonObject workflow = new JsonObject();
        workflow.addProperty("name", request.getWorkflowName());
        ticket.add("workflow", workflow);

        JsonObject accessRequest = new JsonObject();
        accessRequest.addProperty("action", "Allow");

        // Sources
        JsonObject source = new JsonObject();
        source.addProperty("ip_address", request.getSourceIp());

        JsonArray sourcesArray = new JsonArray();
        sourcesArray.add(source);

        JsonObject sources = new JsonObject();
        sources.add("source", sourcesArray);

        accessRequest.add("sources", sources);

        // Destinations
        JsonObject destination = new JsonObject();
        destination.addProperty("ip_address", request.getDestinationIp());

        JsonArray destinationsArray = new JsonArray();
        destinationsArray.add(destination);

        JsonObject destinations = new JsonObject();
        destinations.add("destination", destinationsArray);

        accessRequest.add("destinations", destinations);

        // Services
        JsonObject service = new JsonObject();
        service.addProperty("port", request.getPort());
        service.addProperty("protocol", request.getProtocol());

        JsonArray servicesArray = new JsonArray();
        servicesArray.add(service);

        JsonObject services = new JsonObject();
        services.add("service", servicesArray);

        accessRequest.add("services", services);

        JsonObject field = new JsonObject();
        field.add("access_request", accessRequest);

        JsonObject fields = new JsonObject();
        fields.add("field", field);

        JsonObject task = new JsonObject();
        task.add("fields", fields);

        JsonObject tasks = new JsonObject();
        tasks.add("task", task);

        JsonObject step = new JsonObject();
        step.add("tasks", tasks);

        JsonArray stepsArray = new JsonArray();
        stepsArray.add(step);

        JsonObject steps = new JsonObject();
        steps.add("step", stepsArray);

        ticket.add("steps", steps);

        JsonObject root = new JsonObject();
        root.add("ticket", ticket);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        return gson.toJson(root);

    }

    public  void submitTicket(AccessRequest request)
    {
        String jsonPayload = buildPayload(request);
        System.out.println("[API CLIENT] Would POST to: "+this.baseUrl+"/securechangeworkflow/api/securechange/tickets");
        System.out.println("[API CLIENT] Authorization: Basic <REDACTED>");
        System.out.println("[API CLIENT] Payload: "+jsonPayload);
        System.out.println("[API CLIENT] Mock response: 201 Created — Ticket submitted successfully");

    }
}
