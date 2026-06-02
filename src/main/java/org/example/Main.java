package org.example;

import org.example.structure.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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







    }
}
