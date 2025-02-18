package com.robertx22.dungeon_realm.database.data_blocks.mobs;

import com.robertx22.dungeon_realm.configs.DungeonConfig;
import com.robertx22.dungeon_realm.main.DataBlockTags;
import com.robertx22.dungeon_realm.main.DungeonMain;
import com.robertx22.dungeon_realm.stat_util.MobPackSizeEffect;
import com.robertx22.library_of_exile.database.map_data_block.MapBlockCtx;
import com.robertx22.library_of_exile.database.map_data_block.MapDataBlock;
import com.robertx22.library_of_exile.util.wiki.WikiEntry;
import com.robertx22.library_of_exile.utils.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EliteMobMB extends MapDataBlock {

    public EliteMobMB(String id) {
        super(id, id);
        this.tags.add(DataBlockTags.CAN_SPAWN_LEAGUE);
    }

    @Override
    public void processImplementationINTERNAL(String s, BlockPos pos, Level level, CompoundTag nbt, MapBlockCtx ctx) {
        EntityType<? extends LivingEntity> type = DungeonMain.DUNGEON_MOB_SPAWNS.getPredeterminedRandom(level, pos).getRandomMob().getType();

        int amount = RandomUtils.RandomRange(DungeonConfig.get().MOB_MIN.get(), DungeonConfig.get().MOB_MAX.get());
        MobPackSizeEffect.tryIncreasePackSize(amount, ctx.libMapData.relicStats);

        MobBuilder.of(type, this, x -> {
            x.amount = amount;
        }).summonMobs(level, pos);
    }

    @Override
    public WikiEntry getWikiEntry() {
        return WikiEntry.of("Spawns a few mobs, [With Mine and Slash installed, the mobs are higher rarity]");
    }

    @Override
    public Class<?> getClassForSerialization() {
        return EliteMobMB.class;
    }
}
