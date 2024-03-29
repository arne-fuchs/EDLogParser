# EDLogParser
Log parser tool for the game Elite Dangerous.
It collects information through logs and displays these on a graphical user interface.

Beside the usual planet and system information it will also show:
* Planet with the most biological, geological, human, xeno signals within the system. So you don't have to remember which planet it was.
* A nice tree with all the bodies (and in the future also signals)

![image](https://user-images.githubusercontent.com/60095837/169622112-1eb9ae64-bd71-4110-b68b-9b47e49f4953.png)
![image](https://user-images.githubusercontent.com/60095837/169639612-f9ea07e4-ba4e-4c1b-93f8-91a46d33cf04.png)

# IN DEVELOPMENT

## Project will be moved to a rust version

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

## Requirements 

Java 11 or higher

## Location of journal logs

Linux: /home/user/.steam/steam/steamapps/compatdata/359320/pfx/drive_c/users/steamuser/Saved Games/Frontier Developments/Elite Dangerous
Windows : USER/Saved Games/Frontier Developments/Elite Dangerous

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
* Finishing touches to the log parser tab
