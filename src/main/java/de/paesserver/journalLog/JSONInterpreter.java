package de.paesserver.journalLog;

import de.paesserver.frames.logframe.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSONInterpreter {

    private final LogFrame logFrame;

    public JSONInterpreter(LogFrame logFrame){
        this.logFrame = logFrame;
    }


    public void computeJSONObject(JSONObject jsonObject){

        String event = (String) jsonObject.get("event");
        Database database = DatabaseSingleton.getInstance();

        switch (event){
            //Will be called by loading the game
            case "Location":
            case "FSDJump":
                database.insertSystem(jsonObject);
                logFrame.setSystemData(new SystemInfo((long)jsonObject.get("SystemAddress")));
                logFrame.replaceSystemTree(jsonObject.get("StarSystem").toString());
                logFrame.updateSystemTree(jsonObject.get("StarSystem").toString());
                logFrame.updateBodySignalsTable((long) jsonObject.get("SystemAddress"));
                logFrame.wipeBodyData();
                break;
            case "StartJump":
                System.out.println("StartJump");
                //if(jsonObject.get("JumpType").equals("Supercruise"))
                    //((JTextArea)componentHashMap.get("logOutput")).append("Entered Supercruise"+ "\n");
                /*
                if(jsonObject.get("JumpType").equals("Hyperspace")){
                    //((JTextArea)componentHashMap.get("logOutput")).setText("---LOG---\t\t\t\t\t\t\n");
                    ((JTree)componentHashMap.get("bodiesOutput")).removeAll();
                    ((JTextArea)componentHashMap.get("nonBodiesOutput")).setText("---SIGNALS---\t\t\t\t\t\t\n");
                    systemPane.resetSuffix();
                    systemPane.updateText();
                    //((JTextArea)componentHashMap.get("logOutput")).append("Jump has been initialised"+ "\n");
                }
                 */
                break;
            case "SupercruiseExit":
                System.out.println("SupercruiseExit");
                //((JTextArea)componentHashMap.get("logOutput")).append("Exited Supercruise"+ "\n");
                break;
            case "MaterialCollected":
                System.out.println("MaterialCollected");
                //((JTextArea)componentHashMap.get("logOutput")).append("Material collected: " + jsonObject.get("Name_Localised")  + "\n");
                //((JTextArea)componentHashMap.get("logOutput")).append("Count: " + jsonObject.get("Count")  + "\n");
                break;
            case "Bounty":
                System.out.println("Bounty");
                //{ "timestamp":"2022-03-16T16:33:27Z", "event":"Bounty", "Rewards":[ { "Faction":"HOTCOL", "Reward":271012 } ], "Target":"empire_trader", "Target_Localised":"Imperial Clipper", "TotalReward":271012, "VictimFaction":"HOTCOL" }
                //{ "timestamp":"2022-03-16T16:36:11Z", "event":"Bounty", "Rewards":[ { "Faction":"HOTCOL", "Reward":174749 }, { "Faction":"Colonia Co-operative", "Reward":59843 } ], "Target":"diamondback", "Target_Localised":"Diamondback Scout", "TotalReward":234592, "VictimFaction":"Matlehi Silver Mob" }
                //((JTextArea)componentHashMap.get("logOutput")).append("Bounty collected: " + jsonObject.get("Target_Localised")  + "\n");
                //((JTextArea)componentHashMap.get("logOutput")).append("Total Reward: " + jsonObject.get("TotalReward")  + "\n");
                break;

            //SCAN ACTIVITIES
            case "FSSDiscoveryScan":
                //((JTextArea)componentHashMap.get("logOutput")).append("Discovery-Scan has been initialised\n");
                database.addBodyCounts(jsonObject);
                logFrame.systemInfoData.setTextForBodyCounts();
                logFrame.updateSystemData();
                break;
            case "FSSAllBodiesFound":
                System.out.println("FSSAllBodiesFound");

                //TODO Implement FSSAllBodiesFound
                //((JTextArea)componentHashMap.get("logOutput")).append("All bodies discovered"+ "\n");
                break;
            case "SAAScanComplete":
                System.out.println("SAAScanComplete");
                //TODO Implement SAAScanComplete
                break;
            //{ "timestamp":"2022-02-13T23:07:21Z", "event":"SAAScanComplete", "BodyName":"Synuefe ZR-I b43-10 D 2", "SystemAddress":22671924274545, "BodyID":46, "ProbesUsed":4, "EfficiencyTarget":4 }

            case "SAASignalsFound":
                System.out.println("SAASignalsFound");

                //{ "timestamp":"2022-02-13T23:07:21Z", "event":"SAASignalsFound", "BodyName":"Synuefe ZR-I b43-10 D 2", "SystemAddress":22671924274545, "BodyID":46,
                // "Signals":[ { "Type":"$SAA_SignalType_Guardian;", "Type_Localised":"Guardian", "Count":4 },
                //  { "Type":"$SAA_SignalType_Biological;", "Type_Localised":"Biologisch", "Count":1 },
                //  { "Type":"$SAA_SignalType_Geological;", "Type_Localised":"Geologisch", "Count":3 },
                //  { "Type":"$SAA_SignalType_Human;", "Type_Localised":"Menschlich", "Count":2 } ] }

            case "FSSBodySignals":
            //{ "timestamp":"2022-04-07T15:59:02Z", "event":"FSSBodySignals", "BodyName":"Eol Prou HG-X d1-519 4 a", "BodyID":25, "SystemAddress":17841151349011,
                // "Signals":[
                //  { "Type":"$SAA_SignalType_Biological;", "Type_Localised":"Biological", "Count":2 },
                //  { "Type":"$SAA_SignalType_Geological;", "Type_Localised":"Geological", "Count":3 }
                //  ] }

                database.insertPlanetSignals(jsonObject);
                logFrame.updateBodySignalsTable((long) jsonObject.get("SystemAddress"));
                logFrame.updateBodyData();
                break;

            case "FSSSignalDiscovered":
                database.insertSystemSignal(jsonObject);
                logFrame.updateSystemSignalsTable((long) jsonObject.get("SystemAddress"));
                logFrame.updateSystemData();
                break;

            case "Scan":
                if(jsonObject.containsKey("StarType")){
                    database.insertStar(jsonObject);
                    BodyInfo bodyInfo = new BodyInfo();
                    bodyInfo.setTextforStar(jsonObject.get("BodyName").toString());
                    logFrame.setBodyData(bodyInfo);
                }
                else
                    if(((String)jsonObject.get("BodyName")).contains("Belt Cluster")){
                        database.insertBeltCluster(jsonObject);
                    }
                    else if(((String)jsonObject.get("BodyName")).contains("Ring")){
                            database.insertBodyRing(jsonObject);
                        }
                        else{
                            database.insertBody(jsonObject);
                            BodyInfo bodyInfo = new BodyInfo();
                            bodyInfo.setTextForPlanet(jsonObject.get("BodyName").toString());
                            logFrame.setBodyData(bodyInfo);
                        }

                logFrame.replaceSystemTree(jsonObject.get("StarSystem").toString());
                logFrame.updateSystemTree(jsonObject.get("StarSystem").toString());
                break;
            case "Music": break;
            case "Shutdown": break;
            case "ShipLocker": break; //TODO Inventory Tab
            //{"Components":[{"OwnerID":0,"Count":20,"Name":"graphene"},{"OwnerID":0,"Count":25,"Name":"aerogel"},{"OwnerID":0,"Count":25,"Name_Localised":"Carbon Fibre Plating","Name":"carbonfibreplating"},{"OwnerID":0,"Count":12,"Name_Localised":"Chemical Catalyst","Name":"chemicalcatalyst"},{"OwnerID":0,"Count":26,"Name_Localised":"Chemical Superbase","Name":"chemicalsuperbase"},{"OwnerID":0,"Count":25,"Name_Localised":"Circuit Board","Name":"circuitboard"},{"OwnerID":0,"Count":25,"Name_Localised":"Circuit Switch","Name":"circuitswitch"},{"OwnerID":0,"Count":25,"Name_Localised":"Electrical Fuse","Name":"electricalfuse"},{"OwnerID":0,"Count":28,"Name_Localised":"Electrical Wiring","Name":"electricalwiring"},{"OwnerID":0,"Count":29,"Name_Localised":"Encrypted Memory Chip","Name":"encryptedmemorychip"},{"OwnerID":0,"Count":26,"Name_Localised":"Epoxy Adhesive","Name":"epoxyadhesive"},{"OwnerID":0,"Count":25,"Name_Localised":"Memory Chip","Name":"memorychip"},{"OwnerID":0,"Count":25,"Name_Localised":"Metal Coil","Name":"metalcoil"},{"OwnerID":0,"Count":25,"Name_Localised":"Micro Hydraulics","Name":"microhydraulics"},{"OwnerID":0,"Count":26,"Name_Localised":"Micro Supercapacitor","Name":"microsupercapacitor"},{"OwnerID":0,"Count":25,"Name_Localised":"Micro Thrusters","Name":"microthrusters"},{"OwnerID":0,"Count":27,"Name_Localised":"Micro Transformer","Name":"microtransformer"},{"OwnerID":0,"Count":26,"Name":"motor"},{"OwnerID":0,"Count":25,"Name_Localised":"Optical Fibre","Name":"opticalfibre"},{"OwnerID":0,"Count":19,"Name_Localised":"Optical Lens","Name":"opticallens"},{"OwnerID":0,"Count":26,"Name":"scrambler"},{"OwnerID":0,"Count":26,"Name_Localised":"Titanium Plating","Name":"titaniumplating"},{"OwnerID":0,"Count":26,"Name":"transmitter"},{"OwnerID":0,"Count":20,"Name_Localised":"Tungsten Carbide","Name":"tungstencarbide"},{"OwnerID":0,"Count":25,"Name_Localised":"Viscoelastic Polymer","Name":"viscoelasticpolymer"},{"OwnerID":0,"Count":25,"Name":"rdx"},{"OwnerID":0,"Count":25,"Name":"electromagnet"},{"OwnerID":0,"Count":19,"Name_Localised":"Oxygenic Bacteria","Name":"oxygenicbacteria"},{"OwnerID":0,"Count":27,"Name":"epinephrine"},{"OwnerID":0,"Count":28,"Name_Localised":"pH Neutraliser","Name":"phneutraliser"},{"OwnerID":0,"Count":25,"Name":"microelectrode"},{"OwnerID":0,"Count":25,"Name_Localised":"Ion Battery","Name":"ionbattery"},{"OwnerID":0,"Count":21,"Name_Localised":"Weapon Component","Name":"weaponcomponent"}],"Consumables":[{"OwnerID":0,"Count":98,"Name_Localised":"Medkit","Name":"healthpack"},{"OwnerID":0,"Count":100,"Name_Localised":"Energy Cell","Name":"energycell"},{"OwnerID":0,"Count":98,"Name_Localised":"Shield Disruptor","Name":"amm_grenade_emp"},{"OwnerID":0,"Count":95,"Name_Localised":"Frag Grenade","Name":"amm_grenade_frag"},{"OwnerID":0,"Count":98,"Name_Localised":"Shield Projector","Name":"amm_grenade_shield"},{"OwnerID":0,"Count":93,"Name_Localised":"E-Breach","Name":"bypass"}],"Items":[{"MissionID":798981076,"OwnerID":0,"Count":1,"Name_Localised":"Power Regulator","Name":"largecapacitypowerregulator"},{"MissionID":837662744,"OwnerID":0,"Count":1,"Name_Localised":"Power Regulator","Name":"largecapacitypowerregulator"},{"MissionID":838681072,"OwnerID":0,"Count":1,"Name_Localised":"Power Regulator","Name":"largecapacitypowerregulator"},{"MissionID":844236447,"OwnerID":0,"Count":1,"Name_Localised":"Power Regulator","Name":"largecapacitypowerregulator"},{"MissionID":855927598,"OwnerID":0,"Count":1,"Name_Localised":"Power Regulator","Name":"largecapacitypowerregulator"},{"MissionID":858774249,"OwnerID":0,"Count":1,"Name_Localised":"Power Regulator","Name":"largecapacitypowerregulator"},{"OwnerID":0,"Count":25,"Name_Localised":"Chemical Sample","Name":"chemicalsample"},{"OwnerID":0,"Count":8,"Name_Localised":"Biochemical Agent","Name":"biochemicalagent"},{"OwnerID":0,"Count":14,"Name":"californium"},{"OwnerID":0,"Count":20,"Name_Localised":"Cast Fossil","Name":"castfossil"},{"OwnerID":0,"Count":13,"Name_Localised":"Biological Sample","Name":"geneticsample"},{"OwnerID":0,"Count":25,"Name_Localised":"G-Meds","Name":"gmeds"},{"OwnerID":0,"Count":15,"Name_Localised":"Health Monitor","Name":"healthmonitor"},{"OwnerID":0,"Count":25,"Name_Localised":"Inertia Canister","Name":"inertiacanister"},{"OwnerID":0,"Count":25,"Name":"insight"},{"OwnerID":0,"Count":25,"Name_Localised":"Insight Data Bank","Name":"insightdatabank"},{"OwnerID":0,"Count":25,"Name_Localised":"Ionised Gas","Name":"ionisedgas"},{"OwnerID":0,"Count":22,"Name_Localised":"Personal Computer","Name":"personalcomputer"},{"OwnerID":0,"Count":25,"Name_Localised":"Petrified Fossil","Name":"petrifiedfossil"},{"OwnerID":0,"Count":12,"Name_Localised":"Synthetic Genome","Name":"syntheticgenome"},{"OwnerID":0,"Count":19,"Name_Localised":"True Form Fossil","Name":"trueformfossil"},{"OwnerID":0,"Count":2,"Name_Localised":"Genetic Repair Meds","Name":"geneticrepairmeds"},{"OwnerID":0,"Count":25,"Name_Localised":"Building Schematic","Name":"buildingschematic"},{"OwnerID":0,"Count":25,"Name_Localised":"Compact Library","Name":"compactlibrary"},{"OwnerID":0,"Count":20,"Name_Localised":"Deep Mantle Sample","Name":"deepmantlesample"},{"OwnerID":0,"Count":27,"Name":"hush"},{"OwnerID":0,"Count":25,"Name":"infinity"},{"OwnerID":0,"Count":25,"Name_Localised":"Insight Entertainment Suite","Name":"insightentertainmentsuite"},{"OwnerID":0,"Count":33,"Name":"lazarus"},{"OwnerID":0,"Count":25,"Name_Localised":"Microbial Inhibitor","Name":"microbialinhibitor"},{"OwnerID":0,"Count":25,"Name_Localised":"Nutritional Concentrate","Name":"nutritionalconcentrate"},{"OwnerID":0,"Count":24,"Name_Localised":"Personal Documents","Name":"personaldocuments"},{"OwnerID":0,"Count":33,"Name":"push"},{"OwnerID":0,"Count":18,"Name_Localised":"Ship Schematic","Name":"shipschematic"},{"OwnerID":0,"Count":9,"Name_Localised":"Suit Schematic","Name":"suitschematic"},{"OwnerID":0,"Count":25,"Name_Localised":"Surveillance Equipment","Name":"surveillanceequipment"},{"OwnerID":0,"Count":7,"Name_Localised":"Synthetic Pathogen","Name":"syntheticpathogen"},{"OwnerID":0,"Count":21,"Name_Localised":"Universal Translator","Name":"universaltranslator"},{"OwnerID":0,"Count":13,"Name_Localised":"Vehicle Schematic","Name":"vehicleschematic"},{"OwnerID":0,"Count":25,"Name_Localised":"Weapon Schematic","Name":"weaponschematic"},{"OwnerID":0,"Count":1,"Name_Localised":"Pyrolytic Catalyst","Name":"pyrolyticcatalyst"},{"OwnerID":0,"Count":5,"Name_Localised":"Agricultural Process Sample","Name":"agriculturalprocesssample"},{"OwnerID":0,"Count":2,"Name_Localised":"Chemical Process Sample","Name":"chemicalprocesssample"},{"OwnerID":0,"Count":4,"Name_Localised":"Refinement Process Sample","Name":"refinementprocesssample"},{"OwnerID":0,"Count":25,"Name_Localised":"Compression-Liquefied Gas","Name":"compressionliquefiedgas"},{"OwnerID":0,"Count":25,"Name_Localised":"Degraded Power Regulator","Name":"degradedpowerregulator"},{"OwnerID":0,"Count":12,"Name_Localised":"Power Regulator","Name":"largecapacitypowerregulator"}],"Data":[{"MissionID":844206625,"OwnerID":0,"Count":1,"Name":"spyware"},{"OwnerID":0,"Count":15,"Name_Localised":"Internal Correspondence","Name":"internalcorrespondence"},{"OwnerID":0,"Count":2,"Name_Localised":"Biometric Data","Name":"biometricdata"},{"OwnerID":2248170,"Count":2,"Name_Localised":"Biometric Data","Name":"biometricdata"},{"OwnerID":2248170,"Count":3,"Name_Localised":"NOC Data","Name":"nocdata"},{"OwnerID":0,"Count":8,"Name_Localised":"NOC Data","Name":"nocdata"},{"OwnerID":0,"Count":4,"Name_Localised":"AX Combat Logs","Name":"axcombatlogs"},{"OwnerID":0,"Count":15,"Name_Localised":"Accident Logs","Name":"accidentlogs"},{"OwnerID":0,"Count":15,"Name_Localised":"Air Quality Reports","Name":"airqualityreports"},{"OwnerID":2248170,"Count":5,"Name_Localised":"Atmospheric Data","Name":"atmosphericdata"},{"OwnerID":0,"Count":15,"Name_Localised":"Atmospheric Data","Name":"atmosphericdata"},{"OwnerID":2248170,"Count":1,"Name_Localised":"Audio Logs","Name":"audiologs"},{"OwnerID":0,"Count":6,"Name_Localised":"Audio Logs","Name":"audiologs"},{"OwnerID":0,"Count":5,"Name_Localised":"Ballistics Data","Name":"ballisticsdata"},{"OwnerID":0,"Count":2,"Name_Localised":"Blacklist Data","Name":"blacklistdata"},{"OwnerID":0,"Count":7,"Name_Localised":"Blood Test Results","Name":"bloodtestresults"},{"OwnerID":0,"Count":10,"Name_Localised":"Campaign Plans","Name":"campaignplans"},{"OwnerID":0,"Count":11,"Name_Localised":"Cat Media","Name":"catmedia"},{"OwnerID":0,"Count":15,"Name_Localised":"Census Data","Name":"censusdata"},{"OwnerID":2248170,"Count":2,"Name_Localised":"Chemical Experiment Data","Name":"chemicalexperimentdata"},{"OwnerID":0,"Count":6,"Name_Localised":"Chemical Experiment Data","Name":"chemicalexperimentdata"},{"OwnerID":0,"Count":7,"Name_Localised":"Chemical Formulae","Name":"chemicalformulae"},{"OwnerID":2248170,"Count":7,"Name_Localised":"Chemical Formulae","Name":"chemicalformulae"},{"OwnerID":2248170,"Count":1,"Name_Localised":"Chemical Inventory","Name":"chemicalinventory"},{"OwnerID":0,"Count":5,"Name_Localised":"Chemical Inventory","Name":"chemicalinventory"},{"OwnerID":0,"Count":7,"Name_Localised":"Chemical Patents","Name":"chemicalpatents"},{"OwnerID":0,"Count":11,"Name_Localised":"Classic Entertainment","Name":"classicentertainment"},{"OwnerID":0,"Count":1,"Name_Localised":"Cocktail Recipes","Name":"cocktailrecipes"},{"OwnerID":0,"Count":16,"Name_Localised":"Combat Training Material","Name":"combattrainingmaterial"},{"OwnerID":0,"Count":13,"Name_Localised":"Combatant Performance","Name":"combatantperformance"},{"OwnerID":0,"Count":4,"Name_Localised":"Conflict History","Name":"conflicthistory"},{"OwnerID":0,"Count":3,"Name_Localised":"Crop Yield Analysis","Name":"cropyieldanalysis"},{"OwnerID":0,"Count":14,"Name_Localised":"Digital Designs","Name":"digitaldesigns"},{"OwnerID":0,"Count":15,"Name_Localised":"Duty Rota","Name":"dutyrota"},{"OwnerID":0,"Count":15,"Name_Localised":"Employee Directory","Name":"employeedirectory"},{"OwnerID":0,"Count":8,"Name_Localised":"Employee Expenses","Name":"employeeexpenses"},{"OwnerID":0,"Count":7,"Name_Localised":"Employee Genetic Data","Name":"employeegeneticdata"},{"OwnerID":0,"Count":7,"Name_Localised":"Employment History","Name":"employmenthistory"},{"OwnerID":0,"Count":15,"Name_Localised":"Evacuation Protocols","Name":"evacuationprotocols"},{"OwnerID":0,"Count":15,"Name_Localised":"Exploration Journals","Name":"explorationjournals"},{"OwnerID":0,"Count":11,"Name_Localised":"Extraction Yield Data","Name":"extractionyielddata"},{"OwnerID":0,"Count":6,"Name_Localised":"Faction Associates","Name":"factionassociates"},{"OwnerID":0,"Count":14,"Name_Localised":"Financial Projections","Name":"financialprojections"},{"OwnerID":0,"Count":10,"Name_Localised":"Faction News","Name":"factionnews"},{"OwnerID":0,"Count":1,"Name_Localised":"Fleet Registry","Name":"fleetregistry"},{"OwnerID":0,"Count":2,"Name_Localised":"Gene Sequencing Data","Name":"genesequencingdata"},{"OwnerID":0,"Count":4,"Name_Localised":"Genetic Research","Name":"geneticresearch"},{"OwnerID":0,"Count":7,"Name_Localised":"Hydroponic Data","Name":"hydroponicdata"},{"OwnerID":0,"Count":3,"Name_Localised":"Incident Logs","Name":"incidentlogs"},{"OwnerID":0,"Count":6,"Name_Localised":"Influence Projections","Name":"influenceprojections"},{"OwnerID":0,"Count":4,"Name_Localised":"Interrogation Recordings","Name":"interrogationrecordings"},{"OwnerID":0,"Count":3,"Name_Localised":"Interview Recordings","Name":"interviewrecordings"},{"OwnerID":0,"Count":13,"Name_Localised":"Job Applications","Name":"jobapplications"},{"OwnerID":0,"Count":6,"Name":"kompromat"},{"OwnerID":0,"Count":10,"Name_Localised":"Literary Fiction","Name":"literaryfiction"},{"OwnerID":0,"Count":15,"Name_Localised":"Maintenance Logs","Name":"maintenancelogs"},{"OwnerID":0,"Count":2,"Name_Localised":"Manufacturing Instructions","Name":"manufacturinginstructions"},{"OwnerID":0,"Count":3,"Name_Localised":"Medical Records","Name":"medicalrecords"},{"OwnerID":0,"Count":10,"Name_Localised":"Meeting Minutes","Name":"meetingminutes"},{"OwnerID":0,"Count":13,"Name_Localised":"Mineral Survey","Name":"mineralsurvey"},{"OwnerID":0,"Count":6,"Name_Localised":"Mining Analytics","Name":"mininganalytics"},{"OwnerID":0,"Count":7,"Name_Localised":"Multimedia Entertainment","Name":"multimediaentertainment"},{"OwnerID":0,"Count":15,"Name_Localised":"Network Access History","Name":"networkaccesshistory"},{"OwnerID":0,"Count":9,"Name_Localised":"Network Security Protocols","Name":"networksecurityprotocols"},{"OwnerID":0,"Count":15,"Name_Localised":"Next of Kin Records","Name":"nextofkinrecords"},{"OwnerID":0,"Count":9,"Name_Localised":"Operational Manual","Name":"operationalmanual"},{"OwnerID":0,"Count":1,"Name_Localised":"Opinion Polls","Name":"opinionpolls"},{"OwnerID":0,"Count":1,"Name_Localised":"Patient History","Name":"patienthistory"},{"OwnerID":0,"Count":5,"Name_Localised":"Patrol Routes","Name":"patrolroutes"},{"OwnerID":0,"Count":14,"Name_Localised":"Personal Logs","Name":"personallogs"},{"OwnerID":0,"Count":6,"Name_Localised":"Photo Albums","Name":"photoalbums"},{"OwnerID":0,"Count":3,"Name_Localised":"Plant Growth Charts","Name":"plantgrowthcharts"},{"OwnerID":0,"Count":5,"Name_Localised":"Political Affiliations","Name":"politicalaffiliations"},{"OwnerID":0,"Count":5,"Name_Localised":"Prisoner Logs","Name":"prisonerlogs"},{"OwnerID":0,"Count":13,"Name_Localised":"Production Reports","Name":"productionreports"},{"OwnerID":0,"Count":4,"Name_Localised":"Production Schedule","Name":"productionschedule"},{"OwnerID":2248170,"Count":4,"Name_Localised":"Production Schedule","Name":"productionschedule"},{"OwnerID":0,"Count":12,"Name":"propaganda"},{"OwnerID":0,"Count":15,"Name_Localised":"Purchase Records","Name":"purchaserecords"},{"OwnerID":0,"Count":15,"Name_Localised":"Purchase Requests","Name":"purchaserequests"},{"OwnerID":0,"Count":15,"Name_Localised":"Radioactivity Data","Name":"radioactivitydata"},{"OwnerID":0,"Count":15,"Name_Localised":"Reactor Output Review","Name":"reactoroutputreview"},{"OwnerID":0,"Count":15,"Name_Localised":"Recycling Logs","Name":"recyclinglogs"},{"OwnerID":0,"Count":15,"Name_Localised":"Residential Directory","Name":"residentialdirectory"},{"OwnerID":2248170,"Count":5,"Name_Localised":"Risk Assessments","Name":"riskassessments"},{"OwnerID":0,"Count":9,"Name_Localised":"Risk Assessments","Name":"riskassessments"},{"OwnerID":0,"Count":15,"Name_Localised":"Sales Records","Name":"salesrecords"},{"OwnerID":0,"Count":6,"Name_Localised":"Seed Geneaology","Name":"seedgeneaology"},{"OwnerID":0,"Count":3,"Name_Localised":"Settlement Assault Plans","Name":"settlementassaultplans"},{"OwnerID":2248170,"Count":5,"Name_Localised":"Settlement Assault Plans","Name":"settlementassaultplans"},{"OwnerID":0,"Count":5,"Name_Localised":"Settlement Defence Plans","Name":"settlementdefenceplans"},{"OwnerID":0,"Count":10,"Name_Localised":"Shareholder Information","Name":"shareholderinformation"},{"OwnerID":0,"Count":3,"Name_Localised":"Smear Campaign Plans","Name":"smearcampaignplans"},{"OwnerID":0,"Count":1,"Name_Localised":"Spectral Analysis Data","Name":"spectralanalysisdata"},{"OwnerID":2248170,"Count":2,"Name_Localised":"Stellar Activity Logs","Name":"stellaractivitylogs"},{"OwnerID":0,"Count":15,"Name_Localised":"Stellar Activity Logs","Name":"stellaractivitylogs"},{"OwnerID":0,"Count":8,"Name_Localised":"Surveillance Logs","Name":"surveilleancelogs"},{"OwnerID":0,"Count":6,"Name_Localised":"Tactical Plans","Name":"tacticalplans"},{"OwnerID":0,"Count":14,"Name_Localised":"Tax Records","Name":"taxrecords"},{"OwnerID":2248170,"Count":1,"Name_Localised":"Topographical Surveys","Name":"topographicalsurveys"},{"OwnerID":0,"Count":14,"Name_Localised":"Topographical Surveys","Name":"topographicalsurveys"},{"OwnerID":0,"Count":15,"Name_Localised":"Travel Permits","Name":"travelpermits"},{"OwnerID":0,"Count":15,"Name_Localised":"Union Membership","Name":"unionmembership"},{"OwnerID":0,"Count":2,"Name_Localised":"Vaccination Records","Name":"vaccinationrecords"},{"OwnerID":0,"Count":4,"Name_Localised":"Vaccine Research","Name":"vaccineresearch"},{"OwnerID":0,"Count":2,"Name_Localised":"VIP Security Detail","Name":"vipsecuritydetail"},{"OwnerID":0,"Count":1,"Name_Localised":"Virology Data","Name":"virologydata"},{"OwnerID":0,"Count":15,"Name_Localised":"Visitor Register","Name":"visitorregister"},{"OwnerID":0,"Count":10,"Name_Localised":"Weapon Inventory","Name":"weaponinventory"},{"OwnerID":2248170,"Count":2,"Name_Localised":"Weapon Test Data","Name":"weapontestdata"},{"OwnerID":0,"Count":2,"Name_Localised":"Xeno-Defence Protocols","Name":"xenodefenceprotocols"},{"OwnerID":0,"Count":14,"Name_Localised":"Payroll Information","Name":"payrollinformation"},{"OwnerID":0,"Count":4,"Name_Localised":"Geological Data","Name":"geologicaldata"},{"OwnerID":0,"Count":6,"Name_Localised":"Criminal Records","Name":"criminalrecords"},{"OwnerID":0,"Count":15,"Name_Localised":"Faction Donator List","Name":"factiondonatorlist"},{"OwnerID":0,"Count":9,"Name_Localised":"Pharmaceutical Patents","Name":"pharmaceuticalpatents"}],"event":"ShipLocker","timestamp":"2022-05-24T11:56:01Z"}
            case "ModuleInfo": break;
            case "FuelScoop": break;
            case "ScanBaryCentre": break;
            //{"SystemAddress":282821774659,"SemiMajorAxis":3.8293766379356387E12,"OrbitalPeriod":3.230340600013733E10,"MeanAnomaly":85.715481,"Eccentricity":0.019934,"BodyID":1,"AscendingNode":-24.850609,"StarSystem":"Flyai Eaescs BG-N d7-8","Periapsis":299.308622,"event":"ScanBaryCentre","OrbitalInclination":7.768945,"timestamp":"2022-05-24T11:54:02Z"}
            case "ReceiveText": break;
            case "Powerplay": break;
            default:
                System.out.println("Not found: " + event);
                System.out.println(jsonObject.toJSONString());
        }
    }

    public static JSONObject extractJSONObjectFromString(String line){
        try {
            JSONParser parser = new JSONParser();
            return  (JSONObject) parser.parse(line);
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
