package fr.unice.polytech.al.trafficlight.central.provider;

import java.util.ArrayList;

/**
 * Created by rhoo on 26/10/16.
 */
public class Carrefour {

    private int id;
    private String name;
    private ArrayList<Group> groupList;

    public Carrefour(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Carrefour(int id, String name, ArrayList<Group> group) {
        this.id = id;
        this.name = name;
        groupList = group;
    }

    /** Add a group to the list of group
     *  @param group the group to add
     *   @return 1 if the group already exist, 0 in other case
     */
    public int addGroup(Group group) {
        if(groupList.contains(group))
            return 1;
        groupList.add(group);
        return 0;
    }

    /** Return carrefour id
     *  @return carrefour id
     */
    public int getId() {
        return id;
    }

    /** Set a new id for the carrefour
     *  @param id the new id of the carrefour
     */
    public void setId(int id) {
        this.id = id;
    }
    /** Return carrefour name
     * @return carrefour name
     */
    public String getName() {
        return name;
    }

    /** Set a new name for the carrefour
     *  @param name the new name of the carrefour
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Return carrefour list of group
     *  @return a list who contains all the group of the carrefour
     */
    public ArrayList<Group> getGroupList() {
        return groupList;
    }
}
