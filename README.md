# EDLogParser
Reads the log and gives the commander additional information

#IN DEVELOPMENT

##Release will be announced

##Planned Features:
###LogParser
Tool to read the logs and give the user information

###NavRoute Market Analyzer
Tool to read current market stats and market stats on the route to give the user best buy/sell opportunities.

###Shopping List
List of materials the user can set, which he needs for upgrades. List may update automatically.

##For feature request please use the issues tab

Location of journal logs:
%USER%/Saved Games/Frontier Developments/Elite Dangerous/Journal.DATE.PART.log
Log names build like the following: netLog.DATE.log where DATE is parsed like YYDDMM\*. * is unkown time.
PART is the part of the journal (had only 01 in my case).

What it need to be displayed:
{ "timestamp":"2022-02-21T17:52:41Z", "event":"Location", "Docked":false, "Taxi":false, "Multicrew":false, "StarSystem":"Plaa Eurk WM-W c1-9", "SystemAddress":2554331140626, "StarPos":[-2033.40625,-15.59375,-970.40625], "SystemAllegiance":"", "SystemEconomy":"$economy_None;", "SystemEconomy_Localised":"n/v", "SystemSecondEconomy":"$economy_None;", "SystemSecondEconomy_Localised":"n/v", "SystemGovernment":"$government_None;", "SystemGovernment_Localised":"n/v", "SystemSecurity":"$GAlAXY_MAP_INFO_state_anarchy;", "SystemSecurity_Localised":"Anarchie", "Population":0, "Body":"Plaa Eurk WM-W c1-9 A", "BodyID":1, "BodyType":"Star" }<br />

-> To be displayed (if available): <br />
StarSystem,SystemAllegiance,SystemEconomy,SystemEconomy_Localised,SystemSecondEconomy,SystemSecondEconomy_Localised,SystemGovernment,SystemGovernment_Localised,SystemSecurity,SystemSecurity_Localised,Population,Body,BodyID,BodyType
<hr>

{ "timestamp":"2022-02-21T18:00:37Z", "event":"StartJump", "JumpType":"Hyperspace", "StarSystem":"Plaa Eurk KD-V b2-0", "SystemAddress":643305710617, "StarClass":"M" }

-> Display, that a jump to the next starSystem has been initialised
-> To be displayed: StarSystem,StarClass
<hr>

{ "timestamp":"2022-02-21T17:58:49Z", "event":"FSDJump", "Taxi":false, "Multicrew":false, "StarSystem":"Plaa Eurk SG-Y c1", "SystemAddress":355307885066, "StarPos":[-2043.34375,4.78125,-992.31250], "SystemAllegiance":"", "SystemEconomy":"$economy_None;", "SystemEconomy_Localised":"n/v", "SystemSecondEconomy":"$economy_None;", "SystemSecondEconomy_Localised":"n/v", "SystemGovernment":"$government_None;", "SystemGovernment_Localised":"n/v", "SystemSecurity":"$GAlAXY_MAP_INFO_state_anarchy;", "SystemSecurity_Localised":"Anarchie", "Population":0, "Body":"Plaa Eurk SG-Y c1", "BodyID":0, "BodyType":"Star", "JumpDist":31.524, "FuelUsed":12.166890, "FuelLevel":51.833111 }<br >

-> Display, that a jump to the next starSystem has been initialised
-> To be displayed (if available): StarSystem,SystemAllegiance,SystemEconomy,SystemEconomy_Localised,SystemSecondEconomy,SystemGovernment,SystemGovernment_Localised,SystemSecurity_Localised,Population
<hr>

{ "timestamp":"2022-02-21T18:01:03Z", "event":"Scan", "ScanType":"Detailed", "BodyName":"Plaa Eurk KD-V b2-0 B", "BodyID":2, "Parents":[ {"Null":0} ], "StarSystem":"Plaa Eurk KD-V b2-0", "SystemAddress":643305710617, "DistanceFromArrivalLS":261629.632857, "StarType":"L", "Subclass":0, "StellarMass":0.113281, "Radius":190748832.000000, "AbsoluteMagnitude":14.033813, "Age_MY":218, "SurfaceTemperature":1326.000000, "Luminosity":"Vz", "SemiMajorAxis":61012815237045.289063, "Eccentricity":0.089704, "OrbitalInclination":-93.796768, "Periapsis":247.740756, "OrbitalPeriod":556887400150.299072, "AscendingNode":-14.930951, "MeanAnomaly":75.236472, "RotationPeriod":100347.164436, "AxialTilt":0.000000, "WasDiscovered":true, "WasMapped":false }

