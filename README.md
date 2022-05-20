# EDLogParser
Log parser tool for the game Elite Dangerous.
It collects information through logs and displays these on a graphical user interface.

Beside the usual planet and system information it will also show:
* Planet with the most biological, geological, human, xeno signals within the system. So you don't have to remember which planet it was.
* A nice tree with all the bodies (and in the future also signals)

![image](https://user-images.githubusercontent.com/60095837/169622112-1eb9ae64-bd71-4110-b68b-9b47e49f4953.png)

# IN DEVELOPMENT

## Release will be announced

## Planned Features:
* -[ ] __LogParser__
  * Tool to read the logs and give the user information
  * Information about last explored object
  * Signals in the system
  * General System info
* -[ ] __NavRoute Market Analyzer__
  * Tool to read current market stats and market stats on the route to give the user best buy/sell opportunities.
* -[ ] __Material List__
    * List of materials the user can set, which he needs for upgrades. List may update automatically.

### For feature request please use the issues tab

## Installation

Go into your <br>
`drive_c/users/steamuser/Saved Games/Frontier Developments/Elite Dangerous`<br>
and put the EDLogPrser-X.X.jar into the folder as well as the org.edassets and de.paesserver folder.

Location of journal logs:
%USER%/Saved Games/Frontier Developments/Elite Dangerous/Journal.DATE.PART.log
Log names build like the following: netLog.DATE.log where DATE is parsed like YYDDMM\*. * is unkown time.
PART is the part of the journal (had only 01 in my case).

## Thanks to
https://edassets.org [[GitHub]](https://github.com/SpyTec/EDAssets "GitHub to EDAssets") for the many assets they provide.

<hr>

## Known Issues and todos:
* At first start, the program can be slow. Be patient, it is because the database is updating in the background and access times to that is then slower.
* Planets in the System Tree are unsorted right now. This is already under scope.
* Est. earnings missing for stars. Please help me out to find a list for the earnings of all stars.
* Missing *good* assets for asteroid belts
* There are a bunch of null pointers internally. I don't know where they come from. They scare me. Please send help.

## Current Scope
* Finishing the log parser tab
