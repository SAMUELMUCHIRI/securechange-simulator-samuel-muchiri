package org.example.structure;

public class AccessRequest {
    private String ticketId , subject ,priority ,workflowName ,sourceIp ,destinationIp , protocol ;
    private int port ;
    private TicketStatus status = TicketStatus.PENDING ;
    private RiskLevel riskLevel = RiskLevel.LOW ;

    private String rejectionReason = null;

    public AccessRequest(String ticketId, String subject, String priority, String workflowName,
                         String sourceIp, String destinationIp, String protocol, int port,
                         TicketStatus status, RiskLevel riskLevel , String rejectionReason)  {
        if(isValidIp(destinationIp) && isValidIp(sourceIp))
        {
            this.sourceIp = sourceIp;
            this.destinationIp = destinationIp;
        }
        else {
            throw new InvalidRequestException("Invalid Ip fields");
        }
        if(port < 1 || port > 65535)
        {
            throw new InvalidRequestException("Invalid port field");
        }
        else
        {
            this.port = port;
        }
        this.ticketId = ticketId;
        this.subject = subject;
        this.priority = validateString(priority ,"Normal","High");
        this.workflowName = workflowName;

        this.protocol = validateString(protocol,"TCP","UDP");

        this.status = status;
        this.riskLevel = riskLevel;
        this.rejectionReason = rejectionReason;
    }

    private String validateString(String target , String optionOne , String optionTwo)
    {
        if(target.equals(optionOne) || target.equals(optionTwo) )
        {
            return target;
        }
        else
        {
            throw new IllegalArgumentException("Invalid default name : "+ target + " Choose : "+optionOne + " or "+optionTwo);
        }
    }
    private static boolean isValidIp(String ip)
    {
        if(ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"))
        {
            String[] octets = ip.split("\\.");
            for(String i : octets)
            {
                int octetDig =Integer.parseInt(i) ;
                if((octetDig < 0) || (octetDig > 255))
                {
                    return false ;
                }
            }
            return true;
        }
        else
        {
            return false ;
        }

    }
    public String getSubject()
    {
        return this.subject;
    }
    public String getPriority()
    {
        return this.priority;
    }
    public String getWorkflowName()
    {
        return this.workflowName;
    }
    public String getSourceIp()
    {
        return this.sourceIp;
    }
    public String getDestinationIp()
    {
        return this.destinationIp;
    }

    public String getProtocol()
    {
        return this.protocol;
    }
    public int getPort()
    {
        return this.port;
    }



    public String getTicketId()
    {
        return this.ticketId;
    }
    public String getRejectionReason() {
        return rejectionReason;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }
    public TicketStatus getStatus() {
        return status;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "\n\tTicket : "+ this.ticketId +
                "\n\tsubject : "+ this.subject +
                "\n\tpriority  : "+ this.priority  +
                "\n\tworkflowName : "+ this.workflowName +
                "\n\tsourceIp : "+ this.sourceIp +
                "\n\tdestinationIp : "+ this.destinationIp +
                "\n\tprotocol : "+ this.protocol +
                "\n\tport : "+ this.port +
                "\n\tstatus : "+ this.status+
                "\n\triskLevel : "+ this.riskLevel +
                "\n\trejectionReason : "+ this.rejectionReason ;

    }
}
