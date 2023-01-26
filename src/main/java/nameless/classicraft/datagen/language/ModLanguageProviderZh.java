/*
 * ClassiCraft - ClassiCraftMC
 * Copyright (C) 2018-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package nameless.classicraft.datagen.language;

import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.init.ModBlocks;
import nameless.classicraft.init.ModEntities;
import nameless.classicraft.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProviderZh extends LanguageProvider {

    public ModLanguageProviderZh(DataGenerator gen) {
        super(gen.getPackOutput(), ClassiCraftMod.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.TORCH_LIT.get(), "点燃的火把");
        add(ModItems.TORCH_UNLIT.get(), "熄灭的火把");
        add(ModItems.SOUL_TORCH_UNLIT.get(), "熄灭的灵魂火把");
        add(ModBlocks.REAL_TORCH.get(), "火把");
        add(ModItems.DEBUG_BURN_TIME_STICK.get(), "燃烧时间检测棒");
        add(ModItems.DEPTH_METER.get(), "深度计");
        add(ModBlocks.QUICKSAND.get(), "流沙");
        add(ModBlocks.RED_QUICKSAND.get(), "红沙流沙");
        add(ModBlocks.CHARCOAL_BLOCK.get(), "木炭块");
        add(ModBlocks.CACTUS_BALL.get(), "仙人球");
        add(ModBlocks.ROSE.get(), "玫瑰");
        add(ModItems.TROUT_SPAWN_EGG.get(), "鳟鱼刷怪蛋");
        add(ModItems.TROUT_BUCKET.get(), "鳟鱼桶");
        add(ModItems.TROUT.get(), "鳟鱼");
        add(ModItems.COOKED_TROUT.get(), "熟鳟鱼");
        add(ModItems.TALLOW.get(), "油脂");
        add(ModBlocks.TALLOW_BLOCK.get(), "油脂块");
        add("info.classicraft.burntime", "燃烧时间");
        add("info.classicraft.minutes", "分钟");
        add("info.classicraft.depth.sky_land", "的天域");
        add("info.classicraft.depth.sky", "的高空");
        add("info.classicraft.depth.cloud", "的云层");
        add("info.classicraft.depth.base_cloud", "的低空");
        add("info.classicraft.depth.sea_level", "的海平面");
        add("info.classicraft.depth.surface", "的地表");
        add("info.classicraft.depth.underground", "的地底");
        add("info.classicraft.depth.deep_underground", "的深层地底");
        add("info.classicraft.depth.bedrock", "的基岩层");
        add("info.classicraft.depth.void", "的虚空");
        add("info.classicraft.height", "当前位于高度");
        add("level.classicraft.fresh", "新鲜的");
        add("level.classicraft.stale", "陈旧的");
        add("level.classicraft.spoiled", "变质的");
        add("level.classicraft.rotten", "腐坏的");
        add("info.classicraft.rot", "%s天后腐烂");
        add("info.classicraft.rotting_speed", "腐烂速度：%s");
        add("info.classicraft.shift_press", "按下shift键显示更多信息");
        add("info.classicraft.food.nutrition", "营养价值");
        add("info.classicraft.food.saturation", "恢复价值");
        add(ModItems.OCEAN_SHARK_SPAWN_EGG.get(), "海洋鲨鱼生成蛋");
        add(ModItems.LIVING_DEAD_SPAWN_EGG.get(), "活死人生成蛋");
        add(ModItems.COOKED_EGG.get(), "煎蛋");
        add(ModItems.NETHER_MUSHROOM_STEW.get(), "下界蘑菇汤");
        add(ModItems.ROTTEN_FOOD.get(), "腐烂的食物");
        add(ModBlocks.MOSSY_BRICKS.get(), "苔红砖块");
        add(ModBlocks.MOSSY_BRICKS_STAIRS.get(), "苔红砖楼梯");
        add(ModBlocks.MOSSY_BRICKS_WALL.get(), "苔红砖墙");
        add(ModBlocks.MOSSY_BRICKS_SLAB.get(), "苔红台阶");
        add(ModBlocks.CRACKED_BRICKS.get(), "裂纹红砖块");
        add(ModBlocks.CRACKED_BRICKS_STAIRS.get(), "裂纹红砖楼梯");
        add(ModBlocks.CRACKED_BRICKS_WALL.get(), "裂纹红砖墙");
        add(ModBlocks.CRACKED_BRICKS_SLAB.get(), "裂纹红砖台阶");
        add(ModBlocks.SULFUR_ORE.get(), "硫磺矿");
        add(ModItems.SULFUR.get(), "硫磺");
        add(ModBlocks.SMOOTH_STONE_WALL.get(), "平滑石头墙");
        add(ModBlocks.SMOOTH_STONE_STAIRS.get(), "平滑石头楼梯");
        add(ModBlocks.STONE_WALL.get(), "石头墙");
        add(ModBlocks.FLINT_BLOCK.get(), "燧石块");
        add(ModBlocks.CHISELED_SOUL_SANDSTONE.get(), "雕纹灵魂砂岩");
        add(ModBlocks.CHISELED_QUARTZ_SANDSTONE.get(), "雕纹石英砂岩");
        add(ModBlocks.CUT_SOUL_SANDSTONE.get(), "切制灵魂砂岩");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE.get(), "切制石英砂岩");
        add(ModBlocks.QUARTZ_QUICKSAND.get(), "石英沙流沙");
        add(ModBlocks.QUARTZ_SAND.get(), "石英沙");
        add(ModBlocks.QUARTZ_SANDSTONE.get(), "石英砂岩");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS.get(), "石英砂岩砖");
        add(ModBlocks.RED_SANDSTONE_BRICKS.get(), "红砂岩砖");
        add(ModBlocks.SANDSTONE_BRICKS.get(), "砂岩砖");
        add(ModBlocks.SOUL_SANDSTONE.get(), "灵魂砂岩");
        add(ModBlocks.SOUL_QUICKSAND.get(), "灵魂沙流沙");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS.get(), "灵魂砂岩砖");
        add(ModBlocks.CRACKED_STONE_BRICKS_SLAB.get(), "裂纹石砖台阶");
        add(ModBlocks.CRACKED_STONE_BRICKS_STAIRS.get(), "裂纹石砖楼梯");
        add(ModBlocks.CRACKED_STONE_BRICKS_WALL.get(), "裂纹石砖墙");
        add(ModBlocks.POLISHED_GRANITE_WALL.get(), "磨制花岗岩墙");
        add(ModBlocks.POLISHED_ANDESITE_WALL.get(), "磨制安山岩墙");
        add(ModBlocks.POLISHED_DIORITE_WALL.get(), "磨制闪长岩墙");
        add(ModBlocks.INFESTED_MOSSY_COBBLESTONE.get(), "被虫蚀的苔石");
        add(ModBlocks.DEEPSLATE_WALL.get(), "深板岩墙");
        add(ModBlocks.DEEPSLATE_STAIRS.get(), "深板岩楼梯");
        add(ModBlocks.DEEPSLATE_SLAB.get(), "深板岩台阶");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_WALL.get(), "裂纹深板岩砖墙");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_STAIRS.get(), "裂纹深板岩砖楼梯");
        add(ModBlocks.CRACKED_DEEPSLATE_BRICKS_SLAB.get(), "裂纹深板岩砖台阶");
        add(ModBlocks.INFESTED_CHISELED_DEEPSLATE.get(), "被虫蚀的雕纹深板岩");
        add(ModBlocks.INFESTED_COBBLED_DEEPSLATE.get(), "被虫蚀的深板岩圆石");
        add(ModBlocks.INFESTED_DEEPSLATE_BRICKS.get(), "被虫蚀的深板岩砖");
        add(ModBlocks.INFESTED_DEEPSLATE_TILES.get(), "被虫蚀的深板岩瓦");
        add(ModBlocks.SMOOTH_SANDSTONE_WALL.get(), "平滑砂岩墙");
        add(ModBlocks.CUT_SANDSTONE_WALL.get(), "切制砂岩墙");
        add(ModBlocks.CUT_SANDSTONE_STAIRS.get(), "切制砂岩楼梯");
        add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL.get(), "平滑红砂岩墙");
        add(ModBlocks.CUT_RED_SANDSTONE_STAIRS.get(), "切制红砂岩楼梯");
        add(ModBlocks.CUT_RED_SANDSTONE_WALL.get(), "切制红砂岩墙");
        add(ModBlocks.SOUL_SANDSTONE_SLAB.get(), "灵魂砂岩台阶");
        add(ModBlocks.SOUL_SANDSTONE_STAIRS.get(), "灵魂砂岩楼梯");
        add(ModBlocks.SOUL_SANDSTONE_WALL.get(), "灵魂砂岩墙");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), "平滑灵魂砂岩");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(), "平滑灵魂砂岩楼梯");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(), "平滑灵魂砂岩台阶");
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_WALL.get(), "平滑灵魂砂岩墙");
        add(ModBlocks.CUT_SOUL_SANDSTONE_WALL.get(), "切制灵魂砂岩墙");
        add(ModBlocks.CUT_SOUL_SANDSTONE_STAIRS.get(), "切制灵魂砂岩楼梯");
        add(ModBlocks.END_STONE_SLAB.get(), "末地石台阶");
        add(ModBlocks.END_STONE_STAIRS.get(), "末地石楼梯");
        add(ModBlocks.END_STONE_WALL.get(), "末地石墙");
        add(ModBlocks.SANDSTONE_BRICKS_SLAB.get(), "砂岩砖台阶");
        add(ModBlocks.SANDSTONE_BRICKS_STAIRS.get(), "砂岩砖楼梯");
        add(ModBlocks.SANDSTONE_BRICKS_WALL.get(), "砂岩砖墙");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS_SLAB.get(), "石英砂岩砖台阶");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS_STAIRS.get(), "石英砂岩砖楼梯");
        add(ModBlocks.QUARTZ_SANDSTONE_BRICKS_WALL.get(), "石英砂岩砖墙");
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get(), "平滑石英砂岩台阶");
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get(), "平滑石英砂岩楼梯");
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_WALL.get(), "平滑石英砂岩墙");
        add(ModBlocks.NETHERRACK_SLAB.get(), "下界岩台阶");
        add(ModBlocks.NETHERRACK_STAIRS.get(), "下界岩楼梯");
        add(ModBlocks.NETHERRACK_WALL.get(), "下界岩墙");
        add(ModBlocks.RED_SANDSTONE_BRICKS_SLAB.get(), "红砂岩砖台阶");
        add(ModBlocks.RED_SANDSTONE_BRICKS_STAIRS.get(), "红砂岩砖楼梯");
        add(ModBlocks.RED_SANDSTONE_BRICKS_WALL.get(), "红砂岩砖墙");
        add(ModBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS.get(), "被虫蚀的裂纹深板岩砖");
        add(ModBlocks.INFESTED_CRACKED_DEEPSLATE_TILES.get(), "被虫蚀的裂纹深板岩瓦");
        add(ModBlocks.PRISMARINE_BRICKS_WALL.get(), "海晶石砖墙");
        add(ModBlocks.DARK_PRISMARINE_WALL.get(), "暗海晶石墙");
        add(ModBlocks.CRACKED_DEEPSLATE_TILES_WALL.get(), "裂纹深板岩瓦墙");
        add(ModBlocks.CRACKED_DEEPSLATE_TILES_STAIRS.get(), "裂纹深板岩瓦楼梯");
        add(ModBlocks.CRACKED_DEEPSLATE_TILES_SLAB.get(), "裂纹深板岩瓦台阶");
        add(ModBlocks.SMOOTH_BASALT_SLAB.get(), "平滑玄武岩台阶");
        add(ModBlocks.SMOOTH_BASALT_STAIRS.get(), "平滑玄武岩楼梯");
        add(ModBlocks.SMOOTH_BASALT_WALL.get(), "平滑玄武岩墙");
        add(ModBlocks.FLINT_BLOCK_SLAB.get(), "燧石块台阶");
        add(ModBlocks.FLINT_BLOCK_STAIRS.get(), "燧石块楼梯");
        add(ModBlocks.FLINT_BLOCK_WALL.get(), "燧石块墙");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS_SLAB.get(), "灵魂砂岩砖台阶");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS_STAIRS.get(), "灵魂砂岩砖楼梯");
        add(ModBlocks.SOUL_SANDSTONE_BRICKS_WALL.get(), "灵魂砂岩砖墙");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get(), "切制石英砂岩台阶");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE_STAIRS.get(), "切制石英砂岩楼梯");
        add(ModBlocks.CUT_QUARTZ_SANDSTONE_WALL.get(), "切制石英砂岩墙");
        add(ModBlocks.QUARTZ_BRICKS_WALL.get(), "石英砖墙");
        add(ModBlocks.QUARTZ_BRICKS_SLAB.get(), "石英砖台阶");
        add(ModBlocks.QUARTZ_BRICKS_STAIRS.get(), "石英砖楼梯");
        add("itemGroup.classicraft.common", "天工开物|通常");
        add("itemGroup.classicraft.building_blocks", "天工开物|建筑方块");
        add("itemGroup.classicraft.material", "天工开物|材料");
        add("itemGroup.classicraft.natural_blocks", "天工开物|自然方块");
        add(ModBlocks.WHITE_WOOL_STAIRS.get(), "白色羊毛楼梯");
        add(ModBlocks.WHITE_WOOL_SLAB.get(), "白色羊毛台阶");
        add(ModBlocks.ORANGE_WOOL_STAIRS.get(), "橙色羊毛楼梯");
        add(ModBlocks.ORANGE_WOOL_SLAB.get(), "橙色羊毛台阶");
        add(ModBlocks.MAGENTA_WOOL_STAIRS.get(), "洋红色羊毛楼梯");
        add(ModBlocks.MAGENTA_WOOL_SLAB.get(), "洋红色羊毛台阶");
        add(ModBlocks.LIGHT_BLUE_WOOL_STAIRS.get(), "浅蓝色羊毛楼梯");
        add(ModBlocks.LIGHT_BLUE_WOOL_SLAB.get(), "浅蓝色羊毛台阶");
        add(ModBlocks.YELLOW_WOOL_STAIRS.get(), "黄色羊毛楼梯");
        add(ModBlocks.YELLOW_WOOL_SLAB.get(), "黄色羊毛台阶");
        add(ModBlocks.LIME_WOOL_STAIRS.get(), "柠檬色羊毛楼梯");
        add(ModBlocks.LIME_WOOL_SLAB.get(), "柠檬色羊毛台阶");
        add(ModBlocks.PINK_WOOL_STAIRS.get(), "粉色羊毛楼梯");
        add(ModBlocks.PINK_WOOL_SLAB.get(), "粉色羊毛台阶");
        add(ModBlocks.GRAY_WOOL_STAIRS.get(), "灰色羊毛楼梯");
        add(ModBlocks.GRAY_WOOL_SLAB.get(), "灰色羊毛台阶");
        add(ModBlocks.LIGHT_GRAY_WOOL_STAIRS.get(), "浅灰色羊毛楼梯");
        add(ModBlocks.LIGHT_GRAY_WOOL_SLAB.get(), "浅灰色羊毛台阶");
        add(ModBlocks.CYAN_WOOL_STAIRS.get(), "青色羊毛楼梯");
        add(ModBlocks.CYAN_WOOL_SLAB.get(), "青色羊毛台阶");
        add(ModBlocks.PURPLE_WOOL_STAIRS.get(), "紫色羊毛楼梯");
        add(ModBlocks.PURPLE_WOOL_SLAB.get(), "紫色羊毛台阶");
        add(ModBlocks.BLUE_WOOL_STAIRS.get(), "蓝色羊毛楼梯");
        add(ModBlocks.BLUE_WOOL_SLAB.get(), "蓝色羊毛台阶");
        add(ModBlocks.BROWN_WOOL_STAIRS.get(), "棕色羊毛楼梯");
        add(ModBlocks.BROWN_WOOL_SLAB.get(), "棕色羊毛台阶");
        add(ModBlocks.GREEN_WOOL_STAIRS.get(), "绿色羊毛楼梯");
        add(ModBlocks.GREEN_WOOL_SLAB.get(), "绿色羊毛台阶");
        add(ModBlocks.RED_WOOL_STAIRS.get(), "红色羊毛楼梯");
        add(ModBlocks.RED_WOOL_SLAB.get(), "红色羊毛台阶");
        add(ModBlocks.BLACK_WOOL_STAIRS.get(), "黑色羊毛楼梯");
        add(ModBlocks.BLACK_WOOL_SLAB.get(), "黑色羊毛台阶");
        add(ModBlocks.QUARTZ_SANDSTONE_WALL.get(), "石英砂岩墙");
        add(ModBlocks.QUARTZ_SANDSTONE_STAIRS.get(), "石英砂岩楼梯");
        add(ModBlocks.QUARTZ_SANDSTONE_SLAB.get(), "石英砂岩台阶");
        add(ModBlocks.POLISHED_BASALT_SLAB.get(), "磨制玄武岩台阶");
        add(ModBlocks.POLISHED_BASALT_STAIRS.get(), "磨制玄武岩楼梯");
        add(ModBlocks.POLISHED_BASALT_WALL.get(), "磨制玄武岩墙");
        add(ModBlocks.CRIMSON_NYLIUM_SLAB.get(), "绯红菌岩台阶");
        add(ModBlocks.CRIMSON_NYLIUM_STAIRS.get(), "绯红菌岩楼梯");
        add(ModBlocks.CRIMSON_NYLIUM_WALL.get(), "绯红菌岩墙");
        add(ModBlocks.WARPED_NYLIUM_SLAB.get(), "诡异菌岩台阶");
        add(ModBlocks.WARPED_NYLIUM_STAIRS.get(), "诡异菌岩楼梯");
        add(ModBlocks.WARPED_NYLIUM_WALL.get(), "诡异菌岩墙");
        add(ModBlocks.CRACKED_NETHER_BRICKS_SLAB.get(), "裂纹下界岩砖台阶");
        add(ModBlocks.CRACKED_NETHER_BRICKS_STAIRS.get(), "裂纹下界岩砖楼梯");
        add(ModBlocks.CRACKED_NETHER_BRICKS_WALL.get(), "裂纹下界岩砖墙");
        add(ModBlocks.CRIMSON_NETHER_BRICKS_FENCE.get(), "绯红下界砖栅栏");
        add(ModBlocks.QUARTZ_WALL.get(), "石英墙");
        add(ModBlocks.PURPUR_BLOCK_WALL.get(), "紫珀墙");
        add(ModBlocks.SMOOTH_QUARTZ_WALL.get(), "平滑石英墙");
        add(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_STAIRS.get(), "裂纹磨制黑石砖楼梯");
        add(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_SLAB.get(), "裂纹磨制黑石砖台阶");
        add(ModBlocks.CRACKED_POLISHED_BLACKSTONE_BRICKS_WALL.get(), "裂纹磨制黑石砖墙");
        add(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get(), "切制灵魂砂岩台阶");
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get(), "平滑石英砂岩");
        add(ModItems.PHOBOS_OUTPOST_DISC.get(), "§b音乐唱片");
        add("item.classicraft.phobos_outpost_disc.desc", "Scott Lloyd Shelly - Phobos Outpost");
        add(ModItems.DRAGON_FISH_DISC.get(), "§b音乐唱片");
        add("item.classicraft.dragon_fish_disc.desc", "C418 - Dragon Fish");

        pebbleBlock();
        meta();
        entity();
        vanilla();
    }

    private void pebbleBlock() {
        add(ModBlocks.FLINT.get(), "燧石石块");
        add(ModBlocks.ANDESITE_PEBBLE.get(), "安山岩石块");
        add(ModBlocks.COBBLESTONE_PEBBLE.get(), "圆石石块");
        add(ModBlocks.DIORITE_PEBBLE.get(), "闪长岩石块");
        add(ModBlocks.GRANITE_PEBBLE.get(), "花岗岩石块");
        add(ModBlocks.RED_SANDSTONE_PEBBLE.get(), "红砂岩石块");
        add(ModBlocks.SANDSTONE_PEBBLE.get(), "砂岩石块");
        add(ModBlocks.DEEPSLATE_PEBBLE.get(), "深板岩石块");
        add(ModBlocks.QUARTZ_SANDSTONE_PEBBLE.get(), "石英砂岩石块");
        add(ModBlocks.SOUL_SANDSTONE_PEBBLE.get(), "灵魂砂岩石块");
        add(ModBlocks.NETHERRACK_PEBBLE.get(), "下界岩石块");
        add(ModBlocks.END_STONE_PEBBLE.get(), "末地岩石块");
        add(ModBlocks.BASALT_PEBBLE.get(), "玄武岩石块");
        add(ModBlocks.BLACKSTONE_PEBBLE.get(), "黑石石块");
        add(ModBlocks.PRISMARINE.get(), "海晶岩石块");
        add(ModBlocks.QUARTZ_PEBBLE.get(), "石英石块");
    }

    private void entity() {
        add(ModEntities.OCEAN_SHARK_ENTITY.get(), "海洋鲨鱼");
        add(ModEntities.TROUT_ENTITY.get(), "鳟鱼");
        add(ModEntities.LIVING_DEAD.get(), "活死人");
    }

    private void vanilla() {
        add(Blocks.NETHER_WART_BLOCK, "绯红疣块");
        add(Blocks.NETHER_SPROUTS, "诡异苗");
        add(Items.NETHER_WART, "绯红疣");
        add(Blocks.RED_NETHER_BRICKS, "绯红下界砖块");
        add(Blocks.RED_NETHER_BRICK_STAIRS, "绯红下界砖楼梯");
        add(Blocks.RED_NETHER_BRICK_SLAB, "绯红下界砖台阶");
        add(Blocks.RED_NETHER_BRICK_WALL, "绯红下界砖墙");
    }

    private void meta() {
        add("item.classicraft.adze.cobblestone_adze", "圆石刨铲器");
        add("item.classicraft.adze.deepslate_adze", "深板岩刨铲器");
        add("item.classicraft.adze.flint_adze", "燧石刨铲器");
        add("item.classicraft.adze.blackstone_adze", "黑石刨铲器");
        add("item.classicraft.adze.quartz_adze", "石英刨铲器");
        add("item.classicraft.awl.cobblestone_awl", "圆石锥形器");
        add("item.classicraft.awl.deepslate_awl", "深板岩锥形器");
        add("item.classicraft.awl.flint_awl", "燧石锥形器");
        add("item.classicraft.awl.blackstone_awl", "黑石锥形器");
        add("item.classicraft.awl.quartz_awl", "石英锥形器");
        add("item.classicraft.chopper.cobblestone_chopper", "圆石砍砸器");
        add("item.classicraft.chopper.deepslate_chopper", "深板岩砍砸器");
        add("item.classicraft.chopper.flint_chopper", "燧石砍砸器");
        add("item.classicraft.chopper.blackstone_chopper", "黑石砍砸器");
        add("item.classicraft.chopper.quartz_chopper", "石英砍砸器");
        add("item.classicraft.pebble.andesite_pebble", "安山岩石块");
        add("item.classicraft.pebble.basalt_pebble", "玄武岩石块");
        add("item.classicraft.pebble.blackstone_pebble", "黑石石块");
        add("item.classicraft.pebble.cobblestone_pebble", "圆石石块");
        add("item.classicraft.pebble.deepslate_pebble", "深板岩石块");
        add("item.classicraft.pebble.diorite_pebble", "闪长岩石块");
        add("item.classicraft.pebble.granite_pebble", "花岗岩石块");
        add("item.classicraft.pebble.red_sandstone_pebble", "红砂岩石块");
        add("item.classicraft.pebble.end_stone_pebble", "末地石石块");
        add("item.classicraft.pebble.netherrack_pebble", "下界岩石块");
        add("item.classicraft.pebble.quartz_sandstone_pebble", "石英砂岩石块");
        add("item.classicraft.pebble.soul_sandstone_pebble", "灵魂砂岩石块");
        add("item.classicraft.pebble.sandstone_pebble", "砂岩石块");
        add("item.classicraft.point.cobblestone_point", "圆石尖状器");
        add("item.classicraft.point.deepslate_point", "深板岩尖状器");
        add("item.classicraft.point.flint_point", "燧石尖状器");
        add("item.classicraft.point.blackstone_point", "黑石尖状器");
        add("item.classicraft.point.quartz_point", "石英尖状器");
        add("item.classicraft.scraper.cobblestone_scraper", "圆石刮削器");
        add("item.classicraft.scraper.deepslate_scraper", "深板岩刮削器");
        add("item.classicraft.scraper.flint_scraper", "燧石刮削器");
        add("item.classicraft.scraper.blackstone_scraper", "黑石刮削器");
        add("item.classicraft.scraper.quartz_scraper", "石英刮削器");
    }
}
