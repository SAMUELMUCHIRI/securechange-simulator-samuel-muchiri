package org.example.structure;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public  class AuditLogger {
    private List<String> log = new ArrayList<>();

    public void record(String ticketId, String step, String handler, TicketStatus status)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
        String formattedDateTime = date.format(format);
        String entry = "["+ formattedDateTime+"] Ticket "+ ticketId +" | Step: "+step+" | Handler: "+handler+" | Status: "+status;
        log.add(entry);
    }

    public void printLog()
    {
        for(String entry: log)
        {
            System.out.println(entry);
        }
    }



}
