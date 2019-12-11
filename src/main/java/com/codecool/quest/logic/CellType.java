package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty", true),
    WALL("wall", false),
    BONFIRE("bonfire", false),
    BRIDGE("bridge",true),
    BOAT("boat",false),
    CANDLESTAND("candleStand",false),
    FLOOR("floor",true),
    GRASSONE("grass1",true),
    GRASSTWO("grass2",true),
    HOUSE("house",false),
    OPENDOOR("openDoor",true),
    REMAINS("monsterRemains",true),
    SIGN("sign",false),
    STONEFLOOR("stoneFloor",true),
    TREE("tree",false),
    TREES("trees",false),
    TREETRUNK("treeTrunk",false),
    WALLDOWN("wallDown",false),
    WALLLEFT("wallLeft",false),
    WALLLEFTDOWNCORNER("wallLeftDownCorner",false),
    WALLRIGHTDOWNCORNER("wallRightDownCorner",false),
    WALLLEFTTOPCORNER("wallLeftTopCorner",false),
    WALLRIGHTTOPCORNER("wallRightTopCorner",false),
    WALLUP("wallUp",false),
    WALLRIGHT("wallRight",false),
    WATER("water",false);

    private final String tileName;
    private final boolean stepable;

    CellType(String tileName, boolean stepable) {
        this.tileName = tileName;
        this.stepable = stepable;
    }

    public String getTileName() {
        return tileName;
    }

    public boolean isStepable() {
        return stepable;
    }
}
