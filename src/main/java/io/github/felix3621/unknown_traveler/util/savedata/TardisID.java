package io.github.felix3621.unknown_traveler.util.savedata;

public class TardisID {
    public static Integer getID() {
        int tardis_id = SDControl.tardis_id;
        SDControl.tardis_id = tardis_id+1;
        SDControl.store();
        return tardis_id;
    }
}
