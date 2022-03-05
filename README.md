# EDLogParser
Reads the log and gives the commander additional information

Location of net logs: 
Elite Dangerous/Products/elite-dangerous-odyssey-64/Logs/netLog.*.log<br />
Log names build like the following: netLog.ID.log where ID goes higher, the newer it is.

What it need to be displayed:
{19:05:33GMT 4466.207s} System:"Outordy ZD-Q c19-0" StarPos:(-2648.531,251.656,-1488.375)ly  Supercruise<br />
-> Display System, which just has been entered

{19:05:45GMT 4478.021s} UploadJournal: upload 4 lines of journal (20017)<br />
-> Display how many new lines in the journal has been added

{19:13:24GMT 4936.972s} [Wing] Local player has frame-shifted (wake HyperspaceWake_0x000009114feb3f9d)...<br />
-> Display that Frame-Shift-Drive has been activated
