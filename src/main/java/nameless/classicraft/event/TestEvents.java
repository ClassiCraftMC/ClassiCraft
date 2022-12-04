package nameless.classicraft.event;

import nameless.classicraft.api.event.ItemEntityTickEvent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sereneseasons.api.season.ISeasonState;
import sereneseasons.api.season.Season;
import sereneseasons.handler.season.SeasonHandler;
import sereneseasons.season.SeasonSavedData;

@Mod.EventBusSubscriber
public class TestEvents {

    @SubscribeEvent
    public static void changeLooseRock(ItemEntityTickEvent event) {
        /**
        ItemEntity entity = event.getEntity();
        if (((ISeasonState) entity.getLevel()).getSeason()
                == Season.SubSeason.EARLY_AUTUMN.getSeason()) {
            if (entity.getItem().is(Items.BONE)) {
                if (entity.getItem().getCount() == 2) {
                    if (entity.getLevel().getBiome(entity.getOnPos()).is(Tags.Biomes.IS_HOT)) {
                        //TODO
                    }
                }
            }
        }*/
    }
}
