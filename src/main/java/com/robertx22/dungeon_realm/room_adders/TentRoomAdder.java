package com.robertx22.dungeon_realm.room_adders;


import com.robertx22.library_of_exile.dimension.structure.dungeon.RoomType;

public class TentRoomAdder extends BaseRoomAdder {

    public TentRoomAdder() {
        super();
    }

    @Override
    public void addAllRooms() {

        add("4", RoomType.CURVED_HALLWAY);

        add("6", RoomType.END);

        add("0", RoomType.ENTRANCE);

        add("3", RoomType.FOUR_WAY);

        add("1", RoomType.STRAIGHT_HALLWAY);
        add("trader0", RoomType.STRAIGHT_HALLWAY);

        add("boss1", RoomType.TRIPLE_HALLWAY);
        add("2", RoomType.TRIPLE_HALLWAY);

    }
}