-> To be displayed (always): BodyName,WasDiscovered,WasMapped,DistanceFromArrivalLS <br>
If it is a star display: StarType

If it is a Body display: <br>
TidalLock,TerraformState,PlanetClass,Atmosphere,AtmosphereType,Volcanism,MassEM,SurfaceGravity,SurfaceTemperature,Landable<br>
Materials (built as followed: "Materials":[ { "Name":"iron", "Name_Localised":"Eisen", "Percent":22.797493 }, { "Name":"nickel", "Percent":17.243069 }, { "Name":"sulphur", "Name_Localised":"Schwefel", "Percent":16.189022 }, { "Name":"carbon", "Name_Localised":"Kohlenstoff", "Percent":13.613291 }, { "Name":"manganese", "Name_Localised":"Mangan", "Percent":9.415132 }, { "Name":"phosphorus", "Name_Localised":"Phosphor", "Percent":8.715462 }, { "Name":"vanadium", "Percent":5.598270 }, { "Name":"zirconium", "Percent":2.647259 }, { "Name":"niobium", "Name_Localised":"Niob", "Percent":1.558087 }, { "Name":"tellurium", "Name_Localised":"Tellur", "Percent":1.227377 }, { "Name":"mercury", "Name_Localised":"Quecksilber", "Percent":0.995529 } ])<br>
Composition (built as followed: "Composition":{ "Ice":0.000000, "Rock":0.668933, "Metal":0.331067 })

<hr>
{ "timestamp":"2022-02-21T18:02:54Z", "event":"FSSDiscoveryScan", "Progress":0.619064, "BodyCount":21, "NonBodyCount":4, "SystemName":"Plaa Eurk ZQ-Y b1", "SystemAddress":2841523659785 }

-> Display, that Discovery-Scan has been initialised
-> To be displayed: BodyCount,NonBodyCount
<hr>

{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$Warzone_PointRace_Low:#index=1;", "SignalName_Localised":"Kampfbereich [Geringe Intensität]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$MULTIPLAYER_SCENARIO79_TITLE;", "SignalName_Localised":"Rohstoffabbau-Standort [Gefährlich]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$MULTIPLAYER_SCENARIO78_TITLE;", "SignalName_Localised":"Rohstoffabbau-Standort [Hoch]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$Warzone_PointRace_Low:#index=1;", "SignalName_Localised":"Kampfbereich [Geringe Intensität]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"Deepfrost Port" }
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$Warzone_PointRace_Med:#index=1;", "SignalName_Localised":"Kampfbereich [Mittlere Intensität]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$Warzone_PointRace_Med:#index=1;", "SignalName_Localised":"Kampfbereich [Mittlere Intensität]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"Chretien Terminal", "IsStation":true }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"=DAIMYO= W1T-01Z", "IsStation":true }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"McArthur Dock" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"TIO-750 Aquarius-class Tanker" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$MULTIPLAYER_SCENARIO14_TITLE;", "SignalName_Localised":"Rohstoff-Abbau-Standort" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$Warzone_PointRace_High:#index=1;", "SignalName_Localised":"Kampfbereich [Hohe Intensität]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$Warzone_PointRace_Low:#index=1;", "SignalName_Localised":"Kampfbereich [Geringe Intensität]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"Piaget Orbital" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$Warzone_PointRace_High:#index=1;", "SignalName_Localised":"Kampfbereich [Hohe Intensität]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$Warzone_PointRace_Med:#index=1;", "SignalName_Localised":"Kampfbereich [Mittlere Intensität]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"Planck Heights" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"Yeats Remembrance Theatre" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$MULTIPLAYER_SCENARIO78_TITLE;", "SignalName_Localised":"Rohstoffabbau-Standort [Hoch]" }<br>
{ "timestamp":"2022-02-02T14:59:39Z", "event":"FSSSignalDiscovered", "SystemAddress":13865093899689, "SignalName":"$MULTIPLAYER_SCENARIO42_TITLE;", "SignalName_Localised":"Navigationsboje" }<br>

-> Display, that  signal source has been detected
-> To be displayed: SignalName,SignalName_Localised(If available)
<hr>

{ "timestamp":"2022-03-06T13:16:30Z", "event":"FSSAllBodiesFound", "SystemName":"Boekh QT-H b40-16", "SystemAddress":35715469360985, "Count":11 }

-> Notifying that the starSystem has been fully scanned
