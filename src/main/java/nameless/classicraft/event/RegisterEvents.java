package nameless.classicraft.event;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClassiCraftMod.MOD_ID)
public class RegisterEvents {

    @SubscribeEvent
    public static void sendSpawnLogic(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.OCEAN_SHARK_ENTITY.get(),
                (WaterAnimal::checkSurfaceWaterAnimalSpawnRules));
        event.register(ModEntities.TROUT_ENTITY.get(),
                (WaterAnimal::checkSurfaceWaterAnimalSpawnRules));
    }

    private static boolean validMonsterSpawn(ServerLevelAccessor level, BlockPos pos, EntityType<?> type, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL
                && Monster.isDarkEnoughToSpawn(level, pos, random)
                && checkMobSpawnRules(type, level, MobSpawnType.NATURAL, pos, random);
    }

    public static boolean checkMobSpawnRules(EntityType<?> pType, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        var blockpos = pPos.below();
        return pSpawnType == MobSpawnType.SPAWNER || pLevel.getBlockState(blockpos).isValidSpawn(pLevel, blockpos, pType);
    }
}
