package com.robertx22.dungeon_realm.main;

import com.robertx22.dungeon_realm.item.ObeliskItemNbt;
import com.robertx22.library_of_exile.main.ApiForgeEvents;
import com.robertx22.library_of_exile.tooltip.ExileTooltipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ObeliskClient {

    public static void init() {


        ApiForgeEvents.registerForgeEvent(ItemTooltipEvent.class, event -> {
            if (event.getItemStack().is(DungeonEntries.DUNGEON_MAP_ITEM.get())) {
                ItemStack stack = event.getItemStack();

                if (ObeliskItemNbt.OBELISK_MAP.has(stack)) {
                    var map = ObeliskItemNbt.OBELISK_MAP.loadFrom(stack);

                    var tip = event.getToolTip();

                    tip.add(Component.empty());

                    tip.add(Component.empty());

                    tip.add(DungeonWords.MAP_ITEM_USE_INFO.get().withStyle(ChatFormatting.BLUE));

                    ExileTooltipUtils.removeBlankLines(tip, ExileTooltipUtils.RemoveOption.ONLY_DOUBLE_BLANK_LINES);

                }
            }
        });
    }
}
