package com.robertx22.dungeon_realm.structure;

import com.google.gson.JsonSyntaxException;
import com.robertx22.dungeon_realm.main.DungeonMain;
import com.robertx22.library_of_exile.dimension.MapDataFinder;
import com.robertx22.library_of_exile.dimension.MapDimensionInfo;
import com.robertx22.library_of_exile.utils.LoadSave;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DungeonMapCapability implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public Level world;

    public static final ResourceLocation RESOURCE = new ResourceLocation(DungeonMain.MODID, "world_data");
    public static Capability<DungeonMapCapability> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {
    });

    transient final LazyOptional<DungeonMapCapability> supp = LazyOptional.of(() -> this);

    public DungeonMapCapability(Level world) {
        this.world = world;
    }

    public static DungeonMapCapability get(Level entity) {
        return entity.getServer().overworld().getCapability(INSTANCE).orElse(new DungeonMapCapability(entity));
    }

    public DungeonWorldData data = new DungeonWorldData();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == INSTANCE) {
            return supp.cast();
        }
        return LazyOptional.empty();

    }

    @Override
    public CompoundTag serializeNBT() {
        var nbt = new CompoundTag();

        try {

            LoadSave.Save(data, nbt, "data");
            
        } catch (Exception e) {
            e.printStackTrace();
        }


        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

        try {
            this.data = LoadSave.loadOrBlank(DungeonWorldData.class, new DungeonWorldData(), nbt, "data", new DungeonWorldData());


        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

    }

    public static MapDataFinder<DungeonMapData> DATA_GETTER = new MapDataFinder<>() {
        @Override
        public DungeonMapData getData(Pos pos) {
            return get(pos.level).data.data.getData(this.getInfo().structure, pos.pos);
        }

        @Override
        public MapDimensionInfo getInfo() {
            return DungeonMain.MAP;
        }

    };
}
