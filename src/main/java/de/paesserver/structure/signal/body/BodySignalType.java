package de.paesserver.structure.signal.body;

enum BodySignalType {
    $SAA_SignalType_Geological, $SAA_SignalType_Biological;

    public static BodySignalType extractBodySignalType(String s){
        switch (s){
            case "$SAA_SignalType_Biological;":
                return $SAA_SignalType_Biological;
            case "$SAA_SignalType_Geological":
                return $SAA_SignalType_Geological;
            default:
                return null;
        }
    }
}
