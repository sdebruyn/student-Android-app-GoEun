package be.muel.sa.data;

import java.util.List;

import be.muel.sa.entities.Information;
import be.muel.sa.entities.PlaceOfInterest;
import be.muel.sa.entities.Room;

/**
 * Created by Samuel on 29/04/2014.
 */
public class CachedApiObjects {
    private static CachedApiObjects ourInstance = new CachedApiObjects();

    public static synchronized CachedApiObjects getInstance() {
        return ourInstance;
    }

    private Information infoObject;
    private List<Room> roomList;
    private List<PlaceOfInterest> pOIList;

    private CachedApiObjects() {
    }

    public Information getInfoObject() {
        return infoObject;
    }

    public void setInfoObject(Information infoObject) {
        this.infoObject = infoObject;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<PlaceOfInterest> getpOIList() {
        return pOIList;
    }

    public void setpOIList(List<PlaceOfInterest> pOIList) {
        this.pOIList = pOIList;
    }
}
