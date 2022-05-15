package de.paesserver.structure.body;

public enum PlanetClass {
    AmmoniaWorld,EarthlikeWorld,WaterWorld,
    HighMetalContentPlanet,IcyBody,MetalRichBody,RockyBody,RockyIceBody,
    ClassIGasGiant,ClassIIGasGiant,ClassIIIGasGiant,ClassIVGasGiant,ClassVGasGiant,
    GasGiantwithAmmoniabasedLife,GasGiantwithWaterbasedLife,HeliumRichGasGiant,WaterGiant;

    public static String getString(PlanetClass planetClass){
        switch (planetClass){
            case AmmoniaWorld: return "Ammonia World";
            case EarthlikeWorld: return "Earth-like World";
            case WaterWorld: return "Water World";
            case HighMetalContentPlanet: return "High Metal Content Planet";
            case IcyBody: return "Icy Body";
            case MetalRichBody: return "Metal Rich Body";
            case RockyBody: return "Rocky Body";
            case RockyIceBody: return "Rocky Ice Body";
            case ClassIGasGiant: return "Class I Gas Giant";
            case ClassIIGasGiant: return "Class II Gas Giant";
            case ClassIIIGasGiant: return "Class III Gas Giant";
            case ClassIVGasGiant: return "Class IV Gas Giant";
            case ClassVGasGiant: return "Class V Gas Giant";
            case GasGiantwithAmmoniabasedLife: return "Gas Giant with Ammonia-based Life";
            case GasGiantwithWaterbasedLife: return "Gas Giant with Water-based Life";
            case HeliumRichGasGiant: return "Helium-Rich Gas Giant";
            case WaterGiant: return "Water Giant";
        }
        return null;
    }
    public static PlanetClass getBodyClass(String string){
        switch (string){

            case "Ammonia world": return AmmoniaWorld;
            case "Earthlike body": return EarthlikeWorld;
            case "Water world": return WaterWorld;
            case "High metal content body": return HighMetalContentPlanet;
            case "Icy body": return IcyBody;
            case "Metal rich body": return MetalRichBody;
            case "Rocky body": return RockyBody;
            case "Rocky ice body": return RockyIceBody;
            case "Sudarsky class I gas giant": return ClassIGasGiant;
            case "Sudarsky class II gas giant": return ClassIIGasGiant;
            case "Sudarsky class III gas giant": return ClassIIIGasGiant;
            case "Sudarsky class IV gas giant": return ClassIVGasGiant;
            case "Sudarsky class V gas giant": return ClassVGasGiant;
            case "Gas giant with ammonia based life": return GasGiantwithAmmoniabasedLife;
            case "Gas giant with water based life": return GasGiantwithWaterbasedLife;
            case "Heliumrich gas giant": return HeliumRichGasGiant;
            case "Water giant": return WaterGiant;
        }
        return null;
    }
}
