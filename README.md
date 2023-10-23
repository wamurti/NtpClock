# NtpClock

Enkel klocka som visar systemtid i röd text om man tappar internet, annars NTP-tid i grön text.
Använder NTP-server: time.google.com och kan ändras på rad 249 i /app/src/main/java/com/example/ntpklocka/SNTPClient.java
(sntpClient.requestTime("time.google.com", 5000))

![SystemTid](/SystemTid.png)
![NtpTid](/NtpTid.png)


