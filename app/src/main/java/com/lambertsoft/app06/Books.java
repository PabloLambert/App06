package com.lambertsoft.app06;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.*;

/**
 * Created by InnovaTI on 30-12-14.
 */
public class Books extends GenericJson {

    @Key ("_id")
    private String id;
    @Key
    private String Name;
    @Key
    private String Author;
    @Key
    private String DateOfPublication;
    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;

    public Books(){}  //GenericJson classes must have a public empty constructor

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }


}
