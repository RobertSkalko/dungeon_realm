package com.robertx22.dungeon_realm.room_adders;


import com.robertx22.library_of_exile.dimension.structure.dungeon.RoomType;

public class BrickRoomAdder extends BaseRoomAdder {

    public BrickRoomAdder() {
        super();
    }


    @Override
    public void addAllRooms() {

        add("boss_exit_hidden_lever", RoomType.END);
        add("trader0", RoomType.END);

        add("0", RoomType.ENTRANCE);
        add("2", RoomType.ENTRANCE);

        add("hidden_ceiling", RoomType.FOUR_WAY);
        add("simple0", RoomType.FOUR_WAY);

        add("hidden_big_chest", RoomType.STRAIGHT_HALLWAY);
        add("lava_spiders", RoomType.STRAIGHT_HALLWAY);
        add("simple1", RoomType.STRAIGHT_HALLWAY);

        add("boss0", RoomType.TRIPLE_HALLWAY);

    }
}
