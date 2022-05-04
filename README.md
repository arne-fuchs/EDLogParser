# EDLogParser
Log parser tool for the game Elite Dangerous.
It collects information through logs and displays these on a graphical user interface.

Beside the usual planet and system information it will also show:
* Planet with the most biological, geological, human, xeno signals within the system. So you don't have to remember which planet it was.
* A nice tree with all the bodies (and in the future also signals)

![image](https://user-images.githubusercontent.com/60095837/166445798-620072af-9cdc-492d-9f5c-f5f4fa5fc300.png)
(On some devices the right panel's formation gets a bit messed up. It will be fixed)

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
and put the EDLogPrser-X.X.jar into the folder as well as the org.edassets folder.

Location of journal logs:
%USER%/Saved Games/Frontier Developments/Elite Dangerous/Journal.DATE.PART.log
Log names build like the following: netLog.DATE.log where DATE is parsed like YYDDMM\*. * is unkown time.
PART is the part of the journal (had only 01 in my case).

## Thanks to
https://edassets.org [[GitHub]](https://github.com/SpyTec/EDAssets "GitHub to EDAssets") for the many assets they provide.

<hr>

## Known Issues:
* [FIXED IN UPCOMING VERSION] "Reset Reader" button doesn't work anymore since the database has been added
* [*SHOULD* BE FIXED IN UPCOMING VERSION]The planet's information panel formation can mess up on some devices
* About and help tabs are not implemented yet
* Clicking on a planet in the tree doesn't show information about it... yet

## Current Scope
* Rebuilding datastructure to be based on SQL to make the project more organized and easier to maintain
