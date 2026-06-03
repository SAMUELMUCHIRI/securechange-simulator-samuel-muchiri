package org.example;

import org.example.structure.*;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main() {
        System.out.println("\n\tValid Entries");
        AccessRequest accessRequestOne = new AccessRequest("TK001","SUBJECT001" ,"Normal","Firewall_Access_Request","127.0.4.56","127.23.45.89","TCP",3405,TicketStatus.UNDER_REVIEW,RiskLevel.LOW,"NA");
        AccessRequest accessRequestTwo = new AccessRequest("TK002","SUBJECT002" ,"High","Firewall_Access_Rule_Modification","127.89.40.56","128.23.5.89","UDP",3705,TicketStatus.UNDER_REVIEW,RiskLevel.LOW,"NA");
        System.out.println(accessRequestOne);
        System.out.println(accessRequestTwo);

        System.out.println("\n\tInvalid Entries");
        try
        {
            AccessRequest accessRequestThree = new AccessRequest("TK002","SUBJECT002" ,"High","Firewall_Access_Rule_Modification","12677.89.40.56","128.23.5.89","UDP",3705,TicketStatus.UNDER_REVIEW,RiskLevel.LOW,"NA");
        }
        catch (InvalidRequestException e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            AccessRequest accessRequestThree = new AccessRequest("TK002","SUBJECT002" ,"High","Firewall_Access_Rule_Modification","126.89.40.56","128.23.5.89","UDP",3767805,TicketStatus.UNDER_REVIEW,RiskLevel.LOW,"NA");
        }
        catch (InvalidRequestException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("\n\tSecure Api client \n");

        SecureChangeApiClient apiClient = new SecureChangeApiClient("https://google.github.io/","admin001","63ghfds43dc78s");
        apiClient.submitTicket(accessRequestOne);

        System.out.println("\n\tCreate an AuditLogger and a SecureChangeApiClient\n");
        System.out.println("\n\tCreating Api client \n");
        SecureChangeApiClient apiClientTwo = new SecureChangeApiClient("https://google.github.io/","admin002","63ghfds43dc78s");
        System.out.println("\n\tAudit Logger \n");
        AuditLogger auditLogger= new AuditLogger();

        System.out.println("\n\tAdding ALl three Steps \n");
        List<WorkflowStep> stepListOne = StepList(auditLogger , apiClientTwo , true ,"N/A");
        List<WorkflowStep> stepListTwo = StepList(auditLogger , apiClientTwo , false ,"Manager rejected , because of conflicting details");
        List<WorkflowStep> stepListThree = StepList(auditLogger , apiClientTwo , true ,"N/A");




        System.out.println("\n\tCreate 3 AccessRequest objects\n");

        AccessRequest accessRequestThree = new AccessRequest("TK003", "SUBJECT003", "Normal", "Firewall_Access_Request", "127.0.4.56", "127.23.45.89", "TCP", 8443, TicketStatus.PENDING, RiskLevel.LOW, "NA");
        AccessRequest accessRequestFour = new AccessRequest("TK004", "SUBJECT004", "Normal", "Firewall_Access_Rule_Modification", "127.89.40.56", "128.23.5.89", "UDP", 22, TicketStatus.PENDING, RiskLevel.LOW, "NA");
        AccessRequest accessRequestSix = new AccessRequest("TK006", "SUBJECT006", "Normal", "Firewall_Access_Request_Admin", "127.0.4.56", "127.23.45.89", "TCP", 3405, TicketStatus.PENDING, RiskLevel.LOW, "NA");

        try {
            AccessRequest accessRequestFive = new AccessRequest("TK005", "SUBJECT005", "Normal", "Firewall_Access_Request", "127.0.4.56", "128977.23.45.89", "TCP", 3405, TicketStatus.PENDING, RiskLevel.LOW, "NA");
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n\tTicketing System Process Start\n");
        new TicketProcessor(stepListOne , auditLogger).process(accessRequestThree);
        new TicketProcessor(stepListTwo , auditLogger).process(accessRequestFour);
        new TicketProcessor(stepListThree , auditLogger).process(accessRequestSix);


        System.out.println("\n\tLogging All Events\n");
        auditLogger.printLog();







    }

    public static List<WorkflowStep> StepList(AuditLogger auditLogger , SecureChangeApiClient apiClient, Boolean approve , String reason )
    {
        List<WorkflowStep> workflowStepList = new ArrayList<>();

        workflowStepList.add(new RiskAssessmentStep(auditLogger));
        workflowStepList.add(new ManagerApprovalStep(auditLogger , approve ,reason));
        workflowStepList.add(new ImplementationStep(auditLogger , apiClient ));
        return workflowStepList;

    }

}
